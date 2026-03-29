package br.com.cqrstraining.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProductCommand implements Command {
    
    @Setter
    private Integer id;

    // private String imageUrl;
    
    // @NotBlank
    // private String name;
    
    // @NotBlank
    // private String description;
    
    // @NotNull
    // private BigDecimal value;

}
