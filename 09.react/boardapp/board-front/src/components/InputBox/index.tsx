import  { forwardRef, ChangeEvent, KeyboardEvent } from 'react'
import './style.css'

interface Props {
  label: string;
  type: 'text' | 'password';
  placeholder: string;
  value: string;
  error: boolean;
  
  icon?: 'eye-light-off-icon' | 'eye-light-on-icon' | 'expand-right-light-icon';
  message?: string;
  
  onChange?: (event: ChangeEvent<HTMLInputElement>) => void;
  onButtonClick?: () => void;
  onKeyDown?: (event: KeyboardEvent<HTMLInputElement>) => void;
}

const InputBox = forwardRef<HTMLInputElement, Props>((props: Props, ref) => {

  const { label, type, error, placeholder, value, icon, message } = props;
  const { onChange, onButtonClick, onKeyDown } = props; 

  const onKeyDwonHandler = (event: KeyboardEvent<HTMLInputElement>) => {
    if (!onKeyDown) return;
    onKeyDown(event);
  };

  return (
    <div className='inputbox'>
      <div className='inputbox-label'>{label}</div>
      <div className={error ? 'inputbox-container-error' : 'inputbox-container'}>
        <input ref={ref} type={type} className="input" placeholder={placeholder} value={value} onChange={onChange} onKeyDown={onKeyDwonHandler}/>
        {onButtonClick !== undefined && (
          <div className="icon-button" onClick={onButtonClick}>
            {icon !== undefined && <div className={`icon ${icon}`}></div>}
          </div>
        )}

      </div>
      {message !== undefined && <div className='inputbox-message'>{message}</div>}
    </div>
  )
});

export default InputBox;
