pipeline {
    agent any

    environment {
        IMAGE_NAME = "restaurant-backend"
        CONTAINER_NAME = "restaurant-backend-container"

        DB_URL = "jdbc:postgresql://host.docker.internal:5432/restaurant"
        DB_USERNAME = "postgres"

        DB_PASSWORD = credentials('db-password')
        JWT_SECRET = credentials('jwt-secret')

        JWT_EXPIRATION_MINUTES = "60"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Jar') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$BUILD_NUMBER .'
                sh 'docker tag $IMAGE_NAME:$BUILD_NUMBER $IMAGE_NAME:latest'
            }
        }

        stage('Deploy Container') {
            steps {
                sh 'docker rm -f $CONTAINER_NAME || true'

                sh '''
                docker run -d \
                  --name $CONTAINER_NAME \
                  -p 8081:8080 \
                  -e DB_URL="$DB_URL" \
                  -e DB_USERNAME="$DB_USERNAME" \
                  -e DB_PASSWORD="$DB_PASSWORD" \
                  -e JWT_SECRET="$JWT_SECRET" \
                  -e JWT_EXPIRATION_MINUTES="$JWT_EXPIRATION_MINUTES" \
                  $IMAGE_NAME:latest
                '''
            }
        }
    }
}