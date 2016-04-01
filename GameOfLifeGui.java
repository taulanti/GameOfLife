import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GameOfLifeGui {

	private JFrame frame;
	JButton [][] bt;
	int [][] a = new int[50][50];
	boolean pressed = false;
	int sum = 0;
	Thread t;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOfLifeGui window = new GameOfLifeGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameOfLifeGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(50,50));
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		createBoard();	
		
		JButton btnStartGameOf = new JButton("Start Game of Life");
		btnStartGameOf.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					t = new Thread(new Runnable() {
					
						@Override
						public void run() {
								// TODO Auto-generated method stub
								sum = startGameOfLife();
								while(sum > 0){
									try {
										sum = startGameOfLife();
										Thread.sleep(200);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							
							}
					});
					t.start();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		menuBar.add(btnStartGameOf);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clear();
			}
		});
		
		JButton btnStop = new JButton("Stop");
		menuBar.add(btnStop);
		menuBar.add(btnClear);
		btnStop.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				t.stop();
			}
		});
	}
	
	//board is created using gridlayout 50 by 50 and populated with buttons.
	public  void createBoard(){
		bt = new JButton[50][50];	
		for (int i = 0; i < bt.length; i++) {
			for(int j = 0; j < bt[i].length; j++){
				bt[i][j] = new JButton();
				bt[i][j].setBackground(Color.WHITE);
				JButton buton = bt[i][j];
				bt[i][j].addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
						if(buton.getBackground() == Color.WHITE){
							buton.setBackground(Color.RED);
							initializeBoard();
						}else{
							buton.setBackground(Color.WHITE);
							initializeBoard();
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						if(pressed == true){
							if(buton.getBackground() == Color.WHITE){
								buton.setBackground(Color.RED);
							}
							else{
								buton.setBackground(Color.WHITE);
							}
						}
						initializeBoard();	
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						if(pressed == false){
							pressed = true;
						}	
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						if(pressed == true){
							pressed = false;
						}
					}
				});
					
				frame.getContentPane().add(bt[i][j]);
			}	
		}
		
	}
	
	//initialize board with the cells that are turn red by clicking on cells
	public void initializeBoard(){
		for (int i = 0; i < bt.length; i++) {
			for (int j = 0; j < bt[i].length; j++) {
				if(bt[i][j].getBackground() == Color.RED){a[i][j] = 1;}
			}
		}
	}
	
	public int startGameOfLife(){
		sum = GameOfLife.sum(a);
		GameOfLife.startGame(a);
		copyValues();
		return sum;
	}
	
	//copy new values which will change the color of cells in board
	public void copyValues(){
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if(a[i][j] == 1){bt[i][j].setBackground(Color.RED);}
				else			{bt[i][j].setBackground(Color.WHITE);}
			}
		}
	}
	
	public void clear(){
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				bt[i][j].setBackground(Color.WHITE);
				a[i][j] = 0;
			}	
		}
	}
	
}
