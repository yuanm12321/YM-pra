package com.carBusiness.dao;

import java.util.ArrayList;

import com.carBusiness.bean.*;
import com.carBusiness.data.Data;

/**
 * 分装了对于底层收藏集合的增删改查
 * @author Administrator
 *
 */
public class CollectDao {
	private Data data;
	public CollectDao(Data data){
		this.data = data;
	}
	/**
	 * 在收藏中添加车辆
	 * @param user
	 * @param car
	 */
	public void addCar(User user , Car car){
		data.getCollect().get(user).add(car);
	}
	
	/**
	 * 得到用户对应的收藏车对象集合
	 * @param user
	 * @return
	 */
	public ArrayList<Car> getCollectCars(User user){
		return data.getCollect().get(user);
	}
	
	/**
	 * 在收藏中查询车辆
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
