### README

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

### Headless mode

To run in headless mode, the system property `display` must be `false`, the `browser` must be `chrome`.

To start in normal mode, the system property `display` should be true, the `browser` should be `chrome`, 
or you need to use a different browser.

Example:

Run in headless mode on the browser `chrome`:

```bash
mvn test -Dtest=PurchaseATicketTest
```

Run in normal mode on the browser `chrome`:
```bash
mvn test -Dtest=PurchaseATicketTest -Ddispaly=true
```

Run in normal mode on the browser `opera`:
```bash
mvn test -Dtest=PurchaseATicketTest -Dbrowser=opera
```
