package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class Outcome {
	/* key: prize name, value: winner domain ids */
	Map<String, List<String>> outcome = new HashMap<String, List<String>>();
	
	private static Logger logger = Logger.getLogger(Outcome.class.getName());
	
	public void add(String prizeName, String winnerNo) {
		List<String> winnerNos = outcome.get(prizeName);
		if (winnerNos == null) {
			winnerNos = new ArrayList<String>();
			outcome.put(prizeName, winnerNos);
		}

		winnerNos.add(winnerNo);
	}

	public void traverse(final OutcomeVisitor visitor) {
		if (visitor == null)
			return;

		for (Map.Entry<String, List<String>> entry : outcome.entrySet())
			for (String winnerNo : entry.getValue()) {
				logger.info(entry.getKey() + winnerNo);
				visitor.visit(entry.getKey(), winnerNo);
			}
				
	}

	public boolean remove(String winnerNo) {
		Map.Entry<String, List<String>> entry = null;

		for (Map.Entry<String, List<String>> e : outcome.entrySet()) {
			if (entry != null) break;
			for (String no : e.getValue())
				if (no.equals(winnerNo)) {
					entry = e;
					break;
				}
		}
		
		if (entry != null)
			return entry.getValue().remove(winnerNo);
		return false;
	}

	public boolean isEmpty() {
		return outcome.isEmpty();
	}
	
	public int size(String prizeName) {
		List<String> prizes = outcome.get(prizeName);
		if (prizes == null) 
			return 0;
		
		return prizes.size();
	}
	
}
