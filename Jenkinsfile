pipeline {
 agent any

 parameters {
  string(name: 'BROWSER', defaultValue: 'chrome', description: 'Browser to run tests')
  booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Run browser in headless mode')
 }

 tools {
  jdk 'jdk21'
  maven 'maven'
 }

 stages {

  stage('Checkout Repository') {
   steps {
    checkout scm
   }
  }

  stage('Install Allure CLI') {
   steps {
    sh '''
     if [ ! -d "$WORKSPACE/allure" ]; then
      wget https://github.com/allure-framework/allure2/releases/download/2.29.0/allure-2.29.0.zip
      unzip -o allure-2.29.0.zip
      mv allure-2.29.0 $WORKSPACE/allure
     fi
    '''
   }
  }

  stage('Run Maven Tests') {
   steps {
    sh '''
     mvn clean test \
     -Dbrowser=${BROWSER} \
     -Dheadless=${HEADLESS}
    '''
   }
  }

   stage('Generate Allure Report') {
   steps {
    sh '''
     $WORKSPACE/allure/bin/allure generate target/allure-results \
     --clean \
     --single-file \
     -o target/allure-report
    '''
   }
  }
}


 post {
  always {
   archiveArtifacts artifacts: 'target/allure-report/**', fingerprint: true
  }
 }
}
