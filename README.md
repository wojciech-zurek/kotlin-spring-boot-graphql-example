Now, run the following query.

mutation {
  createVehicle(type: "car", modelCode: "XYZ0192", brandName: "XYZ", launchDate: "2016-08-16") 
  {
    id
  }
}
This will create a row in the Vehicle table. The result should be:

{
  "data": {
    "createVehicle": {
      "id": "1"
    }
  }
}
Let's now run a query to get the data.

query {
  vehicles(count: 1) 
  {
    id, 
    type, 
    modelCode
}
}
The output will be:

{
  "data": {
    "vehicles": [
      {
        "id": "1",
        "type": "bus",
        "modelCode": "XYZ123"
      }
    ]
  }
}

## Download

```bash
    git clone https://github.com/wojciech-zurek/kotlin-spring-fu-mongo-example.git
```

## Run with gradle

```bash
    cd kotlin-spring-fu-mongo-example/
    ./gradlew run
```

## Run as jar file

```bash
    cd kotlin-spring-fu-mongo-example/
    ./gradlew shadowJar
    java -jar build/libs/kotlin-spring-fu-mongo-example-all.jar
```

## Test

```bash
    cd kotlin-spring-fu-mongo-example/
    ./gradlew cleanTest test
`````