import { Patient } from './patient';
import { Test } from './test';
export class Report {
      reportId !:number;
      dateOfReport !:Date;
      descriptionOfReport !:string;
      visualAcuity !:string;
      visualAcuityNear !:string;
      visualAcuityDistance !:string;
      typeOfTest !:Test;
      patient !:Patient;

      constructor( reportId:number,dateOfReport:Date,descriptionOfReport:string,visualAcuity:string,
        visualAcuityNear:string,visualAcuityDistance:string ,typeOfTest:Test, patient:Patient)
        {
            this.reportId = reportId;
            this.dateOfReport = dateOfReport;
            this.descriptionOfReport = descriptionOfReport;
            this.visualAcuity = visualAcuity;
            this.visualAcuityNear = visualAcuityNear;
            this.visualAcuityDistance = visualAcuityDistance;
            this.typeOfTest = typeOfTest;
            this.patient = patient;
        }
}

