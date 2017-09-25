import { BaseEntity } from './../../shared';

export class Match implements BaseEntity {
    constructor(
        public id?: number,
        public homePlayerSets?: number,
        public awayPlayerSets?: number,
        public homePlayer?: BaseEntity,
        public awayPlayer?: BaseEntity,
    ) {
    }
}
