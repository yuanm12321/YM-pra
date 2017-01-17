package com.carBusiness.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.carBusiness.util.Utils;

/**
 * ��װ�˶��ֳ���������Ϣ
 * @author Administrator
 *
 */
public class Car {
	//����Ʒ��
	private String brand;

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}
	
	//�����ͺ�
	private String model;
	public void setModel(String model) {
		this.model = model;
	}

	public String getModel() {
		return model;
	}

	//�����
	private String clutch;
	public void setClutch(String clutch) {
		this.clutch = clutch;
	}

	public String getClutch() {
		return clutch;
	}
	
	//������
	private int kilometres;
	public void setKilometres(int kilometres) {
		this.kilometres = kilometres;
	}

	public int getKilometres() {
		return kilometres;
	}
	
	//�۸�
	private int price;
	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}
	
	//����ʱ��
	private Calendar time;
	
	public String showTime() {
		String time = Utils.showTime(getTime());
		return time;
	}
	public Calendar getTime(){
		return time;
	}
	public void setTime(int year , int month , int day){
		time.set(year, month+1, day);
	}
	

	//��Ϣ����ʱ��
	private Calendar loginTime;
	public void setLoginTime(int loginYear, int loginMonth , int loginDay) {
		loginTime.set(loginYear, loginMonth+1, loginDay);
	}

	public Calendar getLoginTime() {
		return loginTime;
	}
	public String showLoginTime(){
		String loginTime = Utils.showTime(getLoginTime());
		return loginTime;
	}
	public Car(String brand, String model , String clutch , int kilometres, int price, int year , int month , int day , int loginYear , int loginMonth , int loginDay){
		time = Calendar.getInstance();
		loginTime = Calendar.getInstance();
		setBrand(brand);
		setModel(model);
		setClutch(clutch);
		setKilometres(kilometres);
		setPrice(price);
		setTime(year, month, day);
		setLoginTime(loginYear, loginMonth, loginDay);
	}
	public Car(){
		
	}
}
