package com.credibanco.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PoliticaTratamiento.class)
public abstract class PoliticaTratamiento_ {

	public static volatile SingularAttribute<PoliticaTratamiento, Integer> idPolicy;
	public static volatile SingularAttribute<PoliticaTratamiento, Integer> policyVersion;
	public static volatile SingularAttribute<PoliticaTratamiento, String> policyText;
	public static volatile SingularAttribute<PoliticaTratamiento, Date> fechaPublicacion;

}

