package pe.open.client.escale.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * La clase FechaUtil permite extender la funcionalidad de la clase DateUtil,
 * con funcionalidad especifica para el manejo de fechas.
 * 
 */

public final class FechaUtil {
	
	/** La Constante SEGUNDO. */
	private static final int SEGUNDO = 59;

	/** La Constante MINUTO. */
	private static final int MINUTO = 59;

	/** La Constante HORA. */
	private static final int HORA = 23;
	
	/** La Constante MILISECOND. */
	private static final int MILISEGUNDO = 999;

    /** La Constante DATE_SHORT. */
    private static final SimpleDateFormat DATE_SHORT = new SimpleDateFormat("dd/MM/yyyy");
    
    /** La Constante DATE_LONG. */
    private static final SimpleDateFormat DATE_LONG = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    /**
     * Instantiates a new fecha util.
     */
    private FechaUtil() {
    	
    }

    /**
     * Metodo que permite obtener el formato completo de una fecha.
     *
     * @param fecha
     *            Recibe un Date con la fecha.
     * @return Retorna un String con la fecha en el formato establecido.
     */
    public static String obtenerFechaFormatoCompleto(Date fecha) {
        return DATE_LONG.format(fecha);
    }

    /**
     * Metodo que permite obtener un formato personalizado para una fecha
     * especifica.
     *
     * @param fecha
     *            Recibe un Date.
     * @param formato
     *            Recibe un String con el formato a aplicarse a la fecha.
     * @return Retorna un String con el formato personalizado.
     */
    public static String obtenerFechaFormatoPersonalizado(Date fecha,
            String formato) {
        return new SimpleDateFormat(formato, ResourceUtil.obtenerLocaleSession()).format(fecha);
    }

    /**
     * Metodo que permite obtener un formato simple para una fecha especifica.
     *
     * @param fecha
     *            Recibe un Date.
     * @return Retorna un String con el formato simple.
     */
    public static String obtenerFechaFormatoSimple(Date fecha) {
        return DATE_SHORT.format(fecha);
    }

    /**
     * Metodo que permite obtener un formato completo para una fecha especifica.
     *
     * @param fecha
     *            Recibe un String
     * @return Retorna un Date
     * @throws ParseException
     *             Excepci&oacute;n en caso de que no se realize el parseo.
     */
    public static Date obtenerFechaFormatoCompleto(String fecha)
            throws ParseException {
        return DATE_LONG.parse(fecha);
    }

    /**
     * Metodo para obtener la fecha a partir de un String.
     *
     * @param fecha
     *            Recibe un String con la fecha.
     * @return Retorna un Date con la fecha.
     * @throws ParseException
     *             Excepci&oacute;n en caso de que no se realize el parseo.
     */
    public static Date obtenerFecha(String fecha) throws ParseException {
        return DATE_SHORT.parse(fecha);
    }

    /**
     * Metodo para obtener la fecha a partir de la fecha en formato String y el
     * formato.
     *
     * @param fecha
     *            Recibe un String con la fecha.
     * @param formato
     *            Recibe un String con el formato deseado.
     * @return Retorna un Date con la fecha convertida.
     * @throws ParseException
     *             Excepci&oacute;n en caso de que no se realize el parseo.
     */
    public static Date obtenerFechaFormatoPersonalizado(String fecha,
            String formato) throws ParseException {
        return new SimpleDateFormat(formato).parse(fecha);
    }

    /**
     * Metodo que permite obtener la fecha actual.
     *
     * @return Retorna la fecha actual.
     */
    public static Date obtenerFechaActual() {
        return new Date();
    }

