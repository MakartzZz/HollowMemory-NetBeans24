/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hollowmemory;

import java.util.Random;

/**
 *
 * @author david
 */
public class IA {
    private String choice1, choice2;
    private final Random Rand_Cell = new Random();
    private String visualizedName = null;
 
    
    public void Easy(BoardCards boardcards, int row){
        
    Boolean flag1=true, flag2=true;

    int row1= Rand_Cell.nextInt(row);
    int col1= Rand_Cell.nextInt(4);
    
    while(flag1==true){
    if(boardcards.boardCards[row1][col1].cardGuessed==true){
    row1= Rand_Cell.nextInt(row);
    col1= Rand_Cell.nextInt(4);
    }else{
    choice1= row1+","+col1;    
    flag1 =false;
    }
    }
    
    int row2= Rand_Cell.nextInt(row);
    int col2= Rand_Cell.nextInt(4);
    
    while(flag2==true){
        if((row1==row2 && col1==col2) || boardcards.boardCards[row2][col2].cardGuessed==true ){
            row2= Rand_Cell.nextInt(row);
            col2= Rand_Cell.nextInt(4);
        }else{
        choice2= row2+","+col2;
        flag2=false;
        }
    }
    flag1 =true;
    flag2 =true;
    }
    
    public void Normal(BoardCards boardcards, int row){
        
    int choose=Rand_Cell.nextInt(4);
    switch(choose){
        case 0 ->{
            Easy(boardcards,row);
        break;
        }
        case 1 ->{
           SmartLogic(boardcards,row);
        break;
        }
        case 2 ->{
            Easy(boardcards,row);
        break;
        }
        case 3->{
            Easy(boardcards,row);
        break;
        }
    }
    }
    
    public void Smart(BoardCards boardcards, int row){
    int choose=Rand_Cell.nextInt(4);
    switch(choose){
        case 0 ->{
            Easy(boardcards,row);
        break;
        }
        case 1 ->{
           SmartLogic(boardcards,row);
        break;
        }
        case 2 ->{
            SmartLogic(boardcards,row);
        break;
        }
        case 3->{
            Easy(boardcards,row);
        break;
        }
    }
    
    }
    
    //Getters
    public String getChoice1(){
    return this.choice1;
    }
     public String getChoice2(){
    return this.choice2;
    }
     
    private void SmartLogic(BoardCards boardcards,int row){
        Integer row1,col1;
        Boolean flag1=true;
    
        row1= Rand_Cell.nextInt(row);
        col1= Rand_Cell.nextInt(4);
            
        while(flag1==true){
            if(boardcards.boardCards[row1][col1].cardGuessed==true){
                row1= Rand_Cell.nextInt(row);
                col1= Rand_Cell.nextInt(4);
            }else{
                choice1= row1+","+col1;
                visualizedName= boardcards.boardCards[row1][col1].CardImage;
                flag1 =false;
            }
        }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < 4; j++) {
                    String auxposition=i+","+j;
                    if(boardcards.boardCards[i][j].CardImage.equals(visualizedName) && !auxposition.equals(choice1) ){
                        choice2=i+","+j;
                        flag1=true;
                    }
                }
            }
    }
}
