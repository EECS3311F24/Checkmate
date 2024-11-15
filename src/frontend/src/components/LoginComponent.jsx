import React, { useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { sha3_256 } from 'js-sha3';
import { authenticate } from '../services/UserService';
import { getTranslation, useLanguage } from './LanguageProvider';

const LoginComponent = () => {
    const { language, setLanguage } = useLanguage();
    const [userLogin, setUserLogin] = useState('')
    const [password, setPassword] = useState('')

    const [errors, setErrors] = useState({
        userLogin: '',
        password: '',
        login: ''
    })

    const navigator = useNavigate();

    function login(e) {
        e.preventDefault();
        if (validateForm()) {
            const passwordHash = sha3_256(password);
            const user = { username:userLogin, email:null, passwordHash }
            authenticate(user).then((response) => {
                console.log(response.data);
                navigator(`/account/${response.data.id}`)
            }).catch(error => {
                setErrors(prev => ({
                    ...prev,
                    login: getTranslation("LoginComponentLoginError", language)
                }));
            })
        }
    }

    function validateForm() {
        let valid = true;
        const errorsCopy = { ...errors }

        if (userLogin.trim()) {
            errorsCopy.userLogin = '';
        } else {
            errorsCopy.userLogin = getTranslation("LoginComponentUserLoginError", language)
            valid = false;
        }

        if (password.trim()) {
            errorsCopy.password = '';
        } else {
            errorsCopy.password = getTranslation("LoginComponentPasswordError", language)
            valid = false;
        }

        setErrors(errorsCopy);
        return valid;
    }

    return (
        <div className='container'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                {<h2 className='text-center'>{getTranslation("LoginComponentLogin", language)}</h2>}
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>{getTranslation("LoginComponentUserLogin", language)}</label>
                            <input
                                type="text"
                                placeholder={getTranslation("LoginComponentUserLoginPlaceholder", language)}
                                name='userLogin'
                                value={userLogin}
                                className={`form-control ${errors.userLogin ? 'is-invalid' : ''}`}
                                onChange={(e) => setUserLogin(e.target.value)}
                            >
                            </input>
                            {errors.userLogin && <div className='invalid-feedback'> {errors.userLogin} </div>}
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>{getTranslation("LoginComponentPassword", language)}</label>
                            <input
                                type="text"
                                placeholder={getTranslation("LoginComponentPasswordPlaceholder", language)}
                                name='password'
                                value={password}
                                className={`form-control ${errors.password ? 'is-invalid' : ''}`}
                                onChange={(e) => setPassword(e.target.value)}
                            >
                            </input>
                            {errors.password && <div className='invalid-feedback'> {errors.password} </div>}
                        </div>
                        <div>
                            <button className='btn btn-success' onClick={login}>{getTranslation("LoginComponentLogin", language)}</button>
                            {errors.login && <div> {getTranslation("LoginComponentLoginError", language)} </div>}
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default LoginComponent