package org.example.proxyclient.bussiness.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proxyclient.bussiness.user.model.CredentialDTO;

import java.io.Serializable;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CredentialUserServiceCollectionDtoResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Collection<CredentialDTO> collection;

}