    /**
     * Metodo que permite convertir una fecha de tipo Date a tipo Calendar.
     *
     * @param date
     *            Recibe un Date con la fecha.
     * @return Retorna un Calendar con la fecha.
     */
    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
    /**
     * **
     * Devuelve el n&uacute;mero de d&iacute;as de diferencia entre 2 fechas.
     *
     * @param date1 the date1
     * @param date2 the date2
     * @return Retorna un int con los d&iacute;as de diferencia
     */
    public static int restarFechas(Date date1, Date date2) {
            int respuesta;
            GregorianCalendar gDate1 = new GregorianCalendar(2000,01,01);
            gDate1.setTime(date1);
    GregorianCalendar gDate2 = new GregorianCalendar(2000,01,01);
    gDate2.setTime(date2);

    if (gDate1.get(Calendar.YEAR) == gDate2.get(Calendar.YEAR)) {
        respuesta = gDate2.get(Calendar.DAY_OF_YEAR) - gDate1.get(Calendar.DAY_OF_YEAR);
    } else {
        /* SI ESTAMOS EN DISTINTO ANYO COMPROBAMOS QUE EL ANYO DEL DATEINI NO SEA BISIESTO
         * SI ES BISIESTO SON 366 DIAS EL ANYO
         * SINO SON 365
         */
        int diasAnyo = gDate1.isLeapYear(gDate1.get(Calendar.YEAR)) ? 366 : 365;

        /* CALCULAMOS EL RANGO DE ANYOS */
        int rangoAnyos = gDate2.get(Calendar.YEAR) - gDate1.get(Calendar.YEAR);

        /* CALCULAMOS EL RANGO DE DIAS QUE HAY */
        int rango = (rangoAnyos * diasAnyo) + (gDate2.get(Calendar.DAY_OF_YEAR) - gDate1.get(Calendar.DAY_OF_YEAR));

        respuesta = rango;
    }
    return respuesta;
    }

    /**
     * **
     * Devuelve una fecha a la que se le suma un n&uacute;mero de entero de d&iacute;as.
     *
     * @param date the date
     * @param sumar the sumar
     * @return Retorna un Date con el n&uacute;mero de d&iacute;as que se le ha agregado
     */
    public static Date sumarDias(Date date, int sumar) {
            GregorianCalendar gDate1 = new GregorianCalendar(2000,01,01);
            gDate1.setTime(date);
            gDate1.add(Calendar.DATE, sumar);
            return gDate1.getTime();
    }

    /**
     * Sumar horas calendar.
     *
     * @param date the date
     * @return the date
     */
    public static Date sumarHorasCalendar(Date date) {
            if (date == null) {
                    return null;
            } else {
                    Calendar cale = Calendar.getInstance();
                    cale.setTime(date);
                    cale.add(Calendar.HOUR, HORA);
                    cale.add(Calendar.MINUTE, MINUTO);
                    cale.add(Calendar.SECOND, SEGUNDO);
                    cale.add(Calendar.MILLISECOND, MILISEGUNDO);
                    return cale.getTime();
            }
    }

    /**
     * Mes.
     *
     * @param date el date
     * @return the integer
     */
    public static Integer mes(Date date) {
            if (date == null) {
                    return null;
            } else {
                    Calendar cale = Calendar.getInstance();
                    cale.setTime(date);
                    return cale.get(Calendar.MONTH) + 1;
            }
    }

    /**
     * Anio.
     *
     * @param date el date
     * @return the integer
     */
    public static Integer anio(Date date) {
            if (date == null) {
                    return null;
            } else {
                    Calendar cale = Calendar.getInstance();
                    cale.setTime(date);
                    return cale.get(Calendar.YEAR);
            }
    }

    /**
     * Dia.
     *
     * @param date el date
     * @return the integer
     */
    public static Integer dia(Date date) {
            if (date == null) {
                    return null;
            } else {
                    Calendar cale = Calendar.getInstance();
                    cale.setTime(date);
                    return cale.get(Calendar.DATE);
            }
    }

