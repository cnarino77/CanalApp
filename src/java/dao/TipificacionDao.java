package dao;

/**
 * @modify Daniel Moreno
 * @Version 1.1
 */
public class TipificacionDao {

    private String idTipificacion;

    private String tipificacion;

    public TipificacionDao() {
    }

    /**
     * @param idTipificacion
     * @param tipificacion 
     */
    public TipificacionDao( String idTipificacion, String tipificacion ) {
        this.idTipificacion = idTipificacion;
        this.tipificacion = tipificacion;
    }

    /**
     * @return String
     */
    public String getIdTipificacion() {
        return idTipificacion;
    }

    /**
     * @param idTipificacion 
     */
    public void setIdTipificacion( String idTipificacion ) {
        this.idTipificacion = idTipificacion;
    }

    /**
     * @return String
     */
    public String getTipificacion() {
        return tipificacion;
    }

    /**
     * @param tipificacion 
     */
    public void setTipificacion( String tipificacion ) {
        this.tipificacion = tipificacion;
    }
}