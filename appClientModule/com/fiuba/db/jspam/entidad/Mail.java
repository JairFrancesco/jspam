package com.fiuba.db.jspam.entidad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author PNT
 *
 */
public class Mail {
    private String asunto = "";
    private String body = "";
    private String archivo;

    public Collection<String> getNonStopWords(){
    	Collection<String> words = new ArrayList<String>();
    	
    	words.addAll(Arrays.asList(asunto.replaceAll("[^a-zA-Z]", " ").split("\\s+")));
        words.addAll(Arrays.asList(body.replaceAll("[^a-zA-Z]", " ").split("\\s+")));
    	
    	for (String stopWord : Word.STOP_WORDS) {
			words.remove(stopWord);
		}
    	
    	return words; 
    }
    
    /**
     * @return the asunto.
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * @param asunto
     *            the asunto to set.
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * @return the body.
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body
     *            the body to set.
     */
    public void setBody(String body) {
        this.body = body;
    }

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
    
    
}
