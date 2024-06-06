import React, { useEffect, useState } from "react";
import {
  Routes,
  Route,
  Link,
  useNavigate,
  useLocation,
} from "react-router-dom";
import axios from "axios";
import Modal from "./modiary";
import Notice from "./notice.js";
import History from "./history.js";
import Xlog from "./xlog.js"; // Xlog 컴포넌트 추가
import "./App.css";

function Home() {
  const location = useLocation();
  const [userInformation, setUserInformation] = useState({});
  const [isModalOpen, setModalOpen] = useState(false); // 모달 상태 추가
  const navigate = useNavigate();

  const handleButtonClick = () => {
    console.log(userInformation);
  };

  // 컴포넌트가 마운트될 때 로컬 스토리지에서 사용자 정보 가져오기
  useEffect(() => {
    const userInfoFromStorage = localStorage.getItem("userInfo");
    if (userInfoFromStorage) {
      setUserInformation(JSON.parse(userInfoFromStorage));
      console.log(userInfoFromStorage); // 유저 정보를 콘솔에 출력
    }
  }, []);

  const openModal = () => {
    setModalOpen(true); // 모달 열기
  };

  const closeModal = () => {
    setModalOpen(false); // 모달 닫기
  };

  const handleNaverLogout = () => {
    const accessToken = JSON.parse(
      localStorage.getItem("userInfo")
    )?.accessToken;

    if (!accessToken) {
      console.error("No access token found");
      return;
    }
    axios
      .post("http://localhost:8080/api/logout", { accessToken })
      .then((response) => {
        if (response.status === 200) {
          // Remove all local storage data
          localStorage.clear();

          // 네이버 로그아웃 URL을 새 창으로 열기
          const logoutWindow = window.open(
            `https://nid.naver.com/nidlogin.logout`,
            "_blank"
          );

          // 일정 시간(예: 3초) 후 새 창을 닫고 원래 창을 리다이렉트
          setTimeout(() => {
            if (logoutWindow) {
              logoutWindow.close();
            }
            // 현재 창을 http://localhost:3000/로 리다이렉트
            window.location.href = `http://localhost:3000/`;
          }, 3000); // 3초 후 새 창 닫기 및 리다이렉트
        }
      })
      .catch((error) => {
        console.error("Logout error:", error);
      });
  };


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
      <div className="App">
        <div>
          <h1>Green Day!</h1>
          <h4>
            Q. 여러분은 평소에 환경을 얼마큼 생각하시나요?
            <br />
            Green Day는 제로-웨이스트 시도 또는 습관을 기르려는 사람들을 위한
            공간입니다.
          </h4>
          <h5>
            {userInformation.name}님,
            <br />
            환영합니다.
            <br />
            <br />
            <div className="one"></div>
          </h5>
          <ul className="navigation-menu">
            <li>
              <div className="click">홈</div>
            </li>
            <br />
            <li>
              <Link to="/Notice">게시판</Link>
            </li>
            <br />
            <li>
              <Link to="/History">히스토리</Link>
            </li>
            <br />
          </ul>

          <Routes>
            <Route path="/Notice" element={<Notice />} />
            <Route path="/History" element={<History />} />
            <Route path="/Xlog" element={<Xlog />} /> {/* Xlog 경로 추가 */}
          </Routes>
        </div>
      </div>
      <button className="Logout" onClick={handleNaverLogout}>
        <img className="Logout" src="logout.png" alt="logout" />
      </button>
      <button className="tree_image" onClick={() => setModalOpen(true)}>
        <img src="tree.png" alt="tree" />
      </button>

      <div className="App">
        {apples.map((apple) => (
          <button
            key={apple.id}
            className="apple_image"
            onClick={openModal}
            style={{ position: "absolute", ...apple.style }}
          >
            <img
              src={apple.src}
              alt={`Apple ${apple.id}`}
              style={{
                border: "none",
                backgroundColor: "transparent",
                width: "56px",
                height: "56px",
              }}
            />
          </button>
        ))}
        <Modal isOpen={isModalOpen} onClose={closeModal} />
      </div>
    </>
  );
}

export default Home;