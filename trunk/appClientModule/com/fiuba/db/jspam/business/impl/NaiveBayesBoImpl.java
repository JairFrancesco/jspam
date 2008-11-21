package com.fiuba.db.jspam.business.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import com.fiuba.db.jspam.business.SpamFilterBo;
import com.fiuba.db.jspam.entidad.MailClasificado;
import com.fiuba.db.jspam.entidad.Word;
import com.fiuba.db.jspam.exception.IdExistenteException;

/**
 * @author Demian
 *
 */
public class NaiveBayesBoImpl implements SpamFilterBo {	

	/* (non-Javadoc)
	 * @see com.fiuba.db.jspam.business.SpamFilterBo#aprender(java.util.Collection)
	 */
	public void aprender(Collection<MailClasificado> mails) {
		//contiene la cantidad de veces que una palabra aparece en un mail spam
		HashMap<String, Integer> palabrasEnSpam = new HashMap<String, Integer>();
		//contiene la cantidad de veces que una palabra aparece en un mail que NO es spam
		HashMap<String, Integer> palabrasEnNoSpam = new HashMap<String, Integer>();
		//totales de mails spam y no spam
		int totalMailsSpam = 0;
		int totalMailsNoSpam = 0;
		//por cada mail
		for (MailClasificado mailClasificado : mails) {
			HashSet<String> wordProcessed = new HashSet<String>();
			//por cada palabra del mail
			for (String word : mailClasificado.getNonStopWords()) {
				//se procesa un sola vez una palabra dentro de un mail
				if (!wordProcessed.contains(word)){
					wordProcessed.add(word);
					//si es spam
					if (mailClasificado.isSpam()){
						if (palabrasEnSpam.containsKey(word)){
							palabrasEnSpam.put(word, palabrasEnSpam.get(word)+1);
						} else {
							palabrasEnSpam.put(word, new Integer(1));
						}
					} else {
						//si no es spam
						if (palabrasEnNoSpam.containsKey(word)){
							palabrasEnNoSpam.put(word, palabrasEnNoSpam.get(word)+1);
						} else {
							palabrasEnNoSpam.put(word, new Integer(1));
						}
					}
				}
			}
			//se procesaron todas las palabras del mail
			if (mailClasificado.isSpam()){
				totalMailsSpam++;
			} else {
				totalMailsNoSpam++;
			}
		}
		
		//guardar las probabilidades inciales.
		registrarProbabilidades(palabrasEnSpam, palabrasEnNoSpam, totalMailsSpam, totalMailsNoSpam);
	}
	
	private void registrarProbabilidades(HashMap<String, Integer> palabrasEnSpam, HashMap<String, Integer> palabrasEnNoSpam, 
			int totalMailsSpam, int totalMailsNoSpam){
		//recorro cada palabra que se encontro en un mail de spam
		for (Entry<String, Integer> entry: palabrasEnSpam.entrySet()) {
			Word word = new Word();
			word.setId(entry.getKey());
			word.setProbabilidadSpam(new BigDecimal(entry.getValue().intValue()).divide(new BigDecimal(totalMailsSpam)));
			word.setProbabilidadNoSpam(new BigDecimal("0"));
			try {
				word.save();
			} catch (IdExistenteException e) {
				word.update();
			}
		}
		
		//lo mismo pero para cada palabra que se encontro en un mail que no es de spam
		for (Entry<String, Integer> entry: palabrasEnNoSpam.entrySet()){
			Word word = new Word();
			word.setId(entry.getKey());
			word.setProbabilidadNoSpam(new BigDecimal(entry.getValue().intValue()).divide(new BigDecimal(totalMailsNoSpam)));
			word.setProbabilidadSpam(new BigDecimal("0"));
			try {
				word.save();
			} catch (IdExistenteException e) {
				word.update();
			}
		}
	}	
}
