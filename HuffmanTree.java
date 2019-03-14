import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.Iterator;

public class HuffmanTree {
	
	public static HashMap<String, Integer> frequencyCount(String line) {
		HashMap<String, Integer> binaryFrequency = new HashMap<String, Integer>();
		
		int i = 0;
		while (i < line.length()) { 
			String binary = line.charAt(i) + "";
			
			if (binaryFrequency.containsKey(binary)){
				binaryFrequency.put(binary, binaryFrequency.get(binary) + 1);
			} else {
				binaryFrequency.put(binary, 1);
			}
		}
		return binaryFrequency;
	}
	
	public static void main(String[] args){
		Scanner stdin = new Scanner(System.in);
		System.out.print("Enter message: ");
		String message = stdin.nextLine();
		System.out.println();
		stdin.close();
		
		HashMap<String,Integer> a = new HashMap<String,Integer>();
		ArrayList<String> b = new ArrayList<String>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		ArrayList<Node> original = new ArrayList<Node>();
		int i = 0;
		int count = 0;
		while (i<message.length()){
			String c = message.charAt(i)+"";
			if (!a.containsKey(c)){
				a.put(c,1);
				b.add(c);
				count++;
			} else {
				a.put(c,a.get(c)+1);
			}
			i++;
		}
		
		
		i = 0;
		while (i < b.size()){
			String s = b.get(i);
			original.add(new Node(null,null,s,a.get(s)));
			nodes.add(new Node(null,null,s,a.get(s)));
			i++;
		}
		
		Node finalNode;
		
		// Find two smallest, make a new node, delete those old nodes
		while (nodes.size()>2){
			Node smallest = new Node(nodes.get(0));
			int sm = 0;
			Node second = new Node(nodes.get(1));
			int se = 1;
			if (second.isSmaller(smallest)){
				Node temp = new Node(second);
				second = new Node(smallest);
				se = 0;
				smallest = new Node(temp);
				sm = 1;
			}
			
			i = 0;
			// Iterate through list to find smallest two nodes
			while (i < nodes.size()){
				if (i != sm && i != se){
					if (nodes.get(i).isSmaller(smallest)){
						second = new Node(smallest);
						smallest = new Node(nodes.get(i));
						se = sm;
						sm = i;
					} else if (nodes.get(i).isSmaller(second)){
						second = new Node(nodes.get(i));
						se = i;
					}
				}
				i++;
			}
			
			// Combine two smallest nodes
			Node com = new Node(second,smallest,"**",(smallest.frequency+second.frequency));
			if (se < sm){
				nodes.remove(sm);
				nodes.remove(se);
			} else {
				nodes.remove(se);
				nodes.remove(sm);
			}
			nodes.add(com);
		}
		
		if (nodes.get(0).isSmaller(nodes.get(1))){
			finalNode = new Node(nodes.get(1),nodes.get(0),"**",(nodes.get(0).frequency+nodes.get(1).frequency));
		} else {
			finalNode = new Node(nodes.get(0),nodes.get(1),"**",(nodes.get(0).frequency+nodes.get(1).frequency));
		}
		
		finalNode.printCodes("");
		
		i = 0;
		while (i < message.length()){
			String c = message.charAt(i) + "";
			System.out.print(finalNode.getCode(c));
			i++;
		}
		System.out.println();
	}
}
