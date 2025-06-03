package forcasolidaria.Repositories;

import forcasolidaria.Infrastructure.DatabaseConfig;
import forcasolidaria.entities.Relatorio;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class RelatorioRepository {

    public Relatorio gerarRelatorio() throws SQLException {
        Relatorio relatorio = new Relatorio();

        Connection connection = DatabaseConfig.getConnection();
        //consulta geral Pendente
        String totalPendenteQuery = "SELECT COUNT(*) AS total_pendentes FROM T_FS_SOLICITACAO WHERE (STATUS) = (?)";
        PreparedStatement totalPendente = connection.prepareStatement(totalPendenteQuery);
        totalPendente.setString(1,"pendente");
        ResultSet resultSet = totalPendente.executeQuery();

        if (resultSet.next()){
            int totalGeral = resultSet.getInt("total_pendentes");
            relatorio.setTotalPendente(totalGeral);
            Log.debug("Total geral: " + totalGeral);
        }
        resultSet.close();
        totalPendente.close();

        //consulta - Alimentação Pendente
        String alimentacaoPendenteQuery = "SELECT COUNT(*) AS total_alimentacao_pendente FROM T_FS_SOLICITACAO " +
                "WHERE STATUS = (?) AND ID_CATEGORIA = (?)";
        PreparedStatement preparedStatement1 = connection.prepareStatement(alimentacaoPendenteQuery);
        preparedStatement1.setString(1, "pendente");
        preparedStatement1.setInt(2,1);
        resultSet = preparedStatement1.executeQuery();

        if (resultSet.next()){
            int totalAlimentacaoPendente = resultSet.getInt("total_alimentacao_pendente");
            relatorio.setQntAlimentacaoPendente(totalAlimentacaoPendente);
            Log.debug("Alimentacao Pendente: " + totalAlimentacaoPendente);
        }
        resultSet.close();
        preparedStatement1.close();

        //consulta - Moradia Pendente
        String moradiaPendenteQuery = "SELECT COUNT(*) AS total_moradia_pendente FROM T_FS_SOLICITACAO " +
                "WHERE STATUS = (?) AND ID_CATEGORIA = (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(moradiaPendenteQuery);
        preparedStatement.setString(1,"pendente");
        preparedStatement.setInt(2,2);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            int moradiaPendente = resultSet.getInt("total_moradia_pendente");
            relatorio.setQntMoradiaPendente(moradiaPendente);
            Log.debug("Moradia Pendente: " + moradiaPendente);
        }
        preparedStatement.close();
        resultSet.close();

        //consulta - transporte pendente
        String transportePendenteQuery = "SELECT COUNT(*) AS total_transporte_pendente FROM T_FS_SOLICITACAO " +
                "WHERE STATUS = (?) AND ID_CATEGORIA = (?)";
        PreparedStatement preparedStatement2 = connection.prepareStatement(transportePendenteQuery);
        preparedStatement2.setString(1,"pendente");
        preparedStatement2.setInt(2,3);

        resultSet = preparedStatement2.executeQuery();

        if(resultSet.next()){
            int transportePendente = resultSet.getInt("total_transporte_pendente");
            relatorio.setQntTransportePendente(transportePendente);
            Log.debug("Transporte pendente: " + transportePendente);
        }
        resultSet.close();
        preparedStatement2.close();

        //consulta - orientacao pendente
        String orientacaoPendenteQuery = "SELECT COUNT(*) AS total_orientacao_pendente FROM T_FS_SOLICITACAO " +
                "WHERE STATUS = (?) AND ID_CATEGORIA = (?)";
        PreparedStatement preparedStatement3 = connection.prepareStatement(orientacaoPendenteQuery);
        preparedStatement3.setString(1,"pendente");
        preparedStatement3.setInt(2,4);
        resultSet = preparedStatement3.executeQuery();

        if (resultSet.next()){
            int orientacaoPendente = resultSet.getInt("total_orientacao_pendente");
            relatorio.setQntOrientacaoPendente(orientacaoPendente);
            Log.debug("Orientação Pendente: " + orientacaoPendente);
        }
        resultSet.close();
        preparedStatement3.close();

        //consulta - emergencias medicas pendente
        String emergenciasMedicasQuery = "SELECT COUNT(*) AS total_emergencias_pendente FROM T_FS_SOLICITACAO " +
                "WHERE STATUS = (?) AND ID_CATEGORIA = (?)";
        PreparedStatement preparedStatement4 = connection.prepareStatement(emergenciasMedicasQuery);
        preparedStatement4.setString(1,"pendente");
        preparedStatement4.setInt(2,5);
        resultSet = preparedStatement4.executeQuery();

        if(resultSet.next()){
            int emergenciasPendente = resultSet.getInt("total_emergencias_pendente");
            relatorio.setQntEmergenciasMedicasPendente(emergenciasPendente);
            Log.debug("Emergencias médicas: " + emergenciasPendente);
        }

        resultSet.close();
        preparedStatement4.close();

        //consulta - desastres naturais
        String desastresNaturais = "SELECT COUNT(*) AS total_desastresnaturais_pendente FROM T_FS_SOLICITACAO " +
                "WHERE STATUS = (?) AND ID_CATEGORIA = (?)";
        PreparedStatement preparedStatement5 = connection.prepareStatement(desastresNaturais);
        preparedStatement5.setString(1,"pendente");
        preparedStatement5.setInt(2,6);
        resultSet = preparedStatement5.executeQuery();

        if(resultSet.next()){
            int desastresPendente = resultSet.getInt("total_desastresnaturais_pendente");
            relatorio.setQntDesastresNaturaisPendente(desastresPendente);
            Log.debug("Desastres Naturais Pendente: " + desastresPendente);
        }
        resultSet.close();
        preparedStatement5.close();

        //consulta - outros pendentes
        String outrosPendente = "SELECT COUNT(*) AS total_outros_pendente FROM T_FS_SOLICITACAO " +
                "WHERE STATUS = (?) AND ID_CATEGORIA = (?)";
        PreparedStatement preparedStatement6 = connection.prepareStatement(outrosPendente);
        preparedStatement6.setString(1,"pendente");
        preparedStatement6.setInt(2,7);
        resultSet = preparedStatement6.executeQuery();

        if (resultSet.next()){
            int outrosPendentes = resultSet.getInt("total_outros_pendente");
            relatorio.setQntOutrosPendente(outrosPendentes);
            Log.debug("Outros Pendentes: " + outrosPendentes);
        }

        resultSet.close();
        preparedStatement6.close();

        //consulta - total concluida
        String totalConcluido = "SELECT COUNT(*) AS total_concluido FROM T_FS_SOLICITACAO WHERE (STATUS) = (?)";
        PreparedStatement preparedStatement7 = connection.prepareStatement(totalConcluido);
        preparedStatement7.setString(1, "concluido");
        resultSet = preparedStatement7.executeQuery();

        if (resultSet.next()){
            int totalConcluidoqnt = resultSet.getInt("total_concluido");
            relatorio.setTotalConcluido(totalConcluidoqnt);
            Log.debug("Total Concluido: " + totalConcluidoqnt);
        }
        resultSet.close();
        preparedStatement7.close();

        //consulta - alimentacao concluido
        String alimentacaoConcluido = "SELECT COUNT(*) AS total_alimentacao_concluido FROM T_FS_SOLICITACAO " +
                "WHERE STATUS = (?) AND ID_CATEGORIA = (?)";
        PreparedStatement preparedStatement8 = connection.prepareStatement(alimentacaoConcluido);
        preparedStatement8.setString(1,"concluido");
        preparedStatement8.setInt(2,1);
        resultSet = preparedStatement8.executeQuery();

        if(resultSet.next()){
            int totalAlimentacao = resultSet.getInt("total_alimentacao_concluido");
            relatorio.setQntAlimentacaoConcluido(totalAlimentacao);
            Log.debug("Alimentacação Concluido: " + totalAlimentacao);
        }
        resultSet.close();
        preparedStatement8.close();

        //consulta - moradia concluido
        String moradiaConcluido = "SELECT COUNT(*) AS total_moradia_concluido FROM T_FS_SOLICITACAO " +
                "WHERE STATUS = (?) AND ID_CATEGORIA = (?)";
        PreparedStatement preparedStatement9 = connection.prepareStatement(moradiaConcluido);
        preparedStatement9.setString(1,"concluido");
        preparedStatement9.setInt(2,2);
        resultSet = preparedStatement9.executeQuery();

        if (resultSet.next()){
            int moradiaConcluida = resultSet.getInt("total_moradia_concluido");
            relatorio.setQntMoradiaConcluido(moradiaConcluida);
            Log.debug("Moradia concluido: " + moradiaConcluida);
        }
        resultSet.close();
        preparedStatement9.close();

        //consulta - transporte concluido
        String moradia = "SELECT COUNT(*) AS total_transporte_concluido FROM T_FS_SOLICITACAO " +
                "WHERE STATUS = (?) AND ID_CATEGORIA = (?)";
        PreparedStatement preparedStatement10 = connection.prepareStatement(moradia);
        preparedStatement10.setString(1,"concluido");
        preparedStatement10.setInt(2,3);
        resultSet = preparedStatement10.executeQuery();

        if (resultSet.next()){
            int moradiaConcluida = resultSet.getInt("total_transporte_concluido");
            relatorio.setQntMoradiaConcluido(moradiaConcluida);
            Log.debug("Moradia concluida:" + moradiaConcluida);
        }

        resultSet.close();
        preparedStatement10.close();

        //consulta - orientação concluida
        String orientacaoConcluida = "SELECT COUNT(*) AS total_orientacao_concluido FROM T_FS_SOLICITACAO " +
                "WHERE STATUS = (?) AND ID_CATEGORIA = (?)";
        PreparedStatement preparedStatement11 = connection.prepareStatement(orientacaoConcluida);
        preparedStatement11.setString(1, "concluido");
        preparedStatement11.setInt(2,4);
        resultSet = preparedStatement11.executeQuery();

        if (resultSet.next()){
            int orientacaoTotal = resultSet.getInt("total_orientacao_concluido");
            relatorio.setQntOrientacaoConcluido(orientacaoTotal);
            Log.debug("Orientação: " + orientacaoTotal);
        }
        resultSet.close();
        preparedStatement11.close();

        //consulta - emergencias medicas concluidas
        String emergenciaQuery = "SELECT COUNT(*) AS total_orientacao_concluido FROM T_FS_SOLICITACAO " +
                "WHERE STATUS = (?) AND ID_CATEGORIA = (?)";
        PreparedStatement preparedStatement12 = connection.prepareStatement(emergenciaQuery);
        preparedStatement12.setString(1,"concluido");
        preparedStatement12.setInt(2,5);
        resultSet = preparedStatement12.executeQuery();

        if(resultSet.next()){
            int emergenciaTotal = resultSet.getInt("total_orientacao_concluido");
            relatorio.setQntEmergenciasMedicasConcluido(emergenciaTotal);
            Log.debug("Emergencia Medicas: " + emergenciaTotal);
        }
        resultSet.close();
        preparedStatement12.close();

        //consulta - desastres naturais concluidas
        String desastresQuery = "SELECT COUNT(*) AS total_desastres_concluido FROM T_FS_SOLICITACAO " +
                "WHERE STATUS = (?) AND ID_CATEGORIA = (?)";
        PreparedStatement preparedStatement13 = connection.prepareStatement(desastresQuery);
        preparedStatement13.setString(1,"concluido");
        preparedStatement13.setInt(2,6);
        resultSet = preparedStatement13.executeQuery();

        if (resultSet.next()){
            int desastresTotal = resultSet.getInt("total_desastres_concluido");
            relatorio.setQntDesastresNaturaisConcluido(desastresTotal);
            Log.debug("Desastres Naturais: " + desastresTotal);
        }
        resultSet.close();
        preparedStatement13.close();

        //consulta - outros concluida

        String outrosQuery = "SELECT COUNT(*) AS total_outros_concluido FROM T_FS_SOLICITACAO " +
                "WHERE STATUS = (?) AND ID_CATEGORIA = (?)";
        PreparedStatement preparedStatement14 = connection.prepareStatement(outrosQuery);
        preparedStatement14.setString(1,"concluido");
        preparedStatement14.setInt(2,7);
        resultSet = preparedStatement14.executeQuery();

        if(resultSet.next()){
            int outrosConcluido = resultSet.getInt("total_outros_concluido");
            relatorio.setQntOutrosConcluido(outrosConcluido);
            Log.debug("Outros Concluidos: " + outrosConcluido);
        }
        resultSet.close();
        preparedStatement14.close();

        return relatorio;
    }


}
