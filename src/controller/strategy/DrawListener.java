package controller.strategy;

import java.awt.Color;
import java.awt.Font;
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
	
	private static final Font WINNER_FONT = new Font("Comic Sans MS", Font.BOLD, 35);
	
	private static final Color WINNER_FONT_COLOR = Color.WHITE;
	
	private static final Font ABSENT_WINNER_FONT = new Font("Comic Sans MS", Font.ITALIC, 35);
	
	private static final Color ABSENT_WINNER_FONT_COLOR = Color.CYAN;
	
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
		if (!absentees.contains(new CandidateAdapter(winner)))
			innerDrawPanel.addWinnerToBoard(winner.toString(), "", WINNER_FONT, WINNER_FONT_COLOR);
		else 
			innerDrawPanel.addWinnerToBoard(winner.toString(), ABSENT_TIP, ABSENT_WINNER_FONT, ABSENT_WINNER_FONT_COLOR);
	}
	
	public abstract DrawStrategyType getType();
}
