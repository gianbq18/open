package pe.open.client.escale.common.dto.rest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pe.open.client.escale.adm.business.type.TipoComparadorType;

public class RulesFilter {

	private String field;
	private String comp;
	private String value;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		String ret, newField, newValue;
		newField = field.replaceAll("[^a-zA-Z\\._]", "");
		newValue = Pattern.compile("'").matcher(value).replaceAll(Matcher.quoteReplacement("''"));
		
		switch (comp) {
		case "eq":
			ret = newField + " " + TipoComparadorType.EQ.getValue() + " " + "'"+newValue+"'";
			break;
		case "dif":
			ret = newField + " " + TipoComparadorType.DIF.getValue() + " " +"'"+ newValue+"'";
			break;
		case "geq":
			ret = newField + " " + TipoComparadorType.GEQ.getValue() + " " + "'"+newValue+"'";
			break;
		case "leq":
			ret = newField + " " + TipoComparadorType.LEQ.getValue() + " " +"'"+ newValue+"'";
			break;
		case "lke":
			ret = newField + " " + TipoComparadorType.LKE.getValue() + " " +"'%"+ newValue+"%'";
			break;
		case "bet":
			ret = newField + " " + TipoComparadorType.BET.getValue() + " " +""+ newValue+"";
			break;
		default:
			ret = "";
			break;
		}
		return ret;
	}
}
