package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DatosComercio.class)
public abstract class DatosComercio_ {

	public static volatile CollectionAttribute<DatosComercio, ContactoComercio> contactoComercioCollection;
	public static volatile SingularAttribute<DatosComercio, String> razonSocial;
	public static volatile SingularAttribute<DatosComercio, String> nombreComercio;
	public static volatile SingularAttribute<DatosComercio, Ciudades> ciudad;
	public static volatile SingularAttribute<DatosComercio, String> direccion;
	public static volatile SingularAttribute<DatosComercio, DatosComercioPK> datosComercioPK;
	public static volatile SingularAttribute<DatosComercio, Mcc> mcc;

}

