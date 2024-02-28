import axios from 'axios';
import React, { useEffect, useRef, useState } from 'react'
import { useNavigate } from 'react-router-dom';

function Signup() {

  // 변수
  const navigation = useNavigate();
  const usernameRef = useRef();
  const defaultSignupData = {
    username: "",
    nickname: "",
    introduction: "",
  }

  // 상태
  const [signupData, setSignupData] = useState(defaultSignupData);
  const [profile, setProfile] = useState();

  // 메서드
  // 회원가입
  const signup = async () => {

    // blob 으로 데이터 만들기
    const formdata = new FormData();

    // input 데이터
    // json string으로 변경
    const jsonStrData = JSON.stringify(signupData);
    // content-type 맞추기
    const jsonDataBlob = new Blob([jsonStrData], {type : 'application/json'});
    formdata.append("requestSignUpDto", jsonDataBlob);

    // 파일 데이터
    formdata.append("profileImage", profile);

    // 데이터 전송
    try {
      const response = await axios({
        method: "post",
        headers: {
          "Content-Type": "multipart/form-data"
        },
        url: "/members",
        baseURL: "http://localhost:9100/",
        data: formdata
      });

      return response;
      
    } catch(e) {
      console.log(e);
    }

  }

  const handleSignupBtnClick = async () => {

    const response = await signup();

    // 회원가입 실패
    if(!response) {
      alert("다시 등록 버튼을 눌러주세요!");
      return;
    }

    // 회원가입 성공
    const {code} = response.data;

    if(code === 300) {
      navigation("/login");
    }
  }

  const handleCancelBtnClick = () => {
    navigation("/");
  }

  const initFocusInputBox = () => {
    // 처음 초점 맞추기
    const usernameInput = usernameRef.current;
    usernameInput.focus();
  }

  const init = () => {
    initFocusInputBox();
  }

  const handleSignupDataChange = (e) => {
    const {name, value} = e.target;

    setSignupData((current) => {
      const newData = {
        ...current,
        [name]: value
      }

      return newData;
    });
  }

  const handleProfileChange = (e) => {

    const file = e.target.files[0];

    setProfile(() => {
      return file;
    });
  }

  // view

  // 처음 start
  useEffect(() => {

    init();
  
  }, []);

  return (
    <>
      <div className='signupBox'>
        {/* 이름 */}
        <div className='inputBox'>
          <label htmlFor="username">이름</label>
          <input ref={usernameRef} type="text" id='username' name='username' onChange={handleSignupDataChange} />
        </div>

        {/* 아이디 */}
        <div className='inputBox'>
          <label htmlFor="nickname">닉네임</label>
          <input type="text" id='nickname' name='nickname' onChange={handleSignupDataChange} />
        </div>

        {/* 짧은 소개 */}
        <div className='inputBox'>
          <label htmlFor="introduction">소개</label>
          <textarea id='introduction' name='introduction' onChange={handleSignupDataChange}  />
        </div>

        {/* 프로필 이미지 */}
        <div>
          <label htmlFor="profile">프로필이미지</label>
          <input 
            type='file' 
            id='profile' 
            name='profile'
            accept='image/jpg, image/jpeg, image/png'
            onChange={handleProfileChange} 
          />
        </div>
      </div>
      
      <div className='btnBox'>
        <div>
          <button onClick={handleSignupBtnClick}>등록</button>
        </div>
        <div>
          <button onClick={handleCancelBtnClick}>취소</button>
        </div>
      </div>
    </>
  )
}

export default Signup