import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

export interface TransactionRequest {
  accountId: string;
  amount: number;
  currency: string;
  referenceId: string;
}

export interface TransactionResponse {
  id: string;
  status: string;
  reason?: string;
  createdAt: string;
}

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private http = inject(HttpClient);
  private apiUrl = environment.apiUrl;

  createTransaction(request: TransactionRequest): Observable<TransactionResponse> {
    return this.http.post<TransactionResponse>(`${this.apiUrl}/transactions`, request);
  }
}
