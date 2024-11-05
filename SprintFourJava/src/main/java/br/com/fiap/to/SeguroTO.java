package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SeguroTO {

    private Long id;

    @NotBlank(message = "O atributo tipo é obrigatório!")
    @Size(min = 4, max = 50, message = "O atributo tipo deve conter entre 4 e 50 caracteres.")
    private String tipo;

    @NotBlank(message = "O atributo cobertura é obrigatório!")
    @Size(max = 40, message = "O atributo cobertura deve conter no máximo 40 caracteres.")
    private String cobertura;

    @NotNull(message = "O atributo mensalidade é obrigatório!")
    private double mensalidade;

    @NotNull(message = "O atributo data de cadastro é obrigatório!")
    private Timestamp dataDeCadastro;

    @NotNull(message = "O atributo validade é obrigatório!")
    private Timestamp validade;

    private ClienteTO cliente;

    public SeguroTO() {
    }

    public SeguroTO(Long id, String tipo, String cobertura, double mensalidade, Timestamp dataDeCadastro, Timestamp validade, ClienteTO cliente) {
        this.id = id;
        this.tipo = tipo;
        this.cobertura = cobertura;
        this.mensalidade = mensalidade;
        this.dataDeCadastro = dataDeCadastro;
        this.validade = validade;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O atributo tipo é obrigatório!") @Size(min = 4, max = 50, message = "O atributo tipo deve conter entre 4 e 50 caracteres.") String getTipo() {
        return tipo;
    }

    public void setTipo(@NotBlank(message = "O atributo tipo é obrigatório!") @Size(min = 4, max = 50, message = "O atributo tipo deve conter entre 4 e 50 caracteres.") String tipo) {
        this.tipo = tipo;
    }

    public @NotBlank(message = "O atributo cobertura é obrigatório!") @Size(max = 40, message = "O atributo cobertura deve conter no máximo 40 caracteres.") String getCobertura() {
        return cobertura;
    }

    public void setCobertura(@NotBlank(message = "O atributo cobertura é obrigatório!") @Size(max = 40, message = "O atributo cobertura deve conter no máximo 40 caracteres.") String cobertura) {
        this.cobertura = cobertura;
    }

    @NotNull(message = "O atributo mensalidade é obrigatório!")
    public double getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(@NotNull(message = "O atributo mensalidade é obrigatório!") double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public @NotNull(message = "O atributo data de cadastro é obrigatório!") Timestamp getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(@NotNull(message = "O atributo data de cadastro é obrigatório!") Timestamp dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public @NotNull(message = "O atributo validade é obrigatório!") Timestamp getValidade() {
        return validade;
    }

    public void setValidade(@NotNull(message = "O atributo validade é obrigatório!") Timestamp validade) {
        this.validade = validade;
    }

    public ClienteTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteTO cliente) {
        this.cliente = cliente;
    }
}
