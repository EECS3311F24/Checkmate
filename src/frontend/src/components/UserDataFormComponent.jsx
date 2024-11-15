import React, { useState, useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { getUserData, updateUserData } from '../services/UserService';
import { getTranslation, useLanguage } from './LanguageProvider';

const UserDataFormComponent = () => {
    const { language, setLanguage } = useLanguage();
    const { theme, setTheme } = useState('')
    const [userData, setUserData] = useState([])
    const { id } = useParams();

    useEffect(() => {
        getData();
    }, [])

    function getData() {
        getUserData(id).then((response) => {
            setUserData(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    const navigator = useNavigate();

    function updateData() {
        if (id) {
            // TODO language and theme should be upper case
            // TODO translations
            // TODO fix setTheme is not a function???
            const data = { language, theme, wins: userData.wins, loses: userData.loses, gamesPlayed: userData.gamesPlayed }
            updateUserData(data).then((response) => {
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
                                value={language}
                                className="ms-auto"
                                style={{ width: '150px' }}
                                onChange={(e) => setLanguage(e.target.value)}
                            >
                                <option value="en">English</option>
                                <option value="fn">Français</option>
                                <option value="es">Español</option>
                            </select>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>{"Theme: "}</label>
                            <select
                                name='Theme'
                                value={theme}
                                className="ms-auto"
                                style={{ width: '150px' }}
                                onChange={(e) => setTheme(e.target.value)}
                            >
                                <option value="LIGHT">Light</option>
                                <option value="DARK">Dark</option>
                            </select>
                        </div>
                        <button className='btn btn-success' onClick={updateData}>{getTranslation("UserFormComponentSubmit", language)}</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default UserDataFormComponent