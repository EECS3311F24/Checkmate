import React, { useState, useEffect } from 'react';
import './historyReplay.css';

const HistoryReplayComponent = ({ gameHistory }) => {
  const [selectedGame, setSelectedGame] = useState(null);
  const [replayIndex, setReplayIndex] = useState(0);
  const [isPlaying, setIsPlaying] = useState(false);

  useEffect(() => {
    let replayInterval;
    if (isPlaying && selectedGame) {
      replayInterval = setInterval(() => {
        setReplayIndex((prevIndex) => {
          if (prevIndex < selectedGame.moves.length - 1) {
            return prevIndex + 1;
          } else {
            setIsPlaying(false);
            return prevIndex;
          }
        });
      }, 1000);
    }
    return () => clearInterval(replayInterval);
  }, [isPlaying, selectedGame]);

  const handleSelectGame = (game) => {
    setSelectedGame(game);
    setReplayIndex(0);
    setIsPlaying(false);
  };

  const handleReplayControl = (action) => {
    if (action === 'play') setIsPlaying(true);
    if (action === 'pause') setIsPlaying(false);
    if (action === 'rewind') setReplayIndex((prev) => Math.max(prev - 1, 0));
  };

  return (
    <div className="history-replay-container">
      <h2>Game History</h2>
      <div className="game-history-list">
        {gameHistory.length > 0 ? (
          gameHistory.map((game, index) => (
            <div
              key={index}
              className={`game-item ${selectedGame === game ? 'selected' : ''}`}
              onClick={() => handleSelectGame(game)}
            >
              <p>Game ID: {game.id}</p>
              <p>Date: {game.date}</p>
              <p>Opponent: {game.opponent}</p>
            </div>
          ))
        ) : (
          <p>No games available.</p>
        )}
      </div>
      {selectedGame && (
        <div className="replay-section">
          <h3>Replay Game</h3>
          <div className="replay-board">
            <p>Move {replayIndex + 1}: {selectedGame.moves[replayIndex]}</p>
          </div>
          <div className="replay-controls">
            <button onClick={() => handleReplayControl('rewind')}>Rewind</button>
            <button onClick={() => handleReplayControl('pause')}>Pause</button>
            <button onClick={() => handleReplayControl('play')}>Play</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default HistoryReplayComponent;
