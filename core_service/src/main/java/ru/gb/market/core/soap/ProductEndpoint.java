package ru.gb.market.core.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "t1meal.com/market/ws/products";
    private final ProductsSoapService productsSoapService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productsSoapService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }

       /*
        Пример запроса: POST http://localhost:8080/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="https://schemas.xmlsoap.org/soap/envelope/"
        xmlns:f="t1meal.com/market/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */
}
