package com.carBusiness.logic;

import java.util.ArrayList;
import java.util.Calendar;

import com.carBusiness.bean.*;
import com.carBusiness.dao.*;
import com.carBusiness.data.Data;
import com.carBusiness.util.Utils;


/**
 * 封装了二手车交易系统的所有逻辑
 * @author Administrator
 *
 */
public class CarSystem {
	private UserDao userDao;
	private CarDao carDao;
	private CollectDao collectDao;
	//最新信息当前页数
	public int page = 1;
	//查找结果显示的页数
	public int searchPage = 1;
	//每页车的数量
	public  int size = 6;
	public ArrayList<Car> compareCar ;
	public CarSystem(Data data){
		userDao = new UserDao(data);
		carDao = new CarDao(data);
		collectDao = new CollectDao(data);
		compareCar = new ArrayList<Car>();
	}
	
	/**
	 * 系统的入口
	 */
	public void start(){
		System.out.println("--------------入口界面-------------------");
		System.out.println("1.登录\n2.注册\n3.退出\n请选择：");
		int chooise = Utils.getNum();
		switch(chooise){
		case 1:
			login();
			break;
		case 2:
			regist();
			break;
		case 3:
			outSystem();
			break;
		default:
			System.out.println("无效选择，请重新选择！");
			start();
			break;
		}
	}
	
	/**
	 * 登录界面
	 */
	public void login(){
		System.out.println("---------------登录界面--------------------");
		System.out.println("请输入账号：");
		String userName = Utils.getStr();
		System.out.println("请输入密码：");
		String passWord = Utils.getStr();
		//账号不存在时
		if(userDao.findUserByName(userName) == null){
			System.out.println("账号不存在，请确认后再输入！");
			failLogin();
			return;
		}
		//密码错误时
		if(!(userDao.findUserByName(userName).getPassWord().equals(passWord))){
			System.out.println("密码错误，请确认后再输入！");
			failLogin();
			return;
		}
		//进入主菜单
		mainMenu(userDao.findUserByName(userName));
	}
	
	/**
	 * 登录失败的提示信息
	 */
	public void failLogin(){
		System.out.println("是否继续登录？（1.继续登入2.返回上一级）");
		int chooise = Utils.getNum();
		switch(chooise){
		case 1:
			login();
			break;
		case 2:
			start();
			break;
		default:
			System.out.println("无效选择，请重新选择。");
			failLogin();
			break;
		}
	}
	
	/**
	 * 注册界面
	 */
	public void regist(){
		System.out.println("-----------------注册界面---------------------");
		System.out.println("请输入账号：");
		String userName = Utils.getStr();
		System.out.println("请输入密码：");
		String passWord = Utils.getStr();
		//账号已存在
		if(userDao.findUserByName(userName) != null){
			System.out.println("账号已存在，注册失败。");
			failRegist();
			return;
		}
		//添加新用户
		userDao.addUser(new User(userName, passWord));
		//注册成功返回主界面
		start();
	}
	
	/**
	 * 注册失败提示信息
	 */
	public void failRegist(){
		System.out.println("是否继续注册？（1.继续注册2.返回上一级）");
		int chooise = Utils.getNum();
		switch(chooise){
		case 1:
			regist();
			break;
		case 2:
			start();
			break;
		default:
			failRegist();
			break;
		}
	}
	
	/**
	 * 主菜单
	 */
	public void mainMenu(User user){
		System.out.println("--------------主菜单-----------------");
		System.out.println("1.最新二手车信息\n2.搜索二手车\n3.我的收藏\n4.退出登录\n请选择：");
		//重置初始页面数
		page = 1;
		int chooise = Utils.getNum();
		switch(chooise){
		case 1:
			showNewCarInfo(user,page);
			break;
		case 2:
			searchCar(user);
			break;
		case 3:
			showMyCollect(user);
			break;
		case 4:
			compareCar.clear();
			start();
			break;
		default:
			System.out.println("无效输入，请重新选择！");
			mainMenu(user);
			break;
		}
	}
	
