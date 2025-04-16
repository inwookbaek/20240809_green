import axios from "axios";
import { ResponseDto } from "./response";
import { GetSignInUserResponseDto } from "./response/user";
import { SignInRequestDto, SignUpRequestDto } from "./request/auth";
import { SignInResponseDto, SignUpResponseDto } from "./response/auth";
import { PatchBoardRequestDto, PostBoardRequestDto, PostCommentRequestDto } from "./request/board";
import { GetBoardResponseDto, GetCommentListResponseDto, GetFavoriteListResponseDto, PostBoardResponseDto
  , PostCommentResponseDto, PutFavoriteResponseDto , IncreaseViewCountResponseDto , DeleteBoardResponseDto, 
  PatchBoardResponseDto,
  GetLatestBoardListResponseDto,
  GetTop3BoardListResponseDto} from "./response/board";
import { GetPopularListResponseDto, GetRelationListResponseDto } from "./response/search";
import GetSearchBoardListResponseDto from "./response/board/get-search-board-list.response.dto";

// .env 파일에서 포트 번호를 가져옴
const APP_ADDR = process.env.APP_API_ADDR || 'http://localhost'; // 기본값 설정 (옵션)
const APP_PORT = process.env.APP_API_PORT || '8090'; // 기본값 설정 (옵션)
const DOMAIN = `${APP_ADDR}:${APP_PORT}`;

const API_DOMAIN = `${DOMAIN}/api/v1`;

// jwt token
const authorization = (accessToken: string) => {
  return { headers: { Authorization: `Bearer ${accessToken}` } }
};

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

// 요청URL
const GET_BOARD_URL = (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}`;
const GET_LASTEST_BOARD_LIST_URL = () => `${API_DOMAIN}/board/latest`;
const GET_TOP3_BOARD_LIST_URL = () => `${API_DOMAIN}/board/top3`;
const GET_SEARCH_BOARD_LIST_URL = (searchWord: string, preSearchWord: string | null) => `${API_DOMAIN}/board/search-list/${searchWord}${preSearchWord ? '/' + preSearchWord : ''}`;
const INCREASE_VIEW_COUNT_URL = (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}/increase-view-count`;
const GET_FAVORITE_LIST_URL = (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}/favorite-list`;
const GET_COMMENT_LIST_URL = (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}/comment-list`;
const POST_BOARD_URL = () => `${API_DOMAIN}/board`;
const PATCH_BOARD_URL = (boardNumber: string | number)  => `${API_DOMAIN}/board/${boardNumber}`;
const POST_COMMENT_URL = (boardNumber: string | number)  => `${API_DOMAIN}/board/${boardNumber}/comment`;
const PUT_FAVORITE_URL = (boardNumber: string | number)  => `${API_DOMAIN}/board/${boardNumber}/favorite`;
const DELETE_BOARD_URL = (boardNumber: string | number)  => `${API_DOMAIN}/board/${boardNumber}`;

