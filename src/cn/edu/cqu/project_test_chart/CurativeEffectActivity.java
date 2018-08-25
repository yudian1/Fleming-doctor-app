package cn.edu.cqu.project_test_chart;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.project_doctor.R;
import com.example.project_doctor_dao.SleepDiaryDAO;
import com.example.project_doctor_model.SleepDiary;

public class CurativeEffectActivity extends Activity {
	
	private int[] atBed = new int[7];
	private int[] sleepTime = new int[7];
	private int[] SE = new int[7];
	private int flag = 0;
	private String patient_phone;
	private SleepDiary sleepDiary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.curative_effect_layout);
        
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 0);
        patient_phone = intent.getStringExtra("patientPhone");
        
        SleepDiaryDAO sleepDao = new SleepDiaryDAO(this);
    	sleepDiary = sleepDao.find(patient_phone);
//    	System.out.println(sleepDiary.getClass());
    	if (sleepDiary.getDay01() != null && sleepDiary.getDay02() != null && sleepDiary.getDay03() != null && 
    			sleepDiary.getDay04() != null && sleepDiary.getDay05() != null && sleepDiary.getDay06() != null && 
    			sleepDiary.getDay07() != null) {
    		//获取数据
            setdata();
        	for (int i = 0; i < SE.length; i++) {
        		SE[i] = 100 * sleepTime[i] / atBed[i];
        	}
            if (flag == 0) {
            	showChar("在床时间",atBed);
    		}else if (flag == 1) {
    			showChar("睡眠时间",sleepTime);
    		}else if (flag == 2) {
    			showChar("睡眠效率",SE);
    		}
    	}else {
    		Toast.makeText(this, "还没有睡眠数据！", Toast.LENGTH_SHORT).show();
    	}
        
    }

	public void showChar(String title,int data[]) {
		//1、构造显示的渲染图
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        //设置标题和标题字体大小
        renderer.setChartTitleTextSize(30);
        renderer.setChartTitle("睡眠日记数据");
        //XY轴标尺的字体大小
        renderer.setLabelsTextSize(20);
        renderer.setLegendTextSize(20);
        
        renderer.setYTitle("分钟");
        renderer.setXTitle("天");
        //显示网格
        renderer.setShowGrid(true);
        //设置折线内的背景
        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.BLACK);
        //外部背景修改
        renderer.setMarginsColor(Color.DKGRAY);
        //2、进行显示
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        
        for (int i = 0; i < data.length; i++) {
   			System.out.println("data---" + data[i]);
   		}
           XYSeries series = new XYSeries(title);
         //填充数据
   		for (int j = 0; j < data.length; j++) {
   			//填充XY的值
   			series.add((j+1), data[j]);
   		}
   		//需要绘制的数据放入dataset中
   		dataset.addSeries(series);
        // 3, 对点的绘制进行设置
        XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
        // 3.1设置颜色
        xyRenderer.setColor(Color.RED);
        // 3.2设置点的样式
        xyRenderer.setPointStyle(PointStyle.SQUARE);
        // 3.3, 将要绘制的点添加到坐标绘制中
        renderer.addSeriesRenderer(xyRenderer);
        // 3.4,重复 1~3的步骤绘制第二个系列点
  
        LinearLayout barchart = (LinearLayout) findViewById(R.id.barchart);
        //折线图
        GraphicalView mChartView = ChartFactory.getLineChartView(this, dataset, renderer);
        mChartView.repaint();// 刷新折线图
		barchart.addView(mChartView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
	}
    
private void setdata(){
	String day01 = sleepDiary.getDay01();
	String day02 = sleepDiary.getDay02();
	String day03 = sleepDiary.getDay03();
	String day04 = sleepDiary.getDay04();
	String day05 = sleepDiary.getDay05();
	String day06 = sleepDiary.getDay06();
	String day07 = sleepDiary.getDay07();
	System.out.println(day01 + "---" + day02 + "---" + day03 + "---" + day04 + "---" + day05 + "---" + day06 + "---" + day07);
	//所有的在床时间
	atBed[0] = 24*60 - (goBedTime(day01) - getUpTime(day01));
	atBed[1] = 24*60 - (goBedTime(day02) - getUpTime(day02));
	atBed[2] = 24*60 - (goBedTime(day03) - getUpTime(day03));
	atBed[3] = 24*60 - (goBedTime(day04) - getUpTime(day04));
	atBed[4] = 24*60 - (goBedTime(day05) - getUpTime(day05));
	atBed[5] = 24*60 - (goBedTime(day06) - getUpTime(day06));
	atBed[6] = 24*60 - (goBedTime(day07) - getUpTime(day07));
	//所有的睡眠时间
	sleepTime[0] = 24*60 + (wakeTime(day01) - SleepTime(day01));
	sleepTime[1] = 24*60 + (wakeTime(day02) - SleepTime(day02));
	sleepTime[2] = 24*60 + (wakeTime(day03) - SleepTime(day03));
	sleepTime[3] = 24*60 + (wakeTime(day04) - SleepTime(day04));
	sleepTime[4] = 24*60 + (wakeTime(day05) - SleepTime(day05));
	sleepTime[5] = 24*60 + (wakeTime(day06) - SleepTime(day06));
	sleepTime[6] = 24*60 + (wakeTime(day07) - SleepTime(day07));
	
	for (int i = 0; i < sleepTime.length; i++) {
		if (sleepTime[i] > 1440) {
			sleepTime[i] = sleepTime[i] - 1440;
		}
	}
//	for (int i = 0; i < SE.length; i++) {
//		SE[i] = sleepTime[i] / atBed[i] * 100;
//	}
	
//	SE[0] = 100 *sleepTime[0] / sleepTime[0];
}
//截取上床时间
private int goBedTime(String day){
	String gobentime = day.substring(4, 9);
	int hour = Integer.parseInt(gobentime.substring(0, 2));
	int minute = Integer.parseInt(gobentime.substring(3, 5));
	int time = hour * 60 + minute;
	return time;
}
//截取起床时间
private int getUpTime(String day){
	String getUptime = day.substring(20, 25);
	int hour = Integer.parseInt(getUptime.substring(0, 2));
	int minute = Integer.parseInt(getUptime.substring(3, 5));
	int time = hour * 60 + minute;
	return time;
}
//截取睡着时间
private int SleepTime(String day){
	String sleeptime = day.substring(9, 14);
	//String转化成int， String.valueof();转化成String类型的
	int hour = Integer.parseInt(sleeptime.substring(0, 2));
	int minute = Integer.parseInt(sleeptime.substring(3, 5));
	int time = hour * 60 + minute;
	return time;
}
//截取觉醒时间
private int wakeTime(String day){
	String waketime = day.substring(15, 20);
	int hour = Integer.parseInt(waketime.substring(0, 2));
	int minute = Integer.parseInt(waketime.substring(3, 5));
	int time = hour * 60 + minute;
	return time;
}
}
