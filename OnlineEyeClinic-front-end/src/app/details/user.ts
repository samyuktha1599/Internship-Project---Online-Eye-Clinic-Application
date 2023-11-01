export class User {

    userId !:number;
	password !:string;
	userName !:string;
	role !:string;

    constructor(userId:number,password:string,userName:string,role:string)
    {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.role = role;
    }
}
