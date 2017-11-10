package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DestinoPOS.class)
public abstract class DestinoPOS_ {

	public static volatile SingularAttribute<DestinoPOS, Boolean> visible;
	public static volatile SingularAttribute<DestinoPOS, String> destinoPos;
	public static volatile CollectionAttribute<DestinoPOS, SolicitudPos> solicitudPosCollection;
	public static volatile SingularAttribute<DestinoPOS, Integer> idDestino;

}

