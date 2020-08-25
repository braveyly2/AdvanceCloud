#继承centos docker file
FROM centos

#安装JDK
RUN yum install -y java

#配置docker存储目录
VOLUME /tmp

#将maven目录的jar包拷贝到docker中，并命名为for_docker.jar
ADD maven/for_docker-0.0.1-SNAPSHOT.jar for_docker.jar

ENTRYPOINT ["java", "-jar", "/for_docker.jar"]