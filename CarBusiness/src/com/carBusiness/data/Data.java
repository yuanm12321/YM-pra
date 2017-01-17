package com.carBusiness.data;

import java.util.ArrayList;
import java.util.HashMap;

import com.carBusiness.bean.Car;
import com.carBusiness.bean.User;

/**
 * ��װ�������˵ײ�����
 * @author Administrator
 *
 */
public class Data {
	//�û���Ϣ
	private ArrayList<User> users;

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<User> getUsers() {
		return users;
	}
	
	//������Ϣ
	private ArrayList<Car> cars;
	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public ArrayList<Car> getCars() {
		return cars;
	}
	//ÿ���û����ղس����ļ�ֵ��
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
		//��ӳ�ʼ����
		users.add(new User("1", "1"));
		users.get(0).setId(users.size());
		cars.add(new Car("����", "X1","�Զ�",56000, 170000, 2012, 6, 8, 2014, 2, 26));
		cars.add(new Car("����", "POLO", "�ֶ�", 110000, 40000, 2009, 12, 3, 2010, 4, 9));
		cars.add(new Car("ѩ����", "��³��", "�Զ�", 150000, 58000, 2005, 11, 28, 2012, 2, 12));
		cars.add(new Car("ѩ����", "����", "�ֶ�", 1050000, 60000, 2010, 4, 8, 2013, 7, 9));
		cars.add(new Car("ѩ����", "��ŷ", "�ֶ�", 49000, 40000, 1999, 7, 21, 2013, 8, 30));
		cars.add(new Car("����", "������", "˫���",13000, 215000, 2010, 9, 30, 2014, 5, 6));
		cars.add(new Car("����", "������", "˫���", 62000, 330000, 2012, 4, 30, 2013, 9, 13));
		cars.add(new Car("����", "����", "�ֶ�", 25000, 131000, 2014, 8, 1, 2014, 12, 25));
		cars.add(new Car("����", "����", "�ֶ�", 39200, 74800, 2010, 10, 1, 2015, 1, 20));
		cars.add(new Car("����", "CC", "˫���", 38300, 230000, 2014, 4, 25, 2015, 4, 30));
		cars.add(new Car("����", "�׿ǳ�", "�Զ�", 27000, 112000, 2010, 4, 10, 2013, 1, 26));
		cars.add(new Car("����", "X5", "�Զ�", 8200, 730000, 2015, 4, 30, 2015, 7, 4));
		cars.add(new Car("����", "X6", "�Զ�", 50000, 570000, 2012, 6, 1, 2014, 7, 9));
		cars.add(new Car("����", "����", "�Զ�", 25000, 420000, 2012, 9, 12, 2014, 9, 9));
		cars.add(new Car("����", "GLK", "�Զ�", 30000, 350000, 2013, 5, 6, 2015, 4, 24));
		cars.add(new Car("����", "CLS", "�Զ�", 17200, 560000, 2012, 11, 10, 2015, 1, 20));
		cars.add(new Car("���", "GL8", "�Զ�", 13500, 330000, 2015, 4, 1, 2015, 8, 30));
		cars.add(new Car("���", "��Խ", "�ֶ�", 47000, 52000, 2010, 5, 1, 2014, 3, 29));
		cars.add(new Car("���", "����", "�Զ�", 68500, 120000, 2010, 9, 30, 2014, 12, 11));
		cars.add(new Car("���", "��Խ", "�ֶ�", 43000, 50000, 2012, 9, 10, 2013, 11, 27));
		cars.add(new Car("���", "Ӣ��", "�Զ�", 14000, 100000, 2014, 2, 28, 2015, 7, 4));
		cars.add(new Car("����", "�Ÿ�", "�Զ�", 10000, 166000, 2014, 4, 10, 2015, 6, 1));
		cars.add(new Car("����", "˼��", "�Զ�", 9000, 82000, 2009, 3, 12, 2013, 1, 8));
		cars.add(new Car("����", "����", "�Զ�", 19000, 90000, 2013, 8, 9, 2014, 10, 1));
		cars.add(new Car("����", "5ϵ", "�Զ�", 18000, 130000, 2011, 6, 1, 2014, 7, 1));
		//���ղ�����ӵ�һ���û��������Ӧ���ղ�
		collect.put(users.get(0), new ArrayList<Car>());
		//��һ���û����ղس�����
		collect.get(users.get(0)).add(cars.get(0));
		collect.get(users.get(0)).add(cars.get(15));
		collect.get(users.get(0)).add(cars.get(19));
		collect.get(users.get(0)).add(cars.get(8));
		collect.get(users.get(0)).add(cars.get(22));
	}

	

	
}
