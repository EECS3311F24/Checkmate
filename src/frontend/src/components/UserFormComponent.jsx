import React, { useState, useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { sha3_256 } from 'js-sha3';
import { createUser, getUser, updateUser } from '../services/UserService';
import { getTranslation, useLanguage } from './LanguageProvider';

const UserFormComponent = () => {
    const { language, setLanguage } = useLanguage();
    const [username, setUsername] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const { id } = useParams();

    const [errors, setErrors] = useState({
        username: '',
        email: '',
        password: ''
    })

    useEffect(() => {
        if (id) {
            getUser(id).then((response) => {
                setUsername(response.data.username);
                setEmail(response.data.email);
            })
        }
    }, [id])

    const navigator = useNavigate();

    function saveUser(e) {
        e.preventDefault();
        if (validateForm()) {
            const passwordHash = sha3_256(password);
            const user = { username, email, passwordHash }
            if (id) {
                updateUser(id, user).then((response) => {
                    console.log(response.data);
                    navigator("/users")
                }).catch(error => {
                    console.error(error);
                })
            } else {
                createUser(user).then((response) => {
                    console.log(response.data);
                    navigator("/users")
                }).catch(error => {
                    console.error(error);
                })
            }
        }
    }

    function changePassword() {
        navigator(`/change-password/${id}`)
    }

    function validateForm() {
        let valid = true;
        const errorsCopy = { ...errors }

        if (username.trim()) {
            errorsCopy.username = '';
        } else {
            errorsCopy.username = getTranslation("UserFormComponentUsernameError", language)
            valid = false;
        }

        if (email.trim()) {
            errorsCopy.email = '';
        } else {
            errorsCopy.email = getTranslation("UserFormComponentEmailError", language)
            valid = false;
        }

        if (password.trim()) {
            errorsCopy.password = '';
        } else {
            errorsCopy.password = getTranslation("UserFormComponentPasswordError", language)
            valid = false;
        }

        setErrors(errorsCopy);
        return valid;
    }

    function pageTitle() {
        if (id) {
            return <h2 className='text-center'>{getTranslation("UserFormComponentEditUser", language)}</h2>
        } else {
            return <h2 className='text-center'>{getTranslation("UserFormComponentSignup", language)}</h2>
        }
    }

    return (
        <div className='container'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                {pageTitle()}
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>{getTranslation("UserFormComponentUsername", language)}</label>
                            <input
                                type="text"
                                placeholder= {getTranslation("UserFormComponentUsernamePlaceholder", language)}
                                name='username'
                                value={username}
                                className={`form-control ${errors.username ? 'is-invalid' : ''}`}
                                onChange={(e) => setUsername(e.target.value)}
                            >
                            </input>
                            {errors.username && <div className='invalid-feedback'> {errors.username} </div>}
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>{getTranslation("UserFormComponentEmail", language)}</label>
                            <input
                                type="text"
                                placeholder= {getTranslation("UserFormComponentEmailPlaceholder", language)}
                                name='email'
                                value={email}
                                className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                                onChange={(e) => setEmail(e.target.value)}
                            >
                            </input>
                            {errors.email && <div className='invalid-feedback'> {errors.email} </div>}
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>{getTranslation("UserFormComponentPassword", language)}</label>
                            <input
                                type="text"
                                placeholder= {getTranslation("UserFormComponentPasswordPlaceholder", language)}
                                name='password'
                                value={password}
                                className={`form-control ${errors.password ? 'is-invalid' : ''}`}
                                onChange={(e) => setPassword(e.target.value)}
                            >
                            </input>
                            {errors.password && <div className='invalid-feedback'> {errors.password} </div>}
                        </div>
                        <button className='btn btn-success' onClick={saveUser}>{getTranslation("UserFormComponentSubmit", language)}</button>
                        <button className='btn btn-danger' onClick={changePassword}>{getTranslation("UserFormComponentSubmit", language)}</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default UserFormComponent