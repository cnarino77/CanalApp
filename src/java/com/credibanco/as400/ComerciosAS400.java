package com.credibanco.as400;

import java.io.Serializable;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
public class ComerciosAS400 implements Serializable{
    
    private int  MCODES;

    private int MNITES;

    private String MESTAD;

    public ComerciosAS400() {
    }

    /**
     * @param MCODES
     * @param MNITES
     * @param MESTAD 
     */
    public ComerciosAS400( int MCODES, int MNITES, String MESTAD ) {
        this.MCODES = MCODES;
        this.MNITES = MNITES;
        this.MESTAD = MESTAD;
    }

    /**
     * @return int
     */
    public int getMCODES() {
        return MCODES;
    }

    /**
     * @param MCODES
     */
    public void setMCODES( int MCODES ) {
        this.MCODES = MCODES;
    }

    /**
     * @return int
     */
    public int getMNITES() {
        return MNITES;
    }

    /**
     * @param MNITES
     */
    public void setMNITES( int MNITES ) {
        this.MNITES = MNITES;
    }

    /**
     * @return String
     */
    public String getMESTAD() {
        return MESTAD;
    }

    /**
     * @param MESTAD
     */
    public void setMESTAD( String MESTAD ) {
        this.MESTAD = MESTAD;
    }
}