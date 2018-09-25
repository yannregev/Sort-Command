import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Main {
	static final int INPUT_SET_SIZE = 10;
	static PrintStream out;

	static char nextChar (Scanner in) {
		return in.next().charAt(0); 
	}

 	static boolean nextCharIs(Scanner in, char c) {
		return in.hasNext(Pattern.quote(c+"")); 
	}

 	static boolean nextCharIsDigit (Scanner in) {
		return in.hasNext("[0-9]"); 
	}

 	static boolean nextCharIsLetter (Scanner in) {
		return in.hasNext("[a-zA-Z]"); 
	}

	static boolean nextCharIsAlpha(Scanner in) {
		return nextCharIsDigit(in) || nextCharIsLetter(in);
	} 

	static int compareTo(Identifier id, Identifier idOther) {
		return id.toString().compareTo(idOther.toString());
	}

	static int compareToLowerCase(Identifier id, Identifier idOther) {
		return id.toString().toLowerCase().compareTo(idOther.toString().toLowerCase());
	}

	static void readFile(String s, Set set, Map<String, Integer> occurence) throws Exception {
		Scanner in = new Scanner(new File(s));
		in.useDelimiter("");
		char prev = 'c';
		while(in.hasNext()) {
			if(nextCharIsLetter(in)) {
				if(Character.isDigit(prev)) {
					skipString(in);
				} else {
					addIdentifer(in, set, occurence);
				}
				
			}
			prev = nextChar(in);
		}
		System.out.printf("the set is  %s \n", set.toString());
		in.close();

	}

	static void skipString(Scanner in) {
		while(nextCharIsAlpha(in)) {
			System.out.printf("skipped char %c \n", nextChar(in));
		}
	}
	
	static void addIdentifer(Scanner in, Set set, Map<String, Integer> occurence) {
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

	static void sortAndPrint(Map<String, Integer> occurence) {
		SortedSet<String> keys = new TreeSet<>(occurence.keySet());
		for (String key : keys) { 
			int value = occurence.get(key);
			if((value % 2) != 0) {
				System.out.printf("%s \n", key);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Set set1 = new Set(100);
		Set set2 = new Set(100);
		if(args.length < 1) {
			System.out.println("Please provide a file");
			return;
		}
		Map<String, Integer> occurence = new HashMap<String, Integer>();
		readFile(args[0], set1, occurence);
		readFile(args[1], set2, occurence);
		sortAndPrint(occurence);
	}
}
