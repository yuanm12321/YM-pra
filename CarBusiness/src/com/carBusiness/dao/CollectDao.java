package com.carBusiness.dao;

import java.util.ArrayList;

import com.carBusiness.bean.*;
import com.carBusiness.data.Data;

/**
 * ��װ�˶��ڵײ��ղؼ��ϵ���ɾ�Ĳ�
 * @author Administrator
 *
 */
public class CollectDao {
	private Data data;
	public CollectDao(Data data){
		this.data = data;
	}
	/**
	 * ���ղ�����ӳ���
	 * @param user
	 * @param car
	 */
	public void addCar(User user , Car car){
		data.getCollect().get(user).add(car);
	}
	
	/**
	 * �õ��û���Ӧ���ղس����󼯺�
	 * @param user
	 * @return
	 */
	public ArrayList<Car> getCollectCars(User user){
		return data.getCollect().get(user);
	}
	
	/**
	 * ���ղ��в�ѯ����
	 * @param user
	 * @param car
	 * @return
	 */
	public Car getCar(User user ,Car searchCar){
		for(Car car : data.getCollect().get(user)){
			if(car == searchCar){
				return car;
			}
		}
		return null;
	}
}
