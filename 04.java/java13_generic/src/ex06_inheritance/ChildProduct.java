package ex06_inheritance;

public class ChildProduct<K, M, C> extends Product<K, M> {

	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "kind = " + this.getClass() 
			+ ", model = " + this.getModel()
			+ ", color = " + this.getColor();
	}
	
}
