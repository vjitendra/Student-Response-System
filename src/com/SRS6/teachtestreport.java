package com.SRS6;



import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class teachtestreport extends Activity  {
	int[]  strquizno;
	String[] strquizname; 
	DataBaseHelper data;
	Spinner s;
	int tid;
	TableLayout tl;
	Button back;
	ArrayAdapter<String> adapter;
	String s1;
	String[]stunm,date;
	double[]score;
	
	TextView tv;
	
	private static final String SOAP_ACTION = "http://tempuri.org/gettest";  
	private static final String URL = "http://192.168.1.204/studentresponse/Service.asmx";
	
	//getting quiz......................................
	private static final String METHOD_NAME = "gettest";      
	private static final String NAMESPACE = "http://tempuri.org/";   
	
	private static final String SOAP_ACTION1 = "http://tempuri.org/getresult_testid";  
	
	private static final String METHOD_NAME1 = "getresult_testid";   
	
    /** Called when the activity is first created. */ 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachtestreport);
        data=new DataBaseHelper(this);  
        
        
       
        tl = (TableLayout) findViewById(R.id.tbl);
        
        s= (Spinner) findViewById(R.id.sp1);
    	adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
    	s.setAdapter(adapter);
    	   	
    	 call();
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				 tl.removeAllViews();
				 getreports();
				
				
			}
			
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			} 
    		
		});
        
        back=(Button)findViewById(R.id.bk);
		back.setOnClickListener(new Button.OnClickListener() 
		{ public void onClick (View v)
			{ 							
			     getback();
			
			}
		
	    
		});
      
        
    }
    public void callreport() 
	 { 
		 try { 
			 		 
			 	  
	    	  	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1); 
		   		
		  		request.addProperty("inttestid",tid);
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true;  
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION1, envelope);
		    	 Object result = (Object)envelope.getResponse();
		 	      String str1=result.toString();  
		        if(str1.contains("anyType{anyType=")||str1.contains("}")||str1.contains("anyType="))
		        {
		    	  str1=str1.replace("anyType{anyType=", "");
		    	  str1=str1.replace("anyType=", "");
		    	  str1=str1.replace("}", ""); 
		    	  str1=str1.trim();
		    	  System.out.println("New String:"+str1); 
		    	  
		        }
		        String[] arrauserData;
		        arrauserData = ((String) str1).split("; ");
		        
		        int temp2=arrauserData.length;
		        int temp1=((arrauserData.length)/5); 
		        int[] id=new int[temp1]; 
		        String[] temp=new String[temp1];
		        stunm=new String[temp1];
		        int[] tid=new int[temp1]; 
		        score=new double[temp1];
		        date=new String[temp1];
		        
		        
		        
		         
		       // System.out.println("Length:"+temp1);
		         
		        for(int a=0,b=0;a<temp2;a=a+5)
		        {
		        	id[b]=Integer.parseInt(arrauserData[a]);
		        	b++;
	 	                	
		        }
		        
		         for(int a=1,b=0;a<temp2+1;a=a+5)
		        {
		        	stunm[b]=arrauserData[a];
		        	b++;
		                	
		        }
		         
		        for(int c=2,d=0;c<temp2;c=c+5) 
		        {
		        	 
		        tid[d]=Integer.parseInt(arrauserData[c]);
		        
		        d++;
		        }
		        for(int c=3,d=0;c<temp2;c=c+5) 
		        {
		        	 
		        	score[d]=Double.parseDouble(arrauserData[c]);
		        
		        d++;
		        }
		        for(int c=4,d=0;c<temp2;c=c+5) 
		        {
		        	
		         temp[d]=arrauserData[c];
		         if(temp[d].contains(";"))
		         {
		        	 temp[d]=temp[d].replace(";"," ");
		        	 date[d]=temp[d];
		         }
		         else
		         { 
		        date[d]=arrauserData[c];
		         }
		        
		        d++;
		        }
		        getreport();
		        
		 } 
	        catch (Exception e)    
	        { 
  	        tv.setText(e.getMessage());
	        }
	   } 
    public void getreport() 
	 {
    	int i=0;
    	int flag=0,flag1=0,flag2=0; 
    	  	 
    	
    	 
    	for(int i1=0;i1<stunm.length;i1++) 
    	{
    		
				try
				{
				TableRow tr = new TableRow(this); 
	            tr.setId(100+i);
	            tr.layout(2, 0, 0, 0);
	            
	            // Create a TextView to house the name of the province
	            TextView labelTV = new TextView(this);
	            if(flag==0)
	            {
	            labelTV.setBackgroundColor(Color.LTGRAY); 
	            flag=1;
	            }
	            else
	            {
	            labelTV.setBackgroundColor(Color.WHITE);
	            flag=0;
	            }
	                   
	            labelTV.setId(200+i);
	            labelTV.setText(stunm[i1]); 
	            labelTV.setTextColor(Color.BLACK);
	            
	            tr.addView(labelTV);
	            
	         // Create a TextView to house the name of the province
	            TextView dateTV = new TextView(this);
	            if(flag2==0)
	            {
	            dateTV.setBackgroundColor(Color.LTGRAY); 
	            flag2=1;
	            }
	            else
	            {
	            dateTV.setBackgroundColor(Color.WHITE);
	            flag2=0;
	            }
	                   
	            dateTV.setId(200+i);
	            dateTV.setText(date[i1]); 
	            dateTV.setTextColor(Color.BLACK);
	            
	            tr.addView(dateTV);


	            // Create a TextView to house the value of the after-tax income
	            TextView valueTV = new TextView(this);
	            if(flag1==0)
	            {
	           	valueTV.setBackgroundColor(Color.LTGRAY);
	            flag1=1;
	            }
	            else 
	            {
	           	valueTV.setBackgroundColor(Color.WHITE); 
	            flag1=0; 
	            }
	            
	          
	           
	            valueTV.setId(i);
	            valueTV.setText(""+score[i1]);
	            valueTV.setTextColor(Color.BLACK);
	            
	            tr.addView(valueTV);

	            // Add the TableRow to the TableLayout 
	            tl.addView(tr, new TableLayout.LayoutParams(
	                    LayoutParams.FILL_PARENT,
	                    LayoutParams.WRAP_CONTENT));
	            i++; 
				}
				catch (Exception e)    
		        {
		        tv.setText(e.getMessage());
		        }
			}
	 }
    
    public void call()
	{
	        try { 
	        
	         data.teachdeletetest(); 

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
     	     int temp1=((quizdata.length)/2);
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
		        		        
		      	data.teachInserttest(strquizno[x], strquizname[x]);	
		        		        		         
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
		 Cursor c1=data.teachselecttest();
			while(c1.moveToNext())
			{
				n1=c1.getInt(0);
				n2=c1.getString(1);
				adapter.add(n2);
												
			}
			 
	  }
	
	public void getreports()
	 {
		int index = s.getSelectedItemPosition();
	    s1=(String) s.getItemAtPosition(index);
		System.out.println("Result:"+s1);	
		Cursor c=data.getteachid(s1); 
		
		while(c.moveToNext())
		{
			 tid=c.getInt(0);
			 System.out.println("Test id:"+tid);
		}
		//getreport(); 
		callreport();  
			
	 }
	 
	 public void getback()
		{
		 	
		 	Intent i = new Intent(this,SRS6.class);
			
		    startActivity(i);

		}
	
	
}