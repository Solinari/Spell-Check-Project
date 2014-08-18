import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;


/*Justin Tyme Dejesus
CSC 301 - HW 4
06/12/2014*/

// COMPARISON RESULTS!!!!

// RED/BLACK BST

/*		My time for compiling the dictionary was: 446.0000 milliseconds.
My time for comparing the words in the book was 783.0000 milliseconds. 
There was 126449 misspellings & 1671 distinct misspelled words. */

// Linear Probing Hash ST

/*		My time for compiling the dictionary was: 430.0000 milliseconds.
My time for comparing the words in the book was 598.0000 milliseconds. 
There was 126449 misspellings & 1671 distinct misspelled words. */

// Separate Chaining Hash ST
/*		
My time for compiling the dictionary was: 668.0000 milliseconds.
My time for comparing the words in the book was 622.0000 milliseconds. 
There was 126449 misspellings & 1671 distinct misspelled words. */


/*		In the comment at the beginning of SpellCheck.java, answer these questions (for the war+peace.txt input file)
1. Which implementation took the least time to build the dictionary? Linear Probing Hash ST
2. Which implementation took the most time to build the dictionary? Separate Chaining Hash ST
3. Which implementation took the least time to check for misspellings? Linear Probing Hash ST
4. Which implementation took the most time to check for misspellings? RED/BLACK BST*/

public class SpellCheck {
	
	  public static Scanner openInput(String fname)
	  {
		  // for some reason reason it throws errors when I don't have the method in the same file for file I/O
	    Scanner infile = null;
	    try {
	      infile = new Scanner(new File(fname));
	    } catch(FileNotFoundException e) {
	      System.out.printf("Cannot open file '%s' for input\n", fname);
	      System.exit(0);
	    }
	    return infile;
	  }

	public static void main(String[] args) {
		
		// the second one of each is for dumping misspellings nd then calling the size of that for distinct misspellings
		// 1
		//RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
		//RedBlackBST<String, Integer> st2 = new RedBlackBST<String, Integer>();
		
		// 2
		//LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
		//LinearProbingHashST<String, Integer> st2 = new LinearProbingHashST<String, Integer>();
		
		// 3
		SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();
		SeparateChainingHashST<String, Integer> st2 = new SeparateChainingHashST<String, Integer>();		
		
		Scanner stdin = new Scanner(System.in);
		System.out.println("Enter a File name for the dictionary.");
		String dictionary = stdin.next();
		System.out.println("Enter a File name for the text you wish to compare.");
		String mybooktext = stdin.next();
		stdin.close();

		Scanner myfile = openInput(dictionary);
		
		double start = System.currentTimeMillis();
		while (myfile.hasNext()) {
			String nextword = myfile.next(); 
			st.put(nextword, 0);
		}
		double end = System.currentTimeMillis();
		
		double actualtime = end - start;
		
		Scanner mybook = openInput(mybooktext).useDelimiter(Pattern.compile("[\\W|\n]"));
		
		int misspelled = 0;
		double start2 = System.currentTimeMillis();
		while (mybook.hasNext()) {
			String compare = mybook.next().toLowerCase();
					
			if (st.contains(compare) == false) {
				System.out.println(compare);
				misspelled++;
				st2.put(compare, 0);
			}
			
		}
		double end2 = System.currentTimeMillis();
		
		double actualtime2 = end2 - start2;
		
		System.out.printf("My time for compiling the dictionary was: %.4f milliseconds.\n"
				+ "My time for comparing the words in the book was %.4f milliseconds. \n"
				+ "There was %d misspellings & %d distinct misspelled words. \n", actualtime,actualtime2, misspelled, st2.size());
		
		myfile.close();
		

		
	}

}
