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
 * <p>This class allows the player to mark the center position</p>
 */
public class MarkCenter implements TicTacToeRule{
    
    
    @Override
    public CellLocation getNextStep(GameBoard gameBoard, Player player) {
        return GameBoardHandler.MarkCenter(gameBoard, player.getPlayerCellState());
    }
    
}
