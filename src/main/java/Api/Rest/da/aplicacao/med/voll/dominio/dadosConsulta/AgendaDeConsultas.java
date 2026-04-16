package Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta;


import Api.Rest.da.aplicacao.med.voll.dominio.validadoes.cancelamento.ValidarCancelamentoConsulta;
import Api.Rest.da.aplicacao.med.voll.dominio.ValidacaoException;
import Api.Rest.da.aplicacao.med.voll.dominio.medicos.Medico;
import Api.Rest.da.aplicacao.med.voll.dominio.medicos.MedicoRepository;

import Api.Rest.da.aplicacao.med.voll.dominio.pacientes.PacienteRepository;
import Api.Rest.da.aplicacao.med.voll.dominio.validadoes.ValidadorAgendamentoDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {



    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidarCancelamentoConsulta> validar;

    @Autowired
    private List<ValidadorAgendamentoDeConsultas>  validador;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("ID do paciente inválido ");
        }
        if (dados.idMedico()!=null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("ID do médico inválido ");
        }

        validador.forEach(v-> v.validar(dados));

        var medico= escolherMedico(dados);
        var paciente= pacienteRepository.getReferenceById(dados.idPaciente());

        if(medico == null){
            throw new ValidacaoException("Não existe médido disponóivel nessa data");
        }

        var consulta =  new Consulta(null, medico, paciente, dados.data(),null);
        repository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico()!=null){
            return  medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidades()!=null){
            throw new ValidacaoException("Especialidade é obrigatória ");
        }

        return medicoRepository.escolherMedicoAleatoriLivreNaData(dados.especialidades(),dados.data().toLocalDate() );
    }

    public void cancelar(DadosCancelamentoConsultas dados){
        if(!repository.existsById(dados.idConsulta())){
            throw new ValidacaoException("ID da consulta não existe ");
        }
        validar.forEach(v -> v.validar(dados));
        
        var consulta= repository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

}
