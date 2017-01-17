package com.carBusiness.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.carBusiness.util.Utils;

/**
 * 封装了二手车的所有信息
 * @author Administrator
 *
 */
public class Car {
	//车辆品牌
	private String brand;

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}
	
	//车辆型号
	private String model;
	public void setModel(String model) {
		this.model = model;
	}

	public String getModel() {
		return model;
	}

	//离合器
	private String clutch;
	public void setClutch(String clutch) {
		this.clutch = clutch;
	}

	public String getClutch() {
		return clutch;
	}
	
	//公里数
	private int kilometres;
	public void setKilometres(int kilometres) {
		this.kilometres = kilometres;
	}

	public int getKilometres() {
		return kilometres;
	}
	
	//价格
	private int price;
	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}
	
	//上牌时间
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
	

	//信息载入时间
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
