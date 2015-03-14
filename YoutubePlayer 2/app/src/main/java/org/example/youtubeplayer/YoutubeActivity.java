package org.example.youtubeplayer;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import android.os.Bundle;
import android.widget.Toast;


public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
	//this is where you have to replace my API KEY with yours
	public static final String GOOGLE_API_KEY = "AIzaSyArdTDNI-84WJIdEDhvym0MRJAXFIBxgxs";
	//Youtube video ID is the unique identifier for a video on youtube. You can get the ID from the video URL. In the URL it comes as v="Video ID"
	public static final String YOUTUBE_VIDEO_ID = "OMqN6zKSnls";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.youtube);
        //this is where the view is getting configured.
        //the youTubePlayerView is set by getting the youtube_player view by ID from the layout youtube.xml file
		YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
		//Here the view is initialized with the API KEY defined above.
        youTubePlayerView.initialize(GOOGLE_API_KEY,  this);
		
	}

    //Once the initialization is done the event listeners need to be added. To complete the viable initialization the onInitializationFailure
    //has to be defined which displays if the initialization fails
	public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
		Toast.makeText(this, "Did not initialize the Youtube player", Toast.LENGTH_LONG).show();  //show will display the message if there is a problem.
	}
	//the success method is used to set the required event listeners once the initialization is successful.
	public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean check)  //these are callback methods that are called automatically
    {
		player.setPlayerStateChangeListener(playerStateChangeListener);  //these two event listeners are required to build the basic player.
		player.setPlaybackEventListener(playbackEventListener);
		
		if(!check) {    //then play the video
			player.cueVideo(YOUTUBE_VIDEO_ID);
		}
	}
	//creating the first listener. It can be left blank and there is no need to add any functionality but is mandatory to create
	private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {
		//adding few empty methods in case some functionality has to be added in the future.
		public void onBuffering(boolean arg0) {
			
		}
		
		public void onPaused() {
		}
		
		public void onPlaying() {
			
		}
		
		public void onSeekTo(int arg0) {
			
		}
		
		public void onStopped() {
			
		}
	};

    //creating the second listener. It can be left blank and there is no need to add any functionality but is mandatory to create
	private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {
	
		public void onAdStarted() {
			
		}
		
		public void onError(ErrorReason arg0) {
			
		}
		
		public void onLoading() {
			
		}
		
		public void onLoaded(String arg0) {
			
		}
		
		public void onVideoStarted () {
			
		}
		
		public void onVideoEnded() {
			
		}
		
	};
}
//the above code is referred from "https://developers.google.com/youtube/android/player/reference/com/google/android/youtube/player/YouTubePlayerView"
//the above code has also been referred from website "http://javatechig.com/android/youtubeplayerview-example-in-android-using-youtube-api"
