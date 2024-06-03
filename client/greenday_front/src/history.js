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

  // 히스토리 받아오는 코드 
  useEffect(() => {
    const fetchDiary = async () => {
      try {
        const response = await fetch(`https://codingapple1.github.io/shop/data2.json`);
        const data = await response.json();
        setDiary(data);
      } catch (error) {
        console.error('Error fetching diary:', error);
      }};
      fetchDiary();
    }, [page]);


    // __님 환영합니다를 받는 코드 
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
                <div className="space">
                  작성한 날짜 {'<'}{diary.id}{'>'}
                </div>
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

        <div className="historybutton">
          <div className ="beforebutton"><button onClick={() => setPage(page - 1)} disabled={page === 1}>이전</button></div>
          <div className ="nextbutton"><button onClick={() => setPage(page + 1)}>다음</button></div>
        </div>
      </div>
    </>
  );
}

export default History;

