import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Modal from './modiary';
import Home from "./App";
import Notice from "./notice.js";
import History from "./history.js";
import Greenmate from "./greenmate.js";
import Login from "./login.js";


import React,{ useState } from "react";


function App() {
  const [buttonOpen,setButtonOpen]=useState(false);
  
  const openButton=()=>{
    setButtonOpen(true);
  };

  const closeButton=()=>{
    setButtonOpen(false);
  };

  const [isModalOpen, setModalOpen] = useState(false);//useState사용하여 상태 초기화 및 모달의 열림/닫힘 상태관리
  
  //모달열기
  const openModal = (event) => {
    event.preventDefault(); // 링크의 기본 동작 방지
    setModalOpen(true); //setModalOpen(true)를 호출하여 isModalOpen 상태를 true로 설정해 모달 열기
  };

  //모달닫기함수
  const closeModal = () => {
    setModalOpen(false); // 모달 닫기
  };

 
  return (
    <Router>
    <div className="App">
      <body>
        <h1>
           방문자님,<br />
          환영합니다.<br /><br />
          <div className="one"></div>
        </h1>
        
        <ul className="navigation-menu">
          <li><a href="App.js">홈</a></li><br />
          <li><a href="notice.js">게시판</a></li><br />
          <li><a href="history.js">히스토리</a></li><br />
          <li><a href="greenmate.js">그린메이트</a></li><br />
          <li><a href="#" onClick={openModal}>그린일기</a></li><br />
        </ul>
        
        <Routes>
          <Route path="/home" element={<Home />}></Route>
          <Route path="/notice" element={<Notice />}></Route>
          <Route path="/history" element={<History />}></Route>
          <Route path="/greenmate" element={<Greenmate />}></Route>
          <Route path="/login" element={<Login />}></Route>
        </Routes>

        <Modal isOpen={isModalOpen} onClose={closeModal} /> {/* 모달을 닫기 위한 콜백 전달 */}
      </body>

      <div>
        
        
      {buttonOpen && (
        <div className="button">
          <div className="button_content">
            <button onClick={closeButton}>닫기</button>
          </div>
        </div>
      )}
      </div>

      <button className="tree_image" onClick={openButton}>
        <img src='tree.png'/></button>
          </div>
    </Router>
  );
}

export default App;
