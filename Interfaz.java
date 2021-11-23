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
	private String cantadoTruco[];
	
    private Carta tiraJ;		
    private Carta tiraIA[];		//guarda las cartas que tira la IA
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
	cantadoTruco = new String[5];
    tiraJ = new Carta();
    tiraIA = new Carta[2];
    accionUsuario = false;
	turno = true;
	jugando = true;
	
	auxJ = new Carta();
    auxIA = new Carta();
    aux = new Carta();
    
    tiraIAnull = false;
    acumulador = "";
	
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
								cantadoTruco[0] = "truco";
								texto.setText("<html>"+ cantadoTruco[0] +"</html>");
								envidoNo = true;
								accionUsuario = true;
							}
							
						});
						
						
						retruco.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								int i;
								for(i=0;i<5;i++) {
									if(cantadoTruco[i]==null) {
										cantadoTruco[i] = "retruco";
										break;
									}
								}	
								texto.setText("<html>"+ cantadoTruco[i] +"</html>");
								accionUsuario = true;
							}
						});
						
						vale_4.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								int i;
								for(i=0;i<5;i++) {
									if(cantadoTruco[i]==null) {
										cantadoTruco[i] = "vale cuatro";
										break;
									}
								}	
								texto.setText("<html>"+ cantadoTruco[i] +"</html>");
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
									if(cantado[i]==null) {
										cantado[i] = "envido";
										break;
									}
								}
								texto.setText("<html>"+ cantado[i] +"</html>");
								accionUsuario = true;
							}
							
						});
						
						real_envido.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								int i;
								for(i=0;i<5;i++) {
									if(cantado[i]==null) {
										cantado[i] = "real envido";
										break;
									}
								}	
								texto.setText("<html>"+ cantado[i] +"</html>");
								accionUsuario = true;
							}
						});
						
						falta_envido.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								int i;
								for(i=0;i<5;i++) {
									if(cantado[i]==null) {
										cantado[i] = "falta envido";
										break;
									}
								}	
								texto.setText("<html>"+ cantado[i] +"</html>");
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
								
								if(cantadoTruco[0] != null) {
									for(int i=0; i<5 ;i++) {
										if(cantadoTruco[i]==null) {
											cantadoTruco[i] = "quiero";
											break;
										}
									}
								}
								else {
								
									for(int i=0; i<5 ;i++) {
										if(cantado[i]==null) {
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
								
								if(cantadoTruco[0] != null) {
									for(int i=0; i<5;i++) {
										if(cantadoTruco[i]==null) {
											cantadoTruco[i] = "no quiero";
											break;
										}
									}
								}
								else {
									for(int i=0; i<5;i++) {
										if(cantado[i]==null) {
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
										        for(int i = 0;cantado[i] != null;++i) {
										            System.out.println(cantado[i]);
										        }
												
										        /* CANTA ENVIDO (SI TIENE) */
										        
										        if(cantado[0] != null) {
										        	if(cantado[0].equals("envido") || cantado[0].equals("real envido") || cantado[0].equals("falta envido")) {
														
										        		if(cantado[0].equals("falta envido")) {
										        			envido.setEnabled(false);
										        		}
										        		else if (cantado[0].equals("real envido")){
										        			envido.setEnabled(true);;
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
											    		
											    		if(cantado[1].equals("quiero") || cantado[1].equals("no quiero")) {
											    			//SE SUMAN PUNTOS
											    			
											    			if(j.getPuntosEnvido() > MAQUINA.getPuntosEnvido() && cantado[0].equals("envido") && cantado[1].equals("quiero")) {
											    				j.addPuntos(2);
											    				acumulador = String.valueOf(j.getPuntos());
											    				pts1.setText(acumulador);
											    				
											    			}
											    			else {
											    				MAQUINA.addPuntos(2);
											    				acumulador = String.valueOf(MAQUINA.getPuntos());
											    				pts2.setText(acumulador);
											    			}
											    			
											    			if(j.getPuntosEnvido() > MAQUINA.getPuntosEnvido() && cantado[0].equals("real envido") && cantado[1].equals("quiero")) {
											    				j.addPuntos(3);
											    				acumulador = String.valueOf(j.getPuntos());
											    				pts1.setText(acumulador);
											    				
											    			}
											    			else {
											    				MAQUINA.addPuntos(3);
											    				acumulador = String.valueOf(MAQUINA.getPuntos());
											    				pts2.setText(acumulador);
											    			}
											    			
											    			if(j.getPuntosEnvido() > MAQUINA.getPuntosEnvido() && cantado[0].equals("falta envido") && cantado[1].equals("quiero")) {
											    				j.addPuntosF(MAQUINA);
											    				//j.addPuntos(2);
											    				acumulador = String.valueOf(j.getPuntos());
											    				pts1.setText(acumulador);
											    				
											    			}
											    			else {
											    				MAQUINA.addPuntosF(j);
											    				//MAQUINA.addPuntos(2);
											    				acumulador = String.valueOf(MAQUINA.getPuntos());
											    				pts2.setText(acumulador);
											    			}
											    			
											    			if(cantado[1].equals("no quiero")) {
											    				MAQUINA.addPuntos(1);
											    				acumulador = String.valueOf(MAQUINA.getPuntos());
											    				pts2.setText(acumulador);
											    				
											    			}
											    			
											    			cp.remove(menuCantosE);
															cp.add(menu,BorderLayout.WEST);
															cp.revalidate();
															cp.repaint();
											    			
											    		}
											    		
											    		/*SI EL JUGADOR REVIRA*/
											    		
											    		else if(cantado[1].equals("envido") || cantado[1].equals("real envido") || cantado[1].equals("falta envido")) {
											    			
											    			MAQUINA.yourTurnAccept(cantado);
											    			
											    			//SI LA IA QUIERE/NO QUIERE
											    			
											    			if(cantado[2].equals("quiero") || cantado[2].equals("no quiero")) {
											    				
											    				/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
											    				
											    				if(cantado[2].equals("quiero")) {
											    					System.out.println("la IA quiso");
											    				}
											    				else {
											    					System.out.println("la IA no quiso");
											    				}
											    				
											    				/***/
											    				
											    				//SE SUMAN PUNTOS
											    				//OJO QUE SON MUCHOS CANTOS, TENER EN CUENTA TODAS LAS POSIBILIDADES
											    				
											    				if(j.getPuntosEnvido() > MAQUINA.getPuntosEnvido() && cantado[0].equals("envido") && cantado[1].equals("envido") && cantado[2].equals("quiero")) {
												    				j.addPuntos(4);
												    				acumulador = String.valueOf(j.getPuntos());
												    				pts1.setText(acumulador);
												    				
												    			}
												    			else {
												    				MAQUINA.addPuntos(4);
												    				acumulador = String.valueOf(MAQUINA.getPuntos());
												    				pts2.setText(acumulador);
												    			}
												    			
											    				if(j.getPuntosEnvido() > MAQUINA.getPuntosEnvido() && cantado[0].equals("envido") && cantado[1].equals("real envido") && cantado[2].equals("quiero")) {
												    				j.addPuntos(5);
												    				acumulador = String.valueOf(j.getPuntos());
												    				pts1.setText(acumulador);
												    				
												    			}
												    			else {
												    				MAQUINA.addPuntos(5);
												    				acumulador = String.valueOf(MAQUINA.getPuntos());
												    				pts2.setText(acumulador);
												    			}
											    				
											    				if(j.getPuntosEnvido() > MAQUINA.getPuntosEnvido() && cantado[0].equals("envido") && cantado[1].equals("falta envido") && cantado[2].equals("quiero")) {
												    				j.addPuntosF(MAQUINA);
											    					//j.addPuntos();
												    				acumulador = String.valueOf(j.getPuntos());
												    				pts1.setText(acumulador);
												    				
												    				//SI SE LLEGA A 30 DEBER�A TERMINAR LA PARTIDA
												    				
												    			}
												    			else {
												    				MAQUINA.addPuntosF(j);
												    				//MAQUINA.addPuntos();
												    				acumulador = String.valueOf(MAQUINA.getPuntos());
												    				pts2.setText(acumulador);
												    				
												    				//SI SE LLEGA A 30 DEBER�A TERMINAR LA PARTIDA
												    				
												    			}
											    				
											    				if(j.getPuntosEnvido() > MAQUINA.getPuntosEnvido() && cantado[0].equals("real envido") && cantado[1].equals("falta envido") && cantado[2].equals("quiero")) {
												    				j.addPuntosF(MAQUINA);
											    					//j.addPuntos();
												    				acumulador = String.valueOf(j.getPuntos());
												    				pts1.setText(acumulador);
												    				
												    				//SI SE LLEGA A 30 DEBER�A TERMINAR LA PARTIDA
												    				
												    			}
												    			else {
												    				MAQUINA.addPuntosF(j);
												    				//MAQUINA.addPuntos();
												    				acumulador = String.valueOf(MAQUINA.getPuntos());
												    				pts2.setText(acumulador);
												    				
												    				//SI SE LLEGA A 30 DEBER�A TERMINAR LA PARTIDA
												    				
												    			}
											    				
												    			if(cantado[1].equals("envido") && cantado[2].equals("no quiero")) {
												    				j.addPuntos(2);
												    				acumulador = String.valueOf(j.getPuntos());
												    				pts1.setText(acumulador);
												    			}
												    			else if(cantado[0].equals("real envido") && cantado[1].equals("falta envido") && cantado[2].equals("no quiero")) {
												    				j.addPuntos(3);
												    				acumulador = String.valueOf(j.getPuntos());
												    				pts1.setText(acumulador);
												    			}
											    				
											    				
											    				cp.remove(menuCantosE);
																cp.add(menu,BorderLayout.WEST);
																cp.revalidate();
																cp.repaint();
											    				
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
																
																if(cantado[3].equals("quiero") || cantado[3].equals("no quiero")) {
																	
																	//SE SUMAN PUNTOS
														
																	if(j.getPuntosEnvido() > MAQUINA.getPuntosEnvido() && cantado[1].equals("envido") && cantado[2].equals("real envido") && cantado[3].equals("quiero")) {
													    				j.addPuntos(7);
													    				acumulador = String.valueOf(j.getPuntos());
													    				pts1.setText(acumulador);
													    				
													    			}
													    			else {
													    				MAQUINA.addPuntos(7);
													    				acumulador = String.valueOf(MAQUINA.getPuntos());
													    				pts2.setText(acumulador);
													    			}
												    				
												    				if(j.getPuntosEnvido() > MAQUINA.getPuntosEnvido() && cantado[1].equals("envido") && cantado[2].equals("falta envido") && cantado[3].equals("quiero")) {
													    				j.addPuntosF(MAQUINA);
												    					//j.addPuntos();
													    				acumulador = String.valueOf(j.getPuntos());
													    				pts1.setText(acumulador);
													    				
													    				//SI SE LLEGA A 30 DEBER�A TERMINAR LA PARTIDA
													    				
													    			}
													    			else {
													    				MAQUINA.addPuntosF(j);
													    				//MAQUINA.addPuntos();
													    				acumulador = String.valueOf(MAQUINA.getPuntos());
													    				pts2.setText(acumulador);
													    				
													    				//SI SE LLEGA A 30 DEBER�A TERMINAR LA PARTIDA
													    				
													    			}
												    				
													    			if(cantado[3].equals("no quiero")) {
													    				MAQUINA.addPuntos(4);
													    				acumulador = String.valueOf(MAQUINA.getPuntos());
													    				pts2.setText(acumulador);
													    			}
																	
																	cp.remove(menuCantosE);
																	cp.add(menu,BorderLayout.WEST);
																	cp.revalidate();
																	cp.repaint();
																	
																}
																
																//SI EL JUGADOR REVIRA
																
																else if(cantado[3].equals("falta envido")) {
																	
																	MAQUINA.yourTurnAccept(cantado);
																	
																	//SI LA IA QUISO/NO QUISO
																	
																	/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
												    				
																	if(cantado[2].equals("quiero")) {
												    					System.out.println("la IA quiso");
												    				}
												    				else {
												    					System.out.println("la IA no quiso");
												    				}
												    		
												    				/***/
																	
																	if(cantado[4].equals("quiero") || cantado[4].equals("no quiero")) {
																		
																		//SE SUMAN PUNTOS
																		if(j.getPuntosEnvido() > MAQUINA.getPuntosEnvido() && cantado[2].equals("real envido") && cantado[3].equals("falta envido") && cantado[4].equals("quiero")) {
																			j.addPuntosF(MAQUINA);
																			//j.addPuntos(7);
														    				acumulador = String.valueOf(j.getPuntos());
														    				pts1.setText(acumulador);
														    				
														    			}
														    			else {
														    				MAQUINA.addPuntosF(j);
																			//j.addPuntos(7);
														    				acumulador = String.valueOf(MAQUINA.getPuntos());
														    				pts2.setText(acumulador);
														    			}
																		
																		cp.remove(menuCantosE);
																		cp.add(menu,BorderLayout.WEST);
																		cp.revalidate();
																		cp.repaint();
																		
																	}
																	
																}
																
											    			}
											    			
											    			//nota: posible lugar donde vaya el hilo espero2
											    			
											    		}
											    		

													}
										        	
										        	/*CUANDO YA SE CANTO ENVIDO EL ARREGLO DE CANTADOS SE PONE A NULL PARA EL TRUCO*/
										        	
										        	texto.setText("<html>"+ "" +"</html>");
										        	
										        	for(int i=0;i<5;i++) {
										        		cantado[i] = null;
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
										        
										        
										        /*SI NO TIENE PARA EL ENVIDO TIRA LA CARTA DIRECTAMENTE*/
										        
										        
//										        else {
											        
									        	if(cantoEnvido == true) {
									        		envido.setEnabled(false);
									        	}
									        	else {
									        		envido.setEnabled(true);
									        	}
									        
									        	mostrarTirada(tiraIA,tiraIAnull);
									        	auxIA = tiraIA[0];					//esto me sirve para comparar las cartas cuando el jugador tire sin cantar (en la primera)
										        truco.setEnabled(true);
										        c1.setEnabled(true);
										        c2.setEnabled(true);
										        c3.setEnabled(true);
										        quiero.setEnabled(false);
										        noQuiero.setEnabled(false);
										        accionUsuario = false;
											        
											        //MAQUINA.setBandera(false);
											        
											      //tengo que realizar otro hilo de espera por si el jugador canta truco/envido / o tira carta
											        
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
										    		if(envidoNo != true) {
										    		
										    		if(cantado[0].equals("envido") || cantado[0].equals("real envido") || cantado[0].equals("falta envido")) {
										    			
										    			texto.setText("<html>"+ cantado[0] +"</html>");
														quiero.setEnabled(false);
														noQuiero.setEnabled(false);
														
														c1.setEnabled(false);
											    		c2.setEnabled(false);
											    		c3.setEnabled(false);
											    		truco.setEnabled(false);
											    		
											    		
											    		MAQUINA.yourTurnAccept(cantado);
											    		
											    		accionUsuario = false;
											    		
											    		/*SI LA IA QUIERE/NO QUIERE*/
											    		
											    		if(cantado[1].equals("quiero") || cantado[1].equals("no quiero")) {
											    			
											    			/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
										    				
															if(cantado[1].equals("quiero")) {
										    					System.out.println("la IA quiso");
										    				}
										    				else {
										    					System.out.println("la IA no quiso");
										    				}
										    		
										    				/***/
											    			
											    			//SE SUMAN PUNTOS
											    			
															if(cantado[1].equals("no quiero")) {
															
																j.addPuntosF(MAQUINA);
																//j.addPuntos(7);
											    				acumulador = String.valueOf(j.getPuntos());
											    				pts1.setText(acumulador);
															}
															
											    			cp.remove(menuCantosE);
															cp.add(menu,BorderLayout.WEST);
															cp.revalidate();
															cp.repaint();
															
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
											    				
													    		/* SI EL JUGADOR QUIERE/NO QUIERE */
													    		
													    		if(cantado[2].equals("quiero") || cantado[2].equals("no quiero")) {
													    			//SE SUMAN PUNTOS
													    			
													    			cp.remove(menuCantosE);
																	cp.add(menu,BorderLayout.WEST);
																	cp.revalidate();
																	cp.repaint();
																	
																	envido.setEnabled(false);
																	
													    		}
													    		
													    		/* SI EL JUGADOR REVIRA */
													    		
													    		else if(cantado[2].equals("real envido") || cantado[2].equals("falta envido")) {
													    			
													    			MAQUINA.yourTurnAccept(cantado);
													    			
													    			accionUsuario = false;
													    			
													    			//SI LA IA QUIERE
														    		
														    		if(cantado[3].equals("quiero") || cantado[3].equals("no quiero")) {
														    			
																		/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
													    				
																		if(cantado[3].equals("quiero")) {
													    					System.out.println("la IA quiso");
													    				}
													    				else {
													    					System.out.println("la IA no quiso");
													    				}
													    		
													    				/***/
														    			
														    			//SE SUMAN PUNTOS
														    			
														    			cp.remove(menuCantosE);
																		cp.add(menu,BorderLayout.WEST);
																		cp.revalidate();
																		cp.repaint();
																		
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
														    			
															    		/* SI EL JUGADOR QUIERE/NO QUIERE */
															    		
															    		if(cantado[4].equals("quiero") || cantado[4].equals("no quiero")) {
															    			
															    			//SE SUMAN PUNTOS
															    			
															    			cp.remove(menuCantosE);
																			cp.add(menu,BorderLayout.WEST);
																			cp.revalidate();
																			cp.repaint();
																			
																			envido.setEnabled(false);
																			
															    		}
															    		
														    		}
													    			
													    		}
													    		
											    			}
											    			else if(cantado[1].equals("real envido")) {
											    				
											    				quiero.setEnabled(true);
											    				noQuiero.setEnabled(true);
											    				
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
													    		
													    		if(cantado[2].equals("quiero") || cantado[2].equals("no quiero")) {
													    			//SE SUMAN PUNTOS
													    			
													    			cp.remove(menuCantosE);
																	cp.add(menu,BorderLayout.WEST);
																	cp.revalidate();
																	cp.repaint();
																	
																	envido.setEnabled(false);
													    		}
													    		
													    		/* SI EL JUGADOR REVIRA */
													    			
													    		else if(cantado[2].equals("falta envido")) {
													    			
													    			MAQUINA.yourTurnAccept(cantado);
													    			
													    			/* SI LA MAQUINA QUIERE/NO QUIERE */
													    			
													    			if(cantado[3].equals("quiero") || cantado[3].equals("no quiero")) {
														    			
																		/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
													    				
																		if(cantado[3].equals("quiero")) {
													    					System.out.println("la IA quiso");
													    				}
													    				else {
													    					System.out.println("la IA no quiso");
													    				}
													    		
													    				/***/
													    				
													    				//SE SUMAN PUNTOS
														    			
														    			cp.remove(menuCantosE);
																		cp.add(menu,BorderLayout.WEST);
																		cp.revalidate();
																		cp.repaint();
																		
																		envido.setEnabled(false);
																		
														    		}
													    			
													    		}
													    		
											    			}
											    			else {
											    				
											    				quiero.setEnabled(true);
											    				noQuiero.setEnabled(true);
											    				
											    				/* SI EL JUGADOR QUIERE / NO QUIERE */
											    				
											    				if(cantado[2].equals("quiero") || cantado[2].equals("no quiero")) {
													    			//SE SUMAN PUNTOS
													    			
													    			cp.remove(menuCantosE);
																	cp.add(menu,BorderLayout.WEST);
																	cp.revalidate();
																	cp.repaint();
																	
																	envido.setEnabled(false);
													    		}
											    				
											    			}
											    			
											    		}
											    		
										    		}	//FIN ENVIDO JUGADOR
										    		}
										    		/*EL ARREGLO DE CANTADOS VUELVE A NULL POR SI SE CANTA TRUCO*/
										    		
										    		for(int i=0;i<5;i++) {
										        		cantado[i] = null;
										        	}
										    		
										    		texto.setText("<html>"+ "" +"</html>");
										    		
										    		truco.setEnabled(true);
										    		c1.setEnabled(true);
										    		c2.setEnabled(true);
										    		c3.setEnabled(true);
										    		
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
										    		
										    		
										    		
										    		/*EL JUGADOR PUEDE CANTAR TRUCO*/
											        
										    		System.out.println(cantadoTruco[0] + " chequeando");
										    		
										    		if(cantadoTruco[0].equals("truco")) {
										    			
										    			c1.setEnabled(false);
										    			c2.setEnabled(false);
										    			c3.setEnabled(false);
										    			quiero.setEnabled(false);
										    			noQuiero.setEnabled(false);
										    			
										    			MAQUINA.yourTurnAccept(cantadoTruco);
										    			
										    			/*SI LA IA QUIERE*/
										    			
										    			if(cantadoTruco[1].equals("quiero")) {
										    				

															/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
										    					System.out.println("la IA quiso");
										    				/***/
										    				
										    				c1.setEnabled(true);
											    			c2.setEnabled(true);
											    			c3.setEnabled(true);
										    				
										    				//TENER EN CUENTA VARIABLES COMO CANTADO[] / LAS AUX Y OTRAS (EN RELACION A SU VALOR)
										    				trucoQuerido();
										    				texto.setText("<html>"+ "" +"</html>");
										    			}
										    			
										    			/*SI LA IA REVIRA*/
										    			
										    			else if(cantadoTruco[1].equals("retruco")){

															/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
										    					System.out.println("la IA revira");
										    				/***/
										    				
										    				quiero.setEnabled(true);
											    			noQuiero.setEnabled(true);
										    				
										    				Thread espero13 = new Thread() {
												    			
												    			@Override
												    			public void run() {
														    		
												    				System.out.print("\nEspero que el jugador quiera/no quiera - revire ");
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
//																Thread.sleep(1000);
															}catch(InterruptedException e) {}
										    				
												    		/*SI EL JUGADOR QUIERE*/
												    		
												    		if(cantadoTruco[2].equals("quiero")) {
												    			
												    			c1.setEnabled(true);
												    			c2.setEnabled(true);
												    			c3.setEnabled(true);
												    			
												    			//ac� voy a tener que reciclar codigo y debo estar atento a como crear la funcion
											    				
												    			trucoQuerido();
												    			texto.setText("<html>"+ "" +"</html>");
												    		}
												    		
												    		/*SI EL JUGADOR REVIRA*/
												    		
												    		else if(cantadoTruco[2].equals("vale cuatro")) {
												    			
												    			quiero.setEnabled(false);
												    			noQuiero.setEnabled(false);
												    			
												    			MAQUINA.yourTurnAccept(cantadoTruco);
												    			
												    			/*SI LA IA QUIERE*/
												    			
												    			if(cantadoTruco[3].equals("quiero")) {
												    				
																	/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
												    					System.out.println("la IA quiso");
												    				/***/
												    				
												    				c1.setEnabled(true);
													    			c2.setEnabled(true);
													    			c3.setEnabled(true);
													    			
													    			//ac� voy a tener que reciclar codigo y debo estar atento a como crear la funcion
												    				
												    				trucoQuerido();
												    				texto.setText("<html>"+ "" +"</html>");
												    			}
												    			
												    			/*SI LA IA NO QUIERE*/
												    			
												    			else {
																	
												    				/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
												    					System.out.println("la IA no quiso");
												    				/***/
												    				
												    				texto.setText("<html>"+ "" +"</html>");
												    				//EL JUGADOR GANA
													    			//SE SUMAN PUNTOS
												    				
												    				j.addPuntos(3);
												    				
													    			//SIGUIENTE RONDA
												    			}
												    			
												    		}
												    		
												    		/*SI EL JUGADOR NO QUIERE*/
												    		
												    		else {
												    			texto.setText("<html>"+ "" +"</html>");
												    			//LA IA GANA
												    			//SE SUMAN PUNTOS
												    			
												    			MAQUINA.addPuntos(2);
												    			
												    			//SIGUIENTE RONDA
												    		}
										    				
										    			}
										    			
										    			/*SI LA IA NO QUIERE*/
										    			
										    			else if(cantadoTruco[1].equals("no quiero")) {
															
										    				/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
										    					System.out.println("la IA no quiso");
										    				/***/
										    				
										    				texto.setText("<html>"+ "" +"</html>");
										    				//EL JUGADOR GANA
										    				//SE SUMAN PUNTOS
										    				
										    				j.addPuntos(1);
										    				
										    				//SIGUIENTE RONDA
										    				
										    			}
										    			
										    		}
											        
										    		
										    		
											        /*EL JUGADOR PUEDE TIRAR CARTA DIRECTAMENTE*/
											        
										    		//continuar con thread13
										    		
										    		else {
										    			
										    			auxJ = queCartaFueTirada();
											    		
										    			
										    			/* SI EL JUGADOR NO LA MATA */
										    			
										    			if(aux.returnOrden(auxJ) > aux.returnOrden(auxIA)) {
										    				
										    				c1.setEnabled(false);
										    				c2.setEnabled(false);
										    				c3.setEnabled(false);
										    				truco.setEnabled(false);
										    				quiero.setEnabled(false);
										    				noQuiero.setEnabled(false);
										    				
										    				/*LA IA PUEDE TIRAR CARTA DIRECTAMENTE*/
										    				
										    				tiraIA = null;
										    				tiraIA = MAQUINA.yourTurn(cantado, null);
										    				tiraIAnull = true;
										    				mostrarTirada(tiraIA,tiraIAnull);
										    				
										    	    		c1.setEnabled(true);
										    				c2.setEnabled(true);
										    				c3.setEnabled(true);
										    				truco.setEnabled(true);
										    				
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
//										    					Thread.sleep(1000);
										    				}catch(InterruptedException e) {}
										    	    		
										    				if() {
										    					
										    					/*EL JUGADOR PUEDE CANTAR TRUCO*/
										    					
										    					if() {
										    						
										    						/*SI LA IA QUIERE*/
										    						
										    						if() {
										    							
										    							/*SI EL JUGADOR NO LA MATA/EMPARDA*/
										    							
										    							if() {
										    								//LA IA GANA
										    								//SE SUMAN PUNTOS
										    								//SIGUIENTE RONDA
										    							}
										    							
										    							/*SI EL JUGADOR LA MATA*/
										    							//TIRA DOS VECES
										    							else {
										    								
										    								/*SI LA IA MATA*/
										    								
										    								if() {
										    									//LA IA GANA
										    									//SE SUMAN PUNTOS
										    									//SIGUIENTE RONDA
										    								}
										    								
										    								/*SI LA IA NO MATA*/
										    								
										    								else {
										    									//EL JUGADOR GANA
										    									//SE SUMAN PUNTOS
										    									//SIGUIENTE RONDA
										    								}
										    								
										    							}
										    							
										    						}
										    						
										    						/*SI NO QUIERE*/
										    						
										    						else if() {
										    							//EL JUGADOR GANA
										    							//SE SUMAN PUNTOS
										    							//SIGUIENTE RONDA
										    						}
										    						
										    						/*SI LA IA REVIRA*/
										    						
										    						else {
										    							
										    							/*SI EL JUGADOR QUIERE*/
										    							
										    							if() {		//TODO ESTE IF (SOLO DE ADENTRO) ES IGUAL (BLOQUE 1)
										    								
										    								/*SI EL JUGADOR NO MATA/EMPARDA*/
									    									
									    									if() {
									    										//LA IA GANA
									    										//SE SUMAN PUNTOS
									    										//SIGUIENTE RONDA
									    									}
									    									
									    									/*SI EL JUGADOR MATA*/
									    									//TIRA DOS VECES
									    									else {
									    										
									    										/*SI LA IA MATA*/
									    										
									    										if() {
									    											//LA IA GANA
									    											//SE SUMAN PUNTOS
									    											//SIGUIENTE RONDA
									    										}
									    										
									    										/*SI LA IA NO MATA*/
									    										
									    										else {
									    											//EL JUGADOR GANA
									    											//SE SUMAN PUNTOS
									    											//SIGUIENTE RONDA
									    										}
									    										
									    									}
										    								
										    							}
										    							
										    							/*SI EL JUGADOR NO QUIERE*/
										    							
										    							else if() {
										    								//LA IA GANA
										    								//SE SUMAN PUNTOS
										    								//SIGUIENTE RONDA
										    							}
										    							
										    							/*SI EL JUGADOR REVIRA*/
										    							
										    							else {
										    								
										    								/*SI LA IA QUIERE*/
										    								
										    								if() {		//TODO ESTE IF (SOLO DE ADENTRO) ES IGUAL (BLOQUE 1)
										    									
										    									/*SI EL JUGADOR NO MATA/EMPARDA*/
										    									
										    									if() {
										    										//LA IA GANA
										    										//SE SUMAN PUNTOS
										    										//SIGUIENTE RONDA
										    									}
										    									
										    									/*SI EL JUGADOR MATA*/
										    									//TIRA DOS VECES
										    									else {
										    										
										    										/*SI LA IA MATA*/
										    										
										    										if() {
										    											//LA IA GANA
										    											//SE SUMAN PUNTOS
										    											//SIGUIENTE RONDA
										    										}
										    										
										    										/*SI LA IA NO MATA*/
										    										
										    										else {
										    											//EL JUGADOR GANA
										    											//SE SUMAN PUNTOS
										    											//SIGUIENTE RONDA
										    										}
										    										
										    									}
										    									
										    								}
										    								
										    								/*SI LA IA NO QUIERE*/
										    								
										    								else {
										    									//EL JUGADOR GANA
										    									//SE SUMAN PUNTOS
										    									//SIGUIENTE RONDA
										    								}
										    								
										    							}
										    							
										    						}
										    						
										    						
										    					}
										    					
										    					/*EL JUGADOR PUEDE TIRAR CARTA DIRECTAMENTE*/
										    					
										    					else if() {
										    						
										    						/*SI EL JUGADOR NO LA MATA/EMPARDA*/
										    						
										    						if() {
										    							//LA IA GANA
										    							//SE SUMAN PUNTOS
										    							//SIGUIENTE RONDA
										    						}
										    						
										    						/*SI EL JUGADOR LA MATA*/
										    						
										    						else if() {
										    							
										    							/*EL JUGADOR PUEDE CANTAR TRUCO*/
										    							
										    							if() {
										    								
										    								/*SI LA IA QUIERE*/
										    								
										    								if() {	//TODO ESTE IF (SOLO DE ADENTRO) ES IGUAL (BLOQUE 2)
										    									
										    									//EL JUGADOR TIRA LA ULTIMA CARTA
										    									/*SI LA IA MATA*/
										    									
										    									if() {
										    										//LA IA GANA
										    										//SE SUMAN PUNTOS
										    										//SIGUIENTE RONDA
										    									}
										    									
										    									/*SI LA IA NO MATA*/
										    									
										    									else {
										    										//EL JUGADOR GANA
										    										//SE SUMAN PUNTOS
										    										//SIGUIENTE RONDA
										    									}
										    									
										    								}
										    								
										    								/*SI LA IA NO QUIERE*/
										    								
										    								else if() {
										    									//EL JUGADOR GANA
										    									//SE SUMAN PUNTOS
										    									//SIGUIENTE RONDA
										    								}
										    								
										    								/*SI LA IA REVIRA*/
										    								
										    								else {
										    									
										    									/*SI EL JUGADOR QUIERE*/
										    									
										    									if() {	//TODO ESTE IF (SOLO DE ADENTRO) ES IGUAL (BLOQUE 2)
										    										
										    										//EL JUGADOR TIRA LA ULTIMA CARTA
											    									/*SI LA IA MATA*/
											    									
											    									if() {
											    										//LA IA GANA
											    										//SE SUMAN PUNTOS
											    										//SIGUIENTE RONDA
											    									}
											    									
											    									/*SI LA IA NO MATA*/
											    									
											    									else {
											    										//EL JUGADOR GANA
											    										//SE SUMAN PUNTOS
											    										//SIGUIENTE RONDA
											    									}
										    										
										    									}
										    									
										    									/*SI EL JUGADOR NO QUIERE*/
										    									
										    									else if() {
										    										//LA IA GANA
										    										//SE SUMAN PUNTOS
										    										//SIGUIENTE RONDA
										    									}
										    									
										    									/*SI EL JUGADOR REVIRA*/
										    									
										    									else {
										    										
										    										/*SI LA IA QUIERE*/
										    						
										    										if() {		//TODO ESTE IF (SOLO DE ADENTRO) ES IGUAL (BLOQUE 2)
										    											
										    											//EL JUGADOR TIRA LA ULTIMA CARTA
												    									/*SI LA IA MATA*/
												    									
												    									if() {
												    										//LA IA GANA
												    										//SE SUMAN PUNTOS
												    										//SIGUIENTE RONDA
												    									}
												    									
												    									/*SI LA IA NO MATA*/
												    									
												    									else {
												    										//EL JUGADOR GANA
												    										//SE SUMAN PUNTOS
												    										//SIGUIENTE RONDA
												    									}
										    											
										    										}
										    										
										    										/*SI LA IA NO QUIERE*/
										    										
										    										else {
										    											//EL JUGADOR GANA
										    											//SE SUMAN PUNTOS
										    											//SIGUIENTE RONDA
										    										}
										    										
										    									}
										    									
										    								}
										    								
										    							}
										    							
										    							/*EL JUGADOR PUEDE TIRAR CARTA DIRECTAMENTE*/
										    							
										    							else if() {
										    								
										    								/*LA IA PUEDE CANTAR TRUCO*/
										    								
										    								if() {
										    									
										    									/*SI EL JUGADOR QUIERE*/
										    									
										    									if() {	//TODO ESTE IF (SOLO DE ADENTRO) ES IGUAL (BLOQUE 3)
										    										
										    										/*SI LA IA MATA*/
										    										
										    										if() {
										    											//LA IA GANA
										    											//SE SUMAN PUNTOS
										    											//SIGUIENTE RONDA
										    										}
										    										
										    										/*SI LA IA NO MATA*/
										    										
										    										else {
										    											//LA IA GANA
										    											//SE SUMAN PUNTOS
										    											//SIGUIENTE RONDA
										    										}
										    										
										    									}
										    									
										    									/*SI EL JUGADOR NO QUIERE*/
										    									
										    									else if() {
										    									
										    										//LA IA GANA
										    										//SE SUMAN PUNTOS
										    										//SIGUIENTE RONDA
										    									}
										    									
										    									/*SI EL JUGADOR REVIRA*/
										    									
										    									else {
										    										
										    										/*SI LA IA QUIERE*/
										    										
										    										if() {	//TODO ESTE IF (SOLO DE ADENTRO) ES IGUAL (BLOQUE 3)
										    											
										    											/*SI LA IA MATA*/
											    										
											    										if() {
											    											//LA IA GANA
											    											//SE SUMAN PUNTOS
											    											//SIGUIENTE RONDA
											    										}
											    										
											    										/*SI LA IA NO MATA*/
											    										
											    										else {
											    											//LA IA GANA
											    											//SE SUMAN PUNTOS
											    											//SIGUIENTE RONDA
											    										}
										    											
										    										}
										    										
										    										/*SI LA IA NO QUIERE*/
										    										
										    										else if() {
										    											//EL JUGADOR GANA
										    											//SE SUMAN PUNTOS
										    											//SIGUIENTE RONDA
										    										}
										    										
										    										/*SI LA IA REVIRA*/
										    										
										    										else() {
										    											
										    											/*SI EL JUGADOR QUIERE*/
										    											
										    											if() {	//TODO ESTE IF (SOLO DE ADENTRO) ES IGUAL (BLOQUE 3)
										    												
										    												/*SI LA IA MATA*/
												    										
												    										if() {
												    											//LA IA GANA
												    											//SE SUMAN PUNTOS
												    											//SIGUIENTE RONDA
												    										}
												    										
												    										/*SI LA IA NO MATA*/
												    										
												    										else {
												    											//LA IA GANA
												    											//SE SUMAN PUNTOS
												    											//SIGUIENTE RONDA
												    										}
										    												
										    											}
										    											
										    											/*SI EL JUGADOR NO QUIERE*/
										    											
										    											else {
										    												//LA IA GANA
										    												//SE SUMAN PUNTOS
										    												//SIGUIENTE RONDA
										    											}
										    											
										    										}
										    										
										    									}
										    								}
										    								
										    								/*LA IA TIRA CARTA DIRECTAMENTE*/
										    								
										    								else if() {
										    									
										    									/*LA IA MATA*/
										    									
										    									if() {
										    										//GANA IA
										    										//SE SUMAN PUNTOS
										    										//SIGUIENTE RONDA
										    									}
										    									
										    									/*LA IA NO MATA*/
										    									
										    									else {
										    										//GANA JUGADOR
										    										//SE SUMAN PUNTOS
										    										//SIGUIENTE RONDA
										    									}
										    									
										    								}
										    								
										    							}
										    							
										    						}
										    						
										    					}
										    					
										    				}
										    				
										    				/*LA IA PUEDE CANTAR TRUCO*/
										    				
										    				else if() {
										    					
										    					/*SI EL JUGADOR QUIERE*/
										    					
										    					if() {
										    						
										    						//LA IA TIRA
										    						/*SI EL JUGADOR MATA*/
										    						
										    						if() {
										    							
										    							//EL JUGADOR TIRA LA ULTIMA
										    							/*SI LA IA MATA*/
										    							
										    							if() {
										    								//LA IA GANA
											    							//SE SUMAN PUNTOS
											    							//SIGUIENTE RONDA
										    							}
										    							
										    							/*SI LA IA NO MATA*/
										    							
										    							else {
										    								//EL JUGADOR GANA
											    							//SE SUMAN PUNTOS
											    							//SIGUIENTE RONDA
										    							}
										    							
										    						}
										    						
										    						/*SI EL JUGADOR NO MATA*/
										    						
										    						else {
										    							//LA IA GANA
										    							//SE SUMAN PUNTOS
										    							//SIGUIENTE RONDA
										    						}
										    						
										    					}
										    					
										    					/*SI EL JUGADOR NO QUIERE*/
										    					
										    					else if() {
										    						//LA IA GANA
										    						//SE SUMAN PUNTOS
										    						//SIGUIENTE RONDA
										    					}
										    					
										    					/*SI EL JUGADOR REVIRA*/
										    					
										    					else {
										    						
										    						/* SI LA IA QUIERE*/
										    						
										    						if() {	//TODO ESTE IF (SOLO LO DE ADENTRO) ES IGUAL (BLOQUE 4)
										    							
										    							//LA IA TIRA
										    							/*SI EL JUGADOR MATA*/
										    							
										    							if() {
										    								
										    								//EL JUGADOR TIRA
										    								/*SI LA IA MATA*/
										    								
										    								if() {
										    									//LA IA GANA
										    									//SE SUMAN PUNTOS
										    									//SIGUIENTE RONDA
										    								}
										    								
										    								/*SI LA IA NO MATA*/
										    								
										    								else {
										    									//LA IA GANA
										    									//SE SUMAN PUNTOS
										    									//SIGUIENTE RONDA
										    								}
										    								
										    							}
										    							
										    							/*SI EL JUGADOR NO MATA*/
										    							
										    							else {
										    								//LA IA GANA
										    								//SE SUMAN PUNTOS
										    								//SIGUIENTE RONDA
										    							}
										    							
										    						}
										    						
										    						/*SI LA IA NO QUIERE*/
										    						
										    						else if() {
										    							//EL JUGADOR GANA
										    							//SE SUMAN PUNTOS
										    							//SIGUIENTE RONDA
										    						}
										    						
										    						/*SI LA IA REVIRA*/
										    						
										    						else {
										    							
										    							/*SI EL JUGADOR QUIERE*/
										    							
										    							if() {	//TODO ESTE IF (SOLO LO DE ADENTRO) ES IGUAL (BLOQUE 4)
										    								
										    								//LA IA TIRA
											    							/*SI EL JUGADOR MATA*/
											    							
											    							if() {
											    								
											    								//EL JUGADOR TIRA
											    								/*SI LA IA MATA*/
											    								
											    								if() {
											    									//LA IA GANA
											    									//SE SUMAN PUNTOS
											    									//SIGUIENTE RONDA
											    								}
											    								
											    								/*SI LA IA NO MATA*/
											    								
											    								else {
											    									//LA IA GANA
											    									//SE SUMAN PUNTOS
											    									//SIGUIENTE RONDA
											    								}
											    								
											    							}
											    							
											    							/*SI EL JUGADOR NO MATA*/
											    							
											    							else {
											    								//LA IA GANA
											    								//SE SUMAN PUNTOS
											    								//SIGUIENTE RONDA
											    							}
										    								
										    							}
										    							
										    							/*SI EL JUGADOR NO QUIERE*/
										    							
										    							else {
										    								//LA IA GANA
											    							//SE SUMAN PUNTOS
											    							//SIGUIENTE RONDA
										    							}
										    							
										    						}
										    						
										    					}
										    					
										    				}
										    				
										    			}
										    			
										    			
										    			
										    			/*SI EL JUGADOR EMPARDA*/
										    			
										    			else if(){
										    				
										    				
										    				
										    			}
										    			
										    			
										    			
											    		/*SI EL JUGADOR LA MATA */
											    		
											    		else if() {
											    			
										    				//la IA vuelve a esperar porque el jugador tiene que tirar otra carta
										    			
											    		}
										    			
										    		}
										    		

											        
										    		
											       
										    		//nota: variable para saber si se canto envido if(seCantoEnvido == false) then ... else ...	(no es necesaria si el boton esta desactivado)
											        //nota: posiblemente (salvo q lo haya hecho tiki) tenga que llevar un conteo de si el jugador esta ganando tiradas (primera, segunda, etc.) o no
//										        }
									        
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
	
	
	//FUNCION PARA SABER QUE CARTA TIR� EL JUGADOR CUANDO APRET� UN BOTON
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
	
	
	public void trucoQuerido() {
		accionUsuario = false;
		truco.setEnabled(false);
		
		//ambos comienzan a tirar cartas hasta que ganen 2 tiradas
		//la IA espera que el jugador tire al menos una carta
		
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
			
			auxJ = null;
			tiraIA = null;
			MAQUINA.activatePuedoCantarEnvido(false);
			tiraIA = MAQUINA.yourTurn(cantado, auxJ);			//OJO, LA MAQUINA NO DEBERIA CANTAR MAS PORQUE YA SE CANTO ANTES (MANEJARME CON UN ARREGLO DE CANTADOS PARALELO)
			tiraIAnull = true;
			mostrarTirada(tiraIA,tiraIAnull);
			
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
    			if(cantadoTruco[0].equals("truco") && cantadoTruco[1].equals("quiero")) {
    				MAQUINA.addPuntos(2);
    			}
    			else if(cantadoTruco[1].equals("retruco") && cantadoTruco[2].equals("quiero")) {
    				MAQUINA.addPuntos(3);
    			}
    			else {
    				MAQUINA.addPuntos(4);
    			}
    			//SE PASA A LA SIGUIENTE RONDA
    			
    		}
    		
    		/*SI EL JUGADOR LA MATA*/
    		
    		else {
    			
    			auxJ = queCartaFueTirada();		//se puede comentar/eliminar
    			
    			accionUsuario = false;
    			Thread espero9 = new Thread() {
	    			
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
    			
	    		espero9.start();	
	    		
	    		try {
					espero9.join();					
//					Thread.sleep(1000);
				}catch(InterruptedException e) {}
    			
	    		auxJ = queCartaFueTirada();
	    		
	    		//chequear de hacer null a tiraIA
	    		tiraIA = null;
    			tiraIA = MAQUINA.yourTurn(cantado, auxJ);
    			auxIA = tiraIA[0];
    			
    			/*SI LA IA MATA*/
    			
    			if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
    				
    				tiraIAnull = true;
    				mostrarTirada(tiraIA,tiraIAnull);
    				//SE SUMAN PUNTOS
    				if(cantadoTruco[0].equals("truco") && cantadoTruco[1].equals("quiero")) {
        				MAQUINA.addPuntos(2);
        			}
        			else if(cantadoTruco[1].equals("retruco") && cantadoTruco[2].equals("quiero")) {
        				MAQUINA.addPuntos(3);
        			}
        			else {
        				MAQUINA.addPuntos(4);
        			}
    				
    				//EL JUGADOR PIERDE
    				//SE PASA LA SIGUIENTE RONDA
    				
    			}
    			
    			/*SI LA IA NO MATA*/
    			
    			else {
    				//SE SUMAN PUNTOS
    				if(cantadoTruco[0].equals("truco") && cantadoTruco[1].equals("quiero")) {
        				j.addPuntos(2);
        			}
        			else if(cantadoTruco[1].equals("retruco") && cantadoTruco[2].equals("quiero")) {
        				j.addPuntos(3);
        			}
        			else {
        				j.addPuntos(4);
        			}
    				//LA IA PIERDE
    				//SE PASA A LA SIGUIENTE RONDA
    				
    			}
    			
    		}
    		
		}
		
		
		
		/*SI EL JUGADOR LA EMPARDA*/
		
		else if(aux.returnOrden(auxJ) == aux.returnOrden(auxIA)) {
			
			//la IA tira
			MAQUINA.activatePuedoCantarEnvido(false);
			tiraIA = null;
			//chequear de hacer null tiraIA 
			tiraIA = MAQUINA.yourTurn(cantado, null);
			tiraIAnull = true;
			mostrarTirada(tiraIA,tiraIAnull);
			auxIA = tiraIA[0];
			
			accionUsuario = false;
			Thread espero10 = new Thread() {
    			
    			@Override
    			public void run() {
		    		
    				System.out.print("\nEspero que el jugador tire en segunda (empardamos primera y yo ya tire en segunda) ");
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
    			if(cantadoTruco[0].equals("truco") && cantadoTruco[1].equals("quiero")) {
    				MAQUINA.addPuntos(2);
    			}
    			else if(cantadoTruco[1].equals("retruco") && cantadoTruco[2].equals("quiero")) {
    				MAQUINA.addPuntos(3);
    			}
    			else {
    				MAQUINA.addPuntos(4);
    			}
    			
    			//SIGUIENTE RONDA
    		}
    		
    		/*SI EL JUGADOR LA MATA*/
    		
    		else if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
    			//EL JUGADOR GANA
    			//SE SUMAN PUNTOS
    			if(cantadoTruco[0].equals("truco") && cantadoTruco[1].equals("quiero")) {
    				j.addPuntos(2);
    			}
    			else if(cantadoTruco[1].equals("retruco") && cantadoTruco[2].equals("quiero")) {
    				j.addPuntos(3);
    			}
    			else {
    				j.addPuntos(4);
    			}
    			//SIGUIENTE RONDA
    		}
    		
    		/*SI EL JUGADOR EMPARDA*/
    		
    		else {
    			
    			tiraIA = null;
    			tiraIA = MAQUINA.yourTurn(cantado, null);
    			tiraIAnull = true;
    			mostrarTirada(tiraIA,tiraIAnull);
    			auxIA = tiraIA[0];
    			
    			accionUsuario = false;
    			Thread espero11 = new Thread() {
	    			
	    			@Override
	    			public void run() {
			    		
	    				System.out.print("\nEspero que el jugador tire en tercera (empardamos dos veces y yo ya tire todas las cartas) ");
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
	    			if(cantadoTruco[0].equals("truco") && cantadoTruco[1].equals("quiero")) {
	    				j.addPuntos(2);
	    			}
	    			else if(cantadoTruco[1].equals("retruco") && cantadoTruco[2].equals("quiero")) {
	    				j.addPuntos(3);
	    			}
	    			else {
	    				j.addPuntos(4);
	    			}
	    			//SIGUIENTE RONDA
	    		}
	    		
	    		/*SI EL JUGADOR NO LA MATA O EMPARDA*/
	    		
	    		else {
	    			
	    			//LA IA GANA
	    			//SE SUMAN PUNTOS
	    			if(cantadoTruco[0].equals("truco") && cantadoTruco[1].equals("quiero")) {
	    				MAQUINA.addPuntos(2);
	    			}
	    			else if(cantadoTruco[1].equals("retruco") && cantadoTruco[2].equals("quiero")) {
	    				MAQUINA.addPuntos(3);
	    			}
	    			else {
	    				MAQUINA.addPuntos(4);
	    			}
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
    		tiraIA = null;
    		tiraIA = MAQUINA.yourTurn(cantado, auxJ);
    		auxIA = tiraIA[0];
    		tiraIAnull = true;
    		mostrarTirada(tiraIA,tiraIAnull);						//LA TIRA TIRA LAS DOS CARTAS SI MATA
    		
    		/*SI LA IA MATA*/
    		
    		if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
    			
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
	    			if(cantadoTruco[0].equals("truco") && cantadoTruco[1].equals("quiero")) {
	    				j.addPuntos(2);
	    			}
	    			else if(cantadoTruco[1].equals("retruco") && cantadoTruco[2].equals("quiero")) {
	    				j.addPuntos(3);
	    			}
	    			else {
	    				j.addPuntos(4);
	    			}
	    			//SIGUIENTE RONDA
	    		}
	    		
	    		/*SI EL JUGADOR NO MATA*/
	    		
	    		else {
	    			//LA IA GANA
	    			//SE SUMAN PUNTOS
	    			if(cantadoTruco[0].equals("truco") && cantadoTruco[1].equals("quiero")) {
	    				MAQUINA.addPuntos(2);
	    			}
	    			else if(cantadoTruco[1].equals("retruco") && cantadoTruco[2].equals("quiero")) {
	    				MAQUINA.addPuntos(3);
	    			}
	    			else {
	    				MAQUINA.addPuntos(4);
	    			}
	    			//SIGUIENTE RONDA
	    		}
	    		
    		}
    		
    		/*SI LA IA NO MATA*/
    		
    		else {
    			//EL JUGADOR GANA
    			//SE SUMAN PUNTOS
    			if(cantadoTruco[0].equals("truco") && cantadoTruco[1].equals("quiero")) {
    				j.addPuntos(2);
    			}
    			else if(cantadoTruco[1].equals("retruco") && cantadoTruco[2].equals("quiero")) {
    				j.addPuntos(3);
    			}
    			else {
    				j.addPuntos(4);
    			}
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
	
	
	
	public int mostrarTirada(Carta tira[], boolean tiraIAnull) {
		
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
	        
	       
	        
	       if(i==1) {
	    	   return 0;
	       }
	    }
		
		return 0;
	}
	
	
}	//FIN INTERFAZ