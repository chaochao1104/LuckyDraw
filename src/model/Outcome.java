package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Outcome {
	/* key: prize name, value: winner domain ids */
	Map<String, List<String>> outcome = new HashMap<String, List<String>>();

	public void add(String prizeName, String domainId) {
		List<String> winnerNos = outcome.get(prizeName);
		if (winnerNos == null) {
			winnerNos = new ArrayList<String>();
			outcome.put(prizeName, winnerNos);
		}

		winnerNos.add(domainId);
	}

	public void traverse(final OutcomeVisitor visitor) {
		if (visitor == null)
			return;

		for (Map.Entry<String, List<String>> entry : outcome.entrySet())
			for (String winnerNo : entry.getValue())
				visitor.visit(entry.getKey(), winnerNo);
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
}
