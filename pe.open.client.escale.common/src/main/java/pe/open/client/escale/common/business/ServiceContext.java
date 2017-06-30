package pe.open.client.escale.common.business;

import java.io.Serializable;
import java.util.Locale;

import pe.open.client.escale.adm.vo.UsuarioSessionVO;

public class ServiceContext implements Serializable {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** La Constante contexts. */
    private static final ThreadLocal<ServiceContext> contexts = new ThreadLocal<ServiceContext>();
    
    /** El objeto locale. */
    private Locale locale = Locale.getDefault();
    
    /** El objeto usuario session vo. */
    private UsuarioSessionVO usuarioSessionVO;

    /**
     * Metodo para asignar el ServiceContext.
     *
     * @param context
     *            Recibe un ServiceContext como parametro.
     */
    public static void setCurrent(ServiceContext context) {
        contexts.set(context);
    }

    /**
     * Metodo que obtiene el ServiceContext.
     *
     * @return ServiceContext Retorna el ServiceContext con los parametros
     *         inicializados.
     */
    public static ServiceContext getCurrent() {
        return contexts.get();
    }

    /**
     * Metodo que obtiene el la localidad.
     *
     * @return Locale Retorna la localidad actual.
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Metodo para asignar la localidad.
     *
     * @param locale
     *            Recibe un Locale o localidad asignada.
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Metodo que obtiene la informaci&oacuten actual del usuario inici&oacute;
     * la sessi&oacute;n.
     *
     * @return UsuarioSessionVO
     *            Retorna un UsuarioSessionVO con la informaci&oacuten actual
     *            del usuario inici&oacute; la sessi&oacute;n.
     */
    public UsuarioSessionVO getUsuarioSessionVO() {
        return usuarioSessionVO;
    }

    /**
     * Metodo para asignar la informaci&oacuten del usuario que inici&oacute; la sessi&oacute;n.
     *
     * @param usuarioSessionVO
     *            Recibe un UsuarioSessionVO con la informaci&oacuten actual del
     *            usuario inici&oacute; la sessi&oacute;n.
     */
    public void setUsuarioSessionVO(UsuarioSessionVO usuarioSessionVO) {
        this.usuarioSessionVO = usuarioSessionVO;
    }
}
