package org.json;

/**
 * Class for JSend Enum Status representation
 * Examples in Junit test package
 * @see http://labs.omniti.com/labs/jsend
 * @author David Bayo
 *
 */
public class JsonStatus {
	private JsonStatusEnum status;

	public JsonStatusEnum getStatus() {
		return status;
	}

	public void setStatus(JsonStatusEnum status) {
		this.status = status;
	}
	
	public void setStatusToSuccess() {
		this.status = JsonStatusEnum.SUCCESS;
	}
	
	public void setStatusToFail() {
		this.status = JsonStatusEnum.FAIL;
	}
	
	public void setStatusToError() {
		this.status = JsonStatusEnum.ERROR;
	}
}
