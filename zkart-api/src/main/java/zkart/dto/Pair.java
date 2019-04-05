package zkart.dto;

public class Pair {
	String attribute;
	String value;
	
	public Pair(String attribute, String value) {
		this.attribute = attribute;
		this.value = value;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Pair [attribute=" + attribute + ", value=" + value + "]";
	}
}
