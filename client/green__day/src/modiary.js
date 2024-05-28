import { useNavigate, Route, Routes} from "react-router-dom";
import React, { useState, useEffect } from 'react';
import './modiary.css'; 
import axios from 'axios';


const Modal = ({ isOpen, onClose, onSubmit }) => {
    const [text, setText] = useState(''); //text가 값 추적하고 setText함수 통해 상태
    const [message, setMessage] = useState('');
    const [placeholder, setPlaceholder] = useState('');
    const [title, setTitle] = useState('오늘의 그린일기');
    const navigate = useNavigate();

const proverbs = [
    "일회용품", "재활용", "물", "전기", "배달", "걷기", "카페-텀블러", "분리수거", "제로웨이스트쇼핑", "친환경", 
    "멸종위기", "에코백", "설거지", "자전거", "대중교통", "미세먼지", "식물키우기", "손빨래", 
    "플로깅", "봉사활동", "포장", "헌옷수거"
    ];

    const handleInputChange = (event) => { 
        setText(event.target.value); 
    };

    const getRandomIndex = (length) => {
        return Math.floor(Math.random() * length)
    };

    useEffect(() => {
        if (isOpen) {
        setPlaceholder(proverbs[getRandomIndex(proverbs.length)]);
        setTitle(proverbs[getRandomIndex(proverbs.length)]);
        }
    }, [isOpen]);

    const handleSubmit = async () => { //입력 필드에서 입력이 변경될 때마다 호출되며, 입력된 값을 setText를 통해 상태 text에 저장
        if (text.trim() === '') { //입력필드가 비어있는지 검사, 공백이나 빈칸 제출 못하게함
            setMessage('입력된 내용이 없습니다.');
            return;
        }
        
        onSubmit(text); // 글 등록 버튼 클릭 시 동작
        setText(''); // 텍스트 입력칸을 초기화=> 다음 입력위해 빈필드갖게함
        setMessage('저장됐습니다.');
        onClose(); //저장하면 하로 닫기게함 지체ㄴㄴ
    };

    if (!isOpen) {
        return null; // 모달이 닫혀 있을 때는 렌더링하지 않음
    }
    
    return (
            <div className="modal">
                    <button className="Close-button" onClick={onClose}>close</button>  /*모달닫는버튼*/
                    <h3>{title}</h3>
                    <div style={{ display: isOpen ? 'block' : 'none' }}> //isOpen이'block'이면 요소가 보이고, 'none'이면 요소가 보이지 않음
                    <textarea 
                        value={text} //입력된 값을 text와 연결
                        onChange={handleInputChange} // 입력된 텍스트 변경 시 상태 업데이트
                        placeholder="오늘의 일기를 작성하세요"
                    ></textarea>
                    </div>
                    
                    {/* axios.post('/wirte_diary', {"diary_content":"오늘의 일기 내용","login_id": "사용자 ID"}) */}

                    {/* <button className="Save-button" onSubmit={handleSubmit} onClick={onClose} > */}
                    {/* onSubmit 이벤트는 form 요소에서 사용되어야 하므로, button 요소에서는 제거 */}
                    <button className="Save-button" onClick={handleSubmit} >
                   
                        <img src='apple.png' alt="저장됐습니다."/></button>
                        {message && <p>{message}</p>}
             </div>
    );
};

export default Modal;