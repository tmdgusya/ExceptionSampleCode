package me.roach.demobootweb;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class Event {

    private Integer id;

    @NotBlank
    private String name;

    @Min(value = 0)
    private Integer limit;

}
