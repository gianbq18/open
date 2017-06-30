package pe.open.client.escale.common.dto.rest;

import java.util.List;
import java.util.Objects;

public class ConsultaJson {

	private GroupFilter groupFilter;
	private List<String> fields;
	private String table;
	private int batchSize, index;
	private String order;
	private String group; //imendoza 20170120

	public GroupFilter getGroupFilter() {
		return groupFilter;
	}

	public void setGroupFilter(GroupFilter groupFilter) {
		this.groupFilter = groupFilter;
	}

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public String obtenerFields() {

		String listString = "";

		for (String s : this.fields) {
			s.replaceAll("[^a-zA-Z\\.]", "");
			listString += s + ", ";
		}
		listString = listString.substring(0, listString.length() - 2);
		return listString;
	}

	public String getOrder() {
		if (Objects.nonNull(order)) {
			order.replaceAll("[^a-zA-Z\\.\\s]", "");
		}		
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getGroup() {
		if (Objects.nonNull(group)) {
			group.replaceAll("[^a-zA-Z\\.\\s]", "");
		}		
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
	

}
