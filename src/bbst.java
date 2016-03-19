import java.io.File;
import java.util.Scanner;

public class bbst {
	public static void main(String[] args) {
		
		
		EventCounter ec = new EventCounter(new File(args[0]));
		
		Scanner scan = new Scanner(System.in);
		// read interactive commands
		scan.close();

	}
}
