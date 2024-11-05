package br.com.fiap.bo;

import br.com.fiap.dao.*;
import br.com.fiap.to.*;
import java.util.ArrayList;

public class UsuarioBO {
    private UsuarioDAO usuarioDAO;
    private EnderecoDAO enderecoDAO;
    private TelefoneDAO telefoneDAO;
    private ClienteDAO clienteDAO;
    private OficinaDAO oficinaDAO;
    private VeiculoDAO veiculoDAO;
    private MarcaModeloDAO marcaModeloDAO;
    private SeguroDAO seguroDAO;
    private ServicoDAO servicoDAO;

    public UsuarioBO() {
        usuarioDAO = new UsuarioDAO();
        enderecoDAO = new EnderecoDAO();
        telefoneDAO = new TelefoneDAO();
        clienteDAO = new ClienteDAO();
        oficinaDAO = new OficinaDAO();
        veiculoDAO = new VeiculoDAO();
        marcaModeloDAO = new MarcaModeloDAO();
        seguroDAO = new SeguroDAO();
        servicoDAO = new ServicoDAO();
    }

    public UsuarioTO save(UsuarioTO usuario) {
        UsuarioTO savedUsuario = usuarioDAO.save(usuario);

        for (EnderecoTO endereco : usuario.getEnderecos()) {
            endereco.setUsuario(savedUsuario);
            enderecoDAO.save(endereco);
        }

        for (TelefoneTO telefone : usuario.getTelefones()) {
            telefone.setUsuario(savedUsuario);
            telefoneDAO.save(telefone);
        }

        if (usuario.getCliente() != null) {
            ClienteTO cliente = usuario.getCliente();
            cliente.setUsuario(savedUsuario);
            ClienteTO savedCliente = clienteDAO.save(cliente);

            for (VeiculoTO veiculo : cliente.getVeiculos()) {
                veiculo.setCliente(savedCliente);
                veiculoDAO.save(veiculo);

                MarcaModeloTO marcaModelo = veiculo.getMarcaModelo();
                if (marcaModelo != null) {
                    marcaModeloDAO.save(marcaModelo);
                }
            }

            for (SeguroTO seguro : cliente.getSeguros()) {
                seguro.setCliente(savedCliente);
                seguroDAO.save(seguro);
            }
        }

        if (usuario.getOficina() != null) {
            OficinaTO oficina = usuario.getOficina();
            oficina.setUsuario(savedUsuario);
            oficinaDAO.save(oficina);
        }

        return savedUsuario;
    }

    public ArrayList<UsuarioTO> findAll() {
        ArrayList<UsuarioTO> usuarios = usuarioDAO.findAll();

        for (UsuarioTO usuario : usuarios) {
            usuario.setEnderecos(enderecoDAO.findByUsuarioId(usuario.getId()));
            usuario.setTelefones(telefoneDAO.findByUsuarioId(usuario.getId()));

            ClienteTO cliente = clienteDAO.findByUsuarioId(usuario.getId());
            if (cliente != null) {
                cliente.setSeguros(seguroDAO.findByClienteId(cliente.getId()));

                ArrayList<VeiculoTO> veiculos = veiculoDAO.findByClienteId(cliente.getId());
                for (VeiculoTO veiculo : veiculos) {
                    veiculo.setMarcaModelo(marcaModeloDAO.findById(veiculo.getMarcaModelo().getId()));
                }
                cliente.setVeiculos(veiculos);

                usuario.setCliente(cliente);
            }

            OficinaTO oficina = oficinaDAO.findByUsuarioId(usuario.getId());
            if (oficina != null) {
                oficina.setServicos(servicoDAO.findByOficinaId(oficina.getId()));
            }
            usuario.setOficina(oficina);
        }

        return usuarios;
    }

    public UsuarioTO findById(Long id) {
        UsuarioTO usuario = usuarioDAO.findById(id);
        if (usuario != null) {
            usuario.setEnderecos(enderecoDAO.findByUsuarioId(id));
            usuario.setTelefones(telefoneDAO.findByUsuarioId(id));

            ClienteTO cliente = clienteDAO.findByUsuarioId(id);
            if (cliente != null) {
                cliente.setSeguros(seguroDAO.findByClienteId(cliente.getId()));

                ArrayList<VeiculoTO> veiculos = veiculoDAO.findByClienteId(cliente.getId());
                for (VeiculoTO veiculo : veiculos) {
                    veiculo.setMarcaModelo(marcaModeloDAO.findById(veiculo.getMarcaModelo().getId()));
                }
                cliente.setVeiculos(veiculos);

                usuario.setCliente(cliente);
            }

            OficinaTO oficina = oficinaDAO.findByUsuarioId(id);
            if (oficina != null) {
                oficina.setServicos(servicoDAO.findByOficinaId(oficina.getId()));
            }
            usuario.setOficina(oficina);
        }

        return usuario;
    }

    public UsuarioTO update(UsuarioTO usuario) {
        UsuarioTO updatedUsuario = usuarioDAO.update(usuario);

        for (EnderecoTO endereco : usuario.getEnderecos()) {
            endereco.setUsuario(updatedUsuario);
            enderecoDAO.update(endereco);
        }

        for (TelefoneTO telefone : usuario.getTelefones()) {
            telefone.setUsuario(updatedUsuario);
            telefoneDAO.update(telefone);
        }

        if (usuario.getCliente() != null) {
            ClienteTO cliente = usuario.getCliente();
            cliente.setUsuario(updatedUsuario);
            clienteDAO.update(cliente);

            for (VeiculoTO veiculo : cliente.getVeiculos()) {
                veiculo.setCliente(cliente);
                veiculoDAO.update(veiculo);

                MarcaModeloTO marcaModelo = veiculo.getMarcaModelo();
                if (marcaModelo != null) {
                    marcaModeloDAO.update(marcaModelo);
                }
            }

            for (SeguroTO seguro : cliente.getSeguros()) {
                seguro.setCliente(cliente);
                seguroDAO.update(seguro);
            }
        }

        if (usuario.getOficina() != null) {
            OficinaTO oficina = usuario.getOficina();
            oficina.setUsuario(updatedUsuario);
            oficinaDAO.update(oficina);
        }

        return updatedUsuario; // Return the updated UsuarioTO
    }

    public boolean delete(Long id) {
        UsuarioTO usuario = findById(id);

        if (usuario != null) {
            if (usuario.getCliente() != null) {
                for (SeguroTO seguro : usuario.getCliente().getSeguros()) {
                    seguroDAO.delete(seguro.getId());
                }

                for (VeiculoTO veiculo : usuario.getCliente().getVeiculos()) {
                    veiculoDAO.delete(veiculo.getId());
                }

                clienteDAO.delete(usuario.getCliente().getId());
            }

            if (usuario.getOficina() != null) {
                oficinaDAO.delete(usuario.getOficina().getId());
            }

            for (EnderecoTO endereco : usuario.getEnderecos()) {
                enderecoDAO.delete(endereco.getId());
            }

            for (TelefoneTO telefone : usuario.getTelefones()) {
                telefoneDAO.delete(telefone.getId());
            }

            return usuarioDAO.delete(id);
        }

        return false;
    }
}
