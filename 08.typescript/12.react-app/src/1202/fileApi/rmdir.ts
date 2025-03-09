import { rimraf } from "rimraf";
import { fileExists } from "./fileExists";

export const rmdir = (dirname: string): Promise<string> =>
  new Promise(async(resolve, reject) => {
    try {
      const alreadyExists = await fileExists(dirname)
      if(!alreadyExists) {
        resolve(dirname)
      } else {
        await rimraf(dirname);
        resolve(dirname);
      }
    } catch (error) {
      reject(error);
    }
  })