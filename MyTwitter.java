package myTwitter;

import java.util.Vector;
import myTwitter.exceptions.*;

public class MyTwitter implements ITwitter {
	
	IRepositorioUsuario repositorio = null;
	
	public MyTwitter(IRepositorioUsuario repositorio) {
		this.repositorio = repositorio;
	}
	
	
	public void criarPerfil(Perfil usuario) throws PEException {
		try {
			repositorio.cadastrar(usuario);
		}
		catch(UJCException e) {
			throw new PEException();
		}
		
		
	}

	@Override
	public void cancelarPerfil(Perfil usuario) throws PIException, PDException {
		if(usuario == null) {
			throw new PIException();
		}
		if(repositorio.buscar(usuario.getUsuario()) == null) {
			throw new PIException();
		}
		else if(repositorio.buscar(usuario.getUsuario()).isAtivo() == false){
			throw new PDException();
		}
		else {
			repositorio.buscar(usuario.getUsuario()).setAtivo(false);
		}
	}

	@Override
	public void tweetar(String usuario, String mensagem) throws PIException, MFPException {
		if(usuario == null) {
			throw new PIException();
		}
		Perfil perfil = repositorio.buscar(usuario);
		if(perfil == null) {
			throw new PIException();
		}
		// Testar este indice!
		else if(mensagem.length() > 140 || mensagem.length() < 1) {
			throw new MFPException();
		}
		else {
			Tweet tweet = new Tweet(usuario, mensagem);
			perfil.addTweet(tweet);
			for(Perfil seguidor : perfil.getSeguidores()) {
				seguidor.addTweet(tweet);
			}
		}
	}

	@Override
	public Vector<Tweet> timeline(String usuario) throws PIException, PDException {
		if(usuario == null) {
			throw new PIException();
		}
		Perfil perfil = repositorio.buscar(usuario);
		if(perfil == null) {
			throw new PIException();
		}
		else if(perfil.isAtivo() == false) {
			throw new PDException();
		}
		return perfil.getTimeline();
	}

	@Override
	public Vector<Tweet> tweets(String usuario) throws PIException, PDException{
		if(usuario == null) {
			throw new PIException();
		}
		Perfil perfil = repositorio.buscar(usuario);
		Vector<Tweet> vector = new Vector<Tweet>();
		if(perfil == null) {
			throw new PIException();
		}
		else if(perfil.isAtivo() == false) {
			throw new PDException();
		}
		for(Tweet tweet: perfil.getTimeline()) {
			if(tweet.getUsuario().equals(usuario)) {
				vector.add(tweet);
			}
		}
		return vector;
	}

	@Override
	public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException{
		if(seguidor == null || seguido == null) {
			throw new PIException();
		}
		Perfil perfilSeguidor = repositorio.buscar(seguidor);
		Perfil perfilSeguido = repositorio.buscar(seguido);
		if(perfilSeguido == null || perfilSeguidor == null) {
			throw new PIException();
		}
		else if(perfilSeguido.isAtivo() == false || perfilSeguidor.isAtivo() == false) {
			throw new PDException();
		}
		else if(perfilSeguido.getUsuario().equals(perfilSeguidor.getUsuario())) {
			throw new SIException();
		}
		for(Perfil existente: perfilSeguidor.getSeguido()) {
			if(existente.getUsuario().equals(perfilSeguido.getUsuario())) {
				throw new SIException();
			}
		}
		perfilSeguido.addSeguidor(perfilSeguidor);
		perfilSeguidor.addSeguido(perfilSeguido);
		
	}

	@Override
	public int numeroSeguidores(String usuario) throws PIException, PDException{
		if(usuario == null) {
			throw new PIException();
		}
		Perfil perfil = repositorio.buscar(usuario);
		if(perfil == null) {
			throw new PIException();
		}
		else if(perfil.isAtivo() == false) {
			throw new PDException();
		}
		else {
			return perfil.getSeguidores().size();			
		}
	}

	@Override
	public Vector<Perfil> seguidoresUsuario(String usuario) throws PIException, PDException {
		if(usuario == null) {
			throw new PIException();
		}
		Perfil perfil = repositorio.buscar(usuario);
		if(perfil == null) {
			throw new PIException();
		}
		else if(perfil.isAtivo() == false) {
			throw new PDException();
		}
		else {
			return perfil.getSeguidores();
		}
	}

	@Override
	public Vector<Perfil> seguidos(String usuario) throws PIException, PDException {
		if(usuario == null) {
			throw new PIException();
		}
		Perfil perfil = repositorio.buscar(usuario);
		if(perfil == null) {
			throw new PIException();
		}
		else if(perfil.isAtivo() == false) {
			throw new PDException();
		}
		else {
			return perfil.getSeguido();
		}
	}

}
