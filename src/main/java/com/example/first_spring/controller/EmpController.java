package com.example.first_spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@CrossOrigin(origins = {"*"})
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
	public List<EmpVO> callMonthDec(@PathVariable("month") String month){
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
	@CrossOrigin(origins = {"*"})
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
	@CrossOrigin(origins = {"*"})
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
	public int callEmpDeptno(@RequestBody EmpVO empVO) {
		return empservice.getEmpDeptno(empVO);
	}
	//문제2번
	@DeleteMapping("/emp/emp/{empno}")
	public int callEmpSalremove(@PathVariable("empno") int empno) {
		return empservice.EmpSalRemove(empno);
	}
	//문제3번
	///emp/name?search=A
	@GetMapping("/emp/name/name")
	public int callEmpName(@RequestParam("search") String search) {
		return empservice.getCountName(search);
	}

	@GetMapping("/emp/mgr/{isMgr}")
	public List<EmpVO> callEmpIsMgrList(@PathVariable("isMgr") String isMgr){
		return empservice.getEmpIsMgrList(isMgr);
	}
	//문제1. 사원번호가 7902번인 사원 job을 salesman, sal을 3500으로 수정하시오.(update -> Patch)
	//선생님 답안
	@CrossOrigin(origins = {"*"})
	@PatchMapping("/emp/{empno}")
	public int callEmpSalUpdate(@PathVariable("empno") int empno, @RequestBody EmpVO empVO) {
		return empservice.updateEmpJobSal(empVO, empno);
	}
	//문제2. 사원번호가 7844번인 사원의 comm이 0이거나 null이면 기존 급여에서 500를 추가(수정)하시오. (update -> Patch)
	//ex) 3000 -> 3500
	@PatchMapping("/emp/empno/{empno}")
	public int callEmpSalUpdate(@PathVariable("empno") int empno) {
		return empservice.getEmpUpdateSalCount(empno);
	}
	//Test
	//문제1) dept와 연결된 emp에서 없는 부서번호를 찾아서 사원이 새로 insert 될 때 그 부서번호로 insert되게 만들기
	@PostMapping("/test/deptno")
	public int TestDeptno(@RequestBody EmpVO empVO) {
		return empservice.TestDeptno(empVO);
	}
	//문제2) 급여가 3000 이상이면 해고(3000이 안 되는 사원은 return 0)
	@DeleteMapping("/test/emp/{empno}")
	public int TestDelete(@PathVariable("empno") int empno) {
		return empservice.TestDelete(empno);
	}
	//문제3) 쿼리스트링으로 이름이 A로 시작하는 사람수(Count) 구하기
	@GetMapping("/test/emp/count")
	public int TestCount(@RequestParam("find")String find) {
		return empservice.getCountName(find);
	}
	@PatchMapping("/test/emp/empno/{empno}")
	public int TestEmpJobSal(@PathVariable("empno") int empno, @RequestBody EmpVO empVO) {
		return empservice.TestEmpJobSal(empno, empVO);
	}
	@PatchMapping("/test/emp/emp/{empno}")
	public int TestEmpComm(@PathVariable("empno") int empno) {
		return empservice.TestEmpComm(empno);
	}
	//Map
	@GetMapping("/emp/map/list")
	public List<Map<String,Object>> callEmpMapList(){
		return empservice.getEmpMapList();
	}
	//MapTest
	@GetMapping("/test/emp/map-list")
	public List<Map<String,Object>> TestMapList(){
		return empservice.TestGetMap();
	}
	//HTML에서 사원 수정하기
	@CrossOrigin(origins = {"*"})
	@PatchMapping("/api/v1/emp/{empno}")
	public int callApi(@PathVariable("empno")int empno, @RequestBody EmpVO empVO) {
		return empservice.getApi(empno, empVO);
	}
	
}
