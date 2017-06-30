package pe.open.client.escale.common.dto.rest;

public class RespuestaDTO<T> {

	private Boolean success;
	private String errorMsg;
	private Object dataResult;

	public RespuestaDTO(boolean success, String errorMsg, Object dataResult) {
		super();
		this.success = success;
		this.errorMsg = errorMsg;
		this.dataResult = dataResult;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getDataResult() {
		return dataResult;
	}

	public void setDataResult(Object dataResult) {
		this.dataResult = dataResult;
	}

}