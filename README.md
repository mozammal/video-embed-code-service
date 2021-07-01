# reactive-spring-youtube-video-embedCode

# Running Locally
The only dependencies for running this example are:

- [Docker Compose][https://www.docker.com/)
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [httpie](https://github.com/httpie/httpie)
- [Kubernetes](https://kubernetes.io/)

```sh
$ git clone https://github.com/mozammal/spring-rabbitmq-event-module.git
$ cd spring-rabbitmq-event-module
$ mvn clean package
$ mvn jib:dockerBuild
$ kubectl apply -f deploy.yml
$ kubectl get services
```

You can test the application by using the following command:

```sh
$ http -v GET "localhost:31817/embed-code?url=https://www.youtube.com/watch?v=GNU6Ue9HUJE"

If you want to tear-down the application, use the command written below:

```sh
$ kubectl delete -f  deploy.yml
```
