package com.fiuba.db.jspam.persistence;

import java.util.Collection;

import com.fiuba.db.jspam.entidad.Estadistica;
import com.fiuba.db.jspam.entidad.Word;

/**
 * @author Demian
 *
 */
public interface SpamFilterDao {
	
	
    /**
     * guarda una coleccion de palabras.
     * 
     * @param words
     *            la coleccion de palabras a guardar.
     */
    public void saveAll(Collection<Word> words);

    
	/**
	 * guarda la entidad.
	 * @param estadistica la estadistica a guardar.
	 */
	void save(Estadistica estadistica);
	
	/**
	 * busca la ultima estadistica.
	 * @return la Estadistica de los mails leidos.
	 */
	public Estadistica findEstadistica();
	
	/**
	 * busca la palabra por su id.
	 * @param word el id a buscar
	 * @return la palabra
	 */
	public Word findWord(String word);
	
	/**
	 * borra los registros de todas las palabras.
	 */
	public void deleteAllWords();
	
	/**
	 * borra los registros de todas las estadisticas.
	 */
	public void deleteAllEstadisticas();
}
