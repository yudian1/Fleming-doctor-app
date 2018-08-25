package com.example.project_doctor_activity;

import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.project_doctor.R;
import com.example.project_doctor_dao.SleepDiaryDAO;
import com.example.project_doctor_model.SleepDiary;

public class PatientInfoSleepDiary extends Activity{
	private String patient_phone;
	private String day01;
	private String day02;
	private String day03;
	private String day04;
	private String day05;
	private String day06;
	private String day07;

	private ListView lvady;
	private ListView lvday01;
	private ListView lvday02;
	private ListView lvday03;
	private ListView lvday04;
	private ListView lvday05;
	private ListView lvday06;
	private ListView lvday07;

	
	//private String str;
	//String day[] = new String [7];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_sleepdiary);
		
		lvady = (ListView) findViewById(R.id.listview);
		lvday01 = (ListView) findViewById(R.id.listview01);
		lvday02 = (ListView) findViewById(R.id.listview02);
		lvday03 = (ListView) findViewById(R.id.listview03);
		lvday04 = (ListView) findViewById(R.id.listview04);
		lvday05 = (ListView) findViewById(R.id.listview05);
		lvday06 = (ListView) findViewById(R.id.listview06);
		lvday07 = (ListView) findViewById(R.id.listview07);
		
		//��ô��ݹ�����id
		Intent intent = getIntent();
		patient_phone = intent.getStringExtra("patient_phone");
		System.out.println("PatientInfoSleepDiary---" + patient_phone);
		SleepDiaryDAO sddao = new SleepDiaryDAO(PatientInfoSleepDiary.this);
		SleepDiary sleepdiaty = sddao.find(patient_phone);
		day01 = sleepdiaty.getDay01();
		day02 = sleepdiaty.getDay02();
		day03 = sleepdiaty.getDay03();
		day04 = sleepdiaty.getDay04();
		day05 = sleepdiaty.getDay05();
		day06 = sleepdiaty.getDay06();
		day07 = sleepdiaty.getDay07();
		System.out.println(day01 + "---" +day02 + "---" +day02 + "---" +day04 + "---" +day05 + "---" +day06 + "---" +day07);
		ShowInfo();// ��ʾ˯����������
		chart();//��ʾ����ͼ
	}
	private void ShowInfo() {// �������ݴ���Ĺ������ͣ���ʾ��Ӧ����Ϣ
		
		String[] question = new String[]{"��������:","�������:","����ʱ��:","������ҩ:","�ϴ�ʱ��:","˯��ʱ��:","��������:","WASO:","��ʱ��:","����ʱ��"};
		ArrayAdapter<String> arrayAdapter = null;// ����ArrayAdapter����
		arrayAdapter = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,question);
		lvady.setAdapter(arrayAdapter);
		
			if(day01.toString().equals("anyType{}")){
			}
			else{
				System.out.println("��ʼ��������");
				String strday1[] = data(day01);
				ArrayAdapter<String> arrayAdapter1 = null;// ����ArrayAdapter����
				arrayAdapter1 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strday1);
				lvday01.setAdapter(arrayAdapter1);
			}
			if(day02.toString().equals("anyType{}")){
			}
			else{
				String strday2[] = data(day02);
				ArrayAdapter<String> arrayAdapter2 = null;// ����ArrayAdapter����
				arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strday2);
				lvday02.setAdapter(arrayAdapter2);
			}
			if(day03.toString().equals("anyType{}")){
			}
			else{
				String strday3[] = data(day03);
				ArrayAdapter<String> arrayAdapter3 = null;// ����ArrayAdapter����
				arrayAdapter3 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strday3);
				lvday03.setAdapter(arrayAdapter3);
			}
			if(day04.toString().equals("anyType{}")){
			}
			else{
				String strday4[] = data(day04);
				ArrayAdapter<String> arrayAdapter4 = null;// ����ArrayAdapter����
				arrayAdapter4 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strday4);
				lvday04.setAdapter(arrayAdapter4);
			}
			if(day05.toString().equals("anyType{}")){
			}
			else{
				String strday5[] = data(day05);
				ArrayAdapter<String> arrayAdapter5 = null;// ����ArrayAdapter����
				arrayAdapter5 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strday5);
				lvday05.setAdapter(arrayAdapter5);
			}
			if(day06.toString().equals("anyType{}")){
			}
			else{
				String strday6[] = data(day06);
				ArrayAdapter<String> arrayAdapter6 = null;// ����ArrayAdapter����
				arrayAdapter6 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strday6);
				lvday06.setAdapter(arrayAdapter6);
			}
			if(day07.toString().equals("anyType{}")){
			}
			else{
				String strday7[] = data(day07);
				ArrayAdapter<String> arrayAdapter7 = null;// ����ArrayAdapter����
				arrayAdapter7 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strday7);
				lvday07.setAdapter(arrayAdapter7);
			}
			
			
		}
	private String[] data(String str){
		String strday[] = new String[10];
		for(int i = 0; i < 4;i++){
			strday[i] = str.substring(i, i+1);
		}
		System.out.println("str-----" + str);
		strday[4] = str.substring(4, 9);
		strday[5] = str.substring(9, 14);
		strday[6] = str.substring(14, 15);
		strday[7] = str.substring(15, 20);
		strday[8] = str.substring(20, 25);
		strday[9] = str.substring(25, 30);
		if(strday[0].equals("0")){
			strday[0] = "��";
		}
		else if(strday[0].equals("1")){
			strday[0] = "���";
		}
		else if(strday[0].equals("2")){
			strday[0] = "�ж�";
		}
		else if(strday[0].equals("3")){
			strday[0] = "�ض�";
		}
		else if(strday[0].equals("4")){
			strday[0] = "���ض�";
		}
		
		for(int i = 1; i < 3; i++){
			if(strday[i].equals("0")){
				strday[i] = "С��10����";
			}
			else if(strday[i].equals("1")){
				strday[i] = "10-30����";
			}
			else if(strday[i].equals("2")){
				strday[i] = "30-60����";
			}
			else if(strday[i].equals("3")){
				strday[i] = "����60����";
			}
		}
		if(strday[3].equals("0")){
			strday[3] = "��";
		}
		else if(strday[3].equals("1")){
			strday[3] = "��";
		}
		
		if(strday[6].equals("0")){
			strday[6] = "0��";
		}
		else if(strday[6].equals("1")){
			strday[6] = "1��";
		}
		if(strday[6].equals("0")){
			strday[6] = "2��";
		}
		else if(strday[6].equals("1")){
			strday[6] = "3��";
		}
		else if(strday[6].equals("2")){
			strday[6] = "4������";
		}
		return strday;
	}		
	
	public void chart(){
        //1��������ʾ����Ⱦͼ
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setChartTitleTextSize(15);
        renderer.setChartTitle("˯���ռ�����");
        //���������ڵı���
        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.GRAY);
        //�ⲿ�����޸�
        renderer.setMarginsColor(Color.DKGRAY);
