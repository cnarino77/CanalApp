package com.credibanco.as400;

import java.sql.DriverManager;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 * @modify Daniel Moreno
 * @Version 1.1
 */
public class DaoSys {
    
    Connection cn;

    static Logger logger = Logger.getLogger( DAO.class );

    public DaoSys() {
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn( Connection cn ) {
        this.cn = cn;
    }

    /**
     * @throws Exception 
     * 
     * @Description crea la conexion con la BD de as400
     */
    public void conectar() throws Exception {
        try {
            Class.forName( "com.ibm.as400.access.AS400JDBCDriver" );
            cn = DriverManager.getConnection( "jdbc:as400://172.16.0.20/SISTEMAS", "CANALAPP", "ge3espEx" );
            //cn = DriverManager.getConnection( "jdbc:as400://172.26.23.11/SISTEMAS", "CBDINTER01", "CDNE0VISA" );
        } catch ( Exception exc ) {
            logger.error( " >>> conectar >>> " + exc.getMessage() );
            throw exc;
       }
    }

    /**
     * @throws Exception
     *
     * @description Cierra la conexion a la BD de as400
     */
    public void Cerrar() throws Exception {
        try {
            if( cn != null ) {
                if( cn.isClosed() == false )
                    cn.close();
            }
        } catch ( Exception exc ) {
            logger.error( " >>> Cerrar >>> " + exc.getMessage() );
            throw exc;
       }
   }
}