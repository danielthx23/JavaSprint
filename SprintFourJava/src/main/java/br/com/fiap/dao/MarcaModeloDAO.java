package br.com.fiap.dao;

import br.com.fiap.to.MarcaModeloTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaModeloDAO extends Repository {

    public List<MarcaModeloTO> findAll() {
        List<MarcaModeloTO> marcaModelos = new ArrayList<>();
        String sql = "SELECT * FROM OFIX_MM";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MarcaModeloTO marcaModelo = new MarcaModeloTO();
                marcaModelo.setId(rs.getLong("ID_MM"));
                marcaModelo.setMarca(rs.getString("NM_MARCA"));
                marcaModelo.setModelo(rs.getString("NM_MODELO"));
                marcaModelo.setVariavelDePreco(rs.getDouble("NR_PRECO"));
                marcaModelos.add(marcaModelo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar marcas e modelos: " + e.getMessage());
        }

        return marcaModelos;
    }

    public MarcaModeloTO findById(Long id) {
        MarcaModeloTO marcaModelo = null;
        String sql = "SELECT * FROM OFIX_MM WHERE ID_MM = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                marcaModelo = new MarcaModeloTO();
                marcaModelo.setId(rs.getLong("ID_MM"));
                marcaModelo.setMarca(rs.getString("NM_MARCA"));
                marcaModelo.setModelo(rs.getString("NM_MODELO"));
                marcaModelo.setVariavelDePreco(rs.getDouble("NR_PRECO"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar marca e modelo: " + e.getMessage());
        }

        return marcaModelo;
    }

    public MarcaModeloTO save(MarcaModeloTO marcaModelo) {
        String sql = "INSERT INTO OFIX_MM (NM_MARCA, NM_MODELO, NR_PRECO) VALUES (?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql, new String[]{"ID_MM"})) {
            ps.setString(1, marcaModelo.getMarca());
            ps.setString(2, marcaModelo.getModelo());
            ps.setDouble(3, marcaModelo.getVariavelDePreco());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    marcaModelo.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar marca e modelo: " + e.getMessage());
        }

        return marcaModelo;
    }

    public void update(MarcaModeloTO marcaModelo) {
        String sql = "UPDATE OFIX_MM SET NM_MARCA = ?, NM_MODELO = ?, NR_PRECO = ? WHERE ID_MM = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, marcaModelo.getMarca());
            ps.setString(2, marcaModelo.getModelo());
            ps.setDouble(3, marcaModelo.getVariavelDePreco());
            ps.setLong(4, marcaModelo.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar marca e modelo: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM OFIX_MM WHERE ID_MM = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar marca e modelo: " + e.getMessage());
        }
    }
}
