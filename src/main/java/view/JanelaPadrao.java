package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
public class JanelaPadrao extends JFrame {

	public JanelaPadrao(String nome) {	
		setTitle(nome);
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        Color rosa = new Color(205,133,63);
        getContentPane().setBackground(rosa);
        this.setIconImage(new javax.swing.ImageIcon("./src/images/icon.png").getImage());
    
	}
	protected JLabel addLabel(String nome, String fonte, int x, int y, int a, int l, int tamanho,Color cor) {
		JLabel label = new JLabel(nome);
		label.setForeground(cor);
		label.setBounds(x, y, a,l);
		label.setFont(new Font(fonte, Font.PLAIN, tamanho));
		add(label );
		return label;
	}
}
