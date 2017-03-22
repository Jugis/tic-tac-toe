/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdesk.cases.tictactoe.yoursolution;

import com.topdesk.cases.tictactoe.CellLocation;
import com.topdesk.cases.tictactoe.CellState;
import com.topdesk.cases.tictactoe.GameBoard;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author VPM
 */
public final class YourGameBoard {

    public static final List<List<CellLocation>> ROWS = Arrays.asList(
            Collections.unmodifiableList(Arrays.asList(CellLocation.TOP_LEFT, CellLocation.TOP_CENTRE, CellLocation.TOP_RIGHT)),
            Collections.unmodifiableList(Arrays.asList(CellLocation.CENTRE_LEFT, CellLocation.CENTRE_CENTRE, CellLocation.CENTRE_RIGHT)),
            Collections.unmodifiableList(Arrays.asList(CellLocation.BOTTOM_LEFT, CellLocation.BOTTOM_CENTRE, CellLocation.BOTTOM_RIGHT))
    );
    public static final List<List<CellLocation>> COLUMNS = Arrays.asList(
            Collections.unmodifiableList(Arrays.asList(CellLocation.TOP_LEFT, CellLocation.CENTRE_LEFT, CellLocation.BOTTOM_LEFT)),
            Collections.unmodifiableList(Arrays.asList(CellLocation.TOP_CENTRE, CellLocation.CENTRE_CENTRE, CellLocation.BOTTOM_CENTRE)),
            Collections.unmodifiableList(Arrays.asList(CellLocation.TOP_RIGHT, CellLocation.CENTRE_RIGHT, CellLocation.BOTTOM_RIGHT))
    );
    public static final List<CellLocation> POS_DIALOG = Collections.unmodifiableList(Arrays.asList(CellLocation.TOP_LEFT, CellLocation.CENTRE_CENTRE, CellLocation.BOTTOM_RIGHT));
    public static final List<CellLocation> NEG_DIALOG = Collections.unmodifiableList(Arrays.asList(CellLocation.TOP_RIGHT, CellLocation.CENTRE_CENTRE, CellLocation.BOTTOM_LEFT));

    public static final List<List<CellLocation>> DIAGONALS = Arrays.asList(
            Collections.unmodifiableList(Arrays.asList(CellLocation.TOP_LEFT, CellLocation.CENTRE_CENTRE, CellLocation.BOTTOM_RIGHT)),
            Collections.unmodifiableList(Arrays.asList(CellLocation.TOP_RIGHT, CellLocation.CENTRE_CENTRE, CellLocation.BOTTOM_LEFT))
    );
    public static final List<CellLocation> CORNERS = Arrays.asList(CellLocation.TOP_LEFT, CellLocation.TOP_RIGHT, CellLocation.BOTTOM_LEFT, CellLocation.BOTTOM_RIGHT);

    public static int DimensionProcesser(List<CellLocation> list, GameBoard gameBoard, CellState cellState) {
        int value = 0;
        for (CellLocation item : list) {
            if (gameBoard.getCellState(item) == cellState) {
                value++;
            }
        }
        return value;
    }

