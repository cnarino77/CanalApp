package com.credibanco.as400.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.credibanco.as400.DaoVersion;
import org.apache.log4j.Logger;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
public class As400DaoVersion extends DaoVersion {

    static Logger logger = Logger.getLogger( As400DAO.class );

    public As400DaoVersion() {
    }

    /**
     * @param codigoComercio
     * @return boolean
     * @throws Exception
     */
    public Boolean validaCodigoSucurSalCorresponsal( String codigoComercio ) throws Exception {
        PreparedStatement st;
        ResultSet rs;
        boolean existe;
        int resultado;

        int ceros = 10 - codigoComercio.length();
        
        for(int i = 0; i < ceros; i++ ){
            codigoComercio = "0" + codigoComercio;
        }

        String dcdCmp = codigoComercio.substring(0, 4);
        String dcdSuc = codigoComercio.substring(4, 8);
        String dtpMov = codigoComercio.substring(8, 10);

        try {
            this.conectar("SISTEMAS");
            st = this.getCn().prepareStatement( "SELECT * FROM SWFTAB19 WHERE DCDCMP= ? AND DCDSUC = ? AND DTPMOV = ?" );
            st.setInt( 1, Integer.parseInt( dcdCmp ) );
            st.setInt( 2, Integer.parseInt( dcdSuc ) );
            st.setInt( 3, Integer.parseInt( dtpMov ) );
            rs = st.executeQuery();

            if ( rs.next() )
                System.out.println( "Estado: " +  rs.getString( "DCDCMP" ));

            resultado = rs.getRow();	

            existe = resultado > 0;

            return existe;
        } catch ( Exception exc ) {
            logger.error( " >>> validaCodigoSucurSalCorresponsal >>> " + exc.getMessage() );
            throw exc;
        } finally {
            this.Cerrar();
        }
    }

    /**
    * @param codigoComercio
    * @param nitComercio
    * @return Response
    * @throws Exception 
    */
    public boolean validarCodigoUnicoPortal( String codigoComercio ) throws Exception {
        PreparedStatement st;
        boolean response = false;
        ResultSet rs;

        String codigoEstado;

        if( validaCodigoComercios( codigoComercio )) {
            try {
                this.conectar("ESTABLE");
                st = this.getCn().prepareStatement( "SELECT * FROM ESFBASIC WHERE MCODES = ? " );
                st.setString( 1, codigoComercio );
                rs = st.executeQuery();
                rs.next();

                codigoEstado = rs.getString( "MESTAD" );

                if( codigoEstado.equals( "52" ) || codigoEstado.equals( "50" ) || codigoEstado.equals( "57" ) || codigoEstado.equals( "51" ) ||
                        codigoEstado.equals( "53" ) || codigoEstado.equals( "55" ) || codigoEstado.equals( "9" ) || codigoEstado.equals( "58" )||
                        codigoEstado.equals( "56" ) || codigoEstado.equals( "16" ))
                    response = false;
                else
                    response = true;

                logger.info( "Codigo Estado comercio consultado: "  + codigoEstado );
            } catch ( Exception exc ) {
                response = false;
                logger.error( " >>> validaEstadoComercio >>> " + exc.getMessage() );
                logger.error( " >>> validaEstadoComercio >>> " + exc);
            } finally {
                this.Cerrar();
            }
        }
        return response;
    }
    
    /**
     * @param codigoComercio
     * @return boolean
     * @throws Exception
     */
    public Boolean validaCodigoComercios( String codigoComercio ) throws Exception {
        PreparedStatement st;
        ResultSet rs;
        boolean existe;
        int resultado;

        try {
            this.conectar("ESTABLE");
            st = this.getCn().prepareStatement( "SELECT MCODES, MNITES, MESTAD FROM ESFBASIC WHERE MCODES = ?" );
            st.setString( 1, codigoComercio );
            rs = st.executeQuery();

            if ( rs.next() )
                System.out.println( "Estado: " +  rs.getString( "MESTAD" ));

            resultado = rs.getRow();	

            existe = resultado > 0;

            return existe;
        } catch ( Exception exc ) {
            logger.error( " >>> validaCodigoComercios >>> " + exc.getMessage() );
            throw exc;
        } finally {
            this.Cerrar();
        }
    }
}