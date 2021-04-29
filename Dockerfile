# FROM scratch

# WORKDIR /
# COPY . .
# EXPOSE 8080
# CMD javac Bully.java & java Bully

# Linux image 
FROM alpine
WORKDIR /root/be
COPY Bully.java /root/be
COPY Node.java /root/be

# Install JDK
RUN apk add openjdk8
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:$JAVA_HOME/bin

# Compile 
RUN javac Bully.java
ENTRYPOINT java Bully