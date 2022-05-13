package com.example.first_spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public List<EmpVO> getMonthDec(String month){
		List<EmpVO> list = new ArrayList<EmpVO>();
		list = empMapper.getMonthDec(month);
		int max = 0;
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getSal() > max) {
				max = list.get(i).getSal();
			}
			if(i>0) {
				list.remove(i-1);
				i--;
			}
		}
		return list;
	}
	
	//문제4번
	public List<EmpVO> getFirstHiredate(String job){
		List<EmpVO> list = empMapper.getFirstHiredate(job);
		int min = 0;
		String temp = list.get(0).getHiredate().replace("-", "");
		int date2 = Integer.parseInt(temp);
		min = date2;
		for(int i=0; i<list.size();i++) {
			String hiredate = list.get(i).getHiredate().replace("-", "");
			int date = Integer.parseInt(hiredate);
			if(min>date) {
				min = date;
				if(i>0) {
				list.remove(i-1);
				i--;
				}
			}
			if(min < date) {
				list.remove(i);
				i--;
			}
		}
		return list;
	}
	
	//문제5번
	public EmpVO getInfo(int empno) {
		return empMapper.getInfo(empno);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public int setEmp(EmpVO vo) {
		//emp에 없는 부서번호를 찾아서 해당 부서 번호로 insert 되었는지 리턴
//		EmpVO empVO = empMapper.insertEmp();
//		int deptNo = empVO.getDeptno();
//		vo.setDeptno(deptNo);
		
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
	//문제 1번
	public int getEmpDeptno(EmpVO empVO) {
		EmpVO vo = empMapper.selectDeptno();
		int deptNo = vo.getDeptno();
		empVO.setDeptno(deptNo);
		
		int rows = empMapper.allEmp(empVO);
		return rows;
	}
	//문제 2번
	public int EmpSalRemove(int empno) {
		List<EmpVO> list = empMapper.getEmpList();
		for(int i=0; i<list.size();i++) {
			EmpVO vo = list.get(i);
			if(vo.getEmpno() == empno && vo.getSal() < 3000) {
				return 0;
			}
		}
		int rows = empMapper.RemoveSal(empno);
		return rows;
	}
	//문제 3번
	public int getCountName(String search) {
		List<EmpVO> list = empMapper.CountName(search);
		int count =0;
		for(int i=0; i<list.size();i++) {
				count++;
			}
		return count;
	}
	// mybatis-if문
	public List<EmpVO> getEmpIsMgrList(String isMgr){
		return empMapper.selectEmpMgr(isMgr);
	}
	//문제 1번
	public int updateEmpJobSal(EmpVO vo, int empno){
		vo.setEmpno(empno);
		return empMapper.updateEmpJobSal(vo);
	}
	//문제 2번
	@Transactional(rollbackFor = {Exception.class})
	public int getEmpUpdateSalCount(int empno) {
		
		//Comm이 0이거나 NULL 이면
		EmpVO vo = empMapper.selectEmpCommSal(empno);
		
		int comm = vo.getComm();
		
		if(comm == 0) {
			int bonus = 500;
			int sal = vo.getSal();
			vo.setSal(sal+bonus);
			//update로직 추가
			return empMapper.updateEmpSal(vo);
		}
		return 0;
	}
	//Test
	//문제 1번
	public int TestDeptno(EmpVO empVO) {
		EmpVO vo = empMapper.selectDeptno();
		int deptno = vo.getDeptno();
		empVO.setDeptno(deptno);
		int rows = empMapper.allEmp(empVO);
		return rows;
	}
	//문제 2번
	public int TestDelete(int empno) {
		List<EmpVO> list = empMapper.getEmpList();
		for(int i=0; i<list.size();i++) {
			EmpVO vo = list.get(i);
			if(vo.getEmpno() == empno & vo.getSal()<3000) {
				return 0;
			}
		}
		int rows = empMapper.RemoveSal(empno);
		return rows;
	}
	//문제 3번
	public int TestCount(String find) {
		List<EmpVO> list = empMapper.CountName(find);
		int count =0;
		for(int i=0; i<list.size(); i++) {
			count++;
		}
		return count;
	}
	//문제) 직업과 급여 변경하기
	public int TestEmpJobSal(int empno, EmpVO empVO) {
		empVO.setEmpno(empno);
		return empMapper.TestEmpSalJob(empVO);
	}
	//문제) comm이 0이라면 급여 500 더하기
	public int TestEmpComm(int empno) {
		EmpVO vo = empMapper.TestSelectEmpComm(empno);
		int comm = vo.getComm();
		if(comm ==0) {
			int bonus = 500;
			int sal = vo.getSal();
			int sum = bonus+sal;
			vo.setSal(sum);
			return empMapper.updateEmpSal(vo);
		}
		return 0;
	}
	//Map
	public List<Map<String,Object>> getEmpMapList(){
		return empMapper.selectEmpMapList();
	}
	//MapTest
	public List<Map<String,Object>> TestGetMap(){
		return empMapper.TestMapList();
	}
	//HTML에서 수정하기
	public int getApi(int empno, EmpVO vo) {
		vo.setEmpno(empno);
		return empMapper.apiUpdate(vo);
	}
}
