export type FileNameAndNumber = [string, number]

export const getFileNameAndNumber = (defaultFileName: string, 
  defaultNumberOffFakeData: number): FileNameAndNumber => {
    const [bin, node, filename, numberOfFakeData] = process.argv
    return [filename || defaultFileName, 
      numberOfFakeData ? parseInt(numberOfFakeData, 10) : defaultNumberOffFakeData]
  }