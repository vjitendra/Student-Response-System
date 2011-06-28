package com.SRS6;



import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;



public class mainprofile extends TabActivity
{
Button b1,b2;
TextView tv;
String value,value1;
int temp1;
int[] strquizno;
String[] strquizname;
DataBaseHelper data;

private static final String SOAP_ACTION = "http://tempuri.org/gettest";  
private static final String URL = "http://192.168.1.204/studentresponse/Service.asmx";

//getting quiz......................................
private static final String METHOD_NAME = "gettest";      
private static final String NAMESPACE = "http://tempuri.org/";    
 public void onCreate(Bundle icicle)
 {
	 requestWindowFeature(Window.FEATURE_NO_TITLE);  
     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
      

 super.onCreate(icicle);
 setContentView(R.layout.mainprofile);
 
 data=new DataBaseHelper(this);
 
 Bundle bundle = getIntent().getExtras(); 
	value1=bundle.getString("wel");
	value=bundle.getString("welcome");
 
 call();
 
 
 TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
 TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
 //TabSpec secondTabSpec = tabHost.newTabSpec("tid1");
 TabSpec thirdTabSpec = tabHost.newTabSpec("tid1");
 TabSpec fourthTabSpec = tabHost.newTabSpec("tid1");
 
  firstTabSpec.setIndicator("",getResources().getDrawable(R.drawable.profile));
  //firstTabSpec.setIndicator("profile");
  firstTabSpec.setContent(profile());
  //secondTabSpec.setIndicator("study",getResources().getDrawable(R.drawable.lesson3));
  //secondTabSpec.setIndicator("study");
 // secondTabSpec.setContent(study());
  thirdTabSpec.setIndicator("",getResources().getDrawable(R.drawable.review));
  //thirdTabSpec.setIndicator("review");
  thirdTabSpec.setContent(loginnew());
  fourthTabSpec.setIndicator("",getResources().getDrawable(R.drawable.test));
  //fourthTabSpec.setIndicator("test");
  fourthTabSpec.setContent(getnext());
  tabHost.addTab(firstTabSpec);
 // tabHost.addTab(secondTabSpec);
  tabHost.addTab(thirdTabSpec);
  tabHost.addTab(fourthTabSpec);

 }
 
 
 public void call()
	{
	        try { 

	        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

	        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(request);

	        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	        androidHttpTransport.call(SOAP_ACTION, envelope);

	        Object result = (Object)envelope.getResponse();
	        String str1=result.toString();
	        if(str1.contains("anyType{anyType=")||str1.contains("}")||str1.contains("anyType="))
	        {
	    	  str1=str1.replace("anyType{anyType=", "");
	    	  str1=str1.replace("anyType=", "");
	    	  str1=str1.replace("}", "");
	    	  str1=str1.trim();
	    	  System.out.println("String"+str1);
	    	  
	        } 
	        String[] quizdata;
	        quizdata = ((String) str1).split("; ");
	        int temp2=quizdata.length; 
	        temp1=((quizdata.length)/2);
	        strquizno=new int[temp1];
	        strquizname=new String[temp1];
		        System.out.println("Length:"+temp1);
		        for(int c=1,d=0;c<temp2;c=c+2)
		        {
		        	
	 	        strquizname[d]=quizdata[c];
		         
		        d++;
		        }
		        for(int a=0,b=0;a<temp2;a=a+2)
		        {
		        	strquizno[b]=Integer.parseInt(quizdata[a]);
		        	b++;
		                	
		        }		        		           
		        for(int x=0;x<temp1;x++)
		        {
		        		        
		      	data.Inserttest(strquizno[x], strquizname[x]);	
		        		        		         
		        }
		       
		        
	      
	    }
	        catch (Exception e) {
	        tv.setText(e.getMessage());
	        }
	}
 
 public Intent getnext()
	 {
 		Intent i1 = new Intent(this,selectquiz.class);
		Bundle bundle = new Bundle();
		bundle.putString("wel",value1);
		bundle.putString("welcome",value);
		i1.putExtras(bundle);
	   
	    return i1;
		
	 }
 
 private Intent loginnew()
	{
 	Intent i = new Intent(this,mainstudentreview.class);
		Bundle bundle = new Bundle();
		bundle.putString("wel",value1);
		bundle.putString("welcome",value);
		i.putExtras(bundle);
	   
		return i;

	}
 public Intent study()
 {
		Intent i1 = new Intent(this,study.class);
	/*Bundle bundle = new Bundle();
	bundle.putString("wel",value1);
	bundle.putString("welcome",value);
	i1.putExtras(bundle);*/
    
    return i1;
	
 }

private Intent profile()
{
	Intent i = new Intent(this,profile.class);
	Bundle bundle = new Bundle();
	bundle.putString("wel",value1);
	bundle.putString("welcome",value);
	i.putExtras(bundle);
   
	return i;

}
}