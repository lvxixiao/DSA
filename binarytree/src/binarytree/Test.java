package binarytree;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		BinaryTree<String>  bt = new BinaryTree<String>();
		ArrayList<String> al;
		StringBuffer sb = new StringBuffer();
		String[] s = {"L1","T1","L2","T2","L2-1","T2-1"};
		String[] s1 = {"b","f","a","c","e"};
		String[] s2 = {"c","f","a","b","d","e"};
		bt.inserAsRoot("L0");
		bt.inserPre(bt.getRoot(),s);
		al = bt.travPre();
		for(int i = 0 ; i < al.size() ; i++) {
			sb.append(al.get(i)+",");
		}		
		System.out.println("先序遍历："+sb.toString());
		
		sb.delete(0,sb.length());
		
		al = bt.travLevel();
		for(int i = 0 ; i < al.size() ; i++) {
			sb.append(al.get(i)+",");
		}		
		System.out.println("层次遍历："+sb.toString());
		
		sb.delete(0,sb.length());
		
		bt.inserAsRoot("d");
		bt.inserPre(bt.getRoot(),s1);
		al = bt.travIn();
		for(int i = 0 ; i < al.size() ; i++) {
			sb.append(al.get(i)+",");
		}		
		System.out.println("中序遍历："+sb.toString());
		
		sb.delete(0,sb.length());
		
		bt.inserAsRoot("g");
		bt.inserPre(bt.getRoot(),s2);
		al = bt.travPost();
		for(int i = 0 ; i < al.size() ; i++) {
			sb.append(al.get(i)+",");
		}
		System.out.println("后序遍历："+sb.toString());
	}
}
