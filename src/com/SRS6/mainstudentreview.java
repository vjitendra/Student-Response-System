package com.SRS6;



import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.R.color;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class mainstudentreview extends Activity implements OnCheckedChangeListener  {
	int[]  strquizno;
	String[] strquizname; 
	DataBaseHelper data; 
	EditText s;
	int tid1;
	int[] tid;
	String s1,uname;
	String[]stunm,date;
	String[]  tname;
	String id;
	double[]score;
	
	TextView tv,wel;
	protected static RadioGroup mradiogroup;
//	Button back;
	RadioButton r1,r2,r3,r4;
	private static final String SOAP_ACTION = "http://tempuri.org/getresult_user";  
	private static final String URL = "http://192.168.1.204/studentresponse/Service.asmx";
    private static final String NAMESPACE = "http://tempuri.org/";   
	private static final String METHOD_NAME = "getresult_user";   
	
    /** Called when the activity is first created. */ 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainstudentreview);
        
        Bundle bundle = getIntent().getExtras(); 
        id=bundle.getString("wel");
    	uname=bundle.getString("welcome");
        data=new DataBaseHelper(this);  
        
        
        wel=(TextView)findViewById(R.id.welcome1);
        mradiogroup = (RadioGroup) findViewById(R.id.Group1);
        
        r1=(RadioButton)findViewById(R.id.rad1);
     
       
        
        r2=(RadioButton)findViewById(R.id.rad2);
      
        
        r3=(RadioButton)findViewById(R.id.rad3);
     
        
        r4=(RadioButton)findViewById(R.id.rad4);
        
        mradiogroup.setOnCheckedChangeListener(this);
        
        /*back=(Button)findViewById(R.id.bk1);
		back.setOnClickListener(new Button.OnClickListener() 
		{ public void onClick (View v)
			{ 							
			     getback();
			
			}
		
	    
		});*/
		
       
               data.deletetest();
               callreport(); 
				 
				 
				 
				
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
		 	   if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 		   r1.setVisibility(View.GONE);
		 		   r2.setVisibility(View.GONE);
		        	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		        	alertbox.setMessage(uname+" "+"you don't have any reviews to see....would u like to give any test... ");
	    		     
		        	alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		        	     
	                    // do something when the button is clicked
	                    public void onClick(DialogInterface arg0, int arg1) {
	                       // Toast.makeText(getApplicationContext(), "'Yes' button clicked", Toast.LENGTH_SHORT).show();
	                        getnext();
	                    }
	                });
	     
	                // set a negative/no button and create a listener
	                alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
	     
	                    // do something when the button is clicked
	                    public void onClick(DialogInterface arg0, int arg1) {
	                       // Toast.makeText(getApplicationContext(), "'No' button clicked", Toast.LENGTH_SHORT).show();
	                    }
	                });
	     
	                // display box
	                alertbox.show();
		        } 
		        else
		        {
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
                	//System.out.println("Inserted Successfully");
     
		        } 
		        }
		        
		 } 
	        catch (Exception e)    
	        { 
  	        tv.setText(e.getMessage());
	        }
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
		 



	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		
		if(r1.isChecked())
		{
			
			getreport1();	
			 
		}
		if(r2.isChecked())
		{
			getreport2();
			
		}
		
	}
	public void getreport1()
	{
		Intent i =new Intent(mainstudentreview.this,studentreport1.class);
		Bundle bundle = new Bundle();
		bundle.putString("wel",id);
		bundle.putString("welcome",uname);
		i.putExtras(bundle);
	    startActivity(i);
	}
	  
	public void getreport2()
	{
		Intent i =new Intent(mainstudentreview.this,studentreport2.class);
		Bundle bundle = new Bundle();
		bundle.putString("wel",id);
		bundle.putString("welcome",uname);
		i.putExtras(bundle);
	    startActivity(i);
	}
	
	public void getnext()
	 {
		Intent i1 = new Intent(this,selectquiz.class);
		Bundle bundle = new Bundle();
		bundle.putString("wel",id);
		bundle.putString("welcome",uname);
		i1.putExtras(bundle);
		startActivity(i1);
	    
		
	 }
	  

	
	
}
