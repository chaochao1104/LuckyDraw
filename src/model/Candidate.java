package model;


public class Candidate {
		
	private String no;
	
	private String domainId;
	
	public Candidate(String no, String domainId) {
		this.no = no;
		this.domainId = domainId;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getDomainId() {
		return domainId;
	}

	void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(no).append(' ').append(domainId).toString();
	}


}
