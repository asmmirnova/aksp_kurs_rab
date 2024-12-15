import React, { useState, useEffect } from 'react';
import "./chat.css"
import {useNavigate, useLocation} from "react-router-dom";

const Chat = () => {
    const [messages, setMessages] = useState([]);
    const [inputValue, setInputValue] = useState('');
    const navigate = useNavigate();
    const location = useLocation();

    const sendMessage = async() => {
        if (!inputValue.trim()) return;
        const data_ = {
            text: inputValue,
        };
        await fetch('http://localhost:8080/api/save_message', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data_)
        });

        setInputValue('');
    };

    const handleInputChange = (event) => {
        setInputValue(event.target.textContent);
    };

    const handleEndChat = () => {
        navigate("/");
    };

    const getMessages = async(event) => {
        const { userName } = location.state
        const response = await fetch('http://localhost:8080/api/select_chat', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name: userName })
        });
        if (response.ok) {
            const result = await response.json();
            setMessages(
                result
            );
        }
    }

    const checkLogin = async(event) => {
        const response = await fetch('http://localhost:8080/api/check_login', {
            method: 'GET'
        });
        if (response.ok) {
            const result = await response.json();
            if (result === false) {
                navigate("/")
            }
        }
    }

    useEffect(() => {
        void checkLogin();
        void getMessages();
        const timer = setInterval(getMessages, 1000);
        return () => clearInterval(timer);
    }, []);

    return (
        <div id="chat-container">
            <button id="back" type="button" name="back" onClick={handleEndChat}>
                <span>Назад</span>
            </button>
            <div id="chat">
                <div id="message-box">
                    {messages.map((msg) => (
                        <div className={`message ${msg.from.name === location.state.userName ? "other" : "user"}`}>
                            <p className="content">{msg.text}</p>
                            <p className="time">{new Date(msg.date).toLocaleString()}</p>
                        </div>
                    ))}
                </div>
                <div id="input">
                    <div
                        id="field"
                        role="textbox"
                        contentEditable
                        onInput={handleInputChange}
                        suppressContentEditableWarning={true}
                    ></div>
                    <button id="submit" type="button" name="submit" onClick={sendMessage}>
                        <span>Отправить</span>
                    </button>
                </div>
            </div>
        </div>
    );
};

export default Chat;
