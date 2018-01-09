package it.ledmaster;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public class httpClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		try {
			httpclient.start();
			for (int i = 0; i < 50; i++) {
				HttpGet request = new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=" + (100 - i) + "&g=0&b=" + i
						+ "&FlashDelay=150&pin0=1&pin1=1&reverse=1");
				Future<HttpResponse> future = httpclient.execute(request, null);
				HttpResponse response = future.get();

				 System.out.println("Response: " + response.getStatusLine());
			}

		} catch (Exception e) {
			System.out.println("Error calling ->" + e.getMessage());
		} finally {
			try {
				httpclient.close();
			} catch (Exception e) {
				System.out.println("Error closing ->" + e.getMessage());
			}
		}
		System.out.println("Done");
	}

}
