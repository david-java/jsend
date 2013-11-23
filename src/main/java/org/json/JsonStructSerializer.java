package org.json;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * Class for Serializing JSend structure as JSON.
 * You don't need to call serialize method, it's invoked by Jackson library
 * Examples in Junit test package
 * @see http://labs.omniti.com/labs/jsend
 * @author David Bayo
 *
 */
public class JsonStructSerializer extends JsonSerializer<JsonStruct> {

	@Override
	public void serialize(JsonStruct arg0, JsonGenerator arg1,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {
		arg1.writeStartObject();
		JsonStatusEnum status = arg0.getStatus();
		
		if (status != null) {
			arg1.writeFieldName(JsonStruct.STATUS);
			arg1.writeString(status.toString());
		} else {
			throw new IOException(JsonStruct.STATUS + " field is mandatory");
		}

		if (status == JsonStatusEnum.SUCCESS) {
			if (arg0.getData() != null) {
				writeDataObject(arg1, arg0.getData());
			} else {
				throw new IOException(JsonStruct.DATA
						+ " field is mandatory when " + JsonStruct.STATUS
						+ " is " + JsonStatusEnum.SUCCESS);
			}
		}
		
		if (status == JsonStatusEnum.FAIL) {
			if (arg0.getData() != null) {
				writeDataObject(arg1, arg0.getData());
			} else {
				throw new IOException(JsonStruct.DATA
						+ " field is mandatory when " + JsonStruct.STATUS
						+ " is " + JsonStatusEnum.FAIL);
			}
		}

		if (status == JsonStatusEnum.ERROR) {
			if (arg0.getMessage() != null) {
				arg1.writeFieldName(JsonStruct.MESSAGE);
				arg1.writeString(arg0.getMessage());
				if (arg0.getCode() != null) {
					arg1.writeFieldName(JsonStruct.CODE);
					arg1.writeString(arg0.getCode());
				}
				if (arg0.getData() != null) {
					writeDataObject(arg1, arg0.getData());
				}
			} else {
				throw new IOException(JsonStruct.MESSAGE
						+ " field is mandatory when " + JsonStruct.STATUS
						+ " is " + JsonStatusEnum.ERROR);
			}
		}
		arg1.writeEndObject();
	}

	private void writeDataObject(JsonGenerator arg1, JsonData data) throws JsonGenerationException, IOException {
		arg1.writeFieldName(JsonStruct.DATA);
		arg1.writeStartObject();
		Map<String, Object> map = data.getKeysValuesMap();
		
		for (Map.Entry<String, Object> entry : map.entrySet())
		{
			arg1.writeFieldName(entry.getKey());
			arg1.writeObject(entry.getValue());
		}
		arg1.writeEndObject();
	}

}
