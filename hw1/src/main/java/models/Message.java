package models;

public class Message {
  
  static final String ERR_200_MSG = "It's not your turn!";
  static final String ERR_300_MSG = "Missing two players";
  static final String ERR_400_MSG = "Game is over";
  static final String ERR_500_MSG = "That space is taken";
  
  /**
   * Associates code with corresponding error description and validity.
   * 
   * @param code message identifier
   */
  public Message(int code) {
    this.code = code;
    
    if (code == 100) {
      moveValidity = true;
      message = "";
    } else {
      moveValidity = false;
      
      if (code == 200) {
        message = ERR_200_MSG;
      } else if (code == 300) {
        message = ERR_300_MSG;
      } else if (code == 400) {
        message = ERR_400_MSG;
      } else if (code == 500) {
        message = ERR_500_MSG;
      }
    }
  } 
  
  private boolean moveValidity;

  private int code;

  private String message;

}
