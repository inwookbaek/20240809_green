import {getFileNameAndNumber} from './utils'
import {csvFileReaderGenerator} from './csv/csvFileReaderGenerator'

const [filename] = getFileNameAndNumber('./data/fake-100000.csv', 1)

let line = 1
for(let object of csvFileReaderGenerator(filename)) {
  console.log(`[${line++}] ${JSON.stringify(object)}`)
}