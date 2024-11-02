package ca.yorku.checkmate.Model.mainGame;

public class Controller {
    private Chess chess;
    private Player pW;
    private Player pB;

    public Controller() {
        this.pW = new Player(ChessBoard.white);
        this.pB = new Player(ChessBoard.black);
        this.chess = new Chess(pW, pB);
    }

    public void play() {

        while (!chess.isGameOver()) {
            this.report();

            Move from = null;
            Move to = null;
            Player whosTurn = chess.getWhosTurn();

            if (whosTurn.getPlayerColor() == ChessBoard.white) {
                from = pW.getMove();
                to = pW.getMove();
            }
            if (whosTurn.getPlayerColor() == ChessBoard.black) {
                from = pB.getMove();
                to = pB.getMove();
            }
            this.reportMove(whosTurn.getPlayerColor(), from, to);
            ChessPiece piece = this.chess.getChessPiece(from.getRow(), from.getColumn());
            if(piece != null) {
                if(piece.getColor() == whosTurn.getPlayerColor()) {
                    chess.move(to.getRow(), to.getColumn(), piece);
                }
            }
        }
        this.reportFinal();
    }

    private void report() {

        String s = chess.getChessBoard().toString()
                + " " + this.chess.getWhosTurn().getPlayerColor() + " moves next";
        System.out.println(s);
    }

    private void reportMove(char whosTurn, Move from, Move to) {
        System.out.println(whosTurn + " makes move from " + from.getRow() +
                ":"+from.getColumn() + " to " + to.getRow() + ":"+to.getColumn()+"\n");
    }

    private void reportFinal() {

        String s = chess.getChessBoard().toString() +
                "  " + chess.getWinner() + " won\n";
        System.out.println(s);
    }

    public static void main(String[] args) {
        Controller c = new Controller();
        c.play();
    }
}
