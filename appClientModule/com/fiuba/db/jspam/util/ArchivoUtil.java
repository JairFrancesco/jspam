package com.fiuba.db.jspam.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * @author PNT
 * 
 */
public class ArchivoUtil {

    /**
     * Retorna el contenido de un archivo en un String.
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public static String readFileAsString(File file) throws IOException {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try{
        	char[] buf = new char[1024];
	        int numRead = 0;
	        while ((numRead = reader.read(buf)) != -1) {
	            fileData.append(buf, 0, numRead);
	        }
    	} finally{
    		reader.close();
    	}        
    	
    	String stringFile = fileData.toString();
    	String body = stringFile.substring(stringFile.indexOf("<body>")+6, stringFile.lastIndexOf("</body>")); 
    	String bodyEscapeado = forXML(body);
    	
    	StringBuilder xml = new StringBuilder(stringFile.substring(0,stringFile.indexOf("<body>")+6));
    	xml.append(bodyEscapeado);
    	xml.append(stringFile.substring(stringFile.lastIndexOf("</body>")));
    	
    	return xml.toString();
    }
    
    public static String forXML(String aText){
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(aText);
        char character =  iterator.current();
        while (character != CharacterIterator.DONE ){
          if (character == '<') {
            result.append("&lt;");
          }
          else if (character == '>') {
            result.append("&gt;");
          }
          else if (character == '\"') {
            result.append("&quot;");
          }
          else if (character == '\'') {
            result.append("&#039;");
          }
          else if (character == '&') {
             result.append("&amp;");
          }
          else {
            //the char is not a special one
            //add it to the result as is
            result.append(character);
          }
          character = iterator.next();
        }
        return result.toString();
      }

}
