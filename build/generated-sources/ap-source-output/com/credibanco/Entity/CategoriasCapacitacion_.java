package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoriasCapacitacion.class)
public abstract class CategoriasCapacitacion_ {

	public static volatile SingularAttribute<CategoriasCapacitacion, Boolean> visible;
	public static volatile SingularAttribute<CategoriasCapacitacion, String> categoria;
	public static volatile SingularAttribute<CategoriasCapacitacion, Integer> idCategoria;
	public static volatile SingularAttribute<CategoriasCapacitacion, Integer> visitas;
	public static volatile CollectionAttribute<CategoriasCapacitacion, CapacitacionComercios> capacitacionComerciosCollection;

}

