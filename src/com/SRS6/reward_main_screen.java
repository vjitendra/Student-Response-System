package com.SRS6;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.SRS6.reward.MyOnItemSelectedListenerutype;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class reward_main_screen extends Activity{
	
	
	TableLayout summarytbl;
	DataBaseHelper  data;
	int  used_point1,avail_point1,yearly_point,earnedpoint1,redimedpoint1;
	String cmpltname,onlyname,type;
	TextView txt_total_balance,txt_avail_balance,txt_earned_point,txt_given_point,txt_redimed_point;
	
	private static final String NAMESPACE = "http://tempuri.org/";      
	private static final String URL = "http://192.168.1.204/studentresponse/Service.asmx"; 
		
	private static final String SOAP_ACTION10 = "http://tempuri.org/GetReward_Point";      
	private static final String METHOD_NAME10 = "GetReward_Point"; 
	
	
	
	
	
	
	
	
	
	public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.reward_main_screen);
        
        
        summarytbl=(TableLayout)findViewById(R.id.summary);
        
        txt_total_balance=(TextView)findViewById(R.id.total_balance); 
        txt_avail_balance=(TextView)findViewById(R.id.avail_balance);
        txt_given_point=(TextView)findViewById(R.id.used_point);
        txt_earned_point=(TextView)findViewById(R.id.earned_point);
        txt_redimed_point=(TextView)findViewById(R.id.redim_point);
        
        Bundle bundle = getIntent().getExtras(); 
   	 	cmpltname=bundle.getString("wel");
   		onlyname=bundle.getString("welcome");
   		type=bundle.getString("type");   
   		getreward_point();
   		display_summary();

}


	public void getreward_point()
	 {   
		
		 try { 
			 		  
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME10);     
		 		
		 		request.addProperty("UserName",onlyname);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION10, envelope);   
		    	 Object result = (Object)envelope.getResponse();
		 	      String str1=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 		   
		        	
		        } 
		 	    else
		 	    {
		 	    if(str1.contains("anyType{anyType=")||str1.contains("}")||str1.contains("anyType="))
		        {
		    	  str1=str1.replace("anyType{anyType=", "");
		    	  str1=str1.replace("anyType=", "");
		    	  str1=str1.replace("}", ""); 
		    	  str1=str1.trim();
		    	  System.out.println("String"+str1); 
		    	  
		        }
		        String[] arrauserData;
		        arrauserData = ((String) str1).split("; ");
		         
		        
		        if(arrauserData[4].contains(";"))
		        {
		        	arrauserData[4]=arrauserData[4].replace(";", "");
		        }
		        
		        if(arrauserData[3].equals(null))
		        {
		        	arrauserData[3].replace(null,"0");  
		        }
		        if(arrauserData[4].equals(null)) 
		        {
		        	arrauserData[4].replace(null,"0");
		        }
		        
		         
		         used_point1=Integer.parseInt(arrauserData[0]);
		         yearly_point=Integer.parseInt(arrauserData[1]); 
		         avail_point1 =Integer.parseInt(arrauserData[2]);   
		         earnedpoint1=Integer.parseInt(arrauserData[3]);
		         redimedpoint1=Integer.parseInt(arrauserData[4]);
		         
		         		           
		         
		         
		 	   }
		 }
		 	    catch (Exception e)    
		        {
				 
				 System.out.println("Error msg:"+e.getMessage()); 
		        
		        }
	             
		        
		 	       
		 }

	public void display_summary()
	{
		
		txt_total_balance.setText(yearly_point);
		txt_avail_balance.setText(avail_point1);
		txt_given_point.setText(used_point1);
		txt_earned_point.setText(earnedpoint1);
		txt_redimed_point.setText(redimedpoint1);
		
	}
	

