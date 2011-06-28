package com.SRS6;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class result extends Activity {
	
	
	private static final String SOAP_ACTION = "http://tempuri.org/insertquizdata";      
	private static final String METHOD_NAME = "insertquizdata";      
	private static final String NAMESPACE = "http://tempuri.org/";      
	private static final String URL = "http://192.168.1.204/studentresponse/Service.asmx";
	
	
	TextView txtcorrect;
	TextView txtwrong;
	TextView txttime;
	TextView txttotal;
	TextView txtunattempted,tv,testsummery;
	ImageView resimg; 
//	TextView txttimer;
	
	String n1,s,sdate;
	int n2,n;
	double n3;
	String stuname;
	int tesid;
	
	DataBaseHelper data;
	
	
   
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
       // Button skip=new Button(this);
     //   skip=(Button)findViewById(R.id.starttest);
        
        data=new DataBaseHelper(this);
        
        
        //resimg=(ImageView)findViewById(R.id.img);
        testsummery=(TextView)findViewById(R.id.student);
        //tv=(TextView)findViewById(R.id.tv);
        txttotal=(TextView)findViewById(R.id.ua);
        txttotal.setTextColor(Color.BLACK);
        txtunattempted=(TextView)findViewById(R.id.ua1);
        txtunattempted.setTextColor(Color.BLACK);
        txtcorrect=(TextView)findViewById(R.id.correct);
        txtcorrect.setTextColor(Color.BLACK); 
        txtwrong=(TextView)findViewById(R.id.wrong);
        txtwrong.setTextColor(Color.BLACK);
        txttime=(TextView)findViewById(R.id.time3);
        txttime.setTextColor(Color.BLACK);
      //  txttimer=(TextView)findViewById(R.id.time4);
      //  txttimer.setTextColor(Color.BLACK);
        
        Bundle bundle = getIntent().getExtras(); 
        sdate=bundle.getString("sysdate");
         stuname=bundle.getString("stuname");
        System.out.println("Student name:"+stuname);
         tesid=bundle.getInt("testid",0);
        System.out.println("Test id:"+tesid);
    	int value = bundle.getInt("correctcount", 0);
    	int value1 = bundle.getInt("wrongcount", 0);
    	int value2=bundle.getInt("Ques",0);
    	int value3=bundle.getInt("unatt",0);
    	
    	double perce=(value*100)/value2;
    	System.out.println("Percentage:"+perce);
    	
    	String time2 = bundle.getString("time");
    	//String time3 = bundle.getString("timer");

    	testsummery.setText("Welcome"+" "+stuname+" "+"your test summery is :");
    	txttotal.setText(" "+value2+" No of questions");
        txtunattempted.setText(" "+value3+" Unattempted");
        txtcorrect.setText(" "+value+" right answers");
        txtwrong.setText(" "+value1+" wrong answers");
        txttime.setText(" "+time2);
       // txttimer.setText(" "+time3);
        
       // String str=stringToDateTime(sdate);
        //System.out.println("Converted Date is:"+str);
        data.Insertfinalresult(stuname, tesid, perce,sdate,"N");
        getfinalresult();
        /*skip.setOnClickListener(new OnClickListener(){
        	//@Override
            public void onClick(View v) {
            	
            	     // getnext(); 	    			
         			
        		
            	} 
            	       	
            	
      	        });*/
            
        
}
	
	public void call()
	 { 
		 try {
			 		 
			 	  
	      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	    	 	
	    	 	
	    	 	request.addProperty("strusername",n1);
		 		
		 		request.addProperty("inttestid",n2);
		 		
		 		request.addProperty("dblper",s);
		 		
		 		request.addProperty("dtdate",sdate);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

 		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 	   androidHttpTransport.call(SOAP_ACTION, envelope);
		       Toast.makeText(result.this, "Result posted successfully",Toast.LENGTH_SHORT).show();
		       data.updatefinalresult(n);
		       getreport();
		 	      
		 } 
	        catch (Exception e)    
	        { 
	        tv.setText(e.getMessage());
	        }  
	 }
	
	 public void getfinalresult()
	 {
		 Cursor c1=data.getTopfinalresult();
			while(c1.moveToNext())
			{
				n=c1.getInt(0);
				n1=c1.getString(1);
				n2=c1.getInt(2);
				n3=c1.getDouble(3);
				s=""+n3;
				System.out.println("id:"+n);
				System.out.println("First:"+n1); 
				System.out.println("Second:"+n2);
				System.out.println("Third:"+n3); 
				call();
			}
	 }
	 public void getreport()
	 {
		 Cursor c1=data.selectfinalresult();  
			while(c1.moveToNext())
			{     
				int n1=c1.getInt(1);
				String n2=c1.getString(0);
				System.out.println("First:"+n1);
				System.out.println("Second:"+n2);
				
			}
	 } 
	 /*public void getnext()
		{
		 	//v.call();
		 
			Intent i2 =new Intent(result.this,view.class);
			Bundle bundle = new Bundle();
			bundle.putString("wel",stuname);
			bundle.putInt("welcome", tesid);
			i2.putExtras(bundle); 
			startActivity(i2);
			//data.deletetest();
		}*/
	 
	/* public static String stringToDateTime(String string) {
		    SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
		    java.util.Date date = null;
		    String sqlDate;
		    try {
		        try {
					date =  sdf.parse(string);
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
		        sqlDate = sqlFormat.format(date);
		    }
		    catch(ParseException ex) {
		        sqlDate = string;
		    }

		    return sqlDate;
		}
*/
	  
}

  

