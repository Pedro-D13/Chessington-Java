package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList<Move> movesArray = new ArrayList<>();
        int moveOneSquare = (this.colour == PlayerColour.WHITE) ? -1 : 1;
        int moveTwoSquares = (this.colour == PlayerColour.WHITE) ? -2 : 2;
        int startSquareForPawn = (this.colour == PlayerColour.WHITE) ? 6 : 1;
        // move one square
        Move intendedMove = new Move(from, from.plus(moveOneSquare, 0));
        // check if there are piece in front
        if (board.get(intendedMove.getTo()) == null) {
            movesArray.add(intendedMove);
        }
        // move two squares
        intendedMove = new Move(from, from.plus(moveTwoSquares, 0));
        // check if there are piece in front
        if (startSquareForPawn == from.getRow() && board.get(intendedMove.getTo()) == null) {
            movesArray.add(intendedMove);
        }


        return movesArray;
    }
}
