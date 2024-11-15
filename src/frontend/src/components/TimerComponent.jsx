import React, { useState, useEffect } from 'react';

const TimerComponent = ({ timeLimit, onTimeUp, currentPlayer }) => {
  const [timeRemaining, setTimeRemaining] = useState(timeLimit);
  const [intervalId, setIntervalId] = useState(null);

  useEffect(() => {
    if (timeRemaining > 0) {
      const id = setInterval(() => {
        setTimeRemaining((prevTime) => prevTime - 1);
      }, 1000);
      setIntervalId(id);
    } else {
      onTimeUp();
    }
    return () => {
      if (intervalId) clearInterval(intervalId);
    };
  }, [timeRemaining, onTimeUp, intervalId]);

  const handleReset = () => {
    setTimeRemaining(timeLimit);
  };

  return (
    <div className="timer-container">
      <h3>
        {currentPlayer}{' '}
        <span className={`timer ${timeRemaining <= 10 ? 'low-time' : ''}`}>
          {timeRemaining}
        </span>
      </h3>
      <button className="chess-button-outline" onClick={handleReset}>
        Reset
      </button>
    </div>
  );
};

export default TimerComponent;
