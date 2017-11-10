package com.credibanco.as400.model;

import com.credibanco.as400.ComerciosAS400;
import com.credibanco.dto.Response;
import java.sql.PreparedStatement;
import com.credibanco.as400.DAO;
import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
public class As400DAO extends DAO {

    static Logger logger = Logger.getLogger( As400DAO.class );

    public As400DAO() {
    }

    /**
     * @return List
     */
    public List<ComerciosAS400> getAllcomerciosAS400s() {
        List<ComerciosAS400> userList = null;
        return userList;
    }

    /**
     * @param codigoComercio
     * @return boolean
     * @throws Exception
     */
    public Boolean validaCodigoComercios( String codigoComercio, String nit ) {
        PreparedStatement st;
        ResultSet rs;
        boolean existe;
        int resultado;

        try {
            this.conectar();
            st = this.getCn().prepareStatement( "SELECT MCODES, MNITES, MESTAD FROM ESFBASIC WHERE MCODES = ? AND MNITES = ?" );
            st.setString( 1, codigoComercio );
            st.setString( 2, nit );
            rs = st.executeQuery();

            if ( rs.next() )
                System.out.println( "Estado: " +  rs.getString( "MESTAD" ));

            resultado = rs.getRow();	
            System.out.println(resultado);
            existe = resultado > 0;

            return existe;
        } catch ( Exception exc ) {
            System.out.println( "Exception: " + exc );
            exc.printStackTrace();
            return false;
        } finally {
            try {
                this.Cerrar();
            } catch (Exception exc) {
                
            }
        }
    }

   /**
    * @param codigoComercio
    * @param nitComercio
    * @return Response
    * @throws Exception 
    */
    public Response validaEstadoComercio( String codigoComercio, String nit ) throws Exception {
        PreparedStatement st;
        Response response = null;
        ResultSet rs;

        String codigoEstado;

        if( validaCodigoComercios( codigoComercio, nit )) {
            try {
                logger.info( "Inicio Conexion con ESFRBASIC" );
                this.conectar();
                st = this.getCn().prepareStatement( "SELECT * FROM ESFBASIC WHERE MCODES = ?" );
                st.setString( 1, codigoComercio );
                
                rs = st.executeQuery();
                rs.next();

                codigoEstado = rs.getString( "MESTAD" );
                String parametros = rs.getString( "MRASOC" ).trim() + "," + rs.getString( "MTIPNE" ).trim() + "," + rs.getString( "MNOMES" ).trim();

                if( codigoEstado.equals( "52" ) || codigoEstado.equals( "50" ) || codigoEstado.equals( "57" ) 
                        || codigoEstado.equals( "51" ) || codigoEstado.equals( "53" ) || codigoEstado.equals( "55" )
                        || codigoEstado.equals( "9" )  || codigoEstado.equals( "58" ) || codigoEstado.equals( "56" )
                        || codigoEstado.equals( "16" ) || codigoEstado.equals( "09" ))
                    response = new Response( "false", "", "" );
                else
                    response = new Response( "true", "SE ENCUENTRA HABILITADO", "" );

                response.setParametros( parametros );
                logger.info( "Respuesta ESFBASIC: Codigo Estado: "  + codigoEstado + " Descripcion: " + response.getMensaje() + " estado:" + response.getEstado() );
            } catch ( Exception exc ) {
                response = new Response( "false", "NO SE LOGRO VALIDAR EL COMERCIO CON LA INFORMACION INGRESADA", "" );
                logger.error( " >>> validaEstadoComercio >>> " + exc.getMessage() );
                logger.error( " >>> validaEstadoComercio >>> " + exc);
            }
        } else {
            response = new Response( "false", "NO SE LOGRO VALIDAR EL COMERCIO CON LA INFORMACION INGRESADA", "" );
        }
        return response;
    }
}