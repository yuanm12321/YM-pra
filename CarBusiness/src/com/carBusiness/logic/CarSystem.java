package com.carBusiness.logic;

import java.util.ArrayList;
import java.util.Calendar;

import com.carBusiness.bean.*;
import com.carBusiness.dao.*;
import com.carBusiness.data.Data;
import com.carBusiness.util.Utils;


/**
 * ��װ�˶��ֳ�����ϵͳ�������߼�
 * @author Administrator
 *
 */
public class CarSystem {
	private UserDao userDao;
	private CarDao carDao;
	private CollectDao collectDao;
	//������Ϣ��ǰҳ��
	public int page = 1;
	//���ҽ����ʾ��ҳ��
	public int searchPage = 1;
	//ÿҳ��������
	public  int size = 6;
	public ArrayList<Car> compareCar ;
	public CarSystem(Data data){
		userDao = new UserDao(data);
		carDao = new CarDao(data);
		collectDao = new CollectDao(data);
		compareCar = new ArrayList<Car>();
	}
	
	/**
	 * ϵͳ�����
	 */
	public void start(){
		System.out.println("--------------��ڽ���-------------------");
		System.out.println("1.��¼\n2.ע��\n3.�˳�\n��ѡ��");
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
			System.out.println("��Чѡ��������ѡ��");
			start();
			break;
		}
	}
	
	/**
	 * ��¼����
	 */
	public void login(){
		System.out.println("---------------��¼����--------------------");
		System.out.println("�������˺ţ�");
		String userName = Utils.getStr();
		System.out.println("���������룺");
		String passWord = Utils.getStr();
		//�˺Ų�����ʱ
		if(userDao.findUserByName(userName) == null){
			System.out.println("�˺Ų����ڣ���ȷ�Ϻ������룡");
			failLogin();
			return;
		}
		//�������ʱ
		if(!(userDao.findUserByName(userName).getPassWord().equals(passWord))){
			System.out.println("���������ȷ�Ϻ������룡");
			failLogin();
			return;
		}
		//�������˵�
		mainMenu(userDao.findUserByName(userName));
	}
	
	/**
	 * ��¼ʧ�ܵ���ʾ��Ϣ
	 */
	public void failLogin(){
		System.out.println("�Ƿ������¼����1.��������2.������һ����");
		int chooise = Utils.getNum();
		switch(chooise){
		case 1:
			login();
			break;
		case 2:
			start();
			break;
		default:
			System.out.println("��Чѡ��������ѡ��");
			failLogin();
			break;
		}
	}
	
	/**
	 * ע�����
	 */
	public void regist(){
		System.out.println("-----------------ע�����---------------------");
		System.out.println("�������˺ţ�");
		String userName = Utils.getStr();
		System.out.println("���������룺");
		String passWord = Utils.getStr();
		//�˺��Ѵ���
		if(userDao.findUserByName(userName) != null){
			System.out.println("�˺��Ѵ��ڣ�ע��ʧ�ܡ�");
			failRegist();
			return;
		}
		//������û�
		userDao.addUser(new User(userName, passWord));
		//ע��ɹ�����������
		start();
	}
	
	/**
	 * ע��ʧ����ʾ��Ϣ
	 */
	public void failRegist(){
		System.out.println("�Ƿ����ע�᣿��1.����ע��2.������һ����");
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
	 * ���˵�
	 */
	public void mainMenu(User user){
		System.out.println("--------------���˵�-----------------");
		System.out.println("1.���¶��ֳ���Ϣ\n2.�������ֳ�\n3.�ҵ��ղ�\n4.�˳���¼\n��ѡ��");
		//���ó�ʼҳ����
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
			System.out.println("��Ч���룬������ѡ��");
			mainMenu(user);
			break;
		}
	}
	
	/**
	 * ��ʾ���¶��ֳ���Ϣ
	 */
	public void showNewCarInfo(User user, int page){
		System.out.println("------------------------------���¶��ֳ���Ϣ---------------------------------");
		ArrayList<Car> orderCar = new ArrayList<Car>();
		orderCar = Utils.order(carDao.getCars());
		showSearchCars(orderCar, user , 1 ,page , 0);
	}
	
	
	/**
	 * ѡ������ʾ��ϸ��Ϣ
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
				System.out.println("��Чѡ��������ѡ��");
				chooiseCar(cars, allPage, user ,returnWhere ,page , searchType);
				return;
			}else{
				showCar(cars.get((page-1)*size+chooise-1));
			}
			break;
		case 8:
			if(page == 1){
				System.out.println("��ҳ���ǵ�һҳ��");
				System.out.println("������ѡ��");
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
				System.out.println("��ҳ�������һҳ��");
				System.out.println("������ѡ��");
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
			System.out.println("��Чѡ��������ѡ��");
			chooiseCar(cars, allPage, user,returnWhere , page , searchType);
			return;
		}
		System.out.println("1.�����ղ�\n2.����Ա�\n3.����");
		chooiseAdd(user ,cars.get((page-1)*size+chooise-1),returnWhere);
	}
	/**
	 * ѡ�����ԱȻ����ղ�
	 * @param user
	 * @param car
	 * @param returnWhere ����λ��1Ϊ���¶��ֳ���Ϣ 2Ϊ��������
	 */
	public void chooiseAdd(User user ,Car car , int returnWhere){
		int chooise = Utils.getNum();
		switch(chooise){
		case 1:
			if(collectDao.getCar(user, car) == null){
				collectDao.addCar(user, car);
				System.out.println("��ӳɹ���");
			}else{
				System.out.println("�ó��Ѵ����ղؼУ�");
			}
			if(returnWhere == 1){
				System.out.println("�������ض��ֳ�������Ϣ��");
				showNewCarInfo(user,page);
			}else{
				System.out.println("���������������档");
				searchCar(user);
			}
			break;
		case 2:
			compareCars(car, user, returnWhere);
			break;
		case 3:
			if(returnWhere == 1){
				System.out.println("�������ض��ֳ�������Ϣ��");
				showNewCarInfo(user,page);
			}else{
				System.out.println("���������������档");
				searchCar(user);
			}
			break;
		default:
			System.out.println("��Ч���룬���������룡");
			chooiseAdd(user, car ,returnWhere);
			break;
		}
	}
	/**
	 * �Ա�������
	 * @param car
	 */
	public void compareCars(Car car , User user , int returnWhere){
		if(compareCar.size() == 0){
			compareCar.add(car);
			System.out.println("Ŀǰֻ��һ�������޷����бȽϣ������ѡ������");
			if(returnWhere == 1){
				System.out.println("�������ض��ֳ�������Ϣ��");
				showNewCarInfo(user,page);
			}else{
				System.out.println("���������������档");
				searchCar(user);
			}
			return;
		}
		if(compareCar.size() == 2){
			compareCar.remove(0);
		}
		compareCar.add(car);
		showCars(compareCar);
		System.out.println("��������������˵���");
		Utils.getStr();
		mainMenu(user);
	}
	/**
	 * �������ֳ�
	 */
	public void searchCar(User user){
		System.out.println("---------------�������ֳ�------------------");
		System.out.println("1.��������ʱ������\n2.����Ʒ������\n3.���ݼ۸�����\n4.�������˵�\n��ѡ��");
		int chooise = Utils.getNum();
		//������ʼҳ��
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
			System.out.println("��Чѡ��������ѡ��");
			searchCar(user);
			break;
		}
	}
	/**
	 * ��������ʱ������
	 */
	public void searchByTime(User user){
		System.out.println("��������ʼ��ݣ�");
		int year = Utils.putInYear();
		System.out.println("��������ʼ�·ݣ�");
		int month = Utils.putInMonth();
		System.out.println("��������ʼ���ڣ�");
		int day = Utils.putInDay(year, month);
		System.out.println("�����������ݣ�");
		year = Utils.putInYear();
		System.out.println("����������·ݣ�");
		month = Utils.putInMonth();
		System.out.println("������������ڣ�");
		day = Utils.putInDay(year, month);
		Calendar startTime = Calendar.getInstance();
		startTime.set(year, month+1, day);
		Calendar endTime = Calendar.getInstance();
		endTime.set(year, month+1, day);
		System.out.println("����������ʱ��Ϊ"+Utils.showTime(startTime)+"��"+Utils.showTime(endTime));
		System.out.println("1.ȷ�� 2.��������");
		countersign(startTime , endTime , user);
		
	}
	/**
	 * ȷ����������
	 * @param startTime
	 * @param endTime
	 * @param user
	 */
	public void countersign(Calendar startTime , Calendar endTime , User user){
		int chooise = Utils.getNum();
		switch(chooise){
		case 1:
			//����һ��Car���ϴ�ŷ���Ҫ��ĳ�����
			ArrayList<Car> cars = new ArrayList<Car>();
			//��������ʱ���ѯ���ϵĳ����󼯺�
			long startMillisecond = startTime.getTimeInMillis();
			long endMillisecond = endTime.getTimeInMillis();
			cars = carDao.findCarsByTime(startMillisecond, endMillisecond);
			//��ʾ����Ҫ��ĳ����ϵ���Ϣ
			showSearchCars(cars, user , 2 ,searchPage , 1);
			break;
		case 2:
			searchByTime(user);
			break;
		default:
			System.out.println("��Ч���룬���������룡");
			countersign(startTime, endTime, user);
			break;
		}
	}
	/**
	 * ����Ʒ����������
	 */
	public void searchByBrand(User user){
		System.out.println("��ѡ��Ҫ����������Ʒ�ƣ�");
		int i = 1;
		for(String brond : carDao.getBronds()){
			System.out.print(i+"."+brond+"\t");
			i++;
		}
		System.out.println();
		String brand =carDao.getBronds().get(chooseBrond(carDao.getBronds())-1);
		ArrayList<Car> cars = new ArrayList<Car>();
		cars = carDao.findCarByBrond(brand);
		//��ʾ����Ҫ��ĳ����ϵ���Ϣ
		showSearchCars(cars, user , 2 ,searchPage , 2);
	}
	
	/**
	 * ѡ��Ʒ��
	 * @param bronds
	 * @return
	 */
	public int chooseBrond(ArrayList<String> bronds){
		int chooise = Utils.getNum();
		if(chooise < 1 || chooise > bronds.size()){
			System.out.println("��Чѡ��������ѡ��");
			chooise = chooseBrond(bronds);
		}
		return chooise;
	}
	/**
	 * ���ݼ�λ��Χ��������
	 */
	public void searchByPrice(User user){
		System.out.println("������Ҫ���������ļ�λ��Ϣ��");
		System.out.print("��߼۸�");
		int maxPrice = Utils.getNum();
		System.out.print("��ͼ۸�");
		int minPrice = Utils.getNum();
		ArrayList<Car> cars = new ArrayList<Car>();
		cars = carDao.findCarByPrice(maxPrice, minPrice);
		//��ʾ����Ҫ��ĳ����ϵ���Ϣ
		showSearchCars(cars, user , 2 , searchPage , 3);
	}
	
	/**
	 * ��ҳ��ʾ�������
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
	 * �ҵ��ղ�
	 */
	public void showMyCollect(User user){
		if(collectDao.getCollectCars(user).size() == 0){
			System.out.println("�ף�����û�ղع�����������Ӻ������鿴");
			mainMenu(user);
			return;
		}
		showCars(collectDao.getCollectCars(user));
		System.out.println("���������������һ����");
		Utils.getStr();
		mainMenu(user);
	}
	/**
	 * �˳�ϵͳ
	 */
	public void outSystem(){
		System.out.println("���ѳɹ��˳�ϵͳ��");
		System.exit(0);
	}
	/**
	 * ��ʾ�����󼯺���ϸ��Ϣ
	 */
	public  void showCars(ArrayList<Car> cars){
		System.out.println("���\tƷ��\t�ͺ�\t�����\t������\t�۸�\t����ʱ��");
		for(int i = 0 ; i < cars.size() ; i++){
			System.out.println(i+1+"\t"+cars.get(i).getBrand()+"\t"+cars.get(i).getModel()+"\t"+cars.get(i).getClutch()+"\t"+cars.get(i).getKilometres()+"\t"+cars.get(i).getPrice()+"\t"+cars.get(i).showTime());
		}
	}
	/**
	 * ��ʾһ��������ϸ��Ϣ
	 * @param car
	 */
	public  void showCar(Car car){
		System.out.println("---------------------������ϸ��Ϣ------------------------------");
		System.out.println("Ʒ��\t�ͺ�\t�����\t������\t�۸�\t����ʱ��");
		System.out.println(car.getBrand()+"\t"+car.getModel()+"\t"+car.getClutch()+"\t"+car.getKilometres()+"\t"+car.getPrice()+"\t"+car.showTime());
	}
	
	/**
	 * ��ʾ��ҳ�����󼯺ϵĻ�����Ϣ
	 * @param cars
	 * @param page
	 * @param allPage
	 * @param searchType 0���¶��ֳ� 1������ʱ������ 2��Ʒ������ 3����λ����
	 */
	 
	public  void showCarsBasicInfo(ArrayList<Car> cars , int page , int allPage , int searchType){
		int i = 1;
		if(cars.size() == 0){
			System.out.println("û�з���Ҫ��ĳ��������������������棡");
			return;
		}
		if(searchType == 0){
			System.out.println("���\t\tƷ��\t\t�ͺ�\t\t�����\t\t����ʱ��");
			for(Car car : cars){
				System.out.println(i+"\t\t"+car.getBrand()+"\t\t"+car.getModel()+"\t\t"+car.getClutch()+"\t\t"+car.showLoginTime());
				i++;
			}
		}
		if(searchType == 1){
			System.out.println("���\t\tƷ��\t\t�ͺ�\t\t�����\t\t\t\t����ʱ��ʱ��");
			for(Car car : cars){
				System.out.println(i+"\t\t"+car.getBrand()+"\t\t"+car.getModel()+"\t\t"+car.getClutch()+"\t\t"+car.showTime());
				i++;
			}
		}
		if(searchType == 2){
			System.out.println("���\t\tƷ��\t\t�ͺ�\t\t�����");
			for(Car car : cars){
				System.out.println(i+"\t\t"+car.getBrand()+"\t\t"+car.getModel()+"\t\t"+car.getClutch());
				i++;
			}
		}
		if(searchType == 3){
			System.out.println("���\t\tƷ��\t\t�ͺ�\t\t�����\t\t�۸�");
			for(Car car : cars){
				System.out.println(i+"\t\t"+car.getBrand()+"\t\t"+car.getModel()+"\t\t"+car.getClutch()+"\t\t"+car.getPrice());
				i++;
			}
		}
		System.out.println("\t\t\t\t\t-"+page+"-");
		System.out.println();
		if(allPage == 1){
			System.out.println("\t\t\t\t0.������һ��");
			return;
		}
		if(page == 1){
			System.out.println("\t\t\t\t\t9.��һҳ\t\t0.������һ��");
		}else if(page == allPage){
			System.out.println("\t\t\t8.��һҳ\t\t\t\t0.������һ��");
		}else{
			System.out.println("\t\t\t8.��һҳ\t\t9.��һҳ\t\t0.������һ��");
		}
	}
}
