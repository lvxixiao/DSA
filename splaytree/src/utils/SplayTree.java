package utils;

import java.util.ArrayList;

public class SplayTree<T extends Comparable<T>> {
	private BinNode<T> root;
	private BinNode<T> hot;
	
	public BinNode<T> getHot() {
		return hot;
	}
	public void setHot(BinNode<T> hot) {
		this.hot = hot;
	}
	public SplayTree() {
		root = null;
	}
	public SplayTree(BinNode<T> root) {
		this.root = root;
	}
	public SplayTree(T e) {
		root = new BinNode<T>(e,null,null,null);
	}
	public BinNode<T> getRoot() {
		return root;
	}
	public void setRoot(BinNode<T> root) {
		this.root = root;
	}
	//将e作为x的左孩子插入
	public BinNode<T> insertAsLc(BinNode<T> x,BinNode<T> e) {
		BinNode<T> lc = x.getLc();
		x.setLc(e);
		e.setParent(x);
		x.updateHeightAbove();
		return lc;
	}
	//将e作为x的右孩子插入
	public BinNode<T> insertAsRc(BinNode<T> x,BinNode<T> e) {
		BinNode<T> rc = x.getRc();
		x.setRc(e);
		e.setParent(x);
		x.updateHeightAbove();
		return rc;
	}
	//子树中序遍历
	public ArrayList<BinNode<T>> travIn(BinNode<T> e) {
		return e.travIn();
	}
	//插入
	public BinNode<T> insert(T e) {
		this.root = search(e);
		System.out.println("hot = " + this.hot.getData());
		//默认不存在重复元素
		if(this.root.getData().compareTo(e) == 0) 
			return this.root;
		BinNode<T> t = new BinNode<T>(e,null,null,null);
		if(this.root.getData().compareTo(e) < 0) {
			t.setRc(this.root.getRc());
			this.root.removeRc();
			t.setLc(this.root);
		} else {
			System.out.println("hot < e");
			t.setLc(this.root.getLc());
			this.root.removeLc();
			t.setRc(this.root);
		}
		this.root = t;
		this.root.updateHeight();
		return t;
	}
	//删除
	public boolean remove(T e) {
		BinNode<T> x = search(e);
		if(x==null)
			return false;
		if( !x.hasLChild() )//如果左子树为空
		{
			this.root = x.getRc();
			this.root.setParent(null);
			
			x.removeRc();
		} else if(!x.hasRChild()){ //如果右子树为空
			this.root = x.getLc();
			this.root.setParent(null);
			
			x.removeLc();
		} else { //左右子树都存在
			//拆分左子树
			BinNode<T> ltree = x.getLc();
			x.removeLc();
			//隔离被删除节点
			this.root = x.getRc();
			this.root.setParent(null);
			/** 再次搜索节点，将树中x的直接后继推至根节点
			 * 	搜索完成后树根节点左子树必定为null
			 */
			x = search(e);
			x.setLc(ltree);
		}
		return true;
	}	
	public BinNode<T> search(T e) {
		BinNode<T> p = searchIn(root,e);
		if( p != null) {
			this.root = splay(p);
		} else {
			this.root = splay(this.hot);
		}
		return this.root;
	}
	/**
	 *  Splay树伸展算法：从节点v触发逐层伸展
	 * 
	 * */
	private BinNode<T> splay(BinNode<T> v) {
		BinNode<T> p;
		BinNode<T> g;
		//双层伸展，自下而上反复对 v做双层伸展
		while(((p = v.getParent()) !=null ) && ((g = p.getParent())!=null)) {
			BinNode<T> gg = g.getParent();
			if(p.isRChild()) { //zag
				if(v.isRChild()) { //zag-zag
					g.setRc(p.getLc());
					p.setLc(g);
					p.setRc(v.getLc());
					v.setLc(p);
				} else { //zig-zag
					p.setLc(v.getRc());
					v.setRc(p);
					g.setRc(v.getLc());
					v.setLc(g);
				}
			} else { //zig
				if(v.isRChild()) { //zag-zig
					p.setRc(v.getLc());
					v.setLc(p);
					g.setLc(v.getRc());
					v.setRc(g);
				} else { //zig-zig
					g.setLc(p.getRc());
					p.setRc(g);
					p.setLc(v.getRc());
					v.setRc(p);
				}
			}
			if(gg != null) {
				//接回原树
				if(gg.getLc().getData() == g.getData()) {
					gg.setLc(v);
				} else {
					gg.setRc(v);
				}
			} else {
				v.setParent(null);
			}
			//更新高度
			g.updateHeight();
			p.updateHeight();
			v.updateHeight();
		}//双层伸展结束时，g = null , p 不一定为null
		//单层伸展
		if( (p = v.getParent()) != null) {
			if(v.isRChild()) { //zag
				p.setRc(v.getLc());
				v.setLc(p);
			} else { //zig
				p.setLc(v.getRc());
				v.setRc(p);
			}
			p.updateHeight();
			v.updateHeight();
		}
		v.setParent(null);
		return v;
	}
	private BinNode<T> searchIn(BinNode<T> v, T e) {
		if(v==null || v.getData()==e)
			return v;
		this.hot = v;
		return v.getData().compareTo(e)<0?searchIn(v.getRc(), e):searchIn(v.getLc(),e);
	}
}
