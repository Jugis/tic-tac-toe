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
 * <p>If the opponent has a corner in an otherwise empty diagonal, this class allows the player to mark the opposite corner.</p>
 */
public class MarkOppositeCorner implements TicTacToeRule{

    @Override
    public CellLocation getNextStep(GameBoard gameBoard, Player player) {
        return GameBoardHandler.MarkOppositeCorner(gameBoard, player.getPlayerCellState(), player.getOpponentCellState());
    }
    
}
