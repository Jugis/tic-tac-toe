/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdesk.cases.tictactoe.yoursolution;

/**
 *
 * @author VPM
 */
class ImpossibleCaseException extends RuntimeException{

    public ImpossibleCaseException() {
        
    }
    public ImpossibleCaseException(String s) {
        System.out.println(s);
    }
}
