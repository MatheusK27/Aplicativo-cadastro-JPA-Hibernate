package Api.Rest.da.aplicacao.med.voll.cancelamento;

import Api.Rest.da.aplicacao.med.voll.dominio.ValidacaoException;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.ConsultaRepository;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.DadosCancelamentoConsultas;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorCancelamentoConsulta implements ValidarCancelamentoConsulta {

    private ConsultaRepository repository;

    public void validar(DadosCancelamentoConsultas dados ){
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora= LocalDateTime.now();
        var duracao= Duration.between(agora, consulta.getData()) .toHours();

        if(duracao <24){
            throw  new ValidacaoException("Consulta não pode ser cancelada com menos de 24 horas");
        }
    }
}
