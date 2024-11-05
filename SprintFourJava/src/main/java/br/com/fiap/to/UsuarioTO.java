package br.com.fiap.to;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class UsuarioTO {

    private Long id;

    @NotBlank
    @Size(min = 8, max = 150, message = "O atributo nome deve conter no minimo 4 e no máximo 150 caracteres")
    private String nome;

    @NotBlank
    @NotBlank(message = "O atributo de email é Obrigatório!")
    @Size(min = 8, max = 50, message = "O atributo email deve conter no minimo 8 e no máximo 50 caracteres")
    private String email;

    @NotBlank(message = "O atributo de senha é Obrigatório!")
    @Size(min = 8, max = 500, message = "O atributo senha deve conter no minimo 8 e no máximo 500 caracteres")
    private String senha;

    @Size(min = 8, max = 500, message = "O atributo de foto deve conter entre 8 a 500 caracteres")
    private String foto;

    private String token;

    @NotNull(message = "O atributo de data de cadastro é obrigatório!")
    @FutureOrPresent(message = "O atributo de data de cadastro deve estar no futuro ou presente")
    private Timestamp dataDeCadastro;

    @NotNull
    private String tipoConta;

    private List<TelefoneTO> telefones;

    private List<EnderecoTO> enderecos;

    private ClienteTO cliente;

    private OficinaTO oficina;

    public UsuarioTO() {
    }

    public UsuarioTO(Long id, String nome, String email, String senha, String foto, String token, Timestamp dataDeCadastro, String tipoConta, List<TelefoneTO> telefones, List<EnderecoTO> enderecos, ClienteTO cliente, OficinaTO oficina) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
        this.token = token;
        this.dataDeCadastro = dataDeCadastro;
        this.tipoConta = tipoConta;
        this.telefones = telefones;
        this.enderecos = enderecos;
        this.cliente = cliente;
        this.oficina = oficina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(min = 8, max = 150, message = "O atributo nome deve conter no minimo 4 e no máximo 150 caracteres") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @Size(min = 8, max = 150, message = "O atributo nome deve conter no minimo 4 e no máximo 150 caracteres") String nome) {
        this.nome = nome;
    }

    public @NotBlank @NotBlank(message = "O atributo de email é Obrigatório!") @Size(min = 8, max = 50, message = "O atributo email deve conter no minimo 8 e no máximo 50 caracteres") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @NotBlank(message = "O atributo de email é Obrigatório!") @Size(min = 8, max = 50, message = "O atributo email deve conter no minimo 8 e no máximo 50 caracteres") String email) {
        this.email = email;
    }

    public @NotBlank(message = "O atributo de senha é Obrigatório!") @Size(min = 8, max = 500, message = "O atributo senha deve conter no minimo 8 e no máximo 500 caracteres") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "O atributo de senha é Obrigatório!") @Size(min = 8, max = 500, message = "O atributo senha deve conter no minimo 8 e no máximo 500 caracteres") String senha) {
        this.senha = senha;
    }

    public @Size(min = 8, max = 500, message = "O atributo de foto deve conter entre 8 a 500 caracteres") String getFoto() {
        return foto;
    }

    public void setFoto(@Size(min = 8, max = 500, message = "O atributo de foto deve conter entre 8 a 500 caracteres") String foto) {
        this.foto = foto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public @NotNull(message = "O atributo de data de cadastro é obrigatório!") @FutureOrPresent(message = "O atributo de data de cadastro deve estar no futuro ou presente") Timestamp getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(@NotNull(message = "O atributo de data de cadastro é obrigatório!") @FutureOrPresent(message = "O atributo de data de cadastro deve estar no futuro ou presente") Timestamp dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public @NotNull String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(@NotNull String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public List<TelefoneTO> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneTO> telefones) {
        this.telefones = telefones;
    }

    public List<EnderecoTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoTO> enderecos) {
        this.enderecos = enderecos;
    }

    public ClienteTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteTO cliente) {
        this.cliente = cliente;
    }

    public OficinaTO getOficina() {
        return oficina;
    }

    public void setOficina(OficinaTO oficina) {
        this.oficina = oficina;
    }
}
