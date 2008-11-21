package com.fiuba.db.jspam.business;

import java.util.Collection;

import com.fiuba.db.jspam.entidad.MailClasificado;

/**
 * 
 * @author Demian
 *
 */
public interface SpamFilterBo {
	
	void aprender(Collection<MailClasificado> mails);
}
