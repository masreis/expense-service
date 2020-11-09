import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Expense } from './expense.model';
import { API_URL_EXPENSES } from "../app.constraints";

@Injectable({ providedIn: 'root' })
export class ExpenseService {
  private expenses: Expense[] = [];
  //  private expensesUpdated = new Subject<Expense[]>();

  constructor(private http: HttpClient) { }

  findAll() {
    return this.http.get<Expense[]>(API_URL_EXPENSES);
  }

  findById(id: number) {
    const url = API_URL_EXPENSES + "/" + id;
    return this.http.get<Expense>(url);
  }

  create(expense: Expense) {
    return this.http.post<Expense>(API_URL_EXPENSES, expense);
  }
}

