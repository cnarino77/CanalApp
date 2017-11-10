package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EstadoTecnico.class)
public abstract class EstadoTecnico_ {

	public static volatile CollectionAttribute<EstadoTecnico, TecnicoVisita> tecnicoVisitaCollection;
	public static volatile SingularAttribute<EstadoTecnico, Integer> idEstado;
	public static volatile SingularAttribute<EstadoTecnico, Boolean> visible;
	public static volatile SingularAttribute<EstadoTecnico, String> estadoTecnico;

}

