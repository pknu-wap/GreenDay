import { useEffect } from "react";

function NaverLogin({ setGetToken, setUserInfo }) {
  const NAVER_CLIENT_ID = "o72MtePRXsbwlztUtJoj"; // 발급 받은 Client ID 입력
  const NAVER_CALLBACK_URL = "http://ec2-3-36-87-184.ap-northeast-2.compute.amazonaws.com/authuser";

  const initializeNaverLogin = () => {
    if (!window.naver) {
      console.error("Naver SDK not loaded");
      return;
    }

    const naverLogin = new window.naver.LoginWithNaverId({
      clientId: NAVER_CLIENT_ID,
      callbackUrl: NAVER_CALLBACK_URL,
      isPopup: false,
      loginButton: { color: "green", type: 1, height: 30 },
      callbackHandle: true,
    });
    naverLogin.init();

    naverLogin.getLoginStatus(async function (status) {
      if (status) {
        const user = {
          id: naverLogin.user.getEmail(),
          name: naverLogin.user.getName(),
        };
        setUserInfo(user);
      }
    });
  };

  const userAccessToken = () => {
    const url = new URL(window.location.href);
    const token = url.searchParams.get("access_token");
    if (token) {
      getToken(token);
    }
  };

  const getToken = (token) => {
    console.log(token);
    localStorage.setItem("access_token", token);
    setGetToken(token);
  };

  useEffect(() => {
    const loadNaverSDK = () => {
      if (window.naver) {
        initializeNaverLogin();
      } else {
        setTimeout(loadNaverSDK, 100);
      }
    };

    loadNaverSDK();
    userAccessToken();
  }, []);

  return (
    <>
      {/* 구현할 위치에 아래와 같이 코드를 입력해주어야 한다. */}
      {/* 태그에 id="naverIdLogin"를 해주지 않으면 오류가 발생한다! */}
      <div id="naverIdLogin" />
    </>
  );
}

export default NaverLogin;
