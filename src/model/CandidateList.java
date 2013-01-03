package model;

import java.util.List;

public class CandidateList {

	private String name;
	
	private List<Candidate> candidateList;

	public boolean remove(Candidate toRemove) {
		return candidateList.remove(toRemove);
	} 
	
	public boolean add(Candidate toAdd) {
		return candidateList.add(toAdd);
	}
	
	public List<Candidate> getCandidateList() {
		return candidateList;
	}
	
	public void setCandidateList(List<Candidate> candidateList) {
		this.candidateList = candidateList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
}
