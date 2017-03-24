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
 * <p>If there are no positions left but one, this class allows the palyer to mark it.</p>
 */
public class MarkLastRemainingCell implements TicTacToeRule{


    @Override
    public CellLocation getNextStep(GameBoard gameBoard, Player player) {
        return GameBoardHandler.MarkLastRemainingCell(gameBoard);
    }
    
}
