package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Absentee;
import model.Candidate;
import model.CandidateAdapter;
import model.CandidateList;
import model.Outcome;
import model.OutcomeVisitor;
import model.Prize;
import model.exception.NoCandidateListFoundError;
import model.exception.NoPrizeFoundError;
import model.persistenter.ModelPersistenter;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import util.ImageUtil;
public class ContentPane extends JPanel {

	private static final long serialVersionUID = 5744529722826165904L;
	
	private static final Font WINNER_FONT = new Font("Comic Sans MS", Font.BOLD, 22);
	
	private static final Color WINNER_FONT_COLOR = Color.WHITE;
	
	private static final Font ABSENT_WINNER_FONT = new Font("Comic Sans MS", Font.ITALIC, 22);
	
	private static final Color ABSENT_WINNER_FONT_COLOR = Color.CYAN;
	
	private static final String ABSENT_TIP = "加班中...";
	
	private static Logger logger = Logger.getLogger(ContentPane.class.getName());
	
	private PrizeDisplayPanel pnlPrizeDisplay;

	private DrawPanel pnlDraw;

	private JPanel pnlUpperBlankBar;

	private JPanel pnlBottomBlankBar;

	private Image imgMainBackground;

	private List<Prize> prizes;
	
	private Prize currPrize;
	
	private Map<String, Prize> nameIndexedPrizes = new HashMap<String, Prize>();
	
	private Map<String, CandidateList> nameIndexedCandidateLists = new HashMap<String, CandidateList>();
	
	private Map<String, Image> nameIndexedPrizeImgs = new HashMap<String, Image>();
	
	private Set<Absentee> absentees;

	private CandidateList candidateList;
	
	private Map<String, Candidate> noIndexedRemovedCandidates = new HashMap<String, Candidate>();
	
	private Outcome outcome = new Outcome();

	private Thread drawThread;
	
	Point debugPoint;

	public ContentPane() throws Exception {
		super();
		initModels();
		initComponents();
		debug();
	}

	private void debug() {
		this.addMouseMotionListener(new MouseAdapter() {

			public void mouseMoved(MouseEvent e) {
				if (e.isControlDown()) {
					debugPoint = e.getPoint();
					repaint();
				} else {
					debugPoint = null;
				}
			}

		});
	}

	private void initModels() throws UnsupportedEncodingException, FileNotFoundException, DocumentException, NoPrizeFoundError, NoCandidateListFoundError {

		prizes = ModelPersistenter.loadPrizes();
		
		if (prizes == null || prizes.isEmpty())
			throw new NoPrizeFoundError();
		
		for (Prize prize : prizes) {
			String imgName = prize.getImgName();
			Image prizeImg = ImageUtil.loadImg(imgName);
			nameIndexedPrizeImgs.put(imgName, prizeImg);
			
			nameIndexedPrizes.put(prize.getName(), prize);
		}

		for (CandidateList candidateList : ModelPersistenter.loadCandidates())
			nameIndexedCandidateLists.put(candidateList.getName(), candidateList);
		
		if (nameIndexedCandidateLists.isEmpty())
			throw new NoCandidateListFoundError();
		
		currPrize = prizes.get(0);
		candidateList = nameIndexedCandidateLists.get(currPrize.getCandidateListName());
		
		absentees = ModelPersistenter.loadAbsentees();
		
		boolean isOutcomeExisting = true;
		try {
			outcome = ModelPersistenter.loadOutcome();
		} catch (FileNotFoundException e1) {
			isOutcomeExisting = false;
		} catch (DocumentException e1) {
			throw e1;
		}
		
		if (isOutcomeExisting) {
			final String[] options = {"继续上次", "重新开始"};
			int response=JOptionPane.showOptionDialog(
										this, 
										"检测到上次抽奖记录，是否要继续？",
										"继续上次or重新开始",
										JOptionPane.YES_NO_OPTION, 
										JOptionPane.QUESTION_MESSAGE, 
										null, 
										options, 
										options[0]);
			if(response == 0) {
				outcome.traverse(new OutcomeVisitor() {

					@Override
					public void visit(String prize, String winnerNo) {
						Prize prz = nameIndexedPrizes.get(prize);
						CandidateList candidateList = nameIndexedCandidateLists.get(prz.getCandidateListName());
						Candidate removedCandidate = candidateList.removeByCandidateNo(winnerNo);
						noIndexedRemovedCandidates.put(removedCandidate.getNo(), removedCandidate);
					}
					
				});
			} else {
				outcome = new Outcome();
			}
		}
		
	}

