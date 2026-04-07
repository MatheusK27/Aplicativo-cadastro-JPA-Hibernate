package Api.Rest.da.aplicacao.med.voll.controller;


import Api.Rest.da.aplicacao.med.voll.dominio.medicos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody  @Valid dadosCadastroMedicos dados, UriComponentsBuilder uriBuilder){
        var medico= new Medico(dados);
        repository.save(medico) ;
        var uri= uriBuilder.path("medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(medico);

        }
    @GetMapping
    public ResponseEntity <Page<dadosListagemMedicos>> listar(Pageable paginacao){
      var page= repository.findAllByAtivoTrue(paginacao).map(dadosListagemMedicos::new);
      return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid dadosAtualizarMedicos dados){
        var medico= repository.getReferenceById(dados.id());
        medico.atuazaliarMedico(dados);
        return  ResponseEntity.ok( new dadosDetalhamentoMedicos(medico));

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var medico= repository.getReferenceById(id);
        medico.excluir();
        return  ResponseEntity.ok().build();
    }
     @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico= repository.getReferenceById(id);
        return ResponseEntity.ok(new dadosDetalhamentoMedicos(medico));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity excluirMedicoGeral(@PathVariable Long id){
        var medico= repository.getReferenceById(id);
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
