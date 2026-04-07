package Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta;


import Api.Rest.da.aplicacao.med.voll.dominio.ValidacaoException;
import Api.Rest.da.aplicacao.med.voll.dominio.medicos.Medico;
import Api.Rest.da.aplicacao.med.voll.dominio.medicos.MedicoRepository;

import Api.Rest.da.aplicacao.med.voll.dominio.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void  agendar(DadosAgendamentoConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("ID do paciente inválido ");
        }
        if (dados.idMedico()!=null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("ID do médico inválido ");
        }

        var medico= escolherMedico(dados);
        var paciente= pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta =  new Consulta(null, medico, paciente, dados.data());
        repository.save(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico()!=null){
            return  medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidades()!=null){
            throw new ValidacaoException("Especialidade é obrigatória ");
        }

        return medicoRepository.escolherMedicoAleatoriLivreNaData(dados.especialidades(), dados.especialidades());
    }

}
