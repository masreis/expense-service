import { ExpenseCreateComponent } from "./expenses/expense-create/expense-create.component";
import { ExpenseListComponent } from "./expenses/expense-list/expense-list.component";
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', component: ExpenseListComponent },
  { path: 'expense-create', component: ExpenseCreateComponent },
  { path: 'expense-list', component: ExpenseListComponent },
  { path: 'expense-edit/:id', component: ExpenseCreateComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
