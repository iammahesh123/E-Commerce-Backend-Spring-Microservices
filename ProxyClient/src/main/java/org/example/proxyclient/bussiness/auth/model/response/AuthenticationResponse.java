package org.example.proxyclient.bussiness.auth.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String jwtToken;

}
