import { CartItem } from './cartitem';

export interface User {
  username: string;
  password: string;
  cart: CartItem[];
  imageLink: string;
}
