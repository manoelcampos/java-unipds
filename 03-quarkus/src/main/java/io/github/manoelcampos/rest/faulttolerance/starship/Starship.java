package io.github.manoelcampos.rest.faulttolerance.starship;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;
import java.util.List;

/// Record que representa uma nave estelar do universo Star Wars,
/// de acordo com a API REST do site [SWAPI](https://swapi.info/api/).
/// Assim, objetos JSON recebidos via HTTP são mapeados para
/// instâncias deste record. Tais instâncias a serem enviadas
/// para a API são convertidas para JSON automaticamente pela
/// extensão quarkus-rest-client-jsonb.
///
/// @author Manoel Campos
public record Starship(
        String name, String model, String manufacturer,
        String cost_in_credits, String length,
        String max_atmosphering_speed, String crew,
        String passengers, String cargo_capacity,
        String consumables, String hyperdrive_rating,
        String MGLT, String starship_class,
        List<Object> pilots, List<String> films,

        @JsonFormat(pattern=zonedDateTimeFormat)
        ZonedDateTime created,

        @JsonFormat(pattern=zonedDateTimeFormat)
        ZonedDateTime edited,

        String url)
{
    private static final String zonedDateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";
}
