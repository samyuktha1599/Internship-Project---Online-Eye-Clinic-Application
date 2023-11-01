import { User } from './user';
export class Admin extends User{

     email !:string;
     mobile !:string;

    constructor(userId:number,password:string,userName:string,role:string,email:string,mobile:string)
    {
        super(userId,password,userName,role);
        this.email = email;
        this.mobile = mobile;
    }
}
