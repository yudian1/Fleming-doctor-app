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

public class HAMDActivity extends Activity{
	private ListView lv05;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hamd_info);
		
		lv05 = (ListView) findViewById(R.id.lv05);
		//获得传递过来的id
		Intent intent = getIntent();
		String hamd = intent.getStringExtra("hamd");
		System.out.println("hamd---" + hamd);
		
		String[] question = new String[]{"抑郁情绪:","有罪感:","自杀:","入睡困难:","睡眠不深:","早醒:","工作和兴趣:","阻滞:","激越:","精神性焦虑：","躯体性焦虑：","胃肠道症状：",
				"全身症状：","性症状：","疑病：","体重减轻：","自知力：","总分："};
		String[] strhamd = new String[18];
		if(hamd == null){
			strhamd[1] = "您还没有做测试！！";
			System.out.println("hamd没有做测试");
		}else{
			for(int i = 0; i < hamd.length()-2;i++){
				strhamd[i] = hamd.substring(i, i+1);
			}
			strhamd[17] = hamd.substring(hamd.length()-2,hamd.length());
			for(int j = 0;j < 17;j++){
				if(strhamd[j].equals("0")){
					strhamd[j] = "无";
				}
				else if(strhamd[j].equals("1")){
					strhamd[j] = "轻度";
				}
				else if(strhamd[j].equals("2")){
					strhamd[j] = "中度";
				}
				else if(strhamd[j].equals("3")){
					strhamd[j] = "重度";
				}
				else if(strhamd[j].equals("4")){
					strhamd[j] = "极重度";
				}
			}
//			ArrayAdapter<String> arrayAdapter4 = null;// 创建ArrayAdapter对象
//			arrayAdapter4 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strhamd);
//			lv05.setAdapter(arrayAdapter4);
			List<Map<String, String>> mData = new ArrayList<Map<String,String>>();
			for (int i = 0; i < strhamd.length; i++) {
				Map<String, String> item = new HashMap<String, String>();
				item.put("question", question[i]);
				item.put("answer", strhamd[i]);
				mData.add(item);
			}
			SimpleAdapter adapter = new SimpleAdapter(this, mData, R.layout.item, new String []{"question","answer"}, new int[]{R.id.question,R.id.answer});
			lv05.setAdapter(adapter);
		}
		
		
	}

}
