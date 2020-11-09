import { Component } from '@angular/core';
import { Expense } from './expenses/expense.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'expenses-frontend';

  storedExpenses: Expense[] = [];

  onExpenseAdded(expense: Expense) {
    this.storedExpenses.push(expense);
  }

}
