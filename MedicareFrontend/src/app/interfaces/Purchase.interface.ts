import { Medicine } from './Medicine.interface';

export interface Purchase {
  id?: number;
  medicine: Medicine;
  quantity: number;
  totalAmount: number;
}
