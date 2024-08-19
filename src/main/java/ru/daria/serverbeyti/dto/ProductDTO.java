package ru.daria.serverbeyti.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductDTO {
    String name;
    Long volume;
    Long shadeNumber;

}
