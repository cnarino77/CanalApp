package com.credibanco.appCanal.ejb;

import com.credibanco.Entity.CategoriasCapacitacion;
import com.credibanco.Entity.ConvencionesDireccion;
import com.credibanco.Entity.CapacitacionComercios;
import com.credibanco.Entity.LogUsuariosComercios;
import com.credibanco.Entity.RespuestasValTecnico;
import com.credibanco.Entity.PoliticaTratamiento;
import com.credibanco.Entity.Termsandconditions;
import com.credibanco.Entity.ParametrosGlobales;
import com.credibanco.Entity.MensajesAplicacion;
import com.credibanco.Entity.CorreosSolicitudes;
import com.credibanco.Entity.UsuariosComercios;
import com.credibanco.Entity.SolicitudMiPagoPK;
import com.credibanco.Entity.ContactoComercio;
import com.credibanco.Entity.DatosProspectoPK;
import com.credibanco.Entity.DatosComercioPK;
import com.credibanco.Entity.CanalesOficinas;
import com.credibanco.Entity.ReporteFallasPK;
import com.credibanco.Entity.SolicitudMiPago;
import com.credibanco.Entity.EstadosGeneral;
import com.credibanco.Entity.SolicitudPosPK;
import com.credibanco.Entity.DatosProspecto;
import com.credibanco.Entity.TipificacionSM;
import com.credibanco.Entity.DatosComercio;
import com.credibanco.Entity.CategoriasFAQ;
import com.credibanco.Entity.Departamentos;
import com.credibanco.Entity.ReporteFallas;
import com.credibanco.Entity.TecnicoVisita;
import com.credibanco.Entity.SolicitudPos;
import com.credibanco.Entity.MensajesPush;
import com.credibanco.Entity.RolUsuario;
import com.credibanco.Entity.DestinoPOS;
import com.credibanco.Entity.TipodocId;
import com.credibanco.Entity.Ciudades;
import com.credibanco.Entity.TipoPOS;
import com.credibanco.Entity.Faq;
import com.credibanco.Entity.Mcc;

/*import com.peregrine.servicecenter.pws.AppmovilInstanceType.Descripcion;
import com.peregrine.servicecenter.pws.Crcappmovil_Service;
import com.peregrine.servicecenter.pws.common.MessageType;
import com.peregrine.servicecenter.pws.AppmovilModelType;
import com.peregrine.servicecenter.pws.common.StringType;
import com.peregrine.servicecenter.pws.AppmovilKeysType;
import com.peregrine.servicecenter.pws.ObjectFactory;
import com.peregrine.servicecenter.pws.Crcappmovil;*/

import com.peregrine.servicecenter.PWS.AppmovilInstanceType;
import com.peregrine.servicecenter.PWS.AppmovilInstanceTypeDescripcion;
import com.peregrine.servicecenter.PWS.AppmovilKeysType;
import com.peregrine.servicecenter.PWS.AppmovilModelType;
import com.peregrine.servicecenter.PWS.Common.MessageType;
import com.peregrine.servicecenter.PWS.Common.StringType;
import com.peregrine.servicecenter.PWS.Crcappmovil_BindingStub;
import com.peregrine.servicecenter.PWS.Crcappmovil_ServiceLocator;
import com.peregrine.servicecenter.PWS.CreateappmovilRequest;
import com.peregrine.servicecenter.PWS.CreateappmovilResponse;

import com.credibanco.as400.model.As400DaoVersion;
import com.credibanco.as400.model.AS400DaoSis;
import com.credibanco.as400.model.As400DAO;
import com.credibanco.util.TripleDES;
import com.credibanco.dto.Response;
import com.credibanco.util.AES;

import dao.DatosCapacitacion;
import dao.OficinasContactos;
import dao.TipificacionDao;
import dao.MensajePushDao;
import dao.Contactenos;
import dao.MiPago;

import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Calendar;
import java.util.Random;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.BodyPart;
import javax.mail.Session;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.TrustManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.SecureRandom;

import javax.xml.ws.BindingProvider;
import javax.xml.bind.JAXBElement;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import java.text.SimpleDateFormat;
import javax.annotation.Resource;
import java.net.InetAddress;
import java.util.ArrayList;
import java.security.*;
import java.io.File;
import java.util.Iterator;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Stateless
@LocalBean
public class AppCanalWSEJB implements AppCanalWSEJBLocal {
    @Resource(mappedName="java:jboss/mail/canalapp")
    Session session;
    private String SECRET_KEY = "SecretKey";
    @PersistenceContext
    EntityManager em;
    
    private final String secretKey = "923725b7bd";

    private static final String PATTERN_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-°!“”#$%&/(.)=?@¡¿’*¨´+}{\\[\\]_:;,><~`\\\\^])(?=\\S+$).{8,}$";

    private static final String PATTERN_NUMERO_TARJETA = "111111111";

    static Logger logger = Logger.getLogger( AppCanalWSEJB.class );
    
    //private SSLClientAxisEngineConfig config;

    public AppCanalWSEJB() {
    }
    
    /**
     * @param codigoComercio
     * @param nitComercio
     * @return Response
     * @throws Exception
     * 
     * @description Hace el llamado para el método de autentificación del comercio
     */
    @Override
    public Response authenticateComercios( String codigoComercio, String nitComercio ) throws Exception {
        As400DAO comercioAS400Dao = null;
        Response respuesta;
        try {
            comercioAS400Dao = new As400DAO();
        } catch ( Exception exc ) {
            logger.error( " >>> authenticateComercios >>> Error realizando la autentificación con el codigo de comercio: " + codigoComercio + " nit comercio: " + nitComercio );
            logger.error( " >>> authenticateComercios >>> " + exc.toString() );
        }
        respuesta = comercioAS400Dao.validaEstadoComercio( codigoComercio, nitComercio );
        
        if(respuesta.getEstado().equals("false")) {
            Query query = em.createQuery( "SELECT u FROM MensajesAplicacion u WHERE u.mensajesAplicacionPK.messageKey ='registroBloqueado'" );
            MensajesAplicacion mensajesAplicacion = ( MensajesAplicacion ) query.getSingleResult();
            respuesta.setMensaje(mensajesAplicacion.getMessageText());
        }
        return respuesta;
    }

    /**
     * @param email
     * @param password
     * @return boolean
     * @throws Exception
     * 
     * @description Actualiza el password del usuario usando como parametro la
     *               dirección de correo registrada
     */
    @Override
    public boolean changePassword( String email, String password ) throws Exception {
        Query query;
        int contador = 0;

        try {
            query = em.createNativeQuery( "UPDATE USUARIOS SET PASSWORD = '" + password + "', PASSWORDDATE=? where email='" + email + "'" );
            query.setParameter( 1, Calendar.getInstance().getTime());

            contador = query.executeUpdate();
        } catch( Exception exc ) {
            logger.error( " >>> changePassword >>> Error actualizando el Password para el Correo: " + email );
            logger.error( " >>> changePassword >>> " + exc.getMessage() );
        }
        return contador > 0;

    }

    /**
     * @param documentNumber
     * @param token
     * @return boolean
     * @throws Exception
     * 
     * @description Actualiza el numero de Imei para el usuario
     */
    @Override
    public boolean updateImei( String documentNumber, String token ) throws Exception {
        Query query;
        int contador = 0;

        try {
            query = em.createNativeQuery( "UPDATE usuarios_comercios SET Imei = '" + token + "' WHERE Id_Contacto='" + documentNumber + "'" );
            contador = query.executeUpdate();
        } catch( Exception exc ){
            logger.error( " >>> updateImei >>> Error actualizando el Imei para el Documento Número: " + documentNumber );
            logger.error( " >>> updateImei >>> " + exc.getMessage() );
            throw exc;
        }
        return contador > 0;
    }

    /**
     * @param idContacto
     * @return UsuariosComercios
     * @throws Exception 
     */
    @Override
    public UsuariosComercios findUser( String idContacto ) throws Exception {
        List<UsuariosComercios> listaUsuariosComercios;
        UsuariosComercios usuarioComercio = null;
        Query query;

        try {
        query = em.createQuery( "SELECT u FROM UsuariosComercios u WHERE u.idContacto.idContacto =:idcontacto" );
        query.setParameter( "idcontacto", idContacto );

        listaUsuariosComercios = query.getResultList();
        
        if( listaUsuariosComercios.size()>0 )
            usuarioComercio = listaUsuariosComercios.get(0);
        } catch( Exception exc ) {
            logger.error( " >>> findUser >>> Error con el idContacto" + idContacto );
            logger.error( " >>> findUser >>> " + exc.getMessage() );
            throw exc;
        }

        return usuarioComercio;
    }

    /**
     * @param idContacto
     * @param tipoIdentificacion
     * @param codUnico
     * @return UsuariosComercios
     * @throws Exception 
     */
    @Override
    public UsuariosComercios findUser( String idContacto, String tipoIdentificacion, String codUnico ) throws Exception {
        List<UsuariosComercios> listaUsuariosComercios;
        UsuariosComercios usuarioComercio = null;
        Query query;

        try {
            query = em.createQuery( "SELECT u FROM UsuariosComercios u WHERE u.idContacto.idContacto =:idcontacto AND u.idContacto.tipoIdContacto.idTipo =:tipoIdentificacion AND u.idContacto.datosComercio.datosComercioPK.codigoUnico =:codUnico" );
            query.setParameter( "idcontacto", idContacto );
            query.setParameter( "tipoIdentificacion", Integer.parseInt( tipoIdentificacion ));
            query.setParameter( "codUnico", codUnico );

            listaUsuariosComercios = query.getResultList();

            if( listaUsuariosComercios.size() > 0 ) 
                usuarioComercio = listaUsuariosComercios.get(0);
            else
                usuarioComercio = null;

        } catch( Exception exc ) {
            logger.error( " >>> findUser >>> Error con el Id Contacto: " + idContacto + " Tipo Identificacion: " + tipoIdentificacion + " Codigo Unico: " + codUnico );
            logger.error( " >>> findUser >>> " + exc.getMessage() );
            throw exc;
        }
        return usuarioComercio;       
    }

    /**
     * @param idContact
     * @param tipoIdentificacion
     * @param codUnico
     * @param tempPassword
     * @return UsuariosComercios
     * @throws Exception 
     */
    @Override
    public UsuariosComercios findUserTempPassword( String idContact, String tipoIdentificacion, String codUnico, String tempPassword ) throws Exception {
        List<UsuariosComercios> listaUsuariosComercios;
        UsuariosComercios usuarioComercio = null;
        Query query;

        try {
            query = em.createQuery( "SELECT u FROM UsuariosComercios u WHERE u.idContacto.idContacto =:idcontacto AND u.idContacto.tipoIdContacto.idTipo =:tipoIdentificacion AND u.idContacto.datosComercio.datosComercioPK.codigoUnico =:codUnico AND u.passTemporal =:passTemporal" );
            query.setParameter( "idcontacto", idContact );
            query.setParameter( "tipoIdentificacion", Integer.parseInt( tipoIdentificacion ));
            query.setParameter( "codUnico", codUnico );
            query.setParameter( "passTemporal", tempPassword );

            listaUsuariosComercios = query.getResultList();

            if( listaUsuariosComercios.size() > 0 )
                usuarioComercio = listaUsuariosComercios.get(0);
            else
                usuarioComercio = null;

        } catch( Exception exc ) {
            logger.error( " >>> findUserTempPassword >>> " + exc.getMessage() );
            throw exc;
        }
        return usuarioComercio;
    }

    /**
     * @param usuarioComercios
     * 
     * @description genera un password temporal para el usuario y hace el llamado para el envio del correo
     */
    @Override 
    public void generateTempPassword( UsuariosComercios usuarioComercios ) {
        String tempPassword;
        String mensaje ="";
        Query query;

        try {
            tempPassword = RandomStringUtils.randomAlphanumeric(8);
            query = em.createQuery( "UPDATE UsuariosComercios u SET u.passTemporal = '" + tempPassword + "' WHERE u.idContacto.idContacto =:contacto" );
            query.setParameter( "contacto", usuarioComercios.getIdContacto().getIdContacto() );
            query.executeUpdate();

            mensaje = "Buen día, <br/> Recibimos una solicitud de olvido de contraseña para tu ingreso al App de CredibanCo, por lo cual estamos enviando contraseña temporal. Recuerde que al momento de ingresar a la aplicación deberá digitar la siguiente contraseña y posteriormente se le solicitará cambio de la misma:<br/>"+ tempPassword+"<br/>Si tienes alguna consulta ó dificultad, comunícate con nosotros a través de nuestras líneas de atención a nivel nacional, consúltalas en  https://www.credibanco.com/credibanco/oficinas-credibanco.<br/>CredibanCo App";
            sendEmail( usuarioComercios.getIdContacto().getMailContacto(), "credibancoapp@credibanco.com", "Recuperación contraseña CredibanCo App", mensaje );
        } catch ( Exception exc ) {
            logger.error( " >>> generateTempPassword >>> Error generando el password temporal para el usuario: " + usuarioComercios.getIdContacto().getIdContacto() );
            logger.error( " >>> generateTempPassword >>> " + exc.getMessage() );
            throw exc;
        }
    }

