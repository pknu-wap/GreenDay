import logo from "./logo.svg";
import "./App.css";
import Modal from "./modiary";
import {
  Routes,
  Route,
  Link,
  useNavigate,
  useLocation,
} from "react-router-dom";
import Notice from "./Notice.js";
import History from "./History.js";
import Xlog from "./xlog.js";
import React, { useEffect, useState } from "react";
import axios from "axios";

function Home() {
  const location = useLocation();
  const [accessToken, setAccessToken] = useState("");
  const handleTokenReceived = () => {
    // URL에서 토큰 추출
    const hash = location.hash.substring(1);
    const params = new URLSearchParams(hash);
    const accessToken = params.get("access_token");

    // 토큰을 상태에 저장
    if (accessToken) {
      setAccessToken(accessToken);
      console.log(accessToken);

      // 백엔드로 토큰을 보낼 수 있음
      sendTokenToBackend(accessToken);
    }
  };
  const sendTokenToBackend = (token) => {
    axios
      .post("http://localhost:8080/api/user-info", { token })
      .then((response) => {
        console.log("Token sent to backend successfully:", response.data);
      })
      .catch((error) => {
        console.error("Error sending token to backend:", error);
      });
  };

  // let [buttonOpen,setButtonOpen]=useState(false);
  const [isModalOpen, setModalOpen] = useState(false); //useState사용하여 상태 초기화 및 모달의 열림/닫힘 상태관리
  const [buttonOpen, setButtonOpen] = useState(false);

  //모달열기
  const openModal = (event) => {
    event.preventDefault(); // 링크의 기본 동작 방지
    setModalOpen(true); //setModalOpen(true)를 호출하여 isModalOpen 상태를 true로 설정해 모달 열기
  };

  //모달닫기함수
  const closeModal = () => {
    setModalOpen(false); // 모달 닫기
  };

  let [userInformation, setUserInformation] = useState([""]);

  useEffect(() => {
    getBoardList();
    handleTokenReceived();
  }, []);

  const getBoardList = async () => {
    const data = await (
      await axios.get("https://codingapple1.github.io/shop/data2.json")
    ).data;
    setUserInformation(data);
    console.log(userInformation);
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
            <Route path="/Notice" element={<Notice />}></Route>
            <Route path="/History" element={<History />}></Route>
          </Routes>
        </div>
      </div>
      <button
        className="tree_image"
        onClick={() => {
          setButtonOpen(true);
        }}
      >
        <img src="tree.png" />
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
                backgroundcolor: "transparent",
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
