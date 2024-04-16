## Opener-API-USD
 Test Framework is designed using java and testing framework used is Serenity.
 Serenity is BDD based framework, which helps you write cleaner and more maintainable 
 automated acceptance and regression tests faster. Serenity also uses the test results to 
 produce illustrated, narrative reports that document and describe what your application 
 does and how it works

## How to run the tests
```
    mvn clean verify
```

To Run specific tests using tags

```
   mvn clean verify -Dtags="testType=valid"
```

## Report

We can found the report in following location

```
 target/site/serenity/index.html

```
    
## Future Enhancements

- Integrate Docker compatibility.
- User Jenkins or other tools for CI integration.
- Integration with more advance reporting engine



