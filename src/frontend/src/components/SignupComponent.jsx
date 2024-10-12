import React, { useState } from 'react'
import { createUser } from '../services/UserService';
import { useNavigate } from 'react-router-dom';

const SignupComponent = () => {
    const [username, setUsername] = useState('')
    const [email, setEmail] = useState('')

    const [errors, setErrors] = useState({
        username: '',
        email: ''
    })

    const navigator = useNavigate();

    function saveUser(e) {
        e.preventDefault();
        if (validateForm()) {
            const user = { username, email }
            createUser(user).then((response) => {
                console.log(response.data);
                navigator("/users")
            })
        }
    }

    function validateForm() {
        let valid = true;
        const errorsCopy = { ...errors }

        if (username.trim()) {
            errorsCopy.username = '';
        } else {
            errorsCopy.username = "Username is required!"
            valid = false;
        }

        if (email.trim()) {
            errorsCopy.email = '';
        } else {
            errorsCopy.email = "Email is required!"
            valid = false;
        }

        setErrors(errorsCopy);
        return valid;
    }

    return (
        <div className='container'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                <h2 className='text-center'>Signup</h2>
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Username</label>
                            <input
                                type="text"
                                placeholder="Username"
                                name='username'
                                value={username}
                                className={`form-control ${errors.username ? 'is-invalid' : ''}`}
                                onChange={(e) => setUsername(e.target.value)}
                            >
                            </input>
                            {errors.username && <div className='invalid-feedback'> {errors.username} </div>}
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Email</label>
                            <input
                                type="text"
                                placeholder="Email"
                                name='email'
                                value={email}
                                className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                                onChange={(e) => setEmail(e.target.value)}
                            >
                            </input>
                            {errors.email && <div className='invalid-feedback'> {errors.email} </div>}
                        </div>
                        <button className='btn btn-success' onClick={saveUser}>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default SignupComponent