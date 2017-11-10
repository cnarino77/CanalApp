package com.credibanco.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SeccionalesCredibanco.class)
public abstract class SeccionalesCredibanco_ {

	public static volatile SingularAttribute<SeccionalesCredibanco, String> nombreSeccional;
	public static volatile SingularAttribute<SeccionalesCredibanco, Boolean> visible;
	public static volatile SingularAttribute<SeccionalesCredibanco, RegionalesCredibanco> idRegional;
	public static volatile CollectionAttribute<SeccionalesCredibanco, CorreosSolicitudes> correosSolicitudesCollection;
	public static volatile CollectionAttribute<SeccionalesCredibanco, UsuariosInternos> usuariosInternosCollection;
	public static volatile CollectionAttribute<SeccionalesCredibanco, Ciudades> ciudadesCollection;
	public static volatile SingularAttribute<SeccionalesCredibanco, Integer> idSeccional;

}

