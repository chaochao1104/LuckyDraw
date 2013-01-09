package controller.strategy.impl;

import java.awt.event.MouseEvent;
import java.io.IOException;

import model.Candidate;
import model.Prize.DrawStrategy.DrawStrategyType;
import model.persistence.ModelPersistenter;

import org.apache.log4j.Logger;

import controller.strategy.DrawListener;

public class OneByOneDrawListener extends DrawListener {
	
	private static Logger logger = Logger.getLogger(OneByOneDrawListener.class.getName());
	
	public OneByOneDrawListener() {
		super();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!pnlHorizontal.isRolling() && 
			!candidateList.getCandidateList().isEmpty() &&
			outcome.size(currPrize.getName()) < currPrize.getQuantity()) {
			pnlHorizontal.startRolling(candidateList);
			pnlHorizontal.setDrawBtnImgStop();
		} else {
			Candidate winner = pnlHorizontal.stopRolling();
			candidateList.remove(winner);
			addWinnerToBoard(winner);
			outcome.add(currPrize.getName(), winner.getNo());
			pnlHorizontal.setDrawBtnImgRoll();
			
			try {
				ModelPersistenter.persistOutcome(outcome);
			} catch (IOException e2) {
				logger.error(e2.getMessage());
			}

		}	
		
		pnlHorizontal.setDrawBtnEnabled(outcome.size(currPrize.getName()) < currPrize.getQuantity());
		
	}

	@Override
	public DrawStrategyType getType() {
		return DrawStrategyType.ONE_BY_ONE;
	}

}
