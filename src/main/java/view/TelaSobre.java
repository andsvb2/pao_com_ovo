package view;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class TelaSobre extends JanelaPadrao {

	public TelaSobre() {
		super("Menu");
		super.setSize(340,480); 		
		conf();
		setLocationRelativeTo(null);
		this.setVisible(true);
	}
		
		public void conf() {
			Color orchid = new Color(160,82,45);
			Color magnetta = new Color(255,250,240);
			Color amarelo = new Color(240,230,140);
				
			super.addLabel("╔════════ •°•°•° ════════╗", "Serif", 40,10,490,60,20,magnetta);
			super.addLabel("•°Pão°•","Serif", 90,40,490,60,50,magnetta);
			super.addLabel("________","Serif", 60,44,490,60,50,magnetta);
			super.addLabel("com ovo","Monospaced", 60,90,490,60,50,amarelo);
			super.addLabel("╚════════ •°•°•° ════════╝","Serif", 40,130,490,60, 20,magnetta);
			super.addLabel("<html>Projeto Pão com Ovo - PcO: <br> <br> Desenvolvido na disciplina Processos de Software, semestre 2023.1, no curso de ADS do campus IFPB-MT, para prática de SCRUM.<html>", "Arial", 40,80,270,300,13,magnetta);
			super.addLabel("<html> Anderson: <br>Cinthia: <br>Hicaro: <br>Karoline: karolined.profissional@gmail.com <html>", "Arial", 40,180,270,300,13,magnetta);
			
	        JButton voltarButton = new JButton("<-");
	        voltarButton.setBounds(130, 380, 50, 30);
	        voltarButton.setBackground(orchid);
	        voltarButton.setForeground(magnetta);
	        voltarButton.addActionListener(new ActionListener() {
	        	
	        	@Override
	            public void actionPerformed(ActionEvent e) {               
	        		if(e.getSource() == voltarButton) {
	        			dispose();
	        			new TelaMenu();   			
	        		}
	        	}
	              
	        });
	        
	        add(voltarButton);

		}
}
