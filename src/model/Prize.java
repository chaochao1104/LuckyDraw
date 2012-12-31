
package model;


public class Prize {

	private String name;
	
	private String imgName;
	
	private String deccription;
	
	private String candidateListName;
	
	private int quantity;

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
		
}
