package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Usuarios;
import util.JPAUtil;

public class UsuariosDAO {
	public static void salvar(Usuarios u) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
		em.close();
	}

	public static void editar(Usuarios u) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void apagar(Usuarios u) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		u = em.find(Usuarios.class, u.getId());
		em.remove(u);
		em.getTransaction().commit();
		em.close();
	}
	public static List<Usuarios> listar(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select u from Usuario u");
		List<Usuarios> l = q.getResultList();
		em.close();
		return l;
	}	
	public static Usuarios pesquisar(String nome){
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select u from Usuario u where u.nome = '"+nome+"' ");
		Usuarios result = (Usuarios) q.getSingleResult();
		em.close();
		return result;
	}
}