import axios from "axios";
import { ResponseDto } from "./response";
import { GetSignInUserResponseDto, GetUserResponseDto, PatchNicknameResponseDto, PatchProfileImageResponseDto } from "./response/user";
import { SignInRequestDto, SignUpRequestDto } from "./request/auth";
import { SignInResponseDto, SignUpResponseDto } from "./response/auth";
import { PatchBoardRequestDto, PostBoardRequestDto, PostCommentRequestDto } from "./request/board";
import { GetBoardResponseDto, GetCommentListResponseDto, GetFavoriteListResponseDto, PostBoardResponseDto, PostCommentResponseDto, 
  PutFavoriteResponseDto, IncreaseViewCountResponseDto, DeleteBoardResponseDto, PatchBoardResponseDto, GetLatestBoardListResponseDto, 
  GetTop3BoardListResponseDto, GetUserBoardListResponseDto } from "./response/board";
import { GetPopularListResponseDto, GetRelationListResponseDto } from "./response/search";
import GetSearchBoardListResponseDto from "./response/board/get-search-board-list.response.dto";
import { PatchNicknameRequestDto, PatchProfileImageRequestDto } from "./request/user";

// Configuration
const APP_ADDR = process.env.APP_API_ADDR || 'http://localhost';
const APP_PORT = process.env.APP_API_PORT || '8090';
const DOMAIN = `${APP_ADDR}:${APP_PORT}`;
const API_DOMAIN = `${DOMAIN}/api/v1`;
const FILE_DOMAIN = `${DOMAIN}/file`;

// Content-Type 설정
const createHeaders = (contentType?: string) => {
  const headers: Record<string, string> = {};
  if (contentType) {
    headers['Content-Type'] = contentType;
  }
  return { headers };
};

// JWT 토큰을 헤더에 포함시키는 함수
const authorization = (accessToken: string) => ({
  headers: { Authorization: `Bearer ${accessToken}` }
});

const multipartFormData = createHeaders('multipart/form-data');

// Generic request handlers
const handleResponse = <T>(response: any): T => response.data;
const handleError = (error: any): ResponseDto | null => {
  if (!error.response?.data) return null;
  return error.response.data;
};

const getRequest = async <T>(url: string, config?: any): Promise<T | ResponseDto | null> => {
  try {
    const response = await axios.get(url, config);
    return handleResponse<T>(response);
  } catch (error) {
    return handleError(error);
  }
};

const postRequest = async <T>(url: string, data?: any, config?: any): Promise<T | ResponseDto | null> => {
  try {
    const response = await axios.post(url, data, config);
    return handleResponse<T>(response);
  } catch (error) {
    return handleError(error);
  }
};

const patchRequest = async <T>(url: string, data?: any, config?: any): Promise<T | ResponseDto | null> => {
  try {
    const response = await axios.patch(url, data, config);
    return handleResponse<T>(response);
  } catch (error) {
    return handleError(error);
  }
};

const putRequest = async <T>(url: string, data?: any, config?: any): Promise<T | ResponseDto | null> => {
  try {
    const response = await axios.put(url, data, config);
    return handleResponse<T>(response);
  } catch (error) {
    return handleError(error);
  }
};

const deleteRequest = async <T>(url: string, config?: any): Promise<T | ResponseDto | null> => {
  try {
    const response = await axios.delete(url, config);
    return handleResponse<T>(response);
  } catch (error) {
    return handleError(error);
  }
};

// API Endpoints
const API_ENDPOINTS = {
  AUTH: {
    SIGN_IN:              () => `${API_DOMAIN}/auth/sign-in`,
    SIGN_UP:              () => `${API_DOMAIN}/auth/sign-up`,
  },
  BOARD: {
    // 1) 게시글
    GET_BOARD:            (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}`,
    PATCH_BOARD:          (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}`,
    POST_BOARD:           () => `${API_DOMAIN}/board`,
    DELETE_BOARD:         (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}`,
    // 2) 게시글 목록
    LATEST_LIST:          () => `${API_DOMAIN}/board/latest`,
    TOP3_LIST:            () => `${API_DOMAIN}/board/top3`,
    SEARCH_LIST:          (searchWord: string, preSearchWord: string | null) => `${API_DOMAIN}/board/search-list/${searchWord}${preSearchWord ? '/' + preSearchWord : ''}`,
    USER_BOARD_LIST:      (email: string) => `${API_DOMAIN}/board/user-board-list/${email}`,
    INCREASE_VIEW_COUNT:  (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}/increase-view-count`,
    // 3) 좋아요/댓글
    POST_COMMENT:         (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}/comment`,
    PUT_FAVORITE:         (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}/favorite`,
    FAVORITE_LIST:        (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}/favorite-list`,
    COMMENT_LIST:         (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}/comment-list`,
  },
  SEARCH: {
    POPULAR_LIST:         () => `${API_DOMAIN}/search/popular`,
    RELATION_LIST:        (searchWord: string) => `${API_DOMAIN}/search/${searchWord}/relation`,
  },
  USER: {
    GET_USER:             (email: string) => `${API_DOMAIN}/user/${email}`,
    GET_SIGN_IN_USER:     () => `${API_DOMAIN}/user`,
    PATCH_NICKNAME:       () => `${API_DOMAIN}/user/nickname`,
    PATCH_PROFILE_IMAGE:  () => `${API_DOMAIN}/user/profile-image`,
  },
  FILE: {
    UPLOAD: () => `${FILE_DOMAIN}/upload`,
  },
};

