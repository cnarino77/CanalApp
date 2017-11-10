package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContactoComercio.class)
public abstract class ContactoComercio_ {

	public static volatile SingularAttribute<ContactoComercio, String> nombreContacto;
	public static volatile SingularAttribute<ContactoComercio, String> apellidoContacto;
	public static volatile SingularAttribute<ContactoComercio, String> fijoContacto;
	public static volatile SingularAttribute<ContactoComercio, String> movilContacto;
	public static volatile SingularAttribute<ContactoComercio, String> mailContacto;
	public static volatile SingularAttribute<ContactoComercio, String> idContacto;
	public static volatile CollectionAttribute<ContactoComercio, UsuariosComercios> usuariosComerciosCollection;
	public static volatile SingularAttribute<ContactoComercio, DatosComercio> datosComercio;
	public static volatile SingularAttribute<ContactoComercio, TipodocId> tipoIdContacto;

}

