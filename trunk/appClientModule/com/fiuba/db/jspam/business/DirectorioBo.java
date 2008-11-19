package com.fiuba.db.jspam.business;

import java.util.Collection;

import com.fiuba.db.jspam.entidad.MailClasificado;
import com.fiuba.db.jspam.exception.ArchivoXmlMailInvalido;
import com.fiuba.db.jspam.exception.DirectorioInvalidoException;

/**
 * @author PNT
 *
 */
public interface DirectorioBo {

    Collection<MailClasificado> buscarMailsClasificados(String pathDirectorio) throws DirectorioInvalidoException, ArchivoXmlMailInvalido;
}
