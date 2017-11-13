package utils;

import java.util.ArrayList;

import org.junit.Test;

public class TestSplayTree {
	private SplayTree<Integer> tree = new SplayTree<Integer>();
	
	/**
	 * 查找前树结构					| 调用search(6)之后的树结构
	 *		2 						| 						6
	 *	1			4 				|				4			7
	 *			3			6 		|		2			5	
	 *					5		7	|	1		3
	 * */
	@Test
	public void testsearch0() {
		BinNode<Integer> z = new BinNode<Integer>(1);
		BinNode<Integer> g = new BinNode<Integer>(2);
		BinNode<Integer> y = new BinNode<Integer>(3);
		BinNode<Integer> p = new BinNode<Integer>(4);
		BinNode<Integer> w = new BinNode<Integer>(5);
		BinNode<Integer> v = new BinNode<Integer>(6);
		BinNode<Integer> x = new BinNode<Integer>(7);
		ArrayList<BinNode<Integer>> list;
		
		tree.setRoot(g);
		tree.insertAsLc(g, z);
		tree.insertAsRc(g, p);
		
		tree.insertAsLc(p, y);
		tree.insertAsRc(p, v);
		
		tree.insertAsLc(v, w);
		tree.insertAsRc(v, x);
		
		tree.search(6);
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println(binNode.getData());
		}
		
	}
	/**
	 * 查找前树结构					| 调用search(2)
	 *						6		|		2
	 *				4			7 	|	1			4
	 *		2			5			|			3			6
	 *	1		3 					|					5		7
	 * */
	@Test
	public void testsearch1() {
		BinNode<Integer> w = new BinNode<Integer>(1);
		BinNode<Integer> v = new BinNode<Integer>(2);
		BinNode<Integer> x = new BinNode<Integer>(3);
		BinNode<Integer> p = new BinNode<Integer>(4);
		BinNode<Integer> y = new BinNode<Integer>(5);
		BinNode<Integer> g = new BinNode<Integer>(6);
		BinNode<Integer> z = new BinNode<Integer>(7);
		ArrayList<BinNode<Integer>> list;
		
		tree.setRoot(g);
		tree.insertAsLc(g, p);
		tree.insertAsRc(g, z);
		
		tree.insertAsLc(p, v);
		tree.insertAsRc(p, y);
		
		tree.insertAsLc(v, w);
		tree.insertAsRc(v, x);
		
		tree.search(2);
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println(binNode.getData());
		}
		
	}
	/**
	 * 查找前树结构					| 调用search(4)之后的树结构
	 *						6		|				4
	 *		2					7	|		2				6
	 *	1			4				|	1		3		5		7
	 *			3		5			|
	 * */
	@Test
	public void testsearch2() {
		BinNode<Integer> z = new BinNode<Integer>(1);
		BinNode<Integer> p = new BinNode<Integer>(2);
		BinNode<Integer> x = new BinNode<Integer>(3);
		BinNode<Integer> v = new BinNode<Integer>(4);
		BinNode<Integer> y = new BinNode<Integer>(5);
		BinNode<Integer> g = new BinNode<Integer>(6);
		BinNode<Integer> w = new BinNode<Integer>(7);
		ArrayList<BinNode<Integer>> list;
		
		tree.setRoot(g);
		tree.insertAsLc(g, p);
		tree.insertAsRc(g, w);
		
		tree.insertAsLc(p, z);
		tree.insertAsRc(p, v);
		
		tree.insertAsLc(v, x);
		tree.insertAsRc(v, y);
		
		tree.search(4);
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println(binNode.getData());
		}	
	}
	/**
	 * 查找之前树结构					| 调用search(4)之后
	 *	 	2						|				4
	 *	1					6		|		2				6
	 *				4			7	|	1		3		5		7
	 *			3		5			|
	 * */
	@Test
	public void testsearch3() {
		BinNode<Integer> w = new BinNode<Integer>(1);
		BinNode<Integer> g = new BinNode<Integer>(2);
		BinNode<Integer> x = new BinNode<Integer>(3);
		BinNode<Integer> v = new BinNode<Integer>(4);
		BinNode<Integer> y = new BinNode<Integer>(5);
		BinNode<Integer> p = new BinNode<Integer>(6);
		BinNode<Integer> z = new BinNode<Integer>(7);
		ArrayList<BinNode<Integer>> list;
		
		tree.setRoot(g);
		tree.insertAsLc(g, w);
		tree.insertAsRc(g, p);
		
		tree.insertAsLc(p, v);
		tree.insertAsRc(p, z);
		
		tree.insertAsLc(v, x);
		tree.insertAsRc(v, y);
		
		tree.search(4); 
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println(binNode.getData());
		}	
	}
	/**
	 * 查找之前树结构			| 调用search(2)之后
	 * 				4		|		2
	 *		2			5	|	1 			4
	 *	1		3 			|			3		5
	 * */
	@Test
	public void testsearch4() {
		BinNode<Integer> x = new BinNode<Integer>(1);
		BinNode<Integer> v = new BinNode<Integer>(2);
		BinNode<Integer> y = new BinNode<Integer>(3);
		BinNode<Integer> r = new BinNode<Integer>(4);
		BinNode<Integer> z = new BinNode<Integer>(5);
		ArrayList<BinNode<Integer>> list;
		
		tree.setRoot(r);
		tree.insertAsLc(r, v);
		tree.insertAsRc(r, z);
		
		tree.insertAsLc(v, x);
		tree.insertAsRc(v, y);
		
		tree.search(2); 
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println(binNode.getData());
		}	
	}
	/**
	 * 查找之前树结构			|	调用search(4)之后
	 *		2 				|				4
	 *	1		 	4		|		2			5
	 * 			3		5	|	1		3
	 * */
	@Test
	public void testsearch5() {
		BinNode<Integer> x = new BinNode<Integer>(1);
		BinNode<Integer> r = new BinNode<Integer>(2);
		BinNode<Integer> y = new BinNode<Integer>(3);
		BinNode<Integer> v = new BinNode<Integer>(4);
		BinNode<Integer> z = new BinNode<Integer>(5);
		ArrayList<BinNode<Integer>> list;
		
		tree.setRoot(r);
		tree.insertAsLc(r, x);
		tree.insertAsRc(r, v);
		
		tree.insertAsLc(v, y);
		tree.insertAsRc(v, z);
		
		tree.search(4); 
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println(binNode.getData());
		}	
	}
	/**
	 * 插入前树结构					| 调用insert(6)
	 *						7		|						6
	 *				4			8 	|					5		7
	 *		2			5			|				4				8	
	 *	1		3 					|		2						
	 *								|	1		3
	 * */
	@Test
	public void testInsert0() {
		BinNode<Integer> w = new BinNode<Integer>(1);
		BinNode<Integer> v = new BinNode<Integer>(2);
		BinNode<Integer> x = new BinNode<Integer>(3);
		BinNode<Integer> p = new BinNode<Integer>(4);
		BinNode<Integer> y = new BinNode<Integer>(5);
		BinNode<Integer> g = new BinNode<Integer>(7);
		BinNode<Integer> z = new BinNode<Integer>(8);
		ArrayList<BinNode<Integer>> list;
		
		tree.setRoot(g);
		tree.insertAsLc(g, p);
		tree.insertAsRc(g, z);
		
		tree.insertAsLc(p, v);
		tree.insertAsRc(p, y);
		
		tree.insertAsLc(v, w);
		tree.insertAsRc(v, x);
		
		tree.insert(6);
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println(binNode.getData());
		}
	}
	/**
	 * 插入前树结构					| 调用insert(4)
	 *						6		|					4		
	 *				3			7 	|				3		5						
	 *		1			5			|							6			
	 *	0		2 					|		1						7		
	 *								|	0		2		
	 * */
	@Test
	public void testInsert1() {
		BinNode<Integer> w = new BinNode<Integer>(0);
		BinNode<Integer> v = new BinNode<Integer>(1);
		BinNode<Integer> x = new BinNode<Integer>(2);
		BinNode<Integer> p = new BinNode<Integer>(3);
		BinNode<Integer> y = new BinNode<Integer>(5);
		BinNode<Integer> g = new BinNode<Integer>(6);
		BinNode<Integer> z = new BinNode<Integer>(7);
		ArrayList<BinNode<Integer>> list;
		
		tree.setRoot(g);
		tree.insertAsLc(g, p);
		tree.insertAsRc(g, z);
		
		tree.insertAsLc(p, v);
		tree.insertAsRc(p, y);
		
		tree.insertAsLc(v, w);
		tree.insertAsRc(v, x);
		
		tree.insert(4);
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println(binNode.getData());
		}
	}
	/**
	 * 删除前树结构					| 调用remove(0)
	 *						6		|					6			
	 *				3			7 	|	1					7							
	 *		1			5			|			3								
	 *	0		2 					|		2		5								
	 *								|					
	 * */
	@Test
	public void testRemove0() {
		BinNode<Integer> w = new BinNode<Integer>(0);
		BinNode<Integer> v = new BinNode<Integer>(1);
		BinNode<Integer> x = new BinNode<Integer>(2);
		BinNode<Integer> p = new BinNode<Integer>(3);
		BinNode<Integer> y = new BinNode<Integer>(5);
		BinNode<Integer> g = new BinNode<Integer>(6);
		BinNode<Integer> z = new BinNode<Integer>(7);
		ArrayList<BinNode<Integer>> list;
		
		tree.setRoot(g);
		tree.insertAsLc(g, p);
		tree.insertAsRc(g, z);
		
		tree.insertAsLc(p, v);
		tree.insertAsRc(p, y);
		
		tree.insertAsLc(v, w);
		tree.insertAsRc(v, x);
		
		tree.remove(0);
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println(binNode.getData());
		}
		System.out.println("tree.root = " + tree.getRoot().getData());
	}
	/**
	 * 查找前树结构					| 调用remove(7)之后的树结构
	 *		2 						| 		2					
	 *	1			4 				|	1					6		
	 *			3			6 		|				4				
	 *					5		7	|			3		5
	 * */
	@Test
	public void testRemove1() {
		BinNode<Integer> z = new BinNode<Integer>(1);
		BinNode<Integer> g = new BinNode<Integer>(2);
		BinNode<Integer> y = new BinNode<Integer>(3);
		BinNode<Integer> p = new BinNode<Integer>(4);
		BinNode<Integer> w = new BinNode<Integer>(5);
		BinNode<Integer> v = new BinNode<Integer>(6);
		BinNode<Integer> x = new BinNode<Integer>(7);
		ArrayList<BinNode<Integer>> list;
		
		tree.setRoot(g);
		tree.insertAsLc(g, z);
		tree.insertAsRc(g, p);
		
		tree.insertAsLc(p, y);
		tree.insertAsRc(p, v);
		
		tree.insertAsLc(v, w);
		tree.insertAsRc(v, x);
		
		tree.remove(7);
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println(binNode.getData());
		}		
	}
	/**
	 * 查找前树结构					| 调用remove(4)之后的树结构
	 *		2 						| 							
	 *	1			4 				|								
	 *			3			6 		|								
	 *					5		7	|					
	 * */
	@Test
	public void testRemove2() {
		BinNode<Integer> z = new BinNode<Integer>(1);
		BinNode<Integer> g = new BinNode<Integer>(2);
		BinNode<Integer> y = new BinNode<Integer>(3);
		BinNode<Integer> p = new BinNode<Integer>(4);
		BinNode<Integer> w = new BinNode<Integer>(5);
		BinNode<Integer> v = new BinNode<Integer>(6);
		BinNode<Integer> x = new BinNode<Integer>(7);
		ArrayList<BinNode<Integer>> list;
		
		tree.setRoot(g);
		tree.insertAsLc(g, z);
		tree.insertAsRc(g, p);
		
		tree.insertAsLc(p, y);
		tree.insertAsRc(p, v);
		
		tree.insertAsLc(v, w);
		tree.insertAsRc(v, x);
		
		tree.remove(4);
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println(binNode.getData());
		}		
	}
}
