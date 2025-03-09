import * as path from 'path'
import { writeFile } from '../fileApi/writeFile'
import { mkdir } from '../fileApi/mkdir'
import mkdirp = require('mkdirp')

const writeTest = async(filename: string, data: any) => {
  const result = await writeFile(filename, data);
  console.log(`write ${result} to ${filename}`)
}

// ✅ JSON.stringify(value, replacer, space) 파라미터 설명
// value	 : JSON 문자열로 변환할 객체
// replacer: 특정 속성을 필터링할 함수 또는 배열 (기본값: null, 즉 모든 속성 포함)
// space	 : JSON을 보기 쉽게 들여쓰기할 공백 또는 개행 문자 (기본값: 0, 즉 한 줄 출력)
mkdir('./data')
  .then(s => writeTest('./data/hello.txt', 'Hello World!!'))
  .then(s => writeTest('./data/test.json', JSON.stringify({name: '홍길동', age: 1000}, null, 2)))
  .catch((e: Error) => console.log(e.message))