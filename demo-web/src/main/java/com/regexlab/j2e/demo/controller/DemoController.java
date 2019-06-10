package com.regexlab.j2e.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.regexlab.j2e.demo.service.DemoService;

/*
 * controller is not encrypted because we need to use annotation
 * 
 * if one controller needs to be encrypted,
 * we can place a parent class in 'demo-core' project like 'DemoService' does
 */
@Controller
public class DemoController {

	@Autowired
	DemoService demoService;
	
	@RequestMapping("getNow")
	public String getNow(Model m) {
		m.addAttribute("now", demoService.getNow());
		return "now-page";
	}
	
	@ResponseBody
	@RequestMapping("getAll")
	public Object getAll() {
		return demoService.getAll();
	}
	
}
