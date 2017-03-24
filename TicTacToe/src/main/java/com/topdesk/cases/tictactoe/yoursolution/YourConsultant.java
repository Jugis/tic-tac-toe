package com.topdesk.cases.tictactoe.yoursolution;

import com.topdesk.cases.tictactoe.CellLocation;
import com.topdesk.cases.tictactoe.CellState;
import com.topdesk.cases.tictactoe.Consultant;
import com.topdesk.cases.tictactoe.GameBoard;

public class YourConsultant implements Consultant {
    
        CellLocation locationEnum;
        CellState stateEnum;
        Player player;

    @Override
    public CellLocation suggest(GameBoard gameBoard){
            //CHECK IF gameBoard is NULL
            if(gameBoard == null){throw new NullPointerException();}
            
            //CHECK IF gameBoard REPRESENTS AN IMPOSSIBLE CASE
            checkIfInvalid(gameBoard);
            
            //CHECK IF gameBoard REPRESENTS A CASE THAT IS ALREDY WON OR ENDED IN A DRAW   
            checkIfWon(gameBoard);
            
            //CHECK IF gameBoard REPRESENTS A CASE THAT ENDED IN A DRAW
            checkIfDraw(gameBoard);
            
            //CHECK WHOS TURN THE NEXT ONE IS
            whosTurn(gameBoard);
            
            //IF NONE OF THE CONDITIONS ABOVE ENDED THROWING AN EXCEPTION AND EVERYTHING IS FINE, THIS ONE SUGGESTS THE MOVE
            return player.NextMove(gameBoard);
	}

    
    /**
     * Checks if the given GameBoard object represents an impossible state of game, like the board has 3 X-es and 0 O-os!
     * 
     * @param gameBoard 
     */
    private void checkIfInvalid(GameBoard gameBoard){
        int num_of_x = 0;
        int num_of_y = 0;
        for (CellLocation loc_enum : locationEnum.values()) {
            
            if (gameBoard.getCellState(loc_enum).equals(stateEnum.OCCUPIED_BY_X)) {
                num_of_x++;
            }
            
            if (gameBoard.getCellState(loc_enum).equals(stateEnum.OCCUPIED_BY_O)) {
                num_of_y++;
            }
        }
        
        if(Math.abs(num_of_x - num_of_y) >= 2){
            throw new ImpossibleCaseException("Illegal state of a gameboard");
        }
    }

    /**
     * Checks if the game is alredy won by either of the players!
     * 
     * @param gameBoard 
     */
    private void checkIfWon(GameBoard gameBoard) {
        int num_of_x=0;
        int num_of_o=0;
        //CHECKING IF ONE OF THE ROWS CONTAIN ONLY X-S OR O-S
        for (int i = 0; i < GameBoardHandler.ROWS.size(); i++) {
            //processDimensions(YourGameBoard.ROWS.get(i), gameBoard); 
            num_of_x += GameBoardHandler.GetNumberOfTakenCells(GameBoardHandler.ROWS.get(i), gameBoard, CellState.OCCUPIED_BY_X);
            num_of_o += GameBoardHandler.GetNumberOfTakenCells(GameBoardHandler.ROWS.get(i), gameBoard, CellState.OCCUPIED_BY_O);
            if(num_of_o == 3 || num_of_x == 3){throw new IllegalStateException("The game was won by either of the players!");} else {num_of_x=0; num_of_o=0;}
        }
        //CHECKING IF EITHER OF THE COLUMNS CONTAIN ONLY X-S OR O-S
        for(int i = 0; i < GameBoardHandler.COLUMNS.size(); i++){
            //processDimensions(YourGameBoard.COLUMNS.get(i), gameBoard);
            num_of_x += GameBoardHandler.GetNumberOfTakenCells(GameBoardHandler.COLUMNS.get(i), gameBoard, CellState.OCCUPIED_BY_X);
            num_of_o += GameBoardHandler.GetNumberOfTakenCells(GameBoardHandler.COLUMNS.get(i), gameBoard, CellState.OCCUPIED_BY_O);
            if(num_of_o == 3 || num_of_x == 3){throw new IllegalStateException("The game was won by either of the players!");} else {num_of_x=0; num_of_o=0;}
        }
        //CHECKING IF EITHER OF THE DIAGONALS CONTAIN ONLY X-S OR O-S
        for(int i = 0; i < GameBoardHandler.DIAGONALS.size(); i++){
            //processDimensions(YourGameBoard.DIAGONALS.get(i), gameBoard);
            num_of_x += GameBoardHandler.GetNumberOfTakenCells(GameBoardHandler.DIAGONALS.get(i), gameBoard, CellState.OCCUPIED_BY_X);
            num_of_o += GameBoardHandler.GetNumberOfTakenCells(GameBoardHandler.DIAGONALS.get(i), gameBoard, CellState.OCCUPIED_BY_O);
            if(num_of_o == 3 || num_of_x == 3){throw new IllegalStateException("The game was won by either of the players!");} else {num_of_x=0; num_of_o=0;}
        }             
    }


    /**
     * Checks if the game has ended in a draw!
     * 
     * @param gameBoard 
     */
    private void checkIfDraw(GameBoard gameBoard) {
        int num_of_x = 0;
        int num_of_o = 0;
        int num_of_Cells = 9;
        
        for (int i = 0; i < GameBoardHandler.ROWS.size(); i++) {
            num_of_x += GameBoardHandler.GetNumberOfTakenCells(GameBoardHandler.ROWS.get(i), gameBoard, CellState.OCCUPIED_BY_X);           
            num_of_o += GameBoardHandler.GetNumberOfTakenCells(GameBoardHandler.ROWS.get(i), gameBoard, CellState.OCCUPIED_BY_O);
        }
        
        if((num_of_x + num_of_o) == num_of_Cells){
            throw new IllegalStateException("The game ended in a DRAW!");
        }
    }

    /**
     * Determine whose turn it is!
     * 
     * @param gameBoard 
     */
    private void whosTurn(GameBoard gameBoard) {
        int num_of_x = 0;  
        int num_of_o = 0;
        for (int i = 0; i < GameBoardHandler.ROWS.size(); i++) {
            num_of_x += GameBoardHandler.GetNumberOfTakenCells(GameBoardHandler.ROWS.get(i), gameBoard, CellState.OCCUPIED_BY_X);         
        }
        
        for (int i = 0; i < GameBoardHandler.ROWS.size(); i++) {
            num_of_o += GameBoardHandler.GetNumberOfTakenCells(GameBoardHandler.ROWS.get(i), gameBoard, CellState.OCCUPIED_BY_O);         
        }
        
        if (num_of_o < num_of_x) {
            player = new Player(Player.player_enum.O_PLAYER);
        }else{
            player = new Player(Player.player_enum.X_PLAYER);
        }
    }

    
    

}