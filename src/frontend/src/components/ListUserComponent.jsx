import React, {useEffect, useState} from 'react'
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
                    </tr>
                </thead>
                <tbody>
                    {
                        users.map(user =>
                            <tr key={user.ID}>
                                <td>{user.ID}</td>
                                <td>{user.username}</td>
                                <td>{user.email}</td>
                                <td>{user.createdOn}</td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default ListUserComponent