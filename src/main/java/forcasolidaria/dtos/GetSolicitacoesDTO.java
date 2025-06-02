package forcasolidaria.dtos;

import forcasolidaria.entities.Solicitacao;

import java.util.List;

public record GetSolicitacoesDTO(List<Solicitacao>solicitacoes) {
}
