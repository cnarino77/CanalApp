package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Mcc.class)
public abstract class Mcc_ {

	public static volatile SingularAttribute<Mcc, String> codigoMCC;
	public static volatile SingularAttribute<Mcc, String> tipo;
	public static volatile SingularAttribute<Mcc, Integer> idMCC;
	public static volatile CollectionAttribute<Mcc, DatosComercio> datosComercioCollection;

}

