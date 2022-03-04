## Requirements

1. Java 17
2. Maven installed
3. MySql server

## Running the application locally

```bash
git clone https://github.com/jair921/quasar.git
cd quasar
# On src/main/resources/application.properties configure database connection
mvn spring-boot:run
```

## URL Service

[https://fire-quasar-jair.herokuapp.com/](https://fire-quasar-jair.herokuapp.com/) 

## Rest endpoints

### Top secret
Request
```bash
POST /topsecret
{
    "satellites": [
        {
            "name": "kenobi",
            "distance": 100.0,
            "message": [
                "este",
                "",
                "",
                "mensaje",
                ""
            ]
        },
        {
            "name": "skywalker",
            "distance": 115.5,
            "message": [
                "",
                "es",
                "",
                "",
                "secreto"
            ]
        },
        {
            "name": "sato",
            "distance": 142.7,
            "message": [
                "este",
                "",
                "un",
                "",
                ""
            ]
        }
    ]
}
```

Response
```bash
{
    "position": [
        -58.315252587138595,
        -69.55141837312165
    ],
    "message": "este es un mensaje secreto"
}
```
### Top secret split (store)
Request
```bash
POST /topsecret_split/{satellite_name}
{
    "distance": 142.7,
    "message": [
                "este",
                "",
                "un",
                "",
                ""
            ]
}
```

Response
```bash
{
    "position": null,
    "message": "Store split message"
}
```
### Top secret split
Request
```bash
GET /topsecret_split
{
    "distance": 142.7,
    "message": [
                "este",
                "",
                "un",
                "",
                ""
            ]
}
```

Response
```bash
{
    "position": [
        59.634420899438396,
        -30.932718106937056
    ],
    "message": "este es un mensaje secreto"
}
```

### Remove old split messages
Request
```bash
POST /restart_split
```

Response
```bash
{
    "position": null,
    "message": "Remove all split messages."
}
```
