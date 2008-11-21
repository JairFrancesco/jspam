package com.fiuba.db.jspam.business.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fiuba.db.jspam.business.DirectorioBo;
import com.fiuba.db.jspam.entidad.MailClasificado;
import com.fiuba.db.jspam.exception.ArchivoXmlMailInvalido;
import com.fiuba.db.jspam.exception.DirectorioInvalidoException;
import com.fiuba.db.jspam.filter.XmlFileFilter;
import com.fiuba.db.jspam.util.ArchivoUtil;
import com.thoughtworks.xstream.XStream;

/**
 * @author PNT
 *
 */
public class DirectorioBoImpl implements DirectorioBo {
    /**
     * logger.
     */
    private final Log logger = LogFactory.getLog(DirectorioBoImpl.class);

    /**
     * {@inheritDoc}
     */
    public Collection<MailClasificado> buscarMailsClasificados(String pathDirectorio) throws DirectorioInvalidoException, ArchivoXmlMailInvalido {
        Collection<MailClasificado> mails = new ArrayList<MailClasificado>();
        // si no es un directorio retornar error
        File dir = new File(pathDirectorio);
        if (!dir.isDirectory()) {
            throw new DirectorioInvalidoException();
        }
        // recorrer todos los archivos y transformar el xml a un objeto
        // MailClasificado
        File[] archivos = dir.listFiles(new XmlFileFilter());
        XStream xStream = new XStream();
        for (File archivoXml : archivos) {
            try {
                xStream.alias("mail", MailClasificado.class);
                MailClasificado mailClasificado = (MailClasificado) xStream.fromXML(ArchivoUtil.readFileAsString(archivoXml));
                mails.add(mailClasificado);
            } catch (IOException e) {
                logger.error("DirectorioBoImpl.buscarMailsClasificados() - Error al parsear un mail en xml", e);                
                throw new ArchivoXmlMailInvalido(e);
            }
        }
        
        logger.info("Se procesaron " + mails.size() + " mails.");

        return mails;
    }
}
