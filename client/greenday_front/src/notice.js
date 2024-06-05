import React, { useEffect, useState } from "react";
import { Routes, Route, Link, useNavigate } from "react-router-dom";
import axios from "axios";
import Modal from "./modiary.js";
import Home from "./Home.js";
import History from "./history.js";
import Pagination from "react-js-pagination";
import "./App.css";

function Notice() {
  const [userInformation, setUserInformation] = useState({});
  let [text, setText] = useState("");
  const [length, setLength] = useState(0);
  let onChange = (event) => {
    const value = event.target.value;
    setText(value);
    setLength(value.length);
  };
  let [oldText, setOldText] = useState("");

  const [isModalOpen, setModalOpen] = useState(false);

  const openModal = (event) => {
    event.preventDefault();
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
  };

  let [userWriteInformation, setUserWriteInformation] = useState([""]);

  useEffect(() => {
    getBoardList();
  }, []);

  // 게시판 목록 가져오는 코드
  const getBoardList = async () => {
      const data = await (
        await axios.get("http://localhost:8080/api/board/list?page=0&size=100")
      ).data;
    // // 받아온 데이터를 내림차순으로 정렬
    const sortedData = data.content.sort((a, b) => new Date(b.createdDate) - new Date(a.createdDate));
    setUserWriteInformation(data.content);
    console.log(userWriteInformation);

    // // // 받아온 데이터를 내림차순으로 정렬
    // const sortedData = data.content.sort(
    //   (a, b) => new Date(b.createdDate) - new Date(a.createdDate)
    // );
  };

  const api = axios.create({
    baseURL: "http://localhost:8080/api/board",
  });

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const jwtToken = localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo")).jwtToken
        : null;
      if (!jwtToken) {
        console.error("JWT 토큰이 없습니다.");
        return;
      }

      const response = await fetch("http://localhost:8080/api/board/write", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${jwtToken}`,
        },
        body: JSON.stringify({ text }),
      });

      if (!response.ok) {
        console.error("요청 실패:", response.statusText);
        return;
      }

      const data = await response.json();
      alert("등록되었습니다.");
      console.log("응답 데이터:", data);

      setText("");
      setLength(0);
      getBoardList(); // 새로 게시글 목록을 가져옵니다.
    } catch (error) {
      console.error("API 요청 오류:", error);
    }
  };

  let [email, setEmail] = useState("");
  let [jwtToken, setjwtToken] = useState("");

  useEffect(() => {
    const userInfoFromStorage = localStorage.getItem("userInfo");
    if (userInfoFromStorage) {
      const userInfo = JSON.parse(userInfoFromStorage);
      setUserInformation(userInfo);
      setEmail(userInfo.email);
      setjwtToken(userInfo.jwtToken);
    }
  }, []);

  const navigate = useNavigate();

  const [page, setPage] = useState(1);
  const [items, setItems] = useState(4);
  const handlePageChange = (page) => {
    setPage(page);
  };
  const itemChange = (e) => {
    setItems(Number(e.target.value));
  };

  const sendDataToServer = async (data) => {
    try {
      const jwtToken = localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo")).jwtToken
        : null;
      if (!jwtToken) {
        console.error("JWT 토큰이 없습니다.");
        return;
      }

      const response = await api.post("/write", data, {
        headers: { Authorization: `Bearer ${jwtToken}` },
      });
      console.log("성공:", response.data);
      getBoardList(); // 새로 게시글 목록을 가져옵니다.
    } catch (error) {
      console.error("실패:", error);
    }
  };

  const sendUpdateToServer = async (id, data) => {
    try {
      const jwtToken = localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo")).jwtToken
        : null;
      if (!jwtToken) {
        console.error("JWT 토큰이 없습니다.");
        return;
      }

      const response = await api.put(`/update/${id}`, data, {
        headers: { Authorization: `Bearer ${jwtToken}` },
      });
      console.log("수정 성공:", response.data);
      setUserWriteInformation(
        userWriteInformation.map((item) =>
          item.id === id ? { ...item, ...data } : item
        )
      );
    } catch (error) {
      console.error("수정 실패:", error);
    }
  };

  const sendDeleteToServer = async (id) => {
    try {
      const jwtToken = localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo")).jwtToken
        : null;
      if (!jwtToken) {
        console.error("JWT 토큰이 없습니다.");
        return;
      }

      const response = await api.delete(`/delete/${id}`, {
        headers: { Authorization: `Bearer ${jwtToken}` },
      });
      console.log("삭제 성공:", response.data);
      setUserWriteInformation(userWriteInformation.filter((item) => item.id !== id));
    } catch (error) {
      console.error("삭제 실패:", error);
    }
  };

  const loadDataToTextarea = (id, content) => {
    setText(content);
    setLength(content.length);
    setEditId(id);
  };

  const [modifyAndDelete, setModifyAndDelete] = useState(true);
  const [editId, setEditId] = useState(null);

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
        <Modal isOpen={isModalOpen} onClose={closeModal} />
      </div>
      <div className="input_data_list">
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
            onClick={() => {
              setOldText(text);
              alert(editId ? "수정되었습니다." : "등록되었습니다.");
              if (editId) {
                sendUpdateToServer(editId, { content: text });
                setEditId(null);
              } else {
                sendDataToServer({ content: text });
              }
              setText("");
              setLength(0);
            }}
          >
            <img
              src="backrock_button.png"
              alt="backrock button"
            />
          </button>
        </div>
        {userWriteInformation
          .slice(items * (page - 1), items * (page - 1) + items)
          .map((a, i) => {
            const canModifyAndDelete = email === a.userEmail; // 현재 사용자가 작성한 글인지 확인

            return (
              <div key={i}>
                <div className="line1" />
                <div className="userdata">
                  <div className="bar">
                    <div className="title">{a.userEmail}</div>
                    <div className="writetime">
                      작성일:{" "}
                      {a.createdDate ? a.createdDate.substring(0, 10) : ""}
                    </div>
                  </div>
                  {canModifyAndDelete && (
                    <div>
                      <button
                        className="delete"
                        onClick={() => sendDeleteToServer(a.id)}
                      >
                        <img src="deleteButton.png" alt="delete button" />
                      </button>
                      <button
                        className="modify"
                        onClick={() => loadDataToTextarea(a.id, a.content)}
                      >
                        <img src="modifyButton.png" alt="modify button" />
                      </button>
                    </div>
                  )}
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
            totalItemsCount={userWriteInformation.length}
            pageRangeDisplayed={5}
            onChange={handlePageChange}
          ></Pagination>
        </>
      </div>
    </div>
  );
}

export default Notice;
