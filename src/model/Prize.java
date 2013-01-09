
package model;



public class Prize {

	public static class DrawStrategy {
		
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

	
	
	private String name;
	
	private String imgName;
	
	private String deccription;//no use so far.
	
	private String candidateListName;
	
	private int quantity;
	
	private boolean needRedraw;
	
	private DrawStrategy drawStrategy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeccription() {
		return deccription;
	}

	public void setDeccription(String deccription) {
		this.deccription = deccription;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getCandidateListName() {
		return candidateListName;
	}

	public void setCandidateListName(String candidateListName) {
		this.candidateListName = candidateListName;
	}

	public boolean needRedraw() {
		return needRedraw;
	}

	public void setNeedRedraw(boolean needRedraw) {
		this.needRedraw = needRedraw;
	}

	public DrawStrategy getDrawStrategy() {
		return drawStrategy;
	}

	public void setDrawStrategy(DrawStrategy drawStrategy) {
		this.drawStrategy = drawStrategy;
	}	

}
