# Cloud Engine Hello World

## Environment Variables

| Name                | Example                                    |
| ------------------- | ------------------------------------------ |
| `DATABASE_URL`      | `jdbc:postgresql://localhost:5432/test_db` |
| `DATABASE_USERNAME` | `test_db`                                  |
| `DATABASE_PASSWORD` | `test_db`                                  |

## Build

**Java 8 is required**

Create Fat JAR

```bash
$ ./gradlew clean shadowJar
```

**Tests are not executed** and no environment variables are required. Use the `build` task to create the fat JAR and also run the tests.

```bash
$ ./gradlew clean build
```

To run the project

```bash
$ DATABASE_URL="jdbc:postgresql://localhost:5432/test_db" \
  DATABASE_USERNAME="test_db" \
  DATABASE_PASSWORD="test_db" \
  java -jar build/libs/cloud-engine-hello-world-all.jar
```
