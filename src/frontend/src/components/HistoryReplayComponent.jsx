import React, { useState, useEffect } from 'react';
import { useTheme } from './ThemeProvider';
import './historyReplay.css';

const HistoryReplayComponent = ({ gameHistory, boardId }) => {
  const { theme } = useTheme();
  const [selectedGame, setSelectedGame] = useState(null);
  const [replayIndex, setReplayIndex] = useState(0);
  const [isPlaying, setIsPlaying] = useState(false);

  // Replay logic
  // TODO selectedGame.moves does not exist.
  // TODO use the replayIndex to select which board in gameHistory
  // TODO display that board at replayIndex but make it not like a real game just to view!
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

  const handleSelectGame = (game, index) => {
    setSelectedGame(game);
    setReplayIndex(index);
    setIsPlaying(false);
  };

  const handleReplayControl = (action) => {
    if (action === 'play') setIsPlaying(true);
    if (action === 'pause') setIsPlaying(false);
    if (action === 'rewind') setReplayIndex((prev) => Math.max(prev - 1, 0));
    if (action === 'forward')
      setReplayIndex((prev) => Math.min(prev + 1, gameHistory.length - 1));
  };
  const renderRecentMove = () => {
    if (gameHistory && gameHistory.length > 0) {
      return (
        <div className="move-list">
          <h3>Move History</h3>
          <ul>
            {gameHistory.map((move, index) => (
              <li key={index}>
                Move {index + 1}: ({move.start.row},{move.start.col}) → ({move.end.row},{move.end.col})
              </li>
            ))}
          </ul>
        </div>
      );
    }
    return <p>No moves recorded yet.</p>;
  };

  const cardStyle = theme === 'dark' ? { backgroundColor: '#333333', color: '#ffffff' } : theme === 'solarized' ? { backgroundColor: '#f0f8ff', color: '#000000' } : { backgroundColor: '#ffffff', color: '#000000' };
  return (
    <div className="history-replay-container" style={cardStyle}>
      <h2>Game History</h2>
      <div className="game-history-list">
        {gameHistory.length > 0 ? (
          gameHistory.map((game, index) => (
            <div
              key={index}
              className={`game-item ${selectedGame === game ? 'selected' : ''}`}
              onClick={() => handleSelectGame(game, index)}
            >
              <p><strong>Board Id:</strong> {boardId}</p>
              <p><strong>Move:</strong> {index}</p>
            </div>
          ))
        ) : (
          <p>No games available.</p>
        )}
      </div>
      {selectedGame && (
        <div className="replay-section">
          <h3>Replay Game</h3>
          {replayIndex >= 0 && replayIndex < gameHistory.length ? (
            <>
              <div className="replay-board">
                <p>
                  <strong>Move {replayIndex}:</strong>{' '}
                </p>
              </div>
              {renderRecentMove()}
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