    /**
     * Inicializa la Hora de la fecha en 0 horas 0 minutos 0 segundos y 0 milisegundos.
     *
     * @param date el date
     * @return the date
     */
    public static Date iniciarHoraCero(Date date) {
            if (date == null) {
                    return null;
            } else {
                    Calendar cale = Calendar.getInstance();
                    cale.setTime(date);

                    GregorianCalendar calendar = new GregorianCalendar(cale.get(Calendar.YEAR), cale.get(Calendar.MONTH), cale.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                    return calendar.getTime();
            }
    }

    //aumentando desde seleccion
    /**
 * Obtiene diff days.
 *
 * @param startDate el start date
 * @param endDate el end date
 * @return diff days
 */

    public static int getDiffDays(Date startDate, Date endDate) {
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            int factor = 1;
            Calendar startCalAux = Calendar.getInstance();
            Calendar endCalAux = Calendar.getInstance();

            if (startDate.after(endDate)) {
                    factor = -1;
                    startCal.setTime(endDate);
                    endCal.setTime(startDate);
            } else {
                    startCal.setTime(startDate);
                    endCal.setTime(endDate);
            }
            // se evita hacer diferencias con fechas que incluyan horas
            startCalAux.set(startCal.get(Calendar.YEAR),
                            startCal.get(Calendar.MONTH),
                            startCal.get(Calendar.DAY_OF_MONTH));
            endCalAux.set(endCal.get(Calendar.YEAR), endCal.get(Calendar.MONTH),
                            endCal.get(Calendar.DAY_OF_MONTH));

            long dif = endCalAux.getTimeInMillis() - startCalAux.getTimeInMillis();
            double d = dif / (1000 * 60 * 60 * 24); // conversion miliseg a dias
            d *= factor;
            return (int) d;
    }


    /**
     * Obtiene diff hours.
     *
     * @param startDate el start date
     * @param endDate el end date
     * @return diff hours
     */
    public static int getDiffHours(Date startDate, Date endDate) {
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            Calendar startHour = Calendar.getInstance();
            Calendar endHour = Calendar.getInstance();
            // obtener las horas
            startHour.setTime(startDate);
            endHour.setTime(endDate);
            // establecer las horas en el calendario antes de efectuar la
            // comparacion
            startCal.setTime(new Date());
            endCal.setTime(new Date());
            startCal.set(startCal.get(Calendar.YEAR), startCal.get(Calendar.MONTH),
                            startCal.get(Calendar.DAY_OF_MONTH),
                            startHour.get(Calendar.HOUR_OF_DAY),
                            startHour.get(Calendar.MINUTE), startHour.get(Calendar.SECOND));
            endCal.set(endCal.get(Calendar.YEAR), endCal.get(Calendar.MONTH),
                            endCal.get(Calendar.DAY_OF_MONTH),
                            endHour.get(Calendar.HOUR_OF_DAY),
                            endHour.get(Calendar.MINUTE), endHour.get(Calendar.SECOND));
            // diferencia de horas
            long dif = startCal.getTimeInMillis() - endCal.getTimeInMillis();
            double d = dif / (1000); // conversion miliseg a segundos
            return (int) d;
    }

/**
 * Sumar fechas dias.
 *
 * @param fch el fch
 * @param dias el dias
 * @return the java.util. date
 */
public static java.util.Date sumarFechasDias(java.util.Date fch, int dias) {
    Calendar cal = new GregorianCalendar();
    cal.setTimeInMillis(fch.getTime());
    cal.add(Calendar.DATE, dias);
    return new java.sql.Date(cal.getTimeInMillis());
}



/**
     * Obtener hora sistema.
     *
     * @param language el language
     * @param country el country
     * @return the string
     */
    public static String obtenerHoraSistema(String language, String country) {
            Locale loc = new Locale(language, country);
            int hora, minutos;
            Calendar calendario = Calendar.getInstance(loc);
            hora = calendario.get(Calendar.HOUR_OF_DAY);
            minutos = calendario.get(Calendar.MINUTE);
            return hora + ":" + minutos;

    }

    /**
     * Obtener dia del a�o.
     *d
     * @param date el date
     * @return the integer
     */
    public static Integer obtenerDiaDelAnio(Date date) {
            Calendar cale = Calendar.getInstance();
            cale.setTime(date);
            return cale.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * **
     * Devuelve una fecha a la que se le suma un n&uacute;mero de entero de meses.
     *
     * @param date the date
     * @param sumar the sumar
     * @return Retorna un Date con el n&uacute;mero de meses que se le ha agregado
     */
    public static Date sumarMes(Date date, int sumar) {
            GregorianCalendar gDate1 = new GregorianCalendar(2000,01,01);
            gDate1.setTime(date);
            gDate1.add(Calendar.MONTH, sumar);
            return gDate1.getTime();
    }
	
	
	
}
