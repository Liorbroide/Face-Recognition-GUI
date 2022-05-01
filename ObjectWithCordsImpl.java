package hw4;

public class ObjectWithCordsImpl implements ObjectWithCoordinates {
	private int x;
	private int y;
	private Object data;
	
	public ObjectWithCordsImpl(int x, int y, Object data) {
		this.x = x;
		this.y = y;
		this.data = data;
	}
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return this.data;
	}

}
