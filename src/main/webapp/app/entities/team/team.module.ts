import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TableTennisManagerSharedModule } from '../../shared';
import { TableTennisManagerAdminModule } from '../../admin/admin.module';
import {
    TeamService,
    TeamPopupService,
    TeamComponent,
    TeamDetailComponent,
    TeamDialogComponent,
    TeamPopupComponent,
    TeamDeletePopupComponent,
    TeamDeleteDialogComponent,
    teamRoute,
    teamPopupRoute,
} from './';

const ENTITY_STATES = [
    ...teamRoute,
    ...teamPopupRoute,
];

@NgModule({
    imports: [
        TableTennisManagerSharedModule,
        TableTennisManagerAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TeamComponent,
        TeamDetailComponent,
        TeamDialogComponent,
        TeamDeleteDialogComponent,
        TeamPopupComponent,
        TeamDeletePopupComponent,
    ],
    entryComponents: [
        TeamComponent,
        TeamDialogComponent,
        TeamPopupComponent,
        TeamDeleteDialogComponent,
        TeamDeletePopupComponent,
    ],
    providers: [
        TeamService,
        TeamPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TableTennisManagerTeamModule {}
