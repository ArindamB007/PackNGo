package com.png.data.dto.search;

import org.springframework.data.domain.Sort;

public class PagedRequest {
    private Integer pageNo;
    private Integer PageSize;
    private Sort.Direction sortDirection;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        if (pageNo == Sort.Direction.ASC.ordinal())
            this.sortDirection = Sort.Direction.ASC;
        else if (pageNo == Sort.Direction.DESC.ordinal())
            this.sortDirection = Sort.Direction.DESC;
        else
            this.sortDirection = Sort.Direction.ASC;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }
}
