/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdesk.cases.tictactoe.yoursolution;

import com.topdesk.cases.tictactoe.CellLocation;
import com.topdesk.cases.tictactoe.GameBoard;
/**
 * <p>A class designed to win the game if the board's current state allows it.</p<
 */
public class Win implements TicTacToeRule{
/**
 * @param gameBoard an object that implements the GameBoard interface, that represents the game's current state
 * @param player the player that allows to know who's turn it is
 * @return returns the winning cell's location, or {@code null} if the game cannot be won.
 */
    @Override
    public CellLocation getNextStep(GameBoard gameBoard, Player player) {
        CellLocation returnValue = null;
        if (returnValue == null) {
            returnValue = GameBoardHandler.GetLastEmptyCellIfOtherTwoAreTaken(player.getPlayerCellState(), gameBoard, GameBoardHandler.ROWS);
        }
        if (returnValue == null) {
            returnValue = GameBoardHandler.GetLastEmptyCellIfOtherTwoAreTaken(player.getPlayerCellState(), gameBoard, GameBoardHandler.COLUMNS);
        }
        if (returnValue == null) {
            returnValue = GameBoardHandler.GetLastEmptyCellIfOtherTwoAreTaken(player.getPlayerCellState(), gameBoard, GameBoardHandler.DIAGONALS);
        }
        return returnValue;
    }
    
}
