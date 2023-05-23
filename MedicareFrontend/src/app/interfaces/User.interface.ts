import { Cart } from './Cart.interface';
import { Purchase } from './Purchase.interface';

export interface User {
  id?: number;
  email: String;
  name?: String;
  password?: String;
  cart?: Cart[];
  purchases?: Purchase[];
}
