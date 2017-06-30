package pe.open.client.escale.adm.business.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import pe.open.client.escale.adm.vo.ErrorVO;
import pe.open.client.escale.common.business.ErrorService;
import pe.open.client.escale.common.exception.BusinessException;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.MessageResourceUtil;


public class ErrorServiceImpl implements Serializable, ErrorService {
	
	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 2064805397467294447L;
	
	/** El log. */
	private static LogUtil log = new LogUtil(ErrorService.class.getName());

    /**
     * Metodo de la implementaci&oacute;n ErrorServiceImpl que obtiene los
     * mensajes de errores.
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
    public ErrorVO getErrorFor(Object e, Locale locale) {
        ErrorVO error = new ErrorVO();
        error.setCode(code(e, locale));
        error.setDate(new Date());
        error.setDefaultMessage(translate(e, locale));
        return error;
    }

    /**
     * Metodo de la implementaci&oacute;n ErrorServiceImpl que obtiene el codigo
     * de error generado.
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
    public String code(Object e, Locale locale) {
    	BusinessException businessException = null;
        String errorCode = null;
        String key = null;

        if (e != null) {
            businessException = castToBusinessException(e);
            key = businessException != null ? businessException.getKey() + ".code" : e.getClass().getName()
                    + ".code";
            errorCode = MessageResourceUtil.getError(locale, key);
        } else {
            errorCode = "{null}";
        }

        return errorCode;
    }

    /**
     * Metodo de la implementaci&oacute;n ErrorServiceImpl que traduce la
     * Excepci&oacute;n generada.
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
    public String translate(Object e, Locale locale) {
    	BusinessException businessException = null;
        String message = null;
        String key = null;
        if (e != null) {
            businessException = castToBusinessException(e);
            key = businessException != null ? businessException.getKey() : e.getClass().getName();

            if (businessException != null) {
            	if (businessException.getParameters() != null) {
                    message = MessageResourceUtil.getError(locale, key,
                            businessException.getParameters());
                } else if (businessException.getParameter() != null) {
                    message = MessageResourceUtil.getError(locale, key,
                            businessException.getParameter());
                }  else {
                    message = MessageResourceUtil.getError(locale, key);
                }
            } else {
                message = MessageResourceUtil.getError(locale, key);
            }
        } else {
            message = "{null}";
        }
        return message;
    }

    /**
     * Metodo castToBaseException se encarga de instanciar un objeto a
     * BaseException.
     *
     * @param object Recibe como parametro una instancia de Excepci&oacute;n de
     * tipo BaseException.
     * @return the business exception
     */
    private BusinessException castToBusinessException(Object object) {
    	BusinessException be = null;
        try {
            be = (BusinessException) object;
        } catch (Exception e) {
        	log.warn(e);
        }
        return be;
    }
}
