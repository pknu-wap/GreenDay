import { useEffect } from "react";
import axios from "axios";
import "./App.css";

function NaverRedirect() {
  const REACT_APP_API = 'http://3.36.87.184:8080';
  // const REACT_APP_API = 'ec2-3-36-87-184.ap-northeast-2.compute.amazonaws.com:8080';

  async function naverLogin() {
    try {
      const urlParams = new URLSearchParams(window.location.search);
      const code = urlParams.get('code');
      const state = urlParams.get('state');


      const res = await axios.post(REACT_APP_API + `/api/user-info`, { code: code, state: state });
      const { accessToken, refreshToken, email, name,jwtToken } = res.data;

      // JSON 객체로 변환하여 로컬 스토리지에 저장
      const userInfo = {
        email,
        name,
        accessToken,
        refreshToken,
        jwtToken // jwtToken 추가
      };
      localStorage.setItem('userInfo', JSON.stringify(userInfo));

    //   alert("로그인이 성공했습니다.");
      window.location.href = "/Home";
    } catch (error) {
      console.error("로그인 오류:", error);
    }
  }

  useEffect(() => {
    naverLogin();
  }, []);

  return null;
}

export default NaverRedirect;
