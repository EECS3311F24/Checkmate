import React, { useState } from 'react';
import { Container, Card, Button, Row, Col, Alert } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { startGuestGame, makeMove, getPossibleMoves } from '../services/ChessService';

const ChessGame = () => {
    // Piece image mapping
    const pieceImages = {
        'WHITE': {
            'PAWN': 'static/images/chess/WHITE_PAWN.png',
            'ROOK': 'static/images/chess/WHITE-ROOK.png',
            'KNIGHT': 'static/images/chess/WHITE-KNIGHT.png',
            'BISHOP': 'static/images/chess/WHITE-BISHOP.png',
            'QUEEN': 'static/images/chess/WHITE-QUEEN.png',
            'KING': 'static/images/chess/WHITE-KING.png'
        },
        'BLACK': {
            'PAWN': 'static/images/chess/BLACK-PAWN.png',
            'ROOK': 'static/images/chess/BLACK-ROOK.png',
            'KNIGHT': 'static/images/chess/BLACK-KNIGHT.png',
            'BISHOP': 'static/images/chess/BLACK-BISHOP.png',
            'QUEEN': 'static/images/chess/BLACK-QUEEN.png',
            'KING': 'static/images/chess/BLACK-KING.png'
        }
    };
const ChessGame = () => {
    const [gameState, setGameState] = useState({
        board: initializeBoard(),
        isGameStarted: false,
        selectedPiece: null,
        possibleMoves: [],
        error: null,
        currentPlayer: 'WHITE'
    });

    const navigate = useNavigate();

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

    const handleSquareClick = async (row, col) => {
        if (!gameState.isGameStarted) return;

        try {
            if (gameState.selectedPiece) {
                const response = await makeMove(
                    gameState.selectedPiece,
                    { row, col }
                ).catch(() => {
                    throw new Error("Invalid move");
                });

                setGameState(prev => ({
                    ...prev,
                    board: response?.data?.board || prev.board,
                    selectedPiece: null,
                    possibleMoves: [],
                    currentPlayer: response?.data?.currentPlayer || prev.currentPlayer,
                    error: null
                }));
            } else {
                const piece = gameState.board[row][col];
                if (piece && piece.color === gameState.currentPlayer) {
                    const response = await getPossibleMoves(row, col).catch(() => ({ data: [] }));
                    setGameState(prev => ({
                        ...prev,
                        selectedPiece: { row, col },
                        possibleMoves: response.data,
                        error: null
                    }));
                }
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

    return (
        <Container className="mt-4">
            <Card className="mx-auto" style={{ maxWidth: '600px' }}>
                <Card.Header className="bg-dark text-white">
                    <h3 className="text-center mb-0">Chess Game</h3>
                </Card.Header>
                <Card.Body className="p-4">
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
                        <div>
                            <div className="text-center mb-3">
                                <h4>Current Player: {gameState.currentPlayer}</h4>
                            </div>
                            <div className="chess-board">
                                {gameState.board.map((row, rowIndex) => (
                                    <Row key={rowIndex} className="g-0">
                                        {row.map((piece, colIndex) => (
                                            <Col 
                                                key={`${rowIndex}-${colIndex}`} 
                                                xs={1.5}
                                                style={{ 
                                                    aspectRatio: '1/1',
                                                    padding: 0,
                                                    position: 'relative'
                                                }}
                                            >
                                                <div
                                                    onClick={() => handleSquareClick(rowIndex, colIndex)}
                                                    className={`
                                                        chess-square
                                                        ${(rowIndex + colIndex) % 2 === 0 ? 'light-square' : 'dark-square'}
                                                        ${gameState.selectedPiece?.row === rowIndex && 
                                                          gameState.selectedPiece?.col === colIndex ? 'selected' : ''}
                                                        ${gameState.possibleMoves.some(
                                                            move => move.row === rowIndex && move.col === colIndex
                                                        ) ? 'possible-move' : ''}
                                                    `}
                                                >
                                                    {piece && (
                                                        <div className={`chess-piece ${piece.color.toLowerCase()}`}>
                                                            {piece.symbol}
                                                        </div>
                                                    )}
                                                </div>
                                            </Col>
                                        ))}
                                    </Row>
                                ))}
                            </div>
                        </div>
                    )}
                </Card.Body>
            </Card>

            </Container>
        );         
};
}
export default ChessGame;