	/**
	 * 显示最新二手车信息
	 */
	public void showNewCarInfo(User user, int page){
		System.out.println("------------------------------最新二手车信息---------------------------------");
		ArrayList<Car> orderCar = new ArrayList<Car>();
		orderCar = Utils.order(carDao.getCars());
		showSearchCars(orderCar, user , 1 ,page , 0);
	}
	
	
	/**
	 * 选择车辆显示详细信息
	 * @param orderCar
	 * @param page
	 * @param size
	 * @param allPage
	 * @param user
	 */
	public void chooiseCar(ArrayList<Car> cars,int allPage ,User user,int returnWhere,int page , int searchType){
		if(cars.size() == 0){
			searchCar(user);
			return;
		}
		int chooise = Utils.getNum();
		switch(chooise){
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			if((page-1)*size+chooise-1 > cars.size()){
				System.out.println("无效选择，请重新选择。");
				chooiseCar(cars, allPage, user ,returnWhere ,page , searchType);
				return;
			}else{
				showCar(cars.get((page-1)*size+chooise-1));
			}
			break;
		case 8:
			if(page == 1){
				System.out.println("该页已是第一页。");
				System.out.println("请重新选择。");
				chooiseCar(cars, allPage,user, returnWhere , page , searchType);
			}else{
				page--;
				if(returnWhere == 1){
					showNewCarInfo(user,page);
				}else{
					showSearchCars(cars, user , 2 ,page , searchType);
				}
			}
			return;
		case 9:
			if(page == allPage){
				System.out.println("该页已是最后一页。");
				System.out.println("请重新选择。");
				chooiseCar(cars, allPage, user , returnWhere , page , searchType);
			}else{
				page++;
				if(returnWhere == 1){
					showNewCarInfo(user,page);
				}else{
					showSearchCars(cars, user , 2 ,page , searchType);
				}
			}
			return;
		case 0:
			if(returnWhere == 1){
				mainMenu(user);
			}else{
				searchCar(user);
			}
			return;
		default:
			System.out.println("无效选择，请重新选择！");
			chooiseCar(cars, allPage, user,returnWhere , page , searchType);
			return;
		}
		System.out.println("1.加入收藏\n2.加入对比\n3.返回");
		chooiseAdd(user ,cars.get((page-1)*size+chooise-1),returnWhere);
	}
	/**
	 * 选择加入对比或者收藏
	 * @param user
	 * @param car
	 * @param returnWhere 返回位置1为最新二手车信息 2为搜索界面
	 */
	public void chooiseAdd(User user ,Car car , int returnWhere){
		int chooise = Utils.getNum();
		switch(chooise){
		case 1:
			if(collectDao.getCar(user, car) == null){
				collectDao.addCar(user, car);
				System.out.println("添加成功！");
			}else{
				System.out.println("该车已存在收藏夹！");
			}
			if(returnWhere == 1){
				System.out.println("即将返回二手车最新信息。");
				showNewCarInfo(user,page);
			}else{
				System.out.println("即将返回搜索界面。");
				searchCar(user);
			}
			break;
		case 2:
			compareCars(car, user, returnWhere);
			break;
		case 3:
			if(returnWhere == 1){
				System.out.println("即将返回二手车最新信息。");
				showNewCarInfo(user,page);
			}else{
				System.out.println("即将返回搜索界面。");
				searchCar(user);
			}
			break;
		default:
			System.out.println("无效输入，请重新输入！");
			chooiseAdd(user, car ,returnWhere);
			break;
		}
	}
	/**
	 * 对比两辆车
	 * @param car
	 */
	public void compareCars(Car car , User user , int returnWhere){
		if(compareCar.size() == 0){
			compareCar.add(car);
			System.out.println("目前只有一辆车，无法进行比较，请继续选择车辆。");
			if(returnWhere == 1){
				System.out.println("即将返回二手车最新信息。");
				showNewCarInfo(user,page);
			}else{
				System.out.println("即将返回搜索界面。");
				searchCar(user);
			}
			return;
		}
		if(compareCar.size() == 2){
			compareCar.remove(0);
		}
		compareCar.add(car);
		showCars(compareCar);
		System.out.println("按任意键返回主菜单。");
		Utils.getStr();
		mainMenu(user);
	}
	/**
	 * 搜索二手车
	 */
	public void searchCar(User user){
		System.out.println("---------------搜索二手车------------------");
		System.out.println("1.根据上牌时间搜索\n2.根据品牌搜索\n3.根据价格搜索\n4.返回主菜单\n请选择：");
		int chooise = Utils.getNum();
		//重置起始页面
		searchPage = 1;
		switch(chooise){
		case 1:
			searchByTime(user);
			break;
		case 2:
			searchByBrand(user);
			break;
		case 3:
			searchByPrice(user);
			break;
		case 4:
			mainMenu(user);
			break;
		default:
			System.out.println("无效选择，请重新选择！");
			searchCar(user);
			break;
		}
	}
	/**
	 * 根据上牌时间搜索
	 */
	public void searchByTime(User user){
		System.out.println("请输入起始年份：");
		int year = Utils.putInYear();
		System.out.println("请输入起始月份：");
		int month = Utils.putInMonth();
		System.out.println("请输入起始日期：");
		int day = Utils.putInDay(year, month);
		System.out.println("请输入结束年份：");
		year = Utils.putInYear();
		System.out.println("请输入结束月份：");
		month = Utils.putInMonth();
		System.out.println("请输入结束日期：");
		day = Utils.putInDay(year, month);
		Calendar startTime = Calendar.getInstance();
		startTime.set(year, month+1, day);
		Calendar endTime = Calendar.getInstance();
		endTime.set(year, month+1, day);
		System.out.println("搜索的上牌时间为"+Utils.showTime(startTime)+"到"+Utils.showTime(endTime));
		System.out.println("1.确认 2.重新输入");
		countersign(startTime , endTime , user);
		
	}
	/**
	 * 确认搜索日期
	 * @param startTime
	 * @param endTime
	 * @param user
	 */
	public void countersign(Calendar startTime , Calendar endTime , User user){
		int chooise = Utils.getNum();
		switch(chooise){
		case 1:
			//创建一个Car集合存放符合要求的车对象
			ArrayList<Car> cars = new ArrayList<Car>();
			//根据上牌时间查询符合的车对象集合
			long startMillisecond = startTime.getTimeInMillis();
			long endMillisecond = endTime.getTimeInMillis();
			cars = carDao.findCarsByTime(startMillisecond, endMillisecond);
			//显示符合要求的车集合的信息
			showSearchCars(cars, user , 2 ,searchPage , 1);
			break;
		case 2:
			searchByTime(user);
			break;
		default:
			System.out.println("无效输入，请重新输入！");
			countersign(startTime, endTime, user);
			break;
		}
	}
	/**
	 * 根据品牌搜索车辆
	 */
	public void searchByBrand(User user){
		System.out.println("请选择要搜索车辆的品牌：");
		int i = 1;
		for(String brond : carDao.getBronds()){
			System.out.print(i+"."+brond+"\t");
			i++;
		}
		System.out.println();
		String brand =carDao.getBronds().get(chooseBrond(carDao.getBronds())-1);
		ArrayList<Car> cars = new ArrayList<Car>();
		cars = carDao.findCarByBrond(brand);
		//显示符合要求的车集合的信息
		showSearchCars(cars, user , 2 ,searchPage , 2);
	}
	
