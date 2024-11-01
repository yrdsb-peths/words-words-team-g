import greenfoot.Actor;

public class NameScore extends Actor {
  String PlayerName;
  int PlayerScores;
  
  public NameScore(String PlayerName, int playerScores) {
    this.PlayerName = PlayerName;
    this.PlayerScores = playerScores;
  }

  public String getName() {
    return PlayerName;
  }

  public int getScores() {
    return PlayerScores;
  }
}
