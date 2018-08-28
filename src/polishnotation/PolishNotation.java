/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polishnotation;

import javax.swing.JOptionPane;

/**
 *
 * @author Oksana_Miller
 */
public class PolishNotation {
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /* The program is designed to accept a "space-delimited" expression in reverse-Polish notation and
    print the operation/outcome of each step and the final answer.
    N.B. Invalid entries are identified as errors and what operation encountered the error.
    Test Data:
         A) 4 7 * 6 + 2 / 4
         B) 82 - 69 * 4 + 3
         C) 4 * 6 5 - 3 2 + 9
         D) 8 6 5 - 2 * 7
         E) 6 +
         F) -1 2 * 3 +
    Objectives:
     a) Multi-data string parsing.
     b) String tokens and delineators/delimiters.
     c) Operational state management.
     d) String sub-strings.

*/

     String strUserInput = JOptionPane.showInputDialog("Enter you reverse-Polish expression");
     
     int i;
     char c;
     int nNumericResult1 = 0;
     int nNumericResult2 = 0;
     int nResult = 0;
     int nZeroAscii, nAsciiValue;
    
     
    final int FIRST = 1;
    final int SECOND = 2;
    final int OP = 3; 
    int nCurrentState = FIRST;
    int minus = 1;
  
  
     for (i=0; i < strUserInput.length(); i++)
     {       
         c = strUserInput.charAt(i);
         boolean negative = (c == '-');
         
         switch(nCurrentState)
         {
             case FIRST: 
              
               if (Character.isDigit(c))
                 {
                   nAsciiValue = (int)c;
                   nZeroAscii = (int)'0';
                   nNumericResult1 *= 10;
                   nNumericResult1 += (nAsciiValue - nZeroAscii);
                   nNumericResult1 *= minus;
                 }  
               else if(Character.isSpaceChar(c))
                 {
                   nCurrentState = SECOND;
                   minus = 1;
                 }   
               else if (negative)
                 {
                  minus *= -1;
                 }
               else 
                 {
                    System.out.print("Wrong expression. Reverse Polish-notation looks like: operand, operand, operator.");
                 }
               break;
               
               
             case SECOND:  
                if (Character.isDigit(c))
                 {
                   nAsciiValue = (int)c;
                   nZeroAscii = (int)'0';
                   nNumericResult2 *= 10;
                   nNumericResult2 += (nAsciiValue - nZeroAscii);
                   nNumericResult2 *= minus;
                 }       
                else if(Character.isSpaceChar(c))
                 {
                   nCurrentState = OP;
                   minus = 1;
                 }
                else if (negative)
                 {
                   minus *= -1;
                 }
                else
                   {
                     System.out.print("Wrong expression. Reverse Polish-notation looks like: operand, operand, operator. ");
                   }
                break;
             case OP:
                  
                 switch (c)
                 {
                     case '-': 
                        nResult = nNumericResult1 - nNumericResult2;
                        System.out.println(nResult);                        
                     break;
                     case '+':
                        nResult = nNumericResult1 + nNumericResult2;
                        System.out.println(nResult); 
               
                     break;
                     case '/':
                        nResult = nNumericResult1 / nNumericResult2;
                        System.out.println(nResult);                       
                     break;
                     case '*':
                        nResult = nNumericResult1 * nNumericResult2;
                        System.out.println(nResult); 
                     break;
                     default: System.out.print("Wrong expression. Reverse Polish-notation looks like: operand, operand, operator. ");
                        
                 }
                 nNumericResult1 = nResult;
                 nCurrentState = SECOND;
                 i++;
                 nNumericResult2 = 0;         
         } 
     }
     System.out.println(" You expression: " + strUserInput + " The result is: " + nResult);
    }
    
}
