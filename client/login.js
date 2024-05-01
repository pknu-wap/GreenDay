// import React, { useState, useEffect } from 'react';
// import './login.css'; 

// const Modal = ({ isOpen, onClose }) => {
//     if (!isOpen) {
//         return null; // 모달이 닫혀 있을 때는 렌더링하지 않음
//     }

//     const handleClose = () => {
//         onClose(); // 모달을 닫기 위해 호출
//     };

//     return (
//         <div className="modal-overlay" onClick={handleClose}>  {/* 배경을 클릭해도 모달 닫기 */}
//             <div className="modal" onClick={(e) => e.stopPropagation()}>  {/* 모달 클릭을 배경 클릭과 구분 */}
//                 <h3>로그인</h3>
//                 <div>
//                 </div>
//             </div>
//         </div>
//     );
// };

// export default Modal;