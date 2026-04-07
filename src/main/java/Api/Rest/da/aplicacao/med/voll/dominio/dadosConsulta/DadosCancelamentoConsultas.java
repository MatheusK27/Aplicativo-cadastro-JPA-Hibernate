package Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsultas(

    @NotNull
    Long idConsulta,


    @NotNull
    MotivoCancelamento motivo){

}



