package util;

public interface GraphInterface<T> {
	//�ԱߵĲ����ӿ�
	//�ܱ���
	int e();
	//����Ӷ���v��u������
	void insert(Vertex<T> v,Vertex<T> u);
	//ɾ���Ӷ���v��u������
	void remove(Vertex<T> v,Vertex<T> u);
	
	
	//�Զ���Ĳ����ӿ�
	//�ڶ��㼯�в����¶���V
	void insert(Vertex<T> v);
	//������Ӷ��㼯��ɾ��
	void remove(Vertex<T> v);
	//����v���׸��ڽӶ���
	Vertex<T> firstNbr(Vertex<T> v);
	//����V�ĺ���ڽӶ���
	Vertex<T> nextNbr(Vertex<T> v,Vertex<T> u);
}
