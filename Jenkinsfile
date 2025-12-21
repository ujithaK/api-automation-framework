pipeline {
    agent any
    stages {
        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
    post {
        always {
            publishHTML([
                reportDir: 'target',
                reportFiles: 'extent-report.html',
                reportName: 'API Automation Report'
            ])
        }
    }
}
