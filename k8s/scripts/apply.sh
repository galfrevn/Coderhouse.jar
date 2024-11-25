#!/bin/bash

# Apply the Kubernetes resources files
# Apply database deployment
kubectl apply -f k8s/deployment/database.deployment.yaml
if [ $? -ne 0 ]; then
  echo "🟥 Failed to apply database deployment"
  exit 1
fi

# Apply database service
kubectl apply -f k8s/services/database.service.yaml
if [ $? -ne 0 ]; then
  echo "🟥 Failed to apply database service"
  exit 1
fi

# Apply application deployment
kubectl apply -f k8s/deployment/application.deployment.yaml
if [ $? -ne 0 ]; then
  echo "🟥 Failed to apply application deployment"
  exit 1
fi

# Apply application service
kubectl apply -f k8s/services/application.service.yaml
if [ $? -ne 0 ]; then
  echo "🟥 Failed to apply application service"
  exit 1
fi

# Apply application autoscaling
kubectl apply -f k8s/deployment/application.autoscaling.yaml
if [ $? -ne 0 ]; then
  echo "🟥 Failed to apply application autoscaling"
  exit 1
fi

echo "🟩 All resources applied successfully"
