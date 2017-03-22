/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdesk.cases.tictactoe.yoursolution;

import com.topdesk.cases.tictactoe.CellLocation;
import com.topdesk.cases.tictactoe.GameBoard;

/**
 *
 * @author VPM
 */
public class BlockOpponent {

    CellLocation blockOpponentFromWinning(GameBoard gameBoard, Player player) {
        CellLocation returnValue = null;
        if (returnValue == null) {
            returnValue = YourGameBoard.rowHandler(player.getOpponentCellState(), gameBoard, YourGameBoard.ROWS);
        }
        if (returnValue == null) {
            returnValue = YourGameBoard.rowHandler(player.getOpponentCellState(), gameBoard, YourGameBoard.COLUMNS);
        }
        if (returnValue == null) {
            returnValue = YourGameBoard.rowHandler(player.getOpponentCellState(), gameBoard, YourGameBoard.DIAGONALS);
        }
        return returnValue;
    }
    
}
