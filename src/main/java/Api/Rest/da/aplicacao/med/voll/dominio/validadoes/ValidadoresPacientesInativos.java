package Api.Rest.da.aplicacao.med.voll.dominio.validadoes;

import Api.Rest.da.aplicacao.med.voll.dominio.ValidacaoException;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.DadosAgendamentoConsulta;
import Api.Rest.da.aplicacao.med.voll.dominio.pacientes.Paciente;
import Api.Rest.da.aplicacao.med.voll.dominio.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadoresPacientesInativos implements ValidadorAgendamentoDeConsultas  {
    @Autowired
    private PacienteRepository repository;

    public  void validar(DadosAgendamentoConsulta dados){
        var paciente = repository.existsByIdAndAtivoTrue(dados.idPaciente());
        if(!(boolean) paciente){
            throw  new ValidacaoException("Paciente excluído do banco de dados");
        }
    }

}
