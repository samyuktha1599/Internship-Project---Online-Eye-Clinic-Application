import { User } from './user';
export class Doctor extends User{

    doctorConsultationTime !:string;
	doctorMobile !:string;
    doctorEmail !:string;
	address !:string;

    constructor(userId:number,password:string,userName:string,role:string,doctorConsultationTime:string,doctorMobile:string,
        doctorEmail:string,address:string)
        {
            super(userId,password,userName,role);
            this.doctorConsultationTime = doctorConsultationTime;
            this.doctorMobile = doctorMobile;
            this.doctorEmail = doctorEmail;
            this.address = address;
        }

}
