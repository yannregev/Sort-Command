import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Main {
	static PrintStream out;
	boolean caseInsensitive = false, descending = false;

	boolean startsWithLetter(String s) {
		return Character.isLetter(s.charAt(0));
	}


	void readFile(String s, Set set, Map<Identifier, Integer> occurence) throws Exception {
		Scanner in = new Scanner(new File(s));
		while (in.hasNextLine()) {
			String input = in.nextLine();
			input = input.replaceAll("[^a-zA-Z0-9]+", " ");
			input = input.replaceAll("\\s+|\\s+$", " ");
			if (caseInsensitive) {
				input = input.toLowerCase();
			}
			String[] parsedInput = input.split(" ");
			for (int i = 0; i < parsedInput.length; i++) {
				if (startsWithLetter(parsedInput[i])) {
					Identifier identifier = new Identifier(new StringBuffer(parsedInput[i]));
					set.append(identifier);
					if(occurence.get(identifier) == null) {
						occurence.put(identifier, 0);
					}
					int apearences = occurence.get(identifier);
					occurence.put(identifier, apearences + 1);
				}
			}
		}
		in.close();
	}

	void sortAndPrint(Map<Identifier, Integer> occurence) {
		BinaryTreeInterface<Identifier> tree = new BinaryTree<Identifier>();
		for (Identifier s : occurence.keySet()) {
	
			if((occurence.get(s) % 2) != 0) {
				tree.add(s);
			}
		}
		Iterator<Identifier> meIter;
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
		Vector<Set> sets = new Vector<Set>();
		Vector<String> files = new Vector<String>();
		

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

		for (int i = 0; i < files.size(); i++) {
			File file = new File(files.get(i));
			if (!file.exists()) {
				throw new Exception(files.get(i) + " no such file");
			}
			sets.add(new Set());
		}
		Map<Identifier, Integer> occurence = new HashMap<Identifier, Integer>();
		for (int i = 0; i < files.size(); i++) {
			readFile(files.get(i), sets.get(i), occurence);
		}
		sortAndPrint(occurence);
	}

	public static void main(String[] args) {
		out = new PrintStream(System.out);
		try {
			new Main().start(args);
		} catch (Exception e) {
			out.printf("error %s \n",e.getMessage());
		}	
	}
}
