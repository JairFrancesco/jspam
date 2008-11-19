package com.fiuba.db.jspam.console;

import java.util.Collection;

import com.fiuba.db.jspam.business.DirectorioBo;
import com.fiuba.db.jspam.business.impl.DirectorioBoImpl;
import com.fiuba.db.jspam.entidad.MailClasificado;
import com.fiuba.db.jspam.enums.Operacion;
import com.fiuba.db.jspam.exception.ArchivoXmlMailInvalido;
import com.fiuba.db.jspam.exception.DirectorioInvalidoException;
import com.fiuba.db.jspam.exception.ParametrosInvalidosException;

public class Main {
    public static final String PARAMETRO_APRENDER = "-ad";

    public static void main(String[] args) {
        Main main = new Main();
        try {
            Operacion operacion = main.obtenerOperacion(args);
            System.out.println("Operacion: " + operacion);
            if (operacion.equals(Operacion.APRENDER)) {
                main.aprender(args[1]);
            }
        } catch (ParametrosInvalidosException e) {
            System.out.println(main.getAyudaParametros());
        } catch (DirectorioInvalidoException e) {
            System.out.println("El directorio ingresado es invalido");
        } catch (ArchivoXmlMailInvalido e) {
            System.out.println("Alguno de los archivos de mail esta mal formado. Revisar el log para mas detalles");
        }
	}
    
    public void aprender(String directorioPath) throws DirectorioInvalidoException, ArchivoXmlMailInvalido {
        DirectorioBo directorioBo = new DirectorioBoImpl();
        Collection<MailClasificado> mailsClasificados = directorioBo.buscarMailsClasificados(directorioPath);
    }
    
    public String getAyudaParametros() {
        return "Posibles parametros: \n" + "Para que aprenda: -ad directorio \n";
    }

    public Operacion obtenerOperacion(String[] args) throws ParametrosInvalidosException {
        if (args.length <= 0) {
            throw new ParametrosInvalidosException();
        }

        // java -jar jspam.jar -ad "d:\temp\mails"
        if ((args[0].equals(PARAMETRO_APRENDER)) && (args.length == 2)) {
            return Operacion.APRENDER;
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