    /**
     * @param usuarioComercio
     * @param password
     * @return Response
     * 
     * @description Realiza la actualización de la clave del contacto
     */
    @Override 
    public Response updatePassword( UsuariosComercios usuarioComercio, String password ) {
        Response response = null;
        Query query;

        try {
            if( validatePassword( password )) {
                query = em.createNativeQuery( "UPDATE dbo.usuarios_comercios SET Pass_Usuario = :passTemporal WHERE Id_Contacto=:contacto" );
                query.setParameter( "passTemporal", password );
                query.setParameter( "contacto", usuarioComercio.getIdContacto().getIdContacto() );
                query.executeUpdate();
                response = new Response( "true", "EL PASSWORD SE CAMBIO CORRECTAMENTE", "");
            }
            else
                response = new Response( "false", "EL PASSWORD INGRESADO NO ES VALIDO", "");
        } catch( Exception exc ) {
            logger.error( " >>> generateTempPassword >>> Error actualizando el password para el usuario: " + usuarioComercio.getIdContacto().getIdContacto() );
            logger.error( " >>> generateTempPassword >>> " + exc.getMessage() );
            throw exc;
        }
        return response;
    }
    /**
     * @param to
     * @param from
     * @param subject
     * @param content
     * 
     * @descripcion Se encarga del envío de cada uno de los correos de la aplicación
     */
    public void sendEmail(String to, String from, String subject, String content) {
        try {
            MimeMessage message = new MimeMessage( session );
            message.setFrom(new InternetAddress( from ));
            message.setRecipients( MimeMessage.RecipientType.TO,InternetAddress.parse( to ));
            message.setSubject( subject );
 
            MimeMultipart multipart = new MimeMultipart( "related" );

  	    BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<HTML><HEAD></HEAD><BODY>" + "<TABLE><tr><td> " + content + "</td></tr></TABLE></BODY></HTML>";
            messageBodyPart.setContent( htmlText, "text/html; charset=utf-8" );
	    multipart.addBodyPart( messageBodyPart );

  	    message.setContent( multipart );
            Transport.send( message );
	} catch ( MessagingException exc ) {
            logger.error( " >>> sendEmail >>> Sending Email from " + from + " to " + to + " : " + subject );
            logger.error( " >>> sendEmail >>> Error enviando el correo a los usuarios " + to );
            logger.error( " >>> sendEmail >>> " + exc.getMessage() );
	}
    }

    /**
     * @param tipoID
     * @param numeroIdentificacion
     * @param codigoUnico
     * @param contrasenia
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean authenticateUser( String tipoID, String numeroIdentificacion, String codigoUnico, String contrasenia ) throws Exception {
       	List<UsuariosComercios> listaUsuariosComercio;
       	boolean existe = false;
        Query query;

        try {
            query = em.createQuery( "SELECT uc from UsuariosComercios uc, ContactoComercio cc WHERE cc.idContacto = uc.idContacto.idContacto AND cc.tipoIdContacto.idTipo =:tipoIdContacto AND uc.idContacto.idContacto =:idContacto AND cc.datosComercio.datosComercioPK.codigoUnico =:codigoUnico AND uc.passUsuario =:passUsuario " );
            query.setParameter( "idContacto", numeroIdentificacion );
            query.setParameter( "tipoIdContacto", Integer.parseInt( tipoID ));
            query.setParameter( "codigoUnico", codigoUnico );
            query.setParameter( "passUsuario", contrasenia );

            listaUsuariosComercio = query.getResultList();
            existe = listaUsuariosComercio.size() > 0;

        } catch( Exception exc ) {
            logger.error( " >>> authenticateUser >>> Error haciendo la autenticación para el Tipo Id: " + tipoID + " Número Id: " + numeroIdentificacion + " Codigo Unico: " + codigoUnico );
            logger.error( " >>> authenticateUser >>> " + exc.getMessage() );
            throw exc;
        }
       	return existe;
    }

    /**
     * @param auditoria
     * 
     * @description Inserta el dato de auditoria de la opción utilizada por el usuario
     */
    @Override
    public void insertAudit( LogUsuariosComercios auditoria ) {
    	try {
            em.persist( auditoria );
    	}
    	catch( Exception exc ) {
            logger.error( " >>> insertAudit >>> " + exc.getMessage() );
            throw exc;
    	}
    }

    /**
     * @return Termsandconditions
     * @throws Exception
     * 
     * @description Devuelve la información de los terminos y condiciones más recientes.
     */
    @Override
    public Termsandconditions findTermsandconditions() throws Exception {
      	Termsandconditions terminos = new Termsandconditions();
        List<Termsandconditions> listaTerminos;
        Query query;

    	try {
            query = em.createQuery( "SELECT u FROM Termsandconditions u ORDER BY u.idTerms DESC" );

            listaTerminos = query.getResultList();

            if( listaTerminos.size() > 0 )
                terminos = listaTerminos.get(0);

        } catch( Exception exc ) {
            logger.error( " >>> findTermsandconditions >>> " + exc.getMessage() );
            throw exc;
        }
    	return terminos;
    }

