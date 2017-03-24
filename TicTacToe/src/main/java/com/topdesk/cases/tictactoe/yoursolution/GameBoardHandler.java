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
public final class GameBoardHandler {

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
    
    
    /**
     * Get the number of cells that have the given state.
     * 
     * @param list The list the method iterates in.
     * @param gameBoard The state of the GameBoard.
     * @param cellState The cellstate we are looking for.
     * @return the number cells.
     */
    public static int GetNumberOfTakenCells(List<CellLocation> list, GameBoard gameBoard, CellState cellState) {
        int value = 0;
        for (CellLocation item : list) {
            if (gameBoard.getCellState(item) == cellState) {
                value++;
            }
        }
        return value;
    }
    
    /**
     * Returns the empty cell, if the other two possess the given cellstate.
     * 
     * @param list The list
     * @param gameBoard The GameBoard
     * @param cellState The cellstate
     * @return the empty cell
     */
    public static CellLocation GetEmptyCellIfOtherTwoAreTaken(List<CellLocation> list, GameBoard gameBoard, CellState cellState) {
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

    /**
     * <p>Returns a List containing the states of each location of the given gameboard by a TOP_LEFT, TOP_CENTER, TOP_RIGHT, CENTER_LEFT ... -> BOTTOM_RIGHT order.</p>
     * 
     * @param gameBoard The gameboard.
     * @return the list of states.
     */
    private static List<CellState> GetGameBoardStatesList(GameBoard gameBoard) {
        List<CellState> statesList = new ArrayList<>();
        for (int i = 0; i < TempGameBoard.LOCATIONLIST.size(); i++) {
            statesList.add(gameBoard.getCellState(TempGameBoard.LOCATIONLIST.get(i)));
        }
        return statesList;
    }

    /**This method is rather complicated, however I decided not to put them into separate methods, and treat the whole process as a whole.
     * It returns the cellocation that blocks the opponent's fork. The whole point of this method is that it goes through all the "dimensions" checks if there is one,
     * that has only one of the player's marks and the other two are empty. If there is such a situation the method simulates that the player stepped into that CellLocation
     * After that the method simulates that the opponent blocked that step preventing the player from certain winning in the following step.
     * If by blocking that step the opponent did not get into a double fork position (having two ways of winning by one step), the method returns that location.
     * If none of the iterations resulted in a proper location it returns null.
     * 
     * @param loc the current loc
     * @param gameBoard the state of the gameboard
     * @param playerCellState the player's state (the player plays with X or O)
     * @param opponentCellState the opponent's state.
     * @return the cellLocation.
     */
    
    static boolean checkIfCellIsInOneDesiredTwoEmpty(CellLocation loc, GameBoard gameBoard, CellState playerCellState, CellState opponentCellState) {
        //I realize that the method has quite some "lightbulbs" on the left, however if I just chained the method's it would be really unreadable.
        TempGameBoard tmp_gm = null;
        List<CellState> states_list = GetGameBoardStatesList(gameBoard);
        CellLocation empty_cell_that_opponent_takes = null;
        boolean opponent_has_double_fork = false;
        boolean returnValue = false;
        //Iterating through the rows, and if the row has the location, we check if the row has two empties and the cell.
        boolean row_has_loc_and_2_empties = false;
        for (List<CellLocation> row : ROWS) {
            if (row.contains(loc)) {
                row_has_loc_and_2_empties = DimensionContainsTwoDesiredOneOther(gameBoard, CellState.EMPTY, playerCellState, row);
                if (row_has_loc_and_2_empties == true) {
                    //The "simulation" where the player stepped into the current location. I simulated this with the TempGameBoard's object
                    tmp_gm = new TempGameBoard(states_list, loc, playerCellState);
                    //The cell that the opponent takes
                    empty_cell_that_opponent_takes = GetEmptyCellInDimension(row, tmp_gm);
                    //Creates a list that contains the new gameBoard states' cellstates with the normal TOP_LEFT TOP_CENTER... order.
                    states_list = GetGameBoardStatesList(tmp_gm);
                    //Here we simulate that the opponent blocked the player's two in a row dimension
                    tmp_gm = new TempGameBoard(states_list, empty_cell_that_opponent_takes, opponentCellState);
                    //Here we check if the opponent has the double fork situation.
                    opponent_has_double_fork = CheckOpponentIsInDoubleFork(tmp_gm, opponentCellState);
                    if (opponent_has_double_fork == false) {
                        returnValue = true;
                    }
                }
                //return the proper value, and set the states_list back to default
                row_has_loc_and_2_empties = false;
                states_list = GetGameBoardStatesList(gameBoard);
            }
        }

        for (List<CellLocation> row : COLUMNS) {
            if (row.contains(loc)) {
                row_has_loc_and_2_empties = DimensionContainsTwoDesiredOneOther(gameBoard, CellState.EMPTY, playerCellState, row);
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
                states_list = GetGameBoardStatesList(gameBoard);
            }
        }

        for (List<CellLocation> row : DIAGONALS) {
            if (row.contains(loc)) {
                row_has_loc_and_2_empties = DimensionContainsTwoDesiredOneOther(gameBoard, CellState.EMPTY, playerCellState, row);
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
    }

    /**
     * Returns true of the given list contains two of the first cellstate parameter and one of the second cellstate parameter.
     * 
     * @param gameBoard the gameboard.
     * @param firstCellState the first state.
     * @param secondCellState the second state.
     * @param dimension the list the method iterates in.
     * @return true if the list meets the conditions.
     */
    private static boolean DimensionContainsTwoDesiredOneOther(GameBoard gameBoard, CellState firstCellState, CellState secondCellState, List<CellLocation> dimension) {
        int num_of_first = 0;
        int num_of_second = 0;

        for (CellLocation cell : dimension) {
            if (gameBoard.getCellState(cell) == firstCellState) {
                num_of_first++;
            } else if (gameBoard.getCellState(cell) == secondCellState) {
                num_of_second++;
            }
        }

        if (num_of_first == 2 && num_of_second == 1) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Returns the empty cell in a dimension. This method only works optimally if it is certain that there is only ONE empty cell, as it returns the LAST empty cell!!!
     * 
     * @param row the list we are iterating in.
     * @param tmp_gm the gameboard.
     * @return the empty cell.
     */
    private static CellLocation GetEmptyCellInDimension(List<CellLocation> row, GameBoard tmp_gm) {
        CellLocation returnValue = null;
        for (CellLocation cell : row) {
            if (tmp_gm.getCellState(cell) == CellState.EMPTY) {
                returnValue = cell;
            }
        }
        return returnValue;
    }

    /**
     * returns true if on the given board, the player with the given cellstate has a double fork position.
     * 
     * @param tmp_gm the gameboard's state.
     * @param opponentCellState the player's state.
     * @return true if meets the conditions.
     */
    private static boolean CheckOpponentIsInDoubleFork(GameBoard tmp_gm, CellState opponentCellState) {
        List<List<CellLocation>> dimensions_containing_2_opponent_1_empty = new ArrayList<>();
        dimensions_containing_2_opponent_1_empty = GetListsOfDimensions(tmp_gm, opponentCellState, CellState.EMPTY);

        if (dimensions_containing_2_opponent_1_empty.size() >= 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a list of dimensions that contain two of a certain type of marks and one of another mark.
     * 
     * @param gameBoard The state of the gameboard.
     * @param firstCellState The first cellState that the method expects to have two of.
     * @param secondCellState The first cellState that the method expects to have one of.
     * @return 
     */
    static List<List<CellLocation>> GetListsOfDimensions(GameBoard gameBoard, CellState firstCellState, CellState secondCellState) {
        List<List<CellLocation>> returnValue = new ArrayList<>();

        for (List<CellLocation> row : ROWS) {
            if (DimensionContainsTwoDesiredOneOther(gameBoard, firstCellState, secondCellState, row) == true) {
                returnValue.add(row);
            }
        }
        for (List<CellLocation> column : COLUMNS) {
            if (DimensionContainsTwoDesiredOneOther(gameBoard, firstCellState, secondCellState, column) == true) {
                returnValue.add(column);
            }
        }
        for (List<CellLocation> diagonal : DIAGONALS) {
            if (DimensionContainsTwoDesiredOneOther(gameBoard, firstCellState, secondCellState, diagonal) == true) {
                returnValue.add(diagonal);
            }
        }

        return returnValue;
    }

    /**
     * Returns a list of dimensions that contain two of a certain type of marks and one of another mark that contain a certain CellLocation.
     * 
     * @param gameBoard The state of the gameboard.
     * @param firstCellState The first cellState that the method expects to have two of.
     * @param secondCellState The first cellState that the method expects to have one of.
     * @param cell The cell that has to be contained by the dimensions.
     * @return 
     */
    static List<List<CellLocation>> GetListsOfDimensions(GameBoard gameBoard, CellState firstCellState, CellState secondCellState, CellLocation cell) {
        List<List<CellLocation>> returnValue = new ArrayList<>();

        for (List<CellLocation> row : ROWS) {
            if (row.contains(cell)) {
                if (DimensionContainsTwoDesiredOneOther(gameBoard, firstCellState, secondCellState, row) == true) {
                    returnValue.add(row);
                }
            }

        }

        for (List<CellLocation> column : COLUMNS) {
            if (column.contains(cell)) {
                if (DimensionContainsTwoDesiredOneOther(gameBoard, firstCellState, secondCellState, column) == true) {
                    returnValue.add(column);
                }
            }
        }

        for (List<CellLocation> diagonal : DIAGONALS) {
            if (diagonal.contains(cell)) {
                if (DimensionContainsTwoDesiredOneOther(gameBoard, firstCellState, secondCellState, diagonal) == true) {
                    returnValue.add(diagonal);
                }
            }
        }

        return returnValue;
    }

    
    /**
     * Returns the CellLocation that blocks the fork of the player with the mark in opponentCellState
     * 
     * @param gameBoard the gameBoard's situation.
     * @param opponentCellState the opponent's cellstate.
     * @return the cellLocation.
     */
    static CellLocation BlockOpponentForkByBlockingDefiniteWin(GameBoard gameBoard, CellState opponentCellState) {
        List<List<CellLocation>> dimensionsContainingOneOpponentTwoEmpty = new ArrayList<>();
        CellLocation returnValue = null;

        dimensionsContainingOneOpponentTwoEmpty = GetListsOfDimensions(gameBoard, CellState.EMPTY, opponentCellState);
        //In case the list containing intersecting dimensions has a size of two, then return the common cell of these dimensions
        if (dimensionsContainingOneOpponentTwoEmpty.size() >= 2) {
            for (int i = 0; i < dimensionsContainingOneOpponentTwoEmpty.size(); i++) {
                for (int j = 0; j < dimensionsContainingOneOpponentTwoEmpty.size(); j++) {
                    if (dimensionsContainingOneOpponentTwoEmpty.get(i) != dimensionsContainingOneOpponentTwoEmpty.get(j)) {
                        returnValue = GetCommonEmptyCellLocation(gameBoard, dimensionsContainingOneOpponentTwoEmpty.get(i), dimensionsContainingOneOpponentTwoEmpty.get(j), CellState.EMPTY);
                    }
                }
            }
        }
        return returnValue;
    }

    /**
     * Checks if the given cell is the mutual location of the two intersecting dimensions, if it is it returns the cell, otherwise null.
     * 
     * @param gameBoard the state of the gameBoard
     * @param cell the cellLocation.
     * @param state the state of the player
     * @return the location
     */
    static CellLocation IntersectWIthDimenison(GameBoard gameBoard, CellLocation cell, CellState state) {
        CellLocation returnValue = null;
        List<List<CellLocation>> intersectingDimensions = new ArrayList<>();
        intersectingDimensions = GetListsOfDimensions(gameBoard, CellState.EMPTY, state, cell);
        
        //In case the list intersectingDimensions contain two lists, it is imperative that the current cell creates a fork-situation for the player
        if (intersectingDimensions.size() >= 2) {
            returnValue = cell;
        }
        return returnValue;

    }

    /**
     * Returns the common empty cell location of two lists.
     * 
     * @param gameBoard
     * @param dimension1
     * @param dimension2
     * @param cellState
     * @return 
     */
    private static CellLocation GetCommonEmptyCellLocation(GameBoard gameBoard, List<CellLocation> dimension1, List<CellLocation> dimension2, CellState cellState) {
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

    /**
     * Returns the centre of the board
     * 
     * @param gameBoard
     * @param playerCellState
     * @return 
     */
    static CellLocation MarkCenter(GameBoard gameBoard, CellState playerCellState) {
        CellLocation returnValue = null;
        if (gameBoard.getCellState(CellLocation.CENTRE_CENTRE) == CellState.EMPTY) {
            returnValue = CellLocation.CENTRE_CENTRE;
        }
        return returnValue;
    }

    /**
     * Marks the corner of the diagonal if the opponent has the other one.
     * 
     * @param gameBoard
     * @param playerCellState
     * @param opponentCellState
     * @return 
     */
    static CellLocation MarkOppositeCorner(GameBoard gameBoard, CellState playerCellState, CellState opponentCellState) {
        CellLocation returnValue = null;

        for (List<CellLocation> diagonal : DIAGONALS) {
            if (returnValue == null) {
                returnValue = GetEmptyCornerIfOpponentHasOtherCorner(gameBoard, diagonal, opponentCellState);
            }

        }
        return returnValue;
    }
    
/**
 * This methid checks basically if one of the players -in this case the opponent- possesses one of the corners where the diagonal's other two cells are empty
 * 
 * @param gameBoard
 * @param diagonal
 * @param opponentCellState
 * @return 
 */
    private static CellLocation GetEmptyCornerIfOpponentHasOtherCorner(GameBoard gameBoard, List<CellLocation> diagonal, CellState opponentCellState) {
        //This methid checks basically if one of the players -in this case the opponent- possesses one of the corners where the diagonal's other two cells are empty
        CellLocation returnValue = null;
        if (DimensionContainsTwoDesiredOneOther(gameBoard, CellState.EMPTY, opponentCellState, diagonal)) {
            
            if (diagonal.contains(CellLocation.TOP_LEFT)) {
                //check the first diagonal
                if (gameBoard.getCellState(CellLocation.TOP_LEFT) == opponentCellState) {
                    returnValue = CellLocation.BOTTOM_RIGHT;
                } else if (gameBoard.getCellState(CellLocation.BOTTOM_RIGHT) == opponentCellState) {
                    returnValue = CellLocation.TOP_LEFT;
                }
            
            } else if (diagonal.contains(CellLocation.TOP_RIGHT)) {
                //check the second diagonal
                if (gameBoard.getCellState(CellLocation.TOP_RIGHT) == opponentCellState) {
                    returnValue = CellLocation.BOTTOM_LEFT;
                } else if (gameBoard.getCellState(CellLocation.BOTTOM_LEFT) == opponentCellState) {
                    returnValue = CellLocation.TOP_RIGHT;
                }
            }
        }

        return returnValue;
    }

    /**
     * If there is a remaining corner, this method returns one.
     * 
     * @param gameBoard
     * @return 
     */
    static CellLocation MarkARemainingCorner(GameBoard gameBoard) {
        CellLocation returnValue = null;
        for (CellLocation corner : CORNERS) {
            if (gameBoard.getCellState(corner) == CellState.EMPTY) {
                returnValue = corner;
            }
        }
        return returnValue;
    }

    /**
     * Marks the last remaining empty cell on the board
     * 
     * @param gameBoard
     * @return 
     */
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

    /**
     * This method iterates through a list and checks if the player is in a winning situation.
     * 
     * @param state
     * @param gameBoard
     * @param LIST
     * @return 
     */
    static CellLocation GetLastEmptyCellIfOtherTwoAreTaken(CellState state, GameBoard gameBoard, List<List<CellLocation>> LIST) {
        CellLocation temp_returnValue = null;
        CellLocation returnValue = null;
        if (returnValue == null) {
            for (List<CellLocation> list : LIST) {
                temp_returnValue = GameBoardHandler.GetEmptyCellIfOtherTwoAreTaken(list, gameBoard, state);
                if (temp_returnValue != null) {
                    returnValue = temp_returnValue;
                }
            }
        }
        return returnValue;
    }

}
