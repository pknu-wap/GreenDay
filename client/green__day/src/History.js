import logo from "./logo.svg";
import "./App.css";
import { Routes, Route, Link, useNavigate } from "react-router-dom";
import Modal from "./modiary.js";
import Home from "./Home.js";
import Notice from "./Notice.js";
import React, { useEffect, useState } from "react";
import axios from "axios";

function History() {
  const [diary, setDiary] = useState([]);
  const [page, setPage] = useState(1);
  const navigate = useNavigate();

  const MyComponent = () => {
    const [data, setData] = useState(null);
  };

  // const getDiary = async () => {
  //   const data = await (
  //     await axios.get("https://codingapple1.github.io/shop/data2.json")
  //   ).data; // 2) 게시글 목록 데이터에 할당
  //   setBoardList(data); // 3) boardList 변수에 할당
  //   console.log(boardList);
  //   console.log(data);
  // };

  // useEffect(() => {
  //   getBoardList(); // 1) 게시글 목록 조회 함수 호출
  // }, []);

  useEffect(() => {
    axios.get("").then((response) => {
      console.log(response);
    });
  }, []);

  useEffect(() => {
    const fetchDiary = async () => {
      try {
        const response = await fetch(
          `https://codingapple1.github.io/shop/data2.json`
        );
        const data = await response.json();
        setDiary(data);
      } catch (error) {
        console.error("Error fetching diary:", error);
      }
    };

    fetchDiary();
  }, [page]);
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
    <>
      <div className="App">
        <div>
          <h5>
            {userInformation[0].title}님,
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
              <Link to="/Notice">게시판</Link>
            </li>
            <br />
            <li>
              <div className="click">히스토리</div>
            </li>
            <br />
          </ul>

          <Routes>
            <Route path="/Home" element={<Home />}></Route>
            <Route path="/Notice" element={<Notice />}></Route>
          </Routes>
        </div>

        <h1>your history!</h1>

        <div>
          {diary.map((diary, index) => (
            <div className="history">
              <div className="day">
                {diary.id}
                <br />
              </div>
              <div className="content">{diary.title}</div>
              {/* <li key={index}>{diary.id}</li>
              <li key={index}>{diary.title}</li><br /> */}
            </div>
          ))}
        </div>

        {/* 7개씩 데이터를 뜨게 하는 코드 */}
        {/* {diary.map((a, i) => {
            return <Card diary ={i} key={i}></Card>
          })} */}

        <div className="nextbutton">
          <button onClick={() => setPage(page - 1)} disabled={page === 1}>
            이전
          </button>
          <button onClick={() => setPage(page + 1)}>다음</button>
        </div>
      </div>
    </>
  );
}

export default History;
