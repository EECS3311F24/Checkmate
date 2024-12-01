import React, { useState, useEffect } from 'react';
import './historyReplay.css';

const HistoryReplayComponent = ({ gameHistory }) => {
  const [selectedGame, setSelectedGame] = useState(null);
  const [replayIndex, setReplayIndex] = useState(0);
  const [isPlaying, setIsPlaying] = useState(false);
  const [sortKey, setSortKey] = useState('date'); // default sort by date

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
    if (action === 'forward')
      setReplayIndex((prev) => Math.min(prev + 1, selectedGame.moves.length - 1));
  };

  const handleSortChange = (key) => {
    setSortKey(key);
  };

  const sortedHistory = [...gameHistory].sort((a, b) => {
    if (sortKey === 'date') {
      return new Date(a.date) - new Date(b.date);
    } else if (sortKey === 'opponent') {
      return a.opponent.localeCompare(b.opponent);
    }
    return 0;
  });

  return (
    <div className="history-replay-container">
      <h2>Game History</h2>
      <div className="sorting-controls">
        <label>Sort By: </label>
        <select value={sortKey} onChange={(e) => handleSortChange(e.target.value)}>
          <option value="date">Date</option>
          <option value="opponent">Opponent</option>
        </select>
      </div>
      <div className="game-history-list">
        {sortedHistory.length > 0 ? (
          sortedHistory.map((game, index) => (
            <div
              key={index}
              className={`game-item ${selectedGame === game ? 'selected' : ''}`}
              onClick={() => handleSelectGame(game)}
            >
              <p><strong>Game ID:</strong> {game.id}</p>
              <p><strong>Date:</strong> {game.date}</p>
              <p><strong>Opponent:</strong> {game.opponent}</p>
            </div>
          ))
        ) : (
          <p>No games available.</p>
        )}
      </div>
      {selectedGame && (
        <div className="replay-section">
          <h3>Replay Game</h3>
          {selectedGame.moves.length > 0 ? (
            <>
              <div className="replay-board">
                <p>
                  <strong>Move {replayIndex + 1}:</strong>{' '}
                  {selectedGame.moves[replayIndex]}
                </p>
              </div>
              <div className="replay-controls">
                <button onClick={() => handleReplayControl('rewind')}>Rewind</button>
                <button onClick={() => handleReplayControl('pause')}>Pause</button>
                <button onClick={() => handleReplayControl('play')}>Play</button>
                <button onClick={() => handleReplayControl('forward')}>Forward</button>
              </div>
            </>
          ) : (
            <p>No moves available for this game.</p>
          )}
        </div>
      )}
    </div>
  );
};

export default HistoryReplayComponent;
