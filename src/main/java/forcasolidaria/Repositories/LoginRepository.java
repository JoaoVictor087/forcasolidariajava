package forcasolidaria.Repositories;


import forcasolidaria.Infrastructure.DatabaseConfig;
import forcasolidaria.dtos.CadastroDTO;
import forcasolidaria.dtos.LoginRequestDTO;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;

@ApplicationScoped
public class LoginRepository {

    public void criarConta(CadastroDTO dto) throws SQLException, IllegalAccessException{

        if(dto.cpf().isEmpty() || dto.email().isEmpty() || dto.senha().isEmpty() || dto.nm_voluntario().isEmpty()){
            Log.error("Há algum campo obrigatório vazio");
            throw new IllegalAccessException();
        }

        String query = "INSERT INTO T_FS_VOLUNTARIO (email, senha, cpf, dt_nascimento_voluntario, gen_voluntario, nm_voluntario)" +
                " VALUES (?,?,?,?,?,?)";
        String consulta = "SELECT email, cpf from T_FS_VOLUNTARIO where email = ? OR cpf = ?";

        Connection connection = DatabaseConfig.getConnection();
        PreparedStatement statementconsulta = connection.prepareStatement(consulta);
        statementconsulta.setString(1, dto.email());
        statementconsulta.setString(2, dto.cpf());
        ResultSet resultSet = statementconsulta.executeQuery();

        if (resultSet.next()){
            Log.error("já existe cpf ou email cadastrado no banco de dados");
            throw new SQLException();
        }else {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, dto.email());
            preparedStatement.setString(2, dto.senha());
            preparedStatement.setString(3, dto.cpf());
            preparedStatement.setDate(4, Date.valueOf(dto.dt_nascimento_voluntario()));
            preparedStatement.setString(5, String.valueOf(dto.gen_voluntario()));
            preparedStatement.setString(6,dto.nm_voluntario());

            preparedStatement.execute();

        }
    }
    public boolean loginUsuario(LoginRequestDTO loginRequestDTO) throws SQLException{

        String query = "SELECT * FROM T_FS_VOLUNTARIO WHERE email = ?";
        Connection connection = DatabaseConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, loginRequestDTO.email());
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {

            if (resultSet.getString(4).equals(loginRequestDTO.senha())) {
                return true;
            }
        }
        return false;
    }
}


