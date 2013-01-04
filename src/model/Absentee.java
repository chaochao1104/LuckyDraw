package model;

public class Absentee {
	
	private String domainId;

	public Absentee(String domainId) {
		this.domainId = domainId;
	}
	
	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	@Override
	public int hashCode() {
		return domainId == null ? 0 : domainId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		if (obj instanceof Absentee) {
			Absentee other = (Absentee) obj;
			return this.domainId.equals(other.domainId);
		}
		
		return false;
	}

	
}
