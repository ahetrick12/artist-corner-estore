import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { StoreComponent } from './store/store.component';
import { CartComponent } from './cart/cart.component';
import { AnnouncementsComponent } from './announcements/announcements.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { AdminComponent } from './admin/admin.component';
import { ContactComponent } from './contact/contact.component';
import { AboutComponent } from './about/about.component';

import { ConfirmationComponent } from './confirmation/confirmation.component';
import { UserComponent } from './user/user.component';

import { MessageComponent } from './message/message.component';


const routes: Routes = [
  { path: '', redirectTo: '/homepage', pathMatch: 'full' },
  { path: 'homepage', component: HomepageComponent },
  { path: 'store', component: StoreComponent },
  { path: 'cart', component: CartComponent },
  { path: 'login', component: LoginComponent },
  { path: 'announcements', component: AnnouncementsComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'about', component: AboutComponent },

  { path: 'confirmation', component: ConfirmationComponent },
  { path: 'user', component: UserComponent },

  { path: 'message', component: MessageComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
