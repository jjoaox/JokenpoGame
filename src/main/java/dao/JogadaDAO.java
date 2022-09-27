package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Jogada;
import util.JPAUtil;

public class JogadaDAO {
	public static void salvar(Jogada j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(j);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void atualizar(Jogada j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(j);
		em.getTransaction().commit();
		em.close();
	}
	
	public static List<Jogada> listarJogadas(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select j from Jogada j");
		List<Jogada> retorno = q.getResultList();
		em.close();
		return retorno;
	}
	
	public static void deletar(Jogada j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		j = em.find(Jogada.class, j.getId());
		em.remove(j);
		em.getTransaction().commit();
		em.close();
	}
	
}
