// https://www.typescriptlang.org/docs/handbook/basic-types.html
// 1. let, str
let a: number = 1;
console.log(a); // 1

let count = 10,
  message = "Your count";
let result = `${message} is ${count}`;
console.log(result);

// 2. any 타입은 모든 타입을 허용한다.
let b: any = 0;
b = "hello";
b = true;
b = {};
console.log(b); // {}
