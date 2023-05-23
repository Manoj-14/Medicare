import { Medicine } from './Medicine.interface';

export interface Purchase {
  id?: number;
  medicines: Medicine;
  quantity: number;
  totalAmount: number;
}
