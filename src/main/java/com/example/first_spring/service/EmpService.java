package com.example.first_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.first_spring.VO.EmpVO;
import com.example.first_spring.mapper.EmpMapper;

@Service
public class EmpService {

	@Autowired
	private EmpMapper empMapper;
	
	public List<EmpVO> getAllempList(){
		return empMapper.getEmpList();
	}
	public EmpVO getEmp(int empNo) {
		return empMapper.getEmp(empNo);
	}
	public List<EmpVO> getName(){
		return empMapper.getName();
	}
	public List<EmpVO> getComm(){
		return empMapper.getComm();
	}
	public List<EmpVO> getHiredate(){
		return empMapper.getHiredate();
	}
	public List<EmpVO> getManager(String job, int sal) {
		if(job.equals("salesman")) {
			return null;
		}
		return empMapper.selectEmpWhereJobAndSal(job, sal);
	}
	
	//문제0번
	public List<EmpVO> getOverSal(int sal){
		return empMapper.getOverSal(sal);
	}
	
	//문제1번
	public List<EmpVO> getMgrisNull(){
		return empMapper.getMgrisNull();
	}
	
	//문제2번
	public List<EmpVO> getHiredate1987(String year){
		int count=0;
		count++;
		if(count <=3) {
			year = "1981";
		}
		return empMapper.getHiredate1987(year);
	}
	
	//문제 3번
	public List<EmpVO> getMonthDec(String month){
		int max = 0;
		int temp = 0;
		for(int i=0; i<getAllempList().size();i++) {
			int sal = getAllempList().get(i).getSal();
			if(sal >= max) {
				max = sal;
				temp = i;
				return empMapper.getMonthDec(month);
			}
		}
		return null;
	}
	
	//문제4번
	public List<EmpVO> getFirstHiredate(String job){
		return empMapper.getFirstHiredate(job);
	}
}
