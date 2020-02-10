import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule} from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router'; 
import { RegistracijaComponent } from './components/registracija/registracija.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule }   from '@angular/forms';
import { LogovanjeComponent } from './components/logovanje/logovanje.component';
import { VerifikujProfilComponent } from './components/verifikuj-profil/verifikuj-profil.component';
import { MyTasksComponent } from './components/my-tasks/my-tasks.component';
import { OdobriRecezentaComponent } from './components/odobri-recezenta/odobri-recezenta.component';
import { KreirajCasopisComponent } from './components/kreiraj-casopis/kreiraj-casopis.component';
import { NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';
import { OdabiUrednikaComponent } from './components/odabi-urednika/odabi-urednika.component';
import { OdobriCasopisComponent } from './components/odobri-casopis/odobri-casopis.component';
import { StartTaskComponent } from './components/start-task/start-task.component';
import { OdabirCasopisaComponent } from './components/odabir-casopisa/odabir-casopisa.component';
import { KreiranjeRadaComponent } from './components/kreiranje-rada/kreiranje-rada.component';
import { AuthInterceptor } from './auth/auth-interceptor';
import { PregledRadaComponent } from './components/pregled-rada/pregled-rada.component';
import { PregledPdfComponent } from './components/pregled-pdf/pregled-pdf.component';
import { IspravkaPdfComponent } from './components/ispravka-pdf/ispravka-pdf.component';
import { IzaberiRecezentaComponent } from './components/izaberi-recezenta/izaberi-recezenta.component';
import { RecenzijaRadaComponent } from './components/recenzija-rada/recenzija-rada.component';
import { OdlukaUrednikaComponent } from './components/odluka-urednika/odluka-urednika.component';
import { IzmentaTekstaComponent } from './components/izmenta-teksta/izmenta-teksta.component';

const appRoutes : Routes = [
        {path: "registracija", component: RegistracijaComponent},
        {path: "verifikujProfil/:email/:pid", component: VerifikujProfilComponent},
        {path: "logovanje", component: LogovanjeComponent},
        {path: "mojiTaskovi", component: MyTasksComponent},
        {path: "odobriRecezenta/:taskId", component: OdobriRecezentaComponent},
        {path: "kreirajCasopis", component: KreirajCasopisComponent},
        {path: "odabirUrednika/:pid/:issn", component: OdabiUrednikaComponent},
        {path: "odobriCasopis/:taskId", component: OdobriCasopisComponent},
        {path: "startTask", component:StartTaskComponent },
        {path: "odabirCasopisa", component: OdabirCasopisaComponent },
        {path: "kreiranjeRada/:id", component: KreiranjeRadaComponent },
        {path: "pregledRada/:id", component:PregledRadaComponent },
        {path: "pregledPdf/:id", component:PregledPdfComponent },
        {path: "ispravkaPdf/:id", component:IspravkaPdfComponent },
        {path: "izaberiRecezenta/:id", component:IzaberiRecezentaComponent },
        {path: "recenzijaRada/:id", component:RecenzijaRadaComponent },
        {path: "odlukaUrednika/:id", component: OdlukaUrednikaComponent },
        {path: "izmenaTeksta/:id", component: IzmentaTekstaComponent},
        {path: "", component:LogovanjeComponent }
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
    OdobriCasopisComponent,
    StartTaskComponent,
    OdabirCasopisaComponent,
    KreiranjeRadaComponent,
    PregledRadaComponent,
    PregledPdfComponent,
    IspravkaPdfComponent,
    IzaberiRecezentaComponent,
    RecenzijaRadaComponent,
    OdlukaUrednikaComponent,
    IzmentaTekstaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgMultiSelectDropDownModule.forRoot(),
    RouterModule.forRoot(appRoutes,{enableTracing:true})
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
