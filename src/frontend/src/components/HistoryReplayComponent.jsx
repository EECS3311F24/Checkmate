import React, { useState, useEffect } from 'react';
import { useTheme } from './ThemeProvider';
import './historyReplay.css';
import ChessBoard from './ChessBoardComponent';

const HistoryReplayComponent = ({ gameHistory, boardId }) => {
  const { theme } = useTheme();
  const [selectedGame, setSelectedGame] = useState(null);
  const [replayIndex, setReplayIndex] = useState(0);
  const [isPlaying, setIsPlaying] = useState(false);

  // Replay logic
  useEffect(() => {
    let replayInterval;
    if (isPlaying && selectedGame) {
      replayInterval = setInterval(() => {
        setReplayIndex((prevIndex) => {
          if (prevIndex < gameHistory.length - 1) {
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

  const renderReplayBoard = () => {
    if (selectedGame) {
      const currentBoard = gameHistory[replayIndex];
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
          <div className="replay-board">{<ChessBoard board={gameHistory.at(replayIndex)}/>}</div>
          <div className="replay-controls">
            <button onClick={() => handleReplayControl('rewind')}>Rewind</button>
            <button onClick={() => handleReplayControl('pause')}>Pause</button>
            <button onClick={() => handleReplayControl('play')}>Play</button>
            <button onClick={() => handleReplayControl('forward')}>Forward</button>
          </div>
          <p>
            <strong>Move {replayIndex + 1} of {gameHistory.length}</strong>
          </p>
        </div>
      )}
    </div>
  );
};

export default HistoryReplayComponent;