import { rmdir } from '../fileApi/rmdir'

const deleteDataDIr = async (dir) => {
  const result = await rmdir(dir)
  console.log(`${result} dir deleted!!`)
}

deleteDataDIr('./data/today')