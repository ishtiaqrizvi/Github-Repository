package org.example.youtubeplayer;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	Button btnPlayfav;   //declaring variables as buttons
	Button btnSubMenu;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  //tell the Dalvik VM to run the code in addition to the existing code in the onCreate() of the parent class
        setContentView(R.layout.activity_main);   //setting the content view
        btnPlayfav = (Button) findViewById(R.id.btnPlay);    //creating a button by using "Button" in brackets and assigning it to the button named btnPlay which is defined in activity_main
        //when the button is clicked the setOnClickListener will use the onClick method which uses intent to navigate to the YoutubeActivity to play the video
        btnPlayfav.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, YoutubeActivity.class);
				startActivity(intent);   //intent loads the other layout when the button is clicked. In this case youtube.xml
			}
		});
        
        btnSubMenu = (Button) findViewById(R.id.btnSubMenu);   //creating btnSubMenu and assigning it to btnSubMenu defined in activity_main
        btnSubMenu.setOnClickListener(new View.OnClickListener() {
			//when the button clicked is btnSubMenu the intent will navigate the current page from the Main Activity to SubMenu page
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SubMenu.class);
				startActivity(intent);
			}
		});

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
