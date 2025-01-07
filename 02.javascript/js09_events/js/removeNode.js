/*
  for...in vs for...of

  1. for in : 객체의 모든 열거 가능한 속서에 대한 반복문
  2. for of : [Symbol.iterator]속성을 가진 컬렉션 전용 반복문
*/
Object.prototype.objCustom = function() {};
Array.prototype.arrCustom = function() {};

let iterable = [10,20,30];
iterable.foo = "Hello";

for(let key in iterable) {
  console.log(key);
}

for(let value of iterable) {
  console.log(value);
}



const items = document.querySelector('li');

// for(let item of items) {
//   item.addEventListener('click', function() {
//     // this는 이벤트가 발생한 노드를 의미
//     this.parentNode.removeChild(this);
//     // or this.remove(this);
//   })
// }

// addEventListener에서 화살표함수를 사용하면
// 화살표함수에서의 this는 window객체를 가리킨다.
// this를 사용하려면 화살표함수가 익명함수로
// 지정해야 한다. 따라서 아래로직에서는 에러가 
// 발생하기 때문에 상기로직처럼 익명함수로 사용
// 해야 한다.
for(let item of items) {
  item.addEventListener('click', () => {
    this.parentNode.removeChid(this);
    // or this.remove(this)
  })
}