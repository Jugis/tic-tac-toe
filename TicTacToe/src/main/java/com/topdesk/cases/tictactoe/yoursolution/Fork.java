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
public class Fork {

    CellLocation fork(GameBoard gameBoard, Player player) {
        return YourGameBoard.CellIterator(gameBoard, player.getPlayerCellState());
    }
    
}
