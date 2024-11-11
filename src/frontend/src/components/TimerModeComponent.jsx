import React, { useEffect } from 'react';

const Timer = ({ time, isActive, onTimeUp }) => {
  const minutes = Math.floor(time / 60);
  const seconds = time % 60;

  useEffect(() => {
    let interval = null;
    if (isActive && time > 0) {
      interval = setInterval(() => {
        if (time <= 1) {
          onTimeUp();
        }
      }, 1000);
    }
    return () => clearInterval(interval);
  }, [isActive, time, onTimeUp]);

  return (
    <div className={`chess-timer ${isActive ? 'active' : ''}`}>
      {String(minutes).padStart(2, '0')}:{String(seconds).padStart(2, '0')}
    </div>
  );
};

export default Timer;