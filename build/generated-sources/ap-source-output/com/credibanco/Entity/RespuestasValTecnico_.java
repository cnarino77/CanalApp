package com.credibanco.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RespuestasValTecnico.class)
public abstract class RespuestasValTecnico_ {

	public static volatile SingularAttribute<RespuestasValTecnico, String> textoRespuesta;
	public static volatile SingularAttribute<RespuestasValTecnico, Integer> idRespuesta;
	public static volatile SingularAttribute<RespuestasValTecnico, Date> fechaRespuesta;
	public static volatile SingularAttribute<RespuestasValTecnico, PreguntasValTecnico> idPregunta;
	public static volatile SingularAttribute<RespuestasValTecnico, TecnicoVisita> respIdTecnico;

}

