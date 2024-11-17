import React, { useState, useEffect } from 'react';
import { createUser, getUser, updateUser } from '../services/UserService';
import { useNavigate, useParams } from 'react-router-dom';
import { getTranslation, useLanguage } from './LanguageProvider';
import { useTheme } from './ThemeProvider';

const UserFormComponent = () => {
    const { language } = useLanguage();
    const { theme } = useTheme();
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');

    const { id } = useParams();

    const [errors, setErrors] = useState({
        username: '',
        email: ''
    });

    useEffect(() => {
        if (id) {
            getUser(id).then((response) => {
                setUsername(response.data.username);
                setEmail(response.data.email);
            });
        }
    }, [id]);

    useEffect(() => {
        // Update the background color of the website based on the current theme
        document.body.style.backgroundColor = theme === 'dark' ? '#121212' : theme === 'solarized' ? '#fdf6e3' : '#ffffff';
    }, [theme]);

    const navigator = useNavigate();

    function saveUser(e) {
        e.preventDefault();
        if (validateForm()) {
            const user = { username, email };
            if (id) {
                updateUser(id, user).then((response) => {
                    console.log(response.data);
                    navigator("/users");
                }).catch(error => {
                    console.error(error);
                });
            } else {
                createUser(user).then((response) => {
                    console.log(response.data);
                    navigator("/users");
                }).catch(error => {
                    console.error(error);
                });
            }
        }
    }

    function validateForm() {
        let valid = true;
        const errorsCopy = { ...errors };

        if (username.trim()) {
            errorsCopy.username = '';
        } else {
            errorsCopy.username = getTranslation("UserFormComponmentUsernameError", language);
            valid = false;
        }

        if (email.trim()) {
            errorsCopy.email = '';
        } else {
            errorsCopy.email = getTranslation("UserFormComponmentEmailError", language);
            valid = false;
        }

        setErrors(errorsCopy);
        return valid;
    }

    function pageTitle() {
        if (id) {
            return <h2 className='text-center' style={headerStyle}>{getTranslation("UserFormComponmentEditUser", language)}</h2>;
        } else {
            return <h2 className='text-center' style={headerStyle}>{getTranslation("UserFormComponmentSignup", language)}</h2>;
        }
    }

    // Apply different styles for header and form elements based on the current theme
    const headerStyle = theme === 'dark' ? { color: '#ffffff' } : theme === 'solarized' ? { color: '#00008b' } : { color: '#000000' };
    const cardStyle = theme === 'dark' ? { backgroundColor: '#333333', color: '#ffffff' } : theme === 'solarized' ? { backgroundColor: '#f0f8ff', color: '#000000' } : { backgroundColor: '#ffffff', color: '#000000' };
    const inputStyle = theme === 'dark' ? { backgroundColor: '#555555', color: '#ffffff' } : theme === 'solarized' ? { backgroundColor: '#e0ffff', color: '#000000' } : { backgroundColor: '#ffffff', color: '#000000' };

    return (
        <div className='container'>
            <div className='card col-md-6 offset-md-3 offset-md-3' style={cardStyle}>
                {pageTitle()}
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label' style={headerStyle}>{getTranslation("UserFormComponmentUsername")}</label>
                            <input
                                type="text"
                                placeholder={getTranslation("UserFormComponmentUsernamePlaceholder")}
                                name='username'
                                value={username}
                                className={`form-control ${errors.username ? 'is-invalid' : ''}`}
                                style={inputStyle}
                                onChange={(e) => setUsername(e.target.value)}
                            />
                            {errors.username && <div className='invalid-feedback'> {errors.username} </div>}
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label' style={headerStyle}>{getTranslation("UserFormComponmentEmail")}</label>
                            <input
                                type="text"
                                placeholder={getTranslation("UserFormComponmentEmailPlaceholder")}
                                name='email'
                                value={email}
                                className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                                style={inputStyle}
                                onChange={(e) => setEmail(e.target.value)}
                            />
                            {errors.email && <div className='invalid-feedback'> {errors.email} </div>}
                        </div>
                        <button className='btn btn-success' onClick={saveUser}>{getTranslation("UserFormComponmentSubmit")}</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default UserFormComponent;
