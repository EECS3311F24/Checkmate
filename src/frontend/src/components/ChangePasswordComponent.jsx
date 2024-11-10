import React, { useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { sha3_256 } from 'js-sha3';
import { setUserPassword } from '../services/UserService';
import { getTranslation, useLanguage } from './LanguageProvider';

const ChangePasswordComponent = () => {
    const { language, setLanguage } = useLanguage();
    const [oldPassword, setOldPassword] = useState('')
    const [password, setPassword] = useState('')
    const { id } = useParams();

    const [errors, setErrors] = useState({
        oldPassword: '',
        password: ''
    })

    const navigator = useNavigate();

    function changePassword(e) {
        e.preventDefault();
        if (validateForm()) {
            if (id) {
                const oldPasswordHash = sha3_256(oldPassword);
                const passwordHash = sha3_256(password);
                setUserPassword(id, oldPasswordHash, passwordHash).then((response) => {
                    console.log(response.data);
                    navigator("/users")
                }).catch(error => {
                    setErrors(prev => ({
                        ...prev,
                        password: getTranslation("ChangePasswordComponentWrongPasswordError", language)
                    }));
                })
            }
        }
    }

    function validateForm() {
        let valid = true;
        const errorsCopy = { ...errors }

        if (oldPassword.trim()) {
            errorsCopy.username = '';
        } else {
            errorsCopy.oldPassword = getTranslation("ChangePasswordComponentOldPasswordError", language)
            valid = false;
        }

        if (password.trim()) {
            errorsCopy.password = '';
        } else {
            errorsCopy.password = getTranslation("ChangePasswordComponentPasswordError", language)
            valid = false;
        }

        setErrors(errorsCopy);
        return valid;
    }

    return (
        <div className='container'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                {<h2 className='text-center'>{getTranslation("ChangePasswordComponentChangePassword", language)}</h2>}
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>{getTranslation("ChangePasswordComponentOldPassword", language)}</label>
                            <input
                                type="text"
                                placeholder={getTranslation("ChangePasswordComponentOldPasswordPlaceholder", language)}
                                name='oldPassword'
                                value={oldPassword}
                                className={`form-control ${errors.oldPassword ? 'is-invalid' : ''}`}
                                onChange={(e) => setOldPassword(e.target.value)}
                            >
                            </input>
                            {errors.oldPassword && <div className='invalid-feedback'> {errors.oldPassword} </div>}
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>{getTranslation("ChangePasswordComponentPassword", language)}</label>
                            <input
                                type="text"
                                placeholder={getTranslation("ChangePasswordComponentPasswordPlaceholder", language)}
                                name='password'
                                value={password}
                                className={`form-control ${errors.password ? 'is-invalid' : ''}`}
                                onChange={(e) => setPassword(e.target.value)}
                            >
                            </input>
                            {errors.password && <div className='invalid-feedback'> {errors.password} </div>}
                        </div>
                        <button className='btn btn-danger' onClick={changePassword}>{getTranslation("ChangePasswordComponentChangePassword", language)}</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default ChangePasswordComponent