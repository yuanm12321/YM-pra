package com.carBusiness.bean;

/**
 * 封装了用户的所有信息
 * @author Administrator
 *
 */
public class User {
	//用户Id
	private int id;
	//用户名
	private String userName;
	//用户密码
	private String passWord;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public User(String userName , String passWord){
		setUserName(userName);
		setPassWord(passWord);
	}
}
