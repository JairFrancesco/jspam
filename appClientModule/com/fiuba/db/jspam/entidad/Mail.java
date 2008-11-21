package com.fiuba.db.jspam.entidad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author PNT
 *
 */
public class Mail {
    private String asunto;
    private String body;

    public Collection<String> getNonStopWords(){
    	Collection<String> words = new ArrayList<String>();
    	
    	if (asunto != null){
    		words.addAll(Arrays.asList(asunto.split("\\s")));
    	}
    	
    	if (body != null){
    		words.addAll(Arrays.asList(body.split("\\s")));
    	}
    	
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
    
    
}
