package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.util.Arrays;


public class Controller {

    @FXML
    Button restart;

    @FXML
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9;

    private Button a[];

    private char currentPlayer = 'X';
    private char player1 = 'X';
    private char player2 = 'O';

    public void initialize() {

        a = new Button[9];
        a[0] = button1;
        a[1] = button2;
        a[2] = button3;
        a[3] = button4;
        a[4] = button5;
        a[5] = button6;
        a[6] = button7;
        a[7] = button8;
        a[8] = button9;
    }

    @FXML
    public void keyPressed(ActionEvent event) throws Exception {

        if (!win()) {
            if (event.getSource() instanceof Button) {
                Button button = (Button) event.getSource();
                if (button.getText().equals("") && currentPlayer == player1) {
                    button.setText("X");
                    button.setId("X");
                    switchPlayer();

                } else if (button.getText().equals("") && currentPlayer == player2) {
                    button.setText("O");
                    button.setId("O");
                    switchPlayer();
                }
            }
            end();
        }
    }

    private void end() throws Exception{
        if (!win() && isRemis()) {
            remisDialog();
            restart();
        } else if(win()){
            switchPlayer();
            winnerDialog();
            restart();
        }
    }

    private void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    private boolean win() {

        return (a[0].getId().equals(a[1].getId()) && a[1].getId().equals(a[2].getId()) ||
                a[3].getId().equals(a[4].getId()) && a[4].getId().equals(a[5].getId()) ||
                a[6].getId().equals(a[7].getId()) && a[7].getId().equals(a[8].getId()) ||
                // wygrane poziome

                a[0].getId().equals(a[3].getId()) && a[3].getId().equals(a[6].getId()) ||
                a[1].getId().equals(a[4].getId()) && a[4].getId().equals(a[7].getId()) ||
                a[2].getId().equals(a[5].getId()) && a[5].getId().equals(a[8].getId()) ||
                // wygrane pionowe

                a[0].getId().equals(a[4].getId()) && a[4].getId().equals(a[8].getId()) ||
                a[2].getId().equals(a[4].getId()) && a[4].getId().equals(a[6].getId()));
                //wygrane na skos
    }

    private void winnerDialog() throws Exception{
        Dialog dialog = new Dialog();
        FXMLLoader.load(getClass().getResource("pop-up.fxml"));
        dialog.setTitle("WINNER");
        dialog.setHeaderText(currentPlayer + " Won!");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();
    }

    private void remisDialog() throws Exception{
        Dialog dialog = new Dialog();
        FXMLLoader.load(getClass().getResource("pop-up.fxml"));
        dialog.setTitle("REMIS");
        dialog.setHeaderText("REMIS!");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();
    }

    private boolean isRemis() {
        int licznik=0;

        for(int i=0; i<a.length;i++){
            if(a[i].getText().equals("")){
                licznik++;
            }
            if(licznik>0){
                return false;
            }
        }
        return true;
    }

    public void restart(){
        for(int i =0; i<a.length; i++){
            a[i].setText("");
            a[i].setId("button"+i);
            a[i].setDisable(false);
            currentPlayer = player1;
        }
    }
}
