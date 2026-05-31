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

        stage('Test') {
                    steps {
                        sh 'mvn test'
                    }
                    post {
                        always {
                            // Test raporlarını Jenkins üzerinde görüntülemek için
                            junit '**/target/surefire-reports/*.xml'
                        }
                        failure {
                            echo 'Testler başarısız oldu! Build durduruldu.'
                        }
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

    post {
        always {
            echo "Pipeline tamamlandı. Branch: ${env.BRANCH_NAME}"
        }
        success {
            echo 'Pipeline başarıyla tamamlandı!'
        }
        failure {
            echo 'Pipeline başarısız oldu!'
        }
    }

}
