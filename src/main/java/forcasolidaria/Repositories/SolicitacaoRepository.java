package forcasolidaria.Repositories;

import forcasolidaria.Infrastructure.DatabaseConfig;
import forcasolidaria.dtos.SolicitacaoRequestDTO;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;

@ApplicationScoped
public class SolicitacaoRepository {

    public void criarNovaSolicitao(SolicitacaoRequestDTO solicitacaoRequestDTO) throws SQLException {
        //Criação de Usuário na tabela T_FS_USUARIO
        String criarUsuarioQuery = "INSERT INTO T_FS_USUARIO (NM_USUARIO,DT_NASCIMENTO_USUARIO, GEN_USUARIO) " +
                "VALUES (?,?,?)";

        Connection connection = DatabaseConfig.getConnection();
        PreparedStatement usuarioStatement = connection.prepareStatement(criarUsuarioQuery);

        usuarioStatement.setString(1, solicitacaoRequestDTO.nm_usuario());
        usuarioStatement.setDate(2, Date.valueOf(solicitacaoRequestDTO.dt_nascimento_usuario()));
        usuarioStatement.setString(3, solicitacaoRequestDTO.gen_usuario());

        usuarioStatement.executeUpdate();

        //Envio da Solicitação
        String solicitacaoQuery = "INSERT INTO T_FS_SOLICITACAO (DSC, TITULO, STATUS) " +
                "VALUES (?,?,?)";
        PreparedStatement solicitacaoStatement = connection.prepareStatement(solicitacaoQuery);

        solicitacaoStatement.setString(1,solicitacaoRequestDTO.dsc());
        solicitacaoStatement.setString(2,solicitacaoRequestDTO.titulo());
        solicitacaoStatement.setString(3,"pendente");

        solicitacaoStatement.executeUpdate();

    }

    public int transformaZonaEmNumero(String zona){
        if (zona.equalsIgnoreCase("zona norte")){
            Log.info("valor transformado: 1" );
            return 1;
        }
        if (zona.equalsIgnoreCase("zona leste")){
            Log.info("valor transformado: 2" );
            return 2;
        }
        if (zona.equalsIgnoreCase("zona sul")){
            Log.info("valor transformado: 3" );
            return 3;
        }
        if (zona.equalsIgnoreCase("zona oeste")){
            Log.info("valor transformado: 4" );
            return 4;
        }
        if (zona.equalsIgnoreCase("zona central")){
            Log.info("valor transformado: 5" );
            return 5;
        }
        Log.info("valor transformado: 0" );
        return 0;
    }

}
