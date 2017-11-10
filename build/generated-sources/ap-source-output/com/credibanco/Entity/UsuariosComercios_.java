package com.credibanco.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UsuariosComercios.class)
public abstract class UsuariosComercios_ {

	public static volatile SingularAttribute<UsuariosComercios, String> passTemporal;
	public static volatile SingularAttribute<UsuariosComercios, RolUsuario> idRol;
	public static volatile SingularAttribute<UsuariosComercios, Date> fechaTerminos;
	public static volatile SingularAttribute<UsuariosComercios, Integer> versionTerminos;
	public static volatile SingularAttribute<UsuariosComercios, Date> fechaRegistro;
	public static volatile SingularAttribute<UsuariosComercios, Integer> idUsuario;
	public static volatile SingularAttribute<UsuariosComercios, ContactoComercio> idContacto;
	public static volatile SingularAttribute<UsuariosComercios, String> passUsuario;
	public static volatile SingularAttribute<UsuariosComercios, Boolean> accesoBloqueado;
	public static volatile SingularAttribute<UsuariosComercios, String> imei;
	public static volatile SingularAttribute<UsuariosComercios, Date> ultimoingreso;
	public static volatile SingularAttribute<UsuariosComercios, String> token;

}

