package controller.strategy.impl;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import model.Candidate;
import model.CandidateList;
import model.DrawStrategy.DrawStrategyType;
import model.persistence.ModelPersistenter;

import org.apache.log4j.Logger;

import util.ExceptionUtil;
import controller.strategy.NoninterruptableDrawListener;

public class OnceDrawListener extends NoninterruptableDrawListener {

	private static Logger logger = Logger.getLogger(OnceDrawListener.class.getName());
	
	private Timer timer = new Timer(false);
	
	private boolean isWorking = false;
	
	@Override
	public DrawStrategyType getType() {
		return DrawStrategyType.ONCE;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (isWorking) return;
		
		pnlHorizontal.startRolling(new CandidateList(candidateList));
		
		long period = currPrize.getDrawStrategy().getValue();
		timer.schedule(new DrawTimerTask(), 0, period);
	}
	
	class DrawTimerTask extends TimerTask {
	
		Random rand = new Random();
		
		@Override
		public void run() {
			isWorking = true;
			if (outcome.size(currPrize.getName()) < currPrize.getQuantity()) {
				List<Candidate> candidates = candidateList.getCandidateList();
				int randIdx = rand.nextInt(candidates.size());
				Candidate winner = candidates.remove(randIdx);
				outcome.add(currPrize.getName(), winner.getNo());
				addWinnerToBoard(winner);
			} else {
				pnlHorizontal.stopRolling();
				pnlHorizontal.clearRollingLabel();
				this.cancel();
				isWorking = false;
			}
			
			try {
				ModelPersistenter.persistOutcome(outcome);
			} catch (IOException e2) {
				logger.error(ExceptionUtil.getStackTrace(e2));
			}
		}
		
	}

	@Override
	public boolean isWorking() {
		return isWorking;
	}
	
}
