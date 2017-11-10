package com.credibanco.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SolicitudMiPago.class)
public abstract class SolicitudMiPago_ {

	public static volatile SingularAttribute<SolicitudMiPago, EstadosGeneral> idEstado;
	public static volatile SingularAttribute<SolicitudMiPago, Integer> cantidadMiPago;
	public static volatile SingularAttribute<SolicitudMiPago, Date> fechaCreacion;
	public static volatile SingularAttribute<SolicitudMiPago, SolicitudMiPagoPK> solicitudMiPagoPK;
	public static volatile SingularAttribute<SolicitudMiPago, Date> fechaAtencion;
	public static volatile SingularAttribute<SolicitudMiPago, String> respuesta;
	public static volatile SingularAttribute<SolicitudMiPago, UsuariosComercios> usuariosComercios;
	public static volatile SingularAttribute<SolicitudMiPago, Date> fechaRespuesta;

}

