package hw4;

import java.util.LinkedList;



public class HashTable {
	private LinkedList[] hasharray ;
	private int size;
	
	public HashTable(int size) {
		this.hasharray =  new LinkedList[size - 1];
		this.size = size;
		
	}
	public int hashfunction(ObjectWithCoordinates point) {
		int index = (point.getX() + point.getY()) % size;
		return index;
		
	}
	public void insert(ObjectWithCoordinates object){
		int index = this.hashfunction(object);
		if (this.hasharray[index] == null) {
			LinkedList<ObjectWithCoordinates> temp = new LinkedList<ObjectWithCoordinates>();
			temp.add(object);
			this.hasharray[index] = temp;
			
		}
		else {
			this.hasharray[index].add(object);
			
		}
	}

	public ObjectWithCoordinates search(int x, int y){
		//TODO
		int index = (x + y) % size;
		ObjectWithCoordinates temp = (ObjectWithCoordinates) this.hasharray[index];
		if (temp == null) {
			return null;
		}
		for (int i = 0; i< this.hasharray[index].size(); i++) {
			ObjectWithCoordinates temp1 = (ObjectWithCoordinates) this.hasharray[index].get(i);
			if (temp1.getX() == x && temp1.getY() == y) {
				return temp1;
				
			}
			
			
		}
		return null;
		
	}
}

