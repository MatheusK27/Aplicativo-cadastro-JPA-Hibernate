package Api.Rest.da.aplicacao.med.voll.infra.security;

import Api.Rest.da.aplicacao.med.voll.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
private String secret;


    public String gerarToken(Usuario usuario){
        try {
            var algoritimo  = Algorithm.HMAC256(secret);

            return  JWT.create()
                    .withIssuer("Api voll.med")
                    .withSubject(usuario.getLogin())
                    .sign(algoritimo);
        } catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        }   
    }

    public String  getSubject(String tokenJWT){
    try {
        var algoritimo  = Algorithm.HMAC256(secret);
       return JWT.require(algoritimo)
                .withIssuer("Api voll.med")
                .build()
               .verify(tokenJWT)
               .getSubject();


    } catch (JWTVerificationException exception){
          throw new RuntimeException(exception);
    }
    }

}
