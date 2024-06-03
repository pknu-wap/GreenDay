import logo from "./logo.svg";
import "./App.css";
import { Routes, Route, Link, useNavigate } from "react-router-dom";
import Modal from "./modiary";
import Home from "./Home.js";
import Notice from "./notice.js";
import History from "./history.js";
import Xlog from "./xlog.js";
import axios from "axios";
import NaverRedirect from "./NaverRedirect.js";


import { useState, useEffect } from "react";

function App() {
  const [hello, setHello] = useState("");

  let [buttonOpen, setButtonOpen] = useState(false);
  /*let [shouldRenderApp,setShouldRenderApp]=useState(true);*/

  const [isModalOpen, setModalOpen] = useState(false); //useState사용하여 상태 초기화 및 모달의 열림/닫힘 상태관리

  //모달열기
  const openModal = (event) => {
    event.preventDefault(); // 링크의 기본 동작 방지
    setModalOpen(true); //setModalOpen(true)를 호출하여 isModalOpen 상태를 true로 설정해 모달 열기
  };

  //모달닫기함수
  const closeModal = () => {
    setModalOpen(false); // 모달 닫기
  };

  //   useEffect(() => {
  //   fetchData();
  // }, []);

  // async function fetchData() {
  //   try {
  //     const response = await fetch('https://api.example.com/visitor');
  //     const data = await response.json();
  //     setVisitorData(data); // 서버에서 받아온 데이터를 상태에 저장
  //   } catch (error) {
  //     console.error('데이터를 가져오는 중 오류 발생:', error);
  //   }
  // }
  /*  useEffect(() => {
      const path = window.location.pathname;
      const excludedPaths = ['/History', '/Notice',"/Home"]; // 제외할 경로들
      setShouldRenderApp(!excludedPaths.includes(path));
    }, []);
  
    if (!shouldRenderApp) {
      return null; 
    };
  */

  return (
    <>
      <div>
        <Routes>

          <Route path="/authuser" element={<NaverRedirect />}></Route>
          <Route path="/Home" element={<Home />}></Route>
          <Route path="/Notice" element={<Notice />}></Route>
          <Route path="/History" element={<History />}></Route>
          <Route path="/Xlog" element={<Xlog />}></Route>
          <Route path="/" element={<Xlog />}></Route>
        </Routes>

        <Modal isOpen={isModalOpen} onClose={closeModal} />
      </div>
    </>
  );
}

export default App;
