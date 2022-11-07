
import com.lastcruise.view.View;
import org.junit.Assert;
import org.junit.Test;

public class IntroTest {
  View view = new View();

  @Test

  public void getIntro(){
    String Intro = "INTRODUCTION:\n"
        + "\n"
        + "Board the Maximus Ship, and journey into an exciting world.\n"
        + "The Maximus will hit an iceberg but you will survive the shipwreck.\n"
        + "\n"
        + "Swim to a nearby island where you have to rely on your navigation skills, strategy and survival instinct to survive and escape the island. \n"
        + "The only way to escape the island and win the game is to explore the island, gather useful items, return to the beach, and build a raft.\n"
        + "\n"
        + "Be sure to eat items along the way and sleep to maintain your stamina.";
    Assert.assertEquals(Intro, view.printStory());
  }
}
