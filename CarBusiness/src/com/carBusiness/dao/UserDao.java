package com.carBusiness.dao;

import java.util.ArrayList;

import com.carBusiness.bean.*;
import com.carBusiness.data.Data;

/**
 * ��װ�˶��ڵײ��û���Ϣ����ɾ�Ĳ�
 * @author Administrator
 *
 */
public class UserDao {
	private Data data;
	public  UserDao(Data data){
		this.data = data;
	}
	
	/**
	 * ͨ���û��������û���Ϣ
	 * @param UserName
	 * @return
	 */
	public User findUserByName(String userName){
		for(User user : data.getUsers()){
			if(user.getUserName().equals(userName)){
				return user;
			}
		}
		return null;
	}
	
	/**
	 * �����û�Id�����û���Ϣ
	 * @param id
	 * @return
	 */
	public User findUserById(int id){
		for(User user : data.getUsers()){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	/**
	 * ����û���Ϣ
	 * @param user
	 */
	public void addUser(User user){
		data.getUsers().add(user);
		user.setId(data.getUsers().size());
		data.getCollect().put(user, new ArrayList<Car>());
	}
	
}
