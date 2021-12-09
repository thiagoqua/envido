import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Interfaz extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	/*BOTONES PARA CANTAR*/
	
	private JButton flecha;
	private JButton flecha2;
	
	private JButton truco;
	private JButton envido;
	private JButton mazo;
	private JButton rendirse;
	
	private JButton cantarTruco;
	private JButton retruco;
	private JButton vale_4;
	
	private JButton cantarEnvido;
	private JButton real_envido;
	private JButton falta_envido;
	
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
	private JButton ctemp;
	
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
	
	/*COMPONENTES DE JUGAR, LUEGO DE CLICKEAR*/
	
	private JButton jugarMaquina;
	private JButton jugarPersona;
	private JButton volver;
	
	private JPanel banner;
	private JLabel cartel;
	
	/*COMPONENTES DE CONFIGURACION, LUEGO DE CLICKEAR*/
	
	private JLabel ingrese_nombre;
	private JTextField texto_nombre;
	private JButton ok; 
	
	/*CONTENEDORES DE LA VENTANA PRINCIPAL*/
	
	private Container cp;
	
	private JPanel menu;
	private JPanel menuCantosE;
	private JPanel menuCantosT;
	private JPanel union;
	private JPanel unionDer;
	
	/*OBJETOS DE OTRAS CLASES*/
	
	private Jugador j;
	private IA MAQUINA;
	private Mazo mazoCartas;
	private Carta cartaPalo;
	
	/*OBJETOS NECESARIOS PARA LOS JUGADORES E IA*/
	
	private String cantado[];	//acumula los cantos sucesivos
		
    private Carta tiraJ;		
    private Carta tiraIA[];		//guarda las cartas que tira la IA
    private Carta tiratmp[];
	private boolean accionUsuario;	//permite trabar o destrabar la IA cuando espera la respuesta del jugador
	private boolean turno;	//para saber cuando le toca a la IA y cuando esta luego debe esperar la respuesta del jugador
    private boolean jugando;
	
    private Carta auxJ;	//guarda una carta de jugador para comparar
    private Carta auxIA; //guarda una carta de IA para comparar
    private Carta aux; //es utilizado como base para las comparaciones de auxJ y auxIA
    private String acumulador;
    
    private boolean cantoEnvido;
    private boolean envidoNo;
    
    private boolean tiraIAnull;
    //
    
    
	
	public Interfaz() {
	
	/*INICIALIZACION DE OBJETOS DE OTRAS CLASES*/
	
	j = new Jugador();
	MAQUINA = new IA();
	mazoCartas = new Mazo();
	cartaPalo = new Carta();
	
	cantado = new String[5];	//acumula los cantos sucesivos
	cantado = new String[5];
    tiraJ = new Carta();
    tiraIA = new Carta[2];
    tiratmp = new Carta[1];
    
    accionUsuario = false;
	turno = true;
	jugando = true;
	
	auxJ = new Carta();
    auxIA = new Carta();
    aux = new Carta();
    
    tiraIAnull = false;
    acumulador = "";
    envidoNo = true;
	
	/*CREACION DE VENTANA BASICA*/
	setIconImage(new ImageIcon(getClass().getResource("/images/icon.jpg")).getImage());
	setTitle("TRUCO");
	setSize(800,600);			//SE PUEDE CAMBIAR A 1200x900 Y MANTENER BIEN LA ESTRUCTURA (AUNQUE HABRIA QUE CAMBIAR EL TAMA�O DE LAS IMAGENES)
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
	
	/*INICIALIZACION DE ALGUNOS COMPONENTES*/
	
	contorno = BorderFactory.createLineBorder(Color.BLUE);
	contorno2 = BorderFactory.createLineBorder(Color.RED);
	contorno3 = BorderFactory.createLineBorder(Color.BLACK);
	
	texto_nombre = new JTextField("");
	
	cp = getContentPane();
	
	cp.add(ventanaPrincipal());
	

	/*CREACION DE MENU DE BOTONES IZQUIERDO*/
	
	flecha = new JButton("<=");
	flecha2 = new JButton("<=");
	
	truco = new JButton("TRUCO");
	envido = new JButton("ENVIDO");
	mazo = new JButton("MAZO");
	rendirse = new JButton("RENDIRSE");
	
	cantarTruco = new JButton("TRUCO");
	retruco = new JButton("RETRUCO");
	vale_4 = new JButton("VALE 4");
	
	cantarEnvido = new JButton("ENVIDO");
	real_envido = new JButton("REAL ENVIDO");
	falta_envido = new JButton("FALTA ENVIDO");
	
	menu = new JPanel();
	GridLayout columna = new GridLayout(8,1);
	menu.setLayout(columna);
	menu.setPreferredSize(new Dimension(100,600));
	menu.add(new JLabel(""));
	menu.add(new JLabel(""));
	menu.add(truco);
	menu.add(envido);
	menu.add(mazo);
	menu.add(rendirse);
	menu.add(new JLabel(""));
	menu.add(new JLabel(""));
	
	menuCantosE = new JPanel();
	GridLayout columna2 = new GridLayout(8,1);
	menuCantosE.setLayout(columna2);
	menuCantosE.setPreferredSize(new Dimension(100,600));
	menuCantosE.add(new JLabel(""));
	menuCantosE.add(new JLabel(""));
	menuCantosE.add(flecha);
	menuCantosE.add(cantarEnvido);
	menuCantosE.add(real_envido);
	menuCantosE.add(falta_envido);
	menuCantosE.add(new JLabel(""));
	menuCantosE.add(new JLabel(""));
	
	menuCantosT = new JPanel();
	GridLayout columna3 = new GridLayout(8,1);
	menuCantosT.setLayout(columna3);
	menuCantosT.setPreferredSize(new Dimension(100,600));
	menuCantosT.add(new JLabel(""));
	menuCantosT.add(new JLabel(""));
	menuCantosT.add(flecha2);
	menuCantosT.add(cantarTruco);
	menuCantosT.add(retruco);
	menuCantosT.add(vale_4);
	menuCantosT.add(new JLabel(""));
	menuCantosT.add(new JLabel(""));
	
	/*CREACION DE LOS COMPONENTES CENTRALES*/
	
	l1 = new JLabel();
	//l1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blanco.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	l1.setHorizontalAlignment(SwingConstants.CENTER);
	l2 = new JLabel();
	//l2.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blanco.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	l2.setHorizontalAlignment(SwingConstants.CENTER);
	l3 = new JLabel();
	l3.setHorizontalAlignment(SwingConstants.CENTER);
	//l3.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blanco.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	
	JPanel cartas = new JPanel(new GridLayout(1,3,4,4));
	cartas.add(l1);
	cartas.add(l2);
	cartas.add(l3);
	
	//
	
	JPanel cantos = new JPanel();
	cantos.setLayout(new GridBagLayout());
	

	texto = new JLabel("<html>"+ "" +"</html>",SwingConstants.CENTER);
	texto.setPreferredSize(new Dimension(300,30));
	texto.setBorder(contorno3);
	texto.setFont(new Font("Consolas",Font.PLAIN,30));
	
	quiero = new JButton("QUIERO");
	quiero.setBackground(Color.GREEN);
	quiero.setPreferredSize(new Dimension(150,30));
	noQuiero = new JButton("NO QUIERO");
	noQuiero.setBackground(Color.RED);
	noQuiero.setPreferredSize(new Dimension(150,30));
	
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
	//l4.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blanco.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	l4.setHorizontalAlignment(SwingConstants.CENTER);
	l5 = new JLabel();
	//l5.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blanco.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	l5.setHorizontalAlignment(SwingConstants.CENTER);
	l6 = new JLabel();
	l6.setHorizontalAlignment(SwingConstants.CENTER);
	//l6.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blanco.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	
	JPanel cartas2 = new JPanel(new GridLayout(1,3,4,4));
	cartas2.add(l4);
	cartas2.add(l5);
	cartas2.add(l6);
	
	//
	ctemp = new JButton();
	c1 = new JButton();
	c2 = new JButton();
	c3 = new JButton();
	
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
	
	j1 = new JLabel("");
	j1.setBorder(contorno);
	j1.setHorizontalAlignment(SwingConstants.CENTER);
	
	j2 = new JLabel("");
	j2.setBorder(contorno2);
	j2.setHorizontalAlignment(SwingConstants.CENTER);
	
	img_mano = new JLabel();
	img_mano.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mazo.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
	img_mano.setBorder(contorno);
	img_mano.setHorizontalAlignment(SwingConstants.CENTER);
	
	img_mazo = new JLabel();
	img_mazo.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mano.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
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
		
		cartel = new JLabel("� 2021 TIKI Y ESTEBAN ASOCIADOS. TODOS LOS DERECHOS RESERVADOS.");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		principal.add(cartel,gbc);
		
		return principal;
		
	}
	
	//
	
	public void ActionListener() {
		
		/*ACTION LISTENERS*/
		
		salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		//
		
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
						
						/*SE CREA LA INTERFAZ DE JUEGO*/
						
						cp.removeAll();
						cp.revalidate();
						cp.repaint();
						cp.add(menu,BorderLayout.WEST);
						cp.add(union,BorderLayout.CENTER);
						cp.add(unionDer,BorderLayout.EAST);
						
						/*SE SETEAN ALGUNAS CONFIGS PEQUE�AS*/
						
						j1.setText(texto_nombre.getText());			//SE ESTABLECE EL NOMBRE ESCRITO POR EL JUGADOR
						j2.setText("BOT EASY");						//NOMBRE DE LA IA
						
						//OTROS BOTONES DESACTIVADOS (PORQUE SIEMPRE EMPIEZA LA IA)
						//CUANDO LA IA TIRA, DEBEN ACTIVARSE. CUANDO LA IA JUEGA, EST�N DESACTIVADOS.
						
						quiero.setEnabled(false);
						noQuiero.setEnabled(false);
						truco.setEnabled(false);
						envido.setEnabled(false);
						mazo.setEnabled(false);
						
						//
						
						
						c1.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {		
								tirarCartaEnMesa(c1);
//								c1.setEnabled(false);		//TENER CUIDADO CON ESTO POR LAS DUDAS, PORQUE EN EL CODIGO TAMBIEN SE HACEN TRUE O FALSE
								accionUsuario = true;
							}
						});
						
						c2.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								tirarCartaEnMesa(c2);
//								c2.setEnabled(false);
								accionUsuario = true;
							}
						});
						
						c3.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								tirarCartaEnMesa(c3);
