package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioLoginDAO;
import br.com.fiap.to.UsuarioTO;

public class UsuarioLoginBO {
    private UsuarioLoginDAO usuarioLoginDAO;

    public UsuarioTO handleLogin(UsuarioTO usuario) {
        usuarioLoginDAO = new UsuarioLoginDAO();

        UsuarioTO usuarioSaved = usuarioLoginDAO.login(usuario);

        if (usuarioSaved != null) {
            return usuario;
        } else {
            return null;
        }
    }
}
