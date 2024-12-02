import React, { useState, useEffect } from 'react';
import { useTheme } from './ThemeProvider';
import './historyReplay.css';

const HistoryReplayComponent = ({ gameHistory, boardId }) => {
  const { theme } = useTheme();
  const [selectedGame, setSelectedGame] = useState(null);
  const [replayIndex, setReplayIndex] = useState(0);
  const [isPlaying, setIsPlaying] = useState(false);

  // Replay logic
  useEffect(() => {
    let replayInterval;
    if (isPlaying && selectedGame?.boardStates) {
      replayInterval = setInterval(() => {
        setReplayIndex((prevIndex) => {
          if (prevIndex < selectedGame.boardStates.length - 1) {
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
    if (game && game.boardStates) {
      setSelectedGame(game);
      setReplayIndex(0);
      setIsPlaying(false);
    } else {
      console.error("Selected game does not contain boardStates.");
    }
  };

  const handleReplayControl = (action) => {
    if (!selectedGame?.boardStates) return;
    if (action === 'play') setIsPlaying(true);
    if (action === 'pause') setIsPlaying(false);
    if (action === 'rewind') setReplayIndex((prev) => Math.max(prev - 1, 0));
    if (action === 'forward')
      setReplayIndex((prev) => Math.min(prev + 1, selectedGame.boardStates.length - 1));
  };

  const renderReplayBoard = () => {
    if (selectedGame?.boardStates) {
      const currentBoard = selectedGame.boardStates[replayIndex].boardState;
      return (
        <div className="chess-board">
          {currentBoard.map((row, rowIndex) =>
            row.map((piece, colIndex) => (
              <div
                key={`${rowIndex}-${colIndex}`}
                className={`chess-square ${(rowIndex + colIndex) % 2 === 0 ? 'light' : 'dark'}`}
              >
                {piece && (
                  <img
                    src={pieceImages[piece.color][piece.type]}
                    alt={`${piece.color} ${piece.type}`}
                    className="chess-piece"
                  />
                )}
              </div>
            ))
          )}
        </div>
      );
    }
    return <p>No replay data available.</p>;
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
              onClick={() => handleSelectGame(game)}
            >
              <p><strong>Game:</strong> {`Board ID: ${boardId.slice(0, 5)}...${boardId.slice(-3)}`}</p>
              <p><strong>Move {index + 1}:</strong> {game.description}</p>
            </div>
          ))
        ) : (
          <p>No games available.</p>
        )}
      </div>
      {selectedGame && (
        <div className="replay-section">
          <h3>Replay Game</h3>
          <div className="replay-board">{renderReplayBoard()}</div>
          <div className="replay-controls">
            <button onClick={() => handleReplayControl('rewind')} disabled={replayIndex === 0}>Rewind</button>
            <button onClick={() => handleReplayControl('pause')} disabled={!isPlaying}>Pause</button>
            <button onClick={() => handleReplayControl('play')} disabled={isPlaying || replayIndex === selectedGame?.boardStates.length - 1}>Play</button>
            <button onClick={() => handleReplayControl('forward')} disabled={replayIndex === selectedGame?.boardStates.length - 1}>Forward</button>
          </div>
          <p>
            <strong>Move {replayIndex + 1} of {selectedGame.boardStates?.length}</strong><br />
            {selectedGame?.boardStates[replayIndex]?.description && (
              <span><strong>Move Description:</strong> {selectedGame.boardStates[replayIndex].description}</span>
            )}
          </p>
        </div>
      )}
    </div>
  );
};

export default HistoryReplayComponent;
