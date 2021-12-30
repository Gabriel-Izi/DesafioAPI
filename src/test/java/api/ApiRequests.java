package api;

import static io.restassured.RestAssured.given;

public class ApiRequests extends ApiUtils implements ApiVerbos {


    @Override
    public void GET() {//Realiza a busca de usuário na API
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .get(uri+id);

        super.log("GET");
    }

    @Override
    public void POST() {//Cadastra um usuário na API
        response = given()
                .relaxedHTTPSValidation()
                .headers(headers)
                .body(body.toString())
                .post(uri);

        super.log("POST");
    }

    @Override
    public void PUT() {//Altera informações do usuário na API
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .body(body.toString())
                .put(uri);

        super.log("PUT");
    }

    @Override
    public void PATCH() {//Altera informações específicas do usu[ario na API
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .body(body.toString())
                .patch(uri);

        super.log("PATCH");
    }

    @Override
    public void DELETE() {//Delata um usuário
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .delete(uri+id);

        super.log("DELETE");
    }
}
