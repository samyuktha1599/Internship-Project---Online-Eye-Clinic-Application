import { User } from './user';
export class Patient extends User{

    patientAge !:number;
    patientMobile !:number;
    patientEmail !:string;
    patientDOB !:Date;
    address !:string;

    constructor(userId:number,password:string,userName:string,role:string,patientAge:number,patientMobile:number,patientEmail:string,patientDoB:Date,address:string)
    {
        super(userId,password,userName,role);
        this.patientAge=patientAge;
        this.patientMobile=patientMobile;
        this.patientEmail=patientEmail;
        this.patientDOB=patientDoB;
        this.address=address;
    }

}

