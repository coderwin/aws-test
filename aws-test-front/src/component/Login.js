import React from 'react'
import { useNavigate } from 'react-router-dom';

function Login() {

  // 변수
  const navigation = useNavigate();

  // 상태

  // 메서드

  const handleMainBtnClick = () => {
    navigation("/");
  }

  // view

  return (
    <>
      <div>
        <div className='inputBox'>
          <label htmlFor="userId">아이디</label>
          <input type="text" id='userId' />
        </div>
        <div className='inputBox'>
          <label htmlFor="password">비밀번호</label>
          <input type="text" id='password' />
        </div>
      </div>

      <div className='menuBox'>
        <div>
          <button onClick={handleMainBtnClick}>홈으로</button>
        </div>
      </div>
    </>
  )
}

export default Login