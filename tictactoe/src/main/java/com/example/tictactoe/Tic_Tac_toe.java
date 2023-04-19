package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Tic_Tac_toe extends Application {

    private Button buttons[][] = new Button[3][3];
    private Label payerXScoreLabel , player0ScoreLabel;

    private int playerXscore =0,player0Score =0;

    private boolean playerXturn = true;

    private BorderPane createContent(){
        BorderPane root = new BorderPane();
        //Title
        Label titleLabel = new Label("Tic Tac Toe");
        titleLabel.setStyle("-fx-font-size: 35 pt; -fx-font-weight: bold");
        titleLabel.setPadding(new Insets(20));
        root.setTop(titleLabel);
       BorderPane.setAlignment(titleLabel, Pos.CENTER);



        //Game Board
        GridPane gridPane = new GridPane();  //gridpane will use to put the buttons on the grid of the window
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size: 24pt; -fx-font-wight: bold;");
                //adding event handling to the button;
                button.setOnAction(event->buttonClicked(button));
                buttons[i][j] = button;
                gridPane.add(button,j,i);
            }

        }

        //placing gridPane in center
        root.setCenter(gridPane);
        BorderPane.setAlignment(gridPane, Pos.CENTER);

        //Score

        HBox scoreboard = new HBox(20);
        scoreboard.setAlignment(Pos.CENTER);

        payerXScoreLabel = new Label("player X: 0");
        payerXScoreLabel.setStyle("-fx-font-size:16 pt; -fx-font-weight: bold");
        player0ScoreLabel = new Label("player 0 : 0");
        player0ScoreLabel.setStyle("-fx-font-size:16 pt; -fx-font-weight: bold");
        scoreboard.getChildren().addAll(payerXScoreLabel,player0ScoreLabel);
        root.setBottom((scoreboard));




        return root;
    }

    //This function will check whose turn it is and then as per the turn the symbol will toggle;
    private void buttonClicked(Button button)
    {
      if(button.getText().equals(""))  {
            if (playerXturn) {
                button.setText("X");
            } else {
                button.setText("0");
            }

            //turn if toggle
            playerXturn = !playerXturn;

          checkWinner();
        }
        return;
    }



    //checking winning

    private void checkWinner()
    {
        //row checking
        for (int row = 0; row < 3; row++) {
            if(buttons[row][0].getText().equals(buttons[row][1].getText()) &&
            buttons[row][1].getText().equals(buttons[row][2].getText()) &&
                    !buttons[row][0].getText().isEmpty() )    //for to avoid the empty space will match , will check that row text should not be empty
            {//we will have a winner
                String winner = buttons[row][0].getText();
                shoWinnarDialog(winner);
                updateScore(winner);
                resetBoard();
                return;

            }
        }


        //col checking
        for (int col = 0; col < 3; col++) {
            if(buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                    buttons[1][col].getText().equals(buttons[2][col].getText()) &&
                    !buttons[0][col].getText().isEmpty() )    //for to avoid the empty space will match , will check that row text should not be empty
            {//we will have a winner
                String winner = buttons[0][col].getText();
                shoWinnarDialog(winner);
                updateScore(winner);
                resetBoard();
                return;

            }
        }



        //diagonal

        //diagonal1
        if(buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().isEmpty() )    //for to avoid the empty space will match , will check that row text should not be empty
        {//we will have a winner
            String winner = buttons[0][0].getText();
            shoWinnarDialog(winner);
            updateScore(winner);
            resetBoard();
            return;

        }

        //diagonal2
        if(buttons[2][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[0][2].getText()) &&
                !buttons[0][2].getText().isEmpty() )    //for to avoid the empty space will match , will check that row text should not be empty
        {//we will have a winner
            String winner = buttons[0][2].getText();
            shoWinnarDialog(winner);
            updateScore(winner);
            resetBoard();
            return;

        }




        //check game is tie or not
        boolean tie = true;
        for(Button row[]: buttons)
        {
            for (Button button : row)
            {
           if(button.getText().isEmpty())
           {
               tie=false;
               break;
           }
            }
        }

        if(tie)
        {
            shoTieDialog();
            resetBoard();
        }
    }





    //show the alert for the winner
    private  void shoWinnarDialog(String winner)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("winner");
        alert.setContentText("The player with "+ winner +" symbol is winner");
        alert.setHeaderText("");
        alert.showAndWait();
    }


    //show the alert for the tie
    private  void shoTieDialog()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game status");
        alert.setContentText("Game is Tie");
        alert.setHeaderText("");
        alert.showAndWait();
    }


  private void updateScore(String winner)
  {
      if(winner.equals("X"))
      {
          playerXscore++;
          payerXScoreLabel.setText("Player X :"+playerXscore);
      }
      else
      {
        player0Score++;
        player0ScoreLabel.setText("Player 0 :"+player0Score);
      }
  }

  //method to reset the Board
    private void resetBoard()
    {
        for(Button row[]:buttons)
        {
            for(Button button:row)
            {
                button.setText("");
            }
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("TIC TAC TOE");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}