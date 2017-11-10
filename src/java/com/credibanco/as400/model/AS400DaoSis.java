/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credibanco.as400.model;

import com.credibanco.as400.DaoSys;
import static com.credibanco.as400.model.As400DAO.logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Daniel Moreno
 */
public class AS400DaoSis extends DaoSys {

    public AS400DaoSis() {
    }
    
    /**
     * @param codigoComercio
     * @return boolean
     * @throws Exception
     */
    public Boolean validaCodigoComercios21( String codigoComercio ) throws Exception {
        /*PreparedStatement st;
        ResultSet rs;
        boolean existe;
        int resultado;

        try {
            logger.info( "Inicio Conexion REFTAB21" );
            this.conectar();
            logger.info( "Crear Conexion REFTAB21" );
            st = this.getCn().prepareStatement( "SELECT * FROM REFTAB21 WHERE MCODES = ? AND MCOMER IN ('404','407','408','409','410', '451')" );
            st.setString( 1, codigoComercio );
            logger.error( " >>> Ejecuta Consulta REFTAB21>>> "  );

            rs = st.executeQuery();

            logger.error( " >>> Obtiene resultado REFTAB21>>> "  );
            if ( rs.next() )
                System.out.println( "Estado MCOMER: " +  rs.getString( "MCOMER" ));

            resultado = rs.getRow();

            existe = resultado > 0;

            return existe;
        } catch ( Exception exc ) {
            logger.error( " >>> validaCodigoComercios >>> " + exc.getMessage() );
            throw exc;
        } finally {
            this.Cerrar();
        }*/
        return false;
    }
    //SISTEMAS.REFTAB21, MTIPTE
    public boolean validaComercioCorresponsal(String codigoComercio) throws Exception{
        PreparedStatement st;
        ResultSet rs;
        boolean existe;
        int resultado;

        try {
            logger.info( "Inicio Conexion REFTAB21" );
            this.conectar();
            logger.info( "Crear Conexion REFTAB21" );
            st = this.getCn().prepareStatement( "SELECT * FROM REFTAB21 WHERE MCODES = ? AND (MTIPTE = '16' OR MCOMER = '451')" );
            st.setString( 1, codigoComercio );
            logger.error( " >>> Ejecuta Consulta REFTAB21 MPITES>>> "  );
            rs = st.executeQuery();
            System.out.println("SELECT * FROM REFTAB21 WHERE MCODES = " + codigoComercio + " AND (MTIPTE = '16' OR MCOMER = '451')");
            if ( rs.next() )
                System.out.println( "Estado MTIPTE: " +  rs.getString( "MTIPTE" ));

            resultado = rs.getRow();
            System.out.println( "Estado MTIPTE: resultado " +  resultado );
            existe = resultado > 0;
        } catch (Exception exc) {
            System.out.println( "Fallo REFTAB21" );
            exc.printStackTrace();
            existe = false;
        }
        return existe;
    }
}