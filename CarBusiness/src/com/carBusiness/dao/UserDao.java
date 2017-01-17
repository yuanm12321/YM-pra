package com.carBusiness.dao;

import java.util.ArrayList;

import com.carBusiness.bean.*;
import com.carBusiness.data.Data;

/**
 * 分装了对于底层用户信息的增删改查
 * @author Administrator
 *
 */
public class UserDao {
	private Data data;
	public  UserDao(Data data){
		this.data = data;
	}
	
	/**
	 * 通过用户名查找用户信息
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
	 * 根据用户Id查找用户信息
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
	 * 添加用户信息
	 * @param user
	 */
	public void addUser(User user){
		data.getUsers().add(user);
		user.setId(data.getUsers().size());
		data.getCollect().put(user, new ArrayList<Car>());
	}
	
}
