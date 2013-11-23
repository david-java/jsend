package org.json;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for creating JSend data key/s-value/s
 * Instantiate this class and put key-value or key-values in case of arrays
 * Examples in Junit test package
 * @see http://labs.omniti.com/labs/jsend
 * @author David Bayo
 *
 */
public class JsonData {
	
	private Map<String, Object> keysValuesMap = new HashMap<String, Object>();

	public Map<String, Object> getKeysValuesMap() {
		return keysValuesMap;
	}

	public void put(String key, Object value) {
		keysValuesMap.put(key, value);
	}
}