/*public void display_summary() 
{
	int i=0;
	
	 
	try 
			{
		   final TableRow tr = new TableRow(this); 
           tr.setId(100+i);
           tr.layout(0, 0, 0, 0); 
           final TableRow tr1 = new TableRow(this); 
           tr1.setId(100+i);
           tr1.layout(0, 0, 0, 0); 
           final TableRow tr2 = new TableRow(this); 
           tr2.setId(100+i);
           tr2.layout(0, 0, 0, 0); 
           final TableRow tr3 = new TableRow(this); 
           tr3.setId(100+i);
           tr3.layout(0, 0, 0, 0); 
           final TableRow tr4 = new TableRow(this); 
           tr4.setId(100+i);
           tr4.layout(0, 0, 0, 0); 
           
           
           
           final TableRow tr5 = new TableRow(this); 
           tr5.setId(100+i);
           tr5.layout(0, 0, 0, 0); 
           final TableRow tr6 = new TableRow(this); 
           tr6.setId(100+i);
           tr6.layout(0, 0, 0, 0); 
           final TableRow tr7 = new TableRow(this); 
           tr7.setId(100+i);
           tr7.layout(0, 0, 0, 0); 
           final TableRow tr8 = new TableRow(this); 
           tr8.setId(100+i);
           tr8.layout(0, 0, 0, 0); 
           final TableRow tr9 = new TableRow(this); 
           tr9.setId(100+i);
           tr9.layout(0, 0, 0, 0); 
           
           
           
           
       	
           
        // Create a TextView to house the name of the province 
           final TextView lblyearlypoint = new TextView(this);
                            
           lblyearlypoint.setId(200+i);
           lblyearlypoint.setText("Total Balance:" +yearly_point); 
           
           lblyearlypoint.setTextColor(Color.BLACK);
           
           lblyearlypoint.setBackgroundColor(Color.GRAY);
                 
           tr.addView(lblyearlypoint);
           
           
           final TextView lblyearlypoint1 = new TextView(this);
           
           lblyearlypoint1.setId(200+i);
           //lblyearlypoint1.setText("Total Balance:" +yearly_point); 
           
           lblyearlypoint1.setTextColor(Color.BLACK);
           
           lblyearlypoint1.setBackgroundColor(Color.WHITE);
                 
           tr5.addView(lblyearlypoint1);
           
           
           
           
           final TextView lblavailpoint = new TextView(this);
           
           
           lblavailpoint.setId(200+i);
           lblavailpoint.setText("Total Balance:" +avail_point1); 
           
           lblavailpoint.setTextColor(Color.BLACK);
           
           lblyearlypoint.setBackgroundColor(Color.GRAY);
           
           tr1.addView(lblavailpoint);
           
           
final TextView lblavailpoint1 = new TextView(this);
           
           
           lblavailpoint1.setId(200+i);
           //lblavailpoint1.setText("Total Balance:" +avail_point1); 
           
           lblavailpoint1.setTextColor(Color.BLACK);
           
           lblyearlypoint1.setBackgroundColor(Color.WHITE);
           
           tr6.addView(lblavailpoint1);
           
           
           
           
          
           
           final TextView lblearnedpoint = new TextView(this);
           
           
           lblearnedpoint.setId(200+i);
           lblearnedpoint.setText("Total Balance:" +earnedpoint1); 
           
           lblearnedpoint.setTextColor(Color.BLACK);
           
           lblyearlypoint.setBackgroundColor(Color.GRAY);
           
           tr2.addView(lblearnedpoint);
           tr2.setBackgroundColor(Color.GRAY);
           
           
           final TextView lblassignedpoint = new TextView(this);
           
           
           lblassignedpoint.setId(200+i);
           lblassignedpoint.setText("Total Balance:" +used_point1); 
           
           lblassignedpoint.setTextColor(Color.BLACK);
           
           lblyearlypoint.setBackgroundColor(Color.GRAY);
           
           tr3.addView(lblassignedpoint);
           
           
           final TextView lblredimedpoint = new TextView(this);
           
           
           lblredimedpoint.setId(200+i);
           lblredimedpoint.setText("Total Balance:" +redimedpoint1); 
           
           lblredimedpoint.setTextColor(Color.BLACK);
           
           lblyearlypoint.setBackgroundColor(Color.GRAY);
           
           tr4.addView(lblredimedpoint);
           
                   
      
        // Add the TableRow to the TableLayout 
           summarytbl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                   LayoutParams.WRAP_CONTENT));
           summarytbl.addView(tr5, new TableLayout.LayoutParams(
                   LayoutParams.WRAP_CONTENT,
                  LayoutParams.WRAP_CONTENT));
             
          
          
           summarytbl.addView(tr1, new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                   LayoutParams.WRAP_CONTENT));
           
           summarytbl.addView(tr6, new TableLayout.LayoutParams(
                   LayoutParams.WRAP_CONTENT,
                  LayoutParams.WRAP_CONTENT));
           
           summarytbl.addView(tr2, new TableLayout.LayoutParams(
                   LayoutParams.WRAP_CONTENT,
                  LayoutParams.WRAP_CONTENT));
         
          summarytbl.addView(tr3, new TableLayout.LayoutParams(
                   LayoutParams.WRAP_CONTENT,
                  LayoutParams.WRAP_CONTENT));
          
          summarytbl.addView(tr4, new TableLayout.LayoutParams(
                  LayoutParams.WRAP_CONTENT,
                 LayoutParams.WRAP_CONTENT));
          
          i++;  
           
          
           
             
			}
			catch (Exception e)    
	        {
	         
	        System.out.println("Error msg:::::::::"+e.getMessage());
	        }
		}*/
}

