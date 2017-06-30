package pe.open.client.escale.common.dto.rest;

import java.util.List;

import pe.open.client.escale.adm.business.type.TipoOperadoresLogicType;
import pe.open.client.escale.common.util.StringUtil;

public class GroupFilter {

	private String groupOp;
	private List<RulesFilter> rules;
	private List<GroupFilter> groups;

	public String getGroupOp() {
		return groupOp;
	}

	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	public List<RulesFilter> getRules() {
		return rules;
	}

	public void setRules(List<RulesFilter> rules) {
		this.rules = rules;
	}

	public List<GroupFilter> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupFilter> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		String ret = "";
		String strRules = "";
		String strGroups = "";

		if (StringUtil.isNotNullOrBlank(rules)) {
			for (RulesFilter rule : rules) {
				switch (groupOp) {
				case "&":
					strRules = strRules + rule.toString() + " " + TipoOperadoresLogicType.AND.getValue() + " ";
					break;
				case "|":
					strRules = strRules + rule.toString() + " " + TipoOperadoresLogicType.OR.getValue() + " ";
					break;
				default:
					break;
				}
			}
		}

	
		if (StringUtil.isNotNullOrBlank(groups)) {
			for (GroupFilter group : groups) {
				switch (groupOp) {
				case "&":
					strGroups = strGroups + group.toString() + " " + TipoOperadoresLogicType.AND.getValue() + " ";
					break;
				case "|":
					strGroups = strGroups + group.toString() + " " + TipoOperadoresLogicType.OR.getValue() + " ";
					break;
				default:
					break;
				}
			}
		}
		


		switch (groupOp) {
		case "&":
			if (StringUtil.isNotNullOrBlank(strGroups)) {
				strGroups = strGroups.substring(0, strGroups.length() - 5);
			}
			if (StringUtil.isNotNullOrBlank(strRules)) {
				strRules = strRules.substring(0, strRules.length() - 5);
			}
			if (StringUtil.isNotNullOrBlank(strGroups) && StringUtil.isNotNullOrBlank(strRules)) {
				ret = "(" + strRules + " " + TipoOperadoresLogicType.AND.getValue() + " " + strGroups + ")";
			} else if (StringUtil.isNotNullOrBlank(strGroups)) {
				ret = "(" + strGroups + ")";
			} else if (StringUtil.isNotNullOrBlank(strRules)) {
				ret = "(" + strRules + ")";
			} else {
				ret = "";
			}
			break;
		case "|":
			if (StringUtil.isNotNullOrBlank(strGroups)) {
				strGroups = strGroups.substring(0, strGroups.length() - 4);
			}
			if (StringUtil.isNotNullOrBlank(strRules)) {
				strRules = strRules.substring(0, strRules.length() - 4);
			}
			if (StringUtil.isNotNullOrBlank(strGroups) && StringUtil.isNotNullOrBlank(strRules)) {
				ret = "(" + strRules + " " + TipoOperadoresLogicType.OR.getValue() + " " + strGroups + ")";
			} else if (StringUtil.isNotNullOrBlank(strGroups)) {
				ret = "(" + strGroups + ")";
			} else if (StringUtil.isNotNullOrBlank(strRules)) {
				ret = "(" + strRules + ")";
			} else {
				ret = "";
			}
			break;
		default:
			break;
		}

		return ret;
	}

}
