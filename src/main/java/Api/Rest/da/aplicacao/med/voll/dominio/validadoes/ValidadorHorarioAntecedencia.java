package Api.Rest.da.aplicacao.med.voll.dominio.validadoes;

import Api.Rest.da.aplicacao.med.voll.dominio.ValidacaoException;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsultas  {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta= dados.data();

        var agora = LocalDateTime.now();
        var diferencaMin= Duration.between(agora,dataConsulta).toMinutes();

        if (diferencaMin<30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedencia");
        }


    }
}