    /**
     * @param idDepartamento
     * @return List
     * @throws Exception 
     * 
     * @description devuelve el listado de las ciudades:
     *              - si el parametro enviado es 0 devuelve todas
     *              - de lo contrario filtra por el idDepartamento enviado
     */
    @Override
    public List<Ciudades> getCiudades( int idDepartamento ) throws Exception {
       	List<Ciudades> listaCiudades = null;
        Query query;

        try {
            if( idDepartamento != 0 ) {
                query = em.createQuery( "SELECT u FROM Ciudades u WHERE u.visible=true AND u.departamento.idDepartamento =:idDepartamento" );
                query.setParameter( "idDepartamento", idDepartamento );
            }
            else
                query = em.createQuery( "SELECT u FROM Ciudades u WHERE u.visible = true" );

            listaCiudades = query.getResultList();
        } catch( Exception exc ) {
            logger.error( " >>> *** Inicio Log Error getCiudades *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " idDepartamento: " + idDepartamento );
            logger.error( " >>> *** Fin Log Error getCiudades *** >>> " );
            throw exc;
        }
    	return listaCiudades;
    }

    /**
     * @return List
     * @throws Exception
     * 
     * @description Devuelve el listado de departamentos
     */
    @Override
    public List<Departamentos> getDepartamentos() throws Exception {
    	List<Departamentos> listaDepartamentos = null;
        List<Departamentos> listaDepartamentosTemp = new ArrayList<>();
        Query query;

        try {
            query = em.createQuery( "SELECT u FROM Departamentos u WHERE u.visible = true" );
            listaDepartamentos = query.getResultList();

        }catch( Exception exc ) {
            logger.error( " >>> getDepartamentos >>> " + exc.getMessage() );
            throw exc;
        }
    	return  listaDepartamentos;
    }

    /**
     * @return List
     * @throws Exception
     
     * @description Devuelve el listado de los tipos de documentos de identificación
     */
    @Override
    public List<TipodocId> getTiposDocumentos() throws Exception {
        List<TipodocId> listaTipoDocumentos = null;
        Query query;

    	try {
            query = em.createQuery( "SELECT u FROM TipodocId u WHERE u.visible = true" );
            listaTipoDocumentos = query.getResultList();
        } catch( Exception exc ) {
            logger.error( " >>> getTiposDocumentos >>> " + exc.getMessage() );
            throw exc;
        }
    	return  listaTipoDocumentos;
    }
    
    /**
     * @return List
     * @throws Exception
     * 
     * @description Devuelve el listado de las categorias de preguntas frecuentes
     */
    @Override
    public List<CategoriasFAQ> getCategoriasFaq() throws Exception {
        List<CategoriasFAQ> listaCategoriasFaq = null;
        Query query;

        try {
            query = em.createQuery( "SELECT u FROM CategoriasFAQ u WHERE u.visible = true" );
            listaCategoriasFaq = query.getResultList();
        } catch( Exception exc ) {
            logger.error( " >>> getCategoriasFaq >>> " + exc.getMessage() );
            throw exc;
        }
    	return  listaCategoriasFaq;
    }
    
    /**
     * @return List
     * @param idCategoria
     * @throws Exception
     * 
     * @description Devuelve el listado de FAQ(Preguntas frecuentes) filtrando
     *               por la Categoria seleccionada por el usuario.
     */
    @Override
    public List<Faq> getFaq( int idCategoria ) throws Exception {
        List<Faq> listaFaq = null;
        Query query;

        try {
            CategoriasFAQ categoriasFAQ = ( CategoriasFAQ ) em.find( CategoriasFAQ.class, idCategoria );

            query = em.createQuery( "UPDATE CategoriasFAQ u SET u.visitas = (SELECT u.visitas FROM CategoriasFAQ u WHERE u.idCategoria =:idCategoria)+1 WHERE u.idCategoria =:idCategoria" );
            query.setParameter( "idCategoria", idCategoria );
            query.executeUpdate();

            query = em.createQuery( "SELECT u FROM Faq u WHERE u.visible=true AND u.categoriaPregunta.idCategoria =:idCategoria" );
            query.setParameter( "idCategoria", idCategoria );
            listaFaq = query.getResultList();
        } catch( Exception exc ) {
            logger.error( " >>> *** Inicio Log Error getFaq *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " idCategoria: " + idCategoria );
            logger.error( " >>> *** Fin Log Error getFaq *** >>> " );
            throw exc;
        }
    	return  listaFaq;
    }

    /**
     * @param idFaq
     * @throws Exception
     * 
     * @description Actualiza la cantidad de visitas realizadas a una
     *               pregunta frecuente
     */
    @Override
    public void actualizarFaq( String idFaq ) throws Exception {
        Query query;
        try {
            query = em.createQuery( "UPDATE Faq u SET u.visitas =(SELECT u.visitas FROM Faq u WHERE u.idPregunta =:idFaq )+1 WHERE u.idPregunta =:idFaq" );
            query.setParameter( "idFaq", Integer.parseInt( idFaq ));
            query.executeUpdate();
        } catch( Exception exc ) {
            logger.error( " >>> *** Inicio Log Error actualizarFaq *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " idFaq: " + idFaq );
            logger.error( " >>> *** Fin Log Error actualizarFaq *** >>> " );
            throw exc;
        }
    }

    /**
     * @param parametros
     * @return Response
     * @throws Exception
     * 
     * @description Realiza la adición de los datos de un nuevo comercio.
     */
    @Override
    public Response adicionarDatosComercio( String parametros ) throws Exception {
        UsuariosComercios usuariosComercio;
        ContactoComercio contactoComercio;
        DatosComercio datosComercio;
        Response response;
        Query query;

        String nombre = "", apellido = "", correo = "", tipoId = "", numId = "";
        String idCiudad = "", numeroCelular = "", numeroTelefono = "";
        String password, imei, nit, idMcc, direccion;
        String codigoUnico, razonSocial, nombreComercio;
        int versionTerminos, idRolUsuario;
        boolean validaUsuarioComercio = false;
        boolean validaDatosComercio = false;
        boolean validaContactoComercio = false;

        try {
            String llaves[] = obtenerLlaves();
            String ivKey = llaves[0];
            String saltKey = llaves[1];
            
            parametros = AES.decrypt( parametros, ivKey, saltKey );
            String listaParametros[] = parametros.split( "," );

            nombre = listaParametros[0];
            apellido = listaParametros[1];
            correo = listaParametros[2];
            tipoId = listaParametros[3];
            numId = listaParametros[4];
            idCiudad = listaParametros[5];
            numeroCelular = listaParametros[6];
            numeroTelefono = listaParametros[7];
            password = listaParametros[8];

            if( validatePassword( password )) {
                password = TripleDES._encrypt( password, SECRET_KEY );
                imei = listaParametros[9];
                nit = listaParametros[10];
                codigoUnico = listaParametros[11];
                razonSocial = listaParametros[12];
                idMcc = listaParametros[13];
                nombreComercio = listaParametros[14];
                direccion = listaParametros[15];
                idRolUsuario = 2;

                Termsandconditions termsandconditions = findTermsandconditions();
                versionTerminos = termsandconditions.getTermsVersion();

                query = em.createQuery( "SELECT u FROM UsuariosComercios u WHERE u.idContacto.idContacto =:numId" );
                query.setParameter( "numId", numId );

                Ciudades ciudad = ( Ciudades ) em.find( Ciudades.class, Integer.parseInt( idCiudad ));
                TipodocId tipoDocumento = ( TipodocId ) em.find( TipodocId.class, Integer.parseInt( tipoId ));
                RolUsuario rolUsuario = em.find( RolUsuario.class, idRolUsuario );
                Mcc mcc = ( Mcc ) em.find( Mcc.class, idMcc );//Traer de AS400

                try {
                    usuariosComercio = ( UsuariosComercios ) query.getSingleResult();
                }
                catch( Exception exc ) {
                    logger.info( " >>> No existe el Usuario Comercio, continua el proceso >>> " + exc.getMessage() );
                    validaUsuarioComercio = true;
                }

                query = em.createQuery( "SELECT u FROM DatosComercio u WHERE u.datosComercioPK.nitComercio =:nit AND u.datosComercioPK.codigoUnico =:codigoUnico" );
                query.setParameter( "nit", nit );
                query.setParameter( "codigoUnico", codigoUnico );

                try {
                    datosComercio = ( DatosComercio ) query.getSingleResult();
                }
                catch( Exception exc ) {
                    logger.info( " >>> No existe DatosComercio, continua el proceso >>> " + exc.getMessage() );
                    validaDatosComercio = true;
                }

                query = em.createQuery( "SELECT u FROM ContactoComercio u WHERE u.idContacto =:numId" );
                query.setParameter( "numId", numId );
                try {
                    contactoComercio = (ContactoComercio) query.getSingleResult();
                } catch( Exception exc ) {
                    logger.info( " >>> No existe el Contacto, continua el proceso >>> " + exc.getMessage() );
                    validaContactoComercio = true;
                }

                datosComercio = new DatosComercio( new DatosComercioPK( nit, codigoUnico ), razonSocial, nombreComercio, ciudad, mcc, direccion );
                contactoComercio = new ContactoComercio( numId, nombre, apellido, correo, numeroTelefono, numeroCelular, datosComercio, tipoDocumento );
                usuariosComercio = new UsuariosComercios( password, versionTerminos, new Date(), new Date(), new Date(), imei, contactoComercio, rolUsuario );

                if( !validaUsuarioComercio ) {
                    response = new Response( "false", "USUARIO INGRESADO YA EXISTE", "" );
                } else {
                    if( validaDatosComercio )
                        em.persist( datosComercio );
                    if( validaContactoComercio )
                        em.persist( contactoComercio );
                    em.persist( usuariosComercio );
                    em.flush();

                    response = new Response( "true", "DATOS INGRESADOS", generarToken( usuariosComercio ));
                    relacionarAuditoria( numId, "Adición Usuario Comercio", "" );//Llama él metodo para el registro de auditoria
                }
            } else {
                response = new Response( "false", "EL PASSWORD INGRESADO NO ES VALIDO", "" );
            }
        }
        catch( Exception exc ) {
            response = new Response( "false", "DATOS NO INGRESADOS", "");
            logger.error( " >>> *** Inicio Log Error adicionarDatosComercio *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " nombre: " + nombre + " apellido: " + apellido + " correo: " + correo );
            logger.error( " tipoId: " + tipoId + " numId: " + numId + " idCiudad: " + idCiudad );
            logger.error( " numero Celular: " + numeroCelular + " numero Telefono: " + numeroTelefono );
            logger.error( " >>> *** Fin Log Error adicionarDatosComercio *** >>> " );
            throw exc;
        }
        return response;
    }

    /**
     * @param parametros
     * @return Response
     * @throws Exception
     * 
     * @description Permite adicionar los datos de un nuevo prospecto.
     */
    @Override
    public Response adicionarDatosProspectos( String parametros ) throws Exception {
        DatosProspecto prospecto;
        Response response;
        Query query;

        String nombre = "", apellido = "", correoElectronico;
        String telefonoFijo, telefonoCelular, razonSocial;
        String tipoId = "", numId = "", idCiudad = "", idDepartamento = "";
        String direccion, asunto, mensaje;
        boolean ingresar = false;

        try {
            String llaves[] = obtenerLlaves();
            String ivKey = llaves[0];
            String saltKey = llaves[1];

            parametros = AES.decrypt( parametros, ivKey, saltKey );
            String listaParametros[] = parametros.split( "," );

            //Obtención de los parametros
            nombre = listaParametros[0];
            apellido = listaParametros[1];
            correoElectronico = listaParametros[2];
            telefonoFijo = listaParametros[3];
            telefonoCelular = listaParametros[4];
            razonSocial = listaParametros[5];
            tipoId = listaParametros[6];
            numId = listaParametros[7];
            idCiudad = listaParametros[8];
            idDepartamento = listaParametros[9];
            direccion = listaParametros[10];

            Ciudades ciudad = ( Ciudades ) em.find( Ciudades.class, Integer.parseInt( idCiudad ));
            Departamentos departamento = ( Departamentos ) em.find( Departamentos.class, Integer.parseInt( idDepartamento ));

            query = em.createQuery( "SELECT u FROM DatosProspecto u WHERE u.datosProspectoPK.numId =:numId AND u.datosProspectoPK.tipoId =:tipoId" );
            query.setParameter( "numId", numId );
            query.setParameter( "tipoId", Integer.parseInt( tipoId ));

            try {
                prospecto = ( DatosProspecto ) query.getSingleResult();
            } catch( Exception exc ) {
                ingresar = true;
                logger.info( " >>> No existe el Prospecto, continua el proceso >>> " + exc.getMessage() );
            }

            if( ingresar ) {
                prospecto = new DatosProspecto( new DatosProspectoPK( Integer.parseInt( tipoId ), numId ), razonSocial, ciudad, nombre, apellido, telefonoCelular,
                        telefonoFijo, correoElectronico, new Date(), "false", direccion );

                em.persist( prospecto );
                em.flush();

                response = new Response( "true", "DATOS INGRESADOS", "");
                relacionarAuditoria( numId, "Adición Prospecto", "" );//Llama él metodo para el registro de auditoria

                query = em.createQuery( "SELECT u FROM TipodocId u WHERE u.idTipo =:idTipo"  );
                query.setParameter( "idTipo", prospecto.getDatosProspectoPK().getTipoId() );
                TipodocId tipoDocId = ( TipodocId ) query.getSingleResult();

                asunto = "Comercio prospecto para afiliación CredibanCo";
                mensaje = "Buen día, estamos enviando datos de comercio interesado en afiliarse a CredibanCo. <br/>" +
                    "<br/>Nombre persona contacto: " + prospecto.getNombreContacto() + " " + prospecto.getApellidoContacto() +
                    "<br/>Correo Electronico: " + prospecto.getCorreoElectronico() +
                    "<br/>Teléfono fijo: " + prospecto.getNumFijo() +
                    "<br/>Teléfono celular: " + prospecto.getNumMovil() +
                    "<br/>Razón social del comercio: " + prospecto.getRazonSocial() +
                    "<br/>Tipo ID  del comercio: " + tipoDocId.getTipo() +
                    "<br/>Número ID del comercio con digito de chequeo: " + prospecto.getDatosProspectoPK().getNumId() +
                    "<br/>Ciudad, Departamento: " + prospecto.getCiudad().getNombreCiudad() + ", " + departamento.getNombreDepartamento() +
                    "<br/><br/>Agradecemos realizar la respectiva gestión y dar respuesta oportuna al comercio.<br/><br/>CredibanCo App";

                enviarCorreoInternos( ciudad.getSeccional().getIdSeccional(), asunto, mensaje, "PR" );
            } else {
                response = new Response( "false", "USUARIO YA EXISTIA", "");
            }
        } catch( Exception exc ) {
            response = new Response( "false", "DATOS NO INGRESADOS", "");

            logger.error( " >>> *** Inicio Log Error adicionarDatosProspectos *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " tipoId: " + tipoId + " numId: " + numId + " idCiudad: " + idCiudad );
            logger.error( " idDepartamento: " + idDepartamento + " nombre: " + nombre + " apellido: " + apellido );
            logger.error( " >>> *** Fin Log Error adicionarDatosProspectos *** >>> " );
            throw exc;
        }
        return response;
    }

    /**
     * @param parametros
     * @return Response
     * @throws Exception
     * 
     * @descripcion: Ingresa nueva solicitud POS para el comercio
     */
    @Override
    public Response adicionarSolicitudPos( String parametros ) {
        Response response;
        UsuariosComercios usuariosComercio;
        EstadosGeneral estadoGeneral;
        SolicitudPos solicitudPos;
        DestinoPOS destinoPos;
        TipoPOS tipoPos;
        Query query;

        String fechaInicio = "", fechaRetiro = "", nombreEvento = "", direccionEvento = "";
        String idTipoTecnologia = "", ubicacionStand = "", cantidadDatafonos = "";
        String horarioInstalacion = "", idContacto = "", asunto, mensaje, token = "";
        boolean horario = true;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat( "dd-MM-yyyy" );

            String llaves[] = obtenerLlaves();
            String ivKey = llaves[0];
            String saltKey = llaves[1];
            
            parametros = AES.decrypt( parametros, ivKey, saltKey );
            String listaParametros[] = parametros.split( "," );
            fechaInicio = listaParametros[0];
            fechaRetiro = listaParametros[1];
            nombreEvento = listaParametros[2];
            direccionEvento = listaParametros[3];
            ubicacionStand = listaParametros[4];
            cantidadDatafonos = listaParametros[5];
            idTipoTecnologia = listaParametros[6];
            horarioInstalacion = listaParametros[7];
            //idContacto = listaParametros[8];
            token = listaParametros[8];

            if(!horarioInstalacion.equals( "am" ))
                horario = false;

            tipoPos = em.find( TipoPOS.class, Integer.parseInt( idTipoTecnologia ));
            estadoGeneral = em.find( EstadosGeneral.class, 1 );
            destinoPos = em.find( DestinoPOS.class, 1 );

            //query = em.createQuery( "SELECT u FROM UsuariosComercios u WHERE u.idContacto.idContacto =:idContacto" );
            //query.setParameter( "idContacto", idContacto );
            query = em.createQuery( "SELECT u FROM UsuariosComercios u WHERE u.token =:token" );
            query.setParameter( "token", token );
            usuariosComercio = ( UsuariosComercios ) query.getSingleResult();

            query = em.createNativeQuery( "SELECT CURRENT_TIMESTAMP" );
            String idSolicitud = ( String ) query.getSingleResult().toString();
            idSolicitud = idSolicitud.replace( ".", "" ).replace( ":", "" ).replace( "-", "" ).replace( " ", "" );
            idSolicitud = idSolicitud.substring( 7,16 );

            solicitudPos = new SolicitudPos( new SolicitudPosPK( idSolicitud, usuariosComercio.getIdUsuario() ), new Date(),
                Integer.parseInt( cantidadDatafonos ), horario, direccionEvento, ubicacionStand, nombreEvento,
                formatter.parse( fechaInicio ), formatter.parse( fechaRetiro ), destinoPos, estadoGeneral, tipoPos, usuariosComercio );
            boolean prueba= true;
            try {
                prueba = obtenerParametroPruebas(usuariosComercio.getIdContacto().getIdContacto(), usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getNitComercio(), usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getCodigoUnico());
                System.out.println("1. Continua "); 
            }
            catch(Exception exc){
                prueba = true;
                System.out.println("2. Continua ");
            }
            
            if( prueba ) {
                System.out.println("3. Continua "); 
                em.persist( solicitudPos );
                em.flush();
            }
            System.out.println("4. Continua "); 

            response = new Response( "true", "DATOS INGRESADOS", idSolicitud);

            relacionarAuditoria( idContacto, "Adición Solicitud POS", idSolicitud );

            asunto = "Solicitud POS para “Evento ó Feria”- comercio CredibanCo";

            mensaje = "Buen día, estamos enviando datos de comercio afiliado a CredibanCo que solicita POS para “Evento o Feria”: <br/>" + 
                "<br/>Número de referencia de la solicitud: "+ idSolicitud +
                "<br/>Código único: " + usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getCodigoUnico() +
                "<br/>NIT: " + usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getNitComercio() +
                "<br/>Nombre personacontacto: " + usuariosComercio.getIdContacto().getNombreContacto() + " " + usuariosComercio.getIdContacto().getApellidoContacto() +
                "<br/>Correo electrónico: " + usuariosComercio.getIdContacto().getMailContacto() +
                "<br/>Teléfono fijo: " + usuariosComercio.getIdContacto().getFijoContacto() +
                "<br/>Teléfono celular: " + usuariosComercio.getIdContacto().getMovilContacto() +
                "<br/>Razón social del comercio: "+ usuariosComercio.getIdContacto().getDatosComercio().getRazonSocial() +
                "<br/>Ciudad, Departamento: " + usuariosComercio.getIdContacto().getDatosComercio().getCiudad().getNombreCiudad() + ", " + usuariosComercio.getIdContacto().getDatosComercio().getCiudad().getDepartamento().getNombreDepartamento() +
                "<br/>Fecha de inicio evento: " + formatter.parse( fechaInicio ) +
                "<br/>fecha de retiro POS: " + formatter.parse( fechaRetiro ) + 
                "<br/>Nombre del evento: " + nombreEvento +
                "<br/>Dirección: "+ direccionEvento +
                "<br/>Ubicación stand: " + ubicacionStand +
                "<br/>Tipo de tecnología: "+ tipoPos.getTipoPos() + 
                "<br/>Cantidad de POS: " + cantidadDatafonos +
                "<br/>Horario de preferencia para instalación: " + horarioInstalacion +"<br/><br/>Agradecemos realizar la respectiva gesti&oacuten y dar respuesta oportuna al comercio.<br/><br/>CredibanCo App";

            if( prueba ) {
                enviarCorreoInternos( usuariosComercio.getIdContacto().getDatosComercio().getCiudad().getSeccional().getIdSeccional(), asunto, mensaje, "POS" );
            }
        } catch( Exception exc ) {
            response = new Response( "false", "DATOS NO INGRESADOS", "" );

            logger.error( " >>> *** Inicio Log Error adicionarSolicitudPos *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " idContacto: " + idContacto + " fechaInicio: " + fechaInicio + " fechaRetiro: " + fechaRetiro );
            logger.error( " nombreEvento: " + nombreEvento + " direccionEvento: " + direccionEvento + " ubicacionStand: " + ubicacionStand );
            logger.error( " cantidadDatafonos: " + cantidadDatafonos + " idTipoTecnologia: " + idTipoTecnologia + " horarioInstalacion: " + horarioInstalacion );
            logger.error( " >>> *** Fin Log Error adicionarSolicitudPos *** >>> " );
        }
        return response;
    }

    /**
     * @param idSeccional
     * @param asunto
     * @param mensaje
     * @param opcion
     * 
     * @description Consulta los destinatarios del correo por medio de la seccional y de la opción
     *               Luego realiza el envío del correo.
     */
    private void enviarCorreoInternos( int idSeccional, String asunto, String mensaje, String opcion ) {
        List<CorreosSolicitudes> listaInternos;
        String listaCorreo = "";
        String remitente = "credibancoapp@credibanco.com";
        Query query;

        try {
            logger.error( " >>> enviarCorreoInternos >>> opcion: " + opcion + " idSeccional: " + idSeccional );
            query = em.createQuery( "SELECT u FROM CorreosSolicitudes u WHERE u.opc ='" + opcion + "' AND u.idSeccional =" + idSeccional );
            listaInternos = query.getResultList();

            for ( CorreosSolicitudes correoSolicitud : listaInternos ) {
                listaCorreo += correoSolicitud.getCorreo() + ",";
            }
            sendEmail( listaCorreo, remitente, asunto, mensaje );

        } catch( Exception exc ) {
            logger.error( " >>> enviarCorreoInternos >>> " + exc.getMessage() );
            throw exc;
        }
    }

    /**
     * @return Response
     * @param idContacto
     * @throws Exception
     * 
     * @description Valida que el usuario haya aceptado los mas recientes terminos y condiciones.
     */
    @Override
    public Response validarTerminosCondiciones( String token ) throws Exception {
        Termsandconditions termsandconditions;
        UsuariosComercios usuariosComercio;
        Response respuesta;
        Query query;

        int versionTerminos;
        boolean estado;

        try {
            
            String llaves[] = obtenerLlaves();
            String ivKey = llaves[0];
            String saltKey = llaves[1];

            token = AES.decrypt( token.trim(), ivKey, saltKey );
            
            termsandconditions = findTermsandconditions();
            versionTerminos = termsandconditions.getTermsVersion();

            query = em.createQuery( "SELECT u FROM UsuariosComercios u WHERE u.token =:token" );
            query.setParameter( "token", token );
            usuariosComercio = ( UsuariosComercios ) query.getSingleResult();

            estado = ( versionTerminos == usuariosComercio.getVersionTerminos() );
            respuesta = new Response( estado == true ? "true" : "false", estado == true ? "Se encuentra actualizado" : "Debe actualizar Terminos", "" );
        } catch( Exception exc ) {
            logger.error( " >>> *** Inicio Log Error validarTerminosCondiciones *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " token: " + token );
            logger.error( " >>> *** Fin Log Error validarTerminosCondiciones *** >>> " );
            throw exc;
        }
        return respuesta;
    }

    /**
     * @param token
     * @return Response
     * @throws Exception
     * 
     * @description Actualiza la versión de aceptacion de Terminos y condiciones.
     */
    @Override
    public Response actualizarTerminosCondiciones( String token ) throws Exception {
        Termsandconditions termsandconditions;
        Response respuesta;
        Query query;

        try {

            String llaves[] = obtenerLlaves();
            String ivKey = llaves[0];
            String saltKey = llaves[1];

            token = AES.encrypt(token, ivKey, saltKey );

            termsandconditions = findTermsandconditions();
            int versionTerminos = termsandconditions.getTermsVersion();

            query = em.createQuery( "UPDATE UsuariosComercios u SET u.versionTerminos =:versionTerminos , u.fechaTerminos =:fechaTerminos WHERE u.token =:token" );
            query.setParameter( "versionTerminos", versionTerminos );
            query.setParameter( "fechaTerminos", new Date() );
            query.setParameter( "token", token );
            query.executeUpdate();

            respuesta = new Response( "true", "Se encuentra actualizado", "" );
        } catch( Exception exc ) {
            respuesta = new Response( "false", "No fue Posible Actualizar", "" );

            logger.error( " >>> *** Inicio Log Error actualizarTerminosCondiciones *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " token: " + token );
            logger.error( " >>> *** Fin Log Error actualizarTerminosCondiciones *** >>> " );
            throw exc;
        }
        return respuesta;
    }

    /**
     * @return List
     * @throws Exception
     * 
     * @description Obtiene el listado de los tipos de POS que se encuentren
     *              activos.
     */
    @Override
    public List<TipoPOS> getTiposPos() throws Exception {
        List<TipoPOS> listaTiposPos = null;
        Query query;

        try {
            query = em.createQuery( "SELECT u FROM TipoPOS u WHERE u.visible=true" );
            listaTiposPos = query.getResultList();
        } catch( Exception exc ){
            logger.error( " >>> getTiposPos >>> " + exc.getMessage() );
            throw exc;
        }
    	return  listaTiposPos;
    }

    /**
     * @param imagesPath
     * @return Response
     * @param parametros
     * @throws Exception
     * 
     * @description recibe los datos del técnico que se va a validar y devuelve
     * la imagen, pregunta y respuesta de validación.
     */
    @Override
    public Response getValidarTecnico( String parametros, String imagesPath ) throws Exception {
        Response respuesta = null;
        TecnicoVisita tecnicoVisita = null;
        List<RespuestasValTecnico> listaRespuestas;
        String idTipo = "", idTecnico = "";
        boolean cargarFoto = true;
        Query query;

        try {
            String llaves[] = obtenerLlaves();
            String ivKey = llaves[0];
            String saltKey = llaves[1];

            parametros = AES.decrypt( parametros, ivKey, saltKey );

            String listaParametros[] = parametros.split( "," );
            idTipo = listaParametros[0];
            idTecnico = listaParametros[1];
            query = em.createQuery( "SELECT u FROM TecnicoVisita u  where u.idTecnico =:idTecnico and u.tipoIdTecnico.idTipo =:idTipo and u.estadoTecnico.idEstado = 1 and borrado = false" );
            query.setParameter( "idTipo", Integer.parseInt( idTipo ));
            query.setParameter( "idTecnico", idTecnico );

            try{
                tecnicoVisita = ( TecnicoVisita ) query.getSingleResult();
            } catch( NoResultException exc ) {
                query = em.createQuery( "SELECT u.messageText FROM MensajesAplicacion u WHERE u.mensajesAplicacionPK.messageKey='Tech_Inactive'" );
                String mensaje = ( String ) query.getSingleResult();
                mensaje = mensaje.replace( ",", "%" );
                
                respuesta = new Response( "false", "No se encuentra el técnico", mensaje );
                logger.error( " >>> getValidarTecnico >>> No se encontro información del Técnico a Validar " + exc.getMessage() );
            }

            if( tecnicoVisita != null ) {
                query = em.createQuery( "SELECT u FROM RespuestasValTecnico u, PreguntasValTecnico p WHERE u.respIdTecnico =:idTecnico and u.idPregunta = p.idPregunta and p.visible = 1" );
                query.setParameter( "idTecnico", tecnicoVisita );
                listaRespuestas = query.getResultList();

                Random rnd = new Random();
                int idPregunta = ( int )( rnd.nextDouble()*10 );

                while( idPregunta > listaRespuestas.size() ) {
                     idPregunta = ( int )( rnd.nextDouble()*10 );
                }

                RespuestasValTecnico respuestaTec = listaRespuestas.get(idPregunta);

                query = em.createQuery( "SELECT u.textoPregunta FROM PreguntasValTecnico u WHERE u.idPregunta =:idPregunta" );
                query.setParameter( "idPregunta", respuestaTec.getIdPregunta().getIdPregunta());
                String textoPregunta = ( String ) query.getSingleResult();

                File archivo = FileUtils.getFile( imagesPath + "/" + tecnicoVisita.getIdTecnico() + tecnicoVisita.getFechaModificacion().toString().replace("-", "").replace(":","").replace(".","").replace(" ", "").trim() + ".png" );
                FileUtils.deleteQuietly( archivo );

                try {
                    FileUtils.writeByteArrayToFile( new File( imagesPath + "/" + tecnicoVisita.getIdTecnico() + tecnicoVisita.getFechaModificacion().toString().replace("-", "").replace(":","").replace(".","").replace(" ", "").trim() + ".png" ), tecnicoVisita.getFotoTecnico());
                } catch( Exception exc ) {
                    cargarFoto = false;
                    logger.error( " >>> getValidarTecnico >>> No se encontro Imagen realacionada " + exc.getMessage() );
                }

                query = em.createQuery( "SELECT u.messageText FROM MensajesAplicacion u WHERE u.mensajesAplicacionPK.messageKey='Tech_Active'" );
                String mensajeOk = ( String ) query.getSingleResult();
                mensajeOk = mensajeOk.replace(",", "%");

                query = em.createQuery( "SELECT u.messageText FROM MensajesAplicacion u WHERE u.mensajesAplicacionPK.messageKey='Tech_Inactive'" );
                String mensajeMal = ( String ) query.getSingleResult();
                mensajeMal = mensajeMal.replace(",", "%");

                String url = cargarFoto == true ? ( "/images/" + tecnicoVisita.getIdTecnico() + tecnicoVisita.getFechaModificacion().toString().replace("-", "").replace(":","").replace(".","").replace(" ", "").trim() + ".png" ) : "problema al cargar la foto";
                //respuesta = new Response( "true", "Tecnico encontrado", AES.encrypt( textoPregunta, ivKey, saltKey ) + "," + AES.encrypt( respuestaTec.getTextoRespuesta(), ivKey, saltKey ) + "," + tecnicoVisita.getNombreTecnico() + " " + tecnicoVisita.getApellidoTecnico() + "," + tecnicoVisita.getIdTecnico() + "," + tecnicoVisita.getTelefonoTecnico() + "," + url + "," + mensajeOk + "," + mensajeMal );
                respuesta = new Response( "true", "Tecnico encontrado", AES.encrypt(textoPregunta + "," + respuestaTec.getTextoRespuesta() + "," + tecnicoVisita.getNombreTecnico() + " " + tecnicoVisita.getApellidoTecnico() + "," + tecnicoVisita.getIdTecnico() + "," + tecnicoVisita.getTelefonoTecnico() + "," + url + "," + mensajeOk + "," + mensajeMal, ivKey, saltKey ) );
            }
        } catch( Exception exc ) {
            logger.error( " >>> *** Inicio Log Error getValidarTecnico *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " idTipo: " + idTipo + " idTecnico: " + idTecnico );
            logger.error( " >>> *** Fin Log Error getValidarTecnico *** >>> " );
            throw exc;
        }
        return respuesta;
    }

    /**
     * @return Response
     * @param parametros
     * @throws Exception
     * 
     * @description Recibe la información ingresada por el usuario para una nueva
     * solicitud MPOS, guarda el registro y envia un correo a los funcionarios
     * correspondientes.
     */
    @Override
    public Response adicionarSolicitudMiPago( String parametros ) {
        Response respuesta;
        UsuariosComercios usuariosComercio;
        EstadosGeneral estadoGeneral;
        String nombre = "", apellido = "", correo = "";
        //String tipoId = "", numeroId = "";
        String numCelular = "";
        String numFijo = "", razonSocial = "";
        String asunto, mensaje, token = "";
        Query query;

        try {
            String llaves[] = obtenerLlaves();
            String ivKey = llaves[0];
            String saltKey = llaves[1];

            parametros = AES.decrypt( parametros, ivKey, saltKey );
            String listaParametros[] = parametros.split( "," );
            nombre = listaParametros[0];
            apellido = listaParametros[1];
            correo = listaParametros[2];
            //tipoId = listaParametros[3];
            //numeroId = listaParametros[4];
            numCelular = listaParametros[3];
            numFijo = listaParametros[4];
            razonSocial = listaParametros[5];
            token = listaParametros[6];

            query = em.createQuery( "SELECT u FROM UsuariosComercios u WHERE u.token =:token" );
            query.setParameter( "token",  token );
            usuariosComercio = ( UsuariosComercios ) query.getSingleResult();

            usuariosComercio.getIdContacto().setNombreContacto( nombre );
            usuariosComercio.getIdContacto().setApellidoContacto( apellido );
            usuariosComercio.getIdContacto().setMailContacto( correo );
            usuariosComercio.getIdContacto().setFijoContacto( numFijo );
            usuariosComercio.getIdContacto().setMovilContacto( numCelular );
            usuariosComercio.getIdContacto().getDatosComercio().setRazonSocial( razonSocial );

            query = em.createNativeQuery( "SELECT CURRENT_TIMESTAMP" );
            String idSolicitud = ( String ) query.getSingleResult().toString();
            idSolicitud = idSolicitud.replace(".", "").replace(":", "").replace("-", "").replace(" ", "");
            idSolicitud = idSolicitud.substring(7,16);

            SolicitudMiPago solicitudMiPago = new SolicitudMiPago();
            SolicitudMiPagoPK solicitudMiPagoPK = new SolicitudMiPagoPK( idSolicitud, usuariosComercio.getIdUsuario() );
            solicitudMiPago.setFechaCreacion( new Date() );
            estadoGeneral = em.find( EstadosGeneral.class, 1 );
            solicitudMiPago.setIdEstado( estadoGeneral );
            solicitudMiPago.setSolicitudMiPagoPK( solicitudMiPagoPK );
            solicitudMiPago.setCantidadMiPago( 1 );

            em.merge( usuariosComercio );
            em.flush();
            
            boolean prueba= true;
            try {
                prueba = obtenerParametroPruebas(usuariosComercio.getIdContacto().getIdContacto(), usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getNitComercio(), usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getCodigoUnico());
                System.out.println("1. Continua "); 
            }
            catch(Exception exc){
                prueba = true;
                System.out.println("2. Continua ");
            }

            if( prueba ) {
                em.persist( solicitudMiPago );
                em.flush();
            }

            //Llama él metodo para el registro de auditoria
            relacionarAuditoria( usuariosComercio.getIdContacto().getIdContacto(), "Adición Solicitud MPOS", idSolicitud );
            respuesta = new Response( "true", "DATOS INGRESADOS", idSolicitud );

            asunto = "Solicitud de MiPago - comercio CredibanCo";
            mensaje = "Buen día, estamos enviando datos de comercio afiliado a CredibanCo que solicita MiPago adicional: <br/><br/>Número de referencia de la solicitud: "+ idSolicitud +
                "<br/>Código único: " + usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getCodigoUnico() +
                "<br/>NIT: " + usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getNitComercio() +
                "<br/>Nombre persona contacto: " + nombre + " " + apellido +
                "<br/>Correo electrónico: " + correo +
                "<br/>Teléfono fijo: " + numFijo +
                "<br/>Teléfono celular: " + numCelular +
                "<br/>Razón Social del comercio: " + razonSocial +
                "<br/> Ciudad, Departamento: " + usuariosComercio.getIdContacto().getDatosComercio().getCiudad().getNombreCiudad() + ", " + usuariosComercio.getIdContacto().getDatosComercio().getCiudad().getDepartamento().getNombreDepartamento() +
                "<br/><br/>Agradecemos realizar la respectiva gestión e ingresar a la Portal Web administrador del CredibanCo App para dar respuesta oportuna al comercio.<br/><br/>CredibanCo App";

            if( prueba ) {
                enviarCorreoInternos( usuariosComercio.getIdContacto().getDatosComercio().getCiudad().getSeccional().getIdSeccional(), asunto, mensaje, "MP" );
            }

        } catch( Exception exc ) {
            respuesta = new Response( "false", "DATOS NO INGRESADOS", "" );
            logger.error( " >>> *** Inicio Log Error adicionarSolicitudMiPago *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " nombre: " + nombre + " apellido: " + apellido + " correo: " + correo );
            logger.error( " token: " + token + "  numCelular: " + numCelular );
            logger.error( " numFijo: " + numFijo + " razonSocial: " + razonSocial );
            logger.error( " >>> *** Fin Log Error adicionarSolicitudMiPago *** >>> " );
        }
        return respuesta;
    }

    /**
     * @return List
     * @throws Exception
     * 
     * @description devuelve el listado de las categorias de capacitacion 
     * que se encuentren activas.
     */
    @Override
    public List<CategoriasCapacitacion> getTemasCapacitacion() throws Exception {
        List<CategoriasCapacitacion> listaTemasCapacitacion = null;
        Query query;

        try {
            query = em.createQuery( "SELECT u FROM CategoriasCapacitacion u WHERE u.visible=true" );
            listaTemasCapacitacion = query.getResultList();
        }catch( Exception exc ) {
            logger.error( " >>> getTemasCapacitacion >>> " + exc.getMessage() );
            throw exc;
        }
        return listaTemasCapacitacion;
    }

    /**
     * @return List
     * @param idCategoria
     * @param path
     * @throws Exception
     * 
     * @description Recibe la categoria seleccionada por el usuario y devuelve
     * las capacitaciones asociadas a la categoria, actualiza la cantidad de visitas
     * a la categoria escogida.
     */
    @Override
    public List<DatosCapacitacion> getCapacitacionComercios( String idCategoria, String path ) throws Exception {
        List<CapacitacionComercios> listaCapacitacionComercios;
        List<DatosCapacitacion> listaDatosCapacitacion = new ArrayList<>();
        DatosCapacitacion datosCapacitacion;
        Query query;

        try {
            query = em.createQuery( "SELECT u FROM CapacitacionComercios u WHERE u.visible=true AND u.categoriaCap.idCategoria =:idCategoria" );
            query.setParameter( "idCategoria", Integer.parseInt( idCategoria ));
            listaCapacitacionComercios = query.getResultList();

            for ( CapacitacionComercios listaCapacitacionComercio : listaCapacitacionComercios ) {
                String elementoPath;
                String titulo;

                if( listaCapacitacionComercio.getEnlaceCap() != null && !listaCapacitacionComercio.getEnlaceCap().equals("")  ) {
                    elementoPath = listaCapacitacionComercio.getEnlaceCap();
                }
                else {
                    titulo = listaCapacitacionComercio.getTituloCap().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u")
                        .replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U")
                        .replace("ñ", "n").replace("Ñ", "N").replace("¿", "").replace("?", "").replace("!", "")
                        .replace(":", "").replace("%", "").replace("$", "").replace("(", "").replace(")", "");

                    File archivo = FileUtils.getFile( path+"/" + titulo + ".pdf");
                    FileUtils.deleteQuietly( archivo );

                    FileUtils.writeByteArrayToFile( new File( path+"/" + titulo + ".pdf" ), listaCapacitacionComercio.getContenidoCap());
                    elementoPath = "/archivos/" + titulo + ".pdf";
                }
                datosCapacitacion = new DatosCapacitacion( listaCapacitacionComercio.getIdCapacitacion().toString(), listaCapacitacionComercio.getTituloCap(), listaCapacitacionComercio.getDescripcionCap(), elementoPath );
                listaDatosCapacitacion.add( datosCapacitacion );
            }

            CategoriasCapacitacion categoriaCapacitacion = ( CategoriasCapacitacion ) em.find( CategoriasCapacitacion.class, Integer.parseInt( idCategoria ));
            query = em.createQuery( "UPDATE CategoriasCapacitacion u SET u.visitas = (SELECT u.visitas FROM CategoriasCapacitacion u WHERE u.idCategoria=:idCategoria)+1 WHERE u.idCategoria =:idCategoria" );
            query.setParameter( "idCategoria", Integer.parseInt( idCategoria ));
            query.executeUpdate();
        } catch( Exception exc ) {
            logger.error( " >>> *** Inicio Log Error getCapacitacionComercios *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " idCategoria: " + idCategoria );
            logger.error( " >>> *** Fin Log Error getCapacitacionComercios *** >>> " );
            throw exc;
        }
    	return listaDatosCapacitacion;
    }

    /**
     * @return Response
     * @param parametros
     * @throws Exception
     * 
     * @description Recibe la información ingresada por el usuario para una nueva
     * solicitud POS, guarda el registro y envia un correo a los funcionarios
     * correspondientes.
     * 
     */
    @Override
    public Response adicionarSolicitudDatafono( String parametros ) {
        UsuariosComercios usuariosComercio = null;
        EstadosGeneral estadoGeneral;
        SolicitudPos solicitudPos;
        DestinoPOS destinoPos;
        TipoPOS tipoPos;
        Response response;

        String horarioInstalacion = "", cantidadDatafonos = "";
        String idTipoTecnologia = "", token= "";
        //String idContacto= "";
        boolean horario = true;
        Query query;

        try {
            String llaves[] = obtenerLlaves();
            String ivKey = llaves[0];
            String saltKey = llaves[1];
            
            parametros = AES.decrypt( parametros, ivKey, saltKey );
            String listaParametros[] = parametros.split( "," );
            cantidadDatafonos = listaParametros[0];
            idTipoTecnologia = listaParametros[1];
            horarioInstalacion = listaParametros[2];
            //idContacto = listaParametros[3];
            token = listaParametros[3];

            if(!horarioInstalacion.equals( "am" ))
                horario = false;

            tipoPos = em.find( TipoPOS.class, Integer.parseInt( idTipoTecnologia ));
            estadoGeneral = em.find( EstadosGeneral.class, 1 );
            destinoPos = em.find( DestinoPOS.class, 1 );
            query = em.createQuery( "SELECT u FROM UsuariosComercios u WHERE u.token =:token" );
            query.setParameter( "token", token );
            usuariosComercio = ( UsuariosComercios ) query.getSingleResult();

            query = em.createNativeQuery( "SELECT CURRENT_TIMESTAMP" );
            String idSolicitud = ( String ) query.getSingleResult().toString();
            idSolicitud = idSolicitud.replace(".", "").replace(":", "").replace("-", "").replace(" ", "");
            idSolicitud = idSolicitud.substring(7,16);

            solicitudPos = new SolicitudPos(new SolicitudPosPK( idSolicitud, usuariosComercio.getIdUsuario() ), new Date(), Integer.parseInt( cantidadDatafonos ), horario, destinoPos, estadoGeneral, tipoPos);

            boolean prueba= true;
            try {
                prueba = obtenerParametroPruebas(usuariosComercio.getIdContacto().getIdContacto(), usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getNitComercio(), usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getCodigoUnico());
                System.out.println("1. Continua "); 
            }
            catch(Exception exc) {
                prueba = true;
                System.out.println("2. Continua ");
            }
            
            if( prueba ) {
                em.persist( solicitudPos );
                em.flush();
            }

            response = new Response( "true", "DATOS INGRESADOS", idSolicitud );
            relacionarAuditoria( usuariosComercio.getIdContacto().getIdContacto(), "Adición Solicitud Datafono", idSolicitud );

            String asunto = "Solicitud POS - comercio CredibanCo";

            String mensaje = "Buen día, estamos enviando datos de comercio afiliado a CredibanCo que solicita POS adicional: <br/>" + 
                "<br/>Número de referencia de la solicitud: "+ idSolicitud +
                "<br/>Código único: " + usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getCodigoUnico() +
                "<br/>NIT: " + usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getNitComercio() +
                "<br/>Nombre personacontacto: " + usuariosComercio.getIdContacto().getNombreContacto() + " " + usuariosComercio.getIdContacto().getApellidoContacto() +
                "<br/>Correo electrónico: " + usuariosComercio.getIdContacto().getMailContacto() +
                "<br/>Teléfono fijo: " + usuariosComercio.getIdContacto().getFijoContacto() +
                "<br/>Teléfono celular: " + usuariosComercio.getIdContacto().getMovilContacto() +
                "<br/>Razón social del comercio: "+ usuariosComercio.getIdContacto().getDatosComercio().getRazonSocial() +
                "<br/>Ciudad, Departamento: " + usuariosComercio.getIdContacto().getDatosComercio().getCiudad().getNombreCiudad() + ", " + usuariosComercio.getIdContacto().getDatosComercio().getCiudad().getDepartamento().getNombreDepartamento() +
                "<br/>Tipo de tecnología: "+ tipoPos.getTipoPos() + 
                "<br/>Cantidad de POS: " + cantidadDatafonos +
                "<br/>Horario de preferencia para instalación: " + horarioInstalacion +
                "<br/><br/>Agradecemos realizar la respectiva gesti&oacuten e ingresar al Portal Web administrador del CredibanCo App para dar respuesta oportuna al comercio.<br/><br/>CredibanCo App";

            if( prueba ) {
                enviarCorreoInternos( usuariosComercio.getIdContacto().getDatosComercio().getCiudad().getSeccional().getIdSeccional(), asunto, mensaje, "POS" );
            }
        } catch( Exception exc ) {
            response = new Response( "false", "DATOS NO INGRESADOS", "" );
            logger.error( " >>> *** Inicio Log Error adicionarSolicitudDatafono *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " idContacto: " + (usuariosComercio != null ? usuariosComercio.getIdContacto().getIdContacto() : "") + " Horario Instalacion: " + horarioInstalacion );
            logger.error( " idTipoTecnologia: " + idTipoTecnologia + " cantidadDatafonos: " + cantidadDatafonos );
            logger.error( " >>> *** Fin Log Error adicionarSolicitudDatafono *** >>> " );
            throw exc;
        }
        return response;
    }

    /**
     * @return List
     * @param idContacto
     * @throws Exception
     * 
     * @description recibe el token generado al iniciar sesion y busca la información relacionada
     * para cargarla en la pantalla de "Solicitud Mi Pago (MPOS)".
     */
    @Override
    public List<MiPago>getDatosSolicitudMiPago( String parametros ) throws Exception{
        List<MiPago> listaMiPago = new ArrayList<>();
        UsuariosComercios usuariosComercio = null;
        ContactoComercio contactoComercio;
        MiPago miPago;
        Query query;

        try {
            String llaves[] = obtenerLlaves();
            String ivKey = llaves[0];
            String saltKey = llaves[1];
            
            query = em.createQuery( "SELECT u FROM UsuariosComercios u WHERE u.token =:token" );
            query.setParameter( "token", parametros );
            usuariosComercio = ( UsuariosComercios ) query.getSingleResult();
            contactoComercio = usuariosComercio.getIdContacto();

            miPago = new MiPago( AES.encrypt(contactoComercio.getNombreContacto(), ivKey, saltKey ), AES.encrypt(contactoComercio.getApellidoContacto(), ivKey, saltKey ), AES.encrypt(contactoComercio.getMailContacto(), ivKey, saltKey ),
                    contactoComercio.getTipoIdContacto(), AES.encrypt(usuariosComercio.getIdContacto().getIdContacto(), ivKey, saltKey ), AES.encrypt(contactoComercio.getMovilContacto(), ivKey, saltKey ), AES.encrypt(contactoComercio.getFijoContacto(), ivKey, saltKey ), AES.encrypt(contactoComercio.getDatosComercio().getRazonSocial(), ivKey, saltKey ));

            listaMiPago.add( miPago );
        } catch( Exception exc ) {
            logger.error( " >>> *** Inicio Log Error getDatosSolicitudMiPago *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " idContacto: " + (usuariosComercio !=null ? usuariosComercio.getIdContacto().getIdContacto() : "" ));
            logger.error( " >>> *** Fin Log Error getDatosSolicitudMiPago *** >>> " );
            throw exc;
        }
        return listaMiPago;
    }

    /**
     * @return List
     * @throws Exception
     * 
     * @description Devuelve una lista con la información de las oficinas para
     * la opción de Contactenos.
     */
    @Override
    public List<Contactenos> getCanalesOficinas() throws Exception {
        List<Contactenos> listaContactenos = new ArrayList<>();
        List<CanalesOficinas> listaCanalesOficinas;
        List<OficinasContactos> listaOficinas;
        List<String> listaCiudades;
        Query query;

        try {
            query = em.createQuery( "SELECT u FROM CanalesOficinas u WHERE u.oficinasSeccionales.visible = true ORDER BY u.oficinasSeccionales.ciudadOficina.nombreCiudad" );
            listaCanalesOficinas = query.getResultList();

            query = em.createQuery( "SELECT DISTINCT (u.oficinasSeccionales.ciudadOficina.nombreCiudad) FROM CanalesOficinas u WHERE u.oficinasSeccionales.visible = true ORDER BY u.oficinasSeccionales.ciudadOficina.nombreCiudad" );
            listaCiudades = query.getResultList();

            for ( String listaCiudade : listaCiudades ) {
                Contactenos contactenos = new Contactenos();
                contactenos.setNombreCiudad( listaCiudade );
                listaOficinas = new ArrayList<>();

                for ( CanalesOficinas listaCanalesOficina : listaCanalesOficinas ) {
                    if( listaCiudade.equals( listaCanalesOficina.getOficinasSeccionales().getCiudadOficina().getNombreCiudad() )) {
                        OficinasContactos oficina = new OficinasContactos( listaCanalesOficina.getNombreOficina(), listaCanalesOficina.getTelefonoInformacion(), listaCanalesOficina.getTelefonoPOS(), listaCanalesOficina.getOficinasSeccionales().getDireccionOficina());
                        listaOficinas.add( oficina );
                    }
                }
                contactenos.setListaOficinas( listaOficinas );
                listaContactenos.add( contactenos );
            }
        } catch( Exception exc ) {
            logger.error( " >>> getCanalesOficinas >>> " + exc.getMessage() );
            throw exc;
        }
    	return listaContactenos;
    }

    /**
     * @param parametros
     * @return Response
     * @throws Exception
     * 
     * @description obtiene la información de la falla y una vez obtenido
     *              el idSolicitud del webservices lo guarda en la bd.
     */
    @Override
    public Response adicionarFallas( String parametros ) {
        Response respuesta;
        ReporteFallas reporteFallas;
        EstadosGeneral estadoGeneral;
        UsuariosComercios usuariosComercio = null;
        ReporteFallasPK reporteFallasPK;
        Query query;
        AS400DaoSis aS400DaoSis = new AS400DaoSis();

        String token, idSolicitud = "", idTipoPos;
        String tipoError, descripcionFalla;

        try {
            
            String llaves[] = obtenerLlaves();
            String ivKey = llaves[0];
            String saltKey = llaves[1];

            parametros = AES.decrypt(parametros, ivKey, saltKey );
            
            String listaParametros[] = parametros.split( "," );
            String idContacto = "";

            tipoError = listaParametros[0];
            idTipoPos = listaParametros[1];
            descripcionFalla = listaParametros[2];
            descripcionFalla = descripcionFalla.replaceAll("%44", ",");
            token = listaParametros[3];

            TipificacionSM tipificacion = em.find( TipificacionSM.class, Integer.parseInt( tipoError ));
            TipoPOS tipoPos = em.find(TipoPOS.class, Integer.parseInt( idTipoPos ));
            
            try {
                query = em.createQuery( "SELECT u FROM UsuariosComercios u WHERE u.token =:token" );
                query.setParameter( "token", token );
                usuariosComercio = ( UsuariosComercios ) query.getSingleResult();
            } catch (Exception e) {
                logger.error( " >>> *** Inicio Log Error adicionarFallas *** >>> " );
                logger.error( " >>> No encuentra UsuarioComercio con idContacto >>> " + idContacto );
                e.printStackTrace();
                respuesta = new Response("False", "NO SE GENERO SD", "");
            }

            if( !aS400DaoSis.validaComercioCorresponsal(usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getCodigoUnico()))
            {
                if( validarCampoSinNumeroTarjeta( descripcionFalla )) {
                    if( !validarReporteFallasClienteDia( usuariosComercio.getIdContacto().getIdContacto(), tipificacion.getIdTipificacion().toString() )) {
                        reporteFallas = new ReporteFallas();
                        reporteFallas.setTipificacion( tipificacion );
                        reporteFallas.setDescripcion( descripcionFalla );
                        estadoGeneral = em.find( EstadosGeneral.class, 4 );
                        reporteFallas.setIdEstado( estadoGeneral );

                        reporteFallas.setUsuariosComercios( usuariosComercio );
                        reporteFallas.setFechaCreacion( new Date() );
                        reporteFallas.setTipoTecnologia( tipoPos );

                        boolean prueba= true;
                        try {
                            prueba = obtenerParametroPruebas(usuariosComercio.getIdContacto().getIdContacto(), usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getNitComercio(), usuariosComercio.getIdContacto().getDatosComercio().getDatosComercioPK().getCodigoUnico());
                            System.out.println("1. Continua "); 
                        }
                        catch(Exception exc) {
                            prueba = true;
                            System.out.println("2. Continua ");
                        }
                        
                        if( prueba )
                            idSolicitud = consumirWebService( tipoPos, descripcionFalla, usuariosComercio, tipificacion );
                        else {
                            query = em.createNativeQuery( "SELECT CURRENT_TIMESTAMP" );
                            idSolicitud = ( String ) query.getSingleResult().toString();
                            idSolicitud = idSolicitud.replace(".", "").replace(":", "").replace("-", "").replace(" ", "");
                            idSolicitud = idSolicitud.substring(7,16);
                        }

                        if( !idSolicitud.equals( "" )) {
                            if( prueba )
                            {
                                reporteFallasPK = new ReporteFallasPK( idSolicitud, usuariosComercio.getIdUsuario() );
                                reporteFallas.setReporteFallasPK( reporteFallasPK );
                                em.persist( reporteFallas );
                            }

                            respuesta = new Response( "true", "REPORTE DE FALLA ADICIONADO", AES.encrypt( idSolicitud, ivKey, saltKey ));
                            relacionarAuditoria( idContacto, "Envio fallas", idSolicitud );
                        } else
                            respuesta = new Response( "false", "NO SE GENERO SD", "" );
                    } else
                        respuesta = new Response( "true", "YA SE INGRESARON DOS FALLAS PARA ESTA TIPIFICACION EL DIA DE HOY", "" );
                } else {
                    query = em.createQuery( "SELECT u FROM MensajesAplicacion u WHERE u.mensajesAplicacionPK.messageKey ='validarNumeroTarjeta'" );
                    MensajesAplicacion mensajesAplicacion = ( MensajesAplicacion ) query.getSingleResult();
                    respuesta = new Response( "false", mensajesAplicacion.getMessageText(), "" );
                }
            }
            else {
                respuesta = new Response( "false", "Tu comercio está registrado como corresponsal bancario y para ti hay un esquema de atención especial. \n  Para mayor información comunícate con nosotros", "" );
            }
                
        } catch( Exception exc ) {
            respuesta = new Response( "false", "REPORTE DE FALLA NO SE PUDO COMPLETAR", "" );
            logger.error( " >>> *** Datos adicionar fallas *** >>> " );
            logger.error( " >>> parametros >>> " + parametros );
            exc.printStackTrace();
            logger.error( " >>> *** Fin Log Error adicionarFallas *** >>> " );
        }
        return respuesta;
    }

   /**
     * @return boolean
     * @param password
     * 
     * @description Obtiene el password ingresado por el usuario y valida
     *              que cunpla con el patron minimo de seguridad.
     */
    private boolean validatePassword( String password ) {
        Pattern pattern = Pattern.compile( PATTERN_PASSWORD );
        Matcher matcher = pattern.matcher( password );
        return matcher.matches();
    }

    /**
     * @return Response
     * @param imagesPath
     * @throws Exception
     * 
     * @description Obtiene la imagen de la publicidad para la pantalla de inicio,
     *              la deja en el servidor para que pueda ser tomada para cargarla
     *              y devuelve la ruta del servidor en la que queda
     */
    @Override
    public Response getPantallaTemporal( String imagesPath ) throws Exception {
        ParametrosGlobales parametrosGlobales;
        Response response;
        Query query;

        try {
            query = em.createQuery( "SELECT u FROM ParametrosGlobales u WHERE u.parameterKey = 'initImg'" );
            parametrosGlobales = ( ParametrosGlobales ) query.getSingleResult();

            File archivo = FileUtils.getFile( imagesPath + "/" + parametrosGlobales.getParameterKey() + parametrosGlobales.getParameterModificationDate().toString().replace("-", "").replace(":","").replace(".","").replace(" ", "").trim() + ".png" );
            FileUtils.deleteQuietly( archivo );

            FileUtils.writeByteArrayToFile( new File( imagesPath + "/" + parametrosGlobales.getParameterKey() + parametrosGlobales.getParameterModificationDate().toString().replace("-", "").replace(":","").replace(".","").replace(" ", "").trim() + ".png" ), parametrosGlobales.getParameterValueTwo() );
            response = new Response( "true", "Imagen publicidad", "images/" + parametrosGlobales.getParameterKey() + parametrosGlobales.getParameterModificationDate().toString().replace("-", "").replace(":","").replace(".","").replace(" ", "").trim() + ".png" );
        } catch( Exception exc ) {
            response = new Response( "false", "No hay Imagen publicidad", "" );
            logger.error( " >>> getPantallaTemporal >>> " + exc.getMessage() );
        }
        return response;
    }

    /**
     * @param imagesPath
     * @return Response
     * @throws Exception
     * 
     * @description Se encarga de conseguir la información de los banners
     */
    @Override
    public Response getListadoBanner( String imagesPath ) throws Exception {
        List<ParametrosGlobales> listaParametrosGlobales;
        Response response;
        String imagenesBanner = "";
        String urlImagen = "";
        int contador = 0;
        Query query;

        try {
            query = em.createQuery( "SELECT u FROM ParametrosGlobales u WHERE u.parameterKey IN ('bannerOne','bannerTwo','bannerThr')" );
            listaParametrosGlobales = query.getResultList();

            for ( ParametrosGlobales parametroGlobal : listaParametrosGlobales ) {
                boolean adicionar = true; 

                try {
                    byte[] intento = parametroGlobal.getParameterValueTwo();
                } catch( Exception exc) {
                    adicionar = false;
                    logger.error( " >>> getListadoBanner >>> No hay imagen relacionada " + exc.getMessage() );
                }

                if( adicionar ) {
                    File archivo = FileUtils.getFile( imagesPath + "/" + parametroGlobal.getParameterKey() + parametroGlobal.getParameterModificationDate().toString().replace("-", "").replace(":","").replace(".","").replace(" ", "").trim() + ".png" );
                    FileUtils.deleteQuietly( archivo );

                    FileUtils.writeByteArrayToFile( new File( imagesPath + "/" + parametroGlobal.getParameterKey() + parametroGlobal.getParameterModificationDate().toString().replace("-", "").replace(":","").replace(".","").replace(" ", "").trim() + ".png" ), ( parametroGlobal.getParameterValueTwo() ));
                    imagenesBanner += "images/" + parametroGlobal.getParameterKey() + parametroGlobal.getParameterModificationDate().toString().replace("-", "").replace(":","").replace(".","").replace(" ", "").trim() + ".png" + ( contador < listaParametrosGlobales.size() ? "," : "" );
                    urlImagen += parametroGlobal.getParameterValue() + ( contador < listaParametrosGlobales.size() ? "," : "" );
                }
                contador++;
            }
            response = new Response( "true", urlImagen, imagenesBanner );
        } catch( Exception exc ) {
            response = new Response( "false", "Error cargando los banners", "" );
            logger.error( " >>> getListadoBanner >>> " + exc.getMessage() );
        }
        return response;
    }

    /**
     * @param String
     * @param String
     * @throws Exception
     * 
     * @description Guarda en la BD información de Auditoria
     *              identificación, fecha, accion, ip
     */
    private void relacionarAuditoria( String numeroIdentificacion, String accionLog, String itemAccion ) {
        try {
            LogUsuariosComercios auditoria = new LogUsuariosComercios( accionLog, InetAddress.getLocalHost().getHostAddress(), numeroIdentificacion, Calendar.getInstance().getTime(), itemAccion );
            em.persist( auditoria );
        } catch( Exception exc ) {
            logger.error( " >>> relacionarAuditoria >>> " + exc.getMessage() );
        }
    }

    /**
     * @return List
     * @throws Exception
     * 
     * @description devuelve el listado de los tipos de fallas
     */
    @Override
    public List<TipificacionDao> getTipoFallas() throws Exception {
        List<TipificacionDao> listaTipificacionDao = new ArrayList<>();
        List<TipificacionSM> listaTipificacionSM;
        Query query;

        try {
            query = em.createQuery( "SELECT u FROM TipificacionSM u ORDER BY u.tipificacionNombre" );
            listaTipificacionSM = query.getResultList();

            for ( TipificacionSM tipificacionSM : listaTipificacionSM ) {
                TipificacionDao tipificacionDao = new TipificacionDao( tipificacionSM.getIdTipificacion().toString(),tipificacionSM.getTipificacionNombre() );
                listaTipificacionDao.add( tipificacionDao );
            }
        } catch( Exception exc ) {
            logger.error( " >>> getTipoFallas >>> " + exc.getMessage() );
            throw exc;
        }
    	return  listaTipificacionDao;
    }

    /**
    * @param token
    * @return List
    * @throws Exception
    * 
    * @description Trae el listado de los mensajes asociados a un idContacto
    *              validando por medio del correo del destinatario.
    */
    @Override
    public List<MensajePushDao> getListadoMensajesPush( String token ) throws Exception {
        List<MensajePushDao> listaMensajesPushTemp;
        List<MensajesPush> listaMensajesPush;
        UsuariosComercios usuariosComercio;
        Query query;

        try {
            query = em.createQuery( "SELECT u FROM UsuariosComercios u WHERE u.token =:token" );
            query.setParameter( "token", token );
            usuariosComercio = ( UsuariosComercios ) query.getSingleResult();

            query = em.createQuery( "SELECT u FROM MensajesPush u WHERE u.destinatarios LIKE '%;" + usuariosComercio.getIdContacto().getIdContacto() + "%;'" );
            listaMensajesPush = query.getResultList();

            listaMensajesPushTemp = new ArrayList<>();
            String fecha;

            for ( MensajesPush mensajesPush : listaMensajesPush ) {
                Calendar start = Calendar.getInstance();
                start.setTimeInMillis( mensajesPush.getFechaEnvio().getTime() );

                fecha = start.get(Calendar.DAY_OF_MONTH) + "/" + start.get(Calendar.MONTH ) + "/" + start.get(Calendar.YEAR ) + " " + start.get( Calendar.HOUR ) + ":" + start.get( Calendar.MINUTE ) + ":" + start.get( Calendar.SECOND );

                MensajePushDao mensajePushDao = new MensajePushDao( mensajesPush.getIdMensaje(), mensajesPush.getMessageTitle(),
                        mensajesPush.getMessageText(), mensajesPush.getRemitente(), usuariosComercio.getIdContacto().getMailContacto(),
                        mensajesPush.getDestinoCiudad(), mensajesPush.getDestinoMCC(), fecha, mensajesPush.getLeido() );

                listaMensajesPushTemp.add( mensajePushDao );
            }
        } catch( Exception exc ) {
            logger.error( " >>> *** Inicio Log Error getListadoMensajesPush *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " idContacto: " + token );
            logger.error( " >>> *** Fin Log Error getListadoMensajesPush *** >>> " );
            throw exc;
        }
    	return listaMensajesPushTemp;
    }

    /**
     * @param idMensaje
     * @return Response
     * @throws Exception
     * 
     * @description Una vez seleccionado el mensaje lo marca como leido (true)
     */
    @Override
    public Response marcarMensajeLeido( String idMensaje ) throws Exception{
        Response respuesta;
        Query query;

        try {
            query = em.createQuery( "UPDATE MensajesPush u SET u.leido = 'true' WHERE u.idMensaje =:idMensaje" );
            query.setParameter( "idMensaje", Integer.parseInt( idMensaje ));
            query.executeUpdate();
            
            respuesta = new Response("true", "MENSAJE MARCADO COMO LEIDO", "");
        } catch( Exception exc ) {
            respuesta = new Response("false", "MENSAJE NO SE PUDO MARCAR COMO LEIDO", "");
            logger.error( " >>> getListadoMensajesPush >>> idMensaje: " + idMensaje + " >>> " + exc.getMessage() );
        }
        return respuesta;
    }

    /**
     * @param idCapacitacion
     * @throws Exception
     * 
     * @description Actualiza el valor de visitas realizadas a una capacitacion
     *              una vez fue seleccionada por el usuario.
     */
    @Override
    public void actualizarCapacitacionComercios( String idCapacitacion ) throws Exception {
        Query query;

        try {
            query = em.createQuery( "UPDATE CapacitacionComercios u SET u.visitas =(SELECT u.visitas FROM CapacitacionComercios u WHERE u.idCapacitacion =:idCapacitacion)+1 WHERE u.idCapacitacion =:idCapacitacion" );
            query.setParameter( "idCapacitacion", Integer.parseInt( idCapacitacion ));
            query.executeUpdate();
        } catch(Exception exc) {
            logger.error( " >>> *** Inicio Log Error actualizarCapacitacionComercios *** >>> " );
            logger.error( " Mensaje Error: " + exc.getMessage() );
            logger.error( " idCapacitacion: " + idCapacitacion );
            logger.error( " >>> *** Fin Log Error actualizarCapacitacionComercios *** >>> " );
            throw exc;
        }
    }

    /**
     * @param tipoPOS
     * @param descripcionFalla
     * @param usuariosComercios
     * @param tipificacion
     * @return String
     */
    private String consumirWebService( TipoPOS tipoPOS, String descripcionFalla, UsuariosComercios usuariosComercios, TipificacionSM tipificacion ) {
        String codigoUnico = "", tipoCliente = "", tipoSolicitud = "";
        String producto = "", clasificacionTipificacion = "", nombreTipificacion = "";
        String direccion = "", nombreContacto = "", correoContacto = "";
        String telefonoContacto = "", tipoTecnologia = "", idSolicitud = "";

        try {
            /*Listado de los campos que se describen en el diseño tecnico que son necesarios pasar*/
            codigoUnico = usuariosComercios.getIdContacto().getDatosComercio().getDatosComercioPK().getCodigoUnico();
            tipoCliente = tipificacion.getTipoCliente();
            tipoSolicitud = tipificacion.getTipoSolicitud();
            producto = tipificacion.getProducto();
            clasificacionTipificacion = tipificacion.getClasificacion();
            nombreTipificacion = tipificacion.getTipificacion();
            direccion = usuariosComercios.getIdContacto().getDatosComercio().getDireccion();
            nombreContacto = usuariosComercios.getIdContacto().getNombreContacto();
            correoContacto = usuariosComercios.getIdContacto().getMailContacto();
            telefonoContacto = usuariosComercios.getIdContacto().getFijoContacto();
            tipoTecnologia = tipoPOS.getTipoPos();

            Crcappmovil_BindingStub binding;
            try {
                binding = (Crcappmovil_BindingStub) new Crcappmovil_ServiceLocator().getcrcappmovil();
            } catch (javax.xml.rpc.ServiceException jre) {
                if (jre.getLinkedCause() != null) {
                    jre.getLinkedCause().printStackTrace();
                }
                throw jre;
            }

            binding.setTimeout(60000);
            CreateappmovilResponse value = null;

            AppmovilModelType appmovilModelType = new AppmovilModelType();
            AppmovilInstanceType appmovilInstanceType = new AppmovilInstanceType();

            appmovilInstanceType.setClasificacion( new StringType( clasificacionTipificacion ));
            appmovilInstanceType.setNombre( new StringType( nombreContacto ));
            appmovilInstanceType.setTipocliente( new StringType( tipoCliente ));
            appmovilInstanceType.setProducto( new StringType( producto ));
            appmovilInstanceType.setTipificacion( new StringType( nombreTipificacion ));
            appmovilInstanceType.setCodigounico( new StringType( codigoUnico ));
            appmovilInstanceType.setTiposolicitud( new StringType( tipoSolicitud ));
            appmovilInstanceType.setDireccion( new StringType( direccion ));
            appmovilInstanceType.setCorreo( new StringType( correoContacto ));
            appmovilInstanceType.setTelefono( new StringType( telefonoContacto ));
            appmovilInstanceType.setTipotec( new StringType( tipoTecnologia ));
            AppmovilInstanceTypeDescripcion description = new AppmovilInstanceTypeDescripcion();
            StringType[] descriptions = new StringType[1];
            descriptions[0] = new StringType( descripcionFalla );
            description.setDescripcion( descriptions );
            appmovilInstanceType.setDescripcion( description );

            appmovilModelType.setInstance(appmovilInstanceType);

            CreateappmovilRequest request = new CreateappmovilRequest();
            request.setModel(appmovilModelType);

            AppmovilKeysType appmovilKeysType = new AppmovilKeysType();
            appmovilKeysType.setQuery("");
            appmovilModelType.setKeys(appmovilKeysType);

            binding.setUsername("integracionapp");
            binding.setPassword("C0L0mB141");

            value = binding.createappmovil(request);

            System.out.println("Servicio " + value.getMessage());
            

            if( value.getMessage().equals("Exito") )
                idSolicitud = value.getModel().getKeys().getIncidentId().get_value();
            else
                idSolicitud = "";
            System.out.println("idSolicitud " + value.getModel().getKeys().getIncidentId().get_value());

        } catch( Exception exc ) {
            logger.error( " >>> *** Error consumirWebService *** >>> " );
            exc.printStackTrace();
            logger.error( " >>> *** Fin Log Error consumirWebService *** >>> " );
            
            //throw exc;
        }
        return idSolicitud;
    }

    /**
     * @param idContacto
     * @param idTipificacion
     * @return boolean
     * 
     * @description Valida para la fecha cuantos reportes de fallas a 
     *              realizado el cliente si son más de dos retorna true
     *              de lo contrario retorna false
     */
    @Override
    public boolean validarReporteFallasClienteDia( String token, String idTipificacion ) {
        SimpleDateFormat formatter = new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss" );
        List<ReporteFallas> listaFallas;
        boolean respuesta = false;
        String fechaInicio, fechaFinal;
        Query query;

        Calendar fechaCalendar = Calendar.getInstance();
        int año = fechaCalendar.get( Calendar.YEAR );
        int mes = fechaCalendar.get( Calendar.MONTH )+1;
        int dia = fechaCalendar.get( Calendar.DAY_OF_MONTH );

        fechaInicio = dia + "-" + mes + "-" + año + " 00:00:01" ;
        fechaFinal = dia + "-" + mes + "-" + año + " 23:59:59" ;

        try {
            formatter.parse( fechaInicio );
            query = em.createQuery( "SELECT u FROM ReporteFallas u WHERE u.fechaCreacion BETWEEN :fechaInicio AND :fechaFin AND u.usuariosComercios.token =:token AND u.tipificacion.idTipificacion =:idTipificacion" );
            query.setParameter( "fechaInicio", formatter.parse( fechaInicio ) );
            query.setParameter( "fechaFin", formatter.parse( fechaFinal ) );
            query.setParameter( "token", token );
            query.setParameter( "idTipificacion", Integer.parseInt( idTipificacion ));

            listaFallas = query.getResultList();
            respuesta = listaFallas.size() >= 2;
        } catch( Exception exc ) {
            logger.error( " >>> validarFallasClienteDia >>> probelmas con él idContacto: " + token );
            logger.error( " >>> validarFallasClienteDia >>> probelmas con él idContacto: " + exc.getMessage() );
        }
        return respuesta;
    }

    /**
     * @param codigoUnico
     * @param tipoComercio
     * @return boolean
     * @throws Exception 
     */
    @Override
    public boolean validarCodigoUnicoPortal( String codigoUnico, String tipoComercio ) throws Exception {
        boolean respuesta;

        try {
            As400DaoVersion comercioAS400DaoVersion = new As400DaoVersion();

            if( tipoComercio.equals("Comercio") ) {
                respuesta = comercioAS400DaoVersion.validarCodigoUnicoPortal( codigoUnico );
            } else {
                respuesta = comercioAS400DaoVersion.validaCodigoSucurSalCorresponsal( codigoUnico );
            } 
        } catch(Exception exc) {
            logger.error( ">>>> error " + exc.getMessage());
            logger.error( " >>> codigoUnico >>> " + codigoUnico + " tipoComercio " + tipoComercio );
            respuesta = false;
            throw exc;
        }
        return respuesta;
    }

    /**
     * @param codigoCiudad
     * @return List
     * @throws Exception 
     * 
     * @description devuelve las convenciones para el ingreso de direcciones
     */
    @Override
    public List<ConvencionesDireccion> getConvencionesDirecciones( String codigoCiudad ) throws Exception {
        List<ConvencionesDireccion> listaConvencionesDireccion;
        Query query;

        try {
            query = em.createQuery( "SELECT u FROM ConvencionesDireccion u WHERE u.idCiudad =:codigoCiudad OR u.idCiudad IS NULL " );
            query.setParameter( "codigoCiudad", codigoCiudad );
            listaConvencionesDireccion = query.getResultList();
        }catch( Exception exc ) {
            logger.error( " >>> getConvencionesDirecciones >>> " + exc.getMessage() );
            throw exc;
        }
    	return  listaConvencionesDireccion;
    }

    /**
     * @return PoliticaTratamiento
     * @throws Exception 
     */
    @Override
    public PoliticaTratamiento getPoliticaTratamiento() throws Exception {
        PoliticaTratamiento politica = new PoliticaTratamiento();
        List<PoliticaTratamiento> listaPoliticas;
        Query query;

    	try {
            query = em.createQuery( "SELECT u FROM PoliticaTratamiento u ORDER BY u.policyVersion DESC" );

            listaPoliticas = query.getResultList();

            if( listaPoliticas.size() > 0 )
                politica = listaPoliticas.get(0);

        } catch( Exception exc ) {
            logger.error( " >>> getPoliticaTratamiento >>> " + exc.getMessage() );
            throw exc;
        }
    	return politica;
    }
    
    /**
     * @return Response
     * @throws Exception
     * 
     * @description Devuelve la información del link de enlace para MiPago
     */
    @Override
    public Response getMensaje(String key) throws Exception {
        Response respuesta;
        Query query;
        MensajesAplicacion mensajesAplicacion;

        try{
            query = em.createQuery( "SELECT u FROM MensajesAplicacion u WHERE u.mensajesAplicacionPK.messageKey =:key" );
            query.setParameter( "key", key );
            mensajesAplicacion = ( MensajesAplicacion ) query.getSingleResult();
            respuesta = new Response( "true", mensajesAplicacion.getMessageText() , "" );
        } catch( Exception exc ){
            respuesta = new Response( "false", "Error Consultando la informacion de la tabla de mensajes" , "" );
        }
        return respuesta;
    }
    
    public static void doTrustToCertificates() throws Exception {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        TrustManager[] trustAllCerts = new TrustManager[]{
        
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                }
            }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        HostnameVerifier hv = new HostnameVerifier() {
        
            @Override
            public boolean verify(String urlHostName, SSLSession session) {
                if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
                    logger.warn("Warning: URL host '" + urlHostName + "' is different to SSLSession host '" + session.getPeerHost() + "'.");
                }
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }
    
    /**
     * 
     * @param cadena
     * @return boolean
     * 
     * Valida que en la cadena ingresada no se encuentre una serie de números
     * que pueda corresponder al número de una tarjeta
     * 
     * Se valida si el numero es de más de 11 caracteres y si pasa la validación del algoritmo de luhn.
     * 
     * true: la cadena ingresada se encuentra correcta y puede continuar el proceso.
     * false: la cadena ingresada no es correcta y no puede continuar con el proceso.
     */
    public boolean validarCampoSinNumeroTarjeta( String cadena ) {
        boolean respuesta = true, continuar = true;
        String tarjeta = "";

        cadena = cadena.replaceAll( " ", "" ).replaceAll( "-", "" );

        for( int i = 0; i < cadena.length()-1; i++ ) {
            if( cadena.charAt( i ) == '0' || cadena.charAt( i ) == '1' || cadena.charAt( i ) == '2' || cadena.charAt( i ) == '3'
                || cadena.charAt( i ) == '4' || cadena.charAt( i ) == '5' || cadena.charAt( i ) == '6' || cadena.charAt( i ) == '7'
                || cadena.charAt( i ) == '8' || cadena.charAt( i ) == '9' ) {

                tarjeta += cadena.charAt( i );

                for( int j = i+1; j < cadena.length() ; j++ ) {
                    if(( cadena.charAt( j ) == '0' || cadena.charAt( j ) == '1' || cadena.charAt( j ) == '2' 
                        || cadena.charAt( j ) == '3' || cadena.charAt( j ) == '4' || cadena.charAt( j ) == '5'
                        || cadena.charAt( j ) == '6' || cadena.charAt( j ) == '7' || cadena.charAt( j ) == '8'
                        || cadena.charAt( j ) == '9') && continuar ) {

                        tarjeta += cadena.charAt( j );
                    } else
                        continuar = false;
                }

                //System.out.println("tarjeta " + tarjeta + " " + (isValidNumber(tarjeta) && tarjeta.length() > 11));
                if( isValidNumber( tarjeta ) && tarjeta.length() > 11 )
                    respuesta = false;

                continuar = true;
                tarjeta = "";
            }
        }
        System.out.println( tarjeta );
        System.out.println( respuesta );
        return respuesta;
    }

    /**
     * @param s
     * @return boolean
     */
    private boolean isValidNumber( String s ) {
        return doLuhn( s, false ) % 10 == 0;
    }

    /**
     * @param s
     * @param evenPosition
     * @return int
     */
    private int doLuhn( String s, boolean evenPosition ) {
    	int sum = 0, n;
        for ( int i = s.length() - 1; i >= 0; i-- ) {
            n = Integer.parseInt( s.substring( i, i + 1 ));
            if ( evenPosition ) {
                n *= 2;

                if (n > 9)
                    n = (n % 10) + 1;
            }
            sum += n;
            evenPosition = !evenPosition;
        }
        return sum;
    }
    
    /**
     * @param key
     * @return String
     * @throws Exception 
     */
    @Override
    public String consultarMensaje( String key ) throws Exception {
        String mensaje = "";
        
        try {
            Query query = em.createQuery( "SELECT u FROM MensajesAplicacion u WHERE u.mensajesAplicacionPK.messageKey =:key" );
            query.setParameter( "key", key );
            MensajesAplicacion mensajesAplicacion = ( MensajesAplicacion ) query.getSingleResult();
            mensaje = mensajesAplicacion.getMessageText();
        } catch( Exception exc ) {
            logger.error( " >>> consultarMensaje >>> " + exc.getMessage() );
        }
        return mensaje;
    }

    /**
     * @param usuarioComercio
     * @return UsuariosComercios
     * @throws Exception
     * 
     * Genera el token con el que se va a validar al usuario una vez ingresa al aplicativo
     */
    @Override
    public String generarToken( UsuariosComercios usuarioComercio ) throws Exception {
        String respuesta;

        respuesta = ( java.util.UUID.randomUUID().toString() + java.util.UUID.randomUUID().toString() ).replaceAll("-", "");
        usuarioComercio.setToken( respuesta );
        usuarioComercio.setUltimoingreso(new Date());

        em.merge( usuarioComercio );
        em.flush();

        return respuesta;
    }
    /**
     * @param token
     * @throws Exception
     * 
     * Se encarga al momento de cerrar la sesion de eliminar el token que se habia
     * asignado al usuario al momento de ingresar a la aplioacion
     */
    @Override
    public void cerrarSesion( String token ) throws Exception {
        try {
            Query query = em.createQuery( "SELECT u FROM UsuariosComercios u WHERE u.token =:token" );
            query.setParameter( "token", token );
            UsuariosComercios usuarioComercio = ( UsuariosComercios ) query.getSingleResult();
            usuarioComercio.setToken("");
            em.merge( usuarioComercio );
            em.flush();
        } catch( Exception exc ) {
            logger.error( " >>> cerrarSesion >>> " + exc.getMessage() );
        }
    }

    @Override
    public Response registroAplicacion() {
        Response respuesta = new Response();
        try {
            
            String[] respu = obtenerLlaves();
            respuesta.setEstado("true");
            respuesta.setMensaje(respu[0]);
            respuesta.setParametros(respu[1]);
        } catch( Exception exc ){
            respuesta.setEstado("false");
            respuesta.setMensaje("");
            respuesta.setParametros("");
            
        }
        return respuesta;
    }

    @Override
    public String[] obtenerLlaves() {
        String[] llaves= new String[2];

        Query query;
        query = em.createQuery( "SELECT u.parameterValue FROM ParametrosGlobales u WHERE u.parameterKey =:parameterKey" );
        query.setParameter( "parameterKey", "ivKey" );
        llaves[0] = ( String ) query.getSingleResult();

        query = em.createQuery( "SELECT u.parameterValue FROM ParametrosGlobales u WHERE u.parameterKey =:parameterKey" );
        query.setParameter( "parameterKey", "saltKey" );
        llaves[1] = ( String ) query.getSingleResult();

        return llaves;
    }
    
    @Override
    public boolean obtenerParametroPruebas( String idContacto, String nit, String codigoUnico ) {
        boolean respuesta = true;
        Query query;

        try {
            query = em.createQuery( "SELECT count(u.idContacto) FROM ContactoComercio u WHERE u.idContacto =:idContacto AND u.datosComercio.datosComercioPK.nitComercio = (SELECT b.parameterValue FROM ParametrosGlobales b WHERE b.parameterKey = 'nit_CbCo' ) AND u.datosComercio.datosComercioPK.codigoUnico = (SELECT b.parameterValue FROM ParametrosGlobales b WHERE b.parameterKey = 'cU_CbCo' )" );
            query.setParameter( "idContacto", idContacto );
            Object obj = query.getSingleResult();

            if( obj != null ) {
                String str = obj.toString();

                if( str.equals("0"))
                    return true;
                else 
                    return false;
            }
        } catch( Exception exc ){
            exc.printStackTrace();
            return true;
            
        }

        return respuesta;
    }
}