package com.ham.app.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service// 초기화 진행
@Aspect // 포인트컷과 어드바이스를 결합해주는 어노테이션
public class LogAdvice {

	// pointCut설정
	@Pointcut("execution(* com.ham.app..*Impl.*(..))")
	public void aPointCut() { } //참조메서드
	@Pointcut("execution(* com.ham.app..*Impl.get*(..))")
	public void bPointCut() { } //참조메서드

	// 호출된 비즈니스 메서드와 사용된 인자 확인 가능
	@Before("aPointCut()") 
	public void beforeLog(JoinPoint jp) { // 어노테이션의 활용으로, 메서드명이 바뀌어도 따로 수정할 사항이 없게 된다.
		//jp: 어떤 핵심관심이 호출되었는지에 대한 정보가 담겨있음
		String methodName=jp.getSignature().getName();//메서드 이름을 호출
		System.out.println("호출된 핵심관심: "+methodName);

		Object[] args=jp.getArgs();//사용된 인자
		System.out.print("사용된인자: ");
		for(Object v:args) {
			System.out.println(v);
		}
	}
	
	// 호출된 비즈니스 메서드와 반환된 output 확인 가능
	@AfterReturning(value = "bPointCut()", returning = "returnObj")
	public void afterLog(JoinPoint jp,Object returnObj) {
		System.out.println("반환값: "+returnObj);
	}

}
