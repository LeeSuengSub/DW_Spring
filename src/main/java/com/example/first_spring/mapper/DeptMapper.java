package com.example.first_spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.first_spring.VO.DeptVO;
@Mapper
public interface DeptMapper {
	
	public List<DeptVO> getDeptList();
	
	public int InsertDept(DeptVO deptvo);
	
	public int DeleteDept(int deptno);
	
	public int UpdateDept(DeptVO deptvo);
}
