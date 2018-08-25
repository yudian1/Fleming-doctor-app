package com.example.project_doctor_activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.Toast;
import cn.edu.cqu.project_test_chart.SelectChartActivity;

import com.example.project_doctor.R;
import com.example.project_doctor_dao.DoctorAdviceDAO;
import com.example.project_doctor_dao.PatientInformationDAO;
import com.example.project_doctor_dao.PatientTestDAO;
import com.example.project_doctor_dao.SleepDiaryDAO;
import com.example.project_doctor_model.DoctorAdivce;
import com.example.project_doctor_model.PatientInformation;
import com.example.project_doctor_model.PatientTest;
import com.example.project_doctor_model.SleepDiary;

public class MainActivity extends Activity implements OnClickListener{
	
	private ListView listView,patientInfoList;
	private Button updateButton;
	private String data;

	private String wsdlURL,webMethod,namespace,soapAction;
	private SoapObject soapObject;
	private SoapSerializationEnvelope  envelope;
	private HttpTransportSE se ;
	private PatientInformationDAO pi;
	private PatientTestDAO pt;
	private SleepDiaryDAO sddao;
	private List<HashMap<String, Object>> listdata;
	
	private Button patientInfo;
	private Button patientPSQI;
	private Button patientsleepness;
	private Button patientHama;
	private Button patientHamd;
	private Button patientSleepDiary;
	private Button doctorAdivce,hintTable,chartButton;
	private TableLayout tableLayout;
	private LinearLayout otherAdvice;
	//����Ϊȫ�ֱ���,��patientphone����Ϊ����
	private String  patient_phone;
	
	private String pqsi;
	private String sleepness;
	private String hama;
	private String hamd;
	private  int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor);
        
        patientInfo = (Button) findViewById(R.id.patientInfo);
        patientPSQI = (Button) findViewById(R.id.patientPSQI);
        patientsleepness = (Button) findViewById(R.id.sleepness);
        patientHama = (Button) findViewById(R.id.hamaButton);
        patientHamd = (Button) findViewById(R.id.hamdButton);
        patientSleepDiary = (Button) findViewById(R.id.sleepDiary);
        
        doctorAdivce = (Button) findViewById(R.id.send_advice);
        doctorAdivce.setOnClickListener(this);
        
        hintTable = (Button) findViewById(R.id.hint_table);
        hintTable.setOnClickListener(this);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        otherAdvice = (LinearLayout) findViewById(R.id.other_advice);
        chartButton = (Button) findViewById(R.id.chart_button);
        chartButton.setOnClickListener(this);
        
        updateButton = (Button) findViewById(R.id.updataInfo);
        listView = (ListView)findViewById(R.id.listView);
        patientInfoList = (ListView) findViewById(R.id.patientInfoList);
        //���»�����Ϣ
        updateButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (isNetworkAvailable(MainActivity.this) == true )
				{
					UpdatePatientInfoThread ut = new UpdatePatientInfoThread();
					ut.start();
					//ˢ���б�
//					PatientList();
				}
				else{
					new AlertDialog.Builder(MainActivity.this)
					.setTitle("��ܰ��ʾ")
					.setMessage("���������磡")
					.setPositiveButton("ȷ��", null)
					.show();
					return;
				}
				//PatientList();
			}
		});
        //��û����б�
        PatientList();
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				//���ѡ�е�item����Ϣ
				Map<String,Object> getId = new HashMap<String,Object>();
                getId = listdata.get(position);
                
                System.out.println("id------" + getId);
                Object[] str = getId.values().toArray();
                //���ѡ�е�����
                patient_phone = str[3].toString();
                
                //�ڵ�һ��LIstView����ʾ���ߵ���Ϣ
                showPatientInfo(patient_phone);
                
                System.out.println("patientphone------" + patient_phone);
