package pe.open.client.escale.common.dto.rest;

import java.util.List;

import pe.open.client.escale.common.dto.rest.ConsultaJson;

public class ReporteGenerico {

	private ConsultaJson consultaJson;
	private String nombreReporte;
	private List<String> listaCabecera;

	public ReporteGenerico() {

	}

	public ReporteGenerico(ConsultaJson consultaJson, String nombreReporte, List<String> listaCabecera) {
		this.consultaJson = consultaJson;
		this.nombreReporte = nombreReporte;
		this.listaCabecera = listaCabecera;
	}

	public ConsultaJson getConsultaJson() {
		return consultaJson;
	}

	public void setConsultaJson(ConsultaJson consultaJson) {
		this.consultaJson = consultaJson;
	}

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	public List<String> getListaCabecera() {
		return listaCabecera;
	}

	public void setListaCabecera(List<String> listaCabecera) {
		this.listaCabecera = listaCabecera;
	}

}
