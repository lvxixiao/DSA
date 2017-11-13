package util;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * ���ӱ�ʵ������ͼ
 * 
 * */

public class Graph<T> implements GraphInterface<T>{
	private ArrayList<Vertex<T>> Vertexs;
	private int clock;
	public Graph() {
		Vertexs = new ArrayList<Vertex<T>>();
	}

	
	public ArrayList<Vertex<T>> getVertexs() {
		return Vertexs;
	}

	@Override
	public int e() {
		int num = 0;
		for(Vertex<T> v: Vertexs) {
			num = num + v.getEdgeList().size();			
		}
		return num;
	}

	@Override
	public void insert(Vertex<T> v, Vertex<T> u) {
		LinkedList<Edge<T>> ll = v.getEdgeList();
		ll.add(new Edge<T>(v,u,0));
	}

	@Override
	public void remove(Vertex<T> v, Vertex<T> u) {
		LinkedList<Edge<T>> ll = v.getEdgeList();
		ll.remove(new Edge<T>(v,u,0));
	}

	@Override
	public void insert(Vertex<T> v) {
		Vertexs.add(v);
		
	}

	@Override
	public void remove(Vertex<T> v) {
		Vertexs.remove(v);
		
	}
	//����V���׸��ڽӶ���
	@Override
	public Vertex<T> firstNbr(Vertex<T> v) {
		return v.getEdgeList().getFirst().getEndVertex();
	}
	//����V���ڽӶ����У�U�ĺ��
	@Override
	public Vertex<T> nextNbr(Vertex<T> v,Vertex<T> u) {
		LinkedList<Edge<T>> ll = v.getEdgeList();
		Edge<T> e = new Edge<T>(v,u,0);
		int i = ll.indexOf(e);
		if(i == -1)
			return null;
		else
			return ll.get(++i).getEndVertex();
	}

	//�������������ȫͼ��
	public void bfs(Vertex<T> v) {
		int i = 0;
		clock = 0;			
		do {
			if(v.getStatus() == VStatus.UNDISCOVEREN)
				BFS(v);
			v = Vertexs.get(i++);
		} while (i < Vertexs.size());					
	}
	//�������������������ͨ��
	public int BFS(Vertex<T> v) {
		Vertex<T> u = null;
		LinkedList<Edge<T>> ll = null;
		Edge<T> e = null;
		//���븨������
		LinkedList<Vertex<T>> q = new LinkedList<Vertex<T>>();
		//��ʼ�����
		q.add(v);
		v.setStatus(VStatus.DISCOVEREN);
		while(!q.isEmpty()) {
			v = q.poll();
			v.setdTime(++clock);
			ll = v.getEdgeList();
			//ö��v�������ھ�
			while(!ll.isEmpty()) {
				e = ll.poll();
				u = e.getEndVertex();
				if(u.getStatus() == VStatus.UNDISCOVEREN) {
					u.setStatus(VStatus.DISCOVEREN);
					q.add(u);
					System.out.println(u.getLabel().toString());
					e.setType(EType.TREE);
				} else {
					e.setType(EType.CROSS);
				}
			}
			v.setStatus(VStatus.VISITED);
		}				
		return clock;
	}
	//�����������
	public void dfs(Vertex<T> v) {
		int i = 0;
		clock = 0;			
		do {
			if(v.getStatus() == VStatus.UNDISCOVEREN)
				DFS(v);
			v = Vertexs.get(i++);
		} while (i < Vertexs.size());
	}


	private int DFS(Vertex<T> v) {
		
		Vertex<T> u = null;
		Edge<T> e = null;
		LinkedList<Edge<T>> edges = v.getEdgeList();
		
		v.setdTime(++clock);		
		v.setStatus(VStatus.DISCOVEREN);
				
		while(!edges.isEmpty()) {
			e = edges.poll();
			u = e.getEndVertex(); 
			switch (u.getStatus()) {
				case UNDISCOVEREN:
					e.setType(EType.TREE);
					DFS(u);
					break;
				case DISCOVEREN:
					e.setType(EType.BACKWARD);
					break;
				default:
					if(v.getdTime()<u.getdTime()) {
						e.setType(EType.FORWARD);
					} else {
						e.setType(EType.CROSS);
					}
					break;
			}
		}		
		v.setStatus(VStatus.VISITED);
		v.setfTime(++clock);
		System.out.println(v.getLabel().toString() +" dTime = "+ v.getdTime()+" fTime = "+v.getfTime());
		return clock;
	}
	
}
