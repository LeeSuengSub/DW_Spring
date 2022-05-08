package com.example.first_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/emp/no/{empNo}")//중괄호가 파라미터로 넘기겠다.
	public EmpVO callEmp(@PathVariable("empNo") int empNo) {
		return empservice.getEmp(empNo);
//		return empNo;
	}
	@GetMapping("/emp/name")
	public List<EmpVO> callName(){
		return empservice.getName();
	}
	@GetMapping("/emp/comm")
	public List<EmpVO> callComm(){
		return empservice.getComm();
	}
	@GetMapping("/emp/hiredate")
	public List<EmpVO> callHiredate(){
		return empservice.getHiredate();
	}
	@GetMapping("/emp/job/{jobName}/sal/{sal}")
	public List<EmpVO> callManager(@PathVariable("jobName") String job, @PathVariable("sal") int sal) {
		return empservice.getManager(job, sal);
	}
	//문제0번
	@GetMapping("/emp/sal/{sal}")
	public List<EmpVO> callOverSal(@PathVariable("sal") int sal){
		return empservice.getOverSal(sal);
	}
	//문제1번
	@GetMapping("/emp/mgr")
	public List<EmpVO> callMgrisNull(){
		return empservice.getMgrisNull();
	}
	//문제2번
	@GetMapping("/emp/hiredate/year/{year}")
	public List<EmpVO> call1987Hiredate(@PathVariable("year") String year){
		return empservice.getHiredate1987(year);
	}
	//문제3번
	@GetMapping("/emp/hiredate/month/{month}")
	public EmpVO callMonthDec(@PathVariable("month") String month){
		return empservice.getMonthDec(month);
	}
	//문제4번
	@GetMapping("/emp/job/{jobName}")
	public List<EmpVO> callFirstHiredate(@PathVariable("jobName") String job){
		return empservice.getFirstHiredate(job);
	}
}
