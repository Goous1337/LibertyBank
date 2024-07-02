package service;

import api.core.RequestParam;
import io.restassured.response.Response;
import pojo.investmentService.QuestionnaireData;

import java.util.List;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.HEADER;
import static api.core.RequestParamType.QUERY_PARAMETER;
import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.*;
import static constant.CustomerService_2_0_Constants.BEARER_TOKEN;
import static constant.InvestmentConstants.ARTICLE_TYPE;
import static constant.InvestmentConstants.PDF_CONTENT_TYPE;
import static io.restassured.http.Method.*;

public class InvestmentService {
    public static Response getQuestionnaireRequest(String jwtToken) {
        return sendSimpleRequest(GET, QUESTIONNAIRE_FORM,
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + jwtToken));
    }

    public static Response saveQuestionnaireRequest(String jwtToken) {
        return sendSimpleRequest(PATCH, QUESTIONNAIRE_FORM,
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + jwtToken),
                new QuestionnaireData(true, true, true, true, true));
    }

    public static Response saveQuestionnaireRequest(String jwtToken, QuestionnaireData questionnaireData) {
        return sendSimpleRequest(PATCH, QUESTIONNAIRE_FORM,
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + jwtToken),
                questionnaireData);
    }

    public static Response postAuthRequest(String jwtToken) {
        return sendSimpleRequest(POST, INVESTMENT_AUTH,
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + jwtToken));
    }

    public static Response getCommonDocsRequest(String docName, String jwtToken, int statusCode) {
        List<RequestParam> params = List.of(getRP(HEADER, CONTENT_TYPE, PDF_CONTENT_TYPE),
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + jwtToken));
        return sendSimpleRequest(GET, INVESTMENT_COMMON_DOCS + "/" + docName, params, statusCode);
    }

    public static Response getPersonalDocsRequest(String docName, String jwtToken, int statusCode) {
        List<RequestParam> params = List.of(getRP(HEADER, CONTENT_TYPE, PDF_CONTENT_TYPE),
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + jwtToken));
        return sendSimpleRequest(GET, INVESTMENT_PERSONAL_DOCS + "/" + docName, params, statusCode);
    }

    public static Response getListArticlesType(String jwtToken, String articlesType, int statusCode) {
        List<RequestParam> params = List.of(getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + jwtToken),
                getRP(QUERY_PARAMETER, ARTICLE_TYPE, articlesType));
        return sendSimpleRequest(GET, LIST_OF_ARTICLES, params, statusCode);
    }
}