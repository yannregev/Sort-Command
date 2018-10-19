public class Set implements SetInterface {
	private final static int DEFAULT_MAX_SIZE = 20;
	int maxSize;
    	private Identifier[] identifiers;
	private int size;

    	public Set() {
        	this.identifiers = new Identifier[DEFAULT_MAX_SIZE];
		this.maxSize = DEFAULT_MAX_SIZE;
        	this.size = 0;
    	}

	public Set(int size) {
		this.identifiers = new Identifier[size];
		this.maxSize = size;
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
			if (size >= maxSize) {
				increaseSetSize();
			}
			this.identifiers[this.size++] = new Identifier(i);
		}
	}
	
	private void increaseSetSize() {
		maxSize *= 2;
		Identifier[] temp = new Identifier[maxSize];
		//copy loop
		for (int i = 0; i < size; i++) {
			temp[i] = identifiers[i];
		}
		identifiers = temp;
	}

	public SetInterface intersection(SetInterface s) {
		SetInterface intersectionSet = new Set();
		for(int i = 0; i < this.size; i++) {
		    if(s.contains(this.identifiers[i])) {
		        intersectionSet.append(this.identifiers[i]);
		    }
		}
		return intersectionSet;
	}

	public SetInterface difference(SetInterface s) {
		SetInterface differenceSet = new Set();
		for(int i = 0; i < this.size; i++) {
		    if(!s.contains(this.identifiers[i])) {
		        differenceSet.append(this.identifiers[i]);
		    }
		}
		return differenceSet;
	}
    
    	public SetInterface union(SetInterface set) {
        	Set unionSet = new Set();
		Set s = (Set)set;
		unionSet.clone(s);
		for(int i = 0; i < this.size; i++) {
			if(!unionSet.contains(this.identifiers[i])) {
				unionSet.append(this.identifiers[i]);
			}
		}
		return unionSet;
    	}
    
	public SetInterface symmetricDifference(SetInterface set) {
		SetInterface unionSet = intersection(set);
		SetInterface symmDiff = new Set();
		for(int i = 0; i < this.size; i++) {
			if(!unionSet.contains(this.identifiers[i])) {
				symmDiff.append(this.identifiers[i]);
			}
		}
		Set s = (Set)set;
		for(int i = 0; i < s.getSize(); i++) {
			if(!unionSet.contains(s.identifiers[i])) {
				symmDiff.append(s.identifiers[i]);
			}
		}
		return symmDiff;
	}

	public boolean contains(Identifier otherIdentifer) {
		for(int i = 0; i < this.size; i++) {
			if(otherIdentifer.equals(this.identifiers[i])) {
				return true;
			}		
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

	@Override
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
