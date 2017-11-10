package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EstadosGeneral.class)
public abstract class EstadosGeneral_ {

	public static volatile SingularAttribute<EstadosGeneral, Integer> grupoEstado;
	public static volatile CollectionAttribute<EstadosGeneral, SolicitudMiPago> solicitudMiPagoCollection;
	public static volatile SingularAttribute<EstadosGeneral, Integer> idEstado;
	public static volatile SingularAttribute<EstadosGeneral, Boolean> visible;
	public static volatile SingularAttribute<EstadosGeneral, String> textoEstado;
	public static volatile CollectionAttribute<EstadosGeneral, ReporteFallas> reporteFallasCollection;
	public static volatile CollectionAttribute<EstadosGeneral, SolicitudPos> solicitudPosCollection;

}

