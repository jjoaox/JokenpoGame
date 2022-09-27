package bean;

import javax.faces.bean.ManagedBean;
import dao.JogadaDAO;
import entidade.Jogada;
import static util.MessageUtil.addErrorMessage;
import static util.MessageUtil.addInfoMessage;

import java.util.List;

@ManagedBean
public class JogadaBean {
	private Jogada jogada = new Jogada();
	private List<Jogada> jogadas;
	public String salvar() {
		try {
			JogadaDAO.salvar(jogada);
			addInfoMessage("Sucesso", "Jogada salva com sucesso.");
			jogada = new Jogada();
		} catch (Exception e) {
			addErrorMessage("Erro", "Erro ao salvar a jogada.");
		}
		
		return null;
	}
	
	public String deletar() {
		try {
			JogadaDAO.deletar(jogada);
			addInfoMessage("Jogada deletada", "Jogada deletada com sucesso."); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			addErrorMessage("Erro", "Erro ao deletar jogada.");
		}
		return null;	
	}
	
	public String atualizar() {
		try {
			
			addInfoMessage("Sucesso", "Jogada atualizada com sucesso.");
		} catch (Exception e) {
			addErrorMessage("Erro", "Erro ao atualizar jogada.");
		}
		return null;
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
