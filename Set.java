public class Set implements SetInterface {
    private Identifier[] identifiers;
    private int size;
	private final static int DEFAULT_MAX_SIZE = 20;

    public Set() {
        this.identifiers = new Identifier[DEFAULT_MAX_SIZE];
        this.size = 0;
    }

	public Set(int size) {
		this.identifiers = new Identifier[size];
        this.size = 0;
	}

	public Set(Set s) {
		this.clone(s); 
	}

	public void init() {
		this.identifiers = new Identifier[DEFAULT_MAX_SIZE];
		this.size = 0;
	}

	public void init(int size) {
		this.identifiers = new Identifier[size];
		this.size = 0;
	}

	public int getSize() {
		return this.size;
	}

	public void append(Identifier i) {
		if (!this.contains(i)) {
			this.identifiers[this.size++] = new Identifier(i);
		}
	}

	public Set intersection(Set s) {
		Set intersectionSet = new Set();
		for(int i = 0; i < this.size; i++) {
		    if(s.contains(this.identifiers[i])) {
		        intersectionSet.append(this.identifiers[i]);
		    }
		}
		return intersectionSet;
	}

	public Set difference(Set s) {
		Set differenceSet = new Set();
		for(int i = 0; i < this.size; i++) {
		    if(!s.contains(this.identifiers[i])) {
		        differenceSet.append(this.identifiers[i]);
		    }
		}
		return differenceSet;
	}
    
    public Set union(Set s) {
        Set unionSet = new Set();
		unionSet.clone(s);
		for(int i = 0; i < this.size; i++) {
			if(!unionSet.contains(this.identifiers[i])) {
				unionSet.append(this.identifiers[i]);
			}
		}
		return unionSet;
    }
    
	public Set symmetricDifference(Set s) {
		Set unionSet = intersection(s);
		Set symmDiff = new Set();
		for(int i = 0; i < this.size; i++) {
			if(!unionSet.contains(this.identifiers[i])) {
				symmDiff.append(this.identifiers[i]);
			}
		}
		for(int i = 0; i < s.size; i++) {
			if(!unionSet.contains(s.identifiers[i])) {
					symmDiff.append(s.identifiers[i]);
			}
		}
		return symmDiff;
	}

	public boolean contains(Identifier otherIdentifer) {
		for(int i = 0; i < this.size; i++) {
			if(otherIdentifer.equals(this.identifiers[i])) return true;
		}
		return false;
	}
	
	public void clone(Set s) {
		this.init();
		for (int i = 0; i < s.size; i++) {
			this.identifiers[i] = new Identifier(s.identifiers[i]);
		}
		this.size = s.size;
	}

	public String toString() {
		StringBuffer temp = new StringBuffer("{");
		for (int i = 0; i < this.size - 1; i++) {
				temp.append(this.identifiers[i] + " ");
		}
		if (this.size > 0) {
				temp.append(this.identifiers[size -1] + "}");
		} else {
				temp.append("}");
		}
		return temp.toString();
	}
}
