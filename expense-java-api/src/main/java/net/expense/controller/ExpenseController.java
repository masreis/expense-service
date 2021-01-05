package net.expense.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.expense.model.dto.ExpenseDTO;
import net.expense.service.ExpenseService;

@RestController
@RequestMapping("/v1/expenses")
@CrossOrigin("*")
public class ExpenseController {

  private ExpenseService expenseService;

  private ExpenseController(ExpenseService expenseService) {
    this.expenseService = expenseService;
  }

  @PostMapping
  @ApiOperation(value = "Route to create an expense")
  public ResponseEntity<ExpenseDTO> create(
      @Valid @RequestBody ExpenseDTO expenseDto, BindingResult result) throws ValidationException {
    if (result.hasErrors()) {
      throw new ValidationException(
          result
              .getAllErrors()
              .stream()
              .map(ObjectError::getDefaultMessage)
              .collect(Collectors.joining(",")));
    }
    ExpenseDTO saved = expenseService.save(expenseDto);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @ApiOperation(value = "Route to update an expense")
  public ResponseEntity<ExpenseDTO> update(
      @PathVariable final Long id, @Valid @RequestBody ExpenseDTO dto, BindingResult result)
      throws ValidationException {
    if (result.hasErrors()) {
      throw new ValidationException(
          result
              .getAllErrors()
              .stream()
              .map(ObjectError::getDefaultMessage)
              .collect(Collectors.joining(",")));
    }
    ExpenseDTO updated = expenseService.update(dto);
    return new ResponseEntity<>(updated, HttpStatus.OK);
  }

  @GetMapping
  @ApiOperation(value = "Route to find all expenses")
  public ResponseEntity<List<ExpenseDTO>> findAll() {
    List<ExpenseDTO> dtos = expenseService.findAll();
    return new ResponseEntity<>(dtos, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Route to find expense by id")
  public ResponseEntity<ExpenseDTO> findById(@PathVariable final Long id) {
    ExpenseDTO expense = expenseService.findById(id);
    return new ResponseEntity<>(expense, HttpStatus.OK);
  }
}
