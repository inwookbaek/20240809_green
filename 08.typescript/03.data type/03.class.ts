/*
이 코드는 ts2564 오류가 납니다. 그리고 그 이유는 코드를 이런식으로 작성하지 말라는 의미입니다.
하지만, 이 코드는 교육용 이므로, 여기서는 @ts-nocheck 을 사용하여 
컴파일러로 하여금 너무 엄격하게 코드를 해석하지 않도록 했습니다.
*/
// @ts-nocheck
// 1. 클래스
class Person1 {
  name: string;
  age?: number;
}
let jack1: Person1 = new Person1();
jack1.name = "Jack";
jack1.age = 32;
console.log(jack1);

// 2. 생성자 함수
// 생성자매개변수에 public 선언을 하면 Person3와 같은 코드가 생성된다.
// 1) public 선언할 경우
class Person2 {
  constructor(public name: string, public age?: number) {}
}
let jack2: Person2 = new Person2("Jack", 32);
console.log(jack2);

// 2) public 선언없을 경우
class Person3 {
  name: string;
  age?: number;
  constructor(name: string, age?: number) {
    this.name = name;
    this.age = age;
  }
}
let jack3: Person3 = new Person3("Jack", 32);
console.log(jack3);

// 3. 인터페이스구현
interface IPerson4 {
  name: string;
  age?: number;
}

class Person4 implements IPerson4 {
  constructor(public name: string, public age?: number) {}
}
let jack4: IPerson4 = new Person4("Jack", 32);
console.log(jack4); // Person4 { name: 'Jack', age: 32 }

// 4. 추상클래스
abstract class AbstractPerson5 {
  abstract name: string;
  constructor(public age?: number) {}
}
class Person5 extends AbstractPerson5 {
  constructor(public name: string, age?: number) {
    super(age);
  }
}
let jack5: Person5 = new Person5("Jack", 32);
console.log(jack5); // Person5 { name: 'Jack', age: 32 }
