package it.ledmaster;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.application.Application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import java.net.URL;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaMarkerEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

@SuppressWarnings({ "restriction", "unused" })
public class lsmain extends Application {

	public static double pre = 180;
	
	@SuppressWarnings("unchecked")
	void playMedia() {
		System.out.println("Hello from playSuite");
		

		httpclient.start();

		/*
		 * JSONObject obj = new JSONObject(); obj.put("name", "mkyong.com");
		 * obj.put("age", new Integer(100));
		 * 
		 * JSONArray list = new JSONArray(); list.add("msg 1"); list.add("msg 2");
		 * list.add("msg 3");
		 * 
		 * obj.put("messages", list);
		 * 
		 * try (FileWriter file = new FileWriter(
		 * "C:\\eclipse_workspace\\maven-prova\\src\\main\\resources\\test.json")) {
		 * 
		 * file.write(obj.toJSONString()); file.flush();
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 * 
		 * System.out.print(obj); System.out.println("fine scrittura");
		 */

		System.out.println("IMPORTING JSON");
		JSONParser parser = new JSONParser();
		HashMap<String, String> calls = new HashMap<String, String>();
		HashMap<String, ArrayList<HttpGet>> httpCalls = new HashMap<String, ArrayList<HttpGet>>();
		Media media = null;
		try {


			Object obj1 = parser
					.parse(new FileReader(jsonFile));

			JSONObject jsonObject = (JSONObject) obj1;

			System.out.println("IMPORTING MEDIA");
			// String mp3 = "mission.mp3";
			String mp3file = jsonObject.get("mp3file").toString();
			System.out.println("music file -> " + mp3file);
			// URL resource = getClass().getResource(mp3file);
			// System.out.println(resource.toString());
			media = new Media(mp3file);

			// loop array

			JSONArray msg = (JSONArray) jsonObject.get("messages");

			System.out.println("numero messaggi= " + msg.size());
			Iterator<JSONObject> iterator = msg.iterator();
			while (iterator.hasNext()) {
				JSONObject item = iterator.next();
				String time = item.get("time_ms").toString();
				String target = item.get("target").toString();
				// System.out.println("time="+time);
				// System.out.println("target="+target);
				media.getMarkers().put(time, Duration.millis(Double.parseDouble(time) - pre));

				if (calls.containsKey(time)) {
					System.out.println("<Chiamata simultanea>");
					ArrayList<HttpGet> l = (ArrayList<HttpGet>) httpCalls.get(time);
					l.add(new HttpGet(target));
				} else {
					System.out.println("Chiamata: ");
					ArrayList<HttpGet> l = new ArrayList<HttpGet>();
					l.add(new HttpGet(target));
					httpCalls.put(time, l);
				}
				calls.put(time, target);
				System.out.println(
						"--> tims_ms=" + time + ", @ " + (Double.parseDouble(time) - pre) + "ms, target=" + target);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		MediaPlayer mediaPlayer = new MediaPlayer(media);

		mediaPlayer.setOnMarker((MediaMarkerEvent event) -> {
			// System.out.println("call @ " + mediaPlayer.getCurrentTime().toString() + " ->
			// " + calls.get(event.getMarker().getKey()) );
			// call(calls.get(event.getMarker().getKey()).toString());
			try {
				String time = event.getMarker().getKey();
				ArrayList<HttpGet> l = (ArrayList<HttpGet>) httpCalls.get(time);
				System.out.println("call @ " + mediaPlayer.getCurrentTime());
				call(l);
			} catch (Exception e) {

			}

		}

		);

		try {
			System.out.println("Sistema pronto, premere [INVIO] per continuare :");
			int i=System.in.read();
			//System.out.println("Valore byte letto "+i);
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		
		System.out.println("----------- START -----------");

		
		//mediaPlayer.setStartTime(Duration.millis(160000));
		
		mediaPlayer.setStartTime(Duration.millis(Double.valueOf(startMS)));
		mediaPlayer.play();
		

		try {
			Thread.sleep(Long.valueOf(stopMS));
			httpclient.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	static String jsonFile;
	static String startMS;
	static String stopMS;
	static String preCall;
	
	public static void main(String[] args) {
		if (args.length==0) {
			System.out.println("NO PARAMS --> EXITING PROGRAM");
			System.exit(1);
		}else if (args.length!=4) {
			System.out.println("4 PRAMS REQUIRED --> EXITING PROGRAM");
			System.exit(1);
		}
		jsonFile=args[0];
		startMS=args[1];
		stopMS=args[2];
		preCall=args[3];
		
		System.out.println("PARAM 1="+jsonFile);
		System.out.println("PARAM 2="+startMS);
		System.out.println("PARAM 3="+stopMS);
		System.out.println("PARAM 4="+preCall);
		pre=Double.parseDouble(preCall);
		new lsmain().playMedia();
		System.out.println("----------- END -----------");
		System.exit(0);
	}

	@Override
	public void start(Stage stage) throws Exception {

	}

	private RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(800).setConnectTimeout(800).build();
	private CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig)
			.build();

	public void call(ArrayList<HttpGet> list) throws Exception {
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
						/*							try {
						httpclient.close();
						System.out.println(ioe.getMessage());
						}
						*/
					}

					@Override
					public void failed(final Exception ex) {
						latch.countDown();
						System.out.println(request.getRequestLine() + "->" + ex);
						//ex.printStackTrace();
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
	/*
	 * public void call(String target) {
	 * 
	 * CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault(); try {
	 * httpclient.start(); HttpGet request = new HttpGet(target);
	 * Future<HttpResponse> future = httpclient.execute(request, null); HttpResponse
	 * response = future.get(); }catch (Exception e) {
	 * System.out.println("Error calling ->"+e.getMessage()); } finally { try {
	 * httpclient.close(); }catch (Exception e) {
	 * System.out.println("Error closing ->"+e.getMessage()); } } }
	 */

}
