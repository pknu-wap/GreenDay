import logo from "./logo.svg";
import "./App.css";
import { Routes, Route, Link, useNavigate } from "react-router-dom";
import Modal from "./modiary";
import Home from "./Home.js";
import Notice from "./Notice.js";
import History from "./History.js";
import Xlog from "./xlog.js";
import axios from "axios";

import { useState, useEffect } from "react";

function App() {
  const [hello, setHello] = useState("");

  /*   useEffect(() => {
    axios
      .get("/api/hello")
      .then((response) => setHello(response.data))
      .catch((error) => console.log(error));
  }, []); */
  let [buttonOpen, setButtonOpen] = useState(false);
  /*let [shouldRenderApp,setShouldRenderApp]=useState(true);*/

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

  return (
    <>
      <div>
        <Routes>
          <Route path="/Home" element={<Home />}></Route>
          <Route path="/Notice" element={<Notice />}></Route>
          <Route path="/History" element={<History />}></Route>
          <Route path="/" element={<Xlog />}></Route>
        </Routes>

        <Modal isOpen={isModalOpen} onClose={closeModal} />
      </div>
    </>
  );
}

export default App;
