package modelo;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Vista extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JMenuBar menubar;
	private JMenu	menuCliente, menuEmpleado, menuLlamada;
	private JMenuItem menuItemCli1, menuItemCli2,menuItemEmp1, menuItemEmp2, menuItemLlam1, menuItemLlam2,
	menuItemLlam3, menuItemLlam4;
	private JLabel nombre, apellido, cliDni, empDni, valor, activo, fecha, fecha2, satisfaccion, CondSatisfaccion;
	private JRadioButton verdadero;
	private JButton boton1, boton2;
	private JTextField cajaNombre, cajaApellido, cliCajaDni, empCajaDni, cajaValor, cajaAño, cajaMes, cajaDia,  
	cajaAño2, cajaMes2, cajaDia2, cajaSatisfaccion;
	private JTextArea areaTexto;	
    private JScrollPane scroll;   
    private boolean display;
	private Sistema s = new Sistema();
	static int ancho = 800;
	static int alto = 600;
	static int centro = (ancho/2) - 100;
	
	public Vista(){
		this.setSize(ancho, alto);
		setLocationRelativeTo(null);
		
		iniciarComponentes();
	}
	private void iniciarComponentes() {
		colocarPaneles();
		colocarMenu();
		colocarMenuItems();
		cargarDatos();
	}
	private void colocarPaneles() {
		panel = new JPanel();
		panel.setBounds(10, 40, 100, 100);	
		panel.setLayout(null);
		this.getContentPane().add(panel);
	}
	private void colocarMenu() {
		menubar = new JMenuBar();
		setJMenuBar(menubar);
		panel.add(menubar);
		
		menuCliente = new JMenu("Clientes");
		menubar.add(menuCliente);
		
		menuEmpleado = new JMenu("Empleados");
		menubar.add(menuEmpleado);
		
		menuLlamada = new JMenu("Llamadas");
		menubar.add(menuLlamada);
	}
	private void colocarMenuItems() {
		//items-cliente
		menuItemCli1 = new JMenuItem("Agregar");
		menuItemCli1.addActionListener(this);
		menuCliente.add(menuItemCli1);
				
		menuItemCli2 = new JMenuItem("Traer");
		menuItemCli2.addActionListener(this);
		menuCliente.add(menuItemCli2);
				
		//items-empleado
		menuItemEmp1 = new JMenuItem("Agregar");
		menuItemEmp1.addActionListener(this);
		menuEmpleado.add(menuItemEmp1);
				
		menuItemEmp2 = new JMenuItem("Traer");
		menuItemEmp2.addActionListener(this);
		menuEmpleado.add(menuItemEmp2);
		
		//items-llamada
		menuItemLlam1 = new JMenuItem("Agregar");
		menuItemLlam1.addActionListener(this);
		menuLlamada.add(menuItemLlam1);
		
		menuItemLlam2 = new JMenuItem("Traer");
		menuItemLlam2.addActionListener(this);
		menuLlamada.add(menuItemLlam2);
		
		menuItemLlam3 = new JMenuItem("Traer por fecha/satisfaccion");
		menuItemLlam3.addActionListener(this);
		menuLlamada.add(menuItemLlam3);
		
		menuItemLlam4 = new JMenuItem("Traer por empleado");
		menuItemLlam4.addActionListener(this);
		menuLlamada.add(menuItemLlam4);
	}
	public void reset() {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		colocarMenu();
		colocarMenuItems();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == menuItemCli1) {
			panel.repaint();
			clienteAgregar();
		}
		if(e.getSource() == menuItemCli2) {
			panel.repaint();
			clienteTraer();
		}
		if(e.getSource() == menuItemEmp1) {
			panel.repaint();
			empleadoAgregar();
		}
		if(e.getSource() == menuItemEmp2) {
			panel.repaint();
			empleadoTraer();
		}
		if(e.getSource() == menuItemLlam1) {
			panel.repaint();
			llamadaAgregar();
		}
		if(e.getSource() == menuItemLlam2) {
			panel.repaint();
			llamadaTraer();
		}
		if(e.getSource() == menuItemLlam3) {
			panel.repaint();
			llamadaTraerFecha();
		}
		if(e.getSource() == menuItemLlam4) {
			panel.repaint();
			llamadaTraerEmpleado();
		}
	}
	
	private void clienteAgregar() {
		reset();
		nombre = new JLabel("Nombre");
		nombre.setBounds(100, 100, 50, 20);
		panel.add(nombre);
		
		cajaNombre = new JTextField();
		cajaNombre.setBounds(180, 100, 250, 20);
		panel.add(cajaNombre);
		
		apellido = new JLabel("Apellido");
		apellido.setBounds(100, 150, 100, 20);
		panel.add(apellido);
		
		cajaApellido = new JTextField();
		cajaApellido.setBounds(180, 150, 250, 20);
		panel.add(cajaApellido);
		
		cliDni = new JLabel("Documento");
		cliDni.setBounds(100, 200, 100, 20);
		panel.add(cliDni);
		
		cliCajaDni = new JTextField();
		cliCajaDni.setBounds(180, 200, 250, 20);
		panel.add(cliCajaDni);
		
		activo = new JLabel("Activo");
		activo.setBounds(120, 250, 250, 20);
		panel.add(activo);
		
		verdadero = new JRadioButton("Verdadero", false);
		verdadero.setBounds(180, 250, 100, 50);
		panel.add(verdadero);
		
		boton1 = new JButton();
		boton1.setText("Enviar");
		boton1.setBounds(260, 320, 100, 40);
		panel.add(boton1);
		
		ActionListener lAgregar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					long valor = 0;
					valor = Long.parseLong(cliCajaDni.getText());
					s.agregarCliente(cajaNombre.getText(), cajaApellido.getText(), valor, verdadero.isSelected());
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				cajaNombre.setText("");
				cajaApellido.setText("");
				cliCajaDni.setText("");
			}
		};
		boton1.addActionListener(lAgregar );	
	}
	private void clienteTraer() {

		reset();
		boton1 = new JButton();
		boton1.setText("Traer clientes");
		boton1.setBounds(centro, 100, 200, 40);
		panel.add(boton1);
		
		areaTexto = new JTextArea();
		areaTexto.setBounds(50, 150, 700, 200 );
		panel.add(areaTexto);
		
		ActionListener lAgregar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 
				String texto = s.traerClientes().toString();
				areaTexto.setText(texto);
			}
		};
		boton1.addActionListener(lAgregar );	
	}
	private void empleadoAgregar() {
		reset();
		nombre = new JLabel("Nombre");
		nombre.setBounds(100, 100, 50, 20);
		panel.add(nombre);
		
		cajaNombre = new JTextField();
		cajaNombre.setBounds(180, 100, 250, 20);
		panel.add(cajaNombre);
		
		apellido = new JLabel("Apellido");
		apellido.setBounds(100, 150, 200, 20);
		panel.add(apellido);
		
		cajaApellido = new JTextField();
		cajaApellido.setBounds(180, 150, 250, 20);
		panel.add(cajaApellido);
		
		empDni = new JLabel("Documento");
		empDni.setBounds(100, 190, 200, 20);
		panel.add(empDni);
		
		empCajaDni = new JTextField();
		empCajaDni.setBounds(180, 200, 250, 20);
		panel.add(empCajaDni);
		
		valor = new JLabel("Sueldo");
		valor.setBounds(100, 250, 50, 20);
		panel.add(valor);
		
		cajaValor = new JTextField();
		cajaValor.setBounds(180, 250, 250, 20);
		panel.add(cajaValor);
		
		boton1 = new JButton();
		boton1.setText("Enviar");
		boton1.setBounds(260, 300, 100, 40);
		panel.add(boton1);
		
		ActionListener lAgregar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					long valor = 0;
					valor = Long.parseLong(empCajaDni.getText());
					int valorS = 0;
					valorS = Integer.parseInt(cajaValor.getText());
					
					s.agregarEmpleado(cajaNombre.getText(), cajaApellido.getText(), valor, valorS);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				cajaNombre.setText("");
				cajaApellido.setText("");
				empCajaDni.setText("");
				cajaValor.setText("");
			}
		};
		boton1.addActionListener(lAgregar );	
	}
	private void empleadoTraer() {

		reset();
		
		boton1 = new JButton();
		boton1.setText("Traer clientes");
		boton1.setBounds(centro, 100, 200, 40);
		panel.add(boton1);
		
		areaTexto = new JTextArea();
		areaTexto.setBounds(50, 150, 700, 200 );
		panel.add(areaTexto);
		
		ActionListener lAgregar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 
				String texto = s.traerEmpleados().toString();
				areaTexto.setText(texto);
			}
		};
		boton1.addActionListener(lAgregar );	
	}
	private void llamadaAgregar() {
		reset();
		
		cliDni = new JLabel("Documento cliente");
		cliDni.setBounds(50, 100, 200, 20);
		panel.add(cliDni);
		
		cliCajaDni = new JTextField();
		cliCajaDni.setBounds(180, 100, 250, 20);
		panel.add(cliCajaDni);
		
		empDni = new JLabel("Documento empleado");
		empDni.setBounds(50, 150, 200, 20);
		panel.add(empDni);
		
		empCajaDni = new JTextField();
		empCajaDni.setBounds(180, 150, 250, 20);
		panel.add(empCajaDni);
		
		fecha = new JLabel("Fecha");
		fecha.setBounds(100, 200, 50, 20);
		panel.add(fecha);
		
		cajaAño = new JTextField();
		cajaAño.setBounds(180, 200, 50, 20);
		panel.add(cajaAño);
		
		cajaMes = new JTextField();
		cajaMes.setBounds(240, 200, 50, 20);
		panel.add(cajaMes);
		
		cajaDia = new JTextField();
		cajaDia.setBounds(300, 200, 50, 20);
		panel.add(cajaDia);
		
		valor = new JLabel("Nivel de satisfaccion");
		valor.setBounds(50, 250, 200, 20);
		panel.add(valor);
		
		cajaValor = new JTextField();
		cajaValor.setBounds(180, 250, 250, 20);
		panel.add(cajaValor);
		
		boton1 = new JButton();
		boton1.setText("Enviar");
		boton1.setBounds(260, 300, 100, 40);
		panel.add(boton1);
		
		ActionListener lAgregar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					long valorC = Long.parseLong(cliCajaDni.getText());
					long valorE = Long.parseLong(empCajaDni.getText());
					int año = Integer.parseInt(cajaAño.getText());
					int mes = Integer.parseInt(cajaMes.getText());
					int dia = Integer.parseInt(cajaDia.getText());
					int nivel = Integer.parseInt(cajaValor.getText());
					
					s.agregarLlamada(LocalDate.of(año, mes, dia), (Cliente) s.traerPersona(valorC), (Empleado) s.traerPersona(valorE), nivel);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				cajaNombre.setText("");
				cajaApellido.setText("");
				cliCajaDni.setText("");
				cajaValor.setText("");
			}
		};
		boton1.addActionListener(lAgregar );	
	}
	
	private void llamadaTraer() {

		reset();
		
		boton1 = new JButton();
		boton1.setText("Traer llamadas");
		boton1.setBounds(centro, 100, 200, 40);
		panel.add(boton1);
		
		areaTexto = new JTextArea();
		scroll = new JScrollPane(areaTexto,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		scroll.setBounds(new Rectangle(50, 150, 700, 250));  
		panel.add(scroll);
		
		ActionListener lAgregar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String texto = s.getLstLlamadas().toString();
				areaTexto.setText(texto);
			}
		};
		boton1.addActionListener(lAgregar );	
	}
	private void llamadaTraerFecha() {

		reset();
		
		fecha = new JLabel("Fecha desde");
		fecha.setBounds(100, 50, 150, 20);
		panel.add(fecha);
		
		cajaAño = new JTextField();
		cajaAño.setBounds(180, 50, 50, 20);
		panel.add(cajaAño);
		
		cajaMes = new JTextField();
		cajaMes.setBounds(240, 50, 50, 20);
		panel.add(cajaMes);
		
		cajaDia = new JTextField();
		cajaDia.setBounds(300, 50, 50, 20);
		panel.add(cajaDia);
		
		fecha2 = new JLabel("Fecha hasta");
		fecha2.setBounds(100, 100, 150, 20);
		panel.add(fecha2);
		
		cajaAño2 = new JTextField();
		cajaAño2.setBounds(180, 100, 50, 20);
		panel.add(cajaAño2);
		
		cajaMes2 = new JTextField();
		cajaMes2.setBounds(240, 100, 50, 20);
		panel.add(cajaMes2);
		
		cajaDia2 = new JTextField();
		cajaDia2.setBounds(300, 100, 50, 20);
		panel.add(cajaDia2);
		
		CondSatisfaccion = new JLabel("Filtrar por satisfaccion");
		CondSatisfaccion.setBounds(100, 150, 300, 50);
		panel.add(CondSatisfaccion);
		
		boton2 = new JButton();
		boton2.setText("Ok");
		boton2.setBounds(250, 150, 100, 50);
		panel.add(boton2);
		
		boton1 = new JButton();
		boton1.setText("Traer llamadas");
		boton1.setBounds(centro, 220, 200, 40);
		panel.add(boton1);
		
		areaTexto = new JTextArea();
		scroll = new JScrollPane(areaTexto,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		scroll.setBounds(new Rectangle(50, 270, 700, 250));  
		panel.add(scroll);
		
		display = false;
		
		ActionListener lSatisfaccion = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset();
				llamadaTraerFecha();
				
				satisfaccion = new JLabel("Nivel satisfaccion");
				satisfaccion.setBounds(400, 165, 200, 20);
				panel.add(satisfaccion);
				
				cajaSatisfaccion = new JTextField();
				cajaSatisfaccion.setBounds(530, 165, 20, 20);
				panel.add(cajaSatisfaccion);
				
				display=true;
			}
		};
		boton2.addActionListener(lSatisfaccion );
		
		ActionListener lAgregar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int año = Integer.parseInt(cajaAño.getText());
				int mes = Integer.parseInt(cajaMes.getText());
				int dia = Integer.parseInt(cajaDia.getText());
				
				int año2 = Integer.parseInt(cajaAño2.getText());
				int mes2 = Integer.parseInt(cajaMes2.getText());
				int dia2 = Integer.parseInt(cajaDia2.getText());
				
				if(display == false) {
					String texto = s.traerLlamada(LocalDate.of(año, mes, dia), LocalDate.of(año2, mes2, dia2)).toString();
					areaTexto.setText(texto);
				}
				else {
					int valor = Integer.parseInt(cajaSatisfaccion.getText());
					String texto = s.traerLlamada(LocalDate.of(año, mes, dia), LocalDate.of(año2, mes2, dia2), valor).toString();
					areaTexto.setText(texto);
				}
			}
		};
		
		boton1.addActionListener(lAgregar );	
	}
	private void llamadaTraerEmpleado() {

		reset();
		
		fecha = new JLabel("Fecha desde");
		fecha.setBounds(100, 50, 150, 20);
		panel.add(fecha);
		
		cajaAño = new JTextField();
		cajaAño.setBounds(180, 50, 50, 20);
		panel.add(cajaAño);
		
		cajaMes = new JTextField();
		cajaMes.setBounds(240, 50, 50, 20);
		panel.add(cajaMes);
		
		cajaDia = new JTextField();
		cajaDia.setBounds(300, 50, 50, 20);
		panel.add(cajaDia);
		
		fecha2 = new JLabel("Fecha hasta");
		fecha2.setBounds(100, 100, 150, 20);
		panel.add(fecha2);
		
		cajaAño2 = new JTextField();
		cajaAño2.setBounds(180, 100, 50, 20);
		panel.add(cajaAño2);
		
		cajaMes2 = new JTextField();
		cajaMes2.setBounds(240, 100, 50, 20);
		panel.add(cajaMes2);
		
		cajaDia2 = new JTextField();
		cajaDia2.setBounds(300, 100, 50, 20);
		panel.add(cajaDia2);
		
		empDni = new JLabel("Documento");
		empDni.setBounds(100, 150, 50, 20);
		panel.add(empDni);
		
		empCajaDni = new JTextField();
		empCajaDni.setBounds(160, 150, 250, 20);
		panel.add(empCajaDni);
		
		CondSatisfaccion = new JLabel("Filtrar por satisfaccion");
		CondSatisfaccion.setBounds(100, 200, 300, 50);
		panel.add(CondSatisfaccion);
		
		
		boton2 = new JButton();
		boton2.setText("Ok");
		boton2.setBounds(250, 200, 100, 50);
		panel.add(boton2);
		
		boton1 = new JButton();
		boton1.setText("Traer llamadas");
		boton1.setBounds(centro, 270, 200, 40);
		panel.add(boton1);
		
		areaTexto = new JTextArea();
		scroll = new JScrollPane(areaTexto,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		scroll.setBounds(new Rectangle(50, 320, 700, 210));  
		panel.add(scroll);
		
		display = false;
		
		ActionListener lSatisfaccion = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset();
				llamadaTraerEmpleado();
				
				satisfaccion = new JLabel("Nivel satisfaccion");
				satisfaccion.setBounds(400, 210, 200, 20);
				panel.add(satisfaccion);
				
				cajaSatisfaccion = new JTextField();
				cajaSatisfaccion.setBounds(530, 210, 20, 20);
				panel.add(cajaSatisfaccion);
				
				display=true;
			}
		};
		boton2.addActionListener(lSatisfaccion );
		
		ActionListener lAgregar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int año = Integer.parseInt(cajaAño.getText());
				int mes = Integer.parseInt(cajaMes.getText());
				int dia = Integer.parseInt(cajaDia.getText());
				
				int año2 = Integer.parseInt(cajaAño2.getText());
				int mes2 = Integer.parseInt(cajaMes2.getText());
				int dia2 = Integer.parseInt(cajaDia2.getText());
				
				long valor = 0;
				valor = Long.parseLong(empCajaDni.getText());
				
				if(display == false) {
					String texto = s.traerLlamada(LocalDate.of(año, mes, dia), LocalDate.of(año2, mes2, dia2), (Empleado) s.traerPersona(valor)).toString();
					areaTexto.setText(texto);
				}
				else {
					int valorS = Integer.parseInt(cajaSatisfaccion.getText());
					String texto = s.traerLlamada(LocalDate.of(año, mes, dia), LocalDate.of(año2, mes2, dia2), (Empleado) s.traerPersona(valor), valorS).toString();
					areaTexto.setText(texto);
				}
			}
		};
		
		boton1.addActionListener(lAgregar );	
	}
	public void cargarDatos() {
		try {
			s.agregarCliente("Lula", "Perez", 11111111,true);
			s.agregarCliente("Lucas", "Pereyra", 22222222, false);
			s.agregarEmpleado("Pedro", "Lopez", 33333333, 10000);
			s.agregarEmpleado("Pablo", "Gomez", 44444444, 9000);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			s.agregarLlamada(LocalDate.of(2018, 02, 19), (Cliente) s.traerPersona(11111111), (Empleado) s.traerPersona(33333333), 5);
			s.agregarLlamada(LocalDate.of(2018, 02, 19), (Cliente) s.traerPersona(11111111), (Empleado) s.traerPersona(33333333), 5);
			s.agregarLlamada(LocalDate.of(2018, 02, 19), (Cliente) s.traerPersona(11111111), (Empleado) s.traerPersona(33333333), 5);
			s.agregarLlamada(LocalDate.of(2018, 02, 19), (Cliente) s.traerPersona(11111111), (Empleado) s.traerPersona(33333333), 5);
			s.agregarLlamada(LocalDate.of(2018, 02, 19), (Cliente) s.traerPersona(11111111), (Empleado) s.traerPersona(44444444), 5);
			s.agregarLlamada(LocalDate.of(2018, 02, 19), (Cliente) s.traerPersona(22222222), (Empleado) s.traerPersona(33333333), 5);
			s.agregarLlamada(LocalDate.of(2018, 02, 20), (Cliente) s.traerPersona(11111111), (Empleado) s.traerPersona(33333333), 4);
			s.agregarLlamada(LocalDate.of(2018, 02, 20), (Cliente) s.traerPersona(11111111), (Empleado) s.traerPersona(33333333), 4);
			s.agregarLlamada(LocalDate.of(2018, 02, 20), (Cliente) s.traerPersona(11111111), (Empleado) s.traerPersona(33333333), 3);
			s.agregarLlamada(LocalDate.of(2018, 02, 20), (Cliente) s.traerPersona(11111111), (Empleado) s.traerPersona(33333333), 3);
			s.agregarLlamada(LocalDate.of(2018, 02, 20), (Cliente) s.traerPersona(11111111), (Empleado) s.traerPersona(33333333), 2);
			s.agregarLlamada(LocalDate.of(2018, 02, 21), (Cliente) s.traerPersona(11111111), (Empleado) s.traerPersona(33333333), 5);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
