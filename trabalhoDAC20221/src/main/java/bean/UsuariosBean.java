package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.UsuariosDAO;
import entidade.Usuarios;

@ManagedBean
public class UsuariosBean {
	private Usuarios usuarios = new Usuarios();
	private List<Usuarios> lista;
	
	public String salvar() {
		try {
			UsuariosDAO.salvar(usuarios);
			usuarios = new Usuarios();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário cadastrado com sucesso!"));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "Usuário não foi cadastrado!"));
		}
		return null;
	}	
	public String editar() {
		try {
			UsuariosDAO.editar(usuarios);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário editado com sucesso!"));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "Usuário não foi editado!"));
		}
		return "listagem";
	}
	public String apagar() {
		try {
			UsuariosDAO.apagar(usuarios);
			lista.remove(usuarios);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário excluído com sucesso!"));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "Usuário não foi excluído!"));
		}
		return null;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}
	public void setLista(List<Usuarios> lista) {
		this.lista = lista;
	}
	public List<Usuarios> getLista() {
		if (lista == null)
			lista = UsuariosDAO.listar();
		return lista;
	}
}
