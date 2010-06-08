package com.fiuba.gbondi.web.controller;

import com.fiuba.gbondi.web.business.impl.ModeloBoImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author dberjman
 */
public class ConfiguracionPasajeros extends ActionSupport implements Preparable, SessionAware {
    private static final String KEY_TIEMPO_BAJAR_BONDI_MEDIA = "INITIAL		X$MEDBAJAR,";
    private static final String KEY_TIEMPO_BAJAR_BONDI_DESVIO = "INITIAL		X$DESVBAJAR,";
    private static final String KEY_TIEMPO_SUBIR_BONDI_MEDIA = "INITIAL		X$MEDSUBIR,";
    private static final String KEY_TIEMPO_SUBIR_BONDI_DESVIO = "INITIAL		X$DESVSUBIR,";
    private static final String KEY_TIEMPO_IMPACIENCIA = "INITIAL		X$IMPACIENCIA,";
    
    private Map<String, Object> session;
    private String tiempoBajarColectivoMedia;
    private String tiempoBajarColectivoDesvio;
    private String tiempoSubirColectivoMedia;
    private String tiempoSubirColectivoDesvio;
    private String tiempoImpaciencia;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public void prepare() throws Exception {
        FileInputStream inputStream = new FileInputStream((String)getSession().get(Setup.KEY_ARCHIVO_SESION));
        try {
            byte buffer[] = new byte[20024];
            inputStream.read(buffer);
            String archivo = new String(buffer);

            ModeloBoImpl modeloBoImpl = new ModeloBoImpl();

            setTiempoBajarColectivoDesvio(modeloBoImpl.obtenerValorVariable(KEY_TIEMPO_BAJAR_BONDI_DESVIO, archivo));
            setTiempoBajarColectivoMedia(modeloBoImpl.obtenerValorVariable(KEY_TIEMPO_BAJAR_BONDI_MEDIA, archivo));

            setTiempoSubirColectivoDesvio(modeloBoImpl.obtenerValorVariable(KEY_TIEMPO_SUBIR_BONDI_DESVIO, archivo));
            setTiempoSubirColectivoMedia(modeloBoImpl.obtenerValorVariable(KEY_TIEMPO_SUBIR_BONDI_MEDIA, archivo));

            setTiempoImpaciencia(modeloBoImpl.obtenerValorVariable(KEY_TIEMPO_IMPACIENCIA, archivo));
        } finally {
            inputStream.close();
        }
    }

    public String modificarModelo() throws Exception {
        FileInputStream inputStream = new FileInputStream((String)getSession().get(Setup.KEY_ARCHIVO_SESION));
        String archivo;
        try {
            byte buffer[] = new byte[20024];
            int length = inputStream.read(buffer);
            archivo = new String(buffer, 0, length);

            ModeloBoImpl modeloBoImpl = new ModeloBoImpl();
            archivo = modeloBoImpl.grabarValorVariable(KEY_TIEMPO_BAJAR_BONDI_MEDIA, archivo, getTiempoBajarColectivoMedia());
            archivo = modeloBoImpl.grabarValorVariable(KEY_TIEMPO_BAJAR_BONDI_DESVIO, archivo, getTiempoBajarColectivoDesvio());

            archivo = modeloBoImpl.grabarValorVariable(KEY_TIEMPO_SUBIR_BONDI_MEDIA, archivo, getTiempoSubirColectivoMedia());
            archivo = modeloBoImpl.grabarValorVariable(KEY_TIEMPO_SUBIR_BONDI_DESVIO, archivo, getTiempoSubirColectivoDesvio());

            archivo = modeloBoImpl.grabarValorVariable(KEY_TIEMPO_IMPACIENCIA, archivo, getTiempoImpaciencia());
        } finally {
            inputStream.close();
        }

        FileOutputStream salidaModelo = new FileOutputStream((String)getSession().get(Setup.KEY_ARCHIVO_SESION));
        salidaModelo.write(archivo.getBytes());
        salidaModelo.close();

        return SUCCESS;
    }

    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public Map<String, Object> getSession() {
        return this.session;
    }

    /**
     * @return the tiempoBajarColectivoMedia
     */
    public String getTiempoBajarColectivoMedia() {
        return tiempoBajarColectivoMedia;
    }

    /**
     * @param tiempoBajarColectivoMedia the tiempoBajarColectivoMedia to set
     */
    public void setTiempoBajarColectivoMedia(String tiempoBajarColectivoMedia) {
        this.tiempoBajarColectivoMedia = tiempoBajarColectivoMedia;
    }

    /**
     * @return the tiempoBajarColectivoDesvio
     */
    public String getTiempoBajarColectivoDesvio() {
        return tiempoBajarColectivoDesvio;
    }

    /**
     * @param tiempoBajarColectivoDesvio the tiempoBajarColectivoDesvio to set
     */
    public void setTiempoBajarColectivoDesvio(String tiempoBajarColectivoDesvio) {
        this.tiempoBajarColectivoDesvio = tiempoBajarColectivoDesvio;
    }

    /**
     * @return the tiempoSubirColectivoMedia
     */
    public String getTiempoSubirColectivoMedia() {
        return tiempoSubirColectivoMedia;
    }

    /**
     * @param tiempoSubirColectivoMedia the tiempoSubirColectivoMedia to set
     */
    public void setTiempoSubirColectivoMedia(String tiempoSubirColectivoMedia) {
        this.tiempoSubirColectivoMedia = tiempoSubirColectivoMedia;
    }

    /**
     * @return the tiempoSubirColectivoDesvio
     */
    public String getTiempoSubirColectivoDesvio() {
        return tiempoSubirColectivoDesvio;
    }

    /**
     * @param tiempoSubirColectivoDesvio the tiempoSubirColectivoDesvio to set
     */
    public void setTiempoSubirColectivoDesvio(String tiempoSubirColectivoDesvio) {
        this.tiempoSubirColectivoDesvio = tiempoSubirColectivoDesvio;
    }

    /**
     * @return the tiempoImpaciencia
     */
    public String getTiempoImpaciencia() {
        return tiempoImpaciencia;
    }

    /**
     * @param tiempoImpaciencia the tiempoImpaciencia to set
     */
    public void setTiempoImpaciencia(String tiempoImpaciencia) {
        this.tiempoImpaciencia = tiempoImpaciencia;
    }


}
