import logo from './logo.svg';
import React, { useState } from 'react';
import {Routes, Route,Link, useNavigate} from "react-router-dom";
import './App.css';
import Modal from './modiary';
import Notice from "./Notice.js";
import History from "./History.js";
import Home from "./Home.js";


function Xlog() {
    const [buttonOpen,setButtonOpen]=useState(false);
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
    <>
      <h1>Green Day!</h1>
      <h4>Q. 여러분은 평소에 환경을 얼마큼 생각하시나요?<br />
Green Day는 제로-웨이스트 시도 또는 습관을 기르려는 사람들을 위한 공간입니다.</h4>
  <title>네이버 로그인</title>
    <div className="App">
      <div>
        <h5>
           방문자님,<br />
          환영합니다.<br /><br />
          <div className="one"></div>
        </h5>
        
        <ul className="xlog-navigation-menu">
          <li><div onClick={() =>alert('사과나무를 눌러서 로그인을 해주세요')}> 홈 </div></li><br /><br />
          <li><div onClick={() =>alert('사과나무를 눌러서 로그인을 해주세요')}>게시판</div></li><br /><br />
          <li><div onClick={() =>alert('사과나무를 눌러서 로그인을 해주세요')}>히스토리</div></li><br /><br />
          <li><div onClick={() =>alert('사과나무를 눌러서 로그인을 해주세요')}>그린일기</div></li><br /><br />
          <li><Link to ='/home'>로그인 후 화면</Link></li><br />
        </ul>
        
        <Routes>
            <Route path="/Home" element={<Home />}></Route>
          <Route path="/Notice" element={<Notice />}></Route>
          <Route path="/History" element={<History />}></Route>
          <Route path="/Xlog" element={<Xlog />}></Route>
        </Routes>

       
      </div>
      
      </div>
      <button className="tree_image" onClick={()=>{setButtonOpen(true)}}>
        <img src='tree.png'a href="APIExamNaverLogin.html" /></button>

        {
          buttonOpen == true ? <div>
          <div className="login_button">
           <img src="a.png" />
          </div>
      
          <button>
            <div className="login_button_content">
              <img src='x.png' onClick={()=>{setButtonOpen(false)}}
              
              />
            </div>
          </button>
         </div> : null
        }

    </>
  );
}
export default Xlog;