    ;
    
    
    public static ArrayList<Integer> GetPositionsOfCellState(List<CellLocation> list, GameBoard gameBoard, CellState cellState) {
        ArrayList<Integer> positionList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (gameBoard.getCellState(list.get(i)) == cellState) {
                positionList.add(i);
            }
        }
        return positionList;
    }

    public static CellLocation TwoInARowProcesser(List<CellLocation> list, GameBoard gameBoard, CellState cellState) {
        CellLocation emptyLocation = null;
        int number_of_desired_cellstates = 0;

        for (CellLocation item : list) {
            if (gameBoard.getCellState(item) == cellState) {
                number_of_desired_cellstates++;
            }
            if (gameBoard.getCellState(item) == CellState.EMPTY) {
                emptyLocation = item;
            }
        }
        if (number_of_desired_cellstates == 2 && emptyLocation != null) {
            return emptyLocation;
        } else {
            return null;
        }
    }

    static CellLocation CellIterator(GameBoard gameBoard, CellState state) {
        CellLocation returnValue = null;
        for (List<CellLocation> list : ROWS) {
            for (CellLocation cell : list) {
                if (gameBoard.getCellState(cell) == CellState.EMPTY) {
                    returnValue = IntersectWIthDimenison(gameBoard, cell, state);
                }
            }
        }
        return returnValue;
    }

    private static CellLocation IntersectWIthDimenison(GameBoard gameBoard, CellLocation cell, CellState state) {
        int num_of_player_mark = 0;
        int num_of_empy_cells = 0;
        CellLocation returnValue = null;
        List<List<CellLocation>> intersectingDimensions = new ArrayList<>();

        for (List<CellLocation> row : ROWS) {
            if (row.contains(cell)) {
                num_of_player_mark += DimensionProcesser(row, gameBoard, state);
                num_of_empy_cells += DimensionProcesser(row, gameBoard, CellState.EMPTY);

                if (num_of_player_mark == 1 && num_of_empy_cells == 2) {
                    intersectingDimensions.add(row);
                }
            }

        }

        num_of_player_mark = 0;
        num_of_empy_cells = 0;

        for (List<CellLocation> column : COLUMNS) {
            if (column.contains(cell)) {
                num_of_player_mark += DimensionProcesser(column, gameBoard, state);
                num_of_empy_cells += DimensionProcesser(column, gameBoard, CellState.EMPTY);

                if (num_of_player_mark == 1 && num_of_empy_cells == 2) {
                    intersectingDimensions.add(column);
                }
            }

        }

        num_of_player_mark = 0;
        num_of_empy_cells = 0;

        for (List<CellLocation> diagonal : DIAGONALS) {
            if (diagonal.contains(cell)) {
                num_of_player_mark += DimensionProcesser(diagonal, gameBoard, state);
                num_of_empy_cells += DimensionProcesser(diagonal, gameBoard, CellState.EMPTY);

                if (num_of_player_mark == 1 && num_of_empy_cells == 2) {
                    intersectingDimensions.add(diagonal);
                }
            }

        }

        if (intersectingDimensions.size() == 2) {
            System.out.println("NIGGER");
            for (int i = 0; i < 3; i++) {
                if (intersectingDimensions.get(0).contains(intersectingDimensions.get(1).get(i))) {
                    returnValue = intersectingDimensions.get(1).get(i);
                }

            }
        }
        return returnValue;

    }
   
    private static List<CellState> GetGameBoardStatesList(GameBoard gameBoard) {
        List<CellState> statesList = new ArrayList<>();
        for (int i = 0; i < TempGameBoard.LOCATIONLIST.size(); i++) {
            statesList.add(gameBoard.getCellState(TempGameBoard.LOCATIONLIST.get(i)));
        }
        return statesList;
    }

   

    static CellLocation BlockOpponentForkAlt(GameBoard gameBoard, CellState playerCellState, CellState opponentCellState) {
        CellLocation returnValue = null;
        //EZ A METÓDUS VÉGIGITERÁL MINDEGYIK ÜRES KOCKÁN
        boolean cell_is_second_desired_in_dimension = false;
        for (List<CellLocation> row : ROWS) {
            for (CellLocation loc : row) {
                if (gameBoard.getCellState(loc) == CellState.EMPTY) {
                    cell_is_second_desired_in_dimension = checkIfCellIsInOneDesiredTwoEmpty(loc, gameBoard, playerCellState, opponentCellState);
                    if (cell_is_second_desired_in_dimension == true) {
                        returnValue = loc;
                    }
                }
            }
        }
        if (returnValue == null) {
            returnValue = blockOpponentForkByBlockingDefiniteWin(gameBoard, playerCellState, opponentCellState);
        }
        
        return returnValue; 
    }

    private static boolean checkIfCellIsInOneDesiredTwoEmpty(CellLocation loc, GameBoard gameBoard, CellState playerCellState, CellState opponentCellState) {
        TempGameBoard tmp_gm = null;
        List<CellState> states_list = GetGameBoardStatesList(gameBoard);
        CellLocation empty_cell_that_opponent_takes = null;
        CellLocation temp = null;
        boolean opponent_has_double_fork = false;
        boolean returnValue = false;
        
        boolean row_has_loc_and_2_empties = false;
        for (List<CellLocation> row : ROWS) {
            if (row.contains(loc)) {
                row_has_loc_and_2_empties = RowContainsOneDesiredTwoEmpty(playerCellState, gameBoard, row);
                if (row_has_loc_and_2_empties == true) {
                    tmp_gm = new TempGameBoard(states_list, loc, playerCellState);
                    
                    empty_cell_that_opponent_takes = GetEmptyCellInDimension(row, tmp_gm);
                    
                    states_list = GetGameBoardStatesList(tmp_gm);
                    
                    tmp_gm = new TempGameBoard(states_list, empty_cell_that_opponent_takes, opponentCellState);
                    
                    opponent_has_double_fork = CheckOpponentIsInDoubleFork(tmp_gm, opponentCellState);
                    if (opponent_has_double_fork == false) {
                        returnValue = true;
                    }
                }
                
                row_has_loc_and_2_empties = false;
            }
        }
        
        for (List<CellLocation> row : COLUMNS) {
            if (row.contains(loc)) {
                row_has_loc_and_2_empties = RowContainsOneDesiredTwoEmpty(playerCellState, gameBoard, row);
                if (row_has_loc_and_2_empties == true) {
                    tmp_gm = new TempGameBoard(states_list, loc, playerCellState);
                    
                    empty_cell_that_opponent_takes = GetEmptyCellInDimension(row, tmp_gm);
                    
                    states_list = GetGameBoardStatesList(tmp_gm);
                    
                    tmp_gm = new TempGameBoard(states_list, empty_cell_that_opponent_takes, opponentCellState);
                    
                    opponent_has_double_fork = CheckOpponentIsInDoubleFork(tmp_gm, opponentCellState);
                    if (opponent_has_double_fork == false) {
                        returnValue = true;
                    }
                }
                
                row_has_loc_and_2_empties = false;
            }
        }
        
        for (List<CellLocation> row : DIAGONALS) {
            if (row.contains(loc)) {
                row_has_loc_and_2_empties = RowContainsOneDesiredTwoEmpty(playerCellState, gameBoard, row);
                if (row_has_loc_and_2_empties == true) {
                    tmp_gm = new TempGameBoard(states_list, loc, playerCellState);
                    
                    empty_cell_that_opponent_takes = GetEmptyCellInDimension(row, tmp_gm);
                    
                    states_list = GetGameBoardStatesList(tmp_gm);
                    
                    tmp_gm = new TempGameBoard(states_list, empty_cell_that_opponent_takes, opponentCellState);
                    
                    opponent_has_double_fork = CheckOpponentIsInDoubleFork(tmp_gm, opponentCellState);
                    if (opponent_has_double_fork == false) {
                        returnValue = true;
                    }
                }
                
                row_has_loc_and_2_empties = false;
            }
        }
        return returnValue;
        //majd ugyanezt a többi dimenziüra
    }

    private static boolean RowContainsOneDesiredTwoEmpty(CellState playerCellState, GameBoard gameBoard, List<CellLocation> row) {
        boolean returnValue = false;
        int num_of_desired = 0;
        int num_of_empty = 0;
        for (CellLocation loc : row) {
            if (gameBoard.getCellState(loc) == playerCellState) {
                num_of_desired++;
            }else if(gameBoard.getCellState(loc) == CellState.EMPTY){
                num_of_empty++;
            }
        }
        
        if (num_of_desired == 1 && num_of_empty == 2) {
            return true;
        }else{
            return false;
        }
    }

    private static CellLocation GetEmptyCellInDimension(List<CellLocation> row, TempGameBoard tmp_gm) {
        CellLocation returnValue = null;
        for (CellLocation cell : row) {
            if (tmp_gm.getCellState(cell) == CellState.EMPTY) {
                returnValue = cell;
            }
        }
        return returnValue;
    }

    private static boolean CheckOpponentIsInDoubleFork(TempGameBoard tmp_gm, CellState opponentCellState) {
        List<List<CellLocation>> dimensions_containing_2_opponent_1_empty = new ArrayList<>();
        boolean list_contains_2_opponent_1_empty = false;
        
        
        for (List<CellLocation> row : ROWS) {
            list_contains_2_opponent_1_empty = DimensionsContainsTwoDesiredOneEmpty(tmp_gm, opponentCellState, row);
            if (list_contains_2_opponent_1_empty == true) {
                dimensions_containing_2_opponent_1_empty.add(row);
                list_contains_2_opponent_1_empty = false;
            }
        }
        
        for (List<CellLocation> column : COLUMNS) {
            list_contains_2_opponent_1_empty = DimensionsContainsTwoDesiredOneEmpty(tmp_gm, opponentCellState, column);
            if (list_contains_2_opponent_1_empty == true) {
                dimensions_containing_2_opponent_1_empty.add(column);
                list_contains_2_opponent_1_empty = false;
            }
        }
        
        for (List<CellLocation> diagonal : DIAGONALS) {
            list_contains_2_opponent_1_empty = DimensionsContainsTwoDesiredOneEmpty(tmp_gm, opponentCellState, diagonal);
            if (list_contains_2_opponent_1_empty == true) {
                dimensions_containing_2_opponent_1_empty.add(diagonal);
                list_contains_2_opponent_1_empty = false;
            }
        }
        if (dimensions_containing_2_opponent_1_empty.size() > 1) {
            return true;
        }else{
            return false;
        }
        //Fel van töltve a lista azokkal a dimenizókkal, amikbe 2 teli 1 üres van.
    }

    private static boolean DimensionsContainsTwoDesiredOneEmpty(TempGameBoard tmp_gm, CellState opponentCellState, List<CellLocation> row) {
        //boolean returnValue = false;
        int num_of_desired = 0;
        int num_of_empty = 0;
        
        for (CellLocation cell : row) {
            if (tmp_gm.getCellState(cell) == opponentCellState) {
                num_of_desired++;
            }else if(tmp_gm.getCellState(cell) == CellState.EMPTY){
                num_of_empty++;
            }
        }
        
        if (num_of_desired == 2 && num_of_empty == 1) {
            return true;
        }else{
            return false;
        }
    }

    private static CellLocation blockOpponentForkByBlockingDefiniteWin(GameBoard gameBoard, CellState playerCellState, CellState opponentCellState) {
        List<List<CellLocation>> dimensionsContainingOneOpponentTwoEmpty = new ArrayList<>();
        CellLocation returnValue = null;
        
        for (List<CellLocation> row : ROWS) {
            if (RowContainsOneDesiredTwoEmpty(opponentCellState, gameBoard, row) == true) {
                dimensionsContainingOneOpponentTwoEmpty.add(row);
            }
        }
        for (List<CellLocation> column : COLUMNS) {
            if (RowContainsOneDesiredTwoEmpty(opponentCellState, gameBoard, column) == true) {
                dimensionsContainingOneOpponentTwoEmpty.add(column);
            }
        }
        for (List<CellLocation> diagonal : DIAGONALS) {
            if (RowContainsOneDesiredTwoEmpty(opponentCellState, gameBoard, diagonal) == true) {
                dimensionsContainingOneOpponentTwoEmpty.add(diagonal);
            }
        }
        //A listában benne vannak azok a sorok amelyekben az ellenfélnek 2 ürese és 2 kívántja van
        if (dimensionsContainingOneOpponentTwoEmpty.size() >= 2) {
            for (int i = 0; i < dimensionsContainingOneOpponentTwoEmpty.size(); i++) {
                for (int j = 0; j < dimensionsContainingOneOpponentTwoEmpty.size(); j++) {
                    if (dimensionsContainingOneOpponentTwoEmpty.get(i) != dimensionsContainingOneOpponentTwoEmpty.get(j)) {
                        returnValue = getCommonEmptyCellLocation(gameBoard, dimensionsContainingOneOpponentTwoEmpty.get(i), dimensionsContainingOneOpponentTwoEmpty.get(j), CellState.EMPTY);
                    }
                }
            }
        }
        return returnValue;
    }

    private static CellLocation getCommonEmptyCellLocation(GameBoard gameBoard, List<CellLocation> dimension1, List<CellLocation> dimension2, CellState cellState) {
        CellLocation returnValue = null;
        for (CellLocation cellLocation : dimension1) {
            if (dimension2.contains(cellLocation)) {
                if (gameBoard.getCellState(cellLocation) == cellState) {
                    returnValue = cellLocation;
                }
            }
        }
        return returnValue;
    }

    static CellLocation MarkCenter(GameBoard gameBoard, CellState playerCellState) {
        CellLocation returnValue = null;
        if (gameBoard.getCellState(CellLocation.CENTRE_CENTRE) == CellState.EMPTY) {
            returnValue = CellLocation.CENTRE_CENTRE;
        }
        return returnValue;
    }

    static CellLocation MarkOppositeCorner(GameBoard gameBoard, CellState playerCellState, CellState opponentCellState) {
        CellLocation returnValue = null;
        
        for (List<CellLocation> diagonal : DIAGONALS) {
            if (returnValue == null) {
                returnValue = getEmptyCornerIfOpponentHasOtherCorner(gameBoard, diagonal, opponentCellState);
            }
            
        }
        return returnValue;
    }

    private static CellLocation getEmptyCornerIfOpponentHasOtherCorner(GameBoard gameBoard, List<CellLocation> diagonal, CellState opponentCellState) {
        CellLocation returnValue = null;
        if (RowContainsOneDesiredTwoEmpty(opponentCellState, gameBoard, diagonal) == true) {
            if (diagonal.contains(CellLocation.TOP_LEFT)) {
                if (gameBoard.getCellState(CellLocation.TOP_LEFT) == opponentCellState) {
                    returnValue = CellLocation.BOTTOM_RIGHT;
                }else if(gameBoard.getCellState(CellLocation.BOTTOM_RIGHT) == opponentCellState){
                    returnValue = CellLocation.TOP_LEFT;
                }
            }else if(diagonal.contains(CellLocation.TOP_RIGHT)){
                if (gameBoard.getCellState(CellLocation.TOP_RIGHT) == opponentCellState) {
                    returnValue = CellLocation.BOTTOM_LEFT;
                }else if(gameBoard.getCellState(CellLocation.BOTTOM_LEFT) == opponentCellState){
                    returnValue = CellLocation.TOP_RIGHT;
                }
            }
        }
        
        return returnValue;
    }

    static CellLocation MarkARemainingCorner(GameBoard gameBoard) {
        CellLocation returnValue = null;
        for (CellLocation corner : CORNERS) {
            if (gameBoard.getCellState(corner) == CellState.EMPTY) {
                returnValue = corner;
            }
        }
        return returnValue;
    }

    static CellLocation MarkLastRemainingCell(GameBoard gameBoard) {
        CellLocation ret = null;
        for (List<CellLocation> row : ROWS) {
            for (CellLocation loc : row) {
                if (gameBoard.getCellState(loc) == CellState.EMPTY) {
                    ret = loc;
                }
            }
        }
        return ret;
    }

    static CellLocation rowHandler(CellState state, GameBoard gameBoard, List<List<CellLocation>> LIST) {
        CellLocation temp_returnValue = null;
        CellLocation returnValue = null;
        if (returnValue == null) {
            for (List<CellLocation> list : LIST) {
                temp_returnValue = YourGameBoard.TwoInARowProcesser(list, gameBoard, state);
                if (temp_returnValue != null) {
                    returnValue = temp_returnValue;
                }
            }
        }
        return returnValue;
    }

    


}
