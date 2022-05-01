package hw4;
import java.util.Collection;
import java.util.LinkedList;

public class StudentSolution  implements MyInterface{
	private AVL <ObjectWithCoordinates> x;
	private AVL<ObjectWithCoordinates> y;
	private int counter;
	
	public StudentSolution() {
		this.x = new AVL<ObjectWithCoordinates>();
		this.y = new AVL<ObjectWithCoordinates>();
		this.counter = 0;
	}
	
	
	@Override
	public void insertDataFromDBFile(String objectName, int objectX, int objectY) {
		ObjectWithCordsImpl point = new ObjectWithCordsImpl (objectX,objectY,objectName);
		this.x.insert(point.getX(), point);
		this.y.insert(point.getY(), point);
		this.counter = this.counter + 1;
	}
	
	public AVL getsuitable(AVLNode<ObjectWithCoordinates> avlNode, AVL temp, int min, int max) {
		if (avlNode == null) {
			return temp;
		}
		if (avlNode.getKey() > max || avlNode.getKey() < min) {
			return temp;
		}
		if (avlNode.getKey() > min && avlNode.getKey() < max) {
			temp.insert(avlNode.getKey(), avlNode.getData());
			AVL a = getsuitable(avlNode.getRightChild(), temp, min, max);
			a = getsuitable(avlNode.getLeftChild(), a, min, max);
			temp = a;
		}
		return temp;
	}
	public LinkedList<ObjectWithCordsImpl> TreeTraverse(AVLNode root, LinkedList<ObjectWithCordsImpl> linkedarr){
		if (root == null) {
			return null;
		}
		else {
			TreeTraverse(root.getLeftChild(), linkedarr);
			linkedarr.add((ObjectWithCordsImpl) root.getData());
			TreeTraverse(root.getRightChild(), linkedarr);
			
		}
		return linkedarr;
	}
	@Override
	public String[] firstSolution(int leftTopX, int leftTopY, int rightBottomX,
			int rightBottomY) {
		// TODO Auto-generated method stub
		AVL<ObjectWithCoordinates> suitablex1 =  new AVL();
		AVL<ObjectWithCoordinates> suitabley1 = new AVL();
		AVL <ObjectWithCoordinates> suitablex = this.getsuitable(this.x.getRoot(), suitablex1, leftTopX,rightBottomX );
		AVL <ObjectWithCoordinates>suitabley = this.getsuitable(this.y.getRoot(), suitabley1, rightBottomY, leftTopY);
		LinkedList linkedarr = null;
		LinkedList linkedarr2 = null;
		linkedarr = this.TreeTraverse(suitablex.getRoot(), linkedarr);
		linkedarr2 = this.TreeTraverse(suitabley.getRoot(), linkedarr2);
		HashTable hash = new HashTable(Math.max(linkedarr.size(), linkedarr2.size()));
		LinkedList outsider = null;
		int max = Math.max(linkedarr.size(), linkedarr2.size());
		if (max == linkedarr.size()) {
			outsider = linkedarr2;
			while (linkedarr.isEmpty() == false) {
				hash.insert((ObjectWithCoordinates) linkedarr.pop());
			}
		if (max == linkedarr2.size()) {
			outsider = linkedarr;
			while (linkedarr2.isEmpty() == false) {
				hash.insert((ObjectWithCoordinates) linkedarr2.pop());
			}
		}	
		}
		int counter = 0;
		String[] toreturn = new String[Math.min(linkedarr.size(), linkedarr2.size())];
		while (outsider.isEmpty() == false) {
			ObjectWithCoordinates temp = (ObjectWithCoordinates) outsider.pop();
			if (hash.search(temp.getX(), temp.getY()) != null) {
				toreturn[0] = temp.toString();
				counter ++;
			}
		}
		if (toreturn[0] == null) {
			String[] lista = new String[0];
			return lista;
		}
		return toreturn;
		
		
	
			
		
	}

	@Override
	public String[] secondSolution(int leftTopX, int leftTopY,
			int rightBottomX, int rightBottomY) {
		// TODO Auto-generated method stub
		AVL<ObjectWithCoordinates> suitablex = new AVL();
		AVL<ObjectWithCoordinates> suitabley = new AVL();
		AVL <ObjectWithCoordinates> suitablex1 = this.getsuitable(this.x.getRoot(), suitablex, rightBottomX, leftTopX);
		AVL <ObjectWithCoordinates> suitabley1 = this.getsuitable(this.y.getRoot(),suitabley , rightBottomY, leftTopY);
		LinkedList array = new LinkedList();
		LinkedList arrayfory = new LinkedList();
		LinkedList array1 = this.TreeTraverse(suitablex1.getRoot(),array );
		LinkedList array2 = this.TreeTraverse(this.y.getRoot(), arrayfory);
		String[] toreturn = new String[Math.min(array1.size(), array2.size())];
		int counter = 0;
		if (array1.size() >= array2.size()) {
			while (array2.size() != 0) {
				ObjectWithCoordinates temp = (ObjectWithCoordinates) array2.pop();
				if (suitablex1.search(temp.getX()) == null) {
					continue;
				} 
				else {
					
					ObjectWithCoordinates toinsert = suitablex1.search(temp.getX());
					if (toinsert.getY() == temp.getY()) {
					toreturn[counter] = toinsert.toString();
					counter++;}
				}
			}
		}
		if (array2.size() > array1.size()) {
			while (array1.size() != 0) {
				ObjectWithCoordinates temp = (ObjectWithCoordinates) array2.pop();
				if (suitabley1.search(temp.getY()) == null) {
					continue;
				}
				ObjectWithCoordinates toreturn2 = suitabley.search(temp.getY());
				if (toreturn2.getX() == temp.getX()) {
				toreturn[counter] = toreturn2.toString();
				counter++;
				}
			}
		}
		return toreturn;
	}

}
