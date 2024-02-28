import React from 'react'
import { useNavigate } from 'react-router-dom';

function Header() {

  // 변수
  const navigation = useNavigate();

  // 상태

  // 메서드
  const handleLoginBtinClick = () => {
    navigation("/login");
  }

  const handleSingupBtinClick = () => {
    navigation("/signup");
  }

  // view


  return (
    <div className='nav'>
      <div><button onClick={handleLoginBtinClick}>로그인</button></div>
      <div><button onClick={handleSingupBtinClick}>회원가입</button></div>
    </div>
  )
}

export default Header