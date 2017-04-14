package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.data.Transaction;
import com.serverless.db.DynamoDBAdapter;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GetTransactionsHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = Logger.getLogger(GetTransactionsHandler.class);

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		LOG.info("received: " + input);
		List<Transaction> tx;
		try {
			Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
			String accountId = pathParameters.get("account_id");
			LOG.info("Getting transactions for " + accountId);
			tx = DynamoDBAdapter.getInstance().getTransactions(accountId);
		} catch (Exception e) {
			LOG.error(e, e);
			Response responseBody = new Response("Failure getting transactions", input);
			return ApiGatewayResponse.builder()
					.setStatusCode(500)
					.setObjectBody(responseBody)
					.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
					.build();
		}
		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(tx)
				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
				.build();
	}

}