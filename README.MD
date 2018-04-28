Run all tests:
```bash
mvn test
```

Run all tests on one class:
```bash
mvn -Dtest=ClassTest test
```

Run one test on the one class:
```bash
mvn -Dtest=ClassTest#testName test
```
```bash
mvn -DpropertyName=propertyValue test
```

Run Suite:
```bash
mvn test -Dsuite=suiteFileName
```

Example:

Run all tests on `PurchaseATicketTest` class with system property `browser`:
```bash
mvn -Dtest=PurchaseATicketTest -Dbrowser=opera test
```

Run suite on file `blazedemo.xml`
```bash
mvn test -Dsuite=blazedemo
```
