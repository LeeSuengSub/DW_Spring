package com.example.first_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	//job이 manager이고, sal이 2500이상인 사람에게 comm 500주기
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
	//문제5번
	@GetMapping("/emp/empno/{empno}")
	public EmpVO callInfo(@PathVariable("empno") int empno) {
		return empservice.getInfo(empno);
	}
	//emp테이블에 insert해보기
	//PostMapping : 중요한 정보를 보내거나, 데이터를 보낼때 post사용
	//대표적인 EX) 회원가입
	//@RequestBody가 파라미터로 넘어오는 VO를 대신 new해줌
	//null로 나타나면 오타 확인.
	@PostMapping("/emp")
	public int callEmpSet(@RequestBody EmpVO empVO) {
		System.out.println("사원 번호는 "+empVO.getEmpno());
		System.out.println("사원 이름은 "+empVO.getEname());
		System.out.println("사원 부서번호는 "+empVO.getDeptno());
		System.out.println("사원 급여는 "+empVO.getSal());
		System.out.println("사원 직업은 "+empVO.getJob());
		return empservice.setEmp(empVO);
	}
	//@DeleteMapping : 자원 삭제할 때 사용.
	@DeleteMapping("/emp/empno/{empno}")
	public int callEmpRemove(@PathVariable("empno") int empno) {
		return empservice.getEmpRemoveCount(empno);
	}
	
	@PatchMapping("/emp")
	public int callEmpUpdate(@RequestBody EmpVO empVO) {
		return empservice.getEmpUpdateCount(empVO);
	}
	//쿼리스트링으로 GetMapping
	//검색할때 많이 사용
	//tier?region=kr
	//Ex1
	@GetMapping("/tier")
	public String calltier(@RequestParam("region") String region, @RequestParam("name")String name) {
		return region+", "+name;
	}
	
	//board?page=1&pageSize=10&writer=현상원
	@GetMapping("/board")
	public int callBoard(@RequestParam("page")int page, @RequestParam("pageSize")int pageSize,
			@RequestParam("writer") String writer) {
		System.out.println("현재 페이지는 "+page);
		System.out.println("한 페이지에 보여주는 rows "+ pageSize);
		System.out.println("작성자는 "+writer);
		return 0;
	}
	
	//문제1번.
	@PostMapping("/emp/deptno")
	public int callEmpDeptno(@RequestBody EmpVO empvo) {
		return empservice.getEmpDeptno(empvo);
	}
	
	
}
