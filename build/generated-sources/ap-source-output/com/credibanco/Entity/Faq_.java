package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Faq.class)
public abstract class Faq_ {

	public static volatile SingularAttribute<Faq, Boolean> visible;
	public static volatile SingularAttribute<Faq, Integer> versionPregunta;
	public static volatile SingularAttribute<Faq, String> tituloPregunta;
	public static volatile SingularAttribute<Faq, CategoriasFAQ> categoriaPregunta;
	public static volatile SingularAttribute<Faq, String> respuestaPregunta;
	public static volatile SingularAttribute<Faq, Integer> idPregunta;
	public static volatile SingularAttribute<Faq, Long> visitas;

}

