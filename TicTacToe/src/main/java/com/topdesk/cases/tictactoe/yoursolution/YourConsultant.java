package com.topdesk.cases.tictactoe.yoursolution;

import com.topdesk.cases.tictactoe.CellLocation;
import com.topdesk.cases.tictactoe.CellState;
import com.topdesk.cases.tictactoe.Consultant;
import com.topdesk.cases.tictactoe.GameBoard;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private void checkIfWon(GameBoard gameBoard) {
        int num_of_x=0;
        int num_of_o=0;
        //CHECKING IF ONE OF THE ROWS CONTAIN ONLY X-S OR O-S
        for (int i = 0; i < YourGameBoard.ROWS.size(); i++) {
            //processDimensions(YourGameBoard.ROWS.get(i), gameBoard); 
            num_of_x += YourGameBoard.DimensionProcesser(YourGameBoard.ROWS.get(i), gameBoard, CellState.OCCUPIED_BY_X);
            num_of_o += YourGameBoard.DimensionProcesser(YourGameBoard.ROWS.get(i), gameBoard, CellState.OCCUPIED_BY_O);
            if(num_of_o == 3 || num_of_x == 3){throw new IllegalStateException("The game was won by either of the players!");} else {num_of_x=0; num_of_o=0;}
        }
        //CHECKING IF EITHER OF THE COLUMNS CONTAIN ONLY X-S OR O-S
        for(int i = 0; i < YourGameBoard.COLUMNS.size(); i++){
            //processDimensions(YourGameBoard.COLUMNS.get(i), gameBoard);
            num_of_x += YourGameBoard.DimensionProcesser(YourGameBoard.COLUMNS.get(i), gameBoard, CellState.OCCUPIED_BY_X);
            num_of_o += YourGameBoard.DimensionProcesser(YourGameBoard.COLUMNS.get(i), gameBoard, CellState.OCCUPIED_BY_O);
            if(num_of_o == 3 || num_of_x == 3){throw new IllegalStateException("The game was won by either of the players!");} else {num_of_x=0; num_of_o=0;}
        }
        //CHECKING IF EITHER OF THE DIAGONALS CONTAIN ONLY X-S OR O-S
        for(int i = 0; i < YourGameBoard.DIAGONALS.size(); i++){
            //processDimensions(YourGameBoard.DIAGONALS.get(i), gameBoard);
            num_of_x += YourGameBoard.DimensionProcesser(YourGameBoard.DIAGONALS.get(i), gameBoard, CellState.OCCUPIED_BY_X);
            num_of_o += YourGameBoard.DimensionProcesser(YourGameBoard.DIAGONALS.get(i), gameBoard, CellState.OCCUPIED_BY_O);
            if(num_of_o == 3 || num_of_x == 3){throw new IllegalStateException("The game was won by either of the players!");} else {num_of_x=0; num_of_o=0;}
        }             
    }

    /**
    private void processDimensions(List<CellLocation> list, GameBoard gameBoard) {
        int dimension = 3;
        int x = 0;
        int o = 0;
        
        for (CellLocation item : list) {
            if(gameBoard.getCellState(item) == CellState.OCCUPIED_BY_X){
                x++;
            }else if(gameBoard.getCellState(item) == CellState.OCCUPIED_BY_O){
                o++;
            }
        }
        if(x == dimension ||o == dimension){
            throw new IllegalStateException("The game was won by either of the players!");
        }
    }
*/
    private void checkIfDraw(GameBoard gameBoard) {
        int num_of_x = 0;
        int num_of_o = 0;
        int num_of_Cells = 9;
        
        for (int i = 0; i < YourGameBoard.ROWS.size(); i++) {
            num_of_x += YourGameBoard.DimensionProcesser(YourGameBoard.ROWS.get(i), gameBoard, CellState.OCCUPIED_BY_X);           
            num_of_o += YourGameBoard.DimensionProcesser(YourGameBoard.ROWS.get(i), gameBoard, CellState.OCCUPIED_BY_O);
        }
        
        if((num_of_x + num_of_o) == num_of_Cells){
            throw new IllegalStateException("The game ended in a DRAW!");
        }
    }
    /**
    private int processRows(List<CellLocation> list, GameBoard gameBoard){
        int num_of_x_and_o = 0;
        
        for (CellLocation item : list) {
            if(gameBoard.getCellState(item) == CellState.OCCUPIED_BY_X){
                num_of_x_and_o++;
            }else if(gameBoard.getCellState(item) == CellState.OCCUPIED_BY_O){
                num_of_x_and_o++;
            }
        }
        
        return num_of_x_and_o;
    }
  */

    private void whosTurn(GameBoard gameBoard) {
        int num_of_x = 0;  
        int num_of_o = 0;
        for (int i = 0; i < YourGameBoard.ROWS.size(); i++) {
            num_of_x += YourGameBoard.DimensionProcesser(YourGameBoard.ROWS.get(i), gameBoard, CellState.OCCUPIED_BY_X);         
        }
        
        for (int i = 0; i < YourGameBoard.ROWS.size(); i++) {
            num_of_o += YourGameBoard.DimensionProcesser(YourGameBoard.ROWS.get(i), gameBoard, CellState.OCCUPIED_BY_O);         
        }
        
        if (num_of_o < num_of_x) {
            player = new Player(Player.player_enum.O_PLAYER);
        }else{
            player = new Player(Player.player_enum.X_PLAYER);
        }
    }

    
    

}