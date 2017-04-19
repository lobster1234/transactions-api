## transactions-api

This is the AWS Lambda + API Gateway + DynamoDB  project with 2 functions, built and deployed using [Serverless](https://serverless.com) framework.

This is to support the series on [my blog](https://lobster1234.github.io/categories/serverless/).

## Usage

1. Set up Serverless Framework and your AWS account as detailed [here](https://lobster1234.github.io/2017/02/28/serverless-framework-java-maven-part-1/).

2. Deploy the Lambda

```
bash-3.2$ git clone https://github.com/lobster1234/transactions-api.git

bash-3.2$ cd transactions-api

bash-3.2$ mvn clean install

bash-3.2$ serverless deploy

```

3. Hit the HTTP endpoints

4. Destroy the infrastructure

```
bash-3.2$ serverless remove
```
