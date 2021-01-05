import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Expense } from '../expense.model';
import { ExpenseService } from '../expense.service';

@Component({
  selector: "app-expense-create",
  templateUrl: "./expense-create.component.html",
  styleUrls: ["./expense-create.component.css"]
})
export class ExpenseCreateComponent implements OnInit {

  id: number = 0;
  expense = {} as Expense;
  message: string;

  constructor(
    private expenseService: ExpenseService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    console.log("id: " + this.id);
    if (this.id > 0) {
      this.expenseService.findById(this.id).subscribe(
        (response) => {
          this.expense = response;
        }, error => {
          this.message = "Id [" + this.id + "] not found";
        }
      );
    }
  }

  onAddExpense(form: NgForm) {
    if (form.invalid) {
      return;
    }
    const operation = this.id > 0 ?
      this.expenseService.update(this.id, this.expense) :
      this.expenseService.create(this.expense);

    operation.subscribe((response) => {
      this.message = 'Expense id [' + response.id + '] saved';
      if (isNaN(this.id)) form.resetForm();
    }, (error) => {
      console.log(error);
      this.message = JSON.stringify(error.error);
    });
  }

}
