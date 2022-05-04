package com.example.first_spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.first_spring.VO.EmpVO;

@Mapper
public interface MainMapper {
	
	/**
	 * @return
	 * commet : emp테이블 전체사원 조회
	 */
	public List<EmpVO> getEmpList();
	
	public EmpVO getEmp();
}
