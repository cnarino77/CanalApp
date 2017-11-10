package com.credibanco.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MensajesPush.class)
public abstract class MensajesPush_ {

	public static volatile SingularAttribute<MensajesPush, Integer> idMensaje;
	public static volatile SingularAttribute<MensajesPush, String> messageText;
	public static volatile SingularAttribute<MensajesPush, Integer> destinoCiudad;
	public static volatile SingularAttribute<MensajesPush, String> messageEnlace;
	public static volatile SingularAttribute<MensajesPush, Date> fechaEnvio;
	public static volatile SingularAttribute<MensajesPush, String> destinatarios;
	public static volatile SingularAttribute<MensajesPush, String> messageTitle;
	public static volatile SingularAttribute<MensajesPush, Integer> remitente;
	public static volatile SingularAttribute<MensajesPush, Boolean> leido;
	public static volatile SingularAttribute<MensajesPush, Integer> destinoMCC;
	public static volatile SingularAttribute<MensajesPush, Integer> messageCapacitacion;

}

