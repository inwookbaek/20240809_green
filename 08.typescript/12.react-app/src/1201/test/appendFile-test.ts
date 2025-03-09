import * as fs from 'fs'
import { appendFile } from '../fileApi/appendFile'
import { mkdir } from '../fileApi/mkdir'

const appendTest = async(filename: string, data: any) => {
  const result = await appendFile(filename, data)
  console.log(`append ${result} to ${filename}`)
}

mkdir('./data')
  .then(s => appendTest('./data/hello.txt', 'Hi 홍길동???')
  .catch((e: Error) => console.log(e.message)))