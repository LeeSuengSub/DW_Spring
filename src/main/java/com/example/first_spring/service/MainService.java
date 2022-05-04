package com.example.first_spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.first_spring.VO.UserVO;

@Service
public class MainService {
	
//	public List<UserVO> getUserList(){
//		List<UserVO> list = new ArrayList<UserVO>();
//		list.add(new UserVO("이병헌",51,"서울"));
//		return list;
//	}
    //문제 1. 나이가 60 이하인 배우만 리턴하시오.
    public List<UserVO> getUserList(){
		List<UserVO> list = new ArrayList<UserVO>();
		list.add(new UserVO("이병헌",51,"서울"));
        list.add(new UserVO("조승우",42,"서울"));
        list.add(new UserVO("이경영",61,"서울"));
        list.add(new UserVO("배성우",49,"서울"));
        list.add(new UserVO("백윤식",75,"서울"));
        
        for(int i=0; i<list.size(); i++) {
        	if(list.get(i).getAge() >= 60) {
        		list.remove(i);
        		i--;//remove()가 되면서 앞으로 당겨지면 생략되는 식들이 있기 때문에
        	}
        }
		return list;
	}
    //문제 2. 나이가 가장많은 배우의 나이를 리턴하시오.
    public int getUserAgeMax(){
		List<UserVO> list = new ArrayList<UserVO>();
		list.add(new UserVO("이병헌",51,"서울"));
        list.add(new UserVO("조승우",42,"서울"));
        list.add(new UserVO("이경영",61,"서울"));
        list.add(new UserVO("배성우",49,"서울"));
        list.add(new UserVO("백윤식",75,"서울"));
        
        int age = 0;
        for(int i=0; i<list.size(); i++) {
        	if(list.get(i).getAge() > age) {
        		age = list.get(i).getAge();
        	}
        }
		return age;
	}
    //문제 3. list에 배우이름 배성우가 있다면 리턴하시오.
    public UserVO getUser(){
		List<UserVO> list = new ArrayList<UserVO>();
		list.add(new UserVO("이병헌",51,"서울"));
        list.add(new UserVO("조승우",42,"서울"));
        list.add(new UserVO("이경영",61,"서울"));
        list.add(new UserVO("배성우",49,"서울"));
        list.add(new UserVO("백윤식",75,"서울"));
        
        for(int i=0; i<list.size(); i++) {
        	if(list.get(i).getName().equals("배성우")) {
        		return list.get(i);
        	}
        }
		return null;
	}
    //문제 4. list에 성이 이씨인 배우가 몇명인지 리턴하시오.
    public int getUserCount(){
		List<UserVO> list = new ArrayList<UserVO>();
		list.add(new UserVO("이병헌",51,"서울"));
        list.add(new UserVO("조승우",42,"서울"));
        list.add(new UserVO("이경영",61,"서울"));
        list.add(new UserVO("배성우",49,"서울"));
        list.add(new UserVO("백윤식",75,"서울"));
        
        int count = 0;
        for(int i=0; i<list.size(); i++) {
        	String lastName = list.get(i).getName().substring(0,1);
        	if(lastName.equals("이")) {
        		++count;
        	}
        }
		return count;
	}
}
