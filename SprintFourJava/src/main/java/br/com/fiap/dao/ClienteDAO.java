package br.com.fiap.dao;

import br.com.fiap.to.ClienteTO;
import br.com.fiap.to.UsuarioTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends Repository {

    public List<ClienteTO> findAll() {
        List<ClienteTO> clientes = new ArrayList<>();
        String sql = "SELECT * FROM OFIX_CLIENTE";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ClienteTO cliente = new ClienteTO();
                cliente.setId(rs.getLong("ID_CLIENTE"));
                cliente.setCpf(rs.getString("CPF_CLIENTE"));
                cliente.setIdade(rs.getInt("NR_IDADE"));
                cliente.setGenero(rs.getString("FL_SEXO"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar clientes: " + e.getMessage());
        }

        return clientes;
    }

    public ClienteTO findById(long id) {
        ClienteTO cliente = null;
        String sql = "SELECT * FROM OFIX_CLIENTE WHERE ID_CLIENTE = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new ClienteTO();
                cliente.setId(rs.getLong("ID_CLIENTE"));
                cliente.setCpf(rs.getString("CPF_CLIENTE"));
                cliente.setIdade(rs.getInt("NR_IDADE"));
                cliente.setGenero(rs.getString("FL_SEXO"));

                UsuarioTO usuario = new UsuarioTO();
                usuario.setId(rs.getLong("OFIX_USUARIO_ID_USUARIO"));
                cliente.setUsuario(usuario); // Set the usuario to cliente

            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        }

        return cliente;
    }

    public ClienteTO save(ClienteTO cliente) {
        String sql = "INSERT INTO OFIX_CLIENTE (CPF_CLIENTE, NR_IDADE, FL_SEXO, OFIX_USUARIO_ID_USUARIO) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql, new String[]{"ID_CLIENTE"})) {
            ps.setString(1, cliente.getCpf());
            ps.setInt(2, cliente.getIdade());
            ps.setString(3, cliente.getGenero());
            ps.setLong(4, cliente.getUsuario().getId());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    long clienteId = rs.getLong(1);
                    cliente.setId(clienteId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o cliente: " + e.getMessage());
        }

        return cliente;
    }

    public boolean update(ClienteTO cliente) {
        String sql = "UPDATE OFIX_CLIENTE SET CPF_CLIENTE = ?, NR_IDADE = ?, FL_SEXO = ? WHERE ID_CLIENTE = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, cliente.getCpf());
            ps.setInt(2, cliente.getIdade());
            ps.setString(3, cliente.getGenero());
            ps.setLong(4, cliente.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o cliente: " + e.getMessage());
        }
        return false;
    }

    public boolean delete(long clienteId) {
        String sql = "DELETE FROM OFIX_CLIENTE WHERE ID_CLIENTE = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, clienteId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar o cliente: " + e.getMessage());
        }
        return false;
    }

    public ClienteTO findByUsuarioId(long usuarioId) {
        ClienteTO cliente = null;
        String sql = "SELECT * FROM OFIX_CLIENTE WHERE OFIX_USUARIO_ID_USUARIO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, usuarioId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new ClienteTO();
                cliente.setId(rs.getLong("ID_CLIENTE"));
                cliente.setCpf(rs.getString("CPF_CLIENTE"));
                cliente.setIdade(rs.getInt("NR_IDADE"));
                cliente.setGenero(rs.getString("FL_SEXO"));

                UsuarioTO usuario = new UsuarioTO();
                usuario.setId(usuarioId);
                cliente.setUsuario(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente por Usuario ID: " + e.getMessage());
        }

        return cliente;
    }

}
