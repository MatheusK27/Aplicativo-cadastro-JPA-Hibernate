package Api.Rest.da.aplicacao.med.voll.controller;


import Api.Rest.da.aplicacao.med.voll.infra.security.Roles;
import Api.Rest.da.aplicacao.med.voll.usuario.DadosCadastroUsuario;
import Api.Rest.da.aplicacao.med.voll.usuario.Usuario;
import Api.Rest.da.aplicacao.med.voll.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity  cadastrarUsuario(@RequestBody DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) throws Exception {
       var usuario = new Usuario(dados);
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
       repository.save(usuario);
        var uri= uriBuilder.path("usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarUsuario(@PathVariable Long id)  {
        repository.deleteById(id);
          return ResponseEntity.ok().build();

    }

    @PutMapping("/admin/promover")
    public ResponseEntity promover(@RequestBody String login) {

        Usuario usuario = repository.findByLogin(login);

        usuario.setRole(Roles.ADMIN);

        repository.save(usuario);

        return ResponseEntity.ok().build();
    }


}
