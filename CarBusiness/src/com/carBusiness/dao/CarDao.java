package com.carBusiness.dao;

import java.util.ArrayList;

import com.carBusiness.bean.Car;
import com.carBusiness.data.Data;

/**
 * ��װ�˶��ڵײ㳵�������ݵ���ɾ�Ĳ�
 * @author Administrator
 *
 */
public class CarDao {
	private Data data;
	public CarDao(Data data){
		this.data = data;
	}
	
	
	/**
	 * ���ݼ۸��������ϵĳ����󼯺�
	 * @param maxPrice
	 * @param minPrice
	 * @return
	 */
	public ArrayList<Car> findCarByPrice(int maxPrice , int minPrice){
		ArrayList<Car> cars = new ArrayList<Car>();
		for(Car car : data.getCars()){
			if(car.getPrice() < maxPrice && car.getPrice() > minPrice){
				cars.add(car);
			}
		}
		return cars;
	}
	
	/**
	 * �������г�����ļ���
	 * @return
	 */
	public ArrayList<Car> getCars(){
		return data.getCars();
	}
	
	/**
	 * ����Ʒ�����������󼯺�
	 * @param brand
	 * @return
	 */
	public ArrayList<Car>findCarByBrond(String brand){
		ArrayList<Car> cars = new ArrayList<Car>();
		for(Car car : data.getCars()){
			if(car.getBrand().equals(brand)){
				cars.add(car);
			}
		}
		return cars;
	}
	
	/**
	 * �õ��ײ㳵�������е�Ʒ��(���ظ�)
	 * @return
	 */
	public ArrayList<String> getBronds(){
		//����һ��Ʒ�Ƶļ���
		ArrayList<String> bronds = new ArrayList<String>();
		for(Car car : data.getCars()){
			if(!bronds.contains(car.getBrand())){
				bronds.add(car.getBrand());
			}	
		}
		return bronds;
	}
	/**
	 * �����г�����ҳ�鿴
	 * @param cars ������
	 * @param minNum ��С����
	 * @param maxNum �������
	 */
	public ArrayList<Car> showCars(ArrayList<Car> cars,int minNum , int maxNum){
		ArrayList<Car> newCars = new ArrayList<Car>();
		for(int i = minNum,j = 1 ; i <= maxNum ; i++,j++){
			newCars.add(cars.get(i));
		}
		return newCars;
	}
	
	/**
	 * ��������ʱ���ѯ
	 * @param startMillisecond
	 * @param endMillisecond
	 * @return
	 */
	public ArrayList<Car> findCarsByTime(long startMillisecond , long endMillisecond){
		ArrayList<Car> cars = new ArrayList<Car>();
		for(Car car : data.getCars()){
			long carMillisecond = car.getTime().getTimeInMillis();
			if(startMillisecond < carMillisecond && carMillisecond < endMillisecond){
				cars.add(car);
			}
		}
		return cars;
	} 
}
