package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipodocId.class)
public abstract class TipodocId_ {

	public static volatile CollectionAttribute<TipodocId, TecnicoVisita> tecnicoVisitaCollection;
	public static volatile CollectionAttribute<TipodocId, ContactoComercio> contactoComercioCollection;
	public static volatile SingularAttribute<TipodocId, String> tipo;
	public static volatile SingularAttribute<TipodocId, Boolean> visible;
	public static volatile SingularAttribute<TipodocId, Integer> idTipo;

}

