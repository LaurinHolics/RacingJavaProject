package project2_game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import project3_tabelSQL.JTable;

public class Game extends JFrame implements KeyListener, ActionListener {

	JTable table = new JTable();
	Random random = new Random();
	private int xpos = 300;// pozitie pe mapa
	private int ypos = 700;// pozitie pe mapa
	private ImageIcon car;// imagini masini
//	private Timer timer;
	private int roadmove = 0;// deplasarea pe mapa
	private int carxpos[] = { 100, 200, 300, 400, 500 };// valoriile miscarii pe mapa
	private int carypos[] = { -240, -480, -720, -960, -1200 };// valorii miscarilor pe mapa
	private int cxpos1 = 0, cxpos2 = 2, cxpos3 = 4;
	private int cypos1 = random.nextInt(5), cypos2 = random.nextInt(5), cypos3 = random.nextInt(5);// pozitiile random a
																									// oponentilor
	private int y1pos = carypos[cypos1], y2pos = carypos[cypos2], y3pos = carypos[cypos3];
	private ImageIcon car1, car2, car3;
	public int score;// score
	public int delay = 200;// viteza oponentilor
	public boolean gameover;
	private boolean paint;

	// initiez jocul
	public Game(String title) {
		super(title);
		setBounds(300, 10, 700, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		addKeyListener(this);
		setFocusable(true);
		setResizable(false);
		
		
	}

//creem harta unde se desfasoara actiunea 
	public void paint(Graphics g) {
		g.setColor(Color.black);// alegem culoarea in afara soselei
		g.fillRect(0, 0, 700, 700);
		g.setColor(Color.yellow);// alegem culoarea delimitarii soselei
		g.fillRect(90, 0, 10, 700);
		g.fillRect(600, 0, 10, 700);
		g.setColor(Color.gray);// alegem culoarea soselei
		g.fillRect(100, 0, 500, 700);
		

//de la linia 56 pana la 134 aflam pozitiile de deplasare a autovehiculelor
		if (roadmove == 0) {
			for (int i = 0; i <= 700; i += 100) {
				g.setColor(Color.white);
				g.fillRect(350, i, 10, 70);

			}
			roadmove = 1;
		} else if (roadmove == 1) {
			for (int i = 50; i <= 700; i += 100) {
				g.setColor(Color.white);
				g.fillRect(350, i, 10, 70);
			}
			roadmove = 0;
		}

//		if (roadmove == 0) {
//			for (int i = 0; i <= 700; i += 100) {
//				g.setColor(Color.white);
//				g.fillRect(250, i, 10, 70);
//
//			}
//			roadmove = 1;
//		} else if (roadmove == 1) {
//			for (int i = 50; i <= 700; i += 100) {
//				g.setColor(Color.white);
//				g.fillRect(250, i, 10, 70);
//			}
//			roadmove = 0;
//		}
//
//		if (roadmove == 0) {
//			for (int i = 0; i <= 700; i += 100) {
//				g.setColor(Color.white);
//				g.fillRect(150, i, 10, 70);
//
//			}
//			roadmove = 1;
//		} else if (roadmove == 1) {
//			for (int i = 50; i <= 700; i += 100) {
//				g.setColor(Color.white);
//				g.fillRect(150, i, 10, 70);
//			}
//			roadmove = 0;
//		}
//
//		if (roadmove == 0) {
//			for (int i = 0; i <= 700; i += 100) {
//				g.setColor(Color.white);
//				g.fillRect(450, i, 10, 70);
//
//			}
//			roadmove = 1;
//		} else if (roadmove == 1) {
//			for (int i = 50; i <= 700; i += 100) {
//				g.setColor(Color.white);
//				g.fillRect(450, i, 10, 70);
//			}
//			roadmove = 0;
//		}
//
//		if (roadmove == 0) {
//			for (int i = 0; i <= 700; i += 100) {
//				g.setColor(Color.white);
//				g.fillRect(550, i, 10, 70);
//
//			}
//			roadmove = 1;
//		} else if (roadmove == 1) {
//			for (int i = 50; i <= 700; i += 100) {
//				g.setColor(Color.white);
//				g.fillRect(550, i, 10, 70);
//			}
//			roadmove = 0;
//		}

		// adaugam imaginea masinii playerului
		car = new ImageIcon("playerCar.png");
		// cream pozitia de deplasare a playerului
		car.paintIcon(this, g, xpos, ypos);
		ypos -= 40;
		if (ypos < 500) {
			ypos = 500;
		}

		// adaugam masiniile oponentilor
		car1 = new ImageIcon("gameCar1.png");
		car2 = new ImageIcon("gameCar2.png");
		car3 = new ImageIcon("gameCar3.png");

		// de la linia 148 pana la 219 cream desfasurarea miscarilor pe mapa a
		// oponentilor
		car1.paintIcon(this, g, carxpos[cxpos1], y1pos);
		car2.paintIcon(this, g, carxpos[cxpos2], y2pos);
		car3.paintIcon(this, g, carxpos[cxpos3], y3pos);
		y1pos += 50;
		y2pos += 50;
		y3pos += 50;
		// aparitia oponentului 1 pe mapa
		if (y1pos > 700) {
			cxpos1++;
			if (cxpos1 > 4) {
				cxpos1 = 0;
			}

			cxpos1 = random.nextInt(5);
			cypos1 = random.nextInt(5);
			y1pos = carypos[cypos1];

		}
		// apartia oponentului 2 pe mapa
		if (y2pos > 700) {
			cxpos2++;
			if (cxpos2 > 4) {
				cxpos2 = 0;
			}

			cxpos2 = random.nextInt(5);
			cypos2 = random.nextInt(5);
			y2pos = carypos[cypos2];

		}
		// aparitia oponentului 3 pe mapa
		if (y3pos > 700) {
			cxpos3++;
			if (cxpos3 > 4) {
				cxpos3 = 0;
			}
			cxpos3 = random.nextInt(5);
			cypos3 = random.nextInt(5);
			y3pos = carypos[cypos3];
		}
		// de la linia 193 pana la 251 sa scris deplasarea a oponentilor in sens invers
		if (cxpos1 == cxpos2 && cypos1 > -100 && cypos2 > -100) {

			cxpos1 -= 1;
			if (cxpos1 < 0) {
				cxpos1 += 2;
			}
		}
		if (cxpos1 == cxpos3 && cypos1 > -100 && cypos3 > -100) {
			cxpos3 -= 1;
			if (cxpos3 < 0) {
				cxpos3 += 2;
			}
		}
		if (cxpos2 == cxpos3 && cypos3 > -100 && cypos2 > -100) {
			cxpos2 -= 1;
			if (cxpos2 < 0) {
				cxpos2 += 2;
			}
		}
		if (cxpos1 < 2 && cxpos2 < 2 && cxpos3 < 2) {
			if (cxpos1 == 0 && cxpos2 == 0 && cxpos3 == 1) {
				cxpos3++;
				cxpos2++;
			} else if (cxpos1 == 0 && cxpos2 == 1 && cxpos3 == 0) {
				cxpos3++;
				cxpos2++;
			} else if (cxpos1 == 1 && cxpos2 == 0 && cxpos3 == 0) {
				cxpos1++;
				cxpos2++;
			}
		}
		// cream coliziunea intre oponenti si player
		if (y1pos < ypos && y1pos + 175 > ypos && carxpos[cxpos1] == xpos) {
			gameover = true;
		}
		if (y2pos < ypos && y2pos + 175 > ypos && carxpos[cxpos2] == xpos) {
			gameover = true;
		}
		if (y3pos < ypos && y3pos + 175 > ypos && carxpos[cxpos3] == xpos) {
			gameover = true;
		}
		if (ypos < y1pos && ypos + 175 > y1pos && carxpos[cxpos1] == xpos) {
			gameover = true;
		}
		if (ypos < y2pos && ypos + 175 > y2pos && carxpos[cxpos2] == xpos) {
			gameover = true;
		}
		if (ypos < y3pos && ypos + 175 > y3pos && carxpos[cxpos3] == xpos) {
			gameover = true;
		}
		if (y1pos < ypos && y1pos + 175 > ypos && carxpos[cxpos1] == xpos) {
			gameover = true;
		}
		if (y2pos < ypos && y2pos + 175 > ypos && carxpos[cxpos2] == xpos) {
			gameover = true;
		}
		if (y3pos < ypos && y3pos + 175 > ypos && carxpos[cxpos3] == xpos) {
			gameover = true;
		}
		// cream scorul si displayul scorului
		g.setColor(Color.yellow);
		g.fillRect(120, 35, 220, 50);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(125, 40, 210, 40);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 30));		
		g.drawString("Score : " + score, 130, 67);
		// cream scorul si intarzierea oponentilor
		
