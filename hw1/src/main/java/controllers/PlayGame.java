package controllers;

import io.javalin.Javalin;
import java.io.IOException;
import java.util.Queue;
import org.eclipse.jetty.websocket.api.Session;
import models.*;
import java.lang.Integer;

import com.google.gson.Gson;

class PlayGame {

  private static final int PORT_NUMBER = 8080;

  private static Javalin app;
  
  private static GameBoard board;
  
  private static final Gson gson = new Gson();

  /** Main method of the application.
   * @param args Command line arguments
   */
  public static void main(final String[] args) {

    app = Javalin.create(config -> {
      config.addStaticFiles("/public");
    }).start(PORT_NUMBER);

    // Test Echo Server
    app.post("/echo", ctx -> {
      ctx.result(ctx.body());
    });
    
    
//    //
//    Gson gson = new GsonBuilder().create();
//    JavalinJson.setFromJsonMapper(gson::fromJson);
//    JavalinJson.setToJsonMapper(gson::toJson);

    //new game end point
    app.get("/newgame", ctx -> {
      ctx.redirect("/tictactoe.html");
    });
    //start game end point
    app.post("/startgame", ctx -> {
      Player p1 = new Player(ctx.attribute("type"), 1);
      ctx.result(startGame(p1));
    });
    //join game end point
    app.get("/joingame", ctx -> {
      joinGame();
      ctx.redirect("/tictactoe.html?p=2");
    });
    app.post("/move", ctx -> {
      int playerId = Integer.parseInt(ctx.queryParam("p"));
      int moveX = Integer.parseInt(ctx.attribute("x"));
      int moveY = Integer.parseInt(ctx.attribute("y"));
      
      ctx.result(move(playerId, moveX, moveY));
    });
    
    /**
     * Please add your end points here.
     * 
     * 
     * 
     * 
     * Please add your end points here.
     * 
     * 
     * 
     * 
     * Please add your end points here.
     * 
     * 
     * 
     * 
     * Please add your end points here.
     * 
     */

    // Web sockets - DO NOT DELETE or CHANGE
    app.ws("/gameboard", new UiWebSocket());
  }

  private static String startGame(Player p1) {
    board = new GameBoard(p1);
    return gson.toJson(board);
  }
  
  private static void joinGame() {
    char p1Type = board.getPlayer(1).getType();
    char p2Type;
    
    if (p1Type == 'X') {
      p2Type = 'O';
    } else {
      p2Type = 'X';
    }
    Player p2 = new Player(p2Type, 2);
    board.addPlayer2(p2);
    
    sendGameBoardToAllPlayers(gson.toJson(board));
  }
  
  private static String move(int playerId, int moveX, int moveY) {
    Player currentPlayer = board.getPlayer(playerId);
    Move moveAttempt = new Move(currentPlayer, moveX, moveY);
    Message response = board.playTurn(moveAttempt);
    
    sendGameBoardToAllPlayers(gson.toJson(board));
    return gson.toJson(response); 
  }
  /** Send message to all players.
   * @param gameBoardJson Gameboard JSON
   * @throws IOException Websocket message send IO Exception
   */
  private static void sendGameBoardToAllPlayers(final String gameBoardJson) {
    Queue<Session> sessions = UiWebSocket.getSessions();
    for (Session sessionPlayer : sessions) {
      try {
        sessionPlayer.getRemote().sendString(gameBoardJson);
      } catch (IOException e) {
        // Add logger here
      }
    }
  }

  public static void stop() {
    app.stop();
  }
}
