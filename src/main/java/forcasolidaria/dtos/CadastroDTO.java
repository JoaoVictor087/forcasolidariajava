package forcasolidaria.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record CadastroDTO(
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail deve ser válido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String senha,

        @NotBlank(message = "O CPF é obrigatório")
        String cpf,

        @NotNull(message = "A data de nascimento é obrigatória")
        LocalDate dt_nascimento_voluntario,

        String gen_voluntario,

        @NotBlank(message = "O nm_voluntario é obrigatório")
        String nm_voluntario
) {}
