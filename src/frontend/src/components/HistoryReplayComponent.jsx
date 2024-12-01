import React, { useEffect, useState } from "react";

const HistoryReplayComponent = ({ boardId, apiEndpoint }) => {
  const [history, setHistory] = useState([]);
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [currentMoveIndex, setCurrentMoveIndex] = useState(0);

  useEffect(() => {
    // Fetch game history
    const fetchHistory = async () => {
      try {
        setIsLoading(true);
        const response = await fetch(`${apiEndpoint}/chats?boardId=${boardId}`);
        if (!response.ok) {
          throw new Error(`Error ${response.status}: ${response.statusText}`);
        }
        const data = await response.json();
        setHistory(data.history || []); // Assume `data.history` contains moves
        setIsLoading(false);
      } catch (err) {
        setError(err.message);
        setIsLoading(false);
      }
    };

    fetchHistory();
  }, [boardId, apiEndpoint]);

  const handleReplay = (index) => {
    setCurrentMoveIndex(index);
  };

  const renderBoard = (move) => {
    return (
      <div className="chess-board">
        {move.board.map((row, rowIndex) => (
          <div key={rowIndex} className="chess-row">
            {row.map((cell, cellIndex) => (
              <div key={cellIndex} className={`chess-cell ${cell.color}`}>
                {cell.piece}
              </div>
            ))}
          </div>
        ))}
      </div>
    );
  };

  if (isLoading) return <div>Loading game history...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className="history-replay-component">
      <h2>Game History</h2>
      {history.length === 0 ? (
        <p>No moves found.</p>
      ) : (
        <div className="replay-container">
          <div className="move-list">
            <h3>Moves</h3>
            {history.map((move, index) => (
              <button
                key={index}
                onClick={() => handleReplay(index)}
                className={`move-button ${
                  currentMoveIndex === index ? "active" : ""
                }`}
              >
                Move {index + 1}: {move.description}
              </button>
            ))}
          </div>
          <div className="board-display">
            <h3>Move {currentMoveIndex + 1}</h3>
            {renderBoard(history[currentMoveIndex])}
          </div>
        </div>
      )}
    </div>
  );
};

export default HistoryReplayComponent;
