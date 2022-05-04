package com.example.first_spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.first_spring.VO.UserVO;
import com.example.first_spring.service.MainService;

@RestController
public class ActorController {
	@Autowired
	private MainService service;
	
	//문제 1. 나이가 60 이하인 배우만 리턴하시오.
	@GetMapping("/actor/list")
	public List<UserVO> callList(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null) {
			ip = request.getRemoteAddr();
		}
		System.out.println("IP ======> "+ip);
		return service.getUserList();
	}
	//문제 2. 나이가 가장많은 배우의 나이를 리턴하시오.
	@GetMapping("/actor/age")
	public int callAge(){
		return service.getUserAgeMax();
	}
	//문제 3. list에 배우이름 배성우가 있다면 리턴하시오.
	@GetMapping("/actor")
	public UserVO callActor() {
		return service.getUser();
	}

	//문제 4. list에 성이 이씨인 배우가 몇명인지 리턴하시오.
	@GetMapping("/actor/count")
	public int loadCount() {
		return service.getUserCount();
	}
}
