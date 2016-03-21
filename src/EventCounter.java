import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/**
 * @author mebin
 *
 */
public class EventCounter {

	private RedBlackTree rbt;
	EventCounter(File f) {
		rbt = new RedBlackTree();
		FileReader fileReader;
			try {
				fileReader = new FileReader(f);
				BufferedReader bufReader = new BufferedReader(fileReader, 50000);
				Scanner fileScanner = new Scanner(bufReader);
				int n = fileScanner.nextInt();
				while (n != 0) {
					int id = fileScanner.nextInt();
					int count = fileScanner.nextInt();

					rbt.sortedInsert(id, count);

					n--;
				}
//				levelOrder();
				bufReader.close();
				fileReader.close();
			} catch (FileNotFoundException e) {
				System.out
				.println("Please enter the file name from where input has to be read..");
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("First line of the file should be a number!!");
				e.printStackTrace();
			} catch (IOException e) {
				System.out
				.println("IO exception while reading the file exception message is "
						+ e.getMessage());
				e.printStackTrace();
			}
			
		}	/**
	 * Increase the count of the event theID by m. If theID is not present,
	 * insert it. Print the count of theID after the addition.
	 * 
	 * @param theID
	 * @param m
	 */
	public void increase(int theID, int m) {
		rbt.increaseOrInsert(theID, m);
	}
	
	public void levelOrder(){
		rbt.levelOrder();
	}
	/**
	 * Decrease the count of theID by m. If theID’s count becomes less than or
	 * equal to 0, remove theID from the counter. Print the count of theID after
	 * the deletion, or 0 if theID is removed or not present
	 * 
	 * @param theID
	 * @param m
	 */
	public void reduce(int theID, int m) {
		System.out.println(rbt.decreaseOrDelete(theID, m));
//		rbt.levelOrder();
	}

	/**
	 * Print the count of theID. If not present, print 0.
	 * 
	 * @param theID
	 */
	public void count(int theID) {
		System.out.println(rbt.count(theID));
	}

	/**
	 * Print the total count for IDs between ID1 and ID2 inclusively. Note, ID1
	 * ≤ ID2
	 * 
	 * @param id1
	 * @param id2
	 */
	public void inRange(int id1, int id2) {
		System.out.println(rbt.inRange(id1, id2));
	}

	/**
	 * Print the ID and the count of the event with the lowest ID that is
	 * greater that theID. Print “0 0”, if there is no next ID.
	 * 
	 * @param theID
	 */
	public void next(int theID) {
		rbt.next(theID);
	}

	/**
	 * Print the ID and the count of the event with the greatest key that is
	 * less that theID. Print “0 0”, if there is no previous ID.
	 * 
	 * @param theID
	 */
	public void previous(int theID) {
		rbt.previous(theID);
	}
}
