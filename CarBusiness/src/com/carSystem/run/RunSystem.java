package com.carSystem.run;

import com.carBusiness.data.Data;
import com.carBusiness.logic.CarSystem;

public class RunSystem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//�����ײ�����
		Data data = new Data();
		//�������ֳ�ϵͳ
		CarSystem sys = new CarSystem(data);
		//���ж��ֳ�ϵͳ
		sys.start();
	}

}