//                //���ݵ�PatientInfoManageȥ
//				Intent intent = new Intent(DataManagementeActivity.this,PatientInfoManage.class);// ����Intent����
//				intent.putExtra("patientid", patientid);
//				startActivity(intent);// ִ��Intent����
                
                PatientTestDAO ptdao = new PatientTestDAO(MainActivity.this);
        		PatientTest patienttest = ptdao.find(patient_phone);
        		if (patienttest.getPqsi() != null) {
        			pqsi = patienttest.getPqsi();
        			System.out.println("pqsi---" + pqsi);
				}else {
					pqsi = null;
				}
        		if (patienttest.getSleepness() != null) {
        			sleepness = patienttest.getSleepness();
        			System.out.println("pqsi---" + sleepness);
				}else {
					sleepness = null;
				}
        		if (patienttest.getHama() != null) {
        			hama = patienttest.getHama();
        			System.out.println("pqsi---" + hama.toString());
				}else {
					hama = null;
				}
        		if (patienttest.getHamd() != null) {
        			hamd = patienttest.getHamd();
        			System.out.println("pqsi---" + hamd.toString());
				}else {
					hamd = null;
				}
//        		pqsi = patienttest.getPqsi();
//        		sleepness = patienttest.getSleepness();
//        		hama = patienttest.getHama();
//        		hamd = patienttest.getHamd();
        		System.out.println("test3-------");
        		System.out.println("�õ��Ľ��----" + pqsi + "----" + sleepness + "-----" + hama + "-----" + hamd);
			}
		});
        patientInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(patient_phone == null){
					new AlertDialog.Builder(MainActivity.this)
					.setTitle("��ܰ��ʾ")
					.setMessage("��ѡ����!")
					.setPositiveButton("ȷ��", null)
					.show();
					return;
				}
				else{
					Intent intent = new Intent(MainActivity.this,PatientInfoActivity.class);// ����Intent����
					intent.putExtra("patientphone", patient_phone);
					startActivity(intent);// ִ��Intent����
				}
			}
		});
        
        patientPSQI.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(patient_phone == null){
					new AlertDialog.Builder(MainActivity.this)
					.setTitle("��ܰ��ʾ")
					.setMessage("��ѡ����!")
					.setPositiveButton("ȷ��", null)
					.show();
					return;
				}
				else{
					if (!pqsi.toString().equals("anyType{}")) {
						Intent intent = new Intent(MainActivity.this,PQSIActivity.class);// ����Intent����
						intent.putExtra("pqsi", pqsi);
						startActivity(intent);// ִ��Intent����
					}
					else {
//						Toast.makeText(MainActivity.this, "����û���������", Toast.LENGTH_LONG).show();
						new AlertDialog.Builder(MainActivity.this)
						.setTitle("��ܰ��ʾ")
						.setMessage("����û��������ԣ�")
						.setPositiveButton("ȷ��", null)
						.show();
						return;
					}
				}
			}
		});
        patientsleepness.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(patient_phone == null){
					new AlertDialog.Builder(MainActivity.this)
					.setTitle("��ܰ��ʾ")
					.setMessage("��ѡ����!")
					.setPositiveButton("ȷ��", null)
					.show();
					return;
				}
				else{
					if (!sleepness.toString().equals("anyType{}")) {
						Intent intent = new Intent(MainActivity.this,SleepnessActivity.class);// ����Intent����
						intent.putExtra("sleepness", sleepness);
						startActivity(intent);// ִ��Intent����
					}
					else {
//						Toast.makeText(MainActivity.this, "����û���������", Toast.LENGTH_LONG).show();
						new AlertDialog.Builder(MainActivity.this)
						.setTitle("��ܰ��ʾ")
						.setMessage("����û��������ԣ�")
						.setPositiveButton("ȷ��", null)
						.show();
						return;
					}
				}
			}
		});
        patientHama.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(patient_phone == null){
					new AlertDialog.Builder(MainActivity.this)
					.setTitle("��ܰ��ʾ")
					.setMessage("��ѡ����!")
					.setPositiveButton("ȷ��", null)
					.show();
					return;
				}
				else{
					if (!hama.toString().equals("anyType{}")) {
						Intent intent = new Intent(MainActivity.this,HAMAActivity.class);// ����Intent����
						intent.putExtra("hama", hama);
						startActivity(intent);// ִ��Intent����
					}
					else {
//						Toast.makeText(MainActivity.this, "����û���������", Toast.LENGTH_LONG).show();
						new AlertDialog.Builder(MainActivity.this)
						.setTitle("��ܰ��ʾ")
						.setMessage("����û��������ԣ�")
						.setPositiveButton("ȷ��", null)
						.show();
						return;
					}
				}
			}
		});
        patientHamd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(patient_phone == null){
					new AlertDialog.Builder(MainActivity.this)
					.setTitle("��ܰ��ʾ")
					.setMessage("��ѡ����!")
					.setPositiveButton("ȷ��", null)
					.show();
					return;
				}
				else{
					if (!hamd.toString().equals("anyType{}")) {
						Intent intent = new Intent(MainActivity.this,HAMDActivity.class);// ����Intent����
						intent.putExtra("hamd", hamd);
						startActivity(intent);// ִ��Intent����
					}
					else {
						//Toast.makeText(MainActivity.this, "����û���������", Toast.LENGTH_LONG).show();
						new AlertDialog.Builder(MainActivity.this)
						.setTitle("��ܰ��ʾ")
						.setMessage("����û��������ԣ�")
						.setPositiveButton("ȷ��", null)
						.show();
						return;
					}
//					Intent intent = new Intent(MainActivity.this,HAMDActivity.class);// ����Intent����
//					intent.putExtra("hamd", hamd);
//					startActivity(intent);// ִ��Intent����
				}
			}
		});
        patientSleepDiary.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(patient_phone == null){
					new AlertDialog.Builder(MainActivity.this)
					.setTitle("��ܰ��ʾ")
					.setMessage("��ѡ����!")
					.setPositiveButton("ȷ��", null)
					.show();
					return;
				}
				else{
					Intent intent = new Intent(MainActivity.this,PatientInfoSleepDiary.class);// ����Intent����
					intent.putExtra("patient_phone", patient_phone);
					System.out.println("patient_phone11111111111111111111---" + patient_phone);
					startActivity(intent);// ִ��Intent����
				}
			}
		});
    }
    private void showPatientInfo(String patient_phone){
    	 String [] question= new String[]{"������","�Ա�","���䣺","��ϵ�绰��"};  
    	 String [] answer = new String [4];
    	 PatientInformationDAO pinfo = new PatientInformationDAO(this);
    	 PatientInformation patinet = pinfo.find(patient_phone);
    	 answer [0] = patinet.getPatient_name();
    	 answer [1] = patinet.getPatient_gender();
    	 answer [2] = patinet.getPatient_age();
    	 answer [3] = patinet.getPatient_phone();
    	 
    	 List<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;
         for(int i =0; i < answer.length; i++) {    
         	Map<String,Object> item = new HashMap<String,Object>();    
         	item.put("question", question[i]);    
         	item.put("answer", answer[i]);    
         	mData.add(item);     
         }    
         SimpleAdapter adapter = new SimpleAdapter(this,mData,R.layout.item_info,new String[]{"question","answer"},new int[]{R.id.question,R.id.answer});   
         patientInfoList.setAdapter(adapter);

    }

	private void PatientList() {
		String patientInfos[] = null;
		pi = new PatientInformationDAO(MainActivity.this);
		List<PatientInformation> listpatients = pi.getScrollData(0, (int)pi.getCount());
		System.out.println("�����С"+listpatients.size());
        patientInfos = new String[listpatients.size()];
        listdata = new ArrayList<HashMap<String,Object>>(); 
        int i = 0;// ����һ����ʼ��ʶ
		for (PatientInformation patient : listpatients) {// ����List���ͼ���
			// ��֧�������Ϣ��ϳ�һ���ַ������洢���ַ����������Ӧλ��
			patientInfos[i] = patient.getCurrent_date() + "   " + patient.getPatient_name()
					+ "  " + patient.getPatient_gender()+ "   " + patient.getPatient_age();
			i++;// ��ʶ��1
			HashMap<String, Object> item = new HashMap<String, Object>(); 
			item.put("id", patient.getId());
			item.put("currentdate", patient.getCurrent_date());
			item.put("name", patient.getPatient_name());
			item.put("gender", patient.getPatient_gender());
			item.put("age", patient.getPatient_age());
			item.put("patientphone", patient.getPatient_phone());
			listdata.add(item);
		}
		// ʹ���ַ��������ʼ��ArrayAdapter����
//		arrayAdapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, patientInfos);
		SimpleAdapter adapter = new SimpleAdapter(this,listdata,R.layout.doctor_item,new String[]{"currentdate","name","gender","age"},
				new int[]{R.id.date,R.id.name,R.id.gender,R.id.age});
		listView.setAdapter(adapter);// ΪListView�б���������Դ
	}
    
    class UpdatePatientInfoThread extends Thread{
		@Override
		public void run() {
			Looper.prepare();
			
			
			//����1�� ����Webservice �ĵ��ò������뾲̬��IP��ַlocalhost���ϣ����뾲̬IP��ַ
			 wsdlURL= "http://m167x89974.iask.in:52327/CloudServer/services/UserService";
			 webMethod = "get";
			 namespace = "http://service.cloud.chinasoft.com";
			 soapAction = namespace + webMethod;
			
			//����2:����һ������SoapObject
		    soapObject = new SoapObject(namespace, webMethod);
			//����3�����ݲ���
			
			//����4������һ��SoapSer
			SoapSerializationEnvelope  envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

			//����5:�ŷ�����һ�´��ض���
			envelope.bodyOut = soapObject;
		
			//����6�������ʵ�Ա
			 se = new HttpTransportSE(wsdlURL);
			//����7��������
			try {
				se.call(soapAction, envelope);
				//����8����ȡ�ӻ��������صĽ��
				if(envelope.getResponse() != null)
				{
					Object resout = envelope.getResponse();
					System.out.println("result----" + resout.toString());
					System.out.println(resout instanceof Vector);
					//��������resoult
					@SuppressWarnings("unchecked")
					Vector<SoapObject> vec = (Vector<SoapObject>) resout;
					System.out.println("vec--" + vec);
					//ѭ����ʾ
					StringBuffer buffer = new StringBuffer();
					for(SoapObject soapObject : vec)
					{
						buffer.append(soapObject.getProperty("current_date")+"_");
						buffer.append(soapObject.getProperty("patient_name")+"_");
						buffer.append(soapObject.getProperty("patient_gender")+"_");
						buffer.append(soapObject.getProperty("patient_age")+"_");
						buffer.append(soapObject.getProperty("patient_postion")+"_");
						buffer.append(soapObject.getProperty("patient_scde")+"_");
						buffer.append(soapObject.getProperty("patient_insomnia")+"_");
						buffer.append(soapObject.getProperty("patient_mhistory")+"_");
						buffer.append(soapObject.getProperty("patient_phone")+"#");
					}
					
					buffer.deleteCharAt(buffer.length()-1);
					data = new String(buffer);
					jiexiPatientInfo(data);
					
					//���ԣ���ʾ���
					Toast.makeText(MainActivity.this, "���سɹ�", Toast.LENGTH_LONG).show();
					System.out.println("���----" + data);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			//��ȡ��������
			UpdatePatientTest();
			UpdatePatientSleepDiary();
			
			Looper.loop();}
	}
    
    public void jiexiPatientInfo(String str){
		String patient[] = null;
		String patientInfo[] = null;
		//���ա�#���ָ�
		patient = str.split("#");
		pi = new PatientInformationDAO(MainActivity.this);
		//��ձ��
		pi.deletetable();
		for(int i = 0;i < patient.length;i++){
			//��ò�����Ϣ
			System.out.println("patient----" + patient[i]);
			patientInfo = patient[i].split("_");
			//for(int j = 0; j < patientInfo.length;j++){
				//��װ
				PatientInformation patientInfos = new PatientInformation(patientInfo[0],patientInfo[1], patientInfo[2],
						patientInfo[3], patientInfo[4], patientInfo[5], patientInfo[6],patientInfo[7],patientInfo[8]);
				//Log.v("�������������", "���");
				
				//��������
				pi.insert(patientInfos);
//				
//				//���뻼�߲�������
//				PatientTest patientTest = new PatientTest();
//				System.out.println("�������߲��Ա��");
//				PatientTestDAO ptdao = new PatientTestDAO(SleepHealthyActivity.this);
//				ptdao.insert(patientTest);
//				
//				//���뻼��˯���ռ�����
//				SleepDiary sleepDiary = new SleepDiary();
//				System.out.println("����˯���ռǱ��");
//				SleepDiaryDAO sddao = new SleepDiaryDAO(SleepHealthyActivity.this);
//				sddao.insert(sleepDiary);
				//System.out.println("patientInfo----" + patientInfo[j]);
			
		}
		
	} 
    

		private void UpdatePatientTest() {
			//����1�� ����Webservice �ĵ��ò������뾲̬��IP��ַlocalhost���ϣ����뾲̬IP��ַ
			 wsdlURL= "http://m167x89974.iask.in:52327/CloudServer/services/UserService";
			 webMethod = "getgetAllTest_Table";
			 namespace = "http://service.cloud.chinasoft.com";
			 soapAction = namespace + webMethod;
			
			//����2:����һ������SoapObject
			 
		    soapObject = new SoapObject(namespace, webMethod);
			//����3�����ݲ���
			
			//����4������һ��SoapSer
			SoapSerializationEnvelope  envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

			//����5:�ŷ�����һ�´��ض���
			envelope.bodyOut = soapObject;
		
			//����6�������ʵ�Ա
			 se = new HttpTransportSE(wsdlURL);
			//����7��������
			try {
				se.call(soapAction, envelope);
				//����8����ȡ�ӻ��������صĽ��
				if(envelope.getResponse() != null)
				{
					Object resout = envelope.getResponse();
					System.out.println(resout.getClass());
					System.out.println(resout instanceof Vector);
					//��������resoult
					@SuppressWarnings("unchecked")
					Vector<SoapObject> vec = (Vector<SoapObject>) resout;
					
					//ѭ����ʾ
					StringBuffer buffer = new StringBuffer();
					for(SoapObject soapObject : vec)
					{
						buffer.append(soapObject.getProperty("patient_phone")+"_");
						buffer.append(soapObject.getProperty("pqsi")+"_");
						buffer.append(soapObject.getProperty("sleepness")+"_");
						buffer.append(soapObject.getProperty("hama")+"_");
						buffer.append(soapObject.getProperty("hamd")+"#");
					}
					
					buffer.deleteCharAt(buffer.length()-1);
					String patientTestInfo = new String(buffer);
					patientTestInfo(patientTestInfo);
					
					//���ԣ���ʾ���
					//Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
					System.out.println("���----" + data);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
		}
	public void patientTestInfo(String str){
		String patientTest[] = null;
		String patientTestInfo[] = null;
		//���ա�#���ָ�
		patientTest = str.split("#");
		pt = new PatientTestDAO(MainActivity.this);
		//��ձ��
		pt.deletetable();
		for(int i = 0;i < patientTest.length;i++){
			//��ò�����Ϣ
			System.out.println("patient----" + patientTest[i]);
			patientTestInfo = patientTest[i].split("_");
			for(int j = 0; j < patientTestInfo.length;j++){
				System.out.println("patientTestInfo---" + patientTestInfo[j]);
			}
				//��װ
				PatientTest patientInfos = new PatientTest(patientTestInfo[1], patientTestInfo[2],
						patientTestInfo[3],patientTestInfo[4],patientTestInfo[0]);
				//Log.v("�������������", "���");
				
				//��������
				pt.insert(patientInfos);
	}	
	}
	
	private void UpdatePatientSleepDiary() {
		//����1�� ����Webservice �ĵ��ò������뾲̬��IP��ַlocalhost���ϣ����뾲̬IP��ַ
		 wsdlURL= "http://m167x89974.iask.in:52327/CloudServer/services/UserService";
		 webMethod = "getgetSleepdiary_Day";
		 namespace = "http://service.cloud.chinasoft.com";
		 soapAction = namespace + webMethod;
		
		//����2:����һ������SoapObject
		 
	    soapObject = new SoapObject(namespace, webMethod);
		//����3�����ݲ���
		
		//����4������һ��SoapSer
		SoapSerializationEnvelope  envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

		//����5:�ŷ�����һ�´��ض���
		envelope.bodyOut = soapObject;
	
		//����6�������ʵ�Ա
		 se = new HttpTransportSE(wsdlURL);
		//����7��������
		try {
			se.call(soapAction, envelope);
			//����8����ȡ�ӻ��������صĽ��
			if(envelope.getResponse() != null)
			{
				Object resout = envelope.getResponse();
				
				//��������resoult
				@SuppressWarnings("unchecked")
				Vector<SoapObject> vec = (Vector<SoapObject>) resout;
				
				//ѭ����ʾ
				StringBuffer buffer = new StringBuffer();
				for(SoapObject soapObject : vec)
				{
					buffer.append(soapObject.getProperty("patient_phone")+"_");
					buffer.append(soapObject.getProperty("day01")+"_");
					buffer.append(soapObject.getProperty("day02")+"_");
					buffer.append(soapObject.getProperty("day03")+"_");
					buffer.append(soapObject.getProperty("day04")+"_");
					buffer.append(soapObject.getProperty("day05")+"_");
					buffer.append(soapObject.getProperty("day06")+"_");
					buffer.append(soapObject.getProperty("day07")+"#");
				}
				
				buffer.deleteCharAt(buffer.length()-1);
				String patientSleepDiaryInfo = new String(buffer);
				patientSleepDiaryInfo(patientSleepDiaryInfo);
				
				//���ԣ���ʾ���
				//Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
				System.out.println("���----" + data);
				Toast.makeText(MainActivity.this, "���سɹ�", Toast.LENGTH_LONG).show();
				
				new AlertDialog.Builder(MainActivity.this)
				.setTitle("��ܰ��ʾ")
				.setMessage("�������سɹ����������ͻ���")
				.setPositiveButton("ȷ��", null)
				.show();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
	}
public void patientSleepDiaryInfo(String str){
	String patientSleepDiary[] = null;
	String patientSleepDiaryInfo[] = null;
	//���ա�#���ָ�
	patientSleepDiary = str.split("#");
	sddao = new SleepDiaryDAO(MainActivity.this);
	//��ձ��
	sddao.deletetable();
	for(int i = 0;i < patientSleepDiary.length;i++){
		//��ò�����Ϣ
		System.out.println("patient----" + patientSleepDiary[i]);
		patientSleepDiaryInfo = patientSleepDiary[i].split("_");
		for(int j = 0; j < patientSleepDiaryInfo.length;j++){
			System.out.println("patientTestInfo---" +j + "----" + patientSleepDiaryInfo[j]);
		}
			//��װ
			SleepDiary sleepDiarys = new SleepDiary(patientSleepDiaryInfo[0],patientSleepDiaryInfo[1], patientSleepDiaryInfo[2],
					patientSleepDiaryInfo[3],patientSleepDiaryInfo[4],patientSleepDiaryInfo[5],patientSleepDiaryInfo[6],patientSleepDiaryInfo[7]);
			//Log.v("�������������", "���");
			
			//��������
			sddao.insert(sleepDiarys);
//			PatientList();
//			 try {
//				Thread.sleep(500);
//				//����0.5s��ˢ���б�
//				PatientList();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
}	
}
		
    public static boolean isNetworkAvailable(Context context){
    	ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	if(cm == null){
    		
    	}
    	else {
    		NetworkInfo[] info = cm.getAllNetworkInfo();
    		if(info != null){
    			for(int i = 0 ; i < info.length;i ++){
    				if(info[i].getState() == NetworkInfo.State.CONNECTED){
    					return true;
    				}
    					
    			}
    		}
    	}
    	return false;
    }
   
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.send_advice:
			doctor_advice();
			break;
		case R.id.hint_table:
			i ++;
			if (i % 2 == 0) {
				tableLayout.setVisibility(View.GONE);
				otherAdvice.setVisibility(View.VISIBLE);
				hintTable.setText("��һҳ");
			}else if (i % 2 == 1) {
				tableLayout.setVisibility(View.VISIBLE);
				otherAdvice.setVisibility(View.GONE);
				hintTable.setText("��һҳ");
			}
			break;
		case R.id.chart_button:
			if (patient_phone != null) {
				Intent intent = new Intent();
				intent.putExtra("patientPhone", patient_phone);
				intent.setClass(this, SelectChartActivity.class);
				startActivity(intent);
			}else {
				Toast.makeText(this, "��ѡ����", Toast.LENGTH_SHORT).show();
			}
			
			break;

		default:
			break;
		}
	}
	private int a,b,c,d;
	String doctorAdvice;
	public void doctor_advice(){
		
		System.out.println("��ȡҽ����Ϣ----");
		//��һ������
		RadioGroup radiogroup1 = (RadioGroup) findViewById(R.id.radiogroup1);
		final RadioButton radioButton11 = (RadioButton) findViewById(R.id.radioButton11);
		final RadioButton radioButton12 = (RadioButton) findViewById(R.id.radioButton12);
		final RadioButton radioButton13 = (RadioButton) findViewById(R.id.radioButton13);
		
		//�ڶ�������
		RadioGroup radiogroup2 = (RadioGroup) findViewById(R.id.radiogroup2);
		final RadioButton radioButton21 = (RadioButton) findViewById(R.id.radioButton21);
		final RadioButton radioButton22 = (RadioButton) findViewById(R.id.radioButton22);
		final RadioButton radioButton23 = (RadioButton) findViewById(R.id.radioButton23);
		
		//����������
		RadioGroup radiogroup3 = (RadioGroup) findViewById(R.id.radiogroup3);
		final RadioButton radioButton31 = (RadioButton) findViewById(R.id.radioButton31);
		final RadioButton radioButton32 = (RadioButton) findViewById(R.id.radioButton32);
		final RadioButton radioButton33 = (RadioButton) findViewById(R.id.radioButton33);
		
		//��si������
		RadioGroup radiogroup4 = (RadioGroup) findViewById(R.id.radiogroup4);
		final RadioButton radioButton41 = (RadioButton) findViewById(R.id.radioButton41);
		final RadioButton radioButton42 = (RadioButton) findViewById(R.id.radioButton42);
		final RadioButton radioButton43 = (RadioButton) findViewById(R.id.radioButton43);
		
		EditText sleep_latency = (EditText) findViewById(R.id.sleep_latency);
		EditText sleep_time = (EditText) findViewById(R.id.sleep_time);
		EditText sleep_efficient = (EditText) findViewById(R.id.sleep_efficient);
		EditText today_sleep = (EditText) findViewById(R.id.today_sleep);
		EditText sleep_latency2 = (EditText) findViewById(R.id.sleep_latency2);
		EditText sleep_time2 = (EditText) findViewById(R.id.sleep_time2);
		EditText add_doctor_advice = (EditText) findViewById(R.id.add_doctor_advice);
		
//		radiogroup1.clearCheck();
//		radiogroup2.clearCheck();
//		radiogroup3.clearCheck();
//		radiogroup4.clearCheck();
//		sleep_latency.setText("");
//		sleep_time.setText("");
//		sleep_efficient.setText("");
//		today_sleep.setText("");
//		sleep_latency2.setText("");
//		sleep_time2.setText("");
		
		radiogroup1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedId) {
				if(checkedId == radioButton11.getId()){
					a = 0;
				}
				if(checkedId == radioButton12.getId()){
					a = 1;
				}
				if(checkedId == radioButton13.getId()){
					a = 2;
				}
				
			}
		});
		radiogroup2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedId) {
				if(checkedId == radioButton21.getId()){
					b = 0;
				}
				if(checkedId == radioButton22.getId()){
					b = 1;
				}
				if(checkedId == radioButton23.getId()){
					b = 2;
				}
				
			}
		});
		radiogroup3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedId) {
				if(checkedId == radioButton31.getId()){
					c = 0;
				}
				if(checkedId == radioButton32.getId()){
					c = 1;
				}
				if(checkedId == radioButton33.getId()){
					c = 2;
				}
				
			}
		});
		radiogroup4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedId) {
				if(checkedId == radioButton41.getId()){
					d = 0;
				}
				if(checkedId == radioButton42.getId()){
					d = 1;
				}
				if(checkedId == radioButton43.getId()){
					d = 2;
				}
				
			}
		});
		doctorAdvice = sleep_latency.getText().toString() + "," + String.valueOf(a) + "," +
							sleep_time.getText().toString() + "," +String.valueOf(b) + "," +
							sleep_efficient.getText().toString() +"," + String.valueOf(c) + "," +
							today_sleep.getText().toString() + "," + String.valueOf(d) + "," +
							sleep_latency2.getText().toString() + "," + sleep_time2.getText().toString() + 
							"," + add_doctor_advice.getText().toString();
		System.out.println("ҽ����Ϣ----" + doctorAdvice);
