package Views;

import Abstract.AbstractController;
import Constants.FrameworkConstants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static ClientServer.Client.board_position;

public class ReversiView {
    private static Border ROLLOVER_BORDER = BorderFactory.createLineBorder(Color.BLACK, 1);
    public static ImageIcon lightIcon = new ImageIcon("src/main/resources/Reversi/othelloLight.GIF");
    public static  ImageIcon darkIcon = new ImageIcon("src/main/resources/Reversi/othelloDark.GIF");
    public static  ImageIcon emptyIcon = new ImageIcon("src/main/resources/Reversi/othelloEmpty.GIF");


    public static void setupBoard(AbstractController updatedBoard){
        AbstractController board_reversi = updatedBoard;
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                int cell = board_reversi.getCell(i,j);
                board_position[i][j].setBorder(ROLLOVER_BORDER);
                if (cell == FrameworkConstants.PLAYER_WHITE){
                    board_position[i][j].setIcon(lightIcon);
                }
                else if (cell == FrameworkConstants.PLAYER_BLACK){
                    board_position[i][j].setIcon(darkIcon);
                }else{
                    board_position[i][j].setIcon(emptyIcon);
                }
            }
        }
    }
}
