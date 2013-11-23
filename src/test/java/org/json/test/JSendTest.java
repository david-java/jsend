package org.json.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JsonData;
import org.json.JsonStruct;

public class JSendTest extends TestCase {
	private static final String ENCODING_UTF_8 = "UTF-8";

	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public JSendTest(String testName) {
		super(testName);
	}
	
	public static Test suite() {
		return new TestSuite(JSendTest.class);
	}
	
	/**
	 * Test for one key, one set of values in JSend representation.
	 * Output {"status":"success","data":{"numbers":{"two":"2","one":"1","three":"3"}}}
	 * @see http://labs.omniti.com/labs/jsend
	 * @author David Bayo
	 *
	 */
	public void testSuccessOneItem() throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("testSuccessOneItem");
		JsonStruct struct = new JsonStruct();
		JsonData data = new JsonData();
		Map<String, String> oneRowMultipleValue = new HashMap<String, String>();
		oneRowMultipleValue.put("one", "1");
		oneRowMultipleValue.put("two", "2");
		oneRowMultipleValue.put("three", "3");
		
		data.put("numbers", oneRowMultipleValue);
		struct.setStatusToSuccess();
		struct.setData(data);
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// convert user object to json string
		mapper.writeValue(out, struct);
		System.out.println(out);
		//Remove double quotes to see unescaped JSON
		String expectedJSON = "{\"status\":\"success\",\"data\":{\"numbers\":{\"two\":\"2\",\"one\":\"1\",\"three\":\"3\"}}}";
		assertTrue(out.toString(JSendTest.ENCODING_UTF_8).equals(expectedJSON));
	}
	
	/**
	 * Test for one key, one array of set of values in JSend representation
	 * Output {"status":"success","data":{"numbers_array":[{"two":"2","one":"1","three":"3"},{"two":"2","one":"1","three":"3"}]}}
	 * @see http://labs.omniti.com/labs/jsend
	 * @author David Bayo
	 *
	 */
	public void testSuccessItemArray() throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("testSuccessItemArray");
		JsonStruct struct = new JsonStruct();
		JsonData data = new JsonData();
		Map<String, String> oneRowMultipleValue = new HashMap<String, String>();
		oneRowMultipleValue.put("one", "1");
		oneRowMultipleValue.put("two", "2");
		oneRowMultipleValue.put("three", "3");
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list.add(oneRowMultipleValue);
		list.add(oneRowMultipleValue);
		
		data.put("numbers_array", list);
		struct.setStatusToSuccess();
		struct.setData(data);
		
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// convert user object to json string
		mapper.writeValue(out, struct);
		System.out.println(out);
		//Remove double quotes to see unescaped JSON
		String expectedJSON = "{\"status\":\"success\",\"data\":{\"numbers_array\":[{\"two\":\"2\",\"one\":\"1\",\"three\":\"3\"},{\"two\":\"2\",\"one\":\"1\",\"three\":\"3\"}]}}";
		assertTrue(out.toString(JSendTest.ENCODING_UTF_8).equals(expectedJSON));
	}
}
