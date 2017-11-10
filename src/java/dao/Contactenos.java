package dao;

import java.util.List;

/**
 * @modify Daniel Moreno
 * @Version 1.1
 */
public class Contactenos {

    private String nombreCiudad;

    private List<OficinasContactos> listaOficinas;

    /**
     * @return String
     */
    public String getNombreCiudad() {
        return nombreCiudad;
    }

    /**
     * @param nombreCiudad 
     */
    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    /**
     * @return List
     */
    public List<OficinasContactos> getListaOficinas() {
        return listaOficinas;
    }

    /**
     * @param listaOficinas 
     */
    public void setListaOficinas(List<OficinasContactos> listaOficinas) {
        this.listaOficinas = listaOficinas;
    }
}