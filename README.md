# ETH2-Calculate-APR-on-validator
A simple analytical tool for Ethereum 2.0 designed to return a validator's staking APR. 

Calculations performed by looking at the chain data of Validator node.

### Built With
Java 11 

Spring boot 2.5.9

Apache Maven - Dependency management

Swagger

infura.io to access Beacon chain data via their API.
 
### HowTo

* Select & activate a Maven profile within the resources dir.
* Update the application.properties file with your Infura.io ETH2 API credentials.
* Run & consume the REST endpoint at http://localhost:8080/eth2/validator/[validator_index]?ageInSeconds=[]
	
	validator_index = The unique index assigned to your Validator.
	
	ageInSeconds = Number of years/month/days to base the APR calculations in seconds. E.g. 365 days = 31536000.

* Swagger UI at http://localhost:8080/swagger-ui.html
