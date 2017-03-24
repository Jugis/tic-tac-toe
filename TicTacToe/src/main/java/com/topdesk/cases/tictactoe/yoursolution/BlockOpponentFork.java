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
 *<p> This class allows the player to block the opponent's fork optimally</p>
 */
public class BlockOpponentFork implements TicTacToeRule{

    /**
     * <p>This method iterates through every single empty cell and checks if the player stepped there, would that block the opponents fork OPTIMALLY.
     * By this, the method considers that if the player creates a two-in-a-row situation, by defending that the opponent does not get into a two-in-a-row situation themself.</p>
     * 
     * <p>Second part: In case the upper conditions did not meet, and the player cannot fork by creating a two-in-a-row situation, the method returns the position where the opponent's fork is blocked.</p>
     * 
     * @param gameBoard
     * @param playerCellState
     * @param opponentCellState
     * @return 
     */
    static CellLocation BlockOpponentForkAlt(GameBoard gameBoard, CellState playerCellState, CellState opponentCellState) {
        CellLocation returnValue = null;
 
        boolean cell_is_second_desired_in_dimension = false;
        for (List<CellLocation> row : GameBoardHandler.ROWS) {
            for (CellLocation loc : row) {
                if (gameBoard.getCellState(loc) == CellState.EMPTY) {
                    cell_is_second_desired_in_dimension = GameBoardHandler.checkIfCellIsInOneDesiredTwoEmpty(loc, gameBoard, playerCellState, opponentCellState);
                    if (cell_is_second_desired_in_dimension == true) {
                        returnValue = loc;
                    }
                }
            }
        }
        
        //Here begins the second part
        if (returnValue == null) {
            returnValue = GameBoardHandler.BlockOpponentForkByBlockingDefiniteWin(gameBoard, opponentCellState);
        }
        return returnValue;
    }

    @Override
    public CellLocation getNextStep(GameBoard gameBoard, Player player) {
        return BlockOpponentForkAlt(gameBoard, player.getPlayerCellState(), player.getOpponentCellState());
    }
    
    
}
