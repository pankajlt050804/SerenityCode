## Opener-API-USD
Opener-API-USD is a Serenity-BDD based test automation solution utilising Java, Rest Assured, Junit, Maven
in the background and supports API, Unit, Integration tests.
Serenity is BDD based framework, which helps you write cleaner and more maintainable 
automated acceptance and regression tests faster. Serenity also uses the test results to 
produce illustrated, narrative reports that document and describe what your application 
does and how it works.

## How to run the tests
```
    mvn clean verify
```

To Run specific tests using tags

```
   mvn clean verify -Dtags="testType=valid"
```

## Report

User can found the report at following location

```
 target/site/serenity/index.html

```
    
## Probable Future Enhancements

- Integrate Docker and Kubernetes Cluster for test execution.
- Jenkins or other tools for CI integration in DevOps pipeline.
- Integration with more advance reporting engine like reportPortal.io, extent and Allure report etc.
- Parallel execution for feature files.
- Cloud Integration (AWS,AZURE and GCP).
- Run Time Test Data Generation for api tests.
- Integration with Generative AI tools for feature files generation.
- Jira Integration.