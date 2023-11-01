export class Spectacles {

    spectaclesId !:number;
    spectaclesModel !:string;
    spectaclesDescription !:string;
    spectaclesRating !:number;
    spectaclesCost !:number;

    constructor(spectaclesId:number,spectaclesModel:string,spectaclesDescription:string,spectaclesRating:number,spectaclesCost:number)
    {
        this.spectaclesId = spectaclesId;
        this.spectaclesModel = spectaclesModel;
        this.spectaclesDescription = spectaclesDescription;
        this.spectaclesRating = spectaclesRating;
        this.spectaclesCost = spectaclesCost;
       
    }
}
