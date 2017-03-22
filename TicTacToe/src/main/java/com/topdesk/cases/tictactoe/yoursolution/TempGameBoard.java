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
public final class TempGameBoard implements GameBoard{
    
    public static final List<CellLocation> LOCATIONLIST = Arrays.asList(CellLocation.TOP_LEFT, CellLocation.TOP_CENTRE, CellLocation.TOP_RIGHT, CellLocation.CENTRE_LEFT, CellLocation.CENTRE_CENTRE, CellLocation.CENTRE_RIGHT,
        CellLocation.BOTTOM_LEFT, CellLocation.BOTTOM_CENTRE, CellLocation.BOTTOM_RIGHT);
          
    List<CellState> stateList = new ArrayList<>();
    
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
