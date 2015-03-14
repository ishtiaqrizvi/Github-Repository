package org.example.youtubeplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
//the SubMenu doesn't extend the youtubebaseactivity
public class SubMenu extends Activity implements View.OnClickListener {

	public static final String GOOGLE_API_KEY = "AIzaSyArdTDNI-84WJIdEDhvym0MRJAXFIBxgxs";
    //this is the same video ID I have used in YoutubeActivity
	public static final String YOUTUBE_VIDEO_ID = "OMqN6zKSnls";
    //Youtube playlist ID is the unique identifier for a playlist on youtube. You can get the ID from any of its video's URL. In the URL it comes as list="Playlist ID"
	public static final String YOUTUBE_PLAYLIST_ID = "PLCB0DD71DD57D7DAB";

	private Button btnPlayfav;
	private Button btnPlayfavPlaylist;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.standalone);   //this is the layout this code will function on
		
		btnPlayfav = (Button) findViewById(R.id.btnStart);
		btnPlayfavPlaylist = (Button) findViewById(R.id.btnPlaylist);

        //passing the same onClickListener event  listener to remove redundancy. Have to use 'this' for both.
		btnPlayfav.setOnClickListener(this);
		btnPlayfavPlaylist.setOnClickListener(this);
		
	}

    //using the same generic view for checking what should be played
	public void onClick(View view) {
		Intent intent = null;   //initializing
		if(view == btnPlayfav) {
			// My favorite video will be played
            //this class uses its own intent and methods. This can be done by importing YouTubeStandalonePlayer
            //createVideoIntent is the self sufficient method to create a video intent.
			intent = YouTubeStandalonePlayer.createVideoIntent(this, GOOGLE_API_KEY, YOUTUBE_VIDEO_ID);
		} else if(view == btnPlayfavPlaylist) {
			// My favorite play list will be played
            //createPlaylistIntent is the self sufficient method to create a playlist intent.
			intent = YouTubeStandalonePlayer.createPlaylistIntent(this, GOOGLE_API_KEY, YOUTUBE_PLAYLIST_ID);
		}
		//check if there was any problem in setting up the intents
		if(intent != null) {
			startActivity(intent);    //if intent is not null then start the activity
		}
		
	}

}
//The above code is referred from "https://developers.google.com/youtube/android/player/reference/com/google/android/youtube/player/YouTubeStandalonePlayer"