package com.example.project_doctor_activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.project_doctor.R;
import com.example.project_doctor_dao.PatientInformationDAO;
import com.example.project_doctor_model.PatientInformation;

public class PatientInfoActivity extends Activity{
	
	private String patientphone;
	private ListView lv01;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_info);
		
		lv01 = (ListView) findViewById(R.id.lv01);
		//获得传递过来的id
		Intent intent = getIntent();
		patientphone = intent.getStringExtra("patientphone");
		System.out.println("patientphone--" + patientphone);
//		//将String型的id值转换成int型
//		int i=Integer.parseInt(patientid);
//		System.out.println("得到的id----" + i);
		PatientInformationDAO pi = new PatientInformationDAO(PatientInfoActivity.this);
		//通过patientphone来查询表格
		PatientInformation patient = pi.find(patientphone);
		System.out.println("patient----" + patient);
		String [] patientInfo = new String [9];
		patientInfo[0] = patient.getCurrent_date();
		patientInfo[1] = patient.getPatient_name();
		patientInfo[2] = patient.getPatient_gender();
		patientInfo[3] = patient.getPatient_age();
		patientInfo[4] = patient.getPatient_postion();
		patientInfo[5] = patient.getPatient_scde();
		patientInfo[6] = patient.getPatient_insomnia();
		patientInfo[7] = patient.getPatient_mhistory();
		patientInfo[8] = patient.getPatient_phone();
		String[] question = new String[]{"日期:","姓名:","性别:","年龄:","职业:","学历:","失眠时间:","病史:","电话:"};
//		ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
//		arrayAdapter = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,patientInfo);
//		lv01.setAdapter(arrayAdapter);
		 List<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();
	        for(int i =0; i < question.length; i++) {    
	        	Map<String,Object> item = new HashMap<String,Object>();    
	        	item.put("question", question[i]);    
	        	item.put("answer", patientInfo[i]);    
	        	//item.put("性别", mListStr[i]);    
	        	mData.add(item);     
	        }    
	        SimpleAdapter adapter = new SimpleAdapter(
	        				this,
	        				mData,
	        				R.layout.item,    
	        				new String[]{"question","answer"},
	        				new int[]{R.id.question,R.id.answer});   
	        lv01.setAdapter(adapter);
	}

}
