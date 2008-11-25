package com.fiuba.db.jspam.entidad;

import java.math.BigDecimal;

import com.fiuba.db.jspam.exception.IdExistenteException;
import com.fiuba.db.jspam.persistence.jdbc.SpamFilterDaoJdbcImpl;

/**
 * 
 * @author Demian
 *
 */
public class Word {
	public static final String[] STOP_WORDS = {"el", "la", "de", "lo", "a", "del", "las", "los", "les", "le", "y", "te", " ", ""};	
	private String id;
	private BigDecimal probabilidadSpam;
	private BigDecimal probabilidadNoSpam;	
	
	/**
	 * guarda a la entidad.
	 * @throws IdExistenteException si la entidad ya existe
	 */
	public void save() throws IdExistenteException {
		new SpamFilterDaoJdbcImpl().save(this);		
	}
	
	/**
	 * actualiza la entidad.
	 */
	public void update(){
		new SpamFilterDaoJdbcImpl().update(this);
	}
	
	/**
	 * busca la palabra por su id
	 * @param wordInMail
	 * @return
	 */
	public static Word find(String id) {		
		return new SpamFilterDaoJdbcImpl().findWord(id);
	}
	
	public String getId() {
		return id;
	}	
	public void setId(String id) {
		this.id = id;
	}
	
	public BigDecimal getProbabilidadSpam() {
		return probabilidadSpam;
	}

	public void setProbabilidadSpam(BigDecimal probabilidadSpam) {
		this.probabilidadSpam = probabilidadSpam;
	}

	public BigDecimal getProbabilidadNoSpam() {
		return probabilidadNoSpam;
	}

	public void setProbabilidadNoSpam(BigDecimal probabilidadNoSpam) {
		this.probabilidadNoSpam = probabilidadNoSpam;
	}	
}
