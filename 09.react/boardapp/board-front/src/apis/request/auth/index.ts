import { ResponseCode } from "types/enum";
import SignUpRequestDto from "./sign-up.request.dto";

export default interface RequstDto {
  code: ResponseCode;
  message: String;
}