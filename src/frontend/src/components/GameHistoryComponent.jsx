import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getGameHistory, getGameReplay } from '../services/ChessService';
import { useTheme } from './ThemeProvider';
import { useLanguage } from './LanguageProvider';

const GameHistory = () => {
  const [gameHistory, setGameHistory] = useState([]);
  const [selectedGame, setSelectedGame] = useState(null);
  const [replayMoves, setReplayMoves] = useState([]);
  const [currentMoveIndex, setCurrentMoveIndex] = useState(0);
  const [isReplaying, setIsReplaying] = useState(false);

  const { theme } = useTheme();
  const { language } = useLanguage();
  const navigate = useNavigate();

  // Fetch game history on component mount
  useEffect(() => {
    const fetchGameHistory = async () => {
      try {
        const response = await getGameHistory();
        setGameHistory(response.data || []);
      } catch (error) {
        console.error('Error fetching game history:', error);
      }
    };

    fetchGameHistory();
  }, []);

  // Fetch replay moves when a game is selected
  const handleSelectGame = async (gameId) => {
    try {
      const response = await getGameReplay(gameId);
      setSelectedGame(gameId);
      setReplayMoves(response.data.moves || []);
      setCurrentMoveIndex(0);
      setIsReplaying(false);
    } catch (error) {
      console.error('Error fetching game replay:', error);
    }
  };

  // Replay controls
  const handlePlayPause = () => {
    setIsReplaying(!isReplaying);
  };

  const handleNextMove = () => {
    if (currentMoveIndex < replayMoves.length - 1) {
      setCurrentMoveIndex(currentMoveIndex + 1);
    }
  };

  const handlePreviousMove = () => {
    if (currentMoveIndex > 0) {
      setCurrentMoveIndex(currentMoveIndex - 1);
    }
  };

  // Replay move animation
  useEffect(() => {
    let intervalId;
    if (isReplaying && currentMoveIndex < replayMoves.length - 1) {
      intervalId = setInterval(() => {
        setCurrentMoveIndex(prev => 
          prev < replayMoves.length - 1 ? prev + 1 : prev
        );
      }, 1000); // Move every second
    }
    return () => clearInterval(intervalId);
  }, [isReplaying, currentMoveIndex, replayMoves.length]);

  // Styling based on theme
  const cardStyle = theme === 'dark' 
    ? { backgroundColor: '#333333', color: '#ffffff' } 
    : theme === 'solarized' 
    ? { backgroundColor: '#f0f8ff', color: '#000000' } 
    : { backgroundColor: '#ffffff', color: '#000000' };

  return (
    <div className="game-history-container">
      <div className="game-history-header">
        <h2>Game History</h2>
      </div>
      <div className="game-history-content" style={cardStyle}>
        <div className="game-list">
          <table className="table">
            <thead>
              <tr>
                <th>Date</th>
                <th>Opponent</th>
                <th>Result</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {gameHistory.map((game) => (
                <tr key={game.id}>
                  <td>{new Date(game.date).toLocaleDateString()}</td>
                  <td>{game.opponent}</td>
                  <td>{game.result}</td>
                  <td>
                    <button 
                      className="btn btn-primary"
                      onClick={() => handleSelectGame(game.id)}
                    >
                      Replay
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {selectedGame && (
          <div className="game-replay">
            <h3>Game Replay</h3>
            <div className="replay-controls">
              <button 
                className="btn btn-secondary" 
                onClick={handlePreviousMove}
                disabled={currentMoveIndex === 0}
              >
                ←
              </button>
              <button 
                className="btn btn-primary" 
                onClick={handlePlayPause}
              >
                {isReplaying ? 'Pause' : 'Play'}
              </button>
              <button 
                className="btn btn-secondary" 
                onClick={handleNextMove}
                disabled={currentMoveIndex >= replayMoves.length - 1}
              >
                →
              </button>
            </div>
            <div className="move-info">
              <p>Move {currentMoveIndex + 1} of {replayMoves.length}</p>
              {replayMoves[currentMoveIndex] && (
                <p>{replayMoves[currentMoveIndex].description}</p>
              )}
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default GameHistory;