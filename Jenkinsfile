pipeline{
    
    agent any
    
    stages{
        
        stage("build"){
            steps{
                echo("build")
            }
        }
        
         stage("Run UTs"){
            steps{
                echo("UTs")
            }
        }
        
          stage("Deploy to dev"){
            steps{
                echo("dev deployment")
            }
        }
        
                stage("Deploy to qa"){
            steps{
                echo("qa deployment")
            }
        }
        
                stage("Run Automation Reg Test"){
            steps{
                echo("Run automation Reg Test")
            }
        }
        
         stage("Deploy to stage"){
            steps{
                echo("stage deployment")
            }
        }
        
           stage("Deploy to prod"){
            steps{
                echo("prod deployment")
            }
        }
        
    }
}
