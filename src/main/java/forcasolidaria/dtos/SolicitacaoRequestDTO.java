package forcasolidaria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SolicitacaoRequestDTO(
        String dsc,

        @NotNull
        @NotBlank
        String titulo,

        String status,

        @NotNull
        @NotBlank
        int id_usuario,

        @NotBlank
        @NotNull
        int id_local) {
}
