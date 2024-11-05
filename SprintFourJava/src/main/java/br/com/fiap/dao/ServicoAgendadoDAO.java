package br.com.fiap.dao;

import br.com.fiap.to.AgendaTO;
import br.com.fiap.to.ServicoAgendadoTO;
import br.com.fiap.to.ServicoTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicoAgendadoDAO extends Repository {

    public List<ServicoAgendadoTO> findAll() {
        List<ServicoAgendadoTO> servicosAgendados = new ArrayList<>();
        String sql = "SELECT * FROM OFIX_SA";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ServicoAgendadoTO servicoAgendado = new ServicoAgendadoTO();
                servicoAgendado.setId(rs.getLong("ID_SA"));
                servicoAgendado.setPrecoTotal(rs.getDouble("NR_PRECOTOTAL"));
                servicoAgendado.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));
                servicoAgendado.getAgendamento().setId(rs.getLong("OFIX_AGENDAMENTO_ID_AGENDA"));
                servicoAgendado.getServico().setId(rs.getLong("OFIX_SERVICO_ID_SERVICO"));
                servicosAgendados.add(servicoAgendado);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviços agendados: " + e.getMessage());
        }

        return servicosAgendados;
    }

    public ServicoAgendadoTO findById(Long id) {
        ServicoAgendadoTO servicoAgendado = null;
        String sql = "SELECT * FROM OFIX_SA WHERE ID_SA = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                servicoAgendado = new ServicoAgendadoTO();
                servicoAgendado.setId(rs.getLong("ID_SA"));
                servicoAgendado.setPrecoTotal(rs.getDouble("NR_PRECOTOTAL"));
                servicoAgendado.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));
                servicoAgendado.getAgendamento().setId(rs.getLong("OFIX_AGENDAMENTO_ID_AGENDA"));
                servicoAgendado.getServico().setId(rs.getLong("OFIX_SERVICO_ID_SERVICO"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviço agendado: " + e.getMessage());
        }

        return servicoAgendado;
    }

    public ServicoAgendadoTO save(ServicoAgendadoTO servicoAgendado) {
        String sql = "INSERT INTO OFIX_SA (NR_PRECOTOTAL, DT_CADASTRO, OFIX_AGENDAMENTO_ID_AGENDA, OFIX_SERVICO_ID_SERVICO) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql, new String[]{"ID_SA"})) {
            ps.setDouble(1, servicoAgendado.getPrecoTotal());
            ps.setObject(2, servicoAgendado.getDataDeCadastro());
            ps.setLong(3, servicoAgendado.getAgendamento().getId());
            ps.setLong(4, servicoAgendado.getServico().getId());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    servicoAgendado.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar serviço agendado: " + e.getMessage());
        }

        return servicoAgendado;
    }

    public ArrayList<ServicoAgendadoTO> findByAgendamentoId(Long agendamentoId) {
        ArrayList<ServicoAgendadoTO> servicosAgendados = new ArrayList<>();
        String sql = "SELECT * FROM OFIX_SA WHERE OFIX_AGENDAMENTO_ID_AGENDA = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, agendamentoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ServicoAgendadoTO servicoAgendado = new ServicoAgendadoTO();

                servicoAgendado.setId(rs.getLong("ID_SA"));
                servicoAgendado.setPrecoTotal(rs.getDouble("NR_PRECOTOTAL"));
                servicoAgendado.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));

                if (servicoAgendado.getAgendamento() == null) {
                    servicoAgendado.setAgendamento(new AgendaTO());
                }
                if (servicoAgendado.getServico() == null) {
                    servicoAgendado.setServico(new ServicoTO());
                }

                servicoAgendado.getAgendamento().setId(rs.getLong("OFIX_AGENDAMENTO_ID_AGENDA"));
                servicoAgendado.getServico().setId(rs.getLong("OFIX_SERVICO_ID_SERVICO"));

                servicosAgendados.add(servicoAgendado);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviços agendados por agendamento ID: " + e.getMessage());
        }

        return servicosAgendados;
    }

    public void update(ServicoAgendadoTO servicoAgendado) {
        String sql = "UPDATE OFIX_SA SET NR_PRECOTOTAL = ?, DT_CADASTRO = ?, OFIX_AGENDAMENTO_ID_AGENDA = ?, OFIX_SERVICO_ID_SERVICO = ? WHERE ID_SA = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setDouble(1, servicoAgendado.getPrecoTotal());
            ps.setObject(2, servicoAgendado.getDataDeCadastro());
            ps.setLong(3, servicoAgendado.getAgendamento().getId());
            ps.setLong(4, servicoAgendado.getServico().getId());
            ps.setLong(5, servicoAgendado.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar serviço agendado: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM OFIX_SA WHERE ID_SA = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar serviço agendado: " + e.getMessage());
        }
    }
}
