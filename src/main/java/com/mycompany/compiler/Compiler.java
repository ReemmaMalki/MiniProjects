package com.mycompany.compiler;
import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class Compiler {

    static char lookahead;
    static Scanner read;
    
    
    // Write here all the reserved words of C/Java into an array
    private static String[] keywords = { "abstract", "boolean", "byte", "case",
            "catch", "char", "class", "continue", "default", "do", "double",
            "else", "extends", "final", "finally", "float", "for", "if",
            "implements", "import", "instanceof", "int", "interface", "long",
            "native", "new", "package", "private", "protected", "public",
            "return", "short", "static", "super", "switch", "synchronized",
            "this", "throw", "throws", "transient", "try", "void", "volatile",
            "while", "false", "true", "null" };
     
    public static void main(String[] args)throws FileNotFoundException {
        
        
        read = new Scanner(System.in);
        
        // while there is more elements to read
        while(read.hasNext())
                 {
            String line=read.nextLine();
            char[] myChar = line.toCharArray();
            // call tokenizer to generate tokens
            tokenizer(myChar);               
                 }
        read.close();

//=================================================================================
//=================================================================================
 }//end main

    
public static void tokenizer(char []chars)
{   
    int c=0;//to record the position of lookahead
    String token="";
    int caseNum=0;
    
    while(c<chars.length)
    {
     //Read one character from the chars array and store it in lookahead 
     lookahead=chars[c];

     switch(caseNum)
     {
         
         case 0:
            //case for white spaces
            if(lookahead=='\r'||lookahead=='\t'||lookahead==' ' || lookahead=='\n')
                    {
                caseNum=0;
                //skip the white space by reading another character
                c=c+1;                               
                    }
            //go to case of identifiers
            else if(lookahead=='_' || Character.isLetter(lookahead))	
                // underscore and Letter for Identifiers
                    {
                token+=lookahead;
                c=c+1;
                caseNum=1;
					
                    }
            //go to case of digits
            else if (Character.isDigit(lookahead))
                    {
                 
                caseNum=3; 
                    }
            //go to case of ,
            else if(lookahead==',')
                    {
                token+=lookahead;
                caseNum=5; 
                    }
            //go to case of ;
            else if(lookahead==';')
                    {
                token+=lookahead;
                 caseNum=6;
                    }
            //go to case of :
            else if(lookahead==':')
                    {
                token+=lookahead;
                 caseNum=7;
					
                    }
                //go to case of ?
             else if(lookahead=='?')
                    {
                token+=lookahead;
                 caseNum=8;
                       }
            //go to case of {	
            else if(lookahead=='{')
                    {
                token+=lookahead;
                 caseNum=9;
                     }
            //go caseNum of }
            else if(lookahead=='}')
                    {
                token+=lookahead;
                 caseNum=10;
                     }
            else if(lookahead=='+')
                    {
                token+=lookahead;
                c=c+1;
                caseNum=11;
                     }
            else if(lookahead=='-')
                    {
                token+=lookahead;
                c=c+1;
                caseNum=12;
                     }
            else if(lookahead=='=')
                    {
                token+=lookahead;
                c=c+1;
                caseNum=13;
                     }
            //ADD the other cases
            //********************
/* Write the similar code for following operators:
    Operators (+ ,+=, ++) (-,-=,--),(=) 

*/
          //*********************
           else
		{
        	// At the END
                //you should implement error method to display a message that is UNRECOGNIZED TOKEN
        	// and then terminate the Program
                //token+=lookahead;
                caseNum=100;
		}
            break;
            //end case 0
	    //*****************************************************
            //*****************************************************
	    // Case 1 for completing ID  
          case 1:
            if(lookahead=='_'||Character.isLetter(lookahead)||Character.isDigit(lookahead))
                 {	 
                token+=lookahead;//to start new token
                c=c+1;
                caseNum=1; 
                    }
            else
                    {
                caseNum=2;
                     }
                break;
                //end case 1
                         
	//=====================================================
	// case of IDENTIFIER TOKEN
	  case 2:
            //check keywords
              List keys = Arrays.asList(keywords);
//              for(int i=0;i<keywords.length;i++){
//                  if(token.equalsIgnoreCase(keywords[i])){
                if (keys.contains(token)){
                      System.out.println(token+"\t\t"+"IS A KEYWORD");
                      token="";//to start new token
                      caseNum=0;
                      break;}
                  else{
              
            //printing IDENTIFIER TOKEN
            System.out.println(token+"\t\t"+"ID");
            token="";//to start new token
            caseNum=0;
            break;  }
              
        //=====================================================
        // case of INTEGER TOKEN
             case 3:
               if(Character.isDigit(lookahead)){
                    token+=lookahead;//to start new token
                    c=c+1;
                    caseNum=3; 
	        	 }
                    else
	        	 {
                             c=c+1;
                    caseNum=4;
	        	 }
                    break;

        //============================================	          	  
             case 4:
                System.out.println(token+"\t\t"+"INTEGER");
                token="";//to start new token
                 caseNum=0;
                break;        
        //============================================	        
             case 5:
                System.out.println(token+"\t\t"+"COMMA");
                caseNum=0;
                token="";//to start new token
                c=c+1;
                break;
        //=====================================================
              case 6:
                 System.out.println(token+"\t\t"+"SEMI_COLON");
                 caseNum=0;
                 token="";//to start new token
                 c=c+1;
                 break;
        //=====================================================
               case 7:
            	 System.out.println(token+"\t\t"+"COLON");
                 caseNum=0;
                 token="";//to start new token
                 c=c+1;
                 break;
        //=====================================================
              case 8:
            	 System.out.println(token+"\t\t"+"QUESTION_MARK");
                 caseNum=0;
                 token="";//to start new token
                 c=c+1;
                 break;
        //=====================================================
               case 9:
            	 System.out.println(token+"\t\t"+"LEFT_CURLY");
                 caseNum=0;
                 token="";//to start new token
                 c=c+1;
                 break;
        //=====================================================
              case 10:
                 System.out.println(token+"\t\t"+"RIGHT_CURLY");
                 caseNum=0;
                 token="";//to start new token
                 c=c+1;
                 break; 
        //=====================================================
	  case 11:
            if (lookahead == '+') {
                        token += lookahead;
                        System.out.println(token + "\t\t" + "INCREMENT_BY_ONE");
                        caseNum = 0;
                        token = "";// to start new token
                        c=c+1;
                    } else if (lookahead == '=') {
                        token += lookahead;
                        System.out.println(token + "\t\t" + "INCREMENT_BY_VALUE");
                        caseNum = 0;
                        token = "";// to start new token
                        c=c+1;
                    } else {
                        System.out.println(token + "\t\t" + "ADDITION");
                        caseNum = 0;
                        token = "";// to start new token
                        c=c+1;
                    }
                    break;
        //=====================================================
	  case 12:
            if (lookahead == '-') {
                        token += lookahead;
                        System.out.println(token + "\t\t" + "DECCREMENT_BY_ONE");
                        caseNum = 0;
                        token = "";// to start new token
                        c=c+1;
                    } else if (lookahead == '=') {
                        token += lookahead;
                        System.out.println(token + "\t\t" + "DECCREMENT_BY_VALUE");
                        caseNum = 0;
                        token = "";// to start new token
                        c=c+1;
                    } else {
                        System.out.println(token + "\t\t" + "MINUS");
                        caseNum = 0;
                        token = "";// to start new token
                        c=c+1;
                    }
                    break;
        //=====================================================
	  case 13:
            System.out.println(token+"\t\t"+"EQUALS");
            token="";//to start new token
            caseNum=0;
            c=c+1;
            break;         
       //=======================================================  
               case 100: 
                   System.out.println(token+"\t\t"+"ERROR! UNRECOGNIZED TOKEN");
                   System.exit(0);
                   //break;
                  
          }//end of main switch
      
      }//end of while loop   
       
    }//end of tokenizer
    
}//end of class

   
