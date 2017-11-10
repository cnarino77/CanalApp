package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ciudades.class)
public abstract class Ciudades_ {

	public static volatile SingularAttribute<Ciudades, Boolean> visible;
	public static volatile SingularAttribute<Ciudades, String> nombreCiudad;
	public static volatile CollectionAttribute<Ciudades, OficinasSeccionales> oficinasSeccionalesCollection;
	public static volatile SingularAttribute<Ciudades, SeccionalesCredibanco> seccional;
	public static volatile SingularAttribute<Ciudades, Departamentos> departamento;
	public static volatile CollectionAttribute<Ciudades, DatosComercio> datosComercioCollection;
	public static volatile SingularAttribute<Ciudades, Integer> idCiudad;

}

