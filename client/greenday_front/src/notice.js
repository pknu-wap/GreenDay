import logo from "./logo.svg";
import "./App.css";
import { Routes, Route, Link, useNavigate } from "react-router-dom";
import Modal from "./modiary";
import Home from "./Home.js";
import History from "./History.js";

import { useState } from "react";

function Notice() {
  let [buttonOpen, setButtonOpen] = useState(false);
  let [text, setText] = useState("");
  let onChange = (event) => {
    setText(event.target.value);
  };
  let [oldText, setOldText] = useState("");

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

  return (
    <div className="Notice">
      <div className="App">
        <div>
          <h5>
            방문자님,
            <br />
            환영합니다.
            <br />
            <br />
            <div className="one"></div>
          </h5>
          <ul className="navigation-menu">
            <li>
              <Link to="/Home">홈</Link>
            </li>
            <br />
            <li>
              <div className="click">게시판</div>
            </li>
            <br />
            <li>
              <Link to="/History">히스토리</Link>
            </li>
            <br />

          </ul>
          <Routes>
            <Route path="/Home" element={<Home />}></Route>
            <Route path="/History" element={<History />}></Route>
          </Routes>
          <Modal isOpen={isModalOpen} onClose={closeModal} />{" "}
          {/* 모달을 닫기 위한 콜백 전달 */}
        </div>
      </div>

      <input
        className="input"
        style={{ whiteSpace: "pre-wrap" }}
        onChange={onChange}
        value={text}
      ></input>
      <div>
        <button className="backrock_button" style={{ whiteSpace: "pre-wrap" }}>
          <img
            src="backrock_button.png"
            onClick={() => {
              setOldText({ text });
              setOldText(text);
            }}
          />
        </button>
      </div>

      <div className="input_data_list">
        <div className="input1">wjddkfks05@gmail.com</div>
        <div className="input2" style={{ whiteSpace: "pre-wrap" }}>
          {oldText}
        </div>
      </div>
    </div>
  );
}

export default Notice;