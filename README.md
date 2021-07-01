## REST APIs for Converting Youtube video url to embed code.

### Running Locally
The only dependencies for running this example are:

- [Docker Compose][https://www.docker.com/)
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [httpie](https://github.com/httpie/httpie)
- [Kubernetes](https://kubernetes.io/)

```sh
$ git clone https://github.com/mozammal/video-embed-code-service.git
$ cd spring-rabbitmq-event-module
$ mvn clean package
$ mvn jib:dockerBuild
$ kubectl apply -f deploy.yml
```
use the following command to get detailed information about the service:
```sh
$kubectl describe services video-embed-code-service
```
```
The output is similar to:
Name:                     video-embed-code-service
Namespace:                default
Labels:                   app=video-embed-code-service
Annotations:              <none>
Selector:                 app=video-embed-code-service
Type:                     NodePort
IP:                       10.111.101.51
LoadBalancer Ingress:     localhost
Port:                     http  8080/TCP
TargetPort:               8080/TCP
NodePort:                 http  31817/TCP
Endpoints:                10.1.2.173:8080,10.1.2.174:8080
Session Affinity:         None
External Traffic Policy:  Cluster
Events:                   <none>
```
Take a note about the value of NodePort which is 31817 in this example. But this could be
different in your case. 

You can test the application now by using the following command:

```sh
$ http -v GET "localhost:31817/embed-code?url=https://www.youtube.com/watch?v=GNU6Ue9HUJE"
```

If you want to tear-down the application, use the command written below:

```sh
$ kubectl delete -f  deploy.yml
```
