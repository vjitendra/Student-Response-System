package com.SRS6;



import java.math.BigDecimal;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class studentreport1 extends Activity  {
	int[]  strquizno;
	String[] strquizname; 
	DataBaseHelper data;
	EditText s;
	int tid1;
	int[] tid;
	TableLayout tl,tl1;
	ArrayAdapter<String> adapter;
	String s1,uname;
	String[]stunm,date;
	String[]  tname;
	Button back,gv1;
	String id;
	double[]score;
	double[]percentage;
	String[] testname;
	int[] testid;
	TextView tv,wel;
	
	private static final String SOAP_ACTION = "http://tempuri.org/getresult_user";  
	private static final String URL = "http://192.168.1.204/studentresponse/Service.asmx";
    private static final String NAMESPACE = "http://tempuri.org/";   
	private static final String METHOD_NAME = "getresult_user";   
	
	
	private static final String SOAP_ACTION1 = "http://tempuri.org/gettest";  
	
	
	//getting quiz......................................
	private static final String METHOD_NAME1 = "gettest";      
	   
    /** Called when the activity is first created. */ 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentreport1);
        
        Bundle bundle = getIntent().getExtras(); 
        id=bundle.getString("wel");
    	uname=bundle.getString("welcome");
        data=new DataBaseHelper(this);  
       
        wel=(TextView)findViewById(R.id.welcome);
        wel.setText("Welcome"+uname);
        tl = (TableLayout) findViewById(R.id.tbl);
        tl1 = (TableLayout) findViewById(R.id.tbl1);
        
        s= (EditText) findViewById(R.id.sp1);
    			data.deletetest();
    			call();
				 tl.removeAllViews();
				 callreport(); 
				 
				 
				 
				 back=(Button)findViewById(R.id.bck); 
					back.setOnClickListener(new Button.OnClickListener() 
					{ public void onClick (View v)
						{ 							
						     getback();
						
						} 
					
				    
					});	
					back.setVisibility(View.GONE);
					gv1=(Button)findViewById(R.id.graphview);
					gv1.setOnClickListener(new Button.OnClickListener() 
					{ public void onClick (View v)
						{ 	
							back.setVisibility(View.VISIBLE);
							gv1.setVisibility(View.GONE);
							tl.setVisibility(View.GONE);
							tl1.setVisibility(View.GONE);
						    getgraph();
						
						} 
					
				    
					});		
					gv1.setVisibility(View.GONE);
				
				
			}
			 
    public void call()
	{
	        try { 

 	        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);

	        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(request);

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
	    	  System.out.println("String"+str1);
	    	  
	        }
	        String[] quizdata;
	        quizdata = ((String) str1).split("; ");
            int temp2=quizdata.length;
     	    int  temp1=((quizdata.length)/2);
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
   
    public void callreport() 
	 { 
		 try { 
			 		 
			 	  
	     	  	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); 
		   		
		  		request.addProperty("struserid",uname);
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true;  
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
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
		    	  System.out.println("New String:"+str1);  
		    	  
		        }
		        String[] arrauserData;
		        arrauserData = ((String) str1).split("; ");
		        
		        int temp2=arrauserData.length;
		        int temp1=((arrauserData.length)/5);
		        int[] id=new int[temp1]; 
		        String[] temp=new String[temp1];
		        stunm=new String[temp1];
		        tid=new int[temp1]; 
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
		        for(int x=0;x<temp1;x++) 
		        {
		         
                	data.Insertgetfinalresult(stunm[x],tid[x],score[x],date[x]);
                	
     
		        }
		        getdistincttid();
		        
		 } 
	        catch (Exception e)    
	        { 
  	        tv.setText(e.getMessage());
  	        System.out.println("eRROR MSG:"+e.getMessage());
	        }
	 } 
    public void getreport() 
	 {
    	int i=0;
    	int flag=0,flag1=0,flag2=0; 
    	    	  	 
    	for(int i1=0;i1<testname.length;i1++) 
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
	            labelTV.setText(testname[i1]); 
	            labelTV.setTextColor(Color.BLACK);
	            
	            tr.addView(labelTV);
	            
	            
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
	            valueTV.setText(""+percentage[i1]);
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
    
    
	
	/*public void getreports()
	 { 
		tname=new String [tid.length];
	 	for(int i=0;i<tid.length;i++)
		{
			System.out.println("Result:"+tid[i]);	 
			int id=tid[i];
			Cursor c=data.gettname(id); 
			while(c.moveToNext()) 
			{ 
			
				String name;
			 	name=c.getString(0); 
				tname[i]=name;
				System.out.println("Test name::::::"+tname[i]);
			}
		}
		
		//getreport(); 
		//data.deletetest();
			 
	 }*/
	
	public void getdistincttid()
	 { 
		int i=0;
			Cursor c=data.selectfinalresultdistincttestid(); 
			testid=new int[c.getCount()];
			while(c.moveToNext()) 
			{ 
			
				int name;
			 	name=c.getInt(0); 
			 	testid[i]=name; 
			 	i++;
				System.out.println("Test name::::::"+name);
			}
			getdistincttname();
			getavgperce();
		}
	public void getdistincttname()
	 { 
		int i=0; 
		testname=new String[testid.length]; 
		for(int j=0;j<testid.length;j++)
		{
			Cursor c=data.selecttestname(testid[j]); 
			
			while(c.moveToNext()) 
			{ 
			
				String name;
			 	name=c.getString(0); 
			 	testname[i]=name;
			 	i++;
				System.out.println("Test name::::::"+name);
			}
		}
			
		}
	public void getavgperce()
	 { 
		percentage=new double[testid.length];
	 	for(int i=0;i<testid.length;i++)
		{
			System.out.println("Result:"+testid[i]);	 
			//int id=testid[i];
			Cursor c=data.selectfinalresultperce(testid[i], uname); 
			
			while(c.moveToNext()) 
			{ 
			
				double perce1;
				perce1=c.getDouble(0); 
				int decimalPlace = 2;
			    BigDecimal bd = new BigDecimal(perce1);
			    bd = bd.setScale(decimalPlace,BigDecimal.ROUND_UP);
			    perce1 = bd.doubleValue();
			    System.out.println(perce1);
				percentage[i]=perce1; 
				
				//System.out.println("Test name::::::"+tname[i]);
			}
			
		}
		
		getreport(); 
		//data.deletetest();
			
	 }
	
	public void getgraph()
	{
	 	//data.deletetest();
		//Intent i = new Intent(this,stuavgpercegraph.class);
		Intent achartIntent = new stuavgpercegraph().execute(this);
    	Bundle bundle = new Bundle();
		bundle.putString("wel",id);
		bundle.putString("welcome",uname);
		bundle.putDoubleArray("perce",percentage);
		bundle.putStringArray("testnm",testname);
		achartIntent.putExtras(bundle);
		startActivity(achartIntent);

	}
	
	
	 public void getback()
		{
		 	data.deletetest();
		 	Intent i = new Intent(this,mainprofile.class);
			Bundle bundle = new Bundle();
			bundle.putString("wel",id);
			bundle.putString("welcome",uname);
			i.putExtras(bundle);
		    startActivity(i);

		}
	  

	
	
}
