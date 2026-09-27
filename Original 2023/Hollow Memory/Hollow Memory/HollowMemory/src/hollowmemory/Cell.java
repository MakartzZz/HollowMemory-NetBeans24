/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hollowmemory;

/**
 *
 * @author david
 */
public class Cell {
    public String CardImage;
    public int row;
    public int column;
    public boolean cardGuessed= false;

    public Cell(String CardImage, int row, int column) {
        this.CardImage = CardImage;
        this.row = row;
        this.column = column;
        this.cardGuessed=false;
    }
}
