package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessengerAspect {

	//return type은 void이고 send로 시작하는 모든 메소드가 point cut이다.
	//@Around로 aspect를 적용하면 메소드의 인자로 ProceedingJoinPoint type이 전달된다.
	@Around("execution(void send*(..))")
	public void checkGreeting(ProceedingJoinPoint joinPoint) throws Throwable {
		//메소드에 전달된 인자들 목록을 얻어내기
		Object[] args = joinPoint.getArgs();
		//반복문 돌면서 원하는 type의 값을 찾아서 작업
		for(Object tmp:args) {
			//String type이면
			if(tmp instanceof String) {
				//원래 type으로 casting
				String msg = (String)tmp;
				System.out.println("aspect에서 읽어낸 내용: "+msg);
				if(msg.contains("바보")) {
					System.out.println("바보는 금지된 단어 입니다.");
					return; //메소드를 끝내기
				}
			}
		}
		//aspect가 적용된 메소드가 호출되기 직전에 할 작업
		joinPoint.proceed(); //aspect가 적용된 메소드 수행하기
		
		//aspect가 적용된 메소드가 리턴된 직후에 할 작업
		System.out.println("aspect가 적용된 메소드가 리턴 됐습니다.");
	}
	
	/*
	 * return type 은 String 이고 
	 * get 으로 시작은 메소드 이고 
	 * 메소드에 전달되는 인자는 없다 
	 * java.lang 페키지에 있는 type 은 페키지명 생략 가능
	 */
	@Around("execution(String com.example.demo.util.*.get*())")
	public Object checkReturn(ProceedingJoinPoint joinPoint) throws Throwable {

		// aspect 가 적용된 메소드를 수행하고 리턴되는 데이터 받아오기
		Object obj = joinPoint.proceed();
		
		// 원래 type 으로 casting 해서 조사해 볼수가 있다.
		String a = (String)obj;

		// 조사후 아예 다른 값을 리턴해 줄수도 있다.
		return "놀자놀자!";
	}
}
