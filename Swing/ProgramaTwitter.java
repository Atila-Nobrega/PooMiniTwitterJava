package myTwitter.Swing;

import myTwitter.exceptions.*;
import myTwitter.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProgramaTwitter {
	
	private JFrame frame = new JFrame("MyTwitter: Átila_Nóbrega_473754");
	
	//Arquivo:
	ArquivoTwitter arquivo;
	
	//Labels:
	private JLabel texto1 = new JLabel("Usuário Utilizado: ");
	private JLabel texto2 = new JLabel("Lista de Cadastrados: ");
	
	//TextField:
	private JTextField digite = new JTextField(15);
	
	//Table:
	private JTable table = new JTable();
	private DefaultTableModel model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Usu\u00E1rio", "CPF/CNPJ", "Tipo", "Ativo"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
	
	//buttons:
	private JButton cria = new JButton("Criar Perfil");
	private JButton cancela = new JButton("Cancelar Perfil");
	private JButton tweetar = new JButton("Tweetar");
	private JButton timeline = new JButton("Ver Timeline");
	private JButton tweets = new JButton("Ver Tweets");
	private JButton seguir = new JButton("Seguir");
	private JButton numeroseg = new JButton("Ver número de seguidores");
	private JButton seguidores = new JButton("Ver Seguidores");
	private JButton seguidos = new JButton("Ver Seguidos");
	private JButton update = new JButton("Atualizar a lista");
	
	//Panels:
	private JPanel panelleft = new JPanel();
	private JPanel panelup = new JPanel();
	private JPanel panelright = new JPanel();
	
	public RepositorioVector repositorio = new RepositorioVector();
	public MyTwitter mytwitter = new MyTwitter(repositorio); 
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnNewMenu = new JMenu("Arquivo");
	private final JMenuItem salvar = new JMenuItem("Save");
	private final JMenuItem carregar = new JMenuItem("Load");
	
	public ProgramaTwitter() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Frame Setup:
		frame.setSize(900, 600);
		frame.setLocation(200,200);
		frame.setResizable(false);
		
		
		//Panels Setup:
		panelup.setLayout(new FlowLayout(FlowLayout.LEADING));
		panelright.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelright.setPreferredSize(new Dimension(450,800));
		panelleft.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelleft.setLayout(new GridLayout(5,2,5,5));
		
		//Label Setup:
		texto1.setFont(new Font("Arial", Font.PLAIN, 18));
		texto2.setFont(new Font("Arial", Font.PLAIN, 18));
		panelup.add(texto1);
		panelright.add(texto2);
		
		
		//TextField Setup:
		panelup.add(digite);
		
		
		//Table Setup:
		table = new JTable();
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.setPreferredScrollableViewportSize(new Dimension(400,300));
		table.setFillsViewportHeight(true);
		JScrollPane scrollpane = new JScrollPane(table);
		panelright.add(scrollpane);
		
		
		//Button Setup:
		panelleft.add(cria);
		cria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vector<String> information = new Vector<String>();
					String inputValue = JOptionPane.showInputDialog("Escolha um nome de usúario:");
					if(inputValue != null && !inputValue.isEmpty()) {
						information.add(inputValue);
						Object[] options = { "Pessoa Fisica", "Pessoa Juridica" };
					     int x = JOptionPane.showOptionDialog(null, "Escolha o tipo de Perfil", "Aviso",
					          JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					              null, options, options[0]);
					     if(x==0) {
					    	 inputValue = JOptionPane.showInputDialog("Digite o CPF:");
					    	 if(inputValue != null && !inputValue.isEmpty()) {
					    		 information.add(inputValue);
					    		 information.add("Fisica");
					    		 PessoaFisica usuario = new PessoaFisica(information.get(0));
					    		 ((PessoaFisica) usuario).setCpf(Long.parseLong(information.get(1)));
					    		 try {
					    			 mytwitter.criarPerfil(usuario);
					    			 JOptionPane.showMessageDialog(null, "Perfil Criado!");
					    			 update.doClick();
					    		 }
					    		 catch(PEException e1) {
					    			 JOptionPane.showMessageDialog(null, e1.getMessage());
					    		 }
					    	 }
					    	 else {
					    		 JOptionPane.showMessageDialog(null, "Cancelado!");
					    	 }
					     }
					     else if(x==1) {
					    	 inputValue = JOptionPane.showInputDialog("Digite o CNPJ:");
					    	 if(inputValue != null && !inputValue.isEmpty()) {
					    		 information.add(inputValue);
					    		 information.add("Juridica");
					    		 PessoaJuridica usuario = new PessoaJuridica(information.get(0));
					    		 ((PessoaJuridica) usuario).setCnpj(Long.parseLong(information.get(1)));
					    		 try {
					    			 mytwitter.criarPerfil(usuario);
					    			 JOptionPane.showMessageDialog(null, "Perfil Criado!");
					    			 update.doClick();
					    		 }
					    		 catch(PEException e1) {
					    			 JOptionPane.showMessageDialog(null, e1.getMessage());
					    		 }
					    	 }
					    	 else {
					    		 JOptionPane.showMessageDialog(null, "Cancelado!");
					    	 }
					     }
					     else {
					    	 JOptionPane.showMessageDialog(null, "Cancelado!");
					     }
					}
					else {
						JOptionPane.showMessageDialog(null, "Cancelado!");
					}
				}
				catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Digite apenas números para o CPF/CNPJ!");
				}
			}
		});
		
		panelleft.add(cancela);
		cancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String usuario = digite.getText();
					mytwitter.cancelarPerfil(repositorio.buscar(usuario));
					update.doClick();
				}
				catch(PIException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				catch(PDException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		
		panelleft.add(tweetar);
		tweetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = JOptionPane.showInputDialog(digite.getText() + " Digite seu Tweet:");
				if(texto != "") {
					try {
						mytwitter.tweetar(digite.getText(), texto);						
					}
					catch(PIException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					catch(MFPException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Cancelado!");
				}
			}
		});
		
		panelleft.add(timeline);
		timeline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vector<Tweet> tweets = mytwitter.timeline(digite.getText());
					new CaixaTexto(digite.getText(), tweets);
				}
				catch(PIException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				catch(PDException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
				
		panelleft.add(tweets);
		tweets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vector<Tweet> tweets = mytwitter.tweets(digite.getText());
					new CaixaTexto(digite.getText(), tweets);
				}
				catch(PIException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				catch(PDException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		
		panelleft.add(seguir);
		seguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String seguido = JOptionPane.showInputDialog("Escolha quem " + digite.getText() + " deseja seguir:");
					mytwitter.seguir(digite.getText(), seguido);
					JOptionPane.showMessageDialog(null, "Usuário " + seguido + " Seguido!");
				}
				catch(PIException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				catch(PDException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				catch(SIException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		
		panelleft.add(numeroseg);
		numeroseg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int numero = mytwitter.numeroSeguidores(digite.getText());
					JOptionPane.showMessageDialog(null, "Número de seguidores de " + digite.getText() + " = " + numero);
				}
				catch(PIException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				catch(PDException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		
		panelleft.add(seguidores);
		seguidores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vector<Perfil> perfis = mytwitter.seguidoresUsuario(digite.getText());
					new CaixaTexto(perfis, digite.getText(), "Seguidores de ");
				}
				catch(PIException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				catch(PDException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		
		panelleft.add(seguidos);
		seguidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vector<Perfil> perfis = mytwitter.seguidos(digite.getText());
					new CaixaTexto(perfis, digite.getText(), "Seguidos por ");
				}
				catch(PIException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				catch(PDException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		
		panelleft.add(update);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				String linha[] = new String[4];
				for(Perfil perfil: repositorio.perfis) {
					linha[0] = perfil.getUsuario();
					if(perfil instanceof PessoaFisica) {
						linha[1] =  Long.toString(((PessoaFisica) perfil).getCpf());
						linha[2] = "Fisica";
					}
					else {
						linha[1] =  Long.toString(((PessoaJuridica) perfil).getCnpj());
						linha[2] = "Juridica";
					}
					if(perfil.isAtivo() == true) {
						linha[3] = "Sim";
					}
					else {
						linha[3] = "não";
					}
					model.addRow(linha);
				}
			}
		});	
		
		//Menu Setup:
		menuBar.add(mnNewMenu);
		mnNewMenu.add(salvar);
		salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arquivo = new ArquivoTwitter();
				try {
					arquivo.Escrever(repositorio);
				} catch (ESException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
		mnNewMenu.add(carregar);
		carregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arquivo = new ArquivoTwitter();
				try {
					repositorio = arquivo.Ler();
					mytwitter = new MyTwitter(repositorio);
					update.doClick();
				} catch (ELException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		
		
		//Add objects to screen:
		frame.getContentPane().add(panelleft, BorderLayout.WEST);
		frame.getContentPane().add(panelright, BorderLayout.EAST);
		frame.getContentPane().add(panelup, BorderLayout.NORTH);
		frame.setJMenuBar(menuBar);
			
		
		frame.setVisible(true);
	}
}
