/**
*The Dictionary class inserts given words into a binary search tree. 
*The dictionary can be searched for specific words and information about it can be returned.
*Inexact matches for words will be returned in case of misspelling. 
*
*@author Lara Okafor
*@version 02.10.2017
*/

class Dictionary {
    private Node root;
    int[] dybder;

    /**
    *Inserts new word into binary search tree (BST).
    *
    *@param ord Word which will be inserted into BST.
    */
    public void insert(String ord) {
		String streng = ord.toLowerCase();

		if(root == null) {
		    root = new Node(streng);
		}
		else {
		    Node current = root;

		    while(true) {
			if(current.word.compareTo(streng) > 0) {
			    if(current.left == null) {
					current.left = new Node(streng);
					break;
			    }
			    else {
					current = current.left;
			    }
			}

			if(current.word.compareTo(streng) < 0) {
			    if(current.right == null) {
					current.right = new Node(streng);
					break;
			    }
			    else {
					current = current.right;
			    }
			}
		    }
		}
    }

    /**
    *Searches for word in Dictionary and prints out if found. 
    *If word isn't found it returns similar words using variations() method.
    *
    *@param ord Word which will be searched for.
    */
    public void search(String ord) {
		Node current = root;
		String streng = ord.toLowerCase();

		while(true) {
			    if(current == null) {
					System.out.println("Not found.");
					System.out.println("Were you looking for: ");
					variations(ord, root);
					break;
			    }
			    else if(current.word.equals(streng)) {
					System.out.println("Found: " + current.word);
					break;
			    }
			    else {
					if(current.word.compareTo(streng) > 0) {
					    current = current.left;
					}
					else {
					    current = current.right;
					}
			    }
		}
    }

    /**
    *Searches for close matches in Dictionary for given words.
    *
    *@param ord The input word which needs to be matched.
    *@param naa Word already in Dictionary that is being compared to ord.
    */
    public void variations(String ord, Node naa) {
		String streng = ord.toLowerCase();
		int lengde = streng.length();
		int naaLengde = naa.word.length();
		boolean riktig = true;
		char[] karakterer = streng.toCharArray();
		char[] naaArr = naa.word.toCharArray();

		if(naa.left != null) {
		    variations(streng, naa.left);
		}

		if(naa.right != null) {
		    variations(streng, naa.right);
		}

		if(variation1(lengde, naaLengde, riktig, karakterer, naaArr) || 
		   variation2(lengde, naaLengde, riktig, karakterer, naaArr) || 
		   variation3(lengde, naaLengde, riktig, karakterer, naaArr) || 
		   variation4(lengde, naaLengde, riktig, karakterer, naaArr)) 
		{
		   	System.out.println(naa.word);
		}
    }

    /**
    *Returns true if a word with two switched neighbouring letters matches.
    *
    *@param lengde Length of input word.
    *@param naaLengde Length of word in Dictionary that is a possible match.
    *@param riktig Boolean that will be true if they are a match and false if not.
    *@param karakterer Array of characters in input word.
    *@param naaArr Array of characters of possible match in Dictionary.
    *
    *@return Boolean that describes whether the words are a match or not.
    */
    public boolean variation1(int lengde, int naaLengde, boolean riktig, 
    	                      char[] karakterer, char[] naaArr) {
		if(naaLengde == lengde) {
		    for(int i = 0; i < lengde; i++) {
				if(i == 0) {
				    if(!((karakterer[i] == naaArr[i] || karakterer[i] == naaArr[i+1]) && 
		                  (naaArr[i] == karakterer[i] || naaArr[i] == karakterer[i + 1]))) 
				    {
						riktig = false;
				    }
				}
				else if(i == lengde - 1) {
				    if(!((karakterer[i] == naaArr[i] || karakterer[i] == naaArr[i-1]) && 
		                  (naaArr[i] == karakterer[i] || naaArr[i] == karakterer[i - 1]))) 
				    {
						riktig = false;
				    }
				}
				else {
				    if(!((karakterer[i] == naaArr[i] || karakterer[i] == naaArr[i+1] || 
				    	  karakterer[i] == naaArr[i-1]) && (naaArr[i] == karakterer[i] || 
				    	  naaArr[i] == karakterer[i - 1] || naaArr[i] == karakterer[i + 1]))) 
				    {
						riktig = false;
				    }
				}
		    } 
		}
		else {
		    riktig = false;
		}

		return riktig;
    }

    /**
    *Returns true if a word with one letter replaced matches.
    *
    *@param lengde Length of input word.
    *@param naaLengde Length of word in Dictionary that is a possible match.
    *@param riktig Boolean that will be true if they are a match and false if not.
    *@param karakterer Array of characters in input word.
    *@param naaArr Array of characters of possible match in Dictionary.
    *
    *@return Boolean that describes whether the words are a match or not.
    */
    public boolean variation2(int lengde, int naaLengde, boolean riktig, 
    	                      char[] karakterer, char[] naaArr) {
		if(naaLengde == lengde) {
		    int antGanger = 0;
		    for(int i = 0; i < lengde; i++) {
				if(!(karakterer[i] == naaArr[i])) {
				    antGanger++;
				} 
		    }

		    if(antGanger != 1) {
				riktig = false;
		    }
		}
		else {
		    riktig = false;
		}

		return riktig;
    }

