package forcasolidaria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SolicitacaoRequestDTO(
        String dsc,

        @NotNull
        @NotBlank
        String titulo,

        String nm_usuario,


        @NotBlank
        @NotNull
        String gen_usuario,

        @NotBlank
        @NotNull
        LocalDate dt_nascimento_usuario,

        @NotBlank
        @NotNull
        String nm_categoria,

        @NotNull
        @NotBlank
        String zona

) {
}
