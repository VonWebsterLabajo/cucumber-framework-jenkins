pipeline {
 agent any

 parameters {
  string(name: 'BROWSER', defaultValue: 'chrome', description: 'Browser to run tests')
  booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Run browser in headless mode')
  string(name: 'THREAD_COUNT', defaultValue: '3', description: 'TestNG thread count')
  choice(name: 'PARALLEL_MODE', choices: ['methods', 'classes', 'tests'], description: 'Parallel execution mode')

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
    catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
     sh '''
      mvn clean test \
      -Dbrowser=${BROWSER} \
      -Dheadless=${HEADLESS} \
      -Dparallel.mode=methods \
 	  -Dthread.count=3
     '''
    }
   }
  }

  stage('Generate Allure Report') {
   steps {
    sh '''
     if [ -d "target/allure-results" ]; then
      $WORKSPACE/allure/bin/allure generate target/allure-results \
      --clean \
      --single-file \
      -o target/allure-report
     else
      echo "No allure-results found, skipping report generation"
     fi
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