import React, { useEffect, useState } from 'react';
import './ChatBox.css';

const ChatBox = ({ boardId }) => {
  const [messages, setMessages] = useState([
    { userId: 'player1', message: 'Hello, welcome to the game!' },
    { userId: 'player2', message: 'Good luck everyone!' },
  ]);
  const [newMessage, setNewMessage] = useState('');

  const handleSendMessage = () => {
    if (!newMessage.trim()) return;

    const chatMessage = {
      userId: 'player1', // Replace with actual user ID from context/session
      boardId,
      message: newMessage,
    };

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
