package model.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import model.Candidate;
import model.CandidateList;
import model.Prize;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

@SuppressWarnings("unchecked")
public class ModelLoader {
		
	public static List<Prize> loadPrizes() throws UnsupportedEncodingException, FileNotFoundException, DocumentException {
		List<Prize> prizes = new ArrayList<Prize>();
		SAXReader reader = new SAXReader();
		
		Document doc = reader.read(new InputStreamReader(
				new FileInputStream(new File("config/prize/prize.xml")), "UTF-8" ));
		
		List<Node> nodes = doc.selectNodes("/prizes/prize");
		for (Node node : nodes) {
			Prize award = new Prize();
			award.setName(node.selectSingleNode("name").getText());
			award.setCandidateListName((node.selectSingleNode("candidate-list").getText()));
			award.setImgName(node.selectSingleNode("img").getText());
			award.setDeccription(node.selectSingleNode("description").getText());
			award.setQuantity(Integer.parseInt(node.selectSingleNode("quantity").getText()));
				
			prizes.add(award);
		}
		
		return prizes;
	} 
	
	public static List<CandidateList> loadCandidates() throws FileNotFoundException, DocumentException {
		List<CandidateList> result = new ArrayList<CandidateList>();		

		SAXReader reader = new SAXReader();
		
		Document doc = reader.read(
				new FileInputStream(new File("config/candidate/candidate.xml")), 
				"UTF-8");

		List<Node> candidateListNodes = doc.selectNodes("/candidate-lists/candidate-list");
		for (Node candidateListNode : candidateListNodes) {
			String name = candidateListNode.valueOf("./@name");
			CandidateList candidateList = new CandidateList();
			candidateList.setName(name);
			
			List<Candidate> candidates = new ArrayList<Candidate>();
			List<Node> candidateNodes = candidateListNode.selectNodes("./candidate");
			for (Node candidateNode : candidateNodes) {
				String no = candidateNode.selectSingleNode("no").getText();
				//nullable
				Node domainIdNode = candidateNode.selectSingleNode("domain-id");
				String domainId = domainIdNode == null ? "" : domainIdNode.getText();
				candidates.add(new Candidate(no, domainId));
			}
			candidateList.setCandidateList(candidates);
			result.add(candidateList);
		}
		
		return result;
	}
	
}
