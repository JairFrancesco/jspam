package com.fiuba.gbondi.web.controller;

import com.fiuba.gbondi.web.business.impl.ModeloBoImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * <code>Set welcome message.</code>
 */
public class ConfiguracionColectivo extends ActionSupport implements Preparable, SessionAware {
    private static final String KEY_CANTIDAD_UNIDADES = "INITIAL		X$CANTUNIDADES,";
    private static final String KEY_CAPACIDAD = "INITIAL		X$CAPBONDI,";
    private static final String KEY_TIEMPO_RETORNO_MEDIA = "INITIAL		X$MEDRETORNO,";
    private static final String KEY_TIEMPO_RETORNO_DESVIO = "INITIAL		X$DESVRETORNO,";
    private static final String KEY_FRECUENCIA_6a7 = "INITIAL		X$FRECUENCIA,";
    private static final String KEY_FRECUENCIA_7a10 = "SAVEVALUE		FRECUENCIA,";
    private static final String KEY_FRECUENCIA_10a12 = "SAVEVALUE		FRECUENCIA,";

    private String cantidadUnidades;
    private String capacidadUnidad;
    private String tiempoRetornoMedia;
    private String tiempoRetornoDesvio;
    private String frecuencia6a7;
    private String frecuencia7a10;
    private String frecuencia10a12;
    private Map<String, Object> session;

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
            setCantidadUnidades(modeloBoImpl.obtenerValorVariable(KEY_CANTIDAD_UNIDADES, archivo));
            setCapacidadUnidad(modeloBoImpl.obtenerValorVariable(KEY_CAPACIDAD, archivo));
            setTiempoRetornoMedia(modeloBoImpl.obtenerValorVariable(KEY_TIEMPO_RETORNO_MEDIA, archivo));
            setTiempoRetornoDesvio(modeloBoImpl.obtenerValorVariable(KEY_TIEMPO_RETORNO_DESVIO, archivo));
            setFrecuencia6a7(modeloBoImpl.obtenerValorVariable(KEY_FRECUENCIA_6a7, archivo));
            setFrecuencia7a10(modeloBoImpl.obtenerValorVariable(KEY_FRECUENCIA_7a10, archivo));
            setFrecuencia10a12(modeloBoImpl.obtenerValorVariable(KEY_FRECUENCIA_10a12, archivo, 2));
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
            archivo = modeloBoImpl.grabarValorVariable(KEY_CANTIDAD_UNIDADES, archivo, getCantidadUnidades());
            archivo = modeloBoImpl.grabarValorVariable(KEY_CAPACIDAD, archivo, getCapacidadUnidad());
            archivo = modeloBoImpl.grabarValorVariable(KEY_TIEMPO_RETORNO_MEDIA, archivo, getTiempoRetornoMedia());
            archivo = modeloBoImpl.grabarValorVariable(KEY_FRECUENCIA_6a7, archivo, getFrecuencia6a7());
            archivo = modeloBoImpl.grabarValorVariable(KEY_FRECUENCIA_7a10, archivo, getFrecuencia10a12());
            archivo = modeloBoImpl.grabarValorVariable(KEY_FRECUENCIA_10a12, archivo, 2, getFrecuencia10a12());
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
     * @return the cantidadUnidades
     */
    public String getCantidadUnidades() {
        return cantidadUnidades;
    }

    /**
     * @param cantidadUnidades the cantidadUnidades to set
     */
    public void setCantidadUnidades(String cantidadUnidades) {
        this.cantidadUnidades = cantidadUnidades;
    }

    /**
     * @return the capacidadUnidade
     */
    public String getCapacidadUnidad() {
        return capacidadUnidad;
    }

    /**
     * @param capacidadUnidade the capacidadUnidade to set
     */
    public void setCapacidadUnidad(String capacidadUnidad) {
        this.capacidadUnidad = capacidadUnidad;
    }

    /**
     * @return the frecuencia6a7
     */
    public String getFrecuencia6a7() {
        return frecuencia6a7;
    }

    /**
     * @param frecuencia6a7 the frecuencia6a7 to set
     */
    public void setFrecuencia6a7(String frecuencia6a7) {
        this.frecuencia6a7 = frecuencia6a7;
    }

    /**
     * @return the frecuencia7a10
     */
    public String getFrecuencia7a10() {
        return frecuencia7a10;
    }

    /**
     * @param frecuencia7a10 the frecuencia7a10 to set
     */
    public void setFrecuencia7a10(String frecuencia7a10) {
        this.frecuencia7a10 = frecuencia7a10;
    }

    /**
     * @return the frecuencia10a12
     */
    public String getFrecuencia10a12() {
        return frecuencia10a12;
    }

    /**
     * @param frecuencia10a12 the frecuencia10a12 to set
     */
    public void setFrecuencia10a12(String frecuencia10a12) {
        this.frecuencia10a12 = frecuencia10a12;
    }

    /**
     * @return the tiempoRetornoDesvio
     */
    public String getTiempoRetornoDesvio() {
        return tiempoRetornoDesvio;
    }

    /**
     * @param tiempoRetornoDesvio the tiempoRetornoDesvio to set
     */
    public void setTiempoRetornoDesvio(String tiempoRetornoDesvio) {
        this.tiempoRetornoDesvio = tiempoRetornoDesvio;
    }

    /**
     * @return the tiempoRetornoMedia
     */
    public String getTiempoRetornoMedia() {
        return tiempoRetornoMedia;
    }

    /**
     * @param tiempoRetornoMedia the tiempoRetornoMedia to set
     */
    public void setTiempoRetornoMedia(String tiempoRetornoMedia) {
        this.tiempoRetornoMedia = tiempoRetornoMedia;
    }
}

