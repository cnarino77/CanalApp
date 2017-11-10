package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OficinasSeccionales.class)
public abstract class OficinasSeccionales_ {

	public static volatile SingularAttribute<OficinasSeccionales, Ciudades> ciudadOficina;
	public static volatile SingularAttribute<OficinasSeccionales, Boolean> visible;
	public static volatile SingularAttribute<OficinasSeccionales, Integer> idOficina;
	public static volatile SingularAttribute<OficinasSeccionales, String> direccionOficina;
	public static volatile CollectionAttribute<OficinasSeccionales, CanalesOficinas> canalesOficinasCollection;

}

