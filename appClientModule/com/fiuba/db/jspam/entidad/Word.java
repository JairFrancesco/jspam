package com.fiuba.db.jspam.entidad;

import java.math.BigDecimal;

import com.fiuba.db.jspam.exception.IdExistenteException;
import com.fiuba.db.jspam.persistence.SpamFilterDao;
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
	private SpamFilterDao dao;
	
	
	/**
	 * guarda a la entidad.
	 * @throws IdExistenteException si la entidad ya existe
	 */
	public void save() throws IdExistenteException {
		getDao().save(this);		
	}
	
	/**
	 * actualiza la entidad.
	 */
	public void update(){
		getDao().update(this);
	}
	
	public String getId() {
		return id;
	}	
	public void setId(String id) {
		this.id = id;
	}
	
	public SpamFilterDao getDao() {
		this.dao = new SpamFilterDaoJdbcImpl();
		return this.dao;
	}
	public void setDao(SpamFilterDao dao) {
		this.dao = dao;
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
