import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Main {
	static final int SET_SIZE = 100;
	static PrintStream out;

	char nextChar (Scanner in) {
		return in.next().charAt(0); 
	}

 	boolean nextCharIs(Scanner in, char c) {
		return in.hasNext(Pattern.quote(c+"")); 
	}

 	boolean nextCharIsDigit (Scanner in) {
		return in.hasNext("[0-9]"); 
	}

 	boolean nextCharIsLetter (Scanner in) {
		return in.hasNext("[a-zA-Z]"); 
	}

	boolean nextCharIsAlpha(Scanner in) {
		return nextCharIsDigit(in) || nextCharIsLetter(in);
	} 

	boolean startsWithLetter(String s) {
		return Character.isLetter(s.charAt(0));
	}

	boolean containsNonAlphanumeric(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	void readFile(String s, Set set, Map<String, Integer> occurence) throws Exception {
		Scanner in = new Scanner(new File(s));
		while (in.hasNextLine()) {
			String input = in.nextLine();
			String[] parsedInput = input.split("[^a-zA-Z0-9]+");
			for (int i = 0; i < parsedInput.length; i++) {
				if (startsWithLetter(parsedInput[i]))
					set.append(new Identifier(new StringBuffer(parsedInput[i])));
			}
		}
		System.out.printf("the set is  %s \n", set.toString());
		in.close();

	}

	
	void addIdentifer(Scanner in, Set set, Map<String, Integer> occurence) {
		Identifier newIdentifer = new Identifier(nextChar(in));
		while(nextCharIsAlpha(in)) {
			newIdentifer.append(nextChar(in));
		}
		if(occurence.get(newIdentifer.toString()) == null) {
			occurence.put(newIdentifer.toString(), 0);
		}
		int i = occurence.get(newIdentifer.toString());
		occurence.put(newIdentifer.toString(), i + 1);
		set.append(newIdentifer);
	}

	void sortAndPrint(Map<String, Integer> occurence) {
		SortedSet<String> keys = new TreeSet<>(occurence.keySet());
		for (String key : keys) { 
			int value = occurence.get(key);
			if((value % 2) != 0) {
				System.out.printf("%s \n", key);
			}
		}
	}

	void start(String[] args) throws Exception {
		Set set1 = new Set(SET_SIZE);
		Set set2 = new Set(SET_SIZE);
		if(args.length < 2) {
			System.out.println("Usage: java Main -args file1 file2\n");
			return;
		}

		out = new PrintStream(System.out);
		Map<String, Integer> occurence = new HashMap<String, Integer>();
		readFile(args[0], set1, occurence);
		readFile(args[1], set2, occurence);
		sortAndPrint(occurence);

	}

	public static void main(String[] args) throws Exception {
		new Main().start(args);
	}
}
