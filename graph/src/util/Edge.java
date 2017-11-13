package util;

public class Edge<T> {
	//�ߵĶ���U
	private Vertex<T> v;
	//�ߵ��յ�V
	private Vertex<T> u;
	//�ߵ�Ȩ��
	private double weight;
	//�ߵ�״̬
	private EType type = EType.UNDETERMINE;
	
	public Edge(Vertex<T> v,Vertex<T> u,double weight) {
		this.v= v;
		this.u = u;
		this.weight = weight;
	}
	
	public EType getType() {
		return type;
	}

	public void setType(EType type) {
		this.type = type;
	}

	public Vertex<T> getStartVertex() {
		return v;
	}
	public void setStartVertex(Vertex<T> v) {
		this.v = v;
	}
	public Vertex<T> getEndVertex() {
		return u;
	}
	public void setEndVertex(Vertex<T> u) {
		this.u = u;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	@Override
	@SuppressWarnings("all")
	public boolean equals(Object obj) {
		Edge<T> e = (Edge<T>)obj;
		if(e.getEndVertex().equals(u))
			if(e.getStartVertex().equals(v))
				return true;
		return false;
	}
}
