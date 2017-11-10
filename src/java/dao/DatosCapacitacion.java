package dao;

/**
 * @modify Daniel Moreno
 * @Version 1.1
 */
public class DatosCapacitacion {

    private String idCapacitacion;

    private String descripcion;

    private String titulo;

    private String enlace;

    public DatosCapacitacion() {
    }

    /**
     * @param idCapacitacion
     * @param descripcion
     * @param titulo
     * @param enlace 
     */
    public DatosCapacitacion( String idCapacitacion, String descripcion, String titulo, String enlace ) {
        this.idCapacitacion = idCapacitacion;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.enlace = enlace;
    }

    /**
     * @return String
     */
    public String getIdCapacitacion() {
        return idCapacitacion;
    }

    /**
     * @param idCapacitacion
     */
    public void setIdCapacitacion( String idCapacitacion ) {
        this.idCapacitacion = idCapacitacion;
    }

    /**
     * @return String
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion 
     */
    public void setDescripcion( String descripcion ) {
        this.descripcion = descripcion;
    }

    /**
     * @return String
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo
     */
    public void setTitulo( String titulo ) {
        this.titulo = titulo;
    }

    /**
     * @return String
     */
    public String getEnlace() {
        return enlace;
    }

    /**
     * @param enlace
     */
    public void setEnlace( String enlace ) {
        this.enlace = enlace;
    }
}