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
import java.util.List;

/**
 *<p> This is a class that is implements the GameBoard interface, as it is written, objects of this class are immutable. </p>
 *<p> This class is primarily used to help determine which cell is the best possible one when blocking the opponent's fork. Because whenever the player makes a step
 * the code needs to check wether the opponent got into a double fork position by blocking the player's last step. In order to be able to use the older methods creating anew class
 * that implements the GameBoard interface was a neccessary. I named it "TempGameBoard" to suggest it is used sorely for these purposes. </p>
 * 
 */
public final class TempGameBoard implements GameBoard{
    
    public static final List<CellLocation> LOCATIONLIST = Arrays.asList(CellLocation.TOP_LEFT, CellLocation.TOP_CENTRE, CellLocation.TOP_RIGHT, CellLocation.CENTRE_LEFT, CellLocation.CENTRE_CENTRE, CellLocation.CENTRE_RIGHT,
        CellLocation.BOTTOM_LEFT, CellLocation.BOTTOM_CENTRE, CellLocation.BOTTOM_RIGHT);
          
    List<CellState> stateList = new ArrayList<>();
    
    /**
     * Creates a GameBoard object.
     * 
    *@param states_param A list containing the cellstates of the new board, from top_left, top_center, top_right ->... to bottom_right order.
    *@param location The location that we wihich to siwtch to the desired cellstate.
    * @param state The desired cellstate.
    */
    
    TempGameBoard(List<CellState> states_param, CellLocation location, CellState state){
        stateList.clear();
        stateList = states_param;
        for (int i = 0; i < LOCATIONLIST.size(); i++) {
            if (LOCATIONLIST.get(i) == location) {
                stateList.set(i, state);
            }
        }
    }

    @Override
    public CellState getCellState(CellLocation cellLocation) {
        CellState returnValue = null;
        for (int i = 0; i < LOCATIONLIST.size(); i++) {
            if (LOCATIONLIST.get(i) == cellLocation) {
                returnValue = stateList.get(i);
            }
        }
        return returnValue;
    }

    public List<CellState> getStateList() {
        return new ArrayList<>(stateList);
    }
    
    
    
}
