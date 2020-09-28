package models;

public class GameBoard {

  //all other instance vars initialized to defaults
  public GameBoard(Player p1) {
    this.p1 = p1;
    turn = 1;
    boardState = new char[3][3];
  }
  
  private Player p1;

  private Player p2;

  private boolean gameStarted;

  private int turn;

  private char[][] boardState;

  private int winner;

  private boolean isDraw;
  
  public Message playTurn(Move moveAttempt) {
    //checks that both players joined. player 1 is guaranteed to exist already
    if (gameStarted == false) {
      return new Message(300);
    }
    
    //checks that game is still going
    if ((winner > 0) || isDraw) {
      return new Message(400);
    }
    
    //check turn order: p1 moves on odd, p2 moves on even
    Player currentPlayer = moveAttempt.getPlayer();
    if (turn == 2) {
      if (currentPlayer.getId() != 2) {
        return new Message(200);
      }
    } else {
      if (currentPlayer.getId() != 1) {
        return new Message(200);
      }
    }
    
    //check that space is unoccupied
    int moveX = moveAttempt.getMoveX();
    int moveY = moveAttempt.getMoveY();
    if (boardState[moveX][moveY] != 0) {
      return new Message(500);
    }
    //Update boardState
    boardState[moveX][moveY] = currentPlayer.getType();
    turn = (turn % 2) + 1;
    
    checkForWin();
    setIsDraw();
    return new Message(100);
    
  }
  
  public void addPlayer2(Player p2) {
    this.p2 = p2;
    gameStarted = true;
  }
  
  private void checkForWin() {

    //check first row
    if (boardState[0][0] != 0) {
      if (boardState[0][0] == boardState[0][1]) {
        if (boardState[0][1] == boardState[0][2]) {
          setWinner(boardState[0][2]);
          return;
        }
      }
    }
    //check second row
    if (boardState[1][0] != 0) {
      if (boardState[1][0] == boardState[1][1]) {
        if (boardState[1][1] == boardState[1][2]) {
          setWinner(boardState[1][2]);
          return;
        }
      }
    }
    //check third row
    if (boardState[2][0] != 0) {
      if (boardState[2][0] == boardState[2][1]) {
        if (boardState[2][1] == boardState[2][2]) {
          setWinner(boardState[2][2]);
          return;
        }
      }
    }
    //check first column
    if (boardState[0][0] != 0) {
      if (boardState[0][0] == boardState[1][0]) {
        if (boardState[1][0] == boardState[2][0]) {
          setWinner(boardState[2][0]);
          return;
        }
      }
    }
    //check second column
    if (boardState[0][1] != 0) {
      if (boardState[0][1] == boardState[1][1]) {
        if (boardState[1][1] == boardState[2][1]) {
          setWinner(boardState[2][1]);
          return;
        }
      }
    }
    //check third column
    if (boardState[0][2] != 0) {
      if (boardState[0][2] == boardState[1][2]) {
        if (boardState[1][2] == boardState[2][2]) {
          setWinner(boardState[2][2]);
          return;
        }
      }
    }
    //check first diagonal
    if (boardState[0][0] != 0) {
      if (boardState[0][0] == boardState[1][1]) {
        if (boardState[1][1] == boardState[2][2]) {
          setWinner(boardState[2][2]);
          return;
        }
      }
    }
    //check second diagonal
    if (boardState[0][2] != 0) {
      if (boardState[0][2] == boardState[1][1]) {
        if (boardState[1][1] == boardState[2][0]) {
          setWinner(boardState[2][0]);
          return;
        }
      }
    }
  }
  
  private void setWinner(char winnerType) {
    if (winnerType == p1.getType()) {
      winner = 1;
    } else {
      winner = 2;
    }
  }
  
  private void setIsDraw() {
    //check if board is filled
    for (int i = 0; i <= 2; i++) {
      for (int j = 0; j <=  2; j++) {
        if (boardState[i][j] == 0) {
          return;
        }
      }
    }
    if (winner == 0) {
      isDraw = true;
    }
    
  }
  
  public Player getPlayer(int playerId) {
    if (playerId == 1) {
      return p1;
    } else {
      return p2;
    }
  }
  
}
