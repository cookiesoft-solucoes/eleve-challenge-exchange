pipeline {
    agent any
    environment {
        REGISTRY = "docker.io"
        IMAGE_NAME = "alysonrodrigo/elevechallengeexchange"
        K8S_NAMESPACE = "default"
    }
    stages {
        stage('Build') {
            steps {
                script {
                    sh './gradlew clean build'
                }
            }
        }
        stage('Docker Build & Push') {
            steps {
                script {
                    sh "docker build -t $REGISTRY/$IMAGE_NAME:latest ."
                    sh "docker push $REGISTRY/$IMAGE_NAME:latest"
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    sh "kubectl apply -f k8s/deployment.yaml"
                    sh "kubectl apply -f k8s/service.yaml"
                }
            }
        }
    }
}