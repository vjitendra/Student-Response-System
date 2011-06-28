package com.SRS6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class addtest_byprofessor extends Activity {
	
	EditText edtNAME,edtDIM,edtTR,edtMS,edtDESC;
	Spinner sprPARENT_UNIT;
	CheckBox chkPUBLISH,chkSQOBO,chkSGA,chkSCA,chkSHUFA,chkSHUFQ,chkDOL,chkTCBP,chkDWDT;
	Button btnsave;
	
	public void onCreate(Bundle icicle)
	 {
		 requestWindowFeature(Window.FEATURE_NO_TITLE);  
	     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	     

	 super.onCreate(icicle);
	 setContentView(R.layout.addtest_byprofessor);
	 }



	private void funreward()
	{
	
	

	}
}