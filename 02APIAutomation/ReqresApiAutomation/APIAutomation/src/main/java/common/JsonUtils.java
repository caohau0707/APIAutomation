package common;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import com.google.gson.Gson;

import objects.users;

public class JsonUtils {
	public String getValueByKey(String jsonBody, String key) {
		String value = "";
		JSONParser responseParser = new JSONParser();
		try {
			JSONObject responseObject = (JSONObject) responseParser.parse(jsonBody);
			value = responseObject.get(key).toString();

		} catch (org.json.simple.parser.ParseException e) {
			System.out.println("Json response body is invalid");
			e.printStackTrace();
		}

		return value;
	}

	public users[] getValuesAsArray(String jsonArray) {
		Gson gson = new Gson();
		users[] userArray = gson.fromJson(jsonArray, users[].class);
		return userArray;
	}

	public void copyJsonFile(File sourceFile, File destinationFile) {
		if (destinationFile.exists()) {
			destinationFile.delete();
		}
		try {
			Files.copy(sourceFile.toPath(), destinationFile.toPath());
			System.out.println("Copy successfully");
		} catch (Exception e) {
			System.out.println("Json request body is not found");
		}
	}

	// Pass value by fieldName
	public String changeValueByFieldName(File file, String fieldName, String value) {
		String resultFile = null;
		String filePath = file.getAbsolutePath();
		try {
			String originalFile = new String(Files.readAllBytes(Paths.get(filePath)));

			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(originalFile);
			if (value.equals("missing")) {
				jsonObject.remove(fieldName);
			} else if (value.equals("null")) {
				jsonObject.put(fieldName, null);
			} else if (value.equals("true")) {
				jsonObject.put(fieldName, true);
			} else if (value.equals("\"\"")) {
				jsonObject.put(fieldName, "");
			} else {
				jsonObject.put(fieldName, value);
			}
			resultFile = jsonObject.toJSONString();

		} catch (Exception e) {
			System.out.println("File not found");
		}

		return resultFile;

	}
	public String getFilePath(String requestBodyName) {
		String result = System.getProperty("user.dir") + "/src/main/resources/" + requestBodyName;
		return result;
	}
	public String getRequestBodyByName(String requestBodyName) {
		String requestBody = "";
		// Doc file json, chuyen noi dung file sang String
		// Lay duong dan thu muc file
		String filePath = getFilePath(requestBodyName);
		try {
			File file = new File(filePath);
			if (file.exists()) {
				requestBody = new String(Files.readAllBytes(Paths.get(filePath)));
			}
		} catch (Exception e) {
			System.out.println("file path: " + filePath);
			System.out.println("request body: " + requestBody);
		}
		return requestBody;
	}

}
