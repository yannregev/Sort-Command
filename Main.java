import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Main {
	static final int SET_SIZE = 100, NUMBER_OF_FILES = 2;
	static PrintStream out;
	boolean caseInsensitive = false, descending = false;

	boolean startsWithLetter(String s) {
		return Character.isLetter(s.charAt(0));
	}


	void readFile(String s, Set set, Map<String, Integer> occurence) throws Exception {
		Scanner in = new Scanner(new File(s));
		while (in.hasNextLine()) {
			String input = in.nextLine();
			if (caseInsensitive) {
				input = input.toLowerCase();
			}
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
		Iterator<String> meIter;
		if (!descending) {
			meIter = tree.ascendingIterator();
		} else {
			meIter = tree.descendingIterator();
		}
		while (meIter.hasNext()) {
			out.printf("%s\n",meIter.next());
		}

	}

	void start(String[] args) throws Exception {
		Set set1 = new Set(SET_SIZE);
		Set set2 = new Set(SET_SIZE);

		Vector<String> files = new Vector<String>();

		out = new PrintStream(System.out);



		for (int i = 0; i < args.length; i++) {
			
			switch(args[i]) {
				case "-i":
					caseInsensitive = true;
					break;
				case "-d":
					descending = true;
					break;
				default:
					files.add(args[i]);
					break;
			}
		}

		if(files.size() != NUMBER_OF_FILES) {
			out.println("Usage: java Main -args file1 file2");
			return;
		}
		for (int i = 0; i < NUMBER_OF_FILES; i++) {
			File file = new File(files.get(i));
			if (!file.exists()) {
				out.println(files.get(i) + " no such file");
				return;
			}
		}
		Map<String, Integer> occurence = new HashMap<String, Integer>();
		readFile(files.get(0), set1, occurence);
		readFile(files.get(1), set2, occurence);
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
