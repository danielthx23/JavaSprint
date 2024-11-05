package br.com.fiap.dao;

import br.com.fiap.to.SeguroTO;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SeguroDAO extends Repository {

    public SeguroTO save(SeguroTO seguro) {
        String sql = "INSERT INTO OFIX_SEGURO (OFIX_CLIENTE_ID_CLIENTE, DS_TIPO, DS_COBERTURA, NR_MENSALIDADE, DT_CADASTRO, DT_VALIDADE) VALUES (?, ?, ?, ?, ?, ?) RETURNING ID_SEGURO";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, seguro.getCliente().getId());
            ps.setString(2, seguro.getTipo());
            ps.setString(3, seguro.getCobertura());
            ps.setDouble(4, seguro.getMensalidade());
            ps.setObject(5, seguro.getDataDeCadastro());
            ps.setObject(6, seguro.getValidade());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                seguro.setId(rs.getLong("ID_SEGURO"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar o seguro: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return seguro;
    }

    public void update(SeguroTO seguro) {
        String sql = "UPDATE OFIX_SEGURO SET DS_TIPO = ?, DS_COBERTURA = ?, NR_MENSALIDADE = ?, DT_CADASTRO = ?, DT_VALIDADE = ? WHERE ID_SEGURO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, seguro.getTipo());
            ps.setString(2, seguro.getCobertura());
            ps.setDouble(3, seguro.getMensalidade());
            ps.setObject(4, seguro.getDataDeCadastro());
            ps.setObject(5, seguro.getValidade());
            ps.setLong(6, seguro.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o seguro: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM OFIX_SEGURO WHERE ID_SEGURO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o seguro: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public SeguroTO findById(Long id) {
        SeguroTO seguro = null;
        String sql = "SELECT * FROM OFIX_SEGURO WHERE ID_SEGURO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                seguro = new SeguroTO();
                seguro.setId(rs.getLong("ID_SEGURO"));
                seguro.setTipo(rs.getString("DS_TIPO"));
                seguro.setCobertura(rs.getString("DS_COBERTURA"));
                seguro.setMensalidade(rs.getDouble("NR_MENSALIDADE"));
                seguro.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));
                seguro.setValidade(rs.getTimestamp("DT_VALIDADE"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o seguro por ID: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return seguro;
    }

    public List<SeguroTO> findByClienteId(Long clienteId) {
        List<SeguroTO> seguros = new ArrayList<>();
        String sql = "SELECT * FROM OFIX_SEGURO WHERE OFIX_CLIENTE_ID_CLIENTE = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, clienteId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SeguroTO seguro = new SeguroTO();
                seguro.setId(rs.getLong("ID_SEGURO"));
                seguro.setTipo(rs.getString("DS_TIPO"));
                seguro.setCobertura(rs.getString("DS_COBERTURA"));
                seguro.setMensalidade(rs.getDouble("NR_MENSALIDADE"));
                seguro.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));
                seguro.setValidade(rs.getTimestamp("DT_VALIDADE"));
                seguros.add(seguro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar seguros por ID de cliente: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return seguros;
    }
}
