package forcasolidaria.Resources;

import forcasolidaria.Repositories.SolicitacaoRepository;
import forcasolidaria.dtos.SolicitacaoRequestDTO;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/Solicitacao")
public class SolicitacaoResource {

    @Inject
    SolicitacaoRepository solicitacaoRepository;

    @Path("/criarSolicitacao")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response enviarSolicitacao(SolicitacaoRequestDTO solicitacaoRequestDTO){
        try {
            solicitacaoRepository.criarNovaSolicitao(solicitacaoRequestDTO);
            Log.info("Solicitação enviada com sucesso");
            return Response.status(Response.Status.CREATED)
                    .entity("Solicitação enviada com sucesso")
                    .build();
        }catch (SQLException e){
            Log.error("Erro ao conectar-se com o banco de dados");
            Log.error(e.getErrorCode());
            Log.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao conectar no banco de dados")
                    .build();
        }catch (Exception e){
            Log.error("Erro interno do servidor");
            Log.error(e.getClass());
            Log.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor")
                    .build();
        }
    }

}
