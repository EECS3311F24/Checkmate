import React, { useState, useEffect } from 'react'
import { createUser, getUser, updateUser } from '../services/UserService';
import { useNavigate, useParams } from 'react-router-dom';
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
                //setPassword(null);
                // TODO password
            })
        }
    }, [id])

    const navigator = useNavigate();


    function saveUser(e) {
        e.preventDefault();
        if (validateForm()) {
            const user = { username, email }
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

    function validateForm() {
        let valid = true;
        const errorsCopy = { ...errors }

        if (username.trim()) {
            errorsCopy.username = '';
        } else {
            errorsCopy.username = getTranslation("UserFormComponmentUsernameError", language)
            valid = false;
        }

        if (email.trim()) {
            errorsCopy.email = '';
        } else {
            errorsCopy.email = getTranslation("UserFormComponmentEmailError", language)
            valid = false;
        }

        if (password.trim()) {
            errorsCopy.password = '';
        } else {
            errorsCopy.password = getTranslation("UserFormComponmentPasswordError", language)
            valid = false;
        }

        setErrors(errorsCopy);
        return valid;
    }

    function pageTitle() {
        if (id) {
            return <h2 className='text-center'>{getTranslation("UserFormComponmentEditUser", language)}</h2>
        } else {
            return <h2 className='text-center'>{getTranslation("UserFormComponmentSignup", language)}</h2>
        }
    }

    return (
        <div className='container'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                {pageTitle()}
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>{getTranslation("UserFormComponmentUsername")}</label>
                            <input
                                type="text"
                                placeholder= {getTranslation("UserFormComponmentUsernamePlaceholder")}
                                name='username'
                                value={username}
                                className={`form-control ${errors.username ? 'is-invalid' : ''}`}
                                onChange={(e) => setUsername(e.target.value)}
                            >
                            </input>
                            {errors.username && <div className='invalid-feedback'> {errors.username} </div>}
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>{getTranslation("UserFormComponmentEmail")}</label>
                            <input
                                type="text"
                                placeholder= {getTranslation("UserFormComponmentEmailPlaceholder")}
                                name='email'
                                value={email}
                                className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                                onChange={(e) => setEmail(e.target.value)}
                            >
                            </input>
                            {errors.email && <div className='invalid-feedback'> {errors.email} </div>}
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>{getTranslation("UserFormComponmentPassword")}</label>
                            <input
                                type="text"
                                placeholder= {getTranslation("UserFormComponmentPasswordPlaceholder")}
                                name='password'
                                value={password}
                                className={`form-control ${errors.password ? 'is-invalid' : ''}`}
                                onChange={(e) => setPassword(e.target.value)}
                            >
                            </input>
                            {errors.password && <div className='invalid-feedback'> {errors.password} </div>}
                        </div>
                        <button className='btn btn-success' onClick={saveUser}>{getTranslation("UserFormComponmentSubmit")}</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default UserFormComponent