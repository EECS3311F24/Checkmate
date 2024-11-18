import React, { useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { sha3_256 } from 'js-sha3';
import { setUserPassword } from '../services/UserService';
import { getTranslation, useLanguage } from './LanguageProvider';
import { useTheme } from './ThemeProvider';

const ChangePasswordComponent = () => {
    const { language, setLanguage } = useLanguage();
    const { theme } = useTheme();
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
                        password: "ChangePasswordComponentWrongPasswordError"
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
            errorsCopy.password = "ChangePasswordComponentPasswordError"
            valid = false;
        }

        setErrors(errorsCopy);
        return valid;
    }

    const headerStyle = theme === 'dark' ? { color: '#ffffff' } : theme === 'solarized' ? { color: '#00008b' } : { color: '#000000' };
    const cardStyle = theme === 'dark' ? { backgroundColor: '#333333', color: '#ffffff' } : theme === 'solarized' ? { backgroundColor: '#f0f8ff', color: '#000000' } : { backgroundColor: '#ffffff', color: '#000000' };
    const inputStyle = theme === 'dark' ? { backgroundColor: '#555555', color: '#ffffff' } : theme === 'solarized' ? { backgroundColor: '#e0ffff', color: '#000000' } : { backgroundColor: '#ffffff', color: '#000000' };

    return (
        <div className='container'>
            <div className='card col-md-6 offset-md-3 offset-md-3'  style={cardStyle}>
                {<h2 className='text-center' style={headerStyle}>{getTranslation("ChangePasswordComponentChangePassword", language)}</h2>}
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
                                style={inputStyle}
                                onChange={(e) => setOldPassword(e.target.value)}
                            />
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
                                style={inputStyle}
                                onChange={(e) => setPassword(e.target.value)}
                            />
                            {errors.password && <div className='invalid-feedback'> {getTranslation(errors.password, language)} </div>}
                        </div>
                        <button className='btn btn-danger' onClick={changePassword}>{getTranslation("ChangePasswordComponentChangePassword", language)}</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default ChangePasswordComponent