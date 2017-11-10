package dao;

/**
 * @modify Daniel Moreno
 * @Version 1.1
 */
public class OficinasContactos {
    
    private String nombreOficina;

    private String telefonoOficina;

    private String telefonoPos;

    private String direccion;

    public OficinasContactos() {
    }

    /**
     * @param nombreOficina
     * @param telefonoOficina
     * @param telefonoPos
     * @param direccion 
     */
    public OficinasContactos( String nombreOficina, String telefonoOficina, String telefonoPos, String direccion ) {
        this.nombreOficina = nombreOficina;
        this.telefonoOficina = telefonoOficina;
        this.telefonoPos = telefonoPos;
        this.direccion = direccion;
    }

    /**
     * @return String
     */
    public String getNombreOficina() {
        return nombreOficina;
    }

    /**
     * @param nombreOficina
     */
    public void setNombreOficina( String nombreOficina ) {
        this.nombreOficina = nombreOficina;
    }

    /**
     * @return String
     */
    public String getTelefonoOficina() {
        return telefonoOficina;
    }

    /**
     * @param telefonoOficina
     */
    public void setTelefonoOficina( String telefonoOficina ) {
        this.telefonoOficina = telefonoOficina;
    }

    /**
     * @return String
     */
    public String getTelefonoPos() {
        return telefonoPos;
    }

    /**
     * @param telefonoPos 
     */
    public void setTelefonoPos( String telefonoPos ) {
        this.telefonoPos = telefonoPos;
    }

    /**
     * @return String
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion 
     */
    public void setDireccion( String direccion ) {
        this.direccion = direccion;
    }
}