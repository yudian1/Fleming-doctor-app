package com.example.project_doctor_activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.project_doctor.R;

public class PQSIActivity extends Activity{
	private ListView lv02;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pqsi_info);
		
		lv02 = (ListView) findViewById(R.id.lv02);
		//��ô��ݹ�����id
		Intent intent = getIntent();
		String pqsi = intent.getStringExtra("pqsi");
		System.out.println("pqsi---" + pqsi);
		
		String[] question = new String[]{"�ϴ�ʱ��:","SOL:","��ʱ��:","TST:","��˯����:","ҹ�����:","ȥ����:","��������:","���Դ���:","�о��䣺"
				,"�о��ȣ�","�����Σ�","��ʹ���ʣ�","������","����ҩ�","���룺","�������㣺","ͬ˯��","˯��������"};
		//�ж�pqsi�Ƿ�Ϊ��
				String strpqsi[] = new String[19];
					if(pqsi == null){
						strpqsi[1] = "����û�������ԣ�";
					}
					else{
						
						for(int i = 0; i < pqsi.length();i++){
							strpqsi[i] = pqsi.substring(i, i+1);
						}
						System.out.println("22222222222" + strpqsi[0]);
						if(strpqsi[0].equals("0")){
							strpqsi[0] = "20����ǰ";
						}
						else if(strpqsi[0].equals("1")){
							strpqsi[0] = "20-21��";
						}
						else if(strpqsi[0].equals("2")){
							strpqsi[0] = "21-22��";
						}
						else if(strpqsi[0].equals("3")){
							strpqsi[0] = "22-23��";
						}
						else if(strpqsi[0].equals("4")){
							strpqsi[0] = "23-24��";
						}
						System.out.println("pqsi11111111--" + strpqsi[0]);
						
						if(strpqsi[1].equals("0")){
							strpqsi[1] = "С��15����";
						}
						else if(strpqsi[1].equals("1")){
							strpqsi[1] = "16-30����";
						}
						else if(strpqsi[1].equals("2")){
							strpqsi[1] = "30-45����";
						}
						else if(strpqsi[1].equals("3")){
							strpqsi[1] = "45-60����";
						}
						else if(strpqsi[1].equals("4")){
							strpqsi[1] = "1Сʱ����";
						}
						
						if(strpqsi[2].equals("0")){
							strpqsi[2] = "5����ǰ";
						}
						else if(strpqsi[2].equals("1")){
							strpqsi[2] = "5-6��";
						}
						else if(strpqsi[2].equals("2")){
							strpqsi[2] = "6-7��";
						}
						else if(strpqsi[2].equals("3")){
							strpqsi[2] = "7-8��";
						}
						else if(strpqsi[2].equals("4")){
							strpqsi[2] = "8-9��";
						}
						
						if(strpqsi[3].equals("0")){
							strpqsi[3] = "С��4Сʱ";
						}
						else if(strpqsi[3].equals("1")){
							strpqsi[3] = "4-6Сʱ";
						}
						else if(strpqsi[3].equals("2")){
							strpqsi[3] = "6-8Сʱ";
						}
						else if(strpqsi[3].equals("3")){
							strpqsi[3] = "8-10Сʱ";
						}
						else if(strpqsi[3].equals("4")){
							strpqsi[3] = "10Сʱ����";
						}
						
						for(int j = 0;j < 14;j++){
							if(strpqsi[4+j].equals("0")){
								strpqsi[4+j] = "1��/��";
							}
							else if(strpqsi[4+j].equals("1")){
								strpqsi[4+j] = "2��/��";
							}
							else if(strpqsi[4+j].equals("2")){
								strpqsi[4+j] = "3��/��";
							}
							else if(strpqsi[4+j].equals("3")){
								strpqsi[4+j] = "4��/��";
							}
							else if(strpqsi[4+j].equals("4")){
								strpqsi[4+j] = "5��/��";
							}
							else if(strpqsi[4+j].equals("5")){
								strpqsi[4+j] = "6��/��";
							}
							else if(strpqsi[4+j].equals("6")){
								strpqsi[4+j] = "7��/��";
							}
						}
						if(strpqsi[18].equals("0")){
							strpqsi[18] = "�ܺ�";
						}
						else if(strpqsi[18].equals("1")){
							strpqsi[18] = "�Ϻ�";
						}
						else if(strpqsi[18].equals("2")){
							strpqsi[18] = "�ϲ�";
						}
						else if(strpqsi[18].equals("3")){
							strpqsi[18] = "�ܲ�";
						}
//						ArrayAdapter<String> arrayAdapter = null;// ����ArrayAdapter����
//						arrayAdapter = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strpqsi);
//						lv02.setAdapter(arrayAdapter);
						 List<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;
					        for(int i =0; i < question.length; i++) {    
					        	Map<String,Object> item = new HashMap<String,Object>();    
					        	item.put("question", question[i]);    
					        	item.put("answer", strpqsi[i]);    
					        	//item.put("�Ա�", mListStr[i]);    
					        	mData.add(item);     
					        }    
					        SimpleAdapter adapter = new SimpleAdapter(
					        				this,
					        				mData,
					        				R.layout.item,    
					        				new String[]{"question","answer"},
					        				new int[]{R.id.question,R.id.answer});   
					        lv02.setAdapter(adapter);
					}
	}

}
