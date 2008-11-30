package com.fiuba.db.jspam.business.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fiuba.db.jspam.business.DirectorioBo;
import com.fiuba.db.jspam.entidad.Mail;
import com.fiuba.db.jspam.entidad.MailClasificado;
import com.fiuba.db.jspam.entidad.MailPreClasificado;
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
    public Collection<MailPreClasificado> buscarMailsPreClasificados(String pathDirectorio) throws DirectorioInvalidoException, ArchivoXmlMailInvalido {
        return buscarMails(pathDirectorio, new MailPreClasificado());
    }
    
    /** 
     * {@inheritDoc}
     */
	public Collection<Mail> buscarMails(String pathDirectorio) throws DirectorioInvalidoException, ArchivoXmlMailInvalido {
		return buscarMails(pathDirectorio, new Mail());
	}
    
    @SuppressWarnings("unchecked")
	public <T extends Mail> Collection<T> buscarMails(String pathDirectorio, T t) throws DirectorioInvalidoException, ArchivoXmlMailInvalido {
    	Collection<T> mails = new ArrayList<T>();
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
                xStream.alias("mail", MailPreClasificado.class);
                T mail = (T) xStream.fromXML(ArchivoUtil.readFileAsString(archivoXml));
                mail.setArchivo(archivoXml.getAbsolutePath());
                mails.add(mail);
            } catch (IOException e) {
                logger.error("DirectorioBoImpl.buscarMailsClasificados() - Error al parsear un mail en xml", e);                
                throw new ArchivoXmlMailInvalido(e);
            }
        }
        
        logger.info("Se encontraron " + mails.size() + " mails.");

        return mails;
    }
    
    public void moverNoSpamACarpeta(Collection<MailClasificado> mails, String directorioNoSpam) throws IOException {    
		File directorio = new File(directorioNoSpam);				
		if (!directorio.exists()){
			directorio.mkdirs();
		}
		
		for (MailClasificado mailClasificado : mails) {
			if (!mailClasificado.isSpam()){
				moverMail(mailClasificado, directorioNoSpam);
			}
		}    	
    }
    
    private void moverMail(MailClasificado mailClasificado, String pathDirectorio) throws IOException{
    	XStream xStream = new XStream();
    	xStream.alias("mail", MailClasificado.class);
		String xml = xStream.toXML(mailClasificado);			
		String nombreArchivo = mailClasificado.getArchivo().substring(mailClasificado.getArchivo().lastIndexOf("\\")+1);
		FileOutputStream file = new FileOutputStream(pathDirectorio+"/"+nombreArchivo);
		try{
			file.write(xml.getBytes());
		} finally {
			file.close();
		}
		
		File mailOriginal = new File(mailClasificado.getArchivo());
		mailOriginal.delete();
    }

    /**
     * 
     * {@inheritDoc}
     */
	public void moverSpamACarpeta(Collection<MailClasificado> mails, String pathDirectorio) throws IOException {
		File directorio = new File(pathDirectorio);				
		if (!directorio.exists()){
			directorio.mkdirs();
		}
		
		for (MailClasificado mailClasificado : mails) {
			if (mailClasificado.isSpam()){
				moverMail(mailClasificado, pathDirectorio);
			}
		}		
	}    
}
