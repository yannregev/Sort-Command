public interface SetInterface {

    /*
	Elements    : identifiers of Type Identifier 
	Structure   : 
	Domain      : Identifiers up to the given size
	Constructors:	

	public Set();
		PRE -
		POST- A new Set object is created
			and value is the empty identifiers array with a default size of 20
	public Set(int size);
		PRE -
		POST- A new Set object is created
			and the value is a the empty identifiers array with a size of 'size'

	public Set(Set s);
		PRE -
		POST- A new Set object is created
			and the value is the copy of the set 's'

	public Init();
		PRE - 
		POST- the value of the identifiers array is reset to empty with a default size of 20

	Public Init(int size);
		PRE - 
		POST- the value of the identifiers array is reset to empty with a size of 'size'
    */
	public void append(Identifier i);
	/*
		PRE -
		POST- Copys Identifier to the end of the array
	*/
	public int getSize();
	/*
		PRE -
		POST- The size of the set is returned
	*/	
	public boolean contains(Identifier otherIdentifer);
	/*
		PRE -
		POST- TRUE: the element exists in the setInterface object
			FALSE: the element does not exist in the setInterface object
	*/
	public Set intersection(Set s);
	/*
		PRE -
		POST- A new Set is created and the value is the intersection of of the two sets
	*/
	public Set difference(Set s);
	/*
		PRE -
		POST- A new Set is created and the value is the difference of of the two sets
	*/
	public Set union(Set s);
	/*
		PRE -
		POST- A new Set is created and the value is the union of of the two sets
	*/
	public Set symmetricDifference(Set s);
	/*
		PRE -
		POST- A new Set is created and the value is the symmetric difference of of the two sets
	*/
}
