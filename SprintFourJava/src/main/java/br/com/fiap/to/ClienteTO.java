package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public class ClienteTO {

    private Long id;

    @NotBlank(message = "O atributo de CPF é obrigatório!")
    private String cpf;

    @NotNull(message = "O atributo de idade é obrigatório!")
    private int idade;

    @NotBlank(message = "O atributo de genêro é obrigatório!")
    private String genero;

    private List<VeiculoTO> veiculos;

    private List<SeguroTO> seguros;

    private UsuarioTO usuario;

    public ClienteTO() {
    }

    public ClienteTO(Long id, String cpf, int idade, String genero, List<VeiculoTO> veiculos, List<SeguroTO> seguros, UsuarioTO usuario) {
        this.id = id;
        this.cpf = cpf;
        this.idade = idade;
        this.genero = genero;
        this.veiculos = veiculos;
        this.seguros = seguros;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O atributo de CPF é obrigatório!") String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank(message = "O atributo de CPF é obrigatório!") String cpf) {
        this.cpf = cpf;
    }

    @NotNull(message = "O atributo de idade é obrigatório!")
    public int getIdade() {
        return idade;
    }

    public void setIdade(@NotNull(message = "O atributo de idade é obrigatório!") int idade) {
        this.idade = idade;
    }

    public @NotBlank(message = "O atributo de genêro é obrigatório!") String getGenero() {
        return genero;
    }

    public void setGenero(@NotBlank(message = "O atributo de genêro é obrigatório!") String genero) {
        this.genero = genero;
    }

    public List<VeiculoTO> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<VeiculoTO> veiculos) {
        this.veiculos = veiculos;
    }

    public List<SeguroTO> getSeguros() {
        return seguros;
    }

    public void setSeguros(List<SeguroTO> seguros) {
        this.seguros = seguros;
    }

    public UsuarioTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioTO usuario) {
        this.usuario = usuario;
    }
}
