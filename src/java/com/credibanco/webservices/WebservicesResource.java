package com.credibanco.webservices;

import com.credibanco.Entity.CategoriasCapacitacion;
import com.credibanco.Entity.ConvencionesDireccion;
import com.credibanco.Entity.LogUsuariosComercios;
import com.credibanco.Entity.PoliticaTratamiento;
import com.credibanco.Entity.Termsandconditions;
import com.credibanco.Entity.UsuariosComercios;
import com.credibanco.Entity.CategoriasFAQ;
import com.credibanco.Entity.Departamentos;
import com.credibanco.Entity.TipodocId;
import com.credibanco.Entity.Ciudades;
import com.credibanco.Entity.TipoPOS;
import com.credibanco.Entity.Faq;

import com.credibanco.appCanal.ejb.AppCanalWSEJBLocal;
import com.credibanco.as400.model.AS400DaoSis;
import com.credibanco.as400.model.As400DAO;
import com.credibanco.util.TripleDES;
import com.credibanco.dto.Response;
import com.credibanco.util.AES;

import dao.DatosCapacitacion;
import dao.TipificacionDao;
import dao.MensajePushDao;
import dao.Contactenos;
import dao.MiPago;

import java.io.IOException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.ejb.EJB;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Path("Webservices")
public class WebservicesResource {

    @Context
    private UriInfo context;
    @Context
    private ServletContext servletContext; 

    static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger( WebservicesResource.class );

    /**
     * Creates a new instance of WebservicesResource
     */
    public WebservicesResource() {
    }

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +  "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PATTERN_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-°!“”#$%&/(.)=?@¡¿’*¨´+}{\\[\\]_:;,><~`\\\\^])(?=\\S+$).{8,}$";
    private String SECRET_KEY = "SecretKey";

    @EJB
    AppCanalWSEJBLocal ejb;

