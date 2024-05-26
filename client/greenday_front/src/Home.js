import logo from './logo.svg';
import './App.css';
import {Routes, Route,Link, useNavigate} from "react-router-dom";
import Modal from './modiary';
import Notice from "./Notice.js";
import History from "./History.js";
import Xlog from "./xlog.js";
import { useState } from "react";


function Home() {
  // let [buttonOpen,setButtonOpen]=useState(false);
  const [isModalOpen, setModalOpen] = useState(false);//useState사용하여 상태 초기화 및 모달의 열림/닫힘 상태관리
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
  
  const apples = [
    { id: 1, src: 'apple.png', style: { top: '215px', left: '850px' } },
    { id: 2, src: 'apple.png', style: { top: '285px', left: '755px' } },
    { id: 3, src: 'apple.png', style: { top: '320px', left: '890px' } },
    { id: 4, src: 'apple.png', style: { top: '270px', left: '975px' } },
    { id: 5, src: 'apple.png', style: { top: '390px', left: '730px' } },
    { id: 6, src: 'apple.png', style: { top: '350px', left: '810px' } },
    { id: 7, src: 'apple.png', style: { top: '360px', left: '1000px' } },
  ];
  return (
    <>
    <div className="App">
      <div>
      <h1>Green Day!</h1>
      <h4>Q. 여러분은 평소에 환경을 얼마큼 생각하시나요?<br />
Green Day는 제로-웨이스트 시도 또는 습관을 기르려는 사람들을 위한 공간입니다.</h4>
        <h5>
           방문자님,<br />
          환영합니다.<br /><br />
          <div className="one"></div>
        </h5>
        
        <ul className="navigation-menu">
          <li><div className="click">홈</div></li><br />
          <li><Link to="/Notice">게시판</Link></li><br />
          <li><Link to ='/History'>히스토리</Link></li><br />
        </ul>
        
        <Routes>
          <Route path="/Notice" element={<Notice />}></Route>
          <Route path="/History" element={<History />}></Route>
        </Routes>

      </div>

      
      </div>
      <button className="tree_image" onClick={()=>{setButtonOpen(true)}}>
        <img src='tree.png'a href="APIExamNaverLogin.html" /></button>

        <div className="App">
      {apples.map(apple => (
        <button
          key={apple.id}
          className="apple_image"
          onClick={openModal}
          style={{ position: 'absolute', ...apple.style }}
        >
          <img 
            src={apple.src} 
            alt={`Apple ${apple.id}`} 
            style={{ border: 'none', backgroundcolor: 'transparent', width: '56px', height: '56px' }}
            />
        </button>
      ))}
      <Modal isOpen={isModalOpen} onClose={closeModal} />
    </div>


      
      
      

    </>
  );
}



export default Home;