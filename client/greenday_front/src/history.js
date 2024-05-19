import logo from './logo.svg';
import './App.css';
import {Routes, Route,Link, useNavigate} from "react-router-dom";
import Modal from './modiary';
import Home from "./Home.js";
import Notice from "./Notice.js";
import History7day from "./History7day.js";
import { useState } from "react";

function History() {
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
  const applebox = [
    { id: 1, src: 'applebox.png', style: { top: '25%', left: '30%' } },
    { id: 2, src: 'applebox.png', style: { top: '25%', left: '50%' } },
    { id: 3, src: 'applebox.png', style: { top: '25%', left: '70%' } },
    { id: 4, src: 'applebox.png', style: { top: '55%', left: '30%' } },
    { id: 5, src: 'applebox.png', style: { top: '55%', left: '50%' } },
    { id: 6, src: 'applebox.png', style: { top: '55%', left: '70%' } },
  ];

  return (
    <>
    <div className="App">
      <div>
        <h5>
           방문자님,<br />
          환영합니다.<br /><br />
          <div className="one"></div>
        </h5>
        
        <ul className="navigation-menu">
          <li><Link to="/Home">홈</Link></li><br />
          <li><Link to="/Notice">게시판</Link></li><br />
          <li><div className="click">히스토리</div></li><br />
        </ul>
        
        <Routes>
          <Route path="/Home" element={<Home />}></Route>
          <Route path="/Notice" element={<Notice />}></Route>
          <Route path="/History7day" element={<History7day />}></Route>
        </Routes>
      </div>
    <div className="Applebox">
      {applebox.map(applebox => (
        <button 
        key={applebox.id}
        className="applebox_image"
        style={{position: 'absolute', ...applebox.style}}
        >
          번호순
          <img 
          src={applebox.src}
          alt={'Apple ${applebox.id}'}
          style={{ border:'none', width:'130px'}}
          />
        </button>

      ))}

    </div>

      </div>
    </>
  );
}



export default History;