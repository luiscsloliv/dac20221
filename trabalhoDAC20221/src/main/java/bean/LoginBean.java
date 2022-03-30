package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.UsuariosDAO;
import entidade.Usuarios;

@ManagedBean
public class LoginBean {
	private Usuarios usuarios = new Usuarios();
	
	public Boolean entrar() {
		try {			
			//Pesquisar Usuário
			Usuarios usuarioSalvoDB = UsuariosDAO.pesquisar(usuarios.getNome());
			
			if (usuarioSalvoDB != null) {
				//Verificar Senha
				if (usuarios.getSenha().equals(usuarioSalvoDB.getSenha())) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Login Feito com sucesso!"));
					//Login OK
					return true;
				}
				else
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "Senha inválida!"));
					return false;
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "Usuário Não Existe!"));
				return false;
			}
			

			
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "Falha ao fazer login!"));
		}
		return false;
	}	
	

	public Usuarios getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}
}