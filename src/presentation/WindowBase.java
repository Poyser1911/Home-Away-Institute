package presentation;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
//KYS
public class WindowBase {
		
	protected JFrame frame = new JFrame();
	private Point compCoords;
	private JLabel headerlabel;
	private JLabel close;
	private JLabel minimize;
	protected JButton btnLogout;
	private Color borderColor = Color.decode("#003049");
	
	public WindowBase(){
		frame.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 15));
		frame.setFont(new Font("Calibri", Font.PLAIN, 50));
		frame.setResizable(false);
		frame.setBounds(100, 100, 695, 529);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.decode("#FFFFFF"));
		frame.addMouseListener(frameDragOnClick());
		frame.addMouseMotionListener(frameDragOnMotion());
		
		JLabel lblTheHome = new JLabel("The Home & Away Institute");
		lblTheHome.setForeground(Color.WHITE);
		lblTheHome.setHorizontalAlignment(SwingConstants.LEFT);
		lblTheHome.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblTheHome.setBounds(177, -1, 359, 32);
		frame.getContentPane().add(lblTheHome);
		
		close = new JLabel("\u2716");
		close.setToolTipText("Exit Application");
		close.setForeground(Color.WHITE);
		close.setVerticalAlignment(SwingConstants.TOP);
		close.setHorizontalAlignment(SwingConstants.CENTER);
		close.setFont(new Font("Dialog", Font.PLAIN, 25));
		close.setBounds(624, 0, 72, 43);
		close.setCursor(new Cursor(Cursor.HAND_CURSOR));
		close.addMouseListener(monitorClose());
		frame.getContentPane().add(close);
		
		minimize = new JLabel("_");
		minimize.setToolTipText("Minimize Application");
		minimize.setForeground(Color.WHITE);
		minimize.setVerticalAlignment(SwingConstants.TOP);
		minimize.setHorizontalAlignment(SwingConstants.CENTER);
		minimize.setFont(new Font("Dialog", Font.PLAIN, 25));
		minimize.setBounds(576, -12, 52, 53);
		minimize.setCursor(new Cursor(Cursor.HAND_CURSOR));
		minimize.addMouseListener(monitorminmize());
		frame.getContentPane().add(minimize);
		
		JLabel border_bottom = new JLabel();
		border_bottom.setOpaque(true);
		border_bottom.setBackground(borderColor);
		border_bottom.setBounds(0, 511, 696, 17);
		frame.getContentPane().add(border_bottom);
		
		JLabel border_left = new JLabel();
		border_left.setOpaque(true);
		border_left.setBackground(borderColor);
		border_left.setBounds(0, 35, 13, 482);
		frame.getContentPane().add(border_left);
		
		JLabel border_right = new JLabel();
		border_right.setOpaque(true);
		border_right.setBackground(borderColor);
		border_right.setBounds(683, 35, 14, 482);
		frame.getContentPane().add(border_right);
		
		JLabel border_top = new JLabel();
		border_top.setOpaque(true);
		border_top.setBackground(borderColor);
		border_top.setBounds(0, 0, 696, 37);
		frame.getContentPane().add(border_top);
		
		
		headerlabel = new JLabel("<Header>");
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
		headerlabel.setBounds(10, 71, 675, 32);
		frame.getContentPane().add(headerlabel);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(logoutClicked());
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogout.setFocusPainted(false);
		btnLogout.setBackground(new Color(59, 89, 182));
		btnLogout.setBounds(575, 42, 98, 32);
		frame.getContentPane().add(btnLogout);
		
	}
	
	public String getHeaderlabel() {
		return headerlabel.getText();
	}

	public void setHeaderlabel(String headerlabel) {
		this.headerlabel.setText(headerlabel);
	}

	private MouseListener frameDragOnClick(){
		return new MouseListener() {
            public void mouseReleased(MouseEvent e) {
                compCoords = null;
            }
            public void mousePressed(MouseEvent e) {
                compCoords = e.getPoint();
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }
        };
	}
	
	private MouseMotionListener frameDragOnMotion(){
		return new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                frame.setLocation(currCoords.x - compCoords.x, currCoords.y - compCoords.y);
            }
        };
	}
	
	private MouseAdapter monitorClose(){
		return new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}

			public void mouseEntered(MouseEvent e) {
				close.setForeground(Color.yellow);
			}

			public void mouseExited(MouseEvent e) {
				close.setForeground(Color.white);
			}
		};
	}
	
	private MouseAdapter monitorminmize(){
		return new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
			public void mouseEntered(MouseEvent e) {
				minimize.setForeground(Color.yellow);
			}

			public void mouseExited(MouseEvent e) {
				minimize.setForeground(Color.white);
			}
		};
	}
	
	private ActionListener logoutClicked(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessageBox.Show("Logout Sucessful");
				MainWindow.main(null);
				frame.dispose();
			}
		};
	}
	
}