package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EnderecoTO {

    private Long id;

    @NotNull(message = "O atributo de CEP é obrigatório!")
    private int cep;

    @NotBlank(message = "O atributo de endereço é obrigatório!")
    @Size(min = 4, max = 100, message = "O atributo de endereço deve conter entre 4 a 100 caracteres")
    private String endereco;

    @NotBlank(message = "O atributo de bairro é obrigatório!")
    @Size(min = 4, max = 100, message = "O atributo de bairro deve conter entre 4 a 100 caracteres")
    private String bairro;

    @NotBlank(message = "O atributo de estado é obrigatório!")
    @Size(min = 1, max = 50, message = "O atributo de estado deve conter entre 1 a 50 caracteres")
    private String estado;

    @NotBlank(message = "O atributo de rua é obrigatório!")
    @Size(min = 4, max = 100, message = "O atributo de rua deve conter entre 4 a 100 caracteres")
    private String rua;

    @NotNull(message = "O atributo de número é obrigatório!")
    @Size(min = 1, max = 4)
    private int numero;

    @Size(min = 4, max = 100, message = "O atributo de complemento deve conter entre 4 a 100 caracteres")
    private String complemento;

    private UsuarioTO usuario;

    public EnderecoTO() {
    }

    public EnderecoTO(Long id, int cep, String endereco, String bairro, String estado, String rua, int numero, String complemento, UsuarioTO usuario) {
        this.id = id;
        this.cep = cep;
        this.endereco = endereco;
        this.bairro = bairro;
        this.estado = estado;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "O atributo de CEP é obrigatório!")
    public int getCep() {
        return cep;
    }

    public void setCep(@NotNull(message = "O atributo de CEP é obrigatório!") int cep) {
        this.cep = cep;
    }

    public @NotBlank(message = "O atributo de endereço é obrigatório!") @Size(min = 4, max = 100, message = "O atributo de endereço deve conter entre 4 a 100 caracteres") String getEndereco() {
        return endereco;
    }

    public void setEndereco(@NotBlank(message = "O atributo de endereço é obrigatório!") @Size(min = 4, max = 100, message = "O atributo de endereço deve conter entre 4 a 100 caracteres") String endereco) {
        this.endereco = endereco;
    }

    public @NotBlank(message = "O atributo de bairro é obrigatório!") @Size(min = 4, max = 100, message = "O atributo de bairro deve conter entre 4 a 100 caracteres") String getBairro() {
        return bairro;
    }

    public void setBairro(@NotBlank(message = "O atributo de bairro é obrigatório!") @Size(min = 4, max = 100, message = "O atributo de bairro deve conter entre 4 a 100 caracteres") String bairro) {
        this.bairro = bairro;
    }

    public @NotBlank(message = "O atributo de estado é obrigatório!") @Size(min = 1, max = 50, message = "O atributo de estado deve conter entre 1 a 50 caracteres") String getEstado() {
        return estado;
    }

    public void setEstado(@NotBlank(message = "O atributo de estado é obrigatório!") @Size(min = 1, max = 50, message = "O atributo de estado deve conter entre 1 a 50 caracteres") String estado) {
        this.estado = estado;
    }

    public @NotBlank(message = "O atributo de rua é obrigatório!") @Size(min = 4, max = 100, message = "O atributo de rua deve conter entre 4 a 100 caracteres") String getRua() {
        return rua;
    }

    public void setRua(@NotBlank(message = "O atributo de rua é obrigatório!") @Size(min = 4, max = 100, message = "O atributo de rua deve conter entre 4 a 100 caracteres") String rua) {
        this.rua = rua;
    }

    @NotNull(message = "O atributo de número é obrigatório!")
    @Size(min = 1, max = 4)
    public int getNumero() {
        return numero;
    }

    public void setNumero(@NotNull(message = "O atributo de número é obrigatório!") @Size(min = 1, max = 4) int numero) {
        this.numero = numero;
    }

    public @Size(min = 4, max = 100, message = "O atributo de complemento deve conter entre 4 a 100 caracteres") String getComplemento() {
        return complemento;
    }

    public void setComplemento(@Size(min = 4, max = 100, message = "O atributo de complemento deve conter entre 4 a 100 caracteres") String complemento) {
        this.complemento = complemento;
    }

    public UsuarioTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioTO usuario) {
        this.usuario = usuario;
    }
}
