package com.example.first_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.first_spring.VO.DeptVO;
import com.example.first_spring.service.DeptService;

@RestController
public class DeptController {
		@Autowired
		private DeptService deptservice;
		
		@GetMapping("/dept")
		public DeptVO callDname(){
			return deptservice.getDept();
		}
}
