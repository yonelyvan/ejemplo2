package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class formatos extends JFrame {

	private JPanel contentPane;
	private JTextField tf_apellidos;
	private JTextField tf_nombres;
	
	
	private JTextField tf_error;
	private JTextField tf_dni;
	
	JComboBox cb_dia;
	JComboBox cb_mes;
	JComboBox cb_year;
	
	
	
	private String[] MES_name = { "ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE" };
	private String[] MES_num={"01","02","03","04","05","06","07","08","09","10","11","12"};
	private String[] DIAS= new String[31];
	private String[] YEARS= new String[31];
	private Map<String, String> MES_name_num = new HashMap<String, String>();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formatos frame = new formatos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public formatos() throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel panel = new JPanel();		
		
	//DNI
		JLabel lblDni = new JLabel("DNI * :");
		tf_dni = new JTextField();
		tf_dni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				String str=tf_dni.getText();
				if( !Character.isDigit(c) ){ 
					e.consume(); 
				}
				if( str.length()> 7 ){ //numero maximo de digitos de un DNI 
					e.consume(); 
				}
			}
		});
		tf_dni.setColumns(7);
		
	//Apellidos 
		JLabel lblApellidos = new JLabel("Apellidos :");
		tf_apellidos = new JTextField();
		tf_apellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				String str=tf_apellidos.getText();
				if( Character.isLetter(c) ){ 
					e.setKeyChar(Character.toUpperCase(c));
				}
				if( str.length()> 30 ){
					e.consume(); 
				}
			}
		});
		tf_apellidos.setColumns(18);
	
	//Nombres
		JLabel lblNombres = new JLabel("Nombres :");
		tf_nombres = new JTextField();
		tf_nombres.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				String str=tf_nombres.getText();
				if( Character.isLetter(c) ){ 
					e.setKeyChar(Character.toUpperCase(c));
				}
				if( str.length()> 30 ){
					e.consume(); 
				}
			}
		});
		tf_nombres.setColumns(18);
			
	//fecha de nacimiento
		JLabel lblFechaDeNacimiento = new JLabel("fecha de nacimiento: ");
		cargar_data_fecha(); 
		cb_dia = new JComboBox(DIAS);
		cb_mes = new JComboBox(MES_name);
		cb_year = new JComboBox(YEARS);
		
		
	    
			
	//boton imprimir
		JButton btnPrint = new JButton("print");
			btnPrint.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					System.out.println( "dni:       " + tf_dni.getText() );
					System.out.println( "apellidos: " + tf_apellidos.getText() );
					System.out.println( "nombres:   " + tf_nombres.getText() );
					System.out.println( "fecha:     " + cb_year.getSelectedItem()+"-"+ MES_name_num.get( cb_mes.getSelectedItem() )+"-"+cb_dia.getSelectedItem() );
					
				}
			});
		
		JLabel lbl_error =new JLabel("errores");
		tf_error=new JTextField();
			tf_error.setColumns(20);;
			
		
		contentPane.add(panel);
			panel.add(lblDni);
			panel.add(tf_dni);
			panel.add(lblApellidos);
			panel.add(tf_apellidos);
			panel.add(lblNombres);
			panel.add(tf_nombres);
			panel.add(lblFechaDeNacimiento);
			panel.add(cb_dia);
			panel.add(cb_mes);
			panel.add(cb_year);
			panel.add(btnPrint);
			panel.add(lbl_error);
			panel.add(tf_error);
			
	}

	private void cargar_data_fecha(){ 
		Calendar fecha = Calendar.getInstance();
	    int yy = fecha.get(Calendar.YEAR); 			//año actual
		for(int i=0;i<=30;++i){ 					//cargamos dias rango[1:31] y años rango[año_actual-5 : año_actual-5-31]
			DIAS[i]=Integer.toString(i+1);
			YEARS[i]=Integer.toString( (yy-5)-i );
		}
		for(int j=0;j<=11; ++j){ 					//indexamos mes con numero respectivo
			MES_name_num.put(MES_name[j], Integer.toString(j+1)); 
		}
	}
	
}
