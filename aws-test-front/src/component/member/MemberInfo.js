import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import Info from './Info';

function MemberInfo() {

  // 변수
  const navigation = useNavigate();

  // 상태
  const [data, setData] = useState([]);

  // 메서드
  const getMemberInfoList = async () => {

    try {
      const response = await axios({
        method: "get",
        url: "/members",
        baseURL: "http://localhost:9100",
        headers: {
          "Accept": "application/json"
        }
      });

      console.log(response);
      return response;
    } catch(e) {
      console.log("회원정보 가져오는 중 에러 발생!!!!");
      console.log(e);
    }
  }

  const init = async () => {

    // 회원 정보 불러오기
    const response = await getMemberInfoList();

    if(!response) {
      alert("데이터가 없습니다.");
      return;
    }

    const {code, data} = response.data;

    if(code === 200) {

      // data 담기
      setData(data);
    }
  }

  // view

  const memberInfoListView = data.map((memberInfo, index) => {
    return <Info key={index} memberInfo={memberInfo} />
  });

  // 처음 시작
  useEffect(() => {

    init();

  }, []);

  return (
    <div>
      {memberInfoListView}
    </div>
  )
}

export default MemberInfo