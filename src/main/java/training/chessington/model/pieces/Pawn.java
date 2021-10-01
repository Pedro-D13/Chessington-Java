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
        int startingSquareForPawn = (this.colour == PlayerColour.WHITE) ? 6 : 1;
        boolean inBoundsRow = outOfBounds(from.getRow() - 1, from.getRow() + 1);
        boolean inBoundsCol = outOfBounds(from.getCol() - 1, from.getCol() + 1);

        if (inBoundsRow && inBoundsCol) {
            Move intendedMove = createIntendedMove(from, moveOneSquare, 0);
            // move one square
            // check if there are piece in front
            moveForwardOneSquare(board, movesArray, intendedMove);
            // move two squares
            // check if there are piece in front
            intendedMove = createIntendedMove(from, moveTwoSquares, 0);
            moveForwardTwoSquares(from, board, movesArray, startingSquareForPawn, intendedMove);
        }

        boolean captureLeftPiece = board.validMove(new Coordinates(from.getRow(), from.getCol()).plus(moveOneSquare, moveOneSquare));
        boolean captureRightPiece = board.validMove(new Coordinates(from.getRow(), from.getCol()).plus(moveOneSquare, moveOneSquare * -1));


        if (captureLeftPiece && this.colour == PlayerColour.WHITE) {
            Move intendedMove = createIntendedMove(from, moveOneSquare, -1);
            if (board.get(intendedMove.getTo()).getType() != null && board.get(intendedMove.getTo()).getColour() != PlayerColour.WHITE) {
                movesArray.add(intendedMove);
            }
        }
        if (captureRightPiece && this.colour == PlayerColour.WHITE) {

            Move intendedMove = createIntendedMove(from, moveOneSquare, 1);
            if (board.get(intendedMove.getTo()).getType() != null && board.get(intendedMove.getTo()).getColour() != PlayerColour.WHITE) {
                movesArray.add(intendedMove);
            }
        }
        if (captureLeftPiece && this.colour == PlayerColour.BLACK) {
            Move intendedMove = createIntendedMove(from, moveOneSquare, 1);
            if (board.get(intendedMove.getTo()).getType() != null && board.get(intendedMove.getTo()).getColour() != PlayerColour.BLACK) {
                movesArray.add(intendedMove);
            }
        }
        if (captureRightPiece && this.colour == PlayerColour.BLACK) {
            Move intendedMove = createIntendedMove(from, moveOneSquare, -1);
            if (board.get(intendedMove.getTo()).getType() != null && board.get(intendedMove.getTo()).getColour() != PlayerColour.BLACK) {
                movesArray.add(intendedMove);
            }
        }


        return movesArray;
    }


    private void moveForwardTwoSquares(Coordinates from, Board board, ArrayList<Move> movesArray, int startSquareForPawn, Move intendedMove) {
        if (startSquareForPawn == from.getRow() && board.get(intendedMove.getTo()) == null) {
            movesArray.add(intendedMove);
        }
    }

    private void moveForwardOneSquare(Board board, ArrayList<Move> movesArray, Move intendedMove) {
        if (board.get(intendedMove.getTo()) == null) {
            movesArray.add(intendedMove);
        }
    }

    public boolean outOfBounds(int lessThanZero, int moreThanSeven) {
        return lessThanZero >= 0 && moreThanSeven <= 7;
    }

    public Move createIntendedMove(Coordinates from, int moveAlongY, int moveAlongX) {
        return new Move(from, from.plus(moveAlongY, moveAlongX));
    }


}
