package com.credibanco.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UsuariosInternos.class)
public abstract class UsuariosInternos_ {

	public static volatile SingularAttribute<UsuariosInternos, RolUsuario> idRol;
	public static volatile SingularAttribute<UsuariosInternos, Date> ultimoIngreso;
	public static volatile SingularAttribute<UsuariosInternos, Integer> idUsuario;
	public static volatile SingularAttribute<UsuariosInternos, Boolean> estadoUsuario;
	public static volatile SingularAttribute<UsuariosInternos, String> nombreUsuario;
	public static volatile SingularAttribute<UsuariosInternos, SeccionalesCredibanco> idSeccional;
	public static volatile SingularAttribute<UsuariosInternos, String> username;
	public static volatile SingularAttribute<UsuariosInternos, String> eMail;

}

