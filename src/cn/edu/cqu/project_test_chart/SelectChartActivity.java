package cn.edu.cqu.project_test_chart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.project_doctor.R;

public class SelectChartActivity extends Activity implements OnClickListener{

	private Button atBedButton,sleeptimeButton,SEButton;
	private String patient_phone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_chart);
		
		atBedButton = (Button) findViewById(R.id.atBedButton);
		atBedButton.setOnClickListener(this);
		sleeptimeButton = (Button) findViewById(R.id.sleeptimeButton);
		sleeptimeButton.setOnClickListener(this);
		SEButton = (Button) findViewById(R.id.SEButton);
		SEButton.setOnClickListener(this);
		
		Intent intent = getIntent();
		patient_phone = intent.getStringExtra("patientPhone");
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.atBedButton:{
				Intent intent = new Intent();
				intent.putExtra("flag", 0);
				intent.putExtra("patientPhone", patient_phone);
				intent.setClass(this, CurativeEffectActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.sleeptimeButton:{
				Intent intent = new Intent();
				intent.putExtra("flag", 1);
				intent.putExtra("patientPhone", patient_phone);
				intent.setClass(this, CurativeEffectActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.SEButton:{
				Intent intent = new Intent();
				intent.putExtra("flag", 2);
				intent.putExtra("patientPhone", patient_phone);
				intent.setClass(this, CurativeEffectActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}

}