	/**
	 * 选择品牌
	 * @param bronds
	 * @return
	 */
	public int chooseBrond(ArrayList<String> bronds){
		int chooise = Utils.getNum();
		if(chooise < 1 || chooise > bronds.size()){
			System.out.println("无效选择，请重新选择。");
			chooise = chooseBrond(bronds);
		}
		return chooise;
	}
	/**
	 * 根据价位范围搜索车辆
	 */
	public void searchByPrice(User user){
		System.out.println("请输入要搜索车辆的价位信息：");
		System.out.print("最高价格：");
		int maxPrice = Utils.getNum();
		System.out.print("最低价格：");
		int minPrice = Utils.getNum();
		ArrayList<Car> cars = new ArrayList<Car>();
		cars = carDao.findCarByPrice(maxPrice, minPrice);
		//显示符合要求的车集合的信息
		showSearchCars(cars, user , 2 , searchPage , 3);
	}
	
	/**
	 * 分页显示搜索结果
	 * @param cars
	 */
	public void showSearchCars(ArrayList<Car> cars , User user , int returnWhere , int page , int searchType){
		ArrayList<Car> newCars = new ArrayList<Car>();
		int allPage = Utils.getAllPage(size, cars);
		int minNum = Utils.getMinNum(size, page);
		int maxNum = Utils.getMaxNum(size, page, cars);
		newCars = carDao.showCars(cars, minNum, maxNum);
		showCarsBasicInfo(newCars , page , allPage , searchType);
		chooiseCar(cars, allPage ,user, returnWhere , page , searchType);
	}
	/**
	 * 我的收藏
	 */
	public void showMyCollect(User user){
		if(collectDao.getCollectCars(user).size() == 0){
			System.out.println("亲，您还没收藏过车辆，请添加后再来查看");
			mainMenu(user);
			return;
		}
		showCars(collectDao.getCollectCars(user));
		System.out.println("按任意键，返回上一级。");
		Utils.getStr();
		mainMenu(user);
	}
	/**
	 * 退出系统
	 */
	public void outSystem(){
		System.out.println("您已成功退出系统！");
		System.exit(0);
	}
	/**
	 * 显示车对象集合详细信息
	 */
	public  void showCars(ArrayList<Car> cars){
		System.out.println("序号\t品牌\t型号\t离合器\t公里数\t价格\t上牌时间");
		for(int i = 0 ; i < cars.size() ; i++){
			System.out.println(i+1+"\t"+cars.get(i).getBrand()+"\t"+cars.get(i).getModel()+"\t"+cars.get(i).getClutch()+"\t"+cars.get(i).getKilometres()+"\t"+cars.get(i).getPrice()+"\t"+cars.get(i).showTime());
		}
	}
	/**
	 * 显示一辆车的详细信息
	 * @param car
	 */
	public  void showCar(Car car){
		System.out.println("---------------------车辆详细信息------------------------------");
		System.out.println("品牌\t型号\t离合器\t公里数\t价格\t上牌时间");
		System.out.println(car.getBrand()+"\t"+car.getModel()+"\t"+car.getClutch()+"\t"+car.getKilometres()+"\t"+car.getPrice()+"\t"+car.showTime());
	}
	
