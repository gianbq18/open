package pe.open.client.escale.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtil {

	/** El log. */
	private static LogUtil log = new LogUtil(DateUtil.class.getName());

	/** La Constante DD_MM_YYYY_HH_MM_SS. */
	private static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";

	/** La Constante MILI_SEGUNDO. */
	private static final int MILI_SEGUNDO = 999;

	/** La Constante SEGUNDO. */
	private static final int SEGUNDO = 59;

	/** La Constante MINUTO. */
	private static final int MINUTO = 59;

	/** La Constante HORA. */
	private static final int HORA = 23;

	/** La Constante YEAR. */
	public static final int YEAR = 0;
	
	/** La Constante MONTH. */
	public static final int MONTH = 1;

	/**
	 * Metodo que brinda un formato de fechas considerando hora, minuto,
	 * segundo, y milisegundo.
	 * 
	 * @param date
	 *            Recibe un Date con la fecha que se le aplicar&aacute; formato.
	 * @return Retorna un Date con la fecha con el formato aplicado.
	 */
	public static Date dateFrom(Date date) {
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		calen.set(Calendar.HOUR_OF_DAY, 0);
		calen.set(Calendar.MINUTE, 0);
		calen.set(Calendar.SECOND, 0);
		calen.set(Calendar.MILLISECOND, 0);

		return calen.getTime();
	}

	/**
	 * Metodo que brinda un formato de fechas considerando hora, minuto,
	 * segundo, y milisegundo.
	 * 
	 * @param date
	 *            Recibe un Date con la fecha que se le aplicar&aacute; formato.
	 * @return Retorna un Date con la fecha con el formato aplicado.
	 */
	public static Date dateTo(Date date) {
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		calen.set(Calendar.HOUR_OF_DAY, HORA);
		calen.set(Calendar.MINUTE, MINUTO);
		calen.set(Calendar.SECOND, SEGUNDO);
		calen.set(Calendar.MILLISECOND, MILI_SEGUNDO);

		return calen.getTime();
	}

	/**
	 * Metodo que permite validar si la fecha se encuentra en el periodo de fin
	 * de semana.
	 * 
	 * @param c
	 *            Recibe un Calendar.
	 * @return Retorna un valor de tipo boolean, indicando true si la fecha se
	 *         encuentra en el periodo de fin de semana, y false en caso
	 *         contrario.
	 */
	public static boolean isWeekEnd(Calendar c) {
		return (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c
				.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
	}

	/**
	 * Metodo que permite validar si la fecha se encuentra en el periodo de fin
	 * de semana.
	 * 
	 * @param date
	 *            Recibe un Date.
	 * @return Retorna un valor de tipo boolean, indicando true si la fecha se
	 *         encuentra en el periodo de fin de semana, y false en caso
	 *         contrario.
	 */
	public static boolean isWeekEnd(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return isWeekEnd(c);
	}

	/**
	 * Metodo que permite aplicar un formato de fecha de tipo Calendar a fecha
	 * textual en idioma espa&ntilde;ol.
	 * 
	 * @param c
	 *            Recibe un Calendar.
	 * @return Retorna la fecha en formato textual en idioma espa&ntilde;ol.
	 */
	public static String dayToSpanih(Calendar c) {
		String strDay = "";
		switch (c.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.MONDAY:
			strDay = "LUNES";
			break;
		case Calendar.TUESDAY:
			strDay = "MARTES";
			break;
		case Calendar.WEDNESDAY:
			strDay = "MIERCOLES";
			break;
		case Calendar.THURSDAY:
			strDay = "JUEVES";
			break;
		case Calendar.FRIDAY:
			strDay = "VIERNES";
			break;
		case Calendar.SATURDAY:
			strDay = "SABADO";
			break;
		case Calendar.SUNDAY:
			strDay = "DOMINGO";
			break;
		}
		return strDay;
	}

	/**
	 * Metodo que permite aplicar un formato de fecha de tipo Calendar a fecha
	 * textual en idioma ingles.
	 * 
	 * @param c
	 *            Recibe un Calendar.
	 * @return Retorna la fecha en formato textual en idioma ingles.
	 */
	public static String dayToEnglish(Calendar c) {
		String strDay = "";
		switch (c.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.MONDAY:
			strDay = "MONDAY";
			break;
		case Calendar.TUESDAY:
			strDay = "TUESDAY";
			break;
		case Calendar.WEDNESDAY:
			strDay = "WEDNESDAY";
			break;
		case Calendar.THURSDAY:
			strDay = "THURSDAY";
			break;
		case Calendar.FRIDAY:
			strDay = "FRIDAY";
			break;
		case Calendar.SATURDAY:
			strDay = "SATURDAY";
			break;
		case Calendar.SUNDAY:
			strDay = "SUNDAY";
			break;
		}
		return strDay;
	}

	/**
	 * Metodo que permite aplicar un formato de fecha de tipo Calendar a fecha
	 * textual en idioma frances.
	 * 
	 * @param c
	 *            Recibe un Calendar.
	 * @return Retorna la fecha en formato textual en idioma frances.
	 */
	public static String dayToFrench(Calendar c) {
		String strDay = "";
		switch (c.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.MONDAY:
			strDay = "LUNDI";
			break;
		case Calendar.TUESDAY:
			strDay = "MARDI";
			break;
		case Calendar.WEDNESDAY:
			strDay = "MERCREDI";
			break;
		case Calendar.THURSDAY:
			strDay = "JEUDI";
			break;
		case Calendar.FRIDAY:
			strDay = "VENDREDI";
			break;
		case Calendar.SATURDAY:
			strDay = "SAMEDI";
			break;
		case Calendar.SUNDAY:
			strDay = "DIMANCHE";
			break;
		}
		return strDay;
	}

	/**
	 * Metodo que permite aplicar un formato de fecha de tipo Date a fecha
	 * textual en idioma espa&ntilde;ol.
	 * 
	 * @param date
	 *            Recibe un Date.
	 * @return Retorna la fecha en formato textual en idioma espa&ntilde;ol.
	 */
	public static String dayToSpanih(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return dayToSpanih(c);
	}

	/**
	 * Metodo que permite aplicar un formato de fecha de tipo Date a fecha
	 * textual en idioma ingles.
	 * 
	 * @param date
	 *            Recibe un Date.
	 * @return Retorna la fecha en formato textual en idioma ingles.
	 */
	public static String dayToEnglish(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return dayToEnglish(c);
	}

	/**
	 * Metodo que permite aplicar un formato de fecha de tipo Date a fecha
	 * textual en idioma frances.
	 * 
	 * @param date
	 *            Recibe un Date.
	 * @return Retorna la fecha en formato textual en idioma frances.
	 */
	public static String dayToFrench(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return dayToFrench(c);
	}

	/**
	 * Metodo que permite generar una fecha de tipo Calendar a partir del a&ntilde;o,
	 * mes, dia.
	 * 
	 * @param year
	 *            Recibe el a&ntilde;o.
	 * @param month
	 *            Recibe el mes.
	 * @param day
	 *            Recibe el dia.
	 * @return Retorna la fecha con los valores asignados.
	 */
	public static Calendar getCalendar(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}

	/**
	 * Metodo que permite generar una fecha de tipo Date a partir del a&ntilde;o, mes,
	 * dia.
	 * 
	 * @param year
	 *            Recibe el a&ntilde;o.
	 * @param month
	 *            Recibe el mes.
	 * @param day
	 *            Recibe el dia.
	 * @return Retorna la fecha con los valores asignados.
	 */
	public static Date getDate(int year, int month, int day) {
		return getCalendar(year, month, day).getTime();
	}

	/**
	 * Metodo que permite incrementar los dias de acuerdo a una fecha
	 * especifica.
	 * 
	 * @param date
	 *            Recibe la fecha base.
	 * @param amount
	 *            Recibe la cantidad de dias a incrementar.
	 * @return Retorna la fecha con los dias incrementados.
	 */
	public static Date add(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, amount);
		return c.getTime();
	}

	/**
	 * Metodo que permite aplicar el formato dd/MM/yyyy HH:mm:ss a una
	 * determinada fecha.
	 * 
	 * @param c
	 *            Recibe la fecha de tipo Calendar.
	 * @return Retorna la fecha en el formato establecido.
	 */
	public static String toString(Calendar c) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				DD_MM_YYYY_HH_MM_SS);
		return dateFormat.format(c.getTime());
	}

	/**
	 * Metodo que permite convertir la fecha en su formato original a tipo
	 * String.
	 * 
	 * @param date
	 *            Recibe la fecha de tipo Date.
	 * @return Retorna un String con la fecha establecida.
	 */
	public static String toString(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return toString(c);
	}

	/**
	 * Metodo que permite imprimir en consola la fecha.
	 * 
	 * @param date
	 *            Recibe la fecha de tipo Date.
	 */
	public static void imprimir(Date date) {
		System.out.println(toString(date));
	}

	/**
	 * Metodo que permite encontrar la diferencia en dias entre dos fechas
	 * establecidas.
	 * 
	 * @param startDate
	 *            Recibe un Date con la fecha de inicio.
	 * @param endDate
	 *            Recibe un Date con la fecha de Fin.
	 * @return Retorna un entero de tipo int, con la cantidad de dias de diferencia.
	 */
	public static int getDiffDays(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		int factor = 1;

		if (startDate.after(endDate)) {
			factor = -1;
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		} else {
			startCal.setTime(startDate);
			endCal.setTime(endDate);
		}
		long dif = endCal.getTimeInMillis() - startCal.getTimeInMillis();
		double d = dif / (1000 * 60 * 60 * 24); // conversion miliseg a dias
		d *= factor;
		return (int) d;
	}

	
	/**
	 * Gets the date instance of.
	 *
	 * @param value the value
	 * @return the date instance of
	 */
	public static Date getDateInstanceOf(Object value) {   
		Date fecha = null;
		if (value != null) {
			if (value instanceof Date) {
	    		fecha = (Date)value;
		    } else {
		    	if (value instanceof Timestamp) {
		    		fecha = new Date(((Timestamp)value).getTime());
		    	} else {
		    		if (value instanceof Calendar) {		
		    			fecha = new Date(((Calendar)value).getTime().getTime());
			        } else {		
			        	if (value instanceof GregorianCalendar) {
			        		fecha = new Date(((GregorianCalendar)value).getTime().getTime());
			        	}
			        }
		    	}
	        }
		}
		return fecha;
	}	
	
	/**
	 * Metodo principal que realiza las pruebas a la clase.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		log.info("inicio...");
		Calendar c1 = Calendar.getInstance();
		imprimir(c1.getTime());
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.DATE, 170);
		imprimir(c2.getTime());
		if (c2.after(c1)) {
			log.info("plazo vencido");
		}
	}	

}
