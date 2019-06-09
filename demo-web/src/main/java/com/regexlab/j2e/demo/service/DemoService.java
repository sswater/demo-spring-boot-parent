package com.regexlab.j2e.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.regexlab.j2e.demo.dao.AaaDao;
import com.regexlab.j2e.demo.dao.BbbDao;

/*
 * this class is not encrypted, managed by spring and accepting injection
 * 
 * the real business code is in super class
 */
@Service
public class DemoService extends DemoBusiness {

	@Autowired
	void setAaaDao(AaaDao aaaDao) {
		this.aaaDao = aaaDao;
	}

	@Autowired
	void setBbbDao(BbbDao bbbDao) {
		this.bbbDao = bbbDao;
	}

}
