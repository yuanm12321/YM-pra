package com.carBusiness.data;

import java.util.ArrayList;
import java.util.HashMap;

import com.carBusiness.bean.Car;
import com.carBusiness.bean.User;

/**
 * 分装了所有了底层数据
 * @author Administrator
 *
 */
public class Data {
	//用户信息
	private ArrayList<User> users;

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<User> getUsers() {
		return users;
	}
	
	//车辆信息
	private ArrayList<Car> cars;
	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public ArrayList<Car> getCars() {
		return cars;
	}
	//每个用户与收藏车辆的键值对
	private HashMap<User, ArrayList<Car>> collect;
	public void setCollect(HashMap<User, ArrayList<Car>> collect) {
		this.collect = collect;
	}

	public HashMap<User, ArrayList<Car>> getCollect() {
		return collect;
	}
	
	public Data(){
		users = new ArrayList<User>();
		cars = new ArrayList<Car>();
		collect = new HashMap<User, ArrayList<Car>>();
		//添加初始数据
		users.add(new User("1", "1"));
		users.get(0).setId(users.size());
		cars.add(new Car("宝马", "X1","自动",56000, 170000, 2012, 6, 8, 2014, 2, 26));
		cars.add(new Car("大众", "POLO", "手动", 110000, 40000, 2009, 12, 3, 2010, 4, 9));
		cars.add(new Car("雪佛兰", "科鲁兹", "自动", 150000, 58000, 2005, 11, 28, 2012, 2, 12));
		cars.add(new Car("雪佛兰", "景程", "手动", 1050000, 60000, 2010, 4, 8, 2013, 7, 9));
		cars.add(new Car("雪佛兰", "赛欧", "手动", 49000, 40000, 1999, 7, 21, 2013, 8, 30));
		cars.add(new Car("大众", "帕赛特", "双离合",13000, 215000, 2010, 9, 30, 2014, 5, 6));
		cars.add(new Car("大众", "迈特威", "双离合", 62000, 330000, 2012, 4, 30, 2013, 9, 13));
		cars.add(new Car("大众", "速腾", "手动", 25000, 131000, 2014, 8, 1, 2014, 12, 25));
		cars.add(new Car("大众", "朗逸", "手动", 39200, 74800, 2010, 10, 1, 2015, 1, 20));
		cars.add(new Car("大众", "CC", "双离合", 38300, 230000, 2014, 4, 25, 2015, 4, 30));
		cars.add(new Car("大众", "甲壳虫", "自动", 27000, 112000, 2010, 4, 10, 2013, 1, 26));
		cars.add(new Car("宝马", "X5", "自动", 8200, 730000, 2015, 4, 30, 2015, 7, 4));
		cars.add(new Car("宝马", "X6", "自动", 50000, 570000, 2012, 6, 1, 2014, 7, 9));
		cars.add(new Car("奔驰", "威霆", "自动", 25000, 420000, 2012, 9, 12, 2014, 9, 9));
		cars.add(new Car("奔驰", "GLK", "自动", 30000, 350000, 2013, 5, 6, 2015, 4, 24));
		cars.add(new Car("奔驰", "CLS", "自动", 17200, 560000, 2012, 11, 10, 2015, 1, 20));
		cars.add(new Car("别克", "GL8", "自动", 13500, 330000, 2015, 4, 1, 2015, 8, 30));
		cars.add(new Car("别克", "凯越", "手动", 47000, 52000, 2010, 5, 1, 2014, 3, 29));
		cars.add(new Car("别克", "君威", "自动", 68500, 120000, 2010, 9, 30, 2014, 12, 11));
		cars.add(new Car("别克", "君越", "手动", 43000, 50000, 2012, 9, 10, 2013, 11, 27));
		cars.add(new Car("别克", "英朗", "自动", 14000, 100000, 2014, 2, 28, 2015, 7, 4));
		cars.add(new Car("本田", "雅阁", "自动", 10000, 166000, 2014, 4, 10, 2015, 6, 1));
		cars.add(new Car("本田", "思域", "自动", 9000, 82000, 2009, 3, 12, 2013, 1, 8));
		cars.add(new Car("本田", "凌派", "自动", 19000, 90000, 2013, 8, 9, 2014, 10, 1));
		cars.add(new Car("宝马", "5系", "自动", 18000, 130000, 2011, 6, 1, 2014, 7, 1));
		//在收藏中添加第一个用户与他相对应的收藏
		collect.put(users.get(0), new ArrayList<Car>());
		//第一个用户的收藏车集合
		collect.get(users.get(0)).add(cars.get(0));
		collect.get(users.get(0)).add(cars.get(15));
		collect.get(users.get(0)).add(cars.get(19));
		collect.get(users.get(0)).add(cars.get(8));
		collect.get(users.get(0)).add(cars.get(22));
	}

	

	
}
