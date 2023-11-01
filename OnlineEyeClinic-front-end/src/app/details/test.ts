import { Patient } from './patient';
export class Test {

    testId !:number;
    testName !:string;
    testType !:string;
    testDescription !:string;
    testCost !:number;
    patient !:Patient;

    constructor(testId:number,testName:string,testType:string,testDescription:string,testCost:number,patient:Patient)
    {
        this.testId=testId;
        this.testName=testName;
        this.testType=testType;
        this.testDescription=testDescription;
        this.testCost=testCost;
        this.patient=patient;
    }
}
