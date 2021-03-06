package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidade.Jogo;
import util.JPAUtil;

public class JogoDAO {
	public static void salvar(Jogo j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(j);
		em.getTransaction().commit();
		em.close();
	}

	public static void editar(Jogo j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(j);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void apagar(Jogo j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		j = em.find(Jogo.class, j.getId());
		em.remove(j);
		em.getTransaction().commit();
		em.close();
	}
	public static List<Jogo> listar(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select j from Jogo j");
		List<Jogo> j = q.getResultList();
		em.close();
		return j;
	}
	
	public static List<Integer> buscaPares(Jogo j){
		EntityManager em = JPAUtil.criarEntityManager();
		TypedQuery<Jogo> q = em.createQuery("from Jogo j where j.id = :id", Jogo.class).setParameter("id", j.getId());
		List<Integer> lPares = q.getSingleResult().getPares();
		em.close();		
		return lPares;
	}
}
