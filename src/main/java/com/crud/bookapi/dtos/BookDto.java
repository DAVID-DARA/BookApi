package com.crud.bookapi.dtos;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private String title;
    private Set<Long> authorIds;
    private Set<Long> categoryIds;

}

