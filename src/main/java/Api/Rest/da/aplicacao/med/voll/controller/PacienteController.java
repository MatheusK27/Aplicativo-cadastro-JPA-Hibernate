package Api.Rest.da.aplicacao.med.voll.controller;




import Api.Rest.da.aplicacao.med.voll.pacientes.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid dadosCadastroPaciente dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<dadosListagemPacientes> listar (Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(dadosListagemPacientes:: new);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid dadosAtualizarPacientes dados){
        var paciente= repository.getReferenceById(dados.id());
        paciente.atualizarPaciente(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir (@PathVariable Long id){
        var paciente= repository.getReferenceById(id);
        paciente.excluir();
    }


}
