/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdesk.cases.tictactoe.yoursolution;

import com.topdesk.cases.tictactoe.CellLocation;
import com.topdesk.cases.tictactoe.CellState;
import com.topdesk.cases.tictactoe.GameBoard;
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
        
        returnValue = new Win().getNextStep(gameBoard, this);
        if (returnValue != null) {
            return returnValue;
        }        
        returnValue = new BlockOpponent().getNextStep(gameBoard, this);
        if (returnValue != null) {
            return returnValue;
        }
        returnValue = new Fork().getNextStep(gameBoard, this);
        if (returnValue != null) {
            return returnValue;
        }
        returnValue = new BlockOpponentFork().getNextStep(gameBoard, this);
        if (returnValue != null) {
            return returnValue;
        }
        returnValue = new MarkCenter().getNextStep(gameBoard, this);
        if (returnValue != null) {
            return returnValue;
        }
        returnValue = new MarkOppositeCorner().getNextStep(gameBoard, this);
        if (returnValue != null) {
            return returnValue;
        }
        returnValue = new MarkAnEmptyCorner().getNextStep(gameBoard, this);
        if (returnValue != null) {
            return returnValue;
        }
        returnValue = new MarkLastRemainingCell().getNextStep(gameBoard, this);
        if (returnValue != null) {
            return returnValue;
        }

        return null;
    }

    public CellState getPlayerCellState() {
        return playerCellState;
    }

    public CellState getOpponentCellState() {
        return opponentCellState;
    } 
}
