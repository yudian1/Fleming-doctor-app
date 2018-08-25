package com.example.project_doctor_dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_doctor_model.PatientInformation;
import com.example.project_doctor_model.SleepDiary;

public class SleepDiaryDAO {

	private final static String TABLE_NAME03 = "sleepdiary_day";
	private String PATIENT_ID = "id";
	//睡眠日记参数
	public final static String DAY01 = "day01";
	public final static String DAY02 = "day02";
	public final static String DAY03 = "day03";
	public final static String DAY04 = "day04";
	public final static String DAY05 = "day05";
	public final static String DAY06 = "day06";
	public final static String DAY07 = "day07";
	public final static String PATIENT_PHPNE = "patient_phone";
	private PatientDB helper;
	private SQLiteDatabase db;
	public SleepDiaryDAO(Context context){
		helper = new PatientDB(context);// 初始化DBOpenHelper对象
	}
	//增加病人测试数据
	public void insert(SleepDiary sleepdiary){
		//创建一个可写的数据库
		db = helper.getWritableDatabase();
		//ContentValues以键值对的形式存放数据 
		ContentValues cv = new ContentValues();
		cv.put(PATIENT_PHPNE, sleepdiary.getPatient_phone());
		cv.put(DAY01, sleepdiary.getDay01());
		cv.put(DAY02, sleepdiary.getDay02());
		cv.put(DAY03, sleepdiary.getDay03());
		cv.put(DAY04, sleepdiary.getDay04());
		cv.put(DAY05, sleepdiary.getDay05());
		cv.put(DAY06, sleepdiary.getDay06());
		cv.put(DAY07, sleepdiary.getDay07());
		db.insert(TABLE_NAME03, null, cv);
		//return row;	
		
	}
	public void deletetable(){
//		//创建一个可读的数据库
		db = helper.getReadableDatabase();
		//清空一个表格
		db.execSQL("DELETE FROM sleepdiary_day");
//		//删除一个表格
//		String sql=" DROP TABLE IF EXISTS "+TABLE_NAME;
//		db.execSQL(sql);
	}
	//修改病人信息
	public void updateDAY01(int id ,SleepDiary sleepdiary){
		//获得一个可写的数据库
		SQLiteDatabase db = helper.getWritableDatabase();
		//修改条件
		String where = PATIENT_ID + " = ?";
		//修改条件的参数  Integer.toString(id)，把数字用字符串表示
		String[] whereValue = { Integer.toString(id) };
				 
		ContentValues cv = new ContentValues();
		cv.put(DAY01,sleepdiary.getDay01());
		System.out.println("失败了？？？？？");
		db.update(TABLE_NAME03, cv, where, whereValue);
		}
	//修改病人信息
		public void updateDAY02(int id ,SleepDiary sleepdiary){
			//获得一个可写的数据库
			SQLiteDatabase db = helper.getWritableDatabase();
			//修改条件
			String where = PATIENT_ID + " = ?";
			//修改条件的参数  Integer.toString(id)，把数字用字符串表示
			String[] whereValue = { Integer.toString(id) };
					 
			ContentValues cv = new ContentValues();
			cv.put(DAY02, sleepdiary.getDay02());
			db.update(TABLE_NAME03, cv, where, whereValue);
			}
		//修改病人信息
		public void updateDAY03(int id ,SleepDiary sleepdiary){
			//获得一个可写的数据库
			SQLiteDatabase db = helper.getWritableDatabase();
			//修改条件
			String where = PATIENT_ID + " = ?";
			//修改条件的参数  Integer.toString(id)，把数字用字符串表示
			String[] whereValue = { Integer.toString(id) };
					 
			ContentValues cv = new ContentValues();
			cv.put(DAY03, sleepdiary.getDay03());
			db.update(TABLE_NAME03, cv, where, whereValue);
			}
		//修改病人信息
		public void updateDAY04(int id ,SleepDiary sleepdiary){
			//获得一个可写的数据库
			SQLiteDatabase db = helper.getWritableDatabase();
			//修改条件
			String where = PATIENT_ID + " = ?";
			//修改条件的参数  Integer.toString(id)，把数字用字符串表示
			String[] whereValue = { Integer.toString(id) };
					 
			ContentValues cv = new ContentValues();
			cv.put(DAY04, sleepdiary.getDay04());
			db.update(TABLE_NAME03, cv, where, whereValue);
			}
		//修改病人信息
				public void updateDAY05(int id ,SleepDiary sleepdiary){
					//获得一个可写的数据库
					SQLiteDatabase db = helper.getWritableDatabase();
					//修改条件
					String where = PATIENT_ID + " = ?";
					//修改条件的参数  Integer.toString(id)，把数字用字符串表示
					String[] whereValue = { Integer.toString(id) };
							 
					ContentValues cv = new ContentValues();
					cv.put(DAY05, sleepdiary.getDay05());
					db.update(TABLE_NAME03, cv, where, whereValue);
					}
				//修改病人信息
				public void updateDAY06(int id ,SleepDiary sleepdiary){
					//获得一个可写的数据库
					SQLiteDatabase db = helper.getWritableDatabase();
					//修改条件
					String where = PATIENT_ID + " = ?";
					//修改条件的参数  Integer.toString(id)，把数字用字符串表示
					String[] whereValue = { Integer.toString(id) };
							 
					ContentValues cv = new ContentValues();
					cv.put(DAY06, sleepdiary.getDay06());
					db.update(TABLE_NAME03, cv, where, whereValue);
					}
				//修改病人信息
				public void updateDAY07(int id ,SleepDiary sleepdiary){
					//获得一个可写的数据库
					SQLiteDatabase db = helper.getWritableDatabase();
					//修改条件
					String where = PATIENT_ID + " = ?";
					//修改条件的参数  Integer.toString(id)，把数字用字符串表示
					String[] whereValue = { Integer.toString(id) };
							 
					ContentValues cv = new ContentValues();
					cv.put(DAY07, sleepdiary.getDay07());
					db.update(TABLE_NAME03, cv, where, whereValue);
					}
	//查找病人信息
	public SleepDiary find(String patient_phone){
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
                        "select id,day01,day02,day03,day04,day05,day06,day07 from sleepdiary_day where patient_phone = ?",
                        new String[] {patient_phone});//根据编号查找到的病人信息，并存储到Cursor中
		//遍历查找到的病人信息
		if(cursor.moveToNext()){
			//将遍历查找到的病人信息存储到PatientInformation类中
			return new SleepDiary(
                    cursor.getString(cursor.getColumnIndex("day01")),
                    cursor.getString(cursor.getColumnIndex("day02")),
                    cursor.getString(cursor.getColumnIndex("day03")),
                    cursor.getString(cursor.getColumnIndex("day04")),
                    cursor.getString(cursor.getColumnIndex("day05")),
                    cursor.getString(cursor.getColumnIndex("day06")),
                    cursor.getString(cursor.getColumnIndex("day07"))
                    );
		}
		return null;	
	}
	//删除数据
	public void delete(Integer... ids){
	/*db = helper.getWritableDatabase();
	String where = PATIENT_ID + " = ?";
	String[] whereValue ={ Integer.toString(id) };
	db.delete(TABLE_NAME, where, whereValue);*/
		if(ids.length > 0){ //判断是否存在要删除的id
			StringBuffer sb = new StringBuffer();
			for(int i = 0;i < ids.length;i++){
				sb.append('?').append(','); //将删除条件添加到StringBuffer中
			}
			sb.deleteCharAt(sb.length() - 1);
			db = helper.getWritableDatabase();
			db.execSQL("delete from patient where _id in (" + sb + ")",
                    (Object[]) ids);
		}
	}
	/**
	 * 该方法主要是从病人信息中的指定索引处获得病人信息
	 * **/
	public List<PatientInformation> getScrollData(int start, int count) {
		 
        List<PatientInformation> patient = new ArrayList<PatientInformation>();// 创建集合对象
        db = helper.getWritableDatabase();
        //获取所有病人信息
        Cursor cursor = db.rawQuery("select * from sleepdiary_day limit ?,?",
                new String[] {String.valueOf(start), String.valueOf(count)});
        while (cursor.moveToNext())//遍历所有病人信息
        {
            //将遍历到的所有病人信息添加到集合中
        	patient.add(new PatientInformation(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("current_date")),
                    cursor.getString(cursor.getColumnIndex("patient_name")),
                    cursor.getString(cursor.getColumnIndex("patient_gender")),
                    cursor.getString(cursor.getColumnIndex("patient_age")),
                    cursor.getString(cursor.getColumnIndex("patient_postion")),
                    cursor.getString(cursor.getColumnIndex("patient_scde")),
                    cursor.getString(cursor.getColumnIndex("patient_insomnia")),
                    cursor.getString(cursor.getColumnIndex("patient_mhistory")),
                    cursor.getString(cursor.getColumnIndex("patient_phone"))
                    ));
        	}
        return patient;//返回集合
     
	}
	//得到数据库中的数据
	public Cursor export(){
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery( "select * from sleepdiary_day",null);
		return cursor;
		}
	//获取总记录数
	public long getCount() {
		 
        db = helper.getWritableDatabase();
        Cursor cursor = db
                .rawQuery("select count(id) from sleepdiary_day", null);//获取病人信息记录数
        if (cursor.moveToNext())//判断Cursor中是否有数据
        {
 
            return cursor.getLong(0);//返回总记录数
         
        }
        return 0;//没有数据则返回0
     
	}
	//获取病人的最大编号
	public int getMaxId() {
		 
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select max(id) from sleepdiary_day", null);//获取病人信息中的最大编号
        //访问Cursor中的最后一条数据
        while (cursor.moveToLast()) {
            return cursor.getInt(0);//获取访问到的数据，即最大编号
         
}
        return 0;//如果没有数据则返回0
     
}



}
