import React, { useEffect, useState } from 'react';
import { useQuery } from 'react-query';
import { getChatMessagesByBoardId, createChatMessage } from '../services/ChatService';
import { getAuthenticate } from '../services/UserService';
import './ChatBox.css';

const ChatBox = ({ boardId }) => {
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');
  const [user, setUser] = useState();

  getUser();

  const fetchChatMessages = async () => {
    if (!boardId) return;
    const res = await getChatMessagesByBoardId(boardId);
    updateChat(res?.data);
    return res?.data;
  };

  function updateChat(data) {
    if (data) {
      setMessages(data);
    }
  }

  function getUser() {
    getAuthenticate().then(response => {
      setUser(response?.data)
    }).catch(e => { })
  }

  useQuery('chatMessages', fetchChatMessages, { refetchInterval: 1000})

  const handleSendMessage = () => {
    if (!newMessage.trim()) return;
    getUser();
    console.log(user)
    const chatMessage = {
      userId: user?.id,
      boardId,
      message: newMessage,
    };
    createChatMessage(chatMessage).catch(e => {})
    setMessages((prevMessages) => [...prevMessages, chatMessage]);
    setNewMessage('');
  };

  return (
    <div className="chat-box">
      <div className="messages">
        {Array.isArray(messages) ? messages.map((msg, index) => (
          <div key={index} className="message">
            <span className="text">{msg.message}</span>
          </div>
        )) : <p>No messages to display.</p>}
      </div>
      <div className="input-box">
        <input
          type="text"
          value={newMessage}
          onChange={(e) => setNewMessage(e.target.value)}
          placeholder="Type your message..."
        />
        <button onClick={handleSendMessage}>Send</button>
      </div>
    </div>
  );
};

export default ChatBox;