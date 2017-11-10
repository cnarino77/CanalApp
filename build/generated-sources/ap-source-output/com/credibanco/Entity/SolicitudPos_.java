package com.credibanco.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SolicitudPos.class)
public abstract class SolicitudPos_ {

	public static volatile SingularAttribute<SolicitudPos, String> nombreEvento;
	public static volatile SingularAttribute<SolicitudPos, SolicitudPosPK> solicitudPosPK;
	public static volatile SingularAttribute<SolicitudPos, Date> fechaInicioEvento;
	public static volatile SingularAttribute<SolicitudPos, String> direccionStand;
	public static volatile SingularAttribute<SolicitudPos, UsuariosComercios> usuariosComercios;
	public static volatile SingularAttribute<SolicitudPos, Date> fechaRespuesta;
	public static volatile SingularAttribute<SolicitudPos, EstadosGeneral> idEstado;
	public static volatile SingularAttribute<SolicitudPos, DestinoPOS> destinoPOS;
	public static volatile SingularAttribute<SolicitudPos, Date> fechaCreacion;
	public static volatile SingularAttribute<SolicitudPos, String> respuesta;
	public static volatile SingularAttribute<SolicitudPos, Date> fechaRetiro;
	public static volatile SingularAttribute<SolicitudPos, String> stand;
	public static volatile SingularAttribute<SolicitudPos, Boolean> horarioAM;
	public static volatile SingularAttribute<SolicitudPos, String> ubicacionStand;
	public static volatile SingularAttribute<SolicitudPos, TipoPOS> tipoPOS;
	public static volatile SingularAttribute<SolicitudPos, Integer> cantidadPOS;

}

