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

}
