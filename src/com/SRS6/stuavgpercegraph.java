package com.SRS6;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

public class stuavgpercegraph extends Activity{
String id,uname;
String[] testname;
double[] percentage;
String c="cars ",t="trucks",b="bikes ";
int ic=30,it=60,ib=10;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentreport1); 
        Bundle bundle = getIntent().getExtras(); 
        id=bundle.getString("wel");
    	uname=bundle.getString("welcome");
    	percentage=bundle.getDoubleArray("perce");
    	testname=bundle.getStringArray("testnm");       
    	
	
	}
	
	 
	public Intent execute(Context context) {
		
		 
	int[] colors = new int[] { Color.RED, Color.YELLOW};
	DefaultRenderer renderer = buildCategoryRenderer(colors);
	 
	CategorySeries categorySeries = new CategorySeries("Student Response System");
	for(int i=0;i<testname.length;i++)
	{
	categorySeries.add(testname[i], percentage[i]);
	//categorySeries.add(t, it);
	//categorySeries.add(b, ib);
	}
	 
	return ChartFactory.getPieChartIntent(context, categorySeries, renderer, null);
	
	}
	 
	protected DefaultRenderer buildCategoryRenderer(int[] colors) {
	DefaultRenderer renderer = new DefaultRenderer();
	for (int color : colors) {
	SimpleSeriesRenderer r = new SimpleSeriesRenderer();
	r.setColor(color);
	renderer.addSeriesRenderer(r);
	}
	return renderer;
	}

}
