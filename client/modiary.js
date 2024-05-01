import React, { useState, useEffect } from 'react';
import './modiary.css'; 

const Modal = ({ isOpen, onClose, onSubmit }) => {
    const [text, setText] = useState(''); //text가 값 추적하고 setText함수 통해 상태
    const [message, setMessage] = useState('');

    const handleInputChange = (event) => { //입력필드의 값이 변경될때 호출되는 함수
        setText(event.target.value); //setText통해 text에 저장하는데 event를 인자로 받아event.target.value를 통해 입력 필드의 현재값을 받아 setText를 사용하여 업데이트
    };

    const handleSubmit = () => { //입력 필드에서 입력이 변경될 때마다 호출되며, 입력된 값을 setText를 통해 상태 text에 저장
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

    // const handleOverlayClick = (event) => { // 모달 외부를 클릭했을 때 닫기
    //     if (event.target === event.currentTarget) {
    //         onClose(); //이걸 호출해서 닫음 
    //     }
    // };
    
    return (
        // <div className="modal-overlay" onClick={handleOverlayClick}>
            <div className="modal">
                    <button className="Close-button" onClick={onClose}>close</button>  /*모달닫는버튼*/
                    <h3>오늘의 그린일기</h3>
                    <div style={{ display: isOpen ? 'block' : 'none' }}> //isOpen이'block'이면 요소가 보이고, 'none'이면 요소가 보이지 않음
                    <textarea
                        value={text} //입력된 값을 text와 연결
                        onChange={handleInputChange} // 입력된 텍스트 변경 시 상태 업데이트
                        placeholder="오늘의 일기를 작성하세요"
                    ></textarea>
                    </div>
                    <button className="Save-button" onClick={handleSubmit}>
                        <img src='apple.png'/></button>
                        {/* {message && <p>{message}</p>} */}
             </div>
        // </div>
    );
};

export default Modal;
