package com.carBusiness.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.carBusiness.bean.Car;

/**
 * ��װ�����еĹ��߷���
 * @author Administrator
 *
 */
public class Utils {
	static Scanner sc = new Scanner(System.in);
	public static final int DATE = 1;
	public static final int HOURSE = 2;
	public static final int MINUTE = 3;
	public static final int SECOND = 4;
	/**
	 * �Ӽ��̽���һ��int�͵�����
	 * @return
	 */
	public static int getNum(){
		int num = 0;
		String str;
		sc = new Scanner(System.in);
		str = sc.next();
		try{
			num = Integer.parseInt(str);
		}catch(Exception e){
			System.out.println("��Ч���룬���������룡");
			num = getNum();
		}
		return num;
	}
	
	/**
	 * �Ӽ��̽���һ��String�͵�����
	 * @return
	 */
	public static String getStr(){
		String str = sc.next();
		return str;
	}
	
	/**
	 * �Ƚ��������ڵĴ�С���Աȵ�������
	 * ��һ�����ڱȵڶ��������緵��-1������1����ȷ���0��
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compare(Date date1 , Date date2 , int type){
		if(date1.getTime() % transformDiv(type) > date2.getTime() % transformDiv(type)){
			return 1;
		}else if(date1.getTime() % transformDiv(type) == date2.getTime() % transformDiv(type)){
			return 0;
		}
		return -1;
	}
	
	/**
	 * ����ת����������λ
	 * @param type
	 * @return
	 */
	public static int transformDiv(int type){
		int div = -1;
		switch(type){
		//��
		case 1:
			div = 1000*60*60*24;
			break;
		//Сʱ
		case 2:
			div = 1000*60*60;
			break;
		//����
		case 3:
			div = 1000*60;
			break;
		//��
		case 4:
			div = 1000;
			break;
		}
		return div;
	}
	
	/**
	 * �������ϰ�ʱ����µ�������
	 * @param cars
	 * @return
	 */
	public static ArrayList<Car> order(ArrayList<Car> cars){
		ArrayList<Car> orderCars = cars;
		Car tempCar = new Car();
		for(int i = 1 ; i < orderCars.size() ; i++){
			for(int j = 0 ; j < orderCars.size()-i ; j++){
				if(orderCars.get(j).getLoginTime().getTimeInMillis() < orderCars.get(j+1).getLoginTime().getTimeInMillis()){
					tempCar = orderCars.get(j);
					orderCars.set(j, orderCars.get(j+1));
					orderCars.set(j+1,tempCar);
				}
			}
		}
		return orderCars;
	}
	
	/**
	 * ��ȡҳ�����С����
	 * @param size
	 * @param page
	 * @return
	 */
	public static int getMinNum(int size, int page){
		int minNum = (page-1)*size;
		return minNum;
	}
	
	/**
	 * ��ȡҳ����������
	 * @param size
	 * @param page
	 * @param cars
	 * @return
	 */
	public static int getMaxNum(int size , int page ,ArrayList<Car> cars){
		int maxNum = page*size-1;
		if(maxNum > cars.size()-1){
			maxNum = cars.size()-1;
		}
		return maxNum;
	}
	
	/**
	 * ������ҳ��
	 * @param size
	 * @param cars
	 * @return
	 */
	public static int getAllPage(int size ,ArrayList<Car> cars){
		int allPage = cars.size()/size;
		if(cars.size() % size != 0){
			allPage++;
		}
		return allPage;
	}
	/**
	 * ��Calendarת��Ϊyyyy-MM-dd��ʽ���ַ���
	 * @param time
	 * @return
	 */
	/**
	 * ���ַ��� ת��Ϊʱ��
	 */
	public static Date parseStringtoData(String time) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-mm-dd").parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
