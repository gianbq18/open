package pe.open.client.escale.common.util;

import java.util.HashMap;
import java.util.Map;

public class PrivilegioUtil {
	
	/** La Constante acciones. */
	private static final Map<String, String> acciones = new HashMap<String, String>();
	
	static {
		acciones.put("ConfiguracionServiceImpl.ejecutarPrueba", "5|CREAR USUARIO|3|ADMINISTRACION|Crear");
		/*
		 * Privilegios Modulo de administracion
		 */
		acciones.put("SolicitudAdministradorServiceImpl.registrarSolicitudDesactivacionAdministrador", "1|REGISTRAR SOLICITUD DESACTIVACION DE ADMINISTRADOR|3|ADMINISTRACION|Solicitar");//ok
		acciones.put("SolicitudEntidadServiceImpl.registrarSolicitudModificacionEntidad", "2|REGISTRAR SOLICITUD DE MODIFICACION DE ENTIDAD|3|ADMINISTRACION|Solicitar");//ok
		acciones.put("SolicitudGeneralServiceImpl.registrarResultadoEvaluacion", "3|REGISTRAR RESULTADO DE EVALUACION DE SOLICTUD|3|ADMINISTRACION|Crear");//ok
		acciones.put("SolicitudGeneralServiceImpl.buscarSolicitudGeneral", "4|BUSCAR SOLICITUD|3|ADMINISTRACION|Buscar");//ok
		acciones.put("SolicitudEntidadServiceImpl.verSolicitudVisualizarEntidad", "5|VER DETALLE DE SOLICITUD DE ENTIDAD|3|ADMINISTRACION|Ver Detalle");//ok
		acciones.put("SolicitudAdministradorServiceImpl.buscarDetalleSolicitudDesactivacion", "6|VER DETALLE DE SOLICITUD DE DESACTIVACION DE ADMINISTRADOR|3|ADMINISTRACION|Ver Detalle");//ok
		acciones.put("SolicitudGeneralServiceImpl.seguimientoSolicitud", "7|REALIZAR SEGUIMIENTO DE SOLICITUD|3|ADMINISTRACION|Buscar");//ok
		
		acciones.put("OrganismoServiceImpl.crearEntidad", "8|CREAR ENTIDAD|3|ADMINISTRACION|Crear");//ok
		acciones.put("OrganismoServiceImpl.procesoModificarEntidad", "9|MODIFICAR ENTIDAD|3|ADMINISTRACION|Modificar");//ok
		acciones.put("OrganismoServiceImpl.buscarEntidad", "10|BUSCAR ENTIDAD|3|ADMINISTRACION|Buscar");//ok
		acciones.put("OrganismoServiceImpl.visualizarEntidad", "11|VER DETALLE DE ENTIDAD|3|ADMINISTRACION|Ver Detalle");//ok		
		acciones.put("OrganismoServiceImpl.buscarHistoricoEntidad", "12|BUSCAR HIST�RICO DE MODIFICACIONES DE ENTIDAD|3|ADMINISTRACION|Buscar");//ok
		acciones.put("OrganismoServiceImpl.generarReporteEntidad", "13|GENERAR REPORTE DE ENTIDAD|3|ADMINISTRACION|Generar Reporte");//ok
		
		acciones.put("UsuarioServiceImpl.administrarUsuarioRegistrar", "14|ADMINISTRAR USUARIO|3|ADMINISTRACION|Administrar");//ok
		acciones.put("UsuarioServiceImpl.buscarUsuario", "15|BUSCAR USUARIO|3|ADMINISTRACION|Buscar");//ok
		acciones.put("UsuarioServiceImpl.obtenerUsuario", "16|VER DETALLE DE USUARIO|3|ADMINISTRACION|Ver Detalle");//ok
		acciones.put("UsuarioServiceImpl.registrarDesactivacionUsuario", "17|REGISTRAR DESACTIVACION DE USUARIO|3|ADMINISTRACION|Crear");
		acciones.put("UsuarioServiceImpl.buscarHistoricoModificacionesUsuario", "18|BUSCAR HIST�RICO DE MODIFICACIONES DE USUARIOS|3|ADMINISTRACION|Buscar");
		acciones.put("UsuarioServiceImpl.generarReporteActividadUsuario", "19|GENERAR REPORTE DE ACTIVIDADES DE USUARIOS DENTRO DEL SISTEMA|3|ADMINISTRACION|Generar");//ok
		
		acciones.put("NotificacionServiceImpl.enviarNotificacionManual", "20|ENVIAR NOTIFICACION MANUAL|3|ADMINISTRACION|Enviar");//ok
		acciones.put("NotificacionServiceImpl.consultaBandejaNotificacion", "21|CONSULTAR BANDEJA DE NOTIFICACIONES|3|ADMINISTRACION|Buscar");//ok
		acciones.put("NotificacionServiceImpl.consultaBandejaNotificacionDetalle", "22|VER DETALLE DE NOTIFICACION|3|ADMINISTRACION|Ver Detalle");//ok
		acciones.put("NotificacionServiceImpl.administrarConfiguracionNotificacion", "23|CONFIGURAR NOTIFICACION|3|ADMINISTRACION|Configurar");//ok
		
		acciones.put("AdministracionServiceImpl.crearParametro", "24|CREAR PARAMETRO|3|ADMINISTRACION|Crear");//ok
		acciones.put("AdministracionServiceImpl.modificarParametro", "25|MODIFICAR PARAMETRO|3|ADMINISTRACION|Modificar");//ok
		acciones.put("AdministracionServiceImpl.buscarParametro", "26|BUSCAR PARAMETRO|3|ADMINISTRACION|Buscar");//ok
		acciones.put("AdministracionServiceImpl.obtenerParametroDetalle", "27|VER DETALLE DE PARAMETRO|3|ADMINISTRACION|Ver Detalle");//ok
		
		acciones.put("AdministracionServiceImpl.crearPerfil", "28|CREAR PERFIL|3|ADMINISTRACION|Crear");//ok
		acciones.put("AdministracionServiceImpl.modificarPerfil", "29|MODIFICAR PERFIL|3|ADMINISTRACION|Modificar");//ok
		acciones.put("AdministracionServiceImpl.buscarPerfil", "30|BUSCAR PERFIL|3|ADMINISTRACION|Buscar");//ok
		acciones.put("AdministracionServiceImpl.obtenerPerfil", "31|VER DETALLE DE PERFIL|3|ADMINISTRACION|Ver Detalle");//ok
		
		acciones.put("AdministracionServiceImpl.crearRol", "32|CREAR ROL|3|ADMINISTRACION|Crear");//ok
		acciones.put("AdministracionServiceImpl.modificarRol", "33|MODIFICAR ROL|3|ADMINISTRACION|Modificar");//ok		
		acciones.put("AdministracionServiceImpl.buscarRol", "34|BUSCAR ROL|3|ADMINISTRACION|Buscar");//ok
		acciones.put("AdministracionServiceImpl.obtenerRol", "35|VER DETALLE DE ROL|3|ADMINISTRACION|Ver Detalle");//ok
		
		acciones.put("AdministracionServiceImpl.buscarPrivilegio", "36|BUSCAR PRIVILEGIO|3|ADMINISTRACION|Buscar");//ok
		
		acciones.put("AdministracionServiceImpl.crearAccesoDirecto", "37|CREAR ACCESO DIRECTO|3|ADMINISTRACION|Crear");//ok
		acciones.put("AdministracionServiceImpl.modificarAccesoDirecto", "38|MODIFICAR ACCESO DIRECTO|3|ADMINISTRACION|Modificar");
		acciones.put("AdministracionServiceImpl.eliminarAccesoDirecto", "39|ELIMINAR ACCESO DIRECTO|3|ADMINISTRACION|Eliminar");
		acciones.put("AdministracionServiceImpl.buscarAccesoDirecto", "40|BUSCAR ACCESO DIRECTO|3|ADMINISTRACION|Buscar");//ok
		acciones.put("AdministracionServiceImpl.obtenerAccesoDirecto", "41|VER DETALLE DE ACCESO DIRECTO|3|ADMINISTRACION|Ver Detalle");//ok
		acciones.put("AdministracionServiceImpl.asignarAccesoDirectoRol", "42|ASIGNAR ACCESOS DIRECTOS A ROL|3|ADMINISTRACION|Asignar");//ok
		
		acciones.put("AdministracionServiceImpl.generarCalendario", "43|GENERAR CALENDARIO|3|ADMINISTRACION|Generar");//ok
		acciones.put("AdministracionServiceImpl.configurarCalendarioUbigeo", "44|CONFIGURAR ASIGNACI�N DE CALENDARIO|3|ADMINISTRACION|Configurar");//ok
		acciones.put("AdministracionServiceImpl.buscarCalendario", "45|BUSCAR CALENDARIO|3|ADMINISTRACION|Buscar");//ok
		acciones.put("AdministracionServiceImpl.buscarCalendarioDTO", "45|BUSCAR CALENDARIO|3|ADMINISTRACION|Buscar");//ok
		acciones.put("AdministracionServiceImpl.buscarConfiguracionCalendarioMes", "46|VISUALIZAR CALENDARIO|3|ADMINISTRACION|Ver Detalle");//ok
		acciones.put("AdministracionServiceImpl.crearAccesoDirectoPortal", "125|CREAR ACCESO DIRECTO PORTAL|2|PORTAL|Crear");//ok
		acciones.put("AdministracionServiceImpl.modificarAccesoDirectoPortal", "126|MODIFICAR ACCESO DIRECTO PORTAL|2|PORTAL|Modificar");//ok
		acciones.put("AdministracionServiceImpl.eliminarAccesoDirectoPortal", "127|ELIMINAR ACCESO DIRECTO PORTAL|2|PORTAL|Eliminar");//ok
		acciones.put("AdministracionServiceImpl.buscarAccesoDirectoPortal", "128|BUSCAR ACCESO DIRECTO PORTAL|2|PORTAL|Buscar");//ok
		acciones.put("AdministracionServiceImpl.obtenerAccesoDirectoPortal", "129|VER DETALLE DE ACCESO DIRECTO PORTAL|2|PORTAL|Ver Detalle");//ok
		
		acciones.put("CategorizacionServiceImpl.buscarCategorizacion","47|BUSCAR CATEGORIZACION|2|PORTAL|Buscar");
		acciones.put("CategorizacionServiceImpl.crearCategorizacion","48|CREAR CATEGORIZACION|2|PORTAL|Crear");
		acciones.put("CategorizacionServiceImpl.modificarCategorizacion","49|MODIFICAR CATEGORIZACION|2|PORTAL|Modificar");
		acciones.put("CategorizacionServiceImpl.eliminarCategorizacion","50|ELIMINAR CATEGORIZACION|2|PORTAL|Eliminar");
		
		acciones.put("NovedadServiceImpl.buscarNovedad","51|BUSCAR NOVEDAD|2|PORTAL|Buscar");
		acciones.put("NovedadServiceImpl.crearNovedad","52|CREAR NOVEDAD|2|PORTAL|Crear");
		acciones.put("NovedadServiceImpl.modificarNovedad","53|MODIFICAR NOVEDAD|2|PORTAL|Modificar");
		acciones.put("NovedadServiceImpl.eliminarNovedad","54|ELIMINAR NOVEDAD|2|PORTAL|Eliminar");
		acciones.put("NovedadServiceImpl.obtenerNovedad","55|VER DETALLE NOVEDAD|2|PORTAL|Ver Detalle");
		acciones.put("NovedadServiceImpl.publicarNovedad","56|PUBLICAR NOVEDAD|2|PORTAL|Publicar");
		acciones.put("NovedadServiceImpl.despublicarNovedad","57|DESPUBLICAR NOVEDAD|2|PORTAL|Despublicar");
		acciones.put("NovedadServiceImpl.destacarNovedad","58|DESTACAR NOVEDAD|2|PORTAL|Destacar");
		
		acciones.put("EstadisticaServiceImpl.buscarEstadistica","59|BUSCAR ESTADISTICA|2|PORTAL|Buscar");
		acciones.put("EstadisticaServiceImpl.crearEstadistica","60|CREAR ESTADISTICA|2|PORTAL|Crear");
		acciones.put("EstadisticaServiceImpl.modificarEstadistica","61|MODIFICAR ESTADISTICA|2|PORTAL|Modificar");
		acciones.put("EstadisticaServiceImpl.eliminarEstadistica","62|ELIMINAR ESTADISTICA|2|PORTAL|Eliminar");
		acciones.put("EstadisticaServiceImpl.publicarEstadistica","64|PUBLICAR ESTADISTICA|2|PORTAL|Publicar");
		acciones.put("EstadisticaServiceImpl.despublicarEstadistica","65|DESPUBLICAR ESTADISTICA|2|PORTAL|Despublicar");
		
		acciones.put("FaqServiceImpl.buscarFaq","66|BUSCAR FAQ|2|PORTAL|Buscar");
		acciones.put("FaqServiceImpl.crearFaq","67|CREAR FAQ|2|PORTAL|Crear");
		acciones.put("FaqServiceImpl.modificarFaq","68|MODIFICAR FAQ|2|PORTAL|Modificar");
		acciones.put("FaqServiceImpl.eliminarFaq","69|ELIMINAR FAQ|2|PORTAL|Eliminar");
		acciones.put("FaqServiceImpl.obtenerFaq","70|VER FAQ|2|PORTAL|Ver Detalle");
		acciones.put("FaqServiceImpl.publicarFaq","71|PUBLICAR FAQ|2|PORTAL|Publicar");
		acciones.put("FaqServiceImpl.despublicarFaq","72|DESPUBLICAR FAQ|2|PORTAL|Despublicar");
		
		acciones.put("DocumentoServiceImpl.buscarDocumento","73|BUSCAR DOCUMENTO|2|PORTAL|Buscar");
		acciones.put("DocumentoServiceImpl.crearDocumento","74|CREAR DOCUMENTO|2|PORTAL|Crear");
		acciones.put("DocumentoServiceImpl.modificarDocumento","75|MODIFICAR DOCUMENTO|2|PORTAL|Modificar");
		acciones.put("DocumentoServiceImpl.eliminarDocumento","76|ELIMINAR DOCUMENTO|2|PORTAL|Eliminar");
		acciones.put("DocumentoServiceImpl.obtenerDocumento","77|VER DETALLE DOCUMENTO|2|PORTAL|Ver Detalle");
		acciones.put("DocumentoServiceImpl.publicarDocumento","78|PUBLICAR DOCUMENTO|2|PORTAL|Publicar");
		acciones.put("DocumentoServiceImpl.despublicarDocumento","79|DESPUBLICAR DOCUMENTO|2|PORTAL|Despublicar");
		acciones.put("DocumentoServiceImpl.destacarDocumento","80|DESTACAR DOCUMENTO|2|PORTAL|Destacar");
		
		acciones.put("BannerServiceImpl.buscarBanner","81|BUSCAR BANNER|2|PORTAL|Buscar");
		acciones.put("BannerServiceImpl.crearBanner","82|CREAR BANNER|2|PORTAL|Crear");
		acciones.put("BannerServiceImpl.modificarBanner","83|MODIFICAR BANNER|2|PORTAL|Modificar");
		acciones.put("BannerServiceImpl.eliminarBanner","84|ELIMINAR BANNER|2|PORTAL|Eliminar");
		acciones.put("BannerServiceImpl.obtenerBanner","85|VER BANNER|2|PORTAL|Ver Detalle");
		acciones.put("BannerServiceImpl.publicarBanner","86|PUBLICAR BANNER|2|PORTAL|Publicar");
		acciones.put("BannerServiceImpl.despublicarBanner","87|DESPUBLICAR BANNER|2|PORTAL|Despublicar");
		acciones.put("BannerServiceImpl.ordenarBanner","88|ORDENAR BANNER|2|PORTAL|Ordenar");
	
		acciones.put("PortalServiceImpl.modificarPiePagina","89|MODIFICAR PIE PAGINA|2|PORTAL|Modificar");
		acciones.put("PortalServiceImpl.registrarPotencialProveedor","90|REGISTRAR POTENCIAL PROVEEDOR|2|PORTAL|Crear");
		
		
	}
	
	/**
	 * Obtiene privilegio.
	 *
	 * @param key el key
	 * @return privilegio
	 */
	public static String getPrivilegio(String key) {
		return acciones.get(key);
	}
}