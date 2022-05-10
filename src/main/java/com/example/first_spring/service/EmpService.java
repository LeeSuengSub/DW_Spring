package com.example.first_spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(rollbackFor = Exception.class)
	public List<EmpVO> getManager(String job, int sal) {

		List<EmpVO> list = empMapper.selectEmpWhereJobAndSal(job, sal);
		int comm = 500; //커미션
		int rows = 0;//몇행인지 체크
		for(int i=0; i<list.size(); i++) {
			int empComm = list.get(i).getComm();//초기값은 null이므로 0
			int sum = empComm+comm;//0+500
			
			list.get(i).setComm(sum);
			EmpVO vo = list.get(i);//rows에 대입하려고 
			rows += empMapper.updateEmp(vo);//쿼리는 int로 반환함
		}
		if(rows > 0) {
			return empMapper.selectEmpWhereJobAndSal(job, sal);
		}
		return null;
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
		//emp에 없는 부서번호를 찾아서 해당 부서 번호로 insert 되었는지 리턴
		EmpVO empVO = empMapper.findDeptno();
		int deptNo = empVO.getDeptno();
		vo.setDeptno(deptNo);
		
		//1.insert 해야 함
		int rows = empMapper.insertEmp(vo);//몇행 insert 되었는지 리턴
		return rows;
	}
	@Transactional(rollbackFor = {Exception.class})
	public int getEmpRemoveCount(int empno) {
		//급여가 3000인 사람만 삭제
		//1. 급여 3000이상인 쿼리 작성
		//2. mapper 메소드 작성(리턴타입은 쿼리결과에 따라)
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

	public int getEmpSalRemove(int empno) {
		List<EmpVO> list = empMapper.getEmpList();
//		for문 돌려서 해당 vo의 empno가 파라미터와 같은지 , 그리고 sal이 3천보다 작은지 확인하는 if문
		for(int i=0; i<list.size(); i++) {
			EmpVO vo = list.get(i);
		if(list.get(i).getEmpno() == empno && list.get(i).getSal()<3000) {
			return 0;
		}
	}
		return empMapper.insertEmp(null);
	}
	
	public int getEmpDeptno(EmpVO empvo) {
		EmpVO empVO = empMapper.findDeptno();
		int deptNo = empVO.getDeptno();
		empvo.setDeptno(deptNo);
		
		int rows = empMapper.insertEmp(empvo);
		return rows;
	}
	
	public int EmpSalRemove(int empno) {
		List<EmpVO> list = empMapper.getEmpList();
		for(int i=0; i<list.size();i++) {
			EmpVO vo = list.get(i);
			if(vo.getEmpno() == empno && vo.getSal() > 3000) {
				return 0;
			}
		}
		int rows = empMapper.RemoveSal(empno);
		return rows;
	}
	public int getCountName(String search) {
		List<EmpVO> list = empMapper.CountName(search);
		int count =0;
		for(int i=0; i<list.size();i++) {
			EmpVO vo = list.get(i);
				count++;
			}
		return count;
	}
	
}
