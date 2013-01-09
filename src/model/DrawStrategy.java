package model;


public class DrawStrategy {
	
	public enum DrawStrategyType {
		ONE_BY_ONE,
		ONCE,
	}
	
	private DrawStrategyType type;
	
	private long value;

	public DrawStrategyType getType() {
		return type;
	}

	public void setType(DrawStrategyType type) {
		this.type = type;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
	
}
