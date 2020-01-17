import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule} from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router'; 
import { RegistracijaComponent } from './components/registracija/registracija.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule }   from '@angular/forms';
import { LogovanjeComponent } from './components/logovanje/logovanje.component';
import { VerifikujProfilComponent } from './components/verifikuj-profil/verifikuj-profil.component';
import { MyTasksComponent } from './components/my-tasks/my-tasks.component';
import { OdobriRecezentaComponent } from './components/odobri-recezenta/odobri-recezenta.component';
import { KreirajCasopisComponent } from './components/kreiraj-casopis/kreiraj-casopis.component';
import { NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';
import { OdabiUrednikaComponent } from './components/odabi-urednika/odabi-urednika.component';
import { OdobriCasopisComponent } from './components/odobri-casopis/odobri-casopis.component';

const appRoutes : Routes = [
        {path: "registracija", component: RegistracijaComponent},
        {path: "verifikujProfil/:email/:pid", component: VerifikujProfilComponent},
        {path: "logovanje", component: LogovanjeComponent},
        {path: "mojiTaskovi", component: MyTasksComponent},
        {path: "odobriRecezenta/:taskId", component: OdobriRecezentaComponent},
        {path: "kreirajCasopis", component: KreirajCasopisComponent},
        {path: "odabirUrednika/:pid/:issn", component: OdabiUrednikaComponent},
        {path: "odobriCasopis/:taskId", component: OdobriRecezentaComponent},
]

@NgModule({
  declarations: [
    AppComponent,
    RegistracijaComponent,
    LogovanjeComponent,
    VerifikujProfilComponent,
    MyTasksComponent,
    OdobriRecezentaComponent,
    KreirajCasopisComponent,
    OdabiUrednikaComponent,
    OdobriCasopisComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgMultiSelectDropDownModule.forRoot(),
    RouterModule.forRoot(appRoutes,{enableTracing:true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
