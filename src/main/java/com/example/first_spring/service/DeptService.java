package com.example.first_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.first_spring.VO.DeptVO;
import com.example.first_spring.mapper.DeptMapper;

@Service
public class DeptService {

	@Autowired
	private DeptMapper deptmapper;
	
	public List<DeptVO> getDept(){
		return deptmapper.getDeptList();
	}
}
