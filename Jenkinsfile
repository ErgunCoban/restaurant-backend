pipeline {
    agent any

    tools {
        maven 'maven3'
        jdk 'jdk21'
    }

    stages {
        stage('Checkout') {
            steps {
                cleanWs()   //workspace temizlenir.
                checkout scmGit(    //checkout işlemine girilir.
                    branches: [[name: "*/${env.BRANCH_NAME}"]], //her branchi takip edebileceğiz
                    extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/ErgunCoban/restaurant-backend']])
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
            post {
                success {
                    // Build sonrası oluşan jar dosyasını Jenkins üzerinde saklamak için
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
    }
}
