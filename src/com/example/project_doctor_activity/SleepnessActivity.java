package com.example.project_doctor_activity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.project_doctor.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SleepnessActivity extends Activity{
	private ListView lv03;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sleepness_info);
		
		lv03 = (ListView) findViewById(R.id.lv03);
		//��ô��ݹ�����id
		Intent intent = getIntent();
		String sleepness = intent.getStringExtra("sleepness");
		String[] question = new String[]{"��˯����:","ά������:","����:","����̶�:","�ռ书��:","��������:","���ĳ̶�:","�ܷ֣�"};
		String[] strsleepness = new String[8];
		if(sleepness == null){
			strsleepness[1] = "����û�������ԣ�";
			System.out.println("sleepnessû��������");
		}
		else{
			for(int i = 0; i < sleepness.length() - 1;i++){
				strsleepness[i] = sleepness.substring(i, i+1);
			}
			strsleepness[7] = sleepness.substring(sleepness.length() - 2, sleepness.length());
			for(int j = 0;j < 3;j++){
				if(strsleepness[j].equals("0")){
					strsleepness[j] = "��";
				}
				else if(strsleepness[j].equals("1")){
					strsleepness[j] = "���";
				}
				else if(strsleepness[j].equals("2")){
					strsleepness[j] = "�ж�";
				}
				else if(strsleepness[j].equals("3")){
					strsleepness[j] = "�ض�";
				}
				else if(strsleepness[j].equals("4")){
					strsleepness[j] = "���ض�";
				}
			}
			if(strsleepness[3].equals("0")){
				strsleepness[3] = "������";
			}
			else if(strsleepness[3].equals("1")){
				strsleepness[3] = "����";
			}
			else if(strsleepness[3].equals("2")){
				strsleepness[3] = "һ��";
			}
			else if(strsleepness[3].equals("3")){
				strsleepness[3] = "������";
			}
			else if(strsleepness[3].equals("4")){
				strsleepness[3] = "�ܲ�����";
			}
			
			if(strsleepness[4].equals("0")){
				strsleepness[4] = "û�и���";
			}
			else if(strsleepness[4].equals("1")){
				strsleepness[4] = "��΢";
			}
			else if(strsleepness[4].equals("2")){
				strsleepness[4] = "��Щ";
			}
			else if(strsleepness[4].equals("3")){
				strsleepness[4] = "�϶�";
			}
			else if(strsleepness[4].equals("4")){
				strsleepness[4] = "�ܶ����";
			}
			
			if(strsleepness[5].equals("0")){
				strsleepness[5] = "û��";
			}
			else if(strsleepness[5].equals("1")){
				strsleepness[5] = "һ��";
			}
			else if(strsleepness[5].equals("2")){
				strsleepness[5] = "��Щ";
			}
			else if(strsleepness[5].equals("3")){
				strsleepness[5] = "�϶�";
			}
			else if(strsleepness[5].equals("4")){
				strsleepness[5] = "�ܶ�";
			}
			
			if(strsleepness[6].equals("0")){
				strsleepness[6] = "û��";
			}
			else if(strsleepness[6].equals("1")){
				strsleepness[6] = "һ��";
			}
			else if(strsleepness[6].equals("2")){
				strsleepness[6] = "��Щ";
			}
			else if(strsleepness[6].equals("3")){
				strsleepness[6] = "�϶�";
			}
			else if(strsleepness[6].equals("4")){
				strsleepness[6] = "�ܶ�";
			}
//			ArrayAdapter<String> arrayAdapter2 = null;// ����ArrayAdapter����
//			arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strsleepness);
//			lv03.setAdapter(arrayAdapter2);
			List<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;
	        for(int i =0; i < question.length; i++) {    
	        	Map<String,Object> item = new HashMap<String,Object>();    
	        	item.put("question", question[i]);    
	        	item.put("answer", strsleepness[i]);    
	        	//item.put("�Ա�", mListStr[i]);    
	        	mData.add(item);     
	        }    
	        SimpleAdapter adapter = new SimpleAdapter(
	        				this,
	        				mData,
	        				R.layout.item,    
	        				new String[]{"question","answer"},
	        				new int[]{R.id.question,R.id.answer});   
	        lv03.setAdapter(adapter);
		}
	}

}
