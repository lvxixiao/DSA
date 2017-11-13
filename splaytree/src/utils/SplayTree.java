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
	//��e��Ϊx�����Ӳ���
	public BinNode<T> insertAsLc(BinNode<T> x,BinNode<T> e) {
		BinNode<T> lc = x.getLc();
		x.setLc(e);
		e.setParent(x);
		x.updateHeightAbove();
		return lc;
	}
	//��e��Ϊx���Һ��Ӳ���
	public BinNode<T> insertAsRc(BinNode<T> x,BinNode<T> e) {
		BinNode<T> rc = x.getRc();
		x.setRc(e);
		e.setParent(x);
		x.updateHeightAbove();
		return rc;
	}
	//�����������
	public ArrayList<BinNode<T>> travIn(BinNode<T> e) {
		return e.travIn();
	}
	//����
	public BinNode<T> insert(T e) {
		this.root = search(e);
		System.out.println("hot = " + this.hot.getData());
		//Ĭ�ϲ������ظ�Ԫ��
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
	//ɾ��
	public boolean remove(T e) {
		BinNode<T> x = search(e);
		if(x==null)
			return false;
		if( !x.hasLChild() )//���������Ϊ��
		{
			this.root = x.getRc();
			this.root.setParent(null);
			
			x.removeRc();
		} else if(!x.hasRChild()){ //���������Ϊ��
			this.root = x.getLc();
			this.root.setParent(null);
			
			x.removeLc();
		} else { //��������������
			//���������
			BinNode<T> ltree = x.getLc();
			x.removeLc();
			//���뱻ɾ���ڵ�
			this.root = x.getRc();
			this.root.setParent(null);
			/** �ٴ������ڵ㣬������x��ֱ�Ӻ���������ڵ�
			 * 	������ɺ������ڵ��������ض�Ϊnull
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
	 *  Splay����չ�㷨���ӽڵ�v���������չ
	 * 
	 * */
	private BinNode<T> splay(BinNode<T> v) {
		BinNode<T> p;
		BinNode<T> g;
		//˫����չ�����¶��Ϸ����� v��˫����չ
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
				//�ӻ�ԭ��
				if(gg.getLc().getData() == g.getData()) {
					gg.setLc(v);
				} else {
					gg.setRc(v);
				}
			} else {
				v.setParent(null);
			}
			//���¸߶�
			g.updateHeight();
			p.updateHeight();
			v.updateHeight();
		}//˫����չ����ʱ��g = null , p ��һ��Ϊnull
		//������չ
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
