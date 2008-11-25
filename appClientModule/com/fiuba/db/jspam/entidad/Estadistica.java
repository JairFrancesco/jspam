package com.fiuba.db.jspam.entidad;

import com.fiuba.db.jspam.persistence.jdbc.SpamFilterDaoJdbcImpl;

/**
 * @author Demian
 *
 */
public class Estadistica {
	private int totalMailsSpam;
	private int totalMailsNoSpam;
		
	/**
	 * guarda a la entidad.
	 */
	public void save() {
		new SpamFilterDaoJdbcImpl().save(this);
	}
	
	/**
	 * busca la ultima estadistica. 
	 * @return
	 */
	public static Estadistica find() {		
		return new SpamFilterDaoJdbcImpl().findEstadistica();
	}
	
	public int getTotalMailsSpam() {
		return totalMailsSpam;
	}
	public void setTotalMailsSpam(int totalMailsSpam) {
		this.totalMailsSpam = totalMailsSpam;
	}
	public int getTotalMailsNoSpam() {
		return totalMailsNoSpam;
	}
	public void setTotalMailsNoSpam(int totalMailsNoSpam) {
		this.totalMailsNoSpam = totalMailsNoSpam;
	}			
}
