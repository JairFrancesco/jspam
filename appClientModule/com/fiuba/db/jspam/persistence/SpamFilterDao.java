package com.fiuba.db.jspam.persistence;

import com.fiuba.db.jspam.entidad.Word;
import com.fiuba.db.jspam.exception.IdExistenteException;

/**
 * @author Demian
 *
 */
public interface SpamFilterDao {
	
	/**
	 * guarda una entidad
	 * @param word la palabra a guardar.
	 * @throws IdExistenteException si la palabra a guardar ya existe.
	 */
	void save(Word word) throws IdExistenteException;
	
	/**
	 * acutaliza una entidad.
	 * @param word la palabra a actualizar.
	 */
	void update(Word word);
}
