package uva;

import java.util.HashMap;
import java.util.Stack;

public class LeetCode {
	
	public static void main(String [] args) {
		LeetCode o= new LeetCode();
//		System.out.println(new LeetCode().isValid("([]"));
//		ListNode h1;
//		ListNode n1 = new ListNode(3);
//		h1 = n1;
//		n1.next = new ListNode(4);
//		n1 = n1.next;
//		n1.next = new ListNode(8);
//		n1 = n1.next;
//		n1.next = new ListNode(9);
//		n1 = n1.next;
//		n1.next = new ListNode(13);
//		
//		ListNode h2;
//		ListNode n2 = new ListNode(2);
//		h2 = n2;
//		n2.next = new ListNode(7);
//		n2 = n2.next;
//		n2.next = new ListNode(12);
//		
//		o.print(h1);
//		o.print(h2);
//		o.print(o.mergeTwoLists(h1, h2));
		int [] ar = {1,1,2,2,4};
		int val = o.removeDuplicates(ar);
		System.out.println(val);
		for (int i : ar) {
			System.out.print(i+"\t");	
		}
		
		
	} 
	
	
	//13 start
	private static final HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	
	static{
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
	}
	
	public int romanToInt(String s) {
        int val = 0;
        int cursor = 0;
        char[] ch = s.toCharArray();
        for (int i = ch.length-1; i>=0; i--) {
        	int currentVal = map.get(ch[i]);
        	if(currentVal < cursor) {
        		val -= currentVal;
        	} else {
        		val += currentVal;
        	}
			cursor = currentVal;
		}
        return val;
    }
	//13 ends
	
	//20 starts
	
	
	 public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		
		char [] ch = s.toCharArray();
	    for(int i = 0; i<ch.length; i++) {
	    	switch(ch[i]) {
	    	case '(' :
	    		stack.push(')');
	    		break;
	    	case '[' :
	    		stack.push(']');
	    		break;
	    	case '{' :
	    		stack.push('}');
	    		break;
	    	default:
	    		try{
	    			char head = stack.pop();
	    			if(head != ch[i]){
	    				return false;
	    			} else {
	    				
	    			}

	    		}catch (Exception e) {
	    			return false; 
				}
	    		break;
	   		
	    	}
	    	
	    }
	    return stack.empty();
	 }
	//20 ends
	 
	//21 start
	 
	 ListNode mergeTwoLists(ListNode n1, ListNode n2) {

		if(null == n1) return n2;
		if(null == n2) return n1;

		ListNode n3,h;
		if(n1.val < n2.val) {
			n3 = h = n1;
			n1 = n1.next;
		} else {
			n3 = h = n2;
			n2 = n2.next;
		}
		
		while(null != n1 && null != n2) {
			if(n1.val < n2.val) {
				n3.next = n1;
				n1 = n1.next;
			} else {
				n3.next = n2;
				n2 = n2.next;
			}
			n3 = n3.next;
		}
		if(n1 != null ) {
			n3.next = n1;
		} else if(null != n2){
			n3.next = n2;
		}
		
		return h;
	 }
	 
	 void print(ListNode n) {
		 while(null != n) {
			 System.out.print(n.val+"->");
			 n = n.next;
		 }
		 System.out.println();
	 }
	 //21 ends
	 
	 //23 starts
	 public int removeDuplicates(int[] nums) {
		 if(nums.length == 0) return 0;
		 int ans = 1;
		 int l1 = 0;
		 int val = nums[0];
		 for(int i = 1; i<nums.length; i++) {
			 if(val != nums[i]) {
				 ans++;
				 val = nums[i];
				 //int temp = nums[l1+1];
				 nums[l1+1] = nums[i];
				// nums[i] = temp;
				 l1 ++;
			 } 
		 }
		 
		 return ans;
	 }
}

class ListNode{
	int val;
	ListNode next;
	public ListNode(int v) {val = v;}
}