import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HomepageComponent } from './homepage/homepage.component';
import { StoreComponent } from './store/store.component';
import { CartComponent } from './cart/cart.component';

import { LoginComponent } from './login/login.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { AnnouncementsComponent } from './announcements/announcements.component';
import { ContactComponent } from './contact/contact.component';
import { AboutComponent } from './about/about.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { AdminComponent } from './admin/admin.component';

import { ConfirmationComponent } from './confirmation/confirmation.component';
import { UserComponent } from './user/user.component';

import { MessageComponent } from './message/message.component';



@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    StoreComponent,
    CartComponent,
    SearchBarComponent,
    LoginComponent,
    AnnouncementsComponent,
    ContactComponent,
    AboutComponent,
    CheckoutComponent,
    AdminComponent,
    ConfirmationComponent,
    UserComponent,
    MessageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
