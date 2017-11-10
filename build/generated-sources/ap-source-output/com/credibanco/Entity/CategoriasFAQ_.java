package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoriasFAQ.class)
public abstract class CategoriasFAQ_ {

	public static volatile SingularAttribute<CategoriasFAQ, Boolean> visible;
	public static volatile SingularAttribute<CategoriasFAQ, String> categoria;
	public static volatile CollectionAttribute<CategoriasFAQ, Faq> faqCollection;
	public static volatile SingularAttribute<CategoriasFAQ, Integer> idCategoria;
	public static volatile SingularAttribute<CategoriasFAQ, Integer> visitas;

}

