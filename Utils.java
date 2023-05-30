package all;

public class Utils {

  public Utils() {

  }

  public String formatException(Exception e) {
    String[] array = e.toString().split("Exception: ");
    return array[1];
  }
}
