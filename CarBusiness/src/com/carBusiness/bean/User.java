package com.carBusiness.bean;

/**
 * ��װ���û���������Ϣ
 * @author Administrator
 *
 */
public class User {
	//�û�Id
	private int id;
	//�û���
	private String userName;
	//�û�����
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
