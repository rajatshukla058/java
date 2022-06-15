pipeline{
    // 
    agent {
    docker {
        image 'maven:3.8.1-adoptopenjdk-11'
        // label 'my-defined-label'
        args  '-v /tmp:/tmp'
    }
}
    stages{
        stage("A"){
            steps{
                echo "========executing A========"
            }
            post{
                always{
                    echo "========always========"
                }
                success{
                    echo "========A executed successfully========"
                }
                failure{
                    echo "========A execution failed========"
                }
            }
        }
    }
    post{
        always{
            echo "========always========"
        }
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}

adelia.jannah@reddoorz.com
almaditya.said@reddoorz.com
ana.landicho@reddoorz.com
anindita.rosania@reddoorz.com
anjul.sharma@reddoorz.com
ankush.singh@reddoorz.com
arun.singh@reddoorz.com
astri.andungsari@reddoorz.com
avdhesh.sharma@reddoorz.com
avinash.gupta@reddoorz.com
azhar.ahamad@reddoorz.com
dian.noviardi@reddoorz.com
donny.jibril@reddoorz.com
eva.zhang@reddoorz.com
fitria.budhiani@reddoorz.com
gaurav.midha@reddoorz.com
gerhard.sihole@reddoorz.com
hilda.afriyani@reddoorz.com
idham.fuadi@reddoorz.com
indah.susanti@reddoorz.com
jenny.pham@reddoorz.com
jezza.cedeno@reddoorz.com
jhan.ibia@reddoorz.com
jhumpa.dey@reddoorz.com
jingyu.feng@reddoorz.com
joan.ng@reddoorz.com
jocyelyn.seah@reddoorz.com
kevin.suwardi@reddoorz.com
kriscia.constantino@reddoorz.com
krupa.shah@reddoorz.com
mankiran.deol@reddoorz.com
marenelle.caldejon@reddoorz.com
marie.bianca@reddoorz.com
marnix.factor@reddoorz.com
marvin.agsalda@reddoorz.com
mary.abayon@reddoorz.com
micky.haryanto@reddoorz.com
monalisa.hutapea@reddoorz.com
neil.aggabao@reddoorz.com
nitin.gaurav@reddoorz.com
nurma.yuliani@reddoorz.com
orathai.kaewkanya@reddoorz.com
paula.prayitno@reddoorz.com
ram.yadav@reddoorz.com
rio.munthe@reddoorz.com
rizka.fajri@reddoorz.com
rona.gegato@reddoorz.com
sahil.pruthi@reddoorz.com
sumit.kumar@reddoorz.com
tan.ting@reddoorz.com
tapan.sharma@reddoorz.com
tuan.nguyen@reddoorz.com
veer.sachan@reddoorz.com
wendy.chua@reddoorz.com
wita.pratiwi@reddoorz.com
wulang.dewanto@reddoorz.com