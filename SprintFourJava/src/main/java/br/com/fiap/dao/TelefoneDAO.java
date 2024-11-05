package br.com.fiap.dao;

import br.com.fiap.to.TelefoneTO; // Ensure you have this import
import br.com.fiap.to.UsuarioTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefoneDAO extends Repository {

    public List<TelefoneTO> findAll() {
        List<TelefoneTO> telefones = new ArrayList<>();
        String sql = "SELECT * FROM OFIX_TELEFONE";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TelefoneTO telefone = new TelefoneTO();
                telefone.setId(rs.getLong("ID_TELEFONE"));
                telefone.setNumero(rs.getInt("NR_TELEFONE"));
                telefone.setDDD(rs.getInt("NR_DDD"));
                telefone.setDDI(rs.getInt("NR_DDI"));
                telefone.setLembrete(rs.getString("NM_LEMBRETE"));
                telefone.getUsuario().setId(rs.getLong("OFIX_USUARIO_ID_USUARIO"));
                telefones.add(telefone);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar telefones: " + e.getMessage());
        }

        return telefones;
    }

    public TelefoneTO findById(Long id) {
        TelefoneTO telefone = null;
        String sql = "SELECT * FROM OFIX_TELEFONE WHERE ID_TELEFONE = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    telefone = new TelefoneTO();
                    telefone.setId(rs.getLong("ID_TELEFONE"));
                    telefone.setNumero(rs.getInt("NR_TELEFONE"));
                    telefone.setDDD(rs.getInt("NR_DDD"));
                    telefone.setDDI(rs.getInt("NR_DDI"));
                    telefone.setLembrete(rs.getString("NM_LEMBRETE"));
                    telefone.getUsuario().setId(rs.getLong("OFIX_USUARIO_ID_USUARIO"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar telefone: " + e.getMessage());
        }

        return telefone;
    }

    public TelefoneTO save(TelefoneTO telefone) {
        String sql = "INSERT INTO OFIX_TELEFONE (NR_TELEFONE, NR_DDD, NR_DDI, NM_LEMBRETE, OFIX_USUARIO_ID_USUARIO) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql, new String[]{"ID_TELEFONE"})) {
            ps.setInt(1, telefone.getNumero());
            ps.setInt(2, telefone.getDDD());
            ps.setInt(3, telefone.getDDI());
            ps.setString(4, telefone.getLembrete());
            ps.setLong(5, telefone.getUsuario().getId());

            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        telefone.setId(rs.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar telefone: " + e.getMessage());
        }

        return telefone;
    }

    public void update(TelefoneTO telefone) {
        String sql = "UPDATE OFIX_TELEFONE SET NR_TELEFONE = ?, NR_DDD = ?, NR_DDI = ?, NM_LEMBRETE = ?, OFIX_USUARIO_ID_USUARIO = ? WHERE ID_TELEFONE = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, telefone.getNumero());
            ps.setInt(2, telefone.getDDD());
            ps.setInt(3, telefone.getDDI());
            ps.setString(4, telefone.getLembrete());
            ps.setLong(5, telefone.getUsuario().getId());
            ps.setLong(6, telefone.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar telefone: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM OFIX_TELEFONE WHERE ID_TELEFONE = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar telefone: " + e.getMessage());
        }
    }

    public ArrayList<TelefoneTO> findByUsuarioId(Long usuarioId) {
        ArrayList<TelefoneTO> telefones = new ArrayList<>();
        String sql = "SELECT * FROM OFIX_TELEFONE WHERE OFIX_USUARIO_ID_USUARIO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, usuarioId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TelefoneTO telefone = new TelefoneTO();
                    telefone.setId(rs.getLong("ID_TELEFONE"));
                    telefone.setNumero(rs.getInt("NR_TELEFONE"));
                    telefone.setDDD(rs.getInt("NR_DDD"));
                    telefone.setLembrete(rs.getString("NM_LEMBRETE"));
                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setId(rs.getLong("OFIX_USUARIO_ID_USUARIO"));
                    telefone.setUsuario(usuario);
                    telefones.add(telefone);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar telefones por ID de usuário: " + e.getMessage());
        }

        return telefones;
    }

}
