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
public class BoardCards {
    private int row=4;
    public Cell boardCards[][]= new Cell[row][4];
    
    //Inicializar cartas
    public void MakeCard_Board(){
    int i=0;    
    boardCards= new Cell[row][4];
    String cardsImages[]={"Cloth","Cornifer","Grimm","Hornet","Knight","Quirrel","SenioresMantis","Zote"};
    Random Rand_Cell = new Random();
    
    
    while(!FullBoard()){
        //seleccionar una imagen para la carta
        String imgRandSelec=cardsImages[i];
        i++;
        
        //Seleccionar la posicion  donde van a estar las cartas (Aleatorio)
        int firstRandRow= Rand_Cell.nextInt(row);
        int firstRandCol= Rand_Cell.nextInt(4);
        
        while(boardCards[firstRandRow][firstRandCol]!=null){
            firstRandRow= Rand_Cell.nextInt(row);
            firstRandCol= Rand_Cell.nextInt(4);
        }
        
        int secondRandRow= Rand_Cell.nextInt(row);
        int secondRandCol= Rand_Cell.nextInt(4);
        
        while((firstRandRow==secondRandRow && firstRandCol== secondRandCol) || boardCards[secondRandRow][secondRandCol]!=null){
            secondRandRow= Rand_Cell.nextInt(row);
            secondRandCol= Rand_Cell.nextInt(4);
        }
        //asignamos los valores generados
        boardCards[firstRandRow][firstRandCol]= new Cell(imgRandSelec,firstRandRow,firstRandCol);
        boardCards[secondRandRow][secondRandCol]= new Cell(imgRandSelec,secondRandRow,secondRandCol);
    }
    }

    private boolean FullBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < 4; j++) {
             if(boardCards[i][j]==null){
             return false;
             }   
            }
        }
        return true;
    }
    public void setRow(int row){
    this.row=row;
    }
}
