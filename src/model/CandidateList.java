package model;

import java.util.ArrayList;
import java.util.List;

public class CandidateList {

	private String name;
	
	private List<Candidate> candidateList;

	public CandidateList(){}
	
	public CandidateList(CandidateList copy) {
		this.name = copy.name;
		this.candidateList = new ArrayList<Candidate>(copy.candidateList);
	}
	
	public boolean remove(Candidate toRemove) {
		return candidateList.remove(toRemove);
	}
	
	public Candidate removeByCandidateNo(String no) {
		int idxToRemove = -1;
		for (int i = 0; i < candidateList.size(); i++) {
			if (candidateList.get(i).getNo().equals(no)) {
				idxToRemove = i;
				break;
			}
		}
		
		if (idxToRemove > -1)
			return candidateList.remove(idxToRemove);
		
		return null;
	}
	
	public Candidate findCandidateByNo(String no) {
		for (Candidate candidate : candidateList)
			if (candidate.getNo().equals(no))
				return candidate;
		
		return null;
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
