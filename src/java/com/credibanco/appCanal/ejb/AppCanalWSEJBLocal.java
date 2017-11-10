package com.credibanco.appCanal.ejb;

import com.credibanco.Entity.CategoriasCapacitacion;
import com.credibanco.Entity.LogUsuariosComercios;
import com.credibanco.Entity.Termsandconditions;
import com.credibanco.Entity.UsuariosComercios;
import com.credibanco.Entity.CategoriasFAQ;
import com.credibanco.Entity.Departamentos;
import com.credibanco.Entity.TipodocId;
import com.credibanco.Entity.Ciudades;
import com.credibanco.Entity.ContactoComercio;
import com.credibanco.Entity.ConvencionesDireccion;
import com.credibanco.Entity.TipoPOS;
import com.credibanco.Entity.Faq;
import com.credibanco.Entity.PoliticaTratamiento;

import dao.DatosCapacitacion;
import dao.Contactenos;
import dao.MiPago;

import com.credibanco.dto.Response;
import dao.MensajePushDao;
import dao.TipificacionDao;

import javax.ejb.Local;

import java.util.List;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Local
public interface AppCanalWSEJBLocal {
    public boolean authenticateUser( String tipoID, String numeroID, String codigoUnico, String contrasenia ) throws Exception;
    public UsuariosComercios findUser( String idContact, String tipoIdentificacion, String codUnico ) throws Exception;
    public UsuariosComercios findUser( String pNumero_identificacion ) throws Exception;
    
    public boolean changePassword( String email, String password ) throws Exception;
    public Termsandconditions findTermsandconditions() throws Exception;
    public PoliticaTratamiento getPoliticaTratamiento() throws Exception;
    public Response authenticateComercios( String codigoComercio, String nitComercio ) throws Exception;

    void insertAudit( LogUsuariosComercios audit );

    public Response actualizarTerminosCondiciones( String token ) throws Exception;
    public void actualizarFaq( String idFaq ) throws Exception;
    public Response updatePassword( UsuariosComercios usuarioComercio, String password );
    public Response marcarMensajeLeido( String idMensaje ) throws Exception;
    public void actualizarCapacitacionComercios( String idCapacitacion ) throws Exception;
    
    public void generateTempPassword( UsuariosComercios usuarioComercio );
    public UsuariosComercios findUserTempPassword( String idContact, String tipoIdentificacion, String codUnico, String tempPassword ) throws Exception ;

    public Response validarTerminosCondiciones( String id_contacto ) throws Exception;

    public List<DatosCapacitacion> getCapacitacionComercios( String idCategoria, String path ) throws Exception;
    public List<MiPago>getDatosSolicitudMiPago( String parametros ) throws Exception;
    public List<CategoriasCapacitacion> getTemasCapacitacion() throws Exception;
    public List<Ciudades> getCiudades(int idDepartamento) throws Exception;
    public List<Contactenos> getCanalesOficinas() throws Exception;
    public List<Departamentos> getDepartamentos() throws Exception;
    public List<CategoriasFAQ> getCategoriasFaq() throws Exception;
    public List<TipodocId> getTiposDocumentos() throws Exception;
    public List<Faq> getFaq(int idCategoria) throws Exception;
    public List<TipoPOS> getTiposPos() throws Exception;
    public List<TipificacionDao> getTipoFallas() throws Exception;
    public List<MensajePushDao> getListadoMensajesPush( String token ) throws Exception;
    public List<ConvencionesDireccion> getConvencionesDirecciones( String codigoCiudad ) throws Exception;

    public Response getPantallaTemporal( String imagesPath ) throws Exception;
    public Response getListadoBanner( String imagesPath ) throws Exception;
    public Response getValidarTecnico( String parametros,String imagesPath ) throws Exception;

    public Response adicionarSolicitudMiPago( String parametros ) throws Exception;
    public Response adicionarDatosComercio( String parametros ) throws Exception;
    public Response adicionarDatosProspectos( String parametros ) throws Exception;
    public Response adicionarSolicitudPos( String parametros );
    public Response adicionarSolicitudDatafono( String parametros ) throws Exception;
    public Response adicionarFallas( String parametros ) throws Exception;
    public boolean updateImei( String numeroDocumento, String token ) throws Exception;
    public Response getMensaje( String key ) throws Exception;

    public boolean validarReporteFallasClienteDia( String token, String idTipificacion ) throws Exception;
    
    public String consultarMensaje(String key) throws Exception;
    
    public void cerrarSesion( String token ) throws Exception;
    
    public Response registroAplicacion() throws Exception;

    //Todo de aqui para abajo se borra
    public boolean validarCodigoUnicoPortal( String codigoUnico, String tipoComercio ) throws Exception;
    
    public String[] obtenerLlaves();

    //Genera un token aleatorio cada que se inicia exitosamente la sesion
    public String generarToken(UsuariosComercios usuarioComercio) throws Exception;
    
    public boolean obtenerParametroPruebas(String idContacto, String nit, String codigoUnico);
}