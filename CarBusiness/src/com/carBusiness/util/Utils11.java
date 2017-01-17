package com.carBusiness.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.carBusiness.bean.Car;

/**
 * 分装了所有的工具方法
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
	 * 从键盘接收一个int型的数据
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
			System.out.println("无效输入，请重新输入！");
			num = getNum();
		}
		return num;
	}
	
	/**
	 * 从键盘接收一个String型的数据
	 * @return
	 */
	public static String getStr(){
		String str = sc.next();
		return str;
	}
	
	/**
	 * 比较两个日期的大小（对比到天数）
	 * 第一个日期比第二个日期早返回-1，晚返回1，相等返回0。
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
	 * 毫秒转换成其他单位
	 * @param type
	 * @return
	 */
	public static int transformDiv(int type){
		int div = -1;
		switch(type){
		//天
		case 1:
			div = 1000*60*60*24;
			break;
		//小时
		case 2:
			div = 1000*60*60;
			break;
		//分钟
		case 3:
			div = 1000*60;
			break;
		//秒
		case 4:
			div = 1000;
			break;
		}
		return div;
	}
	
	/**
	 * 将车集合按时间从新到旧排序
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
	 * 获取页面的最小索引
	 * @param size
	 * @param page
	 * @return
	 */
	public static int getMinNum(int size, int page){
		int minNum = (page-1)*size;
		return minNum;
	}
	
	/**
	 * 获取页面的最大索引
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
	 * 计算总页数
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
	 * 将Calendar转化为yyyy-MM-dd形式的字符串
	 * @param time
	 * @return
	 */
	/**
	 * 把字符串 转化为时间
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
