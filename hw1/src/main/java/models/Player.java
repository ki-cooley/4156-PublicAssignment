package models;

public class Player {

  /**
   * Constructs a player with their chosen token type.
   * 
   * @param type Token--'x' or 'o
   * @param id player identifier
   */
  public Player(char type, int id) {
    this.type = type;
    this.id = id;
  }

  private char type;

  private int id;

  public char getType() {
    return type;
  }

  public int getId() {
    return id;
  }

}
