# JcAPITools

A complimentary set of services and API's to simplify deploying API's from your webMethods runtime containers or git repositories to your webMethods API Gateway

API documentation can be view [here](https://raw.githack.com/johnpcarter/JcAPITools/main/pub/index.html)  
Service documentation is available in Designer via the comments of each service or via the packages home directory [/JcAPITools](http://localhost:5555/JcAPITools/services.dsp)

**Installation**

This source code is a webMethods Micro Service Runtime package and you will need to first install the Software AG Micro Service Runtime or download a docker image.

**local installation**

If you have an Integration Server or Micro Service Runtime running locally for development purposes, first navigate to your packages directory;

```
$ cd /${SAG_HOME}/IntegrationServer/packages*  
```
or
```
$ cd /${SAG_HOME}/IntegrationServer/instances/${INSTANCE}/packages*  
```

If your packages directory is already under version control

```
$ git submodule add https://github.com/johnpcarter/JcAPITools.git JcAPITools*  
```

or if you are not, then simply clone the repository

```
$ git clone https://github.com/johnpcarter/JcAPITools.git*  
```

Then restart your runtime server and refresh your package browser in Designer.

**Docker Installation**

A predefined Dockerfile template has been provided in the resources directory. It is recommended that you copy the directory
and then update Dockerfile and aclmap_sm.cnf file appropriately.

cd into your directory and download the latest source code

```
$ cd ${WORKING_DIR}*  
$ git clone https://github.com/johnpcarter/JcAPITools.git*  
```

You will also need to add your own packages and configuration as part of the build by copying the following line into the section 'add YOUR packages here'
e.g.

```
ADD --chown=sagadmin ./c8yPhilipsHueAgent /opt/softwareag/IntegrationServer/packages/c8yPhilipsHueAgent*  
```

and also update the ./resources/aclmap_sm.cnf file to include permissions for any the services that you are exposing through an API.

You could also choose to include your MSR license file by adding the following line

```
ADD --chown=sagadmin .licenseKey.xml /opt/softwareag/IntegrationServer/config/licenseKey.xml*  
```

You can now build your image

```
$ docker build -t ${YOUR DOCKER IMAGE} .*  
```

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
