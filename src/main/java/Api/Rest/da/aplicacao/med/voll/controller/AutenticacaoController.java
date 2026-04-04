package Api.Rest.da.aplicacao.med.voll.controller;


import Api.Rest.da.aplicacao.med.voll.infra.security.TokenService;
import Api.Rest.da.aplicacao.med.voll.sicutiry.DadosTokenJWT;
import Api.Rest.da.aplicacao.med.voll.usuario.DadosAutenticacao;
import Api.Rest.da.aplicacao.med.voll.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager maneger;

    @Autowired
    private TokenService tokenService;



    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var token =  new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
       var autenticacao= maneger.authenticate(token);
       var tokenJWT= tokenService.gerarToken((Usuario) autenticacao.getPrincipal());
       return ResponseEntity.ok( new DadosTokenJWT(tokenJWT));
    }



}
