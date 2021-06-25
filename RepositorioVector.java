package myTwitter;

import java.io.Serializable;
import java.util.Vector;
import myTwitter.exceptions.*;

public class RepositorioVector implements IRepositorioUsuario, Serializable {
	public Vector<Perfil> perfis;
	
	public RepositorioVector() {
		perfis = new Vector<Perfil>();
	}
	
	public void cadastrar(Perfil usuario) throws UJCException {
		Perfil perfil = buscar(usuario.getUsuario());
		if (perfil == null) {
			perfis.add(usuario);
		}
		else if (usuario.getUsuario().equals(perfil.getUsuario())) {
			throw new UJCException();
		}
		else {
			perfis.add(usuario);				
		}
	}
	
	public Perfil buscar(String usuario) {
		for(Perfil perfil : perfis) {
		      if(perfil.getUsuario().equals(usuario)) {
		        return perfil;
		      }
		    }
		return null;
	}
	
	
	//Verificar esse metodo com excessão na hora de usar arquivo.
	public void atualizar(Perfil usuario) {
		try {
			Perfil perfil = buscar(usuario.getUsuario());
			if(usuario.getUsuario() == perfil.getUsuario()) {
				perfil = usuario;
			}
			else {
				throw new UNCException();				
			}
		} catch(UNCException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
}
