package com.SRS6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SRS6 extends Activity  {
	
	Button login,register;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new Button.OnClickListener()  
    	{ 
        	
        	public void onClick (View v)
    		{ 
    		  loginnew();    
    		    			
    	 	}});
        
        register=(Button)findViewById(R.id.reg);
        register.setOnClickListener(new Button.OnClickListener() 
    	{ 
        	
        	public void onClick (View v)
    		{ 
    		  stureg();
        		//getnext();
    		    			
    		}}); 
    }
    
    private void loginnew()
	{
		Intent i = new Intent(this,LoginNew.class);
	    startActivity(i);

	}
    private void stureg()
	{
		Intent i = new Intent(this,sturegister.class);
	    startActivity(i);

	}
    public void getnext()
	 {
		 Intent i2 = new Intent(this,reportscreen.class);
		 startActivity(i2);
	 }
}