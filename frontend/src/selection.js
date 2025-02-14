import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./selection.css";

const Selection = () => {
    const [selectedUser, setSelectedUser] = useState(null);
    const [users, setUsers] = useState([]);
    const navigate = useNavigate();

    const getUsers = async(event) => {
        const response = await fetch('http://localhost:8080/api/users', {
            method: 'GET'
        });
        if (response.ok) {
            const result = await response.json();
            setUsers(
                result.map((teacher, index) => ({
                    id: index + 1,
                    name: teacher,
                }))
            );
        }
    }

    useEffect(() => {
        void getUsers();
    }, []);

    const handleUserSelect = (event) => {
        setSelectedUser(event.target.value);
    };

    const handleStartChat = () => {
        if (selectedUser) {
            navigate("/chat", { state: { userName: selectedUser } });
        }
    };

    const exit = async(event) => {
        const response = await fetch('http://localhost:8080/api/exit', {
            method: 'POST'
        });
    }

    const handleExit = () => {
        void exit();
        navigate("/");
    };

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
        void checkLogin()
    }, []);

    return (
        <div id={"selection-container"}>
            <div id="selection">
                <button id="exit" type="button" name="exit" onClick={handleExit}>
                    <span>Выйти</span>
                </button>
                <h1>Выбор преподавателя</h1>
                <select onChange={handleUserSelect} size={8}>
                    {users.map((user) => (
                        <option key={user.id} value={user.name}>
                            {user.name}
                        </option>
                    ))}
                </select>
                <button onClick={handleStartChat} disabled={!selectedUser}>
                    Начать чат
                </button>
            </div>
        </div>
    );
};

export default Selection;