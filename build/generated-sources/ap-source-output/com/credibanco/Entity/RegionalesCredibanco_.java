package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RegionalesCredibanco.class)
public abstract class RegionalesCredibanco_ {

	public static volatile SingularAttribute<RegionalesCredibanco, Boolean> visible;
	public static volatile SingularAttribute<RegionalesCredibanco, Integer> idRegional;
	public static volatile CollectionAttribute<RegionalesCredibanco, SeccionalesCredibanco> seccionalesCredibancoCollection;
	public static volatile SingularAttribute<RegionalesCredibanco, String> codigoRegional;
	public static volatile SingularAttribute<RegionalesCredibanco, String> nombreRegional;

}

