package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipoPOS.class)
public abstract class TipoPOS_ {

	public static volatile SingularAttribute<TipoPOS, Boolean> visible;
	public static volatile SingularAttribute<TipoPOS, Integer> idTipoPos;
	public static volatile CollectionAttribute<TipoPOS, ReporteFallas> reporteFallasCollection;
	public static volatile SingularAttribute<TipoPOS, String> tipoPos;
	public static volatile CollectionAttribute<TipoPOS, SolicitudPos> solicitudPosCollection;

}

