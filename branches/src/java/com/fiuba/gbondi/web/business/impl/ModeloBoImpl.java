package com.fiuba.gbondi.web.business.impl;

/**
 *
 * @author dberjman
 */
public class ModeloBoImpl {

    public String obtenerValorVariable(String key, String archivo) {
        return obtenerValorVariable(key, archivo, 1);
    }

    public String obtenerValorVariable(String key, String archivo, int nroMacht) {
        int inicio = 0;
        String valor = "";
        for (int i = 0; i < nroMacht; i++) {
            inicio = archivo.indexOf(key, inicio);
            valor = archivo.substring(inicio + key.length(), archivo.indexOf('\n', inicio));
            inicio += key.length();
        }
        return valor;
    }

    public String grabarValorVariable(String key, String archivo, String nuevoValor) {
        return grabarValorVariable(key, archivo, 1, nuevoValor);
    }

    public String grabarValorVariable(String key, String archivo, int nroMacht, String nuevoValor) {
        int inicio = 0;
        for (int i = 0; i < nroMacht; i++) {
            inicio = archivo.indexOf(key, inicio);
            if ((i+1) == nroMacht) {               
                String parte1 = archivo.substring(0, inicio + key.length());
                String parte2 = archivo.substring(archivo.indexOf('\n', inicio)-1);
                return parte1 + nuevoValor + parte2;
            } else {
                inicio += key.length();
            }
        }

        return archivo;
    }
}
