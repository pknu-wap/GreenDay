import logo from "./logo.svg";
import "./App.css";
import { Routes, Route, Link, useNavigate } from "react-router-dom";
import Modal from "./modiary.js";
import Home from "./Home.js";
import History from "./History.js";

import axios from "axios";
import React, { useEffect, useState } from "react";

function Notice() {
  let [text, setText] = useState("");
  const [length, setLength] = useState(0);
  let onChange = (event) => {
    const value = event.target.value;
    setText(value);
    setLength(value.length);
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

  let [userInformation, setUserInformation] = useState([""]);

  useEffect(() => {
    getBoardList();
  }, []);

  const getBoardList = async () => {
    const data = await (
      await axios.get("https://codingapple1.github.io/shop/data2.json")
    ).data;
    setUserInformation(data);
    console.log(userInformation);
  };

  return (
    <div>
      <div>
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
      {userInformation.map((a, i) => (
        <div>
          <div className="line1" />
          <div className="userdata">
            <div className="bar">
              <div className="title">{a.title}</div>
              <div className="writetime">Price:{a.price}</div>
            </div>
            <div className="noticeContent">{a.content}</div>
            <br />
            <br />
            <br />
          </div>
        </div>
      ))}
      <div className="input_data_list">
        <div className="input1">{oldText}</div>
        <textarea
          className="input"
          placeholder="내용을 입력하세요"
          style={{ whiteSpace: "pre-wrap" }}
          onChange={onChange}
          value={text}
          maxLength={199}
        ></textarea>
        <div className="inputLength">{length}/200자</div>
        <div>
          <button
            className="backrock_button"
            style={{ whiteSpace: "pre-wrap" }}
          >
            <img
              src="backrock_button.png"
              onClick={() => {
                setOldText({ text });
                setOldText(text);
              }}
            />
          </button>
        </div>
      </div>
    </div>
  );
}

export default Notice;
