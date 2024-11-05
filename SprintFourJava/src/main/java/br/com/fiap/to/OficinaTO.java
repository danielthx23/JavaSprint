package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDateTime;
import java.util.List;

public class OficinaTO extends UsuarioTO {

    private Long id;

    @NotBlank(message = "O atributo CNPJ é obrigatório!")
    private String cnpj;

    @NotBlank(message = "O atributo especialidade é obrigatório!")
    private String especialidade;

    @NotNull(message = "O atributo oficinaPorto é obrigatório!")
    private boolean oficinaPorto;

    @NotNull(message = "A capacidade da oficina é obrigatória!")
    private int capacidade;

    private List<ServicoTO> servicos;

    private UsuarioTO usuario;

    private List<HorarioDisponivelTO> horariosDisponiveis;

    public OficinaTO() {
    }

    public OficinaTO(Long id, String cnpj, String especialidade, boolean oficinaPorto, int capacidade, List<ServicoTO> servicos, UsuarioTO usuario, List<HorarioDisponivelTO> horariosDisponiveis) {
        this.id = id;
        this.cnpj = cnpj;
        this.especialidade = especialidade;
        this.oficinaPorto = oficinaPorto;
        this.capacidade = capacidade;
        this.servicos = servicos;
        this.usuario = usuario;
        this.horariosDisponiveis = horariosDisponiveis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @CNPJ(message = "O atributo CNPJ deve ser válido.") @NotBlank(message = "O atributo CNPJ é obrigatório!") String getCnpj() {
        return cnpj;
    }

    public void setCnpj(@CNPJ(message = "O atributo CNPJ deve ser válido.") @NotBlank(message = "O atributo CNPJ é obrigatório!") String cnpj) {
        this.cnpj = cnpj;
    }

    public @NotBlank(message = "O atributo especialidade é obrigatório!") String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(@NotBlank(message = "O atributo especialidade é obrigatório!") String especialidade) {
        this.especialidade = especialidade;
    }

    @NotNull(message = "O atributo oficinaPorto é obrigatório!")
    public boolean isOficinaPorto() {
        return oficinaPorto;
    }

    public void setOficinaPorto(@NotNull(message = "O atributo oficinaPorto é obrigatório!") boolean oficinaPorto) {
        this.oficinaPorto = oficinaPorto;
    }

    @NotNull(message = "A capacidade da oficina é obrigatória!")
    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(@NotNull(message = "A capacidade da oficina é obrigatória!") int capacidade) {
        this.capacidade = capacidade;
    }

    public List<ServicoTO> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicoTO> servicos) {
        this.servicos = servicos;
    }

    public UsuarioTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioTO usuario) {
        this.usuario = usuario;
    }

    public List<HorarioDisponivelTO> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    public void setHorariosDisponiveis(List<HorarioDisponivelTO> horariosDisponiveis) {
        this.horariosDisponiveis = horariosDisponiveis;
    }
}