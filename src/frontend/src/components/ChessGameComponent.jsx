import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useQuery } from 'react-query';
import { startGuestGame, move, getBoard, deleteBoard, getAllBoards } from '../services/ChessService';
import { getTranslation, useLanguage } from './LanguageProvider';
import HistoryReplayComponent from './HistoryReplayComponent';
import { useTheme } from './ThemeProvider';
import './chess.css';
import './activeChessboards.css'; // Create a new CSS file for styling the active boards
import ChatBox from './ChatBox';

const ChessGame = () => {
  const { language } = useLanguage();
  const { theme } = useTheme();
  const [mode, setMode] = useState('S');
  const [isTimerMode, setIsTimerMode] = useState(false);
  const [isCustomMode, setIsCustomMode] = useState(false);
  const [timeLimit, setTimeLimit] = useState(300);
  const [playerTimes, setPlayerTimes] = useState({
    WHITE: 300,
    BLACK: 300
  });

    // Fetch all active chessboards
    const { data: activeChessboards, refetch: refetchActiveBoards } = useQuery(
      'activeChessBoards',
      getAllBoards,
      { refetchInterval: 5000 } // Update every 5 seconds
    );

  const fetchChessBoard = async () => {
    if (gameState !== null && (!gameState.isGameStarted || gameState.isGameOver)) return;
    const res = await getBoard(gameState.id);
    updateBoard(res?.data);
    return res?.data;
  };

  useQuery('chessBoards', fetchChessBoard, { refetchInterval: 500 })

  // Piece image mapping
  const pieceImages = {
    'WHITE': {
      'PAWN': '/images/chess/WHITE_PAWN.png',
      'ROOK': '/images/chess/WHITE_ROOK.png',
      'KNIGHT': '/images/chess/WHITE_KNIGHT.png',
      'BISHOP': '/images/chess/WHITE_BISHOP.png',
      'QUEEN': '/images/chess/WHITE_QUEEN.png',
      'KING': '/images/chess/WHITE_KING.png'
    },
    'BLACK': {
      'PAWN': '/images/chess/BLACK_PAWN.png',
      'ROOK': '/images/chess/BLACK_ROOK.png',
      'KNIGHT': '/images/chess/BLACK_KNIGHT.png',
      'BISHOP': '/images/chess/BLACK_BISHOP.png',
      'QUEEN': '/images/chess/BLACK_QUEEN.png',
      'KING': '/images/chess/BLACK_KING.png'
    }
  };

  const [gameState, setGameState] = useState({
    id: null,
    id1: null,
    id2: null,
    chess: null,
    board: initializeBoard(),
    isGameStarted: false,
    isGameOver: false,
    winner: null,
    gameHistory: [],
    selectedPiece: null,
    error: null,
    currentPlayer: 'WHITE',
    capturedPieces: {
      WHITE: [],
      BLACK: []
    },
    status: null // New state for game status messages
  });

  // Initialize the board with starting positions
  function initializeBoard() {
    const board = Array(8).fill(null).map(() => Array(8).fill(null));

    // Set up initial pieces
    const setupPieces = () => {
      // Black pieces
      board[0] = [
        { type: 'ROOK', color: 'BLACK' },
        { type: 'KNIGHT', color: 'BLACK' },
        { type: 'BISHOP', color: 'BLACK' },
        { type: 'QUEEN', color: 'BLACK' },
        { type: 'KING', color: 'BLACK' },
        { type: 'BISHOP', color: 'BLACK' },
        { type: 'KNIGHT', color: 'BLACK' },
        { type: 'ROOK', color: 'BLACK' }
      ];
      board[1] = Array(8).fill({ type: 'PAWN', color: 'BLACK' });

      // White pieces
      board[6] = Array(8).fill({ type: 'PAWN', color: 'WHITE' });
      board[7] = [
        { type: 'ROOK', color: 'WHITE' },
        { type: 'KNIGHT', color: 'WHITE' },
        { type: 'BISHOP', color: 'WHITE' },
        { type: 'QUEEN', color: 'WHITE' },
        { type: 'KING', color: 'WHITE' },
        { type: 'BISHOP', color: 'WHITE' },
        { type: 'KNIGHT', color: 'WHITE' },
        { type: 'ROOK', color: 'WHITE' }
      ];
    };

    setupPieces();
    return board;
  }

   // Join an existing chessboard
const joinChessboard = async (boardId) => {
  try {
    // Fetch the board data by its ID
    const response = await getBoard(boardId);
    const data = response?.data;

    // Set the game state to join the selected chessboard
    setGameState(prev => ({
      ...prev,
      id: boardId, // Update gameState.id with the selected board ID
      chess: data.chess,
      board: convertBoard(data.chess.chessBoard.board),
      isGameStarted: true,
      currentPlayer: convertColor(data.chess.whosTurn.playerColor),
      isGameOver: data.chess.gameOver,
      winner: data.chess.gameOver ? convertColor(data.chess.winner.playerColor) : null,
      error: null,
      capturedPieces: convertCapturedPieces(data.chess.chessBoard.capturedPieces) // Update captured pieces if available
    }));

    // If the timer mode is enabled, set the initial time limits
    if (isTimerMode) {
      setPlayerTimes({
        WHITE: timeLimit,
        BLACK: timeLimit
      });
    }
  } catch (error) {
    console.error('Error joining the chessboard:', error);
    setGameState(prev => ({
      ...prev,
      error: "Failed to join board. Please try again."
    }));
  }
};

  function convertBoard(board) {
    const b = Array(8).fill(null).map(() => Array(8).fill(null));
    board.forEach((row, rowIndex) => (
      row.forEach((piece, colIndex) => {
        if (piece.chessPiece !== null) {
          var p = convertPiece(piece)
          b[rowIndex][colIndex] = p;
        }
      }
      )))
    return b;
  }

  const [isFirstMoveMade, setIsFirstMoveMade] = useState(false);
  useEffect(() => {
    let interval;
    if (gameState.isGameStarted && isTimerMode && isFirstMoveMade && !gameState.isGameOver) {
      interval = setInterval(() => {
        setPlayerTimes(prev => ({
          ...prev,
          [gameState.currentPlayer]: Math.max(0, prev[gameState.currentPlayer] - 1)
        }));
      }, 1000);
    }
    return () => clearInterval(interval);
  }, [gameState.isGameStarted, isTimerMode, gameState.currentPlayer, isFirstMoveMade, gameState.isGameOver]);

  useEffect(() => {
    if (isTimerMode && playerTimes[gameState.currentPlayer] === 0) {
      handleTimeUp();
    }
  }, [playerTimes, gameState.currentPlayer]);

  function convertCapturedPieces(captured) {
    var white = [];
    var black = [];
    // TODO piece is somehow null sometimes
    captured.forEach(piece => {
      console.log(piece);
      if (piece !== null && piece.chessPiece !== null) {
        var p = convertPiece(piece);
        console.log(p);
        if (p.color === 'BLACK') black.push(p);
        if (p.color === 'WHITE') white.push(p);
      }
    })
    return { WHITE: white, BLACK: black };
  }

  function convertPiece(piece) {
    piece = piece.chessPiece;
    piece.color = convertColor(piece.color)
    piece.type = convertType(piece.char)
    return { type: piece.type, color: piece.color };
  }

  function convertColor(color) {
    if (color === null) return 'EMPTY'
    if (color === 'B') return 'BLACK'
    if (color === 'W') return 'WHITE'
    return color;
  }

  function convertType(type) {
    if (type === null || type === ' ') return 'EMPTY'
    if (type === 'R') return 'ROOK'
    if (type === 'N') return 'KNIGHT'
    if (type === 'B') return 'BISHOP'
    if (type === 'Q') return 'QUEEN'
    if (type === 'K') return 'KING'
    if (type === 'P') return 'PAWN'
    return null;
  }

  function updateBoard(data) {
    if (data) {
      setGameState(prev => ({
        ...prev,
        chess: data.chess,
        board: convertBoard(data.chess.chessBoard.board),
        currentPlayer: convertColor(data.chess.whosTurn.playerColor),
        isGameOver: data.chess.gameOver,
        winner: data.chess.gameOver ? convertColor(data.chess.winner.playerColor) : null,
        gameHistory: data.chess.gameHistory,
        //capturedPieces: convertCapturedPieces(response.data.chess.chessBoard.capturedPieces)
      }));
    }
  }

  const [gameStatus, setGameStatus] = useState(null);
  const handleTimeUp = () => {
    const winner = gameState.currentPlayer === 'WHITE' ? 'BLACK' : 'WHITE';
    const statusMessage = `Game Over - ${winner} wins! ${gameState.currentPlayer} ran out of time.`;
    console.log(statusMessage); // Log to console
    setGameStatus(statusMessage);
    setGameState(prev => ({
      ...prev,
      status: statusMessage,
      isGameStarted: false
    }));
  };

  const navigator = useNavigate();
  async function quitGame(id) {
    try {
      const response = await deleteBoard(id);
    } catch (error) { console.error(error) }
    setGameState(prev => ({
      ...prev,
      id: null,
      id1: null,
      id2: null,
      chess: null,
      board: initializeBoard(),
      isGameStarted: false,
      isGameOver: false,
      winner: null,
      selectedPiece: null,
      error: null,
      currentPlayer: 'WHITE',
      capturedPieces: {
        WHITE: [],
        BLACK: []
      },
      status: null
    }));
    navigator(`/play/`);
  }

  async function handleStartGame() {
    try {
      const response = await startGuestGame(isCustomMode ? mode : 'S');
      setIsFirstMoveMade(false); // Reset first move state
      setGameState(prev => ({
        ...prev,
        id: response.data.id,
        id1: response.data.id1,
        id2: response.data.id2,
        chess: response.data.chess,
        board: convertBoard(response.data.chess.chessBoard.board),
        isGameStarted: true,
        error: null,
        currentPlayer: convertColor(response.data.chess.whosTurn.playerColor)
      }));
      if (isTimerMode) {
        setPlayerTimes({
          WHITE: timeLimit,
          BLACK: timeLimit
        });
      }
    } catch (error) {
      setGameState(prev => ({
        ...prev,
        error: "Failed to start game. Please try again."
      }));
    }
  };

  async function handleSquareClick(row, col) {
    // TODO start game when there are two players, eg id1 and id2 are not null
    if (!(gameState.isGameStarted && !gameState.isGameOver)) return;
    const piece = gameState.board[row][col];
    if (piece && gameState.currentPlayer === piece.color) {
      setGameState(prev => ({
        ...prev,
        selectedPiece: { row, col }
      }));
    } else if (gameState.selectedPiece) {
      const moves = { start: { row: gameState.selectedPiece.row, col: gameState.selectedPiece.col }, end: { row: row, col: col } };
      await move(gameState.id, moves)
        .then(response => {
          if (!isFirstMoveMade && gameState.currentPlayer === 'WHITE') {
            setIsFirstMoveMade(true);
          }
          const newBoard = convertBoard(response.data.chess.chessBoard.board);
          const selectedPiece = gameState.board[gameState.selectedPiece.row][gameState.selectedPiece.col];
          const targetPiece = gameState.board[row][col];

          // If there's a piece at the target location, add it to captured pieces
          if (targetPiece) {
            const newCapturedPieces = {
              ...gameState.capturedPieces,
              [selectedPiece.color]: [
                ...gameState.capturedPieces[selectedPiece.color],
                targetPiece
              ]
            };
            setGameState(prev => ({
              ...prev,
              capturedPieces: newCapturedPieces,
              status: `${selectedPiece.color} captured ${targetPiece.color} ${targetPiece.type}`
            }));
          }
          newBoard[gameState.selectedPiece.row][gameState.selectedPiece.col] = null;
          newBoard[row][col] = selectedPiece;

          setGameState(prev => ({
            ...prev,
            chess: response.data.chess,
            board: newBoard,
            selectedPiece: null,
            currentPlayer: convertColor(response.data.chess.whosTurn.playerColor)
          }));
        })
        .catch(e => {
          setGameState(prev => ({
            ...prev,
            selectedPiece: null,
            status: null
          }));
        })
    }
    else if (piece && piece.color === gameState.currentPlayer) {
      setGameState(prev => ({
        ...prev,
        selectedPiece: { row, col }
      }));
    }
  };


  const cardStyle = theme === 'dark' ? { backgroundColor: '#333333', color: '#ffffff' } : theme === 'solarized' ? { backgroundColor: '#f0f8ff', color: '#000000' } : { backgroundColor: '#ffffff', color: '#000000' };

  return (
    <div className="chess-container">
      <div className="chess-header style={cardStyle}">
      <h2>{getTranslation("ChessGameComponentChessGame", language)}
          {gameState.isGameStarted && gameState.id && (
            <span> - Lobby ID: {gameState.id}</span>
          )}
        </h2>
      </div>
      <div className="chess-content" style={cardStyle}>
        {gameState.error && (
          <div className="chess-error">
            {gameState.error}
          </div>
        )}
        {gameStatus && (
          <div className="game-status-message">
            {gameStatus}
          </div>
        )}
        {!gameState.isGameStarted ? (
          <div className="chess-controls welcome-screen">

            <button
              className="chess-button"
              onClick={handleStartGame}
            >
              {getTranslation("ChessGameComponentPlayAsGuest", language)}
            </button>
            <button
              className="chess-button"
              onClick={() => setIsTimerMode(!isTimerMode)}
            >
              {isTimerMode ? 'Disable Timer' : 'Enable Timer'}
            </button>
            <button
              className="chess-button"
              onClick={() => setIsCustomMode(!isCustomMode)}
            >
              {isCustomMode ? 'Disable Custom' : 'Enable Custom'}
            </button>

            {isTimerMode && (
              <select
                className="time-select"
                value={timeLimit}
                onChange={(e) => setTimeLimit(parseInt(e.target.value))}
              >
                <option value={60}>1 minutes</option>
                <option value={300}>5 minutes</option>
                <option value={600}>10 minutes</option>
                <option value={900}>15 minutes</option>
              </select>
            )}
            {isCustomMode && (
              <select
                className="time-select"
                value={mode}
                onChange={(e) => setMode(e.target.value)}
              >
                <option value={'S'}>Standard</option>
                <option value={'P'}>Pawns Game</option>
                <option value={'N'}>No Pawns</option>
              </select>
            )}
          </div>
        ) : (
          <div>
            <div className="container text-center">
              {<button className='btn btn-danger' onClick={() => quitGame(gameState.id)}>{getTranslation("ChessGameComponentQuit", language)}</button>}
            </div>
            <div className="chess-current-player" style={cardStyle}>
              {getTranslation("ChessGameComponentCurrentPlayer", language)}
              {(gameState.currentPlayer === 'WHITE' ? getTranslation("ChessGameComponentWhite", language)
                : getTranslation("ChessGameComponentBlack", language))}
              {gameState.isGameOver && <div className="chess-status-banner">
                {gameState.winner + " is the winner!"}
              </div>}
            </div>

            {isTimerMode && (
              <div className="timers">
                <div className="player-timer" style={cardStyle}>
                  White: {Math.floor(playerTimes.WHITE / 60)}:{(playerTimes.WHITE % 60).toString().padStart(2, '0')}
                </div>
                <div className="player-timer" style={cardStyle}>
                  Black: {Math.floor(playerTimes.BLACK / 60)}:{(playerTimes.BLACK % 60).toString().padStart(2, '0')}
                </div>
              </div>
            )}

            {/*Captured pieces display*/}

            <div className="chess-captured-pieces" style={cardStyle}>
              <div className="captured-white">
                {gameState.capturedPieces.WHITE.map((piece, index) => (
                  <img
                    key={index}
                    src={pieceImages[piece.color][piece.type]}
                    alt={`Captured ${piece.color} ${piece.type}`}
                    className="captured-piece"
                  />
                ))}
              </div>
              <div className="captured-black">
                {gameState.capturedPieces.BLACK.map((piece, index) => (
                  <img
                    key={index}
                    src={pieceImages[piece.color][piece.type]}
                    alt={`Captured ${piece.color} ${piece.type}`}
                    className="captured-piece"
                  />
                ))}
              </div>
            </div>

            <div className="chess-board">
              {gameState.board.map((row, rowIndex) => (
                row.map((piece, colIndex) => (
                  <div
                    key={`${rowIndex}-${colIndex}`}
                    onClick={() => handleSquareClick(rowIndex, colIndex)}
                    className={`
                          chess-square
                          ${(rowIndex + colIndex) % 2 === 0 ? 'light' : 'dark'}
                          ${gameState.selectedPiece?.row === rowIndex &&
                        gameState.selectedPiece?.col === colIndex ? 'selected' : ''}
                        `}
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
              ))}
            </div>
          </div>   
        )}
      </div>
      { gameState.isGameStarted && <ChatBox boardId={gameState.id} /> }
      {gameState.isGameStarted &&<div className="history-replay-section">
        <HistoryReplayComponent gameHistory={gameState.gameHistory} boardId={gameState.id} />
      </div>}
      {/* Display list of all active chessboards */}
      <div className="active-chessboards" style={cardStyle}>
          <h3>Active Chessboards</h3>
          <div className="chessboard-list">
            {activeChessboards && activeChessboards.length > 0 ? (
              activeChessboards.map((board, index) => (
                <div
                  key={index}
                  className="chessboard-card" style={cardStyle}
                  onClick={() => joinChessboard(board.id)}
                  disabled={gameState.id === board.id} // Disable button if already joined
                >
                  <h4>Board ID: {board.id}</h4>
                  <p>Status: {board.isGameOver ? 'Game Over' : 'In Progress'}</p>
                  <button className="view-board-button">{gameState.id === board.id ? 'Currently Joined' : 'Join Board'}</button>
                </div>
              ))
            ) : (
              <p>No active chessboards at the moment.</p>
            )}
          </div>
        </div>
    </div>
  );
};

export default ChessGame;