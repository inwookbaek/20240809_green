// 1. interface
interface IPerson {
  name: string;
  age: number;
}
let good: IPerson = { name: "Jack", age: 32 };
// let bad1: IPerson = {name: "Jack"}; // age 속성이 없으므로 오류
// let bad2: IPerson = {age: 32}; // name 속성이 없으므로 오류
// let bad3: IPerson = {}; // name 과 age 속성이 모두 없으므로 오류
// let bad4: IPerson = { name: "Jack", age: 32, etc: true}; // etc란 속성이 있으므로 오류

// 2.선택속성 (?)
interface IPerson2 {
  name: string;
  age: number;
  etc?: boolean;
}

let good1: IPerson2 = { name: "Jack", age: 32 };
// let bad1: IPerson2 = {name: "Jack"}; // age 속성이 없으므로 오류
// let bad2: IPerson2 = {age: 32}; // name 속성이 없으므로 오류
// let bad3: IPerson2 = {}; // name 과 age 속성이 모두 없으므로 오류
let good2: IPerson2 = { name: "Jack", age: 32, etc: true }; // etc란 속성이 있으므로 오류

// 3. ai 익명인터페이스  (익명 인터페이스는 한번만 사용할 때)
let ai: {
  name: string;
  age: number;
  etc?: boolean;
} = { name: "Jack", age: 32 };

function printMe(me: { name: string; age: number; etc?: boolean }) {
  console.log(
    me.etc ? `${me.name} ${me.age} ${me.etc}` : `${me.name} ${me.age}`
  );
}
printMe(ai);
