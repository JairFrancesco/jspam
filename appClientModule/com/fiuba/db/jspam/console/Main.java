package com.fiuba.db.jspam.console;

import java.io.IOException;
import java.util.Collection;

import com.fiuba.db.jspam.business.DirectorioBo;
import com.fiuba.db.jspam.business.SpamFilterBo;
import com.fiuba.db.jspam.business.impl.DirectorioBoImpl;
import com.fiuba.db.jspam.business.impl.NaiveBayesBoImpl;
import com.fiuba.db.jspam.entidad.Mail;
import com.fiuba.db.jspam.entidad.MailClasificado;
import com.fiuba.db.jspam.entidad.MailPreClasificado;
import com.fiuba.db.jspam.enums.Operacion;
import com.fiuba.db.jspam.exception.ArchivoXmlMailInvalido;
import com.fiuba.db.jspam.exception.DirectorioInvalidoException;
import com.fiuba.db.jspam.exception.ParametrosInvalidosException;
import com.fiuba.db.jspam.exception.SinEntrenarException;

public class Main {
    public static final String PARAMETRO_APRENDER = "-ad";
    public static final String PARAMETRO_CLASIFICAR_DIR = "-cd";

    public static void main(String[] args) {
        Main main = new Main();
        try {
            Operacion operacion = main.obtenerOperacion(args);
            System.out.println("Operacion: " + operacion);
            if (operacion.equals(Operacion.APRENDER)) {
                main.aprender(args[1]);
            } else if (operacion.equals(Operacion.CLASIFICAR)) {
            	main.clasificar(args[1]);
            }
        } catch (ParametrosInvalidosException e) {
            System.out.println(main.getAyudaParametros());
        } catch (DirectorioInvalidoException e) {
            System.out.println("El directorio ingresado es invalido");
        } catch (ArchivoXmlMailInvalido e) {
            System.out.println("Alguno de los archivos de mail esta mal formado. Revisar el log para mas detalles");
        } catch (SinEntrenarException e){
        	System.out.println("Para poder clasificar, primero debe realizar la operacion de aprender. Comando -ad directorio");
        } catch (IOException e){
        	System.out.println("Error al querer mover los mails clasificados como spam");
        }
	}
    
    /**
     * A partir de un directorio en donde hay mails en formato xml, los clasifica
     * en mails spam y no spam.
     * @param directorioPath directorio que contiene los mails en formato xml.
     * @throws IOException si hubo un error al mover los mails de spam
     */
    public void clasificar(String directorioPath) throws SinEntrenarException, DirectorioInvalidoException, ArchivoXmlMailInvalido, IOException {
    	SpamFilterBo spamFilterBo = new NaiveBayesBoImpl();
    	DirectorioBo directorioBo = new DirectorioBoImpl();
    	
    	if (!spamFilterBo.isEntrenado()){
    		throw new SinEntrenarException();
    	}		
    	    	
    	Collection<Mail> mails = directorioBo.buscarMails(directorioPath);
    	if (mails.size() > 0){
    		Collection<MailClasificado> mailsClasificados = spamFilterBo.clasificar(mails);    	
	    	    			    	
	    	String pathArchivo = mailsClasificados.iterator().next().getArchivo();
	    	//mover a spam
	    	String directorioSpam = pathArchivo.substring(0, pathArchivo.lastIndexOf("\\")) + "/spam"; 
	    	directorioBo.moverSpamACarpeta(mailsClasificados, directorioSpam);
	    	//mover no spam
	    	String directorioNoSpam = pathArchivo.substring(0, pathArchivo.lastIndexOf("\\")) + "/nospam";
	    	directorioBo.moverNoSpamACarpeta(mailsClasificados, directorioNoSpam);
    	}
	}

	/**
     * A partir de un directorio en donde hay mails en formato xml, ya preclasificados,
     * obtiene probabilidades de cada palabra y las guarda. Estas probabilidades son la base
     * para poder determinar luego si un mail es spam o no.
     * @param directorioPath directorio que contiene los mails en formato xml.
     * @throws DirectorioInvalidoException si el directorio no es valido.
     * @throws ArchivoXmlMailInvalido si alguno de los archivos xml esta mal formado.
     */
    public void aprender(String directorioPath) throws DirectorioInvalidoException, ArchivoXmlMailInvalido {
        DirectorioBo directorioBo = new DirectorioBoImpl();
        SpamFilterBo spamFilterBo = new NaiveBayesBoImpl();
        
        Collection<MailPreClasificado> mailsClasificados = directorioBo.buscarMailsPreClasificados(directorioPath);
        spamFilterBo.aprender(mailsClasificados);
    }
    
    /**
     * Retorna un string con la ayuda para correr el programa.
     * @return un string con la ayuda para correr el programa
     */
    public String getAyudaParametros() {
        return "Posibles parametros: \n" + 
        	   "Para que aprenda: -ad directorio \n" +
        	   "Para que clasifique mails: -cd directorio \n";
    }

    /**
     * Determina en base a los argumentos que se le pasan a la app que operacion se 
     * esta solicitando ejecutar. 
     * @param args
     * @return la operacion a ejecutar
     * @throws ParametrosInvalidosException en caso de parametros invalidos
     */
    public Operacion obtenerOperacion(String[] args) throws ParametrosInvalidosException {
        if (args.length <= 0) {
            throw new ParametrosInvalidosException();
        }

        // java -jar jspam.jar -ad "d:\temp\mailsClasificados"
        if ((args[0].equals(PARAMETRO_APRENDER)) && (args.length == 2)) {
            return Operacion.APRENDER;
        // java -jar jspam.jar -cd "d:\temp\bandejaEntrada"
        } else if ((args[0].equals(PARAMETRO_CLASIFICAR_DIR)) && (args.length == 2)){
        	return Operacion.CLASIFICAR;
        } else {
            throw new ParametrosInvalidosException();
        }
    }

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}