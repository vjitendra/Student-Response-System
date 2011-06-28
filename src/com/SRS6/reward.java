package com.SRS6;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
   


public class reward extends Activity {
	 
	TableLayout newrewrdtbl,rewardearnedtbl,rewardgiventbl,rewardgiventbl1,savetbl,nametbl,btntbl,redimtbl;
	TableLayout otheropttbl,givenrewarddetails,givenrewarddetails1,backtbl,backtbl1,totalpntsearned,showredimdetailstbl;
	Spinner user_type,user_name,reason_reward,asprreason; 
	EditText edtpoint,aedtpoint1;
	String cmpltname,onlyname,str1,type,rewardreason,type_point,strutype,str12,sdate1,course12,utype12,strreason_given;
	int temp6,temp7,temp10;
	String[] userlogin,fname,lname,arrcourse,arrreason,userloginto,userloginby,date_engine,gift_reason,rec;
	String[] new_userloginto,new_userloginby,new_date_engine,new_gift_reason,username;
	int[] courseid,reasonid,courseid_engine,gift_point,gift_reasonid,point;
	int[] new_courseid_engine,new_gift_point,new_gift_reasonid;
	ArrayAdapter<String> usertypeadapter,spnrcourse; 
	int reasonid1,temp11,acourseid,temp16,getpoint,earnedpoint1,redimedpoint1;
	String[] rec_name_given,date_given,reason_given,new_gift_point1,arrtype,arrreason1;
	int[] point_given,reasonid_given,used_point,avail_point;
	String username1,naming,strgotredimpnt,sumearnedpoint,sumredimedpoint,sdate12,sdate13;  
    int  used_point1,avail_point1,yearly_point,earningint,redimingint,gotredimpnt,availpnt,usedpnt,intpoint; 
    String coupen,user_login_name,sdate,utype_addreason,utype13,course13,earning,rediming,getnames,strdateassign,strdateexpire;
   double amount;
    int temp61;
    String[] redimdate,redimcoupen;
	
	DataBaseHelper  data;
	Dialog myDialog; 
	ArrayAdapter<CharSequence> unameadapter,rewardreasonadapter,usernameadapter,arewardreasonadapter;
	TextView txtdate,auname,atxtdate,txtname1,wels,balance;
	Spinner utype ;   
	Spinner sprname,course1,user_type_addreason;  
    EditText edtpoint1;
    Button assign,back,redim,back1;
    int	curtimestamp,nexttimestamp,pointe;  
	
    
    private static final String NAMESPACE01 = "http://tempuri.org/";      
	private static final String URL01 = "http://192.168.1.204/iBharti/Service.asmx"; 
	
	private static final String SOAP_ACTION01 = "http://tempuri.org/insertCoupen";      
	private static final String METHOD_NAME01= "insertCoupen";  
    
    String condi_ser="a:6:{s:4:\"type\";s:32:\"salesrule/rule_condition_combine\";s:9:\"attribute\";N;s:8:\"operator\";N;s:5:\"value\";s:1:\"1\";s:18:\"is_value_processed\";N;s:10:\"aggregator\";s:3:\"all\";}";
    
	String action_ser="a:6:{s:4:\"type\";s:40:\"salesrule/rule_condition_product_combine\";s:9:\"attribute\";N;s:8:\"operator\";N;s:5:\"value\";s:1:\"1\";s:18:\"is_value_processed\";N;s:10:\"aggregator\";s:3:\"all\";}";
	
	private static final String NAMESPACE = "http://tempuri.org/";      
	private static final String URL = "http://192.168.1.204/studentresponse/Service.asmx"; 
	
	private static final String SOAP_ACTION = "http://tempuri.org/InsertReward_reason";      
	private static final String METHOD_NAME = "InsertReward_reason";     
	
	private static final String SOAP_ACTION1 = "http://tempuri.org/GetUserTypeInfo";      
	private static final String METHOD_NAME1 = "GetUserTypeInfo";   
	
	private static final String SOAP_ACTION2 = "http://tempuri.org/GetRewardpt";      
	private static final String METHOD_NAME2 = "GetRewardpt";  
	
	private static final String SOAP_ACTION3 = "http://tempuri.org/InsertReward_point";      
	private static final String METHOD_NAME3 = "InsertReward_point"; 
	
	private static final String SOAP_ACTION4 = "http://tempuri.org/GetCourseName";      
	private static final String METHOD_NAME4 = "GetCourseName";     
	
	private static final String SOAP_ACTION5 = "http://tempuri.org/GetReason";      
	private static final String METHOD_NAME5 = "GetReason"; 
	
	private static final String SOAP_ACTION6 = "http://tempuri.org/GetUserInfo";      
	private static final String METHOD_NAME6 = "GetUserInfo"; 
	
	private static final String SOAP_ACTION7 = "http://tempuri.org/Insertreward_engine";      
	private static final String METHOD_NAME7 = "Insertreward_engine"; 
	
	private static final String SOAP_ACTION8 = "http://tempuri.org/Getreward_engine";      
	private static final String METHOD_NAME8 = "Getreward_engine";
	
	private static final String SOAP_ACTION9 = "http://tempuri.org/Getreward_enginedata";      
	private static final String METHOD_NAME9 = "Getreward_enginedata"; 
	
	private static final String SOAP_ACTION10 = "http://tempuri.org/GetReward_Point";      
	private static final String METHOD_NAME10 = "GetReward_Point"; 
	
	private static final String SOAP_ACTION11 = "http://tempuri.org/Updatereward_Point";      
	private static final String METHOD_NAME11 = "Updatereward_Point"; 
	
	
	private static final String SOAP_ACTION12 = "http://tempuri.org/LoginDetail";  
	private static final String METHOD_NAME12 = "LoginDetail";  
	
	private static final String SOAP_ACTION13 = "http://tempuri.org/SUMreward_enginedata";      
	private static final String METHOD_NAME13 = "SUMreward_enginedata"; 
	
	private static final String SOAP_ACTION14 = "http://tempuri.org/SUMredim_point_details";      
	private static final String METHOD_NAME14 = "SUMredim_point_details"; 
	
	private static final String SOAP_ACTION15 = "http://tempuri.org/UpdatetotalearnedPoint";      
	private static final String METHOD_NAME15 = "UpdatetotalearnedPoint"; 
	
	
	private static final String SOAP_ACTION16 = "http://tempuri.org/UpdateTotalRedimPoint";  
	private static final String METHOD_NAME16 = "UpdateTotalRedimPoint";
	
	private static final String SOAP_ACTION17 = "http://tempuri.org/Insertredim_point_details";  
	private static final String METHOD_NAME17 = "Insertredim_point_details";
	
	private static final String SOAP_ACTION18 = "http://tempuri.org/NewUpdatereward_Point";  
	private static final String METHOD_NAME18 = "NewUpdatereward_Point";
	
	
	private static final String SOAP_ACTION19 = "http://tempuri.org/Getredim_point_details";  
	private static final String METHOD_NAME19 = "Getredim_point_details";
	
	
	
	
	
	
	
