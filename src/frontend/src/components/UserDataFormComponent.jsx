import React, { useState, useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { getUserData, updateUserData } from '../services/UserService';
import { getTranslation, useLanguage } from './LanguageProvider';

const UserDataFormComponent = () => {
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
            setUserLanguage(response.data.language);
            setUserTheme(response.data.theme);
        }).catch(error => {
            console.error(error);
        })
    }

    const navigator = useNavigate();

    function updateData() {
        if (id) {
            const data = { language: userLanguage, theme, wins: userData.wins, loses: userData.loses, gamesPlayed: userData.gamesPlayed }
            updateUserData(id, data).then((response) => {
                console.log(response.data);
                //navigator("/users")
            }).catch(error => {
                console.error(error);
            })
        }
    }

    return (
        <div className='container'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                {<h2 className='text-center'>{getTranslation("LoginComponentLogin", language)}</h2>}
                <div className='card-body'>
                    <form>
                        <p>Wins: {userData.wins}</p>
                        <p>Loses: {userData.loses}</p>
                        <p>Games Played: {userData.gamesPlayed}</p>
                        <div className='form-group mb-2'>
                            <label className='form-label'>{"Language: "}</label>
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
                            <label className='form-label'>{"Theme: "}</label>
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
                    <button className='btn btn-success' onClick={updateData}>{getTranslation("UserFormComponentSubmit", language)}</button>
                </div>
            </div>
        </div>
    )
}

export default UserDataFormComponent