     /**
     * PUT method for updating or creating an instance of WebservicesResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @GET
    @Path("/getAuth")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser( @QueryParam("Tipo_Identificacion")String idTipoIdentificacion,@QueryParam("Numero_identificacion")String numeroIdentificacion, @QueryParam("Codigo_Unico")String codigoUnico, @QueryParam("password")String password ) {
        UsuariosComercios usuarioComercio;
        As400DAO comercioAS400Dao;
        Response response;
        AS400DaoSis aS400DaoSis;
        UsuariosComercios usuarioComercioTemp = null;

        initEjb();

        //password = password.replaceAll("%21","!").replaceAll("%23","#").replaceAll("%24","$").replaceAll("%26","&").replaceAll("%27","'").replaceAll("%28","(").replaceAll("%29",")").replaceAll("%2A","*").replaceAll("%2B","+").replaceAll("%2C",",").replaceAll("%2F","/").replaceAll("%3A",":").replaceAll("%3B",";").replaceAll("%3D","=").replaceAll("%3F","?").replaceAll("%40","@").replaceAll("%5B","[").replaceAll("%5D","]").replaceAll("%25","%");
        String llaves[] = ejb.obtenerLlaves();
        String ivKey = llaves[0];
        String saltKey = llaves[1];
        String nit = "";

        idTipoIdentificacion = AES.decrypt( idTipoIdentificacion, ivKey, saltKey );
        numeroIdentificacion = AES.decrypt( numeroIdentificacion, ivKey, saltKey );
        codigoUnico  = AES.decrypt( codigoUnico, ivKey, saltKey );
        password = AES.decrypt( password, ivKey, saltKey );
        
        try {
            usuarioComercioTemp = ejb.findUser( numeroIdentificacion );
        }
        catch (Exception exc){
            
        }
        
        if(usuarioComercioTemp != null)
            nit = usuarioComercioTemp.getIdContacto().getDatosComercio().getDatosComercioPK().getNitComercio();

        try {
            comercioAS400Dao = new As400DAO();
            response = comercioAS400Dao.validaEstadoComercio( codigoUnico, nit );
            if( response.getEstado().equals( "true" )) {
                password = TripleDES._encrypt(password, SECRET_KEY);

                if( ejb.authenticateUser( idTipoIdentificacion, numeroIdentificacion, codigoUnico, password )) {
                    usuarioComercio = ejb.findUser( numeroIdentificacion );

                    if( usuarioComercio.getAccesoBloqueado() == true )
                        response = new Response( "false", "Su usuario se encuentra bloqueado", "" );
                    else {
                        response = new Response( "true", "Exitoso", "" );
                        aS400DaoSis = new AS400DaoSis();
                        boolean respuesta = aS400DaoSis.validaCodigoComercios21(codigoUnico);
                        logger.error( " >>> validaCodigoComercios21 Respuesta  >>> " + respuesta );

                        if(respuesta)
                            response = new Response( "false", "Tu comercio está registrado como corresponsal bancario y para ti hay un esquema de atención especial. \n  Para mayor información comunícate con nosotros", "" );
                    }

                    if(response.getEstado().equals("true")) {
                        response.setParametros( ejb.generarToken( usuarioComercio ));

                        LogUsuariosComercios auditoria;
                        auditoria = new LogUsuariosComercios();
                        auditoria.setLogDate( Calendar.getInstance().getTime() );
                        auditoria.setLogAccion( "Autenticación Usuario" );
                        auditoria.setUsuario( numeroIdentificacion );
                        auditoria.setIp( InetAddress.getLocalHost().getHostAddress() );
                        ejb.insertAudit( auditoria );
                    }
                } else {
                    usuarioComercio = ejb.findUser( numeroIdentificacion );

                    if( usuarioComercio.getAccesoBloqueado() == true )
                        response = new Response( "false", "Su usuario se encuentra bloqueado", "" );
                    else
                        response = new Response( "false", "Usuario o contraseña incorrecta", "" );
                }
            } else {
                if(response.getMensaje().equals(""))
                    response.setMensaje(ejb.consultarMensaje("ingresoBloqueado"));
            }
        } catch ( Exception exc ) {
            response = new Response( "false", "No se logro autenticar el usuario", "" );
            logger.error( " >>> authenticateUser >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return response;
    }
    
    /*@GET
    @Path("/getAuth")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser( @QueryParam("Tipo_Identificacion")String idTipoIdentificacion, @QueryParam("Numero_identificacion")String numeroIdentificacion, @QueryParam("Codigo_Unico")String codigoUnico, @QueryParam("password")String password ) {
        UsuariosComercios usuarioComercio = null;
        As400DAO comercioAS400Dao;
        Response response;
        AS400DaoSis aS400DaoSis;

        String llaves[] = ejb.obtenerLlaves();
        String ivKey = llaves[0];
        String saltKey = llaves[1];

        idTipoIdentificacion = AES.decrypt( idTipoIdentificacion, ivKey, saltKey );
        numeroIdentificacion = AES.decrypt( numeroIdentificacion, ivKey, saltKey );
        codigoUnico  = AES.decrypt( codigoUnico, ivKey, saltKey );
        password = AES.decrypt( password, ivKey, saltKey );

        initEjb();

        //password = password.replaceAll("%21","!").replaceAll("%23","#").replaceAll("%24","$").replaceAll("%26","&").replaceAll("%27","'").replaceAll("%28","(").replaceAll("%29",")").replaceAll("%2A","*").replaceAll("%2B","+").replaceAll("%2C",",").replaceAll("%2F","/").replaceAll("%3A",":").replaceAll("%3B",";").replaceAll("%3D","=").replaceAll("%3F","?").replaceAll("%40","@").replaceAll("%5B","[").replaceAll("%5D","]").replaceAll("%25","%");

        try {
            comercioAS400Dao = new As400DAO();
            response = comercioAS400Dao.validaEstadoComercio( codigoUnico );
            if( response.getEstado().equals( "true" )) {
                logger.info( "Llamado clase para conexion con REFTAB21" );
                aS400DaoSis = new AS400DaoSis();
                logger.error( " >>> validaCodigoComercios21 >>> "  );
                boolean respuesta = aS400DaoSis.validaCodigoComercios21(codigoUnico);
                
                if( respuesta == false ) {
                    password = TripleDES._encrypt(password, SECRET_KEY);

                    if( ejb.authenticateUser( idTipoIdentificacion, numeroIdentificacion, codigoUnico, password )) {
                        usuarioComercio = ejb.findUser( numeroIdentificacion );

                        if( usuarioComercio.getAccesoBloqueado() == true )
                            response = new Response( "false", "Su usuario se encuentra bloqueado", "" );
                        else
                            response = new Response( "true", "Exitoso", "" );

                        LogUsuariosComercios auditoria;
                        auditoria = new LogUsuariosComercios();
                        auditoria.setLogDate( Calendar.getInstance().getTime() );
                        auditoria.setLogAccion( "Autenticación Usuario" );
                        auditoria.setUsuario( numeroIdentificacion );
                        auditoria.setIp( InetAddress.getLocalHost().getHostAddress() );
                        ejb.insertAudit( auditoria );
                    } else {
                        usuarioComercio = ejb.findUser( numeroIdentificacion );

                        if( usuarioComercio.getAccesoBloqueado() == true )
                            response = new Response( "false", "Su usuario se encuentra bloqueado", "" );
                        else
                            response = new Response( "false", "Usuario o contraseña incorrecta", "" );
                    }
                } else {
                    response = new Response( "false", "Si eres corresponsal bancario, no podrás registrarte en CredibanCo App. Para ti hay un esquema de atención especial.   Para mayor información comunícate con nosotros", "" );
                }
            } else {
                if(response.getMensaje().equals(""))
                    response.setMensaje(ejb.consultarMensaje("ingresoBloqueado"));
            }

            if(response.getEstado().equals("true")) {
                response.setParametros( ejb.generarToken( usuarioComercio ));
            }
        } catch ( Exception exc ) {
            response = new Response( "false", "No se logro autenticar el usuario", "" );
            logger.error( " >>> authenticateUser >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return response;
    }*/

