package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CanalesOficinas.class)
public abstract class CanalesOficinas_ {

	public static volatile SingularAttribute<CanalesOficinas, String> telefonoInformacion;
	public static volatile SingularAttribute<CanalesOficinas, String> nombreOficina;
	public static volatile SingularAttribute<CanalesOficinas, String> correoOficina;
	public static volatile SingularAttribute<CanalesOficinas, OficinasSeccionales> oficinasSeccionales;
	public static volatile SingularAttribute<CanalesOficinas, CanalesOficinasPK> canalesOficinasPK;
	public static volatile SingularAttribute<CanalesOficinas, String> telefonoPOS;

}

