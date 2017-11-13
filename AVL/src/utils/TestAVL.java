package utils;

import java.util.ArrayList;

import org.junit.Test;

public class TestAVL {
	private AVL<Integer> tree = new AVL<Integer>();
	//测试高度
	@Test
	public void testHight() {
		BinNode<Integer> b = new BinNode<Integer>(16);
		BinNode<Integer> b1 = new BinNode<Integer>(15);
		BinNode<Integer> b2 = new BinNode<Integer>(13);
		BinNode<Integer> b3 = new BinNode<Integer>(12);
		BinNode<Integer> b4 = new BinNode<Integer>(17);
		BinNode<Integer> b5 = new BinNode<Integer>(18);
		ArrayList<BinNode<Integer>> list;
		tree.setRoot(b);
		tree.insertAsLc(b, b1);
		tree.insertAsLc(b1, b2);
		tree.insertAsLc(b2, b3);
		tree.insertAsRc(b2, b4);
		tree.insertAsRc(b4, b5);
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println("data ="+ binNode.getData() + "hight =" + binNode.getHight());
		}
	}
	@Test
	public void testConnect34() {
		BinNode<Integer> t0 = new BinNode<Integer>(0);
		BinNode<Integer> a = new BinNode<Integer>(1);
		BinNode<Integer> t1 = new BinNode<Integer>(2);
		BinNode<Integer> b = new BinNode<Integer>(3);
		BinNode<Integer> t2 = new BinNode<Integer>(4);
		BinNode<Integer> c = new BinNode<Integer>(5);
		BinNode<Integer> t3 = new BinNode<Integer>(6);
		ArrayList<BinNode<Integer>> list;
		
		tree.setRoot(a);
		tree.insertAsLc(a, t0);
		tree.insertAsRc(a, b);
		
		tree.insertAsLc(b, t1);
		tree.insertAsRc(b, c);
		
		tree.insertAsLc(c, t2);
		tree.insertAsRc(c, t3);
		System.out.println("插入完成");
		tree.connect34(a, b, c, t0, t1, t2, t3);
		list = tree.travIn(b);
		for (BinNode<Integer> binNode : list) {
			System.out.print("data = "+binNode.getData()+",");
		}
		System.out.println("");
		System.out.println("-----------------------");
		/**
		 * 重构后的根节点应该从a改为b
		 * */
		tree.setRoot(b);
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.print("data = "+binNode.getData()+",");
		}
	}
/*	@Test
 * zag-zag插入前							|	插入9
 * 			2(root,g)					|						5(root,p)
 * 		1				5(p)			|			2(g)				7(v)
 *	0			3				7(v)	|		1		3			6		8
 * 					4		6		8	|	0				4					9
 */
	@Test
	public void testInsert3() {
	    //zag-zag
		BinNode<Integer> t0_0 = new BinNode<Integer>(1);
		BinNode<Integer> g = new BinNode<Integer>(2);
		BinNode<Integer> t1_0 = new BinNode<Integer>(3);
		BinNode<Integer> t1_1 = new BinNode<Integer>(4);
		BinNode<Integer> p = new BinNode<Integer>(5);
		BinNode<Integer> t2 = new BinNode<Integer>(6);
		BinNode<Integer> v = new BinNode<Integer>(7);
		BinNode<Integer> t3 = new BinNode<Integer>(8);
		BinNode<Integer> t0_1 = new BinNode<Integer>(0);
		ArrayList<BinNode<Integer>> list;
		tree.setRoot(g);
		tree.insertAsLc(g, t0_0);
		tree.insertAsRc(g, p);
		
		tree.insertAsLc(t0_0, t0_1);
		
		tree.insertAsLc(p, t1_0);
		tree.insertAsRc(p, v);
		
		tree.insertAsRc(t1_0, t1_1);
		
		tree.insertAsLc(v, t2);
		tree.insertAsRc(v, t3);
		
		tree.insert(9);
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println("是否平衡:"+tree.avlBalanced(binNode)+" "+binNode.getData());
		}
	}
