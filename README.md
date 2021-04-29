# In docker

docker build .
docker images
docker run <ImageId>

# To run this app

Run code: javac Bully.java && java Bully

# To run docker and kubernetes

$ minikube start
$ docker build -t be .
$ docker tag be <dockerID>/be
$ docker push <dockerID>/be
$ kubectl apply -f ./deployment.yaml
$ kubectl apply -f ./service.yaml
$ minikube service --url be-service

# To run again

$ docker build -t <dockerID>/be .
$ minikube start
$ minikube service --url be-service

# To delete stuff

$ kubectl delete -f ./deployment.yaml
$ kubectl delete -f ./service.yaml
Delete rep in dockerhub# To run docker and kubernetes
$ minikube start
$ docker build -t be .
$ docker tag be <dockerID>/be
$ docker push <dockerID>/be
$ kubectl apply -f ./deployment.yaml
$ kubectl apply -f ./service.yaml
$ minikube service --url be-service

# To run again

$ docker build -t <dockerID>/be .
$ minikube start
$ minikube service --url be-service

# To delete stuff

$ kubectl delete -f ./deployment.yaml
$ kubectl delete -f ./service.yaml
Delete rep in dockerhub
