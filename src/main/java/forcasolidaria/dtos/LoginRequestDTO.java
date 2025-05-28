package forcasolidaria.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(
        @NotBlank (message = "campo email não pode estar vazio")
        @Email (message = "email deve ser válido")
        @NotNull (message = "campo email não pode ser nulo")
        String email,

        @NotBlank
        @NotNull
        String senha
) {
}
