package com.fiuba.db.jspam.business;

import java.io.IOException;
import java.util.Collection;

import com.fiuba.db.jspam.entidad.Mail;
import com.fiuba.db.jspam.entidad.MailClasificado;
import com.fiuba.db.jspam.entidad.MailPreClasificado;
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
    Collection<MailPreClasificado> buscarMailsPreClasificados(String pathDirectorio) throws DirectorioInvalidoException, ArchivoXmlMailInvalido;

    /**
     * Busca en un cierto directorio mails en formato xml.
	 * @param pathDirectorio directorio donde estan los archivos xml
	 * @return una coleccion de mails
	 * @throws DirectorioInvalidoException si el directorio es invalido.
	 * @throws ArchivoXmlMailInvalido si alguno de los archivos xml esta mal formado.
     */
	Collection<Mail> buscarMails(String directorioPath) throws DirectorioInvalidoException, ArchivoXmlMailInvalido;
	
	/**
	 * Mueve los mails de la coleccion, a un directorio.
	 * @param mails los mails a mover
	 * @param pathDirectorio el directorio al cual moverlos.
	 * @throws IOException si el directorio es invalido.
	 */
	void moverSpamACarpeta(Collection<MailClasificado> mails, String pathDirectorio) throws IOException;
}
