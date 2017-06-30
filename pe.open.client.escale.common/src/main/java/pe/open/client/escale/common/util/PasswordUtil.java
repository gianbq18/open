package pe.open.client.escale.common.util;

import org.apache.commons.lang.StringUtils;

public class PasswordUtil {
	
	/** Los NUMEROS. */
	private static String NUMEROS = "0123456789";
	
	/** La MAYUSCULAS. */
	private static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/** La MINUSCULAS. */
	private static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
	
	/** Los ESPECIALES. */
	private static String ESPECIALES = "";
	
	/** La Constante UPPER. */
	private static final int UPPER = 1;
	
	/** La Constante LOWER. */
	private static final int LOWER = 2;
	
	/** La Constante DIGIT. */
	private static final int DIGIT = 3;
	
	/**
	 * Genera una contraseña con un tamaño de caracteres seg&uacute;n length.
	 *
	 * @param length n&uacute;mero de caracteres del password
	 * @return String contraseña generada
	 */
	public static String generatePassword(int length) {
		StringBuffer sb = new StringBuffer();
		sb.append(getCapitalRandom());
		sb.append(getMinuRandom());
		sb.append(getNumberRandom());
		sb.append(getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS + ESPECIALES, length - 3));
		return sb.toString();
	}
 
	/**
	 * Gets the password.
	 *
	 * @param key the key
	 * @param length the length
	 * @return the password
	 */
	private static String getPassword(String key, int length) {
		StringBuffer pswd = new StringBuffer();
		for (int i = 0; i < length; i++) {
			pswd.append(key.charAt((int)(Math.random() * key.length())));
		}
 		return pswd.toString();
	}
	
	/**
	 * Gets the number random.
	 *
	 * @return the number random
	 */
	private static String getNumberRandom() {
		return getPassword(NUMEROS, 1);
	}
	
	/**
	 * Gets the capital random.
	 *
	 * @return the capital random
	 */
	private static String getCapitalRandom() {
		return getPassword(MAYUSCULAS, 1);
	}
	
	/**
	 * Gets the minu random.
	 *
	 * @return the minu random
	 */
	private static String getMinuRandom() {
		return getPassword(MINUSCULAS, 1);
	}
	
	/**
	 * Determina si parte o todo la cadana que se encuentra en phrase se encuentra contenida en password.
	 * @param password
	 * 					contraseña a validar
	 * @param phrase
	 * 					frase a buscar
	 * @return boolean 
	 */
	public static boolean isContainsPhrase(String password, String phrase) {
		String [] nameArray = StringUtils.split(phrase);
		boolean flag = false;
		for (int n = 0; n < nameArray.length; n++) {
			flag = password.toUpperCase().indexOf(nameArray[n].toUpperCase()) >= 0;
			if (flag) {
			   break;
			}
		}
		return flag;
		
	}
	
        /**
         * Checks if is minimum length.
         *
         * @param password the password
         * @param minimun the minimun
         * @return true, if is minimum length
         */
        public static boolean isMinimumLength(String password, int minimun) {
		return (password.length() >= minimun);	
	}
	
        /**
         * Checks if is maximum length.
         *
         * @param password the password
         * @param maximum the maximum
         * @return true, if is maximum length
         */
        public static boolean isMaximumLength(String password, int maximum) {
		return (password.length() <= maximum);	
	}
        
        /**
         * Checks if is min upper case.
         *
         * @param password the password
         * @param nroMinUpper the nro min upper
         * @return true, if is min upper case
         */
        public static boolean isMinUpperCase(String password, int nroMinUpper) {
		return isContainsCase(password, UPPER, nroMinUpper);
	}
	
        /**
         * Checks if is min lower case.
         *
         * @param password the password
         * @param nroMinLower the nro min lower
         * @return true, if is min lower case
         */
        public static boolean isMinLowerCase(String password, int nroMinLower) {
		return isContainsCase(password, LOWER, nroMinLower);
	}

        /**
         * Checks if is min digit case.
         *
         * @param password the password
         * @param nroMinDigit the nro min digit
         * @return true, if is min digit case
         */
        public static boolean isMinDigitCase(String password, int nroMinDigit) {
		return isContainsCase(password, DIGIT, nroMinDigit);
	}
	
	/**
	 * Checks if is contains case.
	 *
	 * @param password the password
	 * @param type the type
	 * @param nroCase the nro case
	 * @return true, if is contains case
	 */
	private static boolean isContainsCase(String password, int type, int nroCase) {
		boolean flag = false;
		
		char [] strChar = password.toCharArray();
		int nroAuxCase = 0;
		for (int i = 0; i < strChar.length;i++) {
			
			if (type == UPPER) {
				if (Character.isUpperCase(strChar[i])) { 
					nroAuxCase++;
					break; 
				}
			} else if (type == LOWER) {
				if (Character.isLowerCase(strChar[i])) { 
					nroAuxCase++;
					break;
				}
			} else if (type == DIGIT) {
				if (Character.isDigit(strChar[i])) { 
					nroAuxCase++;
					break; 
				}
			}
		}
		if (nroAuxCase == nroCase) {
			flag = true;
		}
		return flag;
	}	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		//boolean dd = StringUtils.containsIgnoreCase("hola","5");
		//boolean aa = "hola".toUpperCase().indexOf("5".toUpperCase()) > 0;
	}

}
