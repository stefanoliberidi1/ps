package it.ledmaster;
import java.net.URL;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaMarkerEvent;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class VLC extends Application {

	void playMedia() {
		String mp3 = "mission.mp3";
		URL resource = getClass().getResource(mp3);
		System.out.println(resource.toString());
		Media media = new Media(resource.toString());
		double pre = 10;

		System.out.println("MEDIA");

		for (int i = 40; i >= 0; i--) {
			media.getMarkers().put("ciccio" + i, Duration.millis((200 * i) - pre));

		}
		MediaPlayer mediaPlayer = new MediaPlayer(media);

		mediaPlayer.setOnMarker((MediaMarkerEvent event) -> {
			System.out.println(event.getMarker().getKey() + " at " + mediaPlayer.getCurrentTime().toString());
		});

		mediaPlayer.play();

		try {
			Thread.sleep(43000);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void main(String args[]) {
		new VLC().playMedia();

	}

	@Override
	public void start(Stage stage) throws Exception {

	}
}