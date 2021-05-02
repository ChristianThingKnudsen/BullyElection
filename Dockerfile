# Linux image 
FROM alpine
WORKDIR /root/be
COPY Bully.java /root/be
COPY Node.java /root/be

# Install JDK
RUN apk add openjdk8
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:$JAVA_HOME/bin

# Compile and run
RUN javac BetterBully.java
ENTRYPOINT java BetterBully