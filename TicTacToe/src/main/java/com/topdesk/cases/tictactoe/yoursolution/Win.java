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
public class Win {
    
    private Player player;


    CellLocation isWinning(GameBoard gameBoard, Player player) {
        CellLocation returnValue = null;
        if (returnValue == null) {
            returnValue = YourGameBoard.rowHandler(player.getPlayerCellState(), gameBoard, YourGameBoard.ROWS);
        }
        if (returnValue == null) {
            returnValue = YourGameBoard.rowHandler(player.getPlayerCellState(), gameBoard, YourGameBoard.COLUMNS);
        }
        if (returnValue == null) {
            returnValue = YourGameBoard.rowHandler(player.getPlayerCellState(), gameBoard, YourGameBoard.DIAGONALS);
        }
        return returnValue;
    }
    
}
