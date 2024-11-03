import React, {useEffect, useState} from 'react'
import { useNavigate } from 'react-router-dom';
import { deleteUser, listUsers } from '../services/UserService'
import { getTranslation, useLanguage } from './LanguageProvider';

const ListUserComponent = () => {
    const { language, setLanguage } = useLanguage();
    const [users, setUsers] = useState([])
    useEffect(() => {
        getAllUsers();
    }, [])

    function getAllUsers() {
        listUsers().then((response) => {
            setUsers(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    const navigator = useNavigate();

    function editUser(id) {
        navigator(`/edit-user/${id}`)
    }

    function removeUser(id) {
        deleteUser(id).then((response) => {getAllUsers()}).catch(error => {console.error(error)})
    }

    return (
        <div className="container text-center">
            <h2>{getTranslation("ListUserComponentUserList", language)}</h2>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>{getTranslation("ListUserComponentUserID", language)}</th>
                        <th>{getTranslation("ListUserComponentUserName", language)}</th>
                        <th>{getTranslation("ListUserComponentUserEmail", language)}</th>
                        <th>{getTranslation("ListUserComponentUserDateCreated", language)}</th>
                        <th>{getTranslation("ListUserComponentActions", language)}</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        users.map(user =>
                            <tr key={user.id}>
                                <td>{user.id}</td>
                                <td>{user.username}</td>
                                <td>{user.email}</td>
                                <td>{user.createdOn}</td>
                                <td>
                                    <button className='btn btn-info' onClick={() => editUser(user.id)}>{getTranslation("ListUserComponentEdit", language)}</button>
                                    <button className='btn btn-danger' onClick={() => removeUser(user.id)}style={{marginLeft: '10px'}}>{getTranslation("ListUserComponentDelete", language)}</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default ListUserComponent