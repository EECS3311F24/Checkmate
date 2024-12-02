import React from 'react';
import './chess.css';

const ChessBoard = (board) => {
    const chessBoard = convertBoard(board.board);
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

    function convertBoard(board) {
        const b = Array(8).fill(null).map(() => Array(8).fill(null));
        if (!Array.isArray(board)) return b;
        board.forEach((row, rowIndex) => {
            if (!Array.isArray(row)) return b;
            console.log("test")
            row.forEach((piece, colIndex) => {
                if (piece.chessPiece !== null) {
                    var p = convertPiece(piece)
                    b[rowIndex][colIndex] = p;
                }
            })
        })
        return b;
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

    return (
        <div className="chess-board">
            {chessBoard.map((row, rowIndex) =>
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
};

export default ChessBoard;