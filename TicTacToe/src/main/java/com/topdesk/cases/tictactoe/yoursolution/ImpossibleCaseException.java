/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdesk.cases.tictactoe.yoursolution;

/**
 *
 * <p>The behaviour of the code was not defined at certain cases, so I just created this class.</p>
 */
class ImpossibleCaseException extends RuntimeException{

    public ImpossibleCaseException() {
        
    }
    public ImpossibleCaseException(String s) {
        System.out.println(s);
    }
}