//        //��������
//        renderer.setZoomEnabled(true);
//        renderer.setZoomRate(5);
        //2��������ʾ
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        //2.1 ��������
        Random r = new Random();
        //��ʾ��������
        for (int i = 0; i < 2; i++) {
        	
			XYSeries series = new XYSeries("����" + (i + 1));
			//�������
			for (int j = 0; j < 10; j++) {
				//���XY��ֵ
				series.add(j, 20 + r.nextInt() % 10);
			}
			//��Ҫ���Ƶ����ݷ���dataset��
			dataset.addSeries(series);
		}
        // 3, �Ե�Ļ��ƽ�������
        XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
        // 3.1������ɫ
        xyRenderer.setColor(Color.BLUE);
        // 3.2���õ����ʽ
        xyRenderer.setPointStyle(PointStyle.SQUARE);
        // 3.3, ��Ҫ���Ƶĵ���ӵ����������
        renderer.addSeriesRenderer(xyRenderer);
        // 3.4,�ظ� 1~3�Ĳ�����Ƶڶ���ϵ�е�
        xyRenderer = new XYSeriesRenderer();
        xyRenderer.setColor(Color.RED);
        xyRenderer.setPointStyle(PointStyle.CIRCLE);
        renderer.addSeriesRenderer(xyRenderer);
  
        // Intent intent = new LinChart().execute(this);
//        Intent intent = ChartFactory.getLineChartIntent(this, dataset, renderer);
//        startActivity(intent);
        LinearLayout barchart = (LinearLayout) findViewById(R.id.barchart);
        //��״ͼ
//		GraphicalView mChartView = ChartFactory.getBarChartView(this, dataset,
//				renderer, Type.DEFAULT);
        //����ͼ
        GraphicalView mChartView = ChartFactory.getLineChartView(this, dataset, renderer);

		barchart.addView(mChartView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
    
	}
}
