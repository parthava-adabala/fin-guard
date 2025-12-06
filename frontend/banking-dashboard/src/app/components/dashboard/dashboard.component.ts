import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { TransactionService, TransactionResponse } from '../../services/transaction.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    MatSnackBarModule
  ],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  private fb = inject(FormBuilder);
  private transactionService = inject(TransactionService);
  private snackBar = inject(MatSnackBar);

  // Signal to handle loading state (Modern Angular)
  isLoading = signal(false);

  // Signal to store recent transactions
  recentTransactions = signal<TransactionResponse[]>([]);

  form = this.fb.group({
    accountId: ['ACC-001', Validators.required],
    amount: [500, [Validators.required, Validators.min(0.01)]],
    currency: ['USD', Validators.required],
    referenceId: [`REF-${Math.floor(Math.random() * 1000)}`, Validators.required]
  });

  submit() {
    if (this.form.invalid) return;

    this.isLoading.set(true);
    const request = this.form.getRawValue() as any;

    this.transactionService.createTransaction(request).subscribe({
      next: (res) => {
        this.isLoading.set(false);
        this.recentTransactions.update(list => [res, ...list]); // Update signal

        const msg = res.status === 'APPROVED' ? 'Transaction Approved!' : 'Transaction Rejected (Fraud)';
        this.snackBar.open(msg, 'Close', { duration: 3000, panelClass: res.status === 'APPROVED' ? 'success-snack' : 'error-snack' });

        // Reset reference ID for next test
        this.form.patchValue({ referenceId: `REF-${Math.floor(Math.random() * 1000)}` });
      },
      error: (err) => {
        this.isLoading.set(false);
        this.snackBar.open('System Error: Could not connect to backend', 'Close', { duration: 3000 });
        console.error(err);
      }
    });
  }
}
