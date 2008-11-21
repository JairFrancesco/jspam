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

	/**
	 * Busca en un cierto directorio mails, preclasificados, en formato xml.
	 * @param pathDirectorio directorio donde estan los archivos xml
	 * @return una coleccion de mails clasificados
	 * @throws DirectorioInvalidoException si el directorio es invalido.
	 * @throws ArchivoXmlMailInvalido si alguno de los archivos xml esta mal formado.
	 */
    Collection<MailClasificado> buscarMailsClasificados(String pathDirectorio) throws DirectorioInvalidoException, ArchivoXmlMailInvalido;
}
