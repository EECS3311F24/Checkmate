import React, { useState, useEffect } from 'react';
import { Container, Card, Button, Row, Col, Alert } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { startGuestGame, makeMove, getPossibleMoves } from '../services/ChessService';

const ChessGame = () => {
    const [gameState, setGameState] = useState({
        board: Array(8).fill(null).map(() => Array(8).fill(null)),
        isGameStarted: false,
        selectedPiece: null,
        possibleMoves: [],
        error: null,
        currentPlayer: 'WHITE'
    });

    const navigate = useNavigate();

    const handleStartGame = async () => {
        try {
            const response = await startGuestGame();
            setGameState(prev => ({
                ...prev,
                board: response.data.board,
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

    const handleSquareClick = async (row, col) => {
        if (!gameState.isGameStarted) return;

        try {
            if (gameState.selectedPiece) {
                // Try to make a move
                const response = await makeMove(gameState.selectedPiece, { row, col });
                setGameState(prev => ({
                    ...prev,
                    board: response.data.board,
                    selectedPiece: null,
                    possibleMoves: [],
                    currentPlayer: response.data.currentPlayer,
                    error: null
                }));
            } else {
                // Get possible moves for selected piece
                const response = await getPossibleMoves(row, col);
                setGameState(prev => ({
                    ...prev,
                    selectedPiece: { row, col },
                    possibleMoves: response.data,
                    error: null
                }));
            }
        } catch (error) {
            setGameState(prev => ({
                ...prev,
                error: "Invalid move. Please try again.",
                selectedPiece: null,
                possibleMoves: []
            }));
        }
    };

    const squareStyle = (row, col) => ({
        width: '100%',
        paddingBottom: '100%',
        backgroundColor: (row + col) % 2 === 0 ? '#f0d9b5' : '#b58863',
        position: 'relative',
        cursor: 'pointer',
        border: gameState.selectedPiece?.row === row && 
               gameState.selectedPiece?.col === col ? '2px solid blue' : 'none'
    });

    return (
        <Container className="mt-4">
            <Card className="chess-game mx-auto" style={{ maxWidth: '800px' }}>
                <Card.Header>
                    <h3 className="text-center mb-0">Chess Game</h3>
                </Card.Header>
                <Card.Body>
                    {gameState.error && (
                        <Alert variant="danger" className="mb-3">
                            {gameState.error}
                        </Alert>
                    )}
                    
                    {!gameState.isGameStarted ? (
                        <div className="text-center">
                            <Button 
                                variant="primary" 
                                size="lg" 
                                onClick={handleStartGame}
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
                        <>
                            <div className="text-center mb-3">
                                Current Player: {gameState.currentPlayer}
                            </div>
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
                                                className={
                                                    gameState.possibleMoves.some(
                                                        move => move.row === rowIndex && move.col === colIndex
                                                    ) ? 'possible-move' : ''
                                                }
                                            >
                                                <div className="chess-piece">
                                                    {piece && piece.symbol}
                                                </div>
                                            </div>
                                        </Col>
                                    ))
                                ))}
                            </Row>
                        </>
                    )}
                </Card.Body>
            </Card>
        </Container>
    );
};

export default ChessGame;