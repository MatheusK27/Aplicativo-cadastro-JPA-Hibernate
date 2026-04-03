package Api.Rest.da.aplicacao.med.voll.infra.sicurity;

import Api.Rest.da.aplicacao.med.voll.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    public String gerarToken(Usuario usuario){
        try {
            var algoritimo  = Algorithm.HMAC256("123456");

            return  JWT.create()
                    .withIssuer("Api voll.med")
                    .withSubject(usuario.getLogin())
                    .sign(algoritimo);
        } catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        }
    }
}
