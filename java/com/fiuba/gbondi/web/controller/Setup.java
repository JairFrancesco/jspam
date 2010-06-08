package com.fiuba.gbondi.web.controller;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author dberjman
 */
public class Setup extends ActionSupport implements SessionAware {
    private String archivoModelo;
    private Map<String, Object> session;
    public static String KEY_ARCHIVO_SESION = "ARCHIVOMODELO";

    public String modificarConfiguracion() throws Exception {
        getSession().put(KEY_ARCHIVO_SESION, getArchivoModelo());

        return SUCCESS;
    }

    public String inicializar(){
        setArchivoModelo("D:/temp/gbondi/nuevoModelo.snk");

        return SUCCESS;
    }


    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public Map<String, Object> getSession() {
        return this.session;
    }

    /**
     * @return the archivoModelo
     */
    public String getArchivoModelo() {
        return archivoModelo;
    }

    /**
     * @param archivoModelo the archivoModelo to set
     */
    public void setArchivoModelo(String archivoModelo) {
        this.archivoModelo = archivoModelo;
    }
}
