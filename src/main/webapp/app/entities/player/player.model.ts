import { BaseEntity } from './../../shared';

export class Player implements BaseEntity {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public frenoyId?: string,
        public ranking?: string,
        public teams?: BaseEntity[],
        public homeMatches?: BaseEntity[],
        public awayMatches?: BaseEntity[],
    ) {
    }
}
