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
		//获得传递过来的id
		Intent intent = getIntent();
		String pqsi = intent.getStringExtra("pqsi");
		System.out.println("pqsi---" + pqsi);
		
		String[] question = new String[]{"上床时间:","SOL:","起床时间:","TST:","入睡困难:","夜间觉醒:","去厕所:","呼吸不畅:","咳嗽打鼾:","感觉冷："
				,"感觉热：","做恶梦：","疼痛不适：","其他：","催眠药物：","困倦：","精力不足：","同睡：","睡眠质量："};
		//判断pqsi是否为空
				String strpqsi[] = new String[19];
					if(pqsi == null){
						strpqsi[1] = "您还没有做测试！";
					}
					else{
						
						for(int i = 0; i < pqsi.length();i++){
							strpqsi[i] = pqsi.substring(i, i+1);
						}
						System.out.println("22222222222" + strpqsi[0]);
						if(strpqsi[0].equals("0")){
							strpqsi[0] = "20点以前";
						}
						else if(strpqsi[0].equals("1")){
							strpqsi[0] = "20-21点";
						}
						else if(strpqsi[0].equals("2")){
							strpqsi[0] = "21-22点";
						}
						else if(strpqsi[0].equals("3")){
							strpqsi[0] = "22-23点";
						}
						else if(strpqsi[0].equals("4")){
							strpqsi[0] = "23-24点";
						}
						System.out.println("pqsi11111111--" + strpqsi[0]);
						
						if(strpqsi[1].equals("0")){
							strpqsi[1] = "小于15分钟";
						}
						else if(strpqsi[1].equals("1")){
							strpqsi[1] = "16-30分钟";
						}
						else if(strpqsi[1].equals("2")){
							strpqsi[1] = "30-45分钟";
						}
						else if(strpqsi[1].equals("3")){
							strpqsi[1] = "45-60分钟";
						}
						else if(strpqsi[1].equals("4")){
							strpqsi[1] = "1小时以上";
						}
						
						if(strpqsi[2].equals("0")){
							strpqsi[2] = "5点以前";
						}
						else if(strpqsi[2].equals("1")){
							strpqsi[2] = "5-6点";
						}
						else if(strpqsi[2].equals("2")){
							strpqsi[2] = "6-7点";
						}
						else if(strpqsi[2].equals("3")){
							strpqsi[2] = "7-8点";
						}
						else if(strpqsi[2].equals("4")){
							strpqsi[2] = "8-9点";
						}
						
						if(strpqsi[3].equals("0")){
							strpqsi[3] = "小于4小时";
						}
						else if(strpqsi[3].equals("1")){
							strpqsi[3] = "4-6小时";
						}
						else if(strpqsi[3].equals("2")){
							strpqsi[3] = "6-8小时";
						}
						else if(strpqsi[3].equals("3")){
							strpqsi[3] = "8-10小时";
						}
						else if(strpqsi[3].equals("4")){
							strpqsi[3] = "10小时以上";
						}
						
						for(int j = 0;j < 14;j++){
							if(strpqsi[4+j].equals("0")){
								strpqsi[4+j] = "1次/周";
							}
							else if(strpqsi[4+j].equals("1")){
								strpqsi[4+j] = "2次/周";
							}
							else if(strpqsi[4+j].equals("2")){
								strpqsi[4+j] = "3次/周";
							}
							else if(strpqsi[4+j].equals("3")){
								strpqsi[4+j] = "4次/周";
							}
							else if(strpqsi[4+j].equals("4")){
								strpqsi[4+j] = "5次/周";
							}
							else if(strpqsi[4+j].equals("5")){
								strpqsi[4+j] = "6次/周";
							}
							else if(strpqsi[4+j].equals("6")){
								strpqsi[4+j] = "7次/周";
							}
						}
						if(strpqsi[18].equals("0")){
							strpqsi[18] = "很好";
						}
						else if(strpqsi[18].equals("1")){
							strpqsi[18] = "较好";
						}
						else if(strpqsi[18].equals("2")){
							strpqsi[18] = "较差";
						}
						else if(strpqsi[18].equals("3")){
							strpqsi[18] = "很差";
						}
//						ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
//						arrayAdapter = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strpqsi);
//						lv02.setAdapter(arrayAdapter);
						 List<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;
					        for(int i =0; i < question.length; i++) {    
					        	Map<String,Object> item = new HashMap<String,Object>();    
					        	item.put("question", question[i]);    
					        	item.put("answer", strpqsi[i]);    
					        	//item.put("性别", mListStr[i]);    
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
