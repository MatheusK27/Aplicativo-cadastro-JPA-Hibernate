package Api.Rest.da.aplicacao.med.voll.dominio.medicos;

import Api.Rest.da.aplicacao.med.voll.dominio.endereco.Endereco;
import  jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;


@Table(name = "medicos")
@Entity(name =  "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidades especialidade;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Medico(dadosCadastroMedicos dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidades();
        this.endereco = new Endereco(dados.endereco());


    }


    public void atuazaliarMedico(@Valid dadosAtualizarMedicos dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInfomacoes(dados.endereco());
        }
        this.ativo = true;
    }


    public void excluir() {
        this.ativo = false;

    }
}


