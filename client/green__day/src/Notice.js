import React, { useEffect, useState } from "react";
import { Routes, Route, Link, useNavigate } from "react-router-dom";
import axios from "axios";
import Modal from "./modiary.js";
import Home from "./Home.js";
import History from "./history.js";
import Pagination from "react-js-pagination";
import "./App.css";

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
    const userInfoFromStorage = localStorage.getItem("userInfo");
    if (userInfoFromStorage) {
      setUserInformation(JSON.parse(userInfoFromStorage));
      console.log(userInfoFromStorage); // 유저 정보를 콘솔에 출력
    }
    getBoardList();
  }, []);

  const getBoardList = async () => {
    const data = await (
      await axios.get("https://codingapple1.github.io/shop/data2.json")
    ).data;
    setUserInformation(data);
    console.log(userInformation);
  };
  const navigate = useNavigate();

  const [page, setPage] = useState(1);
  const [items, setItems] = useState(2);
  const handlePageChange = (page) => {
    setPage(page);
  };
  const itemChange = (e) => {
    setItems(Number(e.target.value));
  };

  // 입력 데이터를 서버로 전송하는 함수
  const sendDataToServer = async (data) => {
    try {
      const response = await axios.post(
        "https://codingapple1.github.io/shop/data2.json",
        data
      );
      console.log("성공:", response.data);
    } catch (error) {
      console.error("실패:", error);
    }
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
      <div className="input_data_list">
        {/*         <div className="input1">{oldText}</div> */}
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
              alt="backrock button"
              onClick={() => {
                setOldText({ text });
                setOldText(text);

                // 서버로 데이터 전송
                sendDataToServer({ text });
              }}
            />
          </button>
        </div>

        {userInformation
          .slice(items * (page - 1), items * (page - 1) + items)
          .map((a, i) => {
            return (
              <div key={i}>
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
            );
          })}
        <>
          <Pagination
            className="pagination"
            activePage={page}
            itemsCountPerPage={items}
            totalItemsCount={userInformation.length}
            pageRangeDisplayed={5}
            onChange={handlePageChange}
          ></Pagination>
        </>
        {/* npm install react-js-pagination
        yarn add react-js-pagination */}
      </div>
    </div>
  );
}

export default Notice;
