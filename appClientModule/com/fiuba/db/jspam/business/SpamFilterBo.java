package com.fiuba.db.jspam.business;

import java.util.Collection;

import com.fiuba.db.jspam.entidad.Mail;
import com.fiuba.db.jspam.entidad.MailClasificado;
import com.fiuba.db.jspam.entidad.MailPreClasificado;
import com.fiuba.db.jspam.exception.SinEntrenarException;

/**
 * 
 * @author Demian
 *
 */
public interface SpamFilterBo {
	
	/**
	 * A partir de una coleccion de mails pre clasificados, obtiene probabilidades
	 * y las registra para luego utilizarlas al momento de clasificar.
	 * @param mails una coleccion de mails clasificados.
	 */
	void aprender(Collection<MailPreClasificado> mails);

	/**
	 * Determina si ya cuenta con las probabilidades que se generan al aprender.
	 * @return true si ya aprendio.
	 */
	boolean isEntrenado();

	/**
	 * Dada una coleccion de mails, determina aquellos que son spam.
	 * @param mails mails sin clasificar
	 * @return una coleccion de mails clasificados
	 * @throws SinEntrenarException si no fue entrenado previamente
	 */
	Collection<MailClasificado> clasificar(Collection<Mail> mails) throws SinEntrenarException;
}
