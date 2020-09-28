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
    if (turn % 2 == 0) {
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
    turn++;
    return new Message(100);
    
  }
  
  public void addPlayer2(Player p2) {
    this.p2 = p2;
    gameStarted = true;
  }
  
  private void checkForWin() {
    //    only check if turn >= 5/
  }
  
  private void setWinner() {
    //call checkForWin()
  }
  
  private void setIsDraw() {
    //only check when turns = 9
  }
}
