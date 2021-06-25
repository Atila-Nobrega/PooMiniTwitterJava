package myTwitter.Swing;

import myTwitter.*;
import myTwitter.exceptions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ArquivoTwitter {
	
	public ArquivoTwitter() {
		
	}
	
	public void Escrever(RepositorioVector repositorio) throws ESException {
		JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home")));
		chooser.setDialogTitle("Loading a File");
		chooser.setFileFilter(new FileNameExtensionFilter("Arquivo .txt", ".txt"));
		int result = chooser.showSaveDialog(null);			
		if(result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				ObjectOutputStream os;
				os = new ObjectOutputStream(new FileOutputStream(file));
				try {
					os.writeObject(repositorio);
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new ESException();
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				throw new ESException();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw new ESException();
			}
		}
		else {
			throw new ESException();			
		}
		
	}
	
	
	public RepositorioVector Ler() throws ELException{
		JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home")));
		chooser.setDialogTitle("Loading a File");
		chooser.setFileFilter(new FileNameExtensionFilter("Arquivos .txt", "txt"));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = chooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				ObjectInputStream is;
				is = new ObjectInputStream(new FileInputStream(file));
				RepositorioVector repositorio = (RepositorioVector) is.readObject();
				is.close();
				return repositorio;
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				throw new ELException();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw new ELException();
			} catch (ClassNotFoundException e1) {
				throw new ELException();
			}
		}
		else {
			throw new ELException();			
		}
	}
	
}
