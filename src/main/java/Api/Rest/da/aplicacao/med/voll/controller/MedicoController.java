package Api.Rest.da.aplicacao.med.voll.controller;


import Api.Rest.da.aplicacao.med.voll.medicos.dadosAtualizarMedicos;
import Api.Rest.da.aplicacao.med.voll.medicos.Medico;
import Api.Rest.da.aplicacao.med.voll.medicos.MedicoRepository;
import Api.Rest.da.aplicacao.med.voll.medicos.dadosCadastroMedicos;
import Api.Rest.da.aplicacao.med.voll.medicos.dadosListagemMedicos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody  @Valid  dadosCadastroMedicos dados){
        repository.save(new Medico(dados));



        }
    @GetMapping
    public Page<dadosListagemMedicos> listar(Pageable paginacao){
      return  repository.findAll(paginacao).map(dadosListagemMedicos::new);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid dadosAtualizarMedicos dados){
        var medico= repository.getReferenceById(dados.id());
        medico.atuazaliarMedico(dados);

    }
}