/*	@Test
 * zag-zig插入前							|插入6
 * 			2(root,g)					|					4(root,v)
 *		1					7(p)		|			2(g)				7(p)
 *	0				4(v)		8		|		1		3		5			8
 * 				3		5			9	|	0						6			9
 */
	@Test
	public void testInsert0() {
	    //zag-zig
		BinNode<Integer> t0_0 = new BinNode<Integer>(0);
		BinNode<Integer> t0_1 = new BinNode<Integer>(1);
		BinNode<Integer> g = new BinNode<Integer>(2);
		BinNode<Integer> t1 = new BinNode<Integer>(3);
		BinNode<Integer> v = new BinNode<Integer>(4);
		BinNode<Integer> t2 = new BinNode<Integer>(5);
		BinNode<Integer> p = new BinNode<Integer>(7);
		BinNode<Integer> t3_0 = new BinNode<Integer>(8);
		BinNode<Integer> t3_1 = new BinNode<Integer>(9);
		
		ArrayList<BinNode<Integer>> list;
		tree.setRoot(g);
		tree.insertAsLc(g, t0_1);
		tree.insertAsRc(g, p);
		
		tree.insertAsLc(t0_1,t0_0);
		
		tree.insertAsLc(p, v);
		tree.insertAsRc(p, t3_0);
		
		tree.insertAsLc(v, t1);
		tree.insertAsRc(v, t2);
		
		tree.insertAsRc(t3_0, t3_1);
		
		tree.insert(6);
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println("是否平衡:"+tree.avlBalanced(binNode)+" "+binNode.getData());
		}
	}
/*	@Test
 * zig-zig插入前							|插入0
 *							8(root,g)	|					4(root,p)
 *				4(p)			9		|			2(v)				8(g)
 *		2(v)		5				10	|		1		3		5			9
 *	1		3			6				|	0						6			10
 *
 */
	@Test
	public void testInsert1() {
	    //zig-zig
		BinNode<Integer> t0 = new BinNode<Integer>(1);
		BinNode<Integer> v = new BinNode<Integer>(2);
		BinNode<Integer> t1 = new BinNode<Integer>(3);
		BinNode<Integer> p = new BinNode<Integer>(4);
		BinNode<Integer> t2_0 = new BinNode<Integer>(5);
		BinNode<Integer> t2_1 = new BinNode<Integer>(6);
		BinNode<Integer> g = new BinNode<Integer>(8);
		BinNode<Integer> t3_0 = new BinNode<Integer>(9);
		BinNode<Integer> t3_1 = new BinNode<Integer>(10);
		
		ArrayList<BinNode<Integer>> list;
		tree.setRoot(g);
		tree.insertAsRc(g, t3_0);
		tree.insertAsLc(g, p);
		
		tree.insertAsRc(t3_0,t3_1);
		
		tree.insertAsLc(p, v);
		tree.insertAsRc(p, t2_0);
		
		tree.insertAsRc(t2_0, t2_1);
		
		tree.insertAsLc(v, t0);
		tree.insertAsRc(v, t1);		
		
		tree.insert(0);
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println("是否平衡:"+tree.avlBalanced(binNode)+" "+binNode.getData());
		}
	}
	@Test
	public void testCompareTo() {
		Integer i = new Integer(3);
		Integer y = new Integer(4);
		System.out.println(i.compareTo(y));
	}
	/**
	 * zig-zag插入前								|插入7
	 *							8(root,g)		|					5(root,v)
	 *			3(p)							|			3(p)				8(g)
	 *		2			5(v)		9			|		2		4		6			9
	 *	1			4		6			10		|	1						7			10
	 * 
	 */
	@Test
	public void testInsert2() {
	    //zig-zag
		BinNode<Integer> t0_0 = new BinNode<Integer>(1);
		BinNode<Integer> t0_1 = new BinNode<Integer>(2);
		BinNode<Integer> p = new BinNode<Integer>(3);
		BinNode<Integer> t1 = new BinNode<Integer>(4);
		BinNode<Integer> v = new BinNode<Integer>(5);
		BinNode<Integer> t2 = new BinNode<Integer>(6);
		BinNode<Integer> g = new BinNode<Integer>(8);
		BinNode<Integer> t3_0 = new BinNode<Integer>(9);
		BinNode<Integer> t3_1 = new BinNode<Integer>(10);
		
		ArrayList<BinNode<Integer>> list;
		tree.setRoot(g);
		tree.insertAsRc(g, t3_0);
		tree.insertAsLc(g, p);
		
		tree.insertAsRc(t3_0,t3_1);
		
		tree.insertAsLc(p, t0_1);
		tree.insertAsRc(p, v);
		
		tree.insertAsLc(t0_1, t0_0);
		
		tree.insertAsLc(v, t1);
		tree.insertAsRc(v, t2);		
		
		tree.insert(7);
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println("是否平衡:"+tree.avlBalanced(binNode)+" "+binNode.getData());
		}
	}
	
