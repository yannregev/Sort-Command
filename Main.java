import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Main {
	static final int SET_SIZE = 100;
	static PrintStream out;

	boolean startsWithLetter(String s) {
		return Character.isLetter(s.charAt(0));
	}


	void readFile(String s, Set set, Map<String, Integer> occurence) throws Exception {
		Scanner in = new Scanner(new File(s));
		while (in.hasNextLine()) {
			String input = in.nextLine();
			String[] parsedInput = input.split("[^a-zA-Z0-9]+");
			for (int i = 0; i < parsedInput.length; i++) {
				if (startsWithLetter(parsedInput[i])) {
					set.append(new Identifier(new StringBuffer(parsedInput[i])));
					if(occurence.get(parsedInput[i]) == null) {
						occurence.put(parsedInput[i], 0);
					}
					int apearences = occurence.get(parsedInput[i]);
					occurence.put(parsedInput[i], apearences + 1);
				}
			}
		}
		out.printf("the set is  %s \n", set.toString());
		in.close();

	}

	void sortAndPrint(Map<String, Integer> occurence) {
		BinaryTree<String> tree = new BinaryTree<String>();
		for (String s : occurence.keySet()) {
			if((occurence.get(s) % 2) != 0) {
				tree.add(s);
			}
		}
		Iterator<String> meIter = tree.ascendingIterator();
		while (meIter.hasNext()) {
			out.printf("%s\n",meIter.next());
		}

	}

	void start(String[] args) throws Exception {
		Set set1 = new Set(SET_SIZE);
		Set set2 = new Set(SET_SIZE);
		out = new PrintStream(System.out);
		if(args.length < 2) {
			out.println("Usage: java Main -args file1 file2");
			return;
		}
		Map<String, Integer> occurence = new HashMap<String, Integer>();
		readFile(args[0], set1, occurence);
		readFile(args[1], set2, occurence);
		sortAndPrint(occurence);

	}

	public static void main(String[] args) {
		try {
			new Main().start(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
}
