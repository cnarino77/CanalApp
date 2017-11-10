package com.credibanco.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DatosProspecto.class)
public abstract class DatosProspecto_ {

	public static volatile SingularAttribute<DatosProspecto, String> nombreContacto;
	public static volatile SingularAttribute<DatosProspecto, String> razonSocial;
	public static volatile SingularAttribute<DatosProspecto, String> apellidoContacto;
	public static volatile SingularAttribute<DatosProspecto, DatosProspectoPK> datosProspectoPK;
	public static volatile SingularAttribute<DatosProspecto, Ciudades> ciudad;
	public static volatile SingularAttribute<DatosProspecto, Date> fechaRegistro;
	public static volatile SingularAttribute<DatosProspecto, String> atendido;
	public static volatile SingularAttribute<DatosProspecto, String> direccion;
	public static volatile SingularAttribute<DatosProspecto, String> numMovil;
	public static volatile SingularAttribute<DatosProspecto, String> numFijo;
	public static volatile SingularAttribute<DatosProspecto, Date> fechaAtendido;
	public static volatile SingularAttribute<DatosProspecto, String> correoElectronico;

}

