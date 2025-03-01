import React, { ChangeEvent, Dispatch, forwardRef, SetStateAction, KeyboardEvent } from 'react';
import './style.css';

// 🔹 Props(속성) 타입 정의
interface Props {
  label: string; // 입력 필드 상단에 표시될 라벨
  type: 'text' | 'password'; // 입력 필드의 타입 (일반 텍스트 또는 비밀번호)
  placeholder: string; // 입력 필드의 플레이스홀더 (힌트 텍스트)
  value: string; // 현재 입력 필드의 값
  setValue: Dispatch<SetStateAction<string>>; // 입력 값 변경을 처리하는 상태 업데이트 함수
  error: boolean; // 오류 상태 여부 (true일 경우 스타일 변경)

  icon?: string; // 입력 필드 옆에 표시할 아이콘 클래스 (선택 사항)
  onButtonClick?: () => void; // 아이콘 버튼 클릭 시 실행할 함수 (선택 사항)

  message?: string; // 입력 필드 하단에 표시될 메시지 (예: 오류 메시지)

  onKeyDown?: (event: KeyboardEvent<HTMLInputElement>) => void; // 키 입력 이벤트 핸들러 (선택 사항)
}

// 🔹 `LoginBox` 컴포넌트 정의
// `forwardRef`를 사용하여 외부에서 `ref`로 입력 필드를 제어할 수 있도록 함
const LoginBox = forwardRef<HTMLInputElement, Props>(({
  label, // 입력 필드의 라벨
  type, // 입력 타입 (text 또는 password)
  placeholder, // 플레이스홀더
  value, // 현재 입력값
  error, // 오류 상태
  icon, // 아이콘 클래스명
  message, // 메시지 (오류 메시지 또는 안내 메시지)
  setValue, // 입력 값을 업데이트하는 함수
  onButtonClick, // 버튼 클릭 시 실행할 함수
  onKeyDown // 키 입력 이벤트 핸들러
}, ref) => {

  // 🔹 입력 값 변경 핸들러
  // 사용자가 입력 필드에 값을 입력하면 해당 값을 `setValue`를 사용하여 업데이트함
  const onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    setValue(event.target.value); // 입력된 값을 업데이트
  };

  // 🔹 키다운 이벤트 핸들러 (옵션)
  // 사용자가 특정 키(예: Enter)를 누를 경우 실행할 이벤트 핸들러
  const onKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
    onKeyDown?.(event); // `onKeyDown`이 정의된 경우 실행 (옵셔널 체이닝 사용)
  };

  return (
    <div className="inputbox">
      {/* 🔹 입력 필드 라벨 */}
      <div className="inputbox-label">{label}</div>

      {/* 🔹 입력 필드 컨테이너 */}
      {/* 오류 상태(error)가 true이면 다른 스타일 적용 */}
      <div className={error ? "inputbox-container-error" : "inputbox-container"}>
        {/* 🔹 입력 필드 */}
        <input
          ref={ref} // 외부에서 `ref`를 통해 제어 가능하도록 설정
          type={type} // 'text' 또는 'password' 타입
          className="input" // 기본 입력 필드 스타일 적용
          placeholder={placeholder} // 플레이스홀더 텍스트 표시
          value={value} // 현재 입력된 값
          onChange={onChangeHandler} // 입력 값이 변경될 때 실행
          onKeyDown={onKeyDownHandler} // 키보드 이벤트 발생 시 실행
        />

        {/* 🔹 아이콘 버튼 (onButtonClick이 있을 경우만 렌더링) */}
        {onButtonClick && (
          <div className="icon-button" onClick={onButtonClick}>
            {/* 아이콘이 있을 경우 아이콘을 표시 */}
            {icon && <div className={`icon ${icon}`} />}
          </div>
        )}
      </div>

      {/* 🔹 오류 메시지 또는 안내 메시지 (message가 있을 경우만 렌더링) */}
      {message && <div className="inputbox-message">{message}</div>}
    </div>
  );
});

export default LoginBox;