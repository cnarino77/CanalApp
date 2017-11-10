package com.credibanco.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReporteFallas.class)
public abstract class ReporteFallas_ {

	public static volatile SingularAttribute<ReporteFallas, String> descripcion;
	public static volatile SingularAttribute<ReporteFallas, Date> fechaCierre;
	public static volatile SingularAttribute<ReporteFallas, EstadosGeneral> idEstado;
	public static volatile SingularAttribute<ReporteFallas, TipoPOS> tipoTecnologia;
	public static volatile SingularAttribute<ReporteFallas, Date> fechaCreacion;
	public static volatile SingularAttribute<ReporteFallas, UsuariosComercios> usuariosComercios;
	public static volatile SingularAttribute<ReporteFallas, ReporteFallasPK> reporteFallasPK;
	public static volatile SingularAttribute<ReporteFallas, TipificacionSM> tipificacion;

}

