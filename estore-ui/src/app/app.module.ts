import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { HomepageComponent } from './homepage/homepage.component';
import { StoreComponent } from './store/store.component';
import { SearchBarComponent } from './search-bar/search-bar.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    StoreComponent,
    SearchBarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
