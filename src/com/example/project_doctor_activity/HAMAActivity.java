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

public class HAMAActivity extends Activity{
	private ListView lv04;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hama_info);
		
		lv04 = (ListView) findViewById(R.id.lv04);
		
		//获得传递过来的id
		Intent intent = getIntent();
		String hama = intent.getStringExtra("hama");
		String[] question = new String[]{"焦虑心境:","紧张:","害怕:","失眠:","认知功能:","抑郁心境:","肌肉系统:","感觉系统:","心血管系统:","呼吸系统：","胃肠道症状：","生殖泌尿系统：",
				"植物神经症状：","会谈时行为","总分："};
		String[] strhama = new String[15];
		if(hama == null){
			strhama[1] = "您还没有做测试！！";
			System.out.println("hama没有做测试");
		}else{
			
			for(int i = 0; i < hama.length()-2;i++){
				strhama[i] = hama.substring(i, i+1);
			}
			strhama[14] = hama.substring(hama.length()-2,hama.length());
			for(int j = 0;j < 14;j++){
				if(strhama[j].equals("0")){
					strhama[j] = "无";
				}
				else if(strhama[j].equals("1")){
					strhama[j] = "轻度";
				}
				else if(strhama[j].equals("2")){
					strhama[j] = "中度";
				}
				else if(strhama[j].equals("3")){
					strhama[j] = "重度";
				}
				else if(strhama[j].equals("4")){
					strhama[j] = "极重度";
				}
			}
//			ArrayAdapter<String> arrayAdapter3 = null;// 创建ArrayAdapter对象
//			arrayAdapter3 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strhama);
//			lv04.setAdapter(arrayAdapter3);
			 List<Map<String, Object>> mData = new ArrayList<Map<String,Object>>();
			 for (int i = 0; i < question.length; i++) {
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("question", question[i]);
				item.put("answer", strhama[i]);
				mData.add(item);
			}
			 SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), mData, R.layout.item, new String []{"question","answer"}, new int[] {R.id.question,R.id.answer});
			 lv04.setAdapter(adapter);
		}
	}

}
