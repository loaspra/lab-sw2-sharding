

##  Observaciones:

Al crear las replicas en mongo, se deben  de especificar como direcciones a los nombres de los pods containers creados. 

rs.initiate({
  _id: "cfgrs",
  configsvr: true,
  members: [
    { _id : 0, host : "configs1:27017" },
    { _id : 1, host : "configs2:27017" },
    { _id : 2, host : "configs3:27017" }
  ]
})

In this command, the host values are set to the names of the MongoDB services in your Docker Compose file, followed by the port number. This is because Docker Compose creates a default network where each service can be accessed by other services using the service name as the hostname.

2. Notar que en la configuracion de las instancias de replicas, se utiliza el param `shardsvr`, el cual indica que la instancia mongo en cuestion es un shard


El `docker-compose.yaml` para las shards deberia ser el siguiente:

```yaml
version: '3'
services:
  shard1s1:
    container_name: shard1s1
    image: mongo
    command: mongod --shardsvr --replSet shard1rs --port 27017 --dbpath /data/db
    ports:
      - 20001:27017
    volumes:
      - shard1s1:/data/db
  shard1s2:
    container_name: shard1s2
    image: mongo
    command: mongod --shardsvr --replSet shard1rs --port 27017 --dbpath /data/db
    ports:
      - 20002:27017
    volumes:
      - shard1s2:/data/db
  shard1s3:
    container_name: shard1s3
    image: mongo
    command: mongod --shardsvr --replSet shard1rs --port 27017 --dbpath /data/db
    ports:
      - 20003:27017
    volumes:
      - shard1s3:/data/db
volumes:
  shard1s1: {}
  shard1s2: {}
  shard1s3: {}
```

## Resultados:

![rs.status() output](image.png)

![(final) docker-ps output](image-1.png)

Repo:

[repo of batch job](https:github.com/loaspra/lab-sw2-sharding)
