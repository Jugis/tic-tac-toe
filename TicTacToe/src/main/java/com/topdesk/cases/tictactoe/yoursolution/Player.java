/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdesk.cases.tictactoe.yoursolution;

import com.topdesk.cases.tictactoe.CellLocation;
import com.topdesk.cases.tictactoe.CellState;
import com.topdesk.cases.tictactoe.GameBoard;
import java.util.List;

/**
 *
 * @author VPM
 */
public class Player { 
    
    public enum player_enum {
        X_PLAYER, O_PLAYER
    };

    private player_enum typeOfPlayer;
    private CellState playerCellState;
    private CellState opponentCellState;

    public Player(player_enum identifier) {
        typeOfPlayer = identifier;
        decidePlayerState();
    }

    private void decidePlayerState() {
        if (typeOfPlayer == player_enum.X_PLAYER) {
            playerCellState = CellState.OCCUPIED_BY_X;
            opponentCellState = CellState.OCCUPIED_BY_O;
        } else {
            playerCellState = CellState.OCCUPIED_BY_O;
            opponentCellState = CellState.OCCUPIED_BY_X;
        }
    }

    CellLocation NextMove(GameBoard gameBoard) {
        CellLocation returnValue;

        
        returnValue = new Win().isWinning(gameBoard, this);
        if (returnValue != null) {
            return returnValue;
        }
        
        returnValue = new BlockOpponent().blockOpponentFromWinning(gameBoard, this);
        //returnValue = blockOpponnent(gameBoard, this);
        if (returnValue != null) {
            return returnValue;
        }
        returnValue = new Fork().fork(gameBoard, this);
        if (returnValue != null) {
            return returnValue;
        }
        returnValue = BlockAlt(gameBoard);
        if (returnValue != null) {
            return returnValue;
        }
        returnValue = MarkCenter(gameBoard);
        if (returnValue != null) {
            return returnValue;
        }
        returnValue = MarkOppositeCorner(gameBoard);
        if (returnValue != null) {
            return returnValue;
        }
        returnValue = markAnEmptyCorner(gameBoard);
        if (returnValue != null) {
            return returnValue;
        }
        returnValue = markLastRemainingCell(gameBoard);
        if (returnValue != null) {
            return returnValue;
        }

        return null;
    }





    /*
    private CellLocation blockOpponentFork(GameBoard gameBoard) {
        return YourGameBoard.BlockOpponentFOrk(gameBoard, playerCellState, opponentCellState);
    }
*/
    public static CellLocation Block(GameBoard gameBoard, CellState state) {
        CellLocation returnValue = null;

        if (returnValue == null) {
            returnValue = YourGameBoard.rowHandler(state, gameBoard, YourGameBoard.ROWS);
        }
        if (returnValue == null) {
            returnValue = YourGameBoard.rowHandler(state, gameBoard, YourGameBoard.COLUMNS);
        }
        if (returnValue == null) {
            returnValue = YourGameBoard.rowHandler(state, gameBoard, YourGameBoard.DIAGONALS);
        }

        return returnValue;
    }

    private CellLocation BlockAlt(GameBoard gameBoard) {
        return YourGameBoard.BlockOpponentForkAlt(gameBoard, playerCellState, opponentCellState);
    }

    private CellLocation MarkCenter(GameBoard gameBoard) {
        return YourGameBoard.MarkCenter(gameBoard, playerCellState);
    }

    private CellLocation MarkOppositeCorner(GameBoard gameBoard) {
        return YourGameBoard.MarkOppositeCorner(gameBoard, playerCellState, opponentCellState);
    }
    
    private CellLocation markAnEmptyCorner(GameBoard gameBoard) {
        return YourGameBoard.MarkARemainingCorner(gameBoard);
    }
    
    private CellLocation markLastRemainingCell(GameBoard gameBoard) {
        return YourGameBoard.MarkLastRemainingCell(gameBoard);
    }

    public CellState getPlayerCellState() {
        return playerCellState;
    }

    public CellState getOpponentCellState() {
        return opponentCellState;
    }

    
}
