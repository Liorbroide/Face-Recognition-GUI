package hw4;


public class AVLNode<T> {
	public AVLNode left;
	public AVLNode right;
	public AVLNode parent;
	public int height;
	private int key;
	private T data;

	public AVLNode(int key, T data) {
	this.key = key;
	this.data = data;
	this.left = null;
	this.right = null;
	this.parent = null;
	this.height = 0;
	
		
	}






	public int GetHeight() {
		return this.height;
	}
	
	

	public void SetHeight() {
		if (this.left == null && this.right != null) {
			this.height = this.right.height + 1;
			return;
		}
		if (this.right == null && this.left != null) {
			this.height = this.left.height + 1;
			return;
		}
		if (this.left.height > this.right.height) {
			this.height = this.left.height + 1;
			return;}
		if (this.right.height > this.left.height) {
			this.height = this.left.height + 1;
			return;
		}	
		}
	
	
	
	public AVLNode<T> getLeftChild(){
		return this.left;
	}
	
	public AVLNode<T> getRightChild(){
	
		return this.right;
	}
	
	public AVLNode<T> getFather(){
		return this.parent;
	}
	
	public int getKey(){
	
		return this.key;
	}
	
	public T getData(){
	
		return this.data;
	}
	
	@Override
	public String toString() {
		
		String s = "";
		
		if (getLeftChild() != null){
			s+="(";
			s+=getLeftChild().toString();
			s+=")";
		}
		s+=getKey();
		
		if (getRightChild() != null){
			s+="(";
			s+=getRightChild().toString();
			s+=")";
		}
		
		return s;
	}
}

