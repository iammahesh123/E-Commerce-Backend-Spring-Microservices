package org.example.proxyclient.bussiness.payment.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proxyclient.bussiness.payment.model.PaymentDTO;

import java.io.Serializable;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentPaymentServiceDtoCollectionResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Collection<PaymentDTO> collection;

}
