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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class teacherprofile extends Activity implements OnItemClickListener {
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
     //tl1.setVisibility(View.GONE);
     
     tl2=(TableLayout)findViewById(R.id.tbl3);
     tl2.setBackgroundResource(R.layout.shapes);
     //tl2.setVisibility(View.GONE);
     
     tl4=(TableLayout)findViewById(R.id.tbl4);
     tl4.setBackgroundResource(R.layout.shapes);
     tl4.setVisibility(View.GONE);
     
    	 
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
	 display_user_details_web();
	 
	 
	 
	
	 } 
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.professor_menu, menu);  
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.q2t:     Toast.makeText(this, "You pressed the icon!", Toast.LENGTH_LONG).show();
	                            break;
	        case R.id.test:     addtest();
	                            break;
	        case R.id.question: Toast.makeText(this, "You pressed the icon and text!", Toast.LENGTH_LONG).show();
	                            break;
	    }
	    return true;
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
	   
	 
	 
	 public void display_user_details_web()
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
	           
	                   
	           
	            labelTV.setText(course2);
	            System.out.println("Course namessssssss:"+course1[i1]); 
	            labelTV.setTextColor(Color.BLACK);
	            
	            tr.addView(labelTV);
	            final Button btndetails = new Button(this);
		           
                
		           	btndetails.setText("student list");
		            btndetails.setTextColor(Color.BLACK);
		            
	               
	            
	            
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
	 
	 public void getlesson(String course2)
	 {
		
		 
		 Cursor c1=data.selectprofilelesson(course2);
		 lesson1=new String[c1.getCount()];
		 
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
	            
	            
	            
	         // Create a TextView to house the name of the province
	            final TextView labelTV = new TextView(this);
	            
	                   
	            labelTV.setId(200+i);
	            labelTV.setText(course2); 
	            System.out.println("Course namessssssss:"+course1[i1]);
	            labelTV.setTextColor(Color.BLACK);
	            
	            tr.addView(labelTV);
	            
	            
	            final Button btndetails = new Button(this);
		           
                
	           	btndetails.setText("Student list");
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
	        		  //gettest(lesson);
	        		  tl2.removeAllViews();
	        		  getlessons();
	        		}
	        	});
	            
	            btndetails.setOnClickListener(new Button.OnClickListener()  
	        	{ 
	            	
	            	public void onClick (View v)
	        		{ 
	            			            		
	        		   
	        		  
	        		  
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
		Intent i = new Intent(this,reportscreen.class);
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
	
	
	private void addtest()
	{	
        Intent i2 = new Intent(teacherprofile.this,addtest_byprofessor.class);
		
		startActivity(i2);

	}
	
}