package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Departamentos.class)
public abstract class Departamentos_ {

	public static volatile SingularAttribute<Departamentos, Integer> idDepartamento;
	public static volatile SingularAttribute<Departamentos, Boolean> visible;
	public static volatile SingularAttribute<Departamentos, String> nombreDepartamento;
	public static volatile CollectionAttribute<Departamentos, Ciudades> ciudadesCollection;

}