    /**
    *Returns true if a word with an extra letter matches.
    *
    *@param lengde Length of input word.
    *@param naaLengde Length of word in Dictionary that is a possible match.
    *@param riktig Boolean that will be true if they are a match and false if not.
    *@param karakterer Array of characters in input word.
    *@param naaArr Array of characters of possible match in Dictionary.
    *
    *@return Boolean that describes whether the words are a match or not.
    */
    public boolean variation3(int lengde, int naaLengde, boolean riktig, 
    	                      char[] karakterer, char[] naaArr) {
		riktig = false;

		if(naaLengde == lengde + 1) {
		    String ord = new String(karakterer);
		    String naa = new String(naaArr);

		    for(int i = 0; i < naaLengde; i++) {
				String temp = "";
				if(i == 0) {
				    temp = naa.substring(i+1);
				}
				else if(i == naaLengde - 1) {
				    temp = naa.substring(0, i);
				}
				else {
				    temp = naa.substring(0, i) + naa.substring(i+1);
				}

				if(ord.contains(temp)) {
				    riktig = true;
				}
	    	}
		}

		return riktig;
    }

    /**
    *Returns true if a word with a removed letter matches
    *
    *@param lengde Length of input word.
    *@param naaLengde Length of word in Dictionary that is a possible match.
    *@param riktig Boolean that will be true if they are a match and false if not.
    *@param karakterer Array of characters in input word.
    *@param naaArr Array of characters of possible match in Dictionary.
    *
    *@return Boolean that describes whether the words are a match or not.
    */
    public boolean variation4(int lengde, int naaLengde, boolean riktig, 
    	                      char[] karakterer, char[] naaArr) {
		riktig = false;

		if(naaLengde == lengde - 1) {
		    String ord = new String(karakterer);
		    String naa = new String(naaArr);

		    for(int i = 0; i < lengde; i++) {
				String temp = "";
				if(i == 0) {
				    temp = ord.substring(i+1);
				}
				else if(i == lengde - 1) {
				    temp = ord.substring(0, i);
				}
				else {
				    temp = ord.substring(0, i) + ord.substring(i+1);
				}

				if(naa.contains(temp)) {
				    riktig = true;
				}
		    }
		}

		return riktig;
    }

    /**
    *Finds height of a node.
    *
    *@param node Node whose height in the tree will be returned.
    *
    *@return Height of the node.
    */
    private int findHeight(Node node) {	
		if(node == null) {
		    return -1;
		}
		else {
		    int hTre = findHeight(node.right);
		    int vTre = findHeight(node.left);

		    if(hTre > vTre) {
				return hTre + 1;
		    }
		    else {
				return vTre + 1;
		    }
		}
    }

    /**
    *Finds depth of tree by calling findHeight() with root node.
    *
    *@return Depth of the binary search tree. 
    */
    public int findTreeDepth() {
		return findHeight(root);
    }

    /**
    *Initialises dybder array and calls depths() and nodeDepths() methods.
    */
    public void depthArray() {
		dybder = new int[findHeight(root) + 1];
		depths(root, 0);
		nodeDepths();
    }

    /**
    *Fills dybder array with amount of nodes at each depth.
    *
    *@param node Current node being counted.
    *@param dybde Current node depth.
    */
    public void depths(Node node, int dybde) {
		if(dybder[dybde] == 0) {
		    dybder[dybde] = 1;
		}
		else {
		    int temp = dybder[dybde];
		    temp++;
		    dybder[dybde] = temp;
		}

		if(node.left != null) {
		    depths(node.left, dybde + 1);
		}

		if(node.right != null) {
		    depths(node.right, dybde + 1);
		}
    }

    /**
    *Prints out amount of nodes at each depth and average depth of nodes.
    */
    public void nodeDepths() {
		double totalDepth = 0;
		double totalNodes = 0;

		for(int i = 0; i < dybder.length; i++) {
		    totalDepth += i*dybder[i];
		    totalNodes += dybder[i];
		    System.out.println("Depth " + i + ": " + dybder[i]);
		}

		double average = totalDepth/totalNodes;
		System.out.format("\nAverage depth of nodes is %.1f\n", average);
    } 

    /**
    *Finds alphabetically first and last words in Dictionary.
    *
    *@return String containing first and last words in Dictionary. 
    */
    public String firstLast() {
		Node currentL = root;
		Node currentR = root;

		if(root == null) {
		    return "Dictionary is empty.";
		}

		while(currentL.left != null) {
		    currentL = currentL.left;
		}

		while(currentR.right != null) {
		    currentR = currentR.right;
		}

		String first = currentL.word;
		String last = currentR.word;

		return "First: " + first + ". Last: " + last + ".";
    }

    /**
    *Node that contains a String and two pointers to neigbour Nodes in binary search tree. 
    */
    private class Node {
		String word;
		Node left;
		Node right;

		public Node(String word) {
		    this.word = word;
		    this.left = null;
		    this.right = null;
		}
    }
}
