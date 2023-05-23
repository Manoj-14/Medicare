import { Medicine } from './Medicine.interface';

export interface Cart {
  id?: number;
  medicines: Medicine;
  quantity: number;
}
