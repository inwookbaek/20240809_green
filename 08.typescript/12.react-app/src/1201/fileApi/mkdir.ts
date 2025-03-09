import { mkdirp } from 'mkdirp'
import { fileExists } from './fileExists'

export const mkdir = (dirname: string): Promise<string> =>
  new Promise(async (resolve, reject) => {
    try {
      const alreadyExists = await fileExists(dirname);
      if (alreadyExists) {
        resolve(dirname);
      } else {
        const createdDir = await mkdirp(dirname); // mkdirp가 반환하는 값을 저장
        resolve(createdDir || dirname); // `mkdirp`가 void일 경우 대비하여 dirname을 반환
      }
    } catch (error) {
      reject(error);
    }
  });


  
