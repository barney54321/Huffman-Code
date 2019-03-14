public class Node {
	public Node myTop;
	public Node myBottom;
	public String character;
	public int frequency;
	public int value;
	public String code;
	
	public Node(Node n){
		this.myTop = n.myTop;
		this.myBottom = n.myBottom;
		this.character = n.character;
		this.frequency = n.frequency;
		this.value = n.value;
		this.code = null;
	}
	
	public Node(Node left, Node right, String ch, int freq) {
		this.myTop = left;
		this.myBottom = right;
		this.character = ch;
		this.frequency = freq;
		if (left == null){
			this.value = (int) ch.charAt(0);
		} else {
			if (left.value < right.value){
				this.value = left.value;
			} else {
				this.value = right.value;
			}
		}
		this.code = null;
	}
	
	public boolean isLeaf() {
		return myTop == null && myTop == null;
	}
	
	public boolean isSmaller(Node o){
		if (this.frequency < o.frequency){
			return true;
		} else if (this.frequency == o.frequency && this.value < o.value){
			return true;
		} else {
			return false;
		}
	}
	
	public void printCodes(String code){
		if (this.myTop==null){
			System.out.println("'"+this.character+"': "+code);
			this.code = code;
		} else {
			this.myTop.printCodes(code+"0");
			this.myBottom.printCodes(code+"1");
		}
	}
	
	public String getCode(String c){
		if (this.character.equals(c)){
			return this.code;
		} else if (this.myTop == null){
			return null;
		} else {
			String a = myTop.getCode(c);
			String b = myBottom.getCode(c);
			if (a != null){
				return a;
			} else if (b != null){
				return b;
			} else {
				return null;
			}
		}
	}
}
