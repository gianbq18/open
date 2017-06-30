package pe.open.client.escale.adm.vo;

import java.io.Serializable;


public class RecuperarClaveVO implements Serializable {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** El usuario. */
    private String usuario;
    
    /** El correo electronico. */
    private String correoElectronico;
    
    /** El flag capcha. */
    private boolean flagCapcha;

    /**
     * Obtiene usuario.
     *
     * @return usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario.
     *
     * @param usuario el new usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene correo electronico.
     *
     * @return correo electronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Establece el correo electronico.
     *
     * @param correoElectronico el new correo electronico
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Comprueba si es flag capcha.
     *
     * @return true, si es flag capcha
     */
    public boolean isFlagCapcha() {
        return flagCapcha;
    }

    /**
     * Establece el flag capcha.
     *
     * @param flagCapcha el new flag capcha
     */
    public void setFlagCapcha(boolean flagCapcha) {
        this.flagCapcha = flagCapcha;
    }
}
