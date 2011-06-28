package com.SRS6;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class splashscreeen extends Activity {
	
	int value;
	String quizvalue, stunm;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.splash);
    Bundle bundle = getIntent().getExtras(); 
    stunm=bundle.getString("stuname");
	value = bundle.getInt("id", 0);
	quizvalue=bundle.getString("qname");
   
    
    ImageButton b=new ImageButton(this);
    b = (ImageButton)findViewById(R.id.starttest);
    b.setOnClickListener(new Button.OnClickListener() 
	{ public void onClick (View v)
		{ 
		question();
		    			
		}});
		
			
			
		
}
        
    private void question()
	{ 
    	Intent i2 = new Intent(this,questiondemo.class);
		Bundle bundle = new Bundle();
		bundle.putString("stname",stunm);
		bundle.putInt("id1",value);
		bundle.putString("qname1",quizvalue);
		i2.putExtras(bundle);
		startActivity(i2);
		

	}

}


