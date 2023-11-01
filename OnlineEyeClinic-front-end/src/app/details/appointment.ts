import { Patient } from './patient';
import { Doctor } from './doctor';
export class Appointment {

    appointmentId !:number;
	appointmentDate !:Date;
	appointmentTime !:Date;
	doctor !:Doctor;
	status !:string;
	patient !:Patient;

    constructor(appointmentId:number,appointmentDate:Date,appointmentTime:Date,doctor:Doctor,status:string,patient:Patient)
    {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.doctor = doctor;
        this.status = status; 
        this.patient = patient;
    }
    
}
