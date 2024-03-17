export enum Platform {
    네이버,
    알리익스프레스,
    테무,
}

export const platformList: string[] = Object.keys(Platform).filter(key => isNaN(Number(key)));