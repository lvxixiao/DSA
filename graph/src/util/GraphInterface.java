package util;

public interface GraphInterface<T> {
	//对边的操作接口
	//总边数
	int e();
	//引入从顶点v到u的联边
	void insert(Vertex<T> v,Vertex<T> u);
	//删除从顶点v到u的联边
	void remove(Vertex<T> v,Vertex<T> u);
	
	
	//对顶点的操作接口
	//在顶点集中插入新顶点V
	void insert(Vertex<T> v);
	//将顶点从顶点集中删除
	void remove(Vertex<T> v);
	//顶点v的首个邻接顶点
	Vertex<T> firstNbr(Vertex<T> v);
	//顶点V的后继邻接顶点
	Vertex<T> nextNbr(Vertex<T> v,Vertex<T> u);
}
