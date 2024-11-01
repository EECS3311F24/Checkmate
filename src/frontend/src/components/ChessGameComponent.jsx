import React, { useState, useEffect } from 'react';
import { Container, Card, Button, Row, Col } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const ChessGame = () => {
  const [gameState, setGameState] = useState({
    board: [],
    isGuest: false,
    isGameStarted: false,
    selectedPiece: null,
    possibleMoves: [],
  });

  const navigate = useNavigate();

  // Initialize empty 8x8 board
  useEffect(() => {
    const emptyBoard = Array(8).fill(null).map(() => Array(8).fill(null));
    setGameState(prev => ({ ...prev, board: emptyBoard }));
  }, []);

  const startGuestGame = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/game/guest/start', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
      });
      
      if (response.ok) {
        const initialGameState = await response.json();
        setGameState(prev => ({
          ...prev,
          isGuest: true,
          isGameStarted: true,
          board: initialGameState.board,
        }));
      }
    } catch (error) {
      console.error('Failed to start guest game:', error);
    }
  };

  const handleSquareClick = async (row, col) => {
    if (!gameState.isGameStarted) return;

    if (gameState.selectedPiece) {
      // Try to make a move
      try {
        const response = await fetch('http://localhost:8080/api/game/move', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            from: gameState.selectedPiece,
            to: { row, col },
          }),
        });

        if (response.ok) {
          const updatedGameState = await response.json();
          setGameState(prev => ({
            ...prev,
            board: updatedGameState.board,
            selectedPiece: null,
            possibleMoves: [],
          }));
        }
      } catch (error) {
        console.error('Failed to make move:', error);
      }
    } else {
      // Select piece and get possible moves
      try {
        const response = await fetch(`http://localhost:8080/api/game/moves?row=${row}&col=${col}`);
        if (response.ok) {
          const moves = await response.json();
          setGameState(prev => ({
            ...prev,
            selectedPiece: { row, col },
            possibleMoves: moves,
          }));
        }
      } catch (error) {
        console.error('Failed to get possible moves:', error);
      }
    }
  };

  const getPieceSymbol = (piece) => {
    if (!piece) return '';
    const symbols = {
      'WHITE_KING': '♔', 'WHITE_QUEEN': '♕', 'WHITE_ROOK': '♖',
      'WHITE_BISHOP': '♗', 'WHITE_KNIGHT': '♘', 'WHITE_PAWN': '♙',
      'BLACK_KING': '♚', 'BLACK_QUEEN': '♛', 'BLACK_ROOK': '♜',
      'BLACK_BISHOP': '♝', 'BLACK_KNIGHT': '♞', 'BLACK_PAWN': '♟'
    };
    return symbols[piece] || '';
  };

  const isSquareHighlighted = (row, col) => {
    return gameState.possibleMoves.some(move => 
      move.row === row && move.col === col
    );
  };

  // CSS for chess squares
  const squareStyle = (row, col) => ({
    width: '100%',
    paddingBottom: '100%', // Makes square responsive
    backgroundColor: (row + col) % 2 === 0 ? '#f0d9b5' : '#b58863',
    position: 'relative',
    cursor: 'pointer',
    border: gameState.selectedPiece?.row === row && 
           gameState.selectedPiece?.col === col ? '2px solid blue' : 'none',
    backgroundColor: isSquareHighlighted(row, col) ? 
      ((row + col) % 2 === 0 ? '#f7ec5e' : '#f7d358') : 
      ((row + col) % 2 === 0 ? '#f0d9b5' : '#b58863'),
  });

  const pieceStyle = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    fontSize: '2.5vw',
  };

  return (
    <Container className="mt-4">
      <Card className="mx-auto" style={{ maxWidth: '800px' }}>
        <Card.Header className="text-center">
          <h3>Chess Game</h3>
        </Card.Header>
        <Card.Body>
          {!gameState.isGameStarted ? (
            <div className="text-center">
              <Button 
                variant="primary" 
                size="lg" 
                onClick={startGuestGame}
                className="me-2"
              >
                Play as Guest
              </Button>
              <Button 
                variant="outline-primary" 
                size="lg" 
                onClick={() => navigate('/signup')}
              >
                Sign Up to Play
              </Button>
            </div>
          ) : (
            <Row className="g-0">
              {gameState.board.map((row, rowIndex) => (
                row.map((piece, colIndex) => (
                  <Col 
                    key={`${rowIndex}-${colIndex}`} 
                    xs={1.5} 
                    style={{ padding: 0 }}
                  >
                    <div
                      onClick={() => handleSquareClick(rowIndex, colIndex)}
                      style={squareStyle(rowIndex, colIndex)}
                    >
                      <div style={pieceStyle}>
                        {getPieceSymbol(piece)}
                      </div>
                    </div>
                  </Col>
                ))
              ))}
            </Row>
          )}
        </Card.Body>
      </Card>
    </Container>
  );
};

export default ChessGame;