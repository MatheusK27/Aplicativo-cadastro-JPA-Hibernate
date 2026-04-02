package Api.Rest.da.aplicacao.med.voll.dominio.pacientes;
import Api.Rest.da.aplicacao.med.voll.dominio.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;


@Table(name="pacientes")
@Entity(name="Paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;


    @Embedded
    private Endereco endereco;

     private boolean ativo;

    public Paciente(dadosCadastroPaciente dados) {
        this.ativo=true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());

    }



    public void atualizarPaciente(@Valid dadosAtualizarPacientes dados) {
        if(dados.nome()!=null){
            this.nome = dados.nome();
        }
        if (dados.telefone()!=null){
            this.telefone = dados.telefone();
        }
        if (dados.email()!=null){
            this.email = dados.email();
        }

    }
    public void excluir(){

        this.ativo=false;
    }
}
