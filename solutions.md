## Solutions

## Minimal Dekorate configuration to generate manifest and deploy on Openshift:

branch: solution-minimal-dekorate-config

1 - Add `openshift-spring-starter`
```
        <dependency>
			<groupId>io.dekorate</groupId>
			<artifactId>openshift-spring-starter</artifactId>
			<version>2.3-SNAPSHOT</version>
		</dependency>
```

2 - Add `dekorate.openshift.expose=true` in application.properties

3 - Generate manifests and build the image
```shell
mvn clean install -Ddekorate.build=true
```

4 - Deploy manifest to the cluster
```shell
oc login
oc apply -f target/classes/META-INF/dekorate/openshift.yml
```
or
```shell
mvn clean install -Ddekorate.build=true -Ddekorate.deploy=true
```
    
## Bonus: configurate Image Build Strategy

### With Docker

branch: solution-bonus-with-docker

1 - Add dependencies
```shell
        <dependency>
			<groupId>io.dekorate</groupId>
			<artifactId>openshift-annotations</artifactId>
			<version>${dekorate.version}</version>
		</dependency>
		<dependency>
			<groupId>io.dekorate</groupId>
			<artifactId>docker-annotations</artifactId>
			<version>${dekorate.version}</version>
		</dependency>
```

2 - Anotate the DekorateChallengeApplication class

```java
@SpringBootApplication
@S2iBuild(enabled=false)
@DockerBuild(registry = "docker.io")
public class DekorateChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DekorateChallengeApplication.class, args);
	}

}
```

3 - Add a Dockerfile to the root directoy

```dockerfile
FROM openjdk:15.0.2-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

4 - Generate manifests and build the image
```shell
mvn clean install -Ddekorate.build=true
```

5 - Push the image
```shell
docker login
docker push amunozhe/dekorate-challenge:2.3-SNAPSHOT
```

6 - Deploy manifest
```shell
oc login
oc apply -f target/classes/META-INF/dekorate/openshift.yml
```

7 - All in a single step
```shell
mvn clean install -Ddekorate.build=true -Ddekorate.push=true -Ddekorate.deploy=true
```

### With Jib

branch: solution-bonus-with-docker

1 - Add dependencies
```shell
       <dependency>
			<groupId>io.dekorate</groupId>
			<artifactId>jib-annotations</artifactId>
			<version>${dekorate.version}</version>
		</dependency>
```

2 - Anotate the DekorateChallengeApplication class

```java
@SpringBootApplication
@S2iBuild(enabled=false)
@JibBuild(registry = "docker.io")
public class DekorateChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DekorateChallengeApplication.class, args);
	}

}
```
3 - Generate manifests and build+push the image
```shell
mvn clean install -Ddekorate.build=true -Ddekorate.push=true
```

4 - Deploy manifest
```shell
oc login
oc apply -f target/classes/META-INF/dekorate/openshift.yml
```

5 - All in a single step
```shell
mvn clean install -Ddekorate.build=true -Ddekorate.push=true -Ddekorate.deploy=true
```