package com.credibanco.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CapacitacionComercios.class)
public abstract class CapacitacionComercios_ {

	public static volatile SingularAttribute<CapacitacionComercios, Boolean> visible;
	public static volatile SingularAttribute<CapacitacionComercios, Integer> idCapacitacion;
	public static volatile SingularAttribute<CapacitacionComercios, String> descripcionCap;
	public static volatile SingularAttribute<CapacitacionComercios, CategoriasCapacitacion> categoriaCap;
	public static volatile SingularAttribute<CapacitacionComercios, TipoCapacitacion> tipoCapacitacion;
	public static volatile SingularAttribute<CapacitacionComercios, String> tituloCap;
	public static volatile SingularAttribute<CapacitacionComercios, String> enlaceCap;
	public static volatile SingularAttribute<CapacitacionComercios, Date> fechaCreacion;
	public static volatile SingularAttribute<CapacitacionComercios, byte[]> contenidoCap;
	public static volatile SingularAttribute<CapacitacionComercios, Integer> visitas;

}

