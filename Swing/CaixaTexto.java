package myTwitter.Swing;

import java.util.Vector;

import javax.swing.JFrame;

import myTwitter.*;
import java.awt.BorderLayout;
import java.awt.Dimension;


import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class CaixaTexto {
	
	public CaixaTexto(String usuario, Vector<Tweet> tweets) {
		JFrame frame = new JFrame("Timeline de " + usuario);
		frame.setLocation(400, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 500);
		frame.setResizable(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(300, 400));;
		frame.getContentPane().add(scrollPane, BorderLayout.NORTH);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		
		for(Tweet tweet: tweets) {
			textArea.append(tweet.getUsuario() + ": " + tweet.getMensagem() + "\n");
		}
		
		
		
		
		
		frame.setVisible(true);
	}
	
	public CaixaTexto(Vector<Perfil> perfis, String usuario, String titulo) {
		JFrame frame = new JFrame(titulo + usuario + ":");
		frame.setLocation(400, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(180, 200);
		frame.setResizable(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(300, 400));;
		frame.getContentPane().add(scrollPane, BorderLayout.NORTH);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		
		textArea.append(titulo + usuario + ":" + "\n");
		for(Perfil perfil: perfis) {
			textArea.append(perfil.getUsuario() + "\n");
		}
		
		
		
		
		
		frame.setVisible(true);
	}
	
}
