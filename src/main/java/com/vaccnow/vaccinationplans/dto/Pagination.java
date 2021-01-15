package com.vaccnow.vaccinationplans.dto;

import java.util.Collection;

import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class Pagination {

    private long totalElements;
    private int size;
    private int pageSize;
    private int pageNumber;

    public Pagination(Collection<?> content, long totalElements, Pageable pageable) {
        this.totalElements = totalElements;
        this.pageSize = pageable.getPageSize();
        this.pageNumber = pageable.getPageNumber();
        this.size = content.size();
    }
}
