package com.fiuba.db.jspam.entidad;

import java.math.BigDecimal;

/**
 * @author Demian
 *
 */
public class MailClasificado extends MailPreClasificado{
	private BigDecimal probabilidadSpam;
	private BigDecimal probabilidadNoSpam;
	
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
