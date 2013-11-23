package org.json;

import org.codehaus.jackson.annotate.JsonValue;

/**
 * Enum for JSend Status representation
 * Examples in Junit test package
 * @see http://labs.omniti.com/labs/jsend
 * @author David Bayo
 *
 */
public enum JsonStatusEnum {

	SUCCESS("success"), FAIL("fail"), ERROR("error");
	private final String status;

	private JsonStatusEnum(final String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return this.status.toLowerCase();
	}
}