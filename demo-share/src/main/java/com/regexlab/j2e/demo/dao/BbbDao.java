package com.regexlab.j2e.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BbbDao {

	List<Map<String, Object>> loadAll();
	
}
