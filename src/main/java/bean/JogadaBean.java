package bean;

import javax.faces.bean.ManagedBean;
import dao.JogadaDAO;
import entidade.Jogada;
import static util.MessageUtil.addErrorMessage;
import static util.MessageUtil.addInfoMessage;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@ManagedBean
public class JogadaBean{
	private Jogada jogada = new Jogada();
	private List<Jogada> jogadas;
	
	public String salvar() {
		try {
			List<String> opcaoJogadas = Arrays.asList("Pedra","Papel","Tesoura");
			Random rand = new Random();
			jogada.setJogada1(opcaoJogadas.get(rand.nextInt(opcaoJogadas.size())));
			jogada.setJogada2(opcaoJogadas.get(rand.nextInt(opcaoJogadas.size())));
			if(jogada.getJogada1() == "Papel" && jogada.getJogada2() == "Tesoura") {
				jogada.setResultado(jogada.getJogador2());
			}else if(jogada.getJogada1() == "Papel" && jogada.getJogada2() == "Pedra"){
				jogada.setResultado(jogada.getJogador1());
			}else if(jogada.getJogada1() == "Pedra" && jogada.getJogada2() == "Tesoura"){
				jogada.setResultado(jogada.getJogador1());
			}else if(jogada.getJogada1() == "Tesoura" && jogada.getJogada2() == "Papel") {
				jogada.setResultado(jogada.getJogador1());
			}else if(jogada.getJogada1() == "Pedra" && jogada.getJogada2() == "Papel"){
				jogada.setResultado(jogada.getJogador2());
			}else if(jogada.getJogada1() == "Tesoura" && jogada.getJogada2() == "Pedra"){
				jogada.setResultado(jogada.getJogador2());
			}else {
				jogada.setResultado("Empate");
			}
			jogada.setData(new Date());
			JogadaDAO.salvar(jogada);
			addInfoMessage("Sucesso", "Jogada salva com sucesso.");
			jogada = new Jogada();
		} catch (Exception e) {
			addErrorMessage("Erro", "Erro ao salvar a jogada.");
		}
		
		return null;
	}
	
	public String deletar(Jogada jogada) {
		try {
			JogadaDAO.deletar(jogada);
			addInfoMessage("Sucesso", "Jogada deletada com sucesso."); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			addErrorMessage("Erro", "Erro ao deletar jogada.");
		}
		return null;	
	}
	
	
	
	public void atualizar() {
		try {
				if(jogada.isEditando()==false){
					jogada.setEditando(true);
					addInfoMessage("Sucesso", "Jogada atualizada com sucesso.");
					jogada.setEditando(false);
				}
		} catch (Exception e) {
			addErrorMessage("Erro", "Erro ao atualizar jogada.");
		}
	}

	public Jogada getJogada() {
		return jogada;
	}
	public void setJogada(Jogada jogada) {
		this.jogada = jogada;
	}
	public List<Jogada> getJogadas() {
		if (jogadas == null) {
			jogadas = JogadaDAO.listarJogadas();
		}
		return jogadas;
	}
	public void setJogadas(List<Jogada> jogadas) {
		this.jogadas = jogadas;
	}
	
}
