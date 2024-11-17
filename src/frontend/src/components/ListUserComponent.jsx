import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { deleteUser, listUsers, deleteAllUsers } from '../services/UserService';
import { deleteAllBoards } from '../services/ChessService';
import { getTranslation, useLanguage } from './LanguageProvider';
import { useTheme } from './ThemeProvider';

const ListUserComponent = () => {
    const navigator = useNavigate();
    const { language } = useLanguage();
    const { theme } = useTheme();
    const [users, setUsers] = useState([]);

    useEffect(() => {
        getAllUsers();
    }, []);

    useEffect(() => {
        // Update the background color of the website based on the current theme
        document.body.style.backgroundColor = theme === 'dark' ? '#121212' : theme === 'solarized' ? '#fdf6e3' : '#ffffff';
    }, [theme]);

    function getAllUsers() {
        listUsers().then((response) => {
            setUsers(response.data);
        }).catch(error => {
            console.error(error);
        });
    }

    const clearDatabase = () => {
        deleteAllUsers();
        deleteAllBoards();
        navigator("/");
    }

    function editUser(id) {
        navigator(`/edit-user/${id}`);
    }

    function removeUser(id) {
        deleteUser(id).then(() => { getAllUsers(); }).catch(error => { console.error(error); });
    }

    // Apply different styles for header, table, and text based on the current theme
    const headerStyle = theme === 'dark' ? { color: '#ffffff' } : theme === 'solarized' ? { color: '#00008b' } : { color: '#000000' };
    const tableStyle = theme === 'solarized' ? { backgroundColor: '#f0f8ff', color: '#000000' } : { backgroundColor: '#ffffff', color: '#000000' };
    const cellStyle = theme === 'solarized' ? { color: '#00008b' } : { color: '#000000' };

    return (
        <div className="container text-center">
            <h2 style={headerStyle}>{getTranslation("ListUserComponentUserList", language)}</h2>
            <table className='table table-striped table-bordered' style={tableStyle}>
                <thead>
                    <tr>
                        <th style={cellStyle}>{getTranslation("ListUserComponentUserID", language)}</th>
                        <th style={cellStyle}>{getTranslation("ListUserComponentUserName", language)}</th>
                        <th style={cellStyle}>{getTranslation("ListUserComponentUserEmail", language)}</th>
                        <th style={cellStyle}>{getTranslation("ListUserComponentUserDateCreated", language)}</th>
                        <th style={cellStyle}>{getTranslation("ListUserComponentActions", language)}</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        users.map(user =>
                            <tr key={user.id} style={tableStyle}>
                                <td style={cellStyle}>{user.id}</td>
                                <td style={cellStyle}>{user.username}</td>
                                <td style={cellStyle}>{user.email}</td>
                                <td style={cellStyle}>{user.createdOn}</td>
                                <td>
                                    <button className='btn btn-info' onClick={() => editUser(user.id)}>{getTranslation("ListUserComponentEdit", language)}</button>
                                    <button className='btn btn-danger' onClick={() => removeUser(user.id)} style={{ marginLeft: '10px' }}>{getTranslation("ListUserComponentDelete", language)}</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
            <button className='btn btn-danger' onClick={() => clearDatabase()}>{"Clear Databases"}</button>
        </div>
    );
};

export default ListUserComponent;
