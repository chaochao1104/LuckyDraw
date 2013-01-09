package model.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Absentee;
import model.Candidate;
import model.CandidateList;
import model.Outcome;
import model.OutcomeVisitor;
import model.Prize;
import model.Prize.DrawStrategy;
import model.Prize.DrawStrategy.DrawStrategyType;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

@SuppressWarnings("unchecked")
public class ModelPersistenter {
	
	public static final String FILE_IO_CHARSET = "UTF-8";
	
	public static List<Prize> loadPrizes() throws UnsupportedEncodingException, FileNotFoundException, DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new InputStreamReader(
				new FileInputStream(new File("config/prizes/prizes.xml")), FILE_IO_CHARSET ));
		
		List<Prize> ret = new ArrayList<Prize>();
		List<Node> nodes = doc.selectNodes("/prizes/prize");
		for (Node node : nodes) {
			Prize prize = new Prize();
			prize.setName(node.selectSingleNode("name").getText());
			prize.setCandidateListName((node.selectSingleNode("candidate-list").getText()));
			prize.setImgName(node.selectSingleNode("img").getText());
			prize.setDeccription(node.selectSingleNode("description").getText());
			prize.setQuantity(Integer.parseInt(node.selectSingleNode("quantity").getText()));
			Node needRedrawNode = node.selectSingleNode("need-redraw");
			String needRedraw = needRedrawNode == null? "false" : needRedrawNode.getText();
			prize.setNeedRedraw("true".equalsIgnoreCase(needRedraw));
			
			Node strategyNode = node.selectSingleNode("strategy");
			String strategyName = strategyNode.valueOf("./@name");
			String strategyValue = strategyNode.valueOf("./@value");
			DrawStrategy strategy = new DrawStrategy();
			strategy.setType(DrawStrategyType.valueOf(strategyName));
			if (strategyValue != null && !strategyValue.trim().isEmpty())
				strategy.setValue(Long.parseLong(strategyValue));

			prize.setDrawStrategy(strategy);
			
			ret.add(prize);
		}
		
		return ret;
	} 
	
	public static List<CandidateList> loadCandidates() throws FileNotFoundException, DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(
				new FileInputStream(new File("config/candidates/candidates.xml")), 
				FILE_IO_CHARSET);

		List<CandidateList> ret = new ArrayList<CandidateList>();		
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
			
			Collections.shuffle(candidates);
			candidateList.setCandidateList(candidates);
			
			ret.add(candidateList);
		}
		
		return ret;
	}
	
	public static Set<Absentee> loadAbsentees() throws FileNotFoundException, DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(
				new FileInputStream(new File("config/absentees/absentees.xml")), 
				FILE_IO_CHARSET);
		
		Set<Absentee> ret = new HashSet<Absentee>();
		List<Node> absenteeNodes = doc.selectNodes("/absentees/absentee");
		for (Node absenteeListNode : absenteeNodes) {
			String domainId = absenteeListNode.valueOf("./@domain-id");
			Absentee absentee = new Absentee(domainId);
			
			ret.add(absentee);
		}
		
		return ret;
	}
	
	public static void persistOutcome(Outcome outcome) throws IOException  {
		final Document document = DocumentHelper.createDocument();
		final Element prizesElement = document.addElement("prizes");
		
		outcome.traverse(new OutcomeVisitor() {

			public void visit(String prize, String winnerNo) {
				Node prizeNode = document.selectSingleNode("/prizes/prize[@name='" + prize + "']");
				if (prizeNode == null) {
					prizeNode = prizesElement.addElement("prize");
					((Element)prizeNode).addAttribute("name", prize);
				}
				Element winnerElement = ((Element)prizeNode).addElement("winner");
				winnerElement.addAttribute("no", winnerNo);
			}
			
		});
		
		 XMLWriter writer = new XMLWriter(
				 new OutputStreamWriter(new FileOutputStream(new File("outcome.xml")), FILE_IO_CHARSET),
				 OutputFormat.createPrettyPrint());
         writer.write(document);		         
         writer.close();
	}
	
	public static Outcome loadOutcome() throws FileNotFoundException, DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(
				new FileInputStream(new File("outcome.xml")), 
				FILE_IO_CHARSET);
				
		Outcome ret = new Outcome();
		List<Node> prizeNodes = doc.selectNodes("/prizes/prize");
		for (Node prizeNode : prizeNodes) {
			String prizeName = prizeNode.valueOf("./@name");
			
			List<Node> winnerNodes = prizeNode.selectNodes("./winner");
			for (Node winnerNode : winnerNodes) {
				String no = winnerNode.valueOf("./@no");
				ret.add(prizeName, no);
			}
		}
		
		return ret;
	}
	
}
