package com.fiuba.db.jspam.persistence;

import java.util.Collection;

import com.fiuba.db.jspam.entidad.Estadistica;
import com.fiuba.db.jspam.entidad.Word;
import com.fiuba.db.jspam.exception.IdExistenteException;

/**
 * @author Demian
 *
 */
public interface SpamFilterDao {
	
	/**
	 * guarda una entidad.
	 * @param word la palabra a guardar.
	 * @throws IdExistenteException si la palabra a guardar ya existe.
	 */
	void save(Word word) throws IdExistenteException;

    /**
     * guarda una coleccion de palabras.
     * 
     * @param words
     *            la coleccion de palabras a guardar.
     */
    public void saveAll(Collection<Word> words);

    /**
     * acutaliza una entidad.
     * 
     * @param word
     *            la palabra a actualizar.
     */
	void update(Word word);

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
}