	/**
	 * 显示分页车对象集合的基本信息
	 * @param cars
	 * @param page
	 * @param allPage
	 * @param searchType 0最新二手车 1按上牌时间搜索 2按品牌搜索 3按价位搜索
	 */
	 
	public  void showCarsBasicInfo(ArrayList<Car> cars , int page , int allPage , int searchType){
		int i = 1;
		if(cars.size() == 0){
			System.out.println("没有符合要求的车辆，即将返回搜索界面！");
			return;
		}
		if(searchType == 0){
			System.out.println("序号\t\t品牌\t\t型号\t\t离合器\t\t发布时间");
			for(Car car : cars){
				System.out.println(i+"\t\t"+car.getBrand()+"\t\t"+car.getModel()+"\t\t"+car.getClutch()+"\t\t"+car.showLoginTime());
				i++;
			}
		}
		if(searchType == 1){
			System.out.println("序号\t\t品牌\t\t型号\t\t离合器\t\t\t\t上牌时间时间");
			for(Car car : cars){
				System.out.println(i+"\t\t"+car.getBrand()+"\t\t"+car.getModel()+"\t\t"+car.getClutch()+"\t\t"+car.showTime());
				i++;
			}
		}
		if(searchType == 2){
			System.out.println("序号\t\t品牌\t\t型号\t\t离合器");
			for(Car car : cars){
				System.out.println(i+"\t\t"+car.getBrand()+"\t\t"+car.getModel()+"\t\t"+car.getClutch());
				i++;
			}
		}
		if(searchType == 3){
			System.out.println("序号\t\t品牌\t\t型号\t\t离合器\t\t价格");
			for(Car car : cars){
				System.out.println(i+"\t\t"+car.getBrand()+"\t\t"+car.getModel()+"\t\t"+car.getClutch()+"\t\t"+car.getPrice());
				i++;
			}
		}
		System.out.println("\t\t\t\t\t-"+page+"-");
		System.out.println();
		if(allPage == 1){
			System.out.println("\t\t\t\t0.返回上一级");
			return;
		}
		if(page == 1){
			System.out.println("\t\t\t\t\t9.下一页\t\t0.返回上一级");
		}else if(page == allPage){
			System.out.println("\t\t\t8.上一页\t\t\t\t0.返回上一级");
		}else{
			System.out.println("\t\t\t8.上一页\t\t9.下一页\t\t0.返回上一级");
		}
	}
}
