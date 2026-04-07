package Api.Rest.da.aplicacao.med.voll.controller;


import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.DadosCancelamentoConsultas;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.AgendaDeConsultas;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.DadosAgendamentoConsulta;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.DadosDetalhamentoConsulta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

@Autowired
private AgendaDeConsultas agenda;

    @PostMapping
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){

        agenda.agendar(dados);
       return ResponseEntity.ok( new DadosDetalhamentoConsulta(null , null, null , null));

    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar (DadosCancelamentoConsultas dados){

      agenda.cancelar(dados);
      return ResponseEntity.notFound().build();

    }





}
