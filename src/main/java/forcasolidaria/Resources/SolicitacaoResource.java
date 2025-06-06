package forcasolidaria.Resources;

import forcasolidaria.Repositories.RelatorioRepository;
import forcasolidaria.Repositories.SolicitacaoRepository;
import forcasolidaria.dtos.GetSolicitacoesDTO;
import forcasolidaria.dtos.RelatorioResponseDTO;
import forcasolidaria.dtos.SolicitacaoRequestDTO;
import forcasolidaria.entities.Relatorio;
import forcasolidaria.entities.Solicitacao;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/Solicitacao")
public class SolicitacaoResource {

    @Inject
    SolicitacaoRepository solicitacaoRepository;

    @Inject
    RelatorioRepository relatorioRepository;

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

    @Path("/GetSolicitacoes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSolicitacoes(){
    try {
        List<Solicitacao> listasolicitacao = solicitacaoRepository.retornarListaSolicitacoes();
        return Response.status(Response.Status.OK)
                .entity(new GetSolicitacoesDTO(listasolicitacao))
                .build();
    }catch (SQLException e){
        Log.error("Erro ao conectar no banco de dados");
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

    @Path("/relatorio")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRelatorio(){
        try {
            Relatorio relatorio = relatorioRepository.gerarRelatorio();
            return Response.status(Response.Status.OK)
                    .entity(new RelatorioResponseDTO(relatorio))
                    .build();
        }catch (SQLException e){
            Log.error("Erro ao conectar no banco de dado");
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
