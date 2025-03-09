import fetch from 'node-fetch'

// 책의 원본 소스
export const fetchJokes = <T>() => new Promise<T>( (resolve, reject) => {
  // const jokeUrl = 'https://api.icndb.com/jokes/random/5?limitTo=[nerdy]'
  const jokeUrl = 'https://jsonplaceholder.typicode.com/posts'
  
  fetch(jokeUrl)
    .then(res => res.json())
    .then( (fetchResult: unknown) => resolve(fetchResult as T))
    .catch((e: Error) => reject(e))
})

// jokeUrl이 반환하는 json데이터에 맞게 수정한 소스 by deepseek
export const fetchJokes1 = <T>() => new Promise<T>( (resolve, reject) => {
  const jokeUrl = 'https://jsonplaceholder.typicode.com/posts'
  
  fetch(jokeUrl)
    .then(res => res.json())
    .then( (fetchResult: unknown) => {
      // FetchResult 타입에 맞게 데이터 변환
      const transformedResult = {
        type: "success",
        value: (fetchResult as Array<{ userId: number, id: number, title: string, body: string }>).map(post => ({
          id: post.id,
          title: post.title,
          body: post.body.split('\n') // body를 문자열 배열로 변환
        }))
      };
      resolve(transformedResult as T);
    })
    .catch((e: Error) => reject(e))
})