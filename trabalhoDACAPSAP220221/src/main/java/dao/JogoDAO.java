package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	public static List<Jogo> buscaPares(Jogo j){
		EntityManager em = JPAUtil.criarEntityManager();
		//em.getTransaction().begin();
		//j = em.find(Jogo.class, j.getId());
		Query q = em.createQuery("select case when j.v1 % 2 = 0 then j.v1 end as v1,"
										+ "case when j.v2 % 2 = 0 then j.v2 end as v2,"
										+ "case when j.v3 % 2 = 0 then j.v3 end as v3,"
										+ "case when j.v4 % 2 = 0 then j.v4 end as v4,"
										+ "case when j.v5 % 2 = 0 then j.v5 end as v5,"
										+ "case when j.v6 % 2 = 0 then j.v6 end as v6,"
										+ "case when j.v7 % 2 = 0 then j.v7 end as v7,"
										+ "case when j.v8 % 2 = 0 then j.v8 end as v8,"
										+ "case when j.v9 % 2 = 0 then j.v9 end as v9,"
										+ "case when j.v10 % 2 = 0 then j.v10 end as v10 "
										+ "from Jogo j");
		List<Jogo> lPares = q.getResultList();
		em.close();
		return lPares;
	}
	
	
	/*public static Query pares(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query pares = em.createQuery("select case when j.v1 % 2 = 0 then j.v1 end as v1,case when j.v2 % 2 = 0 then j.v2 end as v2,case when j.v3 % 2 = 0 then j.v3 end as v3,case when j.v4 % 2 = 0 then j.v4 end as v4,case when j.v5 % 2 = 0 then j.v5 end as v5,case when j.v6 % 2 = 0 then j.v6 end as v6,case when j.v7 % 2 = 0 then j.v7 end as v7,case when j.v8 % 2 = 0 then j.v8 end as v8,case when j.v9 % 2 = 0 then j.v9 end as v9,case when j.v10 % 2 = 0 then j.v10 end as v10 from Jogo j");
		return pares;
	}*/
}
