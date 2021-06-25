package myTwitter;

import java.util.Vector;

import myTwitter.exceptions.*;

public interface ITwitter {
	
	public void criarPerfil(Perfil usuario) throws PEException;
	
	public void cancelarPerfil(Perfil usuario) throws PIException, PDException;
	
	public void tweetar(String usuario, String mensagem) throws PIException, MFPException;
	
	public Vector<Tweet> timeline(String usuario) throws PIException, PDException;
	
	public Vector<Tweet> tweets(String usuario) throws PIException, PDException;
	
	public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException;
	
	public int numeroSeguidores(String usuario) throws PIException, PDException;
	
	public Vector<Perfil> seguidoresUsuario(String usuario) throws PIException, PDException;	
	
	public Vector<Perfil> seguidos(String usuario) throws PIException, PDException;
	
}
