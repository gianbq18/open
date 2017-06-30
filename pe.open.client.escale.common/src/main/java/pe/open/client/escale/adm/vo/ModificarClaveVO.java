package pe.open.client.escale.adm.vo;

import java.io.Serializable;

public class ModificarClaveVO implements Serializable {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** El coid. */
    private String coid;
    
    /** La contrase�a anterior. */
    private String contrasenaAnterior;
    
    /** La contrase�a nueva. */
    private String contrasenaNueva;
    
    /** La contrase�a nueva dos. */
    private String contrasenaNuevaDos;
    
    /** El usuario correo. */
    private String usuarioCorreo;

    /**
     * Obtiene coid.
     *
     * @return coid
     */
    public String getCoid() {
        return coid;
    }

    /**
     * Establece el coid.
     *
     * @param coid el new coid
     */
    public void setCoid(String coid) {
        this.coid = coid;
    }

    /**
     * Obtiene contrasena anterior.
     *
     * @return contrasena anterior
     */
    public String getContrasenaAnterior() {
        return contrasenaAnterior;
    }

    /**
     * Establece el contrasena anterior.
     *
     * @param contrasenaAnterior el new contrasena anterior
     */
    public void setContrasenaAnterior(String contrasenaAnterior) {
        this.contrasenaAnterior = contrasenaAnterior;
    }

    /**
     * Obtiene contrasena nueva.
     *
     * @return contrasena nueva
     */
    public String getContrasenaNueva() {
        return contrasenaNueva;
    }

    /**
     * Establece el contrasena nueva.
     *
     * @param contrasenaNueva el new contrasena nueva
     */
    public void setContrasenaNueva(String contrasenaNueva) {
        this.contrasenaNueva = contrasenaNueva;
    }

    /**
     * Obtiene contrasena nueva dos.
     *
     * @return contrasena nueva dos
     */
    public String getContrasenaNuevaDos() {
        return contrasenaNuevaDos;
    }

    /**
     * Establece el contrasena nueva dos.
     *
     * @param contrasenaNuevaDos el new contrasena nueva dos
     */
    public void setContrasenaNuevaDos(String contrasenaNuevaDos) {
        this.contrasenaNuevaDos = contrasenaNuevaDos;
    }
    
    /**
     * Obtiene usuario correo.
     *
     * @return usuario correo
     */
    public String getUsuarioCorreo() {
		return usuarioCorreo;
	}
    
    /**
     * Establece el usuario correo.
     *
     * @param usuarioCorreo el new usuario correo
     */
	public void setUsuarioCorreo(String usuarioCorreo) {
		this.usuarioCorreo = usuarioCorreo;
	}
	
}
