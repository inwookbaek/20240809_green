import { useState, useRef, KeyboardEvent, ChangeEvent, useEffect } from 'react';
import './style.css';
import InputBox from 'components/InputBox';
import { SignInRequestDto, SignUpRequestDto } from 'apis/request/auth';
import { signInRequest, signUpRequest } from 'apis';
import { SignInResponseDto, SignUpResponseDto } from 'apis/response/auth';
import { ResponseDto } from 'apis/response';
import { useCookies } from 'react-cookie';
import { useNavigate } from 'react-router-dom';
import { MAIN_PATH } from 'constant';
import { Address, useDaumPostcodePopup } from 'react-daum-postcode'

/**
 * 인증 화면 컴포넌트 (로그인 및 회원가입 UI)
 */
export default function Authentication() {

  // 현재 화면 상태를 저장하는 상태 변수 ('sign-in' 또는 'sign-up')
  const [view, setView] = useState<'sign-in' | 'sign-up'>('sign-in');

  // 쿠키 사용을 위한 Hook
  const [cookies, setCookie] = useCookies();

  // 페이지 이동을 위한 Hook
  const navigate = useNavigate();

  // 로그인 카드 컴포넌트
  const SignInCard = () => {

    // 이메일과 비밀번호 입력 필드의 참조
    const emailRef = useRef<HTMLInputElement | null>(null);
    const passwordRef = useRef<HTMLInputElement | null>(null);

    // 입력값 및 UI 상태 관리
    const [email, setEmail] = useState<string>(''); // 이메일 상태
    const [password, setPassword] = useState<string>(''); // 비밀번호 상태
    const [passwordType, setPasswordType] = useState<'text' | 'password'>('password'); // 비밀번호 가시성 상태
    const [passwordButtonIcon, setPasswordButtonIcon] = useState<'eye-light-off-icon' | 'eye-light-on-icon'>('eye-light-off-icon'); // 비밀번호 보기 버튼 아이콘 상태
    const [error, setError] = useState<boolean>(false); // 에러 표시 여부

    /**
     * 로그인 요청 후 응답을 처리하는 함수
     */
    const signResponse = (responseBody: SignInResponseDto | ResponseDto | null) => {
      if (!responseBody) {
        alert('서버와의 통신이 원활하지 않습니다.');
        return;
      }
      const { code } = responseBody;

      // 응답 코드에 따른 처리
      if (code === 'DBE') alert('데이터베이스 서버와 통신이 원활하지 않습니다.');
      if (code === 'SF' || code === 'VF') setError(true); // 로그인 실패 시 에러 표시
      if (code !== 'SU') return; // 성공이 아니면 종료

      // 로그인 성공 시 쿠키에 토큰 저장 후 메인 페이지로 이동
      const { token, expirationTime } = responseBody as SignInResponseDto;
      console.log('token ===> ', token, 'expirationTime ===> ', expirationTime);

      const now = new Date().getTime();
      const expires = new Date(now + expirationTime * 1000); // 만료 시간 설정

      // expires : accessToken의 유효시간,  path : 쿠키가 유효한 경로
      setCookie('accessToken', token, { expires, path: MAIN_PATH() });
      navigate(MAIN_PATH());
    }

    /**
     * 이메일 입력 시 실행되는 이벤트 핸들러
     */
    const onEmailChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setError(false);
      setEmail(event.target.value);
    }

    /**
     * 비밀번호 입력 시 실행되는 이벤트 핸들러
     */
    const onPasswordChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setError(false);
      setPassword(event.target.value);
    }

    /**
     * 로그인 버튼 클릭 시 실행되는 이벤트 핸들러
     */
    const onSignInButtonClickHandler = () => {
      const requestBody: SignInRequestDto = { email, password };
      signInRequest(requestBody).then(signResponse);
    }

    /**
     * 회원가입 버튼 클릭 시 실행되는 이벤트 핸들러
     */
    const onSignUpButtonClickHandler = () => {
      setView('sign-up');
    }

    /**
     * 비밀번호 보기 버튼 클릭 시 실행되는 이벤트 핸들러
     */
    const onPasswordButtonClickHandler = () => {
      if (passwordType === 'text') {
        setPasswordType('password');
        setPasswordButtonIcon('eye-light-off-icon');
      } else {
        setPasswordType('text');
        setPasswordButtonIcon('eye-light-on-icon');
      }
    }

    /**
     * 이메일 입력 필드에서 엔터 키 입력 시 실행되는 이벤트 핸들러
     */
    const onEmailKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      if (!passwordRef.current) return;
      passwordRef.current.focus(); // 비밀번호 입력 필드로 포커스 이동
    }

    /**
     * 비밀번호 입력 필드에서 엔터 키 입력 시 실행되는 이벤트 핸들러
     */
    const onPasswordKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      onSignInButtonClickHandler();
    }

    return (
      <div className="auth-card">
        <div className="auth-card-box">
          <div className="auth-card-top">
            <div className="auth-card-title-box">
              <div className="auth-card-title">{'로그인'}</div>
            </div>
            <InputBox 
              ref={emailRef} 
              label='이메일주소 *' 
              type='text' 
              placeholder='이메일 주소를 입력해 주세요.' 
              error={error} 
              value={email} 
              onChange={onEmailChangeHandler} 
              onKeyDown={onEmailKeyDownHandler} />
            <InputBox 
              ref={passwordRef} 
              label='비밀번호 *' 
              type={passwordType} 
              placeholder='비밀번호를 입력해 주세요.' 
              error={error} 
              value={password} 
              onChange={onPasswordChangeHandler}
              icon={passwordButtonIcon} 
              onButtonClick={onPasswordButtonClickHandler}
              onKeyDown={onPasswordKeyDownHandler} />
          </div>
          <div className="auth-card-bottom">
            {error &&
              <div className="auth-sign-in-error-box">
                <div className="auth-sign-in-error-message">
                  {'이메일 주소 또는 비밀번호를 잘못 입력했습니다!\n입력하신 내용을 다시 확인해 주세요!!!'}
                </div>
              </div>
            }
            <div className="black-large-full-button" onClick={onSignInButtonClickHandler}>{'로그인'}</div>
            <div className="auth-description-box">
              <div className="auth-description">
                {'신규 사용자이신가요? '} <span className='auth-description-link' onClick={onSignUpButtonClickHandler}>{'회원가입'}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  } // signInCard

 // 회원가입 카드 컴포넌트
  const SignUpCard = () => {

    // Refernce
    const emailRef = useRef<HTMLInputElement | null>(null);
    const passwordRef = useRef<HTMLInputElement | null>(null);
    const passwordCheckRef = useRef<HTMLInputElement | null>(null);
    const nicknameRef = useRef<HTMLInputElement | null>(null);
    const telNumberRef = useRef<HTMLInputElement | null>(null);
    const addressRef = useRef<HTMLInputElement | null>(null);
    const addressDetailRef = useRef<HTMLInputElement | null>(null);
  
    // state
    const [page, setPage] = useState<1 | 2>(1);
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [passwordCheck, setPasswordCheck] = useState<string>('');
    const [passwordType, setPasswordType] = useState<'text' | 'password'>('password');
    const [passwordCheckType, setPasswordCheckType] = useState<'text' | 'password'>('password');
    const [passwordButtonIcon, setPasswordButtonIcon] = useState<'eye-light-off-icon' | 'eye-light-on-icon'>('eye-light-off-icon');
    const [passwordCheckButtonIcon, setPasswordCheckButtonIcon] = useState<'eye-light-off-icon' | 'eye-light-on-icon'>('eye-light-off-icon');
    const [nickname, setNickname] = useState<string>('');
    const [telNumber, setTelNumber] = useState<string>('');
    const [address, setAddress] = useState<string>('');
    const [addressDetail, setAddressDetail] = useState<string>('');
    const [agreedPersonal, setAgreedPersonal] = useState<boolean>(false);
  
    // error state
    const [isEmailError, setEmailError] = useState<boolean>(false);
    const [isPasswordError, setPasswordError] = useState<boolean>(false);
    const [isPasswordCheckError, setPasswordCheckError] = useState<boolean>(false);
    const [isNicknameError, setNicknameError] = useState<boolean>(false);
    const [isTelNumberError, setTelNumberError] = useState<boolean>(false);
    const [isAddressError, setAddressError] = useState<boolean>(false);
    const [isAgreedPersonalError, setAgreedPersonalError] = useState<boolean>(false);
  
    // error message
    const [emailErrorMessage, setEmailErrorMessage] = useState<string>('');
    const [passwordErrorMessage, setPasswordErrorMessage] = useState<string>('');
    const [passwordCheckErrorMessage, setPasswordCheckErrorMessage] = useState<string>('');
    const [nicknameErrorMessage, setNicknameErrorMessage] = useState<string>('');
    const [telNumberErrorMessage,setTelNumberErrorMessage] = useState<string>('');
    const [addressErrorMessage, setAddressErrorMessage] = useState<string>('');
  
    /*------------------------------ ChangeEventHandler ------------------------------------------*/
    // 이메일 입력 핸들러
    const onEmailChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setEmail(event.target.value);
      setEmailError(false);
      setEmailErrorMessage('');
    };
  
    // 비밀번호 입력 핸들러
    const onPasswordChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setPassword(event.target.value);
      setPasswordError(false);
      setPasswordErrorMessage('');
    };
  
    // 비밀번호 확인 입력 핸들러
    const onPasswordCheckChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setPasswordCheck(event.target.value);
      setPasswordCheckError(false);
      setPasswordCheckErrorMessage('');
    };
  
    // 닉네임 입력 핸들러
    const onNicknameChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setNickname(event.target.value);
      setNicknameError(false);
      setNicknameErrorMessage('');
    };

    // 휴대전화 입력 핸들러
    const onTelNumberChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setTelNumber(event.target.value);
      setTelNumberError(false);
      setTelNumberErrorMessage('');
    };

    // 주소 입력 핸들러
    const onAddressChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setAddress(event.target.value);
      setAddressError(false);
      setAddressErrorMessage('');
    };

    const onAddressDetailChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setAddressDetail(event.target.value);
    };

  /*------------------------------ ButtonClickHandler ------------------------------------------*/
    // 비밀번호 보기 버튼 핸들러
    const onPasswordButtonClickHandler = () => {
      if (passwordType === 'text') {
        setPasswordType('password');
        setPasswordButtonIcon('eye-light-off-icon');
      } else {
        setPasswordType('text');
        setPasswordButtonIcon('eye-light-on-icon');
      }
    };
  
    // 비밀번호 확인 보기 버튼 핸들러
    const onPasswordCheckButtonClickHandler = () => {
      if (passwordCheckType === 'text') {
        setPasswordCheckType('password');
        setPasswordCheckButtonIcon('eye-light-off-icon');
      } else {
        setPasswordCheckType('text');
        setPasswordCheckButtonIcon('eye-light-on-icon');
      }
    };

    const onNextButtonClickHandler = () => {
      const emailRegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; // 이메일 정규식  or /^[a-zA-Z0-9]*@([-.]?[a-zA-Z0-9])*\.[a-zA-Z]{2,4}$/; 
      const isEmailPattern = emailRegExp.test(email);

      if(!isEmailPattern) {
        setEmailError(true);
        setEmailErrorMessage('이메일 주소 형식이 맞지 않습니다!!');
        return;
      }

      const isCheckPassword = password.trim().length >= 8;
      if(!isCheckPassword) {
        setPasswordError(true);
        setPasswordErrorMessage('비밀번호는 8자 이상이어야 합니다!!');
        return;
      }

      const isEqualPasswordCheck = password === passwordCheck;
      if(!isEqualPasswordCheck) {
        setPasswordCheckError(true);
        setPasswordCheckErrorMessage('비밀번호가 일치하지 않습니다!!');
        return;
      } 

      if(!isEmailPattern || !isCheckPassword || !isEqualPasswordCheck) return;

      setPage(2); // 다음 페이지로 이동
    };

    const onAgreedPersonalButtonClickHandler = () => {
      setAgreedPersonal(!agreedPersonal);
      setAgreedPersonalError(false);  
    };

  /*------------------------------ Daum Post Code ------------------------------------------*/  
    const open = useDaumPostcodePopup();
    
    const onComplete = (data: Address) => {
      const { address } = data
      setAddress(address);
      setAddressError(false);
      setAddressErrorMessage('');
      if(!addressDetailRef.current) return;
      addressDetailRef.current.focus();      
    };
    
    const onAddressButtonClickHandler = () => {
      // 다음우편번호 npm i react-daum-postcode
      // 참조사이트 : https://www.npmjs.com/package/react-daum-postcode
      open( { onComplete } );
    };

    // Previous PPage
    const onPrviousPageButtonClickHandler = () => {
      setPage(1);
    };

  /*------------------------------ 회원가입 ------------------------------------------*/  
    const singUpResponse = (responseBody: SignUpResponseDto | ResponseDto | null) => {
      if (!responseBody) {
        alert('서버와의 통신이 원활하지 않습니다.');
        return;
      }
      const { code } = responseBody;

      // 응답 코드에 따른 처리
      if (code === 'DE') {
        setEmailError(true);
        setEmailErrorMessage('중복된 이메일주소 입니다. 다시 입력해 주세요!!');
      }
    
      if (code === 'DN') {
        setNicknameError(true);
        setNicknameErrorMessage('중복된 닉네임 입니다. 다시 입력해 주세요!!');
      }

      if (code === 'DT') {
        setTelNumberError(true);
        setTelNumberErrorMessage('중복된 전화번호 입니다. 다시 입력해 주세요!!');
      }
    
      if (code === 'VF')  alert('모든 값을 입력해야 합니다!');
      if (code === 'DBE')  alert('데이터베이스 오류입니다!!');
      if (code !== 'SU') return; // 성공이 아니면 종료

      setView('sign-in');
    }

    // 회원가입버튼 핸들러
    const onSignUpButtonClickHandler = () => {
      
      // alert('회원가입버튼클릭!!!!');
      const emailRegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
      const isEmailPattern = emailRegExp.test(email);

      if(!isEmailPattern) {
        setEmailError(true);
        setEmailErrorMessage('이메일 주소 형식이 맞지 않습니다!!');
        return;
      }

      const isCheckPassword = password.trim().length >= 8;
      if(!isCheckPassword) {
        setPasswordError(true);
        setPasswordErrorMessage('비밀번호는 8자 이상이어야 합니다!!');
        return;
      }

      const isEqualPasswordCheck = password === passwordCheck;
      if(!isEqualPasswordCheck) {
        setPasswordCheckError(true);
        setPasswordCheckErrorMessage('비밀번호가 일치하지 않습니다!!');
        return;
      } 

      if(!isEmailPattern || !isCheckPassword || !isEqualPasswordCheck) {
        setPage(1);
        return;
      }
      
      const hasNickname = nickname.trim().length !== 0;
      if(!hasNickname) {
        setNicknameError(true);
        setNicknameErrorMessage('닉네임을 입력해 주세요!!');
      }

      const telNumberPattern = /^[0-9]{11,13}$/; // /^01[016789]-?\d{3,4}-?\d{4}$/; 
      const isTelNumberPattern = telNumberPattern.test(telNumber);
      if(!isTelNumberPattern) {
        setTelNumberError(true);
        setTelNumberErrorMessage('전화번호포맷이 잘못되었습니다. 숫자만 입력해 주세요!!')
      }

      const hasAddress = address.trim().length > 0;
      if(!hasAddress) {
        setAddressError(true);
        setAddressErrorMessage("주소를 입력하지 않았습니다. 주소를 입력해 주세요!!");
      }

      if(!agreedPersonal) setAgreedPersonalError(true);

      if(!hasNickname || !isTelNumberPattern || !agreedPersonal) return;

      const requestBody: SignUpRequestDto = {
        email, password, nickname, telNumber, address, addressDetail, agreedPersonal
      };

      signUpRequest(requestBody).then(singUpResponse)
    };

    // 로그인 버튼 핸들러
    const onSignInLinkClickHandler = () => {
      setView('sign-in');
    };

  /*------------------------------ KeyDownHandler ------------------------------------------*/  
    // 이메일 입력 필드에서 엔터 키 입력 핸들러
    const onEmailKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      if (!passwordRef.current) return;
      passwordRef.current.focus();
    };
  
    // 비밀번호 입력 필드에서 엔터 키 입력 핸들러
    const onPasswordKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      if (!passwordCheckRef.current) return;
      passwordCheckRef.current.focus();
    };
  
    // 비밀번호 확인 입력 필드에서 엔터 키 입력 핸들러
    const onPasswordCheckKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      onNextButtonClickHandler();
    };
  
    // 비번확인키를 엔터치는 시점에서는  2번째 화면이 랜더링이 되지 않았기 때문에 아래 로직처리
    useEffect(()=> {
      if(page === 2) {
        if(!nicknameRef.current) return;
        nicknameRef.current.focus();
      }
    }, [page])

    // 닉네임 엔터 키 입력 핸들러
    const onNicknameKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      if (!telNumberRef.current) return;
      telNumberRef.current.focus();     
    };
  
    // 휴대전화 엔터 키 입력 핸들러
    const onTelNumberKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      onAddressButtonClickHandler();
    };
  
    // 주소 엔터 키 입력 핸들러
    const onAddressKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      if (!addressDetailRef.current) return;
      addressDetailRef.current.focus();
    };
  
    // 상세주소 엔터 키 입력 핸들러
    const onAddressDetailKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      onSignUpButtonClickHandler();
    };

    return (
      <div className="auth-card">
        <div className="auth-card-box">
          <div className="auth-card-top">
            <div className="auth-card-title-box">
              <div className="auth-card-title">{'회원가입'}</div>
              <div className="auth-card-page">{`${page} / 2`}</div>
            </div>
            
            <div className="auth-card-input-box">
              {page === 1 && (
                <>
                  <InputBox
                    ref={emailRef}
                    label="이메일 주소*"
                    type="text"
                    placeholder="이메일 주소를 입력해 주세요."
                    error={isEmailError}
                    value={email}
                    message={emailErrorMessage}
                    onChange={onEmailChangeHandler}
                    onKeyDown={onEmailKeyDownHandler}
                  />
                  <InputBox
                    ref={passwordRef}
                    label="비밀번호*"
                    type={passwordType}
                    placeholder="비밀번호를 입력해 주세요."
                    error={isPasswordError}
                    value={password}
                    message={passwordErrorMessage}
                    onChange={onPasswordChangeHandler}
                    icon={passwordButtonIcon}
                    onButtonClick={onPasswordButtonClickHandler}
                    onKeyDown={onPasswordKeyDownHandler}
                  />
                  <InputBox
                    ref={passwordCheckRef}
                    label="비밀번호확인*"
                    type={passwordCheckType}
                    placeholder="비밀번호를 다시 입력해 주세요."
                    error={isPasswordCheckError}
                    value={passwordCheck}
                    message={passwordCheckErrorMessage}
                    onChange={onPasswordCheckChangeHandler}
                    icon={passwordCheckButtonIcon}
                    onButtonClick={onPasswordCheckButtonClickHandler}
                    onKeyDown={onPasswordCheckKeyDownHandler}
                    />
                </>
              )}
              {page === 2 && (
                <>
                  <InputBox
                    ref={nicknameRef}
                    label="닉네임*"
                    type="text"
                    placeholder="닉네임을 입력해 주세요."
                    error={isNicknameError}
                    value={nickname}
                    message={nicknameErrorMessage}
                    onChange={onNicknameChangeHandler}
                    onKeyDown={onNicknameKeyDownHandler}
                    />
                  <InputBox
                    ref={telNumberRef}
                    label="휴대전화*"
                    type="text"
                    placeholder="휴대전화번호를 입력해 주세요."
                    error={isTelNumberError}
                    value={telNumber}
                    message={telNumberErrorMessage}
                    onKeyDown={onTelNumberKeyDownHandler}
                    onChange={onTelNumberChangeHandler}
                  />
                  <InputBox
                    ref={addressRef}
                    label="주소*"
                    type="text"
                    placeholder="우편번호 찾기"
                    error={isAddressError}
                    value={address}
                    message={addressErrorMessage}
                    icon={'expand-right-light-icon'}
                    onButtonClick={onAddressButtonClickHandler}
                    onKeyDown={onAddressKeyDownHandler}
                    onChange={onAddressChangeHandler}
                  />
                  <InputBox
                    ref={addressDetailRef}
                    label="상세주소*"
                    type="text"
                    placeholder="상세주소를 입력해 주세요."
                    error={false}
                    value={addressDetail}
                    message={'상세주소를 입력해 주세요.'}
                    onKeyDown={onAddressDetailKeyDownHandler}
                    onChange={onAddressDetailChangeHandler}
                  />
                </>
              )}
            </div>
          </div>
          <div className="auth-card-bottom">
            {(isEmailError || isPasswordError || isPasswordCheckError) && (
              <div className="auth-sign-in-error-box">
                <div className="auth-sign-in-error-message">
                  {'이메일 주소 또는 비밀번호를 잘못 입력했습니다!\n입력하신 내용을 다시 확인해 주세요!!!'}
                </div>
              </div>
            )}
            {page === 1 && (
              <div className="black-large-full-button" onClick={onNextButtonClickHandler}>{'다음 단계'}</div>
            )}
            {page === 2 && (
              <>
                <div className="auth-consent-box">
                  <div className='auth-check-box' onClick={onAgreedPersonalButtonClickHandler}>
                  <div className={`icon ${agreedPersonal ? 'check-round-fill-icon' : 'check-ring-light-icon'}`}></div> 
                  </div>
                  <div className={isAgreedPersonalError ? 'auth-consent-title-error' : 'auth-consent-title'}>{'개인정보동의'}</div>
                  <div className='auth-consent-link'>{'더보기 >'}</div>
                  <div className='auth-consent-prev-page' onClick={onPrviousPageButtonClickHandler}>{'[ 이전단계 ]'}</div>
                </div>
                <button className="black-large-full-button" onClick={onSignUpButtonClickHandler} disabled={!agreedPersonal}>{'회원가입'}</button>
              </>
            )}
            <div className="auth-description-box">
              <div className="auth-description">{'이미 계정이 있으신가요? '}
                <span className="auth-description-link" onClick={onSignInLinkClickHandler}>{'로그인'}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }; // SignUpCard

  return (
    <div id="auth-wrapper">
      <div className="auth-container">
        <div className="auth-jumbotron-box">
          <div className="auth-jumbotron-contents">
            <div className="auth-jumbotron-icon"></div>
            <div className="auth-jumbotron-text-box">
              <div className="auth-jumbotron-text-small">{'방문을 환영합니다.'}</div>
              <div className="auth-jumbotron-text">{'GILCNS 게시판입니다!'}</div>
            </div>
          </div>
        </div>
        {view === 'sign-in' && <SignInCard />}
        {view === 'sign-up' && <SignUpCard />}
      </div>
    </div>
  )  // return

} // Authentication