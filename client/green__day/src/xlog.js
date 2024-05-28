import logo from "./logo.svg";
import React, { useState, useEffect } from "react";
import { Routes, Route, Link, useNavigate } from "react-router-dom";
import "./App.css";
import Modal from "./modiary";
import Notice from "./Notice.js";
import History from "./History.js";
import Home from "./Home.js";

function Xlog({ setGetToken, setUserInfo }) {
  const [buttonOpen, setButtonOpen] = useState(false);
  const [isModalOpen, setModalOpen] = useState(false); // useState 사용하여 상태 초기화 및 모달의 열림/닫힘 상태 관리

  const NAVER_CLIENT_ID = "o72MtePRXsbwlztUtJoj"; // 발급 받은 Client ID 입력
  const NAVER_CALLBACK_URL = "http://localhost:8080/login/oauth2/code/naver";

  const initializeNaverLogin = () => {
    if (!window.naver) {
      console.error("Naver SDK not loaded");
      return;
    }

    const naverLogin = new window.naver.LoginWithNaverId({
      clientId: NAVER_CLIENT_ID,
      callbackUrl: NAVER_CALLBACK_URL,
      isPopup: false,
      loginButton: { color: "green", type: 2, height: 0 },
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

  const handleTreeClick = () => {
    initializeNaverLogin();
    // 로그인 버튼을 클릭하도록 트리거
    document.getElementById("naverIdLogin").firstChild.click();
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
  const apples = [
    { id: 1, src: "apple.png", style: { top: "215px", left: "850px" } },
    { id: 2, src: "apple.png", style: { top: "285px", left: "755px" } },
    { id: 3, src: "apple.png", style: { top: "320px", left: "890px" } },
    { id: 4, src: "apple.png", style: { top: "270px", left: "975px" } },
    { id: 5, src: "apple.png", style: { top: "390px", left: "730px" } },
    { id: 6, src: "apple.png", style: { top: "350px", left: "810px" } },
    { id: 7, src: "apple.png", style: { top: "360px", left: "1000px" } },
  ];

  return (
    <>
      <h1>Green Day!</h1>
      <h4>
        Q. 여러분은 평소에 환경을 얼마큼 생각하시나요?
        <br />
        Green Day는 제로-웨이스트 시도 또는 습관을 기르려는 사람들을 위한
        공간입니다.
      </h4>
      <title>네이버 로그인</title>
      <div className="App">
        <div>
          <h5>
            <br />
            환영합니다.
            <br />
            <br />
            <div className="one"></div>
          </h5>

          <ul className="xlog-navigation-menu">
            <li>
              <div onClick={() => alert("로그인을 해주세요")}> 홈 </div>
            </li>
            <br />
            <br />
            <li>
              <div onClick={() => alert("로그인을 해주세요")}>게시판</div>
            </li>
            <br />
            <br />
            <li>
              <div onClick={() => alert("로그인을 해주세요")}>히스토리</div>
            </li>
            <br />
            <br />
            <li>
              <Link to="/home">로그인 후 화면</Link>
            </li>

            <br />
          </ul>

          <Routes>
            <Route path="/Home" element={<Home />}></Route>
            <Route path="/Notice" element={<Notice />}></Route>
            <Route path="/History" element={<History />}></Route>
            <Route path="/Xlog" element={<Xlog />}></Route>
          </Routes>
        </div>
      </div>

      <button className="tree_image" onClick={handleTreeClick}>
        <img src="tree.png" alt="tree" />
      </button>

      {buttonOpen == true ? (
        <div>
          <div className="login_button">
            <img src="a.png" />
          </div>

          <button>
            <div className="login_button_content">
              <img
                src="x.png"
                onClick={() => {
                  setButtonOpen(false);
                }}
              />
            </div>
          </button>
        </div>
      ) : null}

      {/* 구현할 위치에 아래와 같이 코드를 입력해주어야 한다. */}
      {/* 태그에 id="naverIdLogin"를 해주지 않으면 오류가 발생한다! */}
      <div id="naverIdLogin" />
      {apples.map((apple) => (
        <button
          key={apple.id}
          className="apple_image"
          style={{ position: "absolute", ...apple.style }}
        >
          <img
            src={apple.src}
            alt={`Apple ${apple.id}`}
            style={{
              border: "none",
              backgroundcolor: "transparent",
              width: "56px",
              height: "56px",
            }}
          />
        </button>
      ))}
    </>
  );
}
export default Xlog;
