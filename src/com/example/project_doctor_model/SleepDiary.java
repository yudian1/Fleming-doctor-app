package com.example.project_doctor_model;

public class SleepDiary {
	private int id;
	private String day01;
	private String day02;
	private String day03;
	private String day04;
	private String day05;
	private String day06;
	private String day07;
	private String patient_phone;
	public String getPatient_phone() {
		return patient_phone;
	}
	public void setPatient_phone(String patient_phone) {
		this.patient_phone = patient_phone;
	}
	public SleepDiary(String day01, String day02, String day03, String day04,
			String day05, String day06, String day07) {
		super();
		this.day01 = day01;
		this.day02 = day02;
		this.day03 = day03;
		this.day04 = day04;
		this.day05 = day05;
		this.day06 = day06;
		this.day07 = day07;
	}
	public SleepDiary(int id, String day01, String day02, String day03,
			String day04, String day05, String day06, String day07) {
		super();
		this.id = id;
		this.day01 = day01;
		this.day02 = day02;
		this.day03 = day03;
		this.day04 = day04;
		this.day05 = day05;
		this.day06 = day06;
		this.day07 = day07;
	}
	public SleepDiary(String patient_phone,String day01, String day02, String day03, String day04,
			String day05, String day06, String day07) {
		super();
		this.patient_phone = patient_phone;
		this.day01 = day01;
		this.day02 = day02;
		this.day03 = day03;
		this.day04 = day04;
		this.day05 = day05;
		this.day06 = day06;
		this.day07 = day07;
	}
	public SleepDiary() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDay01() {
		return day01;
	}
	public void setDay01(String day01) {
		this.day01 = day01;
	}
	public String getDay02() {
		return day02;
	}
	public void setDay02(String day02) {
		this.day02 = day02;
	}
	public String getDay03() {
		return day03;
	}
	public void setDay03(String day03) {
		this.day03 = day03;
	}
	public String getDay04() {
		return day04;
	}
	public void setDay04(String day04) {
		this.day04 = day04;
	}
	public String getDay05() {
		return day05;
	}
	public void setDay05(String day05) {
		this.day05 = day05;
	}
	public String getDay06() {
		return day06;
	}
	public void setDay06(String day06) {
		this.day06 = day06;
	}
	public String getDay07() {
		return day07;
	}
	public void setDay07(String day07) {
		this.day07 = day07;
	}
	
}
