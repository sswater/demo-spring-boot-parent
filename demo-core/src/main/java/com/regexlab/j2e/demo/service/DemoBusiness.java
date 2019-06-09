package com.regexlab.j2e.demo.service;

import com.regexlab.j2e.demo.dao.AaaDao;
import com.regexlab.j2e.demo.dao.BbbDao;

/*
 * this class is the real business class, to be encrypted
 * 
 * any annotation of spring will not work because of encryption
 */
public class DemoBusiness {

	protected AaaDao aaaDao;
	protected BbbDao bbbDao;
	
	/*
	 * business code using mapper class
	 */
	public String getNow() {
		return aaaDao.now();
	}
	
	/*
	 * business code using mapper class
	 */
	public Object getAll() {
		return bbbDao.loadAll();
	}
	
}
