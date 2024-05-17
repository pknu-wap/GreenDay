import './App.css';
import {Routes, Route,Link, useNavigate, Outlet} from "react-router-dom";
import Modal from './modiary';
import Home from "./App";
import Notice from "./notice.js";
import History from "./history.js";


import { useState } from "react";


function Greenmate() {
  let [buttonOpen,setButtonOpen]=useState(false);
  let navigate=useNavigate();
  

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
        
        <ul className="navigation-menu">
          <li onClick={()=>{navigate("/app")}}>홈</li><br />
          <li onClick={()=>{navigate("/notice")}}>게시판</li><br />
          <li onClick={()=>{navigate("/history")}}>히스토리</li><br />
          <li onClick={()=>{navigate("greenmate")}}>그린메이트</li><br />
          <li><a href="#" onClick={openModal}>그린일기</a></li><br />
        </ul>
        
        <Routes>
          <Route path="/home" element={<Home />}></Route>
          <Route path="/notice" element={<Notice />}></Route>
          <Route path="/history" element={<History />}></Route>
          <Route path="/greenmate" element={<Greenmate />}></Route>
        </Routes>

        <Modal isOpen={isModalOpen} onClose={closeModal} /> {/* 모달을 닫기 위한 콜백 전달 */}
      
      
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
              <img src='x.png' onClick={()=>{setButtonOpen(false)}}/>
            </div>
          </button>
         </div> : null
        }

    </>
  );
}

export default Greenmate;