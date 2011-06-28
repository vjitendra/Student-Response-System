package com.SRS6;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import org.ksoap2.SoapEnvelope;  
import org.ksoap2.serialization.SoapObject;  
import org.ksoap2.serialization.SoapSerializationEnvelope;  
import org.ksoap2.transport.HttpTransportSE;  
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
//import com.example.android.R;

import android.app.*;  
import android.content.Intent;
import android.database.Cursor;
import android.os.*;  
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;    
import android.widget.Toast;
public class selectquiz extends Activity {  
	Spinner s;
	Button b,logout;
	ArrayAdapter<String> adapter;
	int temp1,qid;
	ImageView v;
	//private String array_spinner[];
	/** Called when the activity is first created. */  
		
	private static final String SOAP_ACTION = "http://tempuri.org/gettest";  
	private static final String URL = "http://192.168.1.204/studentresponse/Service.asmx";
	
	//getting quiz......................................
	private static final String METHOD_NAME = "gettest";      
	private static final String NAMESPACE = "http://tempuri.org/";      
	    
	
	TextView tv,wel;  
	int[] strquizno;
	String[] strquizname;
	String value,value1,value2;
	DataBaseHelper data;
	
	@Override      
	public void onCreate(Bundle savedInstanceState) {          
	super.onCreate(savedInstanceState);          
	//setContentView(R.layout.select);
	
	Bundle bundle = getIntent().getExtras(); 
	value1=bundle.getString("wel");
  	value=bundle.getString("welcome");
  	value2=bundle.getString("test");
  	
  	
	wel=(TextView)findViewById(R.id.stuwel);
	wel.setText("Welcome"+" "+value1);
	
	//v=(ImageView)findViewById(R.id.stu);
	data=new DataBaseHelper(this);
	s= (Spinner) findViewById(R.id.sp1);
	adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
	s.setAdapter(adapter);
		
	logout=(Button)findViewById(R.id.logout);
	logout.setOnClickListener(new Button.OnClickListener() 
	{ public void onClick (View v)
		{ 
			logout1();
	
		}
	
    
	});
	
	
	b=(Button)findViewById(R.id.dnld);
	
	b.setOnClickListener(new OnClickListener()
	{
		
		public void onClick(View v) {
			
			
			int index = s.getSelectedItemPosition();
			String s1=(String) s.getItemAtPosition(index);
			System.out.println("Result:"+s1);
			Cursor c=data.getid(value2);
			while(c.moveToNext())
			{
			 qid=c.getInt(0);
			
			System.out.println("Quiz id:"+qid);
			}
			questions();
			
			
		}
	});
	
	
	call();
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
		        gettest();
		        
	      
	    }
	        catch (Exception e) {
	        tv.setText(e.getMessage());
	        }
	}
	
	public void gettest()
	 {
		int n1;
		String n2;
		 Cursor c1=data.selecttest();
			while(c1.moveToNext())
			{
				n1=c1.getInt(0);
				n2=c1.getString(1);
				//System.out.println("First:"+n1);
				//System.out.println("Second:"+n2);
				adapter.add(n2);
												
			}
			
	 }
	
	
	private void questions()
	{	
		
		String s1 =(String) s.getSelectedItem();    
		System.out.println("Quiz name:"+s1); 
	    Intent i2 = new Intent(selectquiz.this,splashscreeen.class);
		Bundle bundle = new Bundle();
		
		bundle.putString("stuname",value);
		bundle.putInt("id",qid);
		bundle.putString("qname",s1);
		i2.putExtras(bundle);
		startActivity(i2);
		data.deletetest();  
		

	}
	private void logout1()
	{
		Intent i1 = new Intent(this,SRS6.class);
	    startActivity(i1);

	}
	

	
	
}