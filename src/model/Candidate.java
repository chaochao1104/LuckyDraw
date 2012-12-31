package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	private List<String> candidates = new ArrayList<String>();

	public Candidate() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream("config/list.txt"), "UTF-8"));
			String str;
			while ((str = in.readLine()) != null) {
				if (str.trim().length() > 0)
					candidates.add(str.trim());
			}
			in.close();
			
			in = new BufferedReader(new InputStreamReader(new FileInputStream(
					"outcome/make.txt"), "UTF-8"));
			while ((str = in.readLine()) != null) {
				if (str.trim().length() > 0)
					candidates.remove(str.trim());
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.shuffle(candidates);
	}

}
