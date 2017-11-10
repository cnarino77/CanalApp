package com.credibanco.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TecnicoVisita.class)
public abstract class TecnicoVisita_ {

	public static volatile SingularAttribute<TecnicoVisita, byte[]> fotoTecnico;
	public static volatile SingularAttribute<TecnicoVisita, String> telefonoTecnico;
	public static volatile SingularAttribute<TecnicoVisita, Date> fechaModificacion;
	public static volatile SingularAttribute<TecnicoVisita, Boolean> borrado;
	public static volatile SingularAttribute<TecnicoVisita, String> apellidoTecnico;
	public static volatile SingularAttribute<TecnicoVisita, Date> fechaCreacion;
	public static volatile SingularAttribute<TecnicoVisita, Integer> id;
	public static volatile SingularAttribute<TecnicoVisita, String> idTecnico;
	public static volatile SingularAttribute<TecnicoVisita, String> nombreTecnico;
	public static volatile SingularAttribute<TecnicoVisita, TipodocId> tipoIdTecnico;
	public static volatile SingularAttribute<TecnicoVisita, EstadoTecnico> estadoTecnico;

}

