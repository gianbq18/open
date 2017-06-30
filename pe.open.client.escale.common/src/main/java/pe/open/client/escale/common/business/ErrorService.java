package pe.open.client.escale.common.business;

import java.util.Locale;

import pe.open.client.escale.adm.vo.ErrorVO;

public interface ErrorService {

    /**
     * Metodo de la interface ErrorService que obtiene los mensajes de errores.
     *
     * @param e
     *            Recibe como parametro la Excepci&oacute;n de tipo Object.
     * @param locale
     *            Recibe como parametro la localidad actual de tipo Local para
     *            el manejo de la internacionalizaci&oacute;n en los mensajes de
     *            error.
     * @return ErrorVO Retorna un ErrorVO con el mensaje de error traducido
     *         deacuerdo al idioma actual.
     */
    ErrorVO getErrorFor(Object e, Locale locale);

    /**
     * Metodo de la interface ErrorService que obtiene el codigo de error
     * generado.
     *
     * @param e
     *            Recibe como parametro la Excepci&oacute;n de tipo Object.
     * @param locale
     *            Recibe como parametro la localidad actual de tipo Local para
     *            el manejo de la internacionalizaci&oacute;n en los mensajes de
     *            error.
     * @return String Retorna el codigo de error de tipo String producido por la
     *         Excepci&oacute;n.
     */
    String code(Object e, Locale locale);

    /**
     * Metodo de la interface ErrorService que traduce la Excepci&oacute;n
     * generada.
     *
     * @param e
     *            Recibe como parametro la Excepci&oacute;n de tipo Object.
     * @param locale
     *            Recibe como parametro la localidad actual de tipo Local para
     *            el manejo de la internacionalizaci&oacute;n en los mensajes de
     *            error.
     * @return String Retorna el mensaje de error traducido de tipo String
     *         producido segun la localidad actual.
     */
    String translate(Object e, Locale locale);
}
