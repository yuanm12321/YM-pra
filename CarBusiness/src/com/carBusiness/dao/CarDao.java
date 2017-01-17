package com.carBusiness.dao;

import java.util.ArrayList;

import com.carBusiness.bean.Car;
import com.carBusiness.data.Data;

/**
 * 分装了对于底层车对象数据的增删改查
 * @author Administrator
 *
 */
public class CarDao {
	private Data data;
	public CarDao(Data data){
		this.data = data;
	}
	
	
	/**
	 * 根据价格搜索符合的车对象集合
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
	 * 返回所有车对象的集合
	 * @return
	 */
	public ArrayList<Car> getCars(){
		return data.getCars();
	}
	
	/**
	 * 根据品牌搜索车对象集合
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
	 * 得到底层车集合所有的品牌(不重复)
	 * @return
	 */
	public ArrayList<String> getBronds(){
		//创建一个品牌的集合
		ArrayList<String> bronds = new ArrayList<String>();
		for(Car car : data.getCars()){
			if(!bronds.contains(car.getBrand())){
				bronds.add(car.getBrand());
			}	
		}
		return bronds;
	}
	/**
	 * 将所有车辆分页查看
	 * @param cars 车集合
	 * @param minNum 最小索引
	 * @param maxNum 最大索引
	 */
	public ArrayList<Car> showCars(ArrayList<Car> cars,int minNum , int maxNum){
		ArrayList<Car> newCars = new ArrayList<Car>();
		for(int i = minNum,j = 1 ; i <= maxNum ; i++,j++){
			newCars.add(cars.get(i));
		}
		return newCars;
	}
	
	/**
	 * 根据上牌时间查询
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
