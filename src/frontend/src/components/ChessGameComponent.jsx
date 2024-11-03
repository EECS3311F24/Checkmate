import React, { useState } from 'react';
import { startGuestGame } from '../services/ChessService';
import { getTranslation, useLanguage } from './LanguageProvider';
import './chess.css';

const ChessGame = () => {

  const { language, setLanguage } = useLanguage();
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
        selectedPiece: null,
        possibleMoves: [],
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

      function convertBoard(board) {
        const b = Array(8).fill(null).map(() => Array(8).fill(null));
        board.forEach((row, rowIndex) => (
          row.forEach((piece, colIndex) => {
            if (piece.chessPiece !== null) {
              var p = convertPiece(piece)
              console.log(p)
              b[rowIndex][colIndex] = p;
            }
          }
          )))
      }

      function convertPiece(piece) {
        piece = piece.chessPiece;
        piece.color = convertColor(piece.color)
        piece.type = convertType(piece.char)
        return {type:piece.type,color:piece.color};
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


      const handleStartGame = async () => {
        try {
          const response = await startGuestGame();
          console.log(response.data);
          setGameState(prev => ({
            // TODO insert proper names
              ...prev,
              id: response.data.id,
              id1: response.data.id1,
              id2: response.data.id2,
              chess: response.data.chess,
              board: convertBoard(response.data.chess.chessBoard.board) || initializeBoard(),
              isGameStarted: true,
              error: null
          }));
        } catch (error) {
          setGameState(prev => ({
              ...prev,
              error: "Failed to start game. Please try again."
          }));
        }
      };
    
      const handleSquareClick = (row, col) => {
        if (!gameState.isGameStarted) return;
    
        const piece = gameState.board[row][col];
        
        if (gameState.selectedPiece) {
          const newBoard = JSON.parse(JSON.stringify(gameState.board));
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
            board: newBoard,
            selectedPiece: null,
            currentPlayer: prev.currentPlayer === 'WHITE' ? 'BLACK' : 'WHITE'
          }));
        } 
        else if (piece && piece.color === gameState.currentPlayer) {
          setGameState(prev => ({
            ...prev,
            selectedPiece: { row, col }
          }));
        }
      };
    
      return (
        <div className="chess-container">
          <div className="chess-header">
            <h2>{getTranslation("ChessGameComponentChessGame",language)}</h2>
          </div>
          <div className="chess-content">
            {gameState.error && (
              <div className="chess-error">
                {gameState.error}
              </div>
            )}
            
            {!gameState.isGameStarted ? (
              <div className="chess-controls welcome-screen">
               
                <button 
                  className="chess-button"
                  onClick={handleStartGame}
                >
                  {getTranslation("ChessGameComponentPlayAsGuest",language)}
                </button>
                
              </div>
            ) : (
              <div>
                <div className="chess-current-player">
                {getTranslation("ChessGameComponentCurrentPlayer",language)} {gameState.currentPlayer}
                </div>
                
                {/*Captured pieces display*/}

                <div className="chess-captured-pieces">
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
        </div>
      );
    };

export default ChessGame;
