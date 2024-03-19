package com.example.budget_management.system.common.paging;

import java.util.List;

public record ListResponse<T>(
        List<T> contents,
        Long totalElements
) {
    public ListResponse(List<T> contents) {
        this(
                contents,
                (long) contents.size()
        );
    }
}
