package pe.open.client.escale.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * La clase FormatterUtil permite aplicar formato a numeros y fechas.
 * */
public class FormatterUtil {

    /**
     * Metodo que permite convertir un String a un Long.
     *
     * @param str
     *            Recibe un String con el valor que se requiere convertir a
     *            Long.
     * @return Retorna un Long con el valor convertido.
     * @throws NumberFormatException
     *             Excepci&oacute;n en caso la cadena no se pueda convertir a
     *             Long.
     */
    public static Long toLong(String str) throws NumberFormatException {
        str = StringUtils.trimToNull(str);
        return (str == null) ? null : Long.valueOf(str);
    }

    /**
     * Metodo que permite converir un String a un int.
     *
     * @param str
     *            Recibe un String con el valor que se requiere convertir a int.
     * @return Retorna un int con el valor convertido.
     * @throws NumberFormatException
     *             Excepci&oacute;n en caso la cadena no se pueda convertir a
     *             int.
     */
    public static int toIntPrimivite(String str) throws NumberFormatException {
        Long intLong = toLong(str);
        return (intLong != null) ? intLong.intValue() : null;
    }

    /**
     * Metodo que permite convertir un String a un Double.
     *
     * @param str
     *            Recibe un String con el valor que se requiere convertir a
     *            Double.
     * @return Retorna un Double con el valor convertido.
     * @throws NumberFormatException
     *             Excepci&oacute;n en caso la cadena no se pueda convertir a
     *             Double.
     */
    public static Double toDouble(String str) throws NumberFormatException {
        str = StringUtils.trimToNull(str);
        return (str == null) ? null : Double.valueOf(str);
    }

    /**
     * Metodo que permite convertir un String a un double.
     *
     * @param str
     *            Recibe un String con el valor que se requiere convertir a
     *            double.
     * @return Retorna un double con el valor convertido.
     * @throws NumberFormatException
     *             Excepci&oacute;n en caso la cadena no se pueda convertir a
     *             double.
     */
    public static double toDoublePrimitive(String str)
            throws NumberFormatException {
        Double doubleDouble = toDouble(str);
        return (doubleDouble == null) ? null : doubleDouble.doubleValue();
    }

    /**
     * Metodo que permite convertir un String a BigDecimal.
     *
     * @param str
     *            Recibe un String con el valor que se requiere convertir a
     *            BigDecimal.
     * @return Retorna un BigDecimal con el valor convertido.
     * @throws NumberFormatException
     *             Excepci&oacute;n en caso la cadena no se pueda convertir a
     *             BigDecimal.
     */
    public static BigDecimal toBigDecimal(String str)
            throws NumberFormatException {
        str = StringUtils.trimToNull(str);
        return (str == null) ? null : new BigDecimal(str);
    }

    /**
     * Metodo que permite convertir un String a BigInteger.
     *
     * @param str
     *            Recibe un String con el valor que se requiere convertir a
     *            BigInteger.
     * @return Retorna un BigInteger con el valor convertido.
     * @throws NumberFormatException
     *             Excepci&oacute;n en caso la cadena no se pueda convertir a
     *             BigInteger.
     */
    public static BigInteger toBigInteger(String str)
            throws NumberFormatException {
        str = StringUtils.trimToNull(str);
        return (str != null) ? new BigInteger(str) : null;
    }

    /**
     * Metodo que permite eliminar los espacios en blanco de un valor de tipo
     * String.
     *
     * @param str
     *            Recibe un String con el valor a quitar los espacios en blanco.
     * @return Retorna un String con el valor una vez quitado los espacios en
     *         blanco.
     */
    public static String toString(String str) {
        return StringUtils.trimToNull(str);
    }

    /**
     * Metodo que permite converir una fecha de acuerdo a un formato
     * establecido.
     *
     * @param str
     *            Recibe la fecha en formato textual.
     * @param format
     *            Recibe el formato a aplicarse a la fecha.
     * @return Retorna un Date con la fecha aplicada al formato establecido.
     * @throws Exception
     *             Excepci&oacute;n en caso de que no se realize la
     *             conversi&oacute;n de formato.
     */
    public static Date toDate(String str, String format) throws Exception {
        str = StringUtils.trimToNull(str);
        return (str != null) ? FechaUtil.obtenerFechaFormatoPersonalizado(str,
                format) : null;
    }

    /**
     * Metodo que permite converir una fecha en el formato establecido.
     *
     * @param str
     *            la fecha en formato dd/MM/yyyy
     * @return Retorna un Date con la fecha aplicada al formato establecido.
     * @throws Exception
     *             Excepci&oacute;n general en caso no se realize la
     *             conversi&oacute;n.
     */
    public static Date toDate(String str) throws Exception {
        str = StringUtils.trimToNull(str);
        return (str == null) ? null : FechaUtil.obtenerFecha(str);
    }
}
