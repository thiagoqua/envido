import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Interfaz extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*BOTONES PARA CANTAR*/
	
	private JButton flecha;
	private JButton truco;
	private JButton envido;
	private JButton mazo;
	
	/*COMPONENTES DEL CENTRO*/
	
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	
	private JLabel texto;
	private JButton quiero;
	private JButton noQuiero;
	
	private JLabel l4;
	private JLabel l5;
	private JLabel l6;
	
	private JButton c1;
	private JButton c2;
	private JButton c3;
	
	/*COMPONENTES DE LA DERECHA*/
	
	private JLabel pts1;
	private JLabel pts2;
	private JLabel j1;
	private JLabel j2;
	private JLabel img_mano;
	private JLabel img_mazo;
	
	private Border contorno;
	private Border contorno2;
	private Border contorno3;
	
	/*COMPONENTES DE LA VENTANA PRINCIPAL*/
	
	private JLabel portada;
	private JButton jugar;
	private JButton config;
	private JButton salir;
	
	private JButton jugarMaquina;
	private JButton jugarPersona;
	private JButton volver;
	
	private JPanel banner;
	private JLabel cartel;
	
	private Container cp;
	
	private JPanel menu;
	private JPanel union;
	private JPanel unionDer;
	
	public Interfaz() {
		
	
		
	/*CREACION DE VENTANA BASICA*/
		
	setTitle("TRUCO");
	setSize(800,600);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
	
	/*INICIALIZACION DE ALGUNOS COMPONENTES*/
	
	contorno = BorderFactory.createLineBorder(Color.BLUE);
	contorno2 = BorderFactory.createLineBorder(Color.RED);
	contorno3 = BorderFactory.createLineBorder(Color.BLACK);
	
	cp = getContentPane();
	
	cp.add(ventanaPrincipal());
	

	/*CREACION DE MENU DE BOTONES IZQUIERDO*/
	
	flecha = new JButton("<=");
	truco = new JButton("TRUCO");
	envido = new JButton("ENVIDO");
	mazo = new JButton("MAZO");
	
	menu = new JPanel();
	GridLayout columna = new GridLayout(8,1);
	menu.setLayout(columna);
	menu.setPreferredSize(new Dimension(100,600));
	menu.add(new JLabel(""));
	menu.add(new JLabel(""));
	menu.add(flecha);
	menu.add(truco);
	menu.add(envido);
	menu.add(mazo);
	menu.add(new JLabel(""));
	menu.add(new JLabel(""));
	
	/*CREACION DE LOS COMPONENTES CENTRALES*/
	
	l1 = new JLabel();
	l1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/base.jpg")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	l1.setHorizontalAlignment(SwingConstants.CENTER);
	l2 = new JLabel();
	l2.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/base.jpg")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	l2.setHorizontalAlignment(SwingConstants.CENTER);
	l3 = new JLabel();
	l3.setHorizontalAlignment(SwingConstants.CENTER);
	l3.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/base.jpg")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	
	JPanel cartas = new JPanel(new GridLayout(1,3,4,4));
	cartas.add(l1);
	cartas.add(l2);
	cartas.add(l3);
	
	//
	
	JPanel cantos = new JPanel();
	cantos.setLayout(new GridBagLayout());
	
	texto = new JLabel("TRUCO",SwingConstants.CENTER);
	texto.setBorder(contorno3);
	texto.setFont(new Font("Consolas",Font.PLAIN,30));
	quiero = new JButton("QUIERO");
	noQuiero = new JButton("NO QUIERO");
	
	GridBagConstraints constraints = new GridBagConstraints();
	constraints.gridx = 0;
	constraints.gridy = 0;
	constraints.gridwidth = 2;
	constraints.gridheight = 1;
	constraints.fill = GridBagConstraints.HORIZONTAL;
	cantos.add(texto, constraints);
	
	constraints.gridx = 0;
	constraints.gridy = 1;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	cantos.add(quiero, constraints);
	
	constraints.gridx = 1;
	constraints.gridy = 1;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	cantos.add(noQuiero, constraints);
	
	//
	
	l4 = new JLabel();
	l4.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/base.jpg")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	l4.setHorizontalAlignment(SwingConstants.CENTER);
	l5 = new JLabel();
	l5.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/base.jpg")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	l5.setHorizontalAlignment(SwingConstants.CENTER);
	l6 = new JLabel();
	l6.setHorizontalAlignment(SwingConstants.CENTER);
	l6.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/base.jpg")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	
	JPanel cartas2 = new JPanel(new GridLayout(1,3,4,4));
	cartas2.add(l4);
	cartas2.add(l5);
	cartas2.add(l6);
	
	//
	
	c1 = new JButton();
	c1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/base.jpg")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	c2 = new JButton();
	c2.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/base.jpg")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	c3 = new JButton();
	c3.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/base.jpg")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	
	JPanel cartas3 = new JPanel(new GridLayout(1,3,4,4));
	cartas3.add(c1);
	cartas3.add(c2);
	cartas3.add(c3);
	
	//
	
	union = new JPanel();
	union.setLayout(new BoxLayout(union, BoxLayout.Y_AXIS));
	union.add(cartas);
	union.add(cantos);
	union.add(cartas2);
	union.add(cartas3);
	
	
	/*CREACION DE LOS COMPONENTES DERECHOS*/
	
	pts1 = new JLabel("0");
	pts1.setBorder(contorno);
	pts1.setHorizontalAlignment(SwingConstants.CENTER);
	
	pts2 = new JLabel("0");
	pts2.setBorder(contorno2);
	pts2.setHorizontalAlignment(SwingConstants.CENTER);
	
	j1 = new JLabel("TIKI");
	j1.setBorder(contorno);
	j1.setHorizontalAlignment(SwingConstants.CENTER);
	
	j2 = new JLabel("ESTEBAN");
	j2.setBorder(contorno2);
	j2.setHorizontalAlignment(SwingConstants.CENTER);
	
	img_mano = new JLabel();
	img_mano.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mano.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
	img_mano.setBorder(contorno);
	img_mano.setHorizontalAlignment(SwingConstants.CENTER);
	
	img_mazo = new JLabel();
	img_mazo.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/base.jpg")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	img_mazo.setBorder(contorno2);
	img_mazo.setHorizontalAlignment(SwingConstants.CENTER);
	
	JPanel panel_pts = new JPanel(new GridLayout(2,2,4,4));
	panel_pts.add(pts1);
	panel_pts.add(pts2);
	panel_pts.add(j1);
	panel_pts.add(j2);
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel(new GridLayout(1,1,4,4));
	panel2.add(img_mano);
	panel2.add(img_mazo);
	JPanel panel3 = new JPanel();
	
	unionDer = new JPanel(new GridLayout(4,1,4,4));
	unionDer.setPreferredSize(new Dimension(200,600));
	unionDer.add(panel1);
	unionDer.add(panel_pts);
	unionDer.add(panel2);
	unionDer.add(panel3);
	
	ActionListener();
	
	/*CONFIGURACIONES ADICIONALES DE LA VENTANA*/
	
	setResizable(false);
	setVisible(true);
	
	}
	
	
	public JPanel ventanaPrincipal() {
		
		
		/* BOTONES DE VENTANA PRINCIPAL */
		
		//
		
		portada = new JLabel();
		portada.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bannerTruco.png")).getImage().getScaledInstance(315, 210, Image.SCALE_SMOOTH)));

		banner = new JPanel();
		banner.add(portada);
		
		//
		
		jugar = new JButton("JUGAR");
		jugar.setPreferredSize(new Dimension(100,50));
		salir = new JButton("SALIR");
		config = new JButton("CONFIGURACION");
		JPanel inicio = new JPanel(new GridLayout(3,1,4,4));
		inicio.add(jugar);
		inicio.add(config);
		inicio.add(salir);
		
		JPanel principal = new JPanel();
		principal.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		principal.add(banner,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weighty = 1.0;
		principal.add(inicio,gbc);
		
		cartel = new JLabel("2021 TIKI Y ESTEBAN ASOCIADOS. TODOS LOS DERECHOS RESERVADOS.");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		principal.add(cartel,gbc);
		
		return principal;
		
	}
	
	
	public void ActionListener() {
		
		/*ACTION LISTENERS*/
		
		salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		jugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				/* BOTONES DE VENTANA SECUNDARIA */
				
				jugarMaquina = new JButton("JUGAR CONTRA MAQUINA");
				jugarMaquina.setPreferredSize(new Dimension(100,50));
				jugarPersona = new JButton("JUGAR CONTRA PERSONA");
				volver = new JButton("VOLVER");
				JPanel ventanaSec = new JPanel(new GridLayout(3,1,4,4));
				ventanaSec.add(jugarMaquina);
				ventanaSec.add(jugarPersona);
				ventanaSec.add(volver);
				
				JPanel secundario = new JPanel();
				secundario.setLayout(new GridBagLayout());
				GridBagConstraints gbc2 = new GridBagConstraints();
				
				gbc2.gridx = 0;
				gbc2.gridy = 0;
				gbc2.gridwidth = 1;
				gbc2.gridheight = 1;
				secundario.add(banner,gbc2);
				
				gbc2.gridx = 0;
				gbc2.gridy = 1;
				gbc2.gridwidth = 1;
				gbc2.gridheight = 1;
				gbc2.weighty = 1.0;
				secundario.add(ventanaSec,gbc2);
				
				gbc2.gridx = 0;
				gbc2.gridy = 2;
				gbc2.gridwidth = 1;
				gbc2.gridheight = 1;
				secundario.add(cartel,gbc2);
				
				cp.removeAll();
				cp.revalidate();	//Debe invocarse cuando los subcomponentes del contenedor se modifican
				cp.repaint();
				cp.add(secundario);
				
				jugarMaquina.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						cp.removeAll();
						cp.revalidate();
						cp.repaint();
						cp.add(menu,BorderLayout.WEST);
						cp.add(union,BorderLayout.CENTER);
						cp.add(unionDer,BorderLayout.EAST);
						
					}
				});
				
				
				volver.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						/*CONTENEDOR PRINCIPAL*/
						
						cp.removeAll();
						cp.revalidate();
						cp.repaint();
						cp.add(ventanaPrincipal());
						
						ActionListener();
						
					}
				});	//FIN ACTION-LISTENER VOLVER
				
			}	//FIN ACTION-PERFORMED JUGAR	
			
		});		//FIN ACTION-LISTENER JUGAR
		
	}	//FIN FUNCION ACTION-LISTENER
	
}