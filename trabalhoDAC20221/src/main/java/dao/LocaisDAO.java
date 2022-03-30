package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Locais;
import util.JPAUtil;

public class LocaisDAO {
	public static void salvar(Locais l) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(l);
		em.getTransaction().commit();
		em.close();
	}

	public static void editar(Locais l) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(l);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void apagar(Locais l) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		l = em.find(Locais.class, l.getId());
		em.remove(l);
		em.getTransaction().commit();
		em.close();
	}
	public static List<Locais> listar(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select l from Locais l");
		List<Locais> l = q.getResultList();
		em.close();
		return l;
	}
	public static Integer contar() {
		EntityManager em = JPAUtil.criarEntityManager();
		Integer contar = Integer.parseInt(em.createQuery("select count(l) from Locais l").getSingleResult().toString());
		return contar;
	}
//	public static List<Locais> contar(Locais locais){
//		EntityManager em = JPAUtil.criarEntityManager();
//		Query q = em.createQuery("select count(l) from Locais l");
//		List<Locais> l = q.getResultList();
//		em.close();
//		return l;
//	}
//	public static void contar(Locais l) {
//		EntityManager em = JPAUtil.criarEntityManager();
//		Query q = em.createQuery("select count(l) from Locais l");
//		q.getSingleResult();
//		em.close();
//	}
}