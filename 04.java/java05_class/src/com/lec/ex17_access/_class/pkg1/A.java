package com.lec.ex17_access._class.pkg1;

/*
	접근제한자는 public, private, protected, default 4가지가 있다.
	
	class의 접근제한자는 public과 default만 정의할 수 있다.
	class에 접근제한자가 아무것도 정의되지 않았다면 default로 선언한 것이다.
*/
public class A {
	
	B b = new B();
}

// 파일명과 다른 클래스를 동일파일에 선언할 경우에는 파일이름과 같은
// class만 public으로 선언이 가능하고 나머지 클래스는 default로 선언
// 해야 한다.
class X {}
class Y {}
class Z {}