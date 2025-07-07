package com.crud.bookapi.dtos;


import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {
    private String firstName;
    private String lastName;
}
