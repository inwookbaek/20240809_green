import axios from "axios";
import { SignInRequestDto, SignUpRequestDto } from "./request/auth";
import { SignInResponseDto, SignUpResponseDto } from "./response/auth";
import { ResponseDto } from "./response";

// .env 파일에서 포트 번호를 가져옴
const PORT = process.env.REACT_APP_API_PORT || '8090'; // 기본값 설정 (옵션)
const DOMAIN = `http://localhost:${PORT}`;


const API_DOMAIN = `${DOMAIN}/api/v1`;

const SIGN_IN_URL = () => `${API_DOMAIN}/auth/sign-in`;
const SIGN_UP_URL = () => `${API_DOMAIN}/auth/sign-up`;

export const signInRequest = async (requestBody: SignInRequestDto) => {
  const result = await axios.post(SIGN_IN_URL(), requestBody)
    .then(response => { 
      const responseBody: SignInResponseDto = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response.data) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
  return result;
}

export const signUpRequest = async (requestBody: SignUpRequestDto) => {
  const result = await axios.post(SIGN_UP_URL(), requestBody)
    .then(response => {
      const responseBody: SignUpResponseDto = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response.data) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
    return result; 
}