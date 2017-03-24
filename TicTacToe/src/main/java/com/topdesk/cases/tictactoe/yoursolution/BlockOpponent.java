/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdesk.cases.tictactoe.yoursolution;

import com.topdesk.cases.tictactoe.CellLocation;
import com.topdesk.cases.tictactoe.GameBoard;

/**
 * <p>If the opponent can win the game with one step, this class allows us to block that win</p<
 */
public class BlockOpponent implements TicTacToeRule{
    
    /**
     * 
     * @param gameBoard The current state of the GameBoard.
     * @param player The Player, to know who's turn it is
     * @return returns the cell's location {@code CellLocation} that needs to be filled to block, or {@code null} if the opponent is not in a winning situation.
     */
    @Override
    public CellLocation getNextStep(GameBoard gameBoard, Player player) {
        CellLocation returnValue = null;
        if (returnValue == null) {
            returnValue = GameBoardHandler.GetLastEmptyCellIfOtherTwoAreTaken(player.getOpponentCellState(), gameBoard, GameBoardHandler.ROWS);
        }
        if (returnValue == null) {
            returnValue = GameBoardHandler.GetLastEmptyCellIfOtherTwoAreTaken(player.getOpponentCellState(), gameBoard, GameBoardHandler.COLUMNS);
        }
        if (returnValue == null) {
            returnValue = GameBoardHandler.GetLastEmptyCellIfOtherTwoAreTaken(player.getOpponentCellState(), gameBoard, GameBoardHandler.DIAGONALS);
        }
        return returnValue;
    }
    
}
