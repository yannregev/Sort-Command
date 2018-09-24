import java.util.*;

public class Identifier implements IdentInterface{
	private StringBuffer element;
	public Identifier(char c) {
		this.element = new StringBuffer();
		element.append(c);
	}
	public Identifier(StringBuffer element) {
	  this.element = new StringBuffer(element);
	}

	public Identifier(Identifier ide) {
	  this.element = new StringBuffer(ide.element);
	}

	public void init(char c) {
		this.element = new StringBuffer(c);
	}

	public void setValue(StringBuffer element) {
		this.element = new StringBuffer(element);
	}

	public void append(char c) {
		this.element.append(c);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		Identifier i = (Identifier)obj;
		return i.element.toString().equals(this.element.toString());
	}

	public String toString() {
		return element.toString();
	}
}
