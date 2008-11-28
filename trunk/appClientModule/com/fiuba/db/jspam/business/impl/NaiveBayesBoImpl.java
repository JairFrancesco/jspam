package com.fiuba.db.jspam.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fiuba.db.jspam.business.SpamFilterBo;
import com.fiuba.db.jspam.entidad.Estadistica;
import com.fiuba.db.jspam.entidad.Mail;
import com.fiuba.db.jspam.entidad.MailClasificado;
import com.fiuba.db.jspam.entidad.MailPreClasificado;
import com.fiuba.db.jspam.entidad.Word;
import com.fiuba.db.jspam.exception.SinEntrenarException;

/**
 * @author Demian
 *
 */
public class NaiveBayesBoImpl implements SpamFilterBo {	
	/**
     * logger.
     */
    private final Log logger = LogFactory.getLog(NaiveBayesBoImpl.class);

	/**
	 * 
	 * {@inheritDoc}
	 */
	public void aprender(Collection<MailPreClasificado> mails) {
		//contiene la cantidad de veces que una palabra aparece en un mail spam
		HashMap<String, Integer> palabrasEnSpam = new HashMap<String, Integer>();
		//contiene la cantidad de veces que una palabra aparece en un mail que NO es spam
		HashMap<String, Integer> palabrasEnNoSpam = new HashMap<String, Integer>();
		//totales de mails spam y no spam
		int totalMailsSpam = 0;
		int totalMailsNoSpam = 0;
		//por cada mail
		for (MailPreClasificado mailClasificado : mails) {
			HashSet<String> wordProcessed = new HashSet<String>();
			//por cada palabra del mail
			for (String word : mailClasificado.getNonStopWords()) {
			    word = word.toLowerCase();
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
        HashMap<String, Word> allWords = new HashMap<String, Word>();	    
		for (Entry<String, Integer> entry: palabrasEnSpam.entrySet()) {
			Word word = new Word();
			word.setId(entry.getKey());
			word.setProbabilidadSpam(new BigDecimal(entry.getValue().intValue()).divide(new BigDecimal(totalMailsSpam), 3, BigDecimal.ROUND_DOWN));
			word.setProbabilidadNoSpam(new BigDecimal("0"));
			allWords.put(word.getId(), word);
		}
		
		//lo mismo pero para cada palabra que se encontro en un mail que no es de spam
		for (Entry<String, Integer> entry: palabrasEnNoSpam.entrySet()){
		    Word word = new Word();
            // busco si ya existe con la probabilidad de spam
            if (allWords.containsKey(entry.getKey())) {
                word.setProbabilidadSpam(allWords.get(entry.getKey()).getProbabilidadSpam());
            } else {                
                word.setProbabilidadSpam(new BigDecimal("0"));
            }

            word.setId(entry.getKey());
            word
                .setProbabilidadNoSpam(new BigDecimal(entry.getValue().intValue()).divide(new BigDecimal(totalMailsNoSpam), 3, BigDecimal.ROUND_DOWN));
            allWords.put(word.getId(), word);
		}
		
		Word.saveAll(allWords.values());
		
		//estadisticas
		Estadistica estadistica = new Estadistica();
		estadistica.setTotalMailsNoSpam(totalMailsNoSpam);
		estadistica.setTotalMailsSpam(totalMailsSpam);
		estadistica.save();
		
		logger.info("Cantidad de mails spam: "+totalMailsSpam+". Cantidad de mails NO spam: "+totalMailsNoSpam);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public boolean isEntrenado() {
		if (Estadistica.find() != null){
			return true;
		}
		
		return false;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public Collection<MailClasificado> clasificar(Collection<Mail> mails) throws SinEntrenarException{		
		Estadistica estadistica = Estadistica.find();
		if (estadistica == null){
			throw new SinEntrenarException();
		}
		
		Collection<MailClasificado> mailsClasificados = new ArrayList<MailClasificado>();
		for (Mail mail : mails) {
			//determinar la probabilidad de que las palabras del mail esten en uno spam y en uno no spam
			BigDecimal probaPalabrasEnSpam = new BigDecimal("0");
			BigDecimal probaPalabrasEnNoSpam = new BigDecimal("0");
			for (String wordInMail : mail.getNonStopWords()) {
				Word word = Word.find(wordInMail);				
				if (word != null){					
					probaPalabrasEnNoSpam = probaPalabrasEnNoSpam.add(word.getProbabilidadNoSpam());
					probaPalabrasEnSpam = probaPalabrasEnSpam.add(word.getProbabilidadSpam());					
				}								
			}
			//cantidad total de mails			
			int totalMails = estadistica.getTotalMailsNoSpam() + estadistica.getTotalMailsSpam();
			//falta dividir por la cantidad total de mails
			probaPalabrasEnNoSpam = probaPalabrasEnNoSpam.divide(new BigDecimal(totalMails), 3, BigDecimal.ROUND_DOWN);
			probaPalabrasEnSpam = probaPalabrasEnSpam.divide(new BigDecimal(totalMails), 3, BigDecimal.ROUND_DOWN);			
			//probabilidad de que cualquier mail sea spam. P(spam)
			BigDecimal probaCualquierMailSpam =  new BigDecimal(estadistica.getTotalMailsSpam()).divide(new BigDecimal(totalMails), 3, BigDecimal.ROUND_DOWN);
			//probabilidad de que cualquier mail NO sea spam. P(no spam)
			BigDecimal probaCualquierMailNoSpam = new  BigDecimal(estadistica.getTotalMailsNoSpam()).divide(new BigDecimal(totalMails), 3, BigDecimal.ROUND_DOWN);
			//probabilidad que las palabras de este mail, aparezcan en cualquier mail. P(palabras)			
			BigDecimal probaPalabrasCualquierMail = probaPalabrasEnNoSpam.add(probaPalabrasEnSpam);
			//probabilidad de spam = P(palabras/spam) P(spam) / P(palabras)
			BigDecimal probaSeaSpam = (probaPalabrasEnSpam.multiply(probaCualquierMailSpam)).divide(probaPalabrasCualquierMail, 3, BigDecimal.ROUND_DOWN);
			//probabilidad de NO spam = P(palabras/no spam) P(no spam) / p(palabras)
			BigDecimal probaNoSeaSpam = (probaPalabrasEnNoSpam.multiply(probaCualquierMailNoSpam)).divide(probaPalabrasCualquierMail, 3, BigDecimal.ROUND_DOWN);
			
			MailClasificado mailClasificado = new MailClasificado();
			mailClasificado.setArchivo(mail.getArchivo());
			mailClasificado.setAsunto(mail.getAsunto());
			mailClasificado.setBody(mail.getBody());
			mailClasificado.setProbabilidadNoSpam(probaNoSeaSpam);
			mailClasificado.setProbabilidadSpam(probaSeaSpam);
			mailClasificado.setSpam(probaSeaSpam.compareTo(probaNoSeaSpam) == 1);
			mailsClasificados.add(mailClasificado);
		}
		
		logger.info("Se clasificaron "+mailsClasificados.size()+" mails");
		
		return mailsClasificados;
	}
	
}
