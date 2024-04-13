package org.example.proxyclient.bussiness.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proxyclient.bussiness.user.model.UserDTO;

import java.io.Serializable;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserUserServiceCollectionDtoResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Collection<UserDTO> collection;

}