const GET_POPULAR_LIST_URL = () => `${API_DOMAIN}/search/popular`;
export const getPopularListRequest = async () => {
  const result = await axios.get(GET_POPULAR_LIST_URL())
      .then(response => {
        const responseBody: GetPopularListResponseDto = response.data;
        return responseBody
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
  return result;  
};

const GET_RELATION_LIST_URL = (searchWord: string) => `${API_DOMAIN}/search/${searchWord}/relation`;
export const getRelationListRequest = async (searchWord: string) => {
  const result = await axios.get(GET_RELATION_LIST_URL(searchWord))
      .then(response => {
        const responseBody: GetRelationListResponseDto = response.data;
        return responseBody
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
  return result;  
};

const GET_SIGN_IN_USER_URL = () => `${API_DOMAIN}/user`;
export const getSignInUserRequest = async (accessToken: string) => {
  const result = await axios.get(GET_SIGN_IN_USER_URL(), authorization(accessToken))
    .then(response => {
      const responseBody: GetSignInUserResponseDto = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}

const FILE_DOMAIN = `${DOMAIN}/file`;
const FILE_UPLOAD_URL = () => `${FILE_DOMAIN}/upload`;
const multipartFormData = { headers: { 'Content-Type': 'multipart/form-data'} }

export const fileUploadRequest = async (data: FormData) => {
  const result = await axios.post(FILE_UPLOAD_URL(), data, multipartFormData)
      .then(response => {
        const responseBody: string = response.data;
        return responseBody;
      })
      .catch(error => {
        return null;
      })
  return result;
}

export const getBoardRequest = async (boardNumber: number | string) => {
  const result = await axios.get(GET_BOARD_URL(boardNumber))
      .then(response => {
        const responseBody: GetBoardResponseDto = response.data;
        return responseBody
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })

      return result;
}

export const getLatestBoardListRequest = async () => {
  const result = await axios.get(GET_LASTEST_BOARD_LIST_URL())
  .then(response => {
    const responseBody: GetLatestBoardListResponseDto = response.data;
    return responseBody
  })
  .catch(error => {
    if(!error.response) return null;
    const responseBody: ResponseDto = error.response.data;
    return responseBody;
  })
  
  return result;
};

export const getTop3BoardListRequest = async () => {
  const result = await axios.get(GET_TOP3_BOARD_LIST_URL())
      .then(response => {
        const responseBody: GetTop3BoardListResponseDto = response.data;
        return responseBody
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })

  return result;
};

export const getSearchBoardListRequest = async (searchWord: string, preSearchWord: string | null) => {
  const result = await axios.get(GET_SEARCH_BOARD_LIST_URL(searchWord, preSearchWord))
      .then(response => {
        const responseBody: GetSearchBoardListResponseDto = response.data;
        return responseBody
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })

  return result;
};

export const increaseViewCountRequest = async (boardNumber: number | string) => {
  const result = await axios.get(INCREASE_VIEW_COUNT_URL(boardNumber))
    .then(response => {
      const responseBody: IncreaseViewCountResponseDto = response.data;
      return responseBody
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    })
  return result;
}

export const getFavoriteListRequest = async (boardNumber: number | string) => {
  const result = await axios.get(GET_FAVORITE_LIST_URL(boardNumber))
    .then(response => {
      const responseBody: GetFavoriteListResponseDto = response.data;
      return responseBody
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    })
  return result;
}

export const getCommentListRequest = async (boardNumber: number | string) => {
  const result = await axios.get(GET_COMMENT_LIST_URL(boardNumber))
    .then(response => {
      const responseBody: GetCommentListResponseDto = response.data;
      return responseBody
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    })
  return result;
}

export const postBoardRequest = async (requestBody: PostBoardRequestDto, accessToken: string) => {
  const result = await axios.post(POST_BOARD_URL(), requestBody, authorization(accessToken))
      .then((response) => {
        const responseBody: PostBoardResponseDto = response.data;
        return responseBody
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
      return result;
}

// 댓글달기
export const postCommentRequest = async (boardNumber: number | string, requestBody: PostCommentRequestDto, accessToken: string) => {
  const result = await axios.post(POST_COMMENT_URL(boardNumber), requestBody, authorization(accessToken)) 
    .then(response => {
      const responseBody: PostCommentResponseDto = response.data;
      return responseBody
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    })
  return result;
}

// 게시글수정
export const putFavoriteRequest = async (boardNumber: number | string, accessToken: string) => {
  const result = await axios.put(PUT_FAVORITE_URL(boardNumber), {}, authorization(accessToken)) // put 보낼것은 없기 때문에 {}
    .then(response => {
      const responseBody: PutFavoriteResponseDto = response.data;
      return responseBody
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    })
  return result;
}

// 게시글삭제
export const deleteBoardRequest = async (boardNumber: number | string, accessToken: string) => {
  const result = await axios.delete(DELETE_BOARD_URL(boardNumber), authorization(accessToken)) 
    .then(response => {
      const responseBody: DeleteBoardResponseDto = response.data;
      return responseBody
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    })
  return result;
}

export const patchBoardRequest = async (boardNumber: number | string, requestBody: PatchBoardRequestDto, accessToken: string) => {
  const result = await axios.patch(PATCH_BOARD_URL(boardNumber), requestBody, authorization(accessToken))
      .then((response) => {
        const responseBody: PatchBoardResponseDto = response.data;
        return responseBody
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
      return result;
}