//		patient_phone = "18875208558";
		if (patient_phone != null) {
			//����ҽ����Ϣ
			DoctorAdivce da = new DoctorAdivce(patient_phone, doctorAdvice);
			DoctorAdviceDAO dadao = new DoctorAdviceDAO(this);
			dadao.insert(da);
			System.out.println("ҽ�����ݱ���ɹ�");
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					Looper.prepare();
					// ����ҽ��
					SendDoctorAdviceThread();
					Looper.loop();
				}
			}).start();
	
		}else {
			new AlertDialog.Builder(MainActivity.this)
			.setTitle("��ܰ��ʾ")
			.setMessage("��ѡ����!")
			.setPositiveButton("ȷ��", null)
			.show();
			return;
		}
		
	}
	private void SendDoctorAdviceThread() {
//		SleepDiary sd = new SleepDiary();
//		SleepDiaryDAO sddao = new SleepDiaryDAO(UpdateInfomation.this);
//		sd = sddao.find((int)sddao.getCount());
		DoctorAdivce da = new DoctorAdivce();
		DoctorAdviceDAO dadao = new DoctorAdviceDAO(MainActivity.this);
		da = dadao.find((int)dadao.getCount());
//		da = dadao.find(3);
		System.out.println("da----" + da);
		
		//����1�� ����Webservice �ĵ��ò������뾲̬��IP��ַlocalhost���ϣ����뾲̬IP��ַ
		 wsdlURL= "http://m167x89974.iask.in:52327/CloudServer/services/UserService";
		 webMethod = "addDoctor_Service";
		 namespace = "http://service.cloud.chinasoft.com";
		 soapAction = namespace + webMethod;
		
		//����2:����һ������SoapObject
	    soapObject = new SoapObject(namespace, webMethod);
		//����3�����ݲ���
		soapObject.addProperty("doctorAdvice", da);
		
		//����4������һ��SoapSer
		SoapSerializationEnvelope  envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		//�Զ���Ķ���������envelop
		//�����ص㣺ָ���������˵ķ����л������
		//u.getClass�ǻ�ö���ķ��䡣
		envelope.addMapping("http://po.cloud.chinasoft.com/xsd", "doctorAdvice", da.getClass());
		//����5:�ŷ�����һ�´��ض���
		envelope.bodyOut = soapObject;
		//����6�������ʵ�Ա
		 se = new HttpTransportSE(wsdlURL);
		//����7��������
		try {
			se.call(soapAction, envelope);
			//����8����ȡ�ӻ��������صĽ��
			if(envelope.getResponse() != null)
			{
				Object resout = envelope.getResponse();
				//���ԣ���ʾ���
				Toast.makeText(MainActivity.this, "�����ϴ��ɹ�", Toast.LENGTH_LONG).show();
				System.out.println("���----" + resout.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
