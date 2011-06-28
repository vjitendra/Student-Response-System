package com.SRS6;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper 
{

	static final String dbName="Student14";  
	 
	static final String eTable="quiz";
	static final String qid="Quesid";
	static final String quesno="QuesNo";
	static final String ques="Ques";
	static final String opt="Options";
	static final String cans="answer1";
	static final String sta="Status";
	
	static final String rTable="result";
	static final String qid1="Qid1";
	static final String qno="QNo";
	static final String selopt="option";
	static final String ans="answer";
	
	static final String stuTable="sturege";
	static final String stuid="sid";
	static final String fname="Fname";
	static final String lname="Lname";
	static final String sub="Subject";
	static final String dob="DOB";
	static final String mailid="Email";
	static final String pwd="Pass";
	
	static final String teachtestTable="teachtest";
	static final String teachtestid="testid";
	static final String teachtname="testname";
	
	static final String testTable="test";
	static final String id="testid";
	static final String tname="testname";
	
	static final String resTable="wholeresult";
	static final String resid="resultid";
	static final String stunm="stuname";
	static final String tid="test_id";
	static final String perce="percentage";
	static final String ddate="testdate";
	static final String stas="statuss";
	
	static final String getresTable="wholeresult1";
	static final String getresid="resultid1";
	static final String getstunm="stuname1";
	static final String gettid="test_id1";
	static final String getperce="percentage1";
	static final String getddate="testdate1";
	
	
	
	static final String profileTable="profiles";
	static final String profileid="profile";
	static final String stuname="student";
	static final String coursename="course";
	static final String lessonname="lesson";
	static final String testname="test";
	
	static final String prfrnmTable="professorname";
	static final String proid="professorid";
	static final String proname="professorname";
	
	static final String filluserTable="filluser";
	static final String uid="userid";
	static final String ulogin="uloginname";
	static final String ufname="ufirstname";
	static final String ulname="ulastname";
	
	
	static final String mailfromTable="mailboxfrom";
	static final String mid="mailid";
	static final String rec="recipient";
	static final String send="sender";
	static final String dt="date";
	static final String att="attachment";
	static final String title="title";
	static final String body="body";
	static final String viewed="viewed";
	
	static final String mailtoTable="mailboxto";
	static final String midto="mailidto";
	static final String user_loginto="userloginto";
	static final String recto="recipientto";
	static final String sendto="senderto";
	static final String dtto="dateto";
	static final String attto="attachmentto";
	static final String titleto="titleto";
	static final String bodyto="bodyto";
	static final String statusto="statusto";
	
	
	static final String courseTable="courses";
	static final String cid1="courseid1";
	static final String cid="courseid";
	static final String cname="coursename";
	
	
	
	static final String reasonTable="reasons";
	static final String rid1="reasonid1";
	static final String rid="reasonid";
	static final String reason="reason";
	static final String user_types="user_types";
	
	
	static final String reward_engine_outTable="reward_engin_out";
	static final String id1="id1";
	static final String rec_name="reciever";
	static final String rcourseid="rcourseid";
	static final String rdate="reward_date";
	static final String rpoint="reward_point";
	static final String rreasonid="rreasonid";
	
	
	static final String reward_engine_inTable="reward_engin_in";
	static final String id1_in="id1_in";
	static final String rec_name_in="reciever_in";
	static final String rcourseid_in="rcourseid_in";
	static final String rdate_in="reward_date_in";
	static final String rpoint_in="reward_point_in";
	static final String rreasonid_in="rreasonid_in";
	
	
	
	
	
		
	static final String viewEmps="ViewEmps";
	
		 
	public DataBaseHelper(Context context) 
	{
		super(context, dbName, null,33);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
				
		db.execSQL("CREATE TABLE "+testTable+" ("+id+ " INTEGER PRIMARY KEY, "+tname+ " TEXT)");
		db.execSQL("CREATE TABLE "+courseTable+" ("+cid1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+cid+ " INTEGER, "+cname+ " TEXT)");
		db.execSQL("CREATE TABLE "+reasonTable+" ("+rid1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+rid+ " INTEGER, "+reason+ " TEXT, "+user_types+ " TEXT)");
		db.execSQL("CREATE TABLE "+reward_engine_outTable+" ("+id1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+rec_name+ " TEXT, "+rcourseid+ " INTEGER, "+rdate+ " TEXT, "+rpoint+ " INTEGER, "+rreasonid+ " INTEGER)");
		db.execSQL("CREATE TABLE "+reward_engine_inTable+" ("+id1_in+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+rec_name_in+ " TEXT, "+rcourseid_in+ " INTEGER, "+rdate_in+ " TEXT, "+rpoint_in+ " INTEGER, "+rreasonid_in+ " INTEGER)");
		db.execSQL("CREATE TABLE "+teachtestTable+" ("+teachtestid+ " INTEGER PRIMARY KEY, "+teachtname+ " TEXT)");
		db.execSQL("CREATE TABLE "+eTable+" ("+qid+ " INTEGER, "+quesno+ " INTEGER PRIMARY KEY, "+ques+ " TEXT,"+opt+ " TEXT,"+cans+ " TEXT,"+sta+ " TEXT)");
		
		db.execSQL("CREATE TABLE "+stuTable+" ("+stuid+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+fname+ " TEXT,"+lname+ " TEXT,"+sub+ " TEXT, "+dob+ " TEXT,"+mailid+ " TEXT,"+pwd+ " TEXT)");
		db.execSQL("CREATE TABLE "+rTable+" ("+qid1+ " INTEGER, "+qno+ " INTEGER, "+selopt+ " TEXT,"+ans+ " TEXT,FOREIGN KEY ("+qno+") REFERENCES "+eTable+" ("+quesno+"))");
		db.execSQL("CREATE TABLE "+resTable+" ("+resid+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+stunm+ " TEXT, "+tid+ " INTEGER, "+perce+ " double, "+ddate+ " text, "+stas+ " TEXT)");
		db.execSQL("CREATE TABLE "+getresTable+" ("+getresid+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+getstunm+ " TEXT, "+gettid+ " INTEGER, "+getperce+ " double, "+getddate+ " text)");
		db.execSQL("CREATE TABLE "+profileTable+" ("+profileid+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+stuname+ " TEXT, "+coursename+ " TEXT, "+lessonname+ " TEXT, "+testname+ " TEXT)");
		db.execSQL("CREATE TABLE "+mailfromTable+" ("+mid+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+rec+ " TEXT,"+send+ " TEXT, "+dt+ " TEXT,"+att+ " TEXT, "+title+ " TEXT,"+body+ " TEXT, "+viewed+ " TEXT)");
		db.execSQL("CREATE TABLE "+mailtoTable+" ("+midto+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+user_loginto+ " TEXT, "+recto+ " TEXT,"+sendto+ " TEXT, "+dtto+ " TEXT,"+attto+ " TEXT, "+titleto+ " TEXT,"+bodyto+ " TEXT, "+statusto+ " TEXT)");
		db.execSQL("CREATE TABLE "+prfrnmTable+" ("+proid+ " INTEGER PRIMARY KEY, "+proname+ " TEXT)");
		db.execSQL("CREATE TABLE "+filluserTable+" ("+uid+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+ulogin+ " TEXT, "+ufname+ " TEXT,"+ulname+ " TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		onCreate(db);
	}
	public void createfinalresult()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+resTable+" ("+resid+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+stunm+ " TEXT, "+tid+ " INTEGER, "+perce+ " double, "+ddate+ " text, "+stas+ " TEXT)");
		
	}
	
	public void createcourse()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+courseTable+" ("+cid1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+cid+ " INTEGER, "+cname+ " TEXT)");
		
	}
	public void createreason()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+reasonTable+" ("+rid1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+rid+ " INTEGER, "+reason+ " TEXT, "+user_types+ " TEXT)");
		
	}
	
	public void createreward_engine_in()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+reward_engine_inTable+" ("+id1_in+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+rec_name_in+ " TEXT, "+rcourseid_in+ " INTEGER, "+rdate_in+ " TEXT, "+rpoint_in+ " INTEGER, "+rreasonid_in+ " INTEGER)");
		
	}
	
	public void createreward_engine_out()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+reward_engine_outTable+" ("+id1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+rec_name+ " TEXT, "+rcourseid+ " INTEGER, "+rdate+ " TEXT, "+rpoint+ " INTEGER, "+rreasonid+ " INTEGER)");
		
	}
	
	
	
	
	public void createfilluser()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+filluserTable+" ("+uid+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+ulogin+ " TEXT, "+ufname+ " TEXT,"+ulname+ " TEXT)");
		
	}
	public void createmailboxfrom()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+mailfromTable+" ("+mid+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+rec+ " TEXT,"+send+ " TEXT, "+dt+ " TEXT,"+att+ " TEXT, "+title+ " TEXT,"+body+ " TEXT, "+viewed+ " TEXT)");
		
	}
	public void createmailboxto()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		
		db.execSQL("CREATE TABLE "+mailtoTable+" ("+midto+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+user_loginto+ " TEXT, "+recto+ " TEXT,"+sendto+ " TEXT, "+dtto+ " TEXT,"+attto+ " TEXT, "+titleto+ " TEXT,"+bodyto+ " TEXT, "+statusto+ " TEXT)");
	}
	public void createprofessorname()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+prfrnmTable+" ("+proid+ " INTEGER PRIMARY KEY, "+proname+ " TEXT)");
		
	}
	public void creategetfinalresult()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		
		db.execSQL("CREATE TABLE "+getresTable+" ("+getresid+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+getstunm+ " TEXT, "+gettid+ " INTEGER, "+getperce+ " double, "+getddate+ " text)");
	}
	
	public void createtest()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+testTable+" ("+id+ " INTEGER PRIMARY KEY, "+tname+ " TEXT)");
	}
	public void teachcreatetest()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+teachtestTable+" ("+teachtestid+ " INTEGER PRIMARY KEY, "+teachtname+ " TEXT)");
	}
	public void createstu()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+stuTable+" ("+stuid+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+fname+ " TEXT,"+lname+ " TEXT,"+sub+ " TEXT, "+dob+ " TEXT,"+mailid+ " TEXT,"+pwd+ " TEXT)");
	}
	
	public void createres()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+rTable+" ("+qid1+ " INTEGER, "+qno+ " INTEGER, "+selopt+ " TEXT,"+ans+ " TEXT,FOREIGN KEY ("+qno+") REFERENCES "+eTable+" ("+quesno+"))");
	}
	public void createprofile()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+profileTable+" ("+profileid+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+stuname+ " TEXT, "+coursename+ " TEXT, "+lessonname+ " TEXT, "+testname+ " TEXT)");
	}
	
	
	public void create()
	{
		
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+eTable+" ("+qid+ " INTEGER, "+quesno+ " INTEGER PRIMARY KEY, "+ques+ " TEXT,"+opt+ " TEXT,"+cans+ " TEXT,"+sta+ " TEXT)");
	}
	
	int getQuesCount()
	 {
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cur= db.rawQuery("Select * from "+eTable, null);
		//System.out.println("Data:"+cur);
		int x= cur.getCount();
		cur.close();
		return x;
	 }

	
	Cursor selectfinalresult()
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor cur= db.rawQuery("SELECT * FROM "+resTable,null);
		 return cur;
		 	 
	 }
	Cursor selectprofessorname()
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor cur= db.rawQuery("SELECT * FROM "+prfrnmTable,null);
		 return cur;
		 	 
	 }
	Cursor selectmailboxfrom()
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor cur= db.rawQuery("SELECT * FROM "+mailfromTable,null);
		 return cur;
		 	 
	 }
	Cursor selectmailboxto(String strstuname)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor cur= db.rawQuery("SELECT * FROM "+mailtoTable+ " where "+user_loginto+" = '"+strstuname+"'"+"AND "+statusto+" = "+"'N'" ,null);
		 return cur;																			
		 	 
	 }
	Cursor selectgetfinalresult()
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor cur= db.rawQuery("SELECT * FROM "+getresTable,null);
		 return cur;
		 	 
	 }
	Cursor selectfinalresult1(int id)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor cur= db.rawQuery("SELECT * FROM "+resTable+ " where " +tid+ "="+id ,null);
		 System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"); 
		 return cur;
		 
		 	 
	 }
	Cursor selecttestid(String id1)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor cur= db.rawQuery("SELECT * FROM "+teachtestTable+ " where " +teachtname+ "="+id1 ,null);
		 return cur;
		 	 
	 }
	Cursor selecttest()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 Cursor cur= db.rawQuery("SELECT * FROM "+testTable,null);
		 return cur;
		 	 
	 }
	Cursor selectprofile()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 Cursor cur= db.rawQuery("SELECT * FROM "+profileTable,null);
		 return cur;
		 	 
	 }
	Cursor teachselecttest()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 Cursor cur= db.rawQuery("SELECT * FROM "+teachtestTable,null);
		 return cur;
		 	 
	 }
	
	Cursor getstu()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 Cursor cur= db.rawQuery("SELECT * FROM "+stuTable,null);
		 return cur;
		 	 
	 }
	
	 
	 Cursor getAllQues()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 Cursor cur= db.rawQuery("SELECT * FROM "+eTable,null);
		 return cur;
		 	 
	 }
	 Cursor getTop()
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor cur= db.rawQuery("SELECT * FROM "+eTable+ " where " +sta+ "="+ "'N' limit 0,1" ,null);
		 return cur;
		 	 
	 }
	 
	 Cursor getTopfinalresult()
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor cur= db.rawQuery("SELECT * FROM "+resTable+ " where " +stas+ "="+ "'N'" ,null);
		 return cur;
		 	 
	 }
	 void Insertfinalresult(String  strstunm,int strtestid,double strperce,String strdate,String strstatus)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(stunm,strstunm);
			cv.put(tid,strtestid);
			cv.put(perce,strperce);
			cv.put(ddate,strdate);
			cv.put(stas,strstatus);
			myDB.insert(resTable,null,cv);
			 
			
					
	 }
	 void Insertreward_engine_out(String  recname,int cid,String strdate,int point,int reasonid)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(rec_name,recname);
			cv.put(rcourseid,cid);
			cv.put(rdate,strdate);
			cv.put(rpoint,point);
			cv.put(rreasonid,reasonid);
			myDB.insert(reward_engine_outTable,null,cv);
				
						
	 }
	 void Insertreward_engine_in(String  recname,int cid,String strdate,int point,int reasonid)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(rec_name_in,recname);
			cv.put(rcourseid_in,cid);
			cv.put(rdate_in,strdate);
			cv.put(rpoint_in,point);
			cv.put(rreasonid_in,reasonid);
			myDB.insert(reward_engine_inTable,null,cv);
				
						
	 }
	 
	 void Insertcourse(int id,String course)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(cid,id);
			cv.put(cname,course);
			myDB.insert(courseTable,null,cv);
			
			System.out.println("Courses Inserted Succussfully");
			
			 
			
					
	 }
	 
	 void Insertreason(int id,String reason1,String utype)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(rid,id);
			cv.put(reason,reason1);
			cv.put(user_types,utype);
			
			myDB.insert(reasonTable,null,cv);
			 
			
					
	 }
	 
	 void Insertmailboxfrom(String strrec,String strsend,String strdate,String  stratt,String strtitle,String strbody,String strviewed)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			
			cv.put(rec,strrec);
			cv.put(send,strsend);
			cv.put(dt,strdate);
			cv.put(att,stratt);
			cv.put(title,strtitle);
			cv.put(body,strbody);
			cv.put(viewed,strviewed);
			myDB.insert(mailfromTable,null,cv);
			 
			
					
	 }
	 void Insertmailboxto(String  struname,String strrec,String strsend,String strdate,String  stratt,String strtitle,String strbody,String strstatus)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(user_loginto,struname);
			cv.put(recto,strrec);
			cv.put(sendto,strsend);
			cv.put(dtto,strdate);
			cv.put(attto,stratt);
			cv.put(titleto,strtitle);
			cv.put(bodyto,strbody);
			cv.put(statusto,strstatus);
			myDB.insert(mailtoTable,null,cv);
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"); 
			
					
	 }
	 void Insertgetfinalresult(String  strstunm,int strtestid,double strperce,String strdate)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(getstunm,strstunm);
			cv.put(gettid,strtestid);
			cv.put(getperce,strperce);
			cv.put(getddate,strdate);
			
			myDB.insert(getresTable,null,cv);
			 
			
					
	 }
	
	 void Inserttest(int qid,String qname)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(id, qid);
			cv.put(tname, qname);
			myDB.insert(testTable,null,cv);
			 
			
					
	 }
	 void Insertprofessorname(String strproname)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(proname, strproname);
			myDB.insert(prfrnmTable,null,cv);
			 
			
					
	 }
	 void Insertprofile(String sname,String cname,String lname,String tname)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(stuname, sname);
			cv.put(coursename, cname);
			cv.put(lessonname, lname);
			cv.put(testname, tname);
			myDB.insert(profileTable,null,cv);
			
					
	 }
	 void teachInserttest(int qid,String qname)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(teachtestid, qid);
			cv.put(teachtname, qname);
			myDB.insert(teachtestTable,null,cv);
			 
			
					
	 }
	 void InsertSRS(int quesid,int intquesno,String strques,String stropt,String strans,String strstatus)
	  {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
		    cv.put(qid,quesid);
			cv.put(quesno, intquesno);
			cv.put(ques, strques);
			cv.put(opt, stropt);
			cv.put(cans, strans);
			cv.put(sta, strstatus);
			
			myDB.insert(eTable,null,cv);
			
					
	 }
	 
	 
	 void stuinsert(String txtfname,String txtlname,String txtsub,String txtdob,String txtmail,String txtpwd)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			
			cv.put(fname, txtfname);
			cv.put(lname, txtlname);
			cv.put(sub, txtsub);
			cv.put(dob, txtdob);
			cv.put(mailid, txtmail);
			cv.put(pwd,txtpwd);
			myDB.insert(stuTable,null,cv);
					
	 }
	 void Insertresult(int qids,int intquesno,String opt,String answ)
	 {
		 System.out.println("Insert result called");
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
		    cv.put(qid1,qids);
			cv.put(qno, intquesno);
			cv.put(selopt, opt);
			cv.put(ans, answ);
			myDB.insert(rTable,null,cv);
					
	 }
	 void deletetest()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+testTable);
		 createtest();
		 
	 }
	 void deletecourse()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+courseTable);
		 createcourse();
		 
	 }
	 void deletereason()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+reasonTable);
		 createreason();
		 
	 }
	 void deletereward_engine_out()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+reward_engine_outTable);
		 createreward_engine_out();
		 
	 }
	 void deletereward_engine_in()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+reward_engine_inTable);
		 createreward_engine_in();
		 
	 }
	 void deleteprofessorname()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+prfrnmTable);
		 createprofessorname();
		  
	 }
	 void deletemailboxfrom()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+mailfromTable);
		 createmailboxfrom();
		  
	 }
	 void deletemailboxto()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+mailtoTable);
		 createmailboxto();
		  
	 }
	 void teachdeletetest()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+teachtestTable);
		 teachcreatetest();
		  
	 }
	 void deletedb()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+eTable);
		 create();
		  
	 }
	 void deleteres()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 db.execSQL("DROP TABLE IF EXISTS "+rTable); 
		 createres();
 		 
	 }
	 void deleteprofile()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 db.execSQL("DROP TABLE IF EXISTS "+profileTable); 
		 createprofile();
 		 
	 }
	 void deletegetfinalresult()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 db.execSQL("DROP TABLE IF EXISTS "+getresTable); 
		 creategetfinalresult();
 		 
	 }
	 public Cursor selectdb()
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT * FROM "+eTable, null);
		 return c;                                                 
	 }
	 public Cursor selectres()
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT * FROM "+rTable, null);
		 return c;                                                 
	 }
	 public Cursor selectprofilecourse(String strstu)
	 { 
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT distinct "+coursename+" FROM "+profileTable+" where "+stuname+" = '"+strstu+"'", null);
		 return c;                                                 
	 }
	 
	  public Cursor selectstuinbox(String strstu)
	 { 
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+mid+","+rec+","+send+","+dt+","+att+","+title+","+body+","+viewed+" FROM "+mailfromTable+" where "+rec+" = '"+strstu+"'", null);
		 return c;                                                 
	 }
	  public Cursor selectstusent(String strstu)
		 { 
			
			 SQLiteDatabase db=this.getReadableDatabase();
			 Cursor c=db.rawQuery("SELECT "+mid+","+rec+","+send+","+dt+","+att+","+title+","+body+","+viewed+" FROM "+mailfromTable+" where "+send+" = '"+strstu+"'", null);
			 return c;                                                 
		 }
	 public Cursor selectprofilecourselesson(String strstu)
	 { 
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT distinct "+coursename+","+lessonname+" FROM "+profileTable+" where "+stuname+" = '"+strstu+"'", null);
		 return c;                                                 
	 }
	 public Cursor selectprofilelesson(String strcourse)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT distinct "+lessonname+" FROM "+profileTable+" where "+coursename+" = '"+strcourse+"'", null);
		 return c;                                                 
	 }
	 public Cursor selectprofiletest(String strlesson)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT distinct "+testname+" FROM "+profileTable+" where "+lessonname+" = '"+strlesson+"'", null);
		 return c;                                                 
	 }
	 public Cursor selecttestname(int testid1)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+tname+" FROM "+testTable+ " where "+id+" = "+testid1, null);
		 return c;                                                 
	 }
	 
	 public Cursor selectreasonid(String reason1)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+rid+" FROM "+reasonTable+ " where "+reason+" = '"+reason1+"'", null);
		 return c;                                                 
	 }
	 public Cursor selectdistinctusersforreward()
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT distinct "+rec_name+" FROM "+reward_engine_outTable, null);
		 return c;                                                 
	 }
	 public Cursor selectusersforrewardgiven(String name)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT * FROM "+reward_engine_outTable+ " where "+rec_name+" = '"+name+"'", null);
		 return c;                                                 
	 }
	 public Cursor selectsumpointsforreward(String name)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT sum( "+rpoint+" )FROM "+reward_engine_outTable+ " where "+rec_name+" = '"+name+"'", null);
		 return c;                                                 
	 } 
	 
	 
	 public Cursor selectcourseid(String course)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+cid+" FROM "+courseTable+ " where "+cname+" = '"+course+"'", null);
		 return c;                                                 
	 }
	 
	 public Cursor selectreason(int Rid)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+reason+" FROM "+reasonTable+ " where "+rid+" = "+Rid, null);
		 return c;                                                 
	 }
	 
	 public Cursor selectreasonutype(String utypes)  
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+reason+" FROM "+reasonTable+ " where "+user_types+" = '"+utypes+"'", null);
		 return c;                                                 
	 }
	 
	
	 public Cursor selectcourse(int Cid)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+cname+" FROM "+courseTable+ " where "+cid+" = "+Cid, null);
		 return c;                                                 
	 }
	 public Cursor selectfinalresultdistincttestid()
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT distinct "+gettid+" FROM "+getresTable, null);
		 return c;                                                 
	 }
	 public Cursor selectfinalresultperce(int strtid,String strstuname)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT AVG "+" ("+getperce+" )"+" FROM "+getresTable+" where "+getstunm+" = '"+strstuname+"'"+"AND "+gettid+" = "+strtid, null);
		 return c;                                                 
	 }
	 
	 public Cursor getid(String quizname)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+id+" FROM "+testTable+" where "+tname+" = '"+quizname+"'", null);
		 return c;                                                  
	 }
	 public Cursor gettname(int quizid) 
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+tname+" FROM "+testTable+" where "+id+" ="+quizid, null);
		 return c;                                                 
	 }
	 public Cursor getteachid(String quizname)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+teachtestid+" FROM "+teachtestTable+" where "+teachtname+" = '"+quizname+"'", null);
		 return c;                                                 
	 }
	 public void updatefinalresult(int id)
	 {
		 SQLiteDatabase db= this.getWritableDatabase();
	   	db.execSQL("UPDATE "+resTable+"  SET "+stas+" ='Y' where "+resid+" ="+id);
	 }
	 public void updatequiz(int id)
	 {
		 SQLiteDatabase db= this.getWritableDatabase();
	   	db.execSQL("UPDATE "+eTable+"  SET "+sta+" ='Y' where "+qid+" ="+id);
	 }
	 public void updatemail(int id,String struname)
	 {
		 SQLiteDatabase db= this.getWritableDatabase();
	   	db.execSQL("UPDATE "+mailtoTable+"  SET "+statusto+" ='Y' where "+user_loginto+" = '"+struname+"'"+"AND "+midto+" = "+id);
	 }
	 public Cursor checkans()
	 {
		 SQLiteDatabase db= this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+eTable+"."+cans+","+rTable+"."+selopt+" FROM "+eTable+","+rTable+" where "+eTable+"."+qid+" ="+rTable+"."+qid1, null);
		 return c;                                                 
	 }
	 public Cursor getquesno()
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+quesno+" FROM "+eTable, null);  
		 return c;                                                 
	 }
	    

}


