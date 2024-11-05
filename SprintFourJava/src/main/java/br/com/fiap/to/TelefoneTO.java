package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TelefoneTO {

    private Long id;

    @NotNull
    @Size(min = 5, max = 50, message = "O atributo de número deve conter entre 5 a 50 digitos")
    private int numero;

    @NotNull
    @Size(min = 1, max = 4, message = "O atributo de DDD deve conter entre 1 a 4 digitos")
    private int DDD;

    @NotNull
    @Size(min = 1, max = 4, message = "O atributo de DDI deve conter entre 1 a 4 digitos")
    private int DDI;

    @Size(min = 1, max = 500, message = "O atributo de lembrete deve conter entre 1 a 500 caracteres")
    private String lembrete;

    private UsuarioTO usuario;

    public TelefoneTO() {
    }

    public TelefoneTO(Long id, int numero, int DDD, int DDI, String lembrete, UsuarioTO usuario) {
        this.id = id;
        this.numero = numero;
        this.DDD = DDD;
        this.DDI = DDI;
        this.lembrete = lembrete;
        this.usuario = usuario;
    }

    public UsuarioTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioTO usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 5, max = 50, message = "O atributo de número deve conter entre 5 a 50 digitos")
    public int getNumero() {
        return numero;
    }

    public void setNumero(@NotNull @Size(min = 5, max = 50, message = "O atributo de número deve conter entre 5 a 50 digitos") int numero) {
        this.numero = numero;
    }

    @NotNull
    @Size(min = 1, max = 4, message = "O atributo de DDD deve conter entre 1 a 4 digitos")
    public int getDDD() {
        return DDD;
    }

    public void setDDD(@NotNull @Size(min = 1, max = 4, message = "O atributo de DDD deve conter entre 1 a 4 digitos") int DDD) {
        this.DDD = DDD;
    }

    @NotNull
    @Size(min = 1, max = 4, message = "O atributo de DDI deve conter entre 1 a 4 digitos")
    public int getDDI() {
        return DDI;
    }

    public void setDDI(@NotNull @Size(min = 1, max = 4, message = "O atributo de DDI deve conter entre 1 a 4 digitos") int DDI) {
        this.DDI = DDI;
    }

    public @Size(min = 1, max = 500, message = "O atributo de lembrete deve conter entre 1 a 500 caracteres") String getLembrete() {
        return lembrete;
    }

    public void setLembrete(@Size(min = 1, max = 500, message = "O atributo de lembrete deve conter entre 1 a 500 caracteres") String lembrete) {
        this.lembrete = lembrete;
    }
}
