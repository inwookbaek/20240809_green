// 1. 구조화
// 1) 구조화가 필요한 코드
let personName = "Jack";
let personAge = 32;

let companyName = "Apple";
let companyAge = 43;

// 2) 구조화된 코드
export interface IPerson {
  name: string;
  age: number;
}

export interface ICompany {
  name: string;
  age: number;
}

// 3) 비구조화
// import { IPerson, ICompany } from './IPerson_ICompany'

let jack: IPerson = { name: "Jack", age: 32 },
  jane: IPerson = { name: "Jack", age: 32 };
let apple: ICompany = { name: "Apple Computer, Inc", age: 43 },
  ms: ICompany = { name: "Microsoft", age: 44 };

let name = jack.name,
  age = jack.age;

// 4) 비구조화 할당
let { name: jackName, age: jackAge } = jack;
let jack2: IPerson = { name: jackName, age: jackAge };
console.log(jack2); // { name: 'Jack', age: 32 }

// 2. 잔여연산자
let address: any = {
  country: "Korea",
  city: "Seoul",
  address1: "Gangnam-gu",
  address2: "Sinsa-dong 123-456",
  address3: "789 street, 2 Floor ABC building",
};
const { country, city, ...detail } = address;
console.log(detail);

// 3. 전개연산자
let part1 = { name: "jane" },
  part2 = { age: 22 },
  part3 = { city: "Seoul", country: "Kr" };

let merged = { ...part1, ...part2, ...part3 };
console.log(merged); // { name: 'jane', age: 22, city: 'Seoul', country: 'Kr' }

let coord = { ...{ x: 0 }, ...{ y: 0 } };
console.log(coord); // {x:0, y: 0}

// 4. 타입단언(type assertion)
// 타입스크립트는 타입변환이 아니라 타입단언이라고 한다.
export default interface INameable {
  name: string;
}

let obj: object = { name: "Jack" };

// 아래 두 문장은 동일한 의미
let name1 = (<INameable>obj).name;
let name2 = (obj as INameable).name;
console.log(name1, name2); // Jack Jack

let person: object = { name: "Jack", age: 32 };
(<{ name: string }>person).name;
console.log((<{ name: string }>person).name);
