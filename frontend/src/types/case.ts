export type CaseStatus =
  | 'NEW'
  | 'OPEN'
  | 'IN_PROGRESS'
  | 'BLOCKED'
  | 'FINISHED'
  | 'CANCELLED';

export interface CaseProduct {
  articleNumber: number;
  name: string;
  quantity: number;
}

// Matches the CaseDTO returned by the backend
export interface Case {
  id: number;
  case_name: string;
  info: string;
  status: CaseStatus;
  customerId: number;
  products: CaseProduct[];
}
