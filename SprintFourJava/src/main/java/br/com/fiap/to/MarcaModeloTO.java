package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class MarcaModeloTO {

    private Long id;

    @NotBlank(message = "O atributo de marca é obrigatório!")
    @Size(min = 1, max = 100, message = "O atributo de marca deve conter entre 1 a 100 caracteres")
    private String marca;

    @NotBlank(message = "O atributo de modelo é obrigatório!")
    @Size(min = 2, max = 250, message = "O atributo de modelo deve conter entre 2 e 250 caracteres")
    private String modelo;

    private double variavelDePreco;

    private List<VeiculoTO> veiculos;

    public MarcaModeloTO() {
    }

    public MarcaModeloTO(Long id, String marca, String modelo, double variavelDePreco, List<VeiculoTO> veiculos) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.variavelDePreco = variavelDePreco;
        this.veiculos = veiculos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O atributo de marca é obrigatório!") @Size(min = 1, max = 100, message = "O atributo de marca deve conter entre 1 a 100 caracteres") String getMarca() {
        return marca;
    }

    public void setMarca(@NotBlank(message = "O atributo de marca é obrigatório!") @Size(min = 1, max = 100, message = "O atributo de marca deve conter entre 1 a 100 caracteres") String marca) {
        this.marca = marca;
    }

    public @NotBlank(message = "O atributo de modelo é obrigatório!") @Size(min = 2, max = 250, message = "O atributo de modelo deve conter entre 2 e 250 caracteres") String getModelo() {
        return modelo;
    }

    public void setModelo(@NotBlank(message = "O atributo de modelo é obrigatório!") @Size(min = 2, max = 250, message = "O atributo de modelo deve conter entre 2 e 250 caracteres") String modelo) {
        this.modelo = modelo;
    }

    public double getVariavelDePreco() {
        return variavelDePreco;
    }

    public void setVariavelDePreco(double variavelDePreco) {
        this.variavelDePreco = variavelDePreco;
    }

    public List<VeiculoTO> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<VeiculoTO> veiculos) {
        this.veiculos = veiculos;
    }
}
