package com.example.first_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.first_spring.VO.EmpVO;
import com.example.first_spring.VO.UserVO;
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
	public EmpVO getMonthDec(String month){
//		List<EmpVO> list = new ArrayList<EmpVO>();
		int count =0;
		count++;
		int max = 0;
		for(int i=0; i<count; i++) {
			int sal = empMapper.getMonthDec(month).getSal();
			if(sal > max) {
				max = sal;
					
			}
		}
		return empMapper.getMonthDec(month);
	}
	
	//문제4번
	public List<EmpVO> getFirstHiredate(String job){
		return empMapper.getFirstHiredate(job);
	}
	
	//문제5번
	public EmpVO getInfo(int empno) {
		return empMapper.getInfo(empno);
	}
	@Transactional(rollbackFor = {Exception.class})
	public int setEmp(EmpVO vo) {
		int rows = empMapper.insertEmp(vo);//몇행 insert 되었는지 리턴
		return rows;
	}
	@Transactional(rollbackFor = {Exception.class})
	public int getEmpRemoveCount(int empno) {
		int rows = empMapper.deleteEmp(empno);//몇행 delete 되었는지 리턴
		return rows;
	}
	
	//@Transactional(rollbackFor = {Exception.class}) : 에러나면 롤백해줘~
	//Exception : 에러를 잡겠다.
	@Transactional(rollbackFor = {Exception.class})
	public int getEmpUpdateCount(EmpVO vo) {
		int rows = empMapper.updateEmp(vo);//몇행 update되었는지 리턴
		
//		UserVO user = null;
//		String name = user.getName();
//		System.out.println(name);
		
		return rows;
	}
}
