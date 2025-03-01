/*
  ECMA2015이전까지는 기본적으로 서버에서 자료를 가져올 때
  XMLHttpRequest객체를 사용했고 ECMA2015이후에는 feth API를
  사용하고 있다.

  JavaScript에서 네트워크통신을 할때 서버와 비동기식통신을
  하는 방법은 AJAX(Asynchronous JavaScript XML)방법으로 통신을
  한다. 

  즉, 비동기방식으로 서버와 통신을 하게 되는데 비동기란? 동시에
  일어나지 않는다는 의미이다. 서버에 자료를 용청하고 수신이 완료
  될 때 까지 기다리는 것이 아니라 자료를 수신하는 동안 다른 작업
  을 수행할 수 있다는 것을 말한다.

  초창기에는 JavaScript로 데이터를 송수신할 때 XML을 사용해지만
  현재는 JSON형태로 자료를 송수신한다.

  A. XMLHttpRequset의 메서드

  1. open(방식[get|post], url, 비동기여부)
    ... 방식은 HTTP의 요청방식을 의미 GET, POST, PUT, DELETE..., 대문자로 정의
    ... url : 요청한 서버의 url 주소
    ... 비동기여부 : true, false

  2. send(data) - 서버로 사용자의 요청을 보내는 메서드

  3. 기타 method
    a. setRequestHeader(header, value) : 요청할 때 특정값을 저장해서 
        요청하는 메서드 반드시 open()호출한 후에 실행해야 한다.
    b. getRequestHeader() : 서버의 응답중에 http헤더정보를 접근할 때
        사용하는 메서드
    c. getAllResponseHeaders() : http요청에 대한 응답정보를 리턴

  B. XMLHttpRequset의 속성

  1. readyState : XMLHttpRequset의 상태코드 0->1...->4->0의 순서로 반복
    0: 아직 아무것도 요청하지 않은 상태
    1: 서버에 요청이 성공한 상태
    2: 서버에 요청의 응답이 도착한 상태
    3: 서버에 자료가 로딩중인 상태
    4: 자료처리가 완료되어 프로그램에서 사용할 수 있는 상태

  2. status, statusText
    status : http의 상태코드
      a. 100 : 처리중
      b. 200 : 성공
      c. 300 : 리다이렉트
      d. 400 : 실패
      e. 500 : 서버에러
    statusText : state코드에 대한 상세 설명 메시지

  3. responseText
    ... 요청에 대한 응답이 문자열로 저장되는 속성

  C. XMLHttpRequset의 이벤트

  1. onreadystatechange : readyState가 변경될 때 마다 발생되는 이벤트
*/
let xhr = new XMLHttpRequest();
xhr.open('GET', './js/student.json');
xhr.send();

xhr.onreadystatechange = () => {
  console.log(`${xhr.readyState}, ${xhr.status}`);
  if(xhr.readyState === 4 && xhr.status === 200) {
    let students = JSON.parse(xhr.responseText);
    console.log(`${typeof xhr.responseText} , ${xhr.responseText}`);
    console.log(`${typeof students}, ${students[3].name}`);
    renderHTML(students);
  }
}

function renderHTML(students) {
  let output = ""
  // students.forEach(() -> {});
  for(let student of students) {
    output += `<h2>${student.name}</h2>
      <ul>
        <li>전공 : ${student.major}</li>
        <li>학년 : ${student.grade}</li>
      </ul><hr>`
  }
  document.querySelector('#result').innerHTML = output;
}