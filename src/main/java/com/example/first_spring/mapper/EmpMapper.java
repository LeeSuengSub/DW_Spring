package com.example.first_spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.first_spring.VO.EmpVO;

@Mapper
public interface EmpMapper {
	
	/**
	 * @return
	 * commet : emp테이블 전체사원 조회
	 */
	public List<EmpVO> getEmpList();
	
	public EmpVO getEmp(int empNo);
	
	public List<EmpVO> getName();
	
	public List<EmpVO> getComm();
	
	public List<EmpVO> getHiredate();
	
	//mybatis에 데이터를 2개 이상 넘길 때는 @Param 이용
	public List<EmpVO> selectEmpWhereJobAndSal(
			@Param("job") String job,
			@Param("sal") int sal);
	//문제0번
	public List<EmpVO> getOverSal(int sal);
	//문제1번
	public List<EmpVO> getMgrisNull();
	//문제2번
	public List<EmpVO> getHiredate1987(String year);
	//문제3번
	public EmpVO getMonthDec(String month);
	//문제4번
	public List<EmpVO> getFirstHiredate(String job);
	//문제5번
	public EmpVO getInfo(int empno);
	//데이터 삽입
	public int insertEmp(EmpVO empVO);
	//데이터 삭제
	public int deleteEmp(int empno);
	//데이터 수정
	public int updateEmp(EmpVO empVO);
	//1번문제 풀이
	public int allEmp(EmpVO empVO);
	public EmpVO selectDeptno();
	//2번문제
	public int RemoveSal(int empno);
	//3번문제
	public List<EmpVO> CountName(String search);
	//mybatis-if문
	public List<EmpVO> selectEmpMgr(@Param("isMgr") String isMgr);
	//문제 1번
	public int updateEmpJobSal(EmpVO vo);
	//문제 2번
	public EmpVO selectEmpCommSal(@Param("empno") int empno);
	public int updateEmpSal(EmpVO vo);
	//TEST
	public int TestEmpSalJob(EmpVO empVO);
	public EmpVO TestSelectEmpComm(@Param("empno") int empno);
}
