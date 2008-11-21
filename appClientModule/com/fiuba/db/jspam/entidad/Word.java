package com.fiuba.db.jspam.entidad;

/**
 * 
 * @author Demian
 *
 */
public class Word {
	public static final String[] STOP_WORDS = {"el", "la", "de", "lo", "a", "del", "las", "los", "les", "le", "y", "te"};
	
	private String id;
	private Double probabilidadSpam;
	private Double probabilidadNoSpam;
	
	public String getId() {
		return id;
	}	
	public void setId(String id) {
		this.id = id;
	}
	public Double getProbabilidadSpam() {
		return probabilidadSpam;
	}
	public void setProbabilidadSpam(Double probabilidadSpam) {
		this.probabilidadSpam = probabilidadSpam;
	}
	public Double getProbabilidadNoSpam() {
		return probabilidadNoSpam;
	}
	public void setProbabilidadNoSpam(Double probabilidadNoSpam) {
		this.probabilidadNoSpam = probabilidadNoSpam;
	}
	
}
