package common;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApiUtils {
	public HttpResponse<String> sendRequest(String url, String requestBody, String method) {
		HttpResponse<String> response = null;
		if (method.equals("POST")) {
			response = sendPostRequest(url, requestBody);
		} else if (method.equals("GET")) {
			response = sendGetRequest(url, requestBody);
			System.out.println(response);
		} else if(method.equals("DELETE")) {
			response = sendDeleteRequest(url, requestBody);
		} else if(method.equals("PUT")) {
			response = sendPutRequest(url, requestBody);
		}
		
		return response;
	}

	private HttpResponse<String> sendPostRequest(String url, String requestBody) {
		HttpRequest request;
		HttpResponse<String> response = null;
		try {
			request = HttpRequest.newBuilder().header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(requestBody)).uri(new URI(url)).build();

			response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
		} catch (Exception e) {
			System.out.println("Request is invalid");
			e.printStackTrace();
		}
		return response;
	}
	private HttpResponse<String> sendPutRequest(String url, String requestBody) {
		HttpRequest request;
		HttpResponse<String> response = null;
		try {
		request =	 HttpRequest.newBuilder().header("Content-Type", "application/json")
				.PUT(HttpRequest.BodyPublishers.ofString(requestBody)).uri(new URI(url)).build();
		response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
		} catch (Exception e) {
			System.out.println("request is invalid");
			e.printStackTrace();
		}
		return response;
	}

	private HttpResponse<String> sendGetRequest(String url, String requestBody) {
		HttpRequest request;
		HttpResponse<String> response = null;
		try {
			request = HttpRequest.newBuilder().GET().uri(URI.create(url))
					.setHeader("User-Agent", "Java 11 HttpClient Bot").build();
			response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
		} catch (Exception e) {
			System.out.println("Request is invalid");
			e.printStackTrace();
		}
		return response;
	}

	private HttpResponse<String> sendDeleteRequest(String url, String requestBody) {
		HttpRequest request;
		HttpResponse<String> response = null;
		try {
			request = HttpRequest.newBuilder().DELETE().uri(URI.create(url))
					.setHeader("User-Agent", "Java 11 HttpClient Bot").build();
			response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
		} catch (Exception e) {
			System.out.println("Request is invalid");
			e.printStackTrace();
		}
		return response;
	}

	
}
