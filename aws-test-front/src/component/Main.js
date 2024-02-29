import React from 'react'
import { useNavigate } from 'react-router-dom';

function Main() {

  // 변수
  const navigation = useNavigate();

  // 상태

  // 메서드

  const handleMemberListBtnClick = () => {
    navigation("/members/info");
  }


  return (
    
    <>
      <div>
        <h1>메인 화면</h1>
      </div>

      <div>
        <button onClick={handleMemberListBtnClick}>회원정보목록</button>
      </div>
    </>
  )
}

export default Main