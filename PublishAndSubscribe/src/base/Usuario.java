package base;

import java.io.Serializable;

/**
 *
 * @author guest-u1pwic
 */
public class Usuario implements Serializable {
    
    private Long id;
    private String nome;

    public Usuario(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

   

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + '}';
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
