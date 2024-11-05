package br.com.fiap.bo;

import br.com.fiap.dao.EnderecoDAO;
import br.com.fiap.dao.TelefoneDAO;
import br.com.fiap.dao.OficinaDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.OficinaTO;
import br.com.fiap.to.UsuarioTO;
import br.com.fiap.to.EnderecoTO;
import br.com.fiap.to.TelefoneTO;

import java.util.ArrayList;

public class OficinaBO {
    private OficinaDAO oficinaDAO;
    private UsuarioDAO usuarioDAO;
    private EnderecoDAO enderecoDAO;
    private TelefoneDAO telefoneDAO;

    public OficinaBO() {
        oficinaDAO = new OficinaDAO();
        usuarioDAO = new UsuarioDAO();
        enderecoDAO = new EnderecoDAO();
        telefoneDAO = new TelefoneDAO();
    }

    public OficinaTO findById(long id) {
        OficinaTO oficina = oficinaDAO.findById(id);
        if (oficina != null) {
            UsuarioTO usuario = usuarioDAO.findById(oficina.getUsuario().getId());
            if (usuario != null) {
                oficina.setUsuario(usuario);

                ArrayList<EnderecoTO> enderecos = enderecoDAO.findByUsuarioId(usuario.getId());
                oficina.setEnderecos(enderecos);

                ArrayList<TelefoneTO> telefones = telefoneDAO.findByUsuarioId(usuario.getId());
                oficina.setTelefones(telefones);
            }
        }
        return oficina;
    }

    public ArrayList<OficinaTO> findAll() {
        ArrayList<OficinaTO> oficinas = oficinaDAO.findAll();
        for (OficinaTO oficina : oficinas) {
            UsuarioTO usuario = usuarioDAO.findById(oficina.getUsuario().getId());
            if (usuario != null) {
                oficina.setUsuario(usuario);

                ArrayList<EnderecoTO> enderecos = enderecoDAO.findByUsuarioId(usuario.getId());
                oficina.setEnderecos(enderecos);

                ArrayList<TelefoneTO> telefones = telefoneDAO.findByUsuarioId(usuario.getId());
                oficina.setTelefones(telefones);
            }
        }
        return oficinas;
    }
}
