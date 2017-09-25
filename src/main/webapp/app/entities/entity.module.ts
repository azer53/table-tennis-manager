import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { TableTennisManagerTeamModule } from './team/team.module';
import { TableTennisManagerPlayerModule } from './player/player.module';
import { TableTennisManagerMatchModule } from './match/match.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        TableTennisManagerTeamModule,
        TableTennisManagerPlayerModule,
        TableTennisManagerMatchModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TableTennisManagerEntityModule {}
