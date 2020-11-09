import { Component, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Expense } from '../expense.model';
import { ExpenseService } from '../expense.service';

@Component({
  selector: 'app-expense-list',
  templateUrl: './expense-list.component.html',
  styleUrls: ['./expense-list.component.css']
})
export class ExpenseListComponent implements OnInit {

  expenses: Expense[] = [];

  displayedColumns: string[] = ['position', 'name', 'description', 'dateTime', 'value', 'tags', 'edit'];

  constructor(
    public expenseService: ExpenseService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.expenseService.findAll().subscribe(
      (response) => {
        console.log(response);
        this.expenses = response
      }
    );

  }

  edit(id: number) {
    this.router.navigate(['expense-edit', id])
  }

}
