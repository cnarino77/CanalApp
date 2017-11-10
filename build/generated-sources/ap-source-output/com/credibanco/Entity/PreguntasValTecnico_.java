package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PreguntasValTecnico.class)
public abstract class PreguntasValTecnico_ {

	public static volatile SingularAttribute<PreguntasValTecnico, String> textoPregunta;
	public static volatile SingularAttribute<PreguntasValTecnico, Boolean> visible;
	public static volatile CollectionAttribute<PreguntasValTecnico, RespuestasValTecnico> respuestasValTecnicoCollection;
	public static volatile SingularAttribute<PreguntasValTecnico, Integer> idPregunta;

}

