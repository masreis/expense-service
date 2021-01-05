package net.expenseclient.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import net.expenseclient.model.dto.ExpenseDTO;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/v1/expenses")
public class ExpenseClientApiController {

  private ExpenseClientApi expenseClientApi;
  private static Logger log = LoggerFactory.getLogger(ExpenseClientApiController.class);

  @Autowired
  public ExpenseClientApiController(ExpenseClientApi expenseClientApi) {
    this.expenseClientApi = expenseClientApi;
  }

  @PostMapping
  @ApiOperation(value = "Route to create an expense")
  public ResponseEntity<ExpenseDTO> create(
      @Valid @RequestBody ExpenseDTO dto, BindingResult result) {
    if (result.hasErrors()) {
      String collect =
          result
              .getAllErrors()
              .stream()
              .map(ObjectError::getDefaultMessage)
              .collect(Collectors.joining(","));
      throw new ValidationException(collect);
    }
    log.info("create");
    return this.expenseClientApi.create(dto);
  }

  @GetMapping
  @ApiOperation(value = "Route to find all expenses")
  public ResponseEntity<List<ExpenseDTO>> findAll() {
    log.info("findAll");
    return this.expenseClientApi.findAll();
  }

  @PutMapping("/{id}")
  @ApiOperation(value = "Route to update an expense")
  public ResponseEntity<ExpenseDTO> update(
      @PathVariable final Long id, @Valid @RequestBody ExpenseDTO dto) {
    return this.expenseClientApi.update(id, dto);
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Route to find expense by id")
  public ResponseEntity<ExpenseDTO> findById(@PathVariable final Long id) {
    ResponseEntity<ExpenseDTO> findById = this.expenseClientApi.findById(id);
    return findById;
  }
}
