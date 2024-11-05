package br.com.fiap.bo;

import br.com.fiap.dao.OficinaDAO;
import br.com.fiap.dao.ServicoDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.OficinaTO;
import br.com.fiap.to.ServicoTO;
import br.com.fiap.to.UsuarioTO;

import java.util.ArrayList;

public class ServicoBO {
    private OficinaDAO oficinaDAO;
    private UsuarioDAO usuarioDAO;

    public ServicoBO() {
        oficinaDAO = new OficinaDAO();
        usuarioDAO = new UsuarioDAO();
    }

    public ArrayList<ServicoTO> findAll() {
        ServicoDAO servicoDAO = new ServicoDAO();
        ArrayList<ServicoTO> servicos = servicoDAO.findAll();

        for (ServicoTO servico : servicos) {
            OficinaTO oficina = oficinaDAO.findById(servico.getOficina().getId());
            if (oficina != null) {
                servico.setOficina(oficina);
                UsuarioTO usuario = usuarioDAO.findById(oficina.getUsuario().getId());
                oficina.setUsuario(usuario);
            }
        }

        return servicos;
    }

    public ServicoTO findById(long id) {
        ServicoDAO servicoDAO = new ServicoDAO();
        ServicoTO servico = servicoDAO.findById(id);

        if (servico != null) {
            OficinaTO oficina = oficinaDAO.findById(servico.getOficina().getId());
            if (oficina != null) {
                servico.setOficina(oficina);
                UsuarioTO usuario = usuarioDAO.findById(oficina.getUsuario().getId());
                oficina.setUsuario(usuario);
            }
        }

        return servico;
    }

    public ServicoTO save(ServicoTO servico) {
        ServicoDAO servicoDAO = new ServicoDAO();
        ServicoTO savedServico = servicoDAO.save(servico);

        if (savedServico != null) {
            OficinaTO oficina = oficinaDAO.findById(savedServico.getOficina().getId());
            if (oficina != null) {
                savedServico.setOficina(oficina);
                UsuarioTO usuario = usuarioDAO.findById(oficina.getUsuario().getId());
                oficina.setUsuario(usuario);
            }
        }

        return savedServico;
    }

    public ServicoTO update(ServicoTO servico) {
        ServicoDAO servicoDAO = new ServicoDAO();
        return servicoDAO.update(servico);
    }

    public boolean delete(long servicoId) {
        ServicoDAO servicoDAO = new ServicoDAO();
        return servicoDAO.delete(servicoId);
    }
}
