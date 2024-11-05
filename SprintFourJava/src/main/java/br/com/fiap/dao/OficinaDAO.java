package br.com.fiap.dao;

import br.com.fiap.to.OficinaTO;
import br.com.fiap.to.UsuarioTO;

import java.sql.*;
import java.util.ArrayList;

public class OficinaDAO extends Repository {

    public ArrayList<OficinaTO> findAll() {
        ArrayList<OficinaTO> oficinas = new ArrayList<>();
        String sql = "SELECT * FROM OFIX_OFICINA";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OficinaTO oficina = new OficinaTO();
                oficina.setId(rs.getLong("ID_OFICINA"));
                oficina.setCnpj(rs.getString("NR_CNPJ"));
                oficina.setNome(rs.getString("NM_OFICINA"));
                oficina.setOficinaPorto("1".equalsIgnoreCase(rs.getString("BL_PORTO")));
                oficina.setCapacidade(rs.getInt("NR_GARAGEM"));
                oficina.setEspecialidade(rs.getString("DS_ESPECIALIDADE"));

                UsuarioTO usuario = new UsuarioTO();
                usuario.setId(rs.getLong("OFIX_USUARIO_ID_USUARIO"));
                oficina.setUsuario(usuario);

                oficinas.add(oficina);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar oficinas: " + e.getMessage());
        }

        return oficinas;
    }

    public OficinaTO findById(Long id) {
        OficinaTO oficina = null;
        String sql = "SELECT * FROM OFIX_OFICINA WHERE ID_OFICINA = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                oficina = new OficinaTO();
                oficina.setId(rs.getLong("ID_OFICINA"));
                oficina.setCnpj(rs.getString("NR_CNPJ"));
                oficina.setNome(rs.getString("NM_OFICINA"));
                oficina.setOficinaPorto("1".equalsIgnoreCase(rs.getString("BL_PORTO")));
                oficina.setCapacidade(rs.getInt("NR_GARAGEM"));
                oficina.setEspecialidade(rs.getString("DS_ESPECIALIDADE"));

                if (oficina.getUsuario() == null) {
                    oficina.setUsuario(new UsuarioTO());
                }

                oficina.getUsuario().setId(rs.getLong("OFIX_USUARIO_ID_USUARIO"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar oficina: " + e.getMessage());
        }

        return oficina;
    }

    public OficinaTO save(OficinaTO oficina) {
        String sql = "INSERT INTO OFIX_OFICINA (NR_CNPJ, NM_OFICINA, BL_PORTO, NR_GARAGEM, DS_ESPECIALIDADE, OFIX_USUARIO_ID_USUARIO) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql, new String[]{"ID_OFICINA"})) {
            ps.setString(1, oficina.getCnpj());
            ps.setString(2, oficina.getNome());
            ps.setBoolean(3, oficina.isOficinaPorto());
            ps.setInt(4, oficina.getCapacidade());
            ps.setString(5, oficina.getEspecialidade());
            ps.setLong(6, oficina.getUsuario().getId());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    oficina.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar oficina: " + e.getMessage());
        }

        return oficina;
    }

    public void update(OficinaTO oficina) {
        String sql = "UPDATE OFIX_OFICINA SET NR_CNPJ = ?, NM_OFICINA = ?, BL_PORTO = ?, NR_GARAGEM = ?, DS_ESPECIALIDADE = ?, OFIX_USUARIO_ID_USUARIO = ? WHERE ID_OFICINA = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, oficina.getCnpj());
            ps.setString(2, oficina.getNome());
            ps.setBoolean(3, oficina.isOficinaPorto());
            ps.setInt(4, oficina.getCapacidade());
            ps.setString(5, oficina.getEspecialidade());
            ps.setLong(6, oficina.getUsuario().getId());
            ps.setLong(7, oficina.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar oficina: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM OFIX_OFICINA WHERE ID_OFICINA = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar oficina: " + e.getMessage());
        }
    }

    public OficinaTO findByUsuarioId(long usuarioId) {
        OficinaTO oficina = null;
        String sql = "SELECT * FROM OFIX_OFICINA WHERE OFIX_USUARIO_ID_USUARIO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, usuarioId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                oficina = new OficinaTO();
                oficina.setId(rs.getLong("ID_OFICINA"));
                oficina.setCnpj(rs.getString("NR_CNPJ"));
                oficina.setNome(rs.getString("NM_OFICINA"));
                oficina.setOficinaPorto("1".equalsIgnoreCase(rs.getString("BL_PORTO")));
                oficina.setCapacidade(rs.getInt("NR_GARAGEM"));
                oficina.setEspecialidade(rs.getString("DS_ESPECIALIDADE"));

                if (oficina.getUsuario() == null) {
                    oficina.setUsuario(new UsuarioTO());
                }

                oficina.getUsuario().setId(usuarioId);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar oficina por Usuario ID: " + e.getMessage());
        }

        return oficina;
    }
}
