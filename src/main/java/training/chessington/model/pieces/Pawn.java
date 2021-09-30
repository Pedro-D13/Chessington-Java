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
        if (this.colour.equals(PlayerColour.WHITE)) {
            // move one square
            movesArray.add(new Move(from, from.plus(-1, 0)));
            // move two squares
            if ( 6 == from.getRow()) {
                movesArray.add(new Move(from, from.plus(-2, 0)));
            }

        } else {
            movesArray.add(new Move(from, from.plus(1, 0)));
            if ( 1 == from.getRow()) {
                movesArray.add(new Move(from, from.plus(2, 0)));
            }
        }
        return movesArray;
    }
}
