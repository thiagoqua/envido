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

import javax.swing.*;
import javax.swing.border.Border;

import java.util.Arrays;

public class Interfaz extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Thread juego;
	
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
	
	private JPanel principal;
	private JLabel portada;
	private JLabel portadawsp;
	private JButton jugar;
	private JButton config;
	private JButton salir;
	
	private JPanel inicio;
	
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
	
	/*COMPONENTES DE JUGAR PERSONA */
	
	private JButton cliente;
	private JButton servidor;
	private JButton volver2;
	
	/*COMPONENTES DE CLIENTE*/
	
	private JTextField texto_nombre2;
	private JLabel ingrese_nombre2;
	private JButton ok2;
	
	/*OBJETOS DE OTRAS CLASES*/
	
	private Jugador j;
	private IA MAQUINA;
	private Mazo mazoCartas;
	
	/*OBJETOS NECESARIOS PARA LOS JUGADORES E IA*/
	
	private String cantado[];	//acumula los cantos sucesivos
			
    private Carta tiraIA[];		//guarda las cartas que tira la IA
    private Carta tiratmp[];
	private boolean accionUsuario;	//permite trabar o destrabar la IA cuando espera la respuesta del jugador
    private boolean jugando;
	
    private Carta auxJ;	//guarda una carta de jugador para comparar
    private Carta auxIA; //guarda una carta de IA para comparar
    private Carta aux; //es utilizado como base para las comparaciones de auxJ y auxIA
    private String acumulador;
    
    private boolean cantoEnvido;
    
    private boolean tiraIAnull;
    
    private int ronda;
    
    private boolean irseAlMazo;

    private boolean botonRendirse;
    //
    
    /*OTROS*/
    
    private JPanel panel2;			//SIRVE PARA INTERCAMBIAR LA IMAGEN DE MAZO Y MANO
	
	/*OBJETOS PARA SOCKETS*/
	private final int defaultPort; 			//puerto para comunicación socket
	private JTextField chat;
	private String recibo;
    private JTextArea caja_mensajes;
    private JScrollPane barrita;
	
	public Interfaz() {
		
		defaultPort = 5010;
		
		interfazCreacion();
		
	}
	
	
	public JPanel ventanaPrincipal() {
		
		/* BOTONES DE VENTANA PRINCIPAL */
		
		portada = new JLabel();
		portada.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bannerTruco.png")).getImage().getScaledInstance(315, 210, Image.SCALE_SMOOTH)));
		
		portadawsp = new JLabel();
		portadawsp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/wsp+.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		
		banner = new JPanel();
		banner.add(portada);
		
		jugar = new JButton("JUGAR");
		jugar.setPreferredSize(new Dimension(100,50));
		salir = new JButton("SALIR");
		config = new JButton("CONFIGURACION");
		inicio = new JPanel(new GridLayout(3,1,4,4));
		inicio.add(jugar);
		inicio.add(config);
		inicio.add(salir);
		
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
				jugarMaquina.setPreferredSize(new Dimension(200,50));
				jugarPersona = new JButton("MENSAJERIA");
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
				cp.add(secundario);
				cp.revalidate();	//Debe invocarse cuando los subcomponentes del contenedor se modifican
				cp.repaint();
				
				
				jugarPersona.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						/*SE CREA LA INTERFAZ DE JUEGO*/
						
						cp.removeAll();

						
						banner.remove(portada);
						banner.add(portadawsp);
						
						cliente = new JButton("CLIENTE");
						cliente.setPreferredSize(new Dimension(100,50));
						servidor = new JButton("SERVIDOR");
						volver2 = new JButton("VOLVER");
						JPanel ventanaSec = new JPanel(new GridLayout(3,1,4,4));
						ventanaSec.add(cliente);
						ventanaSec.add(servidor);
						ventanaSec.add(volver2);
						
						JPanel terciario = new JPanel();
						terciario.setLayout(new GridBagLayout());
						GridBagConstraints gbc2 = new GridBagConstraints();
						
						gbc2.gridx = 0;
						gbc2.gridy = 0;
						gbc2.gridwidth = 1;
						gbc2.gridheight = 1;
						terciario.add(banner,gbc2);
						
						gbc2.gridx = 0;
						gbc2.gridy = 1;
						gbc2.gridwidth = 1;
						gbc2.gridheight = 1;
						gbc2.weighty = 1.0;
						terciario.add(ventanaSec,gbc2);
						
						gbc2.gridx = 0;
						gbc2.gridy = 2;
						gbc2.gridwidth = 1;
						gbc2.gridheight = 1;
						terciario.add(cartel,gbc2);
						
						cp.add(terciario);
						
						cp.revalidate();
						cp.repaint();

						servidor.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								EnvidoServer server = new EnvidoServer(defaultPort);
								
								cp.removeAll();
								cp.revalidate();
								cp.repaint();
								
								cartel.setText("Escriba 'exit' en consola para abortar el programa.");
								
								JLabel chatear;
								chatear = new JLabel("Envie mensajes a su amigo: ");
								
								chat = new JTextField("");								
								chat = new JTextField(chat.getText());
								chat.setPreferredSize(new Dimension(200,20));
								chat.setEnabled(false);
								
								caja_mensajes = new JTextArea();
								caja_mensajes.setEditable(false);
								
								barrita = new JScrollPane(caja_mensajes);  
								barrita.setPreferredSize(new Dimension(400,400));
								barrita.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
								
								JButton ok3;
								ok3 = new JButton("ENVIAR");
								ok3.setEnabled(false);
								
								JPanel menu2 = new JPanel();
								menu2.setLayout(new BoxLayout(menu2, BoxLayout.X_AXIS));
								menu2.add(chatear);
								menu2.add(chat);
								menu2.add(ok3);
								
								JPanel principal2 = new JPanel();
								principal2.setLayout(new GridBagLayout());
								GridBagConstraints gbc3 = new GridBagConstraints();
								
								gbc3.gridx = 0;
								gbc3.gridy = 1;
								gbc3.gridwidth = 1;
								gbc3.gridheight = 1;
								gbc3.weighty = 1.0;
								principal2.add(menu2,gbc3);
								
								gbc3.gridx = 0;
								gbc3.gridy = 0;
								gbc3.gridwidth = 1;
								gbc3.gridheight = 1;
								principal2.add(barrita,gbc3);
								
								gbc3.gridx = 0;
								gbc3.gridy = 2;
								gbc3.gridwidth = 1;
								gbc3.gridheight = 1;
								principal2.add(cartel,gbc3);
								
								cp.add(principal2);

								chat.addKeyListener(new KeyListener(){
									@Override
									public void keyTyped(KeyEvent e) {}

									@Override
									public void keyPressed(KeyEvent e) {
										if(e.getKeyCode() == 10){
											secuenciaEnvioServidor(server);
										}
									}

									@Override
									public void keyReleased(KeyEvent e) {}
								});

								Thread enableButtons = new Thread(){
									@Override
									public void run() {
										while(true){
											if(server.getIsConnected()){
												chat.setEnabled(true);
												ok3.setEnabled(true);
												break;
											}
											else{
												try{		
													Thread.sleep(400);		//para evitar que el bucle se ejecute tan seguido
												} catch(InterruptedException ie) {}
											}
										}
									}
								};
								Thread respuesta = new Thread(){
									public void run(){
										while(true){
											while(true){
												recibo = server.receive();
												caja_mensajes.append(recibo);
											}
										}
									}
								};

								ok3.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e){
										secuenciaEnvioServidor(server);
									}
								});
								
								respuesta.start();
								enableButtons.start();
							}
						});
						
						cliente.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								ingrese_nombre2 = new JLabel("Ingrese direccion IP del servidor: ");
								texto_nombre2 = new JTextField(texto_nombre2.getText());
								texto_nombre2.setPreferredSize(new Dimension(100,20));
								texto_nombre2.addKeyListener((KeyListener) new KeyListener() {
									
									public void keyTyped(KeyEvent e) {
										if (texto_nombre2.getText().length() > 16){
											System.exit(16);
										}
								    }

									@Override
									public void keyPressed(KeyEvent e) {
										if(e.getKeyCode() == 10){
											secuenciaEntradaIP();
										}
									}

									@Override
									public void keyReleased(KeyEvent e) {} 
									
								});
								
								ok2 = new JButton("OK");

								ok2.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										secuenciaEntradaIP();
									}
								});
								
								JPanel menu2 = new JPanel();
								menu2.setLayout(new BoxLayout(menu2, BoxLayout.X_AXIS));
								menu2.add(ingrese_nombre2);
								menu2.add(texto_nombre2);
								menu2.add(ok2);
								
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
								
							}
						});
						
						
						volver2.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								banner.remove(portadawsp);
								
								inicio.removeAll();
								
								/*CONTENEDOR PRINCIPAL*/
								
								cp.removeAll();
								
								interfazCreacion();
								
							}
						});
						
					}
					
				});
				
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
						
						if(texto_nombre == null) {
							j1.setText("");	
						}
						else {
							j1.setText(texto_nombre.getText());			//SE ESTABLECE EL NOMBRE ESCRITO POR EL JUGADOR
						}
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
//								
								accionUsuario = true;
							}
						});
						
						c2.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								tirarCartaEnMesa(c2);

								accionUsuario = true;
							}
						});
						
						c3.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								tirarCartaEnMesa(c3);

								accionUsuario = true;
							}
						});
						
						mazo.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
																	
								mazo.setEnabled(false);
								
								j.voyMazo(cantado, MAQUINA, cantoEnvido);
								
								//AGREGO LOS PUNTOS DE IRSE AL MAZO DE FORMA VISIBLE EN LA INTERFAZ
								acumulador = "";
								acumulador = String.valueOf(MAQUINA.getPuntos());
								pts2.setText(acumulador);
								puntosMaximosSuperados();
								
								irseAlMazo = true;
								
								accionUsuario = true;
								
							}
							
						});
						
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
									
								for(int i=0;i<5;i++) {
									cantado[i]="";
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
									if(cantado[i]==null || cantado[i].equals("")) {
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
									if(cantado[i]==null || cantado[i]=="") {
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
									if(cantado[i]==null || cantado[i]=="") {
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
						
						
						rendirse.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								JFrame confirmar = new JFrame();
						        
								if(j.getPuntos() >= 30 || MAQUINA.getPuntos() >= 30) {
									
									//RESTAURO CADA VARIABLE
										
										MAQUINA.reset();
										j.reset();
									
										//REPONGO LAS CARTAS
										mazoCartas.reponer();
										
										//RESTAURAR POR LAS DUDAS LAS CARTAS QUE SE REPARTIERON
										for(int i=0;i<3;++i){
								            MAQUINA.cartas[i] = null;
											j.cartas[i] = null;
											MAQUINA.copyCartas[i] = null;
											j.copyCartas[i] = null;
										}
									
										for(int i=0;i<5;i++) {
							        		cantado[i] = "";
							        	}
										
										//RESTAURAR EN CERO LAS CARTAS SOBRE LA MESA
										l1.setIcon(null); l2.setIcon(null); l3.setIcon(null);
										l4.setIcon(null); l5.setIcon(null); l6.setIcon(null);
										
										//RESTAURAR EN CERO LAS CARTAS DEL JUGADOR
										c1.setIcon(null); c2.setIcon(null); c3.setIcon(null);
										
										//RESTAURAR EL MENU IZQUIERDO
										flecha2.setEnabled(true);
										flecha.setEnabled(true);
										
										reiniciarMenu();
										
										//RESTAURAR TEXTO EN VACIO
										texto.setText("<html>"+ "" +"</html>");
										pts1.setText("0");
										pts2.setText("0");
									
										//RESTAURO PUNTOS DEL JUGADOR
										j.puntos = 0;
										MAQUINA.puntos = 0;
										
										//REINICIO EL NRO DE RONDAS
										ronda=1;
										
										//INVERTIR IMAGENES DE MANO Y MAZO
										invertirManoMazo();
										
										cantoEnvido = false;
										acumulador = "";
										
										rendirse.setText("RENDIRSE");
										
									//REMUEVO LA INTERFAZ DE JUEGO
										inicio.removeAll();
										
										cp.removeAll();
										
										interfazCreacion();
									
									
								}
								else {
								
									int result = JOptionPane.showConfirmDialog(confirmar, "Esta seguro que desea salir? El juego se daria como perdido.");
	
							        if (result == 0) {
							        	
							        	MAQUINA.puntos = 30;
							        	
							        	botonRendirse = true;
							        	
							        	accionUsuario = true;
							        	
							        }
							        
							        else if (result == 1) {}
							        else {}

								}
							}
						});		//FIN RENDIRSE
						
						//TODO - INICIO DEL JUEGO
						jugando = true;
						
						juego = new Thread() {
							
					    	@Override
					    	public void run() {
						
					    		funcionJuego();
								
					    		puntosMaximosSuperados();
					    		
					    		if(MAQUINA.getPuntos() >= 30) {
					    			
					    			texto.setText("<html>"+ "LA IA GANO" +"</html>");
					    			
					    		}
					    		else if(j.getPuntos() >= 30){
					    			
					    			texto.setText("<html>"+ "EL JUGADOR GANO" +"</html>");
					    			
					    		}
					    		
					    		System.out.println("\n\nFIN\n\n");
					    		
					    	}
							
					    };
						
						juego.start();					//OJO QUE NO USA JOIN JUEGO.START
						
						
						
					}	//FIN ACTION-PERFORMED JUGAR-MAQUINA
				
				});		//FIN ACTION-LISTENER JUGAR-MAQUINA
				
				
				volver.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						/*CONTENEDOR PRINCIPAL*/
						
						inicio.removeAll();
						
						cp.removeAll();
						
						interfazCreacion();
						
					}
				});	//FIN ACTION-LISTENER VOLVER
				
			}	//FIN ACTION-PERFORMED JUGAR	
			
		});		//FIN ACTION-LISTENER JUGAR
		
		//
		
		config.addActionListener(new ActionListener() {
			
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				/*CAMBIAR NOMBRE*/
				
				texto_nombre = new JTextField("");
				
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
				
				cp.add(principal2);
				
				cp.revalidate();
				cp.repaint();
			
				ok.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						inicio.removeAll();
						
						cp.removeAll();
						
						interfazCreacion();
						
					}
				});	//FIN OK
				
			}
		});		//FIN CONFIG
		
	}	//FIN FUNCION ACTION-LISTENER
	
	
	
	public void interfazCreacion() {
	
		recibo = new String();
		principal = new JPanel();
		
		/*INICIALIZACION DE OBJETOS DE OTRAS CLASES*/
		
		j = new Jugador();
		MAQUINA = new IA();
		mazoCartas = new Mazo();
		
		cantado = new String[5];	//acumula los cantos sucesivos
		cantado = new String[5];
	    tiraIA = new Carta[2];
	    tiratmp = new Carta[1];
	    
	    accionUsuario = false;
		jugando = true;
		
		auxJ = new Carta();
	    auxIA = new Carta();
	    aux = new Carta();
	    
	    tiraIAnull = false;
	    acumulador = "";
	    irseAlMazo = false;
	    ronda = 1;
		cantoEnvido = false;  
		botonRendirse = false;
	    
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
		
		//texto_nombre = new JTextField("");
		texto_nombre2 = new JTextField("");
		
		cp = getContentPane();
		
		cp.add(ventanaPrincipal());
		
		cp.revalidate();
		cp.repaint();

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
		
		panel2 = new JPanel(new GridLayout(1,1,4,4));
		panel2.add(img_mano);
		panel2.add(img_mazo);
		
		JPanel panel3 = new JPanel();
		
		unionDer = new JPanel(new GridLayout(4,1,4,4));
		unionDer.setPreferredSize(new Dimension(200,600));
		unionDer.add(panel1);
		unionDer.add(panel_pts);
		unionDer.add(panel2);
		unionDer.add(panel3);
		
		ActionListener();										//LO LLAMO POR PRIMERA VEZ PARA ARRANCAR LA 1ER PARTIDA
		
		/*CONFIGURACIONES ADICIONALES DE LA VENTANA*/
		
		setResizable(false);
		setVisible(true);
		
		
	}
	
	
	public int funcionJuego() {
	
		while(jugando) {
					
			/*REINICIO ALGUNAS VARIABLES QUE PODR�AN MOLESTAR*/
			
			acumulador = "";
			
    		for(int i=0;i<5;i++) {
        		cantado[i] = "";
        	}
			
    		MAQUINA.truco = false; j.truco = false;
    		mazo.setEnabled(true);
    		
    		//RESTAURAR TEXTO EN VACIO
			texto.setText("<html>"+ "" +"</html>");
    		
			/*SE REPARTEN CARTAS PARA JUGADOR E IA*/
			
		    for(int i=0;i<3;++i){
		            MAQUINA.cartas[i] = mazoCartas.sacar();
					MAQUINA.copyCartas[i] = MAQUINA.cartas[i];
		    		j.cartas[i] = mazoCartas.sacar();
					j.copyCartas[i] = j.cartas[i];            
		    }

			/*SE VISUALIZAN LAS CARTAS DEL JUGADOR EN PANTALLA*/

			mostrarCartasJugador();
			
			if(ronda % 2 == 0) {
				
				for(int i=0;i<5;i++) {
	        		cantado[i] = "";
	        	}
				
				//ACTIVO DETERMINADOS BOTONES
				
				truco.setEnabled(true);
				flecha2.setEnabled(true);
				cantarTruco.setEnabled(true);
				retruco.setEnabled(false);
				vale_4.setEnabled(false);
				
				envido.setEnabled(true);
				flecha.setEnabled(true);
				cantarEnvido.setEnabled(true);
				real_envido.setEnabled(true);
				falta_envido.setEnabled(true);
				
				c1.setEnabled(true);
				c2.setEnabled(true);
				c3.setEnabled(true);
				
				//SETEO LAS MANOS
				j.setAmbasManos(true, MAQUINA);
							
				llamadaHilos();
				
				if(botonRendirse == true) {
					
					return 1;
					
				}
				
				/*EL JUGADOR CANTA ENVIDO*/
				
				if(cantado[0].equals("envido") || cantado[0].equals("real envido") || cantado[0].equals("falta envido")) {
					
					mazo.setEnabled(false);
					//LA MAQUINA YA NO PUEDE CANTAR CUANDO LE TOQUE A ELLA
					cantoEnvido = true;
					MAQUINA.setPuedoCantarEnvido(false);
					
					cantaEnvidoJugador();		//DENTRO DE ESTA FUNCION HAY OTRAS PARA EL TRUCO Y TIRARDIR
					
					if(botonRendirse == true) {
						return 1;
					}
					
				}
				
				/*EL JUGADOR CANTA TRUCO*/
				
				else if(cantado[0].equals("truco")) {
					
					cantarTruco.setEnabled(false);
					
					MAQUINA.setPuedoCantarEnvido(true);
					
					mazo.setEnabled(false);
					
					//LA MAQUINA PUEDE CANTAR PREVIAMENTE ENVIDO ANTES DEL TRUCO
					
					MAQUINA.yourTurnAccept(cantado,j);
					
					if(cantado[0].equals("envido") || cantado[0].equals("real envido") || cantado[0].equals("falta envido")) {
						
						cantoEnvido = true;
						
						mazo.setEnabled(false);
						texto.setText("<html>"+ cantado[0] +"</html>");
						
						cantaEnvidoIA();
						
						if(botonRendirse == true) {
							return 1;
						}
						
					}
					
					if(jugando == false) {
						
						break;
						
					}
					
					texto.setText("<html>"+ "" +"</html>");
					
					Arrays.fill(cantado,"");
					
					//SE DESACTIVA EL ENVIDO
					envido.setEnabled(false);
					
					//TRAS EL ENVIDO, SE ACEPTA O NO EL TRUCO
					cantado[0] = "truco";
					texto.setText("<html>"+ cantado[0] +"</html>");
					
					MAQUINA.setPuedoCantarEnvido(false);
					
					//SE DESARROLLA LA FUNCION DEL TRUCO
					truco2();
					
					if(botonRendirse == true) {
						return 1;
					}
					
				}
				
				/*EL JUGADOR TIRA DIRECTAMENTE*/
				
				else {
					
					auxJ = queCartaFueTirada();
					
					truco.setEnabled(false);
    				cantarTruco.setEnabled(false);
    				
    				envido.setEnabled(false);
    				cantarEnvido.setEnabled(false);
    				real_envido.setEnabled(false);
    				falta_envido.setEnabled(false);
    				
    				c1.setEnabled(false);
    				c2.setEnabled(false);
    				c3.setEnabled(false);
    				
    				MAQUINA.setPuedoCantarEnvido(true);
						
					//LA IA PUEDE CANTAR ENVIDO
					MAQUINA.yourTurnEnvido(cantado);
					
					if(cantado[0].equals("envido") || cantado[0].equals("real envido") || cantado[0].equals("falta envido")) {
						
						cantoEnvido = true;
						
						mazo.setEnabled(false);
						texto.setText("<html>"+ cantado[0] +"</html>");
						
						cantaEnvidoIA(); 
						
						if(botonRendirse == true) {
							return 1;
						}
						
						mazo.setEnabled(true);
						envido.setEnabled(false);
					}
					
					if(jugando == false) {
						
						break;
						
					}
					
					Arrays.fill(cantado,"");
					MAQUINA.setPuedoCantarEnvido(false);
					
					tiraIA = null;
					tiraIA = MAQUINA.yourTurnTruco(cantado, auxJ);	
					
					//LA IA PUEDE CANTAR TRUCO
					
					if(cantado[0].equals("truco")) {
						
						mazo.setEnabled(false);
						truco3();
						
						if(botonRendirse == true) {
							return 1;
						}
						
					}
					
					//LA IA PUEDE TIRAR DIRECTAMENTE
					
					else {
						
						tiraDir2();
						
						if(botonRendirse == true) {
							return 1;
						}
						
					}
					
				}
				
				
			}
			
			else {	
				
				truco.setEnabled(false);
				flecha2.setEnabled(true);
				cantarTruco.setEnabled(false);
				retruco.setEnabled(false);
				vale_4.setEnabled(false);
				
				envido.setEnabled(false);
				flecha.setEnabled(true);
				cantarEnvido.setEnabled(false);
				real_envido.setEnabled(false);
				falta_envido.setEnabled(false);
				
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				
				//SETEO LAS MANOS
				MAQUINA.setAmbasManos(true,j);
										
				/*LA IA EMPIEZA (RONDA 1 Y RONDAS IMPARES) */
				
				MAQUINA.setPuedoCantarEnvido(true);		//nota: decidir si quiero que sea true o false
				tiraIA = MAQUINA.yourTurn(cantado,null);
				auxIA = tiraIA[0];
				
		        /* CANTA ENVIDO (SI TIENE) */
		        
		        if(cantado[0] != null && cantado[0]!="") {
		        	if(cantado[0].equals("envido") || cantado[0].equals("real envido") || cantado[0].equals("falta envido")) {
						
		        		mazo.setEnabled(false);
		        		cantaEnvidoIA();
			    		
		        		if(botonRendirse == true) {
							return 1;
						}
		        		
					}
		        	
		        	if(jugando == false) {
		        		
		        		break;
		        		
		        	}
		        	
		        	/*CUANDO YA SE CANTO ENVIDO EL ARREGLO DE CANTADOS SE PONE A NULL PARA EL TRUCO*/
		        	
		        	mazo.setEnabled(true);
		        	texto.setText("<html>"+ "" +"</html>");
		        	
		        	for(int i=0;i<5;i++) {
		        		cantado[i] = "";
		        	}
		        										        	
		        	mostrarTirada(tiraIA,tiraIAnull);
		        	tiraIA = null;
		        	
		        	truco.setEnabled(true);
		        	envido.setEnabled(false);
		        	cantoEnvido = true;
		        	
			        c1.setEnabled(true);
			        c2.setEnabled(true);
			        c3.setEnabled(true);
			        
			        quiero.setEnabled(false);
			        noQuiero.setEnabled(false);
			       
			        
		        }	//FIN IF DEL CANTO DE ENVIDO
		        
		        if(jugando == false) {
		        	
		        	break;
		        	
		        }
		        
		        for(int i=0;i<5;i++) {
	        		cantado[i] = "";
	        	}
		        
		        /*SI LA IA CANTO ENVIDO ENTONCES SE DESACTIVA EL BOTON, SINO SE MANTIENE ACTIVO*/
			        
	        	if(cantoEnvido == true) {
	        		envido.setEnabled(false);
	        	}
	        	else {
	        		envido.setEnabled(true);
	        		cantarEnvido.setEnabled(true);
	        		real_envido.setEnabled(true);
	        		falta_envido.setEnabled(true);
	        		
	        		/*SI NO TIENE PARA EL ENVIDO TIRA LA CARTA DIRECTAMENTE*/
	        		
	        		mostrarTirada(tiraIA,tiraIAnull);
		        	auxIA = tiraIA[0];					//esto me sirve para comparar las cartas cuando el jugador tire sin cantar (en la primera)
			        tiraIA = null;
	        		
	        	}
	        									        	
	        	truco.setEnabled(true);
	        	cantarTruco.setEnabled(true);
	        	flecha2.setEnabled(true);
	        	retruco.setEnabled(false);
	        	vale_4.setEnabled(false);
	        	
		        c1.setEnabled(true);
		        c2.setEnabled(true);
		        c3.setEnabled(true);
		        
		        quiero.setEnabled(false);
		        noQuiero.setEnabled(false);
			        
		        llamadaHilos();
	    		
		        if(botonRendirse == true) {
		        	return 1;
		        }
		        
	    		/*EL JUGADOR PUEDE CANTAR ENVIDO (SI NO SE CANTO PREVIAMENTE)*/
	    		
	    		if(cantado[0].equals("envido") || cantado[0].equals("real envido") || cantado[0].equals("falta envido")) {
	    			
	    			cantoEnvido = true;
	    			
	    			cantaEnvidoJugador();
		    		
	    			if(botonRendirse == true) {
						return 1;
					}
	    			
	    		}
	    		
	    		
	    		/*EL JUGADOR PUEDE CANTAR TRUCO*/
	    		
	    		else if(cantado[0].equals("truco")) {
	    			
	    			truco();
	    			
	    			if(botonRendirse == true) {
						return 1;
					}
	    			
	    		}
	    		
		        /*EL JUGADOR PUEDE TIRAR CARTA DIRECTAMENTE*/
		        
	    		else {
	    			
	    			tirarDir();
	    			
	    			if(botonRendirse == true) {
						return 1;
					}
	    			
	    		}
	    		
	    		texto.setText("<html>"+ "" +"</html>");
	    		  											
								
			}	//FIN RONDAS IMPARES
			
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			irseAlMazo = false;
			
			
			
			//RESETEO ALGUNAS VARIABLES DE LOS JUGADORES
			MAQUINA.reset();
			j.reset();
			
			//REPONGO LAS CARTAS, LAS LLEVO AL MAZO
			mazoCartas.reponer();
			
			//RESTAURAR DE CERO LA INTERFAZ CUANDO SE PASA A LA SIGUIENTE RONDA
			
				//RESTAURAR POR LAS DUDAS LAS CARTAS QUE SE REPARTIERON
				for(int i=0;i<3;++i){
		            MAQUINA.cartas[i] = null;
		            MAQUINA.copyCartas[i] = null;
					j.cartas[i] = null;
					j.copyCartas[i] = null;
				}
			
				//RESTAURAR EN CERO LAS CARTAS SOBRE LA MESA
				l1.setIcon(null); l2.setIcon(null); l3.setIcon(null);
				l4.setIcon(null); l5.setIcon(null); l6.setIcon(null);
				
				//RESTAURAR EN CERO LAS CARTAS DEL JUGADOR
				c1.setIcon(null); c2.setIcon(null); c3.setIcon(null);
				
				//RESTAURAR EL MENU IZQUIERDO
				flecha2.setEnabled(true);
				flecha.setEnabled(true);
				reiniciarMenu();
							
			ronda+=1;
			
			cantoEnvido = false;
			
			//INVERTIR IMAGENES DE MANO Y MAZO
			invertirManoMazo();
			
			if(jugando != false) {
			
				texto.setText("<html>"+ "REPARTIENDO" +"</html>");
				
				//HAGO UNA PEQUENIA PAUSA PARA QUE SE NOTE LA TRANSICION
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				texto.setText("<html>"+ "" +"</html>");
			
			}
			
			
			
			
		} //FIN WHILE JUEGO
		
		return 0;
		
	}
	
	
	//FUNCION TRUCO - RONDAS PARES - EL JUGADOR TIRA DIRECT. Y LA IA CANTA TRUCO EN PRIMERA
	
	public int truco3() {
		
		texto.setText("<html>"+ cantado[0] +"</html>");
		
		quiero.setEnabled(true);
		noQuiero.setEnabled(true);
		
		truco.setEnabled(true);
		retruco.setEnabled(true);
		mazo.setEnabled(false);
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*SI EL JUGADOR QUIERE*/
		
		if(cantado[1].equals("quiero")) {
			
			retruco.setEnabled(false);
			trucoCantadoIA();
			
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
			
			MAQUINA.yourTurnAccept(cantado,j);
			
			/*SI LA IA QUIERE*/
			
			if(cantado[2].equals("quiero")) {
				
				texto.setText("<html>"+ cantado[2] +"</html>");
				
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				trucoCantadoIA();
				
			}
			
			/*SI LA IA NO QUIERE*/
			
			else if(cantado[2].equals("no quiero")) {
				
				texto.setText("<html>"+ cantado[2] +"</html>");
				
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
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
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
				/*SI EL JUGADOR QUIERE*/
				
				if(cantado[3].equals("quiero")) {
				
					trucoCantadoIA();
					
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
		
		return 0;
		
	}
	
	
	//FUNCION TRUCO - RONDAS PARES - LA IA ES LA QUE CANTA TRUCO EN PRIMERA - FUNCION QUE SIRVE PARA AHORRAR CODIGO DENTRO DE TRUCO3
	
	public int trucoCantadoIA() {
		
		//LA IA TIRA
		
		auxIA = tiraIA[0];
		
		/*SI LA IA MATA*/
		
		if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
			
			mostrarTirada(tiraIA, true);
			
			if(c1.getIcon() != null) {
				c1.setEnabled(true);
			}
			if(c2.getIcon() != null) {
				c2.setEnabled(true);
			}
			if(c3.getIcon() != null) {
				c3.setEnabled(true);
			}
			
			mazo.setEnabled(true);
			
			llamadaHilos();
			
			if(botonRendirse == true) {
	        	return 1;
	        }
			
			/*EL JUGADOR SE VA AL MAZO*/
			
			if(irseAlMazo == true) {

			}
			
			/*EL JUGADOR TIRA*/
			
			else {
				
				auxJ = queCartaFueTirada();
				auxIA = tiraIA[1];
				
				/*SI EL JUGADOR MATA*/
				
				if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
					
					llamadaHilos();
					
					if(botonRendirse == true) {
			        	return 1;
			        }
					
					/*EL JUGADOR SE VA AL MAZO*/
					
					if(irseAlMazo == true) {
						
					}
					
					else {
					
						/*EL JUGADOR TIRA*/
						
						auxJ = queCartaFueTirada();
						
						tiraIA = null;
						tiraIA = MAQUINA.yourTurn(cantado, auxJ);
						auxIA = tiraIA[0];
						mostrarTirada(tiraIA, true);
						
						/*SI LA IA MATA O EMPARDA*/
						
						if(aux.returnOrden(auxIA) <= aux.returnOrden(auxJ)) {
							
							//LA IA GANA
							MAQUINA.truco = true;
							sistPuntuacion(cantado,MAQUINA,j);
							acumulador = String.valueOf(MAQUINA.getPuntos());
							pts2.setText(acumulador);
							
							puntosMaximosSuperados();
							//SIGUIENTE RONDA
							
						}
						
						/*SI LA IA NO MATA*/
						
						else {
							
							//EL JUGADOR GANA
			    			j.truco = true;
							sistPuntuacion(cantado,MAQUINA,j);
							acumulador = String.valueOf(j.getPuntos());
							pts1.setText(acumulador);
							
							puntosMaximosSuperados();
			    			//SIGUIENTE RONDA
							
						}
						
					}
					
				}
				
				/*SI EL JUGADOR NO MATA O EMPARDA*/
				
				else {
					
					c1.setEnabled(false);
					c2.setEnabled(false);
					c3.setEnabled(false);
					
					//LA IA GANA
					MAQUINA.truco = true;
					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(MAQUINA.getPuntos());
					pts2.setText(acumulador);
					
					puntosMaximosSuperados();
					//SIGUIENTE RONDA
					
				}
				
			}
			
			
		}
		
		
		/*SI LA IA NO MATA*/
		
		else if(aux.returnOrden(auxIA) > aux.returnOrden(auxJ)) {
			
			mostrarTirada(tiraIA, true);
			
			if(c1.getIcon() != null) {
				c1.setEnabled(true);
			}
			if(c2.getIcon() != null) {
				c2.setEnabled(true);
			}
			if(c3.getIcon() != null) {
				c3.setEnabled(true);
			}
			
			llamadaHilos();
			
			if(botonRendirse == true) {
	        	return 1;
	        }
			
			/*EL JUGADOR VA AL MAZO*/
			
			if(irseAlMazo == true) {
				
			}
			
			/*EL JUGADOR TIRA*/
			
			else {
			
				auxJ = queCartaFueTirada();
				
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				
				tiraIA = null;
				tiraIA = MAQUINA.yourTurn(cantado, auxJ);
				auxIA = tiraIA[0];
				mostrarTirada(tiraIA, true);
				
				/*SI LA IA MATA*/
				
				if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
					
					//LA IA GANA
					MAQUINA.truco = true;
					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(MAQUINA.getPuntos());
					pts2.setText(acumulador);
					
					puntosMaximosSuperados();
					//SIGUIENTE RONDA
					
				}
				
				/*SI LA IA NO MATA O EMPARDA*/
				
				else {
					
					//EL JUGADOR GANA
	    			j.truco = true;
					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(j.getPuntos());
					pts1.setText(acumulador);
					
					puntosMaximosSuperados();
	    			//SIGUIENTE RONDA
					
				}
			}
			
		}
		
		
		
		/*SI LA IA EMPARDA*/
		
		else {
			
			if(c1.getIcon() != null) {
				c1.setEnabled(true);
			}
			if(c2.getIcon() != null) {
				c2.setEnabled(true);
			}
			if(c3.getIcon() != null) {
				c3.setEnabled(true);
			}
			
			llamadaHilos();
			
			if(botonRendirse == true) {
	        	return 1;
	        }
			
			if(irseAlMazo == true) {
				
			}
			
			else {
			
				auxJ = queCartaFueTirada();
				
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				
				tiraIA = null;
				tiraIA = MAQUINA.yourTurn(cantado, auxJ);
				auxIA = tiraIA[0];
				mostrarTirada(tiraIA, true);
				
				/*SI LA IA MATA*/
				
				if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
					
					//LA IA GANA
					MAQUINA.truco = true;
					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(MAQUINA.getPuntos());
					pts2.setText(acumulador);
					
					puntosMaximosSuperados();
					//SIGUIENTE RONDA
					
				}
				
				/*SI LA IA NO MATA*/
				
				else if(aux.returnOrden(auxIA) > aux.returnOrden(auxJ)) {
					
					//EL JUGADOR GANA
	    			j.truco = true;
					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(j.getPuntos());
					pts1.setText(acumulador);
					
					puntosMaximosSuperados();
	    			//SIGUIENTE RONDA
					
				}
				
				/*SI LA IA EMPARDA*/
				
				else {
					
					if(c1.getIcon() != null) {
						c1.setEnabled(true);
					}
					if(c2.getIcon() != null) {
						c2.setEnabled(true);
					}
					if(c3.getIcon() != null) {
						c3.setEnabled(true);
					}
					
					llamadaHilos();
					
					if(botonRendirse == true) {
			        	return 1;
			        }
					
					auxJ = queCartaFueTirada();
					
					tiraIA = null;
					tiraIA = MAQUINA.yourTurn(cantado, auxJ);
					auxIA = tiraIA[0];
					mostrarTirada(tiraIA, true);
					
					/*SI LA IA MATA*/
					
					if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
						
						//LA IA GANA
						MAQUINA.truco = true;
						sistPuntuacion(cantado,MAQUINA,j);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
						
					}
					
					/*SI LA IA NO MATA O EMPARDA*/
					
					else {
						
						//EL JUGADOR GANA
		    			j.truco = true;
						sistPuntuacion(cantado,MAQUINA,j);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
		    			//SIGUIENTE RONDA
						
					}
					
				}
			
			}
			
		}
		
		return 0;
		
	}
	
	//FUNCION TRUCO - RONDAS PARES
	
	public void truco2() {
		
		truco();
		
	}
	
	//OJO QUE ESTOY USANDO THREADS CON NOMBRES YA USADOS
	
	public int trucoQueridoRondasPares() {
		
		c1.setEnabled(true);
		c2.setEnabled(true);
		c3.setEnabled(true);
		
		truco.setEnabled(false);
		flecha2.setEnabled(true);
		mazo.setEnabled(true);
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
		
			auxJ = queCartaFueTirada();
			
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			
			tiraIA = MAQUINA.yourTurn(cantado, auxJ);
			auxIA = tiraIA[0];
			
			/*SI LA IA MATA*/
			
			if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
				
				//LA IA TIRA DOS CARTAS
				tiraIAnull = false;
				mostrarTirada(tiraIA, tiraIAnull);
				auxIA = tiraIA[1];
				
				if(c1.getIcon() != null) {
					c1.setEnabled(true);
				}
				if(c2.getIcon() != null) {
					c2.setEnabled(true);
				}
				if(c3.getIcon() != null) {
					c3.setEnabled(true);
				}
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
				/*EL JUGADOR SE VA AL MAZO*/
				
				if(irseAlMazo == true) {
					
				}
				
				/*EL JUGADOR TIRA*/
				
				else {
				
					auxJ = queCartaFueTirada();
					
					/*SI EL JUGADOR LA MATA*/
					
					if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
						
						llamadaHilos();
						
						/*EL JUGADOR SE VA AL MAZO*/
						
						if(irseAlMazo == true) {
							
						}
						
						/*EL JUGADOR TIRA*/
						
						else {
						
							auxJ = queCartaFueTirada();
							
																	//OJO CON ESTO PORQUE NO USE TIRATMP NI TIRAIANULL
							tiraIA = null;
							tiraIA = MAQUINA.yourTurn(cantado, auxJ);
							auxIA = tiraIA[0];
							mostrarTirada(tiraIA, true);
							
							/*SI LA IA MATA O EMPARDA*/
							
							if(aux.returnOrden(auxIA) <= aux.returnOrden(auxJ)) {
								//LA IA GANA
								//SE SUMAN PUNTOS
								MAQUINA.truco = true;
								sistPuntuacion(cantado,j,MAQUINA);
				    			acumulador = String.valueOf(MAQUINA.getPuntos());
				    			pts2.setText(acumulador);
				    			
				    			puntosMaximosSuperados();
								
								//SIGUIENTE RONDA
								
								
							}
							
							/*SI LA IA NO MATA*/
							
							else {
								//EL JUGADOR GANA
								//SE SUMAN PUNTOS
								j.truco = true;
								sistPuntuacion(cantado,j,MAQUINA);
				    			acumulador = String.valueOf(j.getPuntos());
				    			pts1.setText(acumulador);
				    			
				    			puntosMaximosSuperados();
								
								//SIGUIENTE RONDA
								
							}
						}
						
					}
					
					/*SI EL JUGADOR NO LA MATA O EMPARDA*/
					
					else {
						//LA IA GANA
						//SE SUMAN PUNTOS
						MAQUINA.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
		    			acumulador = String.valueOf(MAQUINA.getPuntos());
		    			pts2.setText(acumulador);
		    			
		    			puntosMaximosSuperados();
						
						//SIGUIENTE RONDA
						
						
					}
				
				}
				
			}
			
			
			/*SI LA IA NO MATA*/
			
			else if(aux.returnOrden(auxIA) > aux.returnOrden(auxJ)) {
				
				if(c1.getIcon() != null) {
					c1.setEnabled(true);
				}
				if(c2.getIcon() != null) {
					c2.setEnabled(true);
				}
				if(c3.getIcon() != null) {
					c3.setEnabled(true);
				}
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
				/*EL JUGADOR SE VA AL MAZO*/
				
				if(irseAlMazo == true) {
					
				}
				
				/*EL JUGADOR TIRA*/
				
				else {
					
					auxJ = queCartaFueTirada();
					
					c1.setEnabled(false);
					c2.setEnabled(false);
					c3.setEnabled(false);
					
					tiraIA = null;
					tiraIA = MAQUINA.yourTurn(cantado, auxJ);
					auxIA = tiraIA[0];
					
					/*SI LA IA MATA*/
					
					if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
						
						mostrarTirada(tiraIA, true);
						
						if(c1.getIcon() != null) {
							c1.setEnabled(true);
						}
						if(c2.getIcon() != null) {
							c2.setEnabled(true);
						}
						if(c3.getIcon() != null) {
							c3.setEnabled(true);
						}
						
						llamadaHilos();
						
						if(botonRendirse == true) {
				        	return 1;
				        }
						
						/*EL JUGADOR SE VA AL MAZO*/
						
						if(irseAlMazo == true) {
							
							
							
						}
						
						/*EL JUGADOR TIRA*/
						
						else {
							
							auxJ = queCartaFueTirada();
							
							/*SI EL JUGADOR MATA O EMPARDA*/
							
							if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
								
								//EL JUGADOR GANA
								//SE SUMAN PUNTOS
								j.truco = true;
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
								MAQUINA.truco = true;
								sistPuntuacion(cantado,j,MAQUINA);
				    			acumulador = String.valueOf(MAQUINA.getPuntos());
				    			pts2.setText(acumulador);
				    			
				    			puntosMaximosSuperados();
								
								//SIGUIENTE RONDA
								
							}
						
						}
												
					}
					
					/*SI LA IA NO MATA O EMPARDA*/
					
					else {
						
						mostrarTirada(tiraIA, true);		//DEBERIA MOSTRAR SOLO UNA
						
						//EL JUGADOR GANA
						//SE SUMAN PUNTOS
						j.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
		    			acumulador = String.valueOf(j.getPuntos());
		    			pts1.setText(acumulador);
		    			
		    			puntosMaximosSuperados();
						
						//SIGUIENTE RONDA
						
					}
				
				}
				
			}
			
			
			/*SI LA IA EMPARDA*/
			
			else {
				
				if(c1.getIcon() != null) {
					c1.setEnabled(true);
				}
				if(c2.getIcon() != null) {
					c2.setEnabled(true);
				}
				if(c3.getIcon() != null) {
					c3.setEnabled(true);
				}
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
				/*EL JUGADOR SE VA AL MAZO*/
				
				if(irseAlMazo == true) {
					
				}
				
				/*EL JUGADOR TIRA*/
				
				else {
					
					auxJ = queCartaFueTirada();
					
					//LE TOCA A LA IA
					
					c1.setEnabled(false);
					c2.setEnabled(false);
					c3.setEnabled(false);
					
					tiraIA = null;
					tiraIA = MAQUINA.yourTurn(cantado, auxJ);
					auxIA = tiraIA[0];
					
					/*SI LA IA MATA*/
					
					if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
						
						//DIRECTAMENTE HAGO NULL LA SEGUNDA POSICION, CHEQUEAR QUE ANDE JOYA
						tiraIA[1] = null;
						mostrarTirada(tiraIA, true);
						
						//LA IA GANA
						//SE SUMAN PUNTOS
						MAQUINA.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
		    			acumulador = String.valueOf(MAQUINA.getPuntos());
		    			pts2.setText(acumulador);
		    			
		    			puntosMaximosSuperados();
						
						//SIGUIENTE RONDA
						
					}
					
					/*SI LA IA NO MATA*/
					
					else if(aux.returnOrden(auxIA) > aux.returnOrden(auxJ)) {
						
						tiraIA[1] = null;
						mostrarTirada(tiraIA, true);
						
						//EL JUGADOR GANA
						//SE SUMAN PUNTOS
						j.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
		    			acumulador = String.valueOf(j.getPuntos());
		    			pts1.setText(acumulador);
		    			
		    			puntosMaximosSuperados();
						
						//SIGUIENTE RONDA
						
					}
					
					/*SI LA IA EMPARDA*/
					
					else {
						
						if(c1.getIcon() != null) {
							c1.setEnabled(true);
						}
						if(c2.getIcon() != null) {
							c2.setEnabled(true);
						}
						if(c3.getIcon() != null) {
							c3.setEnabled(true);
						}
						
						llamadaHilos();
						
						if(botonRendirse == true) {
				        	return 1;
				        }
						
						/*EL JUGADOR SE VA AL MAZO*/
						
						if(irseAlMazo == true) {
							
						}
						
						/*EL JUGADOR TIRA*/
						
						else {
							
							auxJ = queCartaFueTirada();
							
							//LE TOCA A LA IA
							
							tiraIA = null;
							tiraIA = MAQUINA.yourTurn(cantado, auxJ);
							auxIA = tiraIA[0];
							mostrarTirada(tiraIA, true);
							
							/*SI LA IA MATA*/
							
							if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
								
								//LA IA GANA
								//SE SUMAN PUNTOS
								MAQUINA.truco = true;
								sistPuntuacion(cantado,j,MAQUINA);
				    			acumulador = String.valueOf(MAQUINA.getPuntos());
				    			pts2.setText(acumulador);
				    			
				    			puntosMaximosSuperados();
								
								//SIGUIENTE RONDA
								
							}
							
							/*SI LA IA NO MATA O EMPARDA*/
							
							else {
								
								//EL JUGADOR GANA
								//SE SUMAN PUNTOS
								j.truco = true;
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
		}
		
		return 0;
		
	}
	
	
	//FUNCION TIRAR DIRECTAMENTE - RONDAS PARES
	
	public int tiraDir2() {
		
		auxIA = tiraIA[0];
		
		/*SI LA IA MATA*/
		
		if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
			
			tiratmp[0] = auxIA;
			mostrarTirada(tiratmp, true);
			
			if(tiraIA[1].isGood() == true) {
				cantado[0] = "truco";
			}
			
			/*LA IA CANTA TRUCO*/
			
			if(cantado[0].equals("truco")) {
				
				quiero.setEnabled(true);
				noQuiero.setEnabled(true);
				retruco.setEnabled(true);
				truco.setEnabled(true);
				mazo.setEnabled(false);
				
				texto.setText("<html>"+ cantado[0] +"</html>");
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
				/*SI EL JUGADOR QUIERE*/
				
				if(cantado[1].equals("quiero")) {
					
					retruco.setEnabled(false);
					trucoQueridoB1();			//LA IA TODAVIA TIENE QUE TIRAR LA SEGUNDA CARTA (TIRAIA[1])
					
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
					
					MAQUINA.yourTurnAccept(cantado,j);
					
					/*SI LA IA QUIERE*/
					
					if(cantado[2].equals("quiero")) {
						
						texto.setText("<html>"+ cantado[2] +"</html>");
						
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						trucoQueridoB1();
						
					}
					
					/*SI LA IA NO QUIERE*/
					
					else if(cantado[2].equals("no quiero")) {
						
						texto.setText("<html>"+ cantado[2] +"</html>");
						
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
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
						
						llamadaHilos();
						
						if(botonRendirse == true) {
				        	return 1;
				        }
						
						/*SI EL JUGADOR QUIERE*/
						
						if(cantado[3].equals("quiero")) {
							
							trucoQueridoB1();
							
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
				
				auxIA = tiraIA[1];
				tiratmp[0] = auxIA;
				mostrarTirada(tiratmp, true);
				
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
				cantarTruco.setEnabled(true);
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
				/*EL JUGADOR SE VA AL MAZO*/
				
				if(irseAlMazo == true) {

				}
				
				/*EL JUGADOR CANTA TRUCO*/
				
				else if(cantado[0].equals("truco")) {
					
					c1.setEnabled(false);
					c2.setEnabled(false);
					c3.setEnabled(false);
					mazo.setEnabled(false);
					
					cantarTruco.setEnabled(false);
					
					MAQUINA.yourTurnAccept(cantado,j);
					
					/*SI LA IA QUIERE*/
					
					if(cantado[1].equals("quiero")) {
						
						texto.setText("<html>"+ cantado[1] +"</html>");
						
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						trucoQueridoB2();
						
					}
					
					/*SI LA IA NO QUIERE*/
					
					else if(cantado[1].equals("no quiero")) {
						
						texto.setText("<html>"+ cantado[1] +"</html>");
						
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
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
						
						llamadaHilos();
						
						if(botonRendirse == true) {
				        	return 1;
				        }
						
						truco.setEnabled(false);
						
						/*SI EL JUGADOR QUIERE*/
						
						if(cantado[2].equals("quiero")) {
							
							vale_4.setEnabled(false);
							trucoQueridoB2();
							
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
							
							MAQUINA.yourTurnAccept(cantado,j);
							
							/*SI LA IA QUIERE*/
							
							if(cantado[3].equals("quiero")) {
								
								texto.setText("<html>"+ cantado[3] +"</html>");
								
								try {
									Thread.sleep(800);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								
								trucoQueridoB2();
								
							}
							
							/*SI LA IA NO QUIERE*/
							
							else {
								
								texto.setText("<html>"+ cantado[3] +"</html>");
								
								try {
									Thread.sleep(800);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								
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
					
					auxJ = queCartaFueTirada();
							
					cantarTruco.setEnabled(false);
					
					auxIA = tiraIA[1];
					
					/*SI EL JUGADOR MATA*/
					
					if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
						
						cantarTruco.setEnabled(true);
						
						llamadaHilos();
						
						if(botonRendirse == true) {
				        	return 1;
				        }
						
						cantarTruco.setEnabled(false);
						
						/*EL JUGADOR SE VA AL MAZO*/
						
						if(irseAlMazo == true) {
							
						}
						
						/*EL JUGADOR CANTA TRUCO*/
						
						else if(cantado[0].equals("truco")) {
							
							c1.setEnabled(false);
							c2.setEnabled(false);
							c3.setEnabled(false);
							mazo.setEnabled(false);
							
							MAQUINA.yourTurnAccept(cantado,j);
							
							/*LA IA QUIERE*/
							
							if(cantado[1].equals("quiero")) {
								
								texto.setText("<html>"+ cantado[1] +"</html>");
								
								try {
									Thread.sleep(800);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								
								trucoQueridoB3();
								
							}
							
							/*LA IA NO QUIERE*/
							
							else if(cantado[1].equals("no quiero")) {
								
								texto.setText("<html>"+ cantado[1] +"</html>");
								
								try {
									Thread.sleep(800);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								
								//EL JUGADOR GANA
				    			
								sistPuntuacion(cantado,j,MAQUINA);
								acumulador = String.valueOf(j.getPuntos());
								pts1.setText(acumulador);
								
								puntosMaximosSuperados();
				    			//SIGUIENTE RONDA
							}
							
							/*LA IA REVIRA*/
							
							else if(cantado[1].equals("retruco")) {
								
								texto.setText("<html>"+ cantado[1] +"</html>");
								quiero.setEnabled(true);
								noQuiero.setEnabled(true);
								vale_4.setEnabled(true);
								
								llamadaHilos();
								
								if(botonRendirse == true) {
						        	return 1;
						        }
								
								vale_4.setEnabled(false);
								
								/*SI EL JUGADOR QUIERE*/
								
								if(cantado[2].equals("quiero")) {
									
									vale_4.setEnabled(false);
									trucoQueridoB3();
									
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
									
									MAQUINA.yourTurnAccept(cantado,j);
									
									/*SI LA IA QUIERE*/
									
									if(cantado[3].equals("quiero")) {
										
										texto.setText("<html>"+ cantado[3] +"</html>");
										
										try {
											Thread.sleep(800);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										
										trucoQueridoB3();
										
									}
									
									/*SI LA IA NO QUIERE*/
									
									else {
										
										texto.setText("<html>"+ cantado[3] +"</html>");
										
										try {
											Thread.sleep(800);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										
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
							
							cantarTruco.setEnabled(false);
							
							auxJ = queCartaFueTirada();
							
							tiraIA = null;
							tiraIA = MAQUINA.yourTurn(cantado, auxJ);
							
							/*SI LA IA CANTA TRUCO*/
							
							if(cantado[0].equals("truco")) {
								
								texto.setText("<html>"+ cantado[0] +"</html>");
								quiero.setEnabled(true);
								noQuiero.setEnabled(true);
								truco.setEnabled(true);
								retruco.setEnabled(true);
								mazo.setEnabled(false);
								
								llamadaHilos();
								
								if(botonRendirse == true) {
						        	return 1;
						        }
								
								/*SI EL JUGADOR QUIERE*/
								
								if(cantado[1].equals("quiero")) {
									
									retruco.setEnabled(false);
									trucoQueridoB4();
									
								}
								
								/*SI EL JUGADOR NO QUIERE*/
								
								else if(cantado[1].equals("no quiero")) {
									
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
									
									MAQUINA.yourTurnAccept(cantado,j);
									
									/*SI LA IA QUIERE*/
									
									if(cantado[2].equals("quiero")) {
										
										texto.setText("<html>"+ cantado[2] +"</html>");
										
										try {
											Thread.sleep(800);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										
										trucoQueridoB4();
										
									}
									
									/*SI LA IA NO QUIERE*/
									
									else if(cantado[2].equals("no quiero")) {
										
										texto.setText("<html>"+ cantado[2] +"</html>");
										
										try {
											Thread.sleep(800);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										
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
										
										llamadaHilos();
										
										if(botonRendirse == true) {
								        	return 1;
								        }
										
										/*SI EL JUGADOR QUIERE*/
										
										if(cantado[3].equals("quiero")) {
											
											trucoQueridoB4();
											
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
							
							/*SI LA IA TIRA DIRECTAMENTE*/
							
							else {
								
								auxIA = tiraIA[0];
								mostrarTirada(tiraIA, true);
								
								/*SI LA IA MATA*/
								
								if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
									
									//LA IA GANA
									
									MAQUINA.addPuntos(1);
									acumulador = String.valueOf(MAQUINA.getPuntos());
									pts2.setText(acumulador);
									
									puntosMaximosSuperados();
									//SIGUIENTE RONDA
									
								}
								
								/*SI LA IA NO MATA O EMPARDA*/
								
								else {
									
									//EL JUGADOR GANA
					    			
									j.addPuntos(1);
									acumulador = String.valueOf(j.getPuntos());
									pts1.setText(acumulador);
									
									puntosMaximosSuperados();
					    			//SIGUIENTE RONDA
									
								}
								
							}
							
						}
						
					}
					
					/*SI EL JUGADOR NO MATA O EMPARDA*/
					
					else {
						
						c1.setEnabled(false);
						c2.setEnabled(false);
						c3.setEnabled(false);
						
						//LA IA GANA
						
						MAQUINA.addPuntos(1);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
						
					}
					
				}
				
			}
			
		}
		
		
		
		/*SI LA IA NO MATA*/
		
		else if(aux.returnOrden(auxIA) > aux.returnOrden(auxJ)) {
			
			tiratmp[0] = auxIA;
			mostrarTirada(tiratmp, true);
			
			truco.setEnabled(true);
			cantarTruco.setEnabled(true);
			
			if(c1.getIcon() != null) {
				c1.setEnabled(true);
			}
			if(c2.getIcon() != null) {
				c2.setEnabled(true);
			}
			if(c3.getIcon() != null) {
				c3.setEnabled(true);
			}
			
			llamadaHilos();
			
			if(botonRendirse == true) {
	        	return 1;
	        }
			
			cantarTruco.setEnabled(false);
			
			/*EL JUGADOR PUEDE IRSE AL MAZO*/
			
			if(irseAlMazo == true) {
				
			}
			
			/*EL JUGADOR PUEDE CANTAR TRUCO*/
			
			else if(cantado[0].equals("truco")) {
				
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				mazo.setEnabled(false);
				
				MAQUINA.yourTurnAccept(cantado,j);
				
				/*SI LA IA QUIERE*/
				
				if(cantado[1].equals("quiero")) {
					
					texto.setText("<html>"+ cantado[1] +"</html>");
					
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					trucoQueridoB5();
				}
				
				/*SI LA IA NO QUIERE*/
				
				else if(cantado[1].equals("no quiero")) {
					
					texto.setText("<html>"+ cantado[1] +"</html>");
					
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
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
					
					llamadaHilos();
					
					if(botonRendirse == true) {
			        	return 1;
			        }
					
//					/*SI EL JUGADOR QUIERE*/
					
					if(cantado[2].equals("quiero")) {
						
						vale_4.setEnabled(false);
						trucoQueridoB5();
						
					}
					
					/*SI EL JUGADOR NO QUIERE*/
					
					else if(cantado[2].equals("no quiero")) {
						
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
						
						MAQUINA.yourTurnAccept(cantado,j);
						
						/*SI LA IA QUIERE*/
						
						if(cantado[3].equals("quiero")) {
							
							texto.setText("<html>"+ cantado[3] +"</html>");
							
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							trucoQueridoB5();
							
						}
						
						/*SI LA IA NO QUIERE*/
						
						else {
							
							texto.setText("<html>"+ cantado[3] +"</html>");
							
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
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
				
				auxJ = queCartaFueTirada();
				
				cantarTruco.setEnabled(false);
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				
				tiraIA = null;
				tiraIA = MAQUINA.yourTurn(cantado, auxJ);
				auxIA = tiraIA[0];
				
				/*LA IA CANTA TRUCO*/
				
				if(cantado[0].equals("truco")) {
					
					texto.setText("<html>"+ cantado[0] +"</html>");
					quiero.setEnabled(true);
					noQuiero.setEnabled(true);
					retruco.setEnabled(true);
					mazo.setEnabled(false);
					
					llamadaHilos();
					
					if(botonRendirse == true) {
			        	return 1;
			        }
					
					retruco.setEnabled(false);
					
					/*SI EL JUGADOR QUIERE*/
					
					if(cantado[1].equals("quiero")) {
						
						retruco.setEnabled(false);
						trucoQueridoB6();
						
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
						
						MAQUINA.yourTurnAccept(cantado,j);
						
						/*SI LA IA QUIERE*/
						
						if(cantado[2].equals("quiero")) {
							
							texto.setText("<html>"+ cantado[2] +"</html>");
							
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							trucoQueridoB6();
							
						}
						
						/*SI LA IA NO QUIERE*/
						
						else if(cantado[2].equals("no quiero")) {
							
							texto.setText("<html>"+ cantado[2] +"</html>");
							
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							//EL JUGADOR GANA
			    			
							sistPuntuacion(cantado,MAQUINA,j);
							acumulador = String.valueOf(j.getPuntos());
							pts1.setText(acumulador);
							
							puntosMaximosSuperados();
			    			//SIGUIENTE RONDA
							
						}
						
						/*SI LA IA REVIRA*/
						
						if(cantado[2].equals("vale cuatro")) {
							
							texto.setText("<html>"+ cantado[2] +"</html>");
							quiero.setEnabled(true);
							noQuiero.setEnabled(true);
							
							llamadaHilos();
							
							if(botonRendirse == true) {
					        	return 1;
					        }
							
							/*SI EL JUGADOR QUIERE*/
							
							if(cantado[3].equals("quiero")) {
								
								trucoQueridoB6();
								
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
					
					tiratmp[0] = auxIA;
					mostrarTirada(tiratmp, true);
					
					/*SI LA IA MATA*/
					
					if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
						
						if(tiraIA[1].isGood() == true) {
							
							cantado[0] = "truco";
						}
						
						/*LA IA CANTA TRUCO*/
						
						if(cantado[0].equals("truco")) {
							
							texto.setText("<html>"+ cantado[0] +"</html>");
							truco.setEnabled(true);
							retruco.setEnabled(true);
							quiero.setEnabled(true);
							noQuiero.setEnabled(true);
							mazo.setEnabled(false);
							
							llamadaHilos();
							
							if(botonRendirse == true) {
					        	return 1;
					        }
							
							/*SI EL JUGADOR QUIERE*/
							
							if(cantado[1].equals("quiero")) {
								
								retruco.setEnabled(false);
								trucoQueridoB7();
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
								
								MAQUINA.yourTurnAccept(cantado,j);
								
								/*SI LA IA QUIERE*/
								
								if(cantado[2].equals("quiero")) {
									
									texto.setText("<html>"+ cantado[2] +"</html>");
									
									try {
										Thread.sleep(800);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									
									trucoQueridoB7();
									
								}
								
								/*SI LA IA NO QUIERE*/
								
								else if(cantado[2].equals("no quiero")) {
									
									texto.setText("<html>"+ cantado[2] +"</html>");
									
									try {
										Thread.sleep(800);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									
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
									
									llamadaHilos();
									
									if(botonRendirse == true) {
							        	return 1;
							        }
									
									/*SI EL JUGADOR QUIERE*/
									
									if(cantado[3].equals("quiero")) {
										
										trucoQueridoB7();
										
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
							
							tiratmp[0] = tiraIA[1];
							mostrarTirada(tiratmp, true);
							
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
							cantarTruco.setEnabled(true);
							
							llamadaHilos();
							
							if(botonRendirse == true) {
					        	return 1;
					        }
							
							cantarTruco.setEnabled(false);
							
							/*EL JUGADOR SE VA AL MAZO*/
							
							if(irseAlMazo == true) {
								
							}
							
							/*EL JUGADOR CANTA TRUCO*/
							
							else if(cantado[0].equals("truco")) {
								
								c1.setEnabled(false);
								c2.setEnabled(false);
								c3.setEnabled(false);
								mazo.setEnabled(false);
								
								MAQUINA.yourTurnAccept(cantado,j);
								
								/*SI LA IA QUIERE*/
								
								if(cantado[1].equals("quiero")) {
									
									texto.setText("<html>"+ cantado[1] +"</html>");
									
									try {
										Thread.sleep(800);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									
									trucoQueridoB8();
									
								}
								
								/*SI LA IA NO QUIERE*/
								
								else if(cantado[1].equals("no quiero")) {
									
									texto.setText("<html>"+ cantado[1] +"</html>");
									
									try {
										Thread.sleep(800);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									
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
									vale_4.setEnabled(true);
									quiero.setEnabled(true);
									noQuiero.setEnabled(true);
									
									llamadaHilos();
									
									if(botonRendirse == true) {
							        	return 1;
							        }
									
									/*SI EL JUGADOR QUIERE*/
									
									if(cantado[2].equals("quiero")) {
										
										vale_4.setEnabled(false);
										trucoQueridoB8();
										
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
										
										MAQUINA.yourTurnAccept(cantado,j);
										
										/*SI LA IA QUIERE*/
										
										if(cantado[3].equals("quiero")) {
											
											texto.setText("<html>"+ cantado[3] +"</html>");
											
											try {
												Thread.sleep(800);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
											
											trucoQueridoB8();
											
										}
										
										/*SI LA IA NO QUIERE*/
										
										else {
											
											texto.setText("<html>"+ cantado[3] +"</html>");
											
											try {
												Thread.sleep(800);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
											
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
								
								auxJ = queCartaFueTirada();
								auxIA = tiraIA[1];
								
								/*SI EL JUGADOR MATA O EMPARDA*/
								
								if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
									
									//EL JUGADOR GANA
					    			
									j.addPuntos(1);
									acumulador = String.valueOf(j.getPuntos());
									pts1.setText(acumulador);
									
									puntosMaximosSuperados();
					    			//SIGUIENTE RONDA
																		
								}
								
								/*SI EL JUGADOR NO MATA*/
								
								else {
									
									//LA IA GANA
									
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
						
						//EL JUGADOR GANA
		    			
						j.addPuntos(1);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
		    			//SIGUIENTE RONDA
						
					}
					
				}
				
			}
			
		}
		
		
		
		/*SI LA IA EMPARDA*/
				
		else {
			
			auxIA = tiraIA[0];
			mostrarTirada(tiraIA, true);
			
			truco.setEnabled(true);
			cantarTruco.setEnabled(true);
			
			if(c1.getIcon() != null) {
				c1.setEnabled(true);
			}
			if(c2.getIcon() != null) {
				c2.setEnabled(true);
			}
			if(c3.getIcon() != null) {
				c3.setEnabled(true);
			}
			
			llamadaHilos();
			
			if(botonRendirse == true) {
	        	return 1;
	        }
			
			cantarTruco.setEnabled(false);
			
			/*EL JUGADOR SE VA AL MAZO*/
			
			if(irseAlMazo == true) {
				
			}
			
			/*EL JUGADOR CANTA TRUCO*/
			
			else if(cantado[0].equals("truco")) {
				
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				mazo.setEnabled(false);
				
				MAQUINA.yourTurnAccept(cantado,j);
				
				/*SI LA IA QUIERE*/
				
				if(cantado[1].equals("quiero")) {
					
					texto.setText("<html>"+ cantado[1] +"</html>");
					
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					trucoQueridoB9();
					
				}
				
				/*SI LA IA NO QUIERE*/
				
				else if(cantado[1].equals("no quiero")) {
					
					texto.setText("<html>"+ cantado[1] +"</html>");
					
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
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
					vale_4.setEnabled(true);
					quiero.setEnabled(true);
					noQuiero.setEnabled(true);
					
					llamadaHilos();
					
					if(botonRendirse == true) {
			        	return 1;
			        }
					
					/*SI EL JUGADOR QUIERE*/
					
					if(cantado[2].equals("quiero")) {
						
						trucoQueridoB9();
						
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
						
						vale_4.setEnabled(false);
						quiero.setEnabled(false);
						noQuiero.setEnabled(false);
						
						MAQUINA.yourTurnAccept(cantado,j);
						
						/*SI LA IA QUIERE*/
						
						if(cantado[3].equals("quiero")) {
							
							texto.setText("<html>"+ cantado[3] +"</html>");
							
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							trucoQueridoB9();
						}
						
						/*SI LA IA NO QUIERE*/
						
						else {
							
							texto.setText("<html>"+ cantado[3] +"</html>");
							
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
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
				
				auxJ = queCartaFueTirada();
				
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				
				tiraIA = null;
				tiraIA = MAQUINA.yourTurn(cantado, auxJ);
				
				/*LA IA CANTA TRUCO*/
				
				if(cantado[0].equals("truco")) {
					
					texto.setText("<html>"+ cantado[0] +"</html>");
					retruco.setEnabled(true);
					quiero.setEnabled(true);
					noQuiero.setEnabled(true);
					mazo.setEnabled(false);
					
					llamadaHilos();
					
					if(botonRendirse == true) {
			        	return 1;
			        }
					
					/*SI EL JUGADOR QUIERE*/
					
					if(cantado[1].equals("quiero")) {
						
						trucoQueridoB10();
						
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
						
						retruco.setEnabled(false);
						quiero.setEnabled(false);
						noQuiero.setEnabled(false);
						
						MAQUINA.yourTurnAccept(cantado,j);
						
						/*SI LA IA QUIERE*/
						
						if(cantado[2].equals("quiero")) {
							
							texto.setText("<html>"+ cantado[2] +"</html>");
							
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							trucoQueridoB10();
							
						}
						
						/*SI LA IA NO QUIERE*/
						
						else if(cantado[2].equals("no quiero")) {
							
							texto.setText("<html>"+ cantado[2] +"</html>");
							
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
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
							
							llamadaHilos();
							
							if(botonRendirse == true) {
					        	return 1;
					        }
							
							/*SI EL JUGADOR QUIERE*/
							
							if(cantado[3].equals("quiero")) {
								
								trucoQueridoB10();
								
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
					
					auxIA = tiraIA[0];
					
					/*SI LA IA MATA*/
					
					if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
						
						tiratmp[0] = tiraIA[0];
						mostrarTirada(tiratmp, true);
						
						//LA IA GANA
						
						MAQUINA.addPuntos(1);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
						
					}
					
					/*SI LA IA NO MATA*/
					
					else if(aux.returnOrden(auxIA) > aux.returnOrden(auxJ)) {
						
						tiratmp[0] = tiraIA[0];
						mostrarTirada(tiratmp, true);
						
						//EL JUGADOR GANA
		    			
						j.addPuntos(1);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
		    			//SIGUIENTE RONDA
						
					}
					
					/*SI LA IA EMPARDA*/
					
					else {
						
						tiratmp[0] = tiraIA[0];
						mostrarTirada(tiratmp, true);
						
						if(c1.getIcon() != null) {
							c1.setEnabled(true);
						}
						if(c2.getIcon() != null) {
							c2.setEnabled(true);
						}
						if(c3.getIcon() != null) {
							c3.setEnabled(true);
						}
						
						cantarTruco.setEnabled(true);
						
						llamadaHilos();
						
						if(botonRendirse == true) {
				        	return 1;
				        }
						
						cantarTruco.setEnabled(false);
						c1.setEnabled(false);
						c2.setEnabled(false);
						c3.setEnabled(false);
						
						/*EL JUGADOR SE VA AL MAZO*/
						
						if(irseAlMazo == true) {
							
						}
						
						/*EL JUGADOR CANTA TRUCO*/
						
						else if(cantado[0].equals("truco")) {
							
							mazo.setEnabled(false);
							
							MAQUINA.yourTurnAccept(cantado,j);
							
							/*SI LA IA QUIERE*/
							
							if(cantado[1].equals("quiero")) {
								
								texto.setText("<html>"+ cantado[1] +"</html>");
								
								try {
									Thread.sleep(800);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								
								trucoQueridoB11();
								
							}
							
							/*SI LA IA NO QUIERE*/
							
							else if(cantado[1].equals("no quiero")) {
								
								texto.setText("<html>"+ cantado[1] +"</html>");
								
								try {
									Thread.sleep(800);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								
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
								vale_4.setEnabled(true);
								quiero.setEnabled(true);
								noQuiero.setEnabled(true);
								
								llamadaHilos();
								
								if(botonRendirse == true) {
						        	return 1;
						        }
								
								/*SI EL JUGADOR QUIERE*/
								
								if(cantado[2].equals("quiero")) {
									
									trucoQueridoB11();
									
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
									
									vale_4.setEnabled(false);
									quiero.setEnabled(false);
									noQuiero.setEnabled(false);
									
									MAQUINA.yourTurnAccept(cantado,j);
									
									/*SI LA IA QUIERE*/
									
									if(cantado[3].equals("quiero")) {
										
										texto.setText("<html>"+ cantado[3] +"</html>");
										
										try {
											Thread.sleep(800);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										
										trucoQueridoB11();
									}
									
									/*SI LA IA NO QUIERE*/
									
									else {
										
										texto.setText("<html>"+ cantado[3] +"</html>");
										
										try {
											Thread.sleep(800);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										
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
							
							cantarTruco.setEnabled(false);
							
							auxJ = queCartaFueTirada();
							
							tiraIA = null;
							tiraIA = MAQUINA.yourTurn(cantado, auxJ);
							auxIA = tiraIA[0];
							
							/*SI LA IA CANTA TRUCO*/
							
							if(cantado[0].equals("truco")) {
								
								texto.setText("<html>"+ cantado[0] +"</html>");
								retruco.setEnabled(true);
								quiero.setEnabled(true);
								noQuiero.setEnabled(true);
								mazo.setEnabled(false);
								
								llamadaHilos();
								
								if(botonRendirse == true) {
						        	return 1;
						        }
								
								/*SI EL JUGADOR QUIERE*/
								
								if(cantado[1].equals("quiero")) {
									
									trucoQueridoB12();
									
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
									
									retruco.setEnabled(false);
									quiero.setEnabled(false);
									noQuiero.setEnabled(false);
									
									MAQUINA.yourTurnAccept(cantado,j);
									
									/*SI LA IA QUIERE*/
									
									if(cantado[2].equals("quiero")) {
										
										texto.setText("<html>"+ cantado[2] +"</html>");
										
										try {
											Thread.sleep(800);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										
										trucoQueridoB12();
									}
									
									/*SI LA IA NO QUIERE*/
									
									else if(cantado[2].equals("no quiero")) {
										
										texto.setText("<html>"+ cantado[2] +"</html>");
										
										try {
											Thread.sleep(800);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										
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
										
										llamadaHilos();
										
										if(botonRendirse == true) {
								        	return 1;
								        }
										
										/*SI EL JUGADOR QUIERE*/
										
										if(cantado[3].equals("quiero")) {
											
											trucoQueridoB12();
											
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
							
							/*SI LA IA TIRA DIRECTAMENTE*/
							
							else {
								
								mostrarTirada(tiraIA, true);
								
								/*SI LA IA MATA*/
								
								if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
									
									//LA IA GANA
									
									MAQUINA.addPuntos(1);
									acumulador = String.valueOf(MAQUINA.getPuntos());
									pts2.setText(acumulador);
									
									puntosMaximosSuperados();
									//SIGUIENTE RONDA
									
								}
								
								/*SI LA IA NO MATA O EMPARDA*/
								
								else {
								
									//EL JUGADOR GANA
					    			
									j.addPuntos(1);
									acumulador = String.valueOf(j.getPuntos());
									pts1.setText(acumulador);
									
									puntosMaximosSuperados();
					    			//SIGUIENTE RONDA
									
								}
								
							}
							
							
						}
						
					}
					
				}
			}
			
			
		}
		
		return 0;
		
	}
	
	
	//FUNCION TRUCO
	
	public int truco() {
		
		flecha2.setEnabled(false);
		
		cantarTruco.setEnabled(false);
		retruco.setEnabled(false);
		vale_4.setEnabled(false);
		
		c1.setEnabled(false);
		c2.setEnabled(false);
		c3.setEnabled(false);
		
		quiero.setEnabled(false);
		noQuiero.setEnabled(false);
		
		MAQUINA.yourTurnAccept(cantado,j);
		
		/*SI LA IA QUIERE*/
		
		if(cantado[1].equals("quiero")) {
			
			texto.setText("<html>"+ cantado[1] +"</html>");
			
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//TENER EN CUENTA VARIABLES COMO CANTADO[] / LAS AUX Y OTRAS (EN RELACION A SU VALOR)
			if(ronda%2==0) {
				
				trucoQueridoRondasPares();
				
			}
			else {
				trucoQuerido();
			}
			
		}
		
		/*SI LA IA REVIRA*/
		
		else if(cantado[1].equals("retruco")){

			texto.setText("<html>"+ cantado[1] +"</html>");
			quiero.setEnabled(true);
			noQuiero.setEnabled(true);
			
			vale_4.setEnabled(true);
			
			llamadaHilos();
			
			if(botonRendirse == true) {
	        	return 1;
	        }
			
    		/*SI EL JUGADOR QUIERE*/
    		
    		if(cantado[2].equals("quiero")) {
    			
    			vale_4.setEnabled(false);
    			
    			if(ronda%2==0) {
    				
    				trucoQueridoRondasPares();
    				
    			}
    			else {
    				trucoQuerido();
    			}
    		}
    		
    		/*SI EL JUGADOR REVIRA*/
    		
    		else if(cantado[2].equals("vale cuatro")) {
    			
				quiero.setEnabled(false);
				noQuiero.setEnabled(false);
				
				vale_4.setEnabled(false);
				
	    		c1.setEnabled(false);
	    		c2.setEnabled(false);
	    		c3.setEnabled(false);
    			
    			MAQUINA.yourTurnAccept(cantado,j);
    			
    			/*SI LA IA QUIERE*/
    			
    			if(cantado[3].equals("quiero")) {
    				
					texto.setText("<html>"+ cantado[3] +"</html>");
					
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
    					
					if(ronda%2==0) {
						
						trucoQueridoRondasPares();
						
					}
					else {
						trucoQuerido();
					}
    			}
    			
    			/*SI LA IA NO QUIERE*/
    			
    			else {
					
					texto.setText("<html>"+ cantado[3] +"</html>");
					
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    					
    				
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
			
			texto.setText("<html>"+ cantado[1] +"</html>");
			
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				
			//EL JUGADOR GANA

			sistPuntuacion(cantado,j,MAQUINA);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
			
		}
		
		return 0;
		
	}
	
	
	//FUNCION TIRAR DIRECTAMENTE
	
	public int tirarDir() {
		
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
					mazo.setEnabled(false);
										
					llamadaHilos();
				
					if(botonRendirse == true) {
			        	return 1;
			        }
					
				/*SI EL JUGADOR QUIERE*/
				
				if(cantado[1].equals("quiero")) {		//BLOQUE 1
					
					retruco.setEnabled(false);
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
					
					quiero.setEnabled(false);
					noQuiero.setEnabled(false);
					retruco.setEnabled(false);
					
					MAQUINA.yourTurnAccept(cantado,j);
					
					/* SI LA IA QUIERE*/
					
					if(cantado[2].equals("quiero")) {	//BLOQUE 1
						
						texto.setText("<html>"+ cantado[2] +"</html>");
						
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						trucoQueridoBloque1();
						
					}
					
					/*SI LA IA NO QUIERE*/
					
					else if(cantado[2].equals("no quiero")) {
						
						texto.setText("<html>"+ cantado[2] +"</html>");
						
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
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
			    		
						llamadaHilos();
						
						if(botonRendirse == true) {
				        	return 1;
				        }
						
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
				
				llamadaHilos();
    			
				if(botonRendirse == true) {
		        	return 1;
		        }
				
	    		/*EL JUGADOR PUEDE IRSE AL MAZO*/
	    		
	    		if(irseAlMazo == true) {
	    			
	    		}
	    		
				/*EL JUGADOR PUEDE CANTAR TRUCO*/
				
	    		else if(cantado[0].equals("truco")) {
					
					flecha2.setEnabled(false);
					cantarTruco.setEnabled(false);
					mazo.setEnabled(false);
					
					c1.setEnabled(false);
		    		c2.setEnabled(false);
		    		c3.setEnabled(false);
					
					MAQUINA.yourTurnAccept(cantado,j);
					
					/*SI LA IA QUIERE*/
					
					if(cantado[1].equals("quiero")) {		//BLOQUE 2
						
						texto.setText("<html>"+ cantado[1] +"</html>");
						
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						trucoQueridoBloque2();
						
					}
					
					/*SI LA IA NO QUIERE*/
					
					else if(cantado[1].equals("no quiero")) {
						
						texto.setText("<html>"+ cantado[1] +"</html>");
						
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
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

						llamadaHilos();
						
						if(botonRendirse == true) {
				        	return 1;
				        }
						
						/*SI EL JUGADOR QUIERE*/
						
						if(cantado[2].equals("quiero")) {		//BLOQUE 2
							
							vale_4.setEnabled(false);
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
							
							MAQUINA.yourTurnAccept(cantado,j);
							
							/*SI LA IA QUIERE*/
							
							if(cantado[3].equals("quiero")) {		//BLOQUE 2
								
    							texto.setText("<html>"+ cantado[3] +"</html>");
    							
    							try {
    								Thread.sleep(800);
    							} catch (InterruptedException e) {
    								e.printStackTrace();
    							}
    							
								trucoQueridoBloque2();
								
							}
							
							/*SI LA IA NO QUIERE*/
							
							else {
								
    							texto.setText("<html>"+ cantado[3] +"</html>");
    							
    							try {
    								Thread.sleep(800);
    							} catch (InterruptedException e) {
    								e.printStackTrace();
    							}
    							
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
						
						llamadaHilos();
						
						if(botonRendirse == true) {
				        	return 1;
				        }
						
	    	    		/*EL JUGADOR PUEDE IRSE AL MAZO*/
	    	    		
	    	    		if(irseAlMazo == true) {
	    	    			
	    	    		}
	    	    		
						/*EL JUGADOR PUEDE CANTAR TRUCO*/
						
	    	    		else if(cantado[0].equals("truco")) {
	    	    			
							flecha2.setEnabled(false);
							cantarTruco.setEnabled(false);
							mazo.setEnabled(false);
							
				    		c1.setEnabled(false);
				    		c2.setEnabled(false);
				    		c3.setEnabled(false);
							
							MAQUINA.yourTurnAccept(cantado,j);
							
							/*SI LA IA QUIERE*/
							
							if(cantado[1].equals("quiero")) {			//BLOQUE 3
								
    							texto.setText("<html>"+ cantado[1] +"</html>");
    							
    							try {
    								Thread.sleep(800);
    							} catch (InterruptedException e) {
    								e.printStackTrace();
    							}
    							
								trucoQueridoBloque3();
								
							}
							
							/*SI LA IA NO QUIERE*/
							
							else if(cantado[1].equals("no quiero")) {
								
    							texto.setText("<html>"+ cantado[1] +"</html>");
    							
    							try {
    								Thread.sleep(800);
    							} catch (InterruptedException e) {
    								e.printStackTrace();
    							}
    							
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
	    						
	    						llamadaHilos();
								
	    						if(botonRendirse == true) {
	    				        	return 1;
	    				        }
	    						
								/*SI EL JUGADOR QUIERE*/
								
								if(cantado[2].equals("quiero")) {		//BLOQUE 3
									
									vale_4.setEnabled(false);
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
									
									MAQUINA.yourTurnAccept(cantado,j);
									
									/*SI LA IA QUIERE*/
					
									if(cantado[3].equals("quiero")) {		//BLOQUE 3
										
		    							texto.setText("<html>"+ cantado[3] +"</html>");
		    							
		    							try {
		    								Thread.sleep(800);
		    							} catch (InterruptedException e) {
		    								e.printStackTrace();
		    							}
		    							
										trucoQueridoBloque3();
										
									}
									
									/*SI LA IA NO QUIERE*/
									
									else {
										
		    							texto.setText("<html>"+ cantado[3] +"</html>");
		    							
		    							try {
		    								Thread.sleep(800);
		    							} catch (InterruptedException e) {
		    								e.printStackTrace();
		    							}
		    							
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
							
							cantarTruco.setEnabled(false);
							
							auxJ = queCartaFueTirada();
							
							tiraIA = null;
							tiraIA = MAQUINA.yourTurn(cantado, null);
							
							/*LA IA PUEDE CANTAR TRUCO*/
							
							if(cantado[0].equals("truco")) {
								
								texto.setText("<html>"+ cantado[0] +"</html>");
	    						quiero.setEnabled(true);
	    						noQuiero.setEnabled(true);
	    						mazo.setEnabled(false);
	    						
	    						truco.setEnabled(true);
	    						flecha2.setEnabled(false);
	    						retruco.setEnabled(true);
	    						
					    		c1.setEnabled(false);
					    		c2.setEnabled(false);
					    		c3.setEnabled(false);
								
					    		llamadaHilos();
								
					    		if(botonRendirse == true) {
						        	return 1;
						        }
					    		
								/*SI EL JUGADOR QUIERE*/
								
								if(cantado[1].equals("quiero")) {	//BLOQUE 4
									
									retruco.setEnabled(false);
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
									
									MAQUINA.yourTurnAccept(cantado,j);
									
									/*SI LA IA QUIERE*/
									
									if(cantado[2].equals("quiero")) {	//BLOQUE 4
										
		    							texto.setText("<html>"+ cantado[2] +"</html>");
		    							
		    							try {
		    								Thread.sleep(800);
		    							} catch (InterruptedException e) {
		    								e.printStackTrace();
		    							}
		    							
										trucoQueridoBloque4();
										
									}
									
									/*SI LA IA NO QUIERE*/
									
									else if(cantado[2].equals("no quiero")) {
										
		    							texto.setText("<html>"+ cantado[2] +"</html>");
		    							
		    							try {
		    								Thread.sleep(800);
		    							} catch (InterruptedException e) {
		    								e.printStackTrace();
		    							}
		    							
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

			    						llamadaHilos();
										
			    						if(botonRendirse == true) {
			    				        	return 1;
			    				        }
			    						
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
								
								tiraIAnull = true;
								mostrarTirada(tiraIA,tiraIAnull);
								auxIA = tiraIA[0];
								
								/*SI LA IA MATA*/
								
								if(aux.returnOrden(auxIA) <= aux.returnOrden(auxJ)) {
									//LA IA GANA

									//sistPuntuacion(cantado,MAQUINA,j);
									MAQUINA.addPuntos(1);
									acumulador = String.valueOf(MAQUINA.getPuntos());
									pts2.setText(acumulador);
									
									puntosMaximosSuperados();
									//SIGUIENTE RONDA
								}
								
								/*SI LA IA NO MATA*/
								
								else {
									//EL JUGADOR GANA

									//sistPuntuacion(cantado,MAQUINA,j);
									j.addPuntos(1);
									acumulador = String.valueOf(j.getPuntos());
									pts1.setText(acumulador);
									
									puntosMaximosSuperados();
									//SIGUIENTE RONDA
								}
								
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
				mazo.setEnabled(false);
				
				truco.setEnabled(true);
				retruco.setEnabled(true);
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
				/*EL JUGADOR QUIERE*/
				
				if(cantado[1].equals("quiero")) {				//BLOQUE 5
					
					retruco.setEnabled(false);
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
					
					quiero.setEnabled(false);
					noQuiero.setEnabled(false);
					retruco.setEnabled(false);
					
					MAQUINA.yourTurnAccept(cantado,j);
					
					/*SI LA IA QUIERE*/
					
					if(cantado[2].equals("quiero")) {
						
						texto.setText("<html>"+ cantado[2] +"</html>");
						
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						trucoQueridoBloque5();							//BLOQUE 5
						
					}
					
					/*SI LA IA NO QUIERE*/
					
					else if(cantado[2].equals("no quiero")) {
						
						texto.setText("<html>"+ cantado[2] +"</html>");
						
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
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

						llamadaHilos();
											
						if(botonRendirse == true) {
				        	return 1;
				        }
						
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
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
	    		/*EL JUGADOR SE VA AL MAZO*/
	    		
	    		if(irseAlMazo == true) {
	    			
	    		}
	    		
				/*EL JUGADOR CANTA TRUCO*/
				
	    		else if(cantado[0].equals("truco")) {
					
					flecha2.setEnabled(false);
					cantarTruco.setEnabled(false);
					mazo.setEnabled(false);
					
					c1.setEnabled(false);
		    		c2.setEnabled(false);
		    		c3.setEnabled(false);
					
					MAQUINA.yourTurnAccept(cantado,j);
					
					/*SI LA IA QUIERE*/
					
					if(cantado[1].equals("quiero")) {				//BLOQUE 6
						
						texto.setText("<html>"+ cantado[1] +"</html>");
						
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						trucoQueridoBloque6();
			    		
					}
					
					/*SI LA IA NO QUIERE*/
					
					else if(cantado[1].equals("no quiero")) {
						
						texto.setText("<html>"+ cantado[1] +"</html>");
						
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
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
						
						llamadaHilos();
						
						if(botonRendirse == true) {
				        	return 1;
				        }
						
						/*SI EL JUGADOR QUIERE*/
						
						if(cantado[2].equals("quiero")) {
							
							vale_4.setEnabled(false);
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
							
							MAQUINA.yourTurnAccept(cantado, j);
							
							/*SI LA IA QUIERE*/
							
							if(cantado[3].equals("quiero")) {
								
    							texto.setText("<html>"+ cantado[3] +"</html>");
    							
    							try {
    								Thread.sleep(800);
    							} catch (InterruptedException e) {
    								e.printStackTrace();
    							}
    							
								trucoQueridoBloque6();							//BLOQUE 6
								
							}
							
							/*SI LA IA NO QUIERE*/
							
							else if(cantado[3].equals("no quiero")) {
								
    							texto.setText("<html>"+ cantado[3] +"</html>");
    							
    							try {
    								Thread.sleep(800);
    							} catch (InterruptedException e) {
    								e.printStackTrace();
    							}
    							
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
    						mazo.setEnabled(false);
    						
    						truco.setEnabled(true);
    						retruco.setEnabled(true);
    						
    						llamadaHilos();
	    					
    						if(botonRendirse == true) {
    				        	return 1;
    				        }
    						
		    				/*SI EL JUGADOR QUIERE*/
		    				
		    				if(cantado[1].equals("quiero")) {			//BLOQUE 7
		    					
		    					retruco.setEnabled(false);
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
		    					
		    					MAQUINA.yourTurnAccept(cantado,j);
		    					
		    					/*SI LA IA QUIERE*/
		    					
		    					if(cantado[2].equals("quiero")) {
		    						
	    							texto.setText("<html>"+ cantado[2] +"</html>");
	    							
	    							try {
	    								Thread.sleep(800);
	    							} catch (InterruptedException e) {
	    								e.printStackTrace();
	    							}
	    							
		    						trucoQueridoBloque7();									//BLOQUE 7
		    						
		    					}
		    					
		    					/*SI LA IA NO QUIERE*/
		    					
		    					else if(cantado[2].equals("no quiero")) {
		    						
	    							texto.setText("<html>"+ cantado[2] +"</html>");
	    							
	    							try {
	    								Thread.sleep(800);
	    							} catch (InterruptedException e) {
	    								e.printStackTrace();
	    							}
	    							
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
		    						
		    						llamadaHilos();
		    						
		    						if(botonRendirse == true) {
		    				        	return 1;
		    				        }
		    						
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
	    					
	    					llamadaHilos();
	    					
	    					if(botonRendirse == true) {
	    			        	return 1;
	    			        }
	    					
	    					/*SI EL JUGADOR SE VA AL MAZO*/
	    					
	    					if(irseAlMazo == true) {
	    						
	    					}
	    					
	    					/*SI EL JUGADOR CANTA TRUCO*/
	    					
	    					else if(cantado[0].equals("truco")) {
	    						
	    						cantarTruco.setEnabled(false);
	    						flecha2.setEnabled(false);
	    						mazo.setEnabled(false);
	    						
	    						c1.setEnabled(false);
	    						c2.setEnabled(false);
	    						c3.setEnabled(false);
	    						
	    						MAQUINA.yourTurnAccept(cantado,j);
	    						
	    						/*SI LA IA QUIERE*/
	    						
	    						if(cantado[1].equals("quiero")) {			//BLOQUE 8
	    							
	    							texto.setText("<html>"+ cantado[1] +"</html>");
	    							
	    							try {
	    								Thread.sleep(800);
	    							} catch (InterruptedException e) {
	    								e.printStackTrace();
	    							}
	    							
	    							trucoQueridoBloque8();
			    					
	    						}
	    						
	    						/*SI LA IA NO QUIERE*/
	    						
	    						else if(cantado[1].equals("no quiero")) {
	    							
	    							texto.setText("<html>"+ cantado[1] +"</html>");
	    							
	    							try {
	    								Thread.sleep(800);
	    							} catch (InterruptedException e) {
	    								e.printStackTrace();
	    							}
	    							
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
		    						
		    						llamadaHilos();
	    							
		    						if(botonRendirse == true) {
		    				        	return 1;
		    				        }
		    						
			    					/*SI EL JUGADOR QUIERE*/
			    					
			    					if(cantado[2].equals("quiero")) {
			    						
			    						vale_4.setEnabled(false);
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
			    						
			    						MAQUINA.yourTurnAccept(cantado,j);
			    						
			    						/*SI LA IA QUIERE*/
			    						
			    						if(cantado[3].equals("quiero")) {
			    							
			    							texto.setText("<html>"+ cantado[3] +"</html>");
			    							
			    							try {
			    								Thread.sleep(800);
			    							} catch (InterruptedException e) {
			    								e.printStackTrace();
			    							}
			    							
			    							trucoQueridoBloque8();					//BLOQUE 8
			    							
			    						}
			    						
			    						/*SI LA IA NO QUIERE*/
			    						
			    						else {
			    							
			    							texto.setText("<html>"+ cantado[3] +"</html>");
			    							
			    							try {
			    								Thread.sleep(800);
			    							} catch (InterruptedException e) {
			    								e.printStackTrace();
			    							}
			    							
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
			
			llamadaHilos();
			
			if(botonRendirse == true) {
	        	return 1;
	        }
			
			/*EL JUGADOR SE VA AL MAZO*/
			
			if(irseAlMazo == true) {
				
			}
			
			/*EL JUGADOR CANTA TRUCO*/
			
			else if(cantado[0].equals("truco")) {
				
				truco.setEnabled(false);
				flecha2.setEnabled(false);
				cantarTruco.setEnabled(false);
				mazo.setEnabled(false);
				
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				
				MAQUINA.yourTurnAccept(cantado,j);
				
				/*SI LA IA QUIERE*/
				
				if(cantado[1].equals("quiero")) {					//BLOQUE 12
					
					texto.setText("<html>"+ cantado[1] +"</html>");
					
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					trucoQueridoBloque12();
					
				}
				
				/*SI LA IA NO QUIERE*/
				
				else if(cantado[1].equals("no quiero")) {
					
					texto.setText("<html>"+ cantado[1] +"</html>");
					
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
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
					
					llamadaHilos();
					
					if(botonRendirse == true) {
			        	return 1;
			        }
					
					/*SI EL JUGADOR QUIERE*/
					
					if(cantado[2].equals("quiero")) {					//BLOQUE 12
						
						vale_4.setEnabled(false);
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
						
						MAQUINA.yourTurnAccept(cantado,j);
						
						/*SI LA IA QUIERE*/
						
						if(cantado[3].equals("quiero")) {					//BLOQUE 12
							
							texto.setText("<html>"+ cantado[3] +"</html>");
							
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							trucoQueridoBloque12();
							
						}
						
						/*SI LA IA NO QUIERE*/
						
						else {
							
							texto.setText("<html>"+ cantado[3] +"</html>");
							
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
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
					mazo.setEnabled(false);
					
					truco.setEnabled(true);
					flecha2.setEnabled(false);
					retruco.setEnabled(true);
					
					llamadaHilos();
					
					if(botonRendirse == true) {
			        	return 1;
			        }
					
					/*SI EL JUGADOR QUIERE*/
					
					if(cantado[1].equals("quiero")) {			//BLOQUE 9
						
						retruco.setEnabled(false);
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
						
						MAQUINA.yourTurnAccept(cantado,j);
						
						/*SI LA IA QUIERE*/
						
						if(cantado[2].equals("quiero")) {		//BLOQUE 9
							
							texto.setText("<html>"+ cantado[2] +"</html>");
							
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							trucoQueridoBloque9();
							
						}
						
						/*SI LA IA NO QUIERE*/
						
						else if(cantado[2].equals("no quiero")) {
							
							texto.setText("<html>"+ cantado[2] +"</html>");
							
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
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
	
							llamadaHilos();
							
							if(botonRendirse == true) {
					        	return 1;
					        }
							
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
						
						cantado[1] = "";
						
						/*LA IA CANTA TRUCO*/
						
						if(cantado[0].equals("truco")) {
							
							texto.setText("<html>"+ cantado[0] +"</html>");
							quiero.setEnabled(true);
							noQuiero.setEnabled(true);
							mazo.setEnabled(false);
							
							truco.setEnabled(true);
							flecha2.setEnabled(true);
							retruco.setEnabled(true);
							
							llamadaHilos();
							
							if(botonRendirse == true) {
					        	return 1;
					        }
							
							/*SI EL JUGADOR QUIERE*/
							
							if(cantado[1].equals("quiero")) {							//BLOQUE 11
								
								retruco.setEnabled(false);
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
								
								MAQUINA.yourTurnAccept(cantado,j);
								
								/*SI LA IA QUIERE*/
								
								if(cantado[2].equals("quiero")) {				//BLOQUE 11
									
									texto.setText("<html>"+ cantado[2] +"</html>");
									
									try {
										Thread.sleep(800);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									
									trucoQueridoBloque11();
									
								}
								
								/*SI LA IA NO QUIERE*/
								
								else if(cantado[2].equals("no quiero")) {
									
									texto.setText("<html>"+ cantado[2] +"</html>");
									
									try {
										Thread.sleep(800);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									
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

									llamadaHilos();
									
									if(botonRendirse == true) {
							        	return 1;
							        }
									
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
							
							llamadaHilos();
							
							if(botonRendirse == true) {
					        	return 1;
					        }
							
							/*EL JUGADOR PUEDE IRSE AL MAZO*/
							
							if(irseAlMazo == true) {
								
							}
							
							/*EL JUGADOR PUEDE CANTAR TRUCO*/
							
							else if(cantado[0].equals("truco")) {
								
								flecha2.setEnabled(false);
								cantarTruco.setEnabled(false);
								mazo.setEnabled(false);
								
					    		c1.setEnabled(false);
					    		c2.setEnabled(false);
					    		c3.setEnabled(false);
								
					    		cantado[1] = "";		//SE FUERZA A QUE SOLO HAYA UN CANTO DE TRUCO, YA QUE LO DUPLICA
					    		
								MAQUINA.yourTurnAccept(cantado,j);
								
								/*SI LA IA QUIERE*/
								
								if(cantado[1].equals("quiero")) {					//BLOQUE 10
									
									texto.setText("<html>"+ cantado[1] +"</html>");
									
									try {
										Thread.sleep(800);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									
									trucoQueridoBloque10();
								}
								
								/*SI LA IA NO QUIERE*/
								
								else if(cantado[1].equals("no quiero")) {
									
									texto.setText("<html>"+ cantado[1] +"</html>");
									
									try {
										Thread.sleep(800);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									
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
									
									llamadaHilos();
									
									if(botonRendirse == true) {
							        	return 1;
							        }
									
				    	    		/*SI EL JUGADOR QUIERE*/
				    	    		
				    	    		if(cantado[2].equals("quiero")) {							//BLOQUE 10
				    	    			
				    	    			vale_4.setEnabled(false);
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
				    	    			
				    	    			MAQUINA.yourTurnAccept(cantado,j);
				    	    			
				    	    			/*SI LA IA QUIERE*/
				    	    			
				    	    			if(cantado[3].equals("quiero")) {									//BLOQUE 10
				    	    				
				    	    				texto.setText("<html>"+ cantado[3] +"</html>");
				    	    				
				    	    				try {
				    	    					Thread.sleep(800);
				    	    				} catch (InterruptedException e) {
				    	    					e.printStackTrace();
				    	    				}
				    	    				
				    	    				trucoQueridoBloque10();
				    	    				
				    	    			}
				    	    			
				    	    			/*SI LA IA NO QUIERE*/
				    	    			
				    	    			else {
				    	    				
				    	    				texto.setText("<html>"+ cantado[3] +"</html>");
				    	    				
				    	    				try {
				    	    					Thread.sleep(800);
				    	    				} catch (InterruptedException e) {
				    	    					e.printStackTrace();
				    	    				}
											
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
						
						//EL JUGADOR GANA
	
						j.addPuntos(1);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
						
					}
					
					
				}
			
			}
			
													    			
		}
		
		return 0;
		
	}
	
	
	//FUNCION PARA SABER QUE CARTA TIRO EL JUGADOR CUANDO APRETO UN BOTON
	
	public Carta queCartaFueTirada() {		
		
		//CUANDO YA RETORNO UNA CARTA, ENTONCES CUALQUIER 'C' DEBE ANULARSE, PORQUE YA NO EXISTE MAS NADA AH�
		
		if(c1.getIcon()==null && c1.isEnabled() != false) {
			c1.setEnabled(false);
			return j.cartas[0];
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
		} else if(l6.getIcon() == null){
			l6.setIcon(c.getIcon());
		}
		
		c.setIcon(null);
		
	}
	
	
	
	
	public void trucoQueridoB12() {
		
		mostrarTirada(tiraIA, true);
		
		/*SI LA IA MATA*/
		
		if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
			
			//LA IA GANA
			MAQUINA.truco = true;
			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
			
		}
		
		/*SI LA IA NO MATA O EMPARDA*/
		
		else {
			
			//EL JUGADOR GANA
			j.truco = true;
			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
			
		}
		
	}	
	
	
	public int trucoQueridoB11() {
		
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
		
			auxJ = queCartaFueTirada();
			
			tiraIA = null;
			tiraIA = MAQUINA.yourTurn(cantado, auxJ);
			auxIA = tiraIA[0];
			mostrarTirada(tiraIA, true);
			
			/*SI LA IA MATA*/
			
			if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
				
				//LA IA GANA
				MAQUINA.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
				
			}
			
			/*SI LA IA NO MATA O EMPARDA*/
			
			else {
				
				//EL JUGADOR GANA
				j.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
				
			}
		
		}
		
		return 0;
		
	}
	
	
	public int trucoQueridoB10() {
		
		auxIA = tiraIA[0];
		
		/*SI LA IA MATA*/
		
		if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
			
			tiratmp[0] = tiraIA[0];
			mostrarTirada(tiratmp,true);			
			
			//LA IA GANA
			MAQUINA.truco = true;
			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
			
		}
		
		/*SI LA IA NO MATA*/
		
		else if(aux.returnOrden(auxIA) > aux.returnOrden(auxJ)) {
			
			mostrarTirada(tiraIA,true);
			
			//EL JUGADOR GANA
			j.truco = true;
			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
			
		}
		
		/*SI LA IA EMPARDA*/
		
		else {
			
			mostrarTirada(tiraIA,true);
			
			mazo.setEnabled(true);
			
			if(c1.getIcon() != null) {
				c1.setEnabled(true);
			}
			if(c2.getIcon() != null) {
				c2.setEnabled(true);
			}
			if(c3.getIcon() != null) {
				c3.setEnabled(true);
			}
			
			llamadaHilos();
			
			if(botonRendirse == true) {
	        	return 1;
	        }
			
			/*EL JUGADOR SE VA AL MAZO*/
    		
    		if(irseAlMazo == true) {
    			
    		}
    		
    		/*EL JUGADOR TIRA*/
    		
    		else {
    		
				auxJ = queCartaFueTirada();
				
				tiraIA = null;
				tiraIA = MAQUINA.yourTurn(cantado, auxJ);
				auxIA = tiraIA[0];
				mostrarTirada(tiraIA, true);
				
				/*SI LA IA MATA*/
				
				if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
					
					//LA IA GANA
					MAQUINA.truco = true;
					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(MAQUINA.getPuntos());
					pts2.setText(acumulador);
					
					puntosMaximosSuperados();
					//SIGUIENTE RONDA
					
				}
				
				/*SI LA IA NO MATA O EMPARDA*/
				
				else {
					
					//EL JUGADOR GANA
					j.truco = true;
					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(j.getPuntos());
					pts1.setText(acumulador);
					
					puntosMaximosSuperados();
	    			//SIGUIENTE RONDA
					
				}
			
    		}
			
		}
		
		return 0;
		
	}	
	
	
	public int trucoQueridoB9() {
		
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
		
			auxJ = queCartaFueTirada();
			
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			
			tiraIA = null;
			tiraIA = MAQUINA.yourTurn(cantado, auxJ);
			auxIA = tiraIA[0];
			
			/*SI LA IA MATA*/
			
			if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
				
				tiratmp[0] = tiraIA[0];
				mostrarTirada(tiratmp,true);			
				
				//LA IA GANA
				MAQUINA.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
				
			}
			
			/*SI LA IA NO MATA*/
			
			else if(aux.returnOrden(auxIA) > aux.returnOrden(auxJ)) {
				
				mostrarTirada(tiraIA,true);
				
				//EL JUGADOR GANA
				j.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
				
			}
			
			/*SI LA IA EMPARDA*/
			
			else {
				
				mostrarTirada(tiraIA,true);
				
				if(c1.getIcon() != null) {
					c1.setEnabled(true);
				}
				if(c2.getIcon() != null) {
					c2.setEnabled(true);
				}
				if(c3.getIcon() != null) {
					c3.setEnabled(true);
				}
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
				/*EL JUGADOR SE VA AL MAZO*/
	    		
	    		if(irseAlMazo == true) {
	    			
	    		}
	    		
	    		/*EL JUGADOR TIRA*/
	    		
	    		else {
	    		
					auxJ = queCartaFueTirada();
					
					tiraIA = null;
					tiraIA = MAQUINA.yourTurn(cantado, auxJ);
					auxIA = tiraIA[0];
					mostrarTirada(tiraIA, true);
					
					/*SI LA IA MATA*/
					
					if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
						
						//LA IA GANA
						MAQUINA.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
						
					}
					
					/*SI LA IA NO MATA O EMPARDA*/
					
					else {
						
						//EL JUGADOR GANA
						j.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
		    			//SIGUIENTE RONDA
						
					}
				
	    		}
				
			}
		
		}
		
		return 0;
		
	}
	
	
	
	public int trucoQueridoB8() {
		
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
		
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR MATA O EMPARDA*/
			
			if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
				
				//EL JUGADOR GANA
				j.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
				
			}
			
			/*SI EL JUGADOR NO MATA*/
			
			else {
				
				//LA IA GANA
				MAQUINA.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
				
			}
		
		}
		
		return 0;
		
	}
	
	
	public int trucoQueridoB7() {
		
		tiratmp[0] = tiraIA[1];
		auxIA = tiraIA[1];
		mostrarTirada(tiratmp, true);
		
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
		
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR MATA O EMPARDA*/
			
			if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
				
				//EL JUGADOR GANA
				j.truco = true;
				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
				
			}
			
			/*SI EL JUGADOR NO MATA*/
			
			else {
				
				//LA IA GANA
				MAQUINA.truco = true;
				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
				
			}
		
		}
		
		return 0;
		
	}
	
	
	public int trucoQueridoB6() {
		
		/*SI LA IA MATA*/
		
		if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
			
			mostrarTirada(tiraIA, true);
			
			mazo.setEnabled(true);
			
			if(c1.getIcon() != null) {
				c1.setEnabled(true);
			}
			if(c2.getIcon() != null) {
				c2.setEnabled(true);
			}
			if(c3.getIcon() != null) {
				c3.setEnabled(true);
			}
			
			llamadaHilos();
			
			if(botonRendirse == true) {
	        	return 1;
	        }
			
			/*EL JUGADOR SE VA AL MAZO*/
    		
    		if(irseAlMazo == true) {
    			
    		}
    		
    		/*EL JUGADOR TIRA*/
    		
    		else {
    		
				auxJ = queCartaFueTirada();
				auxIA = tiraIA[1];			
				
				/*SI EL JUGADOR MATA O EMPARDA*/
				
				if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
					
					//EL JUGADOR GANA
					j.truco = true;
					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(j.getPuntos());
					pts1.setText(acumulador);
					
					puntosMaximosSuperados();
	    			//SIGUIENTE RONDA
					
				}
				
				/*SI EL JUGADOR NO MATA*/
				
				else {
					
					//LA IA GANA
					MAQUINA.truco = true;
					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(MAQUINA.getPuntos());
					pts2.setText(acumulador);
					
					puntosMaximosSuperados();
					//SIGUIENTE RONDA
					
				}
			
    		}
			
		}
		
		/*SI LA IA NO MATA O EMPARDA*/

		else {
			
			tiratmp[0] = tiraIA[0];
			mostrarTirada(tiratmp,true);
			
			//EL JUGADOR GANA
			j.truco = true;
			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
			
		}
		
		return 0;
		
	}
	
	
	public int trucoQueridoB5() {
		
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
		
			auxJ = queCartaFueTirada();
			
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			
			tiraIA = null;
			tiraIA = MAQUINA.yourTurn(cantado, auxJ);
			auxIA = tiraIA[0];
			
			/*SI LA IA MATA*/
			
			if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
				
				mostrarTirada(tiraIA, true);
				
				if(c1.getIcon() != null) {
					c1.setEnabled(true);
				}
				if(c2.getIcon() != null) {
					c2.setEnabled(true);
				}
				if(c3.getIcon() != null) {
					c3.setEnabled(true);
				}
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
				/*EL JUGADOR SE VA AL MAZO*/
	    		
	    		if(irseAlMazo == true) {
	    			
	    		}
	    		
	    		/*EL JUGADOR TIRA*/
	    		
	    		else {
	    		
					auxJ = queCartaFueTirada();
					auxIA = tiraIA[1];			
					
					/*SI EL JUGADOR MATA O EMPARDA*/
					
					if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
						
						//EL JUGADOR GANA
						j.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
		    			//SIGUIENTE RONDA
						
					}
					
					/*SI EL JUGADOR NO MATA*/
					
					else {
						
						//LA IA GANA
						MAQUINA.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
						
					}
				
	    		}
				
			}
			
			/*SI LA IA NO MATA O EMPARDA*/
	
			else {
				
				tiratmp[0] = tiraIA[0];
				mostrarTirada(tiratmp,true);
				
				//EL JUGADOR GANA
				j.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
				
			}
		
		}
		
		return 0;
		
	}	
	
	
	public void trucoQueridoB4() {
		
		auxIA = tiraIA[0];
		mostrarTirada(tiraIA, true);
		
		/*SI LA IA MATA O EMPARDA*/
		
		if(aux.returnOrden(auxIA) <= aux.returnOrden(auxJ)) {
			
			//LA IA GANA
			MAQUINA.truco = true;
			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
			
		}
		
		/*SI LA IA NO MATA*/
		
		else {
			
			//EL JUGADOR GANA
			j.truco = true;
			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
			
		}
		
	}
	
	
	public int trucoQueridoB3() {
		
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
		
			auxJ = queCartaFueTirada();
			
			tiraIA = null;
			tiraIA = MAQUINA.yourTurn(cantado, auxJ);
			mostrarTirada(tiraIA, true);
			auxIA = tiraIA[0];
			
			/*SI LA IA MATA O EMPARDA*/
			
			if(aux.returnOrden(auxIA) <= aux.returnOrden(auxJ)) {
				
				//LA IA GANA
				MAQUINA.truco = true;
				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
				
			}
			
			/*SI LA IA NO MATA*/
			
			else {
				
				//EL JUGADOR GANA
				j.truco = true;
				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
				
			}
		
		}
		
		return 0;
		
	}
	
	
	public int trucoQueridoB2() {
		
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
		
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR MATA*/
			
			if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
				/*EL JUGADOR SE VA AL MAZO*/
	    		
	    		if(irseAlMazo == true) {
	    			
	    		}
	    		
	    		/*EL JUGADOR TIRA*/
	    		
	    		else {
	    		
					auxJ = queCartaFueTirada();
					
					tiraIA = null;
					tiraIA = MAQUINA.yourTurn(cantado, auxJ);
					mostrarTirada(tiraIA,true);
					auxIA = tiraIA[0];
					
					/*SI LA IA MATA O EMPARDA*/
					
					if(aux.returnOrden(auxIA) <= aux.returnOrden(auxJ)) {
						
						//LA IA GANA
						MAQUINA.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
						
					}
					
					/*SI LA IA NO MATA*/
					
					else {
						
						//EL JUGADOR GANA
						j.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
		    			//SIGUIENTE RONDA
						
					}
				
	    		}
				
			}
			
			/*SI EL JUGADOR NO MATA O EMPARDA*/
			
			else {
				
				//LA IA GANA
				MAQUINA.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
				
			}
		
		}
		
		return 0;
		
	}

	
	public int trucoQueridoB1() {
		
		auxIA = tiraIA[1];
		tiratmp[0] = tiraIA[1];
		mostrarTirada(tiratmp, true);
		
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
		
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR MATA*/
			
			if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
				/*EL JUGADOR SE VA AL MAZO*/
	    		
	    		if(irseAlMazo == true) {
	    			
	    		}
	    		
	    		/*EL JUGADOR TIRA*/
	    		
	    		else {
	    		
					auxJ = queCartaFueTirada();
					
					tiraIA = null;
					tiraIA = MAQUINA.yourTurn(cantado, auxJ);
					mostrarTirada(tiraIA,true);
					auxIA = tiraIA[0];
					
					/*SI LA IA MATA O EMPARDA*/
					
					if(aux.returnOrden(auxIA) <= aux.returnOrden(auxJ)) {
						
						//LA IA GANA
						MAQUINA.truco = true;
						sistPuntuacion(cantado,MAQUINA,j);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
						
					}
					
					/*SI LA IA NO MATA*/
					
					else {
						
						//EL JUGADOR GANA
						j.truco = true;
						sistPuntuacion(cantado,MAQUINA,j);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
		    			//SIGUIENTE RONDA
						
					}
				
	    		}
				
			}
			
			/*SI EL JUGADOR NO MATA O EMPARDA*/
			
			else {
				
				//LA IA GANA
				MAQUINA.truco = true;
				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
				
			}
		
		}
		
		return 0;
		
	}
	
	
	
	public int trucoQueridoBloque12() {
		
		flecha2.setEnabled(true);
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
			
			//CHEQUEAR SI EFECTIVAMENTE SE TOMO LA SEGUNDA CARTA DE LA SEGUNDA TIRADA
			auxJ = queCartaFueTirada();
			
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			
			//LE TOCA A LA IA
			
			tiraIA = null;
			tiraIA = MAQUINA.yourTurn(cantado, auxJ);
			auxIA = tiraIA[0];
			
			/*SI LA IA MATA*/
			
			if(aux.returnOrden(auxIA) < aux.returnOrden(auxJ)) {
				
				mostrarTirada(tiraIA,true);
			
				if(c1.getIcon() != null) {
					c1.setEnabled(true);
				}
				if(c2.getIcon() != null) {
					c2.setEnabled(true);
				}
				if(c3.getIcon() != null) {
					c3.setEnabled(true);
				}
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
				/*EL JUGADOR SE VA AL MAZO*/
	    		
	    		if(irseAlMazo == true) {
	    			
	    		}
	    		
	    		/*EL JUGADOR TIRA*/
	    		
	    		else {
	    		
					//EL JUGADOR TIRA EN TERCERA
					auxIA = tiraIA[1];
					auxJ = queCartaFueTirada();
					
					/*SI EL JUGADOR MATA*/
					
					if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
						//EL JUGADOR GANA
						j.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
					
					/*SI EL JUGADOR NO MATA*/
					
					else {
						//LA IA GANA
						MAQUINA.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
				
	    		}
						
			}
	
			/*SI LA IA NO MATA O EMPARDA*/
			
			else {
				
				mostrarTirada(tiraIA,true);
				
				//EL JUGADOR GANA
				j.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
				
			}
		
		}
		
		return 0;
		
	}
	
	
	public int trucoQueridoBloque11() {
		
		tiratmp[0] = tiraIA[1];
		tiraIAnull = true;
		mostrarTirada(tiratmp,tiraIAnull);
		
		flecha2.setEnabled(true);
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
		
			auxIA = tiraIA[1];
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR MATA*/
			
			if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
				//EL JUGADOR GANA
				j.truco = true;
				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
			/*SI EL JUGADOR NO MATA*/
			
			else {
				//LA IA GANA
				MAQUINA.truco = true;
				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
		
		}
		
		return 0;
		
	}
	
	
	public int trucoQueridoBloque10() {
		
		flecha2.setEnabled(true);
		mazo.setEnabled(true);
		
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
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
			
			auxIA = tiraIA[1];
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR MATA*/
			
			if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
				//EL JUGADOR GANA
				j.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
			/*SI EL JUGADOR NO MATA*/
			
			else {
				//LA IA GANA
				MAQUINA.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
		
		}
		
		return 0;
		
	}
	
	
	public int trucoQueridoBloque9() {
		
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
			
			flecha2.setEnabled(true);
			mazo.setEnabled(true);
			
			if(c1.getIcon() != null) {
				c1.setEnabled(true);
			}
			if(c2.getIcon() != null) {
				c2.setEnabled(true);
			}
			if(c3.getIcon() != null) {
				c3.setEnabled(true);
			}
			
			llamadaHilos();
			
			if(botonRendirse == true) {
	        	return 1;
	        }
			
			/*EL JUGADOR SE VA AL MAZO*/
    		
    		if(irseAlMazo == true) {
    			
    		}
    		
    		/*EL JUGADOR TIRA*/
    		
    		else {
    		
				//EL JUGADOR TIRA
					
				auxIA = tiraIA[1];
				auxJ = queCartaFueTirada();
				
				/*SI EL JUGADOR MATA*/
				
				if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
					//EL JUGADOR GANA
					j.truco = true;
					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(j.getPuntos());
					pts1.setText(acumulador);
					
					puntosMaximosSuperados();
					//SIGUIENTE RONDA
				}
				
				/*SI EL JUGADOR NO MATA*/
				
				else {
					//LA IA GANA
					MAQUINA.truco = true;
					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(MAQUINA.getPuntos());
					pts2.setText(acumulador);
					
					puntosMaximosSuperados();
					//SIGUIENTE RONDA
				}
			
    		}
			
		}
		
		/*SI LA IA NO MATA O EMPARDA*/
		
		else {
			
			//tiraIAnull = true;
			//tiratmp[0] = tiraIA[0];
			//mostrarTirada(tiratmp,tiraIAnull);	
			
			//EL JUGADOR GANA
			j.truco = true;
			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
			
		}
		
		return 0;
		
	}
	
	
	
	public int trucoQueridoBloque8() {
		
		flecha2.setEnabled(true);
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
			
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR LA MATA*/
			
			if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
				//EL JUGADOR GANA
				j.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
			/*SI EL JUGADOR NO LA MATA*/
			
			else if(aux.returnOrden(auxJ) > aux.returnOrden(auxIA)) {
				//LA IA GANA
				MAQUINA.truco = true;
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
		
		return 0;
		
	}
	
	
	public int trucoQueridoBloque7() {
		
		tiraIAnull = true;
		mostrarTirada(tiraIA,tiraIAnull);
		auxIA = tiraIA[0];
		
		flecha2.setEnabled(true);
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
		
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR LA MATA*/
			
			if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
				//EL JUGADOR GANA
				j.truco = true;
				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
			/*SI EL JUGADOR NO LA MATA*/
			
			else {
				//LA IA GANA
				MAQUINA.truco = true;
				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
		
		}
	
		return 0;
		
	}
	
	
	public int trucoQueridoBloque6() {
		
		flecha2.setEnabled(true);
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
			
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR MATA*/
			
			if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
				//EL JUGADOR GANA
				j.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
			/*SI EL JUGADOR NO MATA*/
			
			else if(aux.returnOrden(auxJ) > aux.returnOrden(auxIA)) {
				//LA IA GANA
				MAQUINA.truco = true;
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
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
	    		/*EL JUGADOR SE VA AL MAZO*/
	    		
	    		if(irseAlMazo == true) {
	    			
	    		}
	    		
	    		/*EL JUGADOR TIRA*/
	    		
	    		else {
	    		
					//TIRA EL JUGADOR
					
		    		auxJ = queCartaFueTirada();
		    		
					/*SI EL JUGADOR MATA*/
					
					if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
						//EL JUGADOR GANA
						j.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
					
					/*SI EL JUGADOR NO MATA*/
					
					else {
						//LA IA GANA
						MAQUINA.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
				
	    		}
				
			}
		
		}
		
		return 0;
		
	}
	
	
	public int trucoQueridoBloque5() {
		
		truco.setEnabled(false);
		flecha2.setEnabled(true);				//POR SI QUIERE IRSE AL MAZO
		mazo.setEnabled(true);
				
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
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
			
			//TIRA EL JUGADOR
			
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR MATA*/
			
			if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
				//EL JUGADOR GANA
				j.truco = true;
				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
			/*SI EL JUGADOR NO MATA*/
			
			else if(aux.returnOrden(auxJ) > aux.returnOrden(auxIA)) {
				//LA IA GANA
				MAQUINA.truco = true;
				sistPuntuacion(cantado,MAQUINA,j);
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
				
				//LA IA TIRA
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
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
	    		/*EL JUGADOR SE VA AL MAZO*/
	    		
	    		if(irseAlMazo == true) {
	    			
	    		}
	    		
	    		/*EL JUGADOR TIRA*/
	    		
	    		else {
	    		
					//EL JUGADOR TIRA
					
		    		auxJ = queCartaFueTirada();
		    		
					/*SI EL JUGADOR MATA*/
					
					if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
						//EL JUGADOR GANA
						j.truco = true;
						sistPuntuacion(cantado,MAQUINA,j);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
					
					/*SI EL JUGADOR NO MATA*/
					
					else {
						//LA IA GANA
						MAQUINA.truco = true;
						sistPuntuacion(cantado,MAQUINA,j);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
				
	    		}
				
			}
		
		}
		
		return 0;
		
	}
	
	
	
	public void trucoQueridoBloque4() {
		
		tiraIAnull = true;
		mostrarTirada(tiraIA,tiraIAnull);
		auxIA = tiraIA[0];
		
		/*SI LA IA MATA O EMPARDA*/
		
		if(aux.returnOrden(auxIA) <= aux.returnOrden(auxJ)) {
			//LA IA GANA
			MAQUINA.truco = true;
			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
		/*SI LA IA NO MATA*/
		
		else {
			//EL JUGADOR GANA
			j.truco = true;
			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			puntosMaximosSuperados();
			//SIGUIENTE RONDA
		}
		
	}
	
	
	public int trucoQueridoBloque3() {
		
		flecha2.setEnabled(true);
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
			
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
				MAQUINA.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
			/*SI LA IA NO MATA*/
			
			else {
				//EL JUGADOR GANA
				j.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
		
		}
		
		return 0;
		
	}
	
	
	public int trucoQueridoBloque2() {
		
		flecha2.setEnabled(true);
		mazo.setEnabled(true);
		
		if(c1.getIcon() != null) {
			c1.setEnabled(true);
		}
		if(c2.getIcon() != null) {
			c2.setEnabled(true);
		}
		if(c3.getIcon() != null) {
			c3.setEnabled(true);
		}
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
			
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR NO LA MATA/EMPARDA*/
			
			if(aux.returnOrden(auxJ) >= aux.returnOrden(auxIA)) {
				
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				
				//LA IA GANA
				MAQUINA.truco = true;
				sistPuntuacion(cantado,j,MAQUINA);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
			
			/*SI EL JUGADOR LA MATA*/
			
			else {
				
				//TIRA NUEVAMENTE
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
	    		/*EL JUGADOR SE VA AL MAZO*/
	    		
	    		if(irseAlMazo == true) {
	    			
	    		}
	    		
	    		/*EL JUGADOR TIRA*/
	    		
	    		else {
	    			
		    		auxJ = queCartaFueTirada();
		    		
		    		tiraIA = null;
					tiraIA = MAQUINA.yourTurn(cantado, null);
					tiraIAnull = true;
					mostrarTirada(tiraIA,tiraIAnull);
					auxIA = tiraIA[0];
		    		
					/*SI LA IA MATA*/
					
					if(aux.returnOrden(auxIA) <= aux.returnOrden(auxJ)) {
						//LA IA GANA
						MAQUINA.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
					
					/*SI LA IA NO MATA*/
					
					else {
						//EL JUGADOR GANA
						j.truco = true;
						sistPuntuacion(cantado,j,MAQUINA);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						
						puntosMaximosSuperados();
						//SIGUIENTE RONDA
					}
				
	    		}
				
			}
		
		}
		
		return 0;
		
	}
	
	
	public int trucoQueridoBloque1() {
		
		truco.setEnabled(false);
		flecha2.setEnabled(true);				//POR SI QUIERE IRSE AL MAZO
		quiero.setEnabled(false);
		noQuiero.setEnabled(false);
		mazo.setEnabled(true);
				
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
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else {
			
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR MATA*/
			
			if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
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
					MAQUINA.truco = true;
					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(MAQUINA.getPuntos());
					pts2.setText(acumulador);
					
					puntosMaximosSuperados();
					//SIGUIENTE RONDA
				}
				
				/*SI LA IA NO MATA*/
				
				else {
					//EL JUGADOR GANA
					j.truco = true;
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
				MAQUINA.truco = true;
				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);
				
				puntosMaximosSuperados();
				//SIGUIENTE RONDA
			}
		
		}
		
		return 0;
		
	}
	
	
	
	public int trucoQuerido() {
		
		c1.setEnabled(true);
		c2.setEnabled(true);
		c3.setEnabled(true);

		truco.setEnabled(false);
		flecha2.setEnabled(true);
		mazo.setEnabled(true);
				
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA*/
		
		else{
			
			auxJ = queCartaFueTirada();
			
			/*SI EL JUGADOR NO LA MATA*/
			
			if(aux.returnOrden(auxJ) > aux.returnOrden(auxIA)) {
				
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				
				tiraIA = null;
				MAQUINA.setPuedoCantarEnvido(false);
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
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
	    		/*EL JUGADOR SE VA AL MAZO*/
	    		
	    		if(irseAlMazo == true) {
	    			
	    		}
	    		
	    		/*EL JUGADOR TIRA*/
	    		
	    		else {
	    			
		    		/*SI EL JUGADOR NO LA MATA O EMPARDA*/
		    		
		    		auxJ = queCartaFueTirada();
		    		auxIA = tiraIA[0];
		    		
		    		if(aux.returnOrden(auxJ) >= aux.returnOrden(auxIA)) {
		    			
		    			//SE SUMAN PUNTOS
		    			MAQUINA.truco = true;
		    			sistPuntuacion(cantado,j,MAQUINA);
		    			acumulador = String.valueOf(MAQUINA.getPuntos());
		    			pts2.setText(acumulador);
		    			
		    			puntosMaximosSuperados();
		    			//SE PASA A LA SIGUIENTE RONDA
		    			
		    		}
		    		
		    		/*SI EL JUGADOR LA MATA*/
		    		
		    		else {
		    			
		    			auxJ = queCartaFueTirada();		//se puede comentar/eliminar
		    			
		    			llamadaHilos();
		    			
		    			if(botonRendirse == true) {
				        	return 1;
				        }
		    			
			    		/*EL JUGADOR SE VA AL MAZO*/
			    		
			    		if(irseAlMazo == true) {
			    			
			    		}
			    		
			    		/*EL JUGADOR TIRA*/
			    		
			    		else {
			    			
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
			    				MAQUINA.truco = true;
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
			    				j.truco = true;
			    				sistPuntuacion(cantado,j,MAQUINA);
			    				acumulador = String.valueOf(j.getPuntos());
			    				pts1.setText(acumulador);
			    				
			    				puntosMaximosSuperados();
			    				//LA IA PIERDE
			    				//SE PASA A LA SIGUIENTE RONDA
			    				
			    			}
		    					    			
			    		}
		    			
		    		}
	    		
	    		}
	    		
			}
			
			
			
			/*SI EL JUGADOR LA EMPARDA*/
			
			else if(aux.returnOrden(auxJ) == aux.returnOrden(auxIA)) {
				
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				
				//LA IA TIRA
				
				MAQUINA.setPuedoCantarEnvido(false);
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
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
	    		/*EL JUGADOR SE VA AL MAZO*/
	    		
	    		if(irseAlMazo == true) {
	    			
	    		}
	    		
	    		/*EL JUGADOR TIRA*/
	    		
	    		else {
	    			
		    		auxJ = queCartaFueTirada();
		    		
		    		/*SI EL JUGADOR NO LA MATA*/
		    		
		    		if(aux.returnOrden(auxJ) > aux.returnOrden(auxIA)) {
		    			//LA IA GANA
		    			//SE SUMAN PUNTOS
		    			MAQUINA.truco = true;
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
		    			j.truco = true;
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
		    			
		    			llamadaHilos();
		    			
		    			if(botonRendirse == true) {
				        	return 1;
				        }
		    			
			    		/*EL JUGADOR SE VA AL MAZO*/
			    		
			    		if(irseAlMazo == true) {
			    			
			    		}
			    		
			    		/*EL JUGADOR TIRA*/
			    		
			    		else {
			    			
				    		auxJ = queCartaFueTirada();
				    		
				    		/*SI EL JUGADOR LA MATA*/
				    		
				    		if(aux.returnOrden(auxJ) < aux.returnOrden(auxIA)) {
				    			//EL JUGADOR GANA
				    			//SE SUMAN PUNTOS
				    			j.truco = true;
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
				    			MAQUINA.truco = true;
				    			sistPuntuacion(cantado,j,MAQUINA);
				    			acumulador = String.valueOf(MAQUINA.getPuntos());
				    			pts2.setText(acumulador);
				    			
				    			puntosMaximosSuperados();
				    			//SIGUIENTE RONDA
				    			
				    		}
			    		
			    		}
			    		
		    		}
	    		
	    		}
	    														    		
			}
			
			
			
			/*SI EL JUGADOR LA MATA*/
			
			else {
				
				llamadaHilos();
	    		
				if(botonRendirse == true) {
		        	return 1;
		        }
				
	    		/*EL JUGADOR SE VA AL MAZO*/
	    		
	    		if(irseAlMazo == true) {
	    			
	    		}
	    		
	    		/*EL JUGADOR TIRA*/
	    		
	    		else {
	    			
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
		    			
		    			llamadaHilos();
		    			
		    			if(botonRendirse == true) {
				        	return 1;
				        }
		    			
			    		/*EL JUGADOR SE VA AL MAZO*/
			    		
			    		if(irseAlMazo == true) {
			    			
			    		}
			    		
			    		/*EL JUGADOR TIRA*/
			    		
			    		else {
			    			
				    		auxJ = queCartaFueTirada();
				    		auxIA = tiraIA[1];
				    		
				    		/*SI EL JUGADOR MATA O EMPARDA*/
				    		
				    		if(aux.returnOrden(auxJ) <= aux.returnOrden(auxIA)) {
				    			//EL JUGADOR GANA
				    			//SE SUMAN PUNTOS
				    			j.truco = true;
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
				    			MAQUINA.truco = true;
				    			sistPuntuacion(cantado,j,MAQUINA);
				    			acumulador = String.valueOf(MAQUINA.getPuntos());
				    			pts2.setText(acumulador);
				    			
				    			puntosMaximosSuperados();
				    			//SIGUIENTE RONDA
				    		}
			    		
			    		}
			    		
		    		}
		    		
		    		/*SI LA IA NO MATA*/
		    		
		    		else {
		    			//EL JUGADOR GANA
		    			//SE SUMAN PUNTOS
		    			j.truco = true;
		    			sistPuntuacion(cantado,j,MAQUINA);
		    			acumulador = String.valueOf(j.getPuntos());
		    			pts1.setText(acumulador);
		    			
		    			puntosMaximosSuperados();
		    			//SIGUIENTE RONDA
		    		}
	    		
	    		}
	    		
			}
				
		}
		
		return 0;
		
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
	        
	        if(i==1) {									//SIRVE PARA LOS TIRA-IA QUE TIENEN DOS CARTAS, YA QUE LLEGA UN MOMENTO DONDE NO CUMPLEN LA COND DEL FOR PORQUE NO SON NULLS
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
                        j1.addPuntos(5);
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
	
	public void reiniciarMenu() {
		
		cp.remove(menuCantosE);
		cp.remove(menuCantosT);
		cp.add(menu,BorderLayout.WEST);
		cp.revalidate();
		cp.repaint();
		
	}
    
	public void puntosMaximosSuperados() {
		
		if(j.getPuntos() >= 30) {
			
			jugando = false;
			
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
			
			rendirse.setText("MENU");			
			
		}
		
		else if(MAQUINA.getPuntos() >= 30){
			
			jugando = false;
			
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
			
			rendirse.setText("MENU");
			
		}
		
	}
	
	
	public void invertirManoMazo() {
		
		if(ronda % 2 == 0) {
			panel2.remove(img_mano);
			panel2.remove(img_mazo);
			panel2.add(img_mazo);
			panel2.add(img_mano);
		}
		else {
			panel2.remove(img_mazo);
			panel2.remove(img_mano);
			panel2.add(img_mano);
			panel2.add(img_mazo);
			
		}
	}
	
	
	public int cantaEnvidoJugador() {
		
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
				
		MAQUINA.yourTurnAccept(cantado,j);
		
		/*SI LA IA QUIERE*/
		
		if(cantado[1].equals("quiero")) {
			
			texto.setText("<html>"+ cantado[1] +"</html>");
			
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//SE SUMAN PUNTOS
			
			sistPuntuacion(cantado,j,MAQUINA);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			acumulador = "";
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			mensajeSaliente();
			
			envido.setEnabled(false);
			reiniciarMenuCantos();
			
			puntosMaximosSuperados();
			
		}
		
		/*SI LA IA NO QUIERE*/
		
		else if(cantado[1].equals("no quiero")) {
			
			texto.setText("<html>"+ cantado[1] +"</html>");
			
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			sistPuntuacion(cantado,j,MAQUINA);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			
			envido.setEnabled(false);
			
			reiniciarMenuCantos();
			puntosMaximosSuperados();
			
		}
		
		/*SI LA IA REVIRA*/
		
		else if(cantado[1].equals("envido") || cantado[1].equals("real envido") || cantado[1].equals("falta envido")) {
			
			texto.setText("<html>"+ cantado[1] +"</html>");
			
			if(cantado[1].equals("envido")) {
				
				quiero.setEnabled(true);
				noQuiero.setEnabled(true);
				
				cantarEnvido.setEnabled(false);
				real_envido.setEnabled(true);
				falta_envido.setEnabled(true);
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
	    		/* SI EL JUGADOR QUIERE */
	    		
	    		if(cantado[2].equals("quiero")) {
	    			//SE SUMAN PUNTOS
	    			
	    			sistPuntuacion(cantado,j,MAQUINA);
	    			acumulador = String.valueOf(j.getPuntos());
	    			pts1.setText(acumulador);
	    			acumulador = "";
	    			acumulador = String.valueOf(MAQUINA.getPuntos());
	    			pts2.setText(acumulador);
	    			
	    			mensajeSaliente();
	    			
	    			envido.setEnabled(false);
	    			
	    			reiniciarMenuCantos();
	    			puntosMaximosSuperados();
	    			
	    		}
	    		
	    		/*SI EL JUGADOR NO QUIERE*/
	    		
	    		else if(cantado[2].equals("no quiero")) {
	    			//SE SUMAN PUNTOS
	    			
	    			sistPuntuacion(cantado,j,MAQUINA);
	    			acumulador = String.valueOf(MAQUINA.getPuntos());
	    			pts2.setText(acumulador);
	    			
	    			envido.setEnabled(false);
	    			
	    			reiniciarMenuCantos();
	    			puntosMaximosSuperados();
	    			
	    		}
	    		
	    		/* SI EL JUGADOR REVIRA */
	    		
	    		else if(cantado[2].equals("real envido") || cantado[2].equals("falta envido")) {
	    			
	    			real_envido.setEnabled(false);
	    			falta_envido.setEnabled(false);
	    			
	    			MAQUINA.yourTurnAccept(cantado,j);
	    			
	    			//SI LA IA QUIERE
		    		
		    		if(cantado[3].equals("quiero")) {
		    			
		    			texto.setText("<html>"+ cantado[3] +"</html>");
						
	    				try {
	    					Thread.sleep(800);
	    				} catch (InterruptedException e) {
	    					e.printStackTrace();
	    				}
		    			
		    			//SE SUMAN PUNTOS
		    			
	    				sistPuntuacion(cantado,j,MAQUINA);
	    				acumulador = String.valueOf(j.getPuntos());
	    				pts1.setText(acumulador);
	    				acumulador = "";
	    				acumulador = String.valueOf(MAQUINA.getPuntos());
	    				pts2.setText(acumulador);
						
	    				mensajeSaliente();
	    				
	    				envido.setEnabled(false);
	    				
						reiniciarMenuCantos();
						puntosMaximosSuperados();
						
		    		}
		    		
		    		/*SI LA IA NO QUIERE*/
		    		
		    		else if(cantado[3].equals("no quiero")) {
		    			
		    			texto.setText("<html>"+ cantado[3] +"</html>");
		    			
	    				try {
	    					Thread.sleep(800);
	    				} catch (InterruptedException e) {
	    					e.printStackTrace();
	    				}
		    			
		    			//SE SUMAN PUNTOS
		    			
	    				sistPuntuacion(cantado,j,MAQUINA);
	    				acumulador = String.valueOf(j.getPuntos());
	    				pts1.setText(acumulador);
	    				
		    			envido.setEnabled(false);
		    			reiniciarMenuCantos();
		    			puntosMaximosSuperados();
		    		
		    		}
		    		
		    		/*SI LA IA REVIRA*/
		    		
		    		else if(cantado[3].equals("falta envido")) {
		    			
		    			texto.setText("<html>"+ cantado[3] +"</html>");
						
		    			quiero.setEnabled(true);
	    				noQuiero.setEnabled(true);
		    			
	    				llamadaHilos();
		    			
	    				if(botonRendirse == true) {
	    		        	return 1;
	    		        }
	    				
			    		/* SI EL JUGADOR QUIERE */
			    		
			    		if(cantado[4].equals("quiero")) {
			    			//SE SUMAN PUNTOS
			    			
			    			sistPuntuacion(cantado,j,MAQUINA);
			    			acumulador = String.valueOf(j.getPuntos());
			    			pts1.setText(acumulador);
			    			acumulador = "";
			    			acumulador = String.valueOf(MAQUINA.getPuntos());
			    			pts2.setText(acumulador);
			    			
			    			mensajeSaliente();
			    			
							envido.setEnabled(false);
			    			reiniciarMenuCantos();
			    			puntosMaximosSuperados();
			    			
							
			    		}
			    		
			    		/*SI EL JUGADOR NO QUIERE*/
			    		
			    		else {
			    			//SE SUMAN PUNTOS
			    			
			    			sistPuntuacion(cantado,j,MAQUINA);
			    			acumulador = String.valueOf(MAQUINA.getPuntos());
			    			pts2.setText(acumulador);

			    			envido.setEnabled(false);
			    			reiniciarMenuCantos();
			    			puntosMaximosSuperados();
			    			
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
				
				llamadaHilos();
				
				if(botonRendirse == true) {
		        	return 1;
		        }
				
	    		/* SI EL JUGADOR QUIERE */
	    		
	    		if(cantado[2].equals("quiero")) {
	    			//SE SUMAN PUNTOS
	    			
	    			sistPuntuacion(cantado,j,MAQUINA);
	    			acumulador = String.valueOf(j.getPuntos());
	    			pts1.setText(acumulador);
	    			acumulador = "";
	    			acumulador = String.valueOf(MAQUINA.getPuntos());
	    			pts2.setText(acumulador);
	    			
	    			mensajeSaliente();
	    			
					envido.setEnabled(false);
	    			reiniciarMenuCantos();
	    			puntosMaximosSuperados();
	    			
	    		}
	    		
	    		/*SI EL JUGADOR NO QUIERE*/
	    		
	    		else if(cantado[2].equals("no quiero")) {
	    			//SE SUMAN PUNTOS
	    			
	    			sistPuntuacion(cantado,j,MAQUINA);
	    			acumulador = String.valueOf(MAQUINA.getPuntos());
	    			pts2.setText(acumulador);

	    			envido.setEnabled(false);
	    			reiniciarMenuCantos();
	    			puntosMaximosSuperados();
	    			
	    		}
	    		
	    		/* SI EL JUGADOR REVIRA */
	    			
	    		else if(cantado[2].equals("falta envido")) {
	    			
	    			falta_envido.setEnabled(false);
	    			
	    			MAQUINA.yourTurnAccept(cantado,j);
	    			
	    			/* SI LA IA QUIERE */
	    			
	    			if(cantado[3].equals("quiero")) {
		    			
	    				texto.setText("<html>"+ cantado[3] +"</html>");
						
	    				try {
	    					Thread.sleep(800);
	    				} catch (InterruptedException e) {
	    					e.printStackTrace();
	    				}
	    				
	    				//SE SUMAN PUNTOS
		    			
	    				sistPuntuacion(cantado,j,MAQUINA);
	    				acumulador = String.valueOf(j.getPuntos());
	    				pts1.setText(acumulador);
	    				acumulador = "";
	    				acumulador = String.valueOf(MAQUINA.getPuntos());
	    				pts2.setText(acumulador);
	    				
	    				mensajeSaliente();
	    				
						envido.setEnabled(false);
		    			reiniciarMenuCantos();
		    			puntosMaximosSuperados();
		    			
						
		    		}
	    			
	    			/*SI LA IA NO QUIERE*/
	    			
	    			else {
	    				
	    				texto.setText("<html>"+ cantado[3] +"</html>");
	    				
	    				try {
	    					Thread.sleep(800);
	    				} catch (InterruptedException e) {
	    					e.printStackTrace();
	    				}
	    				
	    				//SE SUMAN PUNTOS
	    				
	    				sistPuntuacion(cantado,j,MAQUINA);
	    				acumulador = String.valueOf(j.getPuntos());
	    				pts1.setText(acumulador);

	    				envido.setEnabled(false);
	    				reiniciarMenuCantos();
	    				puntosMaximosSuperados();
	    				
	    				
	    			}
	    			
	    		}
	    		
			}
			
			else if(cantado[1].equals("falta envido")){
				
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
					
					mensajeSaliente();
					
					envido.setEnabled(false);
	    			reiniciarMenuCantos();
	    			puntosMaximosSuperados();
	    			
	    		}
				
				/*SI EL JUGADOR NO QUIERE*/
				
				else if(cantado[2].equals("no quiero")) {
					//SE SUMAN PUNTOS
					
					sistPuntuacion(cantado,j,MAQUINA);
					acumulador = String.valueOf(MAQUINA.getPuntos());
					pts2.setText(acumulador);
					
					envido.setEnabled(false);
					reiniciarMenuCantos();
					puntosMaximosSuperados();
				
					
				}
				
			}
			
		}
		
		if(jugando == false) {
			
			return 0;
			
		}
		
		/*SI TERMINA DE CANTAR ENVIDO, LUEGO PUEDE CANTAR TRUCO O TIRAR CARTA DIRECTAMENTE*/
		
		/*EL ARREGLO DE CANTADOS VUELVE A NULL POR SI SE CANTA TRUCO*/
		
		for(int i=0;i<5;i++) {
    		cantado[i] = "";
    	}
		
		mazo.setEnabled(true);
		texto.setText("<html>"+ "" +"</html>");
		
		truco.setEnabled(true);
		flecha2.setEnabled(true);
		
		c1.setEnabled(true);
		c2.setEnabled(true);
		c3.setEnabled(true);
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		/*EL JUGADOR CANTA TRUCO*/
		
		if(cantado[0].equals("truco")) {
			
			truco2();
			
		}
		
		/*EL JUGADOR SE VA AL MAZO*/
		
		else if(irseAlMazo == true) {
			
		}
		
		/*EL JUGADOR TIRA DIRECTAMENTE*/
		
		else {
			
			if(ronda%2 == 0) {
			
				auxJ = queCartaFueTirada();
				
				truco.setEnabled(false);
				cantarTruco.setEnabled(false);
				
				envido.setEnabled(false);
				cantarEnvido.setEnabled(false);
				real_envido.setEnabled(false);
				falta_envido.setEnabled(false);
				
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				
				Arrays.fill(cantado,"");
				
				tiraIA = null;
				tiraIA = MAQUINA.yourTurnTruco(cantado, auxJ);	
				auxIA = tiraIA[0];
				
				//LA IA PUEDE CANTAR TRUCO
				
				if(cantado[0].equals("truco")) {
					
					truco3();
					
				}
				
				//LA IA PUEDE TIRAR DIRECTAMENTE
				
				else {
					
					tiraDir2();
					
				}
			
			}
			
			else {			//SI EL JUGADOR TIRA, DEBO DETERMINAR SI MATA, NO MATA O EMPARDA (ALGO SIMILAR HABRE HECHO ANTES CAPAZ) (RESUELTO)
				
				tirarDir();
				
			}
		}
		
		return 0;
		
	}
	
	
	
	public int cantaEnvidoIA() {
		
		if(cantado[0].equals("falta envido")) {
			envido.setEnabled(false);
			cantarEnvido.setEnabled(false);
			real_envido.setEnabled(false);
			falta_envido.setEnabled(false);
		}
		else if (cantado[0].equals("real envido")){
			envido.setEnabled(true);
			cantarEnvido.setEnabled(false);
			real_envido.setEnabled(false);
			falta_envido.setEnabled(true);
		}
		else if (cantado[0].equals("envido")){
			envido.setEnabled(true);
			cantarEnvido.setEnabled(true);
			real_envido.setEnabled(true);
			falta_envido.setEnabled(true);
		}
		
		texto.setText("<html>"+ cantado[0] +"</html>");
		quiero.setEnabled(true);
		noQuiero.setEnabled(true);
																
		c1.setEnabled(false);
		c2.setEnabled(false);
		c3.setEnabled(false);
		
		llamadaHilos();
		
		if(botonRendirse == true) {
        	return 1;
        }
		
		//SI EL JUGADOR QUIERE
		
		if(cantado[1].equals("quiero")) {
			
			//SE SUMAN PUNTOS
			
			sistPuntuacion(cantado,MAQUINA,j);
			acumulador = String.valueOf(j.getPuntos());
			pts1.setText(acumulador);
			acumulador = "";
			acumulador = String.valueOf(MAQUINA.getPuntos());
			pts2.setText(acumulador);
			
			mensajeSaliente();
			
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
			
			quiero.setEnabled(false);
			noQuiero.setEnabled(false);
			
			if(cantado[1].equals("envido")) {
				cantarEnvido.setEnabled(false);
				
			}
			else if(cantado[1].equals("real envido")) {
				cantarEnvido.setEnabled(false);
				real_envido.setEnabled(false);
			}
			else if(cantado[1].equals("falta envido")){
				cantarEnvido.setEnabled(false);
				real_envido.setEnabled(false);
				falta_envido.setEnabled(false);
			}
			
			MAQUINA.yourTurnAccept(cantado,j);
			
			//SI LA IA QUIERE
			
			if(cantado[2].equals("quiero")) {
				
				texto.setText("<html>"+ cantado[2] +"</html>");
				
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				acumulador = "";
				acumulador = String.valueOf(MAQUINA.getPuntos());
				pts2.setText(acumulador);											    				
				
				mensajeSaliente();
				
				reiniciarMenuCantos();
				
				puntosMaximosSuperados();
				
			}
			
			//SI LA IA NO QUIERE
			
			else if(cantado[2].equals("no quiero")) {
				
				texto.setText("<html>"+ cantado[2] +"</html>");
				
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				sistPuntuacion(cantado,MAQUINA,j);
				acumulador = String.valueOf(j.getPuntos());
				pts1.setText(acumulador);
				
				reiniciarMenuCantos();
				
				puntosMaximosSuperados();
				
			}
			
			//SI LA IA REVIRA
			
			else if(cantado[2].equals("real envido") || cantado[2].equals("falta envido")) {
				
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
					real_envido.setEnabled(false);
					falta_envido.setEnabled(false);
				}
				
				llamadaHilos();
	    		
				if(botonRendirse == true) {
		        	return 1;
		        }
				
				falta_envido.setEnabled(false);
				
				//SI EL JUGADOR QUIERE
				
				if(cantado[3].equals("quiero")) {
					
					//SE SUMAN PUNTOS
		
					sistPuntuacion(cantado,MAQUINA,j);
					acumulador = String.valueOf(j.getPuntos());
					pts1.setText(acumulador);
					acumulador = "";
					acumulador = String.valueOf(MAQUINA.getPuntos());
					pts2.setText(acumulador);
					
					mensajeSaliente();
					
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
					
					quiero.setEnabled(false);
					noQuiero.setEnabled(false);
					
					MAQUINA.yourTurnAccept(cantado,j);
					
					//SI LA IA QUIERE
					
					if(cantado[4].equals("quiero")) {
						
						texto.setText("<html>"+ cantado[4] +"</html>");
						
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						sistPuntuacion(cantado,MAQUINA,j);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);
						acumulador = "";
						acumulador = String.valueOf(MAQUINA.getPuntos());
						pts2.setText(acumulador);
						
						mensajeSaliente();
						
						reiniciarMenuCantos();
						
						puntosMaximosSuperados();
						
					}
					
					else{
						
						texto.setText("<html>"+ cantado[4] +"</html>");
						
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						sistPuntuacion(cantado,MAQUINA,j);
						acumulador = String.valueOf(j.getPuntos());
						pts1.setText(acumulador);

						reiniciarMenuCantos();
						puntosMaximosSuperados();
						
					}
					
				}
				
			}
			
		}
		
		return 0;
		
	}
	
	
	public void mensajeSaliente() {
		
		if(j.getPuntosEnvido() > MAQUINA.getPuntosEnvido() || (j.getPuntosEnvido() == MAQUINA.getPuntosEnvido() && j.soy_mano == true)) {
			
			JOptionPane.showMessageDialog(null, "GANASTE!", "ENVIDO", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
		else {
			
			JOptionPane.showMessageDialog(null, "PERDISTE...", "ENVIDO", JOptionPane.INFORMATION_MESSAGE);
			
		}
				
	}
	
	//FUNCION QUE SERVIRA PARA LLAMAR UN HILO EN VEZ DE CREAR MUCHOS DE FORMA INDIVIDUAL
	public void llamadaHilos() {
		
		accionUsuario = false;
		Thread t = new Thread() {
			
			@Override
			public void run() {
				while(accionUsuario == false) {
					try {
		    			Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		}
			}
			
		};
		
		t.start();			
		
		try {
			t.join();					
//					Thread.sleep(1000);
		}catch(InterruptedException e) {}
		
	}

	private void secuenciaEnvioServidor(EnvidoServer server){
		if(chat.getText().equalsIgnoreCase("exit")){
			server.close();
			cp.removeAll();
			System.exit(11);
		}
		server.send("Servidor: " + chat.getText() + System.lineSeparator());
		caja_mensajes.append("Yo: " + chat.getText() + System.lineSeparator());
		chat.setText("");
	}

	private void secuenciaEnvioCliente(EnvidoClient client){
		if(chat.getText().equalsIgnoreCase("exit")){
			client.close();
			System.exit(12);
		}
		client.send("Cliente: " + chat.getText() + System.lineSeparator());
		caja_mensajes.append("Yo: " + chat.getText() + System.lineSeparator());
		chat.setText("");
	}

	private void secuenciaEntradaIP(){
		String ip = texto_nombre2.getText();
		EnvidoClient client = new EnvidoClient(ip,defaultPort);
		
		cp.removeAll();
		cp.revalidate();
		cp.repaint();
		
		JLabel chatear;
		chat = new JTextField("");
		cartel.setText("Escriba 'exit' en consola para abortar el programa.");
		
		caja_mensajes = new JTextArea("");
		caja_mensajes.setEditable(false);

		barrita = new JScrollPane(caja_mensajes);  
		barrita.setPreferredSize(new Dimension(400,400));
		barrita.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		chatear = new JLabel("Envie mensajes a su amigo: ");
		chat = new JTextField(chat.getText());
		chat.setPreferredSize(new Dimension(200,20));
		
		JButton ok3;
		ok3 = new JButton("ENVIAR");
		
		JPanel menu2 = new JPanel();
		menu2.setLayout(new BoxLayout(menu2, BoxLayout.X_AXIS));
		menu2.add(chatear);
		menu2.add(chat);
		menu2.add(ok3);
		
		JPanel principal2 = new JPanel();
		principal2.setLayout(new GridBagLayout());
		GridBagConstraints gbc3 = new GridBagConstraints();
		
		gbc3.gridx = 0;
		gbc3.gridy = 1;
		gbc3.gridwidth = 1;
		gbc3.gridheight = 1;
		gbc3.weighty = 1.0;
		principal2.add(menu2,gbc3);
		
		gbc3.gridx = 0;
		gbc3.gridy = 0;
		gbc3.gridwidth = 1;
		gbc3.gridheight = 1;
		principal2.add(barrita,gbc3);
		
		gbc3.gridx = 0;
		gbc3.gridy = 2;
		gbc3.gridwidth = 1;
		gbc3.gridheight = 1;
		principal2.add(cartel,gbc3);
		
		cp.add(principal2);

		chat.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10){
					secuenciaEnvioCliente(client);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}
		});
		
		ok3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				secuenciaEnvioCliente(client);
			}
		});
		Thread respuesta = new Thread(){
			public void run() {
				while(true){
					recibo = client.receive();
					caja_mensajes.append(recibo);
				}
			};
		};
		respuesta.start();
	}
    
}	//FIN INTERFAZ