    @GET
    @Path("/getAuthComer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateComercios(@QueryParam("Numero_identificacion")String numeroIdentificacion, @QueryParam("Codigo_Unico")String codigoUnico) throws Exception {
        Response response = null;
        try {
            String llaves[] = ejb.obtenerLlaves();
            String ivKey = llaves[0];
            String saltKey = llaves[1];

            numeroIdentificacion = AES.decrypt( numeroIdentificacion, ivKey, saltKey );
            codigoUnico = AES.decrypt( codigoUnico, ivKey, saltKey );
            
            response =  ejb.authenticateComercios(numeroIdentificacion, codigoUnico);
            if( response.getEstado().equals("false") && response.getMensaje().equals("")) {
                response.setMensaje(ejb.consultarMensaje("ingresoBloqueado"));
            }
                
        } catch( Exception exc ){
            logger.error( " >>> getAuthComer >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return response;
    }

    @GET
    @Path("/validaEstadoComercio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validaEstadoComercio( @QueryParam("MCODES") String MCODES, @QueryParam("MNITES") String MNITES ) throws Exception {
        As400DAO comercioAS400Dao;
        AS400DaoSis aS400DaoSis;
        Response respuesta = null;
        boolean resp = true;

        try {
            String llaves[] = ejb.obtenerLlaves();
            String ivKey = llaves[0];
            String saltKey = llaves[1];

            MCODES = AES.decrypt( MCODES, ivKey, saltKey );
            MNITES = AES.decrypt( MNITES, ivKey, saltKey );
            
            comercioAS400Dao = new As400DAO();
            respuesta = comercioAS400Dao.validaEstadoComercio( MCODES, MNITES );
            
            if( respuesta.getEstado().equals("false") && respuesta.getMensaje().equals(""))
                respuesta.setMensaje(ejb.consultarMensaje("registroBloqueado"));
            
            else {
                aS400DaoSis = new AS400DaoSis();
                resp = aS400DaoSis.validaCodigoComercios21(MCODES);

                if( resp == true  )
                    respuesta = new Response("false", "Tu comercio está registrado como corresponsal bancario y para ti hay un esquema de atención especial. \n  Para mayor información comunícate con nosotros", "");
            }
            
        } catch ( Exception exc ) {
            logger.error( " >>> validaEstadoComercio >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return  respuesta;
    }

    @GET
    @Path("/getDepartament")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Departamentos> getDepartment() {
        List<Departamentos> listaDepartamentos = new ArrayList<>();    
        initEjb();

        try {
            listaDepartamentos = ejb.getDepartamentos();
        } catch ( Exception exc ) {
            logger.error( " >>> getDepartment >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return listaDepartamentos;
    }

    @GET
    @Path("/getCities")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ciudades> getCities( @QueryParam("id_departamento") int idDepartamento ) {
        List<Ciudades> listaCiudades = new ArrayList<>();    
        initEjb();

        try {
            listaCiudades = ejb.getCiudades( idDepartamento );
        } catch ( Exception exc ) {
            logger.error( " >>> getCities >>> " + exc.getMessage() );
            logger.error( " >>> getCities >>> " + exc );
            //exc.printStackTrace();
        }
        return listaCiudades;
    }

    private void initEjb() {
        if ( ejb == null ) {
            try {
                javax.naming.Context ctx = new InitialContext();
                ejb = ( AppCanalWSEJBLocal )ctx.lookup( "java:app/CanalMobile_V2/AppCanalWSEJB!com.credibanco.appCanal.ejb.AppCanalWSEJBLocal" );  
            } catch (NamingException exc) {
                logger.error( " >>> initEjb >>> " + exc.getMessage() );
                exc.printStackTrace();
            }
        }
    }

    @GET
    @Path("/terminosCondiciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Termsandconditions terminosCondiciones() throws Exception {
        Termsandconditions termsandconditions = null;
        try {
            termsandconditions = ejb.findTermsandconditions();
        } catch(Exception exc) {
            logger.error( " >>> terminosCondiciones >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return termsandconditions;
    }

    /**
     * @param email
     * @return boolean
     */
    private boolean validateEmail( String email ) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * @param password
     * @return boolean
     */
    private boolean validatePassword( String password ) {
        Pattern pattern = Pattern.compile( PATTERN_PASSWORD );
        Matcher matcher = pattern.matcher( password );
        return matcher.matches();
    }

    @GET
    @Path("/getTiposDocumentos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipodocId> getTipoDocumentos() {
        List<TipodocId> listaTipoDocumentos = new ArrayList<>();    
        initEjb();

        try {
            listaTipoDocumentos = ejb.getTiposDocumentos();
        } catch ( Exception exc ) {
            logger.error( " >>> getTipoDocumentos >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return listaTipoDocumentos;
    }

    @GET
    @Path("/getCategoriasFAQ")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoriasFAQ> getCategoriasFAQ() {
        List<CategoriasFAQ> listaCategoriasFaq = new ArrayList<>();
        initEjb();

        try {
            listaCategoriasFaq = ejb.getCategoriasFaq();
        } catch ( Exception exc ) {
            logger.error( " >>> getCategoriasFAQ >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return listaCategoriasFaq;
    }

    @GET
    @Path("/getFaq")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Faq> getFaq( @QueryParam("id_categoria") int idCategoria ) {
        List<Faq> listaCategoriasFaq = new ArrayList<>();    
        initEjb();

        try {
            listaCategoriasFaq = ejb.getFaq( idCategoria );
        } catch ( Exception exc ) {
            logger.error( " >>> getFaq >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return listaCategoriasFaq;
    }

    @GET
    @Path("/actualizarFAQ")
    @Produces(MediaType.APPLICATION_JSON)
    public void actualizarFaq( @QueryParam("id_pregunta") String idPregunta ) {
        initEjb();
        try {
            ejb.actualizarFaq( idPregunta );
        } catch ( Exception exc ) {
            logger.error( " >>> actualizarFaq >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
    }

    @GET
    @Path("/adicionarDatosComercio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarDatosComercio( @QueryParam("parametros") String parametros ) throws Exception {
        Response respuesta = null;
        try {
            respuesta = ejb.adicionarDatosComercio( parametros );
        }
        catch(Exception exc) {
            logger.error( " >>> adicionarDatosComercio >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return respuesta;
    }

    @GET
    @Path("/adicionarDatosProspectos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarDatosProspectos( @QueryParam("parametros") String parametros ) throws Exception {
        Response respuesta = null;

        try {
            respuesta = ejb.adicionarDatosProspectos( parametros );
        }
        catch( Exception exc ) {
            logger.error( " >>> adicionarDatosProspectos >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return respuesta;
    }

    @GET
    @Path("/adicionarSolicitudPos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarSolicitudPos( @QueryParam("parametros") String parametros ) throws Exception {
        Response respuesta = null;

        try {
            respuesta = ejb.adicionarSolicitudPos( parametros );
        }
        catch( Exception exc ) {
            logger.error( " >>> adicionarDatosProspectos >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return respuesta;
    }

    @GET
    @Path("/createTemporalPassword")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTemporalPassword( @QueryParam("tipoIdentificacion") String tipoIdentificacion,@QueryParam("nroIdentificacion") String nroIdentificacion, @QueryParam("codigoUnico") String codigoUnico ) throws Exception {
        Response response;
        UsuariosComercios usuarioComercio;

        try {
            
            String llaves[] = ejb.obtenerLlaves();
            String ivKey = llaves[0];
            String saltKey = llaves[1];

            tipoIdentificacion = AES.decrypt( tipoIdentificacion, ivKey, saltKey );
            nroIdentificacion = AES.decrypt( nroIdentificacion, ivKey, saltKey );
            codigoUnico = AES.decrypt( codigoUnico, ivKey, saltKey );
            
            usuarioComercio = ejb.findUser( nroIdentificacion,tipoIdentificacion, codigoUnico );

            ejb.generateTempPassword( usuarioComercio );
            response = new Response( "true", "", "" );
        } catch(Exception exc) {
            response = new Response("false", "Ocurrio un error al generar una contraseña temporal", "" );
            logger.error( " >>> createTemporalPassword >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return response;
    }

    @GET
    @Path("/validateTemporalPassword")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateTemporalPassword( @QueryParam("tipoIdentificacion") String tipoIdentificacion,@QueryParam("nroIdentificacion") String nroIdentificacion, @QueryParam("codigoUnico") String codigoUnico,@QueryParam("tempPassword") String tempPassword ) throws Exception {
        Response response;
        UsuariosComercios usuarioComercio;

        try {
            String llaves[] = ejb.obtenerLlaves();
            String ivKey = llaves[0];
            String saltKey = llaves[1];

            tipoIdentificacion = AES.decrypt( tipoIdentificacion, ivKey, saltKey );
            nroIdentificacion = AES.decrypt( nroIdentificacion, ivKey, saltKey );
            codigoUnico = AES.decrypt( codigoUnico, ivKey, saltKey );
            tempPassword = AES.decrypt( tempPassword, ivKey, saltKey );

            usuarioComercio = ejb.findUserTempPassword( nroIdentificacion,tipoIdentificacion , codigoUnico,tempPassword );

           if( usuarioComercio != null )
               response = new Response( "true", "", "" );
           else
               response = new Response( "false", "La contraseña ingresada no coincide", "" );
        }
        catch( Exception exc ) {
            response = new Response( "false", "Ocurrio un error al validar la contraseña temporal", "" );
            logger.error( " >>> validateTemporalPassword >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return response;
    }

    @GET
    @Path("/changePassword")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword( @QueryParam("tipoIdentificacion") String tipoIdentificacion,@QueryParam("nroIdentificacion") String nroIdentificacion, @QueryParam("codigoUnico") String codigoUnico,@QueryParam("Password") String Password ) throws Exception {
        Response response;
        UsuariosComercios usuarioComercio;

        String llaves[] = ejb.obtenerLlaves();
        String ivKey = llaves[0];
        String saltKey = llaves[1];

        nroIdentificacion = AES.decrypt( nroIdentificacion, ivKey, saltKey );
        tipoIdentificacion = AES.decrypt( tipoIdentificacion, ivKey, saltKey );
        codigoUnico = AES.decrypt( codigoUnico, ivKey, saltKey );
        Password = AES.decrypt( Password, ivKey, saltKey );
        
        try {
            usuarioComercio = ejb.findUser( nroIdentificacion,tipoIdentificacion, codigoUnico );
            response = ejb.updatePassword( usuarioComercio, TripleDES._encrypt( Password, SECRET_KEY ));
        }
        catch( Exception exc ) {
            response = new Response( "false", "Ocurrio un error al validar la contraseña temporal", "" );
            logger.error( " >>> changePassword >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return response;
    }

    @GET
    @Path("/validarTerminosCondiciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validarTerminosCondiciones( @QueryParam("token") String token ) throws Exception {
        Response response = null;

        try {
            response = ejb.validarTerminosCondiciones( token );
        }
        catch( Exception exc ) {
            logger.error( " >>> validarTerminosCondiciones >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return response;
    }

    @GET
    @Path("/actualizarTerminosCondiciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarTerminosCondiciones( @QueryParam("token") String token ) throws Exception {
        Response response = null;

        try {
            response = ejb.actualizarTerminosCondiciones( token );
        }
        catch( Exception exc ) {
            logger.error( " >>> actualizarTerminosCondiciones >>> " + exc.getMessage() );
            logger.error( " >>> actualizarTerminosCondiciones >>> " + exc );
            //exc.printStackTrace();
        }
        return response;
    }

    @GET
    @Path("/getTiposPos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoPOS> getTiposPos( ) throws Exception {
        List<TipoPOS> listaTipoPos = new ArrayList<>();
        initEjb();

        try {
            listaTipoPos = ejb.getTiposPos();
        } catch ( Exception exc ) {
            logger.error( " >>> getTiposPos >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return listaTipoPos;
    }

    @GET
    @Path("/getValidarTecnico")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getValidarTecnico( @QueryParam("parametros") String parametros ) throws Exception {
        Response respuesta = null;
        String relativeWebPath = "/images";
        String imagePath;
        initEjb();

        imagePath = servletContext.getRealPath( relativeWebPath );

        try {
            respuesta = ejb.getValidarTecnico( parametros,imagePath );
        } catch ( Exception exc ) {
            logger.error( " >>> getValidarTecnico >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return respuesta;
    }

    @GET
    @Path("/adicionarSolicitudMiPago")
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarSolicitudMiPago( @QueryParam("parametros") String parametros ) throws Exception {
        Response respuesta = null;

        try {
            respuesta = ejb.adicionarSolicitudMiPago( parametros );
        }
        catch( Exception exc ) {
            logger.error( " >>> adicionarSolicitudMiPago >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return respuesta;
    }

    @GET
    @Path("/getTemasCapacitacion")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoriasCapacitacion> getTemasCapacitacion( ) throws Exception {
        List<CategoriasCapacitacion> listaTemasCapacitacion = new ArrayList<>();    
        initEjb();

        try {
            listaTemasCapacitacion = ejb.getTemasCapacitacion();
        } catch ( Exception exc ) {
            logger.error( " >>> getTemasCapacitacion >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return listaTemasCapacitacion;
    }

    @GET
    @Path("/getCapacitacionComercios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DatosCapacitacion> getCapacitacionComercios( @QueryParam( "id_categoria" ) String idCategoria ) throws Exception {
        List<DatosCapacitacion> listaCapacitacionComercios = new ArrayList<>();
        String relativeWebPath = "/archivos";
        String path;
        initEjb();

        try {
            path = servletContext.getRealPath(relativeWebPath);
            listaCapacitacionComercios = ejb.getCapacitacionComercios( idCategoria, path );
        } catch ( Exception exc ) {
            logger.error( " >>> getCapacitacionComercios >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return listaCapacitacionComercios;
    }

    @GET
    @Path("/adicionarSolicitudDatafono")
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarSolicitudDatafono( @QueryParam("parametros") String parametros ) throws Exception {
        Response response = new Response();    
        initEjb();

        try {
            response = ejb.adicionarSolicitudDatafono( parametros );
        } catch ( Exception exc ) {
            logger.error( " >>> adicionarSolicitudDatafono >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return response;
    }

    @GET
    @Path("/getDatosSolicitudMiPago")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MiPago> getDatosSolicitudMiPago( @QueryParam("parametros") String parametros ) throws Exception {
        List<MiPago> listaDatosMiPago = new ArrayList<>();    
        initEjb();

        try {
            listaDatosMiPago = ejb.getDatosSolicitudMiPago( parametros );
        } catch ( Exception exc ) {
            logger.error( " >>> getDatosSolicitudMiPago >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return listaDatosMiPago;
    }

    @GET
    @Path("/getCanalesOficinas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contactenos> getCanalesOficinas() throws Exception {
        List<Contactenos> listaDatosMiPago = new ArrayList<>();    
        initEjb();

        try {
            listaDatosMiPago = ejb.getCanalesOficinas();
        } catch ( Exception exc ) {
            logger.error( " >>> getCanalesOficinas >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return listaDatosMiPago;
    }

    @GET
    @Path("/adicionarFallas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarFallas( @QueryParam("parametros") String parametros ) throws Exception {
        Response response = new Response();
        initEjb();

        try {
            response = ejb.adicionarFallas( parametros );
        } catch ( Exception exc ) {
            logger.error( " >>> adicionarFallas >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return response;
    }

    @GET
    @Path("/getPantallaTemporal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPantallaTemporal() throws Exception {
        Response response = new Response();
        String relativeWebPath = "/images";
        String imagePath;

        imagePath = servletContext.getRealPath( relativeWebPath );
        initEjb();

        try {
            response = ejb.getPantallaTemporal( imagePath );
        } catch ( Exception exc ) {
            logger.error( " >>> getPantallaTemporal >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return response;
    }

    @GET
    @Path("/getListadoBanner")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListadoBanner() throws Exception {
        Response response = new Response();
        String relativeWebPath = "/images";
        String imagePath;
      
        imagePath = servletContext.getRealPath( relativeWebPath );
        initEjb();

        try {
            response = ejb.getListadoBanner( imagePath );
        } catch ( Exception exc ) {
            logger.error( " >>> getListadoBanner >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return response;
    }

    @GET
    @Path("/updateUserToken")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateUserToken(@QueryParam("identificacion") String identificacion, @QueryParam("token") String token) {
        initEjb();
        
        try {
            ejb.updateImei( identificacion, token );
        }
        catch (Exception exc) {
            logger.error( " >>> updateUserToken >>> " + exc.getMessage() );
            Logger.getLogger( WebservicesResource.class.getName()).log(Level.SEVERE, null, exc );
        }
    }

    @GET
    @Path("/sendPushMessage")
    @Produces(MediaType.APPLICATION_JSON)
    public void sendPushMessage( @QueryParam("identificacion") String as_identificacion, @QueryParam("title") String as_title,@QueryParam("body") String bodyMessage,@QueryParam("message")String message ) {
        HttpClient client = HttpClientBuilder.create().build();
	HttpPost post = new HttpPost("https://fcm.googleapis.com/fcm/send");
	post.setHeader("Content-type", "application/json");
	post.setHeader("Authorization", "key=AAAAP4hr0qI:APA91bFFPsshH-tRhC74kl1-4iUTGnbdDV-NNuR1bKZe6BE2LRRJRt4ow0EamZibeuRMhvyYguypfHUP2k8xyOKK-dtzzwPmW50T1W1RBO5yQEK9F544e40mPFzPOq19pCwhxaNA0I8GQfDMOfc9em6B8P6L2-4Wlg");

        try {
            UsuariosComercios usuarioComercio;
            usuarioComercio = ejb.findUser(as_identificacion);

	    JSONObject body = new JSONObject();
		    // JsonArray registration_ids = new JsonArray();
		    // body.put("registration_ids", registration_ids);
            //fToon6ejg7k:APA91bHcryJls75zCkrpimHGttw7DTHkdRUnkGu5NdfBcWn6ulI4eyJS7eB6kImOLr8DB1Too4236obfZEzgIBdX5oWkLWa5adEG5hhMks0alLGO2bTWEwHeSEyWdS0eq8HJYFjKFKPu         
            body.put("to", usuarioComercio.getImei());
            body.put("priority", "high");
		    // body.put("dry_run", true);

            JSONObject notification = new JSONObject();
            notification.put( "body", bodyMessage );
            notification.put( "title", as_title );
            notification.put( "sound	", "default" );
            notification.put( "click_action", "FCM_PLUGIN_ACTIVITY" );
            notification.put( "icon", "fcm_push_icon" );
		    // notification.put("icon", "myicon");
	
            JSONObject data = new JSONObject();
            data.put( "message", message );

            body.put( "notification", notification );
            body.put( "data", data );
		
            post.setEntity( new StringEntity( body.toString(), "UTF-8" ));
            HttpResponse response;
	
	    response = client.execute( post );
            logger.info( response );
	} catch ( ClientProtocolException exc ) {
	    exc.printStackTrace();
	} catch ( IOException exc ) {
            exc.printStackTrace();
	} catch ( JSONException exc ) {
            exc.printStackTrace();
	} catch ( Exception exc ) {
            logger.error( " >>> sendPushMessage >>> " + exc.getMessage() );
            Logger.getLogger(WebservicesResource.class.getName()).log(Level.SEVERE, null, exc);
        }	
    }

    @GET
    @Path("/getTipoFallas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipificacionDao> getTipoFallas() throws Exception {
        List<TipificacionDao> listaTipificacionSM = new ArrayList<>();    
        initEjb();

        try {
            listaTipificacionSM = ejb.getTipoFallas();
        } catch ( Exception exc ) {
            logger.error( " >>> getTipoFallas >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return listaTipificacionSM;
    }

    @GET
    @Path("/getListadoMensajesPush")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MensajePushDao> getListadoMensajesPush( @QueryParam( "token" ) String token ) throws Exception {
        List<MensajePushDao> listaMensajesPush = new ArrayList<>();    
        initEjb();

        try {
            listaMensajesPush = ejb.getListadoMensajesPush( token );
        } catch ( Exception exc ) {
            logger.error( " >>> getListadoMensajesPush >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return listaMensajesPush;
    }

    @GET
    @Path("/marcarMensajeLeido")
    @Produces(MediaType.APPLICATION_JSON)
    public Response marcarMensajeLeido( @QueryParam( "id_mensaje" ) String idMensaje ) throws Exception {
        Response respuesta = new Response();
        initEjb();

        try {
            respuesta = ejb.marcarMensajeLeido( idMensaje );
        } catch ( Exception exc ) {
            logger.error( " >>> marcarMensajeLeido >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return respuesta;
    }

    @GET
    @Path("/actualizarCapacitacionComercios")
    @Produces(MediaType.APPLICATION_JSON)
    public void actualizarCapacitacionComercios( @QueryParam("id_capacitacion") String idCapacitacion ) {
        initEjb();

        try {
            ejb.actualizarCapacitacionComercios( idCapacitacion );
        } catch ( Exception exc ) {
            logger.error( " >>> marcarMensajeLeido >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
    }

    @GET
    @Path("/validarReporteFallasClienteDia")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean validarReporteFallasClienteDia( @QueryParam("token")String token, @QueryParam("id_tipificacion")String idTipificacion ) {
        boolean respuesta = false;
        try{
            respuesta = ejb.validarReporteFallasClienteDia(token, idTipificacion);
        } catch( Exception exc ) {
            
        }
        return respuesta;
    }

    @GET
    @Path("/getConvencionesDirecciones")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConvencionesDireccion> getConvencionesDirecciones( @QueryParam("codigo_ciudad")String codigoCiudad ) throws Exception {
        List<ConvencionesDireccion> listaConvencionesDireccion = new ArrayList<>();    
        initEjb();

        try {
            listaConvencionesDireccion = ejb.getConvencionesDirecciones( codigoCiudad );
        } catch ( Exception exc ) {
            logger.error( " >>> getConvencionesDirecciones >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return listaConvencionesDireccion;
    }

    @GET
    @Path("/cerrarSesion")
    @Produces(MediaType.APPLICATION_JSON)
    public void cerrarSesion( @QueryParam("token")String token ) throws Exception {
        initEjb();

        try {
            ejb.cerrarSesion( token );
        } catch ( Exception exc ) {
            logger.error( " >>> cerrarSesion >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
    }
    
    @GET
    @Path("/registroAplicacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registroAplicacion( ) throws Exception {
        initEjb();
        Response respuesta = new Response();

        try {
            respuesta = ejb.registroAplicacion( );
        } catch ( Exception exc ) {
            logger.error( " >>> registroAplicacion >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return respuesta;
    }
    
    /** Todo de aqui para abajo se Elimina.
     * @param codigoUnico
     * @param password
     * @param tipoComercio
     * @return boolean
     */
    @GET
    @Path("/validarCodigoUnicoPortal")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean validarCodigoUnicoPortal( @QueryParam("codigo_unico")String codigoUnico, @QueryParam("tipoComercio")String tipoComercio ) {
        boolean respuesta = false;

        try {
            respuesta = ejb.validarCodigoUnicoPortal( codigoUnico, tipoComercio );
        } catch( Exception exc ) {
            exc.printStackTrace();
        }
        return respuesta;
    }
    
    @GET
    @Path("/getPoliticaTratamiento")
    @Produces(MediaType.APPLICATION_JSON)
    public PoliticaTratamiento getPoliticaTratamiento() throws Exception {
        PoliticaTratamiento politicaTratamiento = null;
        try {
            politicaTratamiento = ejb.getPoliticaTratamiento();
        } catch(Exception exc) {
            logger.error( " >>> getPoliticaTratamiento >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return politicaTratamiento;
    }
    
    @GET
    @Path("/getMensaje")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMensaje( @QueryParam("key")String key ) throws Exception {
        Response response = new Response();

        initEjb();

        try {
            response = ejb.getMensaje( key );
        } catch ( Exception exc ) {
            logger.error( " >>> getMensaje >>> " + exc.getMessage() );
            exc.printStackTrace();
        }
        return response;
    }
}