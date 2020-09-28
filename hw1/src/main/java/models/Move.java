package models;

public class Move {

  /**
   * Constructs a move linked to a specific player.
   * 
   * @param player Player making the move
   * @param moveX X-coordinate of selected space
   * @param moveY Y-coordinate of selected space
   */
  public Move(Player player, int moveX, int moveY) {
    this.player = player;
    this.moveX = moveX;
    this.moveY = moveY;
  }

  private Player player;

  private int moveX;

  private int moveY;

  public Player getPlayer() {
    return player;
  }

  public int getMoveX() {
    return moveX;
  }

  public int getMoveY() {
    return moveY;
  }

}
