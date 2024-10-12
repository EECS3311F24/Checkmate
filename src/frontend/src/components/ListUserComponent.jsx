import React, {useEffect, useState} from 'react'
import { useNavigate } from 'react-router-dom';
import { listUsers } from '../services/UserService'

const ListUserComponent = () => {
    const [users, setUsers] = useState([])
    useEffect(() => {
        listUsers().then((response) => {
            setUsers(response.data);
        }).catch(error => {
            console.error(error);
        })
    }, [])

    const navigator = useNavigate();

    function editUser(id) {
        navigator(`/edit-user/${id}`)
    }

    return (
        <div className="container text-center">
            <h2>List of Users</h2>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>User id</th>
                        <th>User username</th>
                        <th>User email</th>
                        <th>User date created</th>
                        <th>Actions</th>
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
                                    <button className='btn btn-info' onClick={() => editUser(user.id)}>Edit</button>
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