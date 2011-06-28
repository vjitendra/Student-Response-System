package com.SRS6;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class mailbox extends Activity  {
	
	 
	DataBaseHelper  data;
	TextView tv,logout,welcome,newmail,sent,inbox,from,date,to;
	TableLayout tbl1,tbl2,tbl3;
	String str1,stunm,onlyname,cmpltname,sdate;
	String[] user_login,rec,send,dt,att,title,body,viewed;
	String[] rec1,send1,dt1,att1,title1,body1,viewed1;
	String[] rec2,send2,dt2,att2,title2,body2,viewed2;
	int[] mid,mid1,mid2;
	int insmid,curtimestamp;
	Dialog myDialog,myDialog1;
	
	String insstruserlogin,insstrrec,insstrsend,insstrdt,insstratt,insstrtitle,insstrbody;
	private static final String NAMESPACE = "http://tempuri.org/";      
	private static final String URL = "http://192.168.1.204/studentresponse/Service.asmx"; 
	
	private static final String SOAP_ACTION = "http://tempuri.org/GetPersonalMSGDetail";      
	private static final String METHOD_NAME = "GetPersonalMSGDetail";       
	
	private static final String SOAP_ACTION1 = "http://tempuri.org/insertMsgData";      
	private static final String METHOD_NAME1 = "insertMsgData";   
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mailbox);
        
        Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		System.out.println("Month is:"+month);
		int year = cal.get(Calendar.YEAR);   
		
		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		curtimestamp=(int) (cal.getTimeInMillis()/1000L);
		
		System.out.println("Time In Millis:"+curtimestamp); 
		
		sdate=(+ day + "-" + (month + 1) + "-" + year);
        
        data=new DataBaseHelper(this);
        
        logout=(TextView)findViewById(R.id.logout);
        welcome=(TextView)findViewById(R.id.welcome);
        newmail=(TextView)findViewById(R.id.newmail);
        inbox=(TextView)findViewById(R.id.inbox);
        sent=(TextView)findViewById(R.id.sent);
        
        Bundle bundle = getIntent().getExtras();
   	 	cmpltname=bundle.getString("wel");
   		onlyname=bundle.getString("welcome");
        
        tbl2=(TableLayout)findViewById(R.id.mail_table2);
        tbl2.setBackgroundResource(R.layout.shapes);
        
        tbl3=(TableLayout)findViewById(R.id.mail_table3);
        tbl3.setBackgroundResource(R.layout.shapes);
        tbl3.setVisibility(View.GONE);
        
        welcome.setText("Welcome"+" "+cmpltname);
        
        newmail.setOnClickListener(new TextView.OnClickListener() {        	 
            public void onClick(View v) {
    	        	myDialog = new Dialog(mailbox.this);
    	        	myDialog.setContentView(R.layout.newmail);
    	        	myDialog.setTitle("Compose Mail");
    	        	myDialog.setCancelable(true); 
    	        	
    	        	final TextView from=(TextView) myDialog.findViewById(R.id.txtfrom);
    	        	final TextView date=(TextView) myDialog.findViewById(R.id.txtdate);
    	        	final EditText to=(EditText) myDialog.findViewById(R.id.editto);
    	        	final EditText sub=(EditText) myDialog.findViewById(R.id.editsub);
    	        	final EditText body=(EditText) myDialog.findViewById(R.id.editbody);
    	        	final EditText att=(EditText) myDialog.findViewById(R.id.editatt);
                    Button button = (Button) myDialog.findViewById(R.id.btnsend);
                    
                    from.setText(onlyname);
                    date.setText(sdate);
                    button.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                    	
                    	String from3=from.getText().toString();
                    	System.out.println("from:"+from3);
                    	String date3=date.getText().toString();
                    	System.out.println("date:"+date3);
                    	String to3=to.getText().toString();
                    	System.out.println("to:"+to3);
                    	String sub3=sub.getText().toString();
                    	System.out.println("title:"+sub3);
                    	String body3=body.getText().toString();   
                    	System.out.println("body:"+body3);
                    	String att3=att.getText().toString();
                    	System.out.println("att:"+att3);
                    	String status="N";
                    	
                    	data.Insertmailboxto(onlyname, from3, to3, date3, att3, sub3, body3, status);
                    	
                    	Toast.makeText(mailbox.this, "Message Sent Successfully",Toast.LENGTH_SHORT).show();
                    	
                    	insertmail(); 
                    	                   	
                    	myDialog.dismiss();
                        }
                    });
        
                    myDialog.show();
                }
            });
        
        inbox.setOnClickListener(new TextView.OnClickListener() {        	 
            public void onClick(View v) {
    	        	tbl3.setVisibility(View.GONE);
    	        	tbl2.removeAllViews();
    	        	getinbox();
    	        	tbl2.setVisibility(View.VISIBLE); 
                }
            });
        sent.setOnClickListener(new TextView.OnClickListener() {        	 
            public void onClick(View v) {
    	        	tbl2.setVisibility(View.GONE);
    	        	
    	        	tbl3.removeAllViews();
    	        	
    	        	getsent();
    	        	tbl3.setVisibility(View.VISIBLE); 
                }
            });
       data.deletemailboxfrom();    
        call();
        
    } 
    
    public void call()
	 {  
		
		 try { 
			 		  
			    
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		 		
		 		request.addProperty("User_Login",onlyname);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION, envelope);
		    	 Object result = (Object)envelope.getResponse();
		 	      str1=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 		   	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		        	alertbox.setMessage(onlyname+" "+"you dont have any mails to see.... ");
	    		     
		        	// set a positive/yes button and create a listener
	    			alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	    				 
	                    // click listener on the alert box
	                    public void onClick(DialogInterface arg0, int arg1) {
	                        // the button was clicked
	                        //Toast.makeText(getApplicationContext(), "OK button clicked", Toast.LENGTH_LONG).show();
	                    }
	                });
	     
	                // show it
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
		    	  System.out.println("String"+str1); 
		    	  
		        }
		        String[] arrauserData;
		        arrauserData = ((String) str1).split("; ");
		         
		        int temp2=arrauserData.length;
		        int temp1=((arrauserData.length)/10);
		        
		        
		        mid=new int[temp1];
		        user_login=new String[temp1];     
		        rec=new String[temp1];
		        send=new String[temp1];
		        dt=new String[temp1];
		        att=new String[temp1];     
		        title=new String[temp1];
		        body=new String[temp1];
		        viewed=new String[temp1];
		        
		        
		         
		       // System.out.println("Length:"+temp1);
		         
		        
		        for(int a=0,b=0;a<temp2;a=a+10)
		        {
		        	mid[b]=Integer.parseInt(arrauserData[a]);
		        	b++;
	 	                	
		         }
		                 
		         for(int a=1,b=0;a<temp2+1;a=a+10)
		        {
		        	rec[b]=arrauserData[a];
		        	b++;
		                	 
		        }
		         
		        for(int c=2,d=0;c<temp2;c=c+10) 
		        {
		        	 
		        send[d]=arrauserData[c];
		        
		        d++;
		        }
		        for(int c=3,d=0;c<temp2;c=c+10)
		        {
		        	
		        dt[d]=arrauserData[c];
		        
		        d++;
		        }
		        for(int a=4,b=0;a<temp2;a=a+10)
		        {
		        	att[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        
		         for(int a=5,b=0;a<temp2+1;a=a+10)
		        {
		        	title[b]=arrauserData[a];
		        	b++;
		                	 
		        }
		         
		        for(int c=6,d=0;c<temp2;c=c+10) 
		        {
		        	 
		        body[d]=arrauserData[c];
		        
		        d++;     
		        }
		        for(int c=8,d=0;c<temp2;c=c+10) 
		        {
		        	
		        viewed[d]=arrauserData[c];
		        
		        d++;
		        }
		        
		        for(int x=0;x<temp1;x++) 
		        { 
		         
               	data.Insertmailboxfrom(rec[x], send[x], dt[x], att[x], title[x], body[x], viewed[x]);
               	
    
		        }
		        getinbox();
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 //text1.setText(e.getMessage()); 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }                       
    
    public void sendmail()
	 {  
		
		 try {      
			  		  
			    
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);
		 		
		 		request.addProperty("UserLogin",insstrrec); 
		 		request.addProperty("recipient",onlyname);
		 		request.addProperty("sender",insstrsend);
		 		request.addProperty("TimeStamp",curtimestamp);
		 		request.addProperty("Attachment",insstratt);
		 		request.addProperty("Title",insstrtitle);   
		 		request.addProperty("Body",insstrbody); 
		 		request.addProperty("FolderId","0");
		 		request.addProperty("Viewed","0");
		 		request.addProperty("Priority","0");
		 		
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION1, envelope);
		 		Toast.makeText(mailbox.this, "Mail send successfully!!!!!!!",Toast.LENGTH_SHORT).show();
		 		
		 		data.updatemail(insmid, onlyname);
		    	 
		 }
	 	    catch (Exception e)    
	        {
			 //text1.setText(e.getMessage()); 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }
    
    public void getinbox()
	 {
		 
		 
		 Cursor c1=data.selectstuinbox(onlyname);
		 mid1=new int[c1.getCount()];
		 rec1=new String[c1.getCount()];
		 send1=new String[c1.getCount()];
		 dt1=new String[c1.getCount()];
		 att1=new String[c1.getCount()];
		 title1=new String[c1.getCount()];
		 body1=new String[c1.getCount()];
		 viewed1=new String[c1.getCount()];
		 int i=0;
		
			while(c1.moveToNext())
			{
				int intmid=c1.getInt(0);
				String strrec=c1.getString(1);
				String strsend=c1.getString(2);
				String strdt=c1.getString(3);
				String stratt=c1.getString(4);
				String strtitle=c1.getString(5);
				String strbody=c1.getString(6);
				String strviewed=c1.getString(7);
				//System.out.println("Course name:"+course);
				
				mid1[i]=intmid;
				rec1[i]=strrec;
				send1[i]=strsend;
				dt1[i]=strdt;
				att1[i]=stratt;
				title1[i]=strtitle;
				body1[i]=strbody;
				viewed1[i]=strviewed;
				i++;
				
			}
			getreportinbox();
			
		 			
	 } 
    
    public void insertmail()
	 {
		 
		 
		 Cursor c1=data.selectmailboxto(onlyname);
		 	
			while(c1.moveToNext())
			{
				 insmid=c1.getInt(0);
				 insstruserlogin=c1.getString(1);
				 insstrrec=c1.getString(2);
				 insstrsend=c1.getString(3);
				 insstrdt=c1.getString(4);
				 insstratt=c1.getString(5);
				 insstrtitle=c1.getString(6);
				 insstrbody=c1.getString(7);
				 
				 
				 
				 
				 System.out.println("Id:"+insmid);
				 System.out.println("Login name:"+insstruserlogin);
				 System.out.println("Reciever:"+insstrrec);
				 System.out.println("Sender:"+insstrsend);
				 System.out.println("Date:"+insstrdt);
				 System.out.println("Attachment:"+insstratt);
				 System.out.println("Subject:"+insstrtitle);
				 System.out.println("Body:"+insstrbody);
				 sendmail();
			}
			
			
		 			
	 } 
    public void getsent()
	 {
		 
		 
		 Cursor c1=data.selectstusent(onlyname);
		 mid2=new int[c1.getCount()];
		 rec2=new String[c1.getCount()];
		 send2=new String[c1.getCount()];
		 dt2=new String[c1.getCount()];
		 att2=new String[c1.getCount()];
		 title2=new String[c1.getCount()];
		 body2=new String[c1.getCount()];
		 viewed2=new String[c1.getCount()];
		 int i=0;
		
			while(c1.moveToNext())
			{
				int intmid=c1.getInt(0);
				String strrec=c1.getString(1);
				String strsend=c1.getString(2);
				String strdt=c1.getString(3);
				String stratt=c1.getString(4);
				String strtitle=c1.getString(5);
				String strbody=c1.getString(6);
				String strviewed=c1.getString(7);
				//System.out.println("Course name:"+course);
				
				mid2[i]=intmid;
				rec2[i]=strrec;
				send2[i]=strsend;
				dt2[i]=strdt;
				att2[i]=stratt;
				title2[i]=strtitle;
				body2[i]=strbody;
				viewed2[i]=strviewed;
				i++;
				
			}
			getreportsent();
			 
		 			
	 } 
    
    public void getreportinbox() 
	 {
   	int i=0;
   	
   	TableRow tr1 = new TableRow(this); 
    tr1.setId(100+i);
    tr1.layout(2, 0, 0, 0); 
    
 // Create a TextView to house the name of the province
    final TextView label2= new TextView(this);
    
           
    label2.setId(300+i);
    label2.setText("     "); 
    label2.setTextColor(Color.BLACK);
    label2.setPadding(20, 0, 0, 0);
    tr1.setPadding(0, 1, 0, 1);
    tr1.addView(label2);
 
               
    
 // Create a TextView to house the name of the province
    final TextView labeltitle2= new TextView(this);
    
           
    labeltitle2.setId(300+i);
    labeltitle2.setText("Status  "); 
    labeltitle2.setTextColor(Color.BLACK);
    labeltitle2.setPadding(20, 0, 0, 0);
    tr1.setPadding(0, 1, 0, 1);
    
   
    tr1.addView(labeltitle2);
    
    
 // Create a TextView to house the value of the after-tax income
     final TextView labelrec2 = new TextView(this);
     
     
   
    
     labelrec2.setId(400+i);
     labelrec2.setText("Subject");
     labelrec2.setTextColor(Color.BLACK);
     labelrec2.setPadding(20, 0, 0, 0);
     tr1.setPadding(0, 1, 0, 1);
     
     
     tr1.addView(labelrec2);
    
 // Create a TextView to house the name of the province
    final TextView labelsend2 = new TextView(this);
    
           
    labelsend2.setId(500+i);
    labelsend2.setText("From"); 
    labelsend2.setTextColor(Color.BLACK);
    labelsend2.setPadding(20, 0, 0, 0);
    tr1.setPadding(0, 1, 0, 1);
    
    
    tr1.addView(labelsend2);
    
    
    
        
    // Create a TextView to house the value of the after-tax income
    final TextView labeldt2 = new TextView(this);
    	            
  
   
    labeldt2.setId(600+i);
    labeldt2.setText("Date");
    labeldt2.setTextColor(Color.BLACK); 
    labeldt2.setPadding(20, 0, 0, 0);
    tr1.setPadding(0, 1, 0, 1);
    
    
    tr1.addView(labeldt2);
    
     // Add the TableRow to the TableLayout 
    tbl2.addView(tr1, new TableLayout.LayoutParams(
            LayoutParams.FILL_PARENT,
            LayoutParams.WRAP_CONTENT));
   	   	    	  	  
   	for(int i1=0;i1<rec1.length;i1++)  
   	{ 
   		
				try
				{
				TableRow tr = new TableRow(this); 
	            tr.setId(100+i);
	            tr.layout(2, 0, 0, 0); 
	            
	            
	         // Create a TextView to house the name of the province
	            CheckBox chk=new CheckBox(this);;
	            	            
	            	                   
	            	            chk.setId(200+i);
	            	            chk.setText(""); 
	            	            chk.setPadding(20, 0, 0, 0);
	            	            tr.setPadding(0, 1, 0, 1);
	            	                      
	            	            tr.addView(chk);
	            
	            // Create a TextView to house the name of the province
	            ImageView imageviewed = new ImageView(this);
	            
	                   
	            imageviewed.setId(200+i);
	            if(viewed1[i1].equals("0"))
	            {
	            	imageviewed.setImageResource(R.drawable.mail);
	            }
	            else
	            {
	            	imageviewed.setImageResource(R.drawable.chkmail);
	            }
	            imageviewed.setPadding(20, 0, 0, 0);
	            tr.setPadding(0, 1, 0, 1);
	            
	            tr.addView(imageviewed);
	            
	         // Create a TextView to house the name of the province
	            final TextView labeltitle = new TextView(this);
	            
	                   
	            labeltitle.setId(300+i);
	            labeltitle.setText(title1[i1]); 
	            labeltitle.setTextColor(Color.BLACK);
	            labeltitle.setPadding(20, 0, 0, 0);
	            tr.setPadding(0, 1, 0, 1);
	            
	            tr.addView(labeltitle);
	            
	         // Create a TextView to house the name of the province
	            final TextView labelsend = new TextView(this);
	            
	                   
	            labelsend.setId(500+i);
	            labelsend.setText(send1[i1]); 
	            labelsend.setTextColor(Color.BLACK);
	            labelsend.setPadding(20, 0, 0, 0);
	            tr.setPadding(0, 1, 0, 1);
	            
	            tr.addView(labelsend);
	            
	            /*// Create a TextView to house the value of the after-tax income
	            final TextView labelrec = new TextView(this);
	            
	            
	          
	           
	            labelrec.setId(400+i); 
	            labelrec.setText(""+rec1[i1]);
	            labelrec.setTextColor(Color.BLACK);
	            
	            tr.addView(labelrec);*/
	            
	            int date1=Integer.parseInt(dt1[i1]);
	            
	            Date date = new Date( date1* 1000L); 
	            
	            SimpleDateFormat postFormater = new SimpleDateFormat("dd-MM-yyyy"); 
	            
	            String newDateStr = postFormater.format(date); 
	            
	            
	            // Create a TextView to house the value of the after-tax income
	            final TextView labeldt = new TextView(this);
	            
	            
	          
	           
	            labeldt.setId(600+i);
	            labeldt.setText(""+newDateStr);
	            labeldt.setTextColor(Color.BLACK);
	            labeldt.setPadding(20, 0, 0, 0);
	            tr.setPadding(0, 1, 0, 1);
	            
	            tr.addView(labeldt);
	            
	            final TextView labelbody = new TextView(this);
	            
	            
		          
		           
	            labelbody.setId(700+i);
	            labelbody.setText(""+body1[i1]);
	            labelbody.setTextColor(Color.BLACK);
	            labelbody.setVisibility(View.GONE);
	            labelbody.setPadding(20, 0, 0, 0);
	            tr.setPadding(0, 1, 0, 1);
	            
	            
	            tr.addView(labelbody);

	            // Add the TableRow to the TableLayout 
	            tbl2.addView(tr, new TableLayout.LayoutParams(
	                    LayoutParams.FILL_PARENT,
	                    LayoutParams.WRAP_CONTENT));
	            i++; 
	            
	            labeltitle.setOnClickListener(new TextView.OnClickListener()  
	        	{ 
	            	
	            	public void onClick (View v)
	        		{ 
	            		
	            		System.out.println("Hi Clicked");
	            		myDialog1 = new Dialog(mailbox.this);
	    	        	myDialog1.setContentView(R.layout.showmail);
	    	        	myDialog1.setTitle("Inbox");
	    	        	myDialog1.setCancelable(true);
	    	        	
	    	        	TextView from = (TextView) myDialog1.findViewById(R.id.txtfrom);
	    	        	TextView date = (TextView) myDialog1.findViewById(R.id.txtdate);
	    	        	TextView to = (TextView) myDialog1.findViewById(R.id.txtto);
	    	        	TextView title = (TextView) myDialog1.findViewById(R.id.txttitle);
	    	        	EditText body = (EditText) myDialog1.findViewById(R.id.editto);
	                    Button button = (Button) myDialog1.findViewById(R.id.btnback);
	                    
	                    //String rec1=labelrec.getText().toString();
	                    
	                    String date1=labeldt.getText().toString();
	                    
	                    String send1=labelsend.getText().toString();
	                    
	                    String title1=labeltitle.getText().toString();
	                    
	                    String body1=labelbody.getText().toString();
	                    
	                    
	                    from.setText(send1);
	                    date.setText(date1);
	                   // to.setText(rec1);
	                    title.setText(title1);
	                    body.setText(body1);
	                    button.setText("Back To Inbox");
	                    
	                    button.setOnClickListener(new OnClickListener() {
	                    public void onClick(View v) {
	                    	myDialog1.dismiss();
	                        } 
	                    });
	        
	                    myDialog1.show();
	            		}
	        		
	        	});
				}
				
				catch (Exception e)    
		        {
		        tv.setText(e.getMessage());
		        }
			}
	 }
    public void getreportsent() 
	 {
  	int i=0;
  	
  	TableRow tr1 = new TableRow(this); 
    tr1.setId(100+i);
    tr1.layout(2, 0, 0, 0); 
    
 // Create a TextView to house the name of the province
    final TextView label2= new TextView(this);
    
           
    label2.setId(300+i);
    label2.setText("      "); 
    label2.setTextColor(Color.BLACK);
    label2.setPadding(20, 0, 0, 0);
    tr1.setPadding(0, 1, 0, 1);
    
   
    tr1.addView(label2); 
 
               
    
 // Create a TextView to house the name of the province
    final TextView labeltitle2= new TextView(this);
    
           
    labeltitle2.setId(300+i);
    labeltitle2.setText("Subject"); 
    labeltitle2.setTextColor(Color.BLACK);
    labeltitle2.setPadding(20, 0, 0, 0);
    tr1.setPadding(0, 1, 0, 1);
    
   
    tr1.addView(labeltitle2);
    
 /*// Create a TextView to house the name of the province
    final TextView labelsend2 = new TextView(this);
    
           
    labelsend2.setId(500+i);
    labelsend2.setText("From"); 
    labelsend2.setTextColor(Color.BLACK);
    
    tr1.addView(labelsend2);*/
    
    // Create a TextView to house the value of the after-tax income
    final TextView labelrec2 = new TextView(this);
    
    
  
   
    labelrec2.setId(400+i);
    labelrec2.setText("To");
    labelrec2.setTextColor(Color.BLACK);
    labelrec2.setPadding(20, 0, 0, 0);
    tr1.setPadding(0, 1, 0, 1);
    
    
    tr1.addView(labelrec2);
    
        
    // Create a TextView to house the value of the after-tax income
    final TextView labeldt2 = new TextView(this);
    	            
  
   
    labeldt2.setId(600+i);
    labeldt2.setText("Date");
    labeldt2.setTextColor(Color.BLACK); 
    labeldt2.setPadding(20, 0, 0, 0);
    tr1.setPadding(0, 1, 0, 1);
    
    
    tr1.addView(labeldt2);
    
     // Add the TableRow to the TableLayout 
    tbl3.addView(tr1, new TableLayout.LayoutParams(
            LayoutParams.FILL_PARENT,
            LayoutParams.WRAP_CONTENT));
  	 	    	  	 
  	for(int i1=0;i1<rec2.length;i1++)  
  	{
  		
				try
				{
				TableRow tr = new TableRow(this); 
	            tr.setId(100+i);
	            tr.layout(2, 0, 0, 0); 
	            
	            
	         // Create a TextView to house the name of the province
	            final CheckBox chk1=new CheckBox(this);;
	                	                   
	            	            chk1.setId(200+i);
	            	            chk1.setText(""); 
	            	            chk1.setPadding(20, 0, 0, 0);
	            	            tr.setPadding(0, 1, 0, 1); 
	            	            
	            	            tr.addView(chk1);
	            
	                       
	            
	         // Create a TextView to house the name of the province
	            final TextView labeltitle1 = new TextView(this);
	            
	                   
	            labeltitle1.setId(300+i);
	            labeltitle1.setText(title2[i1]); 
	            labeltitle1.setTextColor(Color.BLACK);
	            labeltitle1.setPadding(20, 0, 0, 0);
	            tr.setPadding(0, 1, 0, 1);
	            
	            tr.addView(labeltitle1);
	            
	         /*// Create a TextView to house the name of the province
	            final TextView labelsend1 = new TextView(this);
	            
	                   
	            labelsend1.setId(500+i);
	            labelsend1.setText(send2[i1]); 
	            labelsend1.setTextColor(Color.BLACK);
	            
	            tr.addView(labelsend1);*/
	            
	            // Create a TextView to house the value of the after-tax income
	            final TextView labelrec1 = new TextView(this);
	            
	            
	          
	           
	            labelrec1.setId(400+i);
	            labelrec1.setText(""+rec2[i1]);
	            labelrec1.setTextColor(Color.BLACK);
	            labelrec1.setPadding(20, 0, 0, 0);
	            tr.setPadding(0, 1, 0, 1);
	            
	            tr.addView(labelrec1);
	            
	            int date1=Integer.parseInt(dt2[i1]);
	            
	            Date date = new Date( date1* 1000L); 
	            
	            SimpleDateFormat postFormater = new SimpleDateFormat("dd-MM-yyyy"); 
	            
	            String newDateStr = postFormater.format(date); 
	            
	            
	            // Create a TextView to house the value of the after-tax income
	            final TextView labeldt1 = new TextView(this);
	            	            
	          
	           
	            labeldt1.setId(600+i);
	            labeldt1.setText(""+newDateStr);
	            labeldt1.setTextColor(Color.BLACK); 
	            labeldt1.setPadding(20, 0, 0, 0);
	            tr.setPadding(0, 1, 0, 1);
	            
	            tr.addView(labeldt1);
	            
	            final TextView labelbody1 = new TextView(this);
	            
	            
		           
		           
	            labelbody1.setId(700+i);
	            labelbody1.setText(""+body2[i1]);
	            labelbody1.setTextColor(Color.BLACK);
	            labelbody1.setVisibility(View.GONE);
	            
	            labelbody1.setPadding(20, 0, 0, 0);
	            tr.setPadding(0, 1, 0, 1);
	            
	            tr.addView(labelbody1);  


	            // Add the TableRow to the TableLayout 
	            tbl3.addView(tr, new TableLayout.LayoutParams(
	                    LayoutParams.FILL_PARENT,
	                    LayoutParams.WRAP_CONTENT));
	            i++; 
	            
	            labeltitle1.setOnClickListener(new TextView.OnClickListener()  
	        	{ 
	            	
	            	public void onClick (View v)
	        		{ 
	            		
	            		
	            		myDialog1 = new Dialog(mailbox.this);
	    	        	myDialog1.setContentView(R.layout.showmail);
	    	        	myDialog1.setTitle("Sent Mails");
	    	        	myDialog1.setCancelable(true);
	    	        	
	    	        	TextView from = (TextView) myDialog1.findViewById(R.id.txtfrom);
	    	        	TextView date = (TextView) myDialog1.findViewById(R.id.txtdate);
	    	        	TextView to = (TextView) myDialog1.findViewById(R.id.txtto);
	    	        	TextView title = (TextView) myDialog1.findViewById(R.id.txttitle);
	    	        	EditText body = (EditText) myDialog1.findViewById(R.id.editto);
	                    Button button = (Button) myDialog1.findViewById(R.id.btnback);
	                    
	                    String rec1=labelrec1.getText().toString();
	                    
	                    String date1=labeldt1.getText().toString();
	                    
	                    //String send1=labelsend1.getText().toString();
	                    
	                    String title1=labeltitle1.getText().toString();
	                    
	                    String body1=labelbody1.getText().toString();
	                    button.setText("Back To Sent mails");
	                    
	                    //from.setText(send1);
	                    date.setText(date1);
	                    to.setText(rec1);
	                    title.setText(title1);
	                    body.setText(body1);
	                    
	                    button.setOnClickListener(new OnClickListener() {
	                    public void onClick(View v) {
	                    	myDialog1.dismiss();
	                        } 
	                    });
	        
	                    myDialog1.show();  
	            		}
	        		
	        	});
				}
	            
	            
				catch (Exception e)    
		        {
		        tv.setText(e.getMessage());
		        }
			}
	 }
}