//								c3.setEnabled(false);
								accionUsuario = true;
							}
						});
						
						rendirse.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								JFrame confirmar = new JFrame();
						        
								if(j.getPuntos() >= 30 || MAQUINA.getPuntos() >= 30) {
									
									cp.removeAll();
									cp.revalidate();
									cp.repaint();
									cp.add(ventanaPrincipal());
									
									ActionListener();			//CHEQUEAR QUE HACE ESTE ACTION-LISTENER 
									
								}
								else {
								
									int result = JOptionPane.showConfirmDialog(confirmar, "�Esta seguro que desea salir? El juego se daria como perdido.");
	
							        if (result == 0) {
							        	cp.removeAll();
										cp.revalidate();
										cp.repaint();
										cp.add(ventanaPrincipal());
										
										ActionListener();	
							        }
							        
							        else if (result == 1) {}
							        else {}

								}
							}
						});		//FIN RENDIRSE
						
						flecha.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								//creo el menu original
								cp.remove(menuCantosE);
								cp.add(menu,BorderLayout.WEST);
								cp.revalidate();
								cp.repaint();
								
							}
						});
						
						flecha2.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								//creo el menu original
								cp.remove(menuCantosT);
								cp.add(menu,BorderLayout.WEST);
								cp.revalidate();
								cp.repaint();
								
							}
						});
						

						cantarTruco.addActionListener(new ActionListener(){
							
							@Override
							public void actionPerformed(ActionEvent e) {
//								for(int i=0;i<5;i++) {
//									cantado[i] = null;
//								}
								cantado[0] = "truco";
								texto.setText("<html>"+ cantado[0] +"</html>");
								envidoNo = true;
								accionUsuario = true;
							}
							
						});
						
						
						retruco.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								int i;
								for(i=0;i<5;i++) {
									if(cantado[i]==null || cantado[i]=="") {
										cantado[i] = "retruco";
										break;
									}
								}	
								texto.setText("<html>"+ cantado[i] +"</html>");
								accionUsuario = true;
							}
						});
						
						vale_4.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								int i;
								for(i=0;i<5;i++) {
									if(cantado[i]==null || cantado[i]=="") {
										cantado[i] = "vale cuatro";
										break;
									}
								}	
								texto.setText("<html>"+ cantado[i] +"</html>");
								accionUsuario = true;
							}
						});
						
						truco.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								flecha2.setEnabled(true);
																
								cp.remove(menu);
								cp.add(menuCantosT,BorderLayout.WEST);
								cp.revalidate();
								cp.repaint();
								
								//LOS LISTENERS DEL TRUCO SE CREAN APARTE, AFUERA DEL LISTENER PRINCIPAL, PORQUE SINO GENERA ERRORES
							}
						});
						
						//NOTA: NO PUEDO PONER A NULL EL ARREGLO, PORQUE EXISTE EL ENVIDO-ENVIDO
						cantarEnvido.addActionListener(new ActionListener(){
							
							@Override
							public void actionPerformed(ActionEvent e) {
								int i;
								for(i=0;i<5;i++) {
									if(cantado[i]==null || cantado[i]=="") {
										cantado[i] = "envido";
										break;
									}
								}
								texto.setText("<html>"+ cantado[i] +"</html>");
								envidoNo = false;
								accionUsuario = true;
							}
							
						});
						
						real_envido.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								int i;
								for(i=0;i<5;i++) {
									if(cantado[i]==null || cantado[i]=="") {
										cantado[i] = "real envido";
										break;
									}
								}	
								texto.setText("<html>"+ cantado[i] +"</html>");
								envidoNo = false;
								accionUsuario = true;
							}
						});
						
						falta_envido.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								int i;
								for(i=0;i<5;i++) {
									if(cantado[i]==null || cantado[i]=="") {
										cantado[i] = "falta envido";
										break;
									}
								}	
								texto.setText("<html>"+ cantado[i] +"</html>");
								envidoNo = false;
								accionUsuario = true;
							}
						});
						
						envido.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								flecha.setEnabled(true);
								
								//creo el menu con los envidos 
								
								cp.remove(menu);
								cp.add(menuCantosE,BorderLayout.WEST);
								cp.revalidate();
								cp.repaint();
								
								//LOS LISTENERS DEL ENVIDO SE CREAN APARTE, AFUERA DEL LISTENER PRINCIPAL, PORQUE SINO GENERA ERRORES
												
							}
						});
						
						quiero.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								if(cantado[0] != null && cantado[0]!="") {		//PARA TRUCO Y ENVIDO
									for(int i=0; i<5 ;i++) {
										if(cantado[i]==null || cantado[i]=="") {
											cantado[i] = "quiero";
											break;
										}
									}
								}
								
								accionUsuario = true;
								quiero.setEnabled(false);
								noQuiero.setEnabled(false);
							}
						});
						
						noQuiero.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								if(cantado[0] != null && cantado[0]!="") {		//PARA TRUCO Y ENVIDO
									for(int i=0; i<5 ;i++) {
										if(cantado[i]==null || cantado[i]=="") {
											cantado[i] = "no quiero";
											break;
										}
									}
								}
								
								accionUsuario = true;
								noQuiero.setEnabled(false);
								quiero.setEnabled(false);
							}
						});
						
						
						/** A PARTIR DE ACA DEBE HABER UN WHILE (O UN THREAD) QUE ENVUELVA/REPITA TODO HASTA QUE ALGUNO DE LOS DOS GANE*/
					    
						acumulador = "";
						
			    		for(int i=0;i<5;i++) {
			        		cantado[i] = "";
			        	}
			    		
						//VARIAR ENTRE IA Y JUGADOR, PORQUE LAS RONDAS IMPARES ARRANCA LA IA Y LAS PARES EL JUGADOR
						
				        Thread juego = new Thread() {
							
					    	@Override
					    	public void run() {
					    		
					    		while(jugando) {
					    			
									/*SE REPARTEN CARTAS PARA JUGADOR E IA*/
									
								    for(int i=0;i<3;++i){
								            MAQUINA.cartas[i] = mazoCartas.sacar();
											MAQUINA.copyCartas[i] = MAQUINA.cartas[i];
								    		j.cartas[i] = mazoCartas.sacar();
											j.copyCartas[i] = j.cartas[i];            
								    }
								 	
							        for(Carta x : MAQUINA.cartas)
							            System.out.println(x + "\n");
					    			
					    			/*SE VISUALIZAN LAS CARTAS DEL JUGADOR EN PANTALLA*/
					    
					    			mostrarCartasJugador();

					    			/*ESTE HILO EJECUTARA LA ACCION DE LA MAQUINA (TIRAR CARTAS, ETC.)*/

									Thread IAThread = new Thread(){
							             
										@Override
							            public void run() {
											while(turno) {	
												
												/*LA IA EMPIEZA (RONDA 1) */
												 
									    		MAQUINA.setBandera(true);				//nota: cambiarlo por una variable
												MAQUINA.activatePuedoCantarEnvido(true);		//nota: decidir si quiero que sea true o false
												tiraIA = MAQUINA.yourTurn(cantado,null);
												auxIA = tiraIA[0];
												
												System.out.println("\nTira:");
												System.out.println(tiraIA[0]);
												
												System.out.println("\nCantado:");
										        for(int i = 0;cantado[i] != "";++i) {
										            System.out.println(cantado[i]);
										        }
												
										        /* CANTA ENVIDO (SI TIENE) */
										        
										        if(cantado[0] != null && cantado[0]!="") {
										        	if(cantado[0].equals("envido") || cantado[0].equals("real envido") || cantado[0].equals("falta envido")) {
														
										        		if(cantado[0].equals("falta envido")) {
										        			envido.setEnabled(false);
										        		}
										        		else if (cantado[0].equals("real envido")){
										        			envido.setEnabled(true);
										        			cantarEnvido.setEnabled(false);
										        			real_envido.setEnabled(false);
										        		}
										        		else {
										        			envido.setEnabled(true);
										        		}
										        		
										        		texto.setText("<html>"+ cantado[0] +"</html>");
														quiero.setEnabled(true);
														noQuiero.setEnabled(true);
																												
											    		c1.setEnabled(false);
											    		c2.setEnabled(false);
											    		c3.setEnabled(false);
											    		
											    		Thread espero = new Thread() {
											    			
											    			@Override
											    			public void run() {
													    		
											    				System.out.print("\nEspero que el jugador quiera/no quiera el envido o revire ");
											    				while(accionUsuario == false) {
											    					try {
														    			System.out.print(". ");
														    			Thread.sleep(1000);
																	} catch (InterruptedException e) {
																		e.printStackTrace();
																	}
													    		}
													    		System.out.println("\nRetomo la ejecucion\n");
											    			}
											    			
											    		};
											    		
														espero.start();			
														
														try {
															espero.join();					
//															Thread.sleep(1000);
														}catch(InterruptedException e) {}
											    		
											    		accionUsuario = false;
											    		
											    		//SI EL JUGADOR QUIERE
											    		
											    		if(cantado[1].equals("quiero")) {
											    			//SE SUMAN PUNTOS
											    			
										    				sistPuntuacion(cantado,MAQUINA,j);
										    				acumulador = String.valueOf(j.getPuntos());
										    				pts1.setText(acumulador);
											    			acumulador = "";
											    			acumulador = String.valueOf(MAQUINA.getPuntos());
											    			pts2.setText(acumulador);
											    			
											    			reiniciarMenuCantos();
											    			
											    			puntosMaximosSuperados();
											    			
											    		}
											    		
											    		/*SI EL JUGADOR NO QUIERE*/
											    		
											    		else if(cantado[1].equals("no quiero")) {
											    			
											    			sistPuntuacion(cantado,MAQUINA,j);
											    			acumulador = String.valueOf(MAQUINA.getPuntos());
										    				pts2.setText(acumulador);
											    			
										    				reiniciarMenuCantos();
										    				
										    				puntosMaximosSuperados();
										    				
											    		}
											    		
											    		/*SI EL JUGADOR REVIRA*/
											    		
											    		else if(cantado[1].equals("envido") || cantado[1].equals("real envido") || cantado[1].equals("falta envido")) {
											    			
											    			if(cantado[1].equals("envido")) {
											    				cantarEnvido.setEnabled(false);
											    				
											    			}
											    			else if(cantado[1].equals("real envido")) {
											    				cantarEnvido.setEnabled(false);
											    				real_envido.setEnabled(false);
											    			}
											    			else {
											    				cantarEnvido.setEnabled(false);
											    				real_envido.setEnabled(false);
											    				falta_envido.setEnabled(false);
											    			}
											    			
											    			MAQUINA.yourTurnAccept(cantado);
											    			
											    			//SI LA IA QUIERE
											    			
											    			if(cantado[2].equals("quiero")) {
											    				
											    				/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
											    				System.out.println("la IA quiso");
											    				/***/
											    				
											    				sistPuntuacion(cantado,MAQUINA,j);
											    				acumulador = String.valueOf(j.getPuntos());
											    				pts1.setText(acumulador);
											    				acumulador = "";
											    				acumulador = String.valueOf(MAQUINA.getPuntos());
											    				pts2.setText(acumulador);											    				
											    				
											    				reiniciarMenuCantos();
											    				
											    				puntosMaximosSuperados();
											    				
											    			}
											    			
											    			//SI LA IA NO QUIERE
											    			
											    			else if(cantado[2].equals("no quiero")) {
											    				
											    				/**PROVISORIO*/
											    				System.out.println("la IA no quiso");
											    				/***/
											    				
											    				sistPuntuacion(cantado,MAQUINA,j);
											    				acumulador = String.valueOf(j.getPuntos());
											    				pts1.setText(acumulador);
											    				
											    				reiniciarMenuCantos();
											    				
											    				puntosMaximosSuperados();
											    				
											    			}
											    			
											    			//SI LA IA REVIRA, CREO OTRO HILO DE ESPERA
											    			
											    			else if(cantado[2].equals("real envido") || cantado[2].equals("falta envido")) {
											    				
											    				/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
											    				
											    				System.out.println("la IA revira");
											    				
											    				/***/
											    				
											    				texto.setText("<html>"+ cantado[2] +"</html>");
																quiero.setEnabled(true);
																noQuiero.setEnabled(true);
											    				
											    				if(cantado[2].equals("real envido")) {
											    					envido.setEnabled(true);
											    					real_envido.setEnabled(false);
											    					falta_envido.setEnabled(true);
											    				}
											    				else {
											    					envido.setEnabled(false);
											    				}
											    				
													    		Thread espero2 = new Thread() {
													    			
													    			@Override
													    			public void run() {
															    		
													    				System.out.print("\nEspero que el jugador quiera/no quiera - revire (2) ");
													    				while(accionUsuario == false) {
													    					try {
																    			System.out.print(". ");
																    			Thread.sleep(1000);
																			} catch (InterruptedException e) {
																				e.printStackTrace();
																			}
															    		}
															    		System.out.println("\nRetomo la ejecucion\n");
													    			}
													    			
													    		};
												    			
													    		espero2.start();			//nota: ver donde ponerlo si no funca
													    		
																try {
																	espero2.join();					
//																	Thread.sleep(1000);
																}catch(InterruptedException e) {}
													    		
																//SI EL JUGADOR QUIERE
																
																if(cantado[3].equals("quiero")) {
																	
																	//SE SUMAN PUNTOS
														
																	sistPuntuacion(cantado,MAQUINA,j);
																	acumulador = String.valueOf(j.getPuntos());
																	pts1.setText(acumulador);
																	acumulador = "";
																	acumulador = String.valueOf(MAQUINA.getPuntos());
																	pts2.setText(acumulador);
																	
																	reiniciarMenuCantos();
																	
																	puntosMaximosSuperados();
																	
																}
																
																//SI EL JUGADOR NO QUIERE
																
																else if(cantado[3].equals("no quiero")) {
																	
																	sistPuntuacion(cantado,MAQUINA,j);
																	acumulador = String.valueOf(MAQUINA.getPuntos());
																	pts2.setText(acumulador);
																	
																	reiniciarMenuCantos();
																	
																	puntosMaximosSuperados();
																	
																}
																
																//SI EL JUGADOR REVIRA
																
																else if(cantado[3].equals("falta envido")) {
																	
																	MAQUINA.yourTurnAccept(cantado);
																	
																	//SI LA IA QUIERE
																	
																	if(cantado[4].equals("quiero")) {
																		
																		/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
																		System.out.println("la IA quiso");
																		/***/
																		
																		sistPuntuacion(cantado,MAQUINA,j);
																		acumulador = String.valueOf(j.getPuntos());
																		pts1.setText(acumulador);
																		acumulador = "";
																		acumulador = String.valueOf(MAQUINA.getPuntos());
																		pts2.setText(acumulador);
																		
																		reiniciarMenuCantos();
																		
																		puntosMaximosSuperados();
																		
																	}
																	
																	else if(cantado[4].equals("no quiero")){
																		
																		/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
																		System.out.println("la IA no quiso");
																		/***/
																		
																		sistPuntuacion(cantado,MAQUINA,j);
																		acumulador = String.valueOf(j.getPuntos());
																		pts1.setText(acumulador);

																		reiniciarMenuCantos();
																		puntosMaximosSuperados();
																		
																	}
																	
																}
																
											    			}
											    			
											    		}
											    		
													}
										        	
										        	/*CUANDO YA SE CANTO ENVIDO EL ARREGLO DE CANTADOS SE PONE A NULL PARA EL TRUCO*/
										        	
										        	texto.setText("<html>"+ "" +"</html>");
										        	
										        	for(int i=0;i<5;i++) {
										        		cantado[i] = "";
										        	}
										        										        	
										        	mostrarTirada(tiraIA,tiraIAnull);
										        	truco.setEnabled(true);
										        	envido.setEnabled(false);
										        	cantoEnvido = true;
										        	
											        c1.setEnabled(true);
											        c2.setEnabled(true);
											        c3.setEnabled(true);
											        
											        quiero.setEnabled(false);
											        noQuiero.setEnabled(false);
											        accionUsuario = false;
										        	
											        MAQUINA.setBandera(false);
											        
										        }	//FIN IF DEL CANTO DE ENVIDO
										        
										        for(int i=0;i<5;i++) {
									        		cantado[i] = "";
									        	}
										        
										        /*SI LA IA CANTO ENVIDO ENTONCES SE DESACTIVA EL BOTON, SINO SE MANTIENE ACTIVO*/
											        
									        	if(cantoEnvido == true) {
									        		envido.setEnabled(false);
									        	}
									        	else {
									        		envido.setEnabled(true);
									        	}
									        
									        	/*SI NO TIENE PARA EL ENVIDO TIRA LA CARTA DIRECTAMENTE*/
									        	
									        	mostrarTirada(tiraIA,tiraIAnull);
									        	auxIA = tiraIA[0];					//esto me sirve para comparar las cartas cuando el jugador tire sin cantar (en la primera)
										        
									        	truco.setEnabled(true);
									        	flecha2.setEnabled(true);
									        	retruco.setEnabled(false);
									        	vale_4.setEnabled(false);
									        	
										        c1.setEnabled(true);
										        c2.setEnabled(true);
										        c3.setEnabled(true);
										        
										        quiero.setEnabled(false);
										        noQuiero.setEnabled(false);
											        
									        	accionUsuario = false;
										        Thread espero3 = new Thread() {
									    			
									    			@Override
									    			public void run() {
											    		
									    				System.out.print("\nEspero que el jugador tire carta / me cante truco/envido y concluya su turno ");
									    				while(accionUsuario == false) {
									    					try {
												    			System.out.print(". ");
												    			Thread.sleep(1000);
															} catch (InterruptedException e) {
																e.printStackTrace();
															}
											    		}
											    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
									    			}
									    			
									    		};
								    			
									    		espero3.start();	
									    		
									    		try {
													espero3.join();					
//														Thread.sleep(1000);
												}catch(InterruptedException e) {}
									    		
										    		
										    		
									    		/*EL JUGADOR PUEDE CANTAR ENVIDO (SI NO SE CANTO PREVIAMENTE)*/
									    		//NOTA: la IA nunca va a querer cuando arranca ella, pero si puede querer cuando arranca la ronda el jugador
									    		//este codigo nunca se va a ejecutar si la IA empieza, pero puede servir para cuando el jugador inicie la ronda
										    	//IMPORTANTE: ahora que se implemento la mentira, puede servir.
									    		
									    		if(cantado[0].equals("envido") || cantado[0].equals("real envido") || cantado[0].equals("falta envido")) {
									    			
									    			texto.setText("<html>"+ cantado[0] +"</html>");
													quiero.setEnabled(false);
													noQuiero.setEnabled(false);
													
													c1.setEnabled(false);
										    		c2.setEnabled(false);
										    		c3.setEnabled(false);
										    		truco.setEnabled(false);
										    		
										    		flecha.setEnabled(false);
										    		cantarEnvido.setEnabled(false);
										    		real_envido.setEnabled(false);
										    		falta_envido.setEnabled(false);
										    		
										    		MAQUINA.yourTurnAccept(cantado);
										    		
										    		accionUsuario = false;
										    		
										    		/*SI LA IA QUIERE*/
										    		
										    		if(cantado[1].equals("quiero")) {
										    			
										    			/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
									    				System.out.println("la IA quiso");
									    				/***/
										    			
										    			//SE SUMAN PUNTOS
										    			
									    				sistPuntuacion(cantado,j,MAQUINA);
									    				acumulador = String.valueOf(j.getPuntos());
									    				pts1.setText(acumulador);
									    				acumulador = "";
									    				acumulador = String.valueOf(MAQUINA.getPuntos());
									    				pts2.setText(acumulador);
														
										    			reiniciarMenuCantos();
										    			
										    			puntosMaximosSuperados();
														
														envido.setEnabled(false);
														
										    		}
										    		
										    		/*SI LA IA NO QUIERE*/
										    		
										    		else if(cantado[1].equals("no quiero")) {
										    			
										    			/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
									    				System.out.println("la IA no quiso");
									    				/***/
									    				
									    				sistPuntuacion(cantado,j,MAQUINA);
									    				acumulador = String.valueOf(j.getPuntos());
									    				pts1.setText(acumulador);
									    				
									    				reiniciarMenuCantos();
									    				puntosMaximosSuperados();
									    				
														envido.setEnabled(false);
									    				
										    		}
										    		
										    		/*SI LA IA REVIRA*/
										    		
										    		else if(cantado[1].equals("envido") || cantado[1].equals("real envido") || cantado[1].equals("falta envido")) {
										    			
														/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
									    				
														System.out.println("la IA revira");
									    			
									    				/***/
										    			
										    			if(cantado[1].equals("envido")) {
										    				
										    				quiero.setEnabled(true);
										    				noQuiero.setEnabled(true);
										    				
										    				cantarEnvido.setEnabled(false);
										    				real_envido.setEnabled(true);
										    				falta_envido.setEnabled(true);
										    				
										    				Thread espero4 = new Thread() {
												    			
												    			@Override
												    			public void run() {
														    		
												    				System.out.print("\nEspero que el jugador concluya su turno ");
												    				while(accionUsuario == false) {
												    					try {
															    			System.out.print(". ");
															    			Thread.sleep(1000);
																		} catch (InterruptedException e) {
																			e.printStackTrace();
																		}
														    		}
														    		System.out.println("\nRetomo la ejecucion (me toca a mi)\n");
												    			}
												    			
												    		};
											    			
												    		espero4.start();	
												    		
												    		try {
																espero4.join();					
//																	Thread.sleep(1000);
															}catch(InterruptedException e) {}
										    				
												    		/* SI EL JUGADOR QUIERE */
												    		
												    		if(cantado[2].equals("quiero")) {
												    			//SE SUMAN PUNTOS
												    			
												    			sistPuntuacion(cantado,j,MAQUINA);
												    			acumulador = String.valueOf(j.getPuntos());
												    			pts1.setText(acumulador);
												    			acumulador = "";
												    			acumulador = String.valueOf(MAQUINA.getPuntos());
												    			pts2.setText(acumulador);
												    			
												    			reiniciarMenuCantos();
												    			puntosMaximosSuperados();
												    			
																envido.setEnabled(false);
																
												    		}
												    		
												    		/*SI EL JUGADOR NO QUIERE*/
												    		
												    		else if(cantado[2].equals("no quiero")) {
												    			//SE SUMAN PUNTOS
												    			
												    			sistPuntuacion(cantado,j,MAQUINA);
												    			acumulador = String.valueOf(MAQUINA.getPuntos());
												    			pts2.setText(acumulador);
												    			
												    			reiniciarMenuCantos();
												    			puntosMaximosSuperados();
												    			
												    			envido.setEnabled(false);
												    		}
												    		
												    		/* SI EL JUGADOR REVIRA */
												    		
												    		else if(cantado[2].equals("real envido") || cantado[2].equals("falta envido")) {
												    			
												    			real_envido.setEnabled(false);
												    			falta_envido.setEnabled(false);
												    			
												    			MAQUINA.yourTurnAccept(cantado);
												    			
												    			accionUsuario = false;
												    			
												    			//SI LA IA QUIERE
													    		
													    		if(cantado[3].equals("quiero")) {
													    			
																	/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
												    				System.out.println("la IA quiso");
												    				/***/
													    			
													    			//SE SUMAN PUNTOS
													    			
												    				sistPuntuacion(cantado,j,MAQUINA);
												    				acumulador = String.valueOf(j.getPuntos());
												    				pts1.setText(acumulador);
												    				acumulador = "";
												    				acumulador = String.valueOf(MAQUINA.getPuntos());
												    				pts2.setText(acumulador);
																	
																	reiniciarMenuCantos();
																	puntosMaximosSuperados();
																	
																	envido.setEnabled(false);
																	
													    		}
													    		
													    		/*SI LA IA NO QUIERE*/
													    		
													    		else if(cantado[3].equals("no quiero")) {
													    			
													    			/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
												    				System.out.println("la IA quiso");
												    				/***/
													    			
													    			//SE SUMAN PUNTOS
													    			
												    				sistPuntuacion(cantado,j,MAQUINA);
												    				acumulador = String.valueOf(j.getPuntos());
												    				pts1.setText(acumulador);
													    			
													    			reiniciarMenuCantos();
													    			puntosMaximosSuperados();
													    			
													    			envido.setEnabled(false);
													    		}
													    		
													    		/*SI LA IA REVIRA*/
													    		
													    		else if(cantado[3].equals("falta envido")) {
													    			
																	/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
												    				
																	System.out.println("la IA revira");
												    		
												    				/***/
													    			
													    			quiero.setEnabled(true);
												    				noQuiero.setEnabled(true);
													    			
													    			Thread espero5 = new Thread() {
														    			
														    			@Override
														    			public void run() {
																    		
														    				System.out.print("\nEspero que el jugador concluya su turno ");
														    				while(accionUsuario == false) {
														    					try {
																	    			System.out.print(". ");
																	    			Thread.sleep(1000);
																				} catch (InterruptedException e) {
																					e.printStackTrace();
																				}
																    		}
																    		System.out.println("\nRetomo la ejecucion (me toca a mi)\n");
														    			}
														    			
														    		};
													    			
														    		espero5.start();	
														    		
														    		try {
																		espero5.join();					
//																			Thread.sleep(1000);
																	}catch(InterruptedException e) {}
													    			
														    		/* SI EL JUGADOR QUIERE */
														    		
														    		if(cantado[4].equals("quiero")) {
														    			//SE SUMAN PUNTOS
														    			
														    			sistPuntuacion(cantado,j,MAQUINA);
														    			acumulador = String.valueOf(j.getPuntos());
														    			pts1.setText(acumulador);
														    			acumulador = "";
														    			acumulador = String.valueOf(MAQUINA.getPuntos());
														    			pts2.setText(acumulador);
														    			
														    			reiniciarMenuCantos();
														    			puntosMaximosSuperados();
														    			
																		envido.setEnabled(false);
																		
														    		}
														    		
														    		/*SI EL JUGADOR NO QUIERE*/
														    		
														    		else {
														    			//SE SUMAN PUNTOS
														    			
														    			sistPuntuacion(cantado,j,MAQUINA);
														    			acumulador = String.valueOf(MAQUINA.getPuntos());
														    			pts2.setText(acumulador);
														    			
														    			reiniciarMenuCantos();
														    			puntosMaximosSuperados();
														    			
														    			envido.setEnabled(false);
														    		}
														    		
													    		}
												    			
												    		}
												    		
										    			}
										    			else if(cantado[1].equals("real envido")) {
										    				
										    				quiero.setEnabled(true);
										    				noQuiero.setEnabled(true);
										    				cantarEnvido.setEnabled(false);
										    				real_envido.setEnabled(false);
										    				falta_envido.setEnabled(true);
										    				
										    				accionUsuario = false;
										    				
										    					//nota: si se complica, cambiar a nombre diferente
										    					Thread espero4 = new Thread() {
												    			
												    			@Override
												    			public void run() {
														    		
												    				System.out.print("\nEspero que el jugador concluya su turno ");
												    				while(accionUsuario == false) {
												    					try {
															    			System.out.print(". ");
															    			Thread.sleep(1000);
																		} catch (InterruptedException e) {
																			e.printStackTrace();
																		}
														    		}
														    		System.out.println("\nRetomo la ejecucion (me toca a mi)\n");
												    			}
												    			
												    		};
											    			
												    		espero4.start();	
												    		
												    		try {
																espero4.join();					
//																	Thread.sleep(1000);
															}catch(InterruptedException e) {}
										    				
												    		/* SI EL JUGADOR QUIERE */
												    		
												    		if(cantado[2].equals("quiero")) {
												    			//SE SUMAN PUNTOS
												    			
												    			sistPuntuacion(cantado,j,MAQUINA);
												    			acumulador = String.valueOf(j.getPuntos());
												    			pts1.setText(acumulador);
												    			acumulador = "";
												    			acumulador = String.valueOf(MAQUINA.getPuntos());
												    			pts2.setText(acumulador);
												    			
												    			reiniciarMenuCantos();
												    			puntosMaximosSuperados();
												    			
																envido.setEnabled(false);
												    		}
												    		
												    		/*SI EL JUGADOR NO QUIERE*/
												    		
												    		else if(cantado[2].equals("no quiero")) {
												    			//SE SUMAN PUNTOS
												    			
												    			sistPuntuacion(cantado,j,MAQUINA);
												    			acumulador = String.valueOf(MAQUINA.getPuntos());
												    			pts2.setText(acumulador);
												    			
												    			reiniciarMenuCantos();
												    			puntosMaximosSuperados();
												    			
												    			envido.setEnabled(false);
												    		}
												    		
												    		/* SI EL JUGADOR REVIRA */
												    			
												    		else if(cantado[2].equals("falta envido")) {
												    			
												    			falta_envido.setEnabled(false);
												    			
												    			MAQUINA.yourTurnAccept(cantado);
												    			
												    			/* SI LA MAQUINA QUIERE */
												    			
												    			if(cantado[3].equals("quiero")) {
													    			
																	/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
												    				System.out.println("la IA quiso");
												    				/***/
												    				
												    				//SE SUMAN PUNTOS
													    			
												    				sistPuntuacion(cantado,j,MAQUINA);
												    				acumulador = String.valueOf(j.getPuntos());
												    				pts1.setText(acumulador);
												    				acumulador = "";
												    				acumulador = String.valueOf(MAQUINA.getPuntos());
												    				pts2.setText(acumulador);
																	
													    			reiniciarMenuCantos();
													    			puntosMaximosSuperados();
													    			
																	envido.setEnabled(false);
																	
													    		}
												    			
												    			/*SI LA MAQUINA NO QUIERE*/
												    			
												    			else {
												    				
												    				/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
												    				System.out.println("la IA no quiso");
												    				/***/
												    				
												    				//SE SUMAN PUNTOS
												    				
												    				sistPuntuacion(cantado,j,MAQUINA);
												    				acumulador = String.valueOf(j.getPuntos());
												    				pts1.setText(acumulador);
												    				
												    				reiniciarMenuCantos();
												    				puntosMaximosSuperados();
												    				
												    				envido.setEnabled(false);
												    				
												    			}
												    			
												    		}
												    		
										    			}
										    			
										    			else {
										    				
										    				cantarEnvido.setEnabled(false);
										    				real_envido.setEnabled(false);
										    				falta_envido.setEnabled(false);
										    				
										    				quiero.setEnabled(true);
										    				noQuiero.setEnabled(true);
										    				
										    				/* SI EL JUGADOR QUIERE */
										    				
										    				if(cantado[2].equals("quiero")) {
												    			//SE SUMAN PUNTOS
												    			
										    					sistPuntuacion(cantado,j,MAQUINA);
										    					acumulador = String.valueOf(j.getPuntos());
										    					pts1.setText(acumulador);
										    					acumulador = "";
										    					acumulador = String.valueOf(MAQUINA.getPuntos());
										    					pts2.setText(acumulador);
										    					
												    			reiniciarMenuCantos();
												    			puntosMaximosSuperados();
												    			
																envido.setEnabled(false);
												    		}
										    				
										    				/*SI EL JUGADOR NO QUIERE*/
										    				
										    				else if(cantado[2].equals("no quiero")) {
										    					//SE SUMAN PUNTOS
										    					
										    					sistPuntuacion(cantado,j,MAQUINA);
										    					acumulador = String.valueOf(MAQUINA.getPuntos());
										    					pts2.setText(acumulador);
										    					
										    					reiniciarMenuCantos();
										    					puntosMaximosSuperados();
										    					
																envido.setEnabled(false);
										    					
										    				}
										    				
										    			}
										    			
										    		}
										    		
										    		/*SI TERMINA DE CANTAR ENVIDO, LUEGO PUEDE CANTAR TRUCO O TIRAR CARTA DIRECTAMENTE*/
										    		
										    		/*EL ARREGLO DE CANTADOS VUELVE A NULL POR SI SE CANTA TRUCO*/
										    		
										    		for(int i=0;i<5;i++) {
										        		cantado[i] = "";
										        	}
										    		
										    		texto.setText("<html>"+ "" +"</html>");
										    		
										    		truco.setEnabled(true);
										    		flecha2.setEnabled(true);
										    		
										    		c1.setEnabled(true);
										    		c2.setEnabled(true);
										    		c3.setEnabled(true);
										    		
										    		accionUsuario = false;
										    		Thread espero6 = new Thread() {
										    			
										    			@Override
										    			public void run() {
												    		
										    				System.out.print("\nEspero que el jugador cante truco / tire carta y concluya su turno ");
										    				while(accionUsuario == false) {
										    					try {
													    			System.out.print(". ");
													    			Thread.sleep(1000);
																} catch (InterruptedException e) {
																	e.printStackTrace();
																}
												    		}
												    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
										    			}
										    			
										    		};
									    			
										    		espero6.start();	
										    		
										    		try {
														espero6.join();					
//														Thread.sleep(1000);
													}catch(InterruptedException e) {}
										    		
										    		/*SE REALIZA TRUCO/TIRAR DIR MEDIANTE LA LLAMADA A FUNCION*/
										    		
										    		if(cantado[0].equals("truco")) {
										    			
										    			truco();
										    			
										    		}
										    		
										    		else {
										    			
										    			tirarDir();
										    			
										    		}
										    		
									    		}
									    		
									    		
									    		/*EL JUGADOR PUEDE CANTAR TRUCO*/
									    		
									    		else if(cantado[0].equals("truco")) {
									    			
									    			truco();
									    			
									    		} 
										        
									    		
										        /*EL JUGADOR PUEDE TIRAR CARTA DIRECTAMENTE*/
										        
									    		else {
									    			
									    			tirarDir();
									    			
									    		}
									    		
									    		texto.setText("<html>"+ "" +"</html>");
									    		//reiniciarMenuCantos();
								        
									    		turno = false;
											
											}     //FIN WHILE IATHREAD
										
										} //FIN RUN IATHREAD
										
									};
									
									IAThread.start();
									
									try {
										IAThread.join();					//sincroniza los hilos
//										Thread.sleep(1000);
									}catch(InterruptedException e) {}
								
//						    		if(pts1.getText().equals("30") || pts2.getText().equals("30") ) {
//										JOptionPane.showMessageDialog(Interfaz.this, "FIN DEL JUEGO.");
//										jugando = false;
//										
//										//tal vez deba restaurar el contenedor original, llevando a la persona al menu principal
//										
//									}	
									
						    		//PROVISORIO: PORQUE SINO TIRA EXCEPCION
						    		//NO TIRA EXCEPCION CUANDO LA MAQUINA SE DEDICA A ESPERAR. SI DEJA DE ESPERAR VUELVE A TIRAR EXCEPCION
						    		//POSIBLE SOLUCION: TRATAR EL WHILE CON DOS VARIABLES BOOLEANAS
						    		jugando = false;
						    		
					    		} //FIN WHILE JUEGO
					    	
					    		System.out.println("\n\nFIN FIN FINNNNN !!!!!!!\n\n");
					    	
							}	//FIN RUN JUEGO
						
					    };
						
						juego.start();
								
						/** FIN DE WHILE*/
						
					}	//FIN ACTION-PERFORMED JUGAR-MAQUINA
				
				});		//FIN ACTION-LISTENER JUGAR-MAQUINA
				
				
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
		
		//
		
		config.addActionListener(new ActionListener() {
			
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				/*CAMBIAR NOMBRE*/
				
				ingrese_nombre = new JLabel("Ingrese un nombre (max. 12 caracteres): ");
				texto_nombre = new JTextField(texto_nombre.getText());
				texto_nombre.setPreferredSize(new Dimension(100,20));
				texto_nombre.addKeyListener((KeyListener) new KeyListener() {
					
					public void keyTyped(KeyEvent e) {
						if (texto_nombre.getText().length()== 12) {
							e.consume(); 
						}
				    }

					@Override
					public void keyPressed(KeyEvent e) {}

					@Override
					public void keyReleased(KeyEvent e) {} 
					
				});
				
				ok = new JButton("OK");
				
				JPanel menu2 = new JPanel();
				menu2.setLayout(new BoxLayout(menu2, BoxLayout.X_AXIS));
				menu2.add(ingrese_nombre);
				menu2.add(texto_nombre);
				menu2.add(ok);
				
				JPanel principal2 = new JPanel();
				principal2.setLayout(new GridBagLayout());
				GridBagConstraints gbc3 = new GridBagConstraints();
				
				gbc3.gridx = 0;
				gbc3.gridy = 0;
				gbc3.gridwidth = 1;
				gbc3.gridheight = 1;
				principal2.add(banner,gbc3);
				
				gbc3.gridx = 0;
				gbc3.gridy = 1;
				gbc3.gridwidth = 1;
				gbc3.gridheight = 1;
				gbc3.weighty = 1.0;
				principal2.add(menu2,gbc3);
				
				gbc3.gridx = 0;
				gbc3.gridy = 2;
				gbc3.gridwidth = 1;
				gbc3.gridheight = 1;
				principal2.add(cartel,gbc3);
				
				cp.removeAll();
				cp.revalidate();
				cp.repaint();
				cp.add(principal2);
				
				ok.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						cp.removeAll();
						cp.revalidate();
						cp.repaint();
						cp.add(ventanaPrincipal());
						
						ActionListener();
						
					}
				});	//FIN OK
				
			}
		});		//FIN CONFIG
		
	}	//FIN FUNCION ACTION-LISTENER
	
	
	
	//FUNCION TRUCO
	
	public void truco() {
		
		flecha2.setEnabled(false);
		
		cantarTruco.setEnabled(false);
		retruco.setEnabled(false);
		vale_4.setEnabled(false);
		
		c1.setEnabled(false);
		c2.setEnabled(false);
		c3.setEnabled(false);
		
		quiero.setEnabled(false);
		noQuiero.setEnabled(false);
		
		MAQUINA.yourTurnAccept(cantado);
		
		/*SI LA IA QUIERE*/
		
		if(cantado[1].equals("quiero")) {
			

			/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
			System.out.println("la IA quiso");
			/***/
			
			//TENER EN CUENTA VARIABLES COMO CANTADO[] / LAS AUX Y OTRAS (EN RELACION A SU VALOR)
			trucoQuerido();
		
		}
		
		/*SI LA IA REVIRA*/
		
		else if(cantado[1].equals("retruco")){

			texto.setText("<html>"+ cantado[1] +"</html>");
			quiero.setEnabled(true);
			noQuiero.setEnabled(true);
			
			vale_4.setEnabled(true);
			
			Thread espero13 = new Thread() {
    			
    			@Override
    			public void run() {
		    		
    				System.out.print("\nEspero que el jugador quiera/no quiera retruco - revire ");
    				while(accionUsuario == false) {
    					try {
			    			System.out.print(". ");
			    			Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		    		}
		    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
    			}
    			
    		};
			
    		espero13.start();	
    		
    		try {
				espero13.join();					
//				Thread.sleep(1000);
			}catch(InterruptedException e) {}
			
    		/*SI EL JUGADOR QUIERE*/
    		
    		if(cantado[2].equals("quiero")) {
    			
    			trucoQuerido();
    		}
    		
    		/*SI EL JUGADOR REVIRA*/
    		
    		else if(cantado[2].equals("vale cuatro")) {
    			
    			texto.setText("<html>"+ cantado[2] +"</html>");
				quiero.setEnabled(false);
				noQuiero.setEnabled(false);
				
				vale_4.setEnabled(false);
				
	    		c1.setEnabled(false);
	    		c2.setEnabled(false);
	    		c3.setEnabled(false);
    			
    			MAQUINA.yourTurnAccept(cantado);
    			
    			/*SI LA IA QUIERE*/
    			
    			if(cantado[3].equals("quiero")) {
    				
					/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
    					System.out.println("la IA quiso");
    				/***/
    				
    				trucoQuerido();
    			}
    			
    			/*SI LA IA NO QUIERE*/
    			
    			else {
					
    				/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
    					System.out.println("la IA no quiso");
    				/***/
    					
    				//EL JUGADOR GANA
	    			
					sistPuntuacion(cantado,j,MAQUINA);
					acumulador = String.valueOf(j.getPuntos());
					pts1.setText(acumulador);
    				
					puntosMaximosSuperados();
	    			//SIGUIENTE RONDA
    			}
    			
    		}
    		
    		/*SI EL JUGADOR NO QUIERE*/
    		
    		else {
    			//LA IA GANA
    			
    			sistPuntuacion(cantado,j,MAQUINA);
    			acumulador = String.valueOf(MAQUINA.getPuntos());
    			pts2.setText(acumulador);
    			
    			puntosMaximosSuperados();
    			//SIGUIENTE RONDA
    		}
			
		}
		
		/*SI LA IA NO QUIERE*/
		
		else if(cantado[1].equals("no quiero")) {
			
			/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
				System.out.println("la IA no quiso");
			/***/
			
			//EL JUGADOR GANA

			sistPuntuacion(cantado,j,MAQUINA);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
			
		}
		
	}
	
	
	//FUNCION TIRAR DIRECTAMENTE
	
	public void tirarDir() {
		
		envido.setEnabled(false);
		flecha.setEnabled(true);
		cantarEnvido.setEnabled(false);
		real_envido.setEnabled(false);
		falta_envido.setEnabled(false);
		
		auxJ = queCartaFueTirada();
		
		/* SI EL JUGADOR NO LA MATA */
		
		if(aux.returnOrden(auxJ) > aux.returnOrden(auxIA)) {
			
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			truco.setEnabled(false);
			quiero.setEnabled(false);
			noQuiero.setEnabled(false);
			cantarTruco.setEnabled(false);
			retruco.setEnabled(false);
			vale_4.setEnabled(false);
			flecha2.setEnabled(false);
			
			tiraIA = null;
			tiraIA = MAQUINA.yourTurn(cantado, null);
			
			/*LA IA PUEDE CANTAR TRUCO*/
			
			if(cantado[0].equals("truco")) {
					
					texto.setText("<html>"+ cantado[0] +"</html>");
					quiero.setEnabled(true);
					noQuiero.setEnabled(true);
					
					truco.setEnabled(true);
					retruco.setEnabled(true);
										
					accionUsuario = false;
					Thread espero15 = new Thread() {
	    			
	    			@Override
	    			public void run() {
			    		
	    				System.out.print("\nEspero que el jugador quiera/no quiera el truco ");
	    				while(accionUsuario == false) {
	    					try {
				    			System.out.print(". ");
				    			Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
			    		}
			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
	    			}
	    			
	    		};
    			
	    		espero15.start();	
	    		
	    		try {
					espero15.join();					
//					Thread.sleep(1000);
				}catch(InterruptedException e) {}
				
				/*SI EL JUGADOR QUIERE*/
				
				if(cantado[1].equals("quiero")) {		//BLOQUE 1
					
					trucoQueridoBloque1();
					
				}
				
				/*SI EL JUGADOR NO QUIERE*/
				
				else if(cantado[1].equals("no quiero")) {
					//LA IA GANA

					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(MAQUINA.getPuntos());
					pts2.setText(acumulador);
					
					puntosMaximosSuperados();
					//SIGUIENTE RONDA
				}
				
				/*SI EL JUGADOR REVIRA*/
				
				else if(cantado[1].equals("retruco")){
					
					retruco.setEnabled(false);
					
					MAQUINA.yourTurnAccept(cantado);
					
					/* SI LA IA QUIERE*/
					
					if(cantado[2].equals("quiero")) {	//BLOQUE 1
						
						/**PROVISORIO*/
						System.out.println("la IA quiso");
						/***/
						
						trucoQueridoBloque1();
						
					}
					
					/*SI LA IA NO QUIERE*/
					
					else if(cantado[2].equals("no quiero")) {
						
						/**PROVISORIO*/
						System.out.println("la IA no quiso");
						/***/
						
						//EL JUGADOR GANA

						sistPuntuacion(cantado,MAQUINA,j);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
					
					/*SI LA IA REVIRA*/
					
					else if(cantado[2].equals("vale cuatro")){
						
						/**PROVISORIO*/
						System.out.println("la IA revira");
						/***/
						
						texto.setText("<html>"+ cantado[2] +"</html>");
						quiero.setEnabled(true);
						noQuiero.setEnabled(true);
			    		
			    		accionUsuario = false;
						Thread espero32 = new Thread() {
    	    			
    	    			@Override
    	    			public void run() {
    			    		
    	    				System.out.print("\nEspero que el jugador quiera/no quiera el vale cuatro ");
    	    				while(accionUsuario == false) {
    	    					try {
    				    			System.out.print(". ");
    				    			Thread.sleep(1000);
    							} catch (InterruptedException e) {
    								e.printStackTrace();
    							}
    			    		}
    			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
    	    			}
    	    			
						};
        			
	    	    		espero32.start();	
	    	    		
	    	    		try {
	    					espero32.join();					
//												    					Thread.sleep(1000);
	    				}catch(InterruptedException e) {}
						
						/*SI EL JUGADOR QUIERE*/
						
						if(cantado[3].equals("quiero")) {	//BLOQUE 1
							
							trucoQueridoBloque1();
							
						}
						
						/*SI EL JUGADOR NO QUIERE*/
						
						else {
							//LA IA GANA

							sistPuntuacion(cantado,MAQUINA,j);
							acumulador = String.valueOf(MAQUINA.getPuntos());
							pts2.setText(acumulador);
							
							puntosMaximosSuperados();
							//SIGUIENTE RONDA
						}
						
					}
					
				}
				
			}
			
			/*LA IA PUEDE TIRAR CARTA DIRECTAMENTE*/
			
    		else {
				
    			tiraIA = null;
				tiraIA = MAQUINA.yourTurn(cantado, null);
				tiraIAnull = true;
				mostrarTirada(tiraIA,tiraIAnull);
				auxIA = tiraIA[0];
				
				if(c1.getIcon() != null) {
					c1.setEnabled(true);
				}
				if(c2.getIcon() != null) {
					c2.setEnabled(true);
				}
				if(c3.getIcon() != null) {
					c3.setEnabled(true);
				}
								
				truco.setEnabled(true);
				flecha2.setEnabled(true);
				cantarTruco.setEnabled(true);
				
				accionUsuario = false;
				Thread espero13 = new Thread() {
	    			
	    			@Override
	    			public void run() {
			    		
	    				System.out.print("\nEspero que el jugador tire en segunda (le gane primera) ");
	    				while(accionUsuario == false) {
	    					try {
				    			System.out.print(". ");
				    			Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
			    		}
			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
	    			}
	    			
	    		};
    			
	    		espero13.start();	
	    		
	    		try {
					espero13.join();					
//					Thread.sleep(1000);
				}catch(InterruptedException e) {}
    			
				/*EL JUGADOR PUEDE CANTAR TRUCO*/
				
				if(cantado[0].equals("truco")) {
					
					texto.setText("<html>"+ cantado[0] +"</html>");
					flecha2.setEnabled(false);
					cantarTruco.setEnabled(false);
					
					c1.setEnabled(false);
		    		c2.setEnabled(false);
		    		c3.setEnabled(false);
					
					MAQUINA.yourTurnAccept(cantado);
					
					/*SI LA IA QUIERE*/
					
					if(cantado[1].equals("quiero")) {		//BLOQUE 2
						
						/**PROVISORIO*/
						System.out.println("la IA quiso");
						/***/
						
						trucoQueridoBloque2();
						
					}
					
					/*SI LA IA NO QUIERE*/
					
					else if(cantado[1].equals("no quiero")) {
						
						/**PROVISORIO*/
						System.out.println("la IA no quiso");
						/***/
						
						//EL JUGADOR GANA

						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
					
					/*SI LA IA REVIRA*/
					
					else if(cantado[1].equals("retruco")){
						
						texto.setText("<html>"+ cantado[1] +"</html>");
						quiero.setEnabled(true);
						noQuiero.setEnabled(true);
						
						vale_4.setEnabled(true);

						accionUsuario = false;
	    				Thread espero33 = new Thread() {
	    	    			
	    	    			@Override
	    	    			public void run() {
	    			    		
	    	    				System.out.print("\nEspero que el jugador tire en segunda (le gane primera) ");
	    	    				while(accionUsuario == false) {
	    	    					try {
	    				    			System.out.print(". ");
	    				    			Thread.sleep(1000);
	    							} catch (InterruptedException e) {
	    								e.printStackTrace();
	    							}
	    			    		}
	    			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
	    	    			}
	    	    			
	    	    		};
	        			
	    	    		espero33.start();	
	    	    		
	    	    		try {
	    					espero33.join();					
//	    					Thread.sleep(1000);
	    				}catch(InterruptedException e) {}
						
						/*SI EL JUGADOR QUIERE*/
						
						if(cantado[2].equals("quiero")) {		//BLOQUE 2
							
							trucoQueridoBloque2();
							
						}
						
						/*SI EL JUGADOR NO QUIERE*/
						
						else if(cantado[2].equals("no quiero")) {
							//LA IA GANA

							sistPuntuacion(cantado,j,MAQUINA);
							acumulador = String.valueOf(MAQUINA.getPuntos());
							pts2.setText(acumulador);
							
							puntosMaximosSuperados();
							//SIGUIENTE RONDA
						}
						
						/*SI EL JUGADOR REVIRA*/
						
						else if(cantado[2].equals("vale cuatro")){
							
							quiero.setEnabled(false);
							noQuiero.setEnabled(false);
							vale_4.setEnabled(false);
							
							MAQUINA.yourTurnAccept(cantado);
							
							/*SI LA IA QUIERE*/
							
							if(cantado[3].equals("quiero")) {		//BLOQUE 2
								
								/**PROVISORIO*/
    							System.out.println("la IA quiso");
    							/***/
								
								trucoQueridoBloque2();
								
							}
							
							/*SI LA IA NO QUIERE*/
							
							else {
								
								/**PROVISORIO*/
    							System.out.println("la IA no quiso");
    							/***/
								
								//EL JUGADOR GANA

    							sistPuntuacion(cantado,j,MAQUINA);
    							acumulador = String.valueOf(j.getPuntos());
    							pts1.setText(acumulador);
    							
    							puntosMaximosSuperados();
								//SIGUIENTE RONDA
							}
							
						}
						
					}
					
					
				}
				
				/*EL JUGADOR PUEDE TIRAR CARTA DIRECTAMENTE*/
				
				else {
							
					auxJ = queCartaFueTirada();
					
					/*SI EL JUGADOR NO LA MATA/EMPARDA*/
					
					if(aux.returnOrden(auxJ) >= aux.returnOrden(auxIA)) {
						
						c1.setEnabled(false);
						c2.setEnabled(false);
						c3.setEnabled(false);
						
						//LA IA GANA

						//sistPuntuacion(cantadoTruco,j,MAQUINA);
						MAQUINA.addPuntos(1);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						//SIGUIENTE RONDA
					}
					
					/*SI EL JUGADOR LA MATA*/
					
					else {
												
						truco.setEnabled(true);
						flecha2.setEnabled(true);
						cantarTruco.setEnabled(true);
						
						accionUsuario = false;
	    				Thread espero17 = new Thread() {
	    	    			
	    	    			@Override
	    	    			public void run() {
	    			    		
	    	    				System.out.print("\nEspero que el jugador tire en tercera (le gane primera y me gano en segunda) ");
	    	    				while(accionUsuario == false) {
	    	    					try {
	    				    			System.out.print(". ");
	    				    			Thread.sleep(1000);
	    							} catch (InterruptedException e) {
	    								e.printStackTrace();
	    							}
	    			    		}
	    			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
	    	    			}
	    	    			
	    	    		};
	        			
	    	    		espero17.start();	
	    	    		
	    	    		try {
	    					espero17.join();					
//	    					Thread.sleep(1000);
	    				}catch(InterruptedException e) {}
						
						/*EL JUGADOR PUEDE CANTAR TRUCO*/
						
						if(cantado[0].equals("truco")) {
							
							flecha2.setEnabled(false);
							cantarTruco.setEnabled(false);
							
				    		c1.setEnabled(false);
				    		c2.setEnabled(false);
				    		c3.setEnabled(false);
							
							MAQUINA.yourTurnAccept(cantado);
							
							/*SI LA IA QUIERE*/
							
							if(cantado[1].equals("quiero")) {			//BLOQUE 3
								
								/**PROVISORIO*/
    							System.out.println("la IA quiso");
    							/***/
								
								trucoQueridoBloque3();
								
							}
							
							/*SI LA IA NO QUIERE*/
							
							else if(cantado[1].equals("no quiero")) {
								
								/**PROVISORIO*/
    							System.out.println("la IA no quiso");
    							/***/
								
								//EL JUGADOR GANA
								
    							sistPuntuacion(cantado,MAQUINA,j);
    							acumulador = String.valueOf(j.getPuntos());
    							pts1.setText(acumulador);
    							
    							puntosMaximosSuperados();
    							//SIGUIENTE RONDA
							}
							
							/*SI LA IA REVIRA*/
							
							else if(cantado[1].equals("retruco")){
								
								texto.setText("<html>"+ cantado[1] +"</html>");
	    						quiero.setEnabled(true);
	    						noQuiero.setEnabled(true);
	    						
	    						vale_4.setEnabled(true);
	    						
								accionUsuario = false;
			    				Thread espero34 = new Thread() {
			    	    			
			    	    			@Override
			    	    			public void run() {
			    			    		
			    	    				System.out.print("\nEspero que el jugador acepte o no el retruco ");
			    	    				while(accionUsuario == false) {
			    	    					try {
			    				    			System.out.print(". ");
			    				    			Thread.sleep(1000);
			    							} catch (InterruptedException e) {
			    								e.printStackTrace();
			    							}
			    			    		}
			    			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			    	    			}
			    	    			
			    	    		};
			        			
			    	    		espero34.start();	
			    	    		
			    	    		try {
			    					espero34.join();					
//			    					Thread.sleep(1000);
			    				}catch(InterruptedException e) {}
								
								/*SI EL JUGADOR QUIERE*/
								
								if(cantado[2].equals("quiero")) {		//BLOQUE 3
									
									trucoQueridoBloque3();
									
								}
								
								/*SI EL JUGADOR NO QUIERE*/
								
								else if(cantado[2].equals("no quiero")) {
									//LA IA GANA

									sistPuntuacion(cantado,j,MAQUINA);
									acumulador = String.valueOf(MAQUINA.getPuntos());
									pts2.setText(acumulador);
									
									puntosMaximosSuperados();
									//SIGUIENTE RONDA
								}
								
								/*SI EL JUGADOR REVIRA*/
								
								else if(cantado[2].equals("vale cuatro")){
									
									vale_4.setEnabled(false);
									quiero.setEnabled(false);
									noQuiero.setEnabled(false);
									
									MAQUINA.yourTurnAccept(cantado);
									
									/*SI LA IA QUIERE*/
					
									if(cantado[3].equals("quiero")) {		//BLOQUE 3
										
										/**PROVISORIO*/
		    							System.out.println("la IA quiso");
		    							/***/
										
										trucoQueridoBloque3();
										
									}
									
									/*SI LA IA NO QUIERE*/
									
									else {
										
										/**PROVISORIO*/
		    							System.out.println("la IA no quiso");
		    							/***/
										
										//EL JUGADOR GANA

		    							sistPuntuacion(cantado,j,MAQUINA);
		    							acumulador = String.valueOf(j.getPuntos());
		    							pts1.setText(acumulador);
		    							
		    							puntosMaximosSuperados();
										//SIGUIENTE RONDA
									}
									
								}
								
							}
							
						}
						
						/*EL JUGADOR PUEDE TIRAR CARTA DIRECTAMENTE*/
						
						else {
							
							auxJ = queCartaFueTirada();
							
							//		ESTABA DENTRO DE TRUCOQUERIDOBLOQUE4 - TENER EN CUENTA POR CUALQUIER ERROR
							tiraIA = null;
							tiraIA = MAQUINA.yourTurn(cantado, null);
							//
							
							/*LA IA PUEDE CANTAR TRUCO*/
							
							if(cantado[0].equals("truco")) {
								
								texto.setText("<html>"+ cantado[0] +"</html>");
	    						quiero.setEnabled(true);
	    						noQuiero.setEnabled(true);
	    						
	    						truco.setEnabled(true);
	    						flecha2.setEnabled(false);
	    						retruco.setEnabled(true);
	    						
					    		c1.setEnabled(false);
					    		c2.setEnabled(false);
					    		c3.setEnabled(false);
								
								accionUsuario = false;
			    				Thread espero18 = new Thread() {
			    	    			
			    	    			@Override
			    	    			public void run() {
			    			    		
			    	    				System.out.print("\nEspero que el jugador quiera/no quiera truco (estamos en tercera) ");
			    	    				while(accionUsuario == false) {
			    	    					try {
			    				    			System.out.print(". ");
			    				    			Thread.sleep(1000);
			    							} catch (InterruptedException e) {
			    								e.printStackTrace();
			    							}
			    			    		}
			    			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			    	    			}
			    	    			
			    	    		};
			        			
			    	    		espero18.start();	
			    	    		
			    	    		try {
			    					espero18.join();					
//			    					Thread.sleep(1000);
			    				}catch(InterruptedException e) {}
								
								/*SI EL JUGADOR QUIERE*/
								
								if(cantado[1].equals("quiero")) {	//BLOQUE 4
									
									trucoQueridoBloque4();
									
								}
								
								/*SI EL JUGADOR NO QUIERE*/
								
								else if(cantado[1].equals("no quiero")) {
								
									//LA IA GANA

									sistPuntuacion(cantado,MAQUINA,j);
									acumulador = String.valueOf(MAQUINA.getPuntos());
									pts2.setText(acumulador);
									
									puntosMaximosSuperados();
									//SIGUIENTE RONDA
								}
								
								/*SI EL JUGADOR REVIRA*/
								
								else if(cantado[1].equals("retruco")){
									
									quiero.setEnabled(false);
		    						noQuiero.setEnabled(false);
									
									retruco.setEnabled(false);
									
									MAQUINA.yourTurnAccept(cantado);
									
									/*SI LA IA QUIERE*/
									
									if(cantado[2].equals("quiero")) {	//BLOQUE 4
										
										/**PROVISORIO*/
		    							System.out.println("la IA quiso");
		    							/***/
										
										trucoQueridoBloque4();
										
									}
									
									/*SI LA IA NO QUIERE*/
									
									else if(cantado[2].equals("no quiero")) {
										
										/**PROVISORIO*/
		    							System.out.println("la IA no quiso");
		    							/***/
										
										//EL JUGADOR GANA

		    							sistPuntuacion(cantado,MAQUINA,j);
		    							acumulador = String.valueOf(j.getPuntos());
		    							pts1.setText(acumulador);
		    							
		    							puntosMaximosSuperados();
										//SIGUIENTE RONDA
									}
									
									/*SI LA IA REVIRA*/
									
									else if(cantado[2].equals("vale cuatro")) {
										
										texto.setText("<html>"+ cantado[2] +"</html>");
			    						quiero.setEnabled(true);
			    						noQuiero.setEnabled(true);

    									accionUsuario = false;
					    				Thread espero35 = new Thread() {
					    	    			
					    	    			@Override
					    	    			public void run() {
					    			    		
					    	    				System.out.print("\nEspero que el jugador quiera/no quiera vale cuatro (estamos en tercera) ");
					    	    				while(accionUsuario == false) {
					    	    					try {
					    				    			System.out.print(". ");
					    				    			Thread.sleep(1000);
					    							} catch (InterruptedException e) {
					    								e.printStackTrace();
					    							}
					    			    		}
					    			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
					    	    			}
					    	    			
					    	    		};
					        			
					    	    		espero35.start();	
					    	    		
					    	    		try {
					    					espero35.join();					
//					    					Thread.sleep(1000);
					    				}catch(InterruptedException e) {}
										
										/*SI EL JUGADOR QUIERE*/
										
										if(cantado[3].equals("quiero")) {	//BLOQUE 4
											
											trucoQueridoBloque4();
											
										}
										
										/*SI EL JUGADOR NO QUIERE*/
										
										else {
											//LA IA GANA

											sistPuntuacion(cantado,MAQUINA,j);
											acumulador = String.valueOf(MAQUINA.getPuntos());
											pts2.setText(acumulador);
											
											puntosMaximosSuperados();
											//SIGUIENTE RONDA
										}
										
									}
									
								}
							}
							
							/*LA IA TIRA CARTA DIRECTAMENTE*/
							
							else {
								
								//OJO: CHEQUEAR SI EFECTIVAMENTE PERTENENCE AL BLOQUE 4 O SE HACE APARTE
								trucoQueridoBloque4();
								
							}
							
						}
						
					}
					
				}
				
			}
			
			
		}
		
		
		
		/*SI EL JUGADOR EMPARDA*/
		
		else if(aux.returnOrden(auxJ) == aux.returnOrden(auxIA)){
			
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			
			truco.setEnabled(false);
			flecha2.setEnabled(false);
			cantarTruco.setEnabled(false);
			retruco.setEnabled(false);
			vale_4.setEnabled(false);
			
			quiero.setEnabled(false);
			noQuiero.setEnabled(false);
			
			tiraIA = null;
			tiraIA = MAQUINA.yourTurn(cantado, null);
			
			/*LA IA CANTA TRUCO*/
			
			if(cantado[0].equals("truco")) {
				
				texto.setText("<html>"+ cantado[0] +"</html>");
				quiero.setEnabled(true);
				noQuiero.setEnabled(true);
				
				truco.setEnabled(true);
				retruco.setEnabled(true);
				
				accionUsuario = false;
				Thread espero19 = new Thread() {
	    			
	    			@Override
	    			public void run() {
			    		
	    				System.out.print("\nEspero que el jugador quiera el truco (empardamos primera) ");
	    				while(accionUsuario == false) {
	    					try {
				    			System.out.print(". ");
				    			Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
			    		}
			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
	    			}
	    			
	    		};
    			
	    		espero19.start();	
	    		
	    		try {
					espero19.join();					
//					Thread.sleep(1000);
				}catch(InterruptedException e) {}
				
				/*EL JUGADOR QUIERE*/
				
				if(cantado[1].equals("quiero")) {				//BLOQUE 5
					
					trucoQueridoBloque5();
					
				}
				
				/*EL JUGADOR NO QUIERE*/
				
				else if(cantado[1].equals("no quiero")) {
					//LA IA GANA

					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(MAQUINA.getPuntos());
					pts2.setText(acumulador);
					
					puntosMaximosSuperados();
					//SIGUIENTE RONDA
				}
				
				/*EL JUGADOR REVIRA*/
				
				else if(cantado[1].equals("retruco")) {
					
					retruco.setEnabled(false);
					
					MAQUINA.yourTurnAccept(cantado);
					
					/*SI LA IA QUIERE*/
					
					if(cantado[2].equals("quiero")) {
						
						/**PROVISORIO*/
						System.out.println("la IA quiso");
						/***/
						
						trucoQueridoBloque5();							//BLOQUE 5
						
					}
					
					/*SI LA IA NO QUIERE*/
					
					else if(cantado[2].equals("no quiero")) {
						
						/**PROVISORIO*/
						System.out.println("la IA no quiso");
						/***/
						
						//EL JUGADOR GANA

						sistPuntuacion(cantado,MAQUINA,j);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
					
					/*SI LA IA REVIRA*/
					
					else if(cantado[2].equals("vale cuatro")){
						
						texto.setText("<html>"+ cantado[2] +"</html>");
						quiero.setEnabled(true);
						noQuiero.setEnabled(true);

    					accionUsuario = false;
	    				Thread espero36 = new Thread() {
	    	    			
	    	    			@Override
	    	    			public void run() {
	    			    		
	    	    				System.out.print("\nEspero que el jugador quiera el vale cuatro (empardamos primera) ");
	    	    				while(accionUsuario == false) {
	    	    					try {
	    				    			System.out.print(". ");
	    				    			Thread.sleep(1000);
	    							} catch (InterruptedException e) {
	    								e.printStackTrace();
	    							}
	    			    		}
	    			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
	    	    			}
	    	    			
	    	    		};
	        			
	    	    		espero36.start();	
	    	    		
	    	    		try {
	    					espero36.join();					
//	    					Thread.sleep(1000);
	    				}catch(InterruptedException e) {}
																    							
						/*SI EL JUGADOR QUIERE*/
						
						if(cantado[3].equals("quiero")) {
							
							trucoQueridoBloque5();							//BLOQUE 5
							
						}
						
						/*SI EL JUGADOR NO QUIERE*/
						
						else {
							//LA IA GANA

							sistPuntuacion(cantado,MAQUINA,j);
							acumulador = String.valueOf(MAQUINA.getPuntos());
							pts2.setText(acumulador);
							
							puntosMaximosSuperados();
							//SIGUIENTE RONDA
						}
						
					}
					
				}
			
				
			}
			
			/*LA IA TIRA DIRECTAMENTE*/
			
			else {
				
				tiraIAnull = true;
				mostrarTirada(tiraIA,tiraIAnull);
				auxIA = tiraIA[0];
				
				if(c1.getIcon() != null) {
					c1.setEnabled(true);
				}
				if(c2.getIcon() != null) {
					c2.setEnabled(true);
				}
				if(c3.getIcon() != null) {
					c3.setEnabled(true);
				}
								
				truco.setEnabled(true);
				flecha2.setEnabled(true);
				cantarTruco.setEnabled(true);
				
				accionUsuario = false;
				Thread espero24 = new Thread() {
	    			
	    			@Override
	    			public void run() {
			    		
	    				System.out.print("\nEspero que el jugador tire en segunda (empardamos primera) ");
	    				while(accionUsuario == false) {
	    					try {
				    			System.out.print(". ");
				    			Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
			    		}
			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
	    			}
	    			
	    		};
    			
	    		espero24.start();	
	    		
	    		try {
					espero24.join();					
//					Thread.sleep(1000);
				}catch(InterruptedException e) {}
				
				/*EL JUGADOR CANTA TRUCO*/
				
				if(cantado[0].equals("truco")) {
					
					flecha2.setEnabled(false);
					cantarTruco.setEnabled(false);
					
					c1.setEnabled(false);
		    		c2.setEnabled(false);
		    		c3.setEnabled(false);
					
					MAQUINA.yourTurnAccept(cantado);
					
					/*SI LA IA QUIERE*/
					
					if(cantado[1].equals("quiero")) {				//BLOQUE 6
						
						/**PROVISORIO*/
						System.out.println("la IA quiso");
						/***/
						
						trucoQueridoBloque6();
			    		
					}
					
					/*SI LA IA NO QUIERE*/
					
					else if(cantado[1].equals("no quiero")) {
						
						/**PROVISORIO*/
						System.out.println("la IA no quiso");
						/***/
						
						//EL JUGADOR GANA

						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);

						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
					
					/*SI LA IA REVIRA*/
					
					else if(cantado[1].equals("retruco")){
						
						texto.setText("<html>"+ cantado[1] +"</html>");
						quiero.setEnabled(true);
						noQuiero.setEnabled(true);
						
						vale_4.setEnabled(true);
						
						accionUsuario = false;
	    				Thread espero25 = new Thread() {
	    	    			
	    	    			@Override
	    	    			public void run() {
	    			    		
	    	    				System.out.print("\nEspero que el jugador quiera retruco (empardamos primera) ");
	    	    				while(accionUsuario == false) {
	    	    					try {
	    				    			System.out.print(". ");
	    				    			Thread.sleep(1000);
	    							} catch (InterruptedException e) {
	    								e.printStackTrace();
	    							}
	    			    		}
	    			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
	    	    			}
	    	    			
	    	    		};
	        			
	    	    		espero25.start();	
	    	    		
	    	    		try {
	    					espero25.join();					
//	    					Thread.sleep(1000);
	    				}catch(InterruptedException e) {}
						
						/*SI EL JUGADOR QUIERE*/
						
						if(cantado[2].equals("quiero")) {
						
							trucoQueridoBloque6();								//BLOQUE 6
							
						}
						
						/*SI EL JUGADOR NO QUIERE*/
						
						else if(cantado[2].equals("no quiero")) {
							//LA IA GANA

							sistPuntuacion(cantado,j,MAQUINA);
							acumulador = String.valueOf(MAQUINA.getPuntos());
							pts2.setText(acumulador);
							
							puntosMaximosSuperados();
							//SIGUIENTE RONDA
						}
						
						/*SI EL JUGADOR REVIRA*/
						
						else if(cantado[2].equals("vale cuatro")) {
							
							quiero.setEnabled(false);
							noQuiero.setEnabled(false);
							vale_4.setEnabled(false);
							
							/*SI LA IA QUIERE*/
							
							if(cantado[3].equals("quiero")) {
								
								/**PROVISORIO*/
    							System.out.println("la IA quiso");
    							/***/
								
								trucoQueridoBloque6();							//BLOQUE 6
								
							}
							
							/*SI LA IA NO QUIERE*/
							
							else if(cantado[3].equals("no quiero")) {
								
								/**PROVISORIO*/
    							System.out.println("la IA no quiso");
    							/***/
								
								//EL JUGADOR GANA

    							sistPuntuacion(cantado,j,MAQUINA);
    							acumulador = String.valueOf(j.getPuntos());
    							pts1.setText(acumulador);
    							
    							puntosMaximosSuperados();
								//SIGUIENTE RONDA
							}
							
						}
						
					}
					
				}
				
				/*EL JUGADOR TIRA CARTA DIRECTAMENTE*/
				
				else {
					
					auxJ = queCartaFueTirada();
					
					/*SI EL JUGADOR MATA*/
					
					if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
						//EL JUGADOR GANA

						//sistPuntuacion(cantadoTruco,j,MAQUINA);
						j.addPuntos(1);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
					
					/*SI EL JUGADOR NO MATA*/
					
					else if(aux.returnOrden(auxJ) > aux.returnOrden(auxIA)) {
						//LA IA GANA

						//sistPuntuacion(cantadoTruco,j,MAQUINA);
						MAQUINA.addPuntos(1);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
					
					/*SI EL JUGADOR EMPARDA*/
					
					else {
						
						c1.setEnabled(false);
						c2.setEnabled(false);
						c3.setEnabled(false);
						
						tiraIA = null;
	    				tiraIA = MAQUINA.yourTurn(cantado, null);
						
	    				/*LA IA CANTA TRUCO*/
	    				
	    				if(cantado[0].equals("truco")) {
	    					
	    					texto.setText("<html>"+ cantado[0] +"</html>");
    						quiero.setEnabled(true);
    						noQuiero.setEnabled(true);
    						
    						truco.setEnabled(true);
    						retruco.setEnabled(true);
    						
	    					accionUsuario = false;
		    				Thread espero26 = new Thread() {
		    	    			
		    	    			@Override
		    	    			public void run() {
		    			    		
		    	    				System.out.print("\nEspero que el jugador quiera/no quiera el truco (empardamos segunda) ");
		    	    				while(accionUsuario == false) {
		    	    					try {
		    				    			System.out.print(". ");
		    				    			Thread.sleep(1000);
		    							} catch (InterruptedException e) {
		    								e.printStackTrace();
		    							}
		    			    		}
		    			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
		    	    			}
		    	    			
		    	    		};
		        			
		    	    		espero26.start();	
		    	    		
		    	    		try {
		    					espero26.join();					
//		    					Thread.sleep(1000);
		    				}catch(InterruptedException e) {}
	    					

		    				/*SI EL JUGADOR QUIERE*/
		    				
		    				if(cantado[1].equals("quiero")) {			//BLOQUE 7
		    					
		    					trucoQueridoBloque7();
			    	    		
		    				}
		    				
		    				/*SI EL JUGADOR NO QUIERE*/
		    				
		    				else if(cantado[1].equals("no quiero")) {
		    					//LA IA GANA

		    					sistPuntuacion(cantado,MAQUINA,j);
		    					acumulador = String.valueOf(MAQUINA.getPuntos());
		    					pts2.setText(acumulador);
		    					
		    					puntosMaximosSuperados();
		    					//SIGUIENTE RONDA
		    				}
		    				
		    				/*SI EL JUGADOR REVIRA*/
		    				
		    				else if(cantado[1].equals("retruco")) {
		    					
		    					quiero.setEnabled(false);
		    					noQuiero.setEnabled(false);
		    					retruco.setEnabled(false);
		    					
		    					MAQUINA.yourTurnAccept(cantado);
		    					
		    					/*SI LA IA QUIERE*/
		    					
		    					if(cantado[2].equals("quiero")) {
		    						
		    						/**PROVISORIO*/
	    							System.out.println("la IA quiso");
	    							/***/
		    						
		    						trucoQueridoBloque7();									//BLOQUE 7
		    						
		    					}
		    					
		    					/*SI LA IA NO QUIERE*/
		    					
		    					else if(cantado[2].equals("no quiero")) {
		    						
		    						/**PROVISORIO*/
	    							System.out.println("la IA no quiso");
	    							/***/
		    						
		    						//EL JUGADOR GANA

	    							sistPuntuacion(cantado,MAQUINA,j);
	    							acumulador = String.valueOf(j.getPuntos());
	    							pts1.setText(acumulador);
	    							
	    							puntosMaximosSuperados();
		    						//SIGUIENTE RONDA
		    					}
		    					
		    					/*SI LA IA REVIRA*/
		    					
		    					else if(cantado[2].equals("vale cuatro")) {
		    						
		    						texto.setText("<html>"+ cantado[2] +"</html>");
		    						quiero.setEnabled(true);
		    						noQuiero.setEnabled(true);
		    						
		    						accionUsuario = false;
				    				Thread espero28 = new Thread() {
				    	    			
				    	    			@Override
				    	    			public void run() {
				    			    		
				    	    				System.out.print("\nEspero que el jugador quiera/no quiera vale cuatro (empardamos segunda) ");
				    	    				while(accionUsuario == false) {
				    	    					try {
				    				    			System.out.print(". ");
				    				    			Thread.sleep(1000);
				    							} catch (InterruptedException e) {
				    								e.printStackTrace();
				    							}
				    			    		}
				    			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
				    	    			}
				    	    			
				    	    		};
				        			
				    	    		espero28.start();	
				    	    		
				    	    		try {
				    					espero28.join();					
//				    					Thread.sleep(1000);
				    				}catch(InterruptedException e) {}
		    						
		    						/*SI EL JUGADOR QUIERE*/
		    						
		    						if(cantado[3].equals("quiero")) {
		    							
		    							trucoQueridoBloque7();								//BLOQUE 7
		    							
		    						}
		    						
		    						/*SI EL JUGADOR NO QUIERE*/
		    						
		    						else {
		    							//LA IA GANA

		    							sistPuntuacion(cantado,MAQUINA,j);
		    							acumulador = String.valueOf(MAQUINA.getPuntos());
		    							pts2.setText(acumulador);
		    							
		    							puntosMaximosSuperados();
		    							//SIGUIENTE RONDA
		    						}
		    						
		    					}
		    					
		    					
		    				}

	    				}
						
	    				/*LA IA TIRA CARTA DIRECTAMENTE*/
	    				
	    				else {
	    					
	    					tiraIAnull = true;
	    					mostrarTirada(tiraIA,tiraIAnull);
	    					auxIA = tiraIA[0];
	    					
	    					truco.setEnabled(true);
	    					cantarTruco.setEnabled(true);
	    					flecha2.setEnabled(true);
	    					
	    					if(c1.getIcon() != null) {
	    						c1.setEnabled(true);
	    					}
	    					if(c2.getIcon() != null) {
	    						c2.setEnabled(true);
	    					}
	    					if(c3.getIcon() != null) {
	    						c3.setEnabled(true);
	    					}
	    					
	    					accionUsuario = false;
	    					Thread espero28 = new Thread() {
	    						
	    						@Override
	    						public void run() {
	    				    		
	    							System.out.print("\nEspero que el jugador tire en tercera (empardamos segunda y tire en tercera) ");
	    							while(accionUsuario == false) {
	    								try {
	    					    			System.out.print(". ");
	    					    			Thread.sleep(1000);
	    								} catch (InterruptedException e) {
	    									e.printStackTrace();
	    								}
	    				    		}
	    				    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
	    						}
	    						
	    					};
	    					
	    					espero28.start();	
	    					
	    					try {
	    						espero28.join();					
//	    						Thread.sleep(1000);
	    					}catch(InterruptedException e) {}
	    					
	    					/*SI EL JUGADOR CANTA TRUCO*/
	    					
	    					if(cantado[0].equals("truco")) {
	    						
	    						cantarTruco.setEnabled(false);
	    						flecha2.setEnabled(false);
	    						
	    						c1.setEnabled(false);
	    						c2.setEnabled(false);
	    						c3.setEnabled(false);
	    						
	    						MAQUINA.yourTurnAccept(cantado);
	    						
	    						/*SI LA IA QUIERE*/
	    						
	    						if(cantado[1].equals("quiero")) {			//BLOQUE 8
	    							
	    							/**PROVISORIO*/
	    							System.out.println("la IA quiso");
	    							/***/
	    							
	    							trucoQueridoBloque8();
			    					
	    						}
	    						
	    						/*SI LA IA NO QUIERE*/
	    						
	    						else if(cantado[1].equals("no quiero")) {
	    							
	    							/**PROVISORIO*/
	    							System.out.println("la IA no quiso");
	    							/***/
	    							
	    							//EL JUGADOR GANA

	    							sistPuntuacion(cantado,j,MAQUINA);
	    							acumulador = String.valueOf(j.getPuntos());
	    							pts1.setText(acumulador);
	    							
	    							puntosMaximosSuperados();
	    							//SIGUIENTE RONDA
	    						}
	    						
	    						/*SI LA IA REVIRA*/
	    						
	    						else if(cantado[1].equals("retruco")) {
	    							
	    							texto.setText("<html>"+ cantado[1] +"</html>");
		    						quiero.setEnabled(true);
		    						noQuiero.setEnabled(true);
		    						
		    						vale_4.setEnabled(true);
		    						
	    							accionUsuario = false;
			    					Thread espero29 = new Thread() {
			    						
			    						@Override
			    						public void run() {
			    				    		
			    							System.out.print("\nEspero que el jugador quiera/no quiera retruco (empardamos segunda y tire en tercera) ");
			    							while(accionUsuario == false) {
			    								try {
			    					    			System.out.print(". ");
			    					    			Thread.sleep(1000);
			    								} catch (InterruptedException e) {
			    									e.printStackTrace();
			    								}
			    				    		}
			    				    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			    						}
			    						
			    					};
			    					
			    					espero29.start();	
			    					
			    					try {
			    						espero29.join();					
//			    						Thread.sleep(1000);
			    					}catch(InterruptedException e) {}
	    							
	    							
			    					/*SI EL JUGADOR QUIERE*/
			    					
			    					if(cantado[2].equals("quiero")) {
			    						
			    						trucoQueridoBloque8();								//BLOQUE 8
			    						
			    					}
			    					
			    					/*SI EL JUGADOR NO QUIERE*/
			    					
			    					else if(cantado[2].equals("no quiero")) {
			    						//LA IA GANA

			    						sistPuntuacion(cantado,j,MAQUINA);
			    						acumulador = String.valueOf(MAQUINA.getPuntos());
			    						pts2.setText(acumulador);
			    						
			    						puntosMaximosSuperados();
			    						//SIGUIENTE RONDA
			    					}
			    					
			    					/*SI EL JUGADOR REVIRA*/
			    					
			    					else if(cantado[2].equals("vale cuatro")) {
			    						
			    						quiero.setEnabled(false);
			    						noQuiero.setEnabled(false);
			    						vale_4.setEnabled(false);
			    						
			    						MAQUINA.yourTurnAccept(cantado);
			    						
			    						/*SI LA IA QUIERE*/
			    						
			    						if(cantado[3].equals("quiero")) {
			    							
			    							/**PROVISORIO*/
			    							System.out.println("la IA quiso");
			    							/***/
			    							
			    							trucoQueridoBloque8();					//BLOQUE 8
			    							
			    						}
			    						
			    						/*SI LA IA NO QUIERE*/
			    						
			    						else {
			    							
			    							/**PROVISORIO*/
			    							System.out.println("la IA no quiso");
			    							/***/
			    							
			    							//EL JUGADOR GANA

			    							sistPuntuacion(cantado,j,MAQUINA);
			    							acumulador = String.valueOf(j.getPuntos());
			    							pts1.setText(acumulador);
			    							
			    							puntosMaximosSuperados();
			    							//SIGUIENTE RONDA
			    						}
			    						
			    					}
			    					
	    						}
	    						
	    					}
	    					
	    					/*SI EL JUGADOR TIRA CARTA DIRECTAMENTE*/
	    					
	    					else {
	    						
	    						trucoQueridoBloque8();
	    						
	    					}
	    					
	    				}
						
					}
					
				}
				
			}
			
		}
		
		
		
		/*SI EL JUGADOR LA MATA */
		
		else if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
			
			accionUsuario = false;
			Thread espero30 = new Thread() {
				
				@Override
				public void run() {
		    		
					System.out.print("\nEspero que el jugador tire en segunda (me gano primera) ");
					while(accionUsuario == false) {
						try {
			    			System.out.print(". ");
			    			Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		    		}
		    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
				}
				
			};
			
			espero30.start();	
			
			try {
				espero30.join();					
//				Thread.sleep(1000);
			}catch(InterruptedException e) {}
			
			/*EL JUGADOR CANTA TRUCO*/
			
			if(cantado[0].equals("truco")) {
				
				truco.setEnabled(false);
				flecha2.setEnabled(false);
				cantarTruco.setEnabled(false);
				
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				
				MAQUINA.yourTurnAccept(cantado);
				
				/*SI LA IA QUIERE*/
				
				if(cantado[1].equals("quiero")) {					//BLOQUE 12
					
					/**PROVISORIO*/
					System.out.println("la IA quiso");
					/***/
					
					trucoQueridoBloque12();
					
				}
				
				/*SI LA IA NO QUIERE*/
				
				else if(cantado[1].equals("no quiero")) {
					
					/**PROVISORIO*/
					System.out.println("la IA no quiso");
					/***/
					
					sistPuntuacion(cantado,j,MAQUINA);
					acumulador = String.valueOf(j.getPuntos());
					pts1.setText(acumulador);
					
					puntosMaximosSuperados();
				}
				
				/*SI LA IA REVIRA*/
				
				else if(cantado[1].equals("retruco")) {
					
					texto.setText("<html>"+ cantado[1] +"</html>");
					quiero.setEnabled(true);
					noQuiero.setEnabled(true);
					
					vale_4.setEnabled(true);
					
					accionUsuario = false;
					Thread espero29 = new Thread() {
						
						@Override
						public void run() {
							
							System.out.print("\nEspero que el jugador quiera/no quiera retruco ");
							while(accionUsuario == false) {
								try {
					    			System.out.print(". ");
					    			Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							System.out.println("\nRetomo la ejecucion (mi turno)\n");
						}
						
					};
																				    					
					espero29.start();	
																				    					
					try {
						espero29.join();					
						//Thread.sleep(1000);
					}catch(InterruptedException e) {}
					
					/*SI EL JUGADOR QUIERE*/
					
					if(cantado[2].equals("quiero")) {					//BLOQUE 12
						
						trucoQueridoBloque12();
						
					}
					
					/*SI EL JUGADOR NO QUIERE*/
					
					else if(cantado[2].equals("no quiero")) {
						//LA IA GANA
						
						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
					
					/*SI EL JUGADOR REVIRA*/
					
					else if(cantado[2].equals("vale cuatro")) {
						
						quiero.setEnabled(false);
						noQuiero.setEnabled(false);
						vale_4.setEnabled(false);
						
						MAQUINA.yourTurnAccept(cantado);
						
						/*SI LA IA QUIERE*/
						
						if(cantado[3].equals("quiero")) {					//BLOQUE 12
							
							/**PROVISORIO*/
							System.out.println("la IA quiso");
							/***/
							
							trucoQueridoBloque12();
							
						}
						
						/*SI LA IA NO QUIERE*/
						
						else {
							
							/**PROVISORIO*/
							System.out.println("la IA no quiso");
							/***/
							
							//EL JUGADOR GANA
							
							sistPuntuacion(cantado,j,MAQUINA);
							acumulador = String.valueOf(j.getPuntos());
							pts1.setText(acumulador);
							
							puntosMaximosSuperados();
							//SIGUIENTE RONDA
						}
						
					}
					
				}
				
			}
			
			/*EL JUGADOR TIRA DIRECTAMENTE*/
			
			else {
				
				//CHEQUEAR SI EFECTIVAMENTE SE TOMO LA SEGUNDA CARTA DE LA SEGUNDA TIRADA
				auxJ = queCartaFueTirada();
				
				cantarTruco.setEnabled(false);
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				
				//LE TOCA A LA IA
				
				tiraIA = null;
				tiraIA = MAQUINA.yourTurn(cantado, auxJ);
				auxIA = tiraIA[0];
				
				/*LA IA PUEDE CANTAR TRUCO*/
				
				if(cantado[0].equals("truco")) {
					
					texto.setText("<html>"+ cantado[0] +"</html>");
					quiero.setEnabled(true);
					noQuiero.setEnabled(true);
					
					truco.setEnabled(true);
					flecha2.setEnabled(false);
					retruco.setEnabled(true);
					
					accionUsuario = false;
					Thread espero32 = new Thread() {
						
						@Override
						public void run() {
				    		
							System.out.print("\nEspero que el jugador quiera/no quiera truco (me gano primera) ");
							while(accionUsuario == false) {
								try {
					    			System.out.print(". ");
					    			Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
				    		}
				    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
						}
						
					};
					
					espero32.start();	
					
					try {
						espero32.join();					
	//					Thread.sleep(1000);
					}catch(InterruptedException e) {}
					
					/*SI EL JUGADOR QUIERE*/
					
					if(cantado[1].equals("quiero")) {			//BLOQUE 9
						
						trucoQueridoBloque9();
						
					}
					
					/*SI EL JUGADOR NO QUIERE*/
					
					else if(cantado[1].equals("no quiero")) {
						//LA IA GANA
	
						sistPuntuacion(cantado,MAQUINA,j);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
					
					/*SI EL JUGADOR REVIRA*/
					
					else if(cantado[1].equals("retruco")) {
						
						quiero.setEnabled(false);
						noQuiero.setEnabled(false);
						retruco.setEnabled(false);
						
						MAQUINA.yourTurnAccept(cantado);
						
						/*SI LA IA QUIERE*/
						
						if(cantado[2].equals("quiero")) {		//BLOQUE 9
							
							/**PROVISORIO*/
							System.out.println("la IA quiso");
							/***/
							
							trucoQueridoBloque9();
							
						}
						
						/*SI LA IA NO QUIERE*/
						
						else if(cantado[2].equals("no quiero")) {
							
							/**PROVISORIO*/
							System.out.println("la IA no quiso");
							/***/
							
							//EL JUGADOR GANA
	
							sistPuntuacion(cantado,MAQUINA,j);
							acumulador = String.valueOf(j.getPuntos());
							pts1.setText(acumulador);
							
							puntosMaximosSuperados();
							//SIGUIENTE RONDA
						}
						
						/*SI LA IA REVIRA*/
						
						else if(cantado[2].equals("vale cuatro")) {
							
							texto.setText("<html>"+ cantado[2] +"</html>");
							quiero.setEnabled(true);
							noQuiero.setEnabled(true);
	
							accionUsuario = false;
	    					Thread espero33 = new Thread() {
	    						
	    						@Override
	    						public void run() {
	    				    		
	    							System.out.print("\nEspero que el jugador quiera/no quiera retruco (me gano primera) ");
	    							while(accionUsuario == false) {
	    								try {
	    					    			System.out.print(". ");
	    					    			Thread.sleep(1000);
	    								} catch (InterruptedException e) {
	    									e.printStackTrace();
	    								}
	    				    		}
	    				    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
	    						}
	    						
	    					};
	    					
	    					espero33.start();	
	    					
	    					try {
	    						espero33.join();					
	//    						Thread.sleep(1000);
	    					}catch(InterruptedException e) {}
							
							/*SI EL JUGADOR QUIERE*/
	    					
	    					if(cantado[3].equals("quiero")) {			//BLOQUE 9
	    						
	    						trucoQueridoBloque9();
	    						
	    					}
	    					
	    					/*SI EL JUGADOR NO QUIERE*/
	    					
	    					else {
	    						//LA IA GANA
	
	    						sistPuntuacion(cantado,MAQUINA,j);
	    						acumulador = String.valueOf(MAQUINA.getPuntos());
	    						pts2.setText(acumulador);
	    						
	    						puntosMaximosSuperados();
	    						//SIGUIENTE RONDA
	    					}
							
						}
						
					}
					
				}
				
				/*LA IA TIRA CARTA DIRECTAMENTE*/    	
				
				else {						
					
					tiratmp[0] = auxIA;
					tiraIAnull = true;						//CHEQUEAR ESTO
					mostrarTirada(tiratmp,tiraIAnull);
					
					/*SI LA IA MATA*/
					
					if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
						
						if(tiraIA[1].isGood() == true) {
							cantado[0] = "truco";
						}
						
						/*LA IA CANTA TRUCO*/
						
						if(cantado[0].equals("truco")) {
							
							texto.setText("<html>"+ cantado[0] +"</html>");
							quiero.setEnabled(true);
							noQuiero.setEnabled(true);
							
							truco.setEnabled(true);
							flecha2.setEnabled(true);
							retruco.setEnabled(true);
							
							accionUsuario = false;
							Thread espero38 = new Thread() {
								
								@Override
								public void run() {
									
									System.out.print("\nEspero que el jugador quiera/no quiera truco (me gano primera y gane segunda, pero antes de tirar, canto) ");
									while(accionUsuario == false) {
										try {
							    			System.out.print(". ");
							    			Thread.sleep(1000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									System.out.println("\nRetomo la ejecucion (mi turno)\n");
								}
								
							};
																						    					
							espero38.start();	
																						    					
							try {
								espero38.join();					
								//Thread.sleep(1000);
							}catch(InterruptedException e) {}
							
							/*SI EL JUGADOR QUIERE*/
							
							if(cantado[1].equals("quiero")) {							//BLOQUE 11
								
								trucoQueridoBloque11();
								
							}
							
							/*SI EL JUGADOR NO QUIERE*/
							
							else if(cantado[1].equals("no quiero")) {
								//LA IA GANA
								
								sistPuntuacion(cantado,MAQUINA,j);
								acumulador = String.valueOf(MAQUINA.getPuntos());
								pts2.setText(acumulador);
								
								puntosMaximosSuperados();
								//SIGUIENTE RONDA
								
							}
							
							/*SI EL JUGADOR REVIRA*/
							
							else if(cantado[1].equals("retruco")) {
								
								quiero.setEnabled(false);
								noQuiero.setEnabled(false);
								retruco.setEnabled(false);
								
								MAQUINA.yourTurnAccept(cantado);
								
								/*SI LA IA QUIERE*/
								
								if(cantado[2].equals("quiero")) {				//BLOQUE 11
									
									/**PROVISORIO*/
									System.out.println("la IA quiso");
									/***/
									
									trucoQueridoBloque11();
									
								}
								
								/*SI LA IA NO QUIERE*/
								
								else if(cantado[2].equals("no quiero")) {
									
									/**PROVISORIO*/
									System.out.println("la IA no quiso");
									/***/
									
									//EL JUGADOR GANA
									
									sistPuntuacion(cantado,MAQUINA,j);
									acumulador = String.valueOf(MAQUINA.getPuntos());
									pts2.setText(acumulador);
									
									puntosMaximosSuperados();
									//SIGUIENTE RONDA
									
								}
								
								/*SI LA IA REVIRA*/
								
								else if(cantado[2].equals("vale cuatro")) {
									
									texto.setText("<html>"+ cantado[2] +"</html>");
									quiero.setEnabled(true);
									noQuiero.setEnabled(true);

									accionUsuario = false;
									Thread espero39 = new Thread() {
										
										@Override
										public void run() {
											
											System.out.print("\nEspero que el jugador quiera/no quiera vale cuatro ");
											while(accionUsuario == false) {
												try {
									    			System.out.print(". ");
									    			Thread.sleep(1000);
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
											}
											System.out.println("\nRetomo la ejecucion (mi turno)\n");
										}
										
									};
																								    					
									espero39.start();	
																								    					
									try {
										espero39.join();					
										//Thread.sleep(1000);
									}catch(InterruptedException e) {}
									
									/*SI EL JUGADOR QUIERE*/
									
									if(cantado[3].equals("quiero")) {				//BLOQUE 11
										
										trucoQueridoBloque11();
										
									}
									
									/*SI EL JUGADOR NO QUIERE*/
									
									else {
										//LA IA GANA
										
										sistPuntuacion(cantado,MAQUINA,j);
										acumulador = String.valueOf(MAQUINA.getPuntos());
										pts2.setText(acumulador);
										
										puntosMaximosSuperados();
										//SIGUIENTE RONDA
									}
									
								}
								
							}
							
						}
						
						/*LA IA TIRA DIRECTAMENTE*/
						
						else {
					
							//CHEQUEAR, PORQUE SI TIRA BAJA ENTONCES NO PUEDE VOLVER A TIRAR, PORQUE EL JUGADOR LE GANO DOS TIRADAS
							//OJO CON PONERLO A FALSO
							tiratmp[0] = null;
							tiratmp[0] = tiraIA[1];
							tiraIAnull = true;
							mostrarTirada(tiratmp,tiraIAnull);
							
							if(c1.getIcon() != null) {
								c1.setEnabled(true);
							}
							if(c2.getIcon() != null) {
								c2.setEnabled(true);
							}
							if(c3.getIcon() != null) {
								c3.setEnabled(true);
							}
							
							truco.setEnabled(true);
							flecha2.setEnabled(true);
							cantarTruco.setEnabled(true);
							
							accionUsuario = false;
							Thread espero31 = new Thread() {
								
								@Override
								public void run() {
						    		
									System.out.print("\nEspero que el jugador tire en tercera (me gano primera y le gane segunda) ");
									while(accionUsuario == false) {
										try {
							    			System.out.print(". ");
							    			Thread.sleep(1000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
						    		}
						    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
								}
								
							};
							
							espero31.start();	
							
							try {
								espero31.join();					
		//						Thread.sleep(1000);
							}catch(InterruptedException e) {}
							
							/*EL JUGADOR PUEDE CANTAR TRUCO*/
							
							if(cantado[0].equals("truco")) {
								
								flecha2.setEnabled(false);
								cantarTruco.setEnabled(false);
								
					    		c1.setEnabled(false);
					    		c2.setEnabled(false);
					    		c3.setEnabled(false);
								
								MAQUINA.yourTurnAccept(cantado);
								
								/*SI LA IA QUIERE*/
								
								if(cantado[1].equals("quiero")) {					//BLOQUE 10
									
									trucoQueridoBloque10();
								}
								
								/*SI LA IA NO QUIERE*/
								
								else if(cantado[1].equals("no quiero")) {
									
									/**PROVISORIO*/
									System.out.println("la IA no quiso");
									/***/
									
									//EL JUGADOR GANA
		
									sistPuntuacion(cantado,j,MAQUINA);
									acumulador = String.valueOf(j.getPuntos());
									pts1.setText(acumulador);
									
									puntosMaximosSuperados();
									//SIGUIENTE RONDA											    							
								}
								
								/*SI LA IA REVIRA*/
								
								else if(cantado[1].equals("retruco")) {
									
									texto.setText("<html>"+ cantado[1] +"</html>");
									quiero.setEnabled(true);
									noQuiero.setEnabled(true);
									vale_4.setEnabled(true);
									
						    		accionUsuario = false;
									Thread espero38 = new Thread() {
			    	    			
			    	    			@Override
			    	    			public void run() {
			    			    		
			    	    				System.out.print("\nEspero que el jugador quiera/no quiera el retruco ");
			    	    				while(accionUsuario == false) {
			    	    					try {
			    				    			System.out.print(". ");
			    				    			Thread.sleep(1000);
			    							} catch (InterruptedException e) {
			    								e.printStackTrace();
			    							}
			    			    		}
			    			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			    	    			}
			    	    			
									};
			        			
				    	    		espero38.start();	
				    	    		
				    	    		try {
				    					espero38.join();					
//															    					Thread.sleep(1000);
				    				}catch(InterruptedException e) {}
									
									
				    	    		/*SI EL JUGADOR QUIERE*/
				    	    		
				    	    		if(cantado[2].equals("quiero")) {							//BLOQUE 10
				    	    			
				    	    			trucoQueridoBloque10();
				    	    			
				    	    		}
				    	    		
				    	    		/*SI EL JUGADOR NO QUIERE*/
				    	    		
				    	    		else if(cantado[2].equals("no quiero")) {
				    	    			//EL JUGADOR GANA
				    	    			
				    	    			sistPuntuacion(cantado,j,MAQUINA);
										acumulador = String.valueOf(MAQUINA.getPuntos());
										pts2.setText(acumulador);
										
										puntosMaximosSuperados();
				    	    			//SIGUIENTE RONDA
				    	    			
				    	    		}
				    	    		
				    	    		/*SI EL JUGADOR REVIRA*/
				    	    		
				    	    		else if(cantado[2].equals("vale cuatro")) {
				    	    			
				    	    			quiero.setEnabled(false);
				    	    			noQuiero.setEnabled(false);
				    	    			vale_4.setEnabled(false);
				    	    			
				    	    			MAQUINA.yourTurnAccept(cantado);
				    	    			
				    	    			/*SI LA IA QUIERE*/
				    	    			
				    	    			if(cantado[3].equals("quiero")) {									//BLOQUE 10
				    	    				
				    	    				trucoQueridoBloque10();
				    	    				
				    	    			}
				    	    			
				    	    			/*SI LA IA NO QUIERE*/
				    	    			
				    	    			else {
				    	    				
				    	    				/**PROVISORIO*/
											System.out.println("la IA no quiso");
											/***/
											
											sistPuntuacion(cantado,j,MAQUINA);
											acumulador = String.valueOf(j.getPuntos());
											pts1.setText(acumulador);
				    	    				
											puntosMaximosSuperados();
				    	    			}
				    	    			
				    	    		}
				    	    		
				    	    		
								}
							
								
							}
							
							/*EL JUGADOR PUEDE TIRAR CARTA DIRECTAMENTE*/
							
							else {													//PARTE DEL BLOQUE 10, PERO SE DEJA ASI
								
								auxIA = tiraIA[1];
								auxJ = queCartaFueTirada();
								
								/*SI EL JUGADOR MATA*/
								
								if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
									//EL JUGADOR GANA
		
									//sistPuntuacion(cantadoTruco,j,MAQUINA);
									j.addPuntos(1);
									acumulador = String.valueOf(j.getPuntos());
									pts1.setText(acumulador);
									
									puntosMaximosSuperados();
									//SIGUIENTE RONDA
								}
								
								/*SI EL JUGADOR NO MATA*/
								
								else {
									//LA IA GANA
		
									//sistPuntuacion(cantadoTruco,j,MAQUINA);
									MAQUINA.addPuntos(1);
									acumulador = String.valueOf(MAQUINA.getPuntos());
									pts2.setText(acumulador);
									
									puntosMaximosSuperados();
									//SIGUIENTE RONDA
								}
								
							}
						
						
						}
						
						
					}
					
					/*SI LA IA NO MATA O EMPARDA*/
					
					else {
						
						tiraIAnull = false;
						mostrarTirada(tiraIA,tiraIAnull);
						
						//EL JUGADOR GANA
	
						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
						
					}
					
					
				}
			
			}
			
													    			
		}
		
	}
	
	
	//FUNCION PARA SABER QUE CARTA TIRO EL JUGADOR CUANDO APRETO UN BOTON
	public Carta queCartaFueTirada() {		
		
		//OJO CON ESTO, CUANDO YA RETORNO UNA CARTA, ENTONCES CUALQUIER 'C' DEBE ANULARSE, PORQUE YA NO EXISTE MAS NADA AH�
		
		if(c1.getIcon()==null && c1.isEnabled() != false) {
			c1.setEnabled(false);
			return j.cartas[0];		//OJO CON ESTO, POSIBLEMENTE ESTE VACIO (O TAL VEZ LAS CARTAS DE LA IA ESTEN VACIAS)
		}
		else if(c2.getIcon()==null && c2.isEnabled() != false) {
			c2.setEnabled(false);
			return j.cartas[1];
		}
		else if(c3.getIcon()==null && c3.isEnabled() != false){
			c3.setEnabled(false);
			return j.cartas[2];
		}
		return null;
	}
	
	
	//CUANDO APRETO UN BOTON, SE TIRA LA CARTA EN MESA
	public void tirarCartaEnMesa(JButton c) {
		
		if(l4.getIcon() == null) {
			l4.setIcon(c.getIcon());
		} else if(l5.getIcon() == null) {
			l5.setIcon(c.getIcon());
		} else {
			l6.setIcon(c.getIcon());
		}
		
		c.setIcon(null);		//EN VEZ DE HACERLA NULL, DESACTIVO EL BOTON Y DEJO EL ICONO
		
	}
	
	
	public void trucoQueridoBloque12() {
		
		flecha2.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		Thread espero41 = new Thread() {
			
			@Override
			public void run() {
	    		
				System.out.print("\nEspero que el jugador tire en segunda (me gano primera) ");
				while(accionUsuario == false) {
					try {
		    			System.out.print(". ");
		    			Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		}
	    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			}
			
		};
		
		espero41.start();	
		
		try {
			espero41.join();					
//			Thread.sleep(1000);
		}catch(InterruptedException e) {}
		
		
		//CHEQUEAR SI EFECTIVAMENTE SE TOMO LA SEGUNDA CARTA DE LA SEGUNDA TIRADA
		auxJ = queCartaFueTirada();
		
		c1.setEnabled(false);
		c2.setEnabled(false);
		c3.setEnabled(false);
		
		//LE TOCA A LA IA
		
		tiraIA = null;
		tiraIA = MAQUINA.yourTurn(cantado, auxJ);
		auxIA = tiraIA[0];
		tiraIAnull = true;
		mostrarTirada(tiraIA,tiraIAnull);
		
		/*SI LA IA MATA*/
		
		if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
			
			tiraIA = null;
			tiraIA = MAQUINA.yourTurn(cantado, null);
			auxIA = tiraIA[0];
			tiraIAnull = true;
			mostrarTirada(tiraIA,tiraIAnull);
		
			if(c1.getIcon() != null) {
				c1.setEnabled(true);
			}
			if(c2.getIcon() != null) {
				c2.setEnabled(true);
			}
			if(c3.getIcon() != null) {
				c3.setEnabled(true);
			}
			
			accionUsuario = false;
			Thread espero31 = new Thread() {
				
				@Override
				public void run() {
		    		
					System.out.print("\nEspero que el jugador tire en tercera (me gano primera y le gane segunda) ");
					while(accionUsuario == false) {
						try {
			    			System.out.print(". ");
			    			Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		    		}
		    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
				}
				
			};
			
			espero31.start();	
			
			try {
				espero31.join();					
//						Thread.sleep(1000);
			}catch(InterruptedException e) {}
			
			//EL JUGADOR TIRA EN TERCERA
			auxIA = tiraIA[0];
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR MATA*/
			
			if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
				//EL JUGADOR GANA

				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
			/*SI EL JUGADOR NO MATA*/
			
			else {
				//LA IA GANA

				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
					
		}

		/*SI LA IA NO MATA O EMPARDA*/
		
		else {
			
			tiraIAnull = false;
			mostrarTirada(tiraIA,tiraIAnull);
			
			//EL JUGADOR GANA

			sistPuntuacion(cantado,j,MAQUINA);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
			
		}
		
	}
	
	public void trucoQueridoBloque11() {
		
		//CHEQUEAR ESTO SI TIRA CORRECTAMENTE LA TERCER CARTA
		//tiraIAnull = true;
		//mostrarTirada(tiraIA,tiraIAnull);
		
		tiratmp[0] = null;
		tiratmp[0] = tiraIA[1];
		tiraIAnull = true;
		mostrarTirada(tiratmp,tiraIAnull);
		
		flecha2.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		accionUsuario = false;
		Thread espero40 = new Thread() {
			
			@Override
			public void run() {
	    		
				System.out.print("\nEspero que el jugador tire ");
				while(accionUsuario == false) {
					try {
		    			System.out.print(". ");
		    			Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		}
	    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			}
			
		};
		
		espero40.start();	
		
		try {
			espero40.join();					
//						Thread.sleep(1000);
		}catch(InterruptedException e) {}
		
		auxIA = tiraIA[1];
		auxJ = queCartaFueTirada();
		
		/*SI EL JUGADOR MATA*/
		
		if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
			//EL JUGADOR GANA

			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
		/*SI EL JUGADOR NO MATA*/
		
		else {
			//LA IA GANA

			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
	}
	
	public void trucoQueridoBloque10() {
		
		/**PROVISORIO*/
		System.out.println("la IA quiso");
		/***/
		
		flecha2.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		//ESPERO QUE EL JUGADOR TIRE CARTA O SE VAYA AL MAZO (SOLO ESAS DOS OPCIONES)
		accionUsuario = false;
		Thread espero32 = new Thread() {
			
			@Override
			public void run() {
	    		
				System.out.print("\nEspero que el jugador tire en tercera (me gano primera y gane segunda) ");
				while(accionUsuario == false) {
					try {
		    			System.out.print(". ");
		    			Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		}
	    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			}
			
		};
		
		espero32.start();	
		
		try {
			espero32.join();					
//	    						Thread.sleep(1000);
		}catch(InterruptedException e) {}
		
		auxIA = tiraIA[1];
		auxJ = queCartaFueTirada();
		
		/*SI EL JUGADOR MATA*/
		
		if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
			//EL JUGADOR GANA

			sistPuntuacion(cantado,j,MAQUINA);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
		/*SI EL JUGADOR NO MATA*/
		
		else {
			//LA IA GANA
			sistPuntuacion(cantado,j,MAQUINA);
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
	}
	
	public void trucoQueridoBloque9() {
		
		tiratmp[0] = auxIA;
		tiraIAnull = true;						//CHEQUEAR ESTO
		mostrarTirada(tiratmp,tiraIAnull);
		
		/*SI LA IA MATA*/
		
		if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
			
			//CHEQUEAR, PORQUE SI TIRA BAJA ENTONCES NO PUEDE VOLVER A TIRAR, PORQUE EL JUGADOR LE GANO DOS TIRADAS
			tiratmp[0] = null;
			tiratmp[0] = tiraIA[1];
			tiraIAnull = true;
			mostrarTirada(tiratmp,tiraIAnull);
			
			if(c1.getIcon() != null) {
				c1.setEnabled(true);
			}
			if(c2.getIcon() != null) {
				c2.setEnabled(true);
			}
			if(c3.getIcon() != null) {
				c3.setEnabled(true);
			}
			
			accionUsuario = false;
			Thread espero31 = new Thread() {
				
				@Override
				public void run() {
		    		
					System.out.print("\nEspero que el jugador tire en tercera (me gano primera y le gane segunda) ");
					while(accionUsuario == false) {
						try {
			    			System.out.print(". ");
			    			Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		    		}
		    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
				}
				
			};
			
			espero31.start();	
			
			try {
				espero31.join();					
//				Thread.sleep(1000);
			}catch(InterruptedException e) {}
			
			//EL JUGADOR TIRA
				
			auxIA = tiraIA[1];
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR MATA*/
			
			if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
				//EL JUGADOR GANA

				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
			/*SI EL JUGADOR NO MATA*/
			
			else {
				//LA IA GANA

				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
		}
		
		/*SI LA IA NO MATA O EMPARDA*/
		
		else {
			
			tiraIAnull = false;
			mostrarTirada(tiraIA,tiraIAnull);
			
			//EL JUGADOR GANA

			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
			
		}
		
	}
	
	public void trucoQueridoBloque8() {
		
		flecha2.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		accionUsuario = false;
		Thread espero30 = new Thread() {
			
			@Override
			public void run() {
	    		
				System.out.print("\nEspero que el jugador tire en tercera (empardamos segunda y ya tire todas las cartas) ");
				while(accionUsuario == false) {
					try {
		    			System.out.print(". ");
		    			Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		}
	    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			}
			
		};
		
		espero30.start();	
		
		try {
			espero30.join();					
//			Thread.sleep(1000);
		}catch(InterruptedException e) {}
		
		auxJ = queCartaFueTirada();
		
		/*SI EL JUGADOR LA MATA*/
		
		if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
			//EL JUGADOR GANA

			sistPuntuacion(cantado,j,MAQUINA);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
		/*SI EL JUGADOR NO LA MATA*/
		
		else if(aux.returnOrden(auxJ) > aux.returnOrden(auxIA)) {
			//LA IA GANA

			sistPuntuacion(cantado,j,MAQUINA);
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
		/*SI EL JUGADOR EMPARDA*/
		
		else {
			//EL JUGADOR GANA
			
			j.addPuntos(1);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
		}
		
	}
	
	public void trucoQueridoBloque7() {
		
		tiraIAnull = true;
		mostrarTirada(tiraIA,tiraIAnull);
		auxIA = tiraIA[0];
		
		flecha2.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		accionUsuario = false;
		Thread espero27 = new Thread() {
			
			@Override
			public void run() {
	    		
				System.out.print("\nEspero que el jugador tire en tercera (empardamos segunda) ");
				while(accionUsuario == false) {
					try {
		    			System.out.print(". ");
		    			Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		}
	    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			}
			
		};
		
		espero27.start();	
		
		try {
			espero27.join();					
//			Thread.sleep(1000);
		}catch(InterruptedException e) {}
		
		auxJ = queCartaFueTirada();
		
		/*SI EL JUGADOR LA MATA*/
		
		if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
			//EL JUGADOR GANA

			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
		/*SI EL JUGADOR NO LA MATA*/
		
		else {
			//LA IA GANA

			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
	}
	
	public void trucoQueridoBloque6() {
		
		
		
		flecha2.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		accionUsuario = false;
		Thread espero22 = new Thread() {
			
			@Override
			public void run() {
	    		
				System.out.print("\nEspero que el jugador tire en segunda (empardamos primera) ");
				while(accionUsuario == false) {
					try {
		    			System.out.print(". ");
		    			Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		}
	    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			}
			
		};
		
		espero22.start();	
		
		try {
			espero22.join();					
//			Thread.sleep(1000);
		}catch(InterruptedException e) {}
		
		auxJ = queCartaFueTirada();
		
		/*SI EL JUGADOR MATA*/
		
		if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
			//EL JUGADOR GANA

			sistPuntuacion(cantado,j,MAQUINA);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
		/*SI EL JUGADOR NO MATA*/
		
		else if(aux.returnOrden(auxJ) > aux.returnOrden(auxIA)) {
			//LA IA GANA

			sistPuntuacion(cantado,j,MAQUINA);
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
		/*SI EL JUGADOR EMPARDA*/
		
		else {
			
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			
			//TIRA LA IA
			tiraIA = null;
			tiraIA = MAQUINA.yourTurn(cantado, null);
			tiraIAnull = true;
			mostrarTirada(tiraIA,tiraIAnull);
			auxIA = tiraIA[0];
			
			if(c1.getIcon() != null) {
				c1.setEnabled(true);
			}
			if(c2.getIcon() != null) {
				c2.setEnabled(true);
			}
			if(c3.getIcon() != null) {
				c3.setEnabled(true);
			}
			
			accionUsuario = false;
			Thread espero23 = new Thread() {
    			
    			@Override
    			public void run() {
		    		
    				System.out.print("\nEspero que el jugador tire en tercera (empardamos segunda) ");
    				while(accionUsuario == false) {
    					try {
			    			System.out.print(". ");
			    			Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		    		}
		    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
    			}
    			
    		};
			
    		espero23.start();	
    		
    		try {
				espero23.join();					
//				Thread.sleep(1000);
			}catch(InterruptedException e) {}
			
			//TIRA EL JUGADOR
			
    		auxJ = queCartaFueTirada();
    		
			/*SI EL JUGADOR MATA*/
			
			if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
				//EL JUGADOR GANA

				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
			/*SI EL JUGADOR NO MATA*/
			
			else {
				//LA IA GANA

				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
		}
		
	}
	
	public void trucoQueridoBloque5() {
		
		truco.setEnabled(false);
		flecha2.setEnabled(true);				//POR SI QUIERE IRSE AL MAZO
		quiero.setEnabled(false);
		noQuiero.setEnabled(false);
		
		//TIRA LA IA
		
		tiraIAnull = true;
		mostrarTirada(tiraIA,tiraIAnull);
		auxIA = tiraIA[0];
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		accionUsuario = false;
		Thread espero20 = new Thread() {
			
			@Override
			public void run() {
	    		
				System.out.print("\nEspero que el jugador tire en segunda (empardamos primera) ");
				while(accionUsuario == false) {
					try {
		    			System.out.print(". ");
		    			Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		}
	    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			}
			
		};
		
		espero20.start();	
		
		try {
			espero20.join();					
//			Thread.sleep(1000);
		}catch(InterruptedException e) {}
		
		//TIRA EL JUGADOR
		
		auxJ = queCartaFueTirada();
		
		/*SI EL JUGADOR MATA*/
		
		if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
			//EL JUGADOR GANA

			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
		/*SI EL JUGADOR NO MATA*/
		
		else if(aux.returnOrden(auxJ) > aux.returnOrden(auxIA)) {
			//LA IA GANA

			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
		/*SI EL JUGADOR EMPARDA*/
		
		else {
			
			//LA IA TIRA
			tiraIA = null;
			tiraIA = MAQUINA.yourTurn(cantado, null);
			tiraIAnull = true;
			mostrarTirada(tiraIA,tiraIAnull);
			auxIA = tiraIA[0];
			
			accionUsuario = false;
			Thread espero21 = new Thread() {
    			
    			@Override
    			public void run() {
		    		
    				System.out.print("\nEspero que el jugador tire en tercera (empardamos segunda) ");
    				while(accionUsuario == false) {
    					try {
			    			System.out.print(". ");
			    			Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		    		}
		    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
    			}
    			
    		};
			
    		espero21.start();	
    		
    		try {
				espero21.join();					
//				Thread.sleep(1000);
			}catch(InterruptedException e) {}
			
			//EL JUGADOR TIRA
			
    		auxJ = queCartaFueTirada();
    		
			/*SI EL JUGADOR MATA*/
			
			if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
				//EL JUGADOR GANA

				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
			/*SI EL JUGADOR NO MATA*/
			
			else {
				//LA IA GANA

				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
		}
		
	}
	
	public void trucoQueridoBloque4() {
		
		tiraIAnull = true;
		mostrarTirada(tiraIA,tiraIAnull);
		auxIA = tiraIA[0];
		
		/*SI LA IA MATA*/
		
		if(aux.returnOrden(auxIA) <= aux.returnOrden(auxJ)) {
			//LA IA GANA

			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
		/*SI LA IA NO MATA*/
		
		else {
			//LA IA GANA

			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
	}
	
	public void trucoQueridoBloque3() {
		
		flecha2.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		accionUsuario = false;
		Thread espero18 = new Thread() {
			
			@Override
			public void run() {
	    		
				System.out.print("\nEspero que el jugador tire en tercera (me gano segunda primera) ");
				while(accionUsuario == false) {
					try {
		    			System.out.print(". ");
		    			Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		}
	    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			}
			
		};
		
		espero18.start();	
		
		try {
			espero18.join();					
//			Thread.sleep(1000);
		}catch(InterruptedException e) {}
		
		//EL JUGADOR TIRA LA ULTIMA CARTA
		auxJ = queCartaFueTirada();
		
		//LA IA TIRA LA ULTIMA CARTA
		tiraIA = null;
		tiraIA = MAQUINA.yourTurn(cantado, null);
		tiraIAnull = true;
		mostrarTirada(tiraIA,tiraIAnull);
		auxIA = tiraIA[0];
		
		/*SI LA IA MATA*/
		
		if(aux.returnOrden(auxIA) <= aux.returnOrden(auxJ) ) {
			//LA IA GANA

			sistPuntuacion(cantado,j,MAQUINA);
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
		/*SI LA IA NO MATA*/
		
		else {
			//EL JUGADOR GANA

			sistPuntuacion(cantado,j,MAQUINA);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
	}
	
	public void trucoQueridoBloque2() {
		
		flecha2.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		accionUsuario = false;
		Thread espero16 = new Thread() {
			
			@Override
			public void run() {
	    		
				System.out.print("\nEspero que el jugador tire en segunda (le gane primera) ");
				while(accionUsuario == false) {
					try {
		    			System.out.print(". ");
		    			Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		}
	    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			}
			
		};
		
		espero16.start();	
		
		try {
			espero16.join();					
//			Thread.sleep(1000);
		}catch(InterruptedException e) {}
		
		auxJ = queCartaFueTirada();
		
		/*SI EL JUGADOR NO LA MATA/EMPARDA*/
		
		if(aux.returnOrden(auxJ) >= aux.returnOrden(auxIA)) {
			
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			
			//LA IA GANA

			sistPuntuacion(cantado,j,MAQUINA);
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
		/*SI EL JUGADOR LA MATA*/
		
		else {
			
			//TIRA NUEVAMENTE
			accionUsuario = false;
			Thread espero17 = new Thread() {
    			
    			@Override
    			public void run() {
		    		
    				System.out.print("\nEspero que el jugador tire en tercera (me gano segunda) ");
    				while(accionUsuario == false) {
    					try {
			    			System.out.print(". ");
			    			Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		    		}
		    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
    			}
    			
    		};
			
    		espero17.start();	
    		
    		try {
				espero17.join();					
//				Thread.sleep(1000);
			}catch(InterruptedException e) {}
			
    		auxJ = queCartaFueTirada();
    		
    		tiraIA = null;
			tiraIA = MAQUINA.yourTurn(cantado, null);
			tiraIAnull = true;
			mostrarTirada(tiraIA,tiraIAnull);
			auxIA = tiraIA[0];
    		
			/*SI LA IA MATA*/
			
			if(aux.returnOrden(auxIA) <= aux.returnOrden(auxJ)) {
				//LA IA GANA

				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
			/*SI LA IA NO MATA*/
			
			else {
				//EL JUGADOR GANA

				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
		}
		
	}
	
	
	public void trucoQueridoBloque1() {
		
		truco.setEnabled(false);
		flecha2.setEnabled(true);				//POR SI QUIERE IRSE AL MAZO
		quiero.setEnabled(false);
		noQuiero.setEnabled(false);
				
		//LA IA TIRA
		tiraIAnull = true;
		mostrarTirada(tiraIA,tiraIAnull);
		auxIA = tiraIA[0];
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		accionUsuario = false;
		Thread espero14 = new Thread() {
			
			@Override
			public void run() {
	    		
				System.out.print("\nEspero que el jugador tire en segunda (le gane primera) ");
				while(accionUsuario == false) {
					try {
		    			System.out.print(". ");
		    			Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		}
	    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			}
			
		};
		
		espero14.start();	
		
		try {
			espero14.join();					
//			Thread.sleep(1000);
		}catch(InterruptedException e) {}
		
		auxJ = queCartaFueTirada();
		
		/*SI EL JUGADOR MATA*/
		
		if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
			
			accionUsuario = false;
			Thread espero16 = new Thread() {
    			
    			@Override
    			public void run() {
		    		
    				System.out.print("\nEspero que el jugador tire en tercera (me gano segunda) ");
    				while(accionUsuario == false) {
    					try {
			    			System.out.print(". ");
			    			Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		    		}
		    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
    			}
    			
    		};
			
    		espero16.start();	
    		
    		try {
				espero16.join();					
//				Thread.sleep(1000);
			}catch(InterruptedException e) {}
			
    		//EL JUGADOR TIRA LA ULTIMA
    		auxJ = queCartaFueTirada();
    		
    		//LA IA TIRA LA ULTIMA
    		tiraIA = null;
			tiraIA = MAQUINA.yourTurn(cantado, null);
    		tiraIAnull = true;
			mostrarTirada(tiraIA,tiraIAnull);
			auxIA = tiraIA[0];
    		
			/*SI LA IA MATA O EMPARDA*/
			
			if(aux.returnOrden(auxIA) <= aux.returnOrden(auxJ)) {
				//LA IA GANA

				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
			/*SI LA IA NO MATA*/
			
			else {
				//EL JUGADOR GANA

				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
		}
		
		/*SI EL JUGADOR NO MATA*/
		
		else {
			//LA IA GANA

			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
		
		
	}
	
	
	public void trucoQuerido() {
		
		c1.setEnabled(true);
		c2.setEnabled(true);
		c3.setEnabled(true);
		
		accionUsuario = false;
		truco.setEnabled(false);
		flecha2.setEnabled(true);
				
		Thread espero7 = new Thread() {
			
			@Override
			public void run() {
	    		
				System.out.print("\nEspero que el jugador tire en primera ");
				while(accionUsuario == false) {
					try {
		    			System.out.print(". ");
		    			Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		}
	    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
			}
			
		};
		
		espero7.start();	
		
		try {
			espero7.join();					
//			Thread.sleep(1000);
		}catch(InterruptedException e) {}
		
		auxJ = queCartaFueTirada();
		System.out.println(auxJ + " chequeando");
		
		/*SI EL JUGADOR NO LA MATA*/
		
		if(aux.returnOrden(auxJ) > aux.returnOrden(auxIA)) {
			
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			
			tiraIA = null;
			MAQUINA.activatePuedoCantarEnvido(false);
			tiraIA = MAQUINA.yourTurn(cantado, null);			//OJO, LA MAQUINA NO DEBERIA CANTAR MAS PORQUE YA SE CANTO ANTES (MANEJARME CON UN ARREGLO DE CANTADOS PARALELO)
			tiraIAnull = true;
			mostrarTirada(tiraIA,tiraIAnull);
			
			if(c1.getIcon() != null) {
				c1.setEnabled(true);
			}
			if(c2.getIcon() != null) {
				c2.setEnabled(true);
			}
			if(c3.getIcon() != null) {
				c3.setEnabled(true);
			}
			
			accionUsuario = false;
			Thread espero8 = new Thread() {
    			
    			@Override
    			public void run() {
		    		
    				System.out.print("\nEspero que el jugador tire en segunda (gane primera) ");
    				while(accionUsuario == false) {
    					try {
			    			System.out.print(". ");
			    			Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		    		}
		    		System.out.println("\nRetomo la ejecucion (me toca a mi)\n");
    			}
    			
    		};
			
    		espero8.start();	
    		
    		try {
				espero8.join();					
//				Thread.sleep(1000);
			}catch(InterruptedException e) {}
			
    		/*SI EL JUGADOR NO LA MATA O EMPARDA*/
    		
    		auxJ = queCartaFueTirada();
    		auxIA = tiraIA[0];
    		
    		if(aux.returnOrden(auxJ) >= aux.returnOrden(auxIA)) {
    			
    			//SE SUMAN PUNTOS
    			
    			sistPuntuacion(cantado,j,MAQUINA);
    			acumulador = String.valueOf(MAQUINA.getPuntos());
    			pts2.setText(acumulador);
    			
    			puntosMaximosSuperados();
    			//SE PASA A LA SIGUIENTE RONDA
    			
    		}
    		
    		/*SI EL JUGADOR LA MATA*/
    		
    		else {
    			
    			auxJ = queCartaFueTirada();		//se puede comentar/eliminar
    			
    			accionUsuario = false;
    			Thread espero9 = new Thread() {
	    			
	    			@Override
	    			public void run() {
			    		
	    				System.out.print("\nEspero que el jugador tire en tercera (le gane primera y me gano segunda) ");
	    				while(accionUsuario == false) {
	    					try {
				    			System.out.print(". ");
				    			Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
			    		}
			    		System.out.println("\nRetomo la ejecucion (mi turno)\n");
	    			}
	    			
	    		};
    			
	    		espero9.start();	
	    		
	    		try {
					espero9.join();					
//					Thread.sleep(1000);
				}catch(InterruptedException e) {}
    			
	    		auxJ = queCartaFueTirada();
	    		
	    		c1.setEnabled(false);
	    		c2.setEnabled(false);
	    		c3.setEnabled(false);
	    		
	    		//chequear de hacer null a tiraIA
	    		tiraIA = null;
    			tiraIA = MAQUINA.yourTurn(cantado, auxJ);
    			auxIA = tiraIA[0];
    			
    			/*SI LA IA MATA*/
    			
    			if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
    				
    				tiraIAnull = true;
    				mostrarTirada(tiraIA,tiraIAnull);
    				//SE SUMAN PUNTOS

    				sistPuntuacion(cantado,j,MAQUINA);
    				acumulador = String.valueOf(MAQUINA.getPuntos());
    				pts2.setText(acumulador);
    				
    				puntosMaximosSuperados();
    				//EL JUGADOR PIERDE
    				//SE PASA LA SIGUIENTE RONDA
    				
    			}
    			
    			/*SI LA IA NO MATA*/
    			
    			else {
    				//SE SUMAN PUNTOS

    				sistPuntuacion(cantado,j,MAQUINA);
    				acumulador = String.valueOf(j.getPuntos());
    				pts1.setText(acumulador);
    				
    				puntosMaximosSuperados();
    				//LA IA PIERDE
    				//SE PASA A LA SIGUIENTE RONDA
    				
    			}
    			
    		}
    		
		}
		
		
		
		/*SI EL JUGADOR LA EMPARDA*/
		
		else if(aux.returnOrden(auxJ) == aux.returnOrden(auxIA)) {
			
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			
			//LA IA TIRA
			
			MAQUINA.activatePuedoCantarEnvido(false);
			tiraIA = null;
			//chequear de hacer null tiraIA 
			tiraIA = MAQUINA.yourTurn(cantado, null);
			tiraIAnull = true;
			mostrarTirada(tiraIA,tiraIAnull);
			auxIA = tiraIA[0];
			
			if(c1.getIcon() != null) {
				c1.setEnabled(true);
			}
			if(c2.getIcon() != null) {
				c2.setEnabled(true);
			}
			if(c3.getIcon() != null) {
				c3.setEnabled(true);
			}
			
			accionUsuario = false;
			Thread espero10 = new Thread() {
    			
    			@Override
    			public void run() {
		    		
    				System.out.print("\nEspero que el jugador tire en segunda (empardamos primera y ya tire en segunda) ");
    				while(accionUsuario == false) {
    					try {
			    			System.out.print(". ");
			    			Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		    		}
		    		System.out.println("\nRetomo la ejecucion (me toca a mi)\n");
    			}
    			
    		};
			
    		espero10.start();	
    		
    		try {
				espero10.join();					
//				Thread.sleep(1000);
			}catch(InterruptedException e) {}
			
    		auxJ = queCartaFueTirada();
    		
    		/*SI EL JUGADOR NO LA MATA*/
    		
    		if(aux.returnOrden(auxJ) > aux.returnOrden(auxIA)) {
    			//LA IA GANA
    			//SE SUMAN PUNTOS

    			sistPuntuacion(cantado,j,MAQUINA);
    			acumulador = String.valueOf(MAQUINA.getPuntos());
    			pts2.setText(acumulador);
    			
    			puntosMaximosSuperados();
    			//SIGUIENTE RONDA
    		}
    		
    		/*SI EL JUGADOR LA MATA*/
    		
    		else if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
    			//EL JUGADOR GANA
    			//SE SUMAN PUNTOS

    			sistPuntuacion(cantado,j,MAQUINA);
    			acumulador = String.valueOf(j.getPuntos());
    			pts1.setText(acumulador);
    			
    			puntosMaximosSuperados();
    			//SIGUIENTE RONDA
    		}
    		
    		/*SI EL JUGADOR EMPARDA*/
    		
    		else {
    			
    			c1.setEnabled(false);
    			c2.setEnabled(false);
    			c3.setEnabled(false);
    			
    			tiraIA = null;
    			tiraIA = MAQUINA.yourTurn(cantado, null);
    			tiraIAnull = true;
    			mostrarTirada(tiraIA,tiraIAnull);
    			auxIA = tiraIA[0];
    			
    			if(c1.getIcon() != null) {
    				c1.setEnabled(true);
    			}
    			if(c2.getIcon() != null) {
    				c2.setEnabled(true);
    			}
    			if(c3.getIcon() != null) {
    				c3.setEnabled(true);
    			}
    			
    			accionUsuario = false;
    			Thread espero11 = new Thread() {
	    			
	    			@Override
	    			public void run() {
			    		
	    				System.out.print("\nEspero que el jugador tire en tercera (empardamos dos veces y ya tire todas las cartas) ");
	    				while(accionUsuario == false) {
	    					try {
				    			System.out.print(". ");
				    			Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
			    		}
			    		System.out.println("\nRetomo la ejecucion (me toca a mi)\n");
	    			}
	    			
	    		};
    			
	    		espero11.start();	
	    		
	    		try {
					espero11.join();					
//					Thread.sleep(1000);
				}catch(InterruptedException e) {}
    			
	    		auxJ = queCartaFueTirada();
	    		
	    		/*SI EL JUGADOR LA MATA*/
	    		
	    		if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
	    			//EL JUGADOR GANA
	    			//SE SUMAN PUNTOS

	    			sistPuntuacion(cantado,j,MAQUINA);
	    			acumulador = String.valueOf(j.getPuntos());
	    			pts1.setText(acumulador);
	    			
	    			puntosMaximosSuperados();
	    			//SIGUIENTE RONDA
	    		}
	    		
	    		/*SI EL JUGADOR NO LA MATA O EMPARDA*/
	    		
	    		else {
	    			
	    			//LA IA GANA
	    			//SE SUMAN PUNTOS

	    			sistPuntuacion(cantado,j,MAQUINA);
	    			acumulador = String.valueOf(MAQUINA.getPuntos());
	    			pts2.setText(acumulador);
	    			
	    			puntosMaximosSuperados();
	    			//SIGUIENTE RONDA
	    			
	    		}
	    		
    		}
    															    		
		}
		
		
		
		/*SI EL JUGADOR LA MATA*/
		
		else {
			
			accionUsuario = false;
			Thread espero8 = new Thread() {
    			
    			@Override
    			public void run() {
		    		
    				System.out.print("\nEspero que el jugador tire en segunda (me gano primera) ");
    				while(accionUsuario == false) {
    					try {
			    			System.out.print(". ");
			    			Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		    		}
		    		System.out.println("\nRetomo la ejecucion (me toca a mi)\n");
    			}
    			
    		};
			
    		espero8.start();	
    		
    		try {
				espero8.join();					
//				Thread.sleep(1000);
			}catch(InterruptedException e) {}

    		auxJ = queCartaFueTirada();
    		
    		c1.setEnabled(false);
    		c2.setEnabled(false);
    		c3.setEnabled(false);
    		
    		tiraIA = null;
    		tiraIA = MAQUINA.yourTurn(cantado, auxJ);
    		auxIA = tiraIA[0];
    		tiraIAnull = true;
    		mostrarTirada(tiraIA,tiraIAnull);						//LA TIRA TIRA LAS DOS CARTAS SI MATA
    		
    		/*SI LA IA MATA*/
    		
    		if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
    			
    			if(c1.getIcon() != null) {
    				c1.setEnabled(true);
    			}
    			if(c2.getIcon() != null) {
    				c2.setEnabled(true);
    			}
    			if(c3.getIcon() != null) {
    				c3.setEnabled(true);
    			}
    			
    			accionUsuario = false;
    			Thread espero12 = new Thread() {
	    			
	    			@Override
	    			public void run() {
			    		
	    				System.out.print("\nEspero que el jugador tire en tercera (yo ya tire las 3 cartas) ");
	    				while(accionUsuario == false) {
	    					try {
				    			System.out.print(". ");
				    			Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
			    		}
			    		System.out.println("\nRetomo la ejecucion (me toca a mi)\n");
	    			}
	    			
	    		};
    			
	    		espero12.start();	
	    		
	    		try {
					espero12.join();					
//					Thread.sleep(1000);
				}catch(InterruptedException e) {}
    			
	    		auxJ = queCartaFueTirada();
	    		auxIA = tiraIA[1];
	    		
	    		/*SI EL JUGADOR MATA O EMPARDA*/
	    		
	    		if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
	    			//EL JUGADOR GANA
	    			//SE SUMAN PUNTOS

	    			sistPuntuacion(cantado,j,MAQUINA);
	    			acumulador = String.valueOf(j.getPuntos());
	    			pts1.setText(acumulador);
	    			
	    			puntosMaximosSuperados();
	    			//SIGUIENTE RONDA
	    		}
	    		
	    		/*SI EL JUGADOR NO MATA*/
	    		
	    		else {
	    			//LA IA GANA
	    			//SE SUMAN PUNTOS

	    			sistPuntuacion(cantado,j,MAQUINA);
	    			acumulador = String.valueOf(MAQUINA.getPuntos());
	    			pts2.setText(acumulador);
	    			
	    			puntosMaximosSuperados();
	    			//SIGUIENTE RONDA
	    		}
	    		
    		}
    		
    		/*SI LA IA NO MATA*/
    		
    		else {
    			//EL JUGADOR GANA
    			//SE SUMAN PUNTOS

    			sistPuntuacion(cantado,j,MAQUINA);
    			acumulador = String.valueOf(j.getPuntos());
    			pts1.setText(acumulador);
    			
    			puntosMaximosSuperados();
    			//SIGUIENTE RONDA
    		}
    		
		}
		
	}
	
	
	public void mostrarCartasJugador() {
		
		
		for(int i=0;i<3;i++) {

	        switch(j.cartas[i].getNumero()) {
		    case 1:
		    	if(j.cartas[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(j.cartas[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(j.cartas[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 2:
		    	if(j.cartas[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(j.cartas[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(j.cartas[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 3:
		    	if(j.cartas[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(j.cartas[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(j.cartas[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 4:
		    	if(j.cartas[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(j.cartas[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(j.cartas[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 5:
		    	if(j.cartas[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/5_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(j.cartas[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/5_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(j.cartas[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/5_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/5_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 6:
		    	if(j.cartas[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/6_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(j.cartas[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/6_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(j.cartas[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/6_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/6_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 7:
		    	if(j.cartas[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/7_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(j.cartas[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/7_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(j.cartas[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/7_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/7_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 10:
		    	if(j.cartas[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/10_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(j.cartas[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/10_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(j.cartas[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/10_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/10_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 11:
		    	if(j.cartas[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/11_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(j.cartas[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/11_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(j.cartas[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/11_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/11_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 12:
		    	if(j.cartas[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/12_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(j.cartas[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/12_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(j.cartas[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/12_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/12_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    }
	        
	        switch(i) {
	        case 0:
	        	c1.setIcon(ctemp.getIcon());
	        	break;
	        case 1:
	        	c2.setIcon(ctemp.getIcon());
	        	break;
	        case 2:
	        	c3.setIcon(ctemp.getIcon());
	        	break;
	        }
	        
	        ctemp.setIcon(null);
	        
	    }

	}
	
	
	public int mostrarTirada(Carta tira[],boolean tiraIAnull) {
		
		int j=0;
		
		if(tiraIAnull == false) {
			if(l1.getIcon() != null) {
				j+=1;
				if(l2.getIcon() != null) {
					j+=1;
					if(l3.getIcon() != null) {
						return 1;
					}
				}
			}
		}
		
	    for(int i=j; tira[i] != null;i++) {

	        switch(tira[i].getNumero()) {
		    case 1:
		    	if(tira[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(tira[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(tira[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 2:
		    	if(tira[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(tira[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(tira[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 3:
		    	if(tira[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(tira[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(tira[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 4:
		    	if(tira[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(tira[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(tira[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 5:
		    	if(tira[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/5_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(tira[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/5_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(tira[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/5_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/5_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 6:
		    	if(tira[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/6_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(tira[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/6_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(tira[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/6_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/6_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 7:
		    	if(tira[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/7_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(tira[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/7_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(tira[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/7_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/7_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 10:
		    	if(tira[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/10_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(tira[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/10_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(tira[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/10_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/10_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 11:
		    	if(tira[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/11_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(tira[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/11_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(tira[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/11_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/11_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    case 12:
		    	if(tira[i].getPalo().equals("basto")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/12_BASTO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));					    							    		
		    	}else if(tira[i].getPalo().equals("copa")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/12_COPA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}else if(tira[i].getPalo().equals("espada")) {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/12_ESPADA.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	} else {
					ctemp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/12_ORO.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
		    	}
		    	break;
		    }
	        
	        if(tiraIAnull == true) {
	        	if(l1.getIcon() == null) {
	        		l1.setIcon(ctemp.getIcon());
	        	}
	        	else if(l2.getIcon() == null) {
	        		l2.setIcon(ctemp.getIcon());
	        	}
	        	else if(l3.getIcon() == null){
	        		l3.setIcon(ctemp.getIcon());
	        	}
	        	else {
	        		System.out.println("no hay mas espacio");
	        		return -1;
	        	}
	        }
	        else {
	        	switch(i) {
	 	        case 0:
	 	        	l1.setIcon(ctemp.getIcon());
	 	        	break;
	 	        case 1:
	 	        	l2.setIcon(ctemp.getIcon());
	 	        	break;
	 	        case 2:
	 	        	l3.setIcon(ctemp.getIcon());
	 	        	break;
	 	        }
	        }

	        ctemp.setIcon(null);
	        
	        if(tira.length == 1) {						//SIRVE PARA TIRATMP QUE TIENE UNA LONGITUD DE 1
	        	return 0;
	        }
	        
	        
	        if(i==1) {
	    	   return 0;
	        }
	    }
		
		return 0;
	}
	
	

    public static void sistPuntuacion(String cantado[],Jugador j1,Jugador j2) throws IllegalArgumentException{ 
        //j1 siempre debe ser el que canta primero segun cantoPrimi o el que gana la mano si no se canta nada
        int index = 0;
        if(cantado[index] == null){                                 //si no se cantó nada
            j1.addPuntos(1);
        }
        else if(cantado[index].equals("envido")){                   //canta j1       
            if(cantado[++index].equals("quiero")){                  //canta j2
                if(j1.envido(j2))
                    j1.addPuntos(2);
                else
                    j2.addPuntos(2);
            }
            else if(cantado[index].equals("no quiero"))             //canta j2
                j1.addPuntos(1);
            else if(cantado[index].equals("envido")){               //canta j2
                if(cantado[++index].equals("quiero")){              //canta j1
                    if(j1.envido(j2))
                        j1.addPuntos(4);
                    else
                        j2.addPuntos(4);
                }
                else if(cantado[index].equals("no quiero"))         //canta j1
                    j2.addPuntos(2);
                else if(cantado[index].equals("real envido")){      //canta j1
                    if(cantado[++index].equals("quiero")){          //canta j2
                        if(j1.envido(j2))
                            j1.addPuntos(7);
                        else
                            j2.addPuntos(7);
                    }
                    else if(cantado[index].equals("no quiero"))     //canta j2
                        j1.addPuntos(4);
                    else if(cantado[index].equals("falta envido")){ //canta j2
                        if(cantado[++index].equals("quiero")){      //canta j1
                            if(j1.envido(j2))
                                j1.addPuntosF(j2);   
                            else
                                j2.addPuntosF(j1);   
                        }
                        else if(cantado[index].equals("no quiero")) //canta j1
                            j2.addPuntos(7);
                        else 
                            throw new IllegalArgumentException(cantado[index] + " no matcheado");
                    }
                    else 
                        throw new IllegalArgumentException(cantado[index] + " no matcheado");
                }
                else if(cantado[index].equals("falta envido")){     //canta j1
                    if(cantado[++index].equals("quiero")){          //canta j2
                        if(j1.envido(j2))
                            j1.addPuntosF(j2);   
                        else
                            j2.addPuntosF(j1);   
                    }
                    else if(cantado[index].equals("no quiero"))     //canta j2
                        j1.addPuntos(4);
                    else 
                        throw new IllegalArgumentException(cantado[index] + " no matcheado");
                }
                else 
                    throw new IllegalArgumentException(cantado[index] + " no matcheado");
            }
            else if(cantado[index].equals("real envido")){          //canta j2
                if(cantado[++index].equals("quiero")){              //canta j1
                    if(j1.envido(j2))
                        j1.addPuntos(5);
                    else
                        j2.addPuntos(5);
                }
                else if(cantado[index].equals("no quiero"))         //canta j1
                    j2.addPuntos(2);
                else if(cantado[index].equals("falta envido")){     //canta j1
                    if(cantado[++index].equals("quiero")){          //canta j2
                        if(j1.envido(j2))
                            j1.addPuntosF(j2);   
                        else
                            j2.addPuntosF(j1);   
                    }
                    else if(cantado[index].equals("no quiero"))     //canta j2
                        j2.addPuntos(5);
                    else 
                        throw new IllegalArgumentException(cantado[index] + " no matcheado");
                }
                else 
                    throw new IllegalArgumentException(cantado[index] + " no matcheado");
            }
            else if(cantado[index].equals("falta envido")){         //canta j2
                if(cantado[++index].equals("quiero")){              //canta j1
                    if(j1.envido(j2))
                        j1.addPuntosF(j2);   
                    else
                        j2.addPuntosF(j1);   
                }
                else if(cantado[index].equals("no quiero"))         //canta j1
                    j2.addPuntos(2);
                else 
                    throw new IllegalArgumentException(cantado[index] + " no matcheado");
            }
            else 
                throw new IllegalArgumentException(cantado[index] + " no matcheado");
        }
        else if(cantado[index].equals("real envido")){              //canta j1
            if(cantado[++index].equals("quiero")){                  //canta j2
                if(j1.envido(j2))
                    j1.addPuntos(3);
                else
                    j2.addPuntos(3);
            }
            else if(cantado[index].equals("no quiero"))             //canta j2
                j1.addPuntos(1);
            else if(cantado[index].equals("falta envido")){         //canta j2
                if(cantado[++index].equals("quiero")){              //canta j1
                    if(j1.envido(j2))
                        j1.addPuntosF(j2);   
                    else
                        j2.addPuntosF(j1);   
                }
                else if(cantado[index].equals("no quiero"))         //canta j1
                    j2.addPuntos(3);
                else 
                    throw new IllegalArgumentException(cantado[index] + " no matcheado");
            }
            else 
                throw new IllegalArgumentException(cantado[index] + " no matcheado");
        }
        else if(cantado[index].equals("falta envido")){             //canta j1
            if(cantado[++index].equals("quiero")){                  //canta j2
                    if(j1.envido(j2))
                        j1.addPuntosF(j2);   
                    else
                        j2.addPuntosF(j1);   
            }
            else if(cantado[index].equals("no quiero"))             //canta j2
                    j1.addPuntos(1);
            else 
                throw new IllegalArgumentException(cantado[index] + " no matcheado");
        }
        else if(cantado[index].equals("truco")){                    //se canto el truco por j1
            if(cantado[++index].equals("quiero")){                  //canta j2
                if(j1.truco)        
                    j1.addPuntos(2);
                else
                    j2.addPuntos(2);
            }
            else if(cantado[index].equals("no quiero"))             //canta j2
                j1.addPuntos(1);
            else if(cantado[index].equals("retruco")){              //canta j2

                if(cantado[++index].equals("quiero")){              //canta j1
                    if(j1.truco)
                        j1.addPuntos(3);
                    else
                        j2.addPuntos(3);
                }
                else if(cantado[index].equals("no quiero"))         //canta j1
                    j2.addPuntos(2);
                else if(cantado[index].equals("vale cuatro")){      //canta j1
                    if(cantado[++index].equals("quiero")){          //canta j2
                        if(j1.truco)
                            j1.addPuntos(4);
                        else
                            j2.addPuntos(4);
                    }
                    else if(cantado[index].equals("no quiero"))     //canta j2
                        j1.addPuntos(3);
                    else 
                        throw new IllegalArgumentException(cantado[index] + " no matcheado");
                }
                else 
                    throw new IllegalArgumentException(cantado[index] + " no matcheado");
            }
            else 
                throw new IllegalArgumentException(cantado[index] + " no matcheado");
        }
        else 
            throw new IllegalArgumentException(cantado[index] + " no matcheado");
    }
       
	
	public void reiniciarMenuCantos() {
		
		cp.remove(menuCantosE);
		cp.add(menu,BorderLayout.WEST);
		cp.revalidate();
		cp.repaint();
		
	}
    
	public void puntosMaximosSuperados() {
		
		if(j.getPuntos() >= 30) {
			
			quiero.setEnabled(false);
			noQuiero.setEnabled(false);
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			truco.setEnabled(false);
			cantarTruco.setEnabled(false);
			retruco.setEnabled(false);
			vale_4.setEnabled(false);
			envido.setEnabled(false);
			cantarEnvido.setEnabled(false);
			real_envido.setEnabled(false);
			falta_envido.setEnabled(false);
			mazo.setEnabled(false);
			flecha.setEnabled(false);
			flecha2.setEnabled(false);
			
			texto.setText("<html>"+ "EL JUGADOR GAN�" +"</html>");
			
			rendirse.setText("VOLVER AL MENU");
			
		}
		
		else if(MAQUINA.getPuntos() >= 30){
			
			quiero.setEnabled(false);
			noQuiero.setEnabled(false);
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			truco.setEnabled(false);
			cantarTruco.setEnabled(false);
			retruco.setEnabled(false);
			vale_4.setEnabled(false);
			envido.setEnabled(false);
			cantarEnvido.setEnabled(false);
			real_envido.setEnabled(false);
			falta_envido.setEnabled(false);
			mazo.setEnabled(false);
			flecha.setEnabled(false);
			flecha2.setEnabled(false);
			
			texto.setText("<html>"+ "LA IA GAN�" +"</html>");
			
			rendirse.setText("VOLVER AL MENU");
			
		}
		
		
	}
	
    
}	//FIN INTERFAZ