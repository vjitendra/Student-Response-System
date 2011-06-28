package com.SRS6;

import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


public class sturegister extends Activity  {
	
	
	TextView fname;
	TextView lname;
	TextView sub;
	TextView dob;
	TextView email;
	TextView pwd;
	TextView cpwd;
	
	Button b;
	
    DataBaseHelper data;
    
	private int mYear;
	private int mMonth;
	private int mDay;
	static final int DATE_DIALOG_ID = 1;
	
	String sfname,slname,ssub,sdob,semail,spwd,scpwd;
	
	EditText mDateDisplay;
	Button mPickDate;
	
	
	Calendar cal = new GregorianCalendar();
	int month = cal.get(Calendar.MONTH);
	int year = cal.get(Calendar.YEAR);
	int day = cal.get(Calendar.DAY_OF_MONTH);
	//String sdate=(+ (month + 1) + "-" + day + "-" + year);

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.stulogin);
	    
	       
        fname=(TextView)findViewById(R.id.fname);
        lname=(TextView)findViewById(R.id.lname);
        sub=(TextView)findViewById(R.id.sub);
        dob=(TextView)findViewById(R.id.dob);
        email=(TextView)findViewById(R.id.eid);
        pwd=(TextView)findViewById(R.id.pwd);
        cpwd=(TextView)findViewById(R.id.cpwd);
	    
	    data=new DataBaseHelper(this);
	   
	    //data.createstu();
	    
	    mDateDisplay = (EditText) findViewById(R.id.dob);
		mPickDate = (Button) findViewById(R.id.dt);

	    mPickDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

	 // get the current date
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		
		b = (Button) findViewById(R.id.reg);
	    b.setOnClickListener(new View.OnClickListener() {
	    
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				sfname= fname.getText().toString();
				slname= lname.getText().toString();
				ssub= sub.getText().toString();
				sdob= dob.getText().toString();
				semail= email.getText().toString();
				spwd= pwd.getText().toString();
				
				data.stuinsert(sfname, slname, ssub, sdob, semail, spwd);
				System.out.println("Data Inserted Successfully.................");
				getstudetails();
				
			}
			
		});
		
	}
	
	private void updateDisplay() 
	{
		// updates the date in the TextView

		mDateDisplay.setText(
				new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-")
				.append(mDay).append("-")
				.append(mYear).append(" "));


	}
	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener =
		new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, 
				int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}

	};
	
	
	protected Dialog onCreateDialog(int id)
	{
		return new DatePickerDialog(this,
				mDateSetListener,
				mYear, mMonth, mDay);
	}
	
	
	public void getstudetails()
	 {
		 Cursor c1=data.getstu();
			while(c1.moveToNext())
			{
				int n1=c1.getInt(0);
				String n2=c1.getString(1);
				String n3=c1.getString(2);
				String n4=c1.getString(3);
				String n5=c1.getString(4);
				String n6=c1.getString(5);
				String n7=c1.getString(6);
				System.out.println("id:"+n1);
				System.out.println("First name:"+n2);
				System.out.println("last name:"+n3);
				System.out.println("Subject:"+n4);
				System.out.println("DOB:"+n5);
				System.out.println("email id:"+n6);
				System.out.println("passward:"+n7);
			}
	 }
}

