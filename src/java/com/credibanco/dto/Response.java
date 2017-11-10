package com.credibanco.dto;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
public class Response {

    private String estado;
    private String mensaje;
    private String parametros;

    public Response() {
    }

    /**
     * @param estado
     * @param mensaje
     * @param parametros 
     */
    public Response( String estado, String mensaje, String parametros ) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.parametros = parametros;
    }

    /**
     * @return String
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado 
     */
    public void setEstado( String estado ) {
            this.estado = estado;
    }

    /**
     * @return String
     */
    public String getMensaje() {
            return mensaje;
    }

    /**
     * @param mensaje 
     */
    public void setMensaje( String mensaje ) {
            this.mensaje = mensaje;
    }

    /**
     * @return String
     */
    public String getParametros() {
        return parametros;
    }

    /**
     * @param parametros 
     */
    public void setParametros( String parametros ) {
        this.parametros = parametros;
    }
}