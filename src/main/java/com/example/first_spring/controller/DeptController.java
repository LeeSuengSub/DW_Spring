package com.example.first_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.first_spring.VO.DeptVO;
import com.example.first_spring.service.DeptService;

@RestController
public class DeptController {
		@Autowired
		private DeptService deptservice;
		
		@GetMapping("/dept")
		public List<DeptVO> callDname(){
			return deptservice.getDept();
		}
		//연습
		@PostMapping("/dept")
		public int InsertDept(@RequestBody DeptVO deptvo) {
			return deptservice.InsertDept(deptvo);
		}
		@DeleteMapping("/dept/delete/{deptno}")
		public int DeleteDept(@PathVariable ("deptno") int deptno) {
			return deptservice.DeleteDept(deptno);
		}
		@PatchMapping("/dept")
		public int UpdateDept(@RequestBody DeptVO deptvo) {
			return deptservice.UpdateDept(deptvo);
		}
}
