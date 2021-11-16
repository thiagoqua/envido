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
	private IA IA;
	private Mazo mazoCartas;
	private Carta cartaPalo;
	
	/*OBJETOS NECESARIOS PARA LOS JUGADORES E IA*/
	
	private String cantado[];	//acumula los cantos sucesivos
    private Carta tiraJ;
    private Carta tiraIA[];
	private boolean accionUsuario;	//permite trabar o destrabar la IA cuando espera la respuesta del jugador
	private boolean turno;	//para saber cuando le toca a la IA y cuando esta luego debe esperar la respuesta del jugador
    private boolean jugando;
	
    //
    
    
	
	public Interfaz() {
	
	/*INICIALIZACION DE OBJETOS DE OTRAS CLASES*/
	
	j = new Jugador();
	IA = new IA();
	mazoCartas = new Mazo();
	cartaPalo = new Carta();
	
	cantado = new String[5];	//acumula los cantos sucesivos
    tiraJ = new Carta();
    tiraIA = new Carta[2];
    accionUsuario = false;
	turno = true;
	jugando = true;
	
	/*CREACION DE VENTANA BASICA*/
	setIconImage(new ImageIcon(getClass().getResource("/images/icon.jpg")).getImage());
	setTitle("TRUCO");
	setSize(800,600);			//SE PUEDE CAMBIAR A 1200x900 Y MANTENER BIEN LA ESTRUCTURA (AUNQUE HABRIA QUE CAMBIAR EL TAMAï¿½O DE LAS IMAGENES)
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
		
		cartel = new JLabel("ï¿½ 2021 TIKI Y ESTEBAN ASOCIADOS. TODOS LOS DERECHOS RESERVADOS.");
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
						
						/*SE SETEAN ALGUNAS CONFIGS PEQUEÑAS*/
						
						j1.setText(texto_nombre.getText());			//SE ESTABLECE EL NOMBRE ESCRITO POR EL JUGADOR
						j2.setText("BOT EASY");						//NOMBRE DE LA IA
						
						//OTROS BOTONES DESACTIVADOS (PORQUE SIEMPRE EMPIEZA LA IA)
						//CUANDO LA IA TIRA, DEBEN ACTIVARSE. CUANDO LA IA JUEGA, ESTÁN DESACTIVADOS.
						
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
							}
						});
						
						c2.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								tirarCartaEnMesa(c2);
							}
						});
						
						c3.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								tirarCartaEnMesa(c3);
							}
						});
						
						rendirse.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								JFrame confirmar = new JFrame();
						        int result = JOptionPane.showConfirmDialog(confirmar, "¿Esta seguro que desea salir? El juego se daria como perdido.");

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
						
						truco.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								flecha2.setEnabled(true);
								
								cp.remove(menu);
								cp.add(menuCantosT,BorderLayout.WEST);
								cp.revalidate();
								cp.repaint();
								
								//creo los listeners del truco
								
								cantarTruco.addActionListener(new ActionListener(){
									
									@Override
									public void actionPerformed(ActionEvent e) {
										for(int i=0;i<5;i++) {
											cantado[i] = null;
										}
										cantado[0] = "truco";
										texto.setText("<html>"+ cantado[0] +"</html>");
										accionUsuario = true;
									}
									
								});
								
								retruco.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										int i;
										for(i=0;i<5;i++) {
											if(cantado[i]==null) {
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
											if(cantado[i]==null) {
												cantado[i] = "vale cuatro";
												break;
											}
										}	
										texto.setText("<html>"+ cantado[i] +"</html>");
										accionUsuario = true;
									}
								});
								
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
								
								//creo los listeners del envido
								
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
													
							}
						});
						
						quiero.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								for(int i=0; i<5 ;i++) {
									if(cantado[i]==null) {
										cantado[i] = "quiero";
										break;
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
								
								for(int i=0; i<5;i++) {
									if(cantado[i]==null) {
										cantado[i] = "no quiero";
										break;
									}
								}
								
								accionUsuario = true;
								noQuiero.setEnabled(false);
								quiero.setEnabled(false);
							}
						});
						
						
						/** A PARTIR DE ACÁ DEBE HABER UN WHILE (O UN THREAD) QUE ENVUELVA/REPITA TODO HASTA QUE ALGUNO DE LOS DOS GANE*/
					    
				        Thread juego = new Thread() {
							
					    	@Override
					    	public void run() {
					    		
					    		while(jugando) {
					    			
									/*SE REPARTEN CARTAS PARA JUGADOR E IA*/
									
								    for(int i=0;i<3;++i){
								            IA.cartas[i] = mazoCartas.sacar();
								    		j.cartas[i] = mazoCartas.sacar();					            
								    }
								 	
							        for(Carta x : IA.cartas)
							            System.out.println(x + "\n");
					    			
					    			/*SE VISUALIZAN LAS CARTAS DEL JUGADOR EN PANTALLA*/
					    
					    			mostrarCartasJugador();

					    			/*ESTE HILO EJECUTARA LA ACCION DE LA MAQUINA (TIRAR CARTAS, ETC.)*/

									Thread IAThread = new Thread(){
							             
										@Override
							            public void run() {
											while(turno) {	
												
												/*LA IA EMPIEZA (RONDA 1) */
												 
									    		IA.setBandera(true);				//nota: cambiarlo por una variable
												IA.activatePuedoCantarEnvido();		//nota: decidir si quiero que sea true o false
												tiraIA = IA.yourTurn(cantado,tiraJ);
												
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
													    		
											    				System.out.print("\nEspero que el jugador quiera/no quiera - retruque ");
											    				while(accionUsuario == false) {
											    					try {
														    			System.out.print(". ");
														    			Thread.sleep(1000);
																	} catch (InterruptedException e) {
																		e.printStackTrace();
																	}
													    		}
													    		System.out.println("\nRetomo la ejecución\n");
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
											    			
											    		}
											    		
											    		/*SI EL JUGADOR REVIRA*/
											    		
											    		else if(cantado[1].equals("envido") || cantado[1].equals("real envido") || cantado[1].equals("falta envido")) {
											    			
											    			IA.yourTurnAccept(cantado);
											    			
											    			//SI LA IA QUIERE/NO QUIERE
											    			
											    			if(cantado[2].equals("quiero") || cantado[2].equals("no quiero")) {
											    				
											    				//SE SUMAN PUNTOS
											    				/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
											    				
											    				if(cantado[2].equals("quiero")) {
											    					System.out.println("la IA quiso");
											    				}
											    				else {
											    					System.out.println("la IA no quiso");
											    				}
											    				
											    				
											    				/***/
											    				
											    			}
											    			
											    			//SI LA IA REVIRA, CREO OTRO HILO DE ESPERA
											    			
											    			else if(cantado[2].equals("real envido") || cantado[2].equals("falta envido")) {
											    				
											    				/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
											    				
											    				System.out.println("la IA reviró");
											    				
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
															    		
													    				System.out.print("\nEspero que el jugador quiera/no quiera - retruque ");
													    				while(accionUsuario == false) {
													    					try {
																    			System.out.print(". ");
																    			Thread.sleep(1000);
																			} catch (InterruptedException e) {
																				e.printStackTrace();
																			}
															    		}
															    		System.out.println("\nRetomo la ejecución\n");
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
																	
																}
																
																//SI EL JUGADOR REVIRA
																
																else if(cantado[3].equals("falta envido")) {
																	
																	IA.yourTurnAccept(cantado);
																	
																	//SI LA IA QUISO/NO QUISO
																	
																	if(cantado[4].equals("quiero") || cantado[4].equals("no quiero")) {
																		
																		//SE SUMAN PUNTOS
																		
																		/**PROVISORIO PARA SABER EL ESTADO DE LA IA*/
													    				
																		if(cantado[2].equals("quiero")) {
													    					System.out.println("la IA quiso");
													    				}
													    				else {
													    					System.out.println("la IA no quiso");
													    				}
													    		
													    				/***/
																		
																	}
																	
																}
																
											    			}
											    			
											    			//nota: posible lugar donde vaya el hilo espero2
											    			
											    		}
											    		

													}
										        	
										        	/*CUANDO YA SE CANTÓ ENVIDO EL ARREGLO DE CANTADOS SE PONE A NULL PARA EL TRUCO*/
										        	
										        	texto = new JLabel("<html>"+ "" +"</html>");
										        	
										        	for(int i=0;i<5;i++) {
										        		cantado[i] = null;
										        	}
										        	
										        	envido.setEnabled(false);
										        										        	
										        	mostrarTirada(tiraIA);
										        	truco.setEnabled(true);
											        c1.setEnabled(true);
											        c2.setEnabled(true);
											        c3.setEnabled(true);
											        accionUsuario = false;
										        	
											        IA.setBandera(false);
											        
										        }	//FIN IF DEL CANTO DE ENVIDO
										        
										        /*SI NO TIENE PARA EL ENVIDO TIRA LA CARTA DIRECTAMENTE*/
										        
										        else {
											        
										        	mostrarTirada(tiraIA);
											        truco.setEnabled(true);
											        c1.setEnabled(true);
											        c2.setEnabled(true);
											        c3.setEnabled(true);
											        accionUsuario = false;
											        
											      //tengo que realizar otro hilo de espera por si el jugador canta truco/envido / o tira carta
											        
											        /*EL JUGADOR PUEDE TIRAR CARTA DIRECTAMENTE*/
											        
											        
											        
											        /*EL JUGADOR PUEDE CANTAR TRUCO*/
											        
											        
											        /*EL JUGADOR PUEDE CANTAR ENVIDO (SI NO SE CANTÓ PREVIAMENTE*/
											        
										        }
									        
										        turno = false;
											
											}     //FIN WHILE IATHREAD
										
										} //FIN RUN IATHREAD
										
									};
									
									IAThread.start();
									
									try {
										IAThread.join();					//sincroniza los hilos
//										Thread.sleep(1000);
									}catch(InterruptedException e) {}
								
						    		if(pts1.getText().equals("30") || pts2.getText().equals("30") ) {
										JOptionPane.showMessageDialog(Interfaz.this, "FIN DEL JUEGO.");
										jugando = false;
										
										//tal vez deba restaurar el contenedor original, llevando a la persona al menu principal
										
									}	
									
						    		//PROVISORIO: PORQUE SINO TIRA EXCEPCION
						    		//NO TIRA EXCEPCION CUANDO LA MAQUINA SE DEDICA A ESPERAR. SI DEJA DE ESPERAR VUELVE A TIRAR EXCEPCION
						    		//POSIBLE SOLUCION: TRATAR EL WHILE CON DOS VARIABLES BOOLEANAS
						    		jugando = false;
						    		
					    		} //FIN WHILE JUEGO
					    	

					    	
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
	
	
	public void tirarCartaEnMesa(JButton c) {
		
		if(l4.getIcon() == null) {
			l4.setIcon(c.getIcon());
		} else if(l5.getIcon() == null) {
			l5.setIcon(c.getIcon());
		} else {
			l6.setIcon(c.getIcon());
		}
		
		c.setIcon(null);
		
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
	
	
	
	public int mostrarTirada(Carta tira[]) {
		
		int j=0;
		
		if(l1.getIcon() != null) {
			j+=1;
			if(l2.getIcon() != null) {
				j+=1;
				if(l3.getIcon() != null) {
					return 1;
				}
			}
		}

	    for(int i=j; tira[i] != null; i++) {

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
	        
	        ctemp.setIcon(null);
	        
	    }
		
		return 0;
	}
	
	
}	//FIN INTERFAZ