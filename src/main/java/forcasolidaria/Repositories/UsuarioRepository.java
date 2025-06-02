package forcasolidaria.Repositories;


import forcasolidaria.Infrastructure.DatabaseConfig;
import forcasolidaria.dtos.SolicitacaoRequestDTO;
import forcasolidaria.entities.Usuario;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;

@ApplicationScoped
public class UsuarioRepository {

    public long criarUsuario(SolicitacaoRequestDTO dto) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setNm_usuario(dto.nm_usuario());
        usuario.setDt_nascimento_usuario(Date.valueOf(dto.dt_nascimento_usuario()));
        usuario.setGen_usuario(dto.gen_usuario());
        String[] generatedColumns = { "ID_USUARIO" };
        String query = "INSERT INTO T_FS_USUARIO (NM_USUARIO,DT_NASCIMENTO_USUARIO, GEN_USUARIO)"
                + "VALUES (?,?,?)";
        Connection connection = DatabaseConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query,generatedColumns);

        preparedStatement.setString(1,usuario.getNm_usuario());
        preparedStatement.setDate(2, usuario.getDt_nascimento_usuario());
        preparedStatement.setObject(3, usuario.getGen_usuario());

        preparedStatement.executeUpdate();
        Log.info("Usuário criado");

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

        if (generatedKeys.next()) {
            long idGerado = generatedKeys.getLong(1);
            Log.info("ID do usuário retornado com sucesso: " + idGerado);
            return idGerado;
        } else {
            throw new SQLException("Falha grave: O Oracle não retornou o ID_USUARIO gerado.");
        }

    }

}
