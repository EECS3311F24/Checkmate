import React, { useState, useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { getUserData, updateUserData, logout, deleteUser } from '../services/UserService';
import { getTranslation, useLanguage } from './LanguageProvider';

const UserDataFormComponent = () => {
    const navigator = useNavigate();
    const { language, setLanguage } = useLanguage();
    const [ userLanguage, setUserLanguage ] = useState('');
    const [ theme, setUserTheme ] = useState('');
    const [userData, setUserData] = useState([])
    const { id } = useParams();

    useEffect(() => {
        getData();
    }, [])

    function getData() {
        getUserData(id).then((response) => {
            setUserData(response.data);
            setLanguage(convertLanguage(response.data.language))
            setUserLanguage(response.data.language);
            setUserTheme(response.data.theme);
        }).catch(error => {
            console.error(error);
        })
    }

    function convertLanguage(language) {
        if (language === "ENGLISH") return "en";
        if (language === "FRENCH") return "fn";
        if (language === "SPANISH") return "es";
        return language;
    }

    function updateData() {
        if (id) {
            const data = { language: userLanguage, theme, wins: userData.wins, loses: userData.loses, gamesPlayed: userData.gamesPlayed }
            updateUserData(id, data).then((response) => {
                setLanguage(convertLanguage(response.data.language))
                console.log(response.data);
            }).catch(error => {
                console.error(error);
            })
        }
    }

    function editUser() {
        navigator(`/edit-user/` + id)
    }

    function logoutUser() {
        if (id) {
            logout().then((response) => {
                console.log(response.data);
                navigator("/")
            }).catch(error => {});
        }
    }

    function removeUser() {
        deleteUser(id).catch(e => {})
        navigator("/")
    }

    return (
        <div className='container'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                {<h2 className='text-center'>{getTranslation("UserDataFormComponentAccount", language)}</h2>}
                <div className='card-body'>
                    <form>
                        <p>{getTranslation("UserDataFormComponentWins", language) + ": "} {userData.wins}</p>
                        <p>{getTranslation("UserDataFormComponentLoses", language) + ": "} {userData.loses}</p>
                        <p>{getTranslation("UserDataFormComponentGamesPlayed", language) + ": "} {userData.gamesPlayed}</p>
                        <div className='form-group mb-2'>
                            <label className='form-label'>{getTranslation("UserDataFormComponentLanguage", language) + ": "}</label>
                            <select
                                name='Language'
                                value={userLanguage}
                                className="ms-auto"
                                style={{ width: '150px' }}
                                onChange={(e) => setUserLanguage(e.target.value)}
                            >
                                <option value="ENGLISH">English</option>
                                <option value="FRENCH">Français</option>
                                <option value="SPANISH">Español</option>
                            </select>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>{getTranslation("UserDataFormComponentTheme", language) + ": "}</label>
                            <select
                                name='Theme'
                                value={theme}
                                className="ms-auto"
                                style={{ width: '150px' }}
                                onChange={(e) => setUserTheme(e.target.value)}
                            >
                                <option value="LIGHT">Light</option>
                                <option value="DARK">Dark</option>
                            </select>
                        </div>
                    </form>
                    <button className='btn btn-success' onClick={updateData}>{getTranslation("UserDataFormComponentSubmit", language)}</button>
                    <button className='btn btn-info' onClick={editUser}>{getTranslation("UserDataFormComponentEdit", language)}</button>
                    <button className='btn btn-danger' onClick={logoutUser}>{getTranslation("UserDataFormComponentLogout", language)}</button>
                    <button className='btn btn-danger' onClick={removeUser}>{getTranslation("UserDataFormComponentDelete", language)}</button>
                </div>
            </div>
        </div>
    )
}

export default UserDataFormComponent