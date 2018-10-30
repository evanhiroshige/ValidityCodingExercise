import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.codec.language.Metaphone;

public class FindDuplicates {

  private List<String[]> users;
  private int[][] distances;

  public FindDuplicates() {
    users = new ArrayList<>();

    Scanner scanner;
    try {
      scanner = new Scanner(new File("normal.csv"));
    } catch (
        FileNotFoundException e) {
      throw new IllegalArgumentException("could not find file.");
    }

    scanner.useDelimiter("\n");
    scanner.next();
    int count = 0;
    while (scanner.hasNext()) {
      count++;
      String[] values = scanner.next().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
      users.add(values);
    }

    distances = new int[count][count];
    scanner.close();
  }

  // compare fields 1 by 1 with Levenshtein and Metaphone
  private int getDistance(String[] a, String[] b) {
    int sum = 0;
    Metaphone mp = new Metaphone();

    for (int i = 1; i < a.length; i++) {
      String a1 = a[i];
      String b1 = b[i];
      if (mp.isMetaphoneEqual(a1, b1)) {
        continue;
      }
      sum += Levenshtein.distance(a1, b1);
    }
    return sum;
  }

  public void findDups() {
    // will want to compare everything to everything using distance helper
  }

}
