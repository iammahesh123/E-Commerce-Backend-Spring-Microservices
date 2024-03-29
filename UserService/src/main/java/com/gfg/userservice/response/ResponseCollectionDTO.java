package com.gfg.userservice.response;

import lombok.AllArgsConstructor;
import java.util.Collection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseCollectionDTO<T> {
    private Collection<T> collection;
}