	public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.assignreward);
        
        
        Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		System.out.println("Month is:"+month);
		int year = cal.get(Calendar.YEAR);   
		
		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		curtimestamp=(int) (cal.getTimeInMillis()/1000L);
		
		 
		
		sdate=(+ day + "-" + (month + 1) + "-" + year);
		
		sdate12=(+ year + "-" + (month + 1) + "-" + day );
		
		
		Calendar cal1 = new GregorianCalendar();
		
		cal1.add(Calendar.DAY_OF_MONTH,7);
		int day12=cal1.get(Calendar.DAY_OF_MONTH);
		
		int month1 = cal1.get(Calendar.MONTH);
		System.out.println("Month is:"+month);
		
		int year1 = cal1.get(Calendar.YEAR);   
		
		
		sdate13=(+ year1 + "-" + (month1 + 1) + "-" + (day12));
		
		
		
		
		System.out.println("Date IN YYMMDD:"+sdate12);
		System.out.println("Date IN YYMMDD:"+sdate13);
        
        Bundle bundle = getIntent().getExtras(); 
   	 	cmpltname=bundle.getString("wel");
   		onlyname=bundle.getString("welcome");
   		type=bundle.getString("type");   
   		
   		data=new DataBaseHelper(this);
   		
   		 
   		data.deletecourse();
   		data.deletereason();  
   		data.deletereward_engine_in();
   		data.deletereward_engine_out();
    	
   		
        
        final TextView newreward=(TextView) findViewById(R.id.newrewards);
        final TextView rewardearned=(TextView) findViewById(R.id.earned); 
        final TextView rewardgiven=(TextView) findViewById(R.id.givento);
        final TextView otheroption=(TextView) findViewById(R.id.otheroption);
        TextView namegivenby=(TextView) findViewById(R.id.name);
        final TextView txtaddreason=(TextView) findViewById(R.id.txtaddreason);
        final TextView txtassignpoints=(TextView) findViewById(R.id.txtassignpoints);
        TextView txtname1=(TextView) findViewById(R.id.txtname1);
        wels=(TextView) findViewById(R.id.wels);
        assign=(Button) findViewById(R.id.btnassign);
        back=(Button) findViewById(R.id.btnok);
        back1=(Button) findViewById(R.id.btnok1);
        balance=(TextView) findViewById(R.id.balance);
        
        rewardearned.setTextColor(Color.RED);
         
        wels.setText("Welcome "+onlyname);
        if(type.equals("student"))
        {
        	otheroption.setVisibility(View.INVISIBLE);  
        }
        else
        {
        	if(type.equals("professor")||type.equals("administrator"))
       
        {
        	otheroption.setVisibility(View.VISIBLE);
        }
        }
        namegivenby.setText("Reward given by  "+onlyname);
        
        user_type=(Spinner) findViewById(R.id.user_type);
        
              
        ArrayAdapter<CharSequence> usertypeadapter = ArrayAdapter.createFromResource(
                this, R.array.ADMIN, android.R.layout.simple_spinner_item);
        usertypeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_type.setAdapter(usertypeadapter);
		user_type.setOnItemSelectedListener(new MyOnItemSelectedListenerutype()); 
        
             
      //User type spinner
         course1=(Spinner) findViewById(R.id.course);
         
              
          
        //User name spinner   
         user_name=(Spinner) findViewById(R.id.user_name);
         
        
        //Reward Reason spinner
        reason_reward=(Spinner) findViewById(R.id.reason_reward ); 
       
       
        
        edtpoint=(EditText) findViewById(R.id.editpoint);
        
        
        newrewrdtbl=(TableLayout)findViewById(R.id.new_reward);
        savetbl=(TableLayout)findViewById(R.id.save_reward);
        nametbl=(TableLayout)findViewById(R.id.name_table);
        rewardearnedtbl=(TableLayout)findViewById(R.id.reward_earned);
        rewardgiventbl=(TableLayout)findViewById(R.id.reward_given);
        rewardgiventbl1=(TableLayout)findViewById(R.id.reward_given1);
        givenrewarddetails=(TableLayout)findViewById(R.id.givenrewarddetails);
        givenrewarddetails1=(TableLayout)findViewById(R.id.givenrewarddetails1);
        totalpntsearned=(TableLayout)findViewById(R.id.totalpntsearned);
        otheropttbl=(TableLayout)findViewById(R.id.addreason);
        backtbl=(TableLayout)findViewById(R.id.backtbl);
        backtbl1=(TableLayout)findViewById(R.id.backtbl1);
        redimtbl=(TableLayout)findViewById(R.id.redim);
        showredimdetailstbl=(TableLayout)findViewById(R.id.redimdetails);
        
        newrewrdtbl.setVisibility(View.GONE);
    	savetbl.setVisibility(View.GONE);
    	nametbl.setVisibility(View.GONE);
    	rewardgiventbl.setVisibility(View.GONE);
    	rewardgiventbl1.setVisibility(View.GONE);
    	otheropttbl.setVisibility(View.GONE);
    	givenrewarddetails.setVisibility(View.GONE);
    	givenrewarddetails1.setVisibility(View.GONE);
    	backtbl.setVisibility(View.GONE);
    	backtbl1.setVisibility(View.GONE);
    	rewardearnedtbl.setVisibility(View.GONE);
    	showredimdetailstbl.setVisibility(View.GONE);
    	
    	txtname1.setText(onlyname);   
    	
    	getsumearnedpoint(onlyname);
    	getsumredimpoint(onlyname);
    	updatereward_point_earnedpnt(onlyname,Integer.parseInt(sumearnedpoint));
    	updatereward_point_redimpnt(onlyname,Integer.parseInt(sumredimedpoint));
    	utype13=user_type.getSelectedItem().toString();   
    	data.deletereason();
    	getreasonfromserver();
    	fillreasonfromlocal(utype13); 
    	
    	getcoursefromserver();
    	fillcourse();
    	   
    	course13=course1.getSelectedItem().toString();   
      	getuserinfooforassignreward(course13,utype13); 
      	
    	getreward_engine_to();
      	gettotalpntsearned(""+sumearnedpoint,""+sumredimedpoint);
    	getreward_engine_by();     
    	getreward_point1();    
    	
    	
    	
    	
     	 
    	assign.setOnClickListener(new Button.OnClickListener() {        	 
            public void onClick(View v) {
            	
            	
            	fun();
            	
                }
            });
    	back.setOnClickListener(new Button.OnClickListener() {        	 
            public void onClick(View v) {
            	
            	rewardearnedtbl.setVisibility(View.GONE); 
            	newrewrdtbl.setVisibility(View.GONE); 
            	savetbl.setVisibility(View.GONE);
            	nametbl.setVisibility(View.GONE);
            	givenrewarddetails.setVisibility(View.GONE);
            	givenrewarddetails1.setVisibility(View.GONE);
            	otheropttbl.setVisibility(View.GONE);
            	backtbl.setVisibility(View.GONE);
            	//redimtbl.setVisibility(View.GONE);
            	showredimdetailstbl.setVisibility(View.GONE);
    	        	
            	rewardgiventbl.setVisibility(View.VISIBLE); 
            	rewardgiventbl1.setVisibility(View.VISIBLE);  
            	
            	
                }  
            });
    	back1.setOnClickListener(new Button.OnClickListener() {        	 
            public void onClick(View v) {
            	
            	rewardearnedtbl.setVisibility(View.GONE); 
            	newrewrdtbl.setVisibility(View.GONE); 
            	savetbl.setVisibility(View.GONE);
            	nametbl.setVisibility(View.GONE);
            	givenrewarddetails.setVisibility(View.GONE);
            	givenrewarddetails1.setVisibility(View.GONE);
            	otheropttbl.setVisibility(View.GONE);
            	backtbl1.setVisibility(View.GONE);
              	rewardgiventbl.setVisibility(View.GONE); 
            	rewardgiventbl1.setVisibility(View.GONE);
            	showredimdetailstbl.setVisibility(View.GONE);
            	
            	redimtbl.setVisibility(View.VISIBLE);  
            	totalpntsearned.setVisibility(View.VISIBLE);
            	
            	
                }
            });
    	
    	
    	balance.setOnClickListener(new TextView.OnClickListener() {        	 
            public void onClick(View v) {
            	
            	
            	getreward_point();
            	
            	rewardearnedtbl.setVisibility(View.GONE);
            	rewardgiventbl.setVisibility(View.GONE);  
            	rewardgiventbl1.setVisibility(View.GONE);
            	givenrewarddetails.setVisibility(View.GONE);
            	givenrewarddetails1.setVisibility(View.GONE);
            	backtbl.setVisibility(View.GONE);
            	otheropttbl.setVisibility(View.GONE);
            	totalpntsearned.setVisibility(View.GONE);
            	redimtbl.setVisibility(View.GONE);
            	newrewrdtbl.setVisibility(View.GONE); 
            	savetbl.setVisibility(View.GONE);
            	nametbl.setVisibility(View.GONE);
            	showredimdetailstbl.setVisibility(View.GONE);
            	
            	
            	
                }
            });
        
        
        newreward.setOnClickListener(new TextView.OnClickListener() {        	 
            public void onClick(View v) {
            	
            	 
            	getreward_point2();
            	 
            	newreward.setTextColor(Color.RED);
            	
            	txtassignpoints.setTextColor(Color.BLACK);
            	txtaddreason.setTextColor(Color.BLACK);
            	rewardearned.setTextColor(Color.BLACK);  
            	otheroption.setTextColor(Color.BLACK);
            	rewardgiven.setTextColor(Color.BLACK);
            	  
            	rewardearnedtbl.setVisibility(View.GONE);
            	rewardgiventbl.setVisibility(View.GONE);  
            	rewardgiventbl1.setVisibility(View.GONE);
            	givenrewarddetails.setVisibility(View.GONE);
            	givenrewarddetails1.setVisibility(View.GONE);  
            	backtbl.setVisibility(View.GONE);
            	otheropttbl.setVisibility(View.GONE);
            	totalpntsearned.setVisibility(View.GONE);
            	redimtbl.setVisibility(View.GONE);
            	showredimdetailstbl.setVisibility(View.GONE);
            	
                }
            });
        
        rewardearned.setOnClickListener(new TextView.OnClickListener() {        	 
            public void onClick(View v) {
            	
            	
            	
            	
            	getsumearnedpoint(onlyname);
            	getsumredimpoint(onlyname);
            	totalpntsearned.removeAllViews();
            	redimtbl.removeAllViews();
            	gettotalpntsearned(""+sumearnedpoint,""+sumredimedpoint);
            	
            	
            	rewardearned.setTextColor(Color.RED);  
            	
            	txtassignpoints.setTextColor(Color.BLACK);
            	newreward.setTextColor(Color.BLACK);   
            	txtaddreason.setTextColor(Color.BLACK);
            	otheroption.setTextColor(Color.BLACK);
            	rewardgiven.setTextColor(Color.BLACK);
            	
            	rewardgiventbl.setVisibility(View.GONE);
            	rewardgiventbl1.setVisibility(View.GONE);
            	newrewrdtbl.setVisibility(View.GONE);
            	savetbl.setVisibility(View.GONE);
            	nametbl.setVisibility(View.GONE);
            	givenrewarddetails.setVisibility(View.GONE);
            	givenrewarddetails1.setVisibility(View.GONE);
            	backtbl.setVisibility(View.GONE);
            	otheropttbl.setVisibility(View.GONE);
            	rewardearnedtbl.setVisibility(View.GONE);
            	showredimdetailstbl.setVisibility(View.GONE);
            	
            	totalpntsearned.setVisibility(View.VISIBLE);
            	redimtbl.setVisibility(View.VISIBLE);
                }
            });
        rewardgiven.setOnClickListener(new TextView.OnClickListener() {        	 
            public void onClick(View v) {
            	
            	 
            	rewardgiventbl1.removeAllViews();
            	data.deletereward_engine_out();
            	getreward_engine_by();
            	rewardgiven.setTextColor(Color.RED);
            	
            	txtassignpoints.setTextColor(Color.BLACK);
            	newreward.setTextColor(Color.BLACK);
            	txtaddreason.setTextColor(Color.BLACK);
            	rewardearned.setTextColor(Color.BLACK);
            	otheroption.setTextColor(Color.BLACK);
            	
            	
            	rewardearnedtbl.setVisibility(View.GONE);
            	newrewrdtbl.setVisibility(View.GONE);
            	savetbl.setVisibility(View.GONE);
            	nametbl.setVisibility(View.GONE);
            	givenrewarddetails.setVisibility(View.GONE);
            	givenrewarddetails1.setVisibility(View.GONE);
            	backtbl.setVisibility(View.GONE);
            	otheropttbl.setVisibility(View.GONE);
            	totalpntsearned.setVisibility(View.GONE);
            	redimtbl.setVisibility(View.GONE);
            	showredimdetailstbl.setVisibility(View.GONE);
    	        	
            	rewardgiventbl.setVisibility(View.VISIBLE);    
            	rewardgiventbl1.setVisibility(View.VISIBLE);
            	
                }
            });
        
        otheroption.setOnClickListener(new TextView.OnClickListener() {        	 
            public void onClick(View v) {
            	
            	
            	
            	otheroption.setTextColor(Color.RED);
            	
            	txtassignpoints.setTextColor(Color.BLACK);
            	newreward.setTextColor(Color.BLACK);
            	txtaddreason.setTextColor(Color.BLACK);
            	rewardearned.setTextColor(Color.BLACK);
            	rewardgiven.setTextColor(Color.BLACK);
            	
            	
            	rewardearnedtbl.setVisibility(View.GONE);
            	newrewrdtbl.setVisibility(View.GONE);
            	savetbl.setVisibility(View.GONE);
            	nametbl.setVisibility(View.GONE);
            	givenrewarddetails.setVisibility(View.GONE);
            	givenrewarddetails1.setVisibility(View.GONE);
            	backtbl.setVisibility(View.GONE);
            	totalpntsearned.setVisibility(View.GONE);
            	redimtbl.setVisibility(View.GONE);
    	        rewardgiventbl.setVisibility(View.GONE); 
            	rewardgiventbl1.setVisibility(View.GONE);
            	showredimdetailstbl.setVisibility(View.GONE);
            	
            	
            	otheropttbl.setVisibility(View.VISIBLE);
                }
            });
        txtaddreason.setOnClickListener(new TextView.OnClickListener() {        	 
            public void onClick(View v) {
            	
            	
            	txtaddreason.setTextColor(Color.RED);
            	
            	txtassignpoints.setTextColor(Color.BLACK);
            	newreward.setTextColor(Color.BLACK);
            	rewardearned.setTextColor(Color.BLACK);
            	otheroption.setTextColor(Color.RED);
            	rewardgiven.setTextColor(Color.BLACK);
            	
            	funaddreason(); 
            	
                }
            });
        txtassignpoints.setOnClickListener(new TextView.OnClickListener() {     
        	 
        	
        	
            public void onClick(View v) {
            	
            	txtassignpoints.setTextColor(Color.RED); 
            	
            	newreward.setTextColor(Color.BLACK);
            	txtaddreason.setTextColor(Color.BLACK);
            	rewardearned.setTextColor(Color.BLACK);
            	otheroption.setTextColor(Color.RED);
            	rewardgiven.setTextColor(Color.BLACK);
            	
            	
            	funassignpoints();
            	  
                }
            });   
        
          
    } 
	
	
	//ONCLICK LISTENERS-START
	
	
	public class MyOnItemSelectedListenerutype implements OnItemSelectedListener {
 
	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	     
	       utype12=parent.getItemAtPosition(pos).toString();  
	     	              
	       getuserinfooforassignreward(course12,utype12); 
	       data.deletereason();
	       getreasonfromserver(); 
	       fillreasonfromlocal(utype12);
	      
	      
	    }

	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	}
	
	public class MyOnutypepointSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	      Toast.makeText(parent.getContext(), "You Selected " +
	          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
	       type_point=parent.getItemAtPosition(pos).toString();
	       System.out.println("TYPE IS:"+type_point);
	       
	       unameadapter.clear();
	       getuname(type_point);
	       point(type_point);  
	       
	       for(int x=0;x<temp6;x++) 
	        {
	               	
	    	   unameadapter.add(fname[x]+" "+lname[x]);
	        }
	       sprname.setAdapter(unameadapter);
	       sprname.setOnItemSelectedListener(new MyOnItemSelectedListener());
        
         
	      edtpoint1.setText(str12); 
	      
	       
	       
	      
	    }
   
	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	}
	
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener {
		 
	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	     /* Toast.makeText(parent.getContext(), "You Selected " +
	          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();*/  
	       
	      
	      
	    }

	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	}
	
	
	public class MyOnutypepointSelectedListener2 implements OnItemSelectedListener {
		 
	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	      /*Toast.makeText(parent.getContext(), "You Selected " +
	          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();*/  
	      
	      
	      
	    }

	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	}
	
	public class MyOnItemSelectedListener1 implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	      Toast.makeText(parent.getContext(), "You Selected " +
	          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
	    }
  
	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing. 
	    } 
	}
	
	
	public class MyOnCourseSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	    	
	    	System.out.println("Position is:"+pos);
	        
	    	course12=parent.getItemAtPosition(pos).toString();
	    	
	    	getuserinfooforassignreward(course12,utype12);
	          
	       
	      
	    }
 
	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	}//ONCLICK LISTENER FINISHED
	

	//CALL TO WEB SERVICES
	
	
	public void insert_coupen_ibharati(String date,String expdate,String coupen,String amounts)
	 { 
		 try {
			 		 
			 	  
	      	 	SoapObject request = new SoapObject(NAMESPACE01, METHOD_NAME01);
	    	 	
	      	 	
	    	 	request.addProperty("ruleid","null");
	    	 	request.addProperty("name","LPlanet");
	    	 	request.addProperty("desc","LearningPlanet");
	    	 	request.addProperty("fromdate",date);
	    	 	request.addProperty("todate",expdate);
	    	 	request.addProperty("coupencode",coupen);
	    	 	request.addProperty("usespercoupen","1");
	    	 	request.addProperty("usespercustomer","1");
	    	 	request.addProperty("customergroupids","0,1,2,3");
	    	 	request.addProperty("isactive","1");
	    	 	request.addProperty("conditions_serialized",condi_ser);
	    	 	request.addProperty("action_serialized",action_ser);
	    	 	request.addProperty("stoprulesprocessing","0");
	    	 	request.addProperty("isadvanced","1");
	    	 	request.addProperty("productids","");
	    	 	request.addProperty("sortorder","0");
	    	 	request.addProperty("simpleaction","cart_fixed");
	    	 	request.addProperty("discountamt",amounts);
	    	 	request.addProperty("discountqnty","");
	    	 	request.addProperty("discountstep","0");
	    	 	request.addProperty("simplefreeshipping","0");
	    	 	request.addProperty("timesused","0");
	    	 	request.addProperty("isrss","1");
	    	 	request.addProperty("websiteids","5,3,2,4,1");
		 		
		 				 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL01);
		 	
		 	   androidHttpTransport.call(SOAP_ACTION01, envelope);
		       
		 	   Toast.makeText(reward.this, "Coupen Added At iBharati",Toast.LENGTH_SHORT).show();
		       
		       
		 	      
		 } 
	        catch (Exception e)    
	        { 
	        //tv.setText(e.getMessage());
	        	System.out.println("ERROR:"+e.getMessage());
	        }  
	 }
	
	public void addreason()
	 { 
		 try {
			 		 
			 	  
	      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	    	 	
	      	 	
	    	 	request.addProperty("reason",rewardreason);
	    	 	request.addProperty("UserType",utype_addreason);
		 		
		 				 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 	   androidHttpTransport.call(SOAP_ACTION, envelope);
		       
		 	   Toast.makeText(reward.this, "Reason Added Successfully",Toast.LENGTH_SHORT).show();
		       
		       
		 	      
		 } 
	        catch (Exception e)    
	        { 
	        //tv.setText(e.getMessage());
	        	System.out.println("ERROR:"+e.getMessage());
	        }  
	 }
	
	public void getuname(String name)
	 { 
		
		 try {
			 		  
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);
		 		
		 		request.addProperty("UserType",name);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION1, envelope);   
		    	 Object result = (Object)envelope.getResponse();
		 	      str1=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 		   
		        	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		        	//alertbox.setMessage(onlyname+" "+"you dont have any rewards earned.... ");
	    		     
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
		        temp6=((arrauserData.length)/3);
		        userlogin=new String[temp6]; 
		        fname=new String[temp6]; 
		        lname=new String[temp6]; 
		             
		       
		         
		        for(int a=0,b=0;a<temp2;a=a+3)
		        {
		        	userlogin[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        for(int a=1,b=0;a<temp2;a=a+3)
		        {
		        	fname[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        for(int a=2,b=0;a<temp2;a=a+3)
		        {
			        lname[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        
		            
	        	 
	             
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }  
	
	
	public void point(String utype)
	 { 
		
		 try {
			 		  
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME2);
		 		
		 		request.addProperty("UserType",utype);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION2, envelope);
		    	 Object result = (Object)envelope.getResponse();
		 	      str12=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str12);
		 	    if(str12.equals(null)||str12.equals("anyType{}"))
		        {
		 		   
		        	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		        	//alertbox.setMessage(onlyname+" "+"you dont have any rewards earned.... ");
	    		     
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
		 	    if(str12.contains("anyType{anyType=")||str12.contains("}")||str12.contains("anyType="))
		        {
		    	  str12=str12.replace("anyType{anyType=", "");
		    	  str12=str12.replace("anyType=", "");
		    	  str12=str12.replace("}", ""); 
		    	  str12=str12.trim();
		    	  System.out.println("String"+str12); 
		    	  
		        }
		        if(str12.contains(";"))
		        {
		        	str12=str12.replace(";", ""); 
		        }
		         
		             
		        
		 	    }   
		 }
	 	    catch (Exception e)     
	        {
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }
	

	
	

	
	public void fun()
	{
		
		
		String rewardto=user_name.getSelectedItem().toString();
		
		String[] temp;
		temp=new String[2];
		
		temp=rewardto.split(" ");
		
		if(temp[1].contains(";"))
		{
			temp[1].replace(";","");
		}
		
		getuser_login(temp[0],temp[1]);

		
		String rewardcourse=course1.getSelectedItem().toString();
		
		String rewardreason=reason_reward.getSelectedItem().toString();    
			
		int strpoint=Integer.parseInt(edtpoint.getText().toString());
		
		int i1=getcourseid(rewardcourse);
		int i2=getreasonid(rewardreason);
		
		int usedpoint3=used_point1+strpoint;
		int availpoint3=yearly_point-usedpoint3;  
		  
		String s=""+curtimestamp; 
		 
		  
		
		addrewardengine(user_login_name, onlyname, i1,s, strpoint, i2);  
		
		updaterewardpoints(onlyname, usedpoint3, availpoint3);
		
		getreward_point3(user_login_name);
		
		
		
		  
	}
	
	
	public void fillcourse() 
	{
	ArrayAdapter<String> spnrcourse = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
    
    for(int x=0;x<temp7;x++) 
    {
     	        	 
    	spnrcourse.add(arrcourse[x]);   
    	System.out.println("Course is:"+arrcourse[x]);
    } 
    
    spnrcourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    course1.setAdapter(spnrcourse);
    course1.setOnItemSelectedListener(new MyOnCourseSelectedListener());
	}
	
	
	
	public void addrewardpoints(String uname,int ypoint,int usedpoint,int availpoint,String dateassign,String dateexpire,int earn,int redim)
	 { 
		 try {
			 		 
			 	  
	      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME3);
	    	 	
	    	 	
	    	 	request.addProperty("UserName",uname);
	    	 	request.addProperty("YearlyPoint",ypoint);
	    	 	request.addProperty("UsedPoint",usedpoint);
	    	 	request.addProperty("AvailablePoint",availpoint);
	    	 	request.addProperty("Dateassign",dateassign);
	    	 	request.addProperty("DateExpire",dateexpire);
	    	 	request.addProperty("TotalearnedPoint",earn);
	    	 	request.addProperty("totalredimPoint",redim);
		 		
		 				 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 	   androidHttpTransport.call(SOAP_ACTION3, envelope);
		       
		 	  Toast.makeText(reward.this, "Points assigned successfully",Toast.LENGTH_SHORT).show();
		       
		 	      
		 }   
	        catch (Exception e)    
	        { 
	       
	        	System.out.println("ERROR:"+e.getMessage());
	        }  
	 }
	
	
	public void getcoursefromserver()
	 { 
		
		 try {
			 		  
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME4);
		  		
		 		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;   
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	   
		 		androidHttpTransport.call(SOAP_ACTION4, envelope);   
		    	 Object result = (Object)envelope.getResponse();
		 	      str1=result.toString(); 
		 	                
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 		   
		        	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		        	//alertbox.setMessage(onlyname+" "+"you dont have any rewards earned.... ");
	    		     
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
		        temp7=((arrauserData.length)/2);
		             
		        courseid=new int[temp7];
		        arrcourse=new String[temp7]; 
		         
		        
		        for(int a=0,b=0;a<temp2;a=a+2)
		        {
		        	courseid[b]=Integer.parseInt(arrauserData[a]);
		        	b++;
	 	                	
		         }
		       
		         
		        for(int a=1,b=0;a<temp2;a=a+2)
		        {
		        	arrcourse[b]=arrauserData[a];  
		        	b++;
	 	                	
		         }
		        
		        
		        		    
		        
		        for(int x=0;x<temp2;x++) 
		        {
		         
               	data.Insertcourse(courseid[x], arrcourse[x]);
               	
    
		        }
		        
		                   
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }
	
	public void getreasonfromserver()
	 { 
		  
		 try { 
			 		  
			 	   
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME5);
		 		
	    	 	
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION5, envelope);   
		    	 Object result = (Object)envelope.getResponse();
		 	      str1=result.toString();   
		 	      
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
		         
		        int temp2=arrauserData.length; 
		        temp11=((arrauserData.length)/3);
		        reasonid=new int[temp11];
		        arrreason=new String[temp11]; 
		        arrtype=new String[temp11];
		       
		        
		        for(int a=0,b=0;a<temp2;a=a+3)
		        {
		        	reasonid[b]=Integer.parseInt(arrauserData[a]);
		        	b++;
	 	                	
		         }
		        for(int a=1,b=0;a<temp2;a=a+3)
		        {
		        	arrreason[b]=arrauserData[a];
		        	b++; 
	 	                	  
		         }
		        for(int a=2,b=0;a<temp2;a=a+3)
		        {
		        	arrtype[b]=arrauserData[a];  
		        	b++;
	 	                	
		         }     
		        
		        
		          
		        
		          
		         for(int x=0;x<temp11;x++)   
		        {
		         
               	data.Insertreason(reasonid[x],arrreason[x],arrtype[x]);
               	
    
		        }
		        
		         		        
		       
       	
		         
	        	 
	             
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 
			 System.out.println("Error msg:"+e.getMessage()); 
	        
	        }
	 }
	
	public void getuserinfooforassignreward(String coursenm, String utypes)
	 { 
		
		 try {
			 		  
			 	  
	     	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME6); 
		 		
		 		request.addProperty("UserType",utypes);
		 		request.addProperty("CourseName",coursenm);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION6, envelope);   
		    	 Object result = (Object)envelope.getResponse();
		 	      str1=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 		   
		        	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		        	//alertbox.setMessage(onlyname+" "+"you dont have any rewards earned.... ");
	    		     
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
		 	    if(str1.contains("anyType{anyType=")||str1.contains("}")||str1.contains("anyType=")||str1.contains("ArrayOfAnyType{"))
		        {
		    	  str1=str1.replace("anyType{anyType=", "");
		    	  str1=str1.replace("anyType=", "");
		    	  str1=str1.replace("}", ""); 
		    	  str1=str1.replace("ArrayOfAnyType{", ""); 
		    	  str1=str1.trim();
		    	  System.out.println("String"+str1); 
		    	  
		        }
		        String[] arrauserData;
		        arrauserData = ((String) str1).split("; ");
		         
		        int temp2=arrauserData.length;
		        temp10=((arrauserData.length)/2);
		         
		        fname=new String[temp10]; 
		        lname=new String[temp10]; 
		             
		       
		         
		        
		        for(int a=0,b=0;a<temp2;a=a+2)
		        {
		        	fname[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        for(int a=1,b=0;a<temp2;a=a+2)
		        {
			        lname[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        
		        usernameadapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item);
	        		        		        
		        for(int x=0;x<temp10;x++) 
		        {
		               	 
		        	 usernameadapter.add(fname[x]+" "+lname[x]);
		        	 
     
		        } 
		        user_name.setAdapter(usernameadapter);
	        	user_name.setOnItemSelectedListener(new MyOnItemSelectedListener());
        	    }   
		 }
	 	    catch (Exception e)    
	        {
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }
	
	public void addrewardengine(String to,String by,int courses,String ddate,int pnt,int reason1)
	 { 
		 try {
			 		 
			 	  
	      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME7);
	    	 	
	    	 	
	    	 	request.addProperty("UserLogin_to",to);
	    	 	request.addProperty("UserLogin_By",by);
	    	 	request.addProperty("ProfessorLogin","");
	    	 	request.addProperty("CoursesId",courses);
	    	 	request.addProperty("LessonId","");
	    	 	request.addProperty("GiftAmount","");
	    	 	request.addProperty("GiftKey","");
	    	 	request.addProperty("Status","Rewarded");
	    	 	request.addProperty("TimeStamp",ddate);
	    	 	request.addProperty("GifiPoint",pnt);
	    	 	request.addProperty("GiftResonID",reason1); 
	    	 		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD; 
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 	   androidHttpTransport.call(SOAP_ACTION7, envelope);
		 	   
		 	   data.Insertreward_engine_out(to, courses, ddate, pnt, reason1);
		       
		 	   Toast.makeText(reward.this, "Points Assigned Successfully",Toast.LENGTH_SHORT).show();
		       
		       
		 	      
		 } 
	        catch (Exception e)    
	        { 
	        //tv.setText(e.getMessage());
	        	System.out.println("ERROR:"+e.getMessage());
	        }  
	 }
	
	
	
	
	
	public void getreward_engine_by()
	 { 
		
		 try {
			 		  
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME8);
		 		
		 		request.addProperty("ULoginRewardBy",onlyname);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);   
		 		envelope.dotNet=true;  
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION8, envelope);   
		    	 Object result = (Object)envelope.getResponse();
		 	      str1=result.toString(); 
		 	      
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
		        
		        
		         
		        int temp2=arrauserData.length;
		        temp6=((arrauserData.length)/12);
		        userloginto=new String[temp6]; 
		        userloginby=new String[temp6];
		        courseid_engine=new int[temp6];  
		        date_engine=new String[temp6];    
		        gift_point=new int[temp6]; 
		        gift_reason=new String[temp6];
		        gift_reasonid=new int[temp6];
		             
		       
		         
		        for(int a=1,b=0;a<temp2;a=a+12)
		        {
		        	userloginto[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        
		        for(int a=4,b=0;a<temp2;a=a+12)
		        {
		        	courseid_engine[b]=Integer.parseInt(arrauserData[a]);
		        	b++;
	 	                	
		         }
		        
		        for(int a=9,b=0;a<temp2;a=a+12)
		        {
		        	date_engine[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        for(int a=10,b=0;a<temp2;a=a+12)
		        {
		        	gift_point[b]=Integer.parseInt(arrauserData[a]);
		        	b++;
	 	                	
		         }
		        for(int a=11,b=0;a<temp2;a=a+12)
		        {
		        	gift_reason[b]=arrauserData[a];
		        	String gift=gift_reason[b];
		        	if(gift.contains(";"))
		        	{
		        		gift=gift.replace(";", "");
		        	}
		        	gift_reasonid[b]=Integer.parseInt(gift);
		           
		        	b++;
	 	                	
		        }
		        
		        for(int x=0;x<temp6;x++) 
		        {
		         
               	data.Insertreward_engine_out(userloginto[x], courseid_engine[x], date_engine[x], gift_point[x], gift_reasonid[x]);
               	
    
		        }
		        
		        
		        getreward_engine_display();   
	        	 
	             
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {  
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }
	
	
	public void getreward_engine_to()
	 { 
		
		 try {
			 		    
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME9);
		 		
		 		request.addProperty("ULoginRewardTo",onlyname);   
	 	 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true;  
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION9, envelope);   
		    	 Object result = (Object)envelope.getResponse();
		 	      str1=result.toString(); 
		 	      
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
		        
		        
		         
		        int temp2=arrauserData.length;   
		        temp16=((arrauserData.length)/12);
		        new_userloginto=new String[temp16]; 
		        new_userloginby=new String[temp16];
		        new_courseid_engine=new int[temp16];  
		        new_date_engine=new String[temp16];    
		        new_gift_point=new int[temp16]; 
		        new_gift_reason=new String[temp16];
		        new_gift_reasonid=new int[temp16];
		             
		       
		         
		        for(int a=2,b=0;a<temp2;a=a+12) 
		        {
		        	new_userloginby[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        
		        for(int a=4,b=0;a<temp2;a=a+12)
		        {
		        	new_courseid_engine[b]=Integer.parseInt(arrauserData[a]);
		        	b++;
	 	                	
		         }
		        
		        for(int a=9,b=0;a<temp2;a=a+12)
		        {
		        	new_date_engine[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        for(int a=10,b=0;a<temp2;a=a+12)
		        	
		        {
		        		        	
		        	new_gift_point[b]=Integer.parseInt(arrauserData[a]);
		        	b++;
	 	                	
		         } 
		        for(int a=11,b=0;a<temp2;a=a+12)
		        {   
		        	new_gift_reason[b]=arrauserData[a];
		        	String gift=new_gift_reason[b]; 
		        	if(gift.contains(";"))
		        	{
		        		gift=gift.replace(";", "");
		        	}
		        	
		        	new_gift_reasonid[b]=Integer.parseInt(gift);
		           
		        	b++;  
	 	                	
		        }  
		        
		        for(int x=0;x<temp16;x++) 
		        {
		         
              	data.Insertreward_engine_in(new_userloginby[x], new_courseid_engine[x], new_date_engine[x], new_gift_point[x], new_gift_reasonid[x]);
              	
   
		        }
		        
		        
		        new_getreward_engine_display();   
	        	 
	             
		        
		 	    }   
		 }
	 	    catch (Exception e)     
	        {  
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
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
		 	      str1=result.toString(); 
		 	      
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
		         
		         		           
		         
		         
	        	 viewbalance();
	             
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 
			 System.out.println("Error msg:"+e.getMessage()); 
	        
	        }
	 }  
	public void getreward_point1()
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
		 	      str1=result.toString(); 
		 	      
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
	public void getreward_point2()
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
		 	      str1=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 	    	
		 	    	newrewrdtbl.setVisibility(View.GONE); 
	            	savetbl.setVisibility(View.GONE);
	            	nametbl.setVisibility(View.GONE);
		 		   
		        	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		        	alertbox.setMessage(onlyname+" "+"you are not authorized to assign reward.... ");
	    		     
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
		 	    	
		 	    	
		 	    	newrewrdtbl.setVisibility(View.VISIBLE); 
	            	savetbl.setVisibility(View.VISIBLE);
	            	nametbl.setVisibility(View.VISIBLE);
		 	    	
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 
			 System.out.println("Error msg:"+e.getMessage()); 
	        
	        }
	 } 
	
	
	public void getreward_point3(String names)
	 {   
		 
		 try { 
			 		  
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME10);   
		 		
		 		request.addProperty("UserName",names); 
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION10, envelope);     
		    	 Object result = (Object)envelope.getResponse();
		 	    String   str12=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str12);
		 	     
		 	    String[] arrauserData;
		        arrauserData = ((String) str12).split("; ");
		        
		        
		 	    getsumearnedpoint(names); 
		 	    getsumredimpoint(names); 
		 	    
		 	  // if((str12.equals(null)||str12.equals("anyType{}"))||(arrauserData[1].equals("0")))
		 	    
		 	    if(str12.equals(null)||str12.equals("anyType{}"))
		        {
		 	    	
		 	    	
		 	    	addrewardpoints(names,0,0,0,null,null,Integer.parseInt(sumearnedpoint),Integer.parseInt(sumredimedpoint));
		 	    	
		 	    	
		        } 
		 	    else 
		 	    {
		 	    	
		 	    	if(arrauserData[1].equals("0"))
		 	    	{
		 	    		addrewardpoints(names,0,0,0,null,null,Integer.parseInt(sumearnedpoint),Integer.parseInt(sumredimedpoint));
		 	    	}
		 	    	
		 	    	else
		 	    	{
		 	    			 	    	
		 	    	updatereward_point_earnedpnt(names,Integer.parseInt(sumearnedpoint));
		 	    	}
		 	    	
		        
		 	    }   
		 }
	 	    catch (Exception e)      
	        {
			 
			 System.out.println("Error msg:"+e.getMessage()); 
	        
	        }
	 }   
	
	
	
	
	public void getreward_point4()
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
		 	     String  str14=result.toString();   
		 	      
		 	     System.out.println("Data:"+str14);    
		 	    getsumearnedpoint(onlyname);
		 	    getsumredimpoint(onlyname);   
		 	    
		 	    if(Integer.parseInt(sumearnedpoint)-Integer.parseInt(sumredimedpoint)>=getpoint) 
		 	    {
		 	    	
		 	    	insert_coupen_ibharati(sdate12,sdate13,coupen,"0.2");
		 	    	insertredimpoint(onlyname,getpoint,""+curtimestamp,coupen);
		 	    	getsumredimpoint(onlyname);
		 	    
		 	    if(str14.equals(null)||str14.equals("anyType{}"))
		        {
		 	    	
		 	    	
		 	    	addrewardpoints(onlyname,0,0,0,null,null,Integer.parseInt(sumearnedpoint),Integer.parseInt(sumredimedpoint));
		 	    	
		 	    	
		        } 
		 	    
		 	    else 
		 	    {
		 	    	 
		 	    	
		 	    	updatereward_point_redimpnt(onlyname,Integer.parseInt(sumredimedpoint)); 	
		 	    	
		         
		 	    }   
		 }
		 	    else
		 	    {  
		 	    	
		 	    	int pntinhand=Integer.parseInt(sumearnedpoint)-Integer.parseInt(sumredimedpoint);
		 	    	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		        	alertbox.setMessage("You have " +pntinhand +" point in hand");
	    		     
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
		 }
	 	    catch (Exception e)      
	        {
			 
			 System.out.println("Error msg:"+e.getMessage()); 
	        
	        }
	 } 
	
	
	public void getreward_point5(String name)
	 {   
		 
		 try { 
			 		  
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME10);   
		 		
		 		request.addProperty("UserName",name); 
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION10, envelope);     
		    	 Object result = (Object)envelope.getResponse();
		 	      String str13=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str13);
		 	    getsumearnedpoint(name);
		 	    getsumredimpoint(name);
		 	    
		 	    if(str13.equals(null)||str13.equals("anyType{}"))
		        {
		 	    	
		 	    	
		 	    	
		 	    	addrewardpoints(user_login_name, intpoint, usedpnt, availpnt, strdateassign, strdateexpire,Integer.parseInt(sumearnedpoint),Integer.parseInt(sumredimedpoint));
		 	    	
		        } 
		 	    else 
		 	    {
		 	    	
		 	    	
		 	    	
		 	    	updaterewardpointswhole(name,intpoint,usedpnt, availpnt,strdateassign,strdateexpire);	
		 	    	
		          
		 	    }   
		 }
	 	    catch (Exception e)      
	        {
			 
			 System.out.println("Error msg:"+e.getMessage()); 
	        
	        }
	 }  
	
	
	
	
	
	
	
	
	
	public void updaterewardpoints(String uname,int usedpoint,int availpoint)
	 { 
		 try {
			 		 
			 	  
	      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME11);
	    	 	
	    	 	
	    	 	request.addProperty("UserName",uname);
	    	 	request.addProperty("UsedPoint",usedpoint);
	    	 	request.addProperty("AvaliablePoint",availpoint);    
	    	 	
	    	 	
		 		
		 				 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 	    androidHttpTransport.call(SOAP_ACTION11, envelope);  
		       
		 	  Toast.makeText(reward.this, "Points updated successfully",Toast.LENGTH_SHORT).show();
		       
		 	      
		 } 
	        catch (Exception e)    
	        { 
	       
	        	System.out.println("ERROR:"+e.getMessage());
	        }  
	 }
	
	public void getuser_login(String fnames,String lnames)
	 {   
		
		 try {
			 		  
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME12);
		 		
		 		request.addProperty("UserName",fnames);  
		 		request.addProperty("UserSurname",lnames);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);   
		 	
		 		androidHttpTransport.call(SOAP_ACTION12, envelope);   
		    	 Object result = (Object)envelope.getResponse(); 
		 	      str1=result.toString();  
		 	      
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
		         
		        
		        if(arrauserData[0].contains(";"))
		        {
		        	arrauserData[0]=arrauserData[0].replace(";", "");
		        }
		        
		        
		         
		         user_login_name=arrauserData[0];
		         
		         
		           
	        	 
	             
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 
			 System.out.println("Error msg:"+e.getMessage()); 
	        
	        }
	 }  

	public void getsumearnedpoint(String name)
	{
		try { 
	 		   
		 	  
    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME13);   
	 		
	 		request.addProperty("ULoginRewardTo",name);
	 		
	 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request); 
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	 	
	 		androidHttpTransport.call(SOAP_ACTION13, envelope);   
	    	 Object result = (Object)envelope.getResponse();  
	 	      str1=result.toString(); 
	 	      
	 	     System.out.println("Data:"+str1);
	 	    if(str1.equals(null)||str1.equals("anyType{}")||str1.equals("anyType{anyType=; }")) 
	        {
	 	    	sumearnedpoint="0";
	        	
	        } 
	 	    else
	 	    {
	 	    if(str1.contains("anyType{anyType=")||str1.contains("}")||str1.contains("anyType=")||str1.contains(";"))
	        {
	    	  str1=str1.replace("anyType{anyType=", "");
	    	  str1=str1.replace("anyType=", "");
	    	  str1=str1.replace("}", ""); 
	    	  str1=str1.replace(";","");
	    	  str1=str1.trim();
	    	  System.out.println("String"+str1); 
	    	  
	        }
	        sumearnedpoint=str1;
	           
	                 
	 	    }   
	 }
 	    catch (Exception e)    
        {
		 
		 System.out.println("Error msg:"+e.getMessage());  
        
        }  
		
		 
	}
	
	public void getsumredimpoint(String name) 
	{
		
		try {  
	 		  
		 	  
    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME14); 
	 		
	 		request.addProperty("UserName",name);
	 		
	 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request); 
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	 	
	 		androidHttpTransport.call(SOAP_ACTION14, envelope);   
	    	 Object result = (Object)envelope.getResponse();
	 	      str1=result.toString(); 
	 	      
	 	     System.out.println("Data:"+str1); 
	 	    if(str1.equals(null)||str1.equals("anyType{}")||str1.equals("anyType{anyType=; }"))
	        {
	 	    	sumredimedpoint="0";
	        	
	        } 
	 	    else
	 	    {
	 	    if(str1.contains("anyType{anyType=")||str1.contains("}")||str1.contains("anyType=")||str1.contains(";"))
	        {
	    	  str1=str1.replace("anyType{anyType=", "");
	    	  str1=str1.replace("anyType=", "");
	    	  str1=str1.replace("}", ""); 
	    	  str1=str1.replace(";","");
	    	  str1=str1.trim();
	    	  System.out.println("String"+str1); 
	    	  
	        }
	        sumredimedpoint=str1;
	           
	                 
	 	    }   
	 }
 	    catch (Exception e)    
        {
		 
		 System.out.println("Error msg:"+e.getMessage()); 
        
        }
		
		
		
	}
	
	
	
	
	public void updatereward_point_earnedpnt(String name,int pnt)
	{
		
		try {
	 		 
		 	  
      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME15);
    	 	
    	 	
    	 	request.addProperty("UserName",name); 
    	 	request.addProperty("TotalearnedPoint",""+pnt);
    	 	
    	 	
    	 		 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request);
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	 	
	 	   androidHttpTransport.call(SOAP_ACTION15, envelope);
	 	   
	 	   
	       
	 	   Toast.makeText(reward.this, "Updated Succussfully",Toast.LENGTH_SHORT).show();
	       
		
	}
		 catch (Exception e)    
	        { 
	        //tv.setText(e.getMessage());
	        	System.out.println("ERROR:"+e.getMessage());
	        }  
	
	}
	
	public void updatereward_point_redimpnt(String name,int pnt)
	{
		
		try {
	 		 
		 	  
      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME16);
    	 	
    	 	
    	 	request.addProperty("UserName",name);
    	 	request.addProperty("TotalredimPoint",""+pnt);
    	 	
    	 	
    	 		 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request);
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	 	
	 	   androidHttpTransport.call(SOAP_ACTION16, envelope);
	 	   
	 	   
	       
	 	   Toast.makeText(reward.this, "Updated Succussfully",Toast.LENGTH_SHORT).show();
	       
		
	}
		 catch (Exception e)    
	        { 
	        //tv.setText(e.getMessage());
	        	System.out.println("ERROR:"+e.getMessage());  
	        }  
	
	}
	
	public void insertredimpoint(String redimname,int redimpnt,String redimdate,String coupen_id)
	{
		
		try {
	 		 
		 	  
      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME17);
    	 	
    	 	
    	 	request.addProperty("redimUserName",redimname); 
    	 	request.addProperty("redimPoint",redimpnt);
    	 	request.addProperty("redimDate",redimdate);
    	 	request.addProperty("coupenId",coupen_id);
    	 	
    	 		 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request);
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL); 
	 	
	 	   androidHttpTransport.call(SOAP_ACTION17, envelope);
	 	   
	 	   
	       
	 	   Toast.makeText(reward.this, "Coupen generated succussfully",Toast.LENGTH_SHORT).show();
	       
	       
	 	      
	 } 
        catch (Exception e)    
        { 
        //tv.setText(e.getMessage());
        	System.out.println("ERROR:"+e.getMessage());
        }  
	}
	
	public void updaterewardpointswhole(String name,int ypnt,int upnt,int apnt,String adate,String edate)
	{
		
		try {
	 		 
		 	  
      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME18);
    	 	
    	 	
    	 	request.addProperty("UserName",name); 
    	 	request.addProperty("yearlyPoint",ypnt);
    	 	request.addProperty("usedPoint",upnt);
    	 	request.addProperty("AvaliablePoint",apnt);
    	 	request.addProperty("dateofassign",adate);
    	 	request.addProperty("dateofexpire",edate);
    	 	
    	 		 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request);
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL); 
	 	
	 	   androidHttpTransport.call(SOAP_ACTION18, envelope);
	 	   
	 	   
	       
	 	   Toast.makeText(reward.this, "Points Updated Successfully",Toast.LENGTH_SHORT).show();
	       
	       
	 	      
	 } 
        catch (Exception e)    
        { 
        //tv.setText(e.getMessage());
        	System.out.println("ERROR:"+e.getMessage());
        }  
	}
	
	
	public void getredim_details_lplanet()
	 { 
		
		 try {
			 		   
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME19);
		 		
		 		request.addProperty("UserName",onlyname);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION19, envelope);   
		    	 Object result = (Object)envelope.getResponse();
		 	      str1=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 		   
		        	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		        	//alertbox.setMessage(onlyname+" "+"you dont have any rewards earned.... ");
	    		     
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
		        temp61=((arrauserData.length)/5);
		        redimdate=new String[temp61]; 
		        redimcoupen=new String[temp61]; 
		         
		             
		       
		         
		        for(int a=3,b=0;a<temp2;a=a+5)
		        {
		        	redimdate[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        for(int a=4,b=0;a<temp2;a=a+5)
		        {
		        	redimcoupen[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        
		        
		            
	        	 
	             
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 } 
	 
	public void funassignpoints() 
	{
		myDialog = new Dialog(reward.this);
     	myDialog.setContentView(R.layout.assignpointbyadmin);
     	myDialog.setTitle("Assign Points");
     	myDialog.setCancelable(true);   
     	unameadapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item);
     	Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		System.out.println("Month is:"+month);
		int year = cal.get(Calendar.YEAR);   
		
		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		curtimestamp=(int) (cal.getTimeInMillis()/1000L);
		
		System.out.println("Time In Millis:"+curtimestamp);
		
		String sdate=(+ day + "-" + (month + 1) + "-" + year);
		
		//increament date by 12 months
		cal.add(Calendar.MONTH,11);
		int month1 = cal.get(Calendar.MONTH);
		System.out.println("Month is:"+month);
		int year1 = cal.get(Calendar.YEAR);   
		
		
		int day1 = cal.get(Calendar.DAY_OF_MONTH);
		
				 nexttimestamp=(int) (cal.getTimeInMillis()/1000L);
		
		System.out.println("Time In Millis:"+nexttimestamp);
		
		 sdate1=(+ day1 + "-" + (month1 + 1) + "-" + year1);
			
		System.out.println("Date increamented by 12 months:"+sdate1);
		
		
     	
         txtdate=(TextView) myDialog.findViewById(R.id.txtsysdate); 
     	 utype = (Spinner) myDialog.findViewById(R.id.spnrtype);
     	 sprname = (Spinner) myDialog.findViewById(R.id.spnrname);      
         edtpoint1 = (EditText) myDialog.findViewById(R.id.editpoints);
         
         //getuser_login(sprname);
     	 
          
         txtdate.setText("Date:"+sdate);     
                
        
         Button button = (Button) myDialog.findViewById(R.id.btnadd);
         
         
         
 //User type spinner 
 
         ArrayAdapter<CharSequence> utypeadapter = ArrayAdapter.createFromResource(
         this, R.array.ADMIN, android.R.layout.simple_spinner_item);
         utypeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         utype.setAdapter(utypeadapter);
         utype.setOnItemSelectedListener(new MyOnutypepointSelectedListener());
 		
          
        
         
         strutype=utype.getSelectedItem().toString();
         
      	 getuname(strutype);
      	 point(strutype);  
      	  
      	
    	 
         
             for(int x=0;x<temp6;x++) 
	        {
	               	 
        	 unameadapter.add(fname[x]+" "+lname[x]);
	        }
         sprname.setAdapter(unameadapter);
    	 
         
         
         edtpoint1.setText(str12); 
         
              	 
         
         button.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
         	         
        	  strdateassign=""+curtimestamp;
        	 strutype=utype.getSelectedItem().toString();
        	 String strname=sprname.getSelectedItem().toString();
        	 
        	 String[] temp;
     		temp=new String[2];
     		
     		temp=strname.split(" ");
     		
     		
     		
     		getuser_login(temp[0],temp[1]);
        	 
        	
        	 intpoint=Integer.parseInt(str12);
        	  strdateexpire=""+nexttimestamp;
        	 
        	 System.out.println("date is:"+strdateassign);     
        	 System.out.println("type is:"+strutype);
        	 System.out.println("name is:"+strname);                 	 
        	 System.out.println("point are:"+intpoint);
        	 
        	  usedpnt=0;
        	 availpnt=intpoint-usedpnt;
        	 
        	   
        	 getreward_point5(user_login_name);
        	 
        	 
        	 //addrewardpoints(user_login_name, intpoint, usedpnt, availpnt, strdateassign, strdateexpire,earningint,redimingint);
        	 
        	
        	
        	 myDialog.dismiss(); 
                	  
             }  
         });   

         myDialog.show();   
         
          
	}
	
	
	
	
	public int getcourseid(String course)
	 {
		 Cursor c1=data.selectcourseid(course);
		 
			while(c1.moveToNext())
			{
				acourseid=c1.getInt(0);
				
				
			}
			return acourseid;
	 }
	public void getdistinctname()
	 {
		 Cursor c1=data.selectdistinctusersforreward();
		 rec=new String[c1.getCount()];
		 int i=0;
			while(c1.moveToNext())
			{
				String name=c1.getString(0);
				
				rec[i]=name; 
				i++;
				 
			}
			
			
	 }
	 
	public void getsumpoint(String name1)
	 {
		
		 Cursor c1=data.selectsumpointsforreward(name1); 
		 
		 
			while(c1.moveToNext())
			{
				 pointe=c1.getInt(0);
				
				
			}
		
	 } 
	public int getreasonid(String reason)
	 {
		 Cursor c1=data.selectreasonid(reason);
			while(c1.moveToNext())
			{
				reasonid1=c1.getInt(0);      
				
				
			}
			return reasonid1;
	 }
	public String getreason(int reasonid)
	 {
		 Cursor c1=data.selectreason(reasonid);
			while(c1.moveToNext())
			{ 
				strreason_given=c1.getString(0);    
				
				
			}
			return strreason_given;
	 }
	public void getrewardgivendetails(String name)
	 {
		 Cursor c1=data.selectusersforrewardgiven(name);
		 rec_name_given=new String[c1.getCount()];
		 date_given=new String[c1.getCount()];
		 point_given=new int[c1.getCount()];
		 reasonid_given=new int[c1.getCount()];
		 int i=0;
			while(c1.moveToNext())
			{
				    String rec=c1.getString(1);
				    String dat=c1.getString(3);
		  		    int pointec=c1.getInt(4);
				    int rids=c1.getInt(5);
				    
				    rec_name_given[i]=rec;
				    date_given[i]=dat;
				    point_given[i]=pointec;
				    reasonid_given[i]=rids;
				    i++;
				
				
			}
			
	 }
	
	
	public void getreward_engine_display() 
	 {
   	int i=0;
   	int flag=0;
		 
   	getdistinctname();
    
   	
   	for(int i4=0;i4<rec.length;i4++) 
   	{
   		
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
	            final String test2=rec[i4];
	            
	        	getsumpoint(test2);
	            
	         // Create a TextView to house the name of the province 
	            final TextView labelname = new TextView(this);
	            
	                   
	            labelname.setId(200+i);
	            labelname.setText("Name:" +test2); 
	            System.out.println("User Name is:"+rec[i4]);
	            labelname.setTextColor(Color.BLACK);
	            
	            tr.addView(labelname);
	            
	            final Button btndetails = new Button(this);
		           
                
	           	btndetails.setText("View Details");
	            btndetails.setTextColor(Color.BLACK);
	                 
            
                tr.addView(btndetails);    
	            
	            final TextView labelpoint = new TextView(this);
	             
                
	            labelpoint.setId(200+i);
	            labelpoint.setText("Total Points assigned by you: "+pointe);      
	           
	            labelpoint.setTextColor(Color.BLACK);
	            
	            tr2.addView(labelpoint);
	            
	            final Button btnassign = new Button(this);
		                        
	            btnassign.setText("Assign points");
	            btnassign.setTextColor(Color.BLACK);
	                 
            
                tr2.addView(btnassign);
                
               
                final ImageView image = new ImageView(this);
	             
                
                image.setId(200+i);
                image.setImageResource(R.drawable.line);  
	           
               
	            
	            tr4.addView(image);
	            
	            
              
               
           
              
           
	         // Add the TableRow to the TableLayout 
                 rewardgiventbl1.addView(tr, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));
               
               rewardgiventbl1.addView(tr2, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));
               
               i++;  
	            
	            btnassign.setOnClickListener(new Button.OnClickListener()  
	        	{ 
	            	
	            	public void onClick (View v)
	        		{ 
	            			            		
	        		   String temp=(String) labelname.getText();
	        		   funassignmorepoints(temp);
	        		   
	        		          		  
	        		}  
	        	});  
	            btndetails.setOnClickListener(new Button.OnClickListener()  
	        	{ 
	            	
	            	public void onClick (View v)
	        		{ 
	            		
	            		
	            	   givenrewarddetails.removeAllViews(); 
	        		   String temp=(String) labelname.getText();
	        		   if(temp.contains("Name:"))
	        		   {
	        			temp=temp.replace("Name:", "");    
	        		   }
	        		   getgivenrewarddisplay(temp);
	        		   rewardgiventbl.setVisibility(View.GONE);     
	        		   rewardgiventbl1.setVisibility(View.GONE);
	        		   givenrewarddetails.setVisibility(View.VISIBLE);
	        	       givenrewarddetails1.setVisibility(View.VISIBLE);
	        		   
	        		   
	        		          		  
	        		}
	        	});
	            
	              
				}
				catch (Exception e)    
		        {
		         
		        System.out.println("Error msg:::::::::"+e.getMessage());
		        }
			}
	 }
	
	
	public void redim_detail_display() 
	 {
  	int i=0;
  	
  	final TableRow tr = new TableRow(this); 
    tr.setId(100+i);
    tr.layout(0, 0, 0, 0); 
    
    final TableRow tr2 = new TableRow(this); 
    tr2.setId(100+i);
    tr2.layout(0, 0, 0, 0);
    
    
  	
  	final TextView labelname = new TextView(this);
    
    
    labelname.setId(200+i);
    labelname.setText("Coupen will expire in 7 days"); 
    
    labelname.setTextColor(Color.BLACK);
    
    tr.addView(labelname);
    
final TextView labeldatetitle = new TextView(this);
    
    
labeldatetitle.setId(200+i);
labeldatetitle.setText("Date"); 
    
labeldatetitle.setTextColor(Color.BLACK);

labeldatetitle.setPadding(20, 0, 0, 0);
tr.setPadding(0, 1, 0, 1);

    
    tr2.addView(labeldatetitle);
    
final TextView labelcoupentitle = new TextView(this);
    
    
labelcoupentitle.setId(200+i);
labelcoupentitle.setText("Coupen Id"); 
    
labelcoupentitle.setTextColor(Color.BLACK);

labelcoupentitle.setPadding(20, 0, 0, 0);
tr2.setPadding(0, 1, 0, 1);


    
    tr2.addView(labelcoupentitle);
    
    showredimdetailstbl.addView(tr, new TableLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
           LayoutParams.WRAP_CONTENT));
 
   showredimdetailstbl.addView(tr2, new TableLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
           LayoutParams.WRAP_CONTENT));
  	
   
  	
  	for(int i4=0;i4<redimcoupen.length;i4++) 
  	{
  		
				try 
				{
				
	            final TableRow tr1 = new TableRow(this); 
	            tr1.setId(100+i);
	            tr1.layout(0, 0, 0, 0); 
      	
	            
	         // Create a TextView to house the name of the province 
	            
	            int date1=Integer.parseInt(redimdate[i4]);
	            
	            Date date = new Date( date1* 1000L); 
	            
	            SimpleDateFormat postFormater = new SimpleDateFormat("dd-MM-yyyy"); 
	            
	            String newDateStr = postFormater.format(date); 
	               
	            
	            final TextView labeldate = new TextView(this);
	             
               
	            labeldate.setId(200+i);
	            labeldate.setText(""+newDateStr);      
	           
	            labeldate.setTextColor(Color.BLACK);
	            
	            tr1.addView(labeldate);
	            
	            final TextView labelcoupencode = new TextView(this);
	             
	               
	            labelcoupencode.setId(200+i);
	            labelcoupencode.setText(""+redimcoupen[i4]);      
	           
	            labelcoupencode.setTextColor(Color.BLACK);
	            
	            tr1.addView(labelcoupencode);
	            	            
	            
             
	            
          
             
          
	         // Add the TableRow to the TableLayout 
                showredimdetailstbl.addView(tr1, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));
                              
              
              i++;  
	            
	           
	            
	              
				}
				catch (Exception e)    
		        {
		         
		        System.out.println("Error msg:::::::::"+e.getMessage());
		        }
			}
	 }
	
	public void new_getreward_engine_display() 
	 {
  	int i=0;
  	  
  	
  	for(int i4=0;i4<new_userloginby.length;i4++) 
  	{
  		
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
	            final String test2=new_userloginby[i4];
	            
	        	
	            
	         // Create a TextView to house the name of the province 
	            final TextView labelname = new TextView(this);
	            
	                   
	            labelname.setId(200+i);
	            labelname.setText("Reward Given By:" +test2); 
	            System.out.println("User Name is:"+new_userloginby[i4]);
	            labelname.setTextColor(Color.BLACK);
	            
	            tr.addView(labelname);
	            
	            
	            int date1=Integer.parseInt(new_date_engine[i4]);
	            
	            Date date = new Date( date1* 1000L); 
	            
	            SimpleDateFormat postFormater = new SimpleDateFormat("dd-MM-yyyy"); 
	            
	            String newDateStr = postFormater.format(date); 
	            
	            final TextView labeldate = new TextView(this);
	             
	               
	            labeldate.setId(200+i);
	            labeldate.setText("Date: "+newDateStr);      
	           
	            labeldate.setTextColor(Color.BLACK);
	            
	            tr1.addView(labeldate);
	            
	            final TextView labelpoint = new TextView(this);
	             
               
	            labelpoint.setId(200+i);
	            labelpoint.setText("Points assigned to you: "+new_gift_point[i4]);      
	           
	            labelpoint.setTextColor(Color.BLACK);
	            
	            tr2.addView(labelpoint);
	            
	            String reasone=getreason(new_gift_reasonid[i4]);
	            
	            final TextView labelreason = new TextView(this);
	             
	               
	            labelreason.setId(200+i);
	            labelreason.setText("Reason For Reward: "+reasone);      
	           
	            labelreason.setTextColor(Color.BLACK); 
	            
	            tr3.addView(labelreason);
	            
	            final ImageView image = new ImageView(this);
	             
                
                image.setId(200+i);
                image.setImageResource(R.drawable.line);  
	           
               
	            
	            tr4.addView(image);    
             
                
          
             
          
	         // Add the TableRow to the TableLayout 
                rewardearnedtbl.addView(tr, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,    
	                    LayoutParams.WRAP_CONTENT));
              
                rewardearnedtbl.addView(tr1, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));
                rewardearnedtbl.addView(tr2, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));
             
               rewardearnedtbl.addView(tr3, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));
               
               rewardearnedtbl.addView(tr4, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));
              
	            
	            
				
				
	                  
	            i++;  
	                
	            
	              
				}
				catch (Exception e)    
		        {
		         
		        System.out.println("Error msg:::::::::"+e.getMessage());
		        }
			}
	 }
	public void getgivenrewarddisplay(String name1) 
	 {
  	int i=0;
  	
  	final TableRow tr = new TableRow(this); 
    tr.setId(100+i);
     tr.layout(0, 0, 0, 0); 
  	
		 
  	getrewardgivendetails(name1);
  	
  	final String test2=name1;
  	
 // Create a TextView to house the name of the province 
    final TextView labelname = new TextView(this);
    
           
    labelname.setId(200+i);
    labelname.setText("Name:" +test2); 
    System.out.println("User Name is:"+name1);
    labelname.setTextColor(Color.BLACK);
    
    tr.addView(labelname);
    
    
    givenrewarddetails.addView(tr, new TableLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
           LayoutParams.WRAP_CONTENT)); 
   
  	
  	for(int i4=0;i4<rec_name_given.length;i4++) 
  	{  
  		
				try 
				{
				
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
	            
	            
	        	int date1=Integer.parseInt(date_given[i4]);
	            
	            Date date = new Date( date1* 1000L); 
	            
	            SimpleDateFormat postFormater = new SimpleDateFormat("dd-MM-yyyy"); 
	            
	            String newDateStr = postFormater.format(date); 
	            
	            final TextView labeldate = new TextView(this);
	             
	               
	            labeldate.setId(200+i);  
	            labeldate.setText("Date: "+newDateStr);      
	             
	            labeldate.setTextColor(Color.BLACK);
	            
	            tr1.addView(labeldate);
	            
	            
	            
	            final TextView labelpoint = new TextView(this);
	             
               
	            labelpoint.setId(200+i);
	            labelpoint.setText("Points Assigned: "+point_given[i4]);      
	           
	            labelpoint.setTextColor(Color.BLACK);
	            
	            tr2.addView(labelpoint);
	            
	            String reas=getreason(reasonid_given[i4]);
	            
	            
	            final TextView labelresaon = new TextView(this);
	             
	               
	            labelresaon.setId(200+i);
	            labelresaon.setText("Reason For Reward: "+reas);       
	           
	            labelresaon.setTextColor(Color.BLACK);
	            
	            tr3.addView(labelresaon);
	            
	            final ImageView image = new ImageView(this);
	             
                
                image.setId(200+i);
                image.setImageResource(R.drawable.line);  
	           
               
	            
	            tr4.addView(image);             
	            
             
              
          
             
          
	         // Add the TableRow to the TableLayout 
	            
	            givenrewarddetails.addView(tr1, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));
           
	            givenrewarddetails.addView(tr2, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));
              
	            givenrewarddetails.addView(tr3, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));
	            givenrewarddetails.addView(tr4, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));
              
	            
	            
				
				
	                  
	            i++;  
	            
	                     
	              
				}
				catch (Exception e)    
		        {
		         
		        System.out.println("Error msg:::::::::"+e.getMessage());
		        }
			}
	 }
	public void gettotalpntsearned(String string,String string111) 
	 {
 	
 	
 	final TableRow tr = new TableRow(this); 
 	tr.setId(100);
    tr.layout(0, 0, 0, 0); 
    
    final TableRow tr1 = new TableRow(this); 
 	tr1.setId(100);
    tr1.layout(0, 0, 0, 0); 
 		
    // Create a TextView to house the name of the province 
   final TextView labeltotalearn = new TextView(this);
   
          
   labeltotalearn.setId(200);
   labeltotalearn.setText("Total Points Earned:"+string); 
   System.out.println("User Name is:"+string);
   labeltotalearn.setTextColor(Color.BLACK);
   
   tr.addView(labeltotalearn);
   
   final TextView labeltotalredeem = new TextView(this);
   
   
   labeltotalredeem.setId(200);
   labeltotalredeem.setText("Total Points Redeemed:"+string111); 
   System.out.println("User Name is:"+string111);
   labeltotalredeem.setTextColor(Color.BLACK);
   
   tr1.addView(labeltotalredeem);
   
   
   totalpntsearned.addView(tr, new TableLayout.LayoutParams(
           LayoutParams.WRAP_CONTENT,
          LayoutParams.WRAP_CONTENT)); 
   
   totalpntsearned.addView(tr1, new TableLayout.LayoutParams(
           LayoutParams.WRAP_CONTENT,
          LayoutParams.WRAP_CONTENT)); 
   
   final TableRow tr2 = new TableRow(this); 
   tr2.setId(100);
   tr2.layout(0, 0, 0, 0); 
   
   final Button btnredim = new Button(this);  
   
   btnredim.setText("Redeem Points");
   btnredim.setTextColor(Color.BLACK);
        

   tr2.addView(btnredim);
   
   
   final Button btndetails = new Button(this);
      
   btndetails.setText("View Details");
   btndetails.setTextColor(Color.BLACK);
        

   tr2.addView(btndetails);
   
   final Button btnredimdetails = new Button(this);
   
   btnredimdetails.setText("Redeem Details");
   btnredimdetails.setTextColor(Color.BLACK);
        

   tr2.addView(btnredimdetails);
   
   
   redimtbl.addView(tr2, new TableLayout.LayoutParams(
           LayoutParams.WRAP_CONTENT,
          LayoutParams.WRAP_CONTENT)); 
   
   btnredim.setOnClickListener(new Button.OnClickListener()  
	{ 
    	
    	public void onClick (View v)
		{ 
    			            		
		   pointstoredim();
		   
		          		  
		}  
	});
   
   btnredimdetails.setOnClickListener(new Button.OnClickListener()  
	{ 
   	
   	public void onClick (View v)
		{ 
   		  		
   		rewardearnedtbl.setVisibility(View.GONE); 
    	newrewrdtbl.setVisibility(View.GONE); 
    	savetbl.setVisibility(View.GONE);
    	nametbl.setVisibility(View.GONE);
    	givenrewarddetails.setVisibility(View.GONE);
    	givenrewarddetails1.setVisibility(View.GONE);
    	otheropttbl.setVisibility(View.GONE);
    	backtbl1.setVisibility(View.GONE);
      	rewardgiventbl.setVisibility(View.GONE); 
    	rewardgiventbl1.setVisibility(View.GONE);
    	redimtbl.setVisibility(View.GONE);  
    	totalpntsearned.setVisibility(View.GONE);
    	
    	showredimdetailstbl.setVisibility(View.VISIBLE);
    	
   		getredim_details_lplanet();
   		showredimdetailstbl.removeAllViews();
   		redim_detail_display();
		   
		          		  
		}  
	});
   
   btndetails.setOnClickListener(new Button.OnClickListener()  
	{ 
   	
   	public void onClick (View v)
		{ 
   			            		
   		rewardearnedtbl.removeAllViews();
    	data.deletereward_engine_in();
    	getreward_engine_to();
    	rewardearnedtbl.setVisibility(View.VISIBLE);
    	backtbl1.setVisibility(View.VISIBLE);
    	totalpntsearned.setVisibility(View.GONE);
    	redimtbl.setVisibility(View.GONE);
		   
		          		  
		}  
	});  
 	
 	
	 }
	
	public void funassignmorepoints(String temp1)  
	{
		myDialog = new Dialog(reward.this);
     	myDialog.setContentView(R.layout.assignmorepoints);
     	myDialog.setTitle("Assign More Points");
     	myDialog.setCancelable(true);   
     	
     	
		  
     	
         atxtdate=(TextView) myDialog.findViewById(R.id.txdate); 
     	 auname = (TextView) myDialog.findViewById(R.id.txtitle);
     	 asprreason = (Spinner) myDialog.findViewById(R.id.spnrreason);      
         aedtpoint1 = (EditText) myDialog.findViewById(R.id.edtpoint);  
     	 
          
         atxtdate.setText("Date:"+sdate);   
         
         if(temp1.contains("Name:"))
    	 {
        	 temp1=temp1.replace("Name:", "");
    	 }
         auname.setText(temp1);  
         auname.setTextColor(Color.BLACK);
         
        
         Button button = (Button) myDialog.findViewById(R.id.btassign);
         
         
         
            	  
         ArrayAdapter<CharSequence> arewardreasonadapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item);
         for(int x=0;x<temp11;x++) 
	        {
	               	 
      	 arewardreasonadapter.add(arrreason[x]);
	        }
         asprreason.setAdapter(arewardreasonadapter);      
         
          
         
              	 
         
         button.setOnClickListener(new OnClickListener() {
         public void onClick(View v) { 
         	         
        	 String strdateassign=""+curtimestamp;     
        	 String aname=auname.getText().toString();
        	 String areason=asprreason.getSelectedItem().toString();
        	 
        	 int apoint=Integer.parseInt(aedtpoint1.getText().toString());
        	 int acourse = 0;
        	 
        	 int id=getreasonid(areason);   
        	 
        	 int used_point2=used_point1+apoint;  
        	 
        	 int availpoint2=yearly_point-used_point2;              
        	 
        	
        	 
        	 
        	 addrewardengine(aname, onlyname, acourse, strdateassign, apoint, id);  
        	 updaterewardpoints(onlyname, used_point2, availpoint2);
        	 getreward_point3(aname);
        	 
         		myDialog.dismiss(); 
                	  
             }  
         });   

         myDialog.show();   
         
          
	}
	
	public void viewbalance()
	{
		myDialog = new Dialog(reward.this);
     	myDialog.setContentView(R.layout.rewardbalance);
     	myDialog.setTitle("Your Reward Balance");
     	myDialog.setCancelable(true);   
     	
     			
     	
     	TextView wel=(TextView) myDialog.findViewById(R.id.welcomes); 
     	TextView usedpnt = (TextView) myDialog.findViewById(R.id.txtusedpnt);
     	TextView availpnt = (TextView) myDialog.findViewById(R.id.txtavailpnt);      
           
     	 
          
     	wel.setText("Welcome "+onlyname);   
         
         
         usedpnt.setText("Used Point:"+used_point1);  
         availpnt.setText("Available Point:"+avail_point1);
        
         Button back = (Button) myDialog.findViewById(R.id.btnback); 
         
                  
         back.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
         	         
        	     	 
        	 
         		myDialog.dismiss(); 
                	 
             }  
         });     

         myDialog.show();   
         
          
	}
	public void pointstoredim()
	{
		myDialog = new Dialog(reward.this);
     	myDialog.setContentView(R.layout.redimpoint);
     	myDialog.setTitle("REDIM POINTS");
     	myDialog.setCancelable(true);  
     	
     	final int sum=0;
		 
        
        
     	 final EditText pointee = (EditText) myDialog.findViewById(R.id.edtpoints);
     	 
     	
         Button button = (Button) myDialog.findViewById(R.id.btngeneratecoupen);
         
                
            
         
         button.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
        	 
        	 getpoint=Integer.parseInt(pointee.getText().toString());
        	 
        	 
        	 System.out.println("Points to Redim:"+getpoint);
        	 
        	 long tim = System.currentTimeMillis();
             Random random = new Random(tim);
             long RandomNumber = random.nextInt(curtimestamp);   
              coupen=String.valueOf(RandomNumber);
             if(coupen.contains("-"))
             {
            	 coupen=coupen.replace("-", "");
            	 
             }
             
             double amt=getpoint;
             int value=50;
             amount=(amt/value);
             
            
             
             System.out.println("Gift Amount:"+amount);
                        
             System.out.println("Random Coupen:"+RandomNumber);
             
             getreward_point4();
             
             
            getsumredimpoint(onlyname) ;
            
            totalpntsearned.removeAllViews();
            redimtbl.removeAllViews();
            
            gettotalpntsearned(""+sumearnedpoint, ""+sumredimedpoint);
             
             
             
             
             
             
             
             myDialog.dismiss(); 
             
             rewardearnedtbl.setVisibility(View.GONE); 
         	newrewrdtbl.setVisibility(View.GONE); 
         	savetbl.setVisibility(View.GONE);
         	nametbl.setVisibility(View.GONE);
         	givenrewarddetails.setVisibility(View.GONE);
         	givenrewarddetails1.setVisibility(View.GONE);
         	otheropttbl.setVisibility(View.GONE);
         	backtbl1.setVisibility(View.GONE);
           	rewardgiventbl.setVisibility(View.GONE); 
         	rewardgiventbl1.setVisibility(View.GONE);
         	redimtbl.setVisibility(View.GONE);  
         	totalpntsearned.setVisibility(View.GONE);
         	
         	showredimdetailstbl.setVisibility(View.VISIBLE);
         	
        		getredim_details_lplanet();
        		showredimdetailstbl.removeAllViews();
        		redim_detail_display();
             
             
             
            
             
        	 
             } 
         });

         myDialog.show();
		
	}
	
	public void funaddreason()
	{
		
    	
    	myDialog = new Dialog(reward.this);
     	myDialog.setContentView(R.layout.addreason);
     	myDialog.setTitle("Add Reason");
     	myDialog.setCancelable(true); 
     	
     	 user_type_addreason=(Spinner) myDialog.findViewById(R.id.sprutype);
     	 
     	 
     	
     	 //User type spinner 
  
     	 ArrayAdapter<CharSequence> utypeadapter1 = ArrayAdapter.createFromResource(
          this, R.array.ADMIN, android.R.layout.simple_spinner_item);
     	 utypeadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     	 user_type_addreason.setAdapter(utypeadapter1);
     	 user_type_addreason.setOnItemSelectedListener(new MyOnutypepointSelectedListener2());
  
        
     	 final EditText reason = (EditText) myDialog.findViewById(R.id.editreason);
     	 
     	
         Button button = (Button) myDialog.findViewById(R.id.btnadd);
         
                
            
         
         button.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
        	 
        	 
        	 utype_addreason=user_type_addreason.getSelectedItem().toString();
        	  rewardreason=reason.getText().toString();
        	 addreason();
         	myDialog.dismiss();
             }
         });

         myDialog.show();
    	
	}
	
	
	
	
	
	
	
	
	
	public void fillreasonfromlocal(String types)
	{
		Cursor c1=data.selectreasonutype(types);
		int i=0;
		arrreason1=new String[c1.getCount()]; 
		while(c1.moveToNext())
		{
			String reasons=c1.getString(0);         
			
			arrreason1[i]=reasons;
			
			i++;
			
		}
		
		ArrayAdapter<CharSequence> rewardreasonadapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item);
        
        for(int x=0;x<arrreason1.length;x++) 
        {
         		        	 
        	rewardreasonadapter.add(arrreason1[x]);  
            
        } 
        reason_reward.setAdapter(rewardreasonadapter);  
        reason_reward.setOnItemSelectedListener(new MyOnItemSelectedListener());  
		
	}
	
	

}


