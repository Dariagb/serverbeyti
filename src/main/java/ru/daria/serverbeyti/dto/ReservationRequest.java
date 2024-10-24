package ru.daria.serverbeyti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.daria.serverbeyti.model.Manufacturer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationRequest {
  private Manufacturer manufacturer;
}