// Auth API - 인증
export const signInRequest = (requestBody: SignInRequestDto) => postRequest<SignInResponseDto>(API_ENDPOINTS.AUTH.SIGN_IN(), requestBody);
export const signUpRequest = (requestBody: SignUpRequestDto) => postRequest<SignUpResponseDto>(API_ENDPOINTS.AUTH.SIGN_UP(), requestBody);

// Board API
// 1) 게시글
export const getBoardRequest = (boardNumber: number | string) => getRequest<GetBoardResponseDto>(API_ENDPOINTS.BOARD.GET_BOARD(boardNumber));
export const patchBoardRequest = (boardNumber: number | string, requestBody: PatchBoardRequestDto, accessToken: string) => 
  patchRequest<PatchBoardResponseDto>(API_ENDPOINTS.BOARD.PATCH_BOARD(boardNumber), requestBody, authorization(accessToken));
export const postBoardRequest = (requestBody: PostBoardRequestDto, accessToken: string) => 
  postRequest<PostBoardResponseDto>(API_ENDPOINTS.BOARD.POST_BOARD(), requestBody, authorization(accessToken));
export const deleteBoardRequest = (boardNumber: number | string, accessToken: string) => 
  deleteRequest<DeleteBoardResponseDto>(API_ENDPOINTS.BOARD.DELETE_BOARD(boardNumber), authorization(accessToken));
export const increaseViewCountRequest = (boardNumber: number | string) => getRequest<IncreaseViewCountResponseDto>(API_ENDPOINTS.BOARD.INCREASE_VIEW_COUNT(boardNumber));

// 2) 게시글 목록
export const getLatestBoardListRequest = () => getRequest<GetLatestBoardListResponseDto>(API_ENDPOINTS.BOARD.LATEST_LIST());
export const getTop3BoardListRequest = () => getRequest<GetTop3BoardListResponseDto>(API_ENDPOINTS.BOARD.TOP3_LIST());
export const getSearchBoardListRequest = (searchWord: string, preSearchWord: string | null) => 
  getRequest<GetSearchBoardListResponseDto>(API_ENDPOINTS.BOARD.SEARCH_LIST(searchWord, preSearchWord));
export const getUserBoardListRequest = (email: string) => getRequest<GetUserBoardListResponseDto>(API_ENDPOINTS.BOARD.USER_BOARD_LIST(email));

// 3) 좋아요/댓글
export const putFavoriteRequest = (boardNumber: number | string, accessToken: string) => 
  putRequest<PutFavoriteResponseDto>(API_ENDPOINTS.BOARD.PUT_FAVORITE(boardNumber), {}, authorization(accessToken));
export const postCommentRequest = (boardNumber: number | string, requestBody: PostCommentRequestDto, accessToken: string) => 
  postRequest<PostCommentResponseDto>(API_ENDPOINTS.BOARD.POST_COMMENT(boardNumber), requestBody, authorization(accessToken));
export const getFavoriteListRequest = (boardNumber: number | string) => getRequest<GetFavoriteListResponseDto>(API_ENDPOINTS.BOARD.FAVORITE_LIST(boardNumber));
export const getCommentListRequest = (boardNumber: number | string) => getRequest<GetCommentListResponseDto>(API_ENDPOINTS.BOARD.COMMENT_LIST(boardNumber));

// Search API
export const getPopularListRequest = () => getRequest<GetPopularListResponseDto>(API_ENDPOINTS.SEARCH.POPULAR_LIST());
export const getRelationListRequest = (searchWord: string) => getRequest<GetRelationListResponseDto>(API_ENDPOINTS.SEARCH.RELATION_LIST(searchWord));

// User API
export const getUserRequest = (email: string) => getRequest<GetUserResponseDto>(API_ENDPOINTS.USER.GET_USER(email));
export const getSignInUserRequest = (accessToken: string) => getRequest<GetSignInUserResponseDto>(API_ENDPOINTS.USER.GET_SIGN_IN_USER(), authorization(accessToken));
export const patchNicknameRequest = (requestBody: PatchNicknameRequestDto, accessToken: string) => 
  patchRequest<PatchNicknameResponseDto>(API_ENDPOINTS.USER.PATCH_NICKNAME(), requestBody, authorization(accessToken));
export const patchProfileImageRequest = (requestBody: PatchProfileImageRequestDto, accessToken: string) =>
  patchRequest<PatchProfileImageResponseDto>(API_ENDPOINTS.USER.PATCH_PROFILE_IMAGE(), requestBody, authorization(accessToken));

// File API
export const fileUploadRequest = (data: FormData) => postRequest<string>(API_ENDPOINTS.FILE.UPLOAD(), data, multipartFormData);