package com.credibanco.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RolUsuario.class)
public abstract class RolUsuario_ {

	public static volatile SingularAttribute<RolUsuario, Integer> idRol;
	public static volatile SingularAttribute<RolUsuario, Boolean> visible;
	public static volatile SingularAttribute<RolUsuario, String> descripcionRol;
	public static volatile SingularAttribute<RolUsuario, String> nombreRol;
	public static volatile SingularAttribute<RolUsuario, Boolean> interno;
	public static volatile SingularAttribute<RolUsuario, Boolean> admin;
	public static volatile SingularAttribute<RolUsuario, Date> fechaCreacion;
	public static volatile CollectionAttribute<RolUsuario, UsuariosComercios> usuariosComerciosCollection;
	public static volatile CollectionAttribute<RolUsuario, UsuariosInternos> usuariosInternosCollection;
	public static volatile SingularAttribute<RolUsuario, Boolean> activo;

}

