import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TableTennisManagerSharedModule } from '../../shared';
import {
    PlayerService,
    PlayerPopupService,
    PlayerComponent,
    PlayerDetailComponent,
    PlayerDialogComponent,
    PlayerPopupComponent,
    PlayerDeletePopupComponent,
    PlayerDeleteDialogComponent,
    playerRoute,
    playerPopupRoute,
    PlayerResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...playerRoute,
    ...playerPopupRoute,
];

@NgModule({
    imports: [
        TableTennisManagerSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PlayerComponent,
        PlayerDetailComponent,
        PlayerDialogComponent,
        PlayerDeleteDialogComponent,
        PlayerPopupComponent,
        PlayerDeletePopupComponent,
    ],
    entryComponents: [
        PlayerComponent,
        PlayerDialogComponent,
        PlayerPopupComponent,
        PlayerDeleteDialogComponent,
        PlayerDeletePopupComponent,
    ],
    providers: [
        PlayerService,
        PlayerPopupService,
        PlayerResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TableTennisManagerPlayerModule {}
