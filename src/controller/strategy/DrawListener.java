package controller.strategy;

import java.awt.event.MouseAdapter;
import java.util.Set;

import model.Absentee;
import model.Candidate;
import model.CandidateAdapter;
import model.CandidateList;
import model.DrawStrategy.DrawStrategyType;
import model.Outcome;
import model.Prize;
import view.HorizontalPanel;
import view.InnerDrawPanel;


public abstract class DrawListener extends MouseAdapter {
	
	private static final String DISPLAY_TXT_PREFIX = " ";
	
	private static final String DISPLAY_TXT_SUFFIX = " ";
	
	private static final String ABSENT_TIP = "º”∞‡÷–...";
	
	protected HorizontalPanel pnlHorizontal;
	
	protected CandidateList candidateList;
	
	protected Outcome outcome;
	
	protected Set<Absentee> absentees;
	
	protected InnerDrawPanel innerDrawPanel;
	
	protected Prize currPrize;
	
	protected DrawListener() {
	}
	
	public void init(InnerDrawPanel innerDrawPanel,
					 HorizontalPanel pnlHorizontal, 
					 CandidateList candidateList, 
					 Outcome outcome,
					 Set<Absentee> absentees,
					 Prize currPrize) {
		this.innerDrawPanel = innerDrawPanel;
		this.pnlHorizontal = pnlHorizontal;
		this.candidateList = candidateList;
		this.outcome = outcome;
		this.absentees = absentees;
		this.currPrize = currPrize;
	}
	
	public void addWinnerToBoard(Candidate winner) {
		String displayTxt = DISPLAY_TXT_PREFIX + winner.toString() + DISPLAY_TXT_SUFFIX;
		if (!absentees.contains(new CandidateAdapter(winner)))
			innerDrawPanel.addWinnerToBoard(displayTxt, "", currPrize.getWinnerfontGroup().getFont(), currPrize.getWinnerfontGroup().getColor());
		else 
			innerDrawPanel.addWinnerToBoard(displayTxt, ABSENT_TIP, currPrize.getAbsentWinnerFontGroup().getFont(), currPrize.getAbsentWinnerFontGroup().getColor());
	}
	
	public abstract DrawStrategyType getType();
}
