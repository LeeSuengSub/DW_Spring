package com.example.first_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.first_spring.VO.EmpVO;
import com.example.first_spring.service.EmpService;

@RestController
public class EmpController {
	@Autowired
	private EmpService empservice;
	
	@GetMapping("/emp")
	public List<EmpVO> callEmpList(){
		return empservice.getAllempList();
	}
	@GetMapping("/emp/1")
	public EmpVO callEmp() {
		return empservice.getEmp();
	}
}
