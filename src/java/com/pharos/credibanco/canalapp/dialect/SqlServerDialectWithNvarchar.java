package com.pharos.credibanco.canalapp.dialect;

import java.sql.Types;

/**
 * @author Administrator
 */
public class SqlServerDialectWithNvarchar extends org.hibernate.dialect.SQLServerDialect {

    public SqlServerDialectWithNvarchar() {
        registerHibernateType( Types.NVARCHAR, 4000, "string" );
    }

}
