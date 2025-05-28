package forcasolidaria.Resources;

import forcasolidaria.CORS.CORSFilter;
import forcasolidaria.Repositories.LoginRepository;
import forcasolidaria.dtos.CadastroDTO;
import forcasolidaria.dtos.LoginReponseDTO;
import forcasolidaria.dtos.LoginRequestDTO;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/user")
public class LoginResource {

    @Inject
    LoginRepository loginRepository;

    @POST
    @Path("/cadastro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response criarCadastro(@Valid CadastroDTO cadastroDTO) {
        try {
            loginRepository.criarConta(cadastroDTO);

        }catch (SQLException e){
            Log.error("Não foi possível se conectar ao banco de dados");
            Log.error(e.getErrorCode());
            Log.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Não foi possível se conectar ao banco de dados")
                    .build();
        }catch (IllegalAccessException e){
            Log.error("Há algum campo obrigatório vazio");
            Log.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Há algum campo obrigatório vazio")
                    .build();
        }catch (Exception e){
            Log.error("Erro interno do servidor");
            Log.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor")
                    .build();
        }
        return Response.status(Response.Status.CREATED)
                .entity("Usuário cadastrado com sucesso")
                .build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logarUsuario(@Valid LoginRequestDTO loginRequestDTO){
        try {
            boolean logado = loginRepository.loginUsuario(loginRequestDTO);
            if (logado){
                Log.info("Usuário Logado");
                return Response.status(Response.Status.OK)
                        .header("Access-Control-Allow-Origin", "*")
                        .entity(new LoginReponseDTO(true, "Usuário logado com sucesso"))
                        .build();
            }else{
                Log.info("email e/ou senha do usuário inválido");
                return Response.status(Response.Status.UNAUTHORIZED)
                        .header("Access-Control-Allow-Origin", "*")
                        .entity(new LoginReponseDTO(false, "email e/ou senha do usuário inválido"))
                        .build();
            }
        }catch (SQLException e){
            Log.error("Não foi possível conectar-se ao banco de dados");
            Log.error(e.getErrorCode());
            Log.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(new LoginReponseDTO(false, "Não foi possível conectar-se ao banco de dados"))
                    .build();
        }catch (Exception e){
            Log.error("Erro interno do servidor");
            Log.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(new LoginReponseDTO(false, "Erro interno do servidor"))
                    .build();
        }
    }
}
