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
		//��ô��ݹ�����id
		Intent intent = getIntent();
		patientphone = intent.getStringExtra("patientphone");
		System.out.println("patientphone--" + patientphone);
//		//��String�͵�idֵת����int��
//		int i=Integer.parseInt(patientid);
//		System.out.println("�õ���id----" + i);
		PatientInformationDAO pi = new PatientInformationDAO(PatientInfoActivity.this);
		//ͨ��patientphone����ѯ���
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
		String[] question = new String[]{"����:","����:","�Ա�:","����:","ְҵ:","ѧ��:","ʧ��ʱ��:","��ʷ:","�绰:"};
//		ArrayAdapter<String> arrayAdapter = null;// ����ArrayAdapter����
//		arrayAdapter = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,patientInfo);
//		lv01.setAdapter(arrayAdapter);
		 List<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();
	        for(int i =0; i < question.length; i++) {    
	        	Map<String,Object> item = new HashMap<String,Object>();    
	        	item.put("question", question[i]);    
	        	item.put("answer", patientInfo[i]);    
	        	//item.put("�Ա�", mListStr[i]);    
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
