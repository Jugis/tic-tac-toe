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
 * <p> This interface serves as a "container" for those classes that follow the Tic-Tac-Toe game, thus Newell and Simon's algorythim, sorely for encapsulation purposes.</p>
 */
public interface TicTacToeRule {
    
    /**
     * What is the next step, if we follow the rules.
     * 
     * @param gameBoard
     * @param player
     * @return 
     */
    public CellLocation getNextStep(GameBoard gameBoard, Player player);
}
