package dao;

import com.credibanco.Entity.TipodocId;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
public class MiPago {
    private String nombre;

    private String apellido;

    private String correo;

    private TipodocId tipoId;

    private String numeroId;

    private String numeroCelular;

    private String numeroFijo;

    private String razonSocial;

    public MiPago() {
    }

    /**
     * @param nombre
     * @param apellido
     * @param correo
     * @param tipoId
     * @param numeroId
     * @param numeroCelular
     * @param numeroFijo
     * @param razonSocial 
     */
    public MiPago( String nombre, String apellido, String correo, TipodocId tipoId, String numeroId, String numeroCelular, String numeroFijo, String razonSocial ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.tipoId = tipoId;
        this.numeroId = numeroId;
        this.numeroCelular = numeroCelular;
        this.numeroFijo = numeroFijo;
        this.razonSocial = razonSocial;
    }

    /**
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     */
    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }

    /**
     * @return String
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido
     */
    public void setApellido( String apellido ) {
        this.apellido = apellido;
    }

    /**
     * @return String
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo
     */
    public void setCorreo( String correo ) {
        this.correo = correo;
    }

    /**
     * @return TipodocId
     */
    public TipodocId getTipoId() {
        return tipoId;
    }

    /**
     * @param tipoId
     */
    public void setTipoId( TipodocId tipoId ) {
        this.tipoId = tipoId;
    }

    /**
     * @return String
     */
    public String getNumeroId() {
        return numeroId;
    }

    /**
     * @param numeroId
     */
    public void setNumeroId( String numeroId ) {
        this.numeroId = numeroId;
    }

    /**
     * @return String
     */
    public String getNumeroCelular() {
        return numeroCelular;
    }

    /**
     * @param numeroCelular
     */
    public void setNumeroCelular( String numeroCelular ) {
        this.numeroCelular = numeroCelular;
    }

    /**
     * @return String
     */
    public String getNumeroFijo() {
        return numeroFijo;
    }

    /**
     * @param numeroFijo 
     */
    public void setNumeroFijo( String numeroFijo ) {
        this.numeroFijo = numeroFijo;
    }

    /**
     * @return String
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial 
     */
    public void setRazonSocial( String razonSocial ) {
        this.razonSocial = razonSocial;
    }
}