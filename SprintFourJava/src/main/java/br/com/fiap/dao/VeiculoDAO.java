package br.com.fiap.dao;

import br.com.fiap.to.MarcaModeloTO;
import br.com.fiap.to.VeiculoTO; // Ensure you have this import
import br.com.fiap.to.ClienteTO; // Importing ClienteTO to set the Cliente ID

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO extends Repository {

    public List<VeiculoTO> findAll() {
        List<VeiculoTO> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM OFIX_VEICULO";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                VeiculoTO veiculo = new VeiculoTO();
                veiculo.setId(rs.getLong("ID_VEICULO"));
                veiculo.setAlteracoes(rs.getInt("NR_ALTERACOES"));
                veiculo.setQuilometragem(rs.getInt("NR_KM"));
                veiculo.setAnoDeFabricacao(rs.getObject("DT_FABRICACAO", LocalDate.class));
                veiculo.setPlaca(rs.getString("NM_PLACA"));

                ClienteTO cliente = new ClienteTO();
                cliente.setId(rs.getLong("OFIX_CLIENTE_ID_CLIENTE"));
                veiculo.setCliente(cliente);
                veiculo.getMarcaModelo().setId(rs.getLong("OFIX_MM_ID_MM"));

                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar veículos: " + e.getMessage());
        }

        return veiculos;
    }

    public VeiculoTO findById(Long id) {
        VeiculoTO veiculo = null;
        String sql = "SELECT * FROM OFIX_VEICULO WHERE ID_VEICULO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    veiculo = new VeiculoTO();
                    veiculo.setId(rs.getLong("ID_VEICULO"));
                    veiculo.setAlteracoes(rs.getInt("NR_ALTERACOES"));
                    veiculo.setQuilometragem(rs.getInt("NR_KM"));
                    veiculo.setAnoDeFabricacao(rs.getObject("DT_FABRICACAO", LocalDate.class));
                    veiculo.setPlaca(rs.getString("NM_PLACA"));

                    // Set related Cliente object
                    ClienteTO cliente = new ClienteTO();
                    cliente.setId(rs.getLong("OFIX_CLIENTE_ID_CLIENTE"));
                    veiculo.setCliente(cliente);

                    // Initialize the MarcaModelo object if it is not already initialized
                    if (veiculo.getMarcaModelo() == null) {
                        veiculo.setMarcaModelo(new MarcaModeloTO());
                    }

                    // Set the ID for the MarcaModelo
                    veiculo.getMarcaModelo().setId(rs.getLong("OFIX_MM_ID_MM"));
                }

            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar veículo: " + e.getMessage());
        }

        return veiculo;
    }

    public VeiculoTO save(VeiculoTO veiculo) {
        String sql = "INSERT INTO OFIX_VEICULO (NR_ALTERACOES, NR_KM, DT_FABRICACAO, NM_PLACA, OFIX_MM_ID_MM, OFIX_CLIENTE_ID_CLIENTE) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql, new String[]{"ID_VEICULO"})) {
            ps.setInt(1, veiculo.getAlteracoes());
            ps.setInt(2, veiculo.getQuilometragem());
            ps.setObject(3, veiculo.getAnoDeFabricacao()); // Use LocalDate
            ps.setString(4, veiculo.getPlaca());
            ps.setLong(5, veiculo.getMarcaModelo().getId()); // Set MM ID
            ps.setLong(6, veiculo.getCliente().getId()); // Set Cliente ID

            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        veiculo.setId(rs.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar veículo: " + e.getMessage());
        }

        return veiculo;
    }

    public void update(VeiculoTO veiculo) {
        String sql = "UPDATE OFIX_VEICULO SET NR_ALTERACOES = ?, NR_KM = ?, DT_FABRICACAO = ?, NM_PLACA = ?, OFIX_MM_ID_MM = ?, OFIX_CLIENTE_ID_CLIENTE = ? WHERE ID_VEICULO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, veiculo.getAlteracoes());
            ps.setInt(2, veiculo.getQuilometragem());
            ps.setObject(3, veiculo.getAnoDeFabricacao()); // Use LocalDate
            ps.setString(4, veiculo.getPlaca());
            ps.setLong(5, veiculo.getMarcaModelo().getId()); // Set MM ID
            ps.setLong(6, veiculo.getCliente().getId()); // Set Cliente ID
            ps.setLong(7, veiculo.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar veículo: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM OFIX_VEICULO WHERE ID_VEICULO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar veículo: " + e.getMessage());
        }
    }

    public ArrayList<VeiculoTO> findByClienteId(Long clienteId) {
        ArrayList<VeiculoTO> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM OFIX_VEICULO WHERE OFIX_CLIENTE_ID_CLIENTE = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VeiculoTO veiculo = new VeiculoTO();
                    veiculo.setId(rs.getLong("ID_VEICULO"));
                    veiculo.setAlteracoes(rs.getInt("NR_ALTERACOES"));
                    veiculo.setQuilometragem(rs.getInt("NR_KM"));
                    veiculo.setAnoDeFabricacao(rs.getObject("DT_FABRICACAO", LocalDate.class));
                    veiculo.setPlaca(rs.getString("NM_PLACA"));

                    ClienteTO cliente = new ClienteTO();
                    cliente.setId(clienteId);
                    veiculo.setCliente(cliente);

                    if (veiculo.getMarcaModelo() == null) {
                        veiculo.setMarcaModelo(new MarcaModeloTO());
                    }

                    veiculo.getMarcaModelo().setId(rs.getLong("OFIX_MM_ID_MM"));

                    veiculos.add(veiculo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar veículos por ID do cliente: " + e.getMessage());
        }

        return veiculos;
    }

}
