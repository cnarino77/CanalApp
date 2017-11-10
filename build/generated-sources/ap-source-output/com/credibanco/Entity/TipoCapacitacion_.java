package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipoCapacitacion.class)
public abstract class TipoCapacitacion_ {

	public static volatile SingularAttribute<TipoCapacitacion, String> tipoCapacitacion;
	public static volatile SingularAttribute<TipoCapacitacion, Integer> idTipo;
	public static volatile CollectionAttribute<TipoCapacitacion, CapacitacionComercios> capacitacionComerciosCollection;

}

