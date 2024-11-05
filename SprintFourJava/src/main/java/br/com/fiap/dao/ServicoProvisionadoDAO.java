package br.com.fiap.dao;

import br.com.fiap.to.AgendaTO;
import br.com.fiap.to.ServicoProvisionadoTO;
import br.com.fiap.to.ServicoTO; // Ensure you have this import

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicoProvisionadoDAO extends Repository {

    public List<ServicoProvisionadoTO> findAll() {
        List<ServicoProvisionadoTO> servicosProvisionados = new ArrayList<>();
        String sql = "SELECT * FROM OFIX_SP";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ServicoProvisionadoTO servicoProvisionado = new ServicoProvisionadoTO();
                servicoProvisionado.setId(rs.getLong("ID_SP"));
                servicoProvisionado.setPrecoTotal(rs.getDouble("NR_PRECOTOTAL"));
                servicoProvisionado.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));

                AgendaTO agendamento = new AgendaTO();
                agendamento.setId(rs.getLong("OFIX_AGENDAMENTO_ID_AGENDA"));
                servicoProvisionado.setAgendamento(agendamento);

                ServicoTO servico = new ServicoTO();
                servico.setId(rs.getLong("OFIX_SERVICO_ID_SERVICO"));
                servicoProvisionado.setServico(servico);

                servicosProvisionados.add(servicoProvisionado);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviços provisionados: " + e.getMessage());
        }

        return servicosProvisionados;
    }

    public ArrayList<ServicoProvisionadoTO> findByAgendamentoId(Long agendamentoId) {
        ArrayList<ServicoProvisionadoTO> servicosProvisionados = new ArrayList<>();
        String sql = "SELECT * FROM OFIX_SP WHERE OFIX_AGENDAMENTO_ID_AGENDA = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, agendamentoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ServicoProvisionadoTO servicoProvisionado = new ServicoProvisionadoTO();
                    servicoProvisionado.setId(rs.getLong("ID_SP"));
                    servicoProvisionado.setPrecoTotal(rs.getDouble("NR_PRECOTOTAL"));
                    servicoProvisionado.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));

                    AgendaTO agendamento = new AgendaTO();
                    agendamento.setId(rs.getLong("OFIX_AGENDAMENTO_ID_AGENDA"));
                    servicoProvisionado.setAgendamento(agendamento);

                    ServicoTO servico = new ServicoTO();
                    servico.setId(rs.getLong("OFIX_SERVICO_ID_SERVICO"));
                    servicoProvisionado.setServico(servico);

                    servicosProvisionados.add(servicoProvisionado);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviços provisionados por agendamento: " + e.getMessage());
        }

        return servicosProvisionados;
    }

    public ServicoProvisionadoTO findById(Long id) {
        ServicoProvisionadoTO servicoProvisionado = null;
        String sql = "SELECT * FROM OFIX_SP WHERE ID_SP = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    servicoProvisionado = new ServicoProvisionadoTO();
                    servicoProvisionado.setId(rs.getLong("ID_SP"));
                    servicoProvisionado.setPrecoTotal(rs.getDouble("NR_PRECOTOTAL"));
                    servicoProvisionado.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));

                    AgendaTO agendamento = new AgendaTO();
                    agendamento.setId(rs.getLong("OFIX_AGENDAMENTO_ID_AGENDA"));
                    servicoProvisionado.setAgendamento(agendamento);

                    ServicoTO servico = new ServicoTO();
                    servico.setId(rs.getLong("OFIX_SERVICO_ID_SERVICO"));
                    servicoProvisionado.setServico(servico);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviço provisionado: " + e.getMessage());
        }

        return servicoProvisionado;
    }

    public ServicoProvisionadoTO save(ServicoProvisionadoTO servicoProvisionado) {
        String sql = "INSERT INTO OFIX_SP (NR_PRECOTOTAL, DT_CADASTRO, OFIX_AGENDAMENTO_ID_AGENDA, OFIX_SERVICO_ID_SERVICO) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql,new String[]{"ID_SP"})) {
            ps.setDouble(1, servicoProvisionado.getPrecoTotal());
            ps.setObject(2, servicoProvisionado.getDataDeCadastro()); // Use LocalDateTime
            ps.setLong(3, servicoProvisionado.getAgendamento().getId());
            ps.setLong(4, servicoProvisionado.getServico().getId());

            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        servicoProvisionado.setId(rs.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar serviço provisionado: " + e.getMessage());
        }

        return servicoProvisionado;
    }

    public void update(ServicoProvisionadoTO servicoProvisionado) {
        String sql = "UPDATE OFIX_SP SET NR_PRECOTOTAL = ?, DT_CADASTRO = ?, OFIX_AGENDAMENTO_ID_AGENDA = ?, OFIX_SERVICO_ID_SERVICO = ? WHERE ID_SP = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setDouble(1, servicoProvisionado.getPrecoTotal());
            ps.setObject(2, servicoProvisionado.getDataDeCadastro()); // Use LocalDateTime
            ps.setLong(3, servicoProvisionado.getAgendamento().getId());
            ps.setLong(4, servicoProvisionado.getServico().getId());
            ps.setLong(5, servicoProvisionado.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar serviço provisionado: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM OFIX_SP WHERE ID_SP = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar serviço provisionado: " + e.getMessage());
        }
    }
}
