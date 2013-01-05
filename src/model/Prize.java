
package model;


public class Prize {

	private String name;
	
	private String imgName;
	
	private String deccription;//no use so far.
	
	private String candidateListName;
	
	private int quantity;//no use so far.
	
	private boolean needRedraw;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((candidateListName == null) ? 0 : candidateListName
						.hashCode());
		result = prime * result
				+ ((deccription == null) ? 0 : deccription.hashCode());
		result = prime * result + ((imgName == null) ? 0 : imgName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (needRedraw ? 1231 : 1237);
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prize other = (Prize) obj;
		if (candidateListName == null) {
			if (other.candidateListName != null)
				return false;
		} else if (!candidateListName.equals(other.candidateListName))
			return false;
		if (deccription == null) {
			if (other.deccription != null)
				return false;
		} else if (!deccription.equals(other.deccription))
			return false;
		if (imgName == null) {
			if (other.imgName != null)
				return false;
		} else if (!imgName.equals(other.imgName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (needRedraw != other.needRedraw)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}


		
}
