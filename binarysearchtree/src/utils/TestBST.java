package utils;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestBST {
	private BinarySearchTree<Integer> tree;
	
	@Before
	public void testTree() {
		BinNode<Integer> node = null;
		BinNode<Integer> nodelc = null;
		BinNode<Integer> noderc = null;
		this.tree = new BinarySearchTree<Integer>(16);
		
		node = tree.getRoot();
		node.setLc(10);
		node.setRc(25);
		
		node = node.getLc();
		node.setLc(5);
		node.setRc(11);
		nodelc = node.getLc();
		noderc = node.getRc();
		nodelc.setLc(2);
		nodelc.setRc(8);
		noderc.setRc(15);
		
		node = tree.getRoot().getRc();
		node.setLc(19);
		node.setRc(28);
		nodelc = node.getLc();
		noderc = node.getRc();
		nodelc.setLc(17);
		nodelc.setRc(22);
		noderc.setLc(27);
		noderc.setRc(37);
		System.out.println("数据准备完成");
	}
	
	
	@Test
	public void testTravIn() {
		ArrayList<Integer> arr = tree.travIn(tree.getRoot());
		System.out.println(arr.toString());
	}
	
	@Test
	public void testSearch() {
		int e = 26;
		BinNode<Integer> v = null;
		v = tree.search(e);
		System.out.println("hot = "+tree.getHot().getData());
		System.out.println(v == null);
	}
	
	@Test
	public void testInsert() {
		int e = 23;
		tree.insert(e);
		tree.insert(55);
		tree.insert(1);
		ArrayList<Integer> arr = tree.travIn(tree.getRoot());
		System.out.println(arr.toString());
	}
	
	@Test
	public void testSucc() {
		ArrayList<Integer> arr = tree.travIn(tree.getRoot());
		System.out.println(arr.toString());
		BinNode<Integer> root = tree.getRoot();
		System.out.println(root.succ().getData());
	}
	@Test
	public void textRemove() {
		ArrayList<Integer> arr = tree.travIn(tree.getRoot());
		System.out.println(arr.toString());
		tree.remove(8);
		ArrayList<Integer> arr2 = tree.travIn(tree.getRoot());
		System.out.println(arr2.toString());
	}
}
