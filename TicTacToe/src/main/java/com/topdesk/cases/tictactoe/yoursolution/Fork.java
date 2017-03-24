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
 * <p> A class that allows the player to create a -so called- forking situation: Where the player has two possible ways of winning by one step, thus creating a situation where the player can definetly win!</p<
 */
public class Fork implements TicTacToeRule{

    /**
     * <p> This method iterates through every single {@code CellState.EMPTY} cell, to determine which cell is the one that creates a fork situation, or null if there is no such cell.
     * 
     * @param gameBoard The current state of the GameBoard.
     * @param state The player's cellstate, to know wether the player plays with X or O.
     * @return the desired cellLocation, or null if there is no cell that fulfills the conditions.
     */
    static CellLocation CellIterator(GameBoard gameBoard, CellState state) {
        CellLocation returnValue = null;
        for (List<CellLocation> list : GameBoardHandler.ROWS) {
            for (CellLocation cell : list) {
                if (gameBoard.getCellState(cell) == CellState.EMPTY) {
                    if (returnValue == null) {
                        returnValue = GameBoardHandler.IntersectWIthDimenison(gameBoard, cell, state);
                    }                   
                }
            }
        }
        return returnValue;
    }
    /**
     * @param gameBoard The current state of the GameBoard.
     * @param player The player, to know who's turn it is
     * @return returns the desired cell, or null if there is no such cell.
     */
    @Override
    public CellLocation getNextStep(GameBoard gameBoard, Player player) {
        return CellIterator(gameBoard, player.getPlayerCellState());
    }
    
}
