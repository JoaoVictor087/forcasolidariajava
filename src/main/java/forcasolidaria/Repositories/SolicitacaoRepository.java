package forcasolidaria.Repositories;

import forcasolidaria.Infrastructure.DatabaseConfig;
import forcasolidaria.dtos.SolicitacaoRequestDTO;
import forcasolidaria.entities.Solicitacao;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SolicitacaoRepository {

    @Inject
    UsuarioRepository usuarioRepository;

    public void criarNovaSolicitao(SolicitacaoRequestDTO dto) throws SQLException {
        Solicitacao solicitacao = new Solicitacao();
        long id_usuario = usuarioRepository.criarUsuario(dto);
        int id_categoria = retornaIDCategoria(dto.nm_categoria());
        int id_zona = retornaIDZona(dto.zona());
        Log.info("id_categoria: " + id_categoria);
        Log.info("id_usuario: " + id_usuario);
        Log.info("id_zona" + id_zona);

        solicitacao.setDsc(dto.dsc());
        solicitacao.setId_categoria(id_categoria);
        solicitacao.setStatus("pendente");
        solicitacao.setId_usuario((int) id_usuario);
        solicitacao.setTitulo(dto.titulo());


        String query = "INSERT INTO T_FS_SOLICITACAO(DSC,TITULO,STATUS,ID_USUARIO,ID_CATEGORIA,id_zona, endereco)"
                + "VALUES (?,?,?,?,?,?,?)";
        Connection connection = DatabaseConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, solicitacao.getDsc());
        preparedStatement.setString(2, solicitacao.getTitulo());
        preparedStatement.setString(3, solicitacao.getStatus());
        preparedStatement.setLong(4, id_usuario);
        preparedStatement.setInt(5,id_categoria);
        preparedStatement.setInt(6,id_zona);
        preparedStatement.setString(7,dto.endereco());

        preparedStatement.executeUpdate();
        Log.info("Solicitação criada");
    }


    public int retornaIDCategoria(String categoria){
        if (categoria.equalsIgnoreCase("alimentacao")||
                categoria.equalsIgnoreCase("alimentação")){
            return 1;
        } else if (categoria.equalsIgnoreCase("moradia")) {
            return 2;
        } else if (categoria.equalsIgnoreCase("transporte")) {
            return 3;
        } else if (categoria.equalsIgnoreCase("orientacao")||
        categoria.equalsIgnoreCase("orientação")) {
            return 4;
        } else if (categoria.equalsIgnoreCase("emergencias medicas") ||
            categoria.equalsIgnoreCase("emergências médicas")){
            return 5;
        }else if(categoria.equalsIgnoreCase("desastres naturais")){
            return 6;
        }else if(categoria.equalsIgnoreCase("outros")){
            return 7;
        }

        return 0;
    }

    public int retornaIDZona(String zona){
        if (zona.equalsIgnoreCase("zona norte")){
            return 1;
        }
        if (zona.equalsIgnoreCase("zona leste")){
            return 2;
        }
        if (zona.equalsIgnoreCase("zona sul")){
            return 3;
        }
        if (zona.equalsIgnoreCase("zona oeste")){
            return 4;
        }
        if (zona.equalsIgnoreCase("zona central")||zona.equalsIgnoreCase("centro")){
            return 5;
        }
        return 0;
    }

    public List<Solicitacao> retornarListaSolicitacoes() throws SQLException {
        List<Solicitacao> listaDeSolicitacao = new ArrayList<>();

        Connection connection = DatabaseConfig.getConnection();
        String query = "SELECT s.*, u.NM_USUARIO " +
                "FROM T_FS_SOLICITACAO s " +
                "JOIN T_FS_USUARIO u ON s.ID_USUARIO = u.ID_USUARIO " +
                "WHERE s.STATUS = (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "pendente");
        ResultSet resultSet = preparedStatement.executeQuery();


        while (resultSet.next()) {
            Solicitacao solicitacao = new Solicitacao();

            solicitacao.setTitulo(resultSet.getString("TITULO"));
            solicitacao.setStatus(resultSet.getString("STATUS"));
            solicitacao.setDsc(resultSet.getString("DSC"));
            solicitacao.setId_usuario(resultSet.getInt("ID_USUARIO"));
            solicitacao.setId_categoria(resultSet.getInt("ID_CATEGORIA"));
            solicitacao.setId_zona(resultSet.getInt("ID_ZONA"));
            solicitacao.setZona(retornaNomeZona(resultSet.getInt("ID_ZONA")));
            solicitacao.setNm_usuario(resultSet.getString("NM_USUARIO"));
            solicitacao.setEndereco(resultSet.getString("ENDERECO"));

            listaDeSolicitacao.add(solicitacao);
        }
        return listaDeSolicitacao;

    }

    public String retornaNomeZona(int zona){
        if (zona == 1){
            return "zona norte";
        }
        if (zona == 2){
            return "zona leste";
        }
        if (zona == 3){
            return "zona sul";
        }
        if (zona == 4){
            return "zona oeste";
        }
        if (zona == 5){
            return "centro";
        }
        return null;
    }

}

