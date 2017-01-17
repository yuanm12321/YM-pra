package com.carSystem.run;

import com.carBusiness.data.Data;
import com.carBusiness.logic.CarSystem;

public class RunSystem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建底层数据
		Data data = new Data();
		//创建二手车系统
		CarSystem sys = new CarSystem(data);
		//运行二手车系统
		sys.start();
	}

}
