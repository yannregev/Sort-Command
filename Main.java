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

	static boolean startsWithLetter(String s) {
		return Pattern.compile("^[a-zA-Z]").matcher(s).find();
	}

	static boolean containsNonAlphanumeric(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	static String parseString(String s) {
		s = s.substring(1, s.length() - 1);
		s = s.replaceAll("(^\\s+|\\s+$)", "");
		return s;
	}


	static void readFile(String s, Set set) throws Exception {
		Scanner in = new Scanner(new File(s));
		in.useDelimiter("");
		while(in.hasNext()) {
			if(nextCharIsLetter(in)) {
				addIdentifer(in, set);
			}
			nextChar(in);
		}
		System.out.printf("the set is  %s \n", set.toString());
		in.close();

	}
	
	static void addIdentifer(Scanner in, Set set) {
		Identifier newIdentifer = new Identifier(nextChar(in));
		while(nextCharIsAlpha(in)) {
			newIdentifer.append(nextChar(in));
		}
		set.append(newIdentifer);
	}

	public static void main(String[] args) throws Exception {
		Set set1 = new Set(100);
		Set set2 = new Set(100);
		if(args.length < 1) {
			System.out.println("Please provide a file");
			return;
		}
		readFile(args[0], set1);
		readFile(args[1], set2);
	}
}