	private void initComponents() throws FileNotFoundException {

		setLayout(new BorderLayout(10, 20));

		pnlUpperBlankBar = new JPanel();
		pnlUpperBlankBar.setPreferredSize(new Dimension(1280, 274));
		pnlUpperBlankBar.setOpaque(false);
		this.add(pnlUpperBlankBar, BorderLayout.NORTH);

		pnlBottomBlankBar = new JPanel();
		pnlBottomBlankBar.setPreferredSize(new Dimension(1280, 22));
		pnlBottomBlankBar.setOpaque(false);
		this.add(pnlBottomBlankBar, BorderLayout.SOUTH);

		pnlPrizeDisplay = new PrizeDisplayPanel(new Dimension(400, 600));
		pnlPrizeDisplay.setImg(nameIndexedPrizeImgs.get(currPrize.getImgName()));

		this.add(pnlPrizeDisplay, BorderLayout.WEST);
	
		pnlDraw = new DrawPanel(new Dimension(867, 600));
		
		pnlDraw.setOpaque(false);
		final MouseAdapter[] verticalMouseAdapters = new MouseAdapter[VerticalPanel.PRIZE_CATEGORY_QTY];
		for (int i = 0; i < VerticalPanel.PRIZE_CATEGORY_QTY; i++)
			verticalMouseAdapters[i] = new VerticalMouseListener(i);

		pnlDraw.getPnlVertical().setMouseListeners(verticalMouseAdapters);

		pnlDraw.getPnlHorizontal().setDrawButtonMouseListener(
				new DrawButtonMouseListener());
		pnlDraw.getPnlHorizontal().setRedrawButtonMouseListener(
				new RedrawButtonMouseListener());
		this.add(pnlDraw, BorderLayout.CENTER);

		outcome.traverse(new OutcomeVisitor() {

			@Override
			public void visit(String prize, String winnerNo) {
				Prize prz = nameIndexedPrizes.get(prize);
				int idx = prizes.indexOf(prz);
				if (idx == -1) return;
				
				verticalMouseAdapters[idx].mouseClicked(null);
				Candidate winner = noIndexedRemovedCandidates.get(winnerNo);
				if (winner != null)
					addWinnerToBoard(winner);
			}
			
		});
		
		verticalMouseAdapters[VerticalPanel.PRIZE_CATEGORY_QTY - 1].mouseClicked(null);
		imgMainBackground = ImageUtil.loadImg("main-background.jpg");
	}

	private void addWinnerToBoard(Candidate winner) {
		if (!absentees.contains(new CandidateAdapter(winner)))
			pnlDraw.getPnlInnerDraw().addWinnerToBoard(winner.toString(), "", WINNER_FONT, WINNER_FONT_COLOR);
		else 
			pnlDraw.getPnlInnerDraw().addWinnerToBoard(winner.toString(), ABSENT_TIP, ABSENT_WINNER_FONT, ABSENT_WINNER_FONT_COLOR);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(imgMainBackground, 0, 0, null);

		/* debug */
//		g2.setColor(Color.CYAN);
//		paintRect(g2, pnlBottomBlankBar.getBounds());
//		paintRect(g2, pnlUpperBlankBar.getBounds());
//		paintRect(g2, pnlDraw.getBounds());
//		paintRect(g2, pnlPrizeDisplay.getBounds());
//
//		g2.translate(pnlDraw.getX(), pnlDraw.getY());
//		paintRect(g2, pnlDraw.getPnlInnerDraw().getBounds());
//		paintRect(g2, pnlDraw.getPnlVertical().getBounds());
//		paintRect(g2, pnlDraw.getPnlHorizontal().getBounds());
//		paintRect(g2, pnlDraw.getPnlInnerDraw().getCardPanel().getBounds());
//		g2.translate(-pnlDraw.getX(), -pnlDraw.getY());

		g2.setColor(Color.BLACK);
		g2.setFont(Font.getFont("宋体"));
		if (debugPoint != null) {
			g2.drawString(debugPoint.toString(), 0, 30);
		}
	}

	class VerticalMouseListener extends MouseAdapter {

		private int idx;

		public VerticalMouseListener(int idx) {
			super();
			this.idx = idx;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			VerticalPanel pnlVertical = pnlDraw.getPnlVertical();
			pnlVertical.setOnlyButtonDown(idx);

			pnlDraw.getPnlInnerDraw().show(idx);
			currPrize = prizes.get(idx);
			candidateList = nameIndexedCandidateLists.get(currPrize.getCandidateListName());
			Image prizeImg = nameIndexedPrizeImgs.get(currPrize.getImgName());
			
			pnlDraw.getPnlHorizontal().stopRolling();
			pnlDraw.getPnlHorizontal().clearRollingLabel();
			
			pnlDraw.getPnlHorizontal().setRedrawBtnVisible(currPrize.needRedraw());
			
			pnlPrizeDisplay.setImg(prizeImg);
		}

	}

	class DrawButtonMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			pnlDraw.getPnlHorizontal().flipDrawBtnState();
			if (!pnlDraw.getPnlHorizontal().isRolling() && !candidateList.getCandidateList().isEmpty()) {					
				drawThread = pnlDraw.getPnlHorizontal().startRolling(candidateList);
			} else {
				pnlDraw.getPnlHorizontal().stopRolling();
				if (drawThread == null) return;
				
				try {
					drawThread.join(); //wait for roll thread to stop.
				} catch (InterruptedException e1) {
					logger.error(e1.getMessage());
				}
				drawThread = null;
				Candidate winner = pnlDraw.getPnlHorizontal().getWinner();
				candidateList.remove(winner);
				
				addWinnerToBoard(winner);
				
				outcome.add(currPrize.getName(), winner.getNo());

				try {
					ModelPersistenter.persistOutcome(outcome);
				} catch (IOException e2) {
					logger.error(e2.getMessage());
				}

			}
			
		}
	}

	class RedrawButtonMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			String winnerNo = pnlDraw.getPnlInnerDraw().removeLastWinner(); //To re-add into candidateList or not?
			pnlDraw.getPnlHorizontal().clearRollingLabel();
			int idx = winnerNo.indexOf(' ');
			if (-1 == idx) return;
			outcome.remove(winnerNo.substring(0, idx));
			
			try {
				ModelPersistenter.persistOutcome(outcome);
			} catch (IOException e2) {
				logger.error(e2.getMessage());
			}
		}
	}

	// for debug
	public void paintRect(Graphics g, Rectangle rect) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawRect(rect.x + 1, rect.y + 1, rect.width - 3, rect.height - 3);
	}

}
