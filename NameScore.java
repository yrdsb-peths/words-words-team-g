import greenfoot.Actor;
import java.util.*;

public class NameScore extends Actor  implements Comparable <NameScore>{
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

  public int compareTo(NameScore other) {
    return Integer.compare(other.PlayerScores, this.PlayerScores);
  }
}