/*	@Test
	public void testZig() {
		BinNode<Integer> v = new BinNode<Integer>(0);
		BinNode<Integer> c = new BinNode<Integer>(1);
		BinNode<Integer> z = new BinNode<Integer>(2);
		BinNode<Integer> x = new BinNode<Integer>(3);
		BinNode<Integer> y = new BinNode<Integer>(4);
		ArrayList<BinNode<Integer>> list;
		tree.setRoot(v);
		tree.insertAsLc(v, c);
		tree.insertAsRc(v, z);
		tree.insertAsLc(c, x);
		tree.insertAsRc(c, y);
		tree.zig(v);
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println("data ="+ binNode.getData());
		}
	}
	@Test
	public void testZag() {
		BinNode<Integer> v = new BinNode<Integer>(0);
		BinNode<Integer> c = new BinNode<Integer>(1);
		BinNode<Integer> z = new BinNode<Integer>(2);
		BinNode<Integer> x = new BinNode<Integer>(3);
		BinNode<Integer> y = new BinNode<Integer>(4);
		ArrayList<BinNode<Integer>> list;
		tree.setRoot(v);
		tree.insertAsLc(v, x);
		tree.insertAsRc(v, c);
		tree.insertAsLc(c, y);
		tree.insertAsRc(c, z);
		tree.zag(v);
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println("data ="+ binNode.getData());
		}
	}*/
	/*	
	 * zig-zig删除前							|删除9
	 *							7(root,g)	|					4(root,p)
	 *				4(p)			8		|			2(v)				7(g)
	 *		2(v)		5				9	|		1		3		5			8
	 *	1		3			6				|	0						6			
	 */
	@Test
	public void testRemove0() {
	    //zig-zig
		BinNode<Integer> t0 = new BinNode<Integer>(1);
		BinNode<Integer> v = new BinNode<Integer>(2);
		BinNode<Integer> t1 = new BinNode<Integer>(3);
		BinNode<Integer> p = new BinNode<Integer>(4);
		BinNode<Integer> t2_0 = new BinNode<Integer>(5);
		BinNode<Integer> t2_1 = new BinNode<Integer>(6);
		BinNode<Integer> g = new BinNode<Integer>(7);
		BinNode<Integer> t3_0 = new BinNode<Integer>(8);
		BinNode<Integer> t3_1 = new BinNode<Integer>(9);
		
		ArrayList<BinNode<Integer>> list;
		tree.setRoot(g);
		tree.insertAsRc(g, t3_0);
		tree.insertAsLc(g, p);
		
		tree.insertAsRc(t3_0,t3_1);
		
		tree.insertAsLc(p, v);
		tree.insertAsRc(p, t2_0);
		
		tree.insertAsRc(t2_0, t2_1);
		
		tree.insertAsLc(v, t0);
		tree.insertAsRc(v, t1);		
		
		tree.remove(9);
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println("是否平衡:"+tree.avlBalanced(binNode)+" "+binNode.getData());
		}
		System.out.println("root = "+ tree.getRoot().getData());
	}
	@Test
	public void testRemove1() {
	    //zag-zag
		BinNode<Integer> t0_0 = new BinNode<Integer>(1);
		BinNode<Integer> t0 = new BinNode<Integer>(2);
		BinNode<Integer> g = new BinNode<Integer>(3);
		BinNode<Integer> t2_0 = new BinNode<Integer>(4);
		BinNode<Integer> t2 = new BinNode<Integer>(5);
		BinNode<Integer> t2_1 = new BinNode<Integer>(6);
		BinNode<Integer> p = new BinNode<Integer>(7);
		BinNode<Integer> v = new BinNode<Integer>(8);
		BinNode<Integer> t3 = new BinNode<Integer>(9);
		
		ArrayList<BinNode<Integer>> list;
		tree.setRoot(g);
		tree.insertAsRc(g, p);
		tree.insertAsLc(g, t0);
		
		tree.insertAsLc(t0,t0_0);
		
		tree.insertAsLc(p, t2);
		tree.insertAsRc(p, v);
		
		tree.insertAsRc(v, t3);
		
		tree.insertAsLc(t2, t2_0);
		tree.insertAsRc(t2, t2_1);		
		
		tree.remove(1);
		
		list = tree.travIn(tree.getRoot());
		for (BinNode<Integer> binNode : list) {
			System.out.println("是否平衡:"+tree.avlBalanced(binNode)+" "+binNode.getData());
		}
	}
}
