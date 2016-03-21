import java.io.File;
import java.util.Scanner;

public class bbst {
	public static void main(String[] args) {
		
		
		EventCounter ec = new EventCounter(new File(args[0]));
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()){
			String nextLine = scan.nextLine();
			String[] split = nextLine.split(" ");
			String command = split[0];
			if(command.equalsIgnoreCase("increase")){
				int theID = Integer.parseInt(split[1]);
				int m = Integer.parseInt(split[2]);
				ec.increase(theID, m);
			}
			else if(command.equalsIgnoreCase("reduce")){
				int theID = Integer.parseInt(split[1]);
				int m = Integer.parseInt(split[2]);
				ec.reduce(theID, m);
			}
			else if(command.equalsIgnoreCase("count")){
				int theID = Integer.parseInt(split[1]);
				ec.count(theID);
			}
			else if(command.equalsIgnoreCase("inrange")){
				int ID1 = Integer.parseInt(split[1]);
				int ID2 = Integer.parseInt(split[2]);
				ec.inRange(ID1, ID2);
			}
			else if(command.equalsIgnoreCase("next")){
				int theID = Integer.parseInt(split[1]);
				ec.next(theID);
			}
			else if(command.equalsIgnoreCase("previous")){
				int theID = Integer.parseInt(split[1]);
				ec.previous(theID);
//				ec.levelOrder();
			}
			else if(command.equalsIgnoreCase("quit")){
				break; //gracefully exit
			}
			else{
				System.out.println("Invalid Command !!! ");
			}
		}
		// read interactive commands
		scan.close();

	}
}
