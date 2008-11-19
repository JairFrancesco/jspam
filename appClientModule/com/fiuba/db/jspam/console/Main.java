package com.fiuba.db.jspam.console;

import com.fiuba.db.jspam.enums.Operacion;
import com.fiuba.db.jspam.exception.ParametrosInvalidosException;

public class Main {
    public static final String PARAMETRO_APRENDER = "-ad";

    public static void main(String[] args) {
        Main main = new Main();
        try {
            Operacion operacion = main.obtenerOperacion(args);
            System.out.println("Operacion: " + operacion);
        } catch (ParametrosInvalidosException e) {
            System.out.println(main.getAyudaParametros());
        }
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