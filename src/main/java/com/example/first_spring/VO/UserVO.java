package com.example.first_spring.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Getter
//@Setter
//getter,setter or Data : getter,setter 함수를 자동으로 만들어 줌
@Data //Data안에 getter, setter가 들어있다.
@AllArgsConstructor
public class UserVO {
	private String name;
	private int age;
	private String addr;
	
public UserVO() {}//에러때문에 만든 기본 생성자	
}