		score++;

		if (score % 50 == 0) {
			delay -= 10;
			if (delay < 60) {
				delay = 60;
			}
		}
		try {

			TimeUnit.MILLISECONDS.sleep(delay);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		// cand jocul s-a sfarsit s a apara urmatorul display
		if (gameover) {
			g.setColor(Color.red);
			g.fillRect(120, 210, 460, 200);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(130, 220, 440, 180);
			g.setFont(new Font("Serif", Font.PLAIN, 50));
			g.setColor(Color.red);
			g.drawString("Game Over !", 220, 270);
			g.setColor(Color.yellow);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Press Enter to Exit", 220, 340);
			g.setColor(Color.green);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Press SPACE to Restart", 190, 370);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("You score:" + score, 240, 310);
			if (!paint) {
				repaint();
				paint = true;
			}
		} else {
			repaint();
		}

	}



//	private String playerScore() {
//	// TODO Auto-generated method stub
//	return null;
//}

	// cream metodele de manevrabilitate a playerului
	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT && !gameover) {
			xpos += 100;
			if (xpos > 500) {
				xpos = 500;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && !gameover) {
			xpos -= 100;
			if (xpos < 100) {
				xpos = 100;
			}

		}
		// daca apasam ENTER jocul se termina

		if (e.getKeyCode() == KeyEvent.VK_ENTER && gameover) {
			{

				gameover = true;
				paint = true;
//				System.exit(ABORT);
				JTable.main(null);
				
			}

		}

		// daca apasam SPACE reincepe jocul
		if (e.getKeyCode() == KeyEvent.VK_SPACE && gameover) {
			gameover = false;
			paint = false;

			cxpos1 = 0;
			cxpos2 = 2;
			cxpos3 = 4;
			cypos1 = random.nextInt(5);
			cypos2 = random.nextInt(5);
			cypos3 = random.nextInt(5);
			y1pos = carypos[cypos1];
			y2pos = carypos[cypos2];
			y3pos = carypos[cypos3];
			score=0;
			delay = 200;
			xpos = 300;
			ypos = 700;

		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

		if (e.getKeyChar() == 'a' && !gameover) {
			xpos -= 100;
		}
		if (e.getKeyChar() == 'd' && !gameover) {
			xpos += 100;
		}

		repaint();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
	
//	private int playerScore(int score) {{
//		
//		  scorePlayer++;
//
//		if (scorePlayer % 50 == 0) {
//			delay -= 10;
//			if (delay < 60) {
//				delay = 60;
//			}
//		}
//		try {
//
//			TimeUnit.MILLISECONDS.sleep(delay);
//		} catch (InterruptedException e) {
//
//			e.printStackTrace();
//		}
//		
//	}
//	return  score;
//	}
}
