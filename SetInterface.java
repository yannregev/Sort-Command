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
			and the value is a the copy of the 's'

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
		POST- SUCCESS - Copys Identifier to the end of the array
			FAILURE - new size > 'size'
	*/
	public int getSize();
	/*
		PRE -
		POST-
	*/	
	public boolean contains(Identifier otherIdentifer);
	/*
		PRE -
		POST-
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
