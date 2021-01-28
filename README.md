# JcAPITools

A complimentary set of services and API's to simplify deploying API's from your webMethods runtime containers or git repositories to your webMethods API Gateway

API documentation can be view [here]()
Service documentation is available in Designer via the comments of each service or online [here]()

**API Gateway settings**

endpoints and credentials for your API Gateway can be set via environment variables to facilitate integration with your CI/CD pipeline, thus starting up your MSR as a docker container can
be pre-configured via build environment parameters or at runtime via container environment parameters. 

The full list is;
- api_gateway_url: fully qualified url of webMethods API Gateway to publish to.
- api_gateway_user: The API Gateway user id to use for publishing.
- api_gateway_password: Indicates whether password is present.
- api_gateway_allow_update: true if allowed, otherwise pre-existing api's will not be updated.
- api_gateway_default_maturity: default maturity tag to be set when publishing API's.
- api_gateway_default_group: default API grouping to be set when publishing API's.
- api_gateway_default_version: version to be set when publishing API's.
- api_gateway_default_app: default app to be associated with API being published.
