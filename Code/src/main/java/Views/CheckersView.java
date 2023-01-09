package Views;

import Abstract.AbstractController;
import Constants.CheckersConstants;
import Constants.FrameworkConstants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static ClientServer.Client.board_position;

public class CheckersView {
    private static Border ROLLOVER_BORDER = BorderFactory.createLineBorder(Color.BLACK, 1);
    public static ImageIcon checkEvenBlank = new ImageIcon("src/main/resources/Checkers/evenEmpty.gif");
    public static ImageIcon checkOddBlank = new ImageIcon("src/main/resources/Checkers/oddEmpty.gif");
    public static ImageIcon checkLightMan = new ImageIcon("src/main/resources/Checkers/lightMan.gif");
    public static ImageIcon checkDarkMan = new ImageIcon("src/main/resources/Checkers/darkMan.gif");
    public static ImageIcon checkLightKing = new ImageIcon("src/main/resources/Checkers/lightKing.gif");
    public static ImageIcon checkDarkKing = new ImageIcon("src/main/resources/Checkers/darkKing.gif");
    public static ImageIcon checkLightManSel = new ImageIcon("src/main/resources/Checkers/selected/lightMan.gif");
    public static ImageIcon checkDarkManSel = new ImageIcon("src/main/resources/Checkers/selected/darkMan.gif");
    public static ImageIcon checkLightKingSel = new ImageIcon("src/main/resources/Checkers/selected/lightKing.gif");
    public static ImageIcon checkDarkKingSel = new ImageIcon("src/main/resources/Checkers/selected/darkKing.gif");


    public static void setupBoard(AbstractController updatedBoard) {

        int matrix[][] = updatedBoard.getTempPosition();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                int cell = matrix[i][j];
                board_position[i][j].setBorder(ROLLOVER_BORDER);
                if(cell == FrameworkConstants.BLANK_POSITION){
                    if((i+j)%2 == 0){
                        board_position[i][j].setIcon(checkEvenBlank);
                    }else{
                        board_position[i][j].setIcon(checkOddBlank);
                    }
                }else if(cell == CheckersConstants.WHITE_MAN){
                    board_position[i][j].setIcon(checkLightMan);
                }else if(cell == CheckersConstants.BLACK_MAN){
                    board_position[i][j].setIcon(checkDarkMan);
                }else if(cell == CheckersConstants.WHITE_KING){
                    board_position[i][j].setIcon(checkLightKing);
                }else if(cell == CheckersConstants.BLACK_KING){
                    board_position[i][j].setIcon(checkDarkKing);
                }
            }
        }


        if(updatedBoard.getPieceSelected() != FrameworkConstants.BLANK_POSITION){
            Dimension selection = updatedBoard.getSelection();
            int i = selection.width;
            int j = selection.height;
            int cell = matrix[i][j];
            if(cell == CheckersConstants.WHITE_MAN){
                board_position[i][j].setIcon(checkLightManSel);
            }else if(cell == CheckersConstants.BLACK_MAN){
                board_position[i][j].setIcon(checkDarkManSel);
            }else if(cell == CheckersConstants.WHITE_KING){
                board_position[i][j].setIcon(checkLightKingSel);
            }else if(cell == CheckersConstants.BLACK_KING){
                board_position[i][j].setIcon(checkDarkKingSel);
            }
        }
    }

}
