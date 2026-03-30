package Api.Rest.da.aplicacao.med.voll.controller;




import Api.Rest.da.aplicacao.med.voll.pacientes.Paciente;
import Api.Rest.da.aplicacao.med.voll.pacientes.PacienteRepository;
import Api.Rest.da.aplicacao.med.voll.pacientes.dadosCadastroPaciente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
