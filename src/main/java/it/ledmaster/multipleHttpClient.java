package it.ledmaster;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import java.util.ArrayList;

public class multipleHttpClient {

	static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(400).setConnectTimeout(400).build();
	static CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig)
			.build();

	public static void main(final String[] args) throws Exception {

		httpclient.start();

		ArrayList<HttpGet> l = new ArrayList<HttpGet>();

		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();

		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();

		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();

		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=1&r=10&g=0&b=0&FlashDelay=10&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=1&r=10&g=0&b=0&FlashDelay=10&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=15&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=20&b=0&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=20&b=0&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();
		l.add(new HttpGet("http://192.168.1.101/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		l.add(new HttpGet("http://192.168.1.105/function?FUNCTION=0&r=0&g=0&b=0&FlashDelay=90&pin0=1&pin1=1"));
		call(l);
		l.clear();

		httpclient.close();

	}

	static void call(ArrayList<HttpGet> list) throws Exception {
		/*
		 * RequestConfig requestConfig = RequestConfig.custom() .setSocketTimeout(400)
		 * .setConnectTimeout(400).build(); CloseableHttpAsyncClient httpclient =
		 * HttpAsyncClients.custom() .setDefaultRequestConfig(requestConfig) .build();
		 */
		long time1 = System.currentTimeMillis();

		try {
			HttpGet[] requests = new HttpGet[list.size()];
			requests = list.toArray(requests);

			CountDownLatch latch = new CountDownLatch(requests.length);
			for (HttpGet request : requests) {
				httpclient.execute(request, new FutureCallback<HttpResponse>() {

					@Override
					public void completed(final HttpResponse response) {
						latch.countDown();
						System.out.print(request.getRequestLine() + "->" + response.getStatusLine());
						System.out.println(" - Elapsed Time " + (System.currentTimeMillis() - time1));
					}

					@Override
					public void failed(final Exception ex) {
						latch.countDown();
						System.out.println(request.getRequestLine() + "->" + ex);
					}

					@Override
					public void cancelled() {
						latch.countDown();
						System.out.println(request.getRequestLine() + " cancelled");
					}

				});
			}
			latch.await();
		} finally {

		}

	}// fine call

}
