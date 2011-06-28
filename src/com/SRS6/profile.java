package com.SRS6;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;






import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class profile extends Activity implements OnItemClickListener {
	String stunm,str1;
	TextView welstu,text1,tv;
	ImageView stupic;
	String[] coursenm,lessonnm,testnm,stuname,proname,firstname,lastname,pronm;
	DataBaseHelper  data;
	ListView lv1;
	ImageView img; 
	TextView review,mailbox,reward;
	String s1,test,courses,fullname,name,lessons,tests,type;
	int qid,qcount,qid1;
	TableLayout tl,tl1,tl2,tl3,tl4,mailtl,rewardtl,reqtl;
	int i1,temp3,count,tcount;
	int[] strquizno;
	String[] strquizname;
	Dialog myDialog;
	private ArrayList<String> res=new ArrayList<String>();
	private ArrayList<String> res1=new ArrayList<String>(); 
	
	String[] course1,lesson1,test1;
	
	private static final String NAMESPACE = "http://tempuri.org/";      
	private static final String URL = "http://192.168.1.204/studentresponse/Service.asmx"; 
	
	private static final String SOAP_ACTION = "http://tempuri.org/user_profile";      
	private static final String METHOD_NAME = "user_profile";      
		
	private static final String SOAP_ACTION1 = "http://tempuri.org/gettest";  
	private static final String METHOD_NAME1 = "gettest";  
	
	private static final String SOAP_ACTION2 = "http://tempuri.org/user_Name";  
	private static final String METHOD_NAME2 = "user_Name";  
	
	private static final String SOAP_ACTION3 = "http://tempuri.org/LessonUser_Name";  
	private static final String METHOD_NAME3 = "LessonUser_Name";  
	
	private static final String SOAP_ACTION4 = "http://tempuri.org/getquestions";      
	private static final String METHOD_NAME4 = "getquestions"; 
	 
	
	public void onCreate(Bundle icicle)
	 {
		 requestWindowFeature(Window.FEATURE_NO_TITLE);  
	     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	     

	 super.onCreate(icicle);
	 setContentView(R.layout.profiles);
	 
	 Bitmap bitmap =DownloadImage("http://bpsius:Bpsi%40123@bpsi.us/propic.JPG");
	
    
     	img = (ImageView) findViewById(R.id.img);
     	img.setImageBitmap(bitmap);	
     	
             
	 
	 data=new DataBaseHelper(this);
	 
	 welstu=(TextView)findViewById(R.id.welcome);
	 
	 review=(TextView)findViewById(R.id.reviews);
	 
	 mailbox=(TextView)findViewById(R.id.mailbox);
	 
	 reward=(TextView)findViewById(R.id.rewards);
	 
	 
	 tl=(TableLayout)findViewById(R.id.tbl1);
     tl.setBackgroundResource(R.layout.shapes);
     
     tl1=(TableLayout)findViewById(R.id.tbl2);
     tl1.setBackgroundResource(R.layout.shapes);
     
     tl2=(TableLayout)findViewById(R.id.tbl3);
     tl2.setBackgroundResource(R.layout.shapes);
     
     tl4=(TableLayout)findViewById(R.id.tbl4);
     tl4.setBackgroundResource(R.layout.shapes);
          
    	 
	 Bundle bundle = getIntent().getExtras(); 
	 s1=bundle.getString("wel");
	 stunm=bundle.getString("welcome");
	 type=bundle.getString("type");
	 
	 
	 
	 review.setMovementMethod(LinkMovementMethod.getInstance()); 
	 
	 review.setOnClickListener(new TextView.OnClickListener() 
		{ public void onClick (View v)
			{ 
				
				funreview();
			}
		
     
		});
	 
	 mailbox.setMovementMethod(LinkMovementMethod.getInstance());
	 
	 mailbox.setOnClickListener(new TextView.OnClickListener() 
		{ public void onClick (View v)
			{ 
				
				funmailbox();
			}
		
     
		});
	 
 reward.setMovementMethod(LinkMovementMethod.getInstance());
	 
	 reward.setOnClickListener(new TextView.OnClickListener() 
		{ public void onClick (View v)
			{ 
				
				funreward();
			}
		
     
		});
	 
 	 welstu.setText(s1); 
	 data.deleteprofile();
	 call1();
	 call(); 
	 
	
	 }
	private Bitmap DownloadImage(String URL)
    {        
        Bitmap bitmap = null;
        InputStream in = null;        
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return bitmap;                
    }
    
    private InputStream OpenHttpConnection(String urlString) 
    throws IOException
    {
        InputStream in = null;
        int response = -1;
               
        URL url = new URL(urlString); 
        URLConnection conn = url.openConnection();
                 
        if (!(conn instanceof HttpURLConnection))                     
            throw new IOException("Not an HTTP connection");
        
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect(); 

            response = httpConn.getResponseCode();                 
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();                                 
            }                     
        }
        catch (Exception ex)
        {
        	img = (ImageView) findViewById(R.id.img);
            throw new IOException("Error connecting");            
        }
        return in;     
    }
	   
	 public void call()
	 { 
		
		 try {
			 		  
			 	 //data.deleteprofile(); 
 	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		 		
		 		request.addProperty("struserid",stunm);
		 		
		 			         		 
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
		 		   tl1.setVisibility(View.GONE);
		 		   tl2.setVisibility(View.GONE);
		        	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		        	alertbox.setMessage(stunm+" "+"you are yet not registered to any courses...you need to register first.... ");
	    		     
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
		        int temp1=((arrauserData.length)/4);
		        stuname=new String[temp1]; 
		        coursenm=new String[temp1];
		        lessonnm=new String[temp1];
		        testnm=new String[temp1];
		        
		        
		         
		       // System.out.println("Length:"+temp1);
		         
		        for(int a=0,b=0;a<temp2;a=a+4)
		        {
		        	stuname[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        
		         for(int a=1,b=0;a<temp2+1;a=a+4)
		        {
		        	coursenm[b]=arrauserData[a];
		        	b++;
		                	 
		        }
		         
		        for(int c=2,d=0;c<temp2;c=c+4) 
		        {
		        	 
		        lessonnm[d]=arrauserData[c];
		        
		        d++;
		        }
		        for(int c=3,d=0;c<temp2;c=c+4)
		        {
		        	
		        testnm[d]=arrauserData[c];
		        
		        d++;
		        }
		        
		        for(int x=0;x<temp1;x++) 
		        {
		         
                	data.Insertprofile(stuname[x],coursenm[x],lessonnm[x],testnm[x]);
                	
     
		        }
		        getcourse();
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 text1.setText(e.getMessage()); 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }
	 
	 public void getcourse()
	 {
		 
		 
		 Cursor c1=data.selectprofilecourse(stunm);
		 course1=new String[c1.getCount()];
		 int i=0;
		
			while(c1.moveToNext())
			{
				
				String course=c1.getString(0);
				System.out.println("Course name:"+course);
				
				course1[i]=course;
				i++;
				
			}
			
		 
		 
			getreport();
	 } 
	 
	 public void getlessoncount(String course2)
	 {
		
		 
		 Cursor c1=data.selectprofilelesson(course2);
		 
		 count=c1.getCount();
		 	 
	 }
	 public void getlesson(String course2)
	 {
		
		 
		 Cursor c1=data.selectprofilelesson(course2);
		 lesson1=new String[c1.getCount()];
		 int count=c1.getCount();
		 int i=0;
			while(c1.moveToNext())
			{
				
				String lesson=c1.getString(0);
				System.out.println("Lesson name:"+lesson);
				lesson1[i]=lesson;
				i++;
				

				
			}
		 
		 getlessons();
		 
	 }
	 public void gettestcount(String lesson2)
	 {
		
		 
		 Cursor c1=data.selectprofiletest(lesson2);
		 tcount=c1.getCount();
		 	 
	 }
	 public void gettest(String lesson2)
	 {
		
		 
		 Cursor c1=data.selectprofiletest(lesson2);
		 test1=new String[c1.getCount()];
		 int i=0;
			while(c1.moveToNext())
			{
				
				String test=c1.getString(0);
				System.out.println("Lesson name:"+test);
				test1[i]=test;
				i++;
				

				
			}
		 
		 gettests();
		 
	 }
	 public void getprofessorname()
	 {
		
		 
		 Cursor c1=data.selectprofessorname();
		 pronm=new String[c1.getCount()];
		 int i=0;
			while(c1.moveToNext())
			{
				
				String proname=c1.getString(1);
				System.out.println("Professor names are :"+proname);
				pronm[i]=proname;
				i++;
				

				
			}
			name=pronm[0];
		 for(int j=1;j<pronm.length;j++)
		 {
			 fullname=pronm[j];
			 
			name=name+","+fullname;
			
		 }
		 
		 
		 
	 }
	 
	 public void getreport() 
	 {
    	int i=0;
    	int flag=0;
    	
    	
    	final TableRow tr2 = new TableRow(this); 
        tr2.setId(100+i);
        tr2.layout(0, 0, 0, 0); 
        
     // Create a TextView to house the name of the province
        final TextView labelTV1 = new TextView(this);
       
               
        labelTV1.setId(200+i);
        labelTV1.setText("My Courses"); 
       // labelTV1.setBackgroundColor("#E77471");
        
        labelTV1.setTextColor(Color.RED);
        tr2.addView(labelTV1);
         // Add the TableRow to the TableLayout 
            tl1.addView(tr2, new TableLayout.LayoutParams(
                     LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
    	
    	for( i1=0;i1<course1.length;i1++) 
    	{
    		
				try
				{
			          
				final TableRow tr = new TableRow(this); 
	            tr.setId(100+i);
	            tr.layout(0, 0, 0, 0); 
	            final String course2=course1[i1];
	            
	         
	            
	         // Create a TextView to house the name of the province
	            final TextView labelTV = new TextView(this);
	           
	                   
	           /* labelTV.setId(200+i);
	           SpannableString content = new SpannableString(course2);
	            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
	            
	            labelTV.setText(content); */
	            labelTV.setText(course2);
	            System.out.println("Course namessssssss:"+course1[i1]); 
	            labelTV.setTextColor(Color.BLACK);
	            
	            tr.addView(labelTV);
	            final Button btndetails = new Button(this);
		           
                
		           	btndetails.setText("Details");
		            btndetails.setTextColor(Color.BLACK);
		            //btndetails.setWidth(30);
	               
	            
	            
	            tr.addView(btndetails);
	         // Add the TableRow to the TableLayout 
	            tl1.addView(tr, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));
	            
	            
	            
	                  
	            i++;  
	            
	            //labelTV.setMovementMethod(LinkMovementMethod.getInstance());
	            
	            labelTV.setOnClickListener(new TextView.OnClickListener()  
	        	{ 
	            	
	            	public void onClick (View v)
	        		{ 
	            		tl2.removeAllViews();
	            		
	                    
	            		
	        		  String courses=(String) labelTV.getText();
	        		  getlesson(courses);
	        		  tl1.removeAllViews();
	        		  getreport();
	        		}
	        	});
	            btndetails.setOnClickListener(new Button.OnClickListener()  
	        	{ 
	            	
	            	public void onClick (View v)
	        		{ 
	            			            		
	        		   courses=(String) labelTV.getText();
	        		  System.out.println("Course Name is:"+courses);
	        		  //getprofessors(courses);
	        		  data.deleteprofessorname();
	        		  getlessoncount(courses);
	        		  callprofessor();
	        		  
	        		}
	        	});
	            }
				
				
				catch (Exception e)    
		        {
		        text1.setText(e.getMessage()); 
		        System.out.println("Error msg:::::::::"+e.getMessage());
		        }
			}
	 }
	 public void getlessons() 
	 {
    	int i=0;
    	int flag=0;
    	final TableRow tr2 = new TableRow(this);  
    	final TextView labelmylesson = new TextView(this);
    	tr2.layout(0, 0, 0, 0); 
        
        
        labelmylesson.setText("My Lessons"); 
        
        labelmylesson.setTextColor(Color.RED);
        
        tr2.addView(labelmylesson);
     // Add the TableRow to the TableLayout 
        tl2.addView(tr2, new TableLayout.LayoutParams(
                 LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
    	for( i1=0;i1<lesson1.length;i1++) 
    	{
    		
				try
				{
				final TableRow tr = new TableRow(this); 
	            tr.setId(100+i);
	            tr.layout(0, 0, 0, 0); 
	            final String course2=lesson1[i1];
	            
	            
	            final TableRow tr1 = new TableRow(this);  
	        	final TextView labelmytests = new TextView(this);
	         // Create a TextView to house the name of the province
	            final TextView labelTV = new TextView(this);
	            
	                   
	            labelTV.setId(200+i);
	            labelTV.setText(course2); 
	            System.out.println("Course namessssssss:"+course1[i1]);
	            labelTV.setTextColor(Color.BLACK);
	            
	            tr.addView(labelTV);
	            
	            
	            final Button btndetails = new Button(this);
		           
                
	           	btndetails.setText("Details");
	            btndetails.setTextColor(Color.BLACK);
	            //btndetails.setWidth(30);
               
            
            
            tr.addView(btndetails);
            
	         // Add the TableRow to the TableLayout 
	            tl2.addView(tr, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));
	            
	            
				
				
	                  
	            i++;  
	            labelTV.setOnClickListener(new TextView.OnClickListener()  
	        	{ 
	            	
	            	public void onClick (View v)
	        		{ 
	            	  tl4.removeAllViews();
	            	  String lesson=(String) labelTV.getText();
	        		  gettest(lesson);
	        		  tl2.removeAllViews();
	        		  getlessons();
	        		}
	        	});
	            
	            btndetails.setOnClickListener(new Button.OnClickListener()  
	        	{ 
	            	
	            	public void onClick (View v)
	        		{ 
	            			            		
	        		   lessons=(String) labelTV.getText();
	        		  System.out.println("Course Name is:"+lessons);
	        		  //getprofessors(courses);
	        		  data.deleteprofessorname();
	        		  gettestcount(lessons);
	        		  callprofessorlesson();
	        		  
	        		}
	        	});
				} 
				catch (Exception e)    
		        {
		        text1.setText(e.getMessage()); 
		        System.out.println("Error msg:::::::::"+e.getMessage());
		        }
			}
	 }

	 public void gettests() 
	 {
    	int i=0;
    	int flag=0;
		 
    	final TableRow tr2 = new TableRow(this);  
    	final TextView labelmylesson = new TextView(this);
    	tr2.layout(0, 0, 0, 0); 
        
        
        labelmylesson.setText("My Tests"); 
        
        labelmylesson.setTextColor(Color.RED);
        
        tr2.addView(labelmylesson);
     // Add the TableRow to the TableLayout 
        tl4.addView(tr2, new TableLayout.LayoutParams(
                 LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
    	for( i1=0;i1<test1.length;i1++) 
    	{
    		
				try
				{
				final TableRow tr = new TableRow(this); 
	            tr.setId(100+i);
	            tr.layout(0, 0, 0, 0); 
	            final String test2=test1[i1];
	            
	            
	            
	         // Create a TextView to house the name of the province
	            final TextView labelTV = new TextView(this);
	            
	                   
	            labelTV.setId(200+i);
	            labelTV.setText(test2); 
	            System.out.println("Course namessssssss:"+test1[i1]);
	            labelTV.setTextColor(Color.BLACK);
	            
	            tr.addView(labelTV);
	            
	            final Button btndetails = new Button(this);
		           
                
	           	btndetails.setText("Details");
	            btndetails.setTextColor(Color.BLACK);
	            //btndetails.setWidth(30);
               
            
            
                tr.addView(btndetails);
            
	         // Add the TableRow to the TableLayout 
	            tl4.addView(tr, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));
	            
	            
				
				
	                  
	            i++;   
	            labelTV.setOnClickListener(new TextView.OnClickListener()  
	        	{ 
	            	
	            	public void onClick (View v)
	        		{  
	            	  test=(String) labelTV.getText(); 
	            	  System.out.println("Selected Test is:"+test);
	            	  Cursor c=data.getid(test);
	      			while(c.moveToNext())
	      			{
	      			 qid=c.getInt(0);
	      			
	      			System.out.println("Quiz id:"+qid);
	      			}
	      			questions();
	            	  
	        		}
	        	});
	            btndetails.setOnClickListener(new Button.OnClickListener()  
	        	{ 
	            	
	            	public void onClick (View v)
	        		{ 
	            			            		
	        		   tests=(String) labelTV.getText();
	        		   System.out.println("Course Name is:"+tests);
	        		   Cursor c=data.getid(tests);
		      			while(c.moveToNext())
		      			{
		      			 qid1=c.getInt(0);
		      			     			
		      			}
		      			callnoofquestions();
	        		    showtestdetail();
	        		          		  
	        		}
	        	});
				}
				catch (Exception e)    
		        {
		        text1.setText(e.getMessage()); 
		        System.out.println("Error msg:::::::::"+e.getMessage());
		        }
			}
	 }
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	private void funreview()
	{
		Intent i = new Intent(this,mainstudentreview.class);
		Bundle bundle = new Bundle();
		bundle.putString("wel",s1);
		bundle.putString("type",type);
		bundle.putString("welcome",stunm);
		i.putExtras(bundle);
	   startActivity(i);
		

	}
	private void funmailbox()
	{
		Intent i = new Intent(this,mailbox.class);
		Bundle bundle = new Bundle();
		bundle.putString("wel",s1);
		bundle.putString("type",type);
		bundle.putString("welcome",stunm);
		i.putExtras(bundle);
		startActivity(i);
		

	}
	
	private void funreward()
	{
		Intent i = new Intent(this,reward.class);
		Bundle bundle = new Bundle();
		bundle.putString("wel",s1);
		bundle.putString("type",type);
		bundle.putString("welcome",stunm);
		i.putExtras(bundle);
		startActivity(i);
		

	}
	
	
	private void questions()
	{	
		
		//String s1 =test;
		System.out.println("Quiz name:"+s1); 
	    Intent i2 = new Intent(profile.this,splashscreeen.class);
		Bundle bundle = new Bundle();
		bundle.putString("stuname",stunm);
		bundle.putInt("id",qid);
		bundle.putString("qname",test);
		i2.putExtras(bundle);
		startActivity(i2);
		data.deletetest();  
		

	}
	public void call1()
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
     	     temp3=((quizdata.length)/2);
	        strquizno=new int[temp3];
	        strquizname=new String[temp3];
		        System.out.println("Length:"+temp3);
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
		        for(int x=0;x<temp3;x++)
		        {
		        		        
		      	data.Inserttest(strquizno[x], strquizname[x]);	
		        		        		         
		        }
		       
		        
	      
	    }
	        catch (Exception e) {
	        tv.setText(e.getMessage());
	        }
	}
	
	public void callprofessor()
	 { 
		
		 try {
			 		  
			 	 //data.deleteprofile(); 
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME2);
		 		
		 		request.addProperty("Name",courses);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION2, envelope);
		    	 Object result = (Object)envelope.getResponse();
		 	      str1=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 		   /*tl1.setVisibility(View.GONE);
		 		   tl2.setVisibility(View.GONE);
		        	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		        	alertbox.setMessage(stunm+" "+"you are yet not registered to any courses...you need to register first.... ");
	    		     
		        	// set a positive/yes button and create a listener
	    			alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	    				 
	                    // click listener on the alert box
	                    public void onClick(DialogInterface arg0, int arg1) {
	                        // the button was clicked
	                        //Toast.makeText(getApplicationContext(), "OK button clicked", Toast.LENGTH_LONG).show();
	                    }
	                });
	     
	                // show it
	                alertbox.show();*/
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
		        int temp1=((arrauserData.length)/2);
		        firstname=new String[temp1]; 
		        lastname=new String[temp1];
		        
		        
		        
		         
		       // System.out.println("Length:"+temp1);
		         
		        for(int a=0,b=0;a<temp2;a=a+2)
		        {
		        	firstname[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        
		         for(int a=1,b=0;a<temp2+1;a=a+2)
		        {
		        	lastname[b]=arrauserData[a];
		        	b++;
		                	 
		        }
		         
		         int temp3=firstname.length;
		         proname=new String[temp3];
		        	        
		        for(int x=0;x<temp1;x++) 
		        {
		        	
		         proname[x]=firstname[x]+" "+lastname[x];
               	data.Insertprofessorname(proname[x]);
               	   
		        }
		       
		        getprofessorname();
		        showcoursedetail();
		         
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 text1.setText(e.getMessage()); 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }
	
	public void callprofessorlesson()
	 { 
		
		 try {
			 		  
			 	 //data.deleteprofile(); 
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME3);
		 		
		 		request.addProperty("LName",lessons);
		 		
		 			         		  
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION3, envelope);
		    	 Object result = (Object)envelope.getResponse();
		 	      str1=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 		   /*tl1.setVisibility(View.GONE);
		 		   tl2.setVisibility(View.GONE);
		        	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		        	alertbox.setMessage(stunm+" "+"you are yet not registered to any courses...you need to register first.... ");
	    		     
		        	// set a positive/yes button and create a listener
	    			alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	    				 
	                    // click listener on the alert box
	                    public void onClick(DialogInterface arg0, int arg1) {
	                        // the button was clicked
	                        //Toast.makeText(getApplicationContext(), "OK button clicked", Toast.LENGTH_LONG).show();
	                    }
	                });
	     
	                // show it
	                alertbox.show();*/
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
		        int temp1=((arrauserData.length)/2);
		        firstname=new String[temp1]; 
		        lastname=new String[temp1];
		        
		        
		        
		         
		       // System.out.println("Length:"+temp1);
		         
		        for(int a=0,b=0;a<temp2;a=a+2)
		        {
		        	firstname[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        
		         for(int a=1,b=0;a<temp2+1;a=a+2)
		        {
		        	lastname[b]=arrauserData[a];
		        	b++;
		                	 
		        }
		         
		         int temp3=firstname.length;
		         proname=new String[temp3];
		        	        
		        for(int x=0;x<temp1;x++) 
		        {
		        	
		         proname[x]=firstname[x]+" "+lastname[x];
              	data.Insertprofessorname(proname[x]);
              	   
		        }
		       
		        getprofessorname();
		        showlessondetail();
		         
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 text1.setText(e.getMessage()); 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }
	public void showcoursedetail()
	{
	myDialog = new Dialog(profile.this);
 	myDialog.setContentView(R.layout.coursedetails);
 	myDialog.setTitle(courses+" "+"Details");
 	myDialog.setCancelable(true); 
 	
 	 TextView name1 = (TextView) myDialog.findViewById(R.id.txtname);
 	 TextView no = (TextView) myDialog.findViewById(R.id.txtnooflesson);
 	TextView str = (TextView) myDialog.findViewById(R.id.str);
 	TextView noof = (TextView) myDialog.findViewById(R.id.noof);
     Button button = (Button) myDialog.findViewById(R.id.btnok);
     
     name1.setText(name);
     no.setText(""+count);
     str.setText("Click course to view the lesson names");
     noof.setText("No.Of Lessons:");
     button.setOnClickListener(new OnClickListener() {
     public void onClick(View v) {
     	
     	
     	myDialog.dismiss();
         }
     });

     myDialog.show();
	 
	 System.out.println("Full Names are:"+name);
	}
	public void showlessondetail()
	{
	myDialog = new Dialog(profile.this);
 	myDialog.setContentView(R.layout.coursedetails);
 	myDialog.setTitle(lessons+" "+"Details");
 	myDialog.setCancelable(true); 
 	
 	 TextView name1 = (TextView) myDialog.findViewById(R.id.txtname);
 	 TextView no = (TextView) myDialog.findViewById(R.id.txtnooflesson);
 	TextView str = (TextView) myDialog.findViewById(R.id.str);
 	TextView noof = (TextView) myDialog.findViewById(R.id.noof);
     Button button = (Button) myDialog.findViewById(R.id.btnok);
     
     name1.setText(name);
     no.setText(""+tcount);
     str.setText("Click Lesson to view the test names");
     noof.setText("No.Of Tests:");
     button.setOnClickListener(new OnClickListener() {
     public void onClick(View v) {
     	
     	
     	myDialog.dismiss();
         }
     });

     myDialog.show();
	 
	 System.out.println("Full Names are:"+name);
	}
	public void showtestdetail()
	{
	myDialog = new Dialog(profile.this);
 	myDialog.setContentView(R.layout.testdetails);
 	myDialog.setTitle(tests+" "+"Details");
 	myDialog.setCancelable(true); 
 	
 	 TextView qcount1 = (TextView) myDialog.findViewById(R.id.txtnoofques);
 	 TextView mark = (TextView) myDialog.findViewById(R.id.txtmark);
 	 TextView time = (TextView) myDialog.findViewById(R.id.txttotaltime);
 	
     Button button = (Button) myDialog.findViewById(R.id.btnok);
     
     qcount1.setText(""+qcount);
     mark.setText("1");
     time.setText("180 sec");
     
     button.setOnClickListener(new OnClickListener() {
     public void onClick(View v) {
     	
     	
     	myDialog.dismiss();
         }
     });

     myDialog.show();
	 
	 
	}
	
	public void callnoofquestions()
	 { 
		 try {
			 		 
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME4);
		 		
		 		request.addProperty("test_id",qid1);
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION4, envelope);
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
		        String[] arrauserData;
		        arrauserData = ((String) str1).split("; ");
		       
		       
		        int temp2=arrauserData.length;
		        int temp1=((arrauserData.length)/4);
		        String[] strques;
		        strques=new String[temp1];
		        
		        		        
		        
		         for(int a=1,b=0;a<temp2+1;a=a+4)
		        {
		        	strques[b]=arrauserData[a];
		        	b++;
		                	 
		        }
		         
		        qcount=strques.length;
		        System.out.println("No of Questions:"+qcount);
		    	         
	          	     
		        
	     } 
	        catch (Exception e)    
	        {
	        tv.setText(e.getMessage());
	        }
	           
